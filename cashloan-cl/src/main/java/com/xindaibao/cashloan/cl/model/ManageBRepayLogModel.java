package com.xindaibao.cashloan.cl.model;

import java.util.Date;

import tool.util.BigDecimalUtil;
import tool.util.StringUtil;

import com.xindaibao.cashloan.cl.domain.BorrowRepayLog;

public class ManageBRepayLogModel extends BorrowRepayLog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 状态
	 */
	private String state;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 借款金额
	 */
	private Double borrowAmount;
	/**
	 * 综合费用
	 */
	private Double fee;
	/**
	 * 还款金额（还款计划）应该
	 */
	private Double repayAmount;
	/**
	 * 已还款金额（还款记录）实际
	 */
	private Double repayLogAmount;
	/**
	 * 已还款时间
	 */
	private Date repayPlanTime;
	/**
	 * 应还逾期金额 
	 */
	private Double repayPenalty;
	/**
	 * 姓名
	 */
	private String realName;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 应还款总额  还款计划中的金额
	 */
	private Double repayTotal;
	
	/**
	 * 已还款总额    还款记录中的金额
	 */
	private Double repayYesTotal;
	
	public Double getRepayYesTotal() {
		this.repayYesTotal = BigDecimalUtil.add(
				StringUtil.isNotBlank(this.getRepayLogAmount())?this.getRepayLogAmount():0.0,
				StringUtil.isNotBlank(this.getPenaltyAmout())?this.getPenaltyAmout():0.0); 
		return repayYesTotal;
	}

	public void setRepayYesTotal(Double repayYesTotal) {
		this.repayYesTotal = repayYesTotal;
	}
	
	public Double getRepayTotal() {
		this.repayTotal = BigDecimalUtil.add(
				StringUtil.isNotBlank(this.getRepayAmount())?this.getRepayAmount():0.0,
				StringUtil.isNotBlank(this.getRepayPenalty())?this.getRepayPenalty():0.0); 
		return repayTotal;
	}

	public void setRepayTotal(Double repayTotal) {
		this.repayTotal = repayTotal;
	}
	public Double getRepayPenalty() {
		return repayPenalty;
	}

	public void setRepayPenalty(Double repayPenalty) {
		this.repayPenalty = repayPenalty;
	}

	public Date getRepayPlanTime() {
		return repayPlanTime;
	}

	public void setRepayPlanTime(Date repayPlanTime) {
		this.repayPlanTime = repayPlanTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Double getBorrowAmount() {
		return borrowAmount;
	}

	public void setBorrowAmount(Double borrowAmount) {
		this.borrowAmount = borrowAmount;
	}

	public Double getRepayAmount() {
		return repayAmount;
	}

	public void setRepayAmount(Double repayAmount) {
		this.repayAmount = repayAmount;
	}

	public Double getRepayLogAmount() {
		return repayLogAmount;
	}

	public void setRepayLogAmount(Double repayLogAmount) {
		this.repayLogAmount = repayLogAmount;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	
	
	
}
