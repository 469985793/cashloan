package com.xindaibao.creditrank.cr.domain;

import java.io.Serializable;

/**
 * 授信额度管理实体
 * 
 * @author
 * @version 1.0.0
 * @date 2016-12-15 15:47:24


 * 

 */
 public class Credit implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /***还款成功后，提额50****/
    public static final Double ONE_TIME_CREDIT = (double) 50;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户标识
    */
    private String consumerNo;

    /**
    * 授信额度
    */
    private Double total;
    
    /**
    * 已使用额度
    */
    private Double used;

    /**
    * 可使用额度
    */
    private Double unuse;

    /**
    * 状态 10 -正常 20-冻结
    */
    private String state;

    /**
    * 扩展字段
    */
    private String reqExt;

    /**
     * 额度类型
     */
    private long creditType;
 
    private String grade;
    
    /**
     * 提额次数
     */
    private String count;

    /**
	 * 获取额度类型
	 * @return 额度类型
	 */
	public long getCreditType() {
		return creditType;
	}

	/**
	 * 设置额度类型
	 * @param 额度类型
	 */
	public void setCreditType(long creditType) {
		this.creditType = creditType;
	}

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
    * 获取用户标识
    *
    * @return 用户标识
    */
    public String getConsumerNo(){
    return consumerNo;
    }

    /**
    * 设置用户标识
    * 
    * @param consumerNo 要设置的用户标识
    */
    public void setConsumerNo(String consumerNo){
    this.consumerNo = consumerNo;
    }

    /**
    * 获取授信额度
    *
    * @return 授信额度
    */
    public Double getTotal(){
    return total;
    }

    /**
    * 设置授信额度
    * 
    * @param total 要设置的授信额度
    */
    public void setTotal(Double total){
    this.total = total;
    }

    /**
    * 获取已使用额度
    *
    * @return 已使用额度
    */
    public Double getUsed(){
    return used;
    }

    /**
    * 设置已使用额度
    * 
    * @param used 要设置的已使用额度
    */
    public void setUsed(Double used){
    this.used = used;
    }

    /**
    * 获取可使用额度
    *
    * @return 可使用额度
    */
    public Double getUnuse(){
    return unuse;
    }

    /**
    * 设置可使用额度
    * 
    * @param unuse 要设置的可使用额度
    */
    public void setUnuse(Double unuse){
    this.unuse = unuse;
    }

    /**
    * 获取状态 10 -正常 20-冻结
    *
    * @return 状态 10 -正常 20-冻结
    */
    public String getState(){
    return state;
    }

    /**
    * 设置状态 10 -正常 20-冻结
    * 
    * @param state 要设置的状态 10 -正常 20-冻结
    */
    public void setState(String state){
    this.state = state;
    }

    /**
    * 获取扩展字段
    *
    * @return 扩展字段
    */
    public String getReqExt(){
    return reqExt;
    }

    /**
    * 设置扩展字段
    * 
    * @param reqExt 要设置的扩展字段
    */
    public void setReqExt(String reqExt){
    this.reqExt = reqExt;
    }

	/**
	 * 获取grade
	 * @return grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * 设置grade
	 * @param grade
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}