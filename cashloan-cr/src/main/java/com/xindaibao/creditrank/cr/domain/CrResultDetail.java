package com.xindaibao.creditrank.cr.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 评分结果实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-05 16:46:34


 * 

 */
 public class CrResultDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 等级标识
     * 40 因子参数 30 因子 20 项目 10 评分卡
     */
    public static final String LEVEL_FACTOR_PARAM = "40";
    public static final String LEVEL_FACTOR = "30";
    public static final String LEVEL_ITEM = "20";
    public static final String LEVEL_CARD = "10";

    /**
    * 主键Id
    */
    private Long id;
    /**
     * 用户ID
     */
     private Long resultId;

    /**
    * 评分卡ID
    */
    private Long cardId;

    /**
    * 评分项目ID
    */
    private Long itemId;

    /**
    * 评分因子ID
    */
    private Long factorId;
    
    /**
     * 因子参数ID
     */
    private Long paramId;

    /**
    * 参数名称
    */
    private String paramName;

    /**
    * 最高分值
    */
    private Integer paramScore;

    /**
    * 表达式
    */
    private String formula;

    /**
    * 参数取值范围
    */
    private String cvalue;

    /**
    * 参与评分的因子实际值
    */
    private String value;

    /**
    * 评分分数级别10 评分卡 20 项目 30 因子
    */
    private String level;

    /**
    * 得分
    */
    private Integer score;
    
    /**
     * 评分卡额度
     */
    private BigDecimal amount;
    
    /**
     * 评分卡等级
     */
    private String cardLevel;

    /**
    * 添加时间
    */
    private Date addTime;
    
    
    
    public CrResultDetail(Long resultId, Long cardId, Long itemId,
			Long factorId, Long paramId, String paramName, Integer paramScore,
			String formula, String cvalue) {
		super();
		this.resultId = resultId;
		this.cardId = cardId;
		this.itemId = itemId;
		this.factorId = factorId;
		this.paramId = paramId;
		this.paramName = paramName;
		this.paramScore = paramScore;
		this.formula = formula;
		this.cvalue = cvalue;
	}

	public CrResultDetail() {
		super();
	}

	/** 
	 * 获取主键Id
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/** 
	 * 设置主键Id
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/** 
	 * 获取用户ID
	 * @return resultId
	 */
	public Long getResultId() {
		return resultId;
	}

	/** 
	 * 设置用户ID
	 * @param resultId
	 */
	public void setResultId(Long resultId) {
		this.resultId = resultId;
	}

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
	 * 获取参数名称
	 * @return paramName
	 */
	public String getParamName() {
		return paramName;
	}

	/** 
	 * 设置参数名称
	 * @param paramName
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	/** 
	 * 获取最高分值
	 * @return paramScore
	 */
	public Integer getParamScore() {
		return paramScore;
	}

	/** 
	 * 设置最高分值
	 * @param paramScore
	 */
	public void setParamScore(Integer paramScore) {
		this.paramScore = paramScore;
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
	 * 获取参数取值范围
	 * @return cvalue
	 */
	public String getCvalue() {
		return cvalue;
	}

	/** 
	 * 设置参数取值范围
	 * @param cvalue
	 */
	public void setCvalue(String cvalue) {
		this.cvalue = cvalue;
	}

	/** 
	 * 获取参与评分的因子实际值
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/** 
	 * 设置参与评分的因子实际值
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/** 
	 * 获取评分分数级别10评分卡20项目30因子
	 * @return level
	 */
	public String getLevel() {
		return level;
	}

	/** 
	 * 设置评分分数级别10评分卡20项目30因子
	 * @param level
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/** 
	 * 获取得分
	 * @return score
	 */
	public Integer getScore() {
		return score;
	}

	/** 
	 * 设置得分
	 * @param score
	 */
	public void setScore(Integer score) {
		this.score = score;
	}

	/** 
	 * 获取添加时间
	 * @return addTime
	 */
	public Date getAddTime() {
		return addTime;
	}

	/** 
	 * 设置添加时间
	 * @param addTime
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	/** 
	 * 获取评分卡额度
	 * @return amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/** 
	 * 设置评分卡额度
	 * @param amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/** 
	 * 获取评分卡等级
	 * @return cardLevel
	 */
	public String getCardLevel() {
		return cardLevel;
	}

	/** 
	 * 设置评分卡等级
	 * @param cardLevel
	 */
	public void setCardLevel(String cardLevel) {
		this.cardLevel = cardLevel;
	}


}
