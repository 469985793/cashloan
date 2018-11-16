package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;

/**
 * 贷后邦社交风险点实体
 */
 public class DhbRiskSocialNetwork implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 订单号
    */
    private String orderNo;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 葫芦分
    */
    private Integer snScore;

    /**
    * 直接联系人
    */
    private Integer snOrder1ContactsCnt;

    /**
    * 直接联系人在黑名单中数量(直接黑人)
    */
    private Integer snOrder1BlacklistContactsCnt;

    /**
    * 间接联系人在黑名单中数量(间接黑人)
    */
    private Integer snOrder2BlacklistContactsCnt;

    /**
    * 认识间接黑人的直接联系人个数
    */
    private Integer snOrder2BlacklistRoutersCnt;

    /**
    * 认识间接黑人的直接联系人比例
    */
    private Double snOrder2BlacklistRoutersPct;


    /**
    * 获取主键Id
    *
    * @return id
    */
    public Long getId(){
        return id;
    }

    /**
    * 设置主键Id
    * 
    * @param 要设置的主键Id
    */
    public void setId(Long id){
        this.id = id;
    }

    /**
    * 获取订单号
    *
    * @return 订单号
    */
    public String getOrderNo(){
        return orderNo;
    }

    /**
    * 设置订单号
    * 
    * @param orderNo 要设置的订单号
    */
    public void setOrderNo(String orderNo){
        this.orderNo = orderNo;
    }

    /**
    * 获取用户id
    *
    * @return 用户id
    */
    public Long getUserId(){
        return userId;
    }

    /**
    * 设置用户id
    * 
    * @param userId 要设置的用户id
    */
    public void setUserId(Long userId){
        this.userId = userId;
    }

    /**
    * 获取葫芦分
    *
    * @return 葫芦分
    */
    public Integer getSnScore(){
        return snScore;
    }

    /**
    * 设置葫芦分
    * 
    * @param snScore 要设置的葫芦分
    */
    public void setSnScore(Integer snScore){
        this.snScore = snScore;
    }

    /**
    * 获取直接联系人
    *
    * @return 直接联系人
    */
    public Integer getSnOrder1ContactsCnt(){
        return snOrder1ContactsCnt;
    }

    /**
    * 设置直接联系人
    * 
    * @param snOrder1ContactsCnt 要设置的直接联系人
    */
    public void setSnOrder1ContactsCnt(Integer snOrder1ContactsCnt){
        this.snOrder1ContactsCnt = snOrder1ContactsCnt;
    }

    /**
    * 获取直接联系人在黑名单中数量(直接黑人)
    *
    * @return 直接联系人在黑名单中数量(直接黑人)
    */
    public Integer getSnOrder1BlacklistContactsCnt(){
        return snOrder1BlacklistContactsCnt;
    }

    /**
    * 设置直接联系人在黑名单中数量(直接黑人)
    * 
    * @param snOrder1BlacklistContactsCnt 要设置的直接联系人在黑名单中数量(直接黑人)
    */
    public void setSnOrder1BlacklistContactsCnt(Integer snOrder1BlacklistContactsCnt){
        this.snOrder1BlacklistContactsCnt = snOrder1BlacklistContactsCnt;
    }

    /**
    * 获取间接联系人在黑名单中数量(间接黑人)
    *
    * @return 间接联系人在黑名单中数量(间接黑人)
    */
    public Integer getSnOrder2BlacklistContactsCnt(){
        return snOrder2BlacklistContactsCnt;
    }

    /**
    * 设置间接联系人在黑名单中数量(间接黑人)
    * 
    * @param snOrder2BlacklistContactsCnt 要设置的间接联系人在黑名单中数量(间接黑人)
    */
    public void setSnOrder2BlacklistContactsCnt(Integer snOrder2BlacklistContactsCnt){
        this.snOrder2BlacklistContactsCnt = snOrder2BlacklistContactsCnt;
    }

    /**
    * 获取认识间接黑人的直接联系人个数
    *
    * @return 认识间接黑人的直接联系人个数
    */
    public Integer getSnOrder2BlacklistRoutersCnt(){
        return snOrder2BlacklistRoutersCnt;
    }

    /**
    * 设置认识间接黑人的直接联系人个数
    * 
    * @param snOrder2BlacklistRoutersCnt 要设置的认识间接黑人的直接联系人个数
    */
    public void setSnOrder2BlacklistRoutersCnt(Integer snOrder2BlacklistRoutersCnt){
        this.snOrder2BlacklistRoutersCnt = snOrder2BlacklistRoutersCnt;
    }

    /**
    * 获取认识间接黑人的直接联系人比例
    *
    * @return 认识间接黑人的直接联系人比例
    */
    public Double getSnOrder2BlacklistRoutersPct(){
        return snOrder2BlacklistRoutersPct;
    }

    /**
    * 设置认识间接黑人的直接联系人比例
    * 
    * @param snOrder2BlacklistRoutersPct 要设置的认识间接黑人的直接联系人比例
    */
    public void setSnOrder2BlacklistRoutersPct(Double snOrder2BlacklistRoutersPct){
        this.snOrder2BlacklistRoutersPct = snOrder2BlacklistRoutersPct;
    }

}