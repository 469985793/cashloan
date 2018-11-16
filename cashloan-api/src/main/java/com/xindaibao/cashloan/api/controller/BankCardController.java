package com.xindaibao.cashloan.api.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import com.google.common.collect.Maps;
import com.xindaibao.cashloan.cl.model.tongdun.http.HttpRestClient;
import com.xindaibao.cashloan.cl.model.tongdun.sdk.PreloanRequest;
import com.xindaibao.cashloan.cl.model.tongdun.sdk.PreloanResponse;
import com.xindaibao.cashloan.core.common.util.HttpUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tool.util.BeanUtil;
import tool.util.DateUtil;
import tool.util.NumberUtil;
import tool.util.ReflectUtil;
import tool.util.StringUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xindaibao.cashloan.cl.domain.BankCard;
import com.xindaibao.cashloan.cl.model.BankCardModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.AgreementList;
import com.xindaibao.cashloan.cl.model.pay.lianlian.AuthSignModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.QueryAuthSignModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.RiskItems;
import com.xindaibao.cashloan.cl.model.pay.lianlian.constant.LianLianConstant;
import com.xindaibao.cashloan.cl.model.pay.lianlian.util.LianLianHelper;
import com.xindaibao.cashloan.cl.model.pay.lianlian.util.SignUtil;
import com.xindaibao.cashloan.cl.sdk.face.CreditLoanFace;
import com.xindaibao.cashloan.cl.service.BankCardService;
import com.xindaibao.cashloan.cl.service.BorrowRepayService;
import com.xindaibao.cashloan.cl.service.ClBorrowService;
import com.xindaibao.cashloan.cl.service.UserAuthService;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.exception.BussinessException;
import com.xindaibao.cashloan.core.common.util.OrderNoUtil;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.util.code.RSAUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.core.domain.User;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.service.CloanUserService;
import com.xindaibao.cashloan.core.service.UserBaseInfoService;
import com.xindaibao.creditrank.cr.service.CreditRatingService;
import com.xindaibao.creditrank.cr.service.CreditService;

import credit.CreditRequest;
import credit.Header;

 /**
 * 银行卡Controller
 */
