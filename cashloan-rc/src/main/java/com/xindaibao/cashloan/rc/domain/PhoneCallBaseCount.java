package com.xindaibao.cashloan.rc.domain;

import java.io.Serializable;
import java.util.Date;

import com.xindaibao.cashloan.core.common.util.DateUtil;
import com.xindaibao.cashloan.rc.model.OperatorCountModel;

/**
 * 运营商通话信息统计实体
 * 
 * @author
 * @version 1.0.0


 */
public class PhoneCallBaseCount implements Serializable {

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
	 * 通话总次数
	 */
	private Integer count;

	/**
	 * 姓名匹配 ，10一致 20不一致
	 */
	private String nameMatching;

	/**
	 * 最近90天通话地中无现居地址城市 ，10未命中 20命中
	 */
	private String addressMatching;

	/**
	 * 运营商月消费(分)
	 */
	private Integer monthSource;

	/**
	 * 在网时长(月数)
	 */
	private Integer countOne;

	/**
	 * 紧急联系人在过去6个月通话记录中的最小次数
	 */
	private Integer countTwo;

	/**
	 * 最近30天通话次数
	 */
	private Integer countThree;

	/**
	 * 最近30天主叫次数
	 */
	private Integer countFour;

	/**
	 * 最近30天被叫次数
	 */
	private Integer countFive;

	/**
	 * 最近30天通话时长(秒)
	 */
	private Integer countSix;

	/**
	 * 最近30天主叫时长(秒)
	 */
	private Integer countSeven;

	/**
	 * 最近30天被叫时长(秒)
	 */
	private Integer countEight;

	/**
	 * 最近90天通话次数
	 */
	private Integer countNine;

	/**
	 * 最近90天主叫次数
	 */
	private Integer countTen;

	/**
	 * 最近90天被叫次数
	 */
	private Integer countEleven;

	/**
	 * 最近90天通话时长(秒)
	 */
	private Integer countTwelve;

	/**
	 * 最近90天主叫时长(秒)
	 */
	private Integer countThirteen;

	/**
	 * 最近90天被叫时长(秒)
	 */
	private Integer countFourteen;

	/**
	 * 最近90天联系号码个数
	 */
	private Integer countFifteen;

	/**
	 * 最近90天主叫号码个数
	 */
	private Integer countSixteen;

	/**
	 * 最近90天被叫号码个数
	 */
	private Integer countSeventeen;

	/**
	 * 最近90天联系次数超过5次的号码个数
	 */
	private Integer countEighteen;

	/**
	 * 最近90天联系次数差超过3次，通话时长超过60秒的号码个数
	 */
	private Integer countNineteen;

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

	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	

	public PhoneCallBaseCount() {
		super();
	}
	
	public PhoneCallBaseCount(OperatorCountModel model) {
		super();
		this.userId = model.getUserId();
		this.count = model.getCountVoices90();
		this.countOne = model.getJoinMonthCount();
		this.countTwo = model.getEmerConcatTimes6Month();
		this.countThree = model.getVoiceTimes30();
		this.countFour = model.getVoiceTimes30Calling();
		this.countFive = model.getVoiceTimes30Called();
		this.countSix = model.getVoiceDuration30();
		this.countSeven = model.getVoiceDuration30Calling();
		this.countEight = model.getVoiceDuration30Called();
		this.countNine = model.getVoiceTimes90();
		this.countTen = model.getVoiceTimes90Calling();
		this.countEleven = model.getVoiceTimes90Called();
		this.countTwelve = model.getVoiceDuration90();;
		this.countThirteen = model.getVoiceDuration90Calling();
		this.countFourteen = model.getVoiceDuration90Called();
		this.countFifteen = model.getPhoneNumCount90();
		this.countSixteen = model.getPhoneNumCount90Calling();
		this.countSeventeen = model.getPhoneNumCount90Called();
		this.countEighteen = model.getGe5TimesNumCount90();
		this.countNineteen = model.getGe3Times60SNumCount90();
		this.createTime = DateUtil.getNow();
	}

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
	 * 
	 * @param 要设置的主键Id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取用户标识
	 *
	 * @return 用户标识
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置用户标识
	 * 
	 * @param userId
	 *            要设置的用户标识
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取通话总次数
	 *
	 * @return 通话总次数
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 设置通话总次数
	 * 
	 * @param count
	 *            要设置的通话总次数
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * 获取姓名匹配 ，10一致 20不一致
	 *
	 * @return 姓名匹配 ，10一致 20不一致
	 */
	public String getNameMatching() {
		return nameMatching;
	}

