package com.xindaibao.cashloan.core.model;

import java.io.Serializable;
import java.util.Date;

public class KanyaUser {
    private Long id;

    private String userName;

    private String mobile;

    private String email;

    private String password;

    private String payPassword;

    private String iconImgcode;

    private String channelCode;

    private String inviteCode;

    private String registerCode;

    private Byte status;

    private Date createdTime;

    private Date updatedTime;

    //邀请人信息模块所需的子查询字段
    private String  firstName;//用户第一名字
    private String lastName;//用户最后名字
    private Integer borrowingCount;//借款次数
    private Integer reimbursementCount;//还款次数
    private Integer  overdueCount;//逾期次数

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getBorrowingCount() {
        return borrowingCount;
    }

    public void setBorrowingCount(Integer borrowingCount) {
        this.borrowingCount = borrowingCount;
    }

    public Integer getReimbursementCount() {
        return reimbursementCount;
    }

    public void setReimbursementCount(Integer reimbursementCount) {
        this.reimbursementCount = reimbursementCount;
    }

    public Integer getOverdueCount() {
        return overdueCount;
    }

    public void setOverdueCount(Integer overdueCount) {
        this.overdueCount = overdueCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword == null ? null : payPassword.trim();
    }

    public String getIconImgcode() {
        return iconImgcode;
    }

    public void setIconImgcode(String iconImgcode) {
        this.iconImgcode = iconImgcode == null ? null : iconImgcode.trim();
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode == null ? null : inviteCode.trim();
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode == null ? null : registerCode.trim();
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