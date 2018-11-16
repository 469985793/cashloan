package com.xindaibao.cashloan.rule.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 借款规则管理实体
 * 
 * @author
 * @version 1.0.0


 * 

 */
public class BorrowRuleEngine implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 使用场景 10 贷前 20 贷后
	 */
	public static final String ADAPTED_BEFORE = "10";
	public static final String ADAPTED_AFTER = "20";

	public String getAdaptedNameById(String adaptedId) {
		String adaptedName = "";
		if (adaptedId != null && adaptedId != "") {
			if (adaptedId.equals(BorrowRuleEngine.ADAPTED_BEFORE)) {
				adaptedName = "贷前";
			} else if (adaptedId.equals(BorrowRuleEngine.ADAPTED_AFTER)) {
				adaptedName = "贷后";
			}
		}
		return adaptedName;
	}

	/**
	 * 主键Id
	 */
	private Long id;

	/**
	 * 借款类型
	 */
	private String borrowTypeName;

	/**
	 * 借款类型标识
	 */
	private String borrowType;

	/**
	 * 规则类型
	 */
	private Integer ruleCount;

	/**
	 * 规则适用场景标识标识
	 */
	private String adaptedId;

	/**
	 * 规则适用场景标识名称
	 */
	private String adaptedName;

	/**
	 * 添加规则时间
	 */
	private Date addTime;

	/**
	 * 预留字段
	 */
	private String reqExt;

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
	 * @param 要设置的主键Id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取借款类型
	 * 
	 * @return 借款类型
	 */
	public String getBorrowTypeName() {
		return borrowTypeName;
	}

	/**
	 * 设置借款类型
	 * 
	 * @param borrowTypeName
	 *            要设置的借款类型
	 */
	public void setBorrowTypeName(String borrowTypeName) {
		this.borrowTypeName = borrowTypeName;
	}

	/**
	 * 获取借款类型标识
	 * 
	 * @return 借款类型标识
	 */
	public String getBorrowType() {
		return borrowType;
	}

	/**
	 * 设置借款类型标识
	 * 
	 * @param borrowType
	 *            要设置的借款类型标识
	 */
	public void setBorrowType(String borrowType) {
		this.borrowType = borrowType;
	}

	public Integer getRuleCount() {
		return ruleCount;
	}

	public void setRuleCount(Integer ruleCount) {
		this.ruleCount = ruleCount;
	}

	/**
	 * 获取添加规则时间
	 * 
	 * @return 添加规则时间
	 */
	public Date getAddTime() {
		return addTime;
	}

	/**
	 * 设置添加规则时间
	 * 
	 * @param addTime
	 *            要设置的添加规则时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	/**
	 * 获取预留字段
	 * 
	 * @return 预留字段
	 */
	public String getReqExt() {
		return reqExt;
	}

	/**
	 * 设置预留字段
	 * 
	 * @param reqExt
	 *            要设置的预留字段
	 */
	public void setReqExt(String reqExt) {
		this.reqExt = reqExt;
	}

	public String getAdaptedId() {
		return adaptedId;
	}

	public void setAdaptedId(String adaptedId) {
		this.adaptedId = adaptedId;
	}

	public String getAdaptedName() {
		return adaptedName;
	}

	public void setAdaptedName(String adaptedName) {
		this.adaptedName = adaptedName;
	}

}