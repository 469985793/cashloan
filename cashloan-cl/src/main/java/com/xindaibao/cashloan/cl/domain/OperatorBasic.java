package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 运营商信息-基础信息实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-13 16:35:43


 * 

 */
 public class OperatorBasic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;
    
    /**
     * 用户Id
     */
    private Long userId;

    /**
    * 修改时间
    */
    private Date gmtModified;

    /**
    * 当月消费(单位为分)
    */
    private Double basicExpenditure;

    /**
    * 创建时间
    */
    private Date gmtCreate;

    /**
    * 入网时间
    */
    private Date extendJoinDt;

    /**
    * 累计积分（可以为0）
    */
    private Integer basicAllBonus;

    /**
    * 实名认证状态
    */
    private String extendCertifedStatus;

    /**
    * 余额（单位为分）
    */
    private Double basicBalance;

    /**
    * 号码
    */
    private String basicPhoneNum;

    /**
    * 归属地
    */
    private String extendBelongto;

    /**
    * 联系地址
    */
    private String extendContactAddr;

    /**
    * 网龄
    */
    private Integer extendPhoneAge;

    /**
    * 业务编号
    */
    private String bizNo;

    /**
    * 姓名
    */
    private String basicUserName;
    
    /**
     * 添加时间
     */
    private Date createTime;

	/**
	 * @return the 主键Id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param 主键Id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 获取用户Id
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置用户Id
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the 修改时间
	 */
	public Date getGmtModified() {
		return gmtModified;
	}

	/**
	 * @param 修改时间 the gmtModified to set
	 */
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	/**
	 * @return the 当月消费(单位为分)
	 */
	public Double getBasicExpenditure() {
		return basicExpenditure;
	}

	/**
	 * @param 当月消费(单位为分) the basicExpenditure to set
	 */
	public void setBasicExpenditure(Double basicExpenditure) {
		this.basicExpenditure = basicExpenditure;
	}

	/**
	 * @return the 创建时间
	 */
	public Date getGmtCreate() {
		return gmtCreate;
	}

	/**
	 * @param 创建时间 the gmtCreate to set
	 */
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	/**
	 * @return the 入网时间
	 */
	public Date getExtendJoinDt() {
		return extendJoinDt;
	}

	/**
	 * @param 入网时间 the extendJoinDt to set
	 */
	public void setExtendJoinDt(Date extendJoinDt) {
		this.extendJoinDt = extendJoinDt;
	}

	/**
	 * @return the 累计积分（可以为0）
	 */
	public Integer getBasicAllBonus() {
		return basicAllBonus;
	}

	/**
	 * @param 累计积分（可以为0） the basicAllBonus to set
	 */
	public void setBasicAllBonus(Integer basicAllBonus) {
		this.basicAllBonus = basicAllBonus;
	}

	/**
	 * @return the 实名认证状态
	 */
	public String getExtendCertifedStatus() {
		return extendCertifedStatus;
	}

	/**
	 * @param 实名认证状态 the extendCertifedStatus to set
	 */
	public void setExtendCertifedStatus(String extendCertifedStatus) {
		this.extendCertifedStatus = extendCertifedStatus;
	}

	/**
	 * @return the 余额（单位为分）
	 */
	public Double getBasicBalance() {
		return basicBalance;
	}

	/**
	 * @param 余额（单位为分） the basicBalance to set
	 */
	public void setBasicBalance(Double basicBalance) {
		this.basicBalance = basicBalance;
	}

	/**
	 * @return the 号码
	 */
	public String getBasicPhoneNum() {
		return basicPhoneNum;
	}

	/**
	 * @param 号码 the basicPhoneNum to set
	 */
	public void setBasicPhoneNum(String basicPhoneNum) {
		this.basicPhoneNum = basicPhoneNum;
	}

	/**
	 * @return the 归属地
	 */
	public String getExtendBelongto() {
		return extendBelongto;
	}

	/**
	 * @param 归属地 the extendBelongto to set
	 */
	public void setExtendBelongto(String extendBelongto) {
		this.extendBelongto = extendBelongto;
	}

	/**
	 * @return the 联系地址
	 */
	public String getExtendContactAddr() {
		return extendContactAddr;
	}

	/**
	 * @param 联系地址 the extendContactAddr to set
	 */
	public void setExtendContactAddr(String extendContactAddr) {
		this.extendContactAddr = extendContactAddr;
	}

	/**
	 * @return the 网龄
	 */
	public Integer getExtendPhoneAge() {
		return extendPhoneAge;
	}

	/**
	 * @param 网龄 the extendPhoneAge to set
	 */
	public void setExtendPhoneAge(Integer extendPhoneAge) {
		this.extendPhoneAge = extendPhoneAge;
	}

	/**
	 * @return the 业务编号
	 */
	public String getBizNo() {
		return bizNo;
	}

	/**
	 * @param 业务编号 the bizNo to set
	 */
	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}

	/**
	 * @return the 姓名
	 */
	public String getBasicUserName() {
		return basicUserName;
	}

	/**
	 * @param 姓名 the basicUserName to set
	 */
	public void setBasicUserName(String basicUserName) {
		this.basicUserName = basicUserName;
	}

	/**
	 * @return the 添加时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param 添加时间 the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}