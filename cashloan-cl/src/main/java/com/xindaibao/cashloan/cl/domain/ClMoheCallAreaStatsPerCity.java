package com.xindaibao.cashloan.cl.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "cl_mohe_call_area_stats_per_city")
public class ClMoheCallAreaStatsPerCity implements Serializable {
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
     * 通话地城市
     */
    @Column(name = "call_area_city")
    private String callAreaCity;

    /**
     * 通话地排名
     */
    @Column(name = "call_area_seq_no")
    private String callAreaSeqNo;

    /**
     * 近1月通话活跃天数
     */
    @Column(name = "active_day_1call_1month")
    private String activeDay1call1month;

    /**
     * 近1月主叫通话活跃天数
     */
    @Column(name = "active_day_1call_active_1month")
    private String activeDay1callActive1month;

    /**
     * 近3月通话活跃天数
     */
    @Column(name = "active_day_1call_3month")
    private String activeDay1call3month;

    /**
     * 近3月主叫通话活跃天数
     */
    @Column(name = "active_day_1call_active_3month")
    private String activeDay1callActive3month;

    /**
     * 近6月通话活跃天数
     */
    @Column(name = "active_day_1call_6month")
    private String activeDay1call6month;

    /**
     * 近6月主叫通话活跃天数
     */
    @Column(name = "active_day_1call_active_6month")
    private String activeDay1callActive6month;

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
     * 近3月工作日通话次数
     */
    @Column(name = "call_count_workday_3month")
    private String callCountWorkday3month;

    /**
     * 近3月节假日通话次数
     */
    @Column(name = "call_count_holiday_3month")
    private String callCountHoliday3month;

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
     * 近6月工作日通话次数
     */
    @Column(name = "call_count_workday_6month")
    private String callCountWorkday6month;

    /**
     * 近6月节假日通话次数
     */
    @Column(name = "call_count_holiday_6month")
    private String callCountHoliday6month;

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
     * 近1月连续通话活跃大于1天的次数
     */
    @Column(name = "continue_active_day_over1_1call_1month")
    private String continueActiveDayOver11call1month;

    /**
     * 近1月连续通话活跃大于3天的次数
     */
    @Column(name = "continue_active_day_over3_1call_1month")
    private String continueActiveDayOver31call1month;

    /**
     * 近3月连续通话活跃大于1天的次数
     */
    @Column(name = "continue_active_day_over1_1call_3month")
    private String continueActiveDayOver11call3month;

    /**
     * 近3月连续通话活跃大于3天的次数
     */
    @Column(name = "continue_active_day_over3_1call_3month")
    private String continueActiveDayOver31call3month;

    /**
     * 近6月连续通话活跃大于1天的次数
     */
    @Column(name = "continue_active_day_over1_1call_6month")
    private String continueActiveDayOver11call6month;

    /**
     * 近6月连续通话活跃大于3天的次数
     */
    @Column(name = "continue_active_day_over3_1call_6month")
    private String continueActiveDayOver31call6month;

    /**
     * 近1月最大连续通话活跃天数
     */
    @Column(name = "max_continue_active_day_1call_1month")
    private String maxContinueActiveDay1call1month;

    /**
     * 近3月最大连续通话活跃天数
     */
    @Column(name = "max_continue_active_day_1call_3month")
    private String maxContinueActiveDay1call3month;

    /**
     * 近6月最大连续通话活跃天数
     */
    @Column(name = "max_continue_active_day_1call_6month")
    private String maxContinueActiveDay1call6month;

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
     * 获取通话地城市
     *
     * @return call_area_city - 通话地城市
     */
    public String getCallAreaCity() {
        return callAreaCity;
    }

    /**
     * 设置通话地城市
     *
     * @param callAreaCity 通话地城市
     */
    public void setCallAreaCity(String callAreaCity) {
        this.callAreaCity = callAreaCity;
    }

    /**
     * 获取通话地排名
     *
     * @return call_area_seq_no - 通话地排名
     */
    public String getCallAreaSeqNo() {
        return callAreaSeqNo;
    }

    /**
     * 设置通话地排名
     *
     * @param callAreaSeqNo 通话地排名
     */
    public void setCallAreaSeqNo(String callAreaSeqNo) {
        this.callAreaSeqNo = callAreaSeqNo;
    }

    /**
     * 获取近1月通话活跃天数
     *
     * @return active_day_1call_1month - 近1月通话活跃天数
     */
    public String getActiveDay1call1month() {
        return activeDay1call1month;
    }

    /**
     * 设置近1月通话活跃天数
     *
     * @param activeDay1call1month 近1月通话活跃天数
     */
    public void setActiveDay1call1month(String activeDay1call1month) {
        this.activeDay1call1month = activeDay1call1month;
    }

    /**
     * 获取近1月主叫通话活跃天数
     *
     * @return active_day_1call_active_1month - 近1月主叫通话活跃天数
     */
    public String getActiveDay1callActive1month() {
        return activeDay1callActive1month;
    }

