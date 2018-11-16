package com.xindaibao.cashloan.cl.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "cl_mohe_data_completeness")
public class ClMoheDataCompleteness implements Serializable {
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
     * 近1月通话数据是否完整
     */
    @Column(name = "is_call_data_complete_1month")
    private String isCallDataComplete1month;

    /**
     * 近3月通话数据是否完整
     */
    @Column(name = "is_call_data_complete_3month")
    private String isCallDataComplete3month;

    /**
     * 近6月通话数据是否完整
     */
    @Column(name = "is_call_data_complete_6month")
    private String isCallDataComplete6month;

    /**
     * 近1月短信数据是否完整
     */
    @Column(name = "is_msg_data_complete_1month")
    private String isMsgDataComplete1month;

    /**
     * 近3月短信数据是否完整
     */
    @Column(name = "is_msg_data_complete_3month")
    private String isMsgDataComplete3month;

    /**
     * 近6月短信数据是否完整
     */
    @Column(name = "is_msg_data_complete_6month")
    private String isMsgDataComplete6month;

    /**
     * 近1月消费数据是否完整
     */
    @Column(name = "is_consume_data_complete_1month")
    private String isConsumeDataComplete1month;

    /**
     * 近3月消费数据是否完整
     */
    @Column(name = "is_consume_data_complete_3month")
    private String isConsumeDataComplete3month;

    /**
     * 近6月消费数据是否完整
     */
    @Column(name = "is_consume_data_complete_6month")
    private String isConsumeDataComplete6month;

    /**
     * 每个月数据完整性
     */
    @Column(name = "data_completeness_per_month")
    private String dataCompletenessPerMonth;

    /**
     * 月份
     */
    private String month;

    /**
     * 月通话数据是否完整
     */
    @Column(name = "is_call_data_complete")
    private String isCallDataComplete;

    /**
     * 月短信数据是否完整
     */
    @Column(name = "is_msg_data_complete")
    private String isMsgDataComplete;

    /**
     * 月消费数据是否完整
     */
    @Column(name = "is_consume_data_complete")
    private String isConsumeDataComplete;

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
     * 获取近1月通话数据是否完整
     *
     * @return is_call_data_complete_1month - 近1月通话数据是否完整
     */
    public String getIsCallDataComplete1month() {
        return isCallDataComplete1month;
    }

    /**
     * 设置近1月通话数据是否完整
     *
     * @param isCallDataComplete1month 近1月通话数据是否完整
     */
    public void setIsCallDataComplete1month(String isCallDataComplete1month) {
        this.isCallDataComplete1month = isCallDataComplete1month;
    }

    /**
     * 获取近3月通话数据是否完整
     *
     * @return is_call_data_complete_3month - 近3月通话数据是否完整
     */
    public String getIsCallDataComplete3month() {
        return isCallDataComplete3month;
    }

    /**
     * 设置近3月通话数据是否完整
     *
     * @param isCallDataComplete3month 近3月通话数据是否完整
     */
    public void setIsCallDataComplete3month(String isCallDataComplete3month) {
        this.isCallDataComplete3month = isCallDataComplete3month;
    }

    /**
     * 获取近6月通话数据是否完整
     *
     * @return is_call_data_complete_6month - 近6月通话数据是否完整
     */
    public String getIsCallDataComplete6month() {
        return isCallDataComplete6month;
    }

    /**
     * 设置近6月通话数据是否完整
     *
     * @param isCallDataComplete6month 近6月通话数据是否完整
     */
    public void setIsCallDataComplete6month(String isCallDataComplete6month) {
        this.isCallDataComplete6month = isCallDataComplete6month;
    }

    /**
     * 获取近1月短信数据是否完整
     *
     * @return is_msg_data_complete_1month - 近1月短信数据是否完整
     */
    public String getIsMsgDataComplete1month() {
        return isMsgDataComplete1month;
    }

    /**
     * 设置近1月短信数据是否完整
     *
     * @param isMsgDataComplete1month 近1月短信数据是否完整
     */
    public void setIsMsgDataComplete1month(String isMsgDataComplete1month) {
        this.isMsgDataComplete1month = isMsgDataComplete1month;
    }

    /**
     * 获取近3月短信数据是否完整
     *
     * @return is_msg_data_complete_3month - 近3月短信数据是否完整
     */
    public String getIsMsgDataComplete3month() {
        return isMsgDataComplete3month;
    }

