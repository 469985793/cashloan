package com.xindaibao.cashloan.cl.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "cl_mohe_risk_contact_small_loan_stats")
public class ClMoheRiskContactSmallLoanStats implements Serializable {
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
     * 风险类型
     */
    @Column(name = "risk_type")
    private String riskType;

    /**
     * 近1月通话号码数量
     */
    @Column(name = "contact_count_1month")
    private String contactCount1month;

    /**
     * 近3月通话号码数量
     */
    @Column(name = "contact_count_3month")
    private String contactCount3month;

    /**
     * 近6月通话号码数量
     */
    @Column(name = "contact_count_6month")
    private String contactCount6month;

    /**
     * 近1月通话次数
     */
    @Column(name = "call_count_1month")
    private String callCount1month;

    /**
     * 近3月通话次数
     */
    @Column(name = "call_count_3month")
    private String callCount3month;

    /**
     * 近3月主叫通话次数
     */
    @Column(name = "call_count_active_3month")
    private String callCountActive3month;

    /**
     * 近3月被叫通话次数
     */
    @Column(name = "call_count_passive_3month")
    private String callCountPassive3month;

    /**
     * 近6月通话次数
     */
    @Column(name = "call_count_6month")
    private String callCount6month;

    /**
     * 近6月主叫通话次数
     */
    @Column(name = "call_count_active_6month")
    private String callCountActive6month;

    /**
     * 近6月被叫通话次数
     */
    @Column(name = "call_count_passive_6month")
    private String callCountPassive6month;

    /**
     * 近1月通话时长
     */
    @Column(name = "call_time_1month")
    private String callTime1month;

    /**
     * 近3月通话时长
     */
    @Column(name = "call_time_3month")
    private String callTime3month;

    /**
     * 近3月主叫通话时长
     */
    @Column(name = "call_time_active_3month")
    private String callTimeActive3month;

    /**
     * 近3月被叫通话时长
     */
    @Column(name = "call_time_passive_3month")
    private String callTimePassive3month;

    /**
     * 近6月通话时长
     */
    @Column(name = "call_time_6month")
    private String callTime6month;

    /**
     * 近6月主叫通话时长
     */
    @Column(name = "call_time_active_6month")
    private String callTimeActive6month;

    /**
     * 近6月被叫通话时长
     */
    @Column(name = "call_time_passive_6month")
    private String callTimePassive6month;

    /**
     * 近3月短信次数
     */
    @Column(name = "msg_count_3month")
    private String msgCount3month;

    /**
     * 近6月短信次数
     */
    @Column(name = "msg_count_6month")
    private String msgCount6month;

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
     * 获取风险类型
     *
     * @return risk_type - 风险类型
     */
    public String getRiskType() {
        return riskType;
    }

    /**
     * 设置风险类型
     *
     * @param riskType 风险类型
     */
    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    /**
     * 获取近1月通话号码数量
     *
     * @return contact_count_1month - 近1月通话号码数量
     */
    public String getContactCount1month() {
        return contactCount1month;
    }

    /**
     * 设置近1月通话号码数量
     *
     * @param contactCount1month 近1月通话号码数量
     */
    public void setContactCount1month(String contactCount1month) {
        this.contactCount1month = contactCount1month;
    }

    /**
     * 获取近3月通话号码数量
     *
     * @return contact_count_3month - 近3月通话号码数量
     */
    public String getContactCount3month() {
        return contactCount3month;
    }

    /**
     * 设置近3月通话号码数量
     *
     * @param contactCount3month 近3月通话号码数量
     */
    public void setContactCount3month(String contactCount3month) {
        this.contactCount3month = contactCount3month;
    }

    /**
     * 获取近6月通话号码数量
     *
     * @return contact_count_6month - 近6月通话号码数量
     */
    public String getContactCount6month() {
        return contactCount6month;
    }

    /**
     * 设置近6月通话号码数量
     *
     * @param contactCount6month 近6月通话号码数量
     */
    public void setContactCount6month(String contactCount6month) {
        this.contactCount6month = contactCount6month;
    }

    /**
     * 获取近1月通话次数
     *
     * @return call_count_1month - 近1月通话次数
     */
    public String getCallCount1month() {
        return callCount1month;
    }

    /**
     * 设置近1月通话次数
     *
     * @param callCount1month 近1月通话次数
     */
    public void setCallCount1month(String callCount1month) {
        this.callCount1month = callCount1month;
    }

    /**
     * 获取近3月通话次数
     *
     * @return call_count_3month - 近3月通话次数
     */
    public String getCallCount3month() {
        return callCount3month;
    }

    /**
     * 设置近3月通话次数
     *
     * @param callCount3month 近3月通话次数
     */
    public void setCallCount3month(String callCount3month) {
        this.callCount3month = callCount3month;
    }

    /**
     * 获取近3月主叫通话次数
     *
     * @return call_count_active_3month - 近3月主叫通话次数
     */
    public String getCallCountActive3month() {
        return callCountActive3month;
    }

    /**
     * 设置近3月主叫通话次数
     *
     * @param callCountActive3month 近3月主叫通话次数
     */
    public void setCallCountActive3month(String callCountActive3month) {
        this.callCountActive3month = callCountActive3month;
    }

    /**
     * 获取近3月被叫通话次数
     *
     * @return call_count_passive_3month - 近3月被叫通话次数
     */
    public String getCallCountPassive3month() {
        return callCountPassive3month;
    }

