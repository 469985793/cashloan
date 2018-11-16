package com.xindaibao.cashloan.cl.model.kenya;

import java.util.Date;

public class KanyaUserAddressBook {
    private Long id;

    private Long uid;

    private Byte status;

    private Date createdTime;

    private Date updatedTime;

    private String addressbook;

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

    public String getAddressbook() {
        return addressbook;
    }

    public void setAddressbook(String addressbook) {
        this.addressbook = addressbook == null ? null : addressbook.trim();
    }
}