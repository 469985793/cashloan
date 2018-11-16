package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;

/**
 * 同盾运营商认证基本信息表实体
 */
 public class OperatorTdBasic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户id
    */
    private Long userId;
    /**
     * 请求id
     */
    private Long  reqLogId;
    /**
     * 请求订单号
     */
    private String orderNo;
    /**
     * 授权手机号码
     */
    private String userMobile;

    /**
    * 渠道数据源  中国移动中国联通中国电信
    */
    private String channelSrc;
  
    /**
    * 账户余额
    */
    private String accountBalance;

    /**
    * 当前话费
    */
    private String currentFee;

    /**
    * 账户星级
    */
    private String creditLevel;

    /**
    * 账户状态
    */
    private String mobileStatus;

    /**
    * 入网时间
    */
    private String netTime;

    /**
    * 网龄(月)  后 改成 以天为单位
    */
    private Integer netAge;

    /**
    * 实名制信息
    */
    private String realInfo;

    /**
    * 积分
    */
    private String creditPoint;

    /**
    * 星级有效期 
    */
    private String creditEffectiveTime;

    /**
    * 星级得分
    */
    private String creditScore;

    /**
    * 通话级别
    */
    private String landLevel;

    /**
    * 漫游级别
    */
    private String roamState;

    /**
    * 账户可用余额
    */
    private String balanceAvailable;

    /**
    * 账户冻结余额
    */
    private String balanceUnavailable;

    /**
    * 可用预存款
    */
    private String prepayAvailable;

    /**
    * 可用赠款
    */
    private String promAvailable;

    /**
    * 冻结预存款
    */
    private String prepayUnavailable;

    /**
    * 冻结赠款
    */
    private String promUnavailable;


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
    * 获取用户id
    *
    * @return 用户id
    */
    public Long getUserId(){
        return userId;
    }

    /**
    * 设置用户id
    * 
    * @param userId 要设置的用户id
    */
    public void setUserId(Long userId){
        this.userId = userId;
    }

    /**
    * 获取渠道数据源  中国移动中国联通中国电信
    *
    * @return 渠道数据源  中国移动中国联通中国电信
    */
    public String getChannelSrc(){
        return channelSrc;
    }

    /**
    * 设置渠道数据源  中国移动中国联通中国电信
    * 
    * @param channelSrc 要设置的渠道数据源  中国移动中国联通中国电信
    */
    public void setChannelSrc(String channelSrc){
        this.channelSrc = channelSrc;
    }

    /**
    * 获取账户余额
    *
    * @return 账户余额
    */
    public String getAccountBalance(){
        return accountBalance;
    }

    /**
    * 设置账户余额
    * 
    * @param accountBalance 要设置的账户余额
    */
    public void setAccountBalance(String accountBalance){
        this.accountBalance = accountBalance;
    }

    /**
    * 获取当前话费
    *
    * @return 当前话费
    */
    public String getCurrentFee(){
        return currentFee;
    }

    /**
    * 设置当前话费
    * 
    * @param currentFee 要设置的当前话费
    */
    public void setCurrentFee(String currentFee){
        this.currentFee = currentFee;
    }

    /**
    * 获取账户星级
    *
    * @return 账户星级
    */
    public String getCreditLevel(){
        return creditLevel;
    }

    /**
    * 设置账户星级
    * 
    * @param creditLevel 要设置的账户星级
    */
    public void setCreditLevel(String creditLevel){
        this.creditLevel = creditLevel;
    }

    /**
    * 获取账户状态
    *
    * @return 账户状态
    */
    public String getMobileStatus(){
        return mobileStatus;
    }

    /**
    * 设置账户状态
    * 
    * @param mobileStatus 要设置的账户状态
    */
    public void setMobileStatus(String mobileStatus){
        this.mobileStatus = mobileStatus;
    }

    /**
    * 获取入网时间
    *
    * @return 入网时间
    */
    public String getNetTime(){
        return netTime;
    }

    /**
    * 设置入网时间
    * 
    * @param netTime 要设置的入网时间
    */
    public void setNetTime(String netTime){
        this.netTime = netTime;
    }

    /**
    * 获取网龄(月) 
    *
    * @return 网龄(月)
    */
    public Integer getNetAge(){
        return netAge;
    }

    /**
    * 设置网龄(月)
    * 
    * @param netAge 要设置的网龄(月)
    */
    public void setNetAge(Integer netAge){
        this.netAge = netAge;
    }

    /**
    * 获取实名制信息
    *
    * @return 实名制信息
    */
    public String getRealInfo(){
        return realInfo;
    }

    /**
    * 设置实名制信息
    * 
    * @param realInfo 要设置的实名制信息
    */
    public void setRealInfo(String realInfo){
        this.realInfo = realInfo;
    }

    /**
    * 获取积分
    *
    * @return 积分
    */
    public String getCreditPoint(){
        return creditPoint;
    }

    /**
    * 设置积分
    * 
    * @param creditPoint 要设置的积分
    */
    public void setCreditPoint(String creditPoint){
        this.creditPoint = creditPoint;
    }

    /**
    * 获取星级有效期 
    *
    * @return 星级有效期 
    */
    public String getCreditEffectiveTime(){
        return creditEffectiveTime;
    }

    /**
    * 设置星级有效期 
    * 
    * @param creditEffectiveTime 要设置的星级有效期 
    */
    public void setCreditEffectiveTime(String creditEffectiveTime){
        this.creditEffectiveTime = creditEffectiveTime;
    }

    /**
    * 获取星级得分
    *
    * @return 星级得分
    */
    public String getCreditScore(){
        return creditScore;
    }

    /**
    * 设置星级得分
    * 
    * @param creditScore 要设置的星级得分
    */
    public void setCreditScore(String creditScore){
        this.creditScore = creditScore;
    }

    /**
    * 获取通话级别
    *
    * @return 通话级别
    */
    public String getLandLevel(){
        return landLevel;
    }

    /**
    * 设置通话级别
    * 
    * @param landLevel 要设置的通话级别
    */
    public void setLandLevel(String landLevel){
        this.landLevel = landLevel;
    }

    /**
    * 获取漫游级别
    *
    * @return 漫游级别
    */
    public String getRoamState(){
        return roamState;
    }

    /**
    * 设置漫游级别
    * 
    * @param roamState 要设置的漫游级别
    */
    public void setRoamState(String roamState){
        this.roamState = roamState;
    }

    /**
    * 获取账户可用余额
    *
    * @return 账户可用余额
    */
    public String getBalanceAvailable(){
        return balanceAvailable;
    }

    /**
    * 设置账户可用余额
    * 
    * @param balanceAvailable 要设置的账户可用余额
    */
    public void setBalanceAvailable(String balanceAvailable){
        this.balanceAvailable = balanceAvailable;
    }

    /**
    * 获取账户冻结余额
    *
    * @return 账户冻结余额
    */
    public String getBalanceUnavailable(){
        return balanceUnavailable;
    }

    /**
    * 设置账户冻结余额
    * 
    * @param balanceUnavailable 要设置的账户冻结余额
    */
    public void setBalanceUnavailable(String balanceUnavailable){
        this.balanceUnavailable = balanceUnavailable;
    }

    /**
    * 获取可用预存款
    *
    * @return 可用预存款
    */
    public String getPrepayAvailable(){
        return prepayAvailable;
    }

    /**
    * 设置可用预存款
    * 
    * @param prepayAvailable 要设置的可用预存款
    */
    public void setPrepayAvailable(String prepayAvailable){
        this.prepayAvailable = prepayAvailable;
    }

    /**
    * 获取可用赠款
    *
    * @return 可用赠款
    */
    public String getPromAvailable(){
        return promAvailable;
    }

    /**
    * 设置可用赠款
    * 
    * @param promAvailable 要设置的可用赠款
    */
    public void setPromAvailable(String promAvailable){
        this.promAvailable = promAvailable;
    }

    /**
    * 获取冻结预存款
    *
    * @return 冻结预存款
    */
    public String getPrepayUnavailable(){
        return prepayUnavailable;
    }

    /**
    * 设置冻结预存款
    * 
    * @param prepayUnavailable 要设置的冻结预存款
    */
    public void setPrepayUnavailable(String prepayUnavailable){
        this.prepayUnavailable = prepayUnavailable;
    }

    /**
    * 获取冻结赠款
    *
    * @return 冻结赠款
    */
    public String getPromUnavailable(){
        return promUnavailable;
    }

    /**
    * 设置冻结赠款
    * 
    * @param promUnavailable 要设置的冻结赠款
    */
    public void setPromUnavailable(String promUnavailable){
        this.promUnavailable = promUnavailable;
    }

	public Long getReqLogId() {
		return reqLogId;
	}

	public void setReqLogId(Long reqLogId) {
		this.reqLogId = reqLogId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
 

}