package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;

/**
 * 同盾运营商账单信息实体
 */
 public class OperatorTdBills implements Serializable {

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
     * 请求id
     */
    private Long  reqLogId;
    /**
     * 请求订单号
     */
    private String orderNo;

    /**
    * 账单周期
    */
    private String billCycle;

    /**
    * 账单费用
    */
    private String billFee;

    /**
    * 减免
    */
    private String billDiscount;

    /**
    * 费用合计
    */
    private String billTotal;

    /**
    * 违约金
    */
    private String breachAmount;

    /**
    * 已支付
    */
    private String paidAmount;

    /**
    * 未支付
    */
    private String unpaidAmount;

    /**
    * 账单记录
    */
    private String billRecord;

    /**
    * 使用量详情
    */
    private String usageDetail;


    /**
    * 获取主键Id
    *
    * @return id
    */
    public Long getId(){
        return id;
    }

    /**
    * 设置主键Id
    * 
    * @param 要设置的主键Id
    */
    public void setId(Long id){
        this.id = id;
    }

    /**
    * 获取用户id
    *
    * @return 用户id
    */
    public Long getUserId(){
        return userId;
    }

    /**
    * 设置用户id
    * 
    * @param userId 要设置的用户id
    */
    public void setUserId(Long userId){
        this.userId = userId;
    }

    /**
    * 获取账单周期
    *
    * @return 账单周期
    */
    public String getBillCycle(){
        return billCycle;
    }

    /**
    * 设置账单周期
    * 
    * @param billCycle 要设置的账单周期
    */
    public void setBillCycle(String billCycle){
        this.billCycle = billCycle;
    }

    /**
    * 获取账单费用
    *
    * @return 账单费用
    */
    public String getBillFee(){
        return billFee;
    }

    /**
    * 设置账单费用
    * 
    * @param billFee 要设置的账单费用
    */
    public void setBillFee(String billFee){
        this.billFee = billFee;
    }

    /**
    * 获取减免
    *
    * @return 减免
    */
    public String getBillDiscount(){
        return billDiscount;
    }

    /**
    * 设置减免
    * 
    * @param billDiscount 要设置的减免
    */
    public void setBillDiscount(String billDiscount){
        this.billDiscount = billDiscount;
    }

    /**
    * 获取费用合计
    *
    * @return 费用合计
    */
    public String getBillTotal(){
        return billTotal;
    }

    /**
    * 设置费用合计
    * 
    * @param billTotal 要设置的费用合计
    */
    public void setBillTotal(String billTotal){
        this.billTotal = billTotal;
    }

    /**
    * 获取违约金
    *
    * @return 违约金
    */
    public String getBreachAmount(){
        return breachAmount;
    }

    /**
    * 设置违约金
    * 
    * @param breachAmount 要设置的违约金
    */
    public void setBreachAmount(String breachAmount){
        this.breachAmount = breachAmount;
    }

    /**
    * 获取已支付
    *
    * @return 已支付
    */
    public String getPaidAmount(){
        return paidAmount;
    }

    /**
    * 设置已支付
    * 
    * @param paidAmount 要设置的已支付
    */
    public void setPaidAmount(String paidAmount){
        this.paidAmount = paidAmount;
    }

    /**
    * 获取未支付
    *
    * @return 未支付
    */
    public String getUnpaidAmount(){
        return unpaidAmount;
    }

    /**
    * 设置未支付
    * 
    * @param unpaidAmount 要设置的未支付
    */
    public void setUnpaidAmount(String unpaidAmount){
        this.unpaidAmount = unpaidAmount;
    }

    /**
    * 获取账单记录
    *
    * @return 账单记录
    */
    public String getBillRecord(){
        return billRecord;
    }

    /**
    * 设置账单记录
    * 
    * @param billRecord 要设置的账单记录
    */
    public void setBillRecord(String billRecord){
        this.billRecord = billRecord;
    }

    /**
    * 获取使用量详情
    *
    * @return 使用量详情
    */
    public String getUsageDetail(){
        return usageDetail;
    }

    /**
    * 设置使用量详情
    * 
    * @param usageDetail 要设置的使用量详情
    */
    public void setUsageDetail(String usageDetail){
        this.usageDetail = usageDetail;
    }

	public Long getReqLogId() {
		return reqLogId;
	}

	public void setReqLogId(Long reqLogId) {
		this.reqLogId = reqLogId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

 

}