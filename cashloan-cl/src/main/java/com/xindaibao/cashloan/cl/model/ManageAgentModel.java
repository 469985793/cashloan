package com.xindaibao.cashloan.cl.model;

import com.xindaibao.cashloan.cl.domain.UserInvite;


public class ManageAgentModel extends UserInvite {

	private static final long serialVersionUID = 1L;

	private String level;
	
	private String count;
	
	private double borrowAmout;
	
	private double penaltyAmout;
	
	private double repayAmount;
	
	private double agentAmount;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public double getBorrowAmout() {
		return borrowAmout;
	}

	public void setBorrowAmout(double borrowAmout) {
		this.borrowAmout = borrowAmout;
	}

	public double getPenaltyAmout() {
		return penaltyAmout;
	}

	public void setPenaltyAmout(double penaltyAmout) {
		this.penaltyAmout = penaltyAmout;
	}

	public double getRepayAmount() {
		return repayAmount;
	}

	public void setRepayAmount(double repayAmount) {
		this.repayAmount = repayAmount;
	}

	public double getAgentAmount() {
		return agentAmount;
	}

	public void setAgentAmount(double agentAmount) {
		this.agentAmount = agentAmount;
	}

}
