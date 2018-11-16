/**
 *
 *
 * @author
 * @version 1.0.0.0
 * @date 2016年12月01日 下午16:01:55


 * 

 */
package com.xindaibao.cashloan.system.domain;

import java.util.Date;

public class SysPerm {
	/**
	 * sys_perm.id
	 * 主键
	 */ 
	private Long id;

	/**
	 * sys_perm.code
	 * 权限代码 权限唯一性标识, 用":"分割路径
	 */ 
	private String code;

	/**
	 * sys_perm.name
	 * 权限名称
	 */ 
	private String name;

	/**
	 * sys_perm.perm_level
	 * 权限级别 1-系统级别 2-普通级别
	 */ 
	private Integer permLevel;

	/**
	 * sys_perm.remark
	 * 权限备注
	 */ 
	private String remark;

	/**
	 * sys_perm.add_time
	 * 添加时间
	 */ 
	private Date addTime;

	/**
	 * sys_perm.add_user
	 * 添加人
	 */ 
	private String addUser;

	/**
	 * sys_perm.menu_id
	 * 菜单ID
	 */ 
	private Long menuId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPermLevel() {
		return permLevel;
	}

	public void setPermLevel(Integer permLevel) {
		this.permLevel = permLevel;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}


}