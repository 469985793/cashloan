package com.xindaibao.cashloan.cl.model;

import com.xindaibao.cashloan.cl.domain.PayCheck;

/**
 * 资金对账记录Model
 * 
 * @author
 * @version 1.0.0
 * @date 2017年4月13日 下午5:38:02



 */
public class ManagePayCheckModel extends PayCheck {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 错误类型中文说明
	 */
	private String typeStr;

	/**
	 * 状态中文说明
	 */
	private String stateStr;
	
	/**
	 * 处理方式中文说明
	 */
	private String processWayStr;
	
	/**
	 * 处理结果中文说明
	 */
	private String processResultStr;
	
	/**
	 * 支付业务场景 
	 */
	private String scenes;
	
	/**
	 * 支付业务场景中文描述 
	 */
	private String scenesStr;
	
	/**
	 * 支付类型
	 */
	private String payType;

	/**
	 * 支付类型中文描述
	 */
	private String payTypeStr;
	
	
	public ManagePayCheckModel() {
		super();
	}


	public ManagePayCheckModel(String orderNo, double orderAmount,
			double realPayAmount, String type, String processResult) {
		super(orderNo, orderAmount, realPayAmount, type, processResult);
	}
	

	/**
	 * 获取错误类型中文说明
	 * 
	 * @return typeStr
	 */
	public String getTypeStr() {
		this.typeStr = this.typeConvert(this.getType());
		return typeStr;
	}

	/**
	 * 设置错误类型中文说明
	 * 
	 * @param typeStr
	 */
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	/**
	 * 获取状态中文说明
	 * 
	 * @return stateStr
	 */
	public String getStateStr() {
		this.stateStr = this.stateConvert(this.getState());
		return stateStr;
	}

	/**
	 * 设置状态中文说明
	 * 
	 * @param stateStr
	 */
	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}

	/**
	 * 获取处理方式中文说明
	 * 
	 * @return processWayStr
	 */
	public String getProcessWayStr() {
		this.processWayStr = processWayConvert(this.getProcessWay());
		return processWayStr;
	}

	/**
	 * 设置处理方式中文说明
	 * 
	 * @param processWayStr
	 */
	public void setProcessWayStr(String processWayStr) {
		this.processWayStr = processWayStr;
	}

	/**
	 * 获取处理结果中文说明
	 * 
	 * @return processResultStr
	 */
	public String getProcessResultStr() {
		this.processResultStr = processResultConvert(this.getProcessResult());
		return processResultStr;
	}

	/**
	 * 设置处理结果中文说明
	 * 
	 * @param processResultStr
	 */
	public void setProcessResultStr(String processResultStr) {
		this.processResultStr = processResultStr;
	}

	/**
	 * 获取支付业务场景
	 * 
	 * @return scenes
	 */
	public String getScenes() {
		return scenes;
	}

	/**
	 * 设置支付业务场景
	 * 
	 * @param scenes
	 */
	public void setScenes(String scenes) {
		this.scenes = scenes;
	}

	/**
	 * 获取支付业务场景中文描述
	 * 
	 * @return scenesStr
	 */
	public String getScenesStr() {
		this.scenesStr = ManagePayLogModel.scenesConvert(this.getScenes());
		return scenesStr;
	}

	/**
	 * 设置支付业务场景中文描述
	 * 
	 * @param scenesStr
	 */
	public void setScenesStr(String scenesStr) {
		this.scenesStr = scenesStr;
	}

	/**
	 * 获取支付类型
	 * 
	 * @return payType
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * 设置支付类型
	 * 
	 * @param payType
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}

	/**
	 * 获取支付类型中文描述
	 * 
	 * @return payTypeStr
	 */
	public String getPayTypeStr() {
		this.payTypeStr = ManagePayLogModel.typeConvert(this.getPayType());
		return payTypeStr;
	}

	/**
	 * 设置支付类型中文描述
	 * 
	 * @param payTypeStr
	 */
	public void setPayTypeStr(String payTypeStr) {
		this.payTypeStr = payTypeStr;
	}

	/**
	 * 错误类型中文描述转换
	 * 
	 * @param type
	 * @return
	 */
	public String typeConvert(String type) {
		String typeStr = null;
		if (PayCheckModel.TYPE_AMOUNT_MISMATCH.equals(type)) {
			typeStr = "金额不匹配";
		} else if (PayCheckModel.TYPE_UNILATERAL_OUR.equals(type)) {
			typeStr = "我方单边账";
		} else if (PayCheckModel.TYPE_UNILATERAL_PAYMENT.equals(type)) {
			typeStr = "支付方单边账";
		} else {
			typeStr = " - ";
		}
		return typeStr;
	}

	/**
	 * 状态中文描述转换
	 */
	public String stateConvert(String state) {
		String stateStr = null;
		if (PayCheckModel.STATE_PAY_SUCCESS.equals(state)) {
			stateStr = "交易成功";
		} else if (PayCheckModel.STATE_REFUND.equals(state)) {
			stateStr = "退款";
		} else {
			stateStr = " - ";
		}
		return stateStr;
	}

	/**
	 * 处理方式中文描述转换
	 */
	public String processWayConvert(String processWay) {
		String processWayStr = null;
		if (PayCheckModel.PROCESS_WAY_NOT_DEAL.equals(processWay)) {
			processWayStr = "不处理 ";
		} else if (PayCheckModel.PROCESS_WAY_MAKEUP.equals(processWay)) {
			processWayStr = "补录 ";
		} else if (PayCheckModel.PROCESS_WAY_REFUND.equals(processWay)) {
			processWayStr = "退还 ";
		} else if (PayCheckModel.PROCESS_WAY_DEDUCTION.equals(processWay)) {
			processWayStr = "补扣 ";
		} else {
			processWayStr = " - ";
		}
		return processWayStr;

	}

	/**
	 * 处理结果中文描述转换
	 */
	public String processResultConvert(String processResult) {
		String processResultStr = null;
		if (PayCheckModel.PROCESS_RESULT_PENDING_TREATMENT.equals(processResult)) {
			processResultStr ="未处理";
		} else if (PayCheckModel.PROCESS_RESULT_ALREADY_DEAL.equals(processResult)) {
			processResultStr ="已处理";
		} else {
			processResultStr = " - ";
		}
		return processResultStr;
	}

}
