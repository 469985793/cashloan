package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 催款计划表实体
 */
 public class UrgeRepayOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 催收人员id
    */
    private Long userId;

    /**
    * 催款人姓名
    */
    private String userName;
    
    /**
     * 借款人姓名
     */
    private String borrowName;

    /**
    * 借款人手机号
    */
    private String phone;

    /**
    * 借款id
    */
    private Long borrowId;
    /**
	 * 订单号
	 */
	private String orderNo;

    /**
    * 借款金额
    */
    private Double amount;

    /**
    * 借款期限
    */
    private String timeLimit;

    /**
    * 借款时间
    */
    private Date borrowTime;

    /**
    * 应还款时间
    */
    private Date repayTime;

    /**
    * 逾期天数
    */
    private Long penaltyDay;

    /**
    * 逾期金额(罚息)
    */
    private Double penaltyAmout;

    /**
    * 订单状态       10未分配;11待催收;20催收中;30承诺还款;40催收成功;50坏账
    */
    private String state;

    /**
    * 催款总次数
    */
    private Long count;

    /**
    * 创建时间
    */
    private Date createTime;
    
    /**
     * 催收成功日期
     */
    private Date successTime;

    /**
     * 逾期等级       M1 (1-30天)  M2 (31-60天)  M3 (61以上)
     */
     private String level;
     
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
    * 获取催收人员id
    *
    * @return 催收人员id
    */
    public Long getUserId(){
    return userId;
    }

    /**
    * 设置催收人员id
    * 
    * @param userId 要设置的催收人员id
    */
    public void setUserId(Long userId){
    this.userId = userId;
    }

    /**
    * 获取催款人姓名
    *
    * @return 催款人姓名
    */
    public String getUserName(){
    return userName;
    }

    /**
    * 设置催款人姓名
    * 
    * @param userName 要设置的催款人姓名
    */
    public void setUserName(String userName){
    this.userName = userName;
    }

    /**
    * 获取催款人手机号
    *
    * @return 催款人手机号
    */
    public String getPhone(){
    return phone;
    }

    /**
    * 设置催款人手机号
    * 
    * @param phone 要设置的催款人手机号
    */
    public void setPhone(String phone){
    this.phone = phone;
    }

    /**
    * 获取借款id
    *
    * @return 借款id
    */
    public Long getBorrowId(){
    return borrowId;
    }

    /**
    * 设置借款id
    * 
    * @param borrowId 要设置的借款id
    */
    public void setBorrowId(Long borrowId){
    this.borrowId = borrowId;
    }

    /**
    * 获取借款金额
    *
    * @return 借款金额
    */
    public Double getAmount(){
    return amount;
    }

    /**
    * 设置借款金额
    * 
    * @param amount 要设置的借款金额
    */
    public void setAmount(Double amount){
    this.amount = amount;
    }

    /**
    * 获取借款期限
    *
    * @return 借款期限
    */
    public String getTimeLimit(){
    return timeLimit;
    }

    /**
    * 设置借款期限
    * 
    * @param timeLimit 要设置的借款期限
    */
    public void setTimeLimit(String timeLimit){
    this.timeLimit = timeLimit;
    }

    /**
    * 获取借款时间
    *
    * @return 借款时间
    */
    public Date getBorrowTime(){
    return borrowTime;
    }

    /**
    * 设置借款时间
    * 
    * @param borrowTime 要设置的借款时间
    */
    public void setBorrowTime(Date borrowTime){
    this.borrowTime = borrowTime;
    }

    /**
    * 获取应还款时间
    *
    * @return 应还款时间
    */
    public Date getRepayTime(){
    return repayTime;
    }

    /**
    * 设置应还款时间
    * 
    * @param repayTime 要设置的应还款时间
    */
    public void setRepayTime(Date repayTime){
    this.repayTime = repayTime;
    }

    /**
    * 获取逾期天数
    *
    * @return 逾期天数
    */
    public Long getPenaltyDay(){
    return penaltyDay;
    }

    /**
    * 设置逾期天数
    * 
    * @param penaltyDay 要设置的逾期天数
    */
    public void setPenaltyDay(Long penaltyDay){
    this.penaltyDay = penaltyDay;
    }

    /**
    * 获取逾期金额(罚息)
    *
    * @return 逾期金额(罚息)
    */
    public Double getPenaltyAmout(){
    return penaltyAmout;
    }

    /**
    * 设置逾期金额(罚息)
    * 
    * @param penaltyAmout 要设置的逾期金额(罚息)
    */
    public void setPenaltyAmout(Double penaltyAmout){
    this.penaltyAmout = penaltyAmout;
    }

    /**
    * 获取订单状态   10待催收;20催收中;30承诺还款;40催收成功;50坏账
    *
    * @return 订单状态   10待催收;20催收中;30承诺还款;40催收成功;50坏账
    */
    public String getState(){
    return state;
    }

    /**
    * 设置订单状态   10待催收;20催收中;30承诺还款;40催收成功;50坏账
    * 
    * @param state 要设置的订单状态   10待催收;20催收中;30承诺还款;40催收成功;50坏账
    */
    public void setState(String state){
    this.state = state;
    }

    /**
    * 获取催款总次数
    *
    * @return 催款总次数
    */
    public Long getCount(){
    return count;
    }

    /**
    * 设置催款总次数
    * 
    * @param count 要设置的催款总次数
    */
    public void setCount(Long count){
    this.count = count;
    }

    /**
    * 获取创建时间
    *
    * @return 创建时间
    */
    public Date getCreateTime(){
    return createTime;
    }

    /**
    * 设置创建时间
    * 
    * @param createTime 要设置的创建时间
    */
    public void setCreateTime(Date createTime){
    this.createTime = createTime;
    }

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * @return the successTime
	 */
	public Date getSuccessTime() {
		return successTime;
	}

	/**
	 * @param successTime the successTime to set
	 */
	public void setSuccessTime(Date successTime) {
		this.successTime = successTime;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}