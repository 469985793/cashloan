package com.xindaibao.creditrank.cr.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 评分结果实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-05 16:22:54


 * 

 */
 public class CrResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户标识
    */
    private String consumerNo;
    
    /**
     * 额度类型ID
     */
    private Long creditTypeId;

    /**
    * 总得分
    */
    private Integer totalScore;

    /**
    * 总额度
    */
    private BigDecimal totalAmount;

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
    * 获取总得分
    *
    * @return 总得分
    */
    public Integer getTotalScore(){
    	return totalScore;
    }

    /**
    * 设置总得分
    * 
    * @param totalScore 要设置的总得分
    */
    public void setTotalScore(Integer totalScore){
    	this.totalScore = totalScore;
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

	/** 
	 * 获取额度类型ID
	 * @return creditTypeId
	 */
	public Long getCreditTypeId() {
		return creditTypeId;
	}

	/** 
	 * 设置额度类型ID
	 * @param creditTypeId
	 */
	public void setCreditTypeId(Long creditTypeId) {
		this.creditTypeId = creditTypeId;
	}

	/** 
	 * 获取总额度
	 * @return totalAmount
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	/** 
	 * 设置总额度
	 * @param totalAmount
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	/** 
	 * 获取用户标识
	 * @return consumerNo
	 */
	public String getConsumerNo() {
		return consumerNo;
	}

	/** 
	 * 设置用户标识
	 * @param consumerNo
	 */
	public void setConsumerNo(String consumerNo) {
		this.consumerNo = consumerNo;
	}

    

}
