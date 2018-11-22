package com.xindaibao.cashloan.cl.model.kenya;

import java.math.BigDecimal;
import java.util.Date;

public class KanyaPayFlow {

    private Long id;
    private long loanRecordId;
    private String loanRecordNo;
    private long payRecordId;
    private String payRecordNo;
    private String indentNo;
    private String wayCode;
    private BigDecimal amount;
    private String conversationNo;
    private BigDecimal transactionAmount;
    private String transactionNo;
    private Date completionTime;
    private String rtnMsg;
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

    public long getPayRecordId() {
        return payRecordId;
    }

    public void setPayRecordId(long payRecordId) {
        this.payRecordId = payRecordId;
    }

    public String getPayRecordNo() {
        return payRecordNo;
    }

    public void setPayRecordNo(String payRecordNo) {
        this.payRecordNo = payRecordNo;
    }

    public String getIndentNo() {
        return indentNo;
    }

    public void setIndentNo(String indentNo) {
        this.indentNo = indentNo;
    }

    public String getWayCode() {
        return wayCode;
    }

    public void setWayCode(String wayCode) {
        this.wayCode = wayCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getConversationNo() {
        return conversationNo;
    }

    public void setConversationNo(String conversationNo) {
        this.conversationNo = conversationNo;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public Date getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }

    public String getRtnMsg() {
        return rtnMsg;
    }

    public void setRtnMsg(String rtnMsg) {
        this.rtnMsg = rtnMsg;
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
