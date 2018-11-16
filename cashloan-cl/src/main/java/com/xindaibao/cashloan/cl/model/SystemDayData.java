package com.xindaibao.cashloan.cl.model;

import tool.util.NumberUtil;

/**
 * 平台数据日报
 * @author
 * @version 1.0
 * @date 2017年3月20日下午4:58:12


 * 

 */
public class SystemDayData {

	/**
	 * 日期
	 */
	private String date;
	
	/**
	 * 用户数
	 */
	private String userCount;
	
	/**
	 * 借款笔数
	 */
	private String borrowCount; 
	
	/**
	 * 放款笔数
	 */
	private String loanCount; 
	
	/**
	 * 通过率
	 */ 
	private String apr;  
	
	/**
	 * 逾期笔数
	 */
	private String overdueCount; 
	
	/**
	 * 坏账笔数
	 */ 
	private String badAmtCount;  
	
	/**
	 * 坏账金额
	 */
	private String badAmt;  
	
	/**
	 * 放款金额
	 */
	private String loanAmt;  
	
	/**
	 * 还款金额
	 */
	private String repayAmt;  
	
	/**
	 * 服务费金额
	 */
	private String servFeeAmt;  
	
	/**
	 * 逾期金额
	 */
	private String overdueAmt;  
	
	/**
	 * 催收次数
	 */
	private String urgeRepayCount; 
	
	/**
	 * 逾期罚息 
	 */ 
	private String overdueInterest;


	/** 
	 * 获取日期
	 * @return date
	 */
	public String getDate() {
		return date;
	}

	/** 
	 * 设置日期
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/** 
	 * 获取用户数
	 * @return userCount
	 */
	public String getUserCount() {
		return userCount;
	}

	/** 
	 * 设置用户数
	 * @param userCount
	 */
	public void setUserCount(String userCount) {
		this.userCount = userCount;
	}

	/** 
	 * 获取借款笔数
	 * @return borrowCount
	 */
	public String getBorrowCount() {
		return borrowCount;
	}

	/** 
	 * 设置借款笔数
	 * @param borrowCount
	 */
	public void setBorrowCount(String borrowCount) {
		this.borrowCount = borrowCount;
	}

	/** 
	 * 获取放款笔数
	 * @return loanCount
	 */
	public String getLoanCount() {
		return loanCount;
	}

	/** 
	 * 设置放款笔数
	 * @param loanCount
	 */
	public void setLoanCount(String loanCount) {
		this.loanCount = loanCount;
	}

	/** 
	 * 获取通过率
	 * @return apr
	 */
	public String getApr() {
		Double loan = Double.valueOf(this.getLoanCount());
		Double borrow = Double.valueOf(this.getBorrowCount());
		if(loan>0 && borrow>0){
			apr = NumberUtil.format2Str((loan/borrow)*100);
		}else{
			apr = "0.00";
		}
		return apr;
	}

	/** 
	 * 设置通过率
	 * @param apr
	 */
	public void setApr(String apr) {
		this.apr = apr;
	}

	/** 
	 * 获取逾期笔数
	 * @return overdueCount
	 */
	public String getOverdueCount() {
		return overdueCount;
	}

	/** 
	 * 设置逾期笔数
	 * @param overdueCount
	 */
	public void setOverdueCount(String overdueCount) {
		this.overdueCount = overdueCount;
	}

	/** 
	 * 获取坏账笔数
	 * @return badAmtCount
	 */
	public String getBadAmtCount() {
		return badAmtCount;
	}

	/** 
	 * 设置坏账笔数
	 * @param badAmtCount
	 */
	public void setBadAmtCount(String badAmtCount) {
		this.badAmtCount = badAmtCount;
	}

	/** 
	 * 获取坏账金额
	 * @return badAmt
	 */
	public String getBadAmt() {
		return badAmt;
	}

	/** 
	 * 设置坏账金额
	 * @param badAmt
	 */
	public void setBadAmt(String badAmt) {
		this.badAmt = badAmt;
	}

	/** 
	 * 获取放款金额
	 * @return loanAmt
	 */
	public String getLoanAmt() {
		return loanAmt;
	}

	/** 
	 * 设置放款金额
	 * @param loanAmt
	 */
	public void setLoanAmt(String loanAmt) {
		this.loanAmt = loanAmt;
	}

	/** 
	 * 获取还款金额
	 * @return repayAmt
	 */
	public String getRepayAmt() {
		return repayAmt;
	}

	/** 
	 * 设置还款金额
	 * @param repayAmt
	 */
	public void setRepayAmt(String repayAmt) {
		this.repayAmt = repayAmt;
	}

	/** 
	 * 获取服务费金额
	 * @return servFeeAmt
	 */
	public String getServFeeAmt() {
		return servFeeAmt;
	}

	/** 
	 * 设置服务费金额
	 * @param servFeeAmt
	 */
	public void setServFeeAmt(String servFeeAmt) {
		this.servFeeAmt = servFeeAmt;
	}

	/** 
	 * 获取逾期金额
	 * @return overdueAmt
	 */
	public String getOverdueAmt() {
		return overdueAmt;
	}

	/** 
	 * 设置逾期金额
	 * @param overdueAmt
	 */
	public void setOverdueAmt(String overdueAmt) {
		this.overdueAmt = overdueAmt;
	}

	/** 
	 * 获取催收次数
	 * @return urgeRepayCount
	 */
	public String getUrgeRepayCount() {
		return urgeRepayCount;
	}

	/** 
	 * 设置催收次数
	 * @param urgeRepayCount
	 */
	public void setUrgeRepayCount(String urgeRepayCount) {
		this.urgeRepayCount = urgeRepayCount;
	}

	/** 
	 * 获取逾期罚息
	 * @return overdueInterest
	 */
	public String getOverdueInterest() {
		return overdueInterest;
	}

	/** 
	 * 设置逾期罚息
	 * @param overdueInterest
	 */
	public void setOverdueInterest(String overdueInterest) {
		this.overdueInterest = overdueInterest;
	} 
	
	
}
