package com.xindaibao.cashloan.cl.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@Table(name = "cl_mohe_info_check")
public class ClMoheInfoCheck implements Serializable {
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
     * 手机号账户状态是否正常
     */
    @Column(name = "is_mobile_status_active")
    private String isMobileStatusActive;

    /**
     * 身份证号码是否有效
     */
    @Column(name = "is_identity_code_valid")
    private String isIdentityCodeValid;

    /**
     * 入网时长是否大于3个月
     */
    @Column(name = "is_net_age_over_3month")
    private String isNetAgeOver3month;

    /**
     * 近6个月内是否和紧急联系人1通话
     */
    @Column(name = "is_contact1_called_6month")
    private String isContact1Called6month;

    /**
     * 近6个月内是否和紧急联系人2通话
     */
    @Column(name = "is_contact2_called_6month")
    private String isContact2Called6month;

    /**
     * 近6个月内是否和紧急联系人3通话
     */
    @Column(name = "is_contact3_called_6month")
    private String isContact3Called6month;

    /**
     * 近6个月内是否和紧急联系人4通话
     */
    @Column(name = "is_contact4_called_6month")
    private String isContact4Called6month;

    /**
     * 近6个月内是否和紧急联系人5通话
     */
    @Column(name = "is_contact5_called_6month")
    private String isContact5Called6month;

    /**
     * 近6个月内是否和家庭电话通话
     */
    @Column(name = "is_home_tel_called_6month")
    private String isHomeTelCalled6month;

    /**
     * 近6个月内是否和工作电话通话
     */
    @Column(name = "is_work_tel_called_6month")
    private String isWorkTelCalled6month;

    /**
     * 近1个月常用通话地是否和号码归属地一致
     */
    @Column(name = "is_net_addr_call_addr_1month")
    private String isNetAddrCallAddr1month;

    /**
     * 近3个月常用通话地是否和号码归属地一致
     */
    @Column(name = "is_net_addr_call_addr_3month")
    private String isNetAddrCallAddr3month;

    /**
     * 近6个月常用通话地是否和号码归属地一致
     */
    @Column(name = "is_net_addr_call_addr_6month")
    private String isNetAddrCallAddr6month;

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
     * 获取手机号账户状态是否正常
     *
     * @return is_mobile_status_active - 手机号账户状态是否正常
     */
    public String getIsMobileStatusActive() {
        return isMobileStatusActive;
    }

    /**
     * 设置手机号账户状态是否正常
     *
     * @param isMobileStatusActive 手机号账户状态是否正常
     */
    public void setIsMobileStatusActive(String isMobileStatusActive) {
        this.isMobileStatusActive = isMobileStatusActive;
    }

    /**
     * 获取身份证号码是否有效
     *
     * @return is_identity_code_valid - 身份证号码是否有效
     */
    public String getIsIdentityCodeValid() {
        return isIdentityCodeValid;
    }

    /**
     * 设置身份证号码是否有效
     *
     * @param isIdentityCodeValid 身份证号码是否有效
     */
    public void setIsIdentityCodeValid(String isIdentityCodeValid) {
        this.isIdentityCodeValid = isIdentityCodeValid;
    }

    /**
     * 获取入网时长是否大于3个月
     *
     * @return is_net_age_over_3month - 入网时长是否大于3个月
     */
    public String getIsNetAgeOver3month() {
        return isNetAgeOver3month;
    }

    /**
     * 设置入网时长是否大于3个月
     *
     * @param isNetAgeOver3month 入网时长是否大于3个月
     */
    public void setIsNetAgeOver3month(String isNetAgeOver3month) {
        this.isNetAgeOver3month = isNetAgeOver3month;
    }

    /**
     * 获取近6个月内是否和紧急联系人1通话
     *
     * @return is_contact1_called_6month - 近6个月内是否和紧急联系人1通话
     */
    public String getIsContact1Called6month() {
        return isContact1Called6month;
    }

    /**
     * 设置近6个月内是否和紧急联系人1通话
     *
     * @param isContact1Called6month 近6个月内是否和紧急联系人1通话
     */
    public void setIsContact1Called6month(String isContact1Called6month) {
        this.isContact1Called6month = isContact1Called6month;
    }

    /**
     * 获取近6个月内是否和紧急联系人2通话
     *
     * @return is_contact2_called_6month - 近6个月内是否和紧急联系人2通话
     */
    public String getIsContact2Called6month() {
        return isContact2Called6month;
    }

    /**
     * 设置近6个月内是否和紧急联系人2通话
     *
     * @param isContact2Called6month 近6个月内是否和紧急联系人2通话
     */
    public void setIsContact2Called6month(String isContact2Called6month) {
        this.isContact2Called6month = isContact2Called6month;
    }

