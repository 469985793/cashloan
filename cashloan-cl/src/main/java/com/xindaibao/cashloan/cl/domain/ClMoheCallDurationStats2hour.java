package com.xindaibao.cashloan.cl.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@Table(name = "cl_mohe_call_duration_stats_2hour")
public class ClMoheCallDurationStats2hour implements Serializable {
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
     * ENUM: workday_3month, holiday_3month, workday_6month, holiday_6month
     */
    @Column(name = "time_enum")
    private String timeEnum;

    /**
     * 时间段：0-2
     */
    @Column(name = "t_0")
    private String t0;

    /**
     * 时间段：2-4
     */
    @Column(name = "t_1")
    private String t1;

    /**
     * 时间段：4-6
     */
    @Column(name = "t_2")
    private String t2;

    /**
     * 时间段：6-8
     */
    @Column(name = "t_3")
    private String t3;

    /**
     * 时间段：8-10
     */
    @Column(name = "t_4")
    private String t4;

    /**
     * 时间段：10-12
     */
    @Column(name = "t_5")
    private String t5;

    /**
     * 时间段：12-14
     */
    @Column(name = "t_6")
    private String t6;

    /**
     * 时间段：14-16
     */
    @Column(name = "t_7")
    private String t7;

    /**
     * 时间段：16-18
     */
    @Column(name = "t_8")
    private String t8;

    /**
     * 时间段：18-20
     */
    @Column(name = "t_9")
    private String t9;

    /**
     * 时间段：20-22
     */
    @Column(name = "t_10")
    private String t10;

    /**
     * 时间段：22-24
     */
    @Column(name = "t_11")
    private String t11;

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
     * 获取ENUM: workday_3month, holiday_3month, workday_6month, holiday_6month
     *
     * @return time_enum - ENUM: workday_3month, holiday_3month, workday_6month, holiday_6month
     */
    public String getTimeEnum() {
        return timeEnum;
    }

    /**
     * 设置ENUM: workday_3month, holiday_3month, workday_6month, holiday_6month
     *
     * @param timeEnum ENUM: workday_3month, holiday_3month, workday_6month, holiday_6month
     */
    public void setTimeEnum(String timeEnum) {
        this.timeEnum = timeEnum;
    }

    /**
     * 获取时间段：0-2
     *
     * @return t_0 - 时间段：0-2
     */
    public String getT0() {
        return t0;
    }

    /**
     * 设置时间段：0-2
     *
     * @param t0 时间段：0-2
     */
    public void setT0(String t0) {
        this.t0 = t0;
    }

    /**
     * 获取时间段：2-4
     *
     * @return t_1 - 时间段：2-4
     */
    public String getT1() {
        return t1;
    }

    /**
     * 设置时间段：2-4
     *
     * @param t1 时间段：2-4
     */
    public void setT1(String t1) {
        this.t1 = t1;
    }

    /**
     * 获取时间段：4-6
     *
     * @return t_2 - 时间段：4-6
     */
    public String getT2() {
        return t2;
    }

    /**
     * 设置时间段：4-6
     *
     * @param t2 时间段：4-6
     */
    public void setT2(String t2) {
        this.t2 = t2;
    }

    /**
     * 获取时间段：6-8
     *
     * @return t_3 - 时间段：6-8
     */
    public String getT3() {
        return t3;
    }

    /**
     * 设置时间段：6-8
     *
     * @param t3 时间段：6-8
     */
    public void setT3(String t3) {
        this.t3 = t3;
    }

    /**
     * 获取时间段：8-10
     *
     * @return t_4 - 时间段：8-10
     */
    public String getT4() {
        return t4;
    }

    /**
     * 设置时间段：8-10
     *
     * @param t4 时间段：8-10
     */
    public void setT4(String t4) {
        this.t4 = t4;
    }

    /**
     * 获取时间段：10-12
     *
     * @return t_5 - 时间段：10-12
     */
    public String getT5() {
        return t5;
    }

    /**
     * 设置时间段：10-12
     *
     * @param t5 时间段：10-12
     */
    public void setT5(String t5) {
        this.t5 = t5;
    }

    /**
     * 获取时间段：12-14
     *
     * @return t_6 - 时间段：12-14
     */
    public String getT6() {
        return t6;
    }

    /**
     * 设置时间段：12-14
     *
     * @param t6 时间段：12-14
     */
    public void setT6(String t6) {
        this.t6 = t6;
    }

    /**
     * 获取时间段：14-16
     *
     * @return t_7 - 时间段：14-16
     */
    public String getT7() {
        return t7;
    }

    /**
     * 设置时间段：14-16
     *
     * @param t7 时间段：14-16
     */
    public void setT7(String t7) {
        this.t7 = t7;
    }

    /**
     * 获取时间段：16-18
     *
     * @return t_8 - 时间段：16-18
     */
    public String getT8() {
        return t8;
    }

    /**
     * 设置时间段：16-18
     *
     * @param t8 时间段：16-18
     */
    public void setT8(String t8) {
        this.t8 = t8;
    }

    /**
     * 获取时间段：18-20
     *
     * @return t_9 - 时间段：18-20
     */
    public String getT9() {
        return t9;
    }

    /**
     * 设置时间段：18-20
     *
     * @param t9 时间段：18-20
     */
    public void setT9(String t9) {
        this.t9 = t9;
    }

    /**
     * 获取时间段：20-22
     *
     * @return t_10 - 时间段：20-22
     */
    public String getT10() {
        return t10;
    }

    /**
     * 设置时间段：20-22
     *
     * @param t10 时间段：20-22
     */
    public void setT10(String t10) {
        this.t10 = t10;
    }

    /**
     * 获取时间段：22-24
     *
     * @return t_11 - 时间段：22-24
     */
    public String getT11() {
        return t11;
    }

    /**
     * 设置时间段：22-24
     *
     * @param t11 时间段：22-24
     */
    public void setT11(String t11) {
        this.t11 = t11;
    }
}