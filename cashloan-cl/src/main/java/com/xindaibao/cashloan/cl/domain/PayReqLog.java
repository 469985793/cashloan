package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 支付请求记录实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-07 16:18:30
 */
 public class PayReqLog implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	private Long id;

	/**
	 * 第三方接口名称
	 */
	private String service;

	/**
	 * 资金存管订单号
	 */
	private String orderNo;

	/**
	 * 请求参数
	 */
	private String params;

	/**
	 * 请求tpp参数拼接
	 */
	private String reqDetailParams;

	/**
	 * 页面返回/同步回调参数
	 */
	private String returnParams;

	/**
	 * 页面返回/同步回调时间
	 */
	private Date returnTime;

	/**
	 * 后台通知/异步回调参数
	 */
	private String notifyParams;

	/**
	 * 后台通知/异步回调时间
	 */
	private Date notifyTime;

	/**
	 * 响应结果：1成功，-1失败
	 */
	private Integer result;

	/**
	 * 请求时间
	 */
	private Date createTime;

	/**
	 * 请求IP
	 */
	private String ip;

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
	 * 获取第三方接口名称
	 *
	 * @return 第三方接口名称
	 */
	public String getService() {
		return service;
	}

	/**
	 * 设置第三方接口名称
	 * 
	 * @param service
	 *            要设置的第三方接口名称
	 */
	public void setService(String service) {
		this.service = service;
	}

	/**
	 * 获取资金存管订单号
	 *
	 * @return 资金存管订单号
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * 设置资金存管订单号
	 * 
	 * @param orderNo
	 *            要设置的资金存管订单号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 获取请求参数
	 *
	 * @return 请求参数
	 */
	public String getParams() {
		return params;
	}

	/**
	 * 设置请求参数
	 * 
	 * @param params
	 *            要设置的请求参数
	 */
	public void setParams(String params) {
		this.params = params;
	}

	/**
	 * 获取请求tpp参数拼接
	 *
	 * @return 请求tpp参数拼接
	 */
	public String getReqDetailParams() {
		return reqDetailParams;
	}

	/**
	 * 设置请求tpp参数拼接
	 * 
	 * @param reqDetailParams
	 *            要设置的请求tpp参数拼接
	 */
	public void setReqDetailParams(String reqDetailParams) {
		this.reqDetailParams = reqDetailParams;
	}

	/**
	 * 获取页面返回/同步回调参数
	 *
	 * @return 页面返回/同步回调参数
	 */
	public String getReturnParams() {
		return returnParams;
	}

	/**
	 * 设置页面返回/同步回调参数
	 * 
	 * @param returnParams
	 *            要设置的页面返回/同步回调参数
	 */
	public void setReturnParams(String returnParams) {
		this.returnParams = returnParams;
	}

	/**
	 * 获取页面返回/同步回调时间
	 *
	 * @return 页面返回/同步回调时间
	 */
	public Date getReturnTime() {
		return returnTime;
	}

	/**
	 * 设置页面返回/同步回调时间
	 * 
	 * @param returnTime
	 *            要设置的页面返回/同步回调时间
	 */
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	/**
	 * 获取后台通知/异步回调参数
	 *
	 * @return 后台通知/异步回调参数
	 */
	public String getNotifyParams() {
		return notifyParams;
	}

	/**
	 * 设置后台通知/异步回调参数
	 * 
	 * @param notifyParams
	 *            要设置的后台通知/异步回调参数
	 */
	public void setNotifyParams(String notifyParams) {
		this.notifyParams = notifyParams;
	}

	/**
	 * 获取后台通知/异步回调时间
	 *
	 * @return 后台通知/异步回调时间
	 */
	public Date getNotifyTime() {
		return notifyTime;
	}

	/**
	 * 设置后台通知/异步回调时间
	 * 
	 * @param notifyTime
	 *            要设置的后台通知/异步回调时间
	 */
	public void setNotifyTime(Date notifyTime) {
		this.notifyTime = notifyTime;
	}

	/**
	 * 获取响应结果：1成功，-1失败
	 *
	 * @return 响应结果：1成功，-1失败
	 */
	public Integer getResult() {
		return result;
	}

	/**
	 * 设置响应结果：1成功，-1失败
	 * 
	 * @param result
	 *            要设置的响应结果：1成功，-1失败
	 */
	public void setResult(Integer result) {
		this.result = result;
	}

	/**
	 * 获取请求时间
	 * 
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置请求时间
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取请求IP
	 * 
	 * @return ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 设置请求IP
	 * 
	 * @param ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

}