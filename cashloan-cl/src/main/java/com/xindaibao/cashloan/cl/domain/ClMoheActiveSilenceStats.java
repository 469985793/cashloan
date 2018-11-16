package com.xindaibao.cashloan.cl.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "cl_mohe_active_silence_stats")
public class ClMoheActiveSilenceStats implements Serializable {
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
     * 近3月通话活跃天数
     */
    @Column(name = "active_day_1call_3month")
    private String activeDay1call3month;

    /**
     * 近6月通话活跃天数
     */
    @Column(name = "active_day_1call_6month")
    private String activeDay1call6month;

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
     * 近3月无通话静默天数
     */
    @Column(name = "silence_day_0call_3month")
    private String silenceDay0call3month;

    /**
     * 近3月无主叫通话静默天数
     */
    @Column(name = "silence_day_0call_active_3month")
    private String silenceDay0callActive3month;

    /**
     * 近6月无通话静默天数
     */
    @Column(name = "silence_day_0call_6month")
    private String silenceDay0call6month;

    /**
     * 近6月无主叫通话静默天数
     */
    @Column(name = "silence_day_0call_active_6month")
    private String silenceDay0callActive6month;

    /**
     * 近3月连续无通话静默>=3天的次数
     */
    @Column(name = "continue_silence_day_over3_0call_3month")
    private String continueSilenceDayOver30call3month;

    /**
     * 近3月连续无通话静默>=3天的记录
     */
    @Column(name = "continue_silence_day_over3_0call_3month_detail")
    private String continueSilenceDayOver30call3monthDetail;

    /**
     * 近3月连续无通话静默>=15天的次数
     */
    @Column(name = "continue_silence_day_over15_0call_3month")
    private String continueSilenceDayOver150call3month;

    /**
     * 近3月连续无通话静默>=15天的记录
     */
    @Column(name = "continue_silence_day_over15_0call_3month_detail")
    private String continueSilenceDayOver150call3monthDetail;

    /**
     * 近3月连续无主叫通话静默>=3天的次数
     */
    @Column(name = "continue_silence_day_over3_0call_active_3month")
    private String continueSilenceDayOver30callActive3month;

    /**
     * 近3月连续无主叫通话静默>=3天的记录
     */
    @Column(name = "continue_silence_day_over3_0call_active_3month_detail")
    private String continueSilenceDayOver30callActive3monthDetail;

    /**
     * 近3月连续无主叫通话静默>=15天的次数
     */
    @Column(name = "continue_silence_day_over15_0call_active_3month")
    private String continueSilenceDayOver150callActive3month;

    /**
     * 近3月连续无主叫通话静默>=15天的记录
     */
    @Column(name = "continue_silence_day_over15_0call_active_3month_detail")
    private String continueSilenceDayOver150callActive3monthDetail;

    /**
     * 近6月连续无通话静默>=3天的次数
     */
    @Column(name = "continue_silence_day_over3_0call_6month")
    private String continueSilenceDayOver30call6month;

    /**
     * 近6月连续无通话静默>=3天的记录
     */
    @Column(name = "continue_silence_day_over3_0call_6month_detail")
    private String continueSilenceDayOver30call6monthDetail;

    /**
     * 近6月连续无通话静默>=15天的次数
     */
    @Column(name = "continue_silence_day_over15_0call_6month")
    private String continueSilenceDayOver150call6month;

    /**
     * 近6月连续无通话静默>=15天的记录
     */
    @Column(name = "continue_silence_day_over15_0call_6month_detail")
    private String continueSilenceDayOver150call6monthDetail;

    /**
     * 近6月连续无主叫通话静默>=3天的次数
     */
    @Column(name = "continue_silence_day_over3_0call_active_6month")
    private String continueSilenceDayOver30callActive6month;

    /**
     * 近6月连续无主叫通话静默>=3天的记录
     */
    @Column(name = "continue_silence_day_over3_0call_active_6month_detail")
    private String continueSilenceDayOver30callActive6monthDetail;

    /**
     * 近6月连续无主叫通话静默>=15天的次数
     */
    @Column(name = "continue_silence_day_over15_0call_active_6month")
    private String continueSilenceDayOver150callActive6month;

    /**
     * 近6月连续无主叫通话静默>=15天的记录
     */
    @Column(name = "continue_silence_day_over15_0call_active_6month_detail")
    private String continueSilenceDayOver150callActive6monthDetail;

    /**
     * 近3月最大连续无通话静默天数
     */
    @Column(name = "max_continue_silence_day_0call_3month")
    private String maxContinueSilenceDay0call3month;

    /**
     * 近3月最大连续无主叫通话静默天数
     */
    @Column(name = "max_continue_silence_day_0call_active_3month")
    private String maxContinueSilenceDay0callActive3month;

    /**
     * 近6月最大连续无通话静默天数
     */
    @Column(name = "max_continue_silence_day_0call_6month")
    private String maxContinueSilenceDay0call6month;

    /**
     * 近6月最大连续无主叫通话静默天数
     */
    @Column(name = "max_continue_silence_day_0call_active_6month")
    private String maxContinueSilenceDay0callActive6month;


}