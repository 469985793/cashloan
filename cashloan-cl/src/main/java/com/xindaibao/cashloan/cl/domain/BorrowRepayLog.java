package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 还款计录实体
 */
 public class BorrowRepayLog implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	private Long id;

	/**
	 * 还款计划id
	 */
	private Long repayId;

	/**
	 * 借款订单id
	 */
	private Long borrowId;

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 还款金额
	 */
	private Double amount;

	/**
	 * 实际还款时间
	 */
	private Date repayTime;

	/**
	 * 逾期罚金 
	 */
	private Double penaltyAmout;

	/**
	 * 逾期天数
	 */
	private String penaltyDay;

	/**
	 * 还款方式 10代扣，20银行卡转账，30支付宝转账
	 */
	private String repayWay;

	/**
	 * 还款账号
	 */
	private String repayAccount;
	
	/**
	 * 退还补扣金额
	 */
	private String refundDeduction;

	/**
	 * 退还或补扣支付时间
	 */
	private Date payTime;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 流水号
	 */
	private String serialNumber;

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
	 * 获取还款计划id
	 *
	 * @return 还款计划id
	 */
	public Long getRepayId() {
		return repayId;
	}

	/**
	 * 设置还款计划id
	 * 
	 * @param repayId
	 *            要设置的还款计划id
	 */
	public void setRepayId(Long repayId) {
		this.repayId = repayId;
	}

	/**
	 * 获取借款订单id
	 *
	 * @return 借款订单id
	 */
	public Long getBorrowId() {
		return borrowId;
	}

	/**
	 * 设置借款订单id
	 * 
	 * @param borrowId
	 *            要设置的借款订单id
	 */
	public void setBorrowId(Long borrowId) {
		this.borrowId = borrowId;
	}

	/**
	 * 获取用户id
	 *
	 * @return 用户id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置用户id
	 * 
	 * @param userId
	 *            要设置的用户id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取还款金额
	 *
	 * @return 还款金额
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * 设置还款金额
	 * 
	 * @param amount
	 *            要设置的还款金额
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * 获取实际还款时间
	 *
	 * @return 实际还款时间
	 */
	public Date getRepayTime() {
		return repayTime;
	}

	/**
	 * 设置实际还款时间
	 * 
	 * @param repayTime
	 *            要设置的实际还款时间
	 */
	public void setRepayTime(Date repayTime) {
		this.repayTime = repayTime;
	}

	/**
	 * 获取逾期罚金
	 *
	 * @return 逾期罚金
	 */
	public Double getPenaltyAmout() {
		return penaltyAmout;
	}

	/**
	 * 设置逾期罚金
	 * 
	 * @param penaltyAmout
	 *            要设置的逾期罚金
	 */
	public void setPenaltyAmout(Double penaltyAmout) {
		this.penaltyAmout = penaltyAmout;
	}

	/**
	 * 获取逾期天数
	 * @return penaltyDay
	 */
	public String getPenaltyDay() {
		return penaltyDay;
	}

	/**
	 * 设置逾期天数
	 * @param penaltyDay
	 */
	public void setPenaltyDay(String penaltyDay) {
		this.penaltyDay = penaltyDay;
	}

	/**
	 * 获取还款方式10代扣，20银行卡转账，30支付宝转账
	 * @return repayWay
	 */
	public String getRepayWay() {
		return repayWay;
	}

	/**
	 * 设置还款方式10代扣，20银行卡转账，30支付宝转账
	 * @param repayWay
	 */
	public void setRepayWay(String repayWay) {
		this.repayWay = repayWay;
	}

	/**
	 * 获取还款账号
	 * @return repayAccount
	 */
	public String getRepayAccount() {
		return repayAccount;
	}

	/**
	 * 设置还款账号
	 * @param repayAccount
	 */
	public void setRepayAccount(String repayAccount) {
		this.repayAccount = repayAccount;
	}

	/**
	 * 获取退还补扣金额
	 * @return refundDeduction
	 */
	public String getRefundDeduction() {
		return refundDeduction;
	}

	/**
	 * 设置退还补扣金额
	 * @param refundDeduction
	 */
	public void setRefundDeduction(String refundDeduction) {
		this.refundDeduction = refundDeduction;
	}

	/**
	 * 获取退还或补扣支付时间
	 * @return payTime
	 */
	public Date getPayTime() {
		return payTime;
	}

	/**
	 * 设置退还或补扣支付时间
	 * @param payTime
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
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

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	
}