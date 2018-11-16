package com.xindaibao.cashloan.cl.model;

/**
 * 支出明细
 * @author
 * @version 1.0
 * @date 2017年3月21日下午5:15:11


 * 

 */
public class ExpendDetailModel {

	/**
	 * 姓名
	 */
	private String realName;
	
	/**
	 * 日期
	 */
	private String date;
	
	/**
	 * 金额
	 */
	private String amount;

	/** 
	 * 获取姓名
	 * @return realName
	 */
	public String getRealName() {
		return realName;
	}

	/** 
	 * 设置姓名
	 * @param realName
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

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
	 * 获取金额
	 * @return amount
	 */
	public String getAmount() {
		return amount;
	}

	/** 
	 * 设置金额
	 * @param amount
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
}
