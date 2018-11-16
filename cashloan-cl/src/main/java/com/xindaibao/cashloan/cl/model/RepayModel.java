package com.xindaibao.cashloan.cl.model;

import java.util.Date;

public class RepayModel {

	/*还款id*/
	private long id;
	private long borrowId;
	/*还款时间*/
	private Date repayTime;
	private String repayTimeStr;
	/*还款金额*/
	private double amount;
	/*实际到账金额*/
	private double realAmount;
	/*综合费用*/
	private double fee;
	/*逾期罚金*/
	private double penaltyAmout;
	/*逾期罚金*/
	private String penaltyDay;
	/*还款信息*/
	private String msg;
	/*是否逾期*/
	private String isPunish;
	/*订单状态*/
	private String state;
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
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the realAmount
	 */
	public double getRealAmount() {
		return realAmount;
	}
	/**
	 * @param realAmount the realAmount to set
	 */
	public void setRealAmount(double realAmount) {
		this.realAmount = realAmount;
	}
	/**
	 * @return the fee
	 */
	public double getFee() {
		return fee;
	}
	/**
	 * @param fee the fee to set
	 */
	public void setFee(double fee) {
		this.fee = fee;
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
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * @return the repayTimeStr
	 */
	public String getRepayTimeStr() {
		return repayTimeStr;
	}
	/**
	 * @param repayTimeStr the repayTimeStr to set
	 */
	public void setRepayTimeStr(String repayTimeStr) {
		this.repayTimeStr = repayTimeStr;
	}
	/**
	 * @return the isPunish
	 */
	public String getIsPunish() {
		return isPunish;
	}
	/**
	 * @param isPunish the isPunish to set
	 */
	public void setIsPunish(String isPunish) {
		this.isPunish = isPunish;
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
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the borrowId
	 */
	public long getBorrowId() {
		return borrowId;
	}
	/**
	 * @param borrowId the borrowId to set
	 */
	public void setBorrowId(long borrowId) {
		this.borrowId = borrowId;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	
}