	/**
	 * 设置姓名匹配 ，10一致 20不一致
	 * 
	 * @param nameMatching
	 *            要设置的姓名匹配 ，10一致 20不一致
	 */
	public void setNameMatching(String nameMatching) {
		this.nameMatching = nameMatching;
	}

	/**
	 * 获取最近90天通话地中无现居地址城市 ，10未命中 20命中
	 *
	 * @return 最近90天通话地中无现居地址城市 ，10未命中 20命中
	 */
	public String getAddressMatching() {
		return addressMatching;
	}

	/**
	 * 设置最近90天通话地中无现居地址城市 ，10未命中 20命中
	 * 
	 * @param addressMatching
	 *            要设置的最近90天通话地中无现居地址城市 ，10未命中 20命中
	 */
	public void setAddressMatching(String addressMatching) {
		this.addressMatching = addressMatching;
	}

	/**
	 * 获取运营商月消费(分)
	 *
	 * @return 运营商月消费(分)
	 */
	public Integer getMonthSource() {
		return monthSource;
	}

	/**
	 * 设置运营商月消费(分)
	 * 
	 * @param monthSource
	 *            要设置的运营商月消费(分)
	 */
	public void setMonthSource(Integer monthSource) {
		this.monthSource = monthSource;
	}

	/**
	 * 获取在网时长(月数)
	 *
	 * @return 在网时长(月数)
	 */
	public Integer getCountOne() {
		return countOne;
	}

	/**
	 * 设置在网时长(月数)
	 * 
	 * @param countOne
	 *            要设置的在网时长(月数)
	 */
	public void setCountOne(Integer countOne) {
		this.countOne = countOne;
	}

	/**
	 * 获取紧急联系人在过去6个月通话记录中的最小次数
	 *
	 * @return 紧急联系人在过去6个月通话记录中的最小次数
	 */
	public Integer getCountTwo() {
		return countTwo;
	}

	/**
	 * 设置紧急联系人在过去6个月通话记录中的最小次数
	 * 
	 * @param countTwo
	 *            要设置的紧急联系人在过去6个月通话记录中的最小次数
	 */
	public void setCountTwo(Integer countTwo) {
		this.countTwo = countTwo;
	}

	/**
	 * 获取最近30天通话次数
	 *
	 * @return 最近30天通话次数
	 */
	public Integer getCountThree() {
		return countThree;
	}

	/**
	 * 设置最近30天通话次数
	 * 
	 * @param countThree
	 *            要设置的最近30天通话次数
	 */
	public void setCountThree(Integer countThree) {
		this.countThree = countThree;
	}

	/**
	 * 获取最近30天主叫次数
	 *
	 * @return 最近30天主叫次数
	 */
	public Integer getCountFour() {
		return countFour;
	}

	/**
	 * 设置最近30天主叫次数
	 * 
	 * @param countFour
	 *            要设置的最近30天主叫次数
	 */
	public void setCountFour(Integer countFour) {
		this.countFour = countFour;
	}

	/**
	 * 获取最近30天被叫次数
	 *
	 * @return 最近30天被叫次数
	 */
	public Integer getCountFive() {
		return countFive;
	}

	/**
	 * 设置最近30天被叫次数
	 * 
	 * @param countFive
	 *            要设置的最近30天被叫次数
	 */
	public void setCountFive(Integer countFive) {
		this.countFive = countFive;
	}

	/**
	 * 获取最近30天通话时长(秒)
	 *
	 * @return 最近30天通话时长(秒)
	 */
	public Integer getCountSix() {
		return countSix;
	}

	/**
	 * 设置最近30天通话时长(秒)
	 * 
	 * @param countSix
	 *            要设置的最近30天通话时长(秒)
	 */
	public void setCountSix(Integer countSix) {
		this.countSix = countSix;
	}

	/**
	 * 获取最近30天主叫时长(秒)
	 *
	 * @return 最近30天主叫时长(秒)
	 */
	public Integer getCountSeven() {
		return countSeven;
	}

	/**
	 * 设置最近30天主叫时长(秒)
	 * 
	 * @param countSeven
	 *            要设置的最近30天主叫时长(秒)
	 */
	public void setCountSeven(Integer countSeven) {
		this.countSeven = countSeven;
	}

