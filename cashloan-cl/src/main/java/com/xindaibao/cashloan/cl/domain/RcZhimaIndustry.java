package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 芝麻行业关注名单实体
 */
 public class RcZhimaIndustry implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 
    */
    private Long userId;

    /**
    * 是否命中，10命中20未命中
    */
    private String isMatched;

    /**
    * 业务号
    */
    private String bizNo;

    /**
    * 详情
    */
    private String details;
    
    /**
     * 请求标识
     */
    private String transactionId;
    
    /**
     * 请求时间
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
    * 获取
    *
    * @return 
    */
    public Long getUserId(){
    return userId;
    }

    /**
    * 设置
    * 
    * @param userId 要设置的
    */
    public void setUserId(Long userId){
    this.userId = userId;
    }

    /**
    * 获取是否命中，10命中20未命中
    *
    * @return 是否命中，10命中20未命中
    */
    public String getIsMatched(){
    return isMatched;
    }

    /**
    * 设置是否命中，10命中20未命中
    * 
    * @param isMatched 要设置的是否命中，10命中20未命中
    */
    public void setIsMatched(String isMatched){
    this.isMatched = isMatched;
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
    * 获取详情
    *
    * @return 详情
    */
    public String getDetails(){
    return details;
    }

    /**
    * 设置详情
    * 
    * @param details 要设置的详情
    */
    public void setDetails(String details){
    this.details = details;
    }

	/**
	 * transactionId
	 *
	 * @return  the transactionId
	 * @since   1.0.0
	*/
	
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
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