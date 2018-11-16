package com.xindaibao.cashloan.cl.model;

import java.util.Date;

import com.xindaibao.cashloan.cl.domain.BorrowRepay;

import tool.util.BigDecimalUtil;

public class ManageBRepayModel extends BorrowRepay {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 真实姓名
	 */
	private String realName;
 
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 订单号
	 */
	private String orderNo;

	/**
	 * 借款金额
	 */
	private Double borrowAmount;

	/**
	 * 还款金额
	 */
	private Double repayAmount;
	
	/**
	 * 还款总额
	 */
	private Double repayTotal;
	
	/**
	 * 借款时间
	 */
	private Date borrowTime;

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

	public Double getRepayTotal() {
		this.repayTotal =  BigDecimalUtil.add(this.getRepayAmount(),this.getPenaltyAmout());
		return repayTotal;
	}

	public void setRepayTotal(Double repayTotal) {
		this.repayTotal = repayTotal;
	}

	public Date getBorrowTime() {
		return borrowTime;
	}

	public void setBorrowTime(Date borrowTime) {
		this.borrowTime = borrowTime;
	}
	
	
	
	 
	
}
