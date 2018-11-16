package com.xindaibao.cashloan.rc.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 场景与第三方征信接口执行记录
 * @author
 * @version 1.0
 * @date 2017年4月11日上午11:44:37




 */
public class SceneBusinessLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    public Long id;
    
    /**
     * 场景配置ID
     */
    public Long sceneId;
    
    /**
     * 借款申请ID
     */
    public Long borrowId;
    
    /**
     * 用户ID
     */
    public Long userId;
    
    /**
     * 第三方ID
     */
    public Long tppId;
    
    /**
     * 第三方接口ID
     */
    public Long businessId;
    
    /**
     * 第三方接口昵称
     */
    public String nid;
    
    /**
     * 记录添加时间
     */
    public Date createTime;
    
    /**
     * 结果更新时间
     */
    public Date updateTime;
    
    /**
     * 接口返回结果
     */
    public String rsState;
    
    /**
     * 接口返回描述
     */
    public String rsDesc;
    
    /**
     * 接口类型
     */
    public String type;

	public SceneBusinessLog() {
		super();
	}

	public SceneBusinessLog(Long sceneId, Long borrowId, Long userId, Long tppId,
			Long businessId, String nid, Date createTime,String type) {
		super();
		this.sceneId = sceneId;
		this.borrowId = borrowId;
		this.userId = userId;
		this.tppId = tppId;
		this.businessId = businessId;
		this.nid = nid;
		this.createTime = createTime;
		this.type = type;
	}

	/** 
	 * 获取主键ID
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/** 
	 * 设置主键ID
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/** 
	 * 获取场景配置ID
	 * @return sceneId
	 */
	public Long getSceneId() {
		return sceneId;
	}

	/** 
	 * 设置场景配置ID
	 * @param sceneId
	 */
	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	/** 
	 * 获取借款申请ID
	 * @return borrowId
	 */
	public Long getBorrowId() {
		return borrowId;
	}

	/** 
	 * 设置借款申请ID
	 * @param borrowId
	 */
	public void setBorrowId(Long borrowId) {
		this.borrowId = borrowId;
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

	/** 
	 * 获取第三方接口昵称
	 * @return nid
	 */
	public String getNid() {
		return nid;
	}

	/** 
	 * 设置第三方接口昵称
	 * @param nid
	 */
	public void setNid(String nid) {
		this.nid = nid;
	}

	/** 
	 * 获取第三方接口ID
	 * @return businessId
	 */
	public Long getBusinessId() {
		return businessId;
	}

	/** 
	 * 设置第三方接口ID
	 * @param businessId
	 */
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	/** 
	 * 获取记录添加时间
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/** 
	 * 设置记录添加时间
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** 
	 * 获取接口返回结果
	 * @return rsState
	 */
	public String getRsState() {
		return rsState;
	}

	/** 
	 * 设置接口返回结果
	 * @param rsState
	 */
	public void setRsState(String rsState) {
		this.rsState = rsState;
	}

	/** 
	 * 获取接口返回描述
	 * @return rsDesc
	 */
	public String getRsDesc() {
		return rsDesc;
	}

	/** 
	 * 设置接口返回描述
	 * @param rsDesc
	 */
	public void setRsDesc(String rsDesc) {
		this.rsDesc = rsDesc;
	}

	/** 
	 * 获取结果更新时间
	 * @return updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/** 
	 * 设置结果更新时间
	 * @param updateTime
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/** 
	 * 获取接口类型
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/** 
	 * 设置接口类型
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/** 
	 * 获取用户ID
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}

	/** 
	 * 设置用户ID
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
    
    

}