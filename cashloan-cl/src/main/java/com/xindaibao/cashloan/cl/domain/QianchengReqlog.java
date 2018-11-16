package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 浅橙借款申请审核实体
 * 
 * @author
 * @version 1.0.0


 */
public class QianchengReqlog implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	private Long id;

	/**
	 * 申请订单号
	 */
	private String orderNo;

	/**
	 * 借款标识
	 */
	private Long borrowId;

	/**
	 * 用户标识
	 */
	private Long userId;

	/**
	 * 审核状态 10 已提交申请 20 审核通过 30 审核不通过
	 */
	private String state;

	/**
	 * 添加时间
	 */
	private Date createTime;

	/**
	 * 回调返回码
	 */
	private String respCode;

	/**
	 * 回调更新时间
	 */
	private Date updateTime;
	
	/**
	 * 同步回调时间
	 */
	private Date respTime;
	
	/**
	 * 同步返回参数
	 */
	private String respParams;
	
	/**
	 * 异步返回参数
	 */
	private String notifyParams;
	
	/**
	 * 同步返回订单号
	 */
	private String respOrderNo;
	
	/**
	 * 审核结果
	 */
	private String rsState;
	
	/**
	 * 审核结果描述
	 */
	private String rsDesc;
	
	/**
	 * 浅橙请求参数
	 */
	private String reqParams;

	public QianchengReqlog() {
		super();
	}

	public QianchengReqlog(String orderNo, Long borrowId, Long userId,
			String state, Date createTime) {
		super();
		this.orderNo = orderNo;
		this.borrowId = borrowId;
		this.userId = userId;
		this.state = state;
		this.createTime = createTime;
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
	 * 获取申请订单号
	 *
	 * @return 申请订单号
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * 设置申请订单号
	 * 
	 * @param orderNo
	 *            要设置的申请订单号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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
	 * 获取审核状态 10 已提交申请 20 审核通过 30 审核不通过
	 *
	 * @return 审核状态 10 已提交申请 20 审核通过 30 审核不通过
	 */
	public String getState() {
		return state;
	}

	/**
	 * 设置审核状态 10 已提交申请 20 审核通过 30 审核不通过
	 * 
	 * @param state
	 *            要设置的审核状态 10 已提交申请 20 审核通过 30 审核不通过
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 获取添加时间
	 *
	 * @return 添加时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置添加时间
	 * 
	 * @param createTime
	 *            要设置的添加时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取回调返回码
	 *
	 * @return 回调返回码
	 */
	public String getRespCode() {
		return respCode;
	}

	/**
	 * 设置回调返回码
	 * 
	 * @param respCode
	 *            要设置的回调返回码
	 */
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	/**
	 * 获取回调更新时间
	 *
	 * @return 回调更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置回调更新时间
	 * 
	 * @param updateTime
	 *            要设置的回调更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/** 
	 * 获取同步回调时间
	 * @return respTime
	 */
	public Date getRespTime() {
		return respTime;
	}

	/** 
	 * 设置同步回调时间
	 * @param respTime
	 */
	public void setRespTime(Date respTime) {
		this.respTime = respTime;
	}

	/** 
	 * 获取同步返回参数
	 * @return respParams
	 */
	public String getRespParams() {
		return respParams;
	}

	/** 
	 * 设置同步返回参数
	 * @param respParams
	 */
	public void setRespParams(String respParams) {
		this.respParams = respParams;
	}

	/** 
	 * 获取异步返回参数
	 * @return notifyParams
	 */
	public String getNotifyParams() {
		return notifyParams;
	}

	/** 
	 * 设置异步返回参数
	 * @param notifyParams
	 */
	public void setNotifyParams(String notifyParams) {
		this.notifyParams = notifyParams;
	}

	/** 
	 * 获取同步返回订单号
	 * @return respOrderNo
	 */
	public String getRespOrderNo() {
		return respOrderNo;
	}

	/** 
	 * 设置同步返回订单号
	 * @param respOrderNo
	 */
	public void setRespOrderNo(String respOrderNo) {
		this.respOrderNo = respOrderNo;
	}

	/** 
	 * 获取审核结果
	 * @return rsState
	 */
	public String getRsState() {
		return rsState;
	}

	/** 
	 * 设置审核结果
	 * @param rsState
	 */
	public void setRsState(String rsState) {
		this.rsState = rsState;
	}

	/** 
	 * 获取审核结果描述
	 * @return rsDesc
	 */
	public String getRsDesc() {
		return rsDesc;
	}

	/** 
	 * 设置审核结果描述
	 * @param rsDesc
	 */
	public void setRsDesc(String rsDesc) {
		this.rsDesc = rsDesc;
	}

	/** 
	 * 获取浅橙请求参数
	 * @return reqParams
	 */
	public String getReqParams() {
		return reqParams;
	}

	/** 
	 * 设置浅橙请求参数
	 * @param reqParams
	 */
	public void setReqParams(String reqParams) {
		this.reqParams = reqParams;
	}

}