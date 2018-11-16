package com.xindaibao.cashloan.cl.model.pay.lianlian;

/**
 * 连连支付 - 认证支付
 * 
 * @author
 * @version 1.0.0
 * @date 2017年6月29日 下午7:06:51



 */
public class CertifiedPayModel extends BasePaymentModel {

	/**
	 * 商户业务类型
	 */
	private String busi_partner;
	/**
	 * 商户订单时间
	 */
	private String dt_order;
	/**
	 * 商品名称
	 */
	private String name_goods;
	/**
	 * 订单描述
	 */
	private String info_order;
	/**
	 * 交易金额
	 */
	private String money_order;

	/**
	 * 订单有效时间
	 */
	private String valid_order;
	/**
	 * 风险控制参数
	 */
	private String risk_item;

	/**
	 * 商户用户唯一编号
	 */
	private String user_id;
	/**
	 * 是否强制使用该银行的银行卡标志
	 */
	private String force_bank;
	/**
	 * 证件类型
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
	 * 签约协议号
	 */
	private String no_agree;
	/**
	 * 分帐信息数据
	 */
	private String shareing_data;
	/**
	 * 连连支付支付单号
	 */
	private String oid_paybill;
	/**
	 * 支付结果
	 */
	private String result_pay;
	/**
	 * 清算日期
	 */
	private String settle_date;
	/**
	 * 内部协议号
	 */
	private String agreementno;
	/**
	 * 支付方式
	 */
	private String pay_type;
	/**
	 * 银行编号
	 */
	private String bank_code;

	public CertifiedPayModel() {
		super();
	}

	public CertifiedPayModel(String orderNo) {
		super();
		this.setOrderNo(orderNo);
		this.setNo_order(orderNo);
		this.setService("authPay");
	}

	@Override
	public String[] signParamNames() {
		return new String[] { "oid_partner", "sign_type", "sign", "busi_partner", "no_order", "dt_order", "name_goods",
				"info_order", "money_order", "notify_url", "risk_item", };
	}

	@Override
	public String[] reqParamNames() {
		return new String[] { "oid_partner", "sign_type", "sign", "busi_partner", "no_order", "dt_order", "name_goods",
				"info_order", "money_order", "notify_url", "risk_item", "user_id", "id_type", "id_no", "acct_name" };
	}

	@Override
	public String[] respParamNames() {
		return new String[] { "ret_code", "ret_msg", "sign_type", "sign", "oid_partner", "dt_order", "no_order",
				"oid_paybill", "result_pay", "money_order", "settle_date", "info_order" };
	}

	@Override
	public String[] respVerifyParamNames() {
		return new String[] { "oid_partner", "sign_type", "sign", "dt_order", "no_order", "oid_paybill", "money_order",
				"result_pay", "settle_date", "info_order", "pay_type", "bank_code", "no_agree", "id_type", "id_no",
				"acct_name" };
	}
	
	/**
	 * 获取busi_partner
	 * 
	 * @return busi_partner
	 */
	public String getBusi_partner() {
		return busi_partner;
	}

	/**
	 * 设置busi_partner
	 * 
	 * @param busi_partner
	 */
	public void setBusi_partner(String busi_partner) {
		this.busi_partner = busi_partner;
	}

	/**
	 * 获取dt_order
	 * 
	 * @return dt_order
	 */
	public String getDt_order() {
		return dt_order;
	}

	/**
	 * 设置dt_order
	 * 
	 * @param dt_order
	 */
	public void setDt_order(String dt_order) {
		this.dt_order = dt_order;
	}

	/**
	 * 获取name_goods
	 * 
	 * @return name_goods
	 */
	public String getName_goods() {
		return name_goods;
	}

	/**
	 * 设置name_goods
	 * 
	 * @param name_goods
	 */
	public void setName_goods(String name_goods) {
		this.name_goods = name_goods;
	}

	/**
	 * 获取info_order
	 * 
	 * @return info_order
	 */
	public String getInfo_order() {
		return info_order;
	}

	/**
	 * 设置info_order
	 * 
	 * @param info_order
	 */
	public void setInfo_order(String info_order) {
		this.info_order = info_order;
	}

	/**
	 * 获取money_order
	 * 
	 * @return money_order
	 */
	public String getMoney_order() {
		return money_order;
	}

	/**
	 * 设置money_order
	 * 
	 * @param money_order
	 */
	public void setMoney_order(String money_order) {
		this.money_order = money_order;
	}

	/**
	 * 获取valid_order
	 * 
	 * @return valid_order
	 */
	public String getValid_order() {
		return valid_order;
	}

