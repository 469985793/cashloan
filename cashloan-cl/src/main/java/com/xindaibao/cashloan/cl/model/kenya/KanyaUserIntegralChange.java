package com.xindaibao.cashloan.cl.model.kenya;

import java.util.Date;

public class KanyaUserIntegralChange {
    private Long id;

    private Long uid;

    private Byte changeNumber;

    private String changeChannel;

    private Byte type;

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

    public Byte getChangeNumber() {
        return changeNumber;
    }

    public void setChangeNumber(Byte changeNumber) {
        this.changeNumber = changeNumber;
    }

    public String getChangeChannel() {
        return changeChannel;
    }

    public void setChangeChannel(String changeChannel) {
        this.changeChannel = changeChannel == null ? null : changeChannel.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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