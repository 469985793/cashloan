package com.xindaibao.cashloan.cl.model;

import java.util.Date;

public class ManageProfitModel {

	private long id;

	/**
	 * 
	 */
	private long userId;
	
	private long inviteId;
	
	private String userName;
	
	private String inviteName;
	
	/**
	 * 邀请时间 添加时间
	 */
	private Date addTime;

	/**
	 * 用户等级
	 */
	private String level;
	
	/**
	 * 分润率
	 */
	private String rate;

	/**
	 * 获取id
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * 设置id
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 获取
	 * @return userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * 设置
	 * @param userId
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * 获取inviteId
	 * @return inviteId
	 */
	public long getInviteId() {
		return inviteId;
	}

	/**
	 * 设置inviteId
	 * @param inviteId
	 */
	public void setInviteId(long inviteId) {
		this.inviteId = inviteId;
	}

	/**
	 * 获取userName
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置userName
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 获取inviteName
	 * @return inviteName
	 */
	public String getInviteName() {
		return inviteName;
	}

	/**
	 * 设置inviteName
	 * @param inviteName
	 */
	public void setInviteName(String inviteName) {
		this.inviteName = inviteName;
	}

	/**
	 * 获取邀请时间添加时间
	 * @return addTime
	 */
	public Date getAddTime() {
		return addTime;
	}

	/**
	 * 设置邀请时间添加时间
	 * @param addTime
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	/**
	 * 获取用户等级
	 * @return level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * 设置用户等级
	 * @param level
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * 获取分润率
	 * @return rate
	 */
	public String getRate() {
		return rate;
	}

	/**
	 * 设置分润率
	 * @param rate
	 */
	public void setRate(String rate) {
		this.rate = rate;
	}

	
}
