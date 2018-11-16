package com.xindaibao.cashloan.cl.model;

import com.xindaibao.cashloan.cl.domain.OperatorTdSmsInfo;

/**
 * 短信详单实体
 */
 public class OperatorTdSmsInfoModel extends OperatorTdSmsInfo {

    private static final long serialVersionUID = 1L;
 
    /**
    * 短信记录
    */
    private String smsRecord;

	public String getSmsRecord() {
		return smsRecord;
	}

	public void setSmsRecord(String smsRecord) {
		this.smsRecord = smsRecord;
	}

 

}