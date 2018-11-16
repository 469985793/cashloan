package com.xindaibao.cashloan.cl.model.pay.lianlian;

import java.util.Date;

import tool.util.DateUtil;
import tool.util.NumberUtil;

/**
 * 交易明细(分期付)对账
 * 
 * @author
 * @version 1.0.0
 * @date 2017年4月13日 下午3:20:33



 */
public class TransactionCheckModel extends BasePaymentModel{

	/**
	 * 商户订单时间
	 */
	private Date orderTime;

	/**
	 * 商户业务编号
	 */
	private String businessNo;

	/**
	 * 银通订单号
	 */
	private String ytOrderNo;

	/**
	 * 银通账务日期
	 */
	private String ytAccountDate;


	/**
	 * 商户收款标志(0收款 1付款)
	 */
	private String payMark;

	/**
	 * 交易状态(0-成功 5-已退款)
	 */
	private String state;

	/**
	 * 更新时间
	 */
	private Date updatetime;

	/**
	 * 手续费
	 */
	private String fee;

	/**
	 * 支付产品
	 */
	private String payProduct;

	/**
	 * 支付方式
	 */
	private String payWay;

	/**
	 * 订单信息
	 */
	private String orderInfo;
	

	public TransactionCheckModel() {
		super();
	}
	
	public TransactionCheckModel anlsStr(String str) {
		TransactionCheckModel checkModel = new TransactionCheckModel();
		String[] checkArray = str.split(",");
		checkModel.setOrderNo(checkArray[0]);
		checkModel.setOid_partner(checkArray[1]);
		checkModel.setOrderTime(DateUtil.valueOf(checkArray[2]));
		checkModel.setBusinessNo(checkArray[3]);
		checkModel.setYtOrderNo(checkArray[4]);
		checkModel.setYtAccountDate(checkArray[5]);
		checkModel.setAmount(NumberUtil.getDouble(checkArray[6]));
		checkModel.setPayMark(checkArray[7]);
		checkModel.setState(checkArray[8]);
		checkModel.setUpdatetime(DateUtil.valueOf(checkArray[9]));
		checkModel.setFee(checkArray[10]);
		checkModel.setPayProduct(checkArray[11]);
		checkModel.setPayWay(checkArray[12]);
		checkModel.setOrderInfo(checkArray[13]);
		return checkModel;
	}
	
	/**
	 * 获取商户订单时间
	 * 
	 * @return orderTime
	 */
	public Date getOrderTime() {
		return orderTime;
	}

	/**
	 * 设置商户订单时间
	 * 
	 * @param orderTime
	 */
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	/**
	 * 获取商户业务编号
	 * 
	 * @return businessNo
	 */
	public String getBusinessNo() {
		return businessNo;
	}

	/**
	 * 设置商户业务编号
	 * 
	 * @param businessNo
	 */
	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	/**
	 * 获取银通订单号
	 * 
	 * @return ytOrderNo
	 */
	public String getYtOrderNo() {
		return ytOrderNo;
	}

	/**
	 * 设置银通订单号
	 * 
	 * @param ytOrderNo
	 */
	public void setYtOrderNo(String ytOrderNo) {
		this.ytOrderNo = ytOrderNo;
	}

	/**
	 * 获取银通账务日期
	 * 
	 * @return ytAccountDate
	 */
	public String getYtAccountDate() {
		return ytAccountDate;
	}

	/**
	 * 设置银通账务日期
	 * 
	 * @param ytAccountDate
	 */
	public void setYtAccountDate(String ytAccountDate) {
		this.ytAccountDate = ytAccountDate;
	}

	/**
	 * 获取商户收款标志(0收款1付款)
	 * 
	 * @return payMark
	 */
	public String getPayMark() {
		return payMark;
	}

	/**
	 * 设置商户收款标志(0收款1付款)
	 * 
	 * @param payMark
	 */
	public void setPayMark(String payMark) {
		this.payMark = payMark;
	}

	/**
	 * 获取交易状态(0-成功5-已退款)
	 * 
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 * 设置交易状态(0-成功5-已退款)
	 * 
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 获取更新时间
	 * 
	 * @return updatetime
	 */
	public Date getUpdatetime() {
		return updatetime;
	}

	/**
	 * 设置更新时间
	 * 
	 * @param updatetime
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	/**
	 * 获取手续费
	 * 
	 * @return fee
	 */
	public String getFee() {
		return fee;
	}

	/**
	 * 设置手续费
	 * 
	 * @param fee
	 */
	public void setFee(String fee) {
		this.fee = fee;
	}

	/**
	 * 获取支付产品
	 * 
	 * @return payProduct
	 */
	public String getPayProduct() {
		return payProduct;
	}

	/**
	 * 设置支付产品
	 * 
	 * @param payProduct
	 */
	public void setPayProduct(String payProduct) {
		this.payProduct = payProduct;
	}

	/**
	 * 获取支付方式
	 * 
	 * @return payWay
	 */
	public String getPayWay() {
		return payWay;
	}

	/**
	 * 设置支付方式
	 * 
	 * @param payWay
	 */
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	/**
	 * 获取订单信息
	 * 
	 * @return orderInfo
	 */
	public String getOrderInfo() {
		return orderInfo;
	}

	/**
	 * 设置订单信息
	 * 
	 * @param orderInfo
	 */
	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}

}
