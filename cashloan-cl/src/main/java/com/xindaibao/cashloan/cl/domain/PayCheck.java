package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

import tool.util.DateUtil;

/**
 * 资金对账记录实体
 */
 public class PayCheck implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	private Long id;

	/**
	 * 订单号
	 */
	private String orderNo;
	
	/**
	 * 订单金额(我方订单金额)
	 */
	private double orderAmount;
	
	/**
	 * 实际交易金额(支付方订单交易金额)
	 */
	private double realPayAmount;
	
	/**
	 * 错误类型  10:金额不匹配   20:我方单边账 30:支付方单边
	 */
	private String type;

	/**
	 * 订单交易状态 0 成功 5 退款
	 */
	private String state;

	/**
	 * 备注说明
	 */
	private String remark;

	/**
	 * 处理方式   10不处理 20补录 30补扣 40 退还 
	 */
	private String processWay;
	
	/**
	 * 处理结果  10待处理 20 已处理
	 */
	private String processResult;

	/**
	 * 处理时间
	 */
	private Date processTime;

	/**
	 * 创建时间
	 */
	private Date createTime;

	public PayCheck() {
		super();
	}

	public PayCheck(String orderNo, double orderAmount, double realPayAmount,
			String type, String processResult) {
		super();
		this.orderNo = orderNo;
		this.orderAmount = orderAmount;
		this.realPayAmount = realPayAmount;
		this.type = type;
		this.processResult = processResult;
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
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取订单号
	 * 
	 * @return orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * 设置订单号
	 * 
	 * @param orderNo
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 获取订单金额(我方订单金额)
	 * 
	 * @return orderAmount
	 */
	public double getOrderAmount() {
		return orderAmount;
	}

	/**
	 * 设置订单金额(我方订单金额)
	 * 
	 * @param orderAmount
	 */
	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * 获取实际交易金额(支付方订单交易金额)
	 * 
	 * @return realPayAmount
	 */
	public double getRealPayAmount() {
		return realPayAmount;
	}

	/**
	 * 设置实际交易金额(支付方订单交易金额)
	 * 
	 * @param realPayAmount
	 */
	public void setRealPayAmount(double realPayAmount) {
		this.realPayAmount = realPayAmount;
	}

	/**
	 * 获取错误类型10:金额不匹配20:我方单边账30:支付方单边
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置错误类型10:金额不匹配20:我方单边账30:支付方单边
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取订单交易状态10成功20退款
	 * 
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 * 设置订单交易状态10成功20退款
	 * 
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 获取备注说明
	 * 
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注说明
	 * 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取处理方式10不处理20补录30补扣40退还
	 * 
	 * @return processWay
	 */
	public String getProcessWay() {
		return processWay;
	}

	/**
	 * 设置处理方式10不处理20补录30补扣40退还
	 * 
	 * @param processWay
	 */
	public void setProcessWay(String processWay) {
		this.processWay = processWay;
	}

	/**
	 * 获取处理结果10待处理20已处理
	 * 
	 * @return processResult
	 */
	public String getProcessResult() {
		return processResult;
	}

	/**
	 * 设置处理结果10待处理20已处理
	 * 
	 * @param processResult
	 */
	public void setProcessResult(String processResult) {
		this.processResult = processResult;
	}

	/**
	 * 获取处理时间
	 * 
	 * @return processTime
	 */
	public Date getProcessTime() {
		return processTime;
	}

	/**
	 * 设置处理时间
	 * 
	 * @param processTime
	 */
	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	/**
	 * 获取创建时间
	 * 
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}