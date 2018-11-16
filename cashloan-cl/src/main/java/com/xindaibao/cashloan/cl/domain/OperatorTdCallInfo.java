package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;

/**
 * 通话记录详单实体
 */
 public class OperatorTdCallInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 同盾运营商通话详单
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
    * 总通话时长
    */
    private String totalCallTime;

    /**
    * 总通话次数
    */
    private String totalCallCount;

    /**
    * 费用合计
    */
    private String totalFee;

    /**
    * 通话周期
    */
    private String callCycle;


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
    * 获取同盾运营商通话详单
    *
    * @return 同盾运营商通话详单
    */
    public Long getUserId(){
        return userId;
    }

    /**
    * 设置同盾运营商通话详单
    * 
    * @param userId 要设置的同盾运营商通话详单
    */
    public void setUserId(Long userId){
        this.userId = userId;
    }

    /**
    * 获取总通话时长
    *
    * @return 总通话时长
    */
    public String getTotalCallTime(){
        return totalCallTime;
    }

    /**
    * 设置总通话时长
    * 
    * @param totalCallTime 要设置的总通话时长
    */
    public void setTotalCallTime(String totalCallTime){
        this.totalCallTime = totalCallTime;
    }

    /**
    * 获取总通话次数
    *
    * @return 总通话次数
    */
    public String getTotalCallCount(){
        return totalCallCount;
    }

    /**
    * 设置总通话次数
    * 
    * @param totalCallCount 要设置的总通话次数
    */
    public void setTotalCallCount(String totalCallCount){
        this.totalCallCount = totalCallCount;
    }

    /**
    * 获取费用合计
    *
    * @return 费用合计
    */
    public String getTotalFee(){
        return totalFee;
    }

    /**
    * 设置费用合计
    * 
    * @param totalFee 要设置的费用合计
    */
    public void setTotalFee(String totalFee){
        this.totalFee = totalFee;
    }

    /**
    * 获取通话周期
    *
    * @return 通话周期
    */
    public String getCallCycle(){
        return callCycle;
    }

    /**
    * 设置通话周期
    * 
    * @param callCycle 要设置的通话周期
    */
    public void setCallCycle(String callCycle){
        this.callCycle = callCycle;
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