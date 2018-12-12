
package com.creditinfo.schemas._2012._09.multiconnector;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.lang.*;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.creditinfo.schemas._2012._09.multiconnector package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _MultiConnectorResponse_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "MultiConnectorResponse");
    private final static QName _ConnectorSchemas_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "ConnectorSchemas");
    private final static QName _MultiConnectorRequest_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "MultiConnectorRequest");
    private final static QName _Error_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "Error");
    private final static QName _UnsupportedSecurityToken_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "UnsupportedSecurityToken");
    private final static QName _Timeout_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "Timeout");
    private final static QName _ArrayOfConnectorInfo_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "ArrayOfConnectorInfo");
    private final static QName _ConnectorInfo_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "ConnectorInfo");
    private final static QName _ConnectorsListRequest_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "ConnectorsListRequest");
    private final static QName _ConnectorSchemaRequest_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "ConnectorSchemaRequest");
    private final static QName _InProgress_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "InProgress");
    private final static QName _MultiConnectorTicket_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "MultiConnectorTicket");
    private final static QName _ArrayOfConnectorSchemas_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "ArrayOfConnectorSchemas");
    private final static QName _ConnectorsListResponse_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "ConnectorsListResponse");
    private final static QName _FailedAuthentication_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "FailedAuthentication");
    private final static QName _ConnectorSchemaResponse_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "ConnectorSchemaResponse");
    private final static QName _EndQueryTicket_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "ticket");
    private final static QName _QueryRequest_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "request");
    private final static QName _ListConnectorsResponseListConnectorsResult_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "ListConnectorsResult");
    private final static QName _ConnectorsListResponseConnectors_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "Connectors");
    private final static QName _ErrorMessage_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "Message");
    private final static QName _EndQueryResponseEndQueryResult_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "EndQueryResult");
    private final static QName _MultiConnectorResponseOperationCode_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "OperationCode");
    private final static QName _MultiConnectorRequestScheduledTime_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "ScheduledTime");
    private final static QName _TimeoutValue_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "Value");
    private final static QName _QueryResponseQueryResult_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "QueryResult");
    private final static QName _BeginQueryResponseBeginQueryResult_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "BeginQueryResult");
    private final static QName _ConnectorInfoDescription_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "Description");
    private final static QName _SchemaResponseSchemaResult_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector", "SchemaResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.creditinfo.schemas._2012._09.multiconnector
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConnectorSchemas }
     * 
     */
    public ConnectorSchemas createConnectorSchemas() {
        return new ConnectorSchemas();
    }

    /**
     * Create an instance of {@link MultiConnectorResponse }
     * 
     */
    public MultiConnectorResponse createMultiConnectorResponse() {
        return new MultiConnectorResponse();
    }

    /**
     * Create an instance of {@link MultiConnectorRequest }
     * 
     */
    public MultiConnectorRequest createMultiConnectorRequest() {
        return new MultiConnectorRequest();
    }

    /**
     * Create an instance of {@link Query }
     * 
     */
    public Query createQuery() {
        return new Query();
    }

    /**
     * Create an instance of {@link ConnectorSchemaResponse }
     * 
     */
    public ConnectorSchemaResponse createConnectorSchemaResponse() {
        return new ConnectorSchemaResponse();
    }

    /**
     * Create an instance of {@link Schema }
     * 
     */
    public Schema createSchema() {
        return new Schema();
    }

    /**
     * Create an instance of {@link ConnectorSchemaRequest }
     * 
     */
    public ConnectorSchemaRequest createConnectorSchemaRequest() {
        return new ConnectorSchemaRequest();
    }

    /**
     * Create an instance of {@link FailedAuthentication }
     * 
     */
    public FailedAuthentication createFailedAuthentication() {
        return new FailedAuthentication();
    }

    /**
     * Create an instance of {@link ArrayOfConnectorSchemas }
     * 
     */
    public ArrayOfConnectorSchemas createArrayOfConnectorSchemas() {
        return new ArrayOfConnectorSchemas();
    }

    /**
     * Create an instance of {@link ConnectorsListResponse }
     * 
     */
    public ConnectorsListResponse createConnectorsListResponse() {
        return new ConnectorsListResponse();
    }

    /**
     * Create an instance of {@link MultiConnectorTicket }
     * 
     */
    public MultiConnectorTicket createMultiConnectorTicket() {
        return new MultiConnectorTicket();
    }

    /**
     * Create an instance of {@link EndQueryResponse }
     * 
     */
    public EndQueryResponse createEndQueryResponse() {
        return new EndQueryResponse();
    }

    /**
     * Create an instance of {@link InProgress }
     * 
     */
    public InProgress createInProgress() {
        return new InProgress();
    }

    /**
     * Create an instance of {@link SchemaResponse }
     * 
     */
    public SchemaResponse createSchemaResponse() {
        return new SchemaResponse();
    }

    /**
     * Create an instance of {@link ConnectorsListRequest }
     * 
     */
    public ConnectorsListRequest createConnectorsListRequest() {
        return new ConnectorsListRequest();
    }

    /**
     * Create an instance of {@link EndQuery }
     * 
     */
    public EndQuery createEndQuery() {
        return new EndQuery();
    }

    /**
     * Create an instance of {@link ArrayOfConnectorInfo }
     * 
     */
    public ArrayOfConnectorInfo createArrayOfConnectorInfo() {
        return new ArrayOfConnectorInfo();
    }

    /**
     * Create an instance of {@link ConnectorInfo }
     * 
     */
    public ConnectorInfo createConnectorInfo() {
        return new ConnectorInfo();
    }

    /**
     * Create an instance of {@link Timeout }
     * 
     */
    public Timeout createTimeout() {
        return new Timeout();
    }

    /**
     * Create an instance of {@link UnsupportedSecurityToken }
     * 
     */
    public UnsupportedSecurityToken createUnsupportedSecurityToken() {
        return new UnsupportedSecurityToken();
    }

    /**
     * Create an instance of {@link BeginQuery }
     * 
     */
    public BeginQuery createBeginQuery() {
        return new BeginQuery();
    }

    /**
     * Create an instance of {@link Error }
     *
     */
    public Error createError() {
        return new Error();
    }

    /**
     * Create an instance of {@link QueryResponse }
     *
     */
    public QueryResponse createQueryResponse() {
        return new QueryResponse();
    }

    /**
     * Create an instance of {@link BeginQueryResponse }
     *
     */
    public BeginQueryResponse createBeginQueryResponse() {
        return new BeginQueryResponse();
    }

    /**
     * Create an instance of {@link ListConnectors }
     *
     */
    public ListConnectors createListConnectors() {
        return new ListConnectors();
    }

    /**
     * Create an instance of {@link ListConnectorsResponse }
     *
     */
    public ListConnectorsResponse createListConnectorsResponse() {
        return new ListConnectorsResponse();
    }

    /**
     * Create an instance of {@link ConnectorSchemas.InputSchema }
     *
     */
    public ConnectorSchemas.InputSchema createConnectorSchemasInputSchema() {
        return new ConnectorSchemas.InputSchema();
    }

    /**
     * Create an instance of {@link ConnectorSchemas.OutputSchema }
     *
     */
    public ConnectorSchemas.OutputSchema createConnectorSchemasOutputSchema() {
        return new ConnectorSchemas.OutputSchema();
    }

    /**
     * Create an instance of {@link MultiConnectorResponse.ResponseXml }
     *
     */
    public MultiConnectorResponse.ResponseXml createMultiConnectorResponseResponseXml() {
        return new MultiConnectorResponse.ResponseXml();
    }

    /**
     * Create an instance of {@link MultiConnectorRequest.RequestXml }
     *
     */
    public MultiConnectorRequest.RequestXml createMultiConnectorRequestRequestXml() {
        return new MultiConnectorRequest.RequestXml();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiConnectorResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "MultiConnectorResponse")
    public JAXBElement<MultiConnectorResponse> createMultiConnectorResponse(MultiConnectorResponse value) {
        return new JAXBElement<MultiConnectorResponse>(_MultiConnectorResponse_QNAME, MultiConnectorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConnectorSchemas }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "ConnectorSchemas")
    public JAXBElement<ConnectorSchemas> createConnectorSchemas(ConnectorSchemas value) {
        return new JAXBElement<ConnectorSchemas>(_ConnectorSchemas_QNAME, ConnectorSchemas.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiConnectorRequest }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "MultiConnectorRequest")
    public JAXBElement<MultiConnectorRequest> createMultiConnectorRequest(MultiConnectorRequest value) {
        return new JAXBElement<MultiConnectorRequest>(_MultiConnectorRequest_QNAME, MultiConnectorRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Error }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "Error")
    public JAXBElement<Error> createError(Error value) {
        return new JAXBElement<Error>(_Error_QNAME, Error.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnsupportedSecurityToken }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "UnsupportedSecurityToken")
    public JAXBElement<UnsupportedSecurityToken> createUnsupportedSecurityToken(UnsupportedSecurityToken value) {
        return new JAXBElement<UnsupportedSecurityToken>(_UnsupportedSecurityToken_QNAME, UnsupportedSecurityToken.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Timeout }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "Timeout")
    public JAXBElement<Timeout> createTimeout(Timeout value) {
        return new JAXBElement<Timeout>(_Timeout_QNAME, Timeout.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfConnectorInfo }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "ArrayOfConnectorInfo")
    public JAXBElement<ArrayOfConnectorInfo> createArrayOfConnectorInfo(ArrayOfConnectorInfo value) {
        return new JAXBElement<ArrayOfConnectorInfo>(_ArrayOfConnectorInfo_QNAME, ArrayOfConnectorInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConnectorInfo }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "ConnectorInfo")
    public JAXBElement<ConnectorInfo> createConnectorInfo(ConnectorInfo value) {
        return new JAXBElement<ConnectorInfo>(_ConnectorInfo_QNAME, ConnectorInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConnectorsListRequest }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "ConnectorsListRequest")
    public JAXBElement<ConnectorsListRequest> createConnectorsListRequest(ConnectorsListRequest value) {
        return new JAXBElement<ConnectorsListRequest>(_ConnectorsListRequest_QNAME, ConnectorsListRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConnectorSchemaRequest }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "ConnectorSchemaRequest")
    public JAXBElement<ConnectorSchemaRequest> createConnectorSchemaRequest(ConnectorSchemaRequest value) {
        return new JAXBElement<ConnectorSchemaRequest>(_ConnectorSchemaRequest_QNAME, ConnectorSchemaRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InProgress }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "InProgress")
    public JAXBElement<InProgress> createInProgress(InProgress value) {
        return new JAXBElement<InProgress>(_InProgress_QNAME, InProgress.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiConnectorTicket }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "MultiConnectorTicket")
    public JAXBElement<MultiConnectorTicket> createMultiConnectorTicket(MultiConnectorTicket value) {
        return new JAXBElement<MultiConnectorTicket>(_MultiConnectorTicket_QNAME, MultiConnectorTicket.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfConnectorSchemas }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "ArrayOfConnectorSchemas")
    public JAXBElement<ArrayOfConnectorSchemas> createArrayOfConnectorSchemas(ArrayOfConnectorSchemas value) {
        return new JAXBElement<ArrayOfConnectorSchemas>(_ArrayOfConnectorSchemas_QNAME, ArrayOfConnectorSchemas.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConnectorsListResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "ConnectorsListResponse")
    public JAXBElement<ConnectorsListResponse> createConnectorsListResponse(ConnectorsListResponse value) {
        return new JAXBElement<ConnectorsListResponse>(_ConnectorsListResponse_QNAME, ConnectorsListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FailedAuthentication }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "FailedAuthentication")
    public JAXBElement<FailedAuthentication> createFailedAuthentication(FailedAuthentication value) {
        return new JAXBElement<FailedAuthentication>(_FailedAuthentication_QNAME, FailedAuthentication.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConnectorSchemaResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "ConnectorSchemaResponse")
    public JAXBElement<ConnectorSchemaResponse> createConnectorSchemaResponse(ConnectorSchemaResponse value) {
        return new JAXBElement<ConnectorSchemaResponse>(_ConnectorSchemaResponse_QNAME, ConnectorSchemaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiConnectorTicket }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "ticket", scope = EndQuery.class)
    public JAXBElement<MultiConnectorTicket> createEndQueryTicket(MultiConnectorTicket value) {
        return new JAXBElement<MultiConnectorTicket>(_EndQueryTicket_QNAME, MultiConnectorTicket.class, EndQuery.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiConnectorRequest }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "request", scope = Query.class)
    public JAXBElement<MultiConnectorRequest> createQueryRequest(MultiConnectorRequest value) {
        return new JAXBElement<MultiConnectorRequest>(_QueryRequest_QNAME, MultiConnectorRequest.class, Query.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConnectorsListResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "ListConnectorsResult", scope = ListConnectorsResponse.class)
    public JAXBElement<ConnectorsListResponse> createListConnectorsResponseListConnectorsResult(ConnectorsListResponse value) {
        return new JAXBElement<ConnectorsListResponse>(_ListConnectorsResponseListConnectorsResult_QNAME, ConnectorsListResponse.class, ListConnectorsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfConnectorInfo }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "Connectors", scope = ConnectorsListResponse.class)
    public JAXBElement<ArrayOfConnectorInfo> createConnectorsListResponseConnectors(ArrayOfConnectorInfo value) {
        return new JAXBElement<ArrayOfConnectorInfo>(_ConnectorsListResponseConnectors_QNAME, ArrayOfConnectorInfo.class, ConnectorsListResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConnectorSchemaRequest }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "request", scope = Schema.class)
    public JAXBElement<ConnectorSchemaRequest> createSchemaRequest(ConnectorSchemaRequest value) {
        return new JAXBElement<ConnectorSchemaRequest>(_QueryRequest_QNAME, ConnectorSchemaRequest.class, Schema.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "Message", scope = Error.class)
    public JAXBElement<String> createErrorMessage(String value) {
        return new JAXBElement<String>(_ErrorMessage_QNAME, String.class, Error.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiConnectorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "EndQueryResult", scope = EndQueryResponse.class)
    public JAXBElement<MultiConnectorResponse> createEndQueryResponseEndQueryResult(MultiConnectorResponse value) {
        return new JAXBElement<MultiConnectorResponse>(_EndQueryResponseEndQueryResult_QNAME, MultiConnectorResponse.class, EndQueryResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiConnectorRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "request", scope = BeginQuery.class)
    public JAXBElement<MultiConnectorRequest> createBeginQueryRequest(MultiConnectorRequest value) {
        return new JAXBElement<MultiConnectorRequest>(_QueryRequest_QNAME, MultiConnectorRequest.class, BeginQuery.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "OperationCode", scope = MultiConnectorResponse.class)
    public JAXBElement<String> createMultiConnectorResponseOperationCode(String value) {
        return new JAXBElement<String>(_MultiConnectorResponseOperationCode_QNAME, String.class, MultiConnectorResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "OperationCode", scope = MultiConnectorRequest.class)
    public JAXBElement<String> createMultiConnectorRequestOperationCode(String value) {
        return new JAXBElement<String>(_MultiConnectorResponseOperationCode_QNAME, String.class, MultiConnectorRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Duration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "Timeout", scope = MultiConnectorRequest.class)
    public JAXBElement<Duration> createMultiConnectorRequestTimeout(Duration value) {
        return new JAXBElement<Duration>(_Timeout_QNAME, Duration.class, MultiConnectorRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "ScheduledTime", scope = MultiConnectorRequest.class)
    public JAXBElement<XMLGregorianCalendar> createMultiConnectorRequestScheduledTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_MultiConnectorRequestScheduledTime_QNAME, XMLGregorianCalendar.class, MultiConnectorRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConnectorsListRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "request", scope = ListConnectors.class)
    public JAXBElement<ConnectorsListRequest> createListConnectorsRequest(ConnectorsListRequest value) {
        return new JAXBElement<ConnectorsListRequest>(_QueryRequest_QNAME, ConnectorsListRequest.class, ListConnectors.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "Value", scope = Timeout.class)
    public JAXBElement<String> createTimeoutValue(String value) {
        return new JAXBElement<String>(_TimeoutValue_QNAME, String.class, Timeout.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiConnectorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "QueryResult", scope = QueryResponse.class)
    public JAXBElement<MultiConnectorResponse> createQueryResponseQueryResult(MultiConnectorResponse value) {
        return new JAXBElement<MultiConnectorResponse>(_QueryResponseQueryResult_QNAME, MultiConnectorResponse.class, QueryResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiConnectorTicket }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "BeginQueryResult", scope = BeginQueryResponse.class)
    public JAXBElement<MultiConnectorTicket> createBeginQueryResponseBeginQueryResult(MultiConnectorTicket value) {
        return new JAXBElement<MultiConnectorTicket>(_BeginQueryResponseBeginQueryResult_QNAME, MultiConnectorTicket.class, BeginQueryResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "Description", scope = ConnectorInfo.class)
    public JAXBElement<String> createConnectorInfoDescription(String value) {
        return new JAXBElement<String>(_ConnectorInfoDescription_QNAME, String.class, ConnectorInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConnectorSchemaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", name = "SchemaResult", scope = SchemaResponse.class)
    public JAXBElement<ConnectorSchemaResponse> createSchemaResponseSchemaResult(ConnectorSchemaResponse value) {
        return new JAXBElement<ConnectorSchemaResponse>(_SchemaResponseSchemaResult_QNAME, ConnectorSchemaResponse.class, SchemaResponse.class, value);
    }

}
