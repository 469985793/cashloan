package com.xindaibao.cashloan.cl.model.kenya;

import java.util.Date;

public class LoanProduct extends LoanRecord{
    private Long id;

    private String code;

    private String name;

    private Integer fee;

    private Integer cycle;

    private Integer feeType;

    private Integer feePercent;

    private Integer overduePercent;

    private Integer overduePercentTwo;

    private Integer overduePercentMax;

    private Integer fastAudit;

    private Integer accountManage;

    private Integer profit;

    private Integer zhengxin;

    private Integer cuoheFee;

    private Integer infoAudit;

    private Integer loanlossFee;

    private Integer useFee;

    private Integer totalFee;

    private Integer couponsId;

    private Byte status;

    private String statusStr;

    private String scenesStr;

    private Date createdTime;

    private Date updatedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Integer getFeeType() {
        return feeType;
    }

    public void setFeeType(Integer feeType) {
        this.feeType = feeType;
    }

    public Integer getFeePercent() {
        return feePercent;
    }

    public void setFeePercent(Integer feePercent) {
        this.feePercent = feePercent;
    }

    public Integer getOverduePercent() {
        return overduePercent;
    }

    public void setOverduePercent(Integer overduePercent) {
        this.overduePercent = overduePercent;
    }

    public Integer getOverduePercentTwo() {
        return overduePercentTwo;
    }

    public void setOverduePercentTwo(Integer overduePercentTwo) {
        this.overduePercentTwo = overduePercentTwo;
    }

    public Integer getOverduePercentMax() {
        return overduePercentMax;
    }

    public void setOverduePercentMax(Integer overduePercentMax) {
        this.overduePercentMax = overduePercentMax;
    }

    public Integer getFastAudit() {
        return fastAudit;
    }

    public void setFastAudit(Integer fastAudit) {
        this.fastAudit = fastAudit;
    }

    public Integer getAccountManage() {
        return accountManage;
    }

    public void setAccountManage(Integer accountManage) {
        this.accountManage = accountManage;
    }

    public Integer getProfit() {
        return profit;
    }

    public void setProfit(Integer profit) {
        this.profit = profit;
    }

    public Integer getZhengxin() {
        return zhengxin;
    }

    public void setZhengxin(Integer zhengxin) {
        this.zhengxin = zhengxin;
    }

    public Integer getCuoheFee() {
        return cuoheFee;
    }

    public void setCuoheFee(Integer cuoheFee) {
        this.cuoheFee = cuoheFee;
    }

    public Integer getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(Integer infoAudit) {
        this.infoAudit = infoAudit;
    }

    public Integer getLoanlossFee() {
        return loanlossFee;
    }

    public void setLoanlossFee(Integer loanlossFee) {
        this.loanlossFee = loanlossFee;
    }

    public Integer getUseFee() {
        return useFee;
    }

    public void setUseFee(Integer useFee) {
        this.useFee = useFee;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getCouponsId() {
        return couponsId;
    }

    public void setCouponsId(Integer couponsId) {
        this.couponsId = couponsId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }


    public String getScenesStr() {
        return scenesStr;
    }

    public void setScenesStr(String scenesStr) {
        this.scenesStr = scenesStr;
    }
}