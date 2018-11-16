package com.xindaibao.cashloan.cl.model;

import com.xindaibao.cashloan.cl.domain.OperatorTdCallRecord;

/**
 * 通话记录查询实体
 */
 public class OperatorTdCallModel extends OperatorTdCallRecord {

    private static final long serialVersionUID = 1L;

    /**
     * 授权手机号码
     */
    private String userMobile;

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
 

}