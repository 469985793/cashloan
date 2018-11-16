package com.xindaibao.cashloan.cl.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "cl_mohe_all_contact_stats_per_month")
public class ClMoheAllContactStatsPerMonth implements Serializable {
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
     * 月份
     */
    private String month;

    /**
     * 月通话号码数量
     */
    @Column(name = "contact_count")
    private String contactCount;

    /**
     * 月主叫号码数量
     */
    @Column(name = "contact_count_active")
    private String contactCountActive;

    /**
     * 月被叫号码数量
     */
    @Column(name = "contact_count_passive")
    private String contactCountPassive;

    /**
     * 月互通号码数量
     */
    @Column(name = "contact_count_mutual")
    private String contactCountMutual;

    /**
     * 月通话次数>=10的号码数量
     */
    @Column(name = "contact_count_call_count_over10")
    private String contactCountCallCountOver10;

    /**
     * 月通话次数
     */
    @Column(name = "call_count")
    private String callCount;

    /**
     * 月主叫通话次数
     */
    @Column(name = "call_count_active")
    private String callCountActive;

    /**
     * 月被叫通话次数
     */
    @Column(name = "call_count_passive")
    private String callCountPassive;

    /**
     * 月深夜通话次数
     */
    @Column(name = "call_count_late_night")
    private String callCountLateNight;

    /**
     * 月工作时间通话次数
     */
    @Column(name = "call_count_work_time")
    private String callCountWorkTime;

    /**
     * 月非工作时间通话次数
     */
    @Column(name = "call_count_offwork_time")
    private String callCountOffworkTime;

    /**
     * 月通话时长<1分钟的通话次数
     */
    @Column(name = "call_count_call_time_less1min")
    private String callCountCallTimeLess1min;

    /**
     * 月通话时长1-5分钟的通话次数
     */
    @Column(name = "call_count_call_time_1min5min")
    private String callCountCallTime1min5min;

    /**
     * 月通话时长5-10分钟的通话次数
     */
    @Column(name = "call_count_call_time_5min10min")
    private String callCountCallTime5min10min;

    /**
     * 月通话时长>=10分钟的通话次数
     */
    @Column(name = "call_count_call_time_over10min")
    private String callCountCallTimeOver10min;

    /**
     * 月通话时长
     */
    @Column(name = "call_time")
    private String callTime;

    /**
     * 月主叫通话时长
     */
    @Column(name = "call_time_active")
    private String callTimeActive;

    /**
     * 月被叫通话时长
     */
    @Column(name = "call_time_passive")
    private String callTimePassive;

    /**
     * 月短信次数
     */
    @Column(name = "msg_count")
    private String msgCount;

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
     * 获取月通话号码数量
     *
     * @return contact_count - 月通话号码数量
     */
    public String getContactCount() {
        return contactCount;
    }

    /**
     * 设置月通话号码数量
     *
     * @param contactCount 月通话号码数量
     */
    public void setContactCount(String contactCount) {
        this.contactCount = contactCount;
    }

    /**
     * 获取月主叫号码数量
     *
     * @return contact_count_active - 月主叫号码数量
     */
    public String getContactCountActive() {
        return contactCountActive;
    }

    /**
     * 设置月主叫号码数量
     *
     * @param contactCountActive 月主叫号码数量
     */
    public void setContactCountActive(String contactCountActive) {
        this.contactCountActive = contactCountActive;
    }

    /**
     * 获取月被叫号码数量
     *
     * @return contact_count_passive - 月被叫号码数量
     */
    public String getContactCountPassive() {
        return contactCountPassive;
    }

    /**
     * 设置月被叫号码数量
     *
     * @param contactCountPassive 月被叫号码数量
     */
    public void setContactCountPassive(String contactCountPassive) {
        this.contactCountPassive = contactCountPassive;
    }

    /**
     * 获取月互通号码数量
     *
     * @return contact_count_mutual - 月互通号码数量
     */
    public String getContactCountMutual() {
        return contactCountMutual;
    }

    /**
     * 设置月互通号码数量
     *
     * @param contactCountMutual 月互通号码数量
     */
    public void setContactCountMutual(String contactCountMutual) {
        this.contactCountMutual = contactCountMutual;
    }

    /**
     * 获取月通话次数>=10的号码数量
     *
     * @return contact_count_call_count_over10 - 月通话次数>=10的号码数量
     */
    public String getContactCountCallCountOver10() {
        return contactCountCallCountOver10;
    }

    /**
     * 设置月通话次数>=10的号码数量
     *
     * @param contactCountCallCountOver10 月通话次数>=10的号码数量
     */
    public void setContactCountCallCountOver10(String contactCountCallCountOver10) {
        this.contactCountCallCountOver10 = contactCountCallCountOver10;
    }

    /**
     * 获取月通话次数
     *
     * @return call_count - 月通话次数
     */
    public String getCallCount() {
        return callCount;
    }

    /**
     * 设置月通话次数
     *
     * @param callCount 月通话次数
     */
    public void setCallCount(String callCount) {
        this.callCount = callCount;
    }

    /**
     * 获取月主叫通话次数
     *
     * @return call_count_active - 月主叫通话次数
     */
    public String getCallCountActive() {
        return callCountActive;
    }

    /**
     * 设置月主叫通话次数
     *
     * @param callCountActive 月主叫通话次数
     */
    public void setCallCountActive(String callCountActive) {
        this.callCountActive = callCountActive;
    }

