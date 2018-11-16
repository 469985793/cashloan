package com.xindaibao.cashloan.cl.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "cl_mohe_travel_track_analysis_per_city")
public class ClMoheTravelTrackAnalysisPerCity implements Serializable {
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
     * 出发城市
     */
    @Column(name = "leave_city")
    private String leaveCity;

    /**
     * 出发日期
     */
    @Column(name = "leave_day")
    private String leaveDay;

    /**
     * 出发日类型
     */
    @Column(name = "leave_day_type")
    private String leaveDayType;

    /**
     * 到达城市
     */
    @Column(name = "arrive_city")
    private String arriveCity;

    /**
     * 到达日期
     */
    @Column(name = "arrive_day")
    private String arriveDay;

    /**
     * 到达日类型
     */
    @Column(name = "arrive_day_type")
    private String arriveDayType;

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
     * 获取出发城市
     *
     * @return leave_city - 出发城市
     */
    public String getLeaveCity() {
        return leaveCity;
    }

    /**
     * 设置出发城市
     *
     * @param leaveCity 出发城市
     */
    public void setLeaveCity(String leaveCity) {
        this.leaveCity = leaveCity;
    }

    /**
     * 获取出发日期
     *
     * @return leave_day - 出发日期
     */
    public String getLeaveDay() {
        return leaveDay;
    }

    /**
     * 设置出发日期
     *
     * @param leaveDay 出发日期
     */
    public void setLeaveDay(String leaveDay) {
        this.leaveDay = leaveDay;
    }

    /**
     * 获取出发日类型
     *
     * @return leave_day_type - 出发日类型
     */
    public String getLeaveDayType() {
        return leaveDayType;
    }

    /**
     * 设置出发日类型
     *
     * @param leaveDayType 出发日类型
     */
    public void setLeaveDayType(String leaveDayType) {
        this.leaveDayType = leaveDayType;
    }

    /**
     * 获取到达城市
     *
     * @return arrive_city - 到达城市
     */
    public String getArriveCity() {
        return arriveCity;
    }

    /**
     * 设置到达城市
     *
     * @param arriveCity 到达城市
     */
    public void setArriveCity(String arriveCity) {
        this.arriveCity = arriveCity;
    }

    /**
     * 获取到达日期
     *
     * @return arrive_day - 到达日期
     */
    public String getArriveDay() {
        return arriveDay;
    }

    /**
     * 设置到达日期
     *
     * @param arriveDay 到达日期
     */
    public void setArriveDay(String arriveDay) {
        this.arriveDay = arriveDay;
    }

    /**
     * 获取到达日类型
     *
     * @return arrive_day_type - 到达日类型
     */
    public String getArriveDayType() {
        return arriveDayType;
    }

    /**
     * 设置到达日类型
     *
     * @param arriveDayType 到达日类型
     */
    public void setArriveDayType(String arriveDayType) {
        this.arriveDayType = arriveDayType;
    }
}