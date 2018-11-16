package com.xindaibao.cashloan.cl.model;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.core.model.BorrowModel;


/**
 * 借款业务管理
 */
public class ManageBorrowModel extends Borrow {

	private static final long serialVersionUID = 1L;

	public static ManageBorrowModel instance(Borrow borrow) {
		ManageBorrowModel borrowModel = new ManageBorrowModel();
		BeanUtils.copyProperties(borrow, borrowModel);
		return borrowModel;
	}
	
	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 手机号码
	 */
	private String phone;

	/**
	 * 状态中文含义
	 */
	private String stateStr;

	/**
	 * 待还款金额/已还款金额
	 */
	private Double repayAmount;

	/**
	 * 还款时间
	 */
	private String repayTime;

	/**
	 * 逾期罚金
	 */
	private Double penaltyAmout;

	/**
	 * 逾期罚金
	 */
	private String penaltyDay;

    /**
    * 借款订单id
    */
    private long borrowId;
    /**
     * 放款时间
     */
    private Date loanTime;
    
    /**
	 * 逾期等级
	 */
	private String level;
	
	
	/**
	 * 应还款总额 加逾期金额
	 */
	private Double repayTotal;
	
	/**
	 * 已还款总额 加逾期金额
	 */
	private Double repayYesTotal;

	/**
	 * 渠道信息
	 */
	private String channelName;

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Double getRepayTotal() {
		return repayTotal;
	}

	public void setRepayTotal(Double repayTotal) {
		this.repayTotal = repayTotal;
	}
	
	public Double getRepayYesTotal() {
		return repayYesTotal;
	}

	public void setRepayYesTotal(Double repayYesTotal) {
		this.repayYesTotal = repayYesTotal;
	}

	public Date getLoanTime() {
		return loanTime;
	}

	public void setLoanTime(Date loanTime) {
		this.loanTime = loanTime;
	}

	public Double getRepayAmount() {
		return repayAmount;
	}

	public void setRepayAmount(Double repayAmount) {
		this.repayAmount = repayAmount;
	}

	public String getRepayTime() {
		return repayTime;
	}

	public void setRepayTime(String repayTime) {
		this.repayTime = repayTime;
	}

	public Double getPenaltyAmout() {
		return penaltyAmout;
	}

	public void setPenaltyAmout(Double penaltyAmout) {
		this.penaltyAmout = penaltyAmout;
	}

	public String getPenaltyDay() {
		return penaltyDay;
	}

	public void setPenaltyDay(String penaltyDay) {
		this.penaltyDay = penaltyDay;
	}

	public String getStateStr() {
		this.stateStr = BorrowModel.manageConvertBorrowState(this.getState());
		return stateStr;
	}

	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取借款订单id
	 * @return borrowId
	 */
	public long getBorrowId() {
		return borrowId;
	}

	/**
	 * 设置借款订单id
	 * @param borrowId
	 */
	public void setBorrowId(long borrowId) {
		this.borrowId = borrowId;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