	/**
	 * 设置valid_order
	 * 
	 * @param valid_order
	 */
	public void setValid_order(String valid_order) {
		this.valid_order = valid_order;
	}

	/**
	 * 获取risk_item
	 * 
	 * @return risk_item
	 */
	public String getRisk_item() {
		return risk_item;
	}

	/**
	 * 设置risk_item
	 * 
	 * @param risk_item
	 */
	public void setRisk_item(String risk_item) {
		this.risk_item = risk_item;
	}

	/**
	 * 获取user_id
	 * 
	 * @return user_id
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * 设置user_id
	 * 
	 * @param user_id
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * 获取force_bank
	 * 
	 * @return force_bank
	 */
	public String getForce_bank() {
		return force_bank;
	}

	/**
	 * 设置force_bank
	 * 
	 * @param force_bank
	 */
	public void setForce_bank(String force_bank) {
		this.force_bank = force_bank;
	}

	/**
	 * 获取id_type
	 * 
	 * @return id_type
	 */
	public String getId_type() {
		return id_type;
	}

	/**
	 * 设置id_type
	 * 
	 * @param id_type
	 */
	public void setId_type(String id_type) {
		this.id_type = id_type;
	}

	/**
	 * 获取id_no
	 * 
	 * @return id_no
	 */
	public String getId_no() {
		return id_no;
	}

	/**
	 * 设置id_no
	 * 
	 * @param id_no
	 */
	public void setId_no(String id_no) {
		this.id_no = id_no;
	}

	/**
	 * 获取acct_name
	 * 
	 * @return acct_name
	 */
	public String getAcct_name() {
		return acct_name;
	}

	/**
	 * 设置acct_name
	 * 
	 * @param acct_name
	 */
	public void setAcct_name(String acct_name) {
		this.acct_name = acct_name;
	}

	/**
	 * 获取card_no
	 * 
	 * @return card_no
	 */
	public String getCard_no() {
		return card_no;
	}

	/**
	 * 设置card_no
	 * 
	 * @param card_no
	 */
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	/**
	 * 获取no_agree
	 * 
	 * @return no_agree
	 */
	public String getNo_agree() {
		return no_agree;
	}

	/**
	 * 设置no_agree
	 * 
	 * @param no_agree
	 */
	public void setNo_agree(String no_agree) {
		this.no_agree = no_agree;
	}

	/**
	 * 获取shareing_data
	 * 
	 * @return shareing_data
	 */
	public String getShareing_data() {
		return shareing_data;
	}

	/**
	 * 设置shareing_data
	 * 
	 * @param shareing_data
	 */
	public void setShareing_data(String shareing_data) {
		this.shareing_data = shareing_data;
	}

	/**
	 * 获取oid_paybill
	 * 
	 * @return oid_paybill
	 */
	public String getOid_paybill() {
		return oid_paybill;
	}

	/**
	 * 设置oid_paybill
	 * 
	 * @param oid_paybill
	 */
	public void setOid_paybill(String oid_paybill) {
		this.oid_paybill = oid_paybill;
	}

	/**
	 * 获取result_pay
	 * 
	 * @return result_pay
	 */
	public String getResult_pay() {
		return result_pay;
	}

	/**
	 * 设置result_pay
	 * 
	 * @param result_pay
	 */
	public void setResult_pay(String result_pay) {
		this.result_pay = result_pay;
	}

	/**
	 * 获取settle_date
	 * 
	 * @return settle_date
	 */
	public String getSettle_date() {
		return settle_date;
	}

	/**
	 * 设置settle_date
	 * 
	 * @param settle_date
	 */
	public void setSettle_date(String settle_date) {
		this.settle_date = settle_date;
	}

	/**
	 * 获取agreementno
	 * 
	 * @return agreementno
	 */
	public String getAgreementno() {
		return agreementno;
	}

	/**
	 * 设置agreementno
	 * 
	 * @param agreementno
	 */
	public void setAgreementno(String agreementno) {
		this.agreementno = agreementno;
	}

	/**
	 * 获取pay_type
	 * 
	 * @return pay_type
	 */
	public String getPay_type() {
		return pay_type;
	}

	/**
	 * 设置pay_type
	 * 
	 * @param pay_type
	 */
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	/**
	 * 获取bank_code
	 * 
	 * @return bank_code
	 */
	public String getBank_code() {
		return bank_code;
	}

	/**
	 * 设置bank_code
	 * 
	 * @param bank_code
	 */
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
}
