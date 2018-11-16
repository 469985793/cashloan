package com.xindaibao.cashloan.system.domain;

import java.io.Serializable;

/**
 * 
 * 角色和菜单关联表
 * @version 1.0
 * @author
 * @created 2014年9月22日 下午4:41:05
 */
public class SysRoleMenu implements Serializable {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 角色
	 */
	private Long roleId;
	/**
	 * 菜单
	 */
	private Long menuId;

	/**
	 * 获取主键
	 * @return 主键
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设定主键
	 * @param id 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取角色
	 * @return 角色
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * 设定角色
	 * @param role 角色
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * 获取菜单
	 * @return 菜单
	 */
	public Long getMenuId() {
		return menuId;
	}
	
	/**
	 * 设定菜单
	 * @param menu 菜单
	 */
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
}
