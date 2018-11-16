package com.xindaibao.cashloan.cl.model;

import com.xindaibao.cashloan.cl.domain.PayRespLog;


public class ManagePayRespLogModel extends PayRespLog {

	private static final long serialVersionUID = 1L;

	public ManagePayRespLogModel(String orderNo, Integer type, String params) {
		super(orderNo, type, params);
	}
	
}
