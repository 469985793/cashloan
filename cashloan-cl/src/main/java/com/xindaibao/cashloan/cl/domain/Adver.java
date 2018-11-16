package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;

/**
 * 广告管理实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-06-21 14:33:20



 */
 public class Adver implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 标题
    */
    private String title;

    /**
    * 路径
    */
    private String path;

    /**
    * 排序号
    */
    private Long sort;

    /**
    * 状态 10-启用 20-禁用
    */
    private String state;
    
    /**
     * 链接
     */
    private String link;


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
    * 获取标题
    *
    * @return title
    */
    public String getTitle(){
        return title;
    }

    /**
    * 设置标题
    * 
    * @param title 要设置的标题
    */
    public void setTitle(String title){
        this.title = title;
    }

    /**
    * 获取路径
    *
    * @return path
    */
    public String getPath(){
        return path;
    }

    /**
    * 设置路径
    * 
    * @param path 要设置的路径
    */
    public void setPath(String path){
        this.path = path;
    }

    /**
    * 获取排序号
    *
    * @return sort
    */
    public Long getSort(){
        return sort;
    }

    /**
    * 设置排序号
    * 
    * @param sort 要设置的排序号
    */
    public void setSort(Long sort){
        this.sort = sort;
    }

    /**
    * 获取状态 10-启用 20-禁用
    *
    * @return state
    */
    public String getState(){
        return state;
    }

    /**
    * 设置状态 10-启用 20-禁用
    * 
    * @param state 要设置的状态 10-启用 20-禁用
    */
    public void setState(String state){
        this.state = state;
    }

	/**
	 * 获取链接
	 * @return link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * 设置链接
	 * @param link
	 */
	public void setLink(String link) {
		this.link = link;
	}

}