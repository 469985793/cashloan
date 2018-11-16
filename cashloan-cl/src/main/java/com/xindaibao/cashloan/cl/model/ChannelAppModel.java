package com.xindaibao.cashloan.cl.model;

import com.xindaibao.cashloan.cl.domain.ChannelApp;

public class ChannelAppModel extends ChannelApp {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 渠道代码
	 */
	private String code;
	
	/**
	 * 渠道名称
	 */
	private String name;
	
	private String state;

	
  /**
   * 应用类型对应中文值 10 android 20 ios
   */
  private String appTypeStr;
  
  
	public String getAppTypeStr() {
		if ("10".equals(this.getAppType())) {
			this.setAppTypeStr("android");
		} else if ("20".equals(this.getAppType())) {
			this.setAppTypeStr("ios");
		}
		return appTypeStr;
	}

	public void setAppTypeStr(String appTypeStr) {
		this.appTypeStr = appTypeStr;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

