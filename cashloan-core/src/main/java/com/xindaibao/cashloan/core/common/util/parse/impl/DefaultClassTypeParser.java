package com.xindaibao.cashloan.core.common.util.parse.impl;

import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.xindaibao.cashloan.core.common.exception.RDRuntimeException;
import com.xindaibao.cashloan.core.common.util.parse.ClassTypeParser;

public class DefaultClassTypeParser implements ClassTypeParser {
	static ObjectMapper mapper = new ObjectMapper();
	static {
		// 增加mapper的配置
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	}

	@Override
	public <T> T parse(String content, Class<T> valueType) {
		if (StringUtils.isEmpty(content)) {
			return null;
		}
		try {
			return mapper.readValue(content, valueType);
		} catch (Exception e) {
			throw new RDRuntimeException("解析错误", e);
		}
	}
	
	public String parseToJSONString(Object object) {
		// 暂时用Alibaba的fast json，后期可以改成其它的。
		return JSON.toJSONString(object);
	}

}
