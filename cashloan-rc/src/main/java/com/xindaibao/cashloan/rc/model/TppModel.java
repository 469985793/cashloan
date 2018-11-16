package com.xindaibao.cashloan.rc.model;

import java.util.List;

import com.xindaibao.cashloan.rc.domain.Tpp;
import com.xindaibao.cashloan.rc.domain.TppBusiness;

/**
 * 第三方信息model
 * @author
 * @version 1.0
 * @date 2017年3月14日 下午1:59:17




 */
public class TppModel extends Tpp {

	private static final long serialVersionUID = 1L;

	/**
	 * 接口集合
	 */
	private List<TppBusiness> businessList;
	
	/**
	 * 获取 接口集合
	 * @return 
	 */
	public List<TppBusiness> getBusinessList() {
		return businessList;
	}

	/**
	 * 设置 接口集合
	 * @param 
	 */
	public void setBusinessList(List<TppBusiness> businessList) {
		this.businessList = businessList;
	}
}
