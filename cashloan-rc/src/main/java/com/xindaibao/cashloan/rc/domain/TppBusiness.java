package com.xindaibao.cashloan.rc.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 第三方征信接口信息实体
 */

 public class TppBusiness implements Serializable {

    private static final long serialVersionUID = 1L;

    public String toString() {
        return "id: " + id
                + ", tppId:" + tppId
                + ", name:" + name
                + ", nid:" + nid
                + ", name:" + name
                + ", state:" + state
                + ", url:" + url
                + ", testUrl:" + testUrl
                + ", addTime:" + addTime
                + ", extend:" + extend;
    }

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 第三方主键
    */
    private Long tppId;

    /**
    * 接口名称
    */
    private String name;

    /**
    * 接口简称
    */
    private String nid;

    /**
    * 状态 10，启用；20，禁用
    */
    private String state;

    /**
     * 扩展字段
     */
    private String extend;
    
    /**
    * 接口请求地址
    */
    private String url;

    /**
    * 测试地址
    */
    private String testUrl;

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
	 * 获取第三方主键
	 * @return tppId
	 */
	public Long getTppId() {
		return tppId;
	}

	/** 
	 * 设置第三方主键
	 * @param tppId
	 */
	public void setTppId(Long tppId) {
		this.tppId = tppId;
	}

	/**
    * 获取接口名称
    *
    * @return 接口名称
    */
    public String getName(){
    return name;
    }

    /**
    * 设置接口名称
    * 
    * @param name 要设置的接口名称
    */
    public void setName(String name){
    this.name = name;
    }

    /**
    * 获取接口简称
    *
    * @return 接口简称
    */
    public String getNid(){
    return nid;
    }

    /**
    * 设置接口简称
    * 
    * @param nid 要设置的接口简称
    */
    public void setNid(String nid){
    this.nid = nid;
    }

    /**
    * 获取状态 10，启用；20，禁用
    *
    * @return 状态 10，启用；20，禁用
    */
    public String getState(){
    return state;
    }

    /**
    * 设置状态 10，启用；20，禁用
    * 
    * @param state 要设置的状态 10，启用；20，禁用
    */
    public void setState(String state){
    this.state = state;
    }

    /**
    * 获取接口请求地址
    *
    * @return 接口请求地址
    */
    public String getUrl(){
    return url;
    }

    /**
    * 设置接口请求地址
    * 
    * @param url 要设置的接口请求地址
    */
    public void setUrl(String url){
    this.url = url;
    }

    /**
    * 获取测试地址
    *
    * @return 测试地址
    */
    public String getTestUrl(){
    return testUrl;
    }

    /**
    * 设置测试地址
    * 
    * @param testUrl 要设置的测试地址
    */
    public void setTestUrl(String testUrl){
    this.testUrl = testUrl;
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
	 * 获取 扩展字段
	 * @return 
	 */
	public String getExtend() {
		return extend;
	}

	/**
	 * 设置 扩展字段
	 * @param 
	 */
	public void setExtend(String extend) {
		this.extend = extend;
	}

}