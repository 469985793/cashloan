package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;

/**
 * 贷后邦历史机构类型实体
 */
 public class DhbHistoryOrg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 订单号
    */
    private String orderNo;

    /**
    * 用户标识
    */
    private Long userId;

    /**
    * 线上消费分期出现次数
    */
    private Integer onlineInstallmentCnt;

    /**
    * 线下消费分期出现次数
    */
    private Integer offlineInstallmentCnt;

    /**
    * 信用卡代还出现次数
    */
    private Integer creditCardRepaymentCnt;

    /**
    * 小额快速贷出现次数
    */
    private Integer paydayLoanCnt;

    /**
    * 线上现金贷出现次数
    */
    private Integer onlineCashLoanCnt;

    /**
    * 线下现金贷出现次数
    */
    private Integer offlineCashLoanCnt;

    /**
    * 其他
    */
    private Integer othersCnt;


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
    * 获取订单号
    *
    * @return 订单号
    */
    public String getOrderNo(){
        return orderNo;
    }

    /**
    * 设置订单号
    * 
    * @param orderNo 要设置的订单号
    */
    public void setOrderNo(String orderNo){
        this.orderNo = orderNo;
    }

    /**
    * 获取用户标识
    *
    * @return 用户标识
    */
    public Long getUserId(){
        return userId;
    }

    /**
    * 设置用户标识
    * 
    * @param userId 要设置的用户标识
    */
    public void setUserId(Long userId){
        this.userId = userId;
    }

    /**
    * 获取线上消费分期出现次数
    *
    * @return 线上消费分期出现次数
    */
    public Integer getOnlineInstallmentCnt(){
        return onlineInstallmentCnt;
    }

    /**
    * 设置线上消费分期出现次数
    * 
    * @param onlineInstallmentCnt 要设置的线上消费分期出现次数
    */
    public void setOnlineInstallmentCnt(Integer onlineInstallmentCnt){
        this.onlineInstallmentCnt = onlineInstallmentCnt;
    }

    /**
    * 获取线下消费分期出现次数
    *
    * @return 线下消费分期出现次数
    */
    public Integer getOfflineInstallmentCnt(){
        return offlineInstallmentCnt;
    }

    /**
    * 设置线下消费分期出现次数
    * 
    * @param offlineInstallmentCnt 要设置的线下消费分期出现次数
    */
    public void setOfflineInstallmentCnt(Integer offlineInstallmentCnt){
        this.offlineInstallmentCnt = offlineInstallmentCnt;
    }

    /**
    * 获取信用卡代还出现次数
    *
    * @return 信用卡代还出现次数
    */
    public Integer getCreditCardRepaymentCnt(){
        return creditCardRepaymentCnt;
    }

    /**
    * 设置信用卡代还出现次数
    * 
    * @param creditCardRepaymentCnt 要设置的信用卡代还出现次数
    */
    public void setCreditCardRepaymentCnt(Integer creditCardRepaymentCnt){
        this.creditCardRepaymentCnt = creditCardRepaymentCnt;
    }

    /**
    * 获取小额快速贷出现次数
    *
    * @return 小额快速贷出现次数
    */
    public Integer getPaydayLoanCnt(){
        return paydayLoanCnt;
    }

    /**
    * 设置小额快速贷出现次数
    * 
    * @param paydayLoanCnt 要设置的小额快速贷出现次数
    */
    public void setPaydayLoanCnt(Integer paydayLoanCnt){
        this.paydayLoanCnt = paydayLoanCnt;
    }

    /**
    * 获取线上现金贷出现次数
    *
    * @return 线上现金贷出现次数
    */
    public Integer getOnlineCashLoanCnt(){
        return onlineCashLoanCnt;
    }

    /**
    * 设置线上现金贷出现次数
    * 
    * @param onlineCashLoanCnt 要设置的线上现金贷出现次数
    */
    public void setOnlineCashLoanCnt(Integer onlineCashLoanCnt){
        this.onlineCashLoanCnt = onlineCashLoanCnt;
    }

    /**
    * 获取线下现金贷出现次数
    *
    * @return 线下现金贷出现次数
    */
    public Integer getOfflineCashLoanCnt(){
        return offlineCashLoanCnt;
    }

    /**
    * 设置线下现金贷出现次数
    * 
    * @param offlineCashLoanCnt 要设置的线下现金贷出现次数
    */
    public void setOfflineCashLoanCnt(Integer offlineCashLoanCnt){
        this.offlineCashLoanCnt = offlineCashLoanCnt;
    }

    /**
    * 获取其他
    *
    * @return 其他
    */
    public Integer getOthersCnt(){
        return othersCnt;
    }

    /**
    * 设置其他
    * 
    * @param othersCnt 要设置的其他
    */
    public void setOthersCnt(Integer othersCnt){
        this.othersCnt = othersCnt;
    }

}