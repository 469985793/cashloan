package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 分润等级实体
 */
 public class ProfitLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 分润等级
    */
    private Integer level;

    /**
    * 分润率
    */
    private Double rate;

    /**
    * 添加时间
    */
    private Date addTime;

    /**
    * 备注信息
    */
    private String remark;


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
    * 获取分润等级
    *
    * @return 分润等级
    */
    public Integer getLevel(){
    return level;
    }

    /**
    * 设置分润等级
    * 
    * @param level 要设置的分润等级
    */
    public void setLevel(Integer level){
    this.level = level;
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

    /**
    * 获取备注信息
    *
    * @return 备注信息
    */
    public String getRemark(){
    return remark;
    }

    /**
    * 设置备注信息
    * 
    * @param remark 要设置的备注信息
    */
    public void setRemark(String remark){
    this.remark = remark;
    }

}