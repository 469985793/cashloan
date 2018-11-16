package com.xindaibao.cashloan.cl.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "cl_mohe_all_contact_detail")
public class ClMoheAllContactDetail implements Serializable {
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


}