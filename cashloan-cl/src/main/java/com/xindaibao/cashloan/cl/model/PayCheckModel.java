package com.xindaibao.cashloan.cl.model;

import com.xindaibao.cashloan.cl.domain.PayCheck;

/**
 * 资金对账记录Model
 * 
 * @author
 * @version 1.0.0
 * @date 2017年4月17日 下午5:55:04



 */
public class PayCheckModel extends PayCheck {


	private static final long serialVersionUID = 1L;
	
	/** 处理状态 - 成功 */
	public static final String STATE_PAY_SUCCESS = "0";
	/** 处理状态 - 退款 */
	public static final String STATE_REFUND = "5";

	/** 错误类型 - 金额不匹配 */
	public static final String TYPE_AMOUNT_MISMATCH = "10";
	/** 错误类型 - 我方单边账 */
	public static final String TYPE_UNILATERAL_OUR = "20";
	/** 错误类型 - 支付方单边账 */
	public static final String TYPE_UNILATERAL_PAYMENT = "30";
	
	
	/** 处理方式 - 不处理 */
	public static final String PROCESS_WAY_NOT_DEAL = "10";
	/** 处理方式 - 补录 */
	public static final String PROCESS_WAY_MAKEUP = "20";
	/** 处理方式 - 退还 */
	public static final String PROCESS_WAY_REFUND = "30";
	/** 处理方式 - 补扣 */
	public static final String PROCESS_WAY_DEDUCTION = "40";
	
	/** 处理结果 - 未处理 */
	public static final String PROCESS_RESULT_PENDING_TREATMENT ="10";
	/** 处理结果 - 已处理 */
	public static final String PROCESS_RESULT_ALREADY_DEAL ="20";
	
	public PayCheckModel(String orderNo, double orderAmount,
			double realPayAmount, String type, String processResult) {
		super(orderNo, orderAmount, realPayAmount, type, processResult);
	}
	

}
