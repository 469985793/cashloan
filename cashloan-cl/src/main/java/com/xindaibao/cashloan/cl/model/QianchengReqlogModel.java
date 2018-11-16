package com.xindaibao.cashloan.cl.model;


import com.xindaibao.cashloan.cl.domain.QianchengReqlog;

/**
 * /**
 * Created by dufy on 2017/3/22.
 */
public class QianchengReqlogModel extends QianchengReqlog{
	
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
	 * 审核状态对应中文名 10 已提交申请 20 审核通过 30 审核不通过
	 */
	private String stateStr;

	/** 已提交申请*/
	public static final String STATE_SUBMIT = "10";
	/** 审核通过 */
	public static final String STATE_PASS = "20";
	/** 审核不通过*/
	public static final String STATE_REFUSED = "30";
	
	public String getStateStr() {
		if(STATE_SUBMIT.equals(this.getState())){
			this.setStateStr("提交申请");
		}else if(STATE_PASS.equals(this.getState())){
			this.setStateStr("审核通过");
		}else if(STATE_REFUSED.equals(this.getState())){
			this.setStateStr("审核不通过");
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



	
	
	
}
