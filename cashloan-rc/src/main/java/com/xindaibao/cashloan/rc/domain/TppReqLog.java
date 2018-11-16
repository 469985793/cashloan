package com.xindaibao.cashloan.rc.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 第三方征信请求记录实体
 * 
 * @author
 * @version 1.0.0


 */
public class TppReqLog implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	private Long id;

	/**
	 * 第三方标识
	 */
	private String tppNid;

	/**
	 * 接口标识
	 */
	private String businessNid;

	/**
	 * 订单号
	 */
	private String orderNo;
	
	/**
	 * tpp订单号
	 */
	private String tppOrderNo;

	/**
	 * 请求参数集合
	 */
	private String reqParams;

	/**
	 * 同步响应参数集合
	 */
	private String returnParams;

	/**
	 * 同步响应时间
	 */
	private Date returnTime;

	/**
	 * 异步通知参数集合
	 */
	private String notifyParams;

	/**
	 * 异步响应时间
	 */
	private Date notifyTime;

	/**
	 * 异常信息
	 */
	private String exception;

	/**
	 * 添加时间
	 */
	private Date addTime;

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
	 * 获取第三方标识
	 *
	 * @return 第三方标识
	 */
	public String getTppNid() {
		return tppNid;
	}

	/**
	 * 设置第三方标识
	 * 
	 * @param tppNid
	 *            要设置的第三方标识
	 */
	public void setTppNid(String tppNid) {
		this.tppNid = tppNid;
	}

	/**
	 * 获取接口标识
	 *
	 * @return 接口标识
	 */
	public String getBusinessNid() {
		return businessNid;
	}

	/**
	 * 设置接口标识
	 * 
	 * @param businessNid
	 *            要设置的接口标识
	 */
	public void setBusinessNid(String businessNid) {
		this.businessNid = businessNid;
	}

	/**
	 * 获取订单号
	 *
	 * @return 订单号
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * 设置订单号
	 * 
	 * @param orderNo
	 *            要设置的订单号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	/**
	 * 获取 tpp订单号
	 * @return 
	 */
	public String getTppOrderNo() {
		return tppOrderNo;
	}

	/**
	 * 设置 tpp订单号
	 * @param 
	 */
	public void setTppOrderNo(String tppOrderNo) {
		this.tppOrderNo = tppOrderNo;
	}

	/**
	 * 获取请求参数集合
	 *
	 * @return 请求参数集合
	 */
	public String getReqParams() {
		return reqParams;
	}

	/**
	 * 设置请求参数集合
	 * 
	 * @param reqParams
	 *            要设置的请求参数集合
	 */
	public void setReqParams(String reqParams) {
		this.reqParams = reqParams;
	}

	/**
	 * 获取同步响应参数集合
	 *
	 * @return 同步响应参数集合
	 */
	public String getReturnParams() {
		return returnParams;
	}

	/**
	 * 设置同步响应参数集合
	 * 
	 * @param returnParams
	 *            要设置的同步响应参数集合
	 */
	public void setReturnParams(String returnParams) {
		this.returnParams = returnParams;
	}

	/**
	 * 获取同步响应时间
	 *
	 * @return 同步响应时间
	 */
	public Date getReturnTime() {
		return returnTime;
	}

	/**
	 * 设置同步响应时间
	 * 
	 * @param returnTime
	 *            要设置的同步响应时间
	 */
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	/**
	 * 获取异步通知参数集合
	 *
	 * @return 异步通知参数集合
	 */
	public String getNotifyParams() {
		return notifyParams;
	}

	/**
	 * 设置异步通知参数集合
	 * 
	 * @param notifyParams
	 *            要设置的异步通知参数集合
	 */
	public void setNotifyParams(String notifyParams) {
		this.notifyParams = notifyParams;
	}

	/**
	 * 获取异步响应时间
	 *
	 * @return 异步响应时间
	 */
	public Date getNotifyTime() {
		return notifyTime;
	}

	/**
	 * 设置异步响应时间
	 * 
	 * @param notifyTime
	 *            要设置的异步响应时间
	 */
	public void setNotifyTime(Date notifyTime) {
		this.notifyTime = notifyTime;
	}

	/**
	 * 获取异常信息
	 *
	 * @return 异常信息
	 */
	public String getException() {
		return exception;
	}

	/**
	 * 设置异常信息
	 * 
	 * @param exception
	 *            要设置的异常信息
	 */
	public void setException(String exception) {
		this.exception = exception;
	}

	/**
	 * 获取添加时间
	 *
	 * @return 添加时间
	 */
	public Date getAddTime() {
		return addTime;
	}

	/**
	 * 设置添加时间
	 * 
	 * @param addTime
	 *            要设置的添加时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

}