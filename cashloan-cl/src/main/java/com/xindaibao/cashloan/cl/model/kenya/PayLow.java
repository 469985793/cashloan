package com.xindaibao.cashloan.cl.model.kenya;

import java.util.Date;

public class PayLow {
    private Long id;

    private String indentNo;

    private Long payId;

    private String explain;

    private Long amount;

    private String wayCode;

    private String bankCardNo;

    private String bankNo;

    private String bankName;

    private String bankaccName;

    private String rtnCode;

    private String rtnMsg;

    private String rtnOrderNo;

    private Long rtnAmount;

    private Date rtnTime;

    private String remark;

    private Byte status;

    private Date createdTime;

    private Date updatedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIndentNo() {
        return indentNo;
    }

    public void setIndentNo(String indentNo) {
        this.indentNo = indentNo == null ? null : indentNo.trim();
    }

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain == null ? null : explain.trim();
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getWayCode() {
        return wayCode;
    }

    public void setWayCode(String wayCode) {
        this.wayCode = wayCode == null ? null : wayCode.trim();
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo == null ? null : bankCardNo.trim();
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

    public String getBankaccName() {
        return bankaccName;
    }

    public void setBankaccName(String bankaccName) {
        this.bankaccName = bankaccName == null ? null : bankaccName.trim();
    }

    public String getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(String rtnCode) {
        this.rtnCode = rtnCode == null ? null : rtnCode.trim();
    }

    public String getRtnMsg() {
        return rtnMsg;
    }

    public void setRtnMsg(String rtnMsg) {
        this.rtnMsg = rtnMsg == null ? null : rtnMsg.trim();
    }

    public String getRtnOrderNo() {
        return rtnOrderNo;
    }

    public void setRtnOrderNo(String rtnOrderNo) {
        this.rtnOrderNo = rtnOrderNo == null ? null : rtnOrderNo.trim();
    }

    public Long getRtnAmount() {
        return rtnAmount;
    }

    public void setRtnAmount(Long rtnAmount) {
        this.rtnAmount = rtnAmount;
    }

    public Date getRtnTime() {
        return rtnTime;
    }

    public void setRtnTime(Date rtnTime) {
        this.rtnTime = rtnTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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