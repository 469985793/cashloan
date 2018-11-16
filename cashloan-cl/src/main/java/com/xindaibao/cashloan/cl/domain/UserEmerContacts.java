package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;

/**
 * 紧急联系人表实体
 */
 public class UserEmerContacts implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 联系人
    */
    private String name;

    /**
    * 联系号码
    */
    private String phone;

    /**
    * 客户表 外键
    */
    private Long userId;

    /**
    * 与本人关系
    */
    private String relation;

    /**
    * 是否直系
    */
    private String type;


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
    * 获取联系人
    *
    * @return 联系人
    */
    public String getName(){
    return name;
    }

    /**
    * 设置联系人
    * 
    * @param name 要设置的联系人
    */
    public void setName(String name){
    this.name = name;
    }

    /**
    * 获取联系号码
    *
    * @return 联系号码
    */
    public String getPhone(){
    return phone;
    }

    /**
    * 设置联系号码
    * 
    * @param phone 要设置的联系号码
    */
    public void setPhone(String phone){
    this.phone = phone;
    }

    /**
    * 获取客户表 外键
    *
    * @return 客户表 外键
    */
    public Long getUserId(){
    return userId;
    }

    /**
    * 设置客户表 外键
    * 
    * @param userId 要设置的客户表 外键
    */
    public void setUserId(Long userId){
    this.userId = userId;
    }

    /**
    * 获取与本人关系
    *
    * @return 与本人关系
    */
    public String getRelation(){
    return relation;
    }

    /**
    * 设置与本人关系
    * 
    * @param relation 要设置的与本人关系
    */
    public void setRelation(String relation){
    this.relation = relation;
    }

    /**
    * 获取是否直系
    *
    * @return 是否直系
    */
    public String getType(){
    return type;
    }

    /**
    * 设置是否直系
    * 
    * @param type 要设置的是否直系
    */
    public void setType(String type){
    this.type = type;
    }

}