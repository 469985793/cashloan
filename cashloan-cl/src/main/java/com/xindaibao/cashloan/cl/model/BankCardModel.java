package com.xindaibao.cashloan.cl.model;

import com.xindaibao.cashloan.cl.domain.BankCard;

public class BankCardModel extends BankCard {

	private static final long serialVersionUID = 1L;
	/**
	 * 能替换绑卡
	 */
	public static final String STATE_ENABLE = "10";
	/**
	 * 不能替换绑卡
	 */
	public static final String STATE_DISABLE = "20";
	
	/**
	 * 能否替换绑卡（是否存在未完成借款）
	 */
	private String changeAble;

	public String getChangeAble() {
		return changeAble;
	}

	public void setChangeAble(String changeAble) {
		this.changeAble = changeAble;
	}

}
