package com.xindaibao.cashloan.system.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 菜单表
 * @version 1.0
 * @author
 * @created 2014年9月22日 下午4:42:18
 */
public class SysMenu implements Serializable {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键标示
	 */
	private Long id;
	/**
	 * 菜单名称
	 */
	private String text;
	/**
	 * 父级ID
	 */
	private int parentId;
	/**
	 * 链接地址
	 */
	private String href;
	/**
	 * 图标
	 */
	private String iconCls;
	/**
	 * 排序
	 */
	private int sort;
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
	 * 是否删除：0不删除，1删除
	 */
	private byte isDelete;

	/**
	 * 是否菜单，0不是，1是
	 */
	private byte isMenu;
	
	/**
	 * 脚本名称
	 */
	private String scriptid;
	
	/**
	 * 是否为子节点  1 true 0 false
	 */
	private Boolean leaf;

	/**
	 * 构造方法
	 */
	
	private byte level;
	
	/**
	 * 前端控制器名称
	 */
	private String controllerName;
	
	public byte getLevel() {
		return level;
	}

	public void setLevel(byte level) {
		this.level = level;
	}

	public SysMenu() {
	}

	/**
	 * 构造方法
	 * 
	 * @param id 主键
	 */
	public SysMenu(Long id) {
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

	

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	
	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the href
	 */
	public String getHref() {
		return href;
	}

	/**
	 * @param href the href to set
	 */
	public void setHref(String href) {
		this.href = href;
	}

	/**
	 * @return the iconCls
	 */
	public String getIconCls() {
		return iconCls;
	}

	/**
	 * @param iconCls the iconCls to set
	 */
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	/**
	 * 获取排序
	 * 
	 * @return 排序
	 */
	public int getSort() {
		return sort;
	}

	/**
	 * 设置排序
	 * 
	 * @param sort 要设置的排序
	 */
	public void setSort(int sort) {
		this.sort = sort;
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
	 * 获取是否删除：0不删除，1删除
	 * 
	 * @return 是否删除：0不删除，1删除
	 */
	public byte getIsDelete() {
		return isDelete;
	}

	/**
	 * 设置是否删除：0不删除，1删除
	 * 
	 * @param isDelete 要设置的是否删除：0不删除，1删除
	 */
	public void setIsDelete(byte isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * 是否菜单
	 * @return 是否菜单
	 */
	public byte getIsMenu() {
		return isMenu;
	}

	/**
	 *  是否菜单
	 * @param isMenu 要设置的是否菜单
	 */
	public void setIsMenu(byte isMenu) {
		this.isMenu = isMenu;
	}

	public String getScriptid() {
		return scriptid;
	}

	public void setScriptid(String scriptid) {
		this.scriptid = scriptid;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public String getControllerName() {
		return controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}


	
	

}
