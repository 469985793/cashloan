package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 贷后邦贷后邦反欺诈请求记录表实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-06-02 18:20:59



 */
 public class DhbReqLog implements Serializable {

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
    * 添加时间
    */
    private Date createTime;

    /**
    * 回调返回码
    */
    private String respCode;

    /**
    * 同步响应结果
    */
    private String respParams;

    /**
    * 同步响应时间
    */
    private Date respTime;

    /**
    * 同步响应订单号
    */
    private String respOrderNo;


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
    * 获取回调返回码
    *
    * @return 回调返回码
    */
    public String getRespCode(){
        return respCode;
    }

    /**
    * 设置回调返回码
    * 
    * @param respCode 要设置的回调返回码
    */
    public void setRespCode(String respCode){
        this.respCode = respCode;
    }

    /**
    * 获取同步响应结果
    *
    * @return 同步响应结果
    */
    public String getRespParams(){
        return respParams;
    }

    /**
    * 设置同步响应结果
    * 
    * @param respParams 要设置的同步响应结果
    */
    public void setRespParams(String respParams){
        this.respParams = respParams;
    }

    /**
    * 获取同步响应时间
    *
    * @return 同步响应时间
    */
    public Date getRespTime(){
        return respTime;
    }

    /**
    * 设置同步响应时间
    * 
    * @param respTime 要设置的同步响应时间
    */
    public void setRespTime(Date respTime){
        this.respTime = respTime;
    }

    /**
    * 获取同步响应订单号
    *
    * @return 同步响应订单号
    */
    public String getRespOrderNo(){
        return respOrderNo;
    }

    /**
    * 设置同步响应订单号
    * 
    * @param respOrderNo 要设置的同步响应订单号
    */
    public void setRespOrderNo(String respOrderNo){
        this.respOrderNo = respOrderNo;
    }

}