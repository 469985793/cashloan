package com.xindaibao.cashloan.rule.domain;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 规则信息实体
 * 
 * @author
 * @version 1.0.0
 * @date 2016-12-20 13:58:48


 * 

 */
 public class RuleInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 表名称
    */
    private String tbNid;

    /**
    * 列名
    */
    private String tbName;

    /**
    * 对应的规则信息
    */
    private String detail;
    
    /**
    * 状态：10启用，20禁用
    */
    private String state;
    /**
     * 扩展字段
     */
     private String reqExt;


     /**
     * 添加时间
     */
     @JSONField (format="yyyy-MM-dd HH:mm:ss")
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
    * 获取表名称
    *
    * @return 表名称
    */
    public String getTbNid(){
    return tbNid;
    }

    /**
    * 设置表名称
    * 
    * @param tbNid 要设置的表名称
    */
    public void setTbNid(String tbNid){
    this.tbNid = tbNid;
    }

    /**
    * 获取列名
    *
    * @return 列名
    */
    public String getTbName(){
    return tbName;
    }

    /**
    * 设置列名
    * 
    * @param tbName 要设置的列名
    */
    public void setTbName(String tbName){
    this.tbName = tbName;
    }

    /**
    * 获取对应的规则信息
    *
    * @return 对应的规则信息
    */
    public String getDetail(){
    return detail;
    }

    /**
    * 设置对应的规则信息
    * 
    * @param detail 要设置的对应的规则信息
    */
    public void setDetail(String detail){
    this.detail = detail;
    }

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getReqExt() {
		return reqExt;
	}

	public void setReqExt(String reqExt) {
		this.reqExt = reqExt;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

 

}