package com.xindaibao.cashloan.rc.model;

import java.io.Serializable;

/**
 * 上数运营商数据统计
 * 
 * @author
 * @version 1.0


 */
public class OperatorCountModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 紧急联系人在过去6个月通话记录中的最小次数
	 */
	private Integer emerConcatTimes6Month;

	/**
	 * 通话总次数
	 */
	private Integer countVoices90;

	/**
	 * 最近90天通话地中无现居地址城市个数
	 */
	private Integer liveAddrVoice90N;

	/**
	 * 最近30天通话次数
	 */
	private Integer voiceTimes30;

	/**
	 * 最近30天主叫次数
	 */
	private Integer voiceTimes30Calling;

	/**
	 * 最近30天被叫次数
	 */
	private Integer voiceTimes30Called;

	/**
	 * 最近90天通话次数
	 */
	private Integer voiceTimes90;

	/**
	 * 最近90天主叫次数
	 */
	private Integer voiceTimes90Calling;

	/**
	 * 最近90天被叫次数
	 */
	private Integer voiceTimes90Called;

	/**
	 * 最近30天通话时长
	 */
	private Integer voiceDuration30;

	/**
	 * 最近30天主叫通话时长
	 */
	private Integer voiceDuration30Calling;

	/**
	 * 最近30天被叫通话时长
	 */
	private Integer voiceDuration30Called;

	/**
	 * 最近90天通话时长
	 */
	private Integer voiceDuration90;

	/**
	 * 最近90天主叫通话时长
	 */
	private Integer voiceDuration90Calling;

	/**
	 * 最近90天被叫通话时长
	 */
	private Integer voiceDuration90Called;

	/**
	 * 最近90天联系号码个数
	 */
	private Integer phoneNumCount90;

	/**
	 * 最近90天主叫号码个数
	 */
	private Integer phoneNumCount90Calling;

	/**
	 * 最近90天被叫号码个数
	 */
	private Integer phoneNumCount90Called;

	/**
	 * 平均月费
	 */
	private Double monthAmt;

	/**
	 * 入网累计月
	 */
	private Integer joinMonthCount;

	/**
	 * 最近90天联系次数超过5次的号码个数
	 */
	private Integer ge5TimesNumCount90;

	/**
	 * 最近90天联系次数超过3次，通话时长超过60秒的号码个数
	 */
	private Integer ge3Times60SNumCount90;

	/**
	 * 最近90天联系最多的20个号码中借款未逾期人数
	 */
	private Integer pre20NumBorrowY90;

	/**
	 * 最近90天联系最多的20个号码中有逾期未还借款人数
	 */
	private Integer pre20NumBorrowN90;

	/**
	 * 最近90天联系最多的20个号码中有逾期90天(M3)已还借款人数
	 */
	private Integer pre20NumBorrowN90M3;

	/**
	 * 最近90天联系最多的20个号码中有逾期30天(M1)以上已还借款人数
	 */
	private Integer pre20NumBorrowNMore30M1;
	
	/**
	 * 最近90天联系最多的20个号码中有逾期30天(M1)以上已还借款人数
	 */
	private Integer pre20NumBorrowNLess30M1;

	private Long userId;

	private String phone;

	/**
	 * 获取最近90天通话地中无现居地址城市个数
	 * 
	 * @return liveAddrVoice90N
	 */
	public Integer getLiveAddrVoice90N() {
		return liveAddrVoice90N;
	}

	/**
	 * 设置最近90天通话地中无现居地址城市个数
	 * 
	 * @param liveAddrVoice90N
	 */
	public void setLiveAddrVoice90N(Integer liveAddrVoice90N) {
		this.liveAddrVoice90N = liveAddrVoice90N;
	}

	/**
	 * 获取最近30天通话次数
	 * 
	 * @return voiceTimes30
	 */
	public Integer getVoiceTimes30() {
		return voiceTimes30;
	}

	/**
	 * 设置最近30天通话次数
	 * 
	 * @param voiceTimes30
	 */
	public void setVoiceTimes30(Integer voiceTimes30) {
		this.voiceTimes30 = voiceTimes30;
	}

	/**
	 * 获取最近30天主叫次数
	 * 
	 * @return voiceTimes30Calling
	 */
	public Integer getVoiceTimes30Calling() {
		return voiceTimes30Calling;
	}

	/**
	 * 设置最近30天主叫次数
	 * 
	 * @param voiceTimes30Calling
	 */
	public void setVoiceTimes30Calling(Integer voiceTimes30Calling) {
		this.voiceTimes30Calling = voiceTimes30Calling;
	}

	/**
	 * 获取最近30天被叫次数
	 * 
	 * @return voiceTimes30Called
	 */
	public Integer getVoiceTimes30Called() {
		return voiceTimes30Called;
	}

	/**
	 * 设置最近30天被叫次数
	 * 
	 * @param voiceTimes30Called
	 */
	public void setVoiceTimes30Called(Integer voiceTimes30Called) {
		this.voiceTimes30Called = voiceTimes30Called;
	}

	/**
	 * 获取最近90天通话次数
	 * 
	 * @return voiceTimes90
	 */
	public Integer getVoiceTimes90() {
		return voiceTimes90;
	}

	/**
	 * 设置最近90天通话次数
	 * 
	 * @param voiceTimes90
	 */
	public void setVoiceTimes90(Integer voiceTimes90) {
		this.voiceTimes90 = voiceTimes90;
	}

	/**
	 * 获取最近90天主叫次数
	 * 
	 * @return voiceTimes90Calling
	 */
	public Integer getVoiceTimes90Calling() {
		return voiceTimes90Calling;
	}

	/**
	 * 设置最近90天主叫次数
	 * 
	 * @param voiceTimes90Calling
	 */
	public void setVoiceTimes90Calling(Integer voiceTimes90Calling) {
		this.voiceTimes90Calling = voiceTimes90Calling;
	}

	/**
	 * 获取最近90天被叫次数
	 * 
	 * @return voiceTimes90Called
	 */
	public Integer getVoiceTimes90Called() {
		return voiceTimes90Called;
	}

	/**
	 * 设置最近90天被叫次数
	 * 
	 * @param voiceTimes90Called
	 */
	public void setVoiceTimes90Called(Integer voiceTimes90Called) {
		this.voiceTimes90Called = voiceTimes90Called;
	}

	/**
	 * 获取最近30天通话时长
	 * 
	 * @return voiceDuration30
	 */
	public Integer getVoiceDuration30() {
		return voiceDuration30;
	}

	/**
	 * 设置最近30天通话时长
	 * 
	 * @param voiceDuration30
	 */
	public void setVoiceDuration30(Integer voiceDuration30) {
		this.voiceDuration30 = voiceDuration30;
	}

	/**
	 * 获取最近30天主叫通话时长
	 * 
	 * @return voiceDuration30Calling
	 */
	public Integer getVoiceDuration30Calling() {
		return voiceDuration30Calling;
	}

	/**
	 * 设置最近30天主叫通话时长
	 * 
	 * @param voiceDuration30Calling
	 */
	public void setVoiceDuration30Calling(Integer voiceDuration30Calling) {
		this.voiceDuration30Calling = voiceDuration30Calling;
	}

	/**
	 * 获取最近30天被叫通话时长
	 * 
	 * @return voiceDuration30Called
	 */
	public Integer getVoiceDuration30Called() {
		return voiceDuration30Called;
	}

	/**
	 * 设置最近30天被叫通话时长
	 * 
	 * @param voiceDuration30Called
	 */
	public void setVoiceDuration30Called(Integer voiceDuration30Called) {
		this.voiceDuration30Called = voiceDuration30Called;
	}

	/**
	 * 获取最近90天通话时长
	 * 
	 * @return voiceDuration90
	 */
	public Integer getVoiceDuration90() {
		return voiceDuration90;
	}

	/**
	 * 设置最近90天通话时长
	 * 
	 * @param voiceDuration90
	 */
	public void setVoiceDuration90(Integer voiceDuration90) {
		this.voiceDuration90 = voiceDuration90;
	}

	/**
	 * 获取最近90天主叫通话时长
	 * 
	 * @return voiceDuration90Calling
	 */
	public Integer getVoiceDuration90Calling() {
		return voiceDuration90Calling;
	}

	/**
	 * 设置最近90天主叫通话时长
	 * 
	 * @param voiceDuration90Calling
	 */
	public void setVoiceDuration90Calling(Integer voiceDuration90Calling) {
		this.voiceDuration90Calling = voiceDuration90Calling;
	}

	/**
	 * 获取最近90天被叫通话时长
	 * 
	 * @return voiceDuration90Called
	 */
	public Integer getVoiceDuration90Called() {
		return voiceDuration90Called;
	}

	/**
	 * 设置最近90天被叫通话时长
	 * 
	 * @param voiceDuration90Called
	 */
	public void setVoiceDuration90Called(Integer voiceDuration90Called) {
		this.voiceDuration90Called = voiceDuration90Called;
	}

	/**
	 * 获取最近90天联系号码个数
	 * 
	 * @return phoneNumCount90
	 */
	public Integer getPhoneNumCount90() {
		return phoneNumCount90;
	}

	/**
	 * 设置最近90天联系号码个数
	 * 
	 * @param phoneNumCount90
	 */
	public void setPhoneNumCount90(Integer phoneNumCount90) {
		this.phoneNumCount90 = phoneNumCount90;
	}

	/**
	 * 获取最近90天主叫号码个数
	 * 
	 * @return phoneNumCount90Calling
	 */
	public Integer getPhoneNumCount90Calling() {
		return phoneNumCount90Calling;
	}

	/**
	 * 设置最近90天主叫号码个数
	 * 
	 * @param phoneNumCount90Calling
	 */
	public void setPhoneNumCount90Calling(Integer phoneNumCount90Calling) {
		this.phoneNumCount90Calling = phoneNumCount90Calling;
	}

	/**
	 * 获取最近90天被叫号码个数
	 * 
	 * @return phoneNumCount90Called
	 */
	public Integer getPhoneNumCount90Called() {
		return phoneNumCount90Called;
	}

	/**
	 * 设置最近90天被叫号码个数
	 * 
	 * @param phoneNumCount90Called
	 */
	public void setPhoneNumCount90Called(Integer phoneNumCount90Called) {
		this.phoneNumCount90Called = phoneNumCount90Called;
	}

	/**
	 * 获取平均月费
	 * 
	 * @return monthAmt
	 */
	public Double getMonthAmt() {
		return monthAmt;
	}

	/**
	 * 设置平均月费
	 * 
	 * @param monthAmt
	 */
	public void setMonthAmt(Double monthAmt) {
		this.monthAmt = monthAmt;
	}

	/**
	 * 获取入网累计月
	 * 
	 * @return joinMonthCount
	 */
	public Integer getJoinMonthCount() {
		return joinMonthCount;
	}

	/**
	 * 设置入网累计月
	 * 
	 * @param joinMonthCount
	 */
	public void setJoinMonthCount(Integer joinMonthCount) {
		this.joinMonthCount = joinMonthCount;
	}

	/**
	 * 获取最近90天联系次数超过5次的号码个数
	 * 
	 * @return ge5TimesNumCount90
	 */
	public Integer getGe5TimesNumCount90() {
		return ge5TimesNumCount90;
	}

	/**
	 * 设置最近90天联系次数超过5次的号码个数
	 * 
	 * @param ge5TimesNumCount90
	 */
	public void setGe5TimesNumCount90(Integer ge5TimesNumCount90) {
		this.ge5TimesNumCount90 = ge5TimesNumCount90;
	}

	/**
	 * 获取最近90天联系次数超过3次，通话时长超过60秒的号码个数
	 * 
	 * @return ge3Times60SNumCount90
	 */
	public Integer getGe3Times60SNumCount90() {
		return ge3Times60SNumCount90;
	}

	/**
	 * 设置最近90天联系次数超过3次，通话时长超过60秒的号码个数
	 * 
	 * @param ge3Times60SNumCount90
	 */
	public void setGe3Times60SNumCount90(Integer ge3Times60SNumCount90) {
		this.ge3Times60SNumCount90 = ge3Times60SNumCount90;
	}

	/**
	 * 获取最近90天联系最多的20个号码中借款未逾期人数
	 * 
	 * @return pre20NumBorrowY90
	 */
	public Integer getPre20NumBorrowY90() {
		return pre20NumBorrowY90;
	}

	/**
	 * 设置最近90天联系最多的20个号码中借款未逾期人数
	 * 
	 * @param pre20NumBorrowY90
	 */
	public void setPre20NumBorrowY90(Integer pre20NumBorrowY90) {
		this.pre20NumBorrowY90 = pre20NumBorrowY90;
	}

	/**
	 * 获取最近90天联系最多的20个号码中有逾期未还借款人数
	 * 
	 * @return pre20NumBorrowN90
	 */
	public Integer getPre20NumBorrowN90() {
		return pre20NumBorrowN90;
	}

	/**
	 * 设置最近90天联系最多的20个号码中有逾期未还借款人数
	 * 
	 * @param pre20NumBorrowN90
	 */
	public void setPre20NumBorrowN90(Integer pre20NumBorrowN90) {
		this.pre20NumBorrowN90 = pre20NumBorrowN90;
	}

	/**
	 * 获取最近90天联系最多的20个号码中有逾期90天(M3)已还借款人数
	 * 
	 * @return pre20NumBorrowN90M3
	 */
	public Integer getPre20NumBorrowN90M3() {
		return pre20NumBorrowN90M3;
	}

	/**
	 * 设置最近90天联系最多的20个号码中有逾期90天(M3)已还借款人数
	 * 
	 * @param pre20NumBorrowN90M3
	 */
	public void setPre20NumBorrowN90M3(Integer pre20NumBorrowN90M3) {
		this.pre20NumBorrowN90M3 = pre20NumBorrowN90M3;
	}

	/**
	 * 获取userId
	 * 
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置userId
	 * 
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取phone
	 * 
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置phone
	 * 
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/** 
	 * 获取通话总次数
	 * @return countVoices
	 */
	public Integer getCountVoices90() {
		return countVoices90;
	}

	/** 
	 * 设置通话总次数
	 * @param countVoices
	 */
	public void setCountVoices90(Integer countVoices90) {
		this.countVoices90 = countVoices90;
	}

	/** 
	 * 获取最近90天联系最多的20个号码中有逾期30天(M1)以上已还借款人数
	 * @return pre20NumBorrowNMore30M1
	 */
	public Integer getPre20NumBorrowNMore30M1() {
		return pre20NumBorrowNMore30M1;
	}

	/** 
	 * 设置最近90天联系最多的20个号码中有逾期30天(M1)以上已还借款人数
	 * @param pre20NumBorrowNMore30M1
	 */
	public void setPre20NumBorrowNMore30M1(Integer pre20NumBorrowNMore30M1) {
		this.pre20NumBorrowNMore30M1 = pre20NumBorrowNMore30M1;
	}

	/** 
	 * 获取最近90天联系最多的20个号码中有逾期30天(M1)以上已还借款人数
	 * @return pre20NumBorrowNLess30M1
	 */
	public Integer getPre20NumBorrowNLess30M1() {
		return pre20NumBorrowNLess30M1;
	}

	/** 
	 * 设置最近90天联系最多的20个号码中有逾期30天(M1)以上已还借款人数
	 * @param pre20NumBorrowNLess30M1
	 */
	public void setPre20NumBorrowNLess30M1(Integer pre20NumBorrowNLess30M1) {
		this.pre20NumBorrowNLess30M1 = pre20NumBorrowNLess30M1;
	}

	/** 
	 * 获取紧急联系人在过去6个月通话记录中的最小次数
	 * @return emerConcatTimes6Month
	 */
	public Integer getEmerConcatTimes6Month() {
		return emerConcatTimes6Month;
	}

	/** 
	 * 设置紧急联系人在过去6个月通话记录中的最小次数
	 * @param emerConcatTimes6Month
	 */
	public void setEmerConcatTimes6Month(Integer emerConcatTimes6Month) {
		this.emerConcatTimes6Month = emerConcatTimes6Month;
	}
}
