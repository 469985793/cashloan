package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 同盾第三方请求记录实体
 */
 public class TongdunReqLog implements Serializable {

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
    * 审核状态  10 已提交申请   20 查询成功  30 查询失败  
    */
    private String state;

    /**
    * 添加时间
    */
    private Date createTime;

    /**
    * 获取审核报告返回码
    */
    private String submitCode;

    /**
    * 获取审核报告响应结果
    */
    private String submitParams;

    /**
    * 风险报告编码
    */
    private String reportId;

    /**
    * 查询报告时间
    */
    private Date updateTime;

    /**
    * 查询报告返回码
    */
    private String queryCode;

    /**
    * 风控结果    Accept:建议通过,Review:建议审核,Reject:建议拒绝
    */
    private String rsState;

    /**
    * 风控分数
    */
    private Integer rsScore;


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
    * 获取申请订单号
    *
    * @return 申请订单号
    */
    public String getOrderNo(){
    return orderNo;
    }

    /**
    * 设置申请订单号
    * 
    * @param orderNo 要设置的申请订单号
    */
    public void setOrderNo(String orderNo){
    this.orderNo = orderNo;
    }

    /**
    * 获取借款标识
    *
    * @return 借款标识
    */
    public Long getBorrowId(){
    return borrowId;
    }

    /**
    * 设置借款标识
    * 
    * @param borrowId 要设置的借款标识
    */
    public void setBorrowId(Long borrowId){
    this.borrowId = borrowId;
    }

    /**
    * 获取用户标识
    *
    * @return 用户标识
    */
    public Long getUserId(){
    return userId;
    }

    /**
    * 设置用户标识
    * 
    * @param userId 要设置的用户标识
    */
    public void setUserId(Long userId){
    this.userId = userId;
    }

    /**
    * @return 审核状态
    */
    public String getState(){
    return state;
    }

    /**
    * 
    * @param state 要设置的审核状态 
    */
    public void setState(String state){
    this.state = state;
    }

    /**
    * 获取添加时间
    *
    * @return 添加时间
    */
    public Date getCreateTime(){
    return createTime;
    }

    /**
    * 设置添加时间
    * 
    * @param createTime 要设置的添加时间
    */
    public void setCreateTime(Date createTime){
    this.createTime = createTime;
    }

    /**
    * 获取获取审核报告返回码
    *
    * @return 获取审核报告返回码
    */
    public String getSubmitCode(){
    return submitCode;
    }

    /**
    * 设置获取审核报告返回码
    * 
    * @param submitCode 要设置的获取审核报告返回码
    */
    public void setSubmitCode(String submitCode){
    this.submitCode = submitCode;
    }

    /**
    * 获取获取审核报告响应结果
    *
    * @return 获取审核报告响应结果
    */
    public String getSubmitParams(){
    return submitParams;
    }

    /**
    * 设置获取审核报告响应结果
    * 
    * @param submitParams 要设置的获取审核报告响应结果
    */
    public void setSubmitParams(String submitParams){
    this.submitParams = submitParams;
    }

    /**
    * 获取风险报告编码
    *
    * @return 风险报告编码
    */
    public String getReportId(){
    return reportId;
    }

    /**
    * 设置风险报告编码
    * 
    * @param reportId 要设置的风险报告编码
    */
    public void setReportId(String reportId){
    this.reportId = reportId;
    }

    /**
    * 获取查询报告时间
    *
    * @return 查询报告时间
    */
    public Date getUpdateTime(){
    return updateTime;
    }

    /**
    * 设置查询报告时间
    * 
    * @param updateTime 要设置的查询报告时间
    */
    public void setUpdateTime(Date updateTime){
    this.updateTime = updateTime;
    }

    /**
    * 获取查询报告返回码
    *
    * @return 查询报告返回码
    */
    public String getQueryCode(){
    return queryCode;
    }

    /**
    * 设置查询报告返回码
    * 
    * @param queryCode 要设置的查询报告返回码
    */
    public void setQueryCode(String queryCode){
    this.queryCode = queryCode;
    }

    /**
    * 获取风控结果    Accept:建议通过,Review:建议审核,Reject:建议拒绝
    *
    * @return 风控结果    Accept:建议通过,Review:建议审核,Reject:建议拒绝
    */
    public String getRsState(){
    return rsState;
    }

    /**
    * 设置风控结果    Accept:建议通过,Review:建议审核,Reject:建议拒绝
    * 
    * @param rsState 要设置的风控结果    Accept:建议通过,Review:建议审核,Reject:建议拒绝
    */
    public void setRsState(String rsState){
    this.rsState = rsState;
    }

    /**
    * 获取风控分数
    *
    * @return 风控分数
    */
    public Integer getRsScore(){
    return rsScore;
    }

    /**
    * 设置风控分数
    * 
    * @param rsScore 要设置的风控分数
    */
    public void setRsScore(Integer rsScore){
    this.rsScore = rsScore;
    }

}