package com.xindaibao.cashloan.system.domain;

import org.springframework.format.annotation.DateTimeFormat;
import tool.util.DateUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * 访问码实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-24 17:37:49


 * 

 */
 public class SysAppVersion implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	private Long id;

	/**
	 * app编号
	 */
	private String appCode;

	/**
	 * APP名称
	 */
	private String appName;

	/**
	 * 类型： 10 Android; 11 Android Pad; 20 IOS; 21 IOS Pad;
	 */
	private Integer appType;

	/**
	 * 版本号
	 */
	private String versionCode;

	/**
	 * 版本名称
	 */
	private String versionName;

	/**
	 * 版本内容
	 */
	private String versionText;

	/**
	 * 是否强制更新： 0 不强制； 1 强制
	 */
	private Integer forceFlag;

	/**
	 * APP下载地址
	 */
	private String downUrl;

	/**
	 * Google play下载地址
	 */
	private String googleDownUrl;

	/**
	 * 推广主页
	 */
	private String spreadUrl;

	/**
	 * 发布人id
	 */
	private Long publishUid;

	/**
	 * 发布时间，设定什么时候发布
	 */
	private Date publishTime;

	/**
	 * APP分类：1：jumbopesa 2：pesapuls
	 */
	private Integer appClassification;

	/**
	 * 状态： -1删除， 1正常
	 */
	private Integer status;

	/**
	 * 创建时间
	 */
	private Date createdTime;

	/**
	 * 修改时间
	 */
	private Date updatedTime;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Integer getAppType() {
		return appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getVersionText() {
		return versionText;
	}

	public void setVersionText(String versionText) {
		this.versionText = versionText;
	}

	public Integer getForceFlag() {
		return forceFlag;
	}

	public void setForceFlag(Integer forceFlag) {
		this.forceFlag = forceFlag;
	}

	public String getDownUrl() {
		return downUrl;
	}

	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}

	public String getGoogleDownUrl() {
		return googleDownUrl;
	}

	public void setGoogleDownUrl(String googleDownUrl) {
		this.googleDownUrl = googleDownUrl;
	}

	public String getSpreadUrl() {
		return spreadUrl;
	}

	public void setSpreadUrl(String spreadUrl) {
		this.spreadUrl = spreadUrl;
	}

	public Long getPublishUid() {
		return publishUid;
	}

	public void setPublishUid(Long publishUid) {
		this.publishUid = publishUid;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getAppClassification() {
		return appClassification;
	}

	public void setAppClassification(Integer appClassification) {
		this.appClassification = appClassification;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
}