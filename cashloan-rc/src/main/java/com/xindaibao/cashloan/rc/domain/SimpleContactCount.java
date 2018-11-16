package com.xindaibao.cashloan.rc.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 风控数据统计-（简）通讯录统计实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-07-06 18:12:49



 */
 public class SimpleContactCount implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	private Long id;

	/**
	 * 用户标识
	 */
	private Long userId;
	
	/**
	 * 通讯录总条数
	 */
	private Integer countOne;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 获取主键Id
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置主键Id
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取用户标识
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置用户标识
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取通讯录总条数
	 * @return countOne
	 */
	public Integer getCountOne() {
		return countOne;
	}

	/**
	 * 设置通讯录总条数
	 * @param countOne
	 */
	public void setCountOne(Integer countOne) {
		this.countOne = countOne;
	}

	/**
	 * 获取创建时间
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}