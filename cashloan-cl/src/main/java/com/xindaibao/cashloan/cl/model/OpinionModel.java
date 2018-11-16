package com.xindaibao.cashloan.cl.model;

import com.xindaibao.cashloan.cl.domain.Opinion;

/**
 * 意见反馈model
 * @author
 * @version 1.0.0
 * @date 2017年4月6日 下午6:43:38



 */
public class OpinionModel extends Opinion {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 状态_	10待确认
	 */
	public static final String STATE_WAITE_CONFIRM = "10";
	
	/**
	 * 状态_20已确认
	 */
	public static final String STATE_CONFIRMED = "20";
	
	/**
	 * 用户手机号码
	 */
	private String phone;
	
	/**
	 * 用户真实姓名
	 */
	private String userRealName;
	
	/**
	 * 管理员真实姓名
	 */
	private String sysUserRealName;
	
	/**
	 * 状态的中文描述
	 */
	private String stateStr;

	/**
	 * 获取用户手机号码
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置用户手机号码
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取用户真实姓名
	 * @return userRealName
	 */
	public String getUserRealName() {
		return userRealName;
	}

	/**
	 * 设置用户真实姓名
	 * @param userRealName
	 */
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	/**
	 * 获取管理员真实姓名
	 * @return sysUserRealName
	 */
	public String getSysUserRealName() {
		return sysUserRealName;
	}

	/**
	 * 设置管理员真实姓名
	 * @param sysUserRealName
	 */
	public void setSysUserRealName(String sysUserRealName) {
		this.sysUserRealName = sysUserRealName;
	}

	/**
	 * 获取状态的中文描述
	 * @return stateStr
	 */
	public String getStateStr() {
		String state = getState();
		if(STATE_WAITE_CONFIRM.equals(state)){
			return "待确认";
		} else if(STATE_CONFIRMED.equals(state)){
			return "已确认";
		} 
		return stateStr;
	}

	/**
	 * 设置状态的中文描述
	 * @param stateStr
	 */
	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}

}
