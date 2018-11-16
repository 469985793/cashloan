package com.xindaibao.cashloan.cl.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "cl_mohe_behavior_analysis")
public class ClMoheBehaviorAnalysis implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 数据获取任务id (关联)
     */
    @Column(name = "task_id")
    private String taskId;

    @Column(name = "user_id")
    private Long userId;
    /**
     * 近6个月110通话情况
     */
    @Column(name = "call_110_analysis_6month")
    private String call110Analysis6month;

    /**
     * 近6个月120通话情况
     */
    @Column(name = "call_120_analysis_6month")
    private String call120Analysis6month;

    /**
     * 近6个月澳门电话通话情况
     */
    @Column(name = "call_macau_analysis_6month")
    private String callMacauAnalysis6month;

    /**
     * 近6个月律师号码通话情况
     */
    @Column(name = "call_lawyer_analysis_6month")
    private String callLawyerAnalysis6month;

    /**
     * 近6个月小贷号码联系情况
     */
    @Column(name = "loan_contact_analysis_6month")
    private String loanContactAnalysis6month;

    /**
     * 近6个月催收号码联系情况
     */
    @Column(name = "collection_contact_analysis_6month")
    private String collectionContactAnalysis6month;

    /**
     * 号码入网时间
     */
    @Column(name = "mobile_net_age_analysis")
    private String mobileNetAgeAnalysis;

    /**
     * 近6个月互通号码数量
     */
    @Column(name = "mutual_number_analysis_6month")
    private String mutualNumberAnalysis6month;

    /**
     * 近6个月深夜活动情况
     */
    @Column(name = "late_night_analysis_6month")
    private String lateNightAnalysis6month;

    /**
     * 近6个月手机静默情况
     */
    @Column(name = "mobile_silence_analysis_6month")
    private String mobileSilenceAnalysis6month;

    /**
     * 近6个月紧急联系人1通话情况
     */
    @Column(name = "emergency_contact1_analysis_6month")
    private String emergencyContact1Analysis6month;

    /**
     * 近6个月紧急联系人2通话情况
     */
    @Column(name = "emergency_contact2_analysis_6month")
    private String emergencyContact2Analysis6month;

    /**
     * 近6个月紧急联系人3通话情况
     */
    @Column(name = "emergency_contact3_analysis_6month")
    private String emergencyContact3Analysis6month;

    /**
     * 近6个月紧急联系人4通话情况
     */
    @Column(name = "emergency_contact4_analysis_6month")
    private String emergencyContact4Analysis6month;

    /**
     * 近6个月紧急联系人5通话情况
     */
    @Column(name = "emergency_contact5_analysis_6month")
    private String emergencyContact5Analysis6month;

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
     * 获取近6个月110通话情况
     *
     * @return call_110_analysis_6month - 近6个月110通话情况
     */
    public String getCall110Analysis6month() {
        return call110Analysis6month;
    }

    /**
     * 设置近6个月110通话情况
     *
     * @param call110Analysis6month 近6个月110通话情况
     */
    public void setCall110Analysis6month(String call110Analysis6month) {
        this.call110Analysis6month = call110Analysis6month;
    }

    /**
     * 获取近6个月120通话情况
     *
     * @return call_120_analysis_6month - 近6个月120通话情况
     */
    public String getCall120Analysis6month() {
        return call120Analysis6month;
    }

    /**
     * 设置近6个月120通话情况
     *
     * @param call120Analysis6month 近6个月120通话情况
     */
    public void setCall120Analysis6month(String call120Analysis6month) {
        this.call120Analysis6month = call120Analysis6month;
    }

    /**
     * 获取近6个月澳门电话通话情况
     *
     * @return call_macau_analysis_6month - 近6个月澳门电话通话情况
     */
    public String getCallMacauAnalysis6month() {
        return callMacauAnalysis6month;
    }

    /**
     * 设置近6个月澳门电话通话情况
     *
     * @param callMacauAnalysis6month 近6个月澳门电话通话情况
     */
    public void setCallMacauAnalysis6month(String callMacauAnalysis6month) {
        this.callMacauAnalysis6month = callMacauAnalysis6month;
    }

    /**
     * 获取近6个月律师号码通话情况
     *
     * @return call_lawyer_analysis_6month - 近6个月律师号码通话情况
     */
    public String getCallLawyerAnalysis6month() {
        return callLawyerAnalysis6month;
    }

    /**
     * 设置近6个月律师号码通话情况
     *
     * @param callLawyerAnalysis6month 近6个月律师号码通话情况
     */
    public void setCallLawyerAnalysis6month(String callLawyerAnalysis6month) {
        this.callLawyerAnalysis6month = callLawyerAnalysis6month;
    }

    /**
     * 获取近6个月小贷号码联系情况
     *
     * @return loan_contact_analysis_6month - 近6个月小贷号码联系情况
     */
    public String getLoanContactAnalysis6month() {
        return loanContactAnalysis6month;
    }

    /**
     * 设置近6个月小贷号码联系情况
     *
     * @param loanContactAnalysis6month 近6个月小贷号码联系情况
     */
    public void setLoanContactAnalysis6month(String loanContactAnalysis6month) {
        this.loanContactAnalysis6month = loanContactAnalysis6month;
    }

    /**
     * 获取近6个月催收号码联系情况
     *
     * @return collection_contact_analysis_6month - 近6个月催收号码联系情况
     */
    public String getCollectionContactAnalysis6month() {
        return collectionContactAnalysis6month;
    }

    /**
     * 设置近6个月催收号码联系情况
     *
     * @param collectionContactAnalysis6month 近6个月催收号码联系情况
     */
    public void setCollectionContactAnalysis6month(String collectionContactAnalysis6month) {
        this.collectionContactAnalysis6month = collectionContactAnalysis6month;
    }

    /**
     * 获取号码入网时间
     *
     * @return mobile_net_age_analysis - 号码入网时间
     */
    public String getMobileNetAgeAnalysis() {
        return mobileNetAgeAnalysis;
    }

    /**
     * 设置号码入网时间
     *
     * @param mobileNetAgeAnalysis 号码入网时间
     */
    public void setMobileNetAgeAnalysis(String mobileNetAgeAnalysis) {
        this.mobileNetAgeAnalysis = mobileNetAgeAnalysis;
    }

    /**
     * 获取近6个月互通号码数量
     *
     * @return mutual_number_analysis_6month - 近6个月互通号码数量
     */
    public String getMutualNumberAnalysis6month() {
        return mutualNumberAnalysis6month;
    }

    /**
     * 设置近6个月互通号码数量
     *
     * @param mutualNumberAnalysis6month 近6个月互通号码数量
     */
    public void setMutualNumberAnalysis6month(String mutualNumberAnalysis6month) {
        this.mutualNumberAnalysis6month = mutualNumberAnalysis6month;
    }

    /**
     * 获取近6个月深夜活动情况
     *
     * @return late_night_analysis_6month - 近6个月深夜活动情况
     */
    public String getLateNightAnalysis6month() {
        return lateNightAnalysis6month;
    }

    /**
     * 设置近6个月深夜活动情况
     *
     * @param lateNightAnalysis6month 近6个月深夜活动情况
     */
    public void setLateNightAnalysis6month(String lateNightAnalysis6month) {
        this.lateNightAnalysis6month = lateNightAnalysis6month;
    }

    /**
     * 获取近6个月手机静默情况
     *
     * @return mobile_silence_analysis_6month - 近6个月手机静默情况
     */
    public String getMobileSilenceAnalysis6month() {
        return mobileSilenceAnalysis6month;
    }

    /**
     * 设置近6个月手机静默情况
     *
     * @param mobileSilenceAnalysis6month 近6个月手机静默情况
     */
    public void setMobileSilenceAnalysis6month(String mobileSilenceAnalysis6month) {
        this.mobileSilenceAnalysis6month = mobileSilenceAnalysis6month;
    }

    /**
     * 获取近6个月紧急联系人1通话情况
     *
     * @return emergency_contact1_analysis_6month - 近6个月紧急联系人1通话情况
     */
    public String getEmergencyContact1Analysis6month() {
        return emergencyContact1Analysis6month;
    }

    /**
     * 设置近6个月紧急联系人1通话情况
     *
     * @param emergencyContact1Analysis6month 近6个月紧急联系人1通话情况
     */
    public void setEmergencyContact1Analysis6month(String emergencyContact1Analysis6month) {
        this.emergencyContact1Analysis6month = emergencyContact1Analysis6month;
    }

    /**
     * 获取近6个月紧急联系人2通话情况
     *
     * @return emergency_contact2_analysis_6month - 近6个月紧急联系人2通话情况
     */
    public String getEmergencyContact2Analysis6month() {
        return emergencyContact2Analysis6month;
    }

    /**
     * 设置近6个月紧急联系人2通话情况
     *
     * @param emergencyContact2Analysis6month 近6个月紧急联系人2通话情况
     */
    public void setEmergencyContact2Analysis6month(String emergencyContact2Analysis6month) {
        this.emergencyContact2Analysis6month = emergencyContact2Analysis6month;
    }

    /**
     * 获取近6个月紧急联系人3通话情况
     *
     * @return emergency_contact3_analysis_6month - 近6个月紧急联系人3通话情况
     */
    public String getEmergencyContact3Analysis6month() {
        return emergencyContact3Analysis6month;
    }

    /**
     * 设置近6个月紧急联系人3通话情况
     *
     * @param emergencyContact3Analysis6month 近6个月紧急联系人3通话情况
     */
    public void setEmergencyContact3Analysis6month(String emergencyContact3Analysis6month) {
        this.emergencyContact3Analysis6month = emergencyContact3Analysis6month;
    }

    /**
     * 获取近6个月紧急联系人4通话情况
     *
     * @return emergency_contact4_analysis_6month - 近6个月紧急联系人4通话情况
     */
    public String getEmergencyContact4Analysis6month() {
        return emergencyContact4Analysis6month;
    }

    /**
     * 设置近6个月紧急联系人4通话情况
     *
     * @param emergencyContact4Analysis6month 近6个月紧急联系人4通话情况
     */
    public void setEmergencyContact4Analysis6month(String emergencyContact4Analysis6month) {
        this.emergencyContact4Analysis6month = emergencyContact4Analysis6month;
    }

    /**
     * 获取近6个月紧急联系人5通话情况
     *
     * @return emergency_contact5_analysis_6month - 近6个月紧急联系人5通话情况
     */
    public String getEmergencyContact5Analysis6month() {
        return emergencyContact5Analysis6month;
    }

    /**
     * 设置近6个月紧急联系人5通话情况
     *
     * @param emergencyContact5Analysis6month 近6个月紧急联系人5通话情况
     */
    public void setEmergencyContact5Analysis6month(String emergencyContact5Analysis6month) {
        this.emergencyContact5Analysis6month = emergencyContact5Analysis6month;
    }
}