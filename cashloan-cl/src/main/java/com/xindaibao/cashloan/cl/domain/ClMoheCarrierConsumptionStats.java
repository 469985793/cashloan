package com.xindaibao.cashloan.cl.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@Table(name = "cl_mohe_carrier_consumption_stats")
public class ClMoheCarrierConsumptionStats implements Serializable {
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
     * 近1月消费金额
     */
    @Column(name = "consume_amount_1month")
    private String consumeAmount1month;

    /**
     * 近3月消费金额
     */
    @Column(name = "consume_amount_3month")
    private String consumeAmount3month;

    /**
     * 近6月消费金额
     */
    @Column(name = "consume_amount_6month")
    private String consumeAmount6month;

    /**
     * 近1月充值次数
     */
    @Column(name = "recharge_count_1month")
    private String rechargeCount1month;

    /**
     * 近3月充值次数
     */
    @Column(name = "recharge_count_3month")
    private String rechargeCount3month;

    /**
     * 近6月充值次数
     */
    @Column(name = "recharge_count_6month")
    private String rechargeCount6month;

    /**
     * 近1月充值金额
     */
    @Column(name = "recharge_amount_1month")
    private String rechargeAmount1month;

    /**
     * 近3月充值金额
     */
    @Column(name = "recharge_amount_3month")
    private String rechargeAmount3month;

    /**
     * 近6月充值金额
     */
    @Column(name = "recharge_amount_6month")
    private String rechargeAmount6month;

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
     * 获取近1月消费金额
     *
     * @return consume_amount_1month - 近1月消费金额
     */
    public String getConsumeAmount1month() {
        return consumeAmount1month;
    }

    /**
     * 设置近1月消费金额
     *
     * @param consumeAmount1month 近1月消费金额
     */
    public void setConsumeAmount1month(String consumeAmount1month) {
        this.consumeAmount1month = consumeAmount1month;
    }

    /**
     * 获取近3月消费金额
     *
     * @return consume_amount_3month - 近3月消费金额
     */
    public String getConsumeAmount3month() {
        return consumeAmount3month;
    }

    /**
     * 设置近3月消费金额
     *
     * @param consumeAmount3month 近3月消费金额
     */
    public void setConsumeAmount3month(String consumeAmount3month) {
        this.consumeAmount3month = consumeAmount3month;
    }

    /**
     * 获取近6月消费金额
     *
     * @return consume_amount_6month - 近6月消费金额
     */
    public String getConsumeAmount6month() {
        return consumeAmount6month;
    }

    /**
     * 设置近6月消费金额
     *
     * @param consumeAmount6month 近6月消费金额
     */
    public void setConsumeAmount6month(String consumeAmount6month) {
        this.consumeAmount6month = consumeAmount6month;
    }

    /**
     * 获取近1月充值次数
     *
     * @return recharge_count_1month - 近1月充值次数
     */
    public String getRechargeCount1month() {
        return rechargeCount1month;
    }

    /**
     * 设置近1月充值次数
     *
     * @param rechargeCount1month 近1月充值次数
     */
    public void setRechargeCount1month(String rechargeCount1month) {
        this.rechargeCount1month = rechargeCount1month;
    }

    /**
     * 获取近3月充值次数
     *
     * @return recharge_count_3month - 近3月充值次数
     */
    public String getRechargeCount3month() {
        return rechargeCount3month;
    }

    /**
     * 设置近3月充值次数
     *
     * @param rechargeCount3month 近3月充值次数
     */
    public void setRechargeCount3month(String rechargeCount3month) {
        this.rechargeCount3month = rechargeCount3month;
    }

    /**
     * 获取近6月充值次数
     *
     * @return recharge_count_6month - 近6月充值次数
     */
    public String getRechargeCount6month() {
        return rechargeCount6month;
    }

    /**
     * 设置近6月充值次数
     *
     * @param rechargeCount6month 近6月充值次数
     */
    public void setRechargeCount6month(String rechargeCount6month) {
        this.rechargeCount6month = rechargeCount6month;
    }

    /**
     * 获取近1月充值金额
     *
     * @return recharge_amount_1month - 近1月充值金额
     */
    public String getRechargeAmount1month() {
        return rechargeAmount1month;
    }

    /**
     * 设置近1月充值金额
     *
     * @param rechargeAmount1month 近1月充值金额
     */
    public void setRechargeAmount1month(String rechargeAmount1month) {
        this.rechargeAmount1month = rechargeAmount1month;
    }

    /**
     * 获取近3月充值金额
     *
     * @return recharge_amount_3month - 近3月充值金额
     */
    public String getRechargeAmount3month() {
        return rechargeAmount3month;
    }

    /**
     * 设置近3月充值金额
     *
     * @param rechargeAmount3month 近3月充值金额
     */
    public void setRechargeAmount3month(String rechargeAmount3month) {
        this.rechargeAmount3month = rechargeAmount3month;
    }

    /**
     * 获取近6月充值金额
     *
     * @return recharge_amount_6month - 近6月充值金额
     */
    public String getRechargeAmount6month() {
        return rechargeAmount6month;
    }

    /**
     * 设置近6月充值金额
     *
     * @param rechargeAmount6month 近6月充值金额
     */
    public void setRechargeAmount6month(String rechargeAmount6month) {
        this.rechargeAmount6month = rechargeAmount6month;
    }
}