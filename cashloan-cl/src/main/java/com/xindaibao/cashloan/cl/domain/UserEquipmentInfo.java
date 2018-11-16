package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;

/**
 * 用户设备信息表实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-04-17 17:43:07




 */
 public class UserEquipmentInfo implements Serializable {

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
    * 设备指纹
    */
    private String blackBox;

    /**
    * 操作系统
    */
    private String operatingSystem;

    /**
    * 系统版本
    */
    private String systemVersions;

    /**
    * 手机型号
    */
    private String phoneType;

    /**
    * 手机品牌
    */
    private String phoneBrand;

    /**
    * 手机设备标识
    */
    private String phoneMark;

    /**
    * mac
    */
    private String mac;

    /**
    * 应用版本号
    */
    private String versionName;

    /**
    * 应用build号
    */
    private String versionCode;


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
    * 获取设备指纹
    *
    * @return 设备指纹
    */
    public String getBlackBox(){
    return blackBox;
    }

    /**
    * 设置设备指纹
    * 
    * @param blackBox 要设置的设备指纹
    */
    public void setBlackBox(String blackBox){
    this.blackBox = blackBox;
    }

    /**
    * 获取操作系统
    *
    * @return 操作系统
    */
    public String getOperatingSystem(){
    return operatingSystem;
    }

    /**
    * 设置操作系统
    * 
    * @param operatingSystem 要设置的操作系统
    */
    public void setOperatingSystem(String operatingSystem){
    this.operatingSystem = operatingSystem;
    }

    /**
    * 获取系统版本
    *
    * @return 系统版本
    */
    public String getSystemVersions(){
    return systemVersions;
    }

    /**
    * 设置系统版本
    * 
    * @param systemVersions 要设置的系统版本
    */
    public void setSystemVersions(String systemVersions){
    this.systemVersions = systemVersions;
    }

    /**
    * 获取手机型号
    *
    * @return 手机型号
    */
    public String getPhoneType(){
    return phoneType;
    }

    /**
    * 设置手机型号
    * 
    * @param phoneType 要设置的手机型号
    */
    public void setPhoneType(String phoneType){
    this.phoneType = phoneType;
    }

    /**
    * 获取手机品牌
    *
    * @return 手机品牌
    */
    public String getPhoneBrand(){
    return phoneBrand;
    }

    /**
    * 设置手机品牌
    * 
    * @param phoneBrand 要设置的手机品牌
    */
    public void setPhoneBrand(String phoneBrand){
    this.phoneBrand = phoneBrand;
    }

    /**
    * 获取手机设备标识
    *
    * @return 手机设备标识
    */
    public String getPhoneMark(){
    return phoneMark;
    }

    /**
    * 设置手机设备标识
    * 
    * @param phoneMark 要设置的手机设备标识
    */
    public void setPhoneMark(String phoneMark){
    this.phoneMark = phoneMark;
    }

    /**
    * 获取mac
    *
    * @return mac
    */
    public String getMac(){
    return mac;
    }

    /**
    * 设置mac
    * 
    * @param mac 要设置的mac
    */
    public void setMac(String mac){
    this.mac = mac;
    }

    /**
    * 获取应用版本号
    *
    * @return 应用版本号
    */
    public String getVersionName(){
    return versionName;
    }

    /**
    * 设置应用版本号
    * 
    * @param versionName 要设置的应用版本号
    */
    public void setVersionName(String versionName){
    this.versionName = versionName;
    }

    /**
    * 获取应用build号
    *
    * @return 应用build号
    */
    public String getVersionCode(){
    return versionCode;
    }

    /**
    * 设置应用build号
    * 
    * @param versionCode 要设置的应用build号
    */
    public void setVersionCode(String versionCode){
    this.versionCode = versionCode;
    }

}