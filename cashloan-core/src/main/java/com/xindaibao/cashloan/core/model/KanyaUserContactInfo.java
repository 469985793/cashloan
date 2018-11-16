package com.xindaibao.cashloan.core.model;

import java.util.Date;

public class KanyaUserContactInfo extends KanyaUserLive{
    private Long id;

    private Long uid;

    private Byte familyMember;

    private String familyMemberName;

    private String familyMobilePhone;

    private Byte otheContact;

    private String otherContactName;

    private String otherContactMobile;

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

    public Byte getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(Byte familyMember) {
        this.familyMember = familyMember;
    }

    public String getFamilyMemberName() {
        return familyMemberName;
    }

    public void setFamilyMemberName(String familyMemberName) {
        this.familyMemberName = familyMemberName == null ? null : familyMemberName.trim();
    }

    public String getFamilyMobilePhone() {
        return familyMobilePhone;
    }

    public void setFamilyMobilePhone(String familyMobilePhone) {
        this.familyMobilePhone = familyMobilePhone == null ? null : familyMobilePhone.trim();
    }

    public Byte getOtheContact() {
        return otheContact;
    }

    public void setOtheContact(Byte otheContact) {
        this.otheContact = otheContact;
    }

    public String getOtherContactName() {
        return otherContactName;
    }

    public void setOtherContactName(String otherContactName) {
        this.otherContactName = otherContactName == null ? null : otherContactName.trim();
    }

    public String getOtherContactMobile() {
        return otherContactMobile;
    }

    public void setOtherContactMobile(String otherContactMobile) {
        this.otherContactMobile = otherContactMobile == null ? null : otherContactMobile.trim();
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