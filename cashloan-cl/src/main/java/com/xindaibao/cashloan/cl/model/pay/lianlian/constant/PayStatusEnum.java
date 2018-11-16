package com.xindaibao.cashloan.cl.model.pay.lianlian.constant;

/**
 * 支付状态枚举
 * 
 * @author
 * @version 1.0.0
 * @date 2017年3月6日 上午11:03:11


 * 

 */
public enum PayStatusEnum {

	PAYMENT_APPLY("APPLY", "付款申请"),
	PAYMENT_CHECK("CHECK", "复核申请"), 
	PAYMENT_DEALING("PROCESSING", "付款处理中"), 
	PAYMENT_SUCCESS("SUCCESS", "付款成功"), 
	PAYMENT_FAILURE("FAILURE", "付款失败"), 
	PAYMENT_RETURN("CANCEL", "退款"), 
	PAYMENT_CLOSED("CLOSED", "订单关闭");

	private String value;

	private String desc;


	private PayStatusEnum(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}


	public static PayStatusEnum getPayStatusEnumByValue(String value) {
		for (PayStatusEnum statusEnum : PayStatusEnum.values()) {
			if (statusEnum.getValue().equals(value)) {
				return statusEnum;
			}
		}
		return null;
	}

}
