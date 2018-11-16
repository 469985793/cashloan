package com.xindaibao.creditrank.cr.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 评分卡等级详情表实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-06 11:27:25


 * 

 */
 public class RankDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 评分等级id
    */
    private Long rankId;

    /**
    * 评分等级
    */
    private String rank;

    /**
    * 起始信用额度
    */
    private BigDecimal amountMin;

    /**
    * 最高信用额度
    */
    private BigDecimal amountMax;

    /**
    * 最低分值
    */
    private Integer scoreMin;

    /**
    * 最高分值
    */
    private Integer scoreMax;

    /**
    * 是否可用 10是,20否
    */
    private String state;

    /**
    * 额度类别10 区间 20 固定值
    */
    private String rtype;

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
    * 获取评分等级id
    *
    * @return 评分等级id
    */
    public Long getRankId(){
    return rankId;
    }

    /**
    * 设置评分等级id
    * 
    * @param rankId 要设置的评分等级id
    */
    public void setRankId(Long rankId){
    this.rankId = rankId;
    }

    /**
    * 获取评分等级
    *
    * @return 评分等级
    */
    public String getRank(){
    return rank;
    }

    /**
    * 设置评分等级
    * 
    * @param rank 要设置的评分等级
    */
    public void setRank(String rank){
    this.rank = rank;
    }

    /**
    * 获取起始信用额度
    *
    * @return 起始信用额度
    */
    public BigDecimal getAmountMin(){
    return amountMin;
    }

    /**
    * 设置起始信用额度
    * 
    * @param amountMin 要设置的起始信用额度
    */
    public void setAmountMin(BigDecimal amountMin){
    this.amountMin = amountMin;
    }

    /**
    * 获取最高信用额度
    *
    * @return 最高信用额度
    */
    public BigDecimal getAmountMax(){
    return amountMax;
    }

    /**
    * 设置最高信用额度
    * 
    * @param amountMax 要设置的最高信用额度
    */
    public void setAmountMax(BigDecimal amountMax){
    this.amountMax = amountMax;
    }

    /**
    * 获取最低分值
    *
    * @return 最低分值
    */
    public Integer getScoreMin(){
    return scoreMin;
    }

    /**
    * 设置最低分值
    * 
    * @param scoreMin 要设置的最低分值
    */
    public void setScoreMin(Integer scoreMin){
    this.scoreMin = scoreMin;
    }

    /**
    * 获取最高分值
    *
    * @return 最高分值
    */
    public Integer getScoreMax(){
    return scoreMax;
    }

    /**
    * 设置最高分值
    * 
    * @param scoreMax 要设置的最高分值
    */
    public void setScoreMax(Integer scoreMax){
    this.scoreMax = scoreMax;
    }

    /**
    * 获取是否可用 10是,20否
    *
    * @return 是否可用 10是,20否
    */
    public String getState(){
    return state;
    }

    /**
    * 设置是否可用 10是,20否
    * 
    * @param state 要设置的是否可用 10是,20否
    */
    public void setState(String state){
    this.state = state;
    }

    /**
    * 获取额度类别10 区间 20 固定值
    *
    * @return 额度类别10 区间 20 固定值
    */
    public String getRtype(){
    return rtype;
    }

    /**
    * 设置额度类别10 区间 20 固定值
    * 
    * @param rtype 要设置的额度类别10 区间 20 固定值
    */
    public void setRtype(String rtype){
    this.rtype = rtype;
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