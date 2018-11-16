package com.xindaibao.cashloan.cl.model;

import com.xindaibao.cashloan.cl.domain.BorrowProgress;

public class BorrowProgressModel extends BorrowProgress {
	
	private static final long serialVersionUID = 1L;
	
	/** 申请成功待审核 */
	public static final String STATE_PRE = "10";

	/** 自动审核通过 */
	public static final String STATE_AUTO_PASS = "211";
	/** 自动审核不通过 */
	public static final String STATE_AUTO_REFUSED = "212";
	/** 自动审核未决待人工复审 */
	public static final String STATE_NEED_REVIEW = "220";
	/** 人工复审通过 */
	public static final String STATE_PASS = "221";
	/** 人工复审不通过 */
	public static final String STATE_REFUSED = "222";
	

	/** 打款中/放款中 */
	public static final String STATE_REPAYING = "30";
	/**待放款审核*/
	public static final String WAIT_AUDIT_LOAN="301"; //39
	/**放款审核通过*/
	public static final String AUDIT_LOAN_PASS="302"; //37
	/**放款审核不通过*/
	public static final String AUDIT_LOAN_FAIL="303"; //38
	/** 放款失败 */
	public static final String STATE_REPAY_FAIL = "31";
	/** 贷款中 /已放款 (源订单) */
	public static final String STATE_REPAY = "32";
	
	/** 转续借 (源订单) */
	public static final String STATE_TO_RENEW = "33";
	/** 续借中 (续借订单) */
	public static final String STATE_RENEW = "34";
	/** 续借处理中 */
	public static final String STATE_RENEW_PROCESSING = "35";

	/** 正常还清 */
	public static final String STATE_FINISH = "40";
	/** 逾期已还清 */
	public static final String STATE_DELAY_FINISH = "41";
	/** 逾期已还清-金额减免 */
	public static final String STATE_DELAY_REMISSION_FINISH = "42";
	
	/** 还款处理中*/
	public static final String STATE_REPAY_PROCESSING = "43";
	/** 逾期 */
	public static final String STATE_DELAY = "50";
	/** 坏账 */
	public static final String STATE_BAD = "90";

	private String msg;
	
	private String type;
	
	private String createTimeStr;
	
	/**
	 * 状态描述
	 */
	private String str;
	
	private String alter(String state) {
		String stateStr = state;
		if (STATE_PRE.equals(state)) {
			stateStr = ("申请提交成功");
		} else if (STATE_AUTO_PASS.equals(state)) {
			stateStr = ("审核通过");
		} else if (STATE_AUTO_REFUSED.equals(state)) {
			stateStr = ("审核不通过 ");
		} else if (STATE_NEED_REVIEW.equals(state)) {
			stateStr = ("审核中");
		} else if (STATE_PASS.equals(state)) {
			stateStr = ("复审通过");
		} else if (STATE_REFUSED.equals(state)) {
			stateStr = ("复审不通过");
		} else if (STATE_REPAY_FAIL.equals(state)) {
			stateStr = ("打款中");
		} else if (STATE_REPAYING.equals(state)) {
			stateStr = ("打款中");
		} else if (STATE_REPAY.equals(state)) {
			stateStr = ("已打款");
		} else if ("repay".equals(state)) {//待还款
			stateStr = "待还款";
		} else if (STATE_RENEW_PROCESSING.equals(state)) {
			stateStr = ("续借处理中");
		} else if (STATE_TO_RENEW.equals(state)) {
			stateStr = ("已续借");
		} else if (STATE_RENEW.equals(state)) {
			stateStr = ("待还款");
		} else if (STATE_FINISH.equals(state)) {
			stateStr = ("已赎回");
		} else if (STATE_DELAY_FINISH.equals(state)) {
			stateStr = ("已赎回");
		} else if (STATE_DELAY_REMISSION_FINISH.equals(state)) {
			stateStr = ("已赎回");
		} else if (STATE_REPAY_PROCESSING.equals(state)) {
			stateStr = ("还款处理中");
		} else if (STATE_DELAY.equals(state)) {
			stateStr = ("已逾期");
		} else if (STATE_BAD.equals(state)) {
			stateStr = ("已逾期");
		}else if(WAIT_AUDIT_LOAN.equals(state)){
			stateStr=("待放款审核");
		}else if(AUDIT_LOAN_PASS.equals(state)){
			stateStr=("放款审核通过");
		}else if(AUDIT_LOAN_FAIL.equals(state)){
			stateStr=("放款审核不通过");
		}
		return stateStr;
	}


	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


	/**
	 * @return the createTimeStr
	 */
	public String getCreateTimeStr() {
		return createTimeStr;
	}

	/**
	 * @param createTimeStr the createTimeStr to set
	 */
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}



	/**
	 * @return the str
	 */
	public String getStr() {
		return str;
	}


	/**
	 * @param str the str to set
	 */
	public void setStr(String str) {
		this.str = alter(str);
	}

	
}
