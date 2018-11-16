package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;

/**
 * 同盾审核报告详细信息实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-05-17 10:11:29




 */
 public class TongdunRespDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 同盾请求id
    */
    private Long reqId;

    /**
    * 同盾申请记录订单号
    */
    private String orderNo;

    /**
    * 同盾审核报告id
    */
    private String reportId;

    /**
    * 审核报告具体信息
    */
    private String queryParams;


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
    * 获取同盾请求id
    *
    * @return 同盾请求id
    */
    public Long getReqId(){
    return reqId;
    }

    /**
    * 设置同盾请求id
    * 
    * @param reqId 要设置的同盾请求id
    */
    public void setReqId(Long reqId){
    this.reqId = reqId;
    }

    /**
    * 获取同盾申请记录订单号
    *
    * @return 同盾申请记录订单号
    */
    public String getOrderNo(){
    return orderNo;
    }

    /**
    * 设置同盾申请记录订单号
    * 
    * @param orderNo 要设置的同盾申请记录订单号
    */
    public void setOrderNo(String orderNo){
    this.orderNo = orderNo;
    }

    /**
    * 获取同盾审核报告id
    *
    * @return 同盾审核报告id
    */
    public String getReportId(){
    return reportId;
    }

    /**
    * 设置同盾审核报告id
    * 
    * @param reportId 要设置的同盾审核报告id
    */
    public void setReportId(String reportId){
    this.reportId = reportId;
    }

    /**
    * 获取审核报告具体信息
    *
    * @return 审核报告具体信息
    */
    public String getQueryParams(){
    return queryParams;
    }

    /**
    * 设置审核报告具体信息
    * 
    * @param queryParams 要设置的审核报告具体信息
    */
    public void setQueryParams(String queryParams){
    this.queryParams = queryParams;
    }

}