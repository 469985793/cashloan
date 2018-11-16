package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 公积金基本信息表实体
 */
 public class AccfundInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户账号
    */
    private String accountNo;

    /**
    * 账户金额(分)
    */
    private Double amount;

    /**
    * 业务号
    */
    private String bizNo;

    /**
    * 公司信息
    */
    private String company;

    /**
    * 缴纳状态
    */
    private String depositStatus;

    /**
    * 创建时间
    */
    private Date gmtCreate;

    /**
    * 修改时间
    */
    private Date gmtModified;

    /**
    * 公积金 ID
    */
    private String houseAccumulationFundId;

    /**
    * 用户身份证号
    */
    private String idCard;

    /**
    * 姓名
    */
    private String name;

    /**
    * 公积金所在地
    */
    private String region;

    /**
    * 客户表 外键
    */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
    * 获取主键Id
    *
    * @return id
    */
    public Long getId(){
    return id;
    }

    /**
    * 设置主键Id
    * 
    * @param 要设置的主键Id
    */
    public void setId(Long id){
    this.id = id;
    }

    /**
    * 获取用户账号
    *
    * @return 用户账号
    */
    public String getAccountNo(){
    return accountNo;
    }

    /**
    * 设置用户账号
    * 
    * @param accountNo 要设置的用户账号
    */
    public void setAccountNo(String accountNo){
    this.accountNo = accountNo;
    }

    /**
    * 获取账户金额(分)
    *
    * @return 账户金额(分)
    */
    public Double getAmount(){
    return amount;
    }

    /**
    * 设置账户金额(分)
    * 
    * @param amount 要设置的账户金额(分)
    */
    public void setAmount(Double amount){
    this.amount = amount;
    }

    /**
    * 获取业务号
    *
    * @return 业务号
    */
    public String getBizNo(){
    return bizNo;
    }

    /**
    * 设置业务号
    * 
    * @param bizNo 要设置的业务号
    */
    public void setBizNo(String bizNo){
    this.bizNo = bizNo;
    }

    /**
    * 获取公司信息
    *
    * @return 公司信息
    */
    public String getCompany(){
    return company;
    }

    /**
    * 设置公司信息
    * 
    * @param company 要设置的公司信息
    */
    public void setCompany(String company){
    this.company = company;
    }

    /**
    * 获取缴纳状态
    *
    * @return 缴纳状态
    */
    public String getDepositStatus(){
    return depositStatus;
    }

    /**
    * 设置缴纳状态
    * 
    * @param depositStatus 要设置的缴纳状态
    */
    public void setDepositStatus(String depositStatus){
    this.depositStatus = depositStatus;
    }

    /**
    * 获取创建时间
    *
    * @return 创建时间
    */
    public Date getGmtCreate(){
    return gmtCreate;
    }

    /**
    * 设置创建时间
    * 
    * @param gmtCreate 要设置的创建时间
    */
    public void setGmtCreate(Date gmtCreate){
    this.gmtCreate = gmtCreate;
    }

    /**
    * 获取修改时间
    *
    * @return 修改时间
    */
    public Date getGmtModified(){
    return gmtModified;
    }

    /**
    * 设置修改时间
    * 
    * @param gmtModified 要设置的修改时间
    */
    public void setGmtModified(Date gmtModified){
    this.gmtModified = gmtModified;
    }

    /**
    * 获取公积金 ID
    *
    * @return 公积金 ID
    */
    public String getHouseAccumulationFundId(){
    return houseAccumulationFundId;
    }

    /**
    * 设置公积金 ID
    * 
    * @param houseAccumulationFundId 要设置的公积金 ID
    */
    public void setHouseAccumulationFundId(String houseAccumulationFundId){
    this.houseAccumulationFundId = houseAccumulationFundId;
    }

    /**
    * 获取用户身份证号
    *
    * @return 用户身份证号
    */
    public String getIdCard(){
    return idCard;
    }

    /**
    * 设置用户身份证号
    * 
    * @param idCard 要设置的用户身份证号
    */
    public void setIdCard(String idCard){
    this.idCard = idCard;
    }

    /**
    * 获取姓名
    *
    * @return 姓名
    */
    public String getName(){
    return name;
    }

    /**
    * 设置姓名
    * 
    * @param name 要设置的姓名
    */
    public void setName(String name){
    this.name = name;
    }

    /**
    * 获取公积金所在地
    *
    * @return 公积金所在地
    */
    public String getRegion(){
    return region;
    }

    /**
    * 设置公积金所在地
    * 
    * @param region 要设置的公积金所在地
    */
    public void setRegion(String region){
    this.region = region;
    }

    /**
    * 获取客户表 外键
    *
    * @return 客户表 外键
    */
    public Long getUserId(){
    return userId;
    }

    /**
    * 设置客户表 外键
    * 
    * @param userId 要设置的客户表 外键
    */
    public void setUserId(Long userId){
    this.userId = userId;
    }

	/**
	 * createTime
	 *
	 * @return  the createTime
	 * @since   1.0.0
	*/
	
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

    
}