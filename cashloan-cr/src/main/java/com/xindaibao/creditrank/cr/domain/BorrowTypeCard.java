package com.xindaibao.creditrank.cr.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 评分卡类型绑定表实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-12 10:50:10


 * 

 */
 public class BorrowTypeCard implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 借款类型id
    */
    private Long borrowTypeId;

    /**
    * 借款类型名称
    */
    private String borrowTypeName;

    /**
    * 评分卡id
    */
    private Long cardId;

    /**
    * 评分卡名称
    */
    private String cardName;

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
    * 获取借款类型id
    *
    * @return 借款类型id
    */
    public Long getBorrowTypeId(){
    return borrowTypeId;
    }

    /**
    * 设置借款类型id
    * 
    * @param borrowtypeid 要设置的借款类型id
    */
    public void setBorrowTypeId(Long borrowTypeId){
    this.borrowTypeId = borrowTypeId;
    }

    /**
    * 获取借款类型名称
    *
    * @return 借款类型名称
    */
    public String getBorrowTypeName(){
    return borrowTypeName;
    }

    /**
    * 设置借款类型名称
    * 
    * @param borrowtypename 要设置的借款类型名称
    */
    public void setBorrowTypeName(String borrowTypeName){
    this.borrowTypeName = borrowTypeName;
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
    * @param cardid 要设置的评分卡id
    */
    public void setCardId(Long cardId){
    this.cardId = cardId;
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
    * @param cardname 要设置的评分卡名称
    */
    public void setCardName(String cardName){
    this.cardName = cardName;
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