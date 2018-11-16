package com.xindaibao.creditrank.cr.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 评分卡实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-04 15:06:51


 * 

 */
 public class Card implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 评分卡启用和禁用
     * 10 启用  20 禁用
     */
    public static final String CARD_STATE_ENABLE = "10";
    public static final String CARD_STATE_DISABLE = "20";
    
    /**
     * 评分卡是否被使用
     * 10 已经被使用 20 未被使用
     */
    public static final String CARD_TYPE_USED = "10";
    public static final String CARD_TYPE_UNUSED = "20";

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 评分卡名称
    */
    private String cardName;

    /**
    * 评分卡总分
    */
    private Integer score;

    /**
    * 状态 10启用,20禁用
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
    * 评分卡是否使用10-已使用 20-未使用
    */
    private String type;


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
    * 获取评分卡名称
    *
    * @return 评分卡名称
    */
    public String getCardName(){
    return cardName;
    }

    /**
    * 设置评分卡名称
    * 
    * @param cardName 要设置的评分卡名称
    */
    public void setCardName(String cardName){
    this.cardName = cardName;
    }

    /**
    * 获取评分卡总分
    *
    * @return 评分卡总分
    */
    public Integer getScore(){
    return score;
    }

    /**
    * 设置评分卡总分
    * 
    * @param score 要设置的评分卡总分
    */
    public void setScore(Integer score){
    this.score = score;
    }

    /**
    * 获取状态 10启用,20禁用
    *
    * @return 状态 10启用,20禁用
    */
    public String getState(){
    return state;
    }

    /**
    * 设置状态 10启用,20禁用
    * 
    * @param state 要设置的状态 10启用,20禁用
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
    * 获取评分卡是否使用
    *
    * @return 评分卡是否使用
    */
    public String getType(){
    return type;
    }

    /**
    * 设置评分卡是否使用
    * 
    * @param type 要设置的评分卡是否使用
    */
    public void setType(String type){
    this.type = type;
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