package com.xindaibao.cashloan.cl.model;


import com.xindaibao.cashloan.cl.domain.Channel;

/**
 * 渠道信息Model
 * 
 * @author
 * @version 1.0.0
 * @date 2017年3月6日 上午10:09:35


 * 

 */
public class ChannelModel extends Channel{

	private static final long serialVersionUID = 1L;
	
	/*********   状态 - 定义   *********   /
	/** 状态 - 启用 */
	public static final String STATE_ENABLE = "10";

	/** 状态 - 禁用 */
	public static final String STATE_DISABLE = "20";
	
	
	/**
	 * 渠道类型描述
	 */
	private String typeStr;
	
	/**
	 * 渠道状态中文描述
	 */
	private String stateStr;
	
    /**
     * 状态中文转换
     * 
     * @param state
     * @return
     */
    public static String stateConvert(String state) {
        String stateStr;
        if (ChannelModel.STATE_DISABLE.equals(state)) {
            stateStr = "禁用";
        } else {
            stateStr = "启用";
        }
        return stateStr;
    }

	/**
	 * 获取渠道类型描述
	 * @return typeStr
	 */
	public String getTypeStr() {
		return typeStr;
	}

	/**
	 * 设置渠道类型描述
	 * @param typeStr
	 */
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	/**
	 * 获取状态中文描述
	 * @return stateStr
	 */
	public String getStateStr() {
		this.stateStr = stateConvert(this.getState());
		return stateStr;
	}

	/**
	 * 设置状态中文描述
	 * @param stateStr
	 */
	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}
	
	
	
}
