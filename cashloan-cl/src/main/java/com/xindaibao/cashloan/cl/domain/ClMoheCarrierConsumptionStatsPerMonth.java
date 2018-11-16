package com.xindaibao.cashloan.cl.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "cl_mohe_carrier_consumption_stats_per_month")
public class ClMoheCarrierConsumptionStatsPerMonth implements Serializable {
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
     * 月消费金额
     */
    @Column(name = "consume_amount")
    private String consumeAmount;

    /**
     * 月充值次数
     */
    @Column(name = "recharge_count")
    private String rechargeCount;

    /**
     * 月充值金额
     */
    @Column(name = "recharge_amount")
    private String rechargeAmount;

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
     * 获取月消费金额
     *
     * @return consume_amount - 月消费金额
     */
    public String getConsumeAmount() {
        return consumeAmount;
    }

    /**
     * 设置月消费金额
     *
     * @param consumeAmount 月消费金额
     */
    public void setConsumeAmount(String consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    /**
     * 获取月充值次数
     *
     * @return recharge_count - 月充值次数
     */
    public String getRechargeCount() {
        return rechargeCount;
    }

    /**
     * 设置月充值次数
     *
     * @param rechargeCount 月充值次数
     */
    public void setRechargeCount(String rechargeCount) {
        this.rechargeCount = rechargeCount;
    }

    /**
     * 获取月充值金额
     *
     * @return recharge_amount - 月充值金额
     */
    public String getRechargeAmount() {
        return rechargeAmount;
    }

    /**
     * 设置月充值金额
     *
     * @param rechargeAmount 月充值金额
     */
    public void setRechargeAmount(String rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }
}