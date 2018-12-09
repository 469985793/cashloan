package com.xindaibao.cashloan.cl.service.creditInfo.impl;

import com.creditinfo.schemas._2012._09.multiconnector.MultiConnectorRequest;
import com.creditinfo.schemas._2012._09.multiconnector.MultiConnectorResponse;
import com.creditinfo.schemas._2012._09.multiconnector.MultiConnectorTicket;
import com.creditinfo.schemas._2012._09.multiconnector.connectors._int.idmstrategy.request.Request;
import com.creditinfo.schemas._2012._09.multiconnector.messages.request.RequestXml;
import com.xindaibao.cashloan.cl.mapper.CreditInfoMapper;
import com.xindaibao.cashloan.cl.mapper.LoanRecordMapper;
import com.xindaibao.cashloan.cl.model.CreditInfo.CreditInfoLog;
import com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg.CreditInfoResponse;
import com.xindaibao.cashloan.cl.model.kenya.LoanRecord;
import com.xindaibao.cashloan.cl.service.creditInfo.GetCreditInfoResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.tempuri.MultiConnectorService;
import org.tempuri.MultiConnectorService_Service;
import org.w3c.dom.Element;

import javax.annotation.Resource;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.PortInfo;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/2
 */
public class GetCreditInfoResponseServiceImpl implements GetCreditInfoResponseService {

    Logger logger = LoggerFactory.getLogger(GetCreditInfoResponseServiceImpl.class);

    @Autowired
    private CreditInfoMapper creditInfoMapper;

    @Resource
    private LoanRecordMapper loanRecordMapper;
//    @Resource
//    private PaymentService paymentService;



    static final String serviceUrl = "https://idm.creditinfo.co.ke/multiconnector.svc"; //URL OF WEB SERVICE API OF IDM
    static final String strategyId = "2cd2e283-b340-430b-9d16-a52b7de45a14"; //STRATEGY ID GIVEN BY CIK
    static final String username = "antelope"; //CREDENTIALS TO IDM
    static final String password = "ZnPdX7ks"; //CREDENTIALS TO IDM
    static final String connectorId = "1c8f01f8-71a2-4c99-98a1-8bd1d85c4f63"; //CONNECTOR IDENTIFICATIO
    static final String companyStrategyId = "053c1f9c-d105-4006-af62-70a7ee158e13"; //STRATEGY ID GIVEN BY CIK
    static final String ruleA = "A1/A2/A3/B1/B2/B3";
    static final String ruleB = "C1/C2/C3/D1/D2/D3";
    static final String ruleC = "E1/E2/E3/F1/F2/F3";
    static final String ruleX = "XX";


