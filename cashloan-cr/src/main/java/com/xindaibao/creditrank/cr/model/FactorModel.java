package com.xindaibao.creditrank.cr.model;

import java.util.List;

import com.xindaibao.creditrank.cr.domain.Factor;

/** 
 * @author
 * @version 1.0
 * @date 2017-1-5 下午4:51:02


 * 

 */
@SuppressWarnings("serial")
public class FactorModel extends Factor {
	
	/**
	 * 因子类型
	 * 10 系统
	 * 20 手动输入
	 * 30 来源于评分卡
	 */
	public static final Integer FACTOR_TYPE_SYSTEM = 10;
	public static final Integer FACTOR_TYPE_INPUT = 20;
	public static final Integer FACTOR_TYPE_CARD = 30;

	@SuppressWarnings("rawtypes")
	private List children;
	
	private String tab;
	
	private String weight;
	
	private String cardName;
	
	private String itemName;

	/**
	 * 获取children
	 * @return children
	 */
	@SuppressWarnings("rawtypes")
	public List getChildren() {
		return children;
	}

	/**
	 * 设置children
	 * @param children
	 */
	@SuppressWarnings("rawtypes")
	public void setChildren(List children) {
		this.children = children;
	}

	/**
	 * 获取tab
	 * @return tab
	 */
	public String getTab() {
		return tab;
	}

	/**
	 * 设置tab
	 * @param tab
	 */
	public void setTab(String tab) {
		this.tab = tab;
	}

	/**
	 * 获取weight
	 * @return weight
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * 设置weight
	 * @param weight
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/**
	 * 获取cardName
	 * @return cardName
	 */
	public String getCardName() {
		return cardName;
	}

	/**
	 * 设置cardName
	 * @param cardName
	 */
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	/**
	 * 获取itemName
	 * @return itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 设置itemName
	 * @param itemName
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
