package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;

/**
 * 用户联系人
 */
 public class UserContacts implements Serializable {

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
    * 姓名
    */
    private String name;

    /**
    * 手机号码
    */
    private String phone;

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
	 * 获取姓名
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置姓名
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取手机号码
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置手机号码
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

}