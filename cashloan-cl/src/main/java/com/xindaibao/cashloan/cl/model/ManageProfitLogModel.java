package com.xindaibao.cashloan.cl.model;

import java.util.Date;

import com.xindaibao.cashloan.cl.domain.ProfitLog;

public class ManageProfitLogModel extends ProfitLog {
	
	private static final long serialVersionUID = 1L;

	private String agentName;

	private String userName;
	
	private String money;
	
	private String fee;
	
	private Date repayTime;
	
	private Double amount;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the money
	 */
	public String getMoney() {
		return money;
	}

	/**
	 * @param money the money to set
	 */
	public void setMoney(String money) {
		this.money = money;
	}

	/**
	 * @return the fee
	 */
	public String getFee() {
		return fee;
	}

	/**
	 * @param fee the fee to set
	 */
	public void setFee(String fee) {
		this.fee = fee;
	}

	

	/**
	 * 获取repayTime
	 * @return repayTime
	 */
	public Date getRepayTime() {
		return repayTime;
	}

	/**
	 * 设置repayTime
	 * @param repayTime
	 */
	public void setRepayTime(Date repayTime) {
		this.repayTime = repayTime;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @return the agentName
	 */
	public String getAgentName() {
		return agentName;
	}

	/**
	 * @param agentName the agentName to set
	 */
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	
	
}
