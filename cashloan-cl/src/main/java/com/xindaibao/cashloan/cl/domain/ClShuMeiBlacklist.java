package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 数美逾期黑名单实体
 * 
 * @author xx
 * @version 1.0.0
 * @date 2017-11-22 16:10:04



 */
 public class ClShuMeiBlacklist implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 
    */
    private Long user_id;

    /**
    * 添加时间
    */
    private Date create_time;

    /**
     * 请求id
     */
    private String requestId;

    /**
    * 命中黑名单
    */
    private String in_black;

    /**
    * 返回data
    */
    private String submit_params;

    /**
    * 该用户在多少不同网贷平台发生了逾期
    */
    private Integer itfin_loan_overdues;

    /**
    * 网贷最大逾期时长级别
    */
    private Integer itfin_loan_overdue_duration;

    /**
    * 在最近 7 天中，该用户在多少 不同网贷平台发生了逾期
    */
    private Integer itfin_loan_overdues_7d;

    /**
    * 在最近 7 天中，网贷最大逾期 时长级别
    */
    private Integer itfin_loan_overdue_duration_7d;

    /**
    * 在最近 30 天中，该用户在多 少不同网贷平台发生了逾期
    */
    private Integer itfin_loan_overdues_30d;

    /**
    * 在最近 30 天中，网贷最大逾 期时长级别
    */
    private Integer itfin_loan_overdue_duration_30d;

    /**
    * 在最近 60 天中，该用户在多 少不同网贷平台发生了逾期
    */
    private Integer itfin_loan_overdues_60d;

    /**
    * 在最近 60 天中，网贷最大逾 期时长级别
    */
    private Integer itfin_loan_overdue_duration_60d;

    /**
    * 在最近 90 天中，该用户在多 少不同网贷平台发生了逾期
    */
    private Integer itfin_loan_overdues_90d;

    /**
    * 在最近 90 天中，网贷最大逾 期时长级别
    */
    private Integer itfin_loan_overdue_duration_90d;

    /**
    * 在最近 180 天中，该用户在多 少不同网贷平台发生了逾期
    */
    private Integer itfin_loan_overdues_180d;

    /**
    * 在最近 180 天中，网贷最大逾 期时长级别
    */
    private Integer itfin_loan_overdue_duration_180d;


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
    public Long getUser_id(){
        return user_id;
    }

    /**
    * 设置
    * 
    * @param user_id 要设置的
    */
    public void setUser_id(Long user_id){
        this.user_id = user_id;
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
    * 获取命中黑名单
    *
    * @return 命中黑名单
    */
    public String getIn_black(){
        return in_black;
    }

    /**
    * 设置命中黑名单
    * 
    * @param in_black 要设置的命中黑名单
    */
    public void setIn_black(String in_black){
        this.in_black = in_black;
    }

    /**
    * 获取返回data
    *
    * @return 返回data
    */
    public String getSubmit_params(){
        return submit_params;
    }

    /**
    * 设置返回data
    * 
    * @param submit_params 要设置的返回data
    */
    public void setSubmit_params(String submit_params){
        this.submit_params = submit_params;
    }

    /**
    * 获取该用户在多少不同网贷平台发生了逾期
    *
    * @return 该用户在多少不同网贷平台发生了逾期
    */
    public Integer getItfin_loan_overdues(){
        return itfin_loan_overdues;
    }

    /**
    * 设置该用户在多少不同网贷平台发生了逾期
    * 
    * @param itfin_loan_overdues 要设置的该用户在多少不同网贷平台发生了逾期
    */
    public void setItfin_loan_overdues(Integer itfin_loan_overdues){
        this.itfin_loan_overdues = itfin_loan_overdues;
    }

    /**
    * 获取网贷最大逾期时长级别
    *
    * @return 网贷最大逾期时长级别
    */
    public Integer getItfin_loan_overdue_duration(){
        return itfin_loan_overdue_duration;
    }

    /**
    * 设置网贷最大逾期时长级别
    * 
    * @param itfin_loan_overdue_duration 要设置的网贷最大逾期时长级别
    */
    public void setItfin_loan_overdue_duration(Integer itfin_loan_overdue_duration){
        this.itfin_loan_overdue_duration = itfin_loan_overdue_duration;
    }

    /**
    * 获取在最近 7 天中，该用户在多少 不同网贷平台发生了逾期
    *
    * @return 在最近 7 天中，该用户在多少 不同网贷平台发生了逾期
    */
    public Integer getItfin_loan_overdues_7d(){
        return itfin_loan_overdues_7d;
    }

    /**
    * 设置在最近 7 天中，该用户在多少 不同网贷平台发生了逾期
    * 
    * @param itfin_loan_overdues_7d 要设置的在最近 7 天中，该用户在多少 不同网贷平台发生了逾期
    */
    public void setItfin_loan_overdues_7d(Integer itfin_loan_overdues_7d){
        this.itfin_loan_overdues_7d = itfin_loan_overdues_7d;
    }

    /**
    * 获取在最近 7 天中，网贷最大逾期 时长级别
    *
    * @return 在最近 7 天中，网贷最大逾期 时长级别
    */
    public Integer getItfin_loan_overdue_duration_7d(){
        return itfin_loan_overdue_duration_7d;
    }

    /**
    * 设置在最近 7 天中，网贷最大逾期 时长级别
    * 
    * @param itfin_loan_overdue_duration_7d 要设置的在最近 7 天中，网贷最大逾期 时长级别
    */
    public void setItfin_loan_overdue_duration_7d(Integer itfin_loan_overdue_duration_7d){
        this.itfin_loan_overdue_duration_7d = itfin_loan_overdue_duration_7d;
    }

    /**
    * 获取在最近 30 天中，该用户在多 少不同网贷平台发生了逾期
    *
    * @return 在最近 30 天中，该用户在多 少不同网贷平台发生了逾期
    */
    public Integer getItfin_loan_overdues_30d(){
        return itfin_loan_overdues_30d;
    }

    /**
    * 设置在最近 30 天中，该用户在多 少不同网贷平台发生了逾期
    * 
    * @param itfin_loan_overdues_30d 要设置的在最近 30 天中，该用户在多 少不同网贷平台发生了逾期
    */
    public void setItfin_loan_overdues_30d(Integer itfin_loan_overdues_30d){
        this.itfin_loan_overdues_30d = itfin_loan_overdues_30d;
    }

    /**
    * 获取在最近 30 天中，网贷最大逾 期时长级别
    *
    * @return 在最近 30 天中，网贷最大逾 期时长级别
    */
    public Integer getItfin_loan_overdue_duration_30d(){
        return itfin_loan_overdue_duration_30d;
    }

    /**
    * 设置在最近 30 天中，网贷最大逾 期时长级别
    * 
    * @param itfin_loan_overdue_duration_30d 要设置的在最近 30 天中，网贷最大逾 期时长级别
    */
    public void setItfin_loan_overdue_duration_30d(Integer itfin_loan_overdue_duration_30d){
        this.itfin_loan_overdue_duration_30d = itfin_loan_overdue_duration_30d;
    }

    /**
    * 获取在最近 60 天中，该用户在多 少不同网贷平台发生了逾期
    *
    * @return 在最近 60 天中，该用户在多 少不同网贷平台发生了逾期
    */
    public Integer getItfin_loan_overdues_60d(){
        return itfin_loan_overdues_60d;
    }

    /**
    * 设置在最近 60 天中，该用户在多 少不同网贷平台发生了逾期
    * 
    * @param itfin_loan_overdues_60d 要设置的在最近 60 天中，该用户在多 少不同网贷平台发生了逾期
    */
    public void setItfin_loan_overdues_60d(Integer itfin_loan_overdues_60d){
        this.itfin_loan_overdues_60d = itfin_loan_overdues_60d;
    }

    /**
    * 获取在最近 60 天中，网贷最大逾 期时长级别
    *
    * @return 在最近 60 天中，网贷最大逾 期时长级别
    */
    public Integer getItfin_loan_overdue_duration_60d(){
        return itfin_loan_overdue_duration_60d;
    }

    /**
    * 设置在最近 60 天中，网贷最大逾 期时长级别
    * 
    * @param itfin_loan_overdue_duration_60d 要设置的在最近 60 天中，网贷最大逾 期时长级别
    */
    public void setItfin_loan_overdue_duration_60d(Integer itfin_loan_overdue_duration_60d){
        this.itfin_loan_overdue_duration_60d = itfin_loan_overdue_duration_60d;
    }

    /**
    * 获取在最近 90 天中，该用户在多 少不同网贷平台发生了逾期
    *
    * @return 在最近 90 天中，该用户在多 少不同网贷平台发生了逾期
    */
    public Integer getItfin_loan_overdues_90d(){
        return itfin_loan_overdues_90d;
    }

    /**
    * 设置在最近 90 天中，该用户在多 少不同网贷平台发生了逾期
    * 
    * @param itfin_loan_overdues_90d 要设置的在最近 90 天中，该用户在多 少不同网贷平台发生了逾期
    */
    public void setItfin_loan_overdues_90d(Integer itfin_loan_overdues_90d){
        this.itfin_loan_overdues_90d = itfin_loan_overdues_90d;
    }

    /**
    * 获取在最近 90 天中，网贷最大逾 期时长级别
    *
    * @return 在最近 90 天中，网贷最大逾 期时长级别
    */
    public Integer getItfin_loan_overdue_duration_90d(){
        return itfin_loan_overdue_duration_90d;
    }

    /**
    * 设置在最近 90 天中，网贷最大逾 期时长级别
    * 
    * @param itfin_loan_overdue_duration_90d 要设置的在最近 90 天中，网贷最大逾 期时长级别
    */
    public void setItfin_loan_overdue_duration_90d(Integer itfin_loan_overdue_duration_90d){
        this.itfin_loan_overdue_duration_90d = itfin_loan_overdue_duration_90d;
    }

    /**
    * 获取在最近 180 天中，该用户在多 少不同网贷平台发生了逾期
    *
    * @return 在最近 180 天中，该用户在多 少不同网贷平台发生了逾期
    */
    public Integer getItfin_loan_overdues_180d(){
        return itfin_loan_overdues_180d;
    }

    /**
    * 设置在最近 180 天中，该用户在多 少不同网贷平台发生了逾期
    * 
    * @param itfin_loan_overdues_180d 要设置的在最近 180 天中，该用户在多 少不同网贷平台发生了逾期
    */
    public void setItfin_loan_overdues_180d(Integer itfin_loan_overdues_180d){
        this.itfin_loan_overdues_180d = itfin_loan_overdues_180d;
    }

    /**
    * 获取在最近 180 天中，网贷最大逾 期时长级别
    *
    * @return 在最近 180 天中，网贷最大逾 期时长级别
    */
    public Integer getItfin_loan_overdue_duration_180d(){
        return itfin_loan_overdue_duration_180d;
    }

    /**
    * 设置在最近 180 天中，网贷最大逾 期时长级别
    * 
    * @param itfin_loan_overdue_duration_180d 要设置的在最近 180 天中，网贷最大逾 期时长级别
    */
    public void setItfin_loan_overdue_duration_180d(Integer itfin_loan_overdue_duration_180d){
        this.itfin_loan_overdue_duration_180d = itfin_loan_overdue_duration_180d;
    }
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {

        return requestId;
    }

}