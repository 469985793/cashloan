package com.xindaibao.cashloan.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 借款信息表实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-14 10:13:31


 * 

 */
 public class Borrow implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	private Long id;

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 订单号
	 */
	private String orderNo;

	/**
	 * 借款金额
	 */
	private Double amount;

	/**
	 * 实际到账金额
	 */
	private Double realAmount;

	
	
	/**
	 * 综合费用(借款利息+服务费+信息认证费)
	 */
	private Double fee;

	/**
	 * 借款期限(天)
	 */
	private String timeLimit;

	/**
	 * 订单状态 10-申请成功待审核 211-自动审核成功  212-自动审核不通过  220-待人工复审 221-人工复审通过 222-人工复审不通过 
	 * 230-待（客服1）初审 231-（客服1）初审通过 232-（客服1）初审不通过 240-待（客服2）复审 241-（客服2）复审通过 242-（客服2）
	 * 复审不通过 31-放款失败 32-贷款中/已放款 33-转续借（源订单） 34-续借中（续借订单） 35-续借处理中 40-正常还清 41-逾期已还清 
	 * 42-逾期已还清并金额减免 43-还款处理中 50-逾期 90-坏账
	 */
	private String state;

	/**
	 * 收款银行卡关联id
	 */
	private Long cardId;

	/**
	 * 服务费
	 */
	private Double serviceFee;

	/**
	 * 信息认证费
	 */
	private Double infoAuthFee;

	/**
	 * 借款利息
	 */
	private Double interest;

	/**
	 * 原订单Id
	 */
	private Long originalId;

	/**
	 * 原订单订单号
	 */
	private String originalOrderNo;

	/**
	 * 续借状态
	 */
	private String renewState;

	/**
	 * 续借标记（0 表示原订单)
	 */
	private Integer renewMark;

	/**
	 * 续借支付金额
	 */
	private Double renewAmount;
	
	/**
	 * 备注、审核说明
	 */
	private String remark;

	/**
	 * 客户端 默认10-移动app
	 */
	private String client;

	/**
	 * 发起借款地址
	 */
	private String address;

	/**
	 * 借款经纬度坐标
	 */
	private String coordinate;

	/**
	 * ip地址
	 */
	private String ip;

	/**
	 * 订单生成时间
	 */
	private Date createTime;
	
	/**
	 * e签宝签署记录id
	 */
	private String signServiceId;

	public Borrow() {
		super();
	}

	public Borrow(Long userId, Double amount, String timeLimit, Long cardId, String client, String address,
			String coordinate, String ip) {
		super();
		this.userId = userId;
		this.amount = amount;
		this.timeLimit = timeLimit;
		this.cardId = cardId;
		this.client = client;
		this.address = address;
		this.coordinate = coordinate;
		this.ip = ip;
	}

	/**
	 * 获取id
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置id
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取userId
	 * 
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置userId
	 * 
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取orderNo
	 * 
	 * @return orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * 设置orderNo
	 * 
	 * @param orderNo
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 获取amount
	 * 
	 * @return amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * 设置amount
	 * 
	 * @param amount
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * 获取realAmount
	 * 
	 * @return realAmount
	 */
	public Double getRealAmount() {
		return realAmount;
	}

	/**
	 * 设置realAmount
	 * 
	 * @param realAmount
	 */
	public void setRealAmount(Double realAmount) {
		this.realAmount = realAmount;
	}

	/**
	 * 获取fee
	 * 
	 * @return fee
	 */
	public Double getFee() {
		return fee;
	}

	/**
	 * 设置fee
	 * 
	 * @param fee
	 */
	public void setFee(Double fee) {
		this.fee = fee;
	}

	/**
	 * 获取timeLimit
	 * 
	 * @return timeLimit
	 */
	public String getTimeLimit() {
		return timeLimit;
	}

	/**
	 * 设置timeLimit
	 * 
	 * @param timeLimit
	 */
	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	/**
	 * 获取state
	 * 
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 * 设置state
	 * 
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 获取cardId
	 * 
	 * @return cardId
	 */
	public Long getCardId() {
		return cardId;
	}

	/**
	 * 设置cardId
	 * 
	 * @param cardId
	 */
	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	/**
	 * 获取serviceFee
	 * 
	 * @return serviceFee
	 */
	public Double getServiceFee() {
		return serviceFee;
	}

	/**
	 * 设置serviceFee
	 * 
	 * @param serviceFee
	 */
	public void setServiceFee(Double serviceFee) {
		this.serviceFee = serviceFee;
	}

	/**
	 * 获取infoAuthFee
	 * 
	 * @return infoAuthFee
	 */
	public Double getInfoAuthFee() {
		return infoAuthFee;
	}

	/**
	 * 设置infoAuthFee
	 * 
	 * @param infoAuthFee
	 */
	public void setInfoAuthFee(Double infoAuthFee) {
		this.infoAuthFee = infoAuthFee;
	}

	/**
	 * 获取interest
	 * 
	 * @return interest
	 */
	public Double getInterest() {
		return interest;
	}

	/**
	 * 设置interest
	 * 
	 * @param interest
	 */
	public void setInterest(Double interest) {
		this.interest = interest;
	}

	/**
	 * 获取originalId
	 * 
	 * @return originalId
	 */
	public Long getOriginalId() {
		return originalId;
	}

	/**
	 * 设置originalId
	 * 
	 * @param originalId
	 */
	public void setOriginalId(Long originalId) {
		this.originalId = originalId;
	}

	/**
	 * 获取originalOrderNo
	 * 
	 * @return originalOrderNo
	 */
	public String getOriginalOrderNo() {
		return originalOrderNo;
	}

	/**
	 * 设置originalOrderNo
	 * 
	 * @param originalOrderNo
	 */
	public void setOriginalOrderNo(String originalOrderNo) {
		this.originalOrderNo = originalOrderNo;
	}

	/**
	 * 获取renewState
	 * 
	 * @return renewState
	 */
	public String getRenewState() {
		return renewState;
	}

	/**
	 * 设置renewState
	 * 
	 * @param renewState
	 */
	public void setRenewState(String renewState) {
		this.renewState = renewState;
	}

	/**
	 * 获取renewMark
	 * 
	 * @return renewMark
	 */
	public Integer getRenewMark() {
		return renewMark;
	}

	/**
	 * 设置renewMark
	 * 
	 * @param renewMark
	 */
	public void setRenewMark(Integer renewMark) {
		this.renewMark = renewMark;
	}
	
	/**
	 * 获取renewAmount
	 * 
	 * @return renewAmount
	 */
	public Double getRenewAmount() {
		return renewAmount;
	}

	/**
	 * 设置renewAmount
	 * 
	 * @param renewAmount
	 */
	public void setRenewAmount(Double renewAmount) {
		this.renewAmount = renewAmount;
	}

	/**
	 * 获取remark
	 * 
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置remark
	 * 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取client
	 * 
	 * @return client
	 */
	public String getClient() {
		return client;
	}

	/**
	 * 设置client
	 * 
	 * @param client
	 */
	public void setClient(String client) {
		this.client = client;
	}

	/**
	 * 获取address
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置address
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取coordinate
	 * 
	 * @return coordinate
	 */
	public String getCoordinate() {
		return coordinate;
	}

	/**
	 * 设置coordinate
	 * 
	 * @param coordinate
	 */
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * 获取ip
	 * 
	 * @return ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 设置ip
	 * 
	 * @param ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 获取createTime
	 * 
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置createTime
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getSignServiceId() {
		return signServiceId;
	}

	public void setSignServiceId(String signServiceId) {
		this.signServiceId = signServiceId;
	}

 
}
