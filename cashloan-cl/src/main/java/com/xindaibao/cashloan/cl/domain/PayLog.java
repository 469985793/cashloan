package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 支付记录实体
 */
 public class PayLog implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	private Long id;
	
	/**
	 * 请求订单编号
	 */
	private String orderNo;

	/**
	 * 用户标识
	 */
	private Long userId;

	/**
	 * 借款标识
	 */
	private Long borrowId;

	/**
	 * 支付金额
	 */
	private Double amount;

	/**
	 * 用户银行卡卡号
	 */
	private String cardNo;

	/**
	 * 用户银行卡开户行
	 */
	private String bank;
	
	/**
	 * 确认码, 实时付款 确认交易时需要
	 */
	private String confirmCode;

	/**
	 * 资金来源 10:自有资金 20:其他资金
	 */
	private String source;

	/**
	 * 付款方式 10:代付 20:代扣 30:线下代付  40:线下代扣
	 */
	private String type;

	/**
	 * 业务场景  10、放款  11、分润  12、退还  20、还款  21、补扣
	 */
	private String scenes;
	
	/**
	 * 付款状态 10:待支付 、15:待审核 、20:审核通过、 30:审核不通过 、40:支付成功、50:支付失败
	 */
	private String state;

	/**
	 *  支付订交易请求时间
	 */
	private Date payReqTime; 
	
	/**
	 * 备注信息及审核信息
	 */
	private String remark;

	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	/**
	 * 添加时间
	 */
	private Date createTime;

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
	 * 获取请求订单编号
	 * 
	 * @return orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * 设置请求订单编号
	 * 
	 * @param orderNo
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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
	 * 获取借款标识
	 *
	 * @return 借款标识
	 */
	public Long getBorrowId() {
		return borrowId;
	}

	/**
	 * 设置借款标识
	 * 
	 * @param borrowId
	 *            要设置的借款标识
	 */
	public void setBorrowId(Long borrowId) {
		this.borrowId = borrowId;
	}

	/**
	 * 获取支付金额
	 *
	 * @return 支付金额
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * 设置支付金额
	 * 
	 * @param amount
	 *            要设置的支付金额
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * 获取用户银行卡号
	 *
	 * @return 用户银行卡号
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * 设置用户银行卡号
	 * 
	 * @param cardNo
	 *            要设置的用户银行卡号
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * 获取用户银行卡开户行
	 * 
	 * @return bank
	 */
	public String getBank() {
		return bank;
	}

	/**
	 * 设置用户银行卡开户行
	 * 
	 * @param bank
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	/**
	 * 获取确认码, 实时付款 确认交易时需要
	 * 
	 * @return confirmCode
	 */
	public String getConfirmCode() {
		return confirmCode;
	}

	/**
	 * 设置确认码, 实时付款 确认交易时需要
	 * 
	 * @param confirmCode
	 */
	public void setConfirmCode(String confirmCode) {
		this.confirmCode = confirmCode;
	}

	/**
	 * 获取资金来源 10:自有资金 20:其他资金
	 *
	 * @return 资金来源 10:自有资金 20:其他资金
	 */
	public String getSource() {
		return source;
	}

	/**
	 * 设置资金来源 10:自有资金 20:其他资金
	 * 
	 * @param source
	 *            要设置的资金来源 10:自有资金 20:其他资金
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * 获取付款方式 10:代付 20:代扣 30:线下
	 *
	 * @return 付款方式 10:代付 20:代扣 30:线下
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置付款方式 10:代付 20:代扣 30:线下
	 * 
	 * @param type
	 *            要设置的付款方式 10:代付 20:代扣 30:线下
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取业务场景  10、放款  11、分润 12、退还 20、还款 21、补扣
	 * @return scenes
	 */
	public String getScenes() {
		return scenes;
	}

	/**
	 * 设置业务场景  10、放款  11、分润 12、退还 20、还款 21、补扣
	 * @param scenes
	 */
	public void setScenes(String scenes) {
		this.scenes = scenes;
	}

	/**
	 * 获取付款状态 10:待支付 、15:待审核 、20:审核通过、 30:审核不通过 、40:支付成功、50:支付失败
	 *
	 * @return 付款状态 10:待支付 、15:待审核 、20:审核通过、 30:审核不通过 、40:支付成功、50:支付失败
	 */
	public String getState() {
		return state;
	}

	/**
	 * 设置付款状态 10:待支付 、15:待审核 、20:审核通过、 30:审核不通过 、40:支付成功、50:支付失败
	 * 
	 * @param state
	 *            要设置的付款状态 10:待支付 、15:待审核 、20:审核通过、 30:审核不通过 、40:支付成功、50:支付失败
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * 获取支付订交易请求时间
	 * 
	 * @return payReqTime
	 */
	public Date getPayReqTime() {
		return payReqTime;
	}

	/**
	 * 设置支付订交易请求时间
	 * 
	 * @param payReqTime
	 */
	public void setPayReqTime(Date payReqTime) {
		this.payReqTime = payReqTime;
	}

	/**
	 * 获取备注信息及审核信息
	 * 
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注信息及审核信息
	 * 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取更新时间
	 * 
	 * @return updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置更新时间
	 * 
	 * @param updateTime
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取添加时间
	 * 
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置添加时间
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}