    /**
     * 获取近6个月内是否和紧急联系人3通话
     *
     * @return is_contact3_called_6month - 近6个月内是否和紧急联系人3通话
     */
    public String getIsContact3Called6month() {
        return isContact3Called6month;
    }

    /**
     * 设置近6个月内是否和紧急联系人3通话
     *
     * @param isContact3Called6month 近6个月内是否和紧急联系人3通话
     */
    public void setIsContact3Called6month(String isContact3Called6month) {
        this.isContact3Called6month = isContact3Called6month;
    }

    /**
     * 获取近6个月内是否和紧急联系人4通话
     *
     * @return is_contact4_called_6month - 近6个月内是否和紧急联系人4通话
     */
    public String getIsContact4Called6month() {
        return isContact4Called6month;
    }

    /**
     * 设置近6个月内是否和紧急联系人4通话
     *
     * @param isContact4Called6month 近6个月内是否和紧急联系人4通话
     */
    public void setIsContact4Called6month(String isContact4Called6month) {
        this.isContact4Called6month = isContact4Called6month;
    }

    /**
     * 获取近6个月内是否和紧急联系人5通话
     *
     * @return is_contact5_called_6month - 近6个月内是否和紧急联系人5通话
     */
    public String getIsContact5Called6month() {
        return isContact5Called6month;
    }

    /**
     * 设置近6个月内是否和紧急联系人5通话
     *
     * @param isContact5Called6month 近6个月内是否和紧急联系人5通话
     */
    public void setIsContact5Called6month(String isContact5Called6month) {
        this.isContact5Called6month = isContact5Called6month;
    }

    /**
     * 获取近6个月内是否和家庭电话通话
     *
     * @return is_home_tel_called_6month - 近6个月内是否和家庭电话通话
     */
    public String getIsHomeTelCalled6month() {
        return isHomeTelCalled6month;
    }

    /**
     * 设置近6个月内是否和家庭电话通话
     *
     * @param isHomeTelCalled6month 近6个月内是否和家庭电话通话
     */
    public void setIsHomeTelCalled6month(String isHomeTelCalled6month) {
        this.isHomeTelCalled6month = isHomeTelCalled6month;
    }

    /**
     * 获取近6个月内是否和工作电话通话
     *
     * @return is_work_tel_called_6month - 近6个月内是否和工作电话通话
     */
    public String getIsWorkTelCalled6month() {
        return isWorkTelCalled6month;
    }

    /**
     * 设置近6个月内是否和工作电话通话
     *
     * @param isWorkTelCalled6month 近6个月内是否和工作电话通话
     */
    public void setIsWorkTelCalled6month(String isWorkTelCalled6month) {
        this.isWorkTelCalled6month = isWorkTelCalled6month;
    }

    /**
     * 获取近1个月常用通话地是否和号码归属地一致
     *
     * @return is_net_addr_call_addr_1month - 近1个月常用通话地是否和号码归属地一致
     */
    public String getIsNetAddrCallAddr1month() {
        return isNetAddrCallAddr1month;
    }

    /**
     * 设置近1个月常用通话地是否和号码归属地一致
     *
     * @param isNetAddrCallAddr1month 近1个月常用通话地是否和号码归属地一致
     */
    public void setIsNetAddrCallAddr1month(String isNetAddrCallAddr1month) {
        this.isNetAddrCallAddr1month = isNetAddrCallAddr1month;
    }

    /**
     * 获取近3个月常用通话地是否和号码归属地一致
     *
     * @return is_net_addr_call_addr_3month - 近3个月常用通话地是否和号码归属地一致
     */
    public String getIsNetAddrCallAddr3month() {
        return isNetAddrCallAddr3month;
    }

    /**
     * 设置近3个月常用通话地是否和号码归属地一致
     *
     * @param isNetAddrCallAddr3month 近3个月常用通话地是否和号码归属地一致
     */
    public void setIsNetAddrCallAddr3month(String isNetAddrCallAddr3month) {
        this.isNetAddrCallAddr3month = isNetAddrCallAddr3month;
    }

    /**
     * 获取近6个月常用通话地是否和号码归属地一致
     *
     * @return is_net_addr_call_addr_6month - 近6个月常用通话地是否和号码归属地一致
     */
    public String getIsNetAddrCallAddr6month() {
        return isNetAddrCallAddr6month;
    }

    /**
     * 设置近6个月常用通话地是否和号码归属地一致
     *
     * @param isNetAddrCallAddr6month 近6个月常用通话地是否和号码归属地一致
     */
    public void setIsNetAddrCallAddr6month(String isNetAddrCallAddr6month) {
        this.isNetAddrCallAddr6month = isNetAddrCallAddr6month;
    }
}