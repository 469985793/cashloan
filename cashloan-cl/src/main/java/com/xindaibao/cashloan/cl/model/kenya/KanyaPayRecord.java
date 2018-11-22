package com.xindaibao.cashloan.cl.model.kenya;

import java.math.BigDecimal;
import java.util.Date;

public class KanyaPayRecord {
    private Long id;
    private long loanRecordId;
    private String loanRecordNo;
    private String indentNo;
    private BigDecimal amount;
    private Date payTime;
    private String rtnMsg;
    private String wayCode;
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

    public long getLoanRecordId() {
        return loanRecordId;
    }

    public void setLoanRecordId(long loanRecordId) {
        this.loanRecordId = loanRecordId;
    }

    public String getLoanRecordNo() {
        return loanRecordNo;
    }

    public void setLoanRecordNo(String loanRecordNo) {
        this.loanRecordNo = loanRecordNo;
    }

    public String getIndentNo() {
        return indentNo;
    }

    public void setIndentNo(String indentNo) {
        this.indentNo = indentNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getRtnMsg() {
        return rtnMsg;
    }

    public void setRtnMsg(String rtnMsg) {
        this.rtnMsg = rtnMsg;
    }

    public String getWayCode() {
        return wayCode;
    }

    public void setWayCode(String wayCode) {
        this.wayCode = wayCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
