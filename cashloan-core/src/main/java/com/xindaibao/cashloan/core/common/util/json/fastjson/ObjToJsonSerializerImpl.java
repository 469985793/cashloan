package com.xindaibao.cashloan.core.common.util.json.fastjson;


import java.util.List;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.xindaibao.cashloan.core.common.util.json.ObjToJsonSerializer;

/**
 * JSON转化处理
 * @author
 */
public class ObjToJsonSerializerImpl implements ObjToJsonSerializer {

	public String objectToJsonString(Object object, String[] properties,
			Boolean isFilter) {
		JSONSerializer json = new JSONSerializer();
		
		if (properties != null && properties.length > 0) {
			this.arrayToFilters(json, properties, isFilter);		
		}
		json.write(object);
		return json.toString();
	}

	public String objectToJsonString(Object object) {
		JSONSerializer json = new JSONSerializer();
		json.write(object);
		return json.toString();
	}

	public String objectToJsonStringWithInclude(Object object,
			String[] properties) {
		return objectToJsonString(object, properties, false);
	}

	public String objectToJsonStringWithExclude(Object object,
			String[] properties) {
		
		return objectToJsonString(object, properties, true);
	}
	
	/**
	 * 
	 * @param jsonSerializer fastjson的jsonSerializer
	 * @param filters 需要过滤或显示的字段
	 * @param isFilter 是过滤还是显示
	 */
	private void arrayToFilters(JSONSerializer jsonSerializer, String[] properties, Boolean isFilter) {
		//当特殊处理的字段是需要转化的字段时
		List<PropertyFilter> filterList = jsonSerializer.getPropertyFilters();
		if (isFilter != null && isFilter == false) {
			//遍历所有的特殊属性
			for (final String property : properties) {
				PropertyFilter filter = new PropertyFilter() {     
					public boolean apply(Object source, String name, Object value) {
						if (property.equals(name)) 
							return true;
						else
							return false;
					} 
				}; 
				filterList.add(filter);
			}
		}
		else {
			//遍历所有的特殊属性
			for (final String property : properties) {
				PropertyFilter filter = new PropertyFilter() {     
					public boolean apply(Object source, String name, Object value) {
						if (property.equals(name)) 
							return false;
						else
							return true;
					} 
				}; 
				filterList.add(filter);
			}			
		}
	}
	
}
