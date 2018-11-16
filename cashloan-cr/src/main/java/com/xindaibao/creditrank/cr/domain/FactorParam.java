package com.xindaibao.creditrank.cr.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 评分因子参数实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-05 11:13:30


 * 

 */
 public class FactorParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 评分因子id
    */
    private Long factorId;

    /**
    * 分值
    */
    private Integer paramScore;

    /**
    * 公式符号<=>
    */
    private String formula;

    /**
    * 值
    */
    private String cvalue;

    /**
    * 添加时间
    */
    private Date addTime;

    /**
    * 状态 10启用 20禁用
    */
    private String state;

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
    * 获取评分因子id
    *
    * @return 评分因子id
    */
    public Long getFactorId(){
    return factorId;
    }

    /**
    * 设置评分因子id
    * 
    * @param factorId 要设置的评分因子id
    */
    public void setFactorId(Long factorId){
    this.factorId = factorId;
    }


    /**
    * 获取分值
    *
    * @return 分值
    */
    public Integer getParamScore(){
    return paramScore;
    }

    /**
    * 设置分值
    * 
    * @param paramScore 要设置的分值
    */
    public void setParamScore(Integer paramScore){
    this.paramScore = paramScore;
    }

    /**
    * 获取公式符号<=>
    *
    * @return 公式符号<=>
    */
    public String getFormula(){
    return formula;
    }

    /**
    * 设置公式符号<=>
    * 
    * @param formula 要设置的公式符号<=>
    */
    public void setFormula(String formula){
    this.formula = formula;
    }

    /**
    * 获取值
    *
    * @return 值
    */
    public String getCvalue(){
    return cvalue;
    }

    /**
    * 设置值
    * 
    * @param cvalue 要设置的值
    */
    public void setCvalue(String cvalue){
    this.cvalue = cvalue;
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