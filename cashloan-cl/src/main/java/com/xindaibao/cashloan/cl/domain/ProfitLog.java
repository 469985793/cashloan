package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 分润记录实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-18 17:04:10


 * 

 */
 public class ProfitLog implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 借款id
    */
    private Long borrowId;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 分润人id
    */
    private Long agentId;

    /**
    * 分润金额
    */
    private Double amount;

    /**
    * 分润率
    */
    private Double rate;

    /**
    * 添加时间
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
    * 获取借款id
    *
    * @return 借款id
    */
    public Long getBorrowId(){
    return borrowId;
    }

    /**
    * 设置借款id
    * 
    * @param borrowId 要设置的借款id
    */
    public void setBorrowId(Long borrowId){
    this.borrowId = borrowId;
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
    * 获取分润人id
    *
    * @return 分润人id
    */
    public Long getAgentId(){
    return agentId;
    }

    /**
    * 设置分润人id
    * 
    * @param agentId 要设置的分润人id
    */
    public void setAgentId(Long agentId){
    this.agentId = agentId;
    }

    /**
    * 获取分润金额
    *
    * @return 分润金额
    */
    public Double getAmount(){
    return amount;
    }

    /**
    * 设置分润金额
    * 
    * @param amount 要设置的分润金额
    */
    public void setAmount(Double amount){
    this.amount = amount;
    }

    /**
    * 获取分润率
    *
    * @return 分润率
    */
    public Double getRate(){
    return rate;
    }

    /**
    * 设置分润率
    * 
    * @param rate 要设置的分润率
    */
    public void setRate(Double rate){
    this.rate = rate;
    }

    /**
    * 获取添加时间
    *
    * @return 添加时间
    */
    public Date getAddTime(){
    return addTime;
    }

    /**
    * 设置添加时间
    * 
    * @param addTime 要设置的添加时间
    */
    public void setAddTime(Date addTime){
    this.addTime = addTime;
    }

}