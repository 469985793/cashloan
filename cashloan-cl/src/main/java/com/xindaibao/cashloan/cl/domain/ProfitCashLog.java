package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 分润提现记录实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-18 16:51:48


 * 

 */
 public class ProfitCashLog implements Serializable {

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
    * 提现金额
    */
    private Double amount;

    /**
    * 提现时间
    */
    private Date addTime;


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
    * 获取提现金额
    *
    * @return 提现金额
    */
    public Double getAmount(){
    return amount;
    }

    /**
    * 设置提现金额
    * 
    * @param amount 要设置的提现金额
    */
    public void setAmount(Double amount){
    this.amount = amount;
    }

    /**
    * 获取提现时间
    *
    * @return 提现时间
    */
    public Date getAddTime(){
    return addTime;
    }

    /**
    * 设置提现时间
    * 
    * @param addTime 要设置的提现时间
    */
    public void setAddTime(Date addTime){
    this.addTime = addTime;
    }

}