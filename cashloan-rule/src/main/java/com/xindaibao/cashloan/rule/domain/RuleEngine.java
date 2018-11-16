package com.xindaibao.cashloan.rule.domain;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 规则引擎
 * 
 * @author
 * @version 1.0.0


 * 

 */
public class RuleEngine implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 是否启用结果模式 10 不启用 20 启用
	 */
	public static final String TYPE_RESULT_ENABLE = "20";
	public static final String TYPE_RESULT_DISABLE = "10";
	
	/**
	 *  状态：10启用，20禁用
	 */
	public static final String STATE_ENABLE = "10";
	public static final String STATE_DISABLE = "20";
	

	/**
	 * 主键Id
	 */
	private Long id;

	/**
	 * 规则名称
	 */
	private String name;

	/**
	 * 状态：10启用，20禁用
	 */
	private String state;

	/**
	 * 规则引擎下的配置数量
	 */
	private Integer configCount;

	/**
	 * 积分
	 */
	private Integer integral;

	/**
	 * 扩展字段
	 */
	private String reqExt;

	/**
	 * 添加IP
	 */
	private String addIp;

	/**
	 * 添加时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date addTime;
	/**
	 * 权重  作废 预留字段
	 */
	private Integer sort;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 评分字段模式下是否启用 结果评级
	 */
	private String typeResultStatus;

	/**
	 * 获取主键Id
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	public String getTypeResultStatus() {
		return typeResultStatus;
	}

	public void setTypeResultStatus(String typeResultStatus) {
		this.typeResultStatus = typeResultStatus;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 设置主键Id
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取规则名称
	 * 
	 * @return 规则名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置规则名称
	 * 
	 * @param name
	 *            要设置的规则名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取状态：10启用，20禁用
	 * 
	 * @return 状态：10启用，20禁用
	 */
	public String getState() {
		return state;
	}

	/**
	 * 设置状态：10启用，20禁用
	 * 
	 * @param state
	 *            要设置的状态：10启用，20禁用
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 获取规则引擎下的配置数量
	 * 
	 * @return 规则引擎下的配置数量
	 */
	public Integer getConfigCount() {
		return configCount;
	}

	/**
	 * 设置规则引擎下的配置数量
	 * 
	 * @param configCount
	 *            要设置的规则引擎下的配置数量
	 */
	public void setConfigCount(Integer configCount) {
		this.configCount = configCount;
	}

	/**
	 * 获取扩展字段
	 * 
	 * @return 扩展字段
	 */
	public String getReqExt() {
		return reqExt;
	}

	/**
	 * 设置扩展字段
	 * 
	 * @param reqExt
	 *            要设置的扩展字段
	 */
	public void setReqExt(String reqExt) {
		this.reqExt = reqExt;
	}

	/**
	 * 获取添加IP
	 * 
	 * @return 添加IP
	 */
	public String getAddIp() {
		return addIp;
	}

	/**
	 * 设置添加IP
	 * 
	 * @param addIp
	 *            要设置的添加IP
	 */
	public void setAddIp(String addIp) {
		this.addIp = addIp;
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

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