    /**
     * 设置近3月被叫通话次数
     *
     * @param callCountPassive3month 近3月被叫通话次数
     */
    public void setCallCountPassive3month(String callCountPassive3month) {
        this.callCountPassive3month = callCountPassive3month;
    }

    /**
     * 获取近6月通话次数
     *
     * @return call_count_6month - 近6月通话次数
     */
    public String getCallCount6month() {
        return callCount6month;
    }

    /**
     * 设置近6月通话次数
     *
     * @param callCount6month 近6月通话次数
     */
    public void setCallCount6month(String callCount6month) {
        this.callCount6month = callCount6month;
    }

    /**
     * 获取近6月主叫通话次数
     *
     * @return call_count_active_6month - 近6月主叫通话次数
     */
    public String getCallCountActive6month() {
        return callCountActive6month;
    }

    /**
     * 设置近6月主叫通话次数
     *
     * @param callCountActive6month 近6月主叫通话次数
     */
    public void setCallCountActive6month(String callCountActive6month) {
        this.callCountActive6month = callCountActive6month;
    }

    /**
     * 获取近6月被叫通话次数
     *
     * @return call_count_passive_6month - 近6月被叫通话次数
     */
    public String getCallCountPassive6month() {
        return callCountPassive6month;
    }

    /**
     * 设置近6月被叫通话次数
     *
     * @param callCountPassive6month 近6月被叫通话次数
     */
    public void setCallCountPassive6month(String callCountPassive6month) {
        this.callCountPassive6month = callCountPassive6month;
    }

    /**
     * 获取近1月通话时长
     *
     * @return call_time_1month - 近1月通话时长
     */
    public String getCallTime1month() {
        return callTime1month;
    }

    /**
     * 设置近1月通话时长
     *
     * @param callTime1month 近1月通话时长
     */
    public void setCallTime1month(String callTime1month) {
        this.callTime1month = callTime1month;
    }

    /**
     * 获取近3月通话时长
     *
     * @return call_time_3month - 近3月通话时长
     */
    public String getCallTime3month() {
        return callTime3month;
    }

    /**
     * 设置近3月通话时长
     *
     * @param callTime3month 近3月通话时长
     */
    public void setCallTime3month(String callTime3month) {
        this.callTime3month = callTime3month;
    }

    /**
     * 获取近3月主叫通话时长
     *
     * @return call_time_active_3month - 近3月主叫通话时长
     */
    public String getCallTimeActive3month() {
        return callTimeActive3month;
    }

    /**
     * 设置近3月主叫通话时长
     *
     * @param callTimeActive3month 近3月主叫通话时长
     */
    public void setCallTimeActive3month(String callTimeActive3month) {
        this.callTimeActive3month = callTimeActive3month;
    }

    /**
     * 获取近3月被叫通话时长
     *
     * @return call_time_passive_3month - 近3月被叫通话时长
     */
    public String getCallTimePassive3month() {
        return callTimePassive3month;
    }

    /**
     * 设置近3月被叫通话时长
     *
     * @param callTimePassive3month 近3月被叫通话时长
     */
    public void setCallTimePassive3month(String callTimePassive3month) {
        this.callTimePassive3month = callTimePassive3month;
    }

    /**
     * 获取近6月通话时长
     *
     * @return call_time_6month - 近6月通话时长
     */
    public String getCallTime6month() {
        return callTime6month;
    }

    /**
     * 设置近6月通话时长
     *
     * @param callTime6month 近6月通话时长
     */
    public void setCallTime6month(String callTime6month) {
        this.callTime6month = callTime6month;
    }

    /**
     * 获取近6月主叫通话时长
     *
     * @return call_time_active_6month - 近6月主叫通话时长
     */
    public String getCallTimeActive6month() {
        return callTimeActive6month;
    }

    /**
     * 设置近6月主叫通话时长
     *
     * @param callTimeActive6month 近6月主叫通话时长
     */
    public void setCallTimeActive6month(String callTimeActive6month) {
        this.callTimeActive6month = callTimeActive6month;
    }

    /**
     * 获取近6月被叫通话时长
     *
     * @return call_time_passive_6month - 近6月被叫通话时长
     */
    public String getCallTimePassive6month() {
        return callTimePassive6month;
    }

    /**
     * 设置近6月被叫通话时长
     *
     * @param callTimePassive6month 近6月被叫通话时长
     */
    public void setCallTimePassive6month(String callTimePassive6month) {
        this.callTimePassive6month = callTimePassive6month;
    }

    /**
     * 获取近3月短信次数
     *
     * @return msg_count_3month - 近3月短信次数
     */
    public String getMsgCount3month() {
        return msgCount3month;
    }

    /**
     * 设置近3月短信次数
     *
     * @param msgCount3month 近3月短信次数
     */
    public void setMsgCount3month(String msgCount3month) {
        this.msgCount3month = msgCount3month;
    }

    /**
     * 获取近6月短信次数
     *
     * @return msg_count_6month - 近6月短信次数
     */
    public String getMsgCount6month() {
        return msgCount6month;
    }

    /**
     * 设置近6月短信次数
     *
     * @param msgCount6month 近6月短信次数
     */
    public void setMsgCount6month(String msgCount6month) {
        this.msgCount6month = msgCount6month;
    }
}