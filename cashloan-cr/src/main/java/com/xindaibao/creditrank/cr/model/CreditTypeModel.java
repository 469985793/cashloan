package com.xindaibao.creditrank.cr.model;

import com.xindaibao.creditrank.cr.domain.CreditType;

/**
 * 额度类型页面显示model
 * @author
 * @version 1.0.0
 * @date 2017年1月20日 下午2:55:58


 * 

 */
public class CreditTypeModel extends CreditType {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 评分卡名称
	 */
	private String cardName;
	
	/**
	 * 评分等级名称
	 */
	private String rankName;
	
	/**
	 * 借款类型名称
	 */
	private String borrowTypeName;

	/** 
	 * 获取评分卡名称
	 * @return cardName
	 */
	public String getCardName() {
		return cardName;
	}

	/** 
	 * 设置评分卡名称
	 * @param cardName
	 */
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	/** 
	 * 获取评分等级名称
	 * @return rankName
	 */
	public String getRankName() {
		return rankName;
	}

	/** 
	 * 设置评分等级名称
	 * @param rankName
	 */
	public void setRankName(String rankName) {
		this.rankName = rankName;
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