	/**
	 * 获取最近30天被叫时长(秒)
	 *
	 * @return 最近30天被叫时长(秒)
	 */
	public Integer getCountEight() {
		return countEight;
	}

	/**
	 * 设置最近30天被叫时长(秒)
	 * 
	 * @param countEight
	 *            要设置的最近30天被叫时长(秒)
	 */
	public void setCountEight(Integer countEight) {
		this.countEight = countEight;
	}

	/**
	 * 获取最近90天通话次数
	 *
	 * @return 最近90天通话次数
	 */
	public Integer getCountNine() {
		return countNine;
	}

	/**
	 * 设置最近90天通话次数
	 * 
	 * @param countNine
	 *            要设置的最近90天通话次数
	 */
	public void setCountNine(Integer countNine) {
		this.countNine = countNine;
	}

	/**
	 * 获取最近90天主叫次数
	 *
	 * @return 最近90天主叫次数
	 */
	public Integer getCountTen() {
		return countTen;
	}

	/**
	 * 设置最近90天主叫次数
	 * 
	 * @param countTen
	 *            要设置的最近90天主叫次数
	 */
	public void setCountTen(Integer countTen) {
		this.countTen = countTen;
	}

	/**
	 * 获取最近90天被叫次数
	 *
	 * @return 最近90天被叫次数
	 */
	public Integer getCountEleven() {
		return countEleven;
	}

	/**
	 * 设置最近90天被叫次数
	 * 
	 * @param countEleven
	 *            要设置的最近90天被叫次数
	 */
	public void setCountEleven(Integer countEleven) {
		this.countEleven = countEleven;
	}

	/**
	 * 获取最近90天通话时长(秒)
	 *
	 * @return 最近90天通话时长(秒)
	 */
	public Integer getCountTwelve() {
		return countTwelve;
	}

	/**
	 * 设置最近90天通话时长(秒)
	 * 
	 * @param countTwelve
	 *            要设置的最近90天通话时长(秒)
	 */
	public void setCountTwelve(Integer countTwelve) {
		this.countTwelve = countTwelve;
	}

	/**
	 * 获取最近90天主叫时长(秒)
	 *
	 * @return 最近90天主叫时长(秒)
	 */
	public Integer getCountThirteen() {
		return countThirteen;
	}

	/**
	 * 设置最近90天主叫时长(秒)
	 * 
	 * @param countThirteen
	 *            要设置的最近90天主叫时长(秒)
	 */
	public void setCountThirteen(Integer countThirteen) {
		this.countThirteen = countThirteen;
	}

	/**
	 * 获取最近90天被叫时长(秒)
	 *
	 * @return 最近90天被叫时长(秒)
	 */
	public Integer getCountFourteen() {
		return countFourteen;
	}

	/**
	 * 设置最近90天被叫时长(秒)
	 * 
	 * @param countFourteen
	 *            要设置的最近90天被叫时长(秒)
	 */
	public void setCountFourteen(Integer countFourteen) {
		this.countFourteen = countFourteen;
	}

	/**
	 * 获取最近90天联系号码个数
	 *
	 * @return 最近90天联系号码个数
	 */
	public Integer getCountFifteen() {
		return countFifteen;
	}

	/**
	 * 设置最近90天联系号码个数
	 * 
	 * @param countFifteen
	 *            要设置的最近90天联系号码个数
	 */
	public void setCountFifteen(Integer countFifteen) {
		this.countFifteen = countFifteen;
	}

	/**
	 * 获取最近90天主叫号码个数
	 *
	 * @return 最近90天主叫号码个数
	 */
	public Integer getCountSixteen() {
		return countSixteen;
	}

	/**
	 * 设置最近90天主叫号码个数
	 * 
	 * @param countSixteen
	 *            要设置的最近90天主叫号码个数
	 */
	public void setCountSixteen(Integer countSixteen) {
		this.countSixteen = countSixteen;
	}

	/**
	 * 获取最近90天被叫号码个数
	 *
	 * @return 最近90天被叫号码个数
	 */
	public Integer getCountSeventeen() {
		return countSeventeen;
	}

	/**
	 * 设置最近90天被叫号码个数
	 * 
	 * @param countSeventeen
	 *            要设置的最近90天被叫号码个数
	 */
	public void setCountSeventeen(Integer countSeventeen) {
		this.countSeventeen = countSeventeen;
	}

	/**
	 * 获取最近90天联系次数超过5次的号码个数
	 *
	 * @return 最近90天联系次数超过5次的号码个数
	 */
	public Integer getCountEighteen() {
		return countEighteen;
	}

