package com.xindaibao.cashloan.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户详细信息实体
 *
 * @version 1.0.0
 * @date 2017-02-21 13:44:30
 */
 public class UserBaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	private Long id;

	/**
	 * 客户表 外键
	 */
	private Long userId;

	/**
	 * 手机号码
	 */
	private String phone;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 年龄
	 */
	private Integer age;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 民族
	 */
	private String national;

	/**
	 * 证件号码
	 */
	private String idNo;

	/**
	 * 身份证地址
	 */
	private String idAddr;

	/**
	 * 自拍(人脸识别照片)
	 */
	private String livingImg;

	/**
	 * 身份证头像
	 */
	private String ocrImg;

	/**
	 * 身份证正面
	 */
	private String frontImg;

	/**
	 * 身份证反面
	 */
	private String backImg;

	/**
	 * 学历
	 */
	private String education;

	/**
	 * 婚姻状况
	 */
	private String marryState;

	/**
	 * 公司名称
	 */
	private String companyName;

	/**
	 * 公司电话
	 */
	private String companyPhone;

	/**
	 * 公司地址
	 */
	private String companyAddr;

	/**
	 * 公司详细地址
	 */
	private String companyDetailAddr;

	/**
	 * 公司坐标(经度,纬度)
	 */
	private String companyCoordinate;

	/**
	 * 月薪范围
	 */
	private String salary;

	/**
	 * 工作年限
	 */
	private String workingYears;

	/**
	 * 工作照片
	 */
	private String workingImg;

	/**
	 * 居住时长
	 */
	private String liveTime;

	/**
	 * 居住地址
	 */
	private String liveAddr;

	/**
	 * 居住详细地址
	 */
	private String liveDetailAddr;

	/**
	 * 居住地坐标，(经度,纬度)
	 */
	private String liveCoordinate;

	/**
	 * 运营商服务密码
	 */
	private String phoneServerPwd;

	/**
	 * 注册地址
	 */
	private String registerAddr;

	/**
	 * 注册地坐标，(经度,纬度)
	 */
	private String registerCoordinate;

	/**
	 * 是否黑名单 ，10是20不是
	 */
	private String state;

	/**
	 * 拉黑原因
	 */
	private String blackReason;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 创建时间
	 */
	private Date createTime;

    /**
     * 身份证省
     */
    private String idProvince;

    /**
     * 身份证城市
     */
    private String idCity;

    /**
     * 身份证 县 区
     */
    private String idCounty;

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
	 * 获取客户表 外键
	 *
	 * @return 客户表 外键
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置客户表 外键
	 * 
	 * @param userId
	 *            要设置的客户表 外键
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取手机号码
	 *
	 * @return 手机号码
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置手机号码
	 * 
	 * @param phone
	 *            要设置的手机号码
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取真实姓名
	 *
	 * @return 真实姓名
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * 设置真实姓名
	 * 
	 * @param realName
	 *            要设置的真实姓名
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * 获取年龄
	 *
	 * @return 年龄
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * 设置年龄
	 * 
	 * @param age
	 *            要设置的年龄
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * 获取性别
	 *
	 * @return 性别
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 设置性别
	 * 
	 * @param sex
	 *            要设置的性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 获取民族
	 *
	 * @return 民族
	 */
	public String getNational() {
		return national;
	}

	/**
	 * 设置民族
	 * 
	 * @param national
	 *            要设置的民族
	 */
	public void setNational(String national) {
		this.national = national;
	}

	/**
	 * 获取证件号码
	 *
	 * @return 证件号码
	 */
	public String getIdNo() {
		return idNo;
	}

	/**
	 * 设置证件号码
	 * 
	 * @param idNo
	 *            要设置的证件号码
	 */
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	/**
	 * 获取身份证地址
	 *
	 * @return 身份证地址
	 */
	public String getIdAddr() {
		return idAddr;
	}

	/**
	 * 设置身份证地址
	 * 
	 * @param idAddr
	 *            要设置的身份证地址
	 */
	public void setIdAddr(String idAddr) {
		this.idAddr = idAddr;
	}

	/**
	 * 获取自拍(人脸识别照片)
	 *
	 * @return 自拍(人脸识别照片)
	 */
	public String getLivingImg() {
		return livingImg;
	}

	/**
	 * 设置自拍(人脸识别照片)
	 * 
	 * @param livingImg
	 *            要设置的自拍(人脸识别照片)
	 */
	public void setLivingImg(String livingImg) {
		this.livingImg = livingImg;
	}

	/**
	 * 获取身份证头像
	 *
	 * @return 身份证头像
	 */
	public String getOcrImg() {
		return ocrImg;
	}

	/**
	 * 设置身份证头像
	 * 
	 * @param ocrImg
	 *            要设置的身份证头像
	 */
	public void setOcrImg(String ocrImg) {
		this.ocrImg = ocrImg;
	}

	/**
	 * 获取身份证正面
	 *
	 * @return 身份证正面
	 */
	public String getFrontImg() {
		return frontImg;
	}

	/**
	 * 设置身份证正面
	 * 
	 * @param frontImg
	 *            要设置的身份证正面
	 */
	public void setFrontImg(String frontImg) {
		this.frontImg = frontImg;
	}

	/**
	 * 获取身份证反面
	 *
	 * @return 身份证反面
	 */
	public String getBackImg() {
		return backImg;
	}

	/**
	 * 设置身份证反面
	 * 
	 * @param backImg
	 *            要设置的身份证反面
	 */
	public void setBackImg(String backImg) {
		this.backImg = backImg;
	}

	/**
	 * 获取学历
	 *
	 * @return 学历
	 */
	public String getEducation() {
		return education;
	}

	/**
	 * 设置学历
	 * 
	 * @param education
	 *            要设置的学历
	 */
	public void setEducation(String education) {
		this.education = education;
	}

	/**
	 * 获取婚姻状况
	 *
	 * @return 婚姻状况
	 */
	public String getMarryState() {
		return marryState;
	}

	/**
	 * 设置婚姻状况
	 * 
	 * @param marryState
	 *            要设置的婚姻状况
	 */
	public void setMarryState(String marryState) {
		this.marryState = marryState;
	}

	/**
	 * 获取公司名称
	 *
	 * @return 公司名称
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * 设置公司名称
	 * 
	 * @param companyName
	 *            要设置的公司名称
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * 获取公司电话
	 *
	 * @return 公司电话
	 */
	public String getCompanyPhone() {
		return companyPhone;
	}

	/**
	 * 设置公司电话
	 * 
	 * @param companyPhone
	 *            要设置的公司电话
	 */
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	/**
	 * 获取公司地址
	 *
	 * @return 公司地址
	 */
	public String getCompanyAddr() {
		return companyAddr;
	}

	/**
	 * 设置公司地址
	 * 
	 * @param companyAddr
	 *            要设置的公司地址
	 */
	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr;
	}

	/**
	 * 获取公司详细地址
	 *
	 * @return 公司详细地址
	 */
	public String getCompanyDetailAddr() {
		return companyDetailAddr;
	}

	/**
	 * 设置公司详细地址
	 * 
	 * @param companyDetailAddr
	 *            要设置的公司详细地址
	 */
	public void setCompanyDetailAddr(String companyDetailAddr) {
		this.companyDetailAddr = companyDetailAddr;
	}

	/**
	 * 获取公司坐标(经度,纬度)
	 *
	 * @return 公司坐标(经度,纬度)
	 */
	public String getCompanyCoordinate() {
		return companyCoordinate;
	}

	/**
	 * 设置公司坐标(经度,纬度)
	 * 
	 * @param companyCoordinate
	 *            要设置的公司坐标(经度,纬度)
	 */
	public void setCompanyCoordinate(String companyCoordinate) {
		this.companyCoordinate = companyCoordinate;
	}

	/**
	 * 获取月薪范围
	 *
	 * @return 月薪范围
	 */
	public String getSalary() {
		return salary;
	}

	/**
	 * 设置月薪范围
	 * 
	 * @param salary
	 *            要设置的月薪范围
	 */
	public void setSalary(String salary) {
		this.salary = salary;
	}

	/**
	 * 获取工作年限
	 *
	 * @return 工作年限
	 */
	public String getWorkingYears() {
		return workingYears;
	}

	/**
	 * 设置工作年限
	 * 
	 * @param workingYears
	 *            要设置的工作年限
	 */
	public void setWorkingYears(String workingYears) {
		this.workingYears = workingYears;
	}

	/**
	 * 获取工作照片
	 *
	 * @return 工作照片
	 */
	public String getWorkingImg() {
		return workingImg;
	}

	/**
	 * 设置工作照片
	 * 
	 * @param workingImg
	 *            要设置的工作照片
	 */
	public void setWorkingImg(String workingImg) {
		this.workingImg = workingImg;
	}

	/**
	 * 获取居住时长
	 *
	 * @return 居住时长
	 */
	public String getLiveTime() {
		return liveTime;
	}

	/**
	 * 设置居住时长
	 * 
	 * @param liveTime
	 *            要设置的居住时长
	 */
	public void setLiveTime(String liveTime) {
		this.liveTime = liveTime;
	}

	/**
	 * 获取居住地址
	 *
	 * @return 居住地址
	 */
	public String getLiveAddr() {
		return liveAddr;
	}

	/**
	 * 设置居住地址
	 * 
	 * @param liveAddr
	 *            要设置的居住地址
	 */
	public void setLiveAddr(String liveAddr) {
		this.liveAddr = liveAddr;
	}

	/**
	 * 获取居住详细地址
	 *
	 * @return 居住详细地址
	 */
	public String getLiveDetailAddr() {
		return liveDetailAddr;
	}

	/**
	 * 设置居住详细地址
	 * 
	 * @param liveDetailAddr
	 *            要设置的居住详细地址
	 */
	public void setLiveDetailAddr(String liveDetailAddr) {
		this.liveDetailAddr = liveDetailAddr;
	}

	/**
	 * 获取居住地坐标，(经度,纬度)
	 *
	 * @return 居住地坐标，(经度,纬度)
	 */
	public String getLiveCoordinate() {
		return liveCoordinate;
	}

	/**
	 * 设置居住地坐标，(经度,纬度)
	 * 
	 * @param liveCoordinate
	 *            要设置的居住地坐标，(经度,纬度)
	 */
	public void setLiveCoordinate(String liveCoordinate) {
		this.liveCoordinate = liveCoordinate;
	}

	/**
	 * 获取运营商服务密码
	 *
	 * @return 运营商服务密码
	 */
	public String getPhoneServerPwd() {
		return phoneServerPwd;
	}

	/**
	 * 设置运营商服务密码
	 * 
	 * @param phoneServerPwd
	 *            要设置的运营商服务密码
	 */
	public void setPhoneServerPwd(String phoneServerPwd) {
		this.phoneServerPwd = phoneServerPwd;
	}

	/**
	 * 获取注册地址
	 *
	 * @return 注册地址
	 */
	public String getRegisterAddr() {
		return registerAddr;
	}

	/**
	 * 设置注册地址
	 * 
	 * @param registerAddr
	 *            要设置的注册地址
	 */
	public void setRegisterAddr(String registerAddr) {
		this.registerAddr = registerAddr;
	}

	/**
	 * 获取注册地坐标，(经度,纬度)
	 *
	 * @return 注册地坐标，(经度,纬度)
	 */
	public String getRegisterCoordinate() {
		return registerCoordinate;
	}

	/**
	 * 设置注册地坐标，(经度,纬度)
	 * 
	 * @param registerCoordinate
	 *            要设置的注册地坐标，(经度,纬度)
	 */
	public void setRegisterCoordinate(String registerCoordinate) {
		this.registerCoordinate = registerCoordinate;
	}

	/**
	 * 获取是否黑名单 ，10是20不是
	 *
	 * @return 是否黑名单 ，10是20不是
	 */
	public String getState() {
		return state;
	}

	/**
	 * 设置是否黑名单 ，10是20不是
	 * 
	 * @param state
	 *            要设置的是否黑名单 ，10是20不是
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 获取拉黑原因
	 *
	 * @return 拉黑原因
	 */
	public String getBlackReason() {
		return blackReason;
	}

	/**
	 * 设置拉黑原因
	 * 
	 * @param blackReason
	 *            要设置的拉黑原因
	 */
	public void setBlackReason(String blackReason) {
		this.blackReason = blackReason;
	}

	/**
	 * 获取更新时间
	 * 
	 * @return updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置更新时间
	 * 
	 * @param updateTime
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取创建时间
	 * 
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

    public String getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(String idProvince) {
        this.idProvince = idProvince;
    }

    public String getIdCity() {
        return idCity;
    }

    public void setIdCity(String idCity) {
        this.idCity = idCity;
    }

    public String getIdCounty() {
        return idCounty;
    }

    public void setIdCounty(String idCounty) {
        this.idCounty = idCounty;
    }
}