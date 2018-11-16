package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 代理用户信息实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-18 16:24:45
 */
 public class ProfitAgent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 分润等级
    */
    private Integer level;

    /**
    * 代理商id（用户id）
    */
    private Long userId;

    /**
    * 分润率
    */
    private Double rate;

    /**
    * 成为代理商时间
    */
    private Date createTime;
    
    private Date updateTime;

    /**
    * 上级代理id
    */
    private Long leaderId;

    /**
    * 二级代理商升级为一级代理商时间
    */
    private Date applyTime;

    /**
    * 二级代理商时的利润率
    */
    private Double oldRate;

    /**
    * 10-启用 20-禁用
    */
    private Integer isUse;


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
	 * 获取分润等级
	 *
	 * @return 分润等级
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * 设置分润等级
	 * 
	 * @param level
	 *            要设置的分润等级
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * 获取代理商id（用户id）
	 *
	 * @return 代理商id（用户id）
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置代理商id（用户id）
	 * 
	 * @param userId
	 *            要设置的代理商id（用户id）
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取分润率
	 *
	 * @return 分润率
	 */
	public Double getRate() {
		return rate;
	}

	/**
	 * 设置分润率
	 * 
	 * @param rate
	 *            要设置的分润率
	 */
	public void setRate(Double rate) {
		this.rate = rate;
	}


	/**
	 * 获取上级代理id
	 *
	 * @return 上级代理id
	 */
	public Long getLeaderId() {
		return leaderId;
	}

	/**
	 * 设置上级代理id
	 * 
	 * @param leaderId
	 *            要设置的上级代理id
	 */
	public void setLeaderId(Long leaderId) {
		this.leaderId = leaderId;
	}

	/**
	 * 获取二级代理商升级为一级代理商时间
	 *
	 * @return 二级代理商升级为一级代理商时间
	 */
	public Date getApplyTime() {
		return applyTime;
	}

	/**
	 * 设置二级代理商升级为一级代理商时间
	 * 
	 * @param applyTime
	 *            要设置的二级代理商升级为一级代理商时间
	 */
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	/**
	 * 获取二级代理商时的利润率
	 *
	 * @return 二级代理商时的利润率
	 */
	public Double getOldRate() {
		return oldRate;
	}

	/**
	 * 设置二级代理商时的利润率
	 * 
	 * @param oldRate
	 *            要设置的二级代理商时的利润率
	 */
	public void setOldRate(Double oldRate) {
		this.oldRate = oldRate;
	}

	/**
	 * 获取10-启用 20-禁用
	 *
	 * @return 10-启用 20-禁用
	 */
	public Integer getIsUse() {
		return isUse;
	}

	/**
	 * 设置10-启用 20-禁用
	 * 
	 * @param isUse
	 *            要设置的10-启用 20-禁用
	 */
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
}