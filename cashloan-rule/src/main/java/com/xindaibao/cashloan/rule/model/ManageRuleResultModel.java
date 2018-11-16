package com.xindaibao.cashloan.rule.model;

import java.util.List;

import com.xindaibao.cashloan.rule.domain.BorrowRuleResult;

public class ManageRuleResultModel extends BorrowRuleResult{

	private static final long serialVersionUID = 1L;
	
	private int total;
	
	private int pass;
	
	private int noPass;
	
	private int review;
	
	private List infoList;
	
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @return the pass
	 */
	public int getPass() {
		return pass;
	}

	/**
	 * @param pass the pass to set
	 */
	public void setPass(int pass) {
		this.pass = pass;
	}

	/**
	 * @return the noPass
	 */
	public int getNoPass() {
		return noPass;
	}

	/**
	 * @param noPass the noPass to set
	 */
	public void setNoPass(int noPass) {
		this.noPass = noPass;
	}

	/**
	 * @return the review
	 */
	public int getReview() {
		return review;
	}

	/**
	 * @param review the review to set
	 */
	public void setReview(int review) {
		this.review = review;
	}

	/**
	 * @return the infoList
	 */
	public List getInfoList() {
		return infoList;
	}

	/**
	 * @param infoList the infoList to set
	 */
	public void setInfoList(List infoList) {
		this.infoList = infoList;
	}

}
