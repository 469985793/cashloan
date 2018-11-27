package com.xindaibao.cashloan.system.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 后台下载日志统计实体
 * 
 * @author xx
 * @version 1.0.0
 * @date 2017-11-29 15:39:34



 */
 public class SysDownloadLog implements Serializable {
    public static final String PAY_LOG = "支付记录";
    public static final String LOAN_LOG = "借款订单";
    public static final String REPAY_LOG = "还款记录";
   public static final String REPAYPLAN_LOG = "还款计划";
    public static final String TONGDUN_LOG = "同盾贷前审核记录";
    public static final String PAY_CHECK_LOG = "支付对账记录";
    public static final String EXPORT_LOG = "已逾期订单";
    public static final String BADDEBT_LOG = "已坏账订单";
    public static final String URGEREPAY_LOG = "催收总订单";
    public static final String URGE_FEEDBACK_LOG = "催收反馈";


    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 
    */
    private String user_name;

    /**
    * 添加时间
    */
    private Date create_time;

    /**
    * 下载菜单
    */
    private String download_menu;

    /**
    * 下载条数
    */
    private Integer download_count;


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
    * 获取
    *
    * @return 
    */
    public String getUser_name(){
        return user_name;
    }

    /**
    * 设置
    * 
    * @param user_name 要设置的
    */
    public void setUser_name(String user_name){
        this.user_name = user_name;
    }

    /**
    * 获取添加时间
    *
    * @return 添加时间
    */
    public Date getCreate_time(){
        return create_time;
    }

    /**
    * 设置添加时间
    * 
    * @param create_time 要设置的添加时间
    */
    public void setCreate_time(Date create_time){
        this.create_time = create_time;
    }

    /**
    * 获取下载菜单
    *
    * @return 下载菜单
    */
    public String getDownload_menu(){
        return download_menu;
    }

    /**
    * 设置下载菜单
    * 
    * @param download_menu 要设置的下载菜单
    */
    public void setDownload_menu(String download_menu){
        this.download_menu = download_menu;
    }

    /**
    * 获取下载条数
    *
    * @return 下载条数
    */
    public Integer getDownload_count(){
        return download_count;
    }

    /**
    * 设置下载条数
    * 
    * @param download_count 要设置的下载条数
    */
    public void setDownload_count(Integer download_count){
        this.download_count = download_count;
    }

}