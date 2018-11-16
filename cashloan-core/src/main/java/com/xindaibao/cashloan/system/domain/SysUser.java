package com.xindaibao.cashloan.system.domain;


import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 系统用户表
 * @version 1.0
 * @author
 * @created 2014年9月22日 下午4:39:52
 */
public class SysUser implements Serializable {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键标示
	 */
	private Long id;

	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 用户登陆名
	 */
	private String userName;
	/**
	 * 登陆密码
	 */
	private String password;
	/**
	 * 工号
	 */
	private String jobNumber;
	/**
	 * 所属部门
	 */
	private String officeId;
	/**
	 * 所属公司
	 */
	private String companyId;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 手机
	 */
	private String mobile;
	/**
	 * 状态：0正常
	 */
	private Byte status;
	/**
	 * 最后登陆IP
	 */
	private String loginIp;
	/**
	 * 最后登陆时间
	 */
	private Date loginTime;
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
	 * 职务
	 */
	private Byte position;
	/**
	 * 管辖机构
	 */
	private String officeOver;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getJobNumber() {
		return jobNumber;
	}
	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public String getAddUser() {
		return addUser;
	}
	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Byte getPosition() {
		return position;
	}
	public void setPosition(Byte position) {
		this.position = position;
	}
	public String getOfficeOver() {
		return officeOver;
	}
	public void setOfficeOver(String officeOver) {
		this.officeOver = officeOver;
	}
	
}
