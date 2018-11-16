package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 运营商信息--月账单实体
 * @author
 * @version 1.0.0
 * @date 2017-03-13 15:56:20 


 *  

 */
public class OperatorBills implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	private Long id;

	/**
	 * 修改时间
	 */
	private Date gmtModified;

	/**
	 * 号码
	 */
	private String phoneNum;

	/**
	 * 创建时间
	 */
	private Date gmtCreate;

	/**
	 * 语音账单月份
	 */
	private Date month;

	/**
	 * 计费周期-起始日期
	 */
	private Date billMonthDateStart;

	/**
	 * 计费周期-结束日期
	 */
	private Date billMonthDateEnd;

	/**
	 * 本月费用总额（单位为分）
	 */
	private Double billMonthAmt;

	/**
	 * 业务编号
	 */
	private String bizNo;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	

	/**
	 * 获取主键Id
	 *
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置主键Id
	 * @param 要设置的主键Id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取修改时间
	 * @return 修改时间
	 */
	public Date getGmtModified() {
		return gmtModified;
	}

	/**
	 * 设置修改时间
	 * @param gmtModified 要设置的修改时间
	 */
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	/**
	 * 获取号码
	 *
	 * @return 号码
	 */
	public String getPhoneNum() {
		return phoneNum;
	}

	/**
	 * 设置号码
	 * @param phoneNum 要设置的号码
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	/**
	 * 获取创建时间
	 * @return 创建时间
	 */
	public Date getGmtCreate() {
		return gmtCreate;
	}

	/**
	 * 设置创建时间
	 * @param gmtCreate 要设置的创建时间
	 */
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	/**
	 * 获取语音账单月份
	 * @return 语音账单月份
	 */
	public Date getMonth() {
		return month;
	}

	/**
	 * 设置语音账单月份
	 * @param month 要设置的语音账单月份
	 */
	public void setMonth(Date month) {
		this.month = month;
	}

	/**
	 * 获取计费周期-起始日期
	 * @return 计费周期-起始日期
	 */
	public Date getBillMonthDateStart() {
		return billMonthDateStart;
	}

	/**
	 * 设置计费周期-起始日期
	 * @param billMonthDateStart 要设置的计费周期-起始日期
	 */
	public void setBillMonthDateStart(Date billMonthDateStart) {
		this.billMonthDateStart = billMonthDateStart;
	}

	/**
	 * 获取计费周期-结束日期
	 * @return 计费周期-结束日期
	 */
	public Date getBillMonthDateEnd() {
		return billMonthDateEnd;
	}

	/**
	 * 设置计费周期-结束日期
	 * @param billMonthDateEnd 要设置的计费周期-结束日期
	 */
	public void setBillMonthDateEnd(Date billMonthDateEnd) {
		this.billMonthDateEnd = billMonthDateEnd;
	}

	/**
	 * 获取本月费用总额（单位为分）
	 * @return 本月费用总额（单位为分）
	 */
	public Double getBillMonthAmt() {
		return billMonthAmt;
	}

	/**
	 * 设置本月费用总额（单位为分）
	 * @param billMonthAmt 要设置的本月费用总额（单位为分）
	 */
	public void setBillMonthAmt(Double billMonthAmt) {
		this.billMonthAmt = billMonthAmt;
	}

	/**
	 * 获取业务编号
	 * @return 业务编号
	 */
	public String getBizNo() {
		return bizNo;
	}

	/**
	 * 设置业务编号
	 * @param bizNo 要设置的业务编号
	 */
	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}

	/** 
	 * 获取创建时间
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/** 
	 * 设置创建时间
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}