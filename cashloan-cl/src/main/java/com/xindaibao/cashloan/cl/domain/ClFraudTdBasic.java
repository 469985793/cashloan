package com.xindaibao.cashloan.cl.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 风控数据统计-（简）通话记录统计实体
 * 
 * @author xx
 * @version 1.0.0
 * @date 2017-11-10 20:41:21



 */
@Data
 public class ClFraudTdBasic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户编码
    */
    private Long user_id;

    /**
    * 贷前申请风险报告编号
    */
    private String report_id;

    /**
    * 调用失败时的错误码
    */
    private String reason_code;

    /**
    * 错误详情描述
    */
    private String reason_desc;

    /**
    * 风险分数
    */
    private Integer final_score;

    /**
    * 风险结果
    */
    private String final_decision;

    /**
    * 设备类型
    */
    private String device_type;

    /**
    * 代理信息
    */
    private String proxy_info;

    /**
    * 扫描时间
    */
    private Long apply_time;

    /**
    * 报告时间
    */
    private Long report_time;

    /**
    * 设备环境信息
    */
    private String device_info;

    /**
    * 地理位置信息
    */
    private String geo_ip;

    /**
    * 真实地理位置信息
    */
    private String geo_trueip;

    /**
    * 申请编号
    */
    private String application_id;

    /**
    * 信用分
    */
    private Integer credit_score;


}