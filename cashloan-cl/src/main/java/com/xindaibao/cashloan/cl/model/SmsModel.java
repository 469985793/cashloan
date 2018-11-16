package com.xindaibao.cashloan.cl.model;

/**
 * 短信model
 * @author
 * @version 1.0.0
 * @date 2017年3月15日 下午6:39:20



 */
public class SmsModel {
	
	/**
	 * 注册验证码-register
	 */
	public static final String SMS_TYPE_REGISTER = "register";
	
	/**
	 * 绑卡-bindCard
	 */
	public static final String SMS_TYPE_BINDCARD = "bindCard";
	
	/**
	 * 找回登录密码-findReg
	 */
	public static final String SMS_TYPE_FINDREG = "findReg";
	
	/**
	 * 找回交易密码-findPay
	 */
	public static final String SMS_TYPE_FINDPAY = "findPay";

}
