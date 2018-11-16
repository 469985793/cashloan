package com.xindaibao.cashloan.core.model;

import com.xindaibao.cashloan.core.domain.UserBaseInfo;

public class UserWorkInfoModel extends UserBaseInfo {

	private static final long serialVersionUID = 1L;

	/**
	 * 已添加工作照片
	 */
	public static final String WORK_IMG_ADDED = "10";
	
	/**
	 * 未添加工作照片
	 */
	public static final String WORK_IMG_NO_ADD = "20";
	
	
	/**
	 * 是否上传工作照
	 */
	private String workImgState;
	/**
	 * 工作年限code标识
	 */
	private String  itemCode;

	/**
	 * 获取是否上传工作照
	 * 
	 * @return workImgState
	 */
	public String getWorkImgState() {
		return workImgState;
	}

	/**
	 * 设置是否上传工作照
	 * 
	 * @param workImgState
	 */
	public void setWorkImgState(String workImgState) {
		this.workImgState = workImgState;
	}

	/**
	 * 获取工作年限code标识
	 * @return itemCode
	 */
	public String getItemCode() {
		return itemCode;
	}

	/**
	 * 设置工作年限code标识
	 * @param itemCode
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
}
