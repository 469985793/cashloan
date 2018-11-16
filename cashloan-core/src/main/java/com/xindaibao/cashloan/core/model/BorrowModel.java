package com.xindaibao.cashloan.core.model;

import java.util.Date;

import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.util.DateUtil;
import com.xindaibao.cashloan.core.domain.Borrow;

/**
 * 借款进度状态model
 * @author
 * @version 1.0.0
 * @date 2016年1月12日 下午2:35:42


 * 

 */
public class BorrowModel extends Borrow {

	private static final long serialVersionUID = 1L;
	
	/** 申请成功待审核 */
	public static final String STATE_PRE = "10";

	/** 自动审核通过 */
	public static final String STATE_AUTO_PASS = "211";
	/** 自动审核不通过 */
	public static final String STATE_AUTO_REFUSED = "212";
	/** 自动审核未决待人工复审 */
	//public static final String STATE_NEED_REVIEW = "220";
	public static final String STATE_NEED_REVIEW = "2";
	/** 人工复审通过 */
	//public static final String STATE_PASS = "221";
	public static final String STATE_PASS = "3";
	/** 人工复审不通过 */
	//public static final String STATE_REFUSED = "222";
	public static final String STATE_REFUSED = "31";


	/** 打款中/放款中 */
	//public static final String STATE_REPAYING = "30";
	public static final String STATE_REPAYING = "5";
	/**待放款审核*/
	public static final String WAIT_AUDIT_LOAN="301"; //39
	/**放款审核通过*/
	public static final String AUDIT_LOAN_PASS="302"; //37
	/**放款审核不通过*/
	public static final String AUDIT_LOAN_FAIL="303"; //38
	/** 放款失败 */
	public static final String STATE_REPAY_FAIL = "31";
	/** 贷款中 /已放款 (源订单) */
	public static final String STATE_REPAY = "5";
	
	/** 转续借 (源订单) */
	public static final String STATE_TO_RENEW = "33";
	/** 续借中 (续借订单) */
	public static final String STATE_RENEW = "34";
	/** 续借处理中 */
	public static final String STATE_RENEW_PROCESSING = "35";

	/** 正常还清 */
	public static final String STATE_FINISH = "6";
	/** 逾期已还清 */
	//public static final String STATE_DELAY_FINISH = "41";
	public static final String STATE_DELAY_FINISH = "22";
	/** 逾期已还清-金额减免 */
	public static final String STATE_DELAY_REMISSION_FINISH = "42";
	
	/** 还款处理中*/
	public static final String STATE_REPAY_PROCESSING = "43";
	/** 逾期 */
	//public static final String STATE_DELAY = "50";
	public static final String STATE_DELAY = "21";
	/** 坏账 */
	public static final String STATE_BAD = "90";
	
	/** 续借标位 - 原借款 */
	public static final Integer RENEW_MARK_ORIGINAL = 0;

	/**
	 * 状态中文描述
	 */
	private String stateStr;

	/**
	 * 进度说明
	 */
	private String remark;
	
	/**
	 * 获取状态中文描述
	 * @return stateStr
	 */
	public String getStateStr() {
		this.stateStr = BorrowModel.apiConvertBorrowState(this.getState());
		return stateStr;
	}

