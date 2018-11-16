package com.xindaibao.cashloan.cl.model.pay.weixin.helper;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tool.util.OrderNoUtil;
import tool.util.StringUtil;

import com.xindaibao.cashloan.cl.model.pay.weixin.sdk.WXPay;
import com.xindaibao.cashloan.cl.model.pay.weixin.sdk.WXPayConfig;
import com.xindaibao.cashloan.cl.model.pay.weixin.sdk.impl.WXPayConfigImpl;
import com.xindaibao.cashloan.core.common.context.Global;

/**
 * 微信支付
 * @author
 * @version 1.0
 * @date 2017年6月22日 下午4:33:18



 */
public class WeixinPayHelper {

	private static final Logger logger = LoggerFactory.getLogger(WeixinPayHelper.class);
	
	private static WXPay wxPay;
	
	static {
		WXPayConfig config;
		try {
			config = WXPayConfigImpl.getInstance();
			// 测试
			//WXPay pay = new WXPay(config, notifyUrl, true, true);
			wxPay = new WXPay(config);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			Map<String, String> respData = appPay(0.01, "60.190.230.35", "优品生活-测试", 
					OrderNoUtil.getSerialNumber());
			System.out.println(respData.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 验签
	 * TODO 方法说明
	 * @param reqData
	 * @return
	 * @throws Exception
	 */
	public static boolean verifySign(Map<String, String> reqData) throws Exception {
		return wxPay.isPayResultNotifySignatureValid(reqData);
	}
	
	
	/**
	 * 封装支付参数 <br>
	 * 说明：须先调用统一下单接口，拿到预支付订单号，再进行参数封装
	 * @param amount
	 * @param ip
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> appPay(double amount, String ip, String body, String orderNo) throws Exception {
		// 以分为单位
		String money = StringUtil.yuanConvertFen(amount);
		String prepayid = unifiedOrder(money, ip, body, orderNo);
		if (prepayid == null) {
			throw new Exception("统一下单失败");
		}
		Map<String, String> reqData = new HashMap<String, String>();
		reqData.put("prepayid", prepayid);
		reqData.put("package", "Sign=WXPay");
		//return wxPay.fillRequestData(reqData);
		return wxPay.appPayFillRequestData(reqData);
	}
	
	/**
	 * 统一下单
	 * @param amount
	 * @param ip
	 * @param body
	 * @return
	 * @throws Exception
	 */
	private static String unifiedOrder(String amount, String ip, String body, String orderNo) throws Exception {
		String notifyUrl = Global.getValue("server_host") + "/api/pay/wxpay/notify.htm";
		//String notifyUrl = "http://localhost:8080/api/pay/wxpay/notify.htm";
		//String out_trade_no = OrderNoUtil.getSerialNumber();
		String trade_type = "APP";
		Map<String, String> reqData = new HashMap<String, String>();
		reqData.put("body", body);
		reqData.put("out_trade_no", orderNo);
		reqData.put("device_info", "");
		reqData.put("total_fee", amount);
		reqData.put("spbill_create_ip", ip);
		reqData.put("notify_url", notifyUrl);
		reqData.put("trade_type", trade_type);
		Map<String, String> respData = wxPay.unifiedOrder(reqData);
		logger.info("微信支付-统一下单订单号：" + orderNo + "，返回结果：" + respData.toString());
		String prepay_id = respData.get("prepay_id");
		return prepay_id;
	}
	
}
