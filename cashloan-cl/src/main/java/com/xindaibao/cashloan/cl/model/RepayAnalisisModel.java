package com.xindaibao.cashloan.cl.model;

import tool.util.BigDecimalUtil;
import tool.util.StringUtil;

/**
 * 每月/每日 还款分析
 * @author
 * @version 1.0
 * @date 2017年3月21日下午3:27:53


 * 

 */
public class RepayAnalisisModel {
	/**
	 * 日期、月份
	 */
	private String date;
	
	/**
	 * 还款笔数
	 */
	private String repayCount;
	
	/**
	 * 逾期笔数
	 */
	private String overdueCount;
	
	/**
	 * 还款金额
	 */
	private String repayAmt;
	
	/**
	 * 逾期还款金额
	 */
	private String penaltyRepayAmt;
	
	/**
	 * 逾期占比
	 */
	private String apr;
	
	/**
	 * 逾期还款金额占比
	 */
	private String amountApr;

	/** 
	 * 获取日期、月份
	 * @return date
	 */
	public String getDate() {
		return date;
	}

	/** 
	 * 设置日期、月份
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/** 
	 * 获取还款笔数
	 * @return repayCount
	 */
	public String getRepayCount() {
		return repayCount;
	}

	/** 
	 * 设置还款笔数
	 * @param repayCount
	 */
	public void setRepayCount(String repayCount) {
		this.repayCount = repayCount;
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
	 * 获取逾期占比
	 * @return apr
	 */
	public String getApr() {
		String repayCount = this.getRepayCount();
		String overdueCount = this.getOverdueCount();
		if(StringUtil.isNotBlank(repayCount)&&StringUtil.isNotBlank(overdueCount) && Double.valueOf(repayCount)>0){
			Double repay = Double.valueOf(repayCount);
			Double overdue = Double.valueOf(overdueCount);
			Double aprValue = overdue/repay;
			apr = StringUtil.isNull(BigDecimalUtil.decimal(aprValue*100,2));
		}else{
			apr = "0.00";
		}
		return apr;
	}

	/** 
	 * 设置逾期占比
	 * @param apr
	 */
	public void setApr(String apr) {
		this.apr = apr;
	}

	/**
	 * @return the penaltyRepayAmount
	 */
	public String getPenaltyRepayAmt() {
		return penaltyRepayAmt;
	}

	/**
	 * @param penaltyRepayAmount the penaltyRepayAmount to set
	 */
	public void setPenaltyRepayAmt(String penaltyRepayAmt) {
		this.penaltyRepayAmt = penaltyRepayAmt;
	}

	/**
	 * @return the amountApr
	 */
	public String getAmountApr() {
		String repayAmt = this.getRepayAmt();
		String penaltyRepayAmt = this.getPenaltyRepayAmt();
		if (StringUtil.isNotBlank(repayAmt)&&StringUtil.isNotBlank(penaltyRepayAmt)&&Double.valueOf(repayAmt)>0) {
			amountApr = StringUtil.isNull(BigDecimalUtil.decimal((Double.valueOf(penaltyRepayAmt)/Double.valueOf(repayAmt))*100,2));
		}else {
			amountApr = "0.00";
		}
		return amountApr;
	}

	/**
	 * @param amountApr the amountApr to set
	 */
	public void setAmountApr(String amountApr) {
		this.amountApr = amountApr;
	}
	
	
	
}
