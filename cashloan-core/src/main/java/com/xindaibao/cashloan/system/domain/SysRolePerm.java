package com.xindaibao.cashloan.system.domain;

import java.util.Date;

/**
 *
 * @author
 * @version 1.0.0.0
 * @date 2016年12月02日 下午14:56:41


 * 

 */
public class SysRolePerm {
	/**
	 * 主键
	 */ 
	private Long id;

	/**
	 * 角色ID
	 */ 
	private Integer roleId;

	/**
	 * 权限ID
	 */ 
	private Integer permId;

	/**
	 * 添加时间
	 */ 
	private Date addTime;

	/**
	 * 添加人
	 */ 
	private String addUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPermId() {
		return permId;
	}

	public void setPermId(Integer permId) {
		this.permId = permId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getAddUser() {
		return addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}




}