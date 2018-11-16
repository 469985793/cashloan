package com.xindaibao.cashloan.rule.model.srule.model;

/**
 * 基础规则信息，默认规则为结果导向
 * @author
 * @version 1.0.0
 * @date 2017年1月3日 上午11:49:01


 * 

 */
public class SimpleRule {
	
	
	/**
	 * 数字类型
	 */
	public static final String NUMERIC = "int";
	
	/**
	 * 文字类型
	 */
	public static final String TEXT = "string";
	
	/*********************对比结果***********************/
	/**
	 * 对比结果 N 不匹配  Y 通过
	 */
	public static final String COMPAR_PASS = "Y";
	public static final String COMPAR_FAIL = "N";
	/*********************对比结果***********************/
	
	/**
	 * 规则唯一标识
	 */
	public Long ruleId;
	
	/**
	 * 规则名称
	 */
	public String name; 
	
	/**
	 * 规则表达式 
	 */
	public String formula;
	
	/**
	 * 需要匹配的值
	 */
	public String value;
	
	/**
	 * 取值范围
	 */
	public String range;
	
	/**
	 * 对比类型   Numeric  Text
	 */
	public String type;
	
	/**
	 * 结果类型 10 不通过   20 待人工复审  30 通过
	 */
	public String resultType;
	
	/**
	 * 对比结果 N 不匹配  Y 通过
	 */
	public String comparResult;
	
	public SimpleRule() {
		super();
	}

	/**
	 * 构造规则对象
	 * @param ruleId 规则唯一标识
	 * @param name 规则对比的参数名称
	 * @param formula 规则对比使用的表达式 
	 * @param value 需要做比对的值
	 * @param range 表达式的取值范围
	 * @param type 表达式的类型   Numeric(数字类型)  Text(文本类型)
	 * @param resultType 结果类型 10 不通过   20 待人工复审  30 通过 (表达式匹配时代表的结果)
	 */
	public SimpleRule(Long ruleId, String name, String formula, String value,
			String range, String type, String resultType) {
		super();
		this.ruleId = ruleId;
		this.name = name;
		this.formula = formula;
		this.value = value;
		this.range = range;
		this.type = type;
		this.resultType = resultType;
	}


	/** 
	 * 获取规则唯一标识
	 * @return ruleId
	 */
	public Long getRuleId() {
		return ruleId;
	}

	/** 
	 * 设置规则唯一标识
	 * @param ruleId
	 */
	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	/** 
	 * 获取规则名称
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/** 
	 * 设置规则名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** 
	 * 获取规则表达式
	 * @return formula
	 */
	public String getFormula() {
		return formula;
	}

	/** 
	 * 设置规则表达式
	 * @param formula
	 */
	public void setFormula(String formula) {
		this.formula = formula;
	}

	/** 
	 * 获取需要匹配的值
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/** 
	 * 设置需要匹配的值
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/** 
	 * 获取取值范围
	 * @return range
	 */
	public String getRange() {
		return range;
	}

	/** 
	 * 设置取值范围
	 * @param range
	 */
	public void setRange(String range) {
		this.range = range;
	}

	/** 
	 * 获取对比类型NumericText
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/** 
	 * 设置对比类型NumericText
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	/** 
	 * 获取结果类型10通过20不通过
	 * @return resultType
	 */
	public String getResultType() {
		return resultType;
	}

	/** 
	 * 设置结果类型10通过20不通过
	 * @param resultType
	 */
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	
	/** 
	 * 获取对比结果N不匹配Y通过
	 * @return comparResult
	 */
	public String getComparResult() {
		return comparResult;
	}
	
	/** 
	 * 设置对比结果N不匹配Y通过
	 * @param comparResult
	 */
	public void setComparResult(String comparResult) {
		this.comparResult = comparResult;
	}
}