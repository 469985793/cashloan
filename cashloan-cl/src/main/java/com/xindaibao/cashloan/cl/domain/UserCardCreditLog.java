package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 人脸识别请求记录实体
 */
 public class UserCardCreditLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户标识
    */
    private Long userId;

    /**
    * 请求参数
    */
    private String reqParams;

    /**
    * 响应参数
    */
    private String returnParams;

    /**
    * 人脸匹配值
    */
    private String confidence;

    /**
    * 结果
    */
    private String result;

    /**
    * 创建时间
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
    * 获取用户标识
    *
    * @return 用户标识
    */
    public Long getUserId(){
    return userId;
    }

    /**
    * 设置用户标识
    * 
    * @param userid 要设置的用户标识
    */
    public void setUserId(Long userId){
    this.userId = userId;
    }

    /**
    * 获取请求参数
    *
    * @return 请求参数
    */
    public String getReqParams(){
    return reqParams;
    }

    /**
    * 设置请求参数
    * 
    * @param reqParams 要设置的请求参数
    */
    public void setReqParams(String reqParams){
    this.reqParams = reqParams;
    }

    /**
    * 获取响应参数
    *
    * @return 响应参数
    */
    public String getReturnParams(){
    return returnParams;
    }

    /**
    * 设置响应参数
    * 
    * @param returnParams 要设置的响应参数
    */
    public void setReturnParams(String returnParams){
    this.returnParams = returnParams;
    }

    /**
    * 获取人脸匹配值
    *
    * @return 人脸匹配值
    */
    public String getConfidence(){
    return confidence;
    }

    /**
    * 设置人脸匹配值
    * 
    * @param confidence 要设置的人脸匹配值
    */
    public void setConfidence(String confidence){
    this.confidence = confidence;
    }

    /**
    * 获取结果
    *
    * @return 结果
    */
    public String getResult(){
    return result;
    }

    /**
    * 设置结果
    * 
    * @param result 要设置的结果
    */
    public void setResult(String result){
    this.result = result;
    }

    /**
    * 获取创建时间
    *
    * @return 创建时间
    */
    public Date getCreateTime(){
    return createTime;
    }

    /**
    * 设置创建时间
    * 
    * @param createTime 要设置的创建时间
    */
    public void setCreateTime(Date createTime){
    this.createTime = createTime;
    }

}