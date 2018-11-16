package com.xindaibao.cashloan.core.common.util.json;


/**
 * 统一json处理的基类。统一json格是{errorNo:错误号, errorInfo:错误信息, property:数据, ……}
 * @author
 *
 */
public abstract class AbstractJsonSerializer  {
	
	/**
	 * 错误号，默认0，表示成功
	 */
	private String errorNo = "0";
	
	/**
	 * 错误信息
	 */
	private String errorInfo = "";
	
	
	/**
	 * 是否需要错误信息
	 */
	private boolean needErrorInfo = false;
	
	
	
	/**
	 * 增加一个object转化成json,可以是各种类型。可以设定过滤字段或显示字段
	 * @param object 需要转化成json格式的List;
	 * @param name 转化成Json格式时的参数名;
	 * @param propertys 特殊处理的字段 。如果为null默认转化所有字段，如果不为空。根据isFilter属性决定是需要转化的字段还是需要过滤的字段。
	 * @param isFilter 标志特殊字段是显示或者过滤，
	 */
	abstract public void addObject(Object object, String name, String[] properties, Boolean isFilter);
	
	
	/**
	 * 增加一个object转化成json,可以是各种类型,不对字段进行过滤
	 * @param object 需要转化成json格式的List;
	 * @param name 转化成Json格式时的参数名;
	 * @param propertys 特殊处理的字段 。如果为null默认转化所有字段，如果不为空。根据isFilter属性决定是需要转化的字段还是需要过滤的字段。
	 * @param isFilter 标志特殊字段是显示或者过滤，
	 */
	abstract public void addObject(Object object, String name);
	
	
	/**
	 * 
	 * @param object 增加一个object转化成json,可以是各种类型
	 * @param name 转化成Json格式时的参数名
	 * @param properties 需要过滤的字段
	 */
	abstract public void addObjectWithExclude(Object object, String name, String[] properties);
	
	
	/**
	 * 
	 * @param object 增加一个object转化成json,可以是各种类型
	 * @param name 转化成Json格式时的参数名
	 * @param properties 需要显示的字段
	 */
	abstract public void addObjectWithInclude(Object object, String name, String[] properties);
	
	

	/**
	 *	返回JSON字符串,为了提高轮车，只支持调用一次。
	 */
	abstract public String toString();
	
	
	public String getErrorNo() {
		return errorNo;
	}


	public void setErrorNo(String errorNo) {
		this.errorNo = errorNo;
	}


	public String getErrorInfo() {
		return errorInfo;
	}


	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}


	public boolean isNeedErrorInfo() {
		return needErrorInfo;
	}


	public void setNeedErrorInfo(boolean needErrorInfo) {
		this.needErrorInfo = needErrorInfo;
	}

}