    /**
     * 设置近1月主叫通话活跃天数
     *
     * @param activeDay1callActive1month 近1月主叫通话活跃天数
     */
    public void setActiveDay1callActive1month(String activeDay1callActive1month) {
        this.activeDay1callActive1month = activeDay1callActive1month;
    }

    /**
     * 获取近3月通话活跃天数
     *
     * @return active_day_1call_3month - 近3月通话活跃天数
     */
    public String getActiveDay1call3month() {
        return activeDay1call3month;
    }

    /**
     * 设置近3月通话活跃天数
     *
     * @param activeDay1call3month 近3月通话活跃天数
     */
    public void setActiveDay1call3month(String activeDay1call3month) {
        this.activeDay1call3month = activeDay1call3month;
    }

    /**
     * 获取近3月主叫通话活跃天数
     *
     * @return active_day_1call_active_3month - 近3月主叫通话活跃天数
     */
    public String getActiveDay1callActive3month() {
        return activeDay1callActive3month;
    }

    /**
     * 设置近3月主叫通话活跃天数
     *
     * @param activeDay1callActive3month 近3月主叫通话活跃天数
     */
    public void setActiveDay1callActive3month(String activeDay1callActive3month) {
        this.activeDay1callActive3month = activeDay1callActive3month;
    }

    /**
     * 获取近6月通话活跃天数
     *
     * @return active_day_1call_6month - 近6月通话活跃天数
     */
    public String getActiveDay1call6month() {
        return activeDay1call6month;
    }

    /**
     * 设置近6月通话活跃天数
     *
     * @param activeDay1call6month 近6月通话活跃天数
     */
    public void setActiveDay1call6month(String activeDay1call6month) {
        this.activeDay1call6month = activeDay1call6month;
    }

    /**
     * 获取近6月主叫通话活跃天数
     *
     * @return active_day_1call_active_6month - 近6月主叫通话活跃天数
     */
    public String getActiveDay1callActive6month() {
        return activeDay1callActive6month;
    }

