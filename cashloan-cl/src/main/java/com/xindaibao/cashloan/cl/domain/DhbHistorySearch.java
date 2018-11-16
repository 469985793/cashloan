package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;

/**
 * 贷后邦历史查询实体
 */
 public class DhbHistorySearch implements Serializable {

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
    * 历史查询总次数
    */
    private Integer searchCnt;

    /**
    * 最近7天查询次数
    */
    private Integer searchCntRecent7Days;

    /**
    * 最近14天查询次数
    */
    private Integer searchCntRecent14Days;

    /**
    * 最近30天查询次数
    */
    private Integer searchCntRecent30Days;

    /**
    * 最近60天查询次数
    */
    private Integer searchCntRecent60Days;

    /**
    * 最近90天查询次数
    */
    private Integer searchCntRecent90Days;

    /**
    * 最近180天查询次数
    */
    private Integer searchCntRecent180Days;

    /**
    * 历史查询总机构数
    */
    private Integer orgCnt;

    /**
    * 最近7天查询机构数
    */
    private Integer orgCntRecent7Days;

    /**
    * 最近14天查询机构数
    */
    private Integer orgCntRecent14Days;

    /**
    * 最近30天查询机构数
    */
    private Integer orgCntRecent30Days;

    /**
    * 最近60天查询机构数
    */
    private Integer orgCntRecent60Days;

    /**
    * 最近90天查询机构数
    */
    private Integer orgCntRecent90Days;

    /**
    * 最近180天查询机构数
    */
    private Integer orgCntRecent180Days;


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
    * 获取历史查询总次数
    *
    * @return 历史查询总次数
    */
    public Integer getSearchCnt(){
        return searchCnt;
    }

    /**
    * 设置历史查询总次数
    * 
    * @param searchCnt 要设置的历史查询总次数
    */
    public void setSearchCnt(Integer searchCnt){
        this.searchCnt = searchCnt;
    }

    /**
    * 获取最近7天查询次数
    *
    * @return 最近7天查询次数
    */
    public Integer getSearchCntRecent7Days(){
        return searchCntRecent7Days;
    }

    /**
    * 设置最近7天查询次数
    * 
    * @param searchCntRecent7Days 要设置的最近7天查询次数
    */
    public void setSearchCntRecent7Days(Integer searchCntRecent7Days){
        this.searchCntRecent7Days = searchCntRecent7Days;
    }

    /**
    * 获取最近14天查询次数
    *
    * @return 最近14天查询次数
    */
    public Integer getSearchCntRecent14Days(){
        return searchCntRecent14Days;
    }

    /**
    * 设置最近14天查询次数
    * 
    * @param searchCntRecent14Days 要设置的最近14天查询次数
    */
    public void setSearchCntRecent14Days(Integer searchCntRecent14Days){
        this.searchCntRecent14Days = searchCntRecent14Days;
    }

    /**
    * 获取最近30天查询次数
    *
    * @return 最近30天查询次数
    */
    public Integer getSearchCntRecent30Days(){
        return searchCntRecent30Days;
    }

    /**
    * 设置最近30天查询次数
    * 
    * @param searchCntRecent30Days 要设置的最近30天查询次数
    */
    public void setSearchCntRecent30Days(Integer searchCntRecent30Days){
        this.searchCntRecent30Days = searchCntRecent30Days;
    }

    /**
    * 获取最近60天查询次数
    *
    * @return 最近60天查询次数
    */
    public Integer getSearchCntRecent60Days(){
        return searchCntRecent60Days;
    }

    /**
    * 设置最近60天查询次数
    * 
    * @param searchCntRecent60Days 要设置的最近60天查询次数
    */
    public void setSearchCntRecent60Days(Integer searchCntRecent60Days){
        this.searchCntRecent60Days = searchCntRecent60Days;
    }

    /**
    * 获取最近90天查询次数
    *
    * @return 最近90天查询次数
    */
    public Integer getSearchCntRecent90Days(){
        return searchCntRecent90Days;
    }

    /**
    * 设置最近90天查询次数
    * 
    * @param searchCntRecent90Days 要设置的最近90天查询次数
    */
    public void setSearchCntRecent90Days(Integer searchCntRecent90Days){
        this.searchCntRecent90Days = searchCntRecent90Days;
    }

    /**
    * 获取最近180天查询次数
    *
    * @return 最近180天查询次数
    */
    public Integer getSearchCntRecent180Days(){
        return searchCntRecent180Days;
    }

    /**
    * 设置最近180天查询次数
    * 
    * @param searchCntRecent180Days 要设置的最近180天查询次数
    */
    public void setSearchCntRecent180Days(Integer searchCntRecent180Days){
        this.searchCntRecent180Days = searchCntRecent180Days;
    }

    /**
    * 获取历史查询总机构数
    *
    * @return 历史查询总机构数
    */
    public Integer getOrgCnt(){
        return orgCnt;
    }

    /**
    * 设置历史查询总机构数
    * 
    * @param orgCnt 要设置的历史查询总机构数
    */
    public void setOrgCnt(Integer orgCnt){
        this.orgCnt = orgCnt;
    }

    /**
    * 获取最近7天查询机构数
    *
    * @return 最近7天查询机构数
    */
    public Integer getOrgCntRecent7Days(){
        return orgCntRecent7Days;
    }

    /**
    * 设置最近7天查询机构数
    * 
    * @param orgCntRecent7Days 要设置的最近7天查询机构数
    */
    public void setOrgCntRecent7Days(Integer orgCntRecent7Days){
        this.orgCntRecent7Days = orgCntRecent7Days;
    }

    /**
    * 获取最近14天查询机构数
    *
    * @return 最近14天查询机构数
    */
    public Integer getOrgCntRecent14Days(){
        return orgCntRecent14Days;
    }

    /**
    * 设置最近14天查询机构数
    * 
    * @param orgCntRecent14Days 要设置的最近14天查询机构数
    */
    public void setOrgCntRecent14Days(Integer orgCntRecent14Days){
        this.orgCntRecent14Days = orgCntRecent14Days;
    }

    /**
    * 获取最近30天查询机构数
    *
    * @return 最近30天查询机构数
    */
    public Integer getOrgCntRecent30Days(){
        return orgCntRecent30Days;
    }

    /**
    * 设置最近30天查询机构数
    * 
    * @param orgCntRecent30Days 要设置的最近30天查询机构数
    */
    public void setOrgCntRecent30Days(Integer orgCntRecent30Days){
        this.orgCntRecent30Days = orgCntRecent30Days;
    }

    /**
    * 获取最近60天查询机构数
    *
    * @return 最近60天查询机构数
    */
    public Integer getOrgCntRecent60Days(){
        return orgCntRecent60Days;
    }

    /**
    * 设置最近60天查询机构数
    * 
    * @param orgCntRecent60Days 要设置的最近60天查询机构数
    */
    public void setOrgCntRecent60Days(Integer orgCntRecent60Days){
        this.orgCntRecent60Days = orgCntRecent60Days;
    }

    /**
    * 获取最近90天查询机构数
    *
    * @return 最近90天查询机构数
    */
    public Integer getOrgCntRecent90Days(){
        return orgCntRecent90Days;
    }

    /**
    * 设置最近90天查询机构数
    * 
    * @param orgCntRecent90Days 要设置的最近90天查询机构数
    */
    public void setOrgCntRecent90Days(Integer orgCntRecent90Days){
        this.orgCntRecent90Days = orgCntRecent90Days;
    }

    /**
    * 获取最近180天查询机构数
    *
    * @return 最近180天查询机构数
    */
    public Integer getOrgCntRecent180Days(){
        return orgCntRecent180Days;
    }

    /**
    * 设置最近180天查询机构数
    * 
    * @param orgCntRecent180Days 要设置的最近180天查询机构数
    */
    public void setOrgCntRecent180Days(Integer orgCntRecent180Days){
        this.orgCntRecent180Days = orgCntRecent180Days;
    }

}