package com.xindaibao.cashloan.system.domain;

import java.io.Serializable;

/**
 * 数据字典表
 * 
 * @author
 * @version 1.0
 * @since 2014-03-21
 */
public class SysDict implements Serializable {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键标示
	 */
	private Long id;

	/**
	 * 类型名称
	 */
	private String name;
	/**
	 * 类型代码
	 */
	private String code;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 备注
	 */
	private String remark;

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
	 * @param id
	 *            要设置的主键标示
	 */
	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取排序
	 * 
	 * @return 排序
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * 设置排序
	 * 
	 * @param sort
	 *            要设置的排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
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
	 * @param remark
	 *            要设置的备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
