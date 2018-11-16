package com.xindaibao.cashloan.rule.domain;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 规则引擎配置信息
 * 
 * @author
 * @version 1.0.0
 * @date 2016-12-12 17:25:31


 * 

 */
 public class RuleEngineConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	/******************* 结果类型 *************************/
	/**
	 * 结果类型 10 不通过 20 需人工复审 30 通过
	 */
	public static final String RESULT_FAIL = "10";
	public static final String RESULT_REVIEW = "20";
	public static final String RESULT_PASS = "30";
	/******************* 结果类型 *************************/

	/**
	 * 主键Id
	 */
	private Long id;

	/**
	 * 所属规则引擎id
	 */
	private Long ruleEnginId;

	/**
	 * 设置关联表名称
	 */
	private String ctable;

	/**
	 * 设置关联表列
	 */
	private String ccolumn;

	/**
	 * 公式
	 */
	private String formula;

	/**
	 * 值
	 */
	private String cvalue;
	/**
	 * 状态：10启用，20禁用
	 */
	private String state;

	/**
	 * 扩展字段
	 */
	private String reqExt;

	/**
	 * 添加IP
	 */
	private String addIp;

	/**
	 * 表名名称
	 */
	private String tableComment;
	/**
	 * 字段名称
	 */
	private String columnComment;
	/**
	 * 添加时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date addTime;
	/**
	 * 规则类型 int string
	 */
	private String type;

	/**
	 * 分值
	 */
	private Integer integral;

	/**
	 * 结果
	 */
	private String result;

	/**
	 * 结果类型 10 不通过 20 需要人工审核 30 通过
	 */
	private String resultType;
	/**
	 * 权重 作废 预留字段
	 */
	private Integer sort;

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	/**
	 * 获取主键Id
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置主键Id
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取所属规则引擎id
	 * 
	 * @return ruleEnginId
	 */
	public Long getRuleEnginId() {
		return ruleEnginId;
	}

	/**
	 * 设置所属规则引擎id
	 * 
	 * @param ruleEnginId
	 */
	public void setRuleEnginId(Long ruleEnginId) {
		this.ruleEnginId = ruleEnginId;
	}

	/**
	 * 获取设置关联表名称
	 * 
	 * @return 设置关联表名称
	 */
	public String getCtable() {
		return ctable;
	}

	/**
	 * 设置设置关联表名称
	 * 
	 * @param ctable
	 *            要设置的设置关联表名称
	 */
	public void setCtable(String ctable) {
		this.ctable = ctable;
	}

	/**
	 * 获取设置关联表列
	 * 
	 * @return 设置关联表列
	 */
	public String getCcolumn() {
		return ccolumn;
	}

	/**
	 * 设置设置关联表列
	 * 
	 * @param ccolumn
	 *            要设置的设置关联表列
	 */
	public void setCcolumn(String ccolumn) {
		this.ccolumn = ccolumn;
	}

	/**
	 * 获取公式
	 * 
	 * @return 公式
	 */
	public String getFormula() {
		return formula;
	}

	/**
	 * 设置公式
	 * 
	 * @param formula
	 *            要设置的公式
	 */
	public void setFormula(String formula) {
		this.formula = formula;
	}

	/**
	 * 获取值
	 * 
	 * @return 值
	 */
	public String getCvalue() {
		return cvalue;
	}

	/**
	 * 设置值
	 * 
	 * @param cvalue
	 *            要设置的值
	 */
	public void setCvalue(String cvalue) {
		this.cvalue = cvalue;
	}

	/**
	 * 获取状态：10启用，20禁用
	 * 
	 * @return 状态：10启用，20禁用
	 */
	public String getState() {
		return state;
	}

	/**
	 * 设置状态：10启用，20禁用
	 * 
	 * @param state
	 *            要设置的状态：10启用，20禁用
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 获取扩展字段
	 * 
	 * @return 扩展字段
	 */
	public String getReqExt() {
		return reqExt;
	}

	/**
	 * 设置扩展字段
	 * 
	 * @param reqExt
	 *            要设置的扩展字段
	 */
	public void setReqExt(String reqExt) {
		this.reqExt = reqExt;
	}

	/**
	 * 获取添加IP
	 * 
	 * @return 添加IP
	 */
	public String getAddIp() {
		return addIp;
	}

	/**
	 * 设置添加IP
	 * 
	 * @param addIp
	 *            要设置的添加IP
	 */
	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}

	/**
	 * 获取添加时间
	 * 
	 * @return 添加时间
	 */
	public Date getAddTime() {
		return addTime;
	}

	/**
	 * 设置添加时间
	 * 
	 * @param addTime
	 *            要设置的添加时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	/**
	 * 获取类型
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置类型
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

}
