package com.xindaibao.cashloan.cl.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 风控数据统计-（简）通话记录统计实体
 * 
 * @author xx
 * @version 1.0.0
 * @date 2017-11-10 20:45:10



 */

@NoArgsConstructor
@AllArgsConstructor
@Data
 public class ClFraudTdHitList implements Serializable {

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
    * 申请人姓名身份证号命中法院执行信息
    */
    private Integer idcard_hit_execution_list = 0;

    /**
    * 申请人姓名身份证号命中法院失信被执行人
    */
    private Integer idcard_hit_court_list = 0;

    /**
    * 设备号命中信贷逾期黑名单
    */
    private Integer device_hit_black_list = 0;

    /**
    * 手机号命中欺诈名单
    */
    private Integer mob_hit_black_list = 0;

    /**
    * 身份证命中欺诈名单
    */
    private Integer idcard_hit_black_list = 0;

    /**
    * 银行卡号命中信贷逾期黑名单
    */
    private Integer bank_hit_black_list = 0;

    /**
    * 银行卡预留手机号命中信贷逾期黑名单
    */
    private Integer bank_mob_hit_black_list = 0;

    /**
    * 手机号最高逾期天数
    */
    private Integer mob_overdue_day_max = 0;

    /**
    * 身份证最高逾期天数
    */
    private Integer idcard_overdue_day_max = 0;

    /**
    * 手机号命中模糊名单
    */
    private Integer mob_hit_fuzzy_list = 0;

    /**
    * 身份证命中模糊名单
    */
    private Integer idcard_hit_fuzzy_list = 0;

    /**
    * 手机号命中灰名单
    */
    private Integer mob_hit_grey_list = 0;

    /**
    * 身份证命中灰名单
    */
    private Integer idcard_hit_grey_list = 0;

    /**
    * 第一联系人(近亲)手机号最高逾期天数
    */
    private Integer contact1_overdue_day_max = 0;

    /**
    * 第一联系人(一般)手机号最高逾期天数
    */
    private Integer contact2_overdue_day_max = 0;

    /**
    * 报告日期
    */
    private Date report_date;

    private Integer frequency_detail_mob_idcard_cnt_90d = 0;

    private Integer frequency_detail_email_idcard_cnt_90d = 0;

    private Integer frequency_detail_homeaddr_idcard_cnt_90d = 0;

   private Integer frequency_detail_device_idcard_cnt_1d = 0;

   private Integer frequency_detail_device_idcard_cnt_30d = 0;

   private Integer frequency_detail_device_idcard_cnt_7d = 0;

   private Integer frequency_detail_workaddr_idcard_cnt_90d = 0;

   private Integer mob_cnt_hit_black_list = 0;

   private Integer idcard_cnt_hit_black_list = 0;





}