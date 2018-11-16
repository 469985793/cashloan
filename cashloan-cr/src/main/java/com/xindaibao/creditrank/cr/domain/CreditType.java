package com.xindaibao.creditrank.cr.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 额度类型管理实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-18 16:43:13


 * 

 */
 public class CreditType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;
    
    /**
     * 额度类型ID关联字典详情表ID
     */
    private Long creditTypeId;
    
    /**
     * 额度类型名称
     */
    private String name;

    /**
    * 评分卡关联id
    */
    private String cardId;

    /**
    * 评分等级关联id
    */
    private String rankId;

    /**
    * 借款类型关联id,多个值以字符串保存
    */
    private String borrowTypeId;

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
    * 获取评分卡关联id
    *
    * @return 评分卡关联id
    */
    public String getCardId(){
    return cardId;
    }

    /**
    * 设置评分卡关联id
    * 
    * @param cardId 要设置的评分卡关联id
    */
    public void setCardId(String cardId){
    this.cardId = cardId;
    }

    /**
    * 获取评分等级关联id
    *
    * @return 评分等级关联id
    */
    public String getRankId(){
    return rankId;
    }

    /**
    * 设置评分等级关联id
    * 
    * @param rankId 要设置的评分等级关联id
    */
    public void setRankId(String rankId){
    this.rankId = rankId;
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
	 * 获取额度类型名称
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/** 
	 * 设置额度类型名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** 
	 * 获取额度类型ID关联字典详情表ID
	 * @return creditTypeId
	 */
	public Long getCreditTypeId() {
		return creditTypeId;
	}

	/** 
	 * 设置额度类型ID关联字典详情表ID
	 * @param creditTypeId
	 */
	public void setCreditTypeId(Long creditTypeId) {
		this.creditTypeId = creditTypeId;
	}

	/** 
	 * 获取借款类型关联id多个值以字符串保存
	 * @return borrowTypeId
	 */
	public String getBorrowTypeId() {
		return borrowTypeId;
	}

	/** 
	 * 设置借款类型关联id多个值以字符串保存
	 * @param borrowTypeId
	 */
	public void setBorrowTypeId(String borrowTypeId) {
		this.borrowTypeId = borrowTypeId;
	}

}