    /**
     * 设置近6月主叫通话活跃天数
     *
     * @param activeDay1callActive6month 近6月主叫通话活跃天数
     */
    public void setActiveDay1callActive6month(String activeDay1callActive6month) {
        this.activeDay1callActive6month = activeDay1callActive6month;
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
     * 获取近3月工作日通话次数
     *
     * @return call_count_workday_3month - 近3月工作日通话次数
     */
    public String getCallCountWorkday3month() {
        return callCountWorkday3month;
    }

    /**
     * 设置近3月工作日通话次数
     *
     * @param callCountWorkday3month 近3月工作日通话次数
     */
    public void setCallCountWorkday3month(String callCountWorkday3month) {
        this.callCountWorkday3month = callCountWorkday3month;
    }

    /**
     * 获取近3月节假日通话次数
     *
     * @return call_count_holiday_3month - 近3月节假日通话次数
     */
    public String getCallCountHoliday3month() {
        return callCountHoliday3month;
    }

    /**
     * 设置近3月节假日通话次数
     *
     * @param callCountHoliday3month 近3月节假日通话次数
     */
    public void setCallCountHoliday3month(String callCountHoliday3month) {
        this.callCountHoliday3month = callCountHoliday3month;
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
     * 获取近6月工作日通话次数
     *
     * @return call_count_workday_6month - 近6月工作日通话次数
     */
    public String getCallCountWorkday6month() {
        return callCountWorkday6month;
    }

    /**
     * 设置近6月工作日通话次数
     *
     * @param callCountWorkday6month 近6月工作日通话次数
     */
    public void setCallCountWorkday6month(String callCountWorkday6month) {
        this.callCountWorkday6month = callCountWorkday6month;
    }

    /**
     * 获取近6月节假日通话次数
     *
     * @return call_count_holiday_6month - 近6月节假日通话次数
     */
    public String getCallCountHoliday6month() {
        return callCountHoliday6month;
    }

    /**
     * 设置近6月节假日通话次数
     *
     * @param callCountHoliday6month 近6月节假日通话次数
     */
    public void setCallCountHoliday6month(String callCountHoliday6month) {
        this.callCountHoliday6month = callCountHoliday6month;
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
     * 获取近1月连续通话活跃大于1天的次数
     *
     * @return continue_active_day_over1_1call_1month - 近1月连续通话活跃大于1天的次数
     */
    public String getContinueActiveDayOver11call1month() {
        return continueActiveDayOver11call1month;
    }

    /**
     * 设置近1月连续通话活跃大于1天的次数
     *
     * @param continueActiveDayOver11call1month 近1月连续通话活跃大于1天的次数
     */
    public void setContinueActiveDayOver11call1month(String continueActiveDayOver11call1month) {
        this.continueActiveDayOver11call1month = continueActiveDayOver11call1month;
    }

    /**
     * 获取近1月连续通话活跃大于3天的次数
     *
     * @return continue_active_day_over3_1call_1month - 近1月连续通话活跃大于3天的次数
     */
    public String getContinueActiveDayOver31call1month() {
        return continueActiveDayOver31call1month;
    }

    /**
     * 设置近1月连续通话活跃大于3天的次数
     *
     * @param continueActiveDayOver31call1month 近1月连续通话活跃大于3天的次数
     */
    public void setContinueActiveDayOver31call1month(String continueActiveDayOver31call1month) {
        this.continueActiveDayOver31call1month = continueActiveDayOver31call1month;
    }

    /**
     * 获取近3月连续通话活跃大于1天的次数
     *
     * @return continue_active_day_over1_1call_3month - 近3月连续通话活跃大于1天的次数
     */
    public String getContinueActiveDayOver11call3month() {
        return continueActiveDayOver11call3month;
    }

    /**
     * 设置近3月连续通话活跃大于1天的次数
     *
     * @param continueActiveDayOver11call3month 近3月连续通话活跃大于1天的次数
     */
    public void setContinueActiveDayOver11call3month(String continueActiveDayOver11call3month) {
        this.continueActiveDayOver11call3month = continueActiveDayOver11call3month;
    }

    /**
     * 获取近3月连续通话活跃大于3天的次数
     *
     * @return continue_active_day_over3_1call_3month - 近3月连续通话活跃大于3天的次数
     */
    public String getContinueActiveDayOver31call3month() {
        return continueActiveDayOver31call3month;
    }

    /**
     * 设置近3月连续通话活跃大于3天的次数
     *
     * @param continueActiveDayOver31call3month 近3月连续通话活跃大于3天的次数
     */
    public void setContinueActiveDayOver31call3month(String continueActiveDayOver31call3month) {
        this.continueActiveDayOver31call3month = continueActiveDayOver31call3month;
    }

    /**
     * 获取近6月连续通话活跃大于1天的次数
     *
     * @return continue_active_day_over1_1call_6month - 近6月连续通话活跃大于1天的次数
     */
    public String getContinueActiveDayOver11call6month() {
        return continueActiveDayOver11call6month;
    }

    /**
     * 设置近6月连续通话活跃大于1天的次数
     *
     * @param continueActiveDayOver11call6month 近6月连续通话活跃大于1天的次数
     */
    public void setContinueActiveDayOver11call6month(String continueActiveDayOver11call6month) {
        this.continueActiveDayOver11call6month = continueActiveDayOver11call6month;
    }

    /**
     * 获取近6月连续通话活跃大于3天的次数
     *
     * @return continue_active_day_over3_1call_6month - 近6月连续通话活跃大于3天的次数
     */
    public String getContinueActiveDayOver31call6month() {
        return continueActiveDayOver31call6month;
    }

    /**
     * 设置近6月连续通话活跃大于3天的次数
     *
     * @param continueActiveDayOver31call6month 近6月连续通话活跃大于3天的次数
     */
    public void setContinueActiveDayOver31call6month(String continueActiveDayOver31call6month) {
        this.continueActiveDayOver31call6month = continueActiveDayOver31call6month;
    }

    /**
     * 获取近1月最大连续通话活跃天数
     *
     * @return max_continue_active_day_1call_1month - 近1月最大连续通话活跃天数
     */
    public String getMaxContinueActiveDay1call1month() {
        return maxContinueActiveDay1call1month;
    }

    /**
     * 设置近1月最大连续通话活跃天数
     *
     * @param maxContinueActiveDay1call1month 近1月最大连续通话活跃天数
     */
    public void setMaxContinueActiveDay1call1month(String maxContinueActiveDay1call1month) {
        this.maxContinueActiveDay1call1month = maxContinueActiveDay1call1month;
    }

    /**
     * 获取近3月最大连续通话活跃天数
     *
     * @return max_continue_active_day_1call_3month - 近3月最大连续通话活跃天数
     */
    public String getMaxContinueActiveDay1call3month() {
        return maxContinueActiveDay1call3month;
    }

    /**
     * 设置近3月最大连续通话活跃天数
     *
     * @param maxContinueActiveDay1call3month 近3月最大连续通话活跃天数
     */
    public void setMaxContinueActiveDay1call3month(String maxContinueActiveDay1call3month) {
        this.maxContinueActiveDay1call3month = maxContinueActiveDay1call3month;
    }

    /**
     * 获取近6月最大连续通话活跃天数
     *
     * @return max_continue_active_day_1call_6month - 近6月最大连续通话活跃天数
     */
    public String getMaxContinueActiveDay1call6month() {
        return maxContinueActiveDay1call6month;
    }

    /**
     * 设置近6月最大连续通话活跃天数
     *
     * @param maxContinueActiveDay1call6month 近6月最大连续通话活跃天数
     */
    public void setMaxContinueActiveDay1call6month(String maxContinueActiveDay1call6month) {
        this.maxContinueActiveDay1call6month = maxContinueActiveDay1call6month;
    }
}