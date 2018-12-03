package com.xindaibao.cashloan.cl.model.kenya;

import java.math.BigDecimal;
import java.util.Date;

public class RepayFlow {
    private Long id;

    private String indentNo;

    private Long loanRecordId;

    private Long repayRecordId;

    private String loanRecordNo;

    private String repayRecordNo;

    private BigDecimal amount;

    private String wayCode;

    private Date completionTime;

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
        this.indentNo = indentNo;
    }

    public Long getLoanRecordId() {
        return loanRecordId;
    }

    public void setLoanRecordId(Long loanRecordId) {
        this.loanRecordId = loanRecordId;
    }

    public Long getRepayRecordId() {
        return repayRecordId;
    }

    public void setRepayRecordId(Long repayRecordId) {
        this.repayRecordId = repayRecordId;
    }

    public String getLoanRecordNo() {
        return loanRecordNo;
    }

    public void setLoanRecordNo(String loanRecordNo) {
        this.loanRecordNo = loanRecordNo;
    }

    public String getRepayRecordNo() {
        return repayRecordNo;
    }

    public void setRepayRecordNo(String repayRecordNo) {
        this.repayRecordNo = repayRecordNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getWayCode() {
        return wayCode;
    }

    public void setWayCode(String wayCode) {
        this.wayCode = wayCode;
    }

    public Date getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
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