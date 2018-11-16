package com.xindaibao.cashloan.cl.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.xindaibao.cashloan.core.common.context.Global;

 
/**
 * 蚂蚁金服接口基础类
 * @author
 * @version 1.0.0
 * @date 2017-4-1 下午6:07:47



 */
public class BaseAliPayModel {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(BaseAliPayModel.class);
	/**
	 *支付宝分配给开发者的应用ID
	 */
	 private String app_id;	
	/**
	 * 接口服务Service 供请求记录中区分接口使用
	 */
	private String service;
	/**
	 * 公钥
	 */
	private String public_key;
	/**
	 * 私钥
	 */
	private String private_key;
	/**
	 * 提交地址
	 */
	private String subUrl;
	/**
	 * 编码格式
	 */
	private String charset;
	/**
	 * 格式
	 */
	private String format;
	/**
	 * 签名方式
	 */
	private String sign_type;

	/**
	 * 签名
	 */
	private String sign;
	/**
	 * 时间戳
	 */
	private  String  timestamp;
	/**
	 * 接口版本
	 */
	private String version;
	/**
	 * 请求参数的集合
	 */
	private String biz_content;
	
	/**
	 * 服务器异步通知地址
	 */
	private String notify_url;

	/**
	 * 响应结果code
	 */
	public String code;

	/**
	 * 响应描述
	 */
	public String msg;

	 
	public BaseAliPayModel() {
		super();
	 	this.setVersion("1.0");
		this.setFormat("json");
		this.setCharset("GBK");
		this.setSign_type("RSA2");
		this.setApp_id(Global.getValue("alipay_app_id"));
		this.setSubUrl(Global.getValue("alipay_app_apihost"));
		this.setPrivate_key(Global.getValue("alipay_private_key"));
		this.setPublic_key(Global.getValue("alipay_public_key"));
	}
		
 
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getSubUrl() {
		return subUrl;
	}

	public void setSubUrl(String subUrl) {
		this.subUrl = subUrl;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getBiz_content() {
		return biz_content;
	}

	public void setBiz_content(String biz_content) {
		this.biz_content = biz_content;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getPublic_key() {
		return public_key;
	}

	public void setPublic_key(String public_key) {
		this.public_key = public_key;
	}

	public String getPrivate_key() {
		return private_key;
	}

	public void setPrivate_key(String private_key) {
		this.private_key = private_key;
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	public AlipayClient defaultAlipayClient(BaseAliPayModel model) {
		// TODO Auto-generated method stub
		AlipayClient alipayClient = new DefaultAlipayClient(model.getSubUrl(),model.getApp_id(),model.getPrivate_key(),model.getFormat(),model.getCharset(),model.getPublic_key(),model.getSign_type());
		return alipayClient;
	}
 
	
	 
}