    /**
     * 获取月被叫通话次数
     *
     * @return call_count_passive - 月被叫通话次数
     */
    public String getCallCountPassive() {
        return callCountPassive;
    }

    /**
     * 设置月被叫通话次数
     *
     * @param callCountPassive 月被叫通话次数
     */
    public void setCallCountPassive(String callCountPassive) {
        this.callCountPassive = callCountPassive;
    }

    /**
     * 获取月深夜通话次数
     *
     * @return call_count_late_night - 月深夜通话次数
     */
    public String getCallCountLateNight() {
        return callCountLateNight;
    }

    /**
     * 设置月深夜通话次数
     *
     * @param callCountLateNight 月深夜通话次数
     */
    public void setCallCountLateNight(String callCountLateNight) {
        this.callCountLateNight = callCountLateNight;
    }

    /**
     * 获取月工作时间通话次数
     *
     * @return call_count_work_time - 月工作时间通话次数
     */
    public String getCallCountWorkTime() {
        return callCountWorkTime;
    }

    /**
     * 设置月工作时间通话次数
     *
     * @param callCountWorkTime 月工作时间通话次数
     */
    public void setCallCountWorkTime(String callCountWorkTime) {
        this.callCountWorkTime = callCountWorkTime;
    }

    /**
     * 获取月非工作时间通话次数
     *
     * @return call_count_offwork_time - 月非工作时间通话次数
     */
    public String getCallCountOffworkTime() {
        return callCountOffworkTime;
    }

    /**
     * 设置月非工作时间通话次数
     *
     * @param callCountOffworkTime 月非工作时间通话次数
     */
    public void setCallCountOffworkTime(String callCountOffworkTime) {
        this.callCountOffworkTime = callCountOffworkTime;
    }

    /**
     * 获取月通话时长<1分钟的通话次数
     *
     * @return call_count_call_time_less1min - 月通话时长<1分钟的通话次数
     */
    public String getCallCountCallTimeLess1min() {
        return callCountCallTimeLess1min;
    }

    /**
     * 设置月通话时长<1分钟的通话次数
     *
     * @param callCountCallTimeLess1min 月通话时长<1分钟的通话次数
     */
    public void setCallCountCallTimeLess1min(String callCountCallTimeLess1min) {
        this.callCountCallTimeLess1min = callCountCallTimeLess1min;
    }

    /**
     * 获取月通话时长1-5分钟的通话次数
     *
     * @return call_count_call_time_1min5min - 月通话时长1-5分钟的通话次数
     */
    public String getCallCountCallTime1min5min() {
        return callCountCallTime1min5min;
    }

    /**
     * 设置月通话时长1-5分钟的通话次数
     *
     * @param callCountCallTime1min5min 月通话时长1-5分钟的通话次数
     */
    public void setCallCountCallTime1min5min(String callCountCallTime1min5min) {
        this.callCountCallTime1min5min = callCountCallTime1min5min;
    }

    /**
     * 获取月通话时长5-10分钟的通话次数
     *
     * @return call_count_call_time_5min10min - 月通话时长5-10分钟的通话次数
     */
    public String getCallCountCallTime5min10min() {
        return callCountCallTime5min10min;
    }

    /**
     * 设置月通话时长5-10分钟的通话次数
     *
     * @param callCountCallTime5min10min 月通话时长5-10分钟的通话次数
     */
    public void setCallCountCallTime5min10min(String callCountCallTime5min10min) {
        this.callCountCallTime5min10min = callCountCallTime5min10min;
    }

    /**
     * 获取月通话时长>=10分钟的通话次数
     *
     * @return call_count_call_time_over10min - 月通话时长>=10分钟的通话次数
     */
    public String getCallCountCallTimeOver10min() {
        return callCountCallTimeOver10min;
    }

    /**
     * 设置月通话时长>=10分钟的通话次数
     *
     * @param callCountCallTimeOver10min 月通话时长>=10分钟的通话次数
     */
    public void setCallCountCallTimeOver10min(String callCountCallTimeOver10min) {
        this.callCountCallTimeOver10min = callCountCallTimeOver10min;
    }

    /**
     * 获取月通话时长
     *
     * @return call_time - 月通话时长
     */
    public String getCallTime() {
        return callTime;
    }

    /**
     * 设置月通话时长
     *
     * @param callTime 月通话时长
     */
    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    /**
     * 获取月主叫通话时长
     *
     * @return call_time_active - 月主叫通话时长
     */
    public String getCallTimeActive() {
        return callTimeActive;
    }

    /**
     * 设置月主叫通话时长
     *
     * @param callTimeActive 月主叫通话时长
     */
    public void setCallTimeActive(String callTimeActive) {
        this.callTimeActive = callTimeActive;
    }

    /**
     * 获取月被叫通话时长
     *
     * @return call_time_passive - 月被叫通话时长
     */
    public String getCallTimePassive() {
        return callTimePassive;
    }

    /**
     * 设置月被叫通话时长
     *
     * @param callTimePassive 月被叫通话时长
     */
    public void setCallTimePassive(String callTimePassive) {
        this.callTimePassive = callTimePassive;
    }

    /**
     * 获取月短信次数
     *
     * @return msg_count - 月短信次数
     */
    public String getMsgCount() {
        return msgCount;
    }

    /**
     * 设置月短信次数
     *
     * @param msgCount 月短信次数
     */
    public void setMsgCount(String msgCount) {
        this.msgCount = msgCount;
    }
}