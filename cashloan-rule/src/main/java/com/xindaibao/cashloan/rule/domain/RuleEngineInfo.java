package com.xindaibao.cashloan.rule.domain;

import java.io.Serializable;

/**
 * 规则评分设置管理实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-03 17:28:16


 * 

 */
 public class RuleEngineInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 所属规则引擎id
    */
    private Long ruleEnginId;

    /**
    * 分值范围最小值
    */
    private Integer minIntegral;

    /**
    * 分值范围最大值
    */
    private Integer maxIntegral;

    /**
    * 结果描述
    */
    private String result;

    /**
    * 备份字段
    */
    private String reqExt;
    /**
     * 表达式
     */
    private String formula;
    /**
     * 分值
     */
     private Integer integral;
     
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
    * 获取所属规则引擎id
    *
    * @return 所属规则引擎id
    */
    public Long getRuleEnginId(){
    return ruleEnginId;
    }

    /**
    * 设置所属规则引擎id
    * 
    * @param ruleEnginId 要设置的所属规则引擎id
    */
    public void setRuleEnginId(Long ruleEnginId){
    this.ruleEnginId = ruleEnginId;
    }

    /**
    * 获取分值范围最小值
    *
    * @return 分值范围最小值
    */
    public Integer getMinIntegral(){
    return minIntegral;
    }

    /**
    * 设置分值范围最小值
    * 
    * @param minIntegral 要设置的分值范围最小值
    */
    public void setMinIntegral(Integer minIntegral){
    this.minIntegral = minIntegral;
    }

    /**
    * 获取分值范围最大值
    *
    * @return 分值范围最大值
    */
    public Integer getMaxIntegral(){
    return maxIntegral;
    }

    /**
    * 设置分值范围最大值
    * 
    * @param maxIntegral 要设置的分值范围最大值
    */
    public void setMaxIntegral(Integer maxIntegral){
    this.maxIntegral = maxIntegral;
    }

    /**
    * 获取结果描述
    *
    * @return 结果描述
    */
    public String getResult(){
    return result;
    }

    /**
    * 设置结果描述
    * 
    * @param result 要设置的结果描述
    */
    public void setResult(String result){
    this.result = result;
    }

    /**
    * 获取备份字段
    *
    * @return 备份字段
    */
    public String getReqExt(){
    return reqExt;
    }

    /**
    * 设置备份字段
    * 
    * @param reqExt 要设置的备份字段
    */
    public void setReqExt(String reqExt){
    this.reqExt = reqExt;
    }

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

}