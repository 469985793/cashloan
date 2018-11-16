package com.xindaibao.cashloan.system.model;

import com.xindaibao.cashloan.system.domain.SysMenu;
/**
 * 
 * 系统菜单model
 * @version 1.0
 * @author
 * @created 2014年9月23日 上午11:50:09
 */
public class MenuModel extends SysMenu {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -616526029044963364L;
	
	/**
	 * 角色ID：security扩展类
	 */
	private Long roleId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
