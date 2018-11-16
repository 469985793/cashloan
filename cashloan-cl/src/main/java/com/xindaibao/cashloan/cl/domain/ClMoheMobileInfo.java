package com.xindaibao.cashloan.cl.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "cl_mohe_mobile_info")
public class ClMoheMobileInfo implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    /**
     * 数据获取任务id (关联)
     */
    @Column(name = "task_id")
    private String taskId;

    /**
     * 手机号
     */
    @Column(name = "user_mobile")
    private String userMobile;

    /**
     * 号码归属地
     */
    @Column(name = "mobile_net_addr")
    private String mobileNetAddr;

    /**
     * 运营商
     */
    @Column(name = "mobile_carrier")
    private String mobileCarrier;

    /**
     * 运营商登记姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 运营商登记身份证号码
     */
    @Column(name = "identity_code")
    private String identityCode;

    /**
     * 手机号账户状态
     */
    @Column(name = "account_status")
    private String accountStatus;

    /**
     * 账户余额
     */
    @Column(name = "account_balance")
    private String accountBalance;

    /**
     * 入网时间
     */
    @Column(name = "mobile_net_time")
    private String mobileNetTime;

    /**
     * 入网时长
     */
    @Column(name = "mobile_net_age")
    private String mobileNetAge;

    /**
     * 联系地址
     */
    @Column(name = "contact_addr")
    private String contactAddr;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取数据获取任务id (关联)
     *
     * @return task_id - 数据获取任务id (关联)
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 设置数据获取任务id (关联)
     *
     * @param taskId 数据获取任务id (关联)
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * 获取手机号
     *
     * @return user_mobile - 手机号
     */
    public String getUserMobile() {
        return userMobile;
    }

    /**
     * 设置手机号
     *
     * @param userMobile 手机号
     */
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    /**
     * 获取号码归属地
     *
     * @return mobile_net_addr - 号码归属地
     */
    public String getMobileNetAddr() {
        return mobileNetAddr;
    }

    /**
     * 设置号码归属地
     *
     * @param mobileNetAddr 号码归属地
     */
    public void setMobileNetAddr(String mobileNetAddr) {
        this.mobileNetAddr = mobileNetAddr;
    }

    /**
     * 获取运营商
     *
     * @return mobile_carrier - 运营商
     */
    public String getMobileCarrier() {
        return mobileCarrier;
    }

    /**
     * 设置运营商
     *
     * @param mobileCarrier 运营商
     */
    public void setMobileCarrier(String mobileCarrier) {
        this.mobileCarrier = mobileCarrier;
    }

    /**
     * 获取运营商登记姓名
     *
     * @return real_name - 运营商登记姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置运营商登记姓名
     *
     * @param realName 运营商登记姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取运营商登记身份证号码
     *
     * @return identity_code - 运营商登记身份证号码
     */
    public String getIdentityCode() {
        return identityCode;
    }

    /**
     * 设置运营商登记身份证号码
     *
     * @param identityCode 运营商登记身份证号码
     */
    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    /**
     * 获取手机号账户状态
     *
     * @return account_status - 手机号账户状态
     */
    public String getAccountStatus() {
        return accountStatus;
    }

    /**
     * 设置手机号账户状态
     *
     * @param accountStatus 手机号账户状态
     */
    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    /**
     * 获取账户余额
     *
     * @return account_balance - 账户余额
     */
    public String getAccountBalance() {
        return accountBalance;
    }

    /**
     * 设置账户余额
     *
     * @param accountBalance 账户余额
     */
    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    /**
     * 获取入网时间
     *
     * @return mobile_net_time - 入网时间
     */
    public String getMobileNetTime() {
        return mobileNetTime;
    }

    /**
     * 设置入网时间
     *
     * @param mobileNetTime 入网时间
     */
    public void setMobileNetTime(String mobileNetTime) {
        this.mobileNetTime = mobileNetTime;
    }

    /**
     * 获取入网时长
     *
     * @return mobile_net_age - 入网时长
     */
    public String getMobileNetAge() {
        return mobileNetAge;
    }

    /**
     * 设置入网时长
     *
     * @param mobileNetAge 入网时长
     */
    public void setMobileNetAge(String mobileNetAge) {
        this.mobileNetAge = mobileNetAge;
    }

    /**
     * 获取联系地址
     *
     * @return contact_addr - 联系地址
     */
    public String getContactAddr() {
        return contactAddr;
    }

    /**
     * 设置联系地址
     *
     * @param contactAddr 联系地址
     */
    public void setContactAddr(String contactAddr) {
        this.contactAddr = contactAddr;
    }
}