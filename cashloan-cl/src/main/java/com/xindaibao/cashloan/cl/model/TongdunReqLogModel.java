package com.xindaibao.cashloan.cl.model;


import com.xindaibao.cashloan.cl.domain.TongdunReqLog;

/**
 * /**
 * Created by dufy on 2017/3/22.
 */
public class TongdunReqLogModel extends TongdunReqLog{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 借款金额
	 */
	private Double amount;
	
	/**
	 * 真实姓名
	 */
	private String realName;
	
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 借款订单号
	 */
	private String borrowNo;
	
	private String queryParams;
	
	/**
	 * 审核状态对应中文名 10 已提交申请   20 查询成功  30 查询失败  
	 */
	private String stateStr;

	/** 已提交审核*/
	public static final String STATE_SUBMIT = "10";
	/** 查询成功*/
	public static final String STATE_PASS = "20";
	/**  查询失败  */
	public static final String STATE_REFUSED = "30";
	
	public String getStateStr() {
		if(STATE_SUBMIT.equals(this.getState())){
			this.setStateStr("已提交审核");
		}else if(STATE_PASS.equals(this.getState())){
			this.setStateStr("查询成功");
		}else if(STATE_REFUSED.equals(this.getState())){
			this.setStateStr("查询失败  ");
		}else{
			this.setStateStr("--");
		}
		return stateStr;
	}

	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}
	
 
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBorrowNo() {
		return borrowNo;
	}

	public void setBorrowNo(String borrowNo) {
		this.borrowNo = borrowNo;
	}

	public String getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(String queryParams) {
		this.queryParams = queryParams;
	}



	
	
	
}