	/**
	 * 设置最近90天联系次数超过5次的号码个数
	 * 
	 * @param countEighteen
	 *            要设置的最近90天联系次数超过5次的号码个数
	 */
	public void setCountEighteen(Integer countEighteen) {
		this.countEighteen = countEighteen;
	}

	/**
	 * 获取最近90天联系次数差超过3次，通话时长超过60秒的号码个数
	 *
	 * @return 最近90天联系次数差超过3次，通话时长超过60秒的号码个数
	 */
	public Integer getCountNineteen() {
		return countNineteen;
	}

	/**
	 * 设置最近90天联系次数差超过3次，通话时长超过60秒的号码个数
	 * 
	 * @param countNineteen
	 *            要设置的最近90天联系次数差超过3次，通话时长超过60秒的号码个数
	 */
	public void setCountNineteen(Integer countNineteen) {
		this.countNineteen = countNineteen;
	}

	/**
	 * 获取最近90天联系最多的20个号码中借款未逾期人数
	 *
	 * @return 最近90天联系最多的20个号码中借款未逾期人数
	 */
	public Integer getCountTwenty() {
		return countTwenty;
	}

	/**
	 * 设置最近90天联系最多的20个号码中借款未逾期人数
	 * 
	 * @param countTwenty
	 *            要设置的最近90天联系最多的20个号码中借款未逾期人数
	 */
	public void setCountTwenty(Integer countTwenty) {
		this.countTwenty = countTwenty;
	}

	/**
	 * 获取最近90天联系最多的20个号码中有逾期未还借款人数
	 *
	 * @return 最近90天联系最多的20个号码中有逾期未还借款人数
	 */
	public Integer getCountTwentyOne() {
		return countTwentyOne;
	}

	/**
	 * 设置最近90天联系最多的20个号码中有逾期未还借款人数
	 * 
	 * @param countTwentyOne
	 *            要设置的最近90天联系最多的20个号码中有逾期未还借款人数
	 */
	public void setCountTwentyOne(Integer countTwentyOne) {
		this.countTwentyOne = countTwentyOne;
	}

	/**
	 * 获取最近90天联系最多的20个号码中有逾期90天(M3)已还借款人数
	 *
	 * @return 最近90天联系最多的20个号码中有逾期90天(M3)已还借款人数
	 */
	public Integer getCountTwentyTwo() {
		return countTwentyTwo;
	}

	/**
	 * 设置最近90天联系最多的20个号码中有逾期90天(M3)已还借款人数
	 * 
	 * @param countTwentyTwo
	 *            要设置的最近90天联系最多的20个号码中有逾期90天(M3)已还借款人数
	 */
	public void setCountTwentyTwo(Integer countTwentyTwo) {
		this.countTwentyTwo = countTwentyTwo;
	}

	/**
	 * 获取最近90天联系最多的20个号码中有逾期30天(M1)以上已还借款人数
	 *
	 * @return 最近90天联系最多的20个号码中有逾期30天(M1)以上已还借款人数
	 */
	public Integer getCountTwentyThree() {
		return countTwentyThree;
	}

	/**
	 * 设置最近90天联系最多的20个号码中有逾期30天(M1)以上已还借款人数
	 * 
	 * @param countTwentyThree
	 *            要设置的最近90天联系最多的20个号码中有逾期30天(M1)以上已还借款人数
	 */
	public void setCountTwentyThree(Integer countTwentyThree) {
		this.countTwentyThree = countTwentyThree;
	}

	/**
	 * 获取最近90天联系最多的20个号码中有逾期30天(M1)以内已还借款人数
	 *
	 * @return 最近90天联系最多的20个号码中有逾期30天(M1)以内已还借款人数
	 */
	public Integer getCountTwentyFour() {
		return countTwentyFour;
	}

	/**
	 * 设置最近90天联系最多的20个号码中有逾期30天(M1)以内已还借款人数
	 * 
	 * @param countTwentyFour
	 *            要设置的最近90天联系最多的20个号码中有逾期30天(M1)以内已还借款人数
	 */
	public void setCountTwentyFour(Integer countTwentyFour) {
		this.countTwentyFour = countTwentyFour;
	}

	/**
	 * 获取创建时间
	 *
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 * 
	 * @param createTime
	 *            要设置的创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取更新时间
	 *
	 * @return 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置更新时间
	 * 
	 * @param updateTime
	 *            要设置的更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}