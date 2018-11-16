package com.xindaibao.cashloan.cl.model.kenya;

import java.util.Date;

public class LoanRecord {
    private Long id;

    private Long uid;

    private String indentNo;

    private String firstName;

    private String lastName;

    private String mobile;

    private Byte applyTerminal;

    private Byte loanReason;

    private Long balance;

    private Long productId;

    private Integer cycle;

    private Integer feeType;

    private Integer feePercent;

    private Integer overduePercent;

    private Integer fee;

    private Byte feeGetway;

    private Long overdueFee;

    private Date arriveTime;

    private Long actualBalance;

    private Date shouldbackTime;

    private Date lastbackTime;

    private Long actualbackAmt;

    private String bankCardNo;

    private String bankaccName;

    private String bankNo;

    private String bankName;

    private Date auditRiskTime;

    private String auditRiskReson;

    private Long auditUserId;

    private Date auditUserTime;

    private String auditUserReson;

    private Long payUserId;

    private Byte tradeMode;

    private String applyCity;

    private Integer couponsId;

    private String imageCode;

    private Byte status;

    private Date createdTime;

    private Date updatedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getIndentNo() {
        return indentNo;
    }

    public void setIndentNo(String indentNo) {
        this.indentNo = indentNo == null ? null : indentNo.trim();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Byte getApplyTerminal() {
        return applyTerminal;
    }

    public void setApplyTerminal(Byte applyTerminal) {
        this.applyTerminal = applyTerminal;
    }

    public Byte getLoanReason() {
        return loanReason;
    }

    public void setLoanReason(Byte loanReason) {
        this.loanReason = loanReason;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Byte getFeeGetway() {
        return feeGetway;
    }

    public void setFeeGetway(Byte feeGetway) {
        this.feeGetway = feeGetway;
    }

    public Long getOverdueFee() {
        return overdueFee;
    }

    public void setOverdueFee(Long overdueFee) {
        this.overdueFee = overdueFee;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Long getActualBalance() {
        return actualBalance;
    }

    public void setActualBalance(Long actualBalance) {
        this.actualBalance = actualBalance;
    }

    public Date getShouldbackTime() {
        return shouldbackTime;
    }

    public void setShouldbackTime(Date shouldbackTime) {
        this.shouldbackTime = shouldbackTime;
    }

    public Date getLastbackTime() {
        return lastbackTime;
    }

    public void setLastbackTime(Date lastbackTime) {
        this.lastbackTime = lastbackTime;
    }

    public Long getActualbackAmt() {
        return actualbackAmt;
    }

    public void setActualbackAmt(Long actualbackAmt) {
        this.actualbackAmt = actualbackAmt;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo == null ? null : bankCardNo.trim();
    }

    public String getBankaccName() {
        return bankaccName;
    }

    public void setBankaccName(String bankaccName) {
        this.bankaccName = bankaccName == null ? null : bankaccName.trim();
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo == null ? null : bankNo.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public Date getAuditRiskTime() {
        return auditRiskTime;
    }

    public void setAuditRiskTime(Date auditRiskTime) {
        this.auditRiskTime = auditRiskTime;
    }

    public String getAuditRiskReson() {
        return auditRiskReson;
    }

    public void setAuditRiskReson(String auditRiskReson) {
        this.auditRiskReson = auditRiskReson == null ? null : auditRiskReson.trim();
    }

    public Long getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(Long auditUserId) {
        this.auditUserId = auditUserId;
    }

    public Date getAuditUserTime() {
        return auditUserTime;
    }

    public void setAuditUserTime(Date auditUserTime) {
        this.auditUserTime = auditUserTime;
    }

    public String getAuditUserReson() {
        return auditUserReson;
    }

    public void setAuditUserReson(String auditUserReson) {
        this.auditUserReson = auditUserReson == null ? null : auditUserReson.trim();
    }

    public Long getPayUserId() {
        return payUserId;
    }

    public void setPayUserId(Long payUserId) {
        this.payUserId = payUserId;
    }

    public Byte getTradeMode() {
        return tradeMode;
    }

    public void setTradeMode(Byte tradeMode) {
        this.tradeMode = tradeMode;
    }

    public String getApplyCity() {
        return applyCity;
    }

    public void setApplyCity(String applyCity) {
        this.applyCity = applyCity == null ? null : applyCity.trim();
    }

    public Integer getCouponsId() {
        return couponsId;
    }

    public void setCouponsId(Integer couponsId) {
        this.couponsId = couponsId;
    }

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode == null ? null : imageCode.trim();
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
}