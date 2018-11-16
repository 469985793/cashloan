package com.xindaibao.creditrank.cr.domain;

import java.io.Serializable;

/**
 * 评分等级实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-04 15:09:59


 * 

 */
 public class Rank implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;


    /**
    * 评分卡名称
    */
    private String rankName;


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
	 * 获取rankName
	 * @return rankName
	 */
	public String getRankName() {
		return rankName;
	}

	/**
	 * 设置rankName
	 * @param rankName
	 */
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	
}