package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;

/**
 * 通话记录具体详细实体
 */
 public class OperatorTdCallRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 通话记录id
    */
    private Long infoId;

    /**
    * 起始时间
    */
    private String callStartTime;

    /**
    * 通话地点
    */
    private String callAddress;

    /**
    * 呼叫类型 主叫/被叫/呼转/未知
    */
    private String callTypeName;

    /**
    * 对方号码
    */
    private String callOtherNumber;

    /**
    * 通话时长
    */
    private String callTime;

    /**
    * 通话类型 本地/漫游
    */
    private String callLandType;

    /**
    * 本地费或漫游费(分) 
    */
    private String callRoamCost;

    /**
    * 长途费(分) 
    */
    private String callLongDistance;

    /**
    * 减免(分) 
    */
    private String callDiscount;

    /**
    * 费用小计(分) 
    */
    private String callCost;


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
    * 获取通话记录id
    *
    * @return 通话记录id
    */
    public Long getInfoId(){
        return infoId;
    }

    /**
    * 设置通话记录id
    * 
    * @param infoId 要设置的通话记录id
    */
    public void setInfoId(Long infoId){
        this.infoId = infoId;
    }

    /**
    * 获取起始时间
    *
    * @return 起始时间
    */
    public String getCallStartTime(){
        return callStartTime;
    }

    /**
    * 设置起始时间
    * 
    * @param callStartTime 要设置的起始时间
    */
    public void setCallStartTime(String callStartTime){
        this.callStartTime = callStartTime;
    }

    /**
    * 获取通话地点
    *
    * @return 通话地点
    */
    public String getCallAddress(){
        return callAddress;
    }

    /**
    * 设置通话地点
    * 
    * @param callAddress 要设置的通话地点
    */
    public void setCallAddress(String callAddress){
        this.callAddress = callAddress;
    }

    /**
    * 获取呼叫类型 主叫/被叫/呼转/未知
    *
    * @return 呼叫类型 主叫/被叫/呼转/未知
    */
    public String getCallTypeName(){
        return callTypeName;
    }

    /**
    * 设置呼叫类型 主叫/被叫/呼转/未知
    * 
    * @param callTypeName 要设置的呼叫类型 主叫/被叫/呼转/未知
    */
    public void setCallTypeName(String callTypeName){
        this.callTypeName = callTypeName;
    }

    /**
    * 获取对方号码
    *
    * @return 对方号码
    */
    public String getCallOtherNumber(){
        return callOtherNumber;
    }

    /**
    * 设置对方号码
    * 
    * @param callOtherNumber 要设置的对方号码
    */
    public void setCallOtherNumber(String callOtherNumber){
        this.callOtherNumber = callOtherNumber;
    }

    /**
    * 获取通话时长
    *
    * @return 通话时长
    */
    public String getCallTime(){
        return callTime;
    }

    /**
    * 设置通话时长
    * 
    * @param callTime 要设置的通话时长
    */
    public void setCallTime(String callTime){
        this.callTime = callTime;
    }

    /**
    * 获取通话类型 本地/漫游
    *
    * @return 通话类型 本地/漫游
    */
    public String getCallLandType(){
        return callLandType;
    }

    /**
    * 设置通话类型 本地/漫游
    * 
    * @param callLandType 要设置的通话类型 本地/漫游
    */
    public void setCallLandType(String callLandType){
        this.callLandType = callLandType;
    }

    /**
    * 获取本地费或漫游费(分) 
    *
    * @return 本地费或漫游费(分) 
    */
    public String getCallRoamCost(){
        return callRoamCost;
    }

    /**
    * 设置本地费或漫游费(分) 
    * 
    * @param callRoamCost 要设置的本地费或漫游费(分) 
    */
    public void setCallRoamCost(String callRoamCost){
        this.callRoamCost = callRoamCost;
    }

    /**
    * 获取长途费(分) 
    *
    * @return 长途费(分) 
    */
    public String getCallLongDistance(){
        return callLongDistance;
    }

    /**
    * 设置长途费(分) 
    * 
    * @param callLongDistance 要设置的长途费(分) 
    */
    public void setCallLongDistance(String callLongDistance){
        this.callLongDistance = callLongDistance;
    }

    /**
    * 获取减免(分) 
    *
    * @return 减免(分) 
    */
    public String getCallDiscount(){
        return callDiscount;
    }

    /**
    * 设置减免(分) 
    * 
    * @param callDiscount 要设置的减免(分) 
    */
    public void setCallDiscount(String callDiscount){
        this.callDiscount = callDiscount;
    }

    /**
    * 获取费用小计(分) 
    *
    * @return 费用小计(分) 
    */
    public String getCallCost(){
        return callCost;
    }

    /**
    * 设置费用小计(分) 
    * 
    * @param callCost 要设置的费用小计(分) 
    */
    public void setCallCost(String callCost){
        this.callCost = callCost;
    }

}