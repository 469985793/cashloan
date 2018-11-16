package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

import tool.util.DateUtil;

/**
 * 运营商认证通知详情表实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-05-17 12:38:22



 */
 public class OperatorRespDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 请求记录标识
    */
    private Long reqLogId;

    /**
    * 订单号
    */
    private String orderNo;

    /**
    * 异步通知结果
    */
    private String notifyParams;

    /**
    * 异步通知时间
    */
    private Date notifyTime;
    
    public OperatorRespDetail(){
    	super();
    }
    
    public OperatorRespDetail(long reqLogId, String orderNo, String notifyParams){
    	super();
    	this.reqLogId = reqLogId;
    	this.orderNo = orderNo;
    	this.notifyParams = notifyParams;
    	this.notifyTime = DateUtil.getNow();
    }


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
    * 获取请求记录标识
    *
    * @return 请求记录标识
    */
    public Long getReqLogId(){
        return reqLogId;
    }

    /**
    * 设置请求记录标识
    * 
    * @param reqLogId 要设置的请求记录标识
    */
    public void setReqLogId(Long reqLogId){
        this.reqLogId = reqLogId;
    }

    /**
    * 获取订单号
    *
    * @return 订单号
    */
    public String getOrderNo(){
        return orderNo;
    }

    /**
    * 设置订单号
    * 
    * @param orderNo 要设置的订单号
    */
    public void setOrderNo(String orderNo){
        this.orderNo = orderNo;
    }

    /**
    * 获取异步通知结果
    *
    * @return 异步通知结果
    */
    public String getNotifyParams(){
        return notifyParams;
    }

    /**
    * 设置异步通知结果
    * 
    * @param notifyParams 要设置的异步通知结果
    */
    public void setNotifyParams(String notifyParams){
        this.notifyParams = notifyParams;
    }

    /**
    * 获取异步通知时间
    *
    * @return 异步通知时间
    */
    public Date getNotifyTime(){
        return notifyTime;
    }

    /**
    * 设置异步通知时间
    * 
    * @param notifyTime 要设置的异步通知时间
    */
    public void setNotifyTime(Date notifyTime){
        this.notifyTime = notifyTime;
    }

}