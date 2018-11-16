package com.xindaibao.cashloan.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户更多信息实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-14 19:37:25


 * 

 */
 public class UserOtherInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户标识(关联客户主键)
    */
    private Long userId;

    /**
    * 淘宝账号
    */
    private String taobao;

    /**
    * 常用邮箱
    */
    private String email;

    /**
    * qq账号
    */
    private String qq;

    /**
    * 微信账号
    */
    private String wechat;

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
    * 获取用户标识(关联客户主键)
    *
    * @return 用户标识(关联客户主键)
    */
    public Long getUserId(){
    return userId;
    }

    /**
    * 设置用户标识(关联客户主键)
    * 
    * @param userId 要设置的用户标识(关联客户主键)
    */
    public void setUserId(Long userId){
    this.userId = userId;
    }

    /**
    * 获取淘宝账号
    *
    * @return 淘宝账号
    */
    public String getTaobao(){
    return taobao;
    }

    /**
    * 设置淘宝账号
    * 
    * @param taobao 要设置的淘宝账号
    */
    public void setTaobao(String taobao){
    this.taobao = taobao;
    }

    /**
    * 获取常用邮箱
    *
    * @return 常用邮箱
    */
    public String getEmail(){
    return email;
    }

    /**
    * 设置常用邮箱
    * 
    * @param email 要设置的常用邮箱
    */
    public void setEmail(String email){
    this.email = email;
    }

    /**
    * 获取qq账号
    *
    * @return qq账号
    */
    public String getQq(){
    return qq;
    }

    /**
    * 设置qq账号
    * 
    * @param qq 要设置的qq账号
    */
    public void setQq(String qq){
    this.qq = qq;
    }

    /**
    * 获取微信账号
    *
    * @return 微信账号
    */
    public String getWechat(){
    return wechat;
    }

    /**
    * 设置微信账号
    * 
    * @param wechat 要设置的微信账号
    */
    public void setWechat(String wechat){
    this.wechat = wechat;
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