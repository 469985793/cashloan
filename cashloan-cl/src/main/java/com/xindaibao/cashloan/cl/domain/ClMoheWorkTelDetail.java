package com.xindaibao.cashloan.cl.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "cl_mohe_work_tel_detail")
public class ClMoheWorkTelDetail implements Serializable {
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
     * 联系人号码
     */
    @Column(name = "contact_number")
    private String contactNumber;

    /**
     * 联系人关系
     */
    @Column(name = "contact_relation")
    private String contactRelation;

    /**
     * 联系人排名
     */
    @Column(name = "contact_seq_no")
    private String contactSeqNo;

    /**
     * 号码分类
     */
    @Column(name = "contact_type")
    private String contactType;

    /**
     * 号码标签
     */
    @Column(name = "contact_name")
    private String contactName;

    /**
     * 号码归属地
     */
    @Column(name = "contact_area")
    private String contactArea;

    /**
     * 近1周通话次数
     */
    @Column(name = "call_count_1week")
    private String callCount1week;

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
     * 近1月短信次数
     */
    @Column(name = "msg_count_1month")
    private String msgCount1month;

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
     * 近3个月是否有全天通话
     */
    @Column(name = "is_whole_day_call_3month")
    private String isWholeDayCall3month;

    /**
     * 近6个月是否有全天通话
     */
    @Column(name = "is_whole_day_call_6month")
    private String isWholeDayCall6month;

    /**
     * 近6月第一次通话时间
     */
    @Column(name = "first_time_call_6month")
    private String firstTimeCall6month;

    /**
     * 近6月最后一次通话时间
     */
    @Column(name = "last_time_call_6month")
    private String lastTimeCall6month;

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
     * 获取联系人号码
     *
     * @return contact_number - 联系人号码
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * 设置联系人号码
     *
     * @param contactNumber 联系人号码
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * 获取联系人关系
     *
     * @return contact_relation - 联系人关系
     */
    public String getContactRelation() {
        return contactRelation;
    }

    /**
     * 设置联系人关系
     *
     * @param contactRelation 联系人关系
     */
    public void setContactRelation(String contactRelation) {
        this.contactRelation = contactRelation;
    }

    /**
     * 获取联系人排名
     *
     * @return contact_seq_no - 联系人排名
     */
    public String getContactSeqNo() {
        return contactSeqNo;
    }

    /**
     * 设置联系人排名
     *
     * @param contactSeqNo 联系人排名
     */
    public void setContactSeqNo(String contactSeqNo) {
        this.contactSeqNo = contactSeqNo;
    }

    /**
     * 获取号码分类
     *
     * @return contact_type - 号码分类
     */
    public String getContactType() {
        return contactType;
    }

    /**
     * 设置号码分类
     *
     * @param contactType 号码分类
     */
    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    /**
     * 获取号码标签
     *
     * @return contact_name - 号码标签
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * 设置号码标签
     *
     * @param contactName 号码标签
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * 获取号码归属地
     *
     * @return contact_area - 号码归属地
     */
    public String getContactArea() {
        return contactArea;
    }

    /**
     * 设置号码归属地
     *
     * @param contactArea 号码归属地
     */
    public void setContactArea(String contactArea) {
        this.contactArea = contactArea;
    }

    /**
     * 获取近1周通话次数
     *
     * @return call_count_1week - 近1周通话次数
     */
    public String getCallCount1week() {
        return callCount1week;
    }

    /**
     * 设置近1周通话次数
     *
     * @param callCount1week 近1周通话次数
     */
    public void setCallCount1week(String callCount1week) {
        this.callCount1week = callCount1week;
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
     * 获取近1月短信次数
     *
     * @return msg_count_1month - 近1月短信次数
     */
    public String getMsgCount1month() {
        return msgCount1month;
    }

    /**
     * 设置近1月短信次数
     *
     * @param msgCount1month 近1月短信次数
     */
    public void setMsgCount1month(String msgCount1month) {
        this.msgCount1month = msgCount1month;
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

    /**
     * 获取近3个月是否有全天通话
     *
     * @return is_whole_day_call_3month - 近3个月是否有全天通话
     */
    public String getIsWholeDayCall3month() {
        return isWholeDayCall3month;
    }

    /**
     * 设置近3个月是否有全天通话
     *
     * @param isWholeDayCall3month 近3个月是否有全天通话
     */
    public void setIsWholeDayCall3month(String isWholeDayCall3month) {
        this.isWholeDayCall3month = isWholeDayCall3month;
    }

    /**
     * 获取近6个月是否有全天通话
     *
     * @return is_whole_day_call_6month - 近6个月是否有全天通话
     */
    public String getIsWholeDayCall6month() {
        return isWholeDayCall6month;
    }

    /**
     * 设置近6个月是否有全天通话
     *
     * @param isWholeDayCall6month 近6个月是否有全天通话
     */
    public void setIsWholeDayCall6month(String isWholeDayCall6month) {
        this.isWholeDayCall6month = isWholeDayCall6month;
    }

    /**
     * 获取近6月第一次通话时间
     *
     * @return first_time_call_6month - 近6月第一次通话时间
     */
    public String getFirstTimeCall6month() {
        return firstTimeCall6month;
    }

    /**
     * 设置近6月第一次通话时间
     *
     * @param firstTimeCall6month 近6月第一次通话时间
     */
    public void setFirstTimeCall6month(String firstTimeCall6month) {
        this.firstTimeCall6month = firstTimeCall6month;
    }

    /**
     * 获取近6月最后一次通话时间
     *
     * @return last_time_call_6month - 近6月最后一次通话时间
     */
    public String getLastTimeCall6month() {
        return lastTimeCall6month;
    }

    /**
     * 设置近6月最后一次通话时间
     *
     * @param lastTimeCall6month 近6月最后一次通话时间
     */
    public void setLastTimeCall6month(String lastTimeCall6month) {
        this.lastTimeCall6month = lastTimeCall6month;
    }
}