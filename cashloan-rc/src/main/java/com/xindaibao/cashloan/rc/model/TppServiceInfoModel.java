package com.xindaibao.cashloan.rc.model;

import java.io.Serializable;

/**
 * 第三方接口信息
 * @author
 * @version 1.0
 * @date 2017年4月10日下午5:13:18




 */
public class TppServiceInfoModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 第三方数据接口
	 */
	public static final String SERVICE_TYPE_THIRD = "10";
	
	/**
	 * 系统内部统计接口
	 */
	public static final String SERVICE_TYPE_STATISTICS = "20";
	
	/**
	 * 获取一次
	 */
	public static final String GET_WAY_ONCE = "00";
	
	/**
	 * 每次获取
	 */
	public static final String GET_WAY_EVERYTIMES = "10";
	
	/**
	 * 固定周期获取（单位为天）
	 */
	public static final String GET_WAY_CYCLE = "20";

	/**
	 * 场景接口配置关系Id
	 */
	public Long sceneId;
	
	/**
	 * 场景编码，关联字典详情表的code
	 */
	public String sceneCode;
	
	/**
	 * 数据获取方式
	 */
	public String getWay;
	
	/**
	 * 数据获取周期
	 */
	public String period;
	
	/**
	 * 商户号
	 */
	public String merNo;
	
	/**
	 * 签名方式  RSA MD5
	 */
	public String signType;
	
	/**
	 * 第三方Id
	 */
	private Long tppId;
	
	/**
	 * 第三方秘钥
	 */
	public String tppKey;
	
	/**
	 * 第三方参数
	 */
	public String tppParams;
	
	/**
	 * 接口ID
	 */
	public Long busId;
	
	/**
	 * 接口昵称
	 */
	public String busNid;
	
	/**
	 * 接口名称
	 */
	public String busName;
	
	/**
	 * 接口地址
	 */
	public String url;
	
	/**
	 * 接口测试地址
	 */
	public String testUrl;
	
	/**
	 * 接口参数
	 */
	public String serParams;
	
	/**
	 * 接口类型 10 第三方接口 20 数据统计接口
	 */
	private String type;

	/** 
	 * 获取场景接口配置关系Id
	 * @return sceneId
	 */
	public Long getSceneId() {
		return sceneId;
	}

	/** 
	 * 设置场景接口配置关系Id
	 * @param sceneId
	 */
	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	/**
	 * @return the 场景编码，关联字典详情表的code
	 */
	public String getSceneCode() {
		return sceneCode;
	}

	/**
	 * @param 场景编码，关联字典详情表的code the sceneCode to set
	 */
	public void setSceneCode(String sceneCode) {
		this.sceneCode = sceneCode;
	}

	/**
	 * @return the 数据获取方式
	 */
	public String getGetWay() {
		return getWay;
	}

	/**
	 * @param 数据获取方式 the getWay to set
	 */
	public void setGetWay(String getWay) {
		this.getWay = getWay;
	}

	/**
	 * @return the 数据获取周期
	 */
	public String getPeriod() {
		return period;
	}

	/**
	 * @param 数据获取周期 the period to set
	 */
	public void setPeriod(String period) {
		this.period = period;
	}

	/**
	 * @return the 商户号
	 */
	public String getMerNo() {
		return merNo;
	}

	/**
	 * @param 商户号 the merNo to set
	 */
	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}

	/**
	 * @return the 签名方式RSAMD5
	 */
	public String getSignType() {
		return signType;
	}

	/**
	 * @param 签名方式RSAMD5 the signType to set
	 */
	public void setSignType(String signType) {
		this.signType = signType;
	}

	/**
	 * @return the 第三方Id
	 */
	public Long getTppId() {
		return tppId;
	}

	/**
	 * @param 第三方Id the tppId to set
	 */
	public void setTppId(Long tppId) {
		this.tppId = tppId;
	}

	/**
	 * @return the 第三方秘钥
	 */
	public String getTppKey() {
		return tppKey;
	}

	/**
	 * @param 第三方秘钥 the tppKey to set
	 */
	public void setTppKey(String tppKey) {
		this.tppKey = tppKey;
	}

	/**
	 * @return the 第三方参数
	 */
	public String getTppParams() {
		return tppParams;
	}

	/**
	 * @param 第三方参数 the tppParams to set
	 */
	public void setTppParams(String tppParams) {
		this.tppParams = tppParams;
	}

	/**
	 * @return the 接口ID
	 */
	public Long getBusId() {
		return busId;
	}

	/**
	 * @param 接口ID the busId to set
	 */
	public void setBusId(Long busId) {
		this.busId = busId;
	}

	/** 
	 * 获取接口昵称
	 * @return busNid
	 */
	public String getBusNid() {
		return busNid;
	}

	/** 
	 * 设置接口昵称
	 * @param busNid
	 */
	public void setBusNid(String busNid) {
		this.busNid = busNid;
	}

	/** 
	 * 获取接口名称
	 * @return busName
	 */
	public String getBusName() {
		return busName;
	}

	/** 
	 * 设置接口名称
	 * @param busName
	 */
	public void setBusName(String busName) {
		this.busName = busName;
	}

	/**
	 * @return the 接口地址
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param 接口地址 the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the 接口测试地址
	 */
	public String getTestUrl() {
		return testUrl;
	}

	/**
	 * @param 接口测试地址 the testUrl to set
	 */
	public void setTestUrl(String testUrl) {
		this.testUrl = testUrl;
	}

	/**
	 * @return the 接口参数
	 */
	public String getSerParams() {
		return serParams;
	}

	/**
	 * @param 接口参数 the serParams to set
	 */
	public void setSerParams(String serParams) {
		this.serParams = serParams;
	}

	/** 
	 * 获取接口类型10第三方接口20数据统计接口
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/** 
	 * 设置接口类型10第三方接口20数据统计接口
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
}
