package com.xindaibao.creditrank.cr.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 评分关联表实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-04 15:05:09


 * 

 */
 public class Info implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 表名
    */
    private String tbNid;

    /**
    * 表名注释
    */
    private String tbName;

    /**
    * 规则信息
    */
    private String detail;

    /**
    * 添加时间
    */
    private Date addTime;
    
    /**
     * 状态
     */
    private String state;


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
    * 获取表名
    *
    * @return 表名
    */
    public String getTbNid(){
    return tbNid;
    }

    /**
    * 设置表名
    * 
    * @param tbNid 要设置的表名
    */
    public void setTbNid(String tbNid){
    this.tbNid = tbNid;
    }

    /**
    * 获取表名注释
    *
    * @return 表名注释
    */
    public String getTbName(){
    return tbName;
    }

    /**
    * 设置表名注释
    * 
    * @param tbName 要设置的表名注释
    */
    public void setTbName(String tbName){
    this.tbName = tbName;
    }

    /**
    * 获取规则信息
    *
    * @return 规则信息
    */
    public String getDetail(){
    return detail;
    }

    /**
    * 设置规则信息
    * 
    * @param detail 要设置的规则信息
    */
    public void setDetail(String detail){
    this.detail = detail;
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
	 * 获取state
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 * 设置state
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

}