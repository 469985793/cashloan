package com.xindaibao.cashloan.cl.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "cl_mohe_contact_area_stats_per_city")
public class ClMoheContactAreaStatsPerCity implements Serializable {
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
     * 归属地城市
     */
    @Column(name = "contact_area_city")
    private String contactAreaCity;

    /**
     * 归属地排名
     */
    @Column(name = "contact_area_seq_no")
    private String contactAreaSeqNo;

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
     * 近3月主叫号码数量
     */
    @Column(name = "contact_count_active_3month")
    private String contactCountActive3month;

    /**
     * 近3月被叫号码数量
     */
    @Column(name = "contact_count_passive_3month")
    private String contactCountPassive3month;

    /**
     * 近3月互通号码数量
     */
    @Column(name = "contact_count_mutual_3month")
    private String contactCountMutual3month;

    /**
     * 近3月通话次数>=10的号码数量
     */
    @Column(name = "contact_count_call_count_over10_3month")
    private String contactCountCallCountOver103month;

    /**
     * 近6月通话号码数量
     */
    @Column(name = "contact_count_6month")
    private String contactCount6month;

    /**
     * 近6月主叫号码数量
     */
    @Column(name = "contact_count_active_6month")
    private String contactCountActive6month;

    /**
     * 近6月被叫号码数量
     */
    @Column(name = "contact_count_passive_6month")
    private String contactCountPassive6month;

    /**
     * 近6月互通号码数量
     */
    @Column(name = "contact_count_mutual_6month")
    private String contactCountMutual6month;

    /**
     * 近6月通话次数>=10的号码数量
     */
    @Column(name = "contact_count_call_count_over10_6month")
    private String contactCountCallCountOver106month;

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
     * 近3月深夜通话次数
     */
    @Column(name = "call_count_late_night_3month")
    private String callCountLateNight3month;

    /**
     * 近3月工作时间通话次数
     */
    @Column(name = "call_count_work_time_3month")
    private String callCountWorkTime3month;

    /**
     * 近3月非工作时间通话次数
     */
    @Column(name = "call_count_offwork_time_3month")
    private String callCountOffworkTime3month;

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
     * 近6月深夜通话次数
     */
    @Column(name = "call_count_late_night_6month")
    private String callCountLateNight6month;

    /**
     * 近6月工作时间通话次数
     */
    @Column(name = "call_count_work_time_6month")
    private String callCountWorkTime6month;

    /**
     * 近6月非工作时间通话次数
     */
    @Column(name = "call_count_offwork_time_6month")
    private String callCountOffworkTime6month;

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
     * 近6月通话时长<1分钟的通话次数
     */
    @Column(name = "call_count_call_time_less1min_6month")
    private String callCountCallTimeLess1min6month;

    /**
     * 近6月通话时长1-5分钟的通话次数
     */
    @Column(name = "call_count_call_time_1min5min_6month")
    private String callCountCallTime1min5min6month;

    /**
     * 近6月通话时长5-10分钟的通话次数
     */
    @Column(name = "call_count_call_time_5min10min_6month")
    private String callCountCallTime5min10min6month;

    /**
     * 近6月通话时长>=10分钟的通话次数
     */
    @Column(name = "call_count_call_time_over10min_6month")
    private String callCountCallTimeOver10min6month;

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
     * 获取归属地城市
     *
     * @return contact_area_city - 归属地城市
     */
    public String getContactAreaCity() {
        return contactAreaCity;
    }

    /**
     * 设置归属地城市
     *
     * @param contactAreaCity 归属地城市
     */
    public void setContactAreaCity(String contactAreaCity) {
        this.contactAreaCity = contactAreaCity;
    }

    /**
     * 获取归属地排名
     *
     * @return contact_area_seq_no - 归属地排名
     */
    public String getContactAreaSeqNo() {
        return contactAreaSeqNo;
    }

