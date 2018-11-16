package com.xindaibao.cashloan.cl.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 极光黑名单记录表实体
 * 
 *
 */
@Data
 public class RcJiGuangBlacklistLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户标识
    */
    private Long userId;

    /**
    * 订单号
    */
    private String orderNo;

    /**
    * 同步响应返回黑名单分数
    */
    private Integer score;

    /**
     * 同步响应返回黑名单分数集合
     */
    private String hits;
    /**
     * 同步响应返回黑名单结果
     */
    private String description;

    /**
    * 响应码
    */
    private String respCode;

    /**
    * 同步响应结果
    */
    private String respParams;

    /**
    * 创建时间
    */
    private Date createTime;




}