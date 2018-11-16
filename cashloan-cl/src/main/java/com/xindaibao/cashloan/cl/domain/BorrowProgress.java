package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 借款进度表实体
 */
 public class BorrowProgress implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	private Long id;

	/**
	 * 关联用户id
	 */
	private Long userId;

	/**
	 * 借款信息id
	 */
	private Long borrowId;

	/**
	 * 借款进度状态 10 自动审核中 11 自动审核通过 12自动审核拒绝（待人工复审） 20人工复审通过 21人工复审核拒绝 30 还款中 35 还款完成 40逾期
	 */
	private String state;

	/**
	 * 进度描述
	 */
	private String remark;

	/**
	 * 进度生成时间
	 */
	private Date createTime;
	
	/**
	 * 审核备注
	 */
	private String auditRemark;
	
	
	/**
	 * 审核人
	 */
	private Long auditUser;
	
	
	public BorrowProgress() {
		super();
	}

	public BorrowProgress(Long userId, Long borrowId, String state, String remark) {
		super();
		this.userId = userId;
		this.borrowId = borrowId;
		this.state = state;
		this.remark = remark;
	}

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
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取关联用户id
	 * 
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置关联用户id
	 * 
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取借款信息id
	 * 
	 * @return borrowId
	 */
	public Long getBorrowId() {
		return borrowId;
	}

	/**
	 * 设置借款信息id
	 * 
	 * @param borrowId
	 */
	public void setBorrowId(Long borrowId) {
		this.borrowId = borrowId;
	}

	/**
	 * 获取借款进度状态10自动审核中11自动审核通过12自动审核拒绝（待人工复审）20人工复审通过21人工复审核拒绝30还款中35还款完成40逾期
	 * 
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 * 设置借款进度状态10自动审核中11自动审核通过12自动审核拒绝（待人工复审）20人工复审通过21人工复审核拒绝30还款中35还款完成40逾期
	 * 
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 获取进度描述
	 * 
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置进度描述
	 * 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取进度生成时间
	 * 
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置进度生成时间
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getAuditRemark() {
		return auditRemark;
	}

	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}

	public Long getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(Long auditUser) {
		this.auditUser = auditUser;
	}

}