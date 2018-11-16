package com.xindaibao.cashloan.manage.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务记录实体
 */
 public class QuartzLog implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	private Long id;

	/**
	 * 定时任务id
	 */
	private Long quartzId;

	/**
	 * 启动时间
	 */
	private Date startTime;

	/**
	 * 任务用时
	 */
	private long time;

	/**
	 * 执行是否成功 10-成功 20-失败
	 */
	private String result;
	
	/**
	 * 备注信息
	 */
	private String remark;

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
	 * 获取定时任务id
	 *
	 * @return 定时任务id
	 */
	public Long getQuartzId() {
		return quartzId;
	}

	/**
	 * 设置定时任务id
	 * 
	 * @param quartzId
	 *            要设置的定时任务id
	 */
	public void setQuartzId(Long quartzId) {
		this.quartzId = quartzId;
	}

	/**
	 * 获取启动时间
	 *
	 * @return 启动时间
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * 设置启动时间
	 * 
	 * @param startTime
	 *            要设置的启动时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * 获取任务用时
	 *
	 * @return 任务用时
	 */
	public long getTime() {
		return time;
	}

	/**
	 * 设置任务用时
	 * 
	 * @param time
	 *            要设置的任务用时
	 */
	public void setTime(long time) {
		this.time = time;
	}

	/**
	 * 获取执行是否成功 10-成功 20-失败
	 *
	 * @return 执行是否成功 10-成功 20-失败
	 */
	public String getResult() {
		return result;
	}

	/**
	 * 设置执行是否成功 10-成功 20-失败
	 * 
	 * @param result
	 *            要设置的执行是否成功 10-成功 20-失败
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}