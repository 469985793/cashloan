package com.xindaibao.creditrank.cr.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 评分项目实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-04 15:09:22


 * 

 */
 public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 评分卡id
    */
    private Long cardId;


    /**
    * 评分项目名称
    */
    private String itemName;

    /**
    * 项目分值
    */
    private Integer score;

    /**
    * 状态 10启用 20禁用
    */
    private String state;

    /**
    * 添加时间
    */
    private Date addTime;
    
    /**
     * 唯一标识
     */
    private String nid;

    /**
    * 扩展字段
    */
    private String reqExt;


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
    * 获取评分卡id
    *
    * @return 评分卡id
    */
    public Long getCardId(){
    return cardId;
    }

    /**
    * 设置评分卡id
    * 
    * @param cardId 要设置的评分卡id
    */
    public void setCardId(Long cardId){
    this.cardId = cardId;
    }


    /**
    * 获取评分项目名称
    *
    * @return 评分项目名称
    */
    public String getItemName(){
    return itemName;
    }

    /**
    * 设置评分项目名称
    * 
    * @param itemName 要设置的评分项目名称
    */
    public void setItemName(String itemName){
    this.itemName = itemName;
    }

    /**
    * 获取项目分值
    *
    * @return 项目分值
    */
    public Integer getScore(){
    return score;
    }

    /**
    * 设置项目分值
    * 
    * @param score 要设置的项目分值
    */
    public void setScore(Integer score){
    this.score = score;
    }

    /**
    * 获取状态 10启用 20禁用
    *
    * @return 状态 10启用 20禁用
    */
    public String getState(){
    return state;
    }

    /**
    * 设置状态 10启用 20禁用
    * 
    * @param state 要设置的状态 10启用 20禁用
    */
    public void setState(String state){
    this.state = state;
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
    * 获取扩展字段
    *
    * @return 扩展字段
    */
    public String getReqExt(){
    return reqExt;
    }

    /**
    * 设置扩展字段
    * 
    * @param reqExt 要设置的扩展字段
    */
    public void setReqExt(String reqExt){
    this.reqExt = reqExt;
    }

	/**
	 * 获取nid
	 * @return nid
	 */
	public String getNid() {
		return nid;
	}

	/**
	 * 设置nid
	 * @param nid
	 */
	public void setNid(String nid) {
		this.nid = nid;
	}

}