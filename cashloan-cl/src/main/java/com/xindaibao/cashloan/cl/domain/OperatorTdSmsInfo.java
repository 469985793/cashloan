package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;

/**
 * 短信详单实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-05-24 09:29:45



 */
 public class OperatorTdSmsInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 
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
    * 费用合计
    */
    private String totalMsgCost;

    /**
    * 总短信次数
    */
    private String totalMsgCount;

    /**
    * 短信周期
    */
    private String msgCycle;


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
    * 获取
    *
    * @return 
    */
    public Long getUserId(){
        return userId;
    }

    /**
    * 设置
    * 
    * @param userId 要设置的
    */
    public void setUserId(Long userId){
        this.userId = userId;
    }

    /**
    * 获取费用合计
    *
    * @return 费用合计
    */
    public String getTotalMsgCost(){
        return totalMsgCost;
    }

    /**
    * 设置费用合计
    * 
    * @param totalMsgCost 要设置的费用合计
    */
    public void setTotalMsgCost(String totalMsgCost){
        this.totalMsgCost = totalMsgCost;
    }

    /**
    * 获取总短信次数
    *
    * @return 总短信次数
    */
    public String getTotalMsgCount(){
        return totalMsgCount;
    }

    /**
    * 设置总短信次数
    * 
    * @param totalMsgCount 要设置的总短信次数
    */
    public void setTotalMsgCount(String totalMsgCount){
        this.totalMsgCount = totalMsgCount;
    }

    /**
    * 获取短信周期
    *
    * @return 短信周期
    */
    public String getMsgCycle(){
        return msgCycle;
    }

    /**
    * 设置短信周期
    * 
    * @param msgCycle 要设置的短信周期
    */
    public void setMsgCycle(String msgCycle){
        this.msgCycle = msgCycle;
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