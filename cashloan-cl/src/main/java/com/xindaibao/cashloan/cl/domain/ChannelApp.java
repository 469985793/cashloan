package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;

/**
 * app渠道版本表实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-05-10 10:29:55




 */
 public class ChannelApp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 渠道id
    */
    private Long channelId;

    /**
    * 应用类型 10 android 20 ios
    */
    private String appType;

    /**
    * 最新版本
    */
    private String latestVersion;

    /**
    * 最低支持版本
    */
    private String minVersion;

    /**
    * 下载地址
    */
    private String downloadUrl;

    private String state;
    
    /**
    * 获取主键Id
    *
    * @return id
    */
    public Long getId(){
    return id;
    }


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
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
    * 获取渠道id
    *
    * @return 渠道id
    */
    public Long getChannelId(){
    return channelId;
    }

    /**
    * 设置渠道id
    * 
    * @param channelId 要设置的渠道id
    */
    public void setChannelId(Long channelId){
    this.channelId = channelId;
    }

    /**
    * 获取应用类型 10 android 20 ios
    *
    * @return 应用类型 10 android 20 ios
    */
    public String getAppType(){
    return appType;
    }

    /**
    * 设置应用类型 10 android 20 ios
    * 
    * @param appType 要设置的应用类型 10 android 20 ios
    */
    public void setAppType(String appType){
    this.appType = appType;
    }

    /**
    * 获取最新版本
    *
    * @return 最新版本
    */
    public String getlatestVersion(){
    return latestVersion;
    }

    /**
    * 设置最新版本
    * 
    * @param latest-version 要设置的最新版本
    */
    public void setlatestVersion(String latestVersion){
    this.latestVersion = latestVersion;
    }

    /**
    * 获取最低支持版本
    *
    * @return 最低支持版本
    */
    public String getMinVersion(){
    return minVersion;
    }

    /**
    * 设置最低支持版本
    * 
    * @param minVersion 要设置的最低支持版本
    */
    public void setMinVersion(String minVersion){
    this.minVersion = minVersion;
    }

    /**
    * 获取下载地址
    *
    * @return 下载地址
    */
    public String getDownloadUrl(){
    return downloadUrl;
    }

    /**
    * 设置下载地址
    * 
    * @param downloadurl 要设置的下载地址
    */
    public void setDownloadUrl(String downloadUrl){
    this.downloadUrl = downloadUrl;
    }

}