	/**
	 * 设置状态中文描述
	 * @param stateStr
	 */
	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}

	/**
	 * 获取进度说明
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置进度说明
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	

	/** 
	 * 响应给管理后台的借款/借款进度状态中文描述转换
	 * @return stateStr  
	 */
	public static String manageConvertBorrowState(String state) {
		String stateStr = " - ";
		if (STATE_PRE.equals(state)) {
			stateStr = ("申请成功待审核");
		} else if (STATE_AUTO_PASS.equals(state)) {
			stateStr = ("自动审核通过");
		} else if (STATE_AUTO_REFUSED.equals(state)) {
			stateStr = ("自动审核不通过 ");
		} else if (STATE_NEED_REVIEW.equals(state)) {
			stateStr = ("待人工复审");
		} else if (STATE_PASS.equals(state)) {
			stateStr = ("人工复审通过");
		} else if (STATE_REFUSED.equals(state)) {
			stateStr = ("人工复审不通过");
		} else if (STATE_REPAYING.equals(state)) {
			stateStr = ("打款中");
		} else if (STATE_REPAY_FAIL.equals(state)) {
			stateStr = ("放款失败");
		} else if (STATE_REPAY.equals(state)) {
			stateStr = ("贷款中");
		} else if (STATE_TO_RENEW.equals(state)) {
			stateStr = ("转续借");
		} else if (STATE_RENEW.equals(state)) {
			stateStr = ("续借中");
		} else if (STATE_FINISH.equals(state)) {
			stateStr = ("正常还清");
		} else if (STATE_DELAY_FINISH.equals(state)) {
			stateStr = ("逾期已还清");
		} else if (STATE_DELAY_REMISSION_FINISH.equals(state)) {
			stateStr = ("逾期已还清-金额减免");
		} else if (STATE_DELAY.equals(state)) {
			stateStr = ("已逾期");
		} else if (STATE_BAD.equals(state)) {
			stateStr = ("已坏账");
		} else if (STATE_RENEW_PROCESSING.equals(state)) {
			stateStr = ("续借处理中");
		} else if (STATE_REPAY_PROCESSING.equals(state)) {
			stateStr = ("还款处理中");
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
	 * 借款状态中文描述转换
	 * 
	 * @param state
	 * @return
	 */
	public static String convertBorrowRemark(String state) {
		String remarkStr = " - ";
		if (STATE_PRE.equals(state)) {
			remarkStr = ("系统审核中,请耐心等待");
		} else if (STATE_AUTO_PASS.equals(state)) {
			remarkStr = ("恭喜通过风控审核");
		} else if (STATE_AUTO_REFUSED.equals(state)) {
			remarkStr = ("很遗憾，您未通过审核 ");
		} else if (STATE_NEED_REVIEW.equals(state)) {
			remarkStr = ("待复审，请耐心等待");
		} else if (STATE_PASS.equals(state)) {
			remarkStr = ("恭喜通过风控复审");
		} else if (STATE_REFUSED.equals(state)) {
			remarkStr = ("很遗憾，您未通过复审");
		} else if (WAIT_AUDIT_LOAN.equals(state)) {
			remarkStr = ("打款中，请耐心等待");
		} else if (STATE_REPAY_FAIL.equals(state)) {
			remarkStr = ("放款失败");
		} else if (STATE_REPAY.equals(state)) {
			remarkStr = ("已打款，请查看您的提现银行卡");
		} else if (STATE_RENEW_PROCESSING.equals(state)) {
			remarkStr = ("续借处理中，请等待处理结果");
		} else if (STATE_TO_RENEW.equals(state)) {
			remarkStr = ("借款已转续借");
		} else if (STATE_RENEW.equals(state)) {
			remarkStr = ("借款续借中");
		} else if (STATE_FINISH.equals(state)) {
			remarkStr = ("正常还清");
		} else if (STATE_DELAY_FINISH.equals(state)) {
			remarkStr = ("借款逾期已还清");
		} else if (STATE_DELAY_REMISSION_FINISH.equals(state)) {
			remarkStr = ("借款人无法支付全部借款金额，减免正常还清");
		} else if (STATE_REPAY_PROCESSING.equals(state)) {
			remarkStr = ("还款处理中，请等待处理结果");
		} else if (STATE_DELAY.equals(state)) {
			remarkStr = ("您的借款已逾期");
		} else if (STATE_BAD.equals(state)) {
			remarkStr = ("经长时间催收，没有结果");
		}else if (BorrowModel.WAIT_AUDIT_LOAN.equals(state)) {
			remarkStr = "放款审核中,请等待";
		}else if (BorrowModel.AUDIT_LOAN_PASS.equals(state)) {
			remarkStr = "恭喜您,放款审核通过";
		} else if (BorrowModel.AUDIT_LOAN_FAIL.equals(state)) {
			remarkStr = "很遗憾,审核放款不通过";
		}
		return remarkStr;
	}
	
	/**
	 * 响应给app的借款状态中文描述转换
	 * @param state
	 * @return
	 */
	public static String apiConvertBorrowState(String state) {
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
	 * 响应给app的借款备注中文描述转换
	 * @param state
	 * @return
	 */
	public static String apiConvertBorrowRemark(Borrow borrow, String state, double repayAmount,
			double penaltyAmount, Date repayTime) {
		int day = 0;
		if (null != repayTime) {
			day = DateUtil.daysBetween(new Date(), repayTime);
		}
		
		String remarkStr = " - ";
		if (STATE_PRE.equals(state)) {
			remarkStr = "平台收回价" + borrow.getAmount() + "元，期限" + borrow.getTimeLimit() + "天，" + "综合服务费" + borrow.getFee()
					+ "元";
		} else if (STATE_AUTO_PASS.equals(state)) {
			remarkStr = "已进入风控审核状态，请耐心等待";
		} else if (STATE_AUTO_REFUSED.equals(state) || STATE_REFUSED.equals(state)) {
			String againBorrow = Global.getValue("again_borrow");
			if (Integer.valueOf(againBorrow) > 0) {
				remarkStr = "很遗憾,您的借款未通过审核，请于" + againBorrow + "天之后再尝试申请，或拨打客服电话联系我们";
			} else {
				remarkStr = "很遗憾,您的借款未通过审核，请再尝试申请，拨打客服电话联系我们";
			}
		} else if (STATE_NEED_REVIEW.equals(state) || STATE_PASS.equals(state)) {
			remarkStr = "已进入风控审核状态，请耐心等待";
		} else if (STATE_REPAY_FAIL.equals(state)) {
			remarkStr = "终审通过，打款中，请耐心等待";
		} else if (WAIT_AUDIT_LOAN.equals(state)) {
			remarkStr = ("打款中，请耐心等待");
		} else if (STATE_REPAY.equals(state)) {
			remarkStr = "已打款，请查看您的收款银行卡";
		} else if ("repay".equals(state)) {// 待还款
			if (day > 0) {
				remarkStr = "您需要" + day + "天之后还款" + repayAmount + "元";
			} else {
				remarkStr = "您需要于今日还款" + repayAmount + "元";
			}
		} else if (STATE_RENEW_PROCESSING.equals(state)) {
			remarkStr = ("您的续借申请正在处理，请耐心等待");
		} else if (STATE_TO_RENEW.equals(state)) {
			remarkStr = ("借款已转续借");
		} else if (STATE_RENEW.equals(state)) {
			if (day > 0) {
				remarkStr = "您需要" + day + "天之后还款" + repayAmount + "元";
			} else {
				remarkStr = "您需要于今日还款" + repayAmount + "元";
			}
		} else if (STATE_FINISH.equals(state) || STATE_DELAY_FINISH.equals(state)
				|| STATE_DELAY_REMISSION_FINISH.equals(state)) {
			remarkStr = ("赎回成功，您可以根据赎回中的订单详情iCloud密码更换账号");
		} else if (STATE_REPAY_PROCESSING.equals(state)) {
			remarkStr = ("还款处理中，请耐心等待");
		} else if (STATE_DELAY.equals(state) || STATE_BAD.equals(state)) {
			remarkStr = ("当前订单已产生滞纳金" + penaltyAmount + "元，请尽快还款");
		}
		return remarkStr;
	}
}