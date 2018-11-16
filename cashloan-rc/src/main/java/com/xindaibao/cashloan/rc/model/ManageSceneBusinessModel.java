package com.xindaibao.cashloan.rc.model;

import com.xindaibao.cashloan.rc.domain.SceneBusiness;

/**
 * 场景与第三方征信接口关联关系Model - 后台管理  
 * 
 * @author
 * @version 1.0.0
 * @date 2017年3月18日 下午3:23:36




 */
public class ManageSceneBusinessModel extends SceneBusiness {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 第三方ID
	 */
	private Long tppId;
	
	/**
	 * 征信名称
	 */
	private String tppName;
	
	/**
	 * 征信接口名称
	 */
	private String businessName;
	
	/**
	 * 场景名称
	 */
	private String sceneName;

	/**
	 * 获取方式中文描述
	 */
	private String getWayStr;
	
	/**
	 * 状态中文描述
	 */
	private String stateStr;
	
	/**
	 * 获取征信名称
	 * 
	 * @return tppName
	 */
	public String getTppName() {
		return tppName;
	}

	/**
	 * 设置征信名称
	 * 
	 * @param tppName
	 */
	public void setTppName(String tppName) {
		this.tppName = tppName;
	}

	/**
	 * 获取征信接口名称
	 * 
	 * @return businessName
	 */
	public String getBusinessName() {
		return businessName;
	}

	/**
	 * 设置征信接口名称
	 * 
	 * @param businessName
	 */
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	/**
	 * 获取场景名称
	 * 
	 * @return sceneName
	 */
	public String getSceneName() {
		return sceneName;
	}

	/**
	 * 设置场景名称
	 * 
	 * @param sceneName
	 */
	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	/**
	 * 获取获取方式中文描述
	 * 00，获取一次；10，每次获取；20，固定周期获取（单位为天）
	 * @return getWayStr
	 */
	public String getGetWayStr() {
		if("00".equals(this.getGetWay())){
			getWayStr = "获取一次";
		}else if("10".equals(this.getGetWay())){
			getWayStr = "每次获取";
		}else if("20".equals(this.getGetWay())){
			getWayStr = "固定周期获取";
		}
		return getWayStr;
	}

	/**
	 * 设置获取方式中文描述
	 * 
	 * @param getWayStr
	 */
	public void setGetWayStr(String getWayStr) {
		this.getWayStr = getWayStr;
	}

	/**
	 * 获取状态中文描述
	 * 
	 * @return stateStr
	 */
	public String getStateStr() {
		return stateStr;
	}

	/**
	 * 设置状态中文描述
	 * 
	 * @param stateStr
	 */
	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}

	/** 
	 * 获取第三方ID
	 * @return tppId
	 */
	public Long getTppId() {
		return tppId;
	}

	/** 
	 * 设置第三方ID
	 * @param tppId
	 */
	public void setTppId(Long tppId) {
		this.tppId = tppId;
	}
	
}
