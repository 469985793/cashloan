package com.xindaibao.cashloan.core.common.model;

import org.apache.log4j.Logger;


/** 
 * @author
 * @version 1.0
 * @date 2016年12月13日 下午2:18:28


 * 

 */
public class SignModel {
	
	private static final Logger logger = Logger.getLogger(SignModel.class);
	
	/**
	 * 真實姓名
	 */
	private String realName;
	
	/**
	 * 用戶名
	 */
	private String userName;
	
	/**
	 * 身份证号码
	 */
	private String idNo;
	
	/**
	 * 手机号
	 */
	private String phone;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 银行卡号
	 */
	private String cardId;
	
	/**
	 * 密码
	 */
	private String pwd;
	
	/**
	 * 支付密码
	 */
	private String payPwd;
	
	/**
	 * 签名 
	 */
	private String signInfo;
	
	/**
	 * 请求参数
	 */
	private String[] requestParamNames;
	
	/**
	 * 响应参数
	 */
	private String[] responseParamNames;
	

	public String getSignInfo() {
		return signInfo;
	}

	public void setSignInfo(String signInfo) {
		this.signInfo = signInfo;
	}

	public String[] getRequestParamNames() {
		return requestParamNames;
	}

	public void setRequestParamNames(String[] requestParamNames) {
		this.requestParamNames = requestParamNames;
	}

	public String[] getResponseParamNames() {
		return responseParamNames;
	}

	public void setResponseParamNames(String[] responseParamNames) {
		this.responseParamNames = responseParamNames;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPayPwd() {
		return payPwd;
	}

	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}

	public static Logger getLogger() {
		return logger;
	}
	
	
}
