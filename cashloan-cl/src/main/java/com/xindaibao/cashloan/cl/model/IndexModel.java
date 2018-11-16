package com.xindaibao.cashloan.cl.model;

import java.util.Date;

import com.xindaibao.cashloan.core.domain.Borrow;

/**
 * 首页参数
 * @author
 *
 */
public class IndexModel extends Borrow {

	private static final long serialVersionUID = 1L;
	
	private String cardNo;
	
	private Date creditTime;
	
	private Date loanTime;

	/**
	 * @return the cardNo
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * @return the creditTime
	 */
	public Date getCreditTime() {
		return creditTime;
	}

	/**
	 * @param creditTime the creditTime to set
	 */
	public void setCreditTime(Date creditTime) {
		this.creditTime = creditTime;
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
}
