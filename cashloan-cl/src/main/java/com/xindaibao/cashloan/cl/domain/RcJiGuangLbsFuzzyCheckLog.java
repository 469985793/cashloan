package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 极光黑名单记录表实体
 * 
 *
 */
 public class RcJiGuangLbsFuzzyCheckLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户标识
    */
    private Long userId;

    /**
    * 订单号
    */
    private String orderNo;

    /**
    * 同步响应返回是否黑名单内容
    */
    private String isBlack;

    /**
    * 响应码
    */
    private String respCode;

    /**
    * 同步响应结果
    */
    private String respParams;

    /**
    * 创建时间
    */
    private Date createTime;


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
    * 获取同步响应返回是否黑名单内容
    *
    * @return 同步响应返回是否黑名单内容
    */
    public String getIsBlack(){
        return isBlack;
    }

    /**
    * 设置同步响应返回是否黑名单内容
    *
    * @param isBlack 要设置的同步响应返回是否黑名单内容
    */
    public void setIsBlack(String isBlack){
        this.isBlack = isBlack;
    }

    /**
    * 获取响应码
    *
    * @return 响应码
    */
    public String getRespCode(){
        return respCode;
    }

    /**
    * 设置响应码
    *
    * @param respCode 要设置的响应码
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
    * 获取创建时间
    *
    * @return 创建时间
    */
    public Date getCreateTime(){
        return createTime;
    }

    /**
    * 设置创建时间
    *
    * @param createTime 要设置的创建时间
    */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

}