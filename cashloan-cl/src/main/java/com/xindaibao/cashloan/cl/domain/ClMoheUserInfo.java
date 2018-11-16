package com.xindaibao.cashloan.cl.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "cl_mohe_user_info")
public class ClMoheUserInfo implements Serializable {
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
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 身份证号码
     */
    @Column(name = "identity_code")
    private String identityCode;

    /**
     * 年龄
     */
    private String age;

    /**
     * 性别
     */
    private String gender;

    /**
     * 籍贯
     */
    private String hometown;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 家庭地址
     */
    @Column(name = "home_addr")
    private String homeAddr;

    /**
     * 家庭电话
     */
    @Column(name = "home_tel")
    private String homeTel;

    /**
     * 工作地址
     */
    @Column(name = "work_addr")
    private String workAddr;

    /**
     * 工作电话
     */
    @Column(name = "work_tel")
    private String workTel;

    /**
     * 工作单位
     */
    @Column(name = "company_name")
    private String companyName;

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
     * 获取真实姓名
     *
     * @return real_name - 真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置真实姓名
     *
     * @param realName 真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取身份证号码
     *
     * @return identity_code - 身份证号码
     */
    public String getIdentityCode() {
        return identityCode;
    }

    /**
     * 设置身份证号码
     *
     * @param identityCode 身份证号码
     */
    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public String getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * 获取性别
     *
     * @return gender - 性别
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置性别
     *
     * @param gender 性别
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取籍贯
     *
     * @return hometown - 籍贯
     */
    public String getHometown() {
        return hometown;
    }

    /**
     * 设置籍贯
     *
     * @param hometown 籍贯
     */
    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取家庭地址
     *
     * @return home_addr - 家庭地址
     */
    public String getHomeAddr() {
        return homeAddr;
    }

    /**
     * 设置家庭地址
     *
     * @param homeAddr 家庭地址
     */
    public void setHomeAddr(String homeAddr) {
        this.homeAddr = homeAddr;
    }

    /**
     * 获取家庭电话
     *
     * @return home_tel - 家庭电话
     */
    public String getHomeTel() {
        return homeTel;
    }

    /**
     * 设置家庭电话
     *
     * @param homeTel 家庭电话
     */
    public void setHomeTel(String homeTel) {
        this.homeTel = homeTel;
    }

    /**
     * 获取工作地址
     *
     * @return work_addr - 工作地址
     */
    public String getWorkAddr() {
        return workAddr;
    }

    /**
     * 设置工作地址
     *
     * @param workAddr 工作地址
     */
    public void setWorkAddr(String workAddr) {
        this.workAddr = workAddr;
    }

    /**
     * 获取工作电话
     *
     * @return work_tel - 工作电话
     */
    public String getWorkTel() {
        return workTel;
    }

    /**
     * 设置工作电话
     *
     * @param workTel 工作电话
     */
    public void setWorkTel(String workTel) {
        this.workTel = workTel;
    }

    /**
     * 获取工作单位
     *
     * @return company_name - 工作单位
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置工作单位
     *
     * @param companyName 工作单位
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}