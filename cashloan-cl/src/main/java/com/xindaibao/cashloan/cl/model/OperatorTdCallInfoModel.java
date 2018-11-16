package com.xindaibao.cashloan.cl.model;

import com.xindaibao.cashloan.cl.domain.OperatorTdCallInfo;

/**
 * 通话记录详单实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-05-24 09:32:41



 */
 public class OperatorTdCallInfoModel extends OperatorTdCallInfo {

    private static final long serialVersionUID = 1L;
    /**
    * 通话记录
    */
    private String callRecord;
    
	public String getCallRecord() {
		return callRecord;
	}
	public void setCallRecord(String callRecord) {
		this.callRecord = callRecord;
	}
 

}