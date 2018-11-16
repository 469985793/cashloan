package com.xindaibao.cashloan.cl.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "cl_mohe_all_contact_stats")
public class ClMoheAllContactStats implements Serializable {
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


}