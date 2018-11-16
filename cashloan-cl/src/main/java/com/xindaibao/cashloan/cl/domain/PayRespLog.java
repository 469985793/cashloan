package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

import tool.util.DateUtil;

/**
 * 支付响应记录实体
 */
 public class PayRespLog implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	private Long id;

	/**
	 * 商户订单编号
	 */
	private String orderNo;

	/**
	 * 通知类型 1、TPP同步返回 2、Tpp异步响应
	 */
	private Integer type;

	/**
	 * Tpp通知信息
	 */
	private String params;

	/**
	 * 推送时间
	 */
	private Date createTime;

	
	
	public PayRespLog(String orderNo, Integer type, String params) {
		super();
		this.orderNo = orderNo;
		this.type = type;
		this.params = params;
		this.setCreateTime(DateUtil.getNow());
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
	 * @param 要设置的主键Id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取商户订单编号
	 *
	 * @return 商户订单编号
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * 设置商户订单编号
	 * 
	 * @param orderNo
	 *            要设置的商户订单编号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 获取通知类型 1、TPP同步返回 2、Tpp异步响应
	 *
	 * @return 通知类型 1、TPP同步返回 2、Tpp异步响应
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 设置通知类型 1、TPP同步返回 2、Tpp异步响应
	 * 
	 * @param type
	 *            要设置的通知类型 1、TPP同步返回 2、Tpp异步响应
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 获取Tpp通知信息
	 *
	 * @return Tpp通知信息
	 */
	public String getParams() {
		return params;
	}

	/**
	 * 设置Tpp通知信息
	 * 
	 * @param params
	 *            要设置的Tpp通知信息
	 */
	public void setParams(String params) {
		this.params = params;
	}

	/**
	 * 获取推送时间
	 * 
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置推送时间
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}