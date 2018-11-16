package com.xindaibao.cashloan.cl.model.kenya;

import java.util.Date;

public class KanyaUserCredit {
    private Long id;

    private Long uid;

    private Integer limits;

    private Long totalAmount;

    private Integer totalTimes;

    private Integer normalTimes;

    private Integer dueTimes;

    private Byte badTimes;

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

    public Integer getLimits() {
        return limits;
    }

    public void setLimits(Integer limits) {
        this.limits = limits;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalTimes() {
        return totalTimes;
    }

    public void setTotalTimes(Integer totalTimes) {
        this.totalTimes = totalTimes;
    }

    public Integer getNormalTimes() {
        return normalTimes;
    }

    public void setNormalTimes(Integer normalTimes) {
        this.normalTimes = normalTimes;
    }

    public Integer getDueTimes() {
        return dueTimes;
    }

    public void setDueTimes(Integer dueTimes) {
        this.dueTimes = dueTimes;
    }

    public Byte getBadTimes() {
        return badTimes;
    }

    public void setBadTimes(Byte badTimes) {
        this.badTimes = badTimes;
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