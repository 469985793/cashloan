package com.xindaibao.cashloan.system.model;

import java.io.Serializable;

import com.xindaibao.cashloan.system.domain.SysMenu;
/**
 *  
 * @author
 *
 */

public class TreeSysMenu extends SysMenu implements Serializable {

	
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
	 * 图标
	 */
	private String iconCls;

	/**
	 * 加载脚本的id
	 */

	private String scriptId;
	
	
	private  boolean expanded;


	public Long getId() {
		return id;
	}


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


	public String getIconCls() {
		return iconCls;
	}


	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}


	public String getScriptId() {
		return scriptId;
	}


	public void setScriptId(String scriptId) {
		this.scriptId = scriptId;
	}


	public boolean isExpanded() {
		return expanded;
	}


	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}


	public TreeSysMenu() {
		super();
	}
    
}
