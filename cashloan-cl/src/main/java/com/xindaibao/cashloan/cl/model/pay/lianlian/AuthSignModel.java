package com.xindaibao.cashloan.cl.model.pay.lianlian;


/**
 * 连连支付 -分期付  SDK签约授权
 * 
 * @author
 * @version 1.0.0
 * @date 2017年3月9日 下午1:35:20


 * 

 */
public class AuthSignModel extends BasePaymentModel {
	
	/**
	 * 商户用户唯一编号
	 */
	private String user_id;

	/**
	 * 证件类型 默认:0 身份证
	 */
	private String id_type;

	/**
	 * 证件号码
	 */
	private String id_no;

	/**
	 * 银行账号姓名
	 */
	private String acct_name;

	/**
	 * 银行卡号
	 */
	private String card_no;

	/**
	 * 风险控制参数
	 */
	private String risk_item;

	/**
	 * 签约结果
	 */
	private String result_sign;

	/**
	 * 签约协议号
	 */
	private String no_agree;
	
	
	public AuthSignModel() {
		super();
	}
	
	public AuthSignModel(String orderNo) {
		super();
		this.setOrderNo(orderNo);
	}
	
	@Override
	public String[] signParamNames() {
		return new String[] { "user_id", "platform", "oid_partner",
				"sign_type", "sign", "id_type", "id_no", "acct_name",
				"card_no", "risk_item" };
	}

	@Override
	public String[] reqParamNames() {
		return new String[] { "user_id", "platform", "oid_partner",
				"sign_type", "sign", "id_type", "id_no", "acct_name",
				"card_no", "risk_item" };
	}
	

	@Override
	public String[] respParamNames() {
		return new String[] { "ret_code", "ret_msg", "sign_type", "sign",
				"result_sign", "oid_partner", "user_id", "no_agree" };
	}

	/**
	 * 获取商户用户唯一编号
	 * @return user_id
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * 设置商户用户唯一编号
	 * @param user_id
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * 获取证件类型默认:0身份证
	 * @return id_type
	 */
	public String getId_type() {
		return id_type;
	}

	/**
	 * 设置证件类型默认:0身份证
	 * @param id_type
	 */
	public void setId_type(String id_type) {
		this.id_type = id_type;
	}

	/**
	 * 获取证件号码
	 * @return id_no
	 */
	public String getId_no() {
		return id_no;
	}

	/**
	 * 设置证件号码
	 * @param id_no
	 */
	public void setId_no(String id_no) {
		this.id_no = id_no;
	}

	/**
	 * 获取银行账号姓名
	 * @return acct_name
	 */
	public String getAcct_name() {
		return acct_name;
	}

	/**
	 * 设置银行账号姓名
	 * @param acct_name
	 */
	public void setAcct_name(String acct_name) {
		this.acct_name = acct_name;
	}

	/**
	 * 获取银行卡号
	 * @return card_no
	 */
	public String getCard_no() {
		return card_no;
	}

	/**
	 * 设置银行卡号
	 * @param card_no
	 */
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	/**
	 * 获取风险控制参数
	 * @return risk_item
	 */
	public String getRisk_item() {
		return risk_item;
	}

	/**
	 * 设置风险控制参数
	 * @param risk_item
	 */
	public void setRisk_item(String risk_item) {
		this.risk_item = risk_item;
	}

	/**
	 * 获取签约结果
	 * @return result_sign
	 */
	public String getResult_sign() {
		return result_sign;
	}

	/**
	 * 设置签约结果
	 * @param result_sign
	 */
	public void setResult_sign(String result_sign) {
		this.result_sign = result_sign;
	}

	/**
	 * 获取签约协议号
	 * @return no_agree
	 */
	public String getNo_agree() {
		return no_agree;
	}

	/**
	 * 设置签约协议号
	 * @param no_agree
	 */
	public void setNo_agree(String no_agree) {
		this.no_agree = no_agree;
	}

}
