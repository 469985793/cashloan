package com.xindaibao.cashloan.system.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * 角色表
 * @version 1.0
 * @author
 * @created 2014年9月22日 下午4:41:32
 */
public class SysRole implements Serializable {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键标示
	 */
	private Long id;
	/**
	 * 角色名
	 */
	private String name;
	/**
	 * 添加时间
	 */
	private Date addTime;
	/**
	 * 添加者
	 */
	private String addUser;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 修改者
	 */
	private String updateUser;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否删除：0删除，1不删除
	 */
	private Byte isDelete;

	/**
	 * 唯一标示
	 */
	private String nid;

	/**
	 * 获取权限
	 */
	private List<SysRoleMenu> roleMenus;

	private List<SysUserRole> operatorRoles;

	public SysRole() {
		super();
	}

	public SysRole(Long id) {
		this.id = id;
	}

	/**
	 * 获取主键标示
	 * 
	 * @return 主键标示
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置主键标示
	 * 
	 * @param id 要设置的主键标示
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取角色名
	 * 
	 * @return 角色名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置角色名
	 * 
	 * @param name 要设置的角色名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取添加时间
	 * 
	 * @return 添加时间
	 */
	public Date getAddTime() {
		return addTime;
	}

	/**
	 * 设置添加时间
	 * 
	 * @param addTime 要设置的添加时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	/**
	 * 获取添加者
	 * 
	 * @return 添加者
	 */
	public String getAddUser() {
		return addUser;
	}

	/**
	 * 设置添加者
	 * 
	 * @param addUser 要设置的添加者
	 */
	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	/**
	 * 获取修改时间
	 * 
	 * @return 修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置修改时间
	 * 
	 * @param updateTime 要设置的修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取修改者
	 * 
	 * @return 修改者
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * 设置修改者
	 * 
	 * @param updateUser 要设置的修改者
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * 获取备注
	 * 
	 * @return 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注
	 * 
	 * @param remark 要设置的备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取是否删除：0删除，1不删除
	 * 
	 * @return 是否删除：0删除，1不删除
	 */
	public Byte getIsDelete() {
		return isDelete;
	}

	/**
	 * 设置是否删除：0删除，1不删除
	 * 
	 * @param isDelete 要设置的是否删除：0删除，1不删除
	 */
	public void setIsDelete(Byte isDelete) {
		this.isDelete = isDelete;
	}

	public List<SysRoleMenu> getRoleMenus() {
		return roleMenus;
	}

	public void setRoleMenus(List<SysRoleMenu> roleMenus) {
		this.roleMenus = roleMenus;
	}

	public List<SysUserRole> getOperatorRoles() {
		return operatorRoles;
	}

	public void setOperatorRoles(List<SysUserRole> operatorRoles) {
		this.operatorRoles = operatorRoles;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}
}
