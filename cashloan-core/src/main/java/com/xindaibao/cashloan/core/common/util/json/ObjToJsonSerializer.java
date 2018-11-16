package com.xindaibao.cashloan.core.common.util.json;

/**
 * JSON序列化处理
 * @author
 *
 */
public interface ObjToJsonSerializer {
	/**
	 * 讲一个object转化成json字符串返回，字段不过滤
	 * @param object 需要转或成json的对象
	 * @param properties 特殊处理的字段 。如果为null默认转化所有字段，如果不为空。根据isFilter属性决定是需要转化的字段还是需要过滤的字段。
	 * @param isFilter 标志特殊字段是显示或者过滤，
	 * @return
	 */
	abstract public String objectToJsonString(Object object, String[] properties, Boolean isFilter);
	
	/**
	 * 讲一个object转化成json字符串返回。可以设定过滤字段或显示字段
	 * @param object 需要转或成json的对象
	 * @param properties 特殊处理的字段 。如果为null默认转化所有字段，如果不为空。根据isFilter属性决定是需要转化的字段还是需要过滤的字段。
	 * @param isFilter 标志特殊字段是显示或者过滤，
	 * @return
	 */
	abstract public String objectToJsonString(Object object);
	
	/**
	 * 讲一个object转化成json字符串返回。可以设定需要显示的字段
	 * @param object 需要转或成json的对象
	 * @param properties 需要显示的字段
	 * @return
	 */
	abstract public String objectToJsonStringWithInclude(Object object, String[] properties);
	
	
	/**
	 * 讲一个object转化成json字符串返回。可以设定需要过滤的字段
	 * @param object 需要转或成json的对象
	 * @param properties 需要显示的字段
	 * @return
	 */
	abstract public String objectToJsonStringWithExclude(Object object, String[] properties);
}