    /**
     * 设置归属地排名
     *
     * @param contactAreaSeqNo 归属地排名
     */
    public void setContactAreaSeqNo(String contactAreaSeqNo) {
        this.contactAreaSeqNo = contactAreaSeqNo;
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
     * 获取近3月主叫号码数量
     *
     * @return contact_count_active_3month - 近3月主叫号码数量
     */
    public String getContactCountActive3month() {
        return contactCountActive3month;
    }

    /**
     * 设置近3月主叫号码数量
     *
     * @param contactCountActive3month 近3月主叫号码数量
     */
    public void setContactCountActive3month(String contactCountActive3month) {
        this.contactCountActive3month = contactCountActive3month;
    }

    /**
     * 获取近3月被叫号码数量
     *
     * @return contact_count_passive_3month - 近3月被叫号码数量
     */
    public String getContactCountPassive3month() {
        return contactCountPassive3month;
    }

    /**
     * 设置近3月被叫号码数量
     *
     * @param contactCountPassive3month 近3月被叫号码数量
     */
    public void setContactCountPassive3month(String contactCountPassive3month) {
        this.contactCountPassive3month = contactCountPassive3month;
    }

    /**
     * 获取近3月互通号码数量
     *
     * @return contact_count_mutual_3month - 近3月互通号码数量
     */
    public String getContactCountMutual3month() {
        return contactCountMutual3month;
    }

    /**
     * 设置近3月互通号码数量
     *
     * @param contactCountMutual3month 近3月互通号码数量
     */
    public void setContactCountMutual3month(String contactCountMutual3month) {
        this.contactCountMutual3month = contactCountMutual3month;
    }

    /**
     * 获取近3月通话次数>=10的号码数量
     *
     * @return contact_count_call_count_over10_3month - 近3月通话次数>=10的号码数量
     */
    public String getContactCountCallCountOver103month() {
        return contactCountCallCountOver103month;
    }

    /**
     * 设置近3月通话次数>=10的号码数量
     *
     * @param contactCountCallCountOver103month 近3月通话次数>=10的号码数量
     */
    public void setContactCountCallCountOver103month(String contactCountCallCountOver103month) {
        this.contactCountCallCountOver103month = contactCountCallCountOver103month;
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
     * 获取近6月主叫号码数量
     *
     * @return contact_count_active_6month - 近6月主叫号码数量
     */
    public String getContactCountActive6month() {
        return contactCountActive6month;
    }

    /**
     * 设置近6月主叫号码数量
     *
     * @param contactCountActive6month 近6月主叫号码数量
     */
    public void setContactCountActive6month(String contactCountActive6month) {
        this.contactCountActive6month = contactCountActive6month;
    }

    /**
     * 获取近6月被叫号码数量
     *
     * @return contact_count_passive_6month - 近6月被叫号码数量
     */
    public String getContactCountPassive6month() {
        return contactCountPassive6month;
    }

    /**
     * 设置近6月被叫号码数量
     *
     * @param contactCountPassive6month 近6月被叫号码数量
     */
    public void setContactCountPassive6month(String contactCountPassive6month) {
        this.contactCountPassive6month = contactCountPassive6month;
    }

    /**
     * 获取近6月互通号码数量
     *
     * @return contact_count_mutual_6month - 近6月互通号码数量
     */
    public String getContactCountMutual6month() {
        return contactCountMutual6month;
    }

    /**
     * 设置近6月互通号码数量
     *
     * @param contactCountMutual6month 近6月互通号码数量
     */
    public void setContactCountMutual6month(String contactCountMutual6month) {
        this.contactCountMutual6month = contactCountMutual6month;
    }

    /**
     * 获取近6月通话次数>=10的号码数量
     *
     * @return contact_count_call_count_over10_6month - 近6月通话次数>=10的号码数量
     */
    public String getContactCountCallCountOver106month() {
        return contactCountCallCountOver106month;
    }

    /**
     * 设置近6月通话次数>=10的号码数量
     *
     * @param contactCountCallCountOver106month 近6月通话次数>=10的号码数量
     */
    public void setContactCountCallCountOver106month(String contactCountCallCountOver106month) {
        this.contactCountCallCountOver106month = contactCountCallCountOver106month;
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
     * 获取近3月深夜通话次数
     *
     * @return call_count_late_night_3month - 近3月深夜通话次数
     */
    public String getCallCountLateNight3month() {
        return callCountLateNight3month;
    }

    /**
     * 设置近3月深夜通话次数
     *
     * @param callCountLateNight3month 近3月深夜通话次数
     */
    public void setCallCountLateNight3month(String callCountLateNight3month) {
        this.callCountLateNight3month = callCountLateNight3month;
    }

    /**
     * 获取近3月工作时间通话次数
     *
     * @return call_count_work_time_3month - 近3月工作时间通话次数
     */
    public String getCallCountWorkTime3month() {
        return callCountWorkTime3month;
    }

    /**
     * 设置近3月工作时间通话次数
     *
     * @param callCountWorkTime3month 近3月工作时间通话次数
     */
    public void setCallCountWorkTime3month(String callCountWorkTime3month) {
        this.callCountWorkTime3month = callCountWorkTime3month;
    }

    /**
     * 获取近3月非工作时间通话次数
     *
     * @return call_count_offwork_time_3month - 近3月非工作时间通话次数
     */
    public String getCallCountOffworkTime3month() {
        return callCountOffworkTime3month;
    }

    /**
     * 设置近3月非工作时间通话次数
     *
     * @param callCountOffworkTime3month 近3月非工作时间通话次数
     */
    public void setCallCountOffworkTime3month(String callCountOffworkTime3month) {
        this.callCountOffworkTime3month = callCountOffworkTime3month;
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
     * 获取近6月深夜通话次数
     *
     * @return call_count_late_night_6month - 近6月深夜通话次数
     */
    public String getCallCountLateNight6month() {
        return callCountLateNight6month;
    }

    /**
     * 设置近6月深夜通话次数
     *
     * @param callCountLateNight6month 近6月深夜通话次数
     */
    public void setCallCountLateNight6month(String callCountLateNight6month) {
        this.callCountLateNight6month = callCountLateNight6month;
    }

    /**
     * 获取近6月工作时间通话次数
     *
     * @return call_count_work_time_6month - 近6月工作时间通话次数
     */
    public String getCallCountWorkTime6month() {
        return callCountWorkTime6month;
    }

    /**
     * 设置近6月工作时间通话次数
     *
     * @param callCountWorkTime6month 近6月工作时间通话次数
     */
    public void setCallCountWorkTime6month(String callCountWorkTime6month) {
        this.callCountWorkTime6month = callCountWorkTime6month;
    }

    /**
     * 获取近6月非工作时间通话次数
     *
     * @return call_count_offwork_time_6month - 近6月非工作时间通话次数
     */
    public String getCallCountOffworkTime6month() {
        return callCountOffworkTime6month;
    }

    /**
     * 设置近6月非工作时间通话次数
     *
     * @param callCountOffworkTime6month 近6月非工作时间通话次数
     */
    public void setCallCountOffworkTime6month(String callCountOffworkTime6month) {
        this.callCountOffworkTime6month = callCountOffworkTime6month;
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
     * 获取近6月通话时长<1分钟的通话次数
     *
     * @return call_count_call_time_less1min_6month - 近6月通话时长<1分钟的通话次数
     */
    public String getCallCountCallTimeLess1min6month() {
        return callCountCallTimeLess1min6month;
    }

    /**
     * 设置近6月通话时长<1分钟的通话次数
     *
     * @param callCountCallTimeLess1min6month 近6月通话时长<1分钟的通话次数
     */
    public void setCallCountCallTimeLess1min6month(String callCountCallTimeLess1min6month) {
        this.callCountCallTimeLess1min6month = callCountCallTimeLess1min6month;
    }

    /**
     * 获取近6月通话时长1-5分钟的通话次数
     *
     * @return call_count_call_time_1min5min_6month - 近6月通话时长1-5分钟的通话次数
     */
    public String getCallCountCallTime1min5min6month() {
        return callCountCallTime1min5min6month;
    }

    /**
     * 设置近6月通话时长1-5分钟的通话次数
     *
     * @param callCountCallTime1min5min6month 近6月通话时长1-5分钟的通话次数
     */
    public void setCallCountCallTime1min5min6month(String callCountCallTime1min5min6month) {
        this.callCountCallTime1min5min6month = callCountCallTime1min5min6month;
    }

    /**
     * 获取近6月通话时长5-10分钟的通话次数
     *
     * @return call_count_call_time_5min10min_6month - 近6月通话时长5-10分钟的通话次数
     */
    public String getCallCountCallTime5min10min6month() {
        return callCountCallTime5min10min6month;
    }

    /**
     * 设置近6月通话时长5-10分钟的通话次数
     *
     * @param callCountCallTime5min10min6month 近6月通话时长5-10分钟的通话次数
     */
    public void setCallCountCallTime5min10min6month(String callCountCallTime5min10min6month) {
        this.callCountCallTime5min10min6month = callCountCallTime5min10min6month;
    }

    /**
     * 获取近6月通话时长>=10分钟的通话次数
     *
     * @return call_count_call_time_over10min_6month - 近6月通话时长>=10分钟的通话次数
     */
    public String getCallCountCallTimeOver10min6month() {
        return callCountCallTimeOver10min6month;
    }

    /**
     * 设置近6月通话时长>=10分钟的通话次数
     *
     * @param callCountCallTimeOver10min6month 近6月通话时长>=10分钟的通话次数
     */
    public void setCallCountCallTimeOver10min6month(String callCountCallTimeOver10min6month) {
        this.callCountCallTimeOver10min6month = callCountCallTimeOver10min6month;
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