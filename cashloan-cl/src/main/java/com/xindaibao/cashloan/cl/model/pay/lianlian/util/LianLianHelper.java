package com.xindaibao.cashloan.cl.model.pay.lianlian.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.xindaibao.cashloan.cl.domain.BorrowRepay;
import com.xindaibao.cashloan.cl.model.pay.lianlian.constant.LianLianConstant;
import com.xindaibao.cashloan.cl.service.PayReqLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xindaibao.cashloan.cl.domain.PayReqLog;
import com.xindaibao.cashloan.cl.model.pay.lianlian.AuthApplyModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.AuthSignModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.BasePaymentModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.CancelAuthSignModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.CertifiedPayModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.ConfirmPaymentModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.PaymentModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.QueryAuthSignModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.QueryPaymentModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.QueryRepaymentModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.RepaymentModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.RepaymentPlan;
import com.xindaibao.cashloan.cl.model.pay.lianlian.RepaymentPlanModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.RiskItems;
import com.xindaibao.cashloan.cl.model.pay.lianlian.SmsParams;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.util.HttpsUtil;
import com.xindaibao.cashloan.core.common.util.OrderNoUtil;
import com.xindaibao.cashloan.core.common.util.ReflectUtil;
import com.xindaibao.cashloan.core.domain.User;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;

import tool.util.BeanUtil;
import tool.util.DateUtil;
import tool.util.IPUtil;
import tool.util.NumberUtil;
import tool.util.StringUtil;

/**
 * 连连支付
 * 
 * @author
 * @version 1.0.0
 * @date 2017年3月6日 下午4:36:16


 * 

 */

public class LianLianHelper {

	public static final Logger logger = LoggerFactory.getLogger(LianLianHelper.class);

