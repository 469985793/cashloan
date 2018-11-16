package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 公积金详细信息表(流水)实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-05-16 11:15:39




 */
 public class AccfundLog implements Serializable {

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
    * 
    */
    private Double amountBalance;

    /**
    * 业务号
    */
    private String bizNo;

    /**
    * 业务发生时间
    */
    private Date bizTime;

    /**
    * 摘要
    */
    private String digest;

    /**
    * 资金流向（流入、流出）INCOME/EXPENSE
    */
    private String fundFlow;

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
    * 获取
    *
    * @return 
    */
    public Double getAmountBalance(){
    return amountBalance;
    }

    /**
    * 设置
    * 
    * @param amountBalance 要设置的
    */
    public void setAmountBalance(Double amountBalance){
    this.amountBalance = amountBalance;
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
    * 获取业务发生时间
    *
    * @return 业务发生时间
    */
    public Date getBizTime(){
    return bizTime;
    }

    /**
    * 设置业务发生时间
    * 
    * @param bizTime 要设置的业务发生时间
    */
    public void setBizTime(Date bizTime){
    this.bizTime = bizTime;
    }

    /**
    * 获取摘要
    *
    * @return 摘要
    */
    public String getDigest(){
    return digest;
    }

    /**
    * 设置摘要
    * 
    * @param digest 要设置的摘要
    */
    public void setDigest(String digest){
    this.digest = digest;
    }

    /**
    * 获取资金流向（流入、流出）INCOME/EXPENSE
    *
    * @return 资金流向（流入、流出）INCOME/EXPENSE
    */
    public String getFundFlow(){
    return fundFlow;
    }

    /**
    * 设置资金流向（流入、流出）INCOME/EXPENSE
    * 
    * @param fundFlow 要设置的资金流向（流入、流出）INCOME/EXPENSE
    */
    public void setFundFlow(String fundFlow){
    this.fundFlow = fundFlow;
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