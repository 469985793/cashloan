package com.xindaibao.creditrank.cr.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 评分因子实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-04 15:11:15


 * 

 */
 public class Factor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 评分因子名称
    */
    private String factorName;

    /**
    * 评分项目id
    */
    private Long itemId;


    /**
    * 因子最高分值
    */
    private Integer factorScore;

    /**
    * 状态 10启用 20禁用
    */
    private String state;

    /**
    * 添加时间
    */
    private Date addTime;

    /**
    * 维护类型 10-系统读取 20-手动录入 30-关联评分卡
    */
    private String type;

    /**
    * 信息类型 10 定性 20定量
    */
    private String nnid;
    
    /**
     * 关联表名称
     */
     private String ctable;

     /**
     * 关联表名称注释
     */
     private String ctableName;

     /**
     * 关联表字段
     */
     private String ccolumn;

     /**
     * 关联表字段注释
     */
     private String ccolumnName;
     
     /**
      * 表字段类型
      */
     private String ctype;
     
     /**
      * 唯一标识
      */
     private String nid;

    /**
    * 扩展字段
    */
    private long cardId;


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
    * 获取评分因子名称
    *
    * @return 评分因子名称
    */
    public String getFactorName(){
    return factorName;
    }

    /**
    * 设置评分因子名称
    * 
    * @param factorName 要设置的评分因子名称
    */
    public void setFactorName(String factorName){
    this.factorName = factorName;
    }


    /**
    * 获取评分项目id
    *
    * @return 评分项目id
    */
    public Long getItemId(){
    return itemId;
    }

    /**
    * 设置评分项目id
    * 
    * @param itemId 要设置的评分项目id
    */
    public void setItemId(Long itemId){
    this.itemId = itemId;
    }


    /**
    * 获取因子最高分值
    *
    * @return 因子最高分值
    */
    public Integer getFactorScore(){
    return factorScore;
    }

    /**
    * 设置因子最高分值
    * 
    * @param factorScore 要设置的因子最高分值
    */
    public void setFactorScore(Integer factorScore){
    this.factorScore = factorScore;
    }

    /**
    * 获取状态 10启用 20禁用
    *
    * @return 状态 10启用 20禁用
    */
    public String getState(){
    return state;
    }

    /**
    * 设置状态 10启用 20禁用
    * 
    * @param state 要设置的状态 10启用 20禁用
    */
    public void setState(String state){
    this.state = state;
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

    /**
    * 获取维护类型 10-系统读取 20-手动录入 30-关联评分卡
    *
    * @return 维护类型 10-系统读取 20-手动录入 30-关联评分卡
    */
    public String getType(){
    return type;
    }

    /**
    * 设置维护类型 10-系统读取 20-手动录入 30-关联评分卡
    * 
    * @param type 要设置的维护类型 10-系统读取 20-手动录入 30-关联评分卡
    */
    public void setType(String type){
    this.type = type;
    }

    /**
    * 获取信息类型 10 定性 20定量
    *
    * @return 信息类型 10 定性 20定量
    */
    public String getNnid(){
    return nnid;
    }

    /**
    * 设置信息类型 10 定性 20定量
    * 
    * @param nid 信息类型 10 定性 20定量
    */
    public void setNnid(String nnid){
    this.nnid = nnid;
    }
    
    /**
     * 获取关联表名称
     *
     * @return 关联表名称
     */
     public String getCtable(){
     return ctable;
     }

     /**
     * 设置关联表名称
     * 
     * @param ctable 要设置的关联表名称
     */
     public void setCtable(String ctable){
     this.ctable = ctable;
     }

     /**
     * 获取关联表名称注释
     *
     * @return 关联表名称注释
     */
     public String getCtableName(){
     return ctableName;
     }

     /**
     * 设置关联表名称注释
     * 
     * @param ctableName 要设置的关联表名称注释
     */
     public void setCtableName(String ctableName){
     this.ctableName = ctableName;
     }

     /**
     * 获取关联表字段
     *
     * @return 关联表字段
     */
     public String getCcolumn(){
     return ccolumn;
     }

     /**
     * 设置关联表字段
     * 
     * @param ccolumn 要设置的关联表字段
     */
     public void setCcolumn(String ccolumn){
     this.ccolumn = ccolumn;
     }

     /**
     * 获取关联表字段注释
     *
     * @return 关联表字段注释
     */
     public String getCcolumnName(){
     return ccolumnName;
     }

     /**
     * 设置关联表字段注释
     * 
     * @param ccolumnName 要设置的关联表字段注释
     */
     public void setCcolumnName(String ccolumnName){
     this.ccolumnName = ccolumnName;
     }

     /**
 	 * 获取表字段类型
 	 * @return type
 	 */
 	public String getCtype() {
 		return ctype;
 	}

 	/**
 	 * 设置表字段类型
 	 * @param type
 	 */
 	public void setCtype(String ctype) {
 		this.ctype = ctype;
 	}

    /**
    * 获取扩展字段
    *
    * @return 扩展字段
    */
    public long getCardId(){
    return cardId;
    }

    /**
    * 设置扩展字段
    * 
    * @param reqExt 要设置的扩展字段
    */
    public void setCardId(long cardId){
    this.cardId = cardId;
    }

	/**
	 * 获取nid
	 * @return nid
	 */
	public String getNid() {
		return nid;
	}

	/**
	 * 设置nid
	 * @param nid
	 */
	public void setNid(String nid) {
		this.nid = nid;
	}

}