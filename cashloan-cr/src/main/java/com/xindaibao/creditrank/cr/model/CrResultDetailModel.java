package com.xindaibao.creditrank.cr.model;


import java.util.List;

import com.xindaibao.creditrank.cr.domain.CrResultDetail;

@SuppressWarnings("serial")
public class CrResultDetailModel extends CrResultDetail {
	
	
	public List<CrResultItemDetail> itemList;

	/** 
	 * 获取itemList
	 * @return itemList
	 */
	public List<CrResultItemDetail> getItemList() {
		return itemList;
	}

	/** 
	 * 设置itemList
	 * @param itemList
	 */
	public void setItemList(List<CrResultItemDetail> itemList) {
		this.itemList = itemList;
	}



	
}
