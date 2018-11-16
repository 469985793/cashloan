package com.xindaibao.cashloan.cl.model;

import com.xindaibao.cashloan.cl.domain.OperatorBills;

/**
 * 运营商信息-账单信息
 * @author
 * @version 1.0
 * @date 2017年3月13日下午7:26:31


 * 

 */
public class OperatorBillsModel extends OperatorBills {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 账单起始日期2016-11-01-2016-11-30
	 */
	private String billMonthDate;

	/** 
	 * 获取账单起始日期2016-11-01-2016-11-30
	 * @return billMonthDate
	 */
	public String getBillMonthDate() {
		return billMonthDate;
	}

	/** 
	 * 设置账单起始日期2016-11-01-2016-11-30
	 * @param billMonthDate
	 */
	public void setBillMonthDate(String billMonthDate) {
		this.billMonthDate = billMonthDate;
	}
	
	
}
