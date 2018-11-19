package com.xindaibao.cashloan.cl.model.kenya;

import java.util.Date;

public class LoanRepayFlow {
    private Long id;

    private String indentNo;

    private Long loanId;

    private String wayCode;

    private String rtnCode;

    private String rtnMsg;

    private String rtnOrderNo;

    private Long rtnAmount;

    private Date rtnTime;

    private Byte status;

    private Date createdTime;

    private Date updatedTime;

    private String type;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public String getWayCode() {
        return wayCode;
    }

    public void setWayCode(String wayCode) {
        this.wayCode = wayCode == null ? null : wayCode.trim();
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