    /**
     * 请求征信接口验证
     * @param nationalId
     * @param loanRecord
     * @return
     * @throws Exception
     */
    @Override
    public CreditInfoResponse getCreditInfoResponse(String nationalId, LoanRecord loanRecord)throws Exception {
        MultiConnectorService_Service service = new MultiConnectorService_Service(new URL(serviceUrl));
        service.setHandlerResolver(new HeaderHandlerResolver(username, password));
        MultiConnectorService mcService = service.getBasicHttpBindingMultiConnectorService();

        MultiConnectorRequest multiConnectorRequest = new MultiConnectorRequest();
        multiConnectorRequest.setMessageId(UUID.randomUUID().toString());
        multiConnectorRequest.setRequestXml(createRequestXmlIndividualIdm(nationalId));
        MultiConnectorTicket beginQueryResponse = mcService.beginQuery(multiConnectorRequest);

        logger.info("CreditInfo Response MessageID :" + beginQueryResponse.getMessageId());

        MultiConnectorResponse endQueryResponse = getReport(mcService, beginQueryResponse);
        MultiConnectorResponse.ResponseXml responseXml = endQueryResponse.getResponseXml();
        com.creditinfo.schemas._2012._09.multiconnector.messages.response.Response response1 = (com.creditinfo.schemas._2012._09.multiconnector.messages.response.Response)responseXml.getAny();
        com.creditinfo.schemas._2012._09.multiconnector.connectors._int.idmstrategy.response.Response response3 = (com.creditinfo.schemas._2012._09.multiconnector.connectors._int.idmstrategy.response.Response ) response1.getConnector().get(0).getData().getContent().get(0);

        CreditInfoResponse creditInfoResponse = new CreditInfoResponse();
        creditInfoResponse.setCreditLimit(new BigDecimal(response3.getAny().get(3).getElementsByTagName("CreditLimit").item(0).getTextContent()));
        creditInfoResponse.setResultCode(response3.getAny().get(3).getElementsByTagName("RecommendedDecision").item(0).getTextContent());
        creditInfoResponse.setSubjectIDNumber(response3.getAny().get(3).getElementsByTagName("SubjectIDNumber").item(0).getTextContent());
        creditInfoResponse.setFullName(response3.getAny().get(4).getElementsByTagName("FullName").item(0).getTextContent());
        creditInfoResponse.setCIPRiskGrade(response3.getAny().get(5).getElementsByTagName("CIPRiskGrade").item(0).getTextContent());
        creditInfoResponse.setMobileScoreRiskGrade(response3.getAny().get(5).getElementsByTagName("MobileScoreRiskGrade").item(0).getTextContent());
        //保存征信接口请求日志
        saveResponseLog(creditInfoResponse,beginQueryResponse.getMessageId(),response3.getAny());

        //征信规则验证
        creditInfoRule(loanRecord,creditInfoResponse);
        return creditInfoResponse;
    }

    /**
     *  保存征信接口返回参数
     * @param creditInfoResponse
     * @param messageId
     * @param objectResponse
     */
    public void saveResponseLog(CreditInfoResponse creditInfoResponse,String messageId, Object objectResponse){

        CreditInfoLog creditInfoLog = new CreditInfoLog();
        creditInfoLog.setCIPRiskGrade(creditInfoResponse.getCIPRiskGrade());
        creditInfoLog.setSubjectIDNumber(creditInfoResponse.getSubjectIDNumber());
        creditInfoLog.setResultCode(creditInfoResponse.getResultCode());
        creditInfoLog.setFullName(creditInfoResponse.getFullName());
        creditInfoLog.setMessageId(messageId);
        creditInfoLog.setMobileScoreGrade(creditInfoResponse.getMobileScoreRiskGrade());
        creditInfoLog.setCreditLimit(creditInfoResponse.getCreditLimit());
        creditInfoLog.setRemark(objectResponse.toString());
        creditInfoLog.setStatus("1");
        creditInfoLog.setCreatedTime(new Date());
        creditInfoLog.setUpdatedTime(new Date());

        creditInfoMapper.save(creditInfoLog);

    }

