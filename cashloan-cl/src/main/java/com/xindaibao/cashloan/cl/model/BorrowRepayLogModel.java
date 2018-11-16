package com.xindaibao.cashloan.cl.model;

import com.xindaibao.cashloan.cl.domain.BorrowRepayLog;
import tool.util.DateUtil;

public class BorrowRepayLogModel extends BorrowRepayLog {

	private static final long serialVersionUID = 1L;
	/** 还款方式 - 代扣 */
	public static final String REPAY_WAY_CHARGE = "10";

	/** 还款方式 - 银行卡转账 */
	public static final String REPAY_WAY_TRANSFER = "20";

	/** 还款方式 - 支付宝转账 */
	public static final String REPAY_WAY_ALIPAY_TRANSFER = "30";
	
	/** 还款方式 - 认证支付 */
	public static final String  REPAY_WAY_CERTIFIED = "40";
	
	/** 还款方式 - 支付宝APP支付 */
	public static final String  REPAY_WAY_ALIPAY = "41";
	
	/** 还款方式 - 微信APP支付 */
	public static final String  REPAY_WAY_WXPAY = "42";
	
	public static String convertRepayWay(String repayWay) {
		String repayWayStr = repayWay;
		if (REPAY_WAY_CHARGE.equals(repayWay)) {
			repayWayStr = "代扣";
		} else if (REPAY_WAY_TRANSFER.equals(repayWay)) {
			repayWayStr = "银行卡转账";
		} else if (REPAY_WAY_ALIPAY_TRANSFER.equals(repayWay)) {
			repayWayStr = "支付宝转账";
		} else if (REPAY_WAY_CERTIFIED.equals(repayWay)) {
			repayWayStr = "认证支付";
		} else if (REPAY_WAY_ALIPAY.equals(repayWay)) {
			repayWayStr = "支付宝APP支付";
		} else if (REPAY_WAY_WXPAY.equals(repayWay)) {
			repayWayStr = "微信APP支付 ";
		}  
		return repayWayStr;
	}


	
	/**
	 * 还款时间
	 */
	private String repayTimeStr;
	
	/**
	 * 获取还款时间
	 * 
	 * @return repayTimeStr
	 */
	public String getRepayTimeStr() {
		this.repayTimeStr = DateUtil.dateStr(this.getRepayTime(),"yyyy-MM-dd HH:mm");
		return repayTimeStr;
	}

	/**
	 * 设置还款时间
	 * 
	 * @param repayTimeStr
	 */
	public void setRepayTimeStr(String repayTimeStr) {
		this.repayTimeStr = repayTimeStr;
	}
}
