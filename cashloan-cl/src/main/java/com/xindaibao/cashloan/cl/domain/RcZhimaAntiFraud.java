package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 芝麻反欺诈实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-06 18:47:03


 * 

 */
 public class RcZhimaAntiFraud implements Serializable {

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
    * ivs评分。取值区间为[0,100]，越高越好
    */
    private String ivsScore;

    /**
    * 风险因素code与风险描述说明
    */
    private String ivsDetail;

    /**
    * 芝麻返回的业务号
    */
    private String bizNo;
    
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
    * 获取ivs评分。取值区间为[0,100]，越高越好
    *
    * @return ivs评分。取值区间为[0,100]，越高越好
    */
    public String getIvsScore(){
    return ivsScore;
    }

    /**
    * 设置ivs评分。取值区间为[0,100]，越高越好
    * 
    * @param ivsScore 要设置的ivs评分。取值区间为[0,100]，越高越好
    */
    public void setIvsScore(String ivsScore){
    this.ivsScore = ivsScore;
    }

    /**
    * 获取风险因素code与风险描述说明
    *
    * @return 风险因素code与风险描述说明
    */
    public String getIvsDetail(){
    return ivsDetail;
    }

    /**
    * 设置风险因素code与风险描述说明
    * 
    * @param ivsDetail 要设置的风险因素code与风险描述说明
    */
    public void setIvsDetail(String ivsDetail){
    this.ivsDetail = ivsDetail;
    }

    /**
    * 获取芝麻返回的业务号
    *
    * @return 芝麻返回的业务号
    */
    public String getBizNo(){
    return bizNo;
    }

    /**
    * 设置芝麻返回的业务号
    * 
    * @param bizNo 要设置的芝麻返回的业务号
    */
    public void setBizNo(String bizNo){
    this.bizNo = bizNo;
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