	private static BasePaymentModel doSubmit(BasePaymentModel model) {
		// 保存请求记录
		saveReqLog(model);

		Map<String, String> map = ReflectUtil.fieldValueToMap(model, model.reqParamNames());

		String jsonStr = JSONObject.toJSONString(map);
		String resp = null;
		try {
			// 获取系统参数中连连支付启用状态
			String lianlianSwitch = Global.getValue("lianlian_switch");

			if (StringUtil.isNotBlank(lianlianSwitch) && "1".equals(lianlianSwitch)) {
				logger.info("请求地址：" + model.getSubUrl());
				resp = HttpsUtil.postStrClient(model.getSubUrl(), jsonStr);
			} else {
				logger.info("关闭连连支付,模拟返回结果");
				resp = "{\"ret_code\":\"0000\",\"ret_msg\":\"模拟交易成功\"}";
			}

			model.response(resp);

		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		// 更新请求记录
		modifyReqLog(model, resp);
		return model;
	}

	/**
	 * 连连支付 实时付款 - 付款交易
	 * 
	 * @param model
	 * @return
	 */
	public static BasePaymentModel payment(Map<String, Object> paramMap) {
		Date payTime = (Date) paramMap.get("payTime");
		Double amount = NumberUtil.getDouble(StringUtil.isNull(paramMap.get("amount")));
		String cardNo = StringUtil.isNull(paramMap.get("cardNo"));
		String realName = StringUtil.isNull(paramMap.get("realName"));
		String orderMemoInfo = StringUtil.isNull(paramMap.get("orderMemoInfo"));
		String notifyUrl = StringUtil.isNull(paramMap.get("notifyUrl"));
		
		String orderNo = OrderNoUtil.getSerialNumber();
		
		PaymentModel payment = new PaymentModel(orderNo);
		payment.setDt_order(DateUtil.dateStr3(payTime));
		if ("dev".equals(Global.getValue("app_environment"))) {
			payment.setMoney_order("0.01");
		} else {
			payment.setMoney_order(StringUtil.isNull(amount));
		}
		payment.setAmount(amount);
		payment.setCard_no(cardNo);
		payment.setAcct_name(realName);
		payment.setInfo_order(orderMemoInfo);
		payment.setMemo(orderMemoInfo);
		payment.setNotify_url(notifyUrl);

		payment.sign();
		doSubmit(payment);
		return payment;
	}

	/**
	 * 连连支付 实时付款 - 确认付款
	 * 
	 * @param paramMap
	 * @return
	 */
	public static BasePaymentModel confirmPayment(Map<String, Object> paramMap) {
		String payOrderNo = StringUtil.isNull(paramMap.get("payOrderNo"));
		String confirmCode = StringUtil.isNull(paramMap.get("confirmCode"));
		String notifyUrl = StringUtil.isNull(paramMap.get("notifyUrl"));

		String orderNo = OrderNoUtil.getSerialNumber();
		ConfirmPaymentModel confirmPayment = new ConfirmPaymentModel(orderNo);
		confirmPayment.setNo_order(payOrderNo);
		confirmPayment.setConfirm_code(confirmCode);
		confirmPayment.setNotify_url(notifyUrl);

		confirmPayment.sign();
		doSubmit(confirmPayment);
		return confirmPayment;
	}

	/**
	 * 连连支付 实时付款 - 查询付款交易
	 * 
	 * @param model
	 * @return
	 */
	public static BasePaymentModel queryPayment(QueryPaymentModel model) {
		model.sign();
		doSubmit(model);
		return model;
	}

	/**
	 * 连连支付 签约参数封装
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String authSignEncapsulate(Map<String, Object> paramMap) {

		User user = (User) paramMap.get("user");
		UserBaseInfo baseInfo = (UserBaseInfo) paramMap.get("userBaseInfo");
		String cardNo = StringUtil.isNull(paramMap.get("cardNo"));

		// 风险控制参数
		RiskItems riskItems = new RiskItems(user.getUuid(), baseInfo.getPhone(),
				DateUtil.dateStr3(user.getRegistTime()), baseInfo.getRealName(), baseInfo.getIdNo());

		String orderNo = OrderNoUtil.getSerialNumber();
		AuthSignModel authSign = new AuthSignModel(orderNo);
		authSign.setUser_id(user.getUuid());
		authSign.setId_no(baseInfo.getIdNo());
		authSign.setAcct_name(baseInfo.getRealName());
		authSign.setCard_no(cardNo);
		authSign.setRisk_item(JSONObject.toJSONString(riskItems));
		authSign.sign();
		Map<String, String> map = ReflectUtil.fieldValueToMap(authSign, authSign.reqParamNames());
		String authSignData = JSON.toJSONString(map);

		return authSignData;
	}

	/**
	 * 连连支付 分期付 - 授权申请
	 * 
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static BasePaymentModel authApply(Map<String, Object> paramMap) {
		User user = (User) paramMap.get("user");
		String borrowOrderNo = StringUtil.isNull(paramMap.get("borrowOrderNo"));
		String agreeNo = StringUtil.isNull(paramMap.get("agreeNo"));
		List<BorrowRepay> borrowRepayList = ((List<BorrowRepay>) paramMap.get("borrowRepayList"));

		List<RepaymentPlan> repaymentPlanList = new ArrayList<RepaymentPlan>();
		for (BorrowRepay borrowRepay : borrowRepayList) {
			RepaymentPlan plan = new RepaymentPlan();
			plan.setDate(DateUtil.dateStr2(borrowRepay.getRepayTime()));
			plan.setAmount(StringUtil.isNull(borrowRepay.getAmount()));
			repaymentPlanList.add(plan);
		}
		Map<String, Object> repaymentPlanMap = new HashMap<String, Object>();
		repaymentPlanMap.put("repaymentPlan", repaymentPlanList);
		
		String repaymentPlan = JSONObject.toJSONString(repaymentPlanMap);

		String orderNo = OrderNoUtil.getSerialNumber();
		AuthApplyModel authApply = new AuthApplyModel(orderNo);
		authApply.setUser_id(user.getUuid());
		authApply.setRepayment_plan(repaymentPlan);
		authApply.setRepayment_no(borrowOrderNo);
		SmsParams smsParams = new SmsParams();
		smsParams.setContract_type(Global.getValue("title"));
		smsParams.setContact_way(Global.getValue("customer_hotline"));
		authApply.setSms_param(JSONObject.toJSONString(smsParams));
		authApply.setNo_agree(agreeNo);
		
		authApply.sign();
		doSubmit(authApply);
		return authApply;
	}

	/**
	 * 查询签约结果
	 * 
	 * @param model
	 * @return
	 */
	public static BasePaymentModel queryAuthSign(Map<String, Object> paramMap) {
		String userUuid = StringUtil.isNull(paramMap.get("userUuid"));
		String cardNo = StringUtil.isNull(paramMap.get("cardNo"));
		String orderNo = OrderNoUtil.getSerialNumber();

		QueryAuthSignModel model = new QueryAuthSignModel(orderNo);
		model.setUser_id(StringUtil.isNull(userUuid));
		model.setCard_no(cardNo);
		model.sign();
		doSubmit(model);

		return model;
	}

	/**
	 * 取消签约授权
	 * 
	 * @param model
	 * @return
	 */
	public static BasePaymentModel cancelAuthSign(Map<String,Object> paramMap) {
		String userUuid = StringUtil.isNull(paramMap.get("userUuid"));
		String agreeNo = StringUtil.isNull(paramMap.get("agreeNo"));
		String orderNo = OrderNoUtil.getSerialNumber();
		
		CancelAuthSignModel model = new CancelAuthSignModel(orderNo);
		model.setUser_id(userUuid);
		model.setNo_agree(agreeNo);
		model.sign();
		doSubmit(model);
		return model;
	}

	/**
	 * 连连支付 分期付 - 还款计划变更
	 * 
	 * @param model
	 * @return
	 */
	public static BasePaymentModel repaymentPlanChange(RepaymentPlanModel model) {
		model.sign();
		doSubmit(model);
		return model;
	}

	/**
	 * 连连支付 分期付 - 银行还款扣款
	 * 
	 * @param model
	 * @return
	 */
	public static BasePaymentModel repayment(Map<String, Object> paramMap) {
		User user = (User) paramMap.get("user");
		UserBaseInfo baseInfo = (UserBaseInfo) paramMap.get("userBaseInfo");
		String agreeNo = StringUtil.isNull(paramMap.get("agreeNo"));
		Date payTime =  (Date) paramMap.get("payTime");
		String amount = StringUtil.isNull(paramMap.get("amount"));
		Date repayTime =  (Date) paramMap.get("repayTime");
		String borrowOrderNo = StringUtil.isNull(paramMap.get("borrowOrderNo"));
		String orderMemoInfo = StringUtil.isNull(paramMap.get("orderMemoInfo"));
		String notifyUrl = StringUtil.isNull(paramMap.get("notifyUrl"));
		
		String orderNo = OrderNoUtil.getSerialNumber();
		RiskItems riskItems = new RiskItems(user.getUuid(), baseInfo.getPhone(), DateUtil.dateStr3(user.getRegistTime()), baseInfo.getRealName(), 
				baseInfo.getIdNo());
		RepaymentModel repayment = new RepaymentModel(orderNo);
		repayment.setUser_id(user.getUuid());
		repayment.setBusi_partner(LianLianConstant.GOODS_VIRTUAL);
		repayment.setDt_order(DateUtil.dateStr3(payTime));
		repayment.setName_goods(orderMemoInfo);
		repayment.setInfo_order(orderMemoInfo);
		if ("dev".equals(Global.getValue("app_environment"))) {
			repayment.setMoney_order("0.01");
		} else {
			repayment.setMoney_order(StringUtil.isNull(amount));
		}
		repayment.setAmount(NumberUtil.getDouble(amount));
		repayment.setRisk_item(JSONObject.toJSONString(riskItems));
		repayment.setSchedule_repayment_date(DateUtil.dateStr2(repayTime));
		repayment.setRepayment_no(borrowOrderNo);
		repayment.setNo_agree(agreeNo);
		repayment.setNotify_url(notifyUrl);
		
		repayment.sign();
		
		doSubmit(repayment);
		return repayment;
	}

	/**
	 * 连连支付 分期付 - 银行还款扣款查询
	 * 
	 * @param model
	 * @return
	 */
	public static BasePaymentModel queryRepayment(Map<String, Object> paramMap) {
		String queryOrderNo = StringUtil.isNull(paramMap.get("queryOrderNo"));
		Date queryOrderTime = (Date) paramMap.get("queryOrderTime");

		String orderNo = OrderNoUtil.getSerialNumber();
		QueryRepaymentModel queryRepayment = new QueryRepaymentModel(orderNo);
		queryRepayment.setNo_order(queryOrderNo);
		queryRepayment.setDt_order(DateUtil.dateStr3(queryOrderTime));

		queryRepayment.sign();
		doSubmit(queryRepayment);
		return queryRepayment;
	}

	/**
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String certifiedPay(Map<String, Object> paramMap) {
		User user = (User) paramMap.get("user");
		UserBaseInfo baseInfo = (UserBaseInfo) paramMap.get("userBaseInfo");
		String orderNo = StringUtil.isNull(paramMap.get("orderNo"));
		String agreeNo = StringUtil.isNull(paramMap.get("agreeNo"));
		String amount = StringUtil.isNull(paramMap.get("amount"));
		String notifyUrl = StringUtil.isNull(paramMap.get("notifyUrl"));
		Date payReqTime = DateUtil.getNow();

		CertifiedPayModel certifiedPay = new CertifiedPayModel(orderNo);
		certifiedPay.setBusi_partner(LianLianConstant.GOODS_VIRTUAL);
		certifiedPay.setDt_order(DateUtil.dateStr3(payReqTime));
		certifiedPay.setName_goods("");
		certifiedPay.setInfo_order("");

		if ("dev".equals(Global.getValue("app_environment"))) {
			certifiedPay.setMoney_order("0.01");
		} else {
			certifiedPay.setMoney_order(amount);
		}
		
		certifiedPay.setAmount(NumberUtil.getDouble(amount));
		certifiedPay.setNotify_url(Global.getValue("server_host"));
		RiskItems riskItems = new RiskItems(user.getUuid(), baseInfo.getPhone(),
				DateUtil.dateStr3(user.getRegistTime()), baseInfo.getRealName(), baseInfo.getIdNo());
		String riskItem = JSONObject.toJSONString(riskItems);
		certifiedPay.setRisk_item(riskItem);
		certifiedPay.setUser_id(user.getUuid());
		certifiedPay.setId_type("0"); // 0 表示身份证
		certifiedPay.setId_no(baseInfo.getIdNo());
		certifiedPay.setAcct_name(baseInfo.getRealName());
		certifiedPay.setNo_agree(agreeNo);
		certifiedPay.setNotify_url(notifyUrl);

		certifiedPay.sign();
		
		Map<String, String> map = ReflectUtil.fieldValueToMap(certifiedPay, certifiedPay.reqParamNames());
		String certifiedPayData = JSON.toJSONString(map);

		return certifiedPayData;
	}

	/**
	 * 保存请求记录
	 * 
	 * @param model
	 */
	public static void saveReqLog(BasePaymentModel model) {
		PayReqLogService payReqLogService = (PayReqLogService) BeanUtil.getBean("payReqLogService");
		PayReqLog payReqLog = new PayReqLog();
		payReqLog.setOrderNo(model.getOrderNo());
		payReqLog.setService(model.getService());
		payReqLog.setCreateTime(DateUtil.getNow());
		payReqLog.setParams(ReflectUtil.fieldValueToJson(model, model.signParamNames()));
		payReqLog.setReqDetailParams(ReflectUtil.fieldValueToJson(model, model.reqParamNames()));

		if (null != ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			String ip = IPUtil.getRemortIP(request);
			payReqLog.setIp(ip);
		}
		payReqLogService.save(payReqLog);
	}

	/**
	 * 更新返回数据
	 * 
	 * @param upsModel
	 * @param txBaseResponse
	 */
	private static void modifyReqLog(BasePaymentModel model, String resp) {
		PayReqLogService payReqLogService = (PayReqLogService) BeanUtil.getBean("payReqLogService");
		PayReqLog reqLog = payReqLogService.findByOrderNo(model.getOrderNo());
		if (null == reqLog) {
			return;
		}
		reqLog.setReturnParams(resp);
		reqLog.setReturnTime(DateUtil.getNow());
		payReqLogService.updateById(reqLog);
	}
	
}

