package com.xindaibao.cashloan.cl.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 风控数据统计-（简）通话记录统计实体
 * 
 * @author xx
 * @version 1.0.0
 * @date 2017-11-10 20:47:39
 */


@Data
 public class ClFraudTdMultiPlatform implements Serializable {

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
    * 7天内申请人在小额贷款公司申请借款次数
    */
    private Integer multi_platform_small_loan_cnt_7d;

    /**
    * 7天内申请人在P2P平台申请借款次数
    */
    private Integer multi_platform_p2p_cnt_7d;

    /**
    * 7天内申请人在大型消费金融公司申请次数
    */
    private Integer multi_platform_consumer_cnt_7d;

    /**
    * 7天内申请人在一般消费分期平台申请次数
    */
    private Integer multi_platform_installment_cnt_7d;

    /**
    * 7天内申请人在联网金融门户申请次数
    */
    private Integer multi_platform_portals_cnt_7d;

    /**
    * 7天内不同平台的申请次数
    */
    private Integer multi_platform_cnt_7d;

    /**
    * 30天内不同平台的申请次数
    */
    private Integer multi_platform_cnt_30d;

    /**
    * 90天内不同平台的申请次数
    */
    private Integer multi_platform_cnt_90d;

    /**
    * 3个月内申请人在多个平台被放款_不包含本合作方
    */
    private Integer multi_platform_approved_cnt_90d;

    private Integer multi_platform_idcard_cnt_7d;
    private Integer multi_platform_mob_cnt_7d;
    private Integer multi_platform_device_cnt_7d;

    /**
    * 报告日期
    */
    private Date report_date;



}