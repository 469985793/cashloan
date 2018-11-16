package com.xindaibao.creditrank.cr.model;

import java.io.Serializable;

/**
 * 信用评级参数信息查询
 * @author
 * @version 1.0.0
 * @date 2017年1月6日 上午10:53:17


 * 

 */
public class CreditRatingModel implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 借款类型
	 */
	private String borrowTypeId;
	
	/**
	 * 借款类型名称
	 */
	private String borrowTypeName;
	
	/**
	 * 额度类型ID
	 */
	private Long creditTypeId;
	
	/**
	 * 额度类型名称
	 */
	private String creditTypeName;

	/**
	 * 评分卡ID
	 */
	private Long cardId;
	
	/**
	 * 评分卡分数
	 */
	private Integer cardScore;
	
	/**
	 * 评分项目ID
	 */
	private Long itemId;
	
	/**
	 * 评分项目总分
	 */
	private Integer itemScore;
	
	/**
	 * 评分因子ID
	 */
	private Long factorId;
	
	/**
	 * 评分因子最高分
	 */
	private Integer factorScore;
	
	/**
	 * 因子参数ID
	 */
	private Long paramId;
	
	/**
	 * 因子参数得分
	 */
	private Integer paramScore;
	
	/**
	 * 表名称
	 */
	private String tabName;
	
	/**
	 * 列名称
	 */
	private String colName;
	
	/**
	 * 表达式
	 */
	private String formula;
	
	/**
	 * 因子范围值
	 */
	private String range;
	
	/**
	 * 类型  int  数字  string 文字
	 */
	private String type;
	
	/**
	 * 最后得分
	 */
	private Integer score;

	/** 
	 * 获取评分卡ID
	 * @return cardId
	 */
	public Long getCardId() {
		return cardId;
	}

	/** 
	 * 设置评分卡ID
	 * @param cardId
	 */
	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	/** 
	 * 获取评分卡分数
	 * @return cardScore
	 */
	public Integer getCardScore() {
		return cardScore;
	}

	/** 
	 * 设置评分卡分数
	 * @param cardScore
	 */
	public void setCardScore(Integer cardScore) {
		this.cardScore = cardScore;
	}

	/** 
	 * 获取评分项目ID
	 * @return itemId
	 */
	public Long getItemId() {
		return itemId;
	}

	/** 
	 * 设置评分项目ID
	 * @param itemId
	 */
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	/** 
	 * 获取评分项目总分
	 * @return itemScore
	 */
	public Integer getItemScore() {
		return itemScore;
	}

	/** 
	 * 设置评分项目总分
	 * @param itemScore
	 */
	public void setItemScore(Integer itemScore) {
		this.itemScore = itemScore;
	}

	/** 
	 * 获取评分因子ID
	 * @return factorId
	 */
	public Long getFactorId() {
		return factorId;
	}

	/** 
	 * 设置评分因子ID
	 * @param factorId
	 */
	public void setFactorId(Long factorId) {
		this.factorId = factorId;
	}

	/** 
	 * 获取评分因子最高分
	 * @return factorScore
	 */
	public Integer getFactorScore() {
		return factorScore;
	}

	/** 
	 * 设置评分因子最高分
	 * @param factorScore
	 */
	public void setFactorScore(Integer factorScore) {
		this.factorScore = factorScore;
	}

	/** 
	 * 获取因子参数ID
	 * @return paramId
	 */
	public Long getParamId() {
		return paramId;
	}

	/** 
	 * 设置因子参数ID
	 * @param paramId
	 */
	public void setParamId(Long paramId) {
		this.paramId = paramId;
	}

	/** 
	 * 获取因子参数得分
	 * @return paramScore
	 */
	public Integer getParamScore() {
		return paramScore;
	}

	/** 
	 * 设置因子参数得分
	 * @param paramScore
	 */
	public void setParamScore(Integer paramScore) {
		this.paramScore = paramScore;
	}

	/** 
	 * 获取表名称
	 * @return tabName
	 */
	public String getTabName() {
		return tabName;
	}

	/** 
	 * 设置表名称
	 * @param tabName
	 */
	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	/** 
	 * 获取列名称
	 * @return colName
	 */
	public String getColName() {
		return colName;
	}

	/** 
	 * 设置列名称
	 * @param colName
	 */
	public void setColName(String colName) {
		this.colName = colName;
	}

	/** 
	 * 获取表达式
	 * @return formula
	 */
	public String getFormula() {
		return formula;
	}

	/** 
	 * 设置表达式
	 * @param formula
	 */
	public void setFormula(String formula) {
		this.formula = formula;
	}

	/** 
	 * 获取因子范围值
	 * @return range
	 */
	public String getRange() {
		return range;
	}

	/** 
	 * 设置因子范围值
	 * @param range
	 */
	public void setRange(String range) {
		this.range = range;
	}

	/** 
	 * 获取类型int数字string文字
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/** 
	 * 设置类型int数字string文字
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/** 
	 * 获取最后得分
	 * @return score
	 */
	public Integer getScore() {
		return score;
	}

	/** 
	 * 设置最后得分
	 * @param score
	 */
	public void setScore(Integer score) {
		this.score = score;
	}

	/** 
	 * 获取额度类型ID
	 * @return creditTypeId
	 */
	public Long getCreditTypeId() {
		return creditTypeId;
	}

	/** 
	 * 设置额度类型ID
	 * @param creditTypeId
	 */
	public void setCreditTypeId(Long creditTypeId) {
		this.creditTypeId = creditTypeId;
	}

	/** 
	 * 获取额度类型名称
	 * @return creditTypeName
	 */
	public String getCreditTypeName() {
		return creditTypeName;
	}

	/** 
	 * 设置额度类型名称
	 * @param creditTypeName
	 */
	public void setCreditTypeName(String creditTypeName) {
		this.creditTypeName = creditTypeName;
	}

	/** 
	 * 获取借款类型
	 * @return borrowTypeId
	 */
	public String getBorrowTypeId() {
		return borrowTypeId;
	}

	/** 
	 * 设置借款类型
	 * @param borrowTypeId
	 */
	public void setBorrowTypeId(String borrowTypeId) {
		this.borrowTypeId = borrowTypeId;
	}

	/** 
	 * 获取借款类型名称
	 * @return borrowTypeName
	 */
	public String getBorrowTypeName() {
		return borrowTypeName;
	}

	/** 
	 * 设置借款类型名称
	 * @param borrowTypeName
	 */
	public void setBorrowTypeName(String borrowTypeName) {
		this.borrowTypeName = borrowTypeName;
	}
	
	
}