@Scope("prototype")
@Controller
public class BankCardController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(BankCardController.class);


     @Resource
	private BankCardService bankCardService;

	@Resource
	private CloanUserService cloanUserService;
	
	@Resource
	private UserBaseInfoService userInfoService;
	
	@Resource
	private UserAuthService userAuthService;
	
	@Resource
	private BorrowRepayService borrowRepayService;
	
	@Resource
	private ClBorrowService clBorrowService;
	    
    @Resource
	private CreditRatingService creditRatingService;
    
    @Resource
    private CreditService creditService;
	
	
	/**
	 * @description 获取单个用户的所有绑定银行卡
	 * @param userId
	 * @author
	 * @return void
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/api/act/mine/bankCard/getBankCardList.htm")
	public void getBankCardList() {
		String userId = request.getSession().getAttribute("userId").toString();
		BankCardModel model = new BankCardModel();
		model.setChangeAble(BankCardModel.STATE_ENABLE);
		BankCard card = bankCardService.getBankCardByUserId(NumberUtil.getLong(userId));
		//校验能否更换银行卡
		List<Borrow> list = clBorrowService.findUserUnFinishedBorrow(NumberUtil.getLong(userId));
	    if (null != list && !list.isEmpty()) {
	    	model.setChangeAble(BankCardModel.STATE_DISABLE);
		}
	    BeanUtil.copyProperties(card, model);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_DATA, model);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response, result);
	}
	
	/**
	 * @description 验证码
	 * @param userId
	 * @author
	 * @return void
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/api/act/mine/bankCard/getCaptcha.htm",method=RequestMethod.GET)
	public void getCaptcha(@RequestParam(value="userId",required=true)String userId){
		Map<String,Object> returnMap=new HashMap<String,Object>();
		returnMap.put(Constant.RESPONSE_CODE,Constant.SUCCEED_CODE_VALUE);
		returnMap.put(Constant.RESPONSE_CODE_MSG,"短信发送成功");
		ServletUtils.writeToResponse(response, returnMap);
	}
	
	/**
	 * 请求签约
	 * @param bank
	 * @param cardNo
	 * @param userId
	 */
	@RequestMapping(value = "/api/act/mine/bankCard/authSign.htm", method = RequestMethod.POST)
	public void saveOrUpdate(
			@RequestParam(value = "bank", required = true) String bank,
			@RequestParam(value = "cardNo", required = true) String cardNo,
			@RequestParam(value = "appType", required = true) Integer appType) {
			String userId = request.getSession().getAttribute("userId").toString();
			//校验更换绑卡时，是否存在未结束的借款
			List<Borrow> list = clBorrowService.findUserUnFinishedBorrow(NumberUtil.getLong(userId));
		    if (null != list && !list.isEmpty()) {
		    	throw new BussinessException("已有未完成借款!");
			}
		
			if(!StringUtil.isNumber(cardNo) || cardNo.length() <15 || cardNo.length() > 30){
				Map<String,Object> result=new HashMap<String,Object>();
				result.put(Constant.RESPONSE_CODE,Constant.FAIL_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG,"银行卡号格式有误");
				ServletUtils.writeToResponse(response,result);
				return ; 
			}
			
			User user = new User();//cloanUserService.getById(NumberUtil.getLong(userId));
			UserBaseInfo baseInfo = userInfoService.findByUserId(NumberUtil.getLong(userId));
			
//			final String APIKEY =  Global.getValue("apikey");
//			final String SECRETKEY = Global.getValue("secretkey");
//			final String TX_CHANGENO = Global.getValue("tx_channelNo");
//			final String INTERFACE_NAME = Global.getValue("four_elements_interfaceName");
//			final String API_HOST = Global.getValue("tx_bankCard_four");
//			
//			long timestamp = DateUtil.getNowTime();
//			Header header = new Header(APIKEY, TX_CHANGENO, INTERFACE_NAME, timestamp);
//			CreditRequest creditRequest = new CreditRequest(API_HOST, header);
//			Map<String, Object> payload = new HashMap<String, Object>();
//			payload.put("name", baseInfo.getRealName());
//			payload.put("idCard", baseInfo.getIdNo());
//			payload.put("accountNO", cardNo);
//			payload.put("bankPreMobile", baseInfo.getPhone());
//			
//			creditRequest.setPayload(payload);
//			creditRequest.signByKey(SECRETKEY);
			String orderNo  = OrderNoUtil.getSerialNumber();
			JSONObject resultByInterface = null;
			try {
				//resultByInterface = creditRequest.request();
				resultByInterface = signcreatebill(user,baseInfo,appType,cardNo,orderNo);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			
			//JSONObject array = JSONObject.parseObject(resultByInterface);
			if (resultByInterface == null || resultByInterface.size() == 0) {
				throw new RuntimeException("请求返回异常");
			}
			if(!"0000".equals(resultByInterface.getString("ret_code"))){
				throw new RuntimeException(resultByInterface.getString("ret_msg"));
			}
			
//			JSONObject res = resultByInterface.getJSONObject("res");
//			if(!"SAME".equals((String)res.get("checkStatus"))){
//				throw new RuntimeException("用户姓名、身份证号、手机号码、银行卡号信息不一致");
//			}
			
			AuthSignModel authSign = new AuthSignModel(orderNo);
			authSign.setUser_id(user.getUuid());
			authSign.setId_no(baseInfo.getIdNo());
			authSign.setAcct_name(baseInfo.getRealName());
			authSign.setCard_no(cardNo);

			RiskItems riskItems = new RiskItems();
			riskItems.setFrms_ware_category("2010");    
			riskItems.setUser_info_mercht_userno(user.getUuid());
			riskItems.setUser_info_bind_phone(baseInfo.getPhone());
			riskItems.setUser_info_dt_register(DateUtil.dateStr3(user.getRegistTime()));
			riskItems.setUser_info_full_name(baseInfo.getRealName());
			riskItems.setUser_info_id_type("0");
			riskItems.setUser_info_id_no(baseInfo.getIdNo());
			riskItems.setUser_info_identify_state("1");
			riskItems.setUser_info_identify_type("1");
			authSign.setRisk_item(JSONObject.toJSONString(riskItems));
			authSign.sign();
			Map<String, String> map = ReflectUtil.fieldValueToMap(authSign,authSign.reqParamNames());
		
			Map<String, String> authSignMap = new HashMap<String, String>();
			authSignMap.put("authSignData", JSON.toJSONString(map));

			Map<String,Object> result=new HashMap<String,Object>();
			result.put(Constant.RESPONSE_DATA, authSignMap);
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
			ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 签约响应回调
	 * 
	 * @param uuid
	 * @param contractNo
	 * @param signResult
	 * @param userId
	 */
	@RequestMapping(value = "/api/act/mine/bankCard/authSignReturn.htm", method = RequestMethod.POST)
	public void authSignReturn(
			@RequestParam(value = "uuid", required = true) String uuid,
			@RequestParam(value = "agreeNo", required = true) String agreeNo,
			@RequestParam(value = "signResult", required = true) String signResult,
			@RequestParam(value = "bank", required = true) String bank,
			@RequestParam(value = "cardNo", required = true) String cardNo) {

		Long userId = Long.valueOf(request.getSession().getAttribute("userId").toString());
		Map<String, Object> result = new HashMap<String, Object>();

		if (StringUtil.isEmpty(uuid) || StringUtil.isEmpty(signResult)) {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "参数不能为空");
			ServletUtils.writeToResponse(response, result);
			return;
		}

		BankCard bankCard = bankCardService.getBankCardByUserId(userId);

		boolean flag = false;
		if (LianLianConstant.RESULT_SUCCESS.equals(signResult)) {

			if (null == bankCard) {
				BankCard card = new BankCard();
				card.setCardNo(cardNo);
				card.setBindTime(DateUtil.getNow());
				card.setUserId(userId);
				card.setBank(bank);
				card.setAgreeNo(agreeNo);
				flag = bankCardService.save(card);
			} else {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("id", bankCard.getId());
				paramMap.put("bank", bank);
				paramMap.put("cardNo", cardNo);
				paramMap.put("agreeNo", agreeNo);
				flag = bankCardService.updateSelective(paramMap);
			}

		} else if (LianLianConstant.RESULT_PROCESSING.equals(signResult)) {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("userUuid", StringUtil.isNull(uuid));
			paramMap.put("cardNo", StringUtil.isNull(cardNo));
			QueryAuthSignModel model = (QueryAuthSignModel) LianLianHelper.queryAuthSign(paramMap);

			List<AgreementList> agreementList = JSONArray.parseArray(model.getAgreement_list(), AgreementList.class);
			AgreementList agreement = agreementList.get(0);
			// 查询协议号不为空，修改绑卡信息
			if (StringUtil.isNotBlank(agreement.getNo_agree())) {
				flag = bankCardService.saveOrUpdate(userId, bank, cardNo, agreement.getNo_agree());
			}
		}

		if (flag) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userId", userId);
			paramMap.put("bankCardState", "30");
			userAuthService.updateByUserId(paramMap);
			//验证状态，启用评分，更新额度
			boolean authFlag = userAuthService.findAuthState(userId);
			if (authFlag){//完成全部认证
				String consumerNo = userId.toString();
				//开视评分
				creditRatingService.saveCreditRating(consumerNo, 1l);//借款类型默认为现金贷，即 long borrowTypeId=1l
				//开始更新授信额度数据
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("used", "0");
				map.put("consumerNo", userId);
				map.put("state", "10");//待审批
				creditService.updateByAuth(map);
			}
		}
		
		
		if (flag) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "保存成功");
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "保存失败");
		}

		ServletUtils.writeToResponse(response, result);
	}

     private static final String URL_STRING = "https://fourelementapi.lianlianpay.com/repay/signcreatebill";

     private JSONObject signcreatebill(User user ,UserBaseInfo baseInfo,Integer appType ,String cardNo,String orderNo)throws Exception{
         //String uuid =UUID.randomUUID().toString().replaceAll("-", "");
         String time= new SimpleDateFormat("YYYYMMddHHmmss").format(new Date());
         String lianlianPrivatKey = Global.getValue("lianlian_private_key");
         String lianlianBusinessNo  = Global.getValue("lianlian_business_no");
         JSONObject obj = new JSONObject();
         obj.put("frms_ware_category", "2010");
         obj.put("user_info_mercht_userno", user.getUuid());
         obj.put("user_info_dt_registe", DateUtil.dateStr3(user.getRegistTime()));
         obj.put("user_info_full_name", baseInfo.getRealName());
         obj.put("user_info_id_no", baseInfo.getIdNo());
         obj.put("user_info_identify_state", "1");
         obj.put("user_info_identify_type", "1");

         JSONObject params = new JSONObject();
         params.put("api_version","1.0");
         params.put("sign_type", "RSA");
         params.put("time_stamp",time );
         params.put("oid_partner",lianlianBusinessNo);
         params.put("user_id",user.getUuid());
         params.put("no_order",orderNo);
         params.put("dt_order",time);
         params.put("risk_item",obj.toJSONString());
         params.put("flag_pay_product","8");
         params.put("flag_chnl",appType);
         params.put("id_type","0");
         params.put("id_no",baseInfo.getIdNo());
         params.put("acct_name",baseInfo.getRealName());
         params.put("card_no",cardNo);
         params.put("bind_mob",baseInfo.getPhone());
         params.put("notify_url","");
         String paramsStr = SignUtil.genSignData(params.toJSONString());
         params.put("sign", SignUtil.sign(lianlianPrivatKey,paramsStr));
         logger.warn("bind card request args ==========>" + params.toJSONString());

         //================
         Map<String, Object> paramsMap = Maps.newHashMap();
         paramsMap.put("api_version","1.0");
         paramsMap.put("sign_type", "RSA");
         paramsMap.put("time_stamp",time );
         paramsMap.put("oid_partner",lianlianBusinessNo);
         paramsMap.put("user_id",user.getUuid());
         paramsMap.put("no_order",orderNo);
         paramsMap.put("dt_order",time);
         paramsMap.put("risk_item",obj.toJSONString());
         paramsMap.put("flag_pay_product","8");
         paramsMap.put("flag_chnl", String.valueOf(appType));
         paramsMap.put("id_type","0");
         paramsMap.put("id_no",baseInfo.getIdNo());
         paramsMap.put("acct_name",baseInfo.getRealName());
         paramsMap.put("card_no",cardNo);
         paramsMap.put("bind_mob",baseInfo.getPhone());
         paramsMap.put("notify_url","");
         paramsMap.put("sign", SignUtil.sign(lianlianPrivatKey,paramsStr));

         PreloanRequest request = new PreloanRequest();
         request.setServerHost(URL_STRING);
         request.setParamMap(paramsMap);

         request.setHttpMethod("post");

         logger.info("======request：" + JSONObject.toJSONString(request));


		 JSONObject jsonObject = HttpUtil.postClient(URL_STRING, JSONObject.toJSONString(params));
         logger.info("======response：" + jsonObject.toString());
		 return jsonObject;

		 // PreloanResponse response = HttpRestClient.create().executeThenGetJsonResponse2(request);
         //logger.info("======response：" + JSON.toJSONString(response));
         //return response.getData();
//        return HttpUtil.postClient(URL_STRING, params.toJSONString());

     }

	/*private JSONObject signcreatebill(User user ,UserBaseInfo baseInfo,Integer appType ,String cardNo,String orderNo)throws Exception{
		//String uuid =UUID.randomUUID().toString().replaceAll("-", "");
	 	String time= new SimpleDateFormat("YYYYMMddHHmmss").format(new Date());
	 	String lianlianPrivatKey = Global.getValue("lianlian_private_key");
	 	String lianlianBusinessNo  = Global.getValue("lianlian_business_no");
		JSONObject obj = new JSONObject();
		obj.put("frms_ware_category", "2010");
		obj.put("user_info_mercht_userno", user.getUuid());
		obj.put("user_info_dt_registe", DateUtil.dateStr3(user.getRegistTime()));
		obj.put("user_info_full_name", baseInfo.getRealName());
		obj.put("user_info_id_no", baseInfo.getIdNo());
		obj.put("user_info_identify_state", "1");
		obj.put("user_info_identify_type", "1");
		
	 	JSONObject params = new JSONObject();
		params.put("api_version","1.0");
        params.put("sign_type", "RSA");
        params.put("time_stamp",time );
        params.put("oid_partner",lianlianBusinessNo);
        params.put("user_id",user.getUuid());
        params.put("no_order",orderNo);
        params.put("dt_order",time);
        params.put("risk_item",obj.toJSONString());
        params.put("flag_pay_product","8");
        params.put("flag_chnl",appType);
        params.put("id_type","0");
        params.put("id_no",baseInfo.getIdNo());
        params.put("acct_name",baseInfo.getRealName());
        params.put("card_no",cardNo);
        params.put("bind_mob",baseInfo.getPhone());
        params.put("notify_url","");
        String paramsStr = SignUtil.genSignData(params.toJSONString());
        params.put("sign", SignUtil.sign(lianlianPrivatKey,paramsStr));
        logger.warn("bind card request args ==========>" + params.toJSONString());
		return HttpUtil.postClient("https://fourelementapi.lianlianpay.com/repay/signcreatebill", params.toJSONString());
	}*/
	
}
