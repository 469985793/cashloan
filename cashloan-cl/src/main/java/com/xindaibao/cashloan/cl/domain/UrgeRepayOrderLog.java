package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 催款记录表实体
 */
public class UrgeRepayOrderLog implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	private Long id;

	/**
	 * 催收订单id
	 */
	private Long dueId;

	/**
	 * 借款id
	 */
	private Long borrowId;

	/**
	 * 催款人id
	 */
	private Long userId;

	/**
	 * 催收时间
	 */
	private Date createTime;

	/**
	 * 状态           20催收中;30承诺还款
	 */
	private String state;

	/**
	 * 备注说明
	 */
	private String remark;

	/**
	 * 承诺还款时间
	 */
	private Date promiseTime;
	
	/**
	 * 催款方式  10 电话；20 邮件 ；30 短信；40现场沟通；50 其他
	 */
	private String way;

	public Date getPromiseTime() {
		return promiseTime;
	}

	public void setPromiseTime(Date promiseTime) {
		this.promiseTime = promiseTime;
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
	 * @param 要设置的主键Id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取催收订单id
	 * 
	 * @return 催收订单id
	 */
	public Long getDueId() {
		return dueId;
	}

	/**
	 * 设置催收订单id
	 * 
	 * @param dueId
	 *            要设置的催收订单id
	 */
	public void setDueId(Long dueId) {
		this.dueId = dueId;
	}

	/**
	 * 获取借款id
	 * 
	 * @return 借款id
	 */
	public Long getBorrowId() {
		return borrowId;
	}

	/**
	 * 设置借款id
	 * 
	 * @param borrowId
	 *            要设置的借款id
	 */
	public void setBorrowId(Long borrowId) {
		this.borrowId = borrowId;
	}

	/**
	 * 获取催款人id
	 * 
	 * @return 催款人id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置催款人id
	 * 
	 * @param userId
	 *            要设置的催款人id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取催收时间
	 * 
	 * @return 催收时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置催收时间
	 * 
	 * @param createTime
	 *            要设置的催收时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取状态
	 * 
	 * @return 状态
	 */
	public String getState() {
		return state;
	}

	/**
	 * 设置状态
	 * 
	 * @param state
	 *            要设置的状态
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 获取备注说明
	 * 
	 * @return 备注说明
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注说明
	 * 
	 * @param remark
	 *            要设置的备注说明
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	
}