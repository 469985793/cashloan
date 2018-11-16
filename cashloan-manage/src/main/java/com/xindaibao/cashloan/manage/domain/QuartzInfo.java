package com.xindaibao.cashloan.manage.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务详情实体
 */
 public class QuartzInfo implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	private Long id;

	/**
	 * 定时任务名称
	 */
	private String name;

	/**
	 * 定时任务标识
	 */
	private String code;

	/**
	 * 定时任务执行周期
	 */
	private String cycle;

	/**
	 * 定时任务执行类
	 */
	private String className;

	/**
	 * 成功执行次数
	 */
	private Integer succeed;

	/**
	 * 失败执行次数
	 */
	private Integer fail;

	/**
	 * 是否启用 10-启用 20-禁用
	 */
	private String state;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 获取主键Id
	 *
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置主键Id
	 * 
	 * @param 要设置的主键Id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取定时任务名称
	 *
	 * @return 定时任务名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置定时任务名称
	 * 
	 * @param name
	 *            要设置的定时任务名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取定时任务标识
	 * 
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置定时任务标识
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取定时任务执行周期
	 *
	 * @return 定时任务执行周期
	 */
	public String getCycle() {
		return cycle;
	}

	/**
	 * 设置定时任务执行周期
	 * 
	 * @param cycle
	 *            要设置的定时任务执行周期
	 */
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	/**
	 * 获取定时任务执行类
	 *
	 * @return 定时任务执行类
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * 设置定时任务执行类
	 * 
	 * @param className 要设置的定时任务执行类
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * 获取成功执行次数
	 *
	 * @return 成功执行次数
	 */
	public Integer getSucceed() {
		return succeed;
	}

	/**
	 * 设置成功执行次数
	 * 
	 * @param succeed
	 *            要设置的成功执行次数
	 */
	public void setSucceed(Integer succeed) {
		this.succeed = succeed;
	}

	/**
	 * 获取失败执行次数
	 *
	 * @return 失败执行次数
	 */
	public Integer getFail() {
		return fail;
	}

	/**
	 * 设置失败执行次数
	 * 
	 * @param fail
	 *            要设置的失败执行次数
	 */
	public void setFail(Integer fail) {
		this.fail = fail;
	}

	/**
	 * 获取是否启用 10-启用 20-禁用
	 *
	 * @return 是否启用 10-启用 20-禁用
	 */
	public String getState() {
		return state;
	}

	/**
	 * 设置是否启用 10-启用 20-禁用
	 * 
	 * @param state
	 *            要设置的是否启用 10-启用 20-禁用
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 获取创建时间
	 *
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 * 
	 * @param createTime
	 *            要设置的创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}