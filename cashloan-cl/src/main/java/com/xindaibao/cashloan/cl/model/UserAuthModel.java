 package com.xindaibao.cashloan.cl.model;

import com.xindaibao.cashloan.cl.domain.UserAuth;


public class UserAuthModel extends UserAuth {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 

	/** 认证状态 - 未认证/未完善 */
	public static final String STATE_NOT_CERTIFIED = "10";

	/** 认证状态 - 认证中/完善中*/
	public static final String STATE_ERTIFICATION = "20";

	/** 认证状态 - 已认证/已完善*/
	public static final String STATE_VERIFIED = "30";



	/**
	 * 登录名
	 */
	private String loginName;
  
    /**
    * 真实姓名
    */
    private String realName;
    /**
     * 手机号码
     */
     private String phone;
     
	 
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	 
}
