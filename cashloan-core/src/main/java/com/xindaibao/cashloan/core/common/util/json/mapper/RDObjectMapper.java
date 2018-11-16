package com.xindaibao.cashloan.core.common.util.json.mapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SuppressWarnings("serial")
public class RDObjectMapper extends ObjectMapper {

	private String dateFormatPattern;

	public void setDateFormatPattern(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
	}

	public void init() {
		// 排除值为空属性
		setSerializationInclusion(JsonInclude.Include.NON_NULL);
		// 进行缩进输出
		configure(SerializationFeature.INDENT_OUTPUT, true);
		configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

		configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// 进行日期格式化
		if (StringUtils.isNotEmpty(dateFormatPattern)) {
			DateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
			setDateFormat(dateFormat);
		}
	}

}
