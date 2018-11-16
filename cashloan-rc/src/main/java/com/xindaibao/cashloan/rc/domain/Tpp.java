package com.xindaibao.cashloan.rc.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 第三方征信信息实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-14 13:41:05




 */
 public class Tpp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 第三方名称
    */
    private String name;

    /**
    * 第三方简称
    */
    private String nid;

    /**
    * 商户号
    */
    private String merNo;

    /**
    * 加密方式
    */
    private String signType;

    /**
    * 加密所需要的key
    */
    private String key;

    /**
    * 扩展字段
    */
    private String extend;

    /**
    * 路径集合
    */
    private String url;

    /**
    * 测试地址集合
    */
    private String testUrl;

    /**
    * 状态 10，启用；20，禁用
    */
    private String state;

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
    * 获取第三方名称
    *
    * @return 第三方名称
    */
    public String getName(){
    return name;
    }

    /**
    * 设置第三方名称
    * 
    * @param name 要设置的第三方名称
    */
    public void setName(String name){
    this.name = name;
    }

    /**
    * 获取第三方简称
    *
    * @return 第三方简称
    */
    public String getNid(){
    return nid;
    }

    /**
    * 设置第三方简称
    * 
    * @param nid 要设置的第三方简称
    */
    public void setNid(String nid){
    this.nid = nid;
    }

    /**
    * 获取商户号
    *
    * @return 商户号
    */
    public String getMerNo(){
    return merNo;
    }

    /**
    * 设置商户号
    * 
    * @param merNo 要设置的商户号
    */
    public void setMerNo(String merNo){
    this.merNo = merNo;
    }

    /**
    * 获取加密方式
    *
    * @return 加密方式
    */
    public String getSignType(){
    return signType;
    }

    /**
    * 设置加密方式
    * 
    * @param signType 要设置的加密方式
    */
    public void setSignType(String signType){
    this.signType = signType;
    }

    /**
    * 获取加密所需要的key
    *
    * @return 加密所需要的key
    */
    public String getKey(){
    return key;
    }

    /**
    * 设置加密所需要的key
    * 
    * @param key 要设置的加密所需要的key
    */
    public void setKey(String key){
    this.key = key;
    }

    /**
    * 获取扩展字段
    *
    * @return 扩展字段
    */
    public String getExtend(){
    return extend;
    }

    /**
    * 设置扩展字段
    * 
    * @param extend 要设置的扩展字段
    */
    public void setExtend(String extend){
    this.extend = extend;
    }

    /**
    * 获取路径集合
    *
    * @return 路径集合
    */
    public String getUrl(){
    return url;
    }

    /**
    * 设置路径集合
    * 
    * @param url 要设置的路径集合
    */
    public void setUrl(String url){
    this.url = url;
    }

    /**
    * 获取测试地址集合
    *
    * @return 测试地址集合
    */
    public String getTestUrl(){
    return testUrl;
    }

    /**
    * 设置测试地址集合
    * 
    * @param testUrl 要设置的测试地址集合
    */
    public void setTestUrl(String testUrl){
    this.testUrl = testUrl;
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