    /**
     * 征信接口返回规则验证
     * @param loanRecord
     * @param creditInfoResponse
     */
    public void creditInfoRule(LoanRecord loanRecord,CreditInfoResponse creditInfoResponse){

        LoanRecord loan = new LoanRecord();
        loan.setId(loanRecord.getId());
        loan = loanRecordMapper.findByPrimary(loanRecord.getId());
        if (loan == null || loan.getId() == null) {
            logger.error("未找到单号为{}的订单记录", loanRecord.getIndentNo());
        }

        StringBuilder stringBuilder = new StringBuilder();
        logger.info("CIPCode 验证： code :" +creditInfoResponse.getCIPRiskGrade());
        String name = stringBuilder.append(loanRecord.getFirstName().toLowerCase()).append(" ").append(loanRecord.getLastName().toLowerCase()).toString();
        if (boolResultCode(creditInfoResponse.getCIPRiskGrade(), ruleA)) {
            logger.info("requestCode 验证：  :" +creditInfoResponse.getResultCode());
            logger.info("limit 验证：  :" +boolResultLimit(creditInfoResponse.getCreditLimit(), loanRecord.getBalance()));
            if (creditInfoResponse.getResultCode().equals("Approve") && boolResultLimit(creditInfoResponse.getCreditLimit(), loanRecord.getBalance())) {
                logger.info("name 验证：  :" +boolResultName(creditInfoResponse.getFullName().toLowerCase(), name) +" "+name +"   "+creditInfoResponse.getFullName().toLowerCase());
                if (boolResultName(creditInfoResponse.getFullName().toLowerCase(), name)) {
                    loan.setStatus((byte) 4);
                }else{
                    loan.setStatus((byte)2);
                }
            } else {
                if (!boolResultLimit(creditInfoResponse.getCreditLimit(), loanRecord.getBalance())) {
                    loan.setStatus((byte)33);
                }

            }
        }
        if (boolResultCode(creditInfoResponse.getCIPRiskGrade(), ruleB)) {
            logger.info("requestCode 验证：  :" +creditInfoResponse.getResultCode());
            logger.info("limit 验证：  :" +boolResultLimit(creditInfoResponse.getCreditLimit(), loanRecord.getBalance()));
            if (creditInfoResponse.getResultCode().equals("Approve") && boolResultLimit(creditInfoResponse.getCreditLimit(), loanRecord.getBalance())) {
                loan.setStatus((byte)2);
            } else {
                if (!boolResultLimit(creditInfoResponse.getCreditLimit(), loanRecord.getBalance())) {
                    loan.setStatus((byte)33);
                }
            }
        }
        if (boolResultCode(creditInfoResponse.getCIPRiskGrade(), ruleC)) {
            loan.setStatus((byte)32);
        }
        if (boolResultCode(creditInfoResponse.getCIPRiskGrade(), ruleX)) {
            if (creditInfoResponse.getResultCode().equals("Approve") && boolResultLimit(creditInfoResponse.getCreditLimit(),  loanRecord.getBalance())) {
                loan.setStatus((byte)4);
            } else {
                loan.setStatus((byte)32);
            }
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", loanRecord.getId());
        paramMap.put("state", loan.getStatus());
        paramMap.put("updatedTime",new Date());

        Integer i = loanRecordMapper.updateStatus(paramMap);
        if(i > 0){
            logger.error("修改单号为{}的订单异常", loanRecord.getIndentNo());
        }else{
//            if(loan.getStatus() ==4){
//                loanRecord.setStatus((byte) 4);
//                paymentService.createPayOrderNo(loanRecord, 2); // 自动放款
//            }

        }

    }

    private static MultiConnectorResponse getReport(MultiConnectorService mcService, MultiConnectorTicket ticket) throws Exception
    {
        int tryCount = 30;
        while(tryCount > 0)
        {
            System.out.println(String.format("Trying to get report response, tryCount: %s", 31 - tryCount));
            try
            {
                MultiConnectorRequest multiConnectorRequest = new MultiConnectorRequest();
                multiConnectorRequest.setMessageId(UUID.randomUUID().toString());
                MultiConnectorResponse response = mcService.endQuery(ticket);
                return response;
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
            Thread.sleep(5000);
            tryCount--;
        }
        throw new Exception("report was not generated within 150s");
    }


    /**
     * 验证借款金额是否超过可借金额
     */
    private boolean boolResultLimit(BigDecimal resultLimit, Long balance){
        boolean result = false;
        BigDecimal balances = new BigDecimal(balance);
        if(resultLimit.compareTo(balances)== 0 || resultLimit.compareTo(balances) > 0){
            result = true;
        }
        return  result;
    }

    /**
     * 姓名验证
     */
    private boolean boolResultName(String name, String  fullName){
        boolean result = false;

        String[] chrstr = fullName.split(" ");
        String[] chrstr1 = name.split(" ");
        List names = new ArrayList();
        List names1 = new ArrayList();
        for(int i=0;i<chrstr.length;i++){
            names.add(chrstr[i]);
        }
        for(int i=0;i<chrstr1.length;i++){
            names1.add(chrstr1[i]);
        }
        if(names.size() == names1.size()){
            for(int i = 0; i<names.size() ; i++){
                if(!names.get(i).equals(names1.get(i))){
                    return result;
                }
            }
            result = true;
        }else if(names.size() > names1.size()){
            if(!names.get(0).equals(names1.get(0))){
                return result;
            }else if(!names.get(names.size()-1).equals(names1.get(names1.size()-1))){
                return result;
            }
            result = true;
        }
        return  result;
    }

    /**
     * 资格验证
     */
    private static boolean boolResultCode(String code, String rule){
        boolean result = false;
        String[] chrstr_rule = rule.split("/");
        List rules = new ArrayList();
        for(int i=0;i<chrstr_rule.length;i++){
            rules.add(chrstr_rule[i]);
        }
        for(int i=0;i<rules.size();i++){
            if(code.split(rules.get(i).toString()).length == 0){
                return  true;
            }
        }
        return  result;
    }




    private static MultiConnectorRequest.RequestXml createRequestXmlIndividualIdm(String nationalId) throws Exception
    {
        Request request = new Request();
        request.setConsent(true);
        Request.Strategy strategy = new Request.Strategy();
        strategy.setId(strategyId);
        request.setStrategy(strategy);
        request.setCb5SearchParameters(getIndividualSearchParameters(nationalId));
        request.setCustomFields(getCustomFields());

        MultiConnectorRequest.RequestXml requestXml = new MultiConnectorRequest.RequestXml();
        requestXml.setRequestXml(new com.creditinfo.schemas._2012._09.multiconnector.messages.request.RequestXml());
        requestXml.getRequestXml().getConnector().add(getConnector(request));
        return requestXml;
    }

    private static MultiConnectorRequest.RequestXml createRequestXmlCompanyIdm() throws Exception
    {
        Request request = new Request();
        request.setConsent(true);
        Request.Strategy strategy = new Request.Strategy();
        strategy.setId(companyStrategyId);
        request.setStrategy(strategy);

        request.setCb5SearchParameters(getCompanySearchParameters("C125446"));
        request.setCustomFields(getCustomFieldsCompany());

        MultiConnectorRequest.RequestXml requestXml = new MultiConnectorRequest.RequestXml();
        requestXml.setRequestXml(new com.creditinfo.schemas._2012._09.multiconnector.messages.request.RequestXml());
        requestXml.getRequestXml().getConnector().add(getConnector(request));
        return requestXml;
    }

    private static Request.Cb5SearchParameters getIndividualSearchParameters(String nationalId) throws Exception
    {
        Request.Cb5SearchParameters searchParams = new Request.Cb5SearchParameters();
        searchParams.getAny().add(createElement("NationalId", nationalId));
        return searchParams;
    }

    private static Request.Cb5SearchParameters getCompanySearchParameters(String registrationNumber) throws Exception
    {
        Request.Cb5SearchParameters searchParams = new Request.Cb5SearchParameters();
        searchParams.getAny().add(createElement("RegistrationNumber", registrationNumber));
        return searchParams;
    }

    private static Request.CustomFields getCustomFields() throws Exception
    {
        Request.CustomFields customFields = new Request.CustomFields();
        customFields.getAny().add(createElement("Age", "35"));
        customFields.getAny().add(createElement("Gender", "MALE"));
        customFields.getAny().add(createElement("IsEmployed", "YES"));
        customFields.getAny().add(createElement("IsHFCCustomer", "YES"));
        customFields.getAny().add(createElement("IsKenyan", "YES"));
        customFields.getAny().add(createElement("AgeOnWhizz", "18"));
        customFields.getAny().add(createElement("OkoaJahaziCount", "1"));
        customFields.getAny().add(createElement("AvgOkoaJahaziAmount", "175000"));
        customFields.getAny().add(createElement("AvgBankTurnovers", "2000000"));
        customFields.getAny().add(createElement("LoanRepaymentCount", "2"));
        customFields.getAny().add(createElement("AvgmPesaCredit6M", "15000"));
        customFields.getAny().add(createElement("AvgAirtimePurchase", "165000"));
        customFields.getAny().add(createElement("PaidMobileLoansCount", "2"));
        customFields.getAny().add(createElement("Education", "NONE"));
        customFields.getAny().add(createElement("Employment", "EMPLOYED"));
        customFields.getAny().add(createElement("LoanPurpose", "BUSINESS"));
        customFields.getAny().add(createElement("mpesa_transaction_count", "1"));
        customFields.getAny().add(createElement("mpesa_total_inout_2mon", "1"));
        return customFields;
    }

    private static Request.CustomFields getCustomFieldsCompany() throws Exception
    {
        Request.CustomFields customFields = new Request.CustomFields();
        return customFields;
    }

    private static Element createElement(String elementName, String elementValue) throws Exception
    {
        Element e = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .newDocument()
                .createElementNS("http://creditinfo.com/schemas/2012/09/MultiConnector/Connectors/INT/IdmStrategy/Request", elementName);
        e.setTextContent(elementValue);
        return e;
    }

    private static RequestXml.Connector getConnector(Request request)
    {
        RequestXml.Connector.Data data = new RequestXml.Connector.Data();
        data.getContent().add(request);
        data.setId(UUID.randomUUID().toString());
        RequestXml.Connector connector = new RequestXml.Connector();
        connector.setData(data);
        connector.setId(connectorId);
        return connector;
    }
}

class HeaderHandler implements SOAPHandler<SOAPMessageContext>
{

    public static final String WSSE_NS = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
    public static final String SECURITY_TOKEN_FORMAT = "SecurityToken-%s";
    public static final String WSSE = "wsse";

    private String username;
    private String password;

    public HeaderHandler(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context)
    {

        Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (outboundProperty.booleanValue())
        {
            SOAPMessage message = context.getMessage();

            try
            {
                SOAPEnvelope envelope = context.getMessage().getSOAPPart().getEnvelope();
                if (envelope.getHeader() != null)
                {
                    envelope.getHeader().detachNode();
                }
                SOAPHeader header = envelope.addHeader();

                SOAPElement security = header.addChildElement("Security", WSSE, WSSE_NS);

                SOAPElement usernameToken = security.addChildElement("UsernameToken", WSSE);
                usernameToken.addAttribute(new QName("xmlns:wsu"), WSSE_NS);
                usernameToken.addAttribute(new QName("wsu:Id"), String.format(SECURITY_TOKEN_FORMAT, UUID.randomUUID().toString()));
                SOAPElement usernameElement = usernameToken.addChildElement("Username", WSSE);
                SOAPElement passwordElement = usernameToken.addChildElement("Password", WSSE);
                passwordElement.setAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");

                usernameElement.setTextContent(username);
                passwordElement.setTextContent(password);

                // Print out the outbound SOAP message
                System.out.println("Request:");
                message.writeTo(System.out);
                System.out.println("");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            try
            {
                SOAPMessage message = context.getMessage();
                // Print out the inbound SOAP message
                System.out.println("Response:");
                message.writeTo(System.out);
                System.out.println("");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void close(MessageContext context)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public Set<QName> getHeaders()
    {
        // TODO Auto-generated method stub
        return null;
    }
}

class HeaderHandlerResolver implements HandlerResolver {

    private String username;
    private String password;

    public HeaderHandlerResolver(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public List<Handler> getHandlerChain(PortInfo portInfo)
    {
        List<Handler> handlerChain = new ArrayList<Handler>();
        HeaderHandler headerHandler = new HeaderHandler(username, password);
        handlerChain.add(headerHandler);
        return handlerChain;
    }
}