    /**
     * 设置近3月短信数据是否完整
     *
     * @param isMsgDataComplete3month 近3月短信数据是否完整
     */
    public void setIsMsgDataComplete3month(String isMsgDataComplete3month) {
        this.isMsgDataComplete3month = isMsgDataComplete3month;
    }

    /**
     * 获取近6月短信数据是否完整
     *
     * @return is_msg_data_complete_6month - 近6月短信数据是否完整
     */
    public String getIsMsgDataComplete6month() {
        return isMsgDataComplete6month;
    }

    /**
     * 设置近6月短信数据是否完整
     *
     * @param isMsgDataComplete6month 近6月短信数据是否完整
     */
    public void setIsMsgDataComplete6month(String isMsgDataComplete6month) {
        this.isMsgDataComplete6month = isMsgDataComplete6month;
    }

    /**
     * 获取近1月消费数据是否完整
     *
     * @return is_consume_data_complete_1month - 近1月消费数据是否完整
     */
    public String getIsConsumeDataComplete1month() {
        return isConsumeDataComplete1month;
    }

    /**
     * 设置近1月消费数据是否完整
     *
     * @param isConsumeDataComplete1month 近1月消费数据是否完整
     */
    public void setIsConsumeDataComplete1month(String isConsumeDataComplete1month) {
        this.isConsumeDataComplete1month = isConsumeDataComplete1month;
    }

    /**
     * 获取近3月消费数据是否完整
     *
     * @return is_consume_data_complete_3month - 近3月消费数据是否完整
     */
    public String getIsConsumeDataComplete3month() {
        return isConsumeDataComplete3month;
    }

    /**
     * 设置近3月消费数据是否完整
     *
     * @param isConsumeDataComplete3month 近3月消费数据是否完整
     */
    public void setIsConsumeDataComplete3month(String isConsumeDataComplete3month) {
        this.isConsumeDataComplete3month = isConsumeDataComplete3month;
    }

    /**
     * 获取近6月消费数据是否完整
     *
     * @return is_consume_data_complete_6month - 近6月消费数据是否完整
     */
    public String getIsConsumeDataComplete6month() {
        return isConsumeDataComplete6month;
    }

    /**
     * 设置近6月消费数据是否完整
     *
     * @param isConsumeDataComplete6month 近6月消费数据是否完整
     */
    public void setIsConsumeDataComplete6month(String isConsumeDataComplete6month) {
        this.isConsumeDataComplete6month = isConsumeDataComplete6month;
    }

    /**
     * 获取每个月数据完整性
     *
     * @return data_completeness_per_month - 每个月数据完整性
     */
    public String getDataCompletenessPerMonth() {
        return dataCompletenessPerMonth;
    }

    /**
     * 设置每个月数据完整性
     *
     * @param dataCompletenessPerMonth 每个月数据完整性
     */
    public void setDataCompletenessPerMonth(String dataCompletenessPerMonth) {
        this.dataCompletenessPerMonth = dataCompletenessPerMonth;
    }

    /**
     * 获取月份
     *
     * @return month - 月份
     */
    public String getMonth() {
        return month;
    }

    /**
     * 设置月份
     *
     * @param month 月份
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * 获取月通话数据是否完整
     *
     * @return is_call_data_complete - 月通话数据是否完整
     */
    public String getIsCallDataComplete() {
        return isCallDataComplete;
    }

    /**
     * 设置月通话数据是否完整
     *
     * @param isCallDataComplete 月通话数据是否完整
     */
    public void setIsCallDataComplete(String isCallDataComplete) {
        this.isCallDataComplete = isCallDataComplete;
    }

    /**
     * 获取月短信数据是否完整
     *
     * @return is_msg_data_complete - 月短信数据是否完整
     */
    public String getIsMsgDataComplete() {
        return isMsgDataComplete;
    }

    /**
     * 设置月短信数据是否完整
     *
     * @param isMsgDataComplete 月短信数据是否完整
     */
    public void setIsMsgDataComplete(String isMsgDataComplete) {
        this.isMsgDataComplete = isMsgDataComplete;
    }

    /**
     * 获取月消费数据是否完整
     *
     * @return is_consume_data_complete - 月消费数据是否完整
     */
    public String getIsConsumeDataComplete() {
        return isConsumeDataComplete;
    }

    /**
     * 设置月消费数据是否完整
     *
     * @param isConsumeDataComplete 月消费数据是否完整
     */
    public void setIsConsumeDataComplete(String isConsumeDataComplete) {
        this.isConsumeDataComplete = isConsumeDataComplete;
    }
}