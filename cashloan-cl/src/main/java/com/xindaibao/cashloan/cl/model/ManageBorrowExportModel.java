package com.xindaibao.cashloan.cl.model;

import java.util.Date;

import com.xindaibao.cashloan.core.domain.Borrow;

public class ManageBorrowExportModel extends Borrow{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户名
	 */
	private String realName;
	
	/**
	 * 手机号
	 */
	private String phone;
	
	/**
	 * 放款时间
	 */
	private Date loanTime;
	
	/**
	 * 逾期天数
	 */
	private String penaltyDay;
	
	/**
	 * 逾期罚金
	 */
	private double penaltyAmout;
	
	/**
	 * 还款时间
	 */
	private Date repayTime;
	
	/**
	 * 还款金额
	 */
	private double repayAmount;
	
	/**
	 * 逾期等级
	 */
	private String level;

	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the loanTime
	 */
	public Date getLoanTime() {
		return loanTime;
	}

	/**
	 * @param loanTime the loanTime to set
	 */
	public void setLoanTime(Date loanTime) {
		this.loanTime = loanTime;
	}

	/**
	 * @return the penaltyDay
	 */
	public String getPenaltyDay() {
		return penaltyDay;
	}

	/**
	 * @param penaltyDay the penaltyDay to set
	 */
	public void setPenaltyDay(String penaltyDay) {
		this.penaltyDay = penaltyDay;
	}

	/**
	 * @return the penaltyAmout
	 */
	public double getPenaltyAmout() {
		return penaltyAmout;
	}

	/**
	 * @param penaltyAmout the penaltyAmout to set
	 */
	public void setPenaltyAmout(double penaltyAmout) {
		this.penaltyAmout = penaltyAmout;
	}

	/**
	 * @return the repayTime
	 */
	public Date getRepayTime() {
		return repayTime;
	}

	/**
	 * @param repayTime the repayTime to set
	 */
	public void setRepayTime(Date repayTime) {
		this.repayTime = repayTime;
	}

	/**
	 * @return the repayAmount
	 */
	public double getRepayAmount() {
		return repayAmount;
	}

	/**
	 * @param repayAmount the repayAmount to set
	 */
	public void setRepayAmount(double repayAmount) {
		this.repayAmount = repayAmount;
	}

	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	
}
