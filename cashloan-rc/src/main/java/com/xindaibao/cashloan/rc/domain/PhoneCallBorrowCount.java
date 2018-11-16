package com.xindaibao.cashloan.rc.domain;

import java.io.Serializable;
import java.util.Date;

import com.xindaibao.cashloan.core.common.util.DateUtil;
import com.xindaibao.cashloan.rc.model.OperatorCountModel;

/**
 * 运营商联系人借款信息统计实体
 * 
 * @author
 * @version 1.0.0


 */
public class PhoneCallBorrowCount implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	private Long id;

	/**
	 * 用户标识
	 */
	private Long userId;

	/**
	 * 最近90天联系最多的20个号码中借款未逾期人数
	 */
	private Integer countTwenty;

	/**
	 * 最近90天联系最多的20个号码中有逾期未还借款人数
	 */
	private Integer countTwentyOne;

	/**
	 * 最近90天联系最多的20个号码中有逾期90天(M3)已还借款人数
	 */
	private Integer countTwentyTwo;

	/**
	 * 最近90天联系最多的20个号码中有逾期30天(M1)以上已还借款人数
	 */
	private Integer countTwentyThree;

	/**
	 * 最近90天联系最多的20个号码中有逾期30天(M1)以内已还借款人数
	 */
	private Integer countTwentyFour;

	/**
	 * 创建时间
	 */
	private Date createTime;
	
	

	public PhoneCallBorrowCount() {
		super();
	}
	
	public PhoneCallBorrowCount(OperatorCountModel model) {
		super();
		this.userId = model.getUserId();
		this.countTwenty = model.getPre20NumBorrowY90();
		this.countTwentyOne = model.getPre20NumBorrowN90();
		this.countTwentyTwo = model.getPre20NumBorrowN90M3();
		this.countTwentyThree = model.getPre20NumBorrowNMore30M1();
		this.countTwentyFour = model.getPre20NumBorrowNLess30M1();
		this.createTime = DateUtil.getNow();
	}



	/** 
	 * 获取主键Id
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/** 
	 * 设置主键Id
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/** 
	 * 获取用户标识
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}

	/** 
	 * 设置用户标识
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/** 
	 * 获取最近90天联系最多的20个号码中借款未逾期人数
	 * @return countTwenty
	 */
	public Integer getCountTwenty() {
		return countTwenty;
	}

	/** 
	 * 设置最近90天联系最多的20个号码中借款未逾期人数
	 * @param countTwenty
	 */
	public void setCountTwenty(Integer countTwenty) {
		this.countTwenty = countTwenty;
	}

	/** 
	 * 获取最近90天联系最多的20个号码中有逾期未还借款人数
	 * @return countTwentyOne
	 */
	public Integer getCountTwentyOne() {
		return countTwentyOne;
	}

	/** 
	 * 设置最近90天联系最多的20个号码中有逾期未还借款人数
	 * @param countTwentyOne
	 */
	public void setCountTwentyOne(Integer countTwentyOne) {
		this.countTwentyOne = countTwentyOne;
	}

	/** 
	 * 获取最近90天联系最多的20个号码中有逾期90天(M3)已还借款人数
	 * @return countTwentyTwo
	 */
	public Integer getCountTwentyTwo() {
		return countTwentyTwo;
	}

	/** 
	 * 设置最近90天联系最多的20个号码中有逾期90天(M3)已还借款人数
	 * @param countTwentyTwo
	 */
	public void setCountTwentyTwo(Integer countTwentyTwo) {
		this.countTwentyTwo = countTwentyTwo;
	}

	/** 
	 * 获取最近90天联系最多的20个号码中有逾期30天(M1)以上已还借款人数
	 * @return countTwentyThree
	 */
	public Integer getCountTwentyThree() {
		return countTwentyThree;
	}

	/** 
	 * 设置最近90天联系最多的20个号码中有逾期30天(M1)以上已还借款人数
	 * @param countTwentyThree
	 */
	public void setCountTwentyThree(Integer countTwentyThree) {
		this.countTwentyThree = countTwentyThree;
	}

	/** 
	 * 获取最近90天联系最多的20个号码中有逾期30天(M1)以内已还借款人数
	 * @return countTwentyFour
	 */
	public Integer getCountTwentyFour() {
		return countTwentyFour;
	}

	/** 
	 * 设置最近90天联系最多的20个号码中有逾期30天(M1)以内已还借款人数
	 * @param countTwentyFour
	 */
	public void setCountTwentyFour(Integer countTwentyFour) {
		this.countTwentyFour = countTwentyFour;
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

	/** 
	 * 获取更新时间
	 * @return updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/** 
	 * 设置更新时间
	 * @param updateTime
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 更新时间
	 */
	private Date updateTime;

	
	

}