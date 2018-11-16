package com.xindaibao.cashloan.rc.model;

import com.xindaibao.cashloan.rc.domain.TppBusiness;

/**
 * 第三方征信接口信息Model
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-14 13:41:57




 */
public class TppBusinessModel extends TppBusiness {

	private static final long serialVersionUID = 1L;
	
	public static final String BUS_NID_QCRISK = "QcRisk";
	
	public static final String BUS_NID_TONGDUN = "TongdunApply";

	/**
	 * 第三方Nid
	 */
	private String tppNid;

	/**
	 * 获取第三方Nid
	 * 
	 * @return tppNid
	 */
	public String getTppNid() {
		return tppNid;
	}

	/**
	 * 设置第三方Nid
	 * 
	 * @param tppNid
	 */
	public void setTppNid(String tppNid) {
		this.tppNid = tppNid;
	}

}