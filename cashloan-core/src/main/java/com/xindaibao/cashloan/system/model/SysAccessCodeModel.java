package com.xindaibao.cashloan.system.model;

import com.xindaibao.cashloan.system.domain.SysAccessCode;

/**
 * 访问码
 * @author
 *
 */
public class SysAccessCodeModel extends SysAccessCode {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 角色名
	 */
	private String name;
	
	/**
	 * 状态对应中文名 10启用 20禁用
	 */
	private String stateStr;

	/**禁用*/
	public static final String STATE_ENABLE = "10";
	/**启用 */
	public static final String STATE_DISABLE = "20";
	
	public String getStateStr() {
		if(STATE_ENABLE.equals(this.getState())){
			this.setStateStr("启用");
		}else if(STATE_DISABLE.equals(this.getState())){
			this.setStateStr("禁用");
		}else{
			this.setStateStr("--");
		}
		return stateStr;
	}

	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
	
}
