package com.xindaibao.cashloan.cl.model;

import com.xindaibao.cashloan.cl.domain.BorrowRepay;

import java.util.Date;

/**
 * 还款计划Model
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-14 13:42:32


 * 

 */
public class BorrowRepayModel extends BorrowRepay {

	private static final long serialVersionUID = 1L;

	
	/** 还款方式 - 正常还款 */
	public static final String NORMAL_REPAYMENT = "6";

	/** 还款方式 - 逾期减免 */
	public static final String OVERDUE_RELIEF = "20";

	/** 还款方式 - 逾期正常还款 */
	public static final String OVERDUE_REPAYMENT = "30";
	
	/** 还款方式 - 续期申请-原来订单还款 */
	public static final String RENEW_APPLY_REPAYMENT = "40";
	
	
	
	/** 还款状态 -已还款 */
	//public static final String STATE_REPAY_YES = "10";
	public static final String STATE_REPAY_YES = "5";

	/** 还款状态 - 未还款 */
	public static final String STATE_REPAY_NO = "20";

	/**
	 * 借款人手机号
	 */
	private String phone;
	/**
	 * 还款时间格式化 (yyyy-MM-dd HH:mm)
	 */
	private String repayTimeStr;
	/**
	 * 实际还款时间
	 */
	private Date realRepayTime;
	
	/**
	 * 实际还款金额
	 */
	private Double realRepayAmount;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRepayTimeStr() {
		return repayTimeStr;
	}

	public void setRepayTimeStr(String repayTimeStr) {
		this.repayTimeStr = repayTimeStr;
	}


	public Date getRealRepayTime() {
		return realRepayTime;
	}

	public void setRealRepayTime(Date realRepayTime) {
		this.realRepayTime = realRepayTime;
	}

	public Double getRealRepayAmount() {
		return realRepayAmount;
	}

	public void setRealRepayAmount(Double realRepayAmount) {
		this.realRepayAmount = realRepayAmount;
	}
}
