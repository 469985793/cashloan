package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 意见反馈表实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-14 11:30:57
 */
 public class Opinion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户标识
    */
    private Long userId;

    /**
    * 意见
    */
    private String opinion;
    
    /**
     * 管理员标识
     */
    private Long sysUserId;
    
    /**
     * 反馈
     */
    private String feedback;
    
    /**
     * 状态 10待确认，20已确认
     */
    private String state;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 确认时间
     */
    private Date confirmTime;

	/**
	 * 获取主键Id
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置主键Id
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取用户标识
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置用户标识
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取意见
	 * @return opinion
	 */
	public String getOpinion() {
		return opinion;
	}

	/**
	 * 设置意见
	 * @param opinion
	 */
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	/**
	 * 获取反馈
	 * @return feedback
	 */
	public String getFeedback() {
		return feedback;
	}

	/**
	 * 设置反馈
	 * @param feedback
	 */
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	/**
	 * 获取管理员标识
	 * @return sysUserId
	 */
	public Long getSysUserId() {
		return sysUserId;
	}

	/**
	 * 设置管理员标识
	 * @param sysUserId
	 */
	public void setSysUserId(Long sysUserId) {
		this.sysUserId = sysUserId;
	}

	/**
	 * 获取状态10待确认，20已确认
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 * 设置状态10待确认，20已确认
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 获取创建时间
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取确认时间
	 * @return confirmTime
	 */
	public Date getConfirmTime() {
		return confirmTime;
	}

	/**
	 * 设置确认时间
	 * @param confirmTime
	 */
	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}
    

}

