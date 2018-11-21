package com.xindaibao.cashloan.cl.model;

import java.util.Date;

/**
 * 渠道统计model
 * 
 * @author
 * @version 1.0.0


 */
public class ChannelCountModel {
	/**
	 * 统计时间---注册时间
	 */
	private Date time;

	public String getLinker() {
		return linker;
	}

	public void setLinker(String linker) {
		this.linker = linker;
	}

	/**
	 * 渠道供应商
	 */
	private String linker;
	/**
	 * 渠道编码
	 */
	private String code;
	/**
	 * 渠道名称
	 */
	private String name;
	/**
	 * 注册人数
	 */
	private String registerCount;
	/**
	 * 借款人数
	 */
	private String borrowMember;
	/**
	 * 借款次数
	 */
	private String borrowCount;
	/**
	 * 借款金额
	 */
	private String borrowAmout;
	/**
	 * 逾期坏账率
	 */
	private String badRate;
	/**
	 * 放款成功笔数
	 */
	private String payCount;
	/**
	 * 放款成功累计金额
	 */
	private String payAccount;

	/**
	 * 申请借款次数（渠道-单人次统计）
	 */
	private String userBorrowCount;

	public String getUserBorrowCount() {
		return userBorrowCount;
	}

	public void setUserBorrowCount(String userBorrowCount) {
		this.userBorrowCount = userBorrowCount;
	}

	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegisterCount() {
		return registerCount;
	}
	public void setRegisterCount(String registerCount) {
		this.registerCount = registerCount;
	}
	public String getBorrowMember() {
		return borrowMember;
	}
	public void setBorrowMember(String borrowMember) {
		this.borrowMember = borrowMember;
	}
	public String getBorrowCount() {
		return borrowCount;
	}
	public void setBorrowCount(String borrowCount) {
		this.borrowCount = borrowCount;
	}
	public String getBorrowAmout() {
		return borrowAmout;
	}
	public void setBorrowAmout(String borrowAmout) {
		this.borrowAmout = borrowAmout;
	}
	public String getBadRate() {
		return badRate;
	}
	public void setBadRate(String badRate) {
		this.badRate = badRate;
	}
	public String getPayCount() {
		return payCount;
	}
	public void setPayCount(String payCount) {
		this.payCount = payCount;
	}
	public String getPayAccount() {
		return payAccount;
	}
	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

}
