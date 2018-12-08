package com.xindaibao.cashloan.cl.model;

import java.util.Date;

import com.xindaibao.cashloan.core.domain.Borrow;
import org.activiti.rest.service.api.repository.BaseModelResource;

public class ManageBorrowExportModel extends BaseModelResource {

	private static final long serialVersionUID = 1L;
	/**
	 * 用户名
	 */
	private Integer id;
	/**
	 * 用户名
	 */
	private String firstName;
	/**
	 * 订单号
	 */
	private String indentNo;
	/**
	 * 借款金额
	 */
	private String balance;
	/**
	 * 借款期限
	 */
	private String cycle;
	/**
	 * 创建时间
	 */
	private Date createdTime;
	/**
	 * 订单状态
	 */
	private Integer state;
	/**
	 * 用户名
	 */
	private Date lastbackTime;
	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 逾期天数
	 */
	private String penaltyDay;
	
	/**
	 * 逾期罚金
	 */
	private Integer overdueFee;
	
	/**
	 * 还款时间
	 */
	private Date repayTime;
	
	/**
	 * 还款金额
	 */
	private Integer actualbackAmt;
	/**
	 * 还款金额
	 */
	private Date exportTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getIndentNo() {
		return indentNo;
	}

	public void setIndentNo(String indentNo) {
		this.indentNo = indentNo;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}


	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getLastbackTime() {
		return lastbackTime;
	}

	public void setLastbackTime(Date lastbackTime) {
		this.lastbackTime = lastbackTime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPenaltyDay() {
		return penaltyDay;
	}

	public void setPenaltyDay(String penaltyDay) {
		this.penaltyDay = penaltyDay;
	}

	public Integer getOverdueFee() {
		return overdueFee;
	}

	public void setOverdueFee(Integer overdueFee) {
		this.overdueFee = overdueFee;
	}

	public Date getRepayTime() {
		return repayTime;
	}

	public void setRepayTime(Date repayTime) {
		this.repayTime = repayTime;
	}

	public Integer getActualbackAmt() {
		return actualbackAmt;
	}

	public void setActualbackAmt(Integer actualbackAmt) {
		this.actualbackAmt = actualbackAmt;
	}

	public Date getExportTime() {
		return exportTime;
	}

	public void setExportTime(Date exportTime) {
		this.exportTime = exportTime;
	}
}
