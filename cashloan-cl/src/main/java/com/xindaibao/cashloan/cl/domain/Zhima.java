package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 芝麻信用实体
 */
 public class Zhima implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 是否已绑定
    */
    private String bind;

    /**
    * 绑定时间
    */
    private Date bindTime;

    /**
    * 客户表 外键
    */
    private Long userId;

    /**
    * 分数
    */
    private Double score;

    /**
     * 用户在芝麻信用上的唯一标识
     */
    private String openId;
    
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
    * 获取是否已绑定
    *
    * @return 是否已绑定
    */
    public String getBind(){
    return bind;
    }

    /**
    * 设置是否已绑定
    * 
    * @param bind 要设置的是否已绑定
    */
    public void setBind(String bind){
    this.bind = bind;
    }

    /**
    * 获取绑定时间
    *
    * @return 绑定时间
    */
    public Date getBindTime(){
    return bindTime;
    }

    /**
    * 设置绑定时间
    * 
    * @param bindTime 要设置的绑定时间
    */
    public void setBindTime(Date bindTime){
    this.bindTime = bindTime;
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
	 * 获取分数
	 * @return score
	 */
	public Double getScore() {
		return score;
	}

	/** 
	 * 设置分数
	 * @param score
	 */
	public void setScore(Double score) {
		this.score = score;
	}

	/**
	 * openId
	 *
	 * @return  the openId
	 * @since   1.0.0
	*/
	
	public String getOpenId() {
		return openId;
	}

	/**
	 * @param openId the openId to set
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}

    
}