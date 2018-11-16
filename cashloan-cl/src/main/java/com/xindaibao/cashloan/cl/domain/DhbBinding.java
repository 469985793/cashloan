package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;

/**
 * 贷后邦可疑绑定信息实体
 */
 public class DhbBinding implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 订单号
    */
    private String orderNo;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 可疑绑定身份证个数
    */
    private Integer bindingIdcardsSize;

    /**
    * 可疑绑定身份证具体信息
    */
    private String bindingIdcardsDetail;

    /**
    * 可疑绑定号码个数
    */
    private Integer bindingPhonesSize;

    /**
    * 可疑绑定号码具体信息
    */
    private String bindingPhonesDetail;


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
    * 获取可疑绑定身份证个数
    *
    * @return 可疑绑定身份证个数
    */
    public Integer getBindingIdcardsSize(){
        return bindingIdcardsSize;
    }

    /**
    * 设置可疑绑定身份证个数
    * 
    * @param bindingIdcardsSize 要设置的可疑绑定身份证个数
    */
    public void setBindingIdcardsSize(Integer bindingIdcardsSize){
        this.bindingIdcardsSize = bindingIdcardsSize;
    }

    /**
    * 获取可疑绑定身份证具体信息
    *
    * @return 可疑绑定身份证具体信息
    */
    public String getBindingIdcardsDetail(){
        return bindingIdcardsDetail;
    }

    /**
    * 设置可疑绑定身份证具体信息
    * 
    * @param bindingIdcardsDetail 要设置的可疑绑定身份证具体信息
    */
    public void setBindingIdcardsDetail(String bindingIdcardsDetail){
        this.bindingIdcardsDetail = bindingIdcardsDetail;
    }

    /**
    * 获取可疑绑定号码个数
    *
    * @return 可疑绑定号码个数
    */
    public Integer getBindingPhonesSize(){
        return bindingPhonesSize;
    }

    /**
    * 设置可疑绑定号码个数
    * 
    * @param bindingPhonesSize 要设置的可疑绑定号码个数
    */
    public void setBindingPhonesSize(Integer bindingPhonesSize){
        this.bindingPhonesSize = bindingPhonesSize;
    }

    /**
    * 获取可疑绑定号码具体信息
    *
    * @return 可疑绑定号码具体信息
    */
    public String getBindingPhonesDetail(){
        return bindingPhonesDetail;
    }

    /**
    * 设置可疑绑定号码具体信息
    * 
    * @param bindingPhonesDetail 要设置的可疑绑定号码具体信息
    */
    public void setBindingPhonesDetail(String bindingPhonesDetail){
        this.bindingPhonesDetail = bindingPhonesDetail;
    }

}