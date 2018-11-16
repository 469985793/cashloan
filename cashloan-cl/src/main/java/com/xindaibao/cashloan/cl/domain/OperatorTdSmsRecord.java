package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;

/**
 * 短信记录具体记录实体
 */
 public class OperatorTdSmsRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 
    */
    private Long infoId;

    /**
    * 起始时间
    */
    private String msgStartTime;

    /**
    * 发送方式
    */
    private String msgType;

    /**
    * 对方号码
    */
    private String msgOtherNum;

    /**
    * 信息类型
    */
    private String msgChannel;

    /**
    * 业务类型
    */
    private String msgBizName;

    /**
    * 短信地区
    */
    private String msgAddress;

    /**
    * 通信费(分)
    */
    private String msgFee;

    /**
    * 减免(分)
    */
    private String msgDiscount;

    /**
    * 费用小计(分)
    */
    private String msgCost;

    /**
    * 备注(分)
    */
    private String msgRemark;


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
    * 获取
    *
    * @return 
    */
    public Long getInfoId(){
        return infoId;
    }

    /**
    * 设置
    * 
    * @param infoId 要设置的
    */
    public void setInfoId(Long infoId){
        this.infoId = infoId;
    }

    /**
    * 获取起始时间
    *
    * @return 起始时间
    */
    public String getMsgStartTime(){
        return msgStartTime;
    }

    /**
    * 设置起始时间
    * 
    * @param msgStartTime 要设置的起始时间
    */
    public void setMsgStartTime(String msgStartTime){
        this.msgStartTime = msgStartTime;
    }

    /**
    * 获取发送方式
    *
    * @return 发送方式
    */
    public String getMsgType(){
        return msgType;
    }

    /**
    * 设置发送方式
    * 
    * @param msgType 要设置的发送方式
    */
    public void setMsgType(String msgType){
        this.msgType = msgType;
    }

    /**
    * 获取对方号码
    *
    * @return 对方号码
    */
    public String getMsgOtherNum(){
        return msgOtherNum;
    }

    /**
    * 设置对方号码
    * 
    * @param msgOtherNum 要设置的对方号码
    */
    public void setMsgOtherNum(String msgOtherNum){
        this.msgOtherNum = msgOtherNum;
    }

    /**
    * 获取信息类型
    *
    * @return 信息类型
    */
    public String getMsgChannel(){
        return msgChannel;
    }

    /**
    * 设置信息类型
    * 
    * @param msgChannel 要设置的信息类型
    */
    public void setMsgChannel(String msgChannel){
        this.msgChannel = msgChannel;
    }

    /**
    * 获取业务类型
    *
    * @return 业务类型
    */
    public String getMsgBizName(){
        return msgBizName;
    }

    /**
    * 设置业务类型
    * 
    * @param msgBizName 要设置的业务类型
    */
    public void setMsgBizName(String msgBizName){
        this.msgBizName = msgBizName;
    }

    /**
    * 获取短信地区
    *
    * @return 短信地区
    */
    public String getMsgAddress(){
        return msgAddress;
    }

    /**
    * 设置短信地区
    * 
    * @param msgAddress 要设置的短信地区
    */
    public void setMsgAddress(String msgAddress){
        this.msgAddress = msgAddress;
    }

    /**
    * 获取通信费(分)
    *
    * @return 通信费(分)
    */
    public String getMsgFee(){
        return msgFee;
    }

    /**
    * 设置通信费(分)
    * 
    * @param msgFee 要设置的通信费(分)
    */
    public void setMsgFee(String msgFee){
        this.msgFee = msgFee;
    }

    /**
    * 获取减免(分)
    *
    * @return 减免(分)
    */
    public String getMsgDiscount(){
        return msgDiscount;
    }

    /**
    * 设置减免(分)
    * 
    * @param msgDiscount 要设置的减免(分)
    */
    public void setMsgDiscount(String msgDiscount){
        this.msgDiscount = msgDiscount;
    }

    /**
    * 获取费用小计(分)
    *
    * @return 费用小计(分)
    */
    public String getMsgCost(){
        return msgCost;
    }

    /**
    * 设置费用小计(分)
    * 
    * @param msgCost 要设置的费用小计(分)
    */
    public void setMsgCost(String msgCost){
        this.msgCost = msgCost;
    }

    /**
    * 获取备注(分)
    *
    * @return 备注(分)
    */
    public String getMsgRemark(){
        return msgRemark;
    }

    /**
    * 设置备注(分)
    * 
    * @param msgRemark 要设置的备注(分)
    */
    public void setMsgRemark(String msgRemark){
        this.msgRemark = msgRemark;
    }

}