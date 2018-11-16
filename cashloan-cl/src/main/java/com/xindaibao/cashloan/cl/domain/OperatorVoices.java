package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 运营商信息-通话记录实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-13 16:44:01


 * 

 */
 public class OperatorVoices implements Serializable {

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
    * 修改时间
    */
    private Date gmtModified;

    /**
    * 号码
    */
    private String phoneNum;

    /**
    * 通话地
    */
    private String voicePlace;

    /**
    * 创建时间
    */
    private Date gmtCreate;

    /**
    * 通话时长（单位为秒）
    */
    private Long voiceDuration;

    /**
    * 语音账单月份
    */
    private Date month;

    /**
    * 通话类型
    */
    private String voiceType;

    /**
    * 对方号码
    */
    private String voiceToNumber;

    /**
    * 时间
    */
    private Date voiceDate;

    /**
    * 通话状态
    */
    private String voiceStatus;

    /**
    * 业务编号
    */
    private String bizNo;
    
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
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置用户id
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
    * 获取修改时间
    *
    * @return 修改时间
    */
    public Date getGmtModified(){
    return gmtModified;
    }

    /**
    * 设置修改时间
    * 
    * @param gmtModified 要设置的修改时间
    */
    public void setGmtModified(Date gmtModified){
    this.gmtModified = gmtModified;
    }

    /**
    * 获取号码
    *
    * @return 号码
    */
    public String getPhoneNum(){
    return phoneNum;
    }

    /**
    * 设置号码
    * 
    * @param phoneNum 要设置的号码
    */
    public void setPhoneNum(String phoneNum){
    this.phoneNum = phoneNum;
    }

    /**
    * 获取通话地
    *
    * @return 通话地
    */
    public String getVoicePlace(){
    return voicePlace;
    }

    /**
    * 设置通话地
    * 
    * @param voicePlace 要设置的通话地
    */
    public void setVoicePlace(String voicePlace){
    this.voicePlace = voicePlace;
    }

    /**
    * 获取创建时间
    *
    * @return 创建时间
    */
    public Date getGmtCreate(){
    return gmtCreate;
    }

    /**
    * 设置创建时间
    * 
    * @param gmtCreate 要设置的创建时间
    */
    public void setGmtCreate(Date gmtCreate){
    this.gmtCreate = gmtCreate;
    }

    /**
    * 获取通话时长（单位为秒）
    *
    * @return 通话时长（单位为秒）
    */
    public Long getVoiceDuration(){
    return voiceDuration;
    }

    /**
    * 设置通话时长（单位为秒）
    * 
    * @param voiceDuration 要设置的通话时长（单位为秒）
    */
    public void setVoiceDuration(Long voiceDuration){
    this.voiceDuration = voiceDuration;
    }

    /**
    * 获取语音账单月份
    *
    * @return 语音账单月份
    */
    public Date getMonth(){
    return month;
    }

    /**
    * 设置语音账单月份
    * 
    * @param month 要设置的语音账单月份
    */
    public void setMonth(Date month){
    this.month = month;
    }

    /**
    * 获取通话类型
    *
    * @return 通话类型
    */
    public String getVoiceType(){
    return voiceType;
    }

    /**
    * 设置通话类型
    * 
    * @param voiceType 要设置的通话类型
    */
    public void setVoiceType(String voiceType){
    this.voiceType = voiceType;
    }

    /**
    * 获取对方号码
    *
    * @return 对方号码
    */
    public String getVoiceToNumber(){
    return voiceToNumber;
    }

    /**
    * 设置对方号码
    * 
    * @param voiceToNumber 要设置的对方号码
    */
    public void setVoiceToNumber(String voiceToNumber){
    this.voiceToNumber = voiceToNumber;
    }

    /**
    * 获取时间
    *
    * @return 时间
    */
    public Date getVoiceDate(){
    return voiceDate;
    }

    /**
    * 设置时间
    * 
    * @param voiceDate 要设置的时间
    */
    public void setVoiceDate(Date voiceDate){
    this.voiceDate = voiceDate;
    }

    /**
    * 获取通话状态
    *
    * @return 通话状态
    */
    public String getVoiceStatus(){
    return voiceStatus;
    }

    /**
    * 设置通话状态
    * 
    * @param voiceStatus 要设置的通话状态
    */
    public void setVoiceStatus(String voiceStatus){
    this.voiceStatus = voiceStatus;
    }

    /**
    * 获取业务编号
    *
    * @return 业务编号
    */
    public String getBizNo(){
    return bizNo;
    }

    /**
    * 设置业务编号
    * 
    * @param bizNo 要设置的业务编号
    */
    public void setBizNo(String bizNo){
    this.bizNo = bizNo;
    }

	/** 
	 * 获取添加时间
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/** 
	 * 设置添加时间
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}