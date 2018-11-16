package com.xindaibao.cashloan.cl.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "cl_mohe_report_info")
public class ClMoheReportInfo implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    /**
     * 报告id
     */
    @Column(name = "report_id")
    private String reportId;

    /**
     * 报告版本
     */
    @Column(name = "report_version")
    private String reportVersion;

    /**
     * 报告更新时间
     */
    @Column(name = "report_update_time")
    private String reportUpdateTime;

    /**
     * 数据获取任务id
     */
    @Column(name = "task_id")
    private String taskId;

    /**
     * 数据获取任务时间
     */
    @Column(name = "task_time")
    private String taskTime;

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
     * 获取报告id
     *
     * @return report_id - 报告id
     */
    public String getReportId() {
        return reportId;
    }

    /**
     * 设置报告id
     *
     * @param reportId 报告id
     */
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    /**
     * 获取报告版本
     *
     * @return report_version - 报告版本
     */
    public String getReportVersion() {
        return reportVersion;
    }

    /**
     * 设置报告版本
     *
     * @param reportVersion 报告版本
     */
    public void setReportVersion(String reportVersion) {
        this.reportVersion = reportVersion;
    }

    /**
     * 获取报告更新时间
     *
     * @return report_update_time - 报告更新时间
     */
    public String getReportUpdateTime() {
        return reportUpdateTime;
    }

    /**
     * 设置报告更新时间
     *
     * @param reportUpdateTime 报告更新时间
     */
    public void setReportUpdateTime(String reportUpdateTime) {
        this.reportUpdateTime = reportUpdateTime;
    }

    /**
     * 获取数据获取任务id
     *
     * @return task_id - 数据获取任务id
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 设置数据获取任务id
     *
     * @param taskId 数据获取任务id
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * 获取数据获取任务时间
     *
     * @return task_time - 数据获取任务时间
     */
    public String getTaskTime() {
        return taskTime;
    }

    /**
     * 设置数据获取任务时间
     *
     * @param taskTime 数据获取任务时间
     */
    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }
}