package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 渠道信息实体
 */
 public class Channel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 渠道代码
    */
    private String code;

    /**
    * 渠道名称
    */
    private String name;

    /**
    *联系人
    */
    private String linker;

    /**
    * 联系电话
    */
    private String phone;

    /**
    * 渠道类型  备用
    */
    private String type;

    /**
    * 状态 10：启用20：禁用
    */
    private String state;

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
    * 获取渠道代码
    *
    * @return 渠道代码
    */
    public String getCode(){
    return code;
    }

    /**
    * 设置渠道代码
    * 
    * @param code 要设置的渠道代码
    */
    public void setCode(String code){
    this.code = code;
    }

    /**
    * 获取渠道名称
    *
    * @return 渠道名称
    */
    public String getName(){
    return name;
    }

    /**
    * 设置渠道名称
    * 
    * @param name 要设置的渠道名称
    */
    public void setName(String name){
    this.name = name;
    }

    
    public String getLinker() {
		return linker;
	}

	public void setLinker(String linker) {
		this.linker = linker;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
    * 获取渠道类型 10：Android 20：IOS
    *
    * @return 渠道类型 10：Android 20：IOS
    */
    public String getType(){
    return type;
    }

    /**
    * 设置渠道类型 10：Android 20：IOS
    * 
    * @param type 要设置的渠道类型 10：Android 20：IOS
    */
    public void setType(String type){
    this.type = type;
    }

    /**
    * 获取状态 10：启用20：禁用
    *
    * @return 状态 10：启用20：禁用
    */
    public String getState(){
    return state;
    }

    /**
    * 设置状态 10：启用20：禁用
    * 
    * @param state 要设置的状态 10：启用20：禁用
    */
    public void setState(String state){
    this.state = state;
    }

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

 

}