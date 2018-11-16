package com.xindaibao.cashloan.cl.model;

import com.xindaibao.cashloan.cl.domain.ProfitCashLog;

public class ManageCashLogModel extends ProfitCashLog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;

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
}
