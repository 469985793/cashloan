package com.xindaibao.cashloan.core.model;

import java.util.Date;

public class KanyaUserLive extends KanyaUserJob{
    private Long id;

    private Long uid;

    private Byte liveBelong;

    private String liveAddress;

    private String liveState;

    private String liveCity;

    private Integer rentYear;

    private String liveTime;

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

    public Byte getLiveBelong() {
        return liveBelong;
    }

    public void setLiveBelong(Byte liveBelong) {
        this.liveBelong = liveBelong;
    }

    public String getLiveAddress() {
        return liveAddress;
    }

    public void setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress == null ? null : liveAddress.trim();
    }

    public String getLiveState() {
        return liveState;
    }

    public void setLiveState(String liveState) {
        this.liveState = liveState == null ? null : liveState.trim();
    }

    public String getLiveCity() {
        return liveCity;
    }

    public void setLiveCity(String liveCity) {
        this.liveCity = liveCity == null ? null : liveCity.trim();
    }

    public Integer getRentYear() {
        return rentYear;
    }

    public void setRentYear(Integer rentYear) {
        this.rentYear = rentYear;
    }

    public String getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(String liveTime) {
        this.liveTime = liveTime == null ? null : liveTime.trim();
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