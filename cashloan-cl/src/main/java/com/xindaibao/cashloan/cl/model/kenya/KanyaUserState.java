package com.xindaibao.cashloan.cl.model.kenya;

import java.util.Date;

public class KanyaUserState {
    private Long id;

    private Long uid;

    private Byte infoFlag;

    private Date infoTime;

    private String infoRemark;

    private Byte jobFlag;

    private Date jobTime;

    private String jobRemark;

    private Byte liveFlag;

    private Date liveTime;

    private String liveRemark;

    private Byte contactInfoFlag;

    private Date contactInfoTime;

    private String contactInfoRemark;

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

    public Byte getInfoFlag() {
        return infoFlag;
    }

    public void setInfoFlag(Byte infoFlag) {
        this.infoFlag = infoFlag;
    }

    public Date getInfoTime() {
        return infoTime;
    }

    public void setInfoTime(Date infoTime) {
        this.infoTime = infoTime;
    }

    public String getInfoRemark() {
        return infoRemark;
    }

    public void setInfoRemark(String infoRemark) {
        this.infoRemark = infoRemark == null ? null : infoRemark.trim();
    }

    public Byte getJobFlag() {
        return jobFlag;
    }

    public void setJobFlag(Byte jobFlag) {
        this.jobFlag = jobFlag;
    }

    public Date getJobTime() {
        return jobTime;
    }

    public void setJobTime(Date jobTime) {
        this.jobTime = jobTime;
    }

    public String getJobRemark() {
        return jobRemark;
    }

    public void setJobRemark(String jobRemark) {
        this.jobRemark = jobRemark == null ? null : jobRemark.trim();
    }

    public Byte getLiveFlag() {
        return liveFlag;
    }

    public void setLiveFlag(Byte liveFlag) {
        this.liveFlag = liveFlag;
    }

    public Date getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(Date liveTime) {
        this.liveTime = liveTime;
    }

    public String getLiveRemark() {
        return liveRemark;
    }

    public void setLiveRemark(String liveRemark) {
        this.liveRemark = liveRemark == null ? null : liveRemark.trim();
    }

    public Byte getContactInfoFlag() {
        return contactInfoFlag;
    }

    public void setContactInfoFlag(Byte contactInfoFlag) {
        this.contactInfoFlag = contactInfoFlag;
    }

    public Date getContactInfoTime() {
        return contactInfoTime;
    }

    public void setContactInfoTime(Date contactInfoTime) {
        this.contactInfoTime = contactInfoTime;
    }

    public String getContactInfoRemark() {
        return contactInfoRemark;
    }

    public void setContactInfoRemark(String contactInfoRemark) {
        this.contactInfoRemark = contactInfoRemark == null ? null : contactInfoRemark.trim();
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