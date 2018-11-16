package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * sdk识别记录表实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-04-20 09:47:04




 */
 public class UserSdkLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 识别类型
    */
    private String timeType;

    /**
    * 添加时间
    */
    private Date createTime;


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
    * 获取用户id
    *
    * @return 用户id
    */
    public Long getUserId(){
    return userId;
    }

    /**
    * 设置用户id
    * 
    * @param userId 要设置的用户id
    */
    public void setUserId(Long userId){
    this.userId = userId;
    }

    /**
    * 获取识别类型
    *
    * @return 识别类型
    */
    public String getTimeType(){
    return timeType;
    }

    /**
    * 设置识别类型
    * 
    * @param timeType 要设置的识别类型
    */
    public void setTimeType(String timeType){
    this.timeType = timeType;
    }

    /**
    * 获取添加时间
    *
    * @return 添加时间
    */
    public Date getCreateTime(){
    return createTime;
    }

    /**
    * 设置添加时间
    * 
    * @param createTime 要设置的添加时间
    */
    public void setCreateTime(Date createTime){
    this.createTime = createTime;
    }

}