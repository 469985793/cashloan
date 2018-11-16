package com.xindaibao.cashloan.rc.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 场景与第三方征信接口关联关系实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-14 13:42:36




 */
 public class SceneBusiness implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	private Long id;

	/**
	 * 场景
	 */
	private String scene;

	/**
	 * 第三方征信接口主键
	 */
	private Long businessId;

	/**
	 * 获取方式 00，获取一次；10，每次获取；20，固定周期获取（单位为天）
	 */
	private String getWay;

	/**
	 * 周期，单位为天 当get_way为20时有效
	 */
	private Integer period;

	/**
	 * 状态 10，启用；20，禁用
	 */
	private String state;

	/**
	 * 添加时间
	 */
	private Date addTime;
	
	/**
	 * 类型 10 第三方接口  30 系统统计数据接口
	 */
	private String type;

	/**
	 * 获取主键Id
	 *
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置主键Id
	 * 
	 * @param 要设置的主键Id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取场景
	 *
	 * @return 场景
	 */
	public String getScene() {
		return scene;
	}

	/**
	 * 设置场景
	 * 
	 * @param scene
	 *            要设置的场景
	 */
	public void setScene(String scene) {
		this.scene = scene;
	}


	/**
	 * @return the 第三方征信接口主键
	 */
	public Long getBusinessId() {
		return businessId;
	}

	/**
	 * @param 第三方征信接口主键 the businessId to set
	 */
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	/**
	 * 获取获取方式 00，获取一次；10，每次获取；20，固定周期获取（单位为天）
	 *
	 * @return 获取方式 00，获取一次；10，每次获取；20，固定周期获取（单位为天）
	 */
	public String getGetWay() {
		return getWay;
	}

	/**
	 * 设置获取方式 00，获取一次；10，每次获取；20，固定周期获取（单位为天）
	 * 
	 * @param getWay
	 *            要设置的获取方式 00，获取一次；10，每次获取；20，固定周期获取（单位为天）
	 */
	public void setGetWay(String getWay) {
		this.getWay = getWay;
	}

	/**
	 * 获取周期，单位为天 当get_way为20时有效
	 *
	 * @return 周期，单位为天 当get_way为20时有效
	 */
	public Integer getPeriod() {
		return period;
	}

	/**
	 * 设置周期，单位为天 当get_way为20时有效
	 * 
	 * @param period
	 *            要设置的周期，单位为天 当get_way为20时有效
	 */
	public void setPeriod(Integer period) {
		this.period = period;
	}

	/**
	 * 获取状态 10，启用；20，禁用
	 *
	 * @return 状态 10，启用；20，禁用
	 */
	public String getState() {
		return state;
	}

	/**
	 * 设置状态 10，启用；20，禁用
	 * 
	 * @param state
	 *            要设置的状态 10，启用；20，禁用
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 获取添加时间
	 *
	 * @return 添加时间
	 */
	public Date getAddTime() {
		return addTime;
	}

	/**
	 * 设置添加时间
	 * 
	 * @param addTime
	 *            要设置的添加时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	/** 
	 * 获取类型10第三方接口30系统统计数据接口
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/** 
	 * 设置类型10第三方接口30系统统计数据接口
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

}