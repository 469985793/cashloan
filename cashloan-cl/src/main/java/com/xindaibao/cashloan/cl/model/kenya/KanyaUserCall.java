package com.xindaibao.cashloan.cl.model.kenya;

import java.util.Date;

public class KanyaUserCall {
    private Long id;

    private Long uid;

    private Byte status;

    private Date createdTime;

    private Date updatedTime;

    private String callRecords;

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

    public String getCallRecords() {
        return callRecords;
    }

    public void setCallRecords(String callRecords) {
        this.callRecords = callRecords == null ? null : callRecords.trim();
    }
}