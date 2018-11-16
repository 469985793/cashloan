package com.xindaibao.cashloan.rc.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 风控数据统计接口实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-04-13 17:57:55




 */
 public class StatisticsBusiness implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 风控数据统计分类Id
    */
    private Long statisticsId;

    /**
    * 接口名称
    */
    private String name;

    /**
    * 接口简称
    */
    private String nid;

    /**
    * 状态 10，启用；20，禁用
    */
    private String state;

    /**
    * 
    */
    private String extend;

    /**
    * 添加时间
    */
    private Date addTime;


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
	 * 获取风控数据统计分类Id
	 * @return statisticsId
	 */
	public Long getStatisticsId() {
		return statisticsId;
	}

	/** 
	 * 设置风控数据统计分类Id
	 * @param statisticsId
	 */
	public void setStatisticsId(Long statisticsId) {
		this.statisticsId = statisticsId;
	}

	/**
    * 获取接口名称
    *
    * @return 接口名称
    */
    public String getName(){
    return name;
    }

    /**
    * 设置接口名称
    * 
    * @param name 要设置的接口名称
    */
    public void setName(String name){
    this.name = name;
    }

    /**
    * 获取接口简称
    *
    * @return 接口简称
    */
    public String getNid(){
    return nid;
    }

    /**
    * 设置接口简称
    * 
    * @param nid 要设置的接口简称
    */
    public void setNid(String nid){
    this.nid = nid;
    }

    /**
    * 获取状态 10，启用；20，禁用
    *
    * @return 状态 10，启用；20，禁用
    */
    public String getState(){
    return state;
    }

    /**
    * 设置状态 10，启用；20，禁用
    * 
    * @param state 要设置的状态 10，启用；20，禁用
    */
    public void setState(String state){
    this.state = state;
    }

    /**
    * 获取
    *
    * @return 
    */
    public String getExtend(){
    return extend;
    }

    /**
    * 设置
    * 
    * @param extend 要设置的
    */
    public void setExtend(String extend){
    this.extend = extend;
    }

    /**
    * 获取添加时间
    *
    * @return 添加时间
    */
    public Date getAddTime(){
    return addTime;
    }

    /**
    * 设置添加时间
    * 
    * @param addTime 要设置的添加时间
    */
    public void setAddTime(Date addTime){
    this.addTime = addTime;
    }

}