package com.xindaibao.cashloan.cl.model;

import java.util.Date;


 /**
 * 支付宝查询解析后的信息实体
 * @author
 * @version 1.0.0
 * @date 2017-4-5 上午11:20:28



 */
public class AlipayModel {
	 
	/**
	 * 转账账户
	 */
	private String account;
	/**
	 * 转账金额
	 */
	private String amount;
	/**
	 * 转账备注
	 */
	private String remark;
	/**
	 * 转账时间
	 */
	private Date repayTime;
	/**
	 * 流水号
	 */
	private String serialNumber;
 
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public Date getRepayTime() {
		return repayTime;
	}
	public void setRepayTime(Date repayTime) {
		this.repayTime = repayTime;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	 
}
