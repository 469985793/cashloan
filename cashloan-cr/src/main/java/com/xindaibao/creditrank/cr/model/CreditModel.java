package com.xindaibao.creditrank.cr.model;

import com.xindaibao.cashloan.core.domain.UserBaseInfo;


/** 
 * @author
 * @version 1.0
 * @date 2016-12-13 上午11:02:06


 * 

 */
public class CreditModel extends UserBaseInfo{

	private static final long serialVersionUID = 1L;
	// 授信额度
	private Double total;
	// 未使用额度
	private Double unuse;
	// 已使用额度
	private Double used;
	private String consumerNo;
	private String creditName;
	// 总评分
	private String grade;//评分功能未完善,暂时做0处理

	private String stateStr; 
	
	private String state;
	
	public String getStateStr() {
		return stateConvert(this.state);
	}

	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String stateConvert(String state){
		String stateStr = null;
		if ("10".equals(state)) {
			stateStr=  "正常";
		}else if("20".equals(state)){
			stateStr=  "冻结";
		}else if("30".equals(state)){
			stateStr=  "待审批";
		}else if("40".equals(state)){
			stateStr=  "审批不通过";
		}
		return stateStr;
	}
	
	/**
	 * 获取unuse
	 * @return unuse
	 */
	public Double getUnuse() {
		return unuse;
	}

	/**
	 * 设置unuse
	 * @param unuse
	 */
	public void setUnuse(Double unuse) {
		this.unuse = unuse;
	}

	

	/**
	 * 获取serialversionuid
	 * @return serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 获取total
	 * @return total
	 */
	public Double getTotal() {
		return total;
	}

	/**
	 * 设置total
	 * @param total
	 */
	public void setTotal(Double total) {
		this.total = total;
	}

	/**
	 * 获取used
	 * @return used
	 */
	public Double getUsed() {
		return used;
	}

	/**
	 * 设置used
	 * @param used
	 */
	public void setUsed(Double used) {
		this.used = used;
	}

	/**
	 * 获取grade
	 * @return grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * 设置grade
	 * @param grade
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
 

	/**
	 * 获取creditName
	 * @return creditName
	 */
	public String getCreditName() {
		return creditName;
	}

	/**
	 * 设置creditName
	 * @param creditName
	 */
	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	public String getConsumerNo() {
		return consumerNo;
	}

	public void setConsumerNo(String consumerNo) {
		this.consumerNo = consumerNo;
	}

}
