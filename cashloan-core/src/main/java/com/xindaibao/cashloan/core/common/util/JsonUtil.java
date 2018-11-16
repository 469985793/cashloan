package com.xindaibao.cashloan.core.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tool.util.NumberUtil;
import tool.util.StringUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.pagehelper.PageInfo;
import com.xindaibao.cashloan.core.common.context.Constant;

public class JsonUtil extends tool.util.JsonUtil {
	
	public static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	
	private static ObjectMapper mapper = new ObjectMapper();
	private static ObjectMapper mapperWithYMDDate = new ObjectMapper();
	
	static {
	    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); //去掉默认的时间戳格式
	    mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true); //单引号处理
	    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //反序列化时，属性不存在的兼容处理
	    mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false); //设置不写NULLmap值
	    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); //空值不序列化
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")); //序列化时，日期的统一格式
        
        mapperWithYMDDate.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapperWithYMDDate.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapperWithYMDDate.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapperWithYMDDate.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapperWithYMDDate.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	}
	
	public static ObjectMapper getJsonMapper(){
		 return mapper;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T parse(String value,Class<T> clz){
		if (StringUtils.isEmpty(value)) {
			return null;
		}
		try {
			T obj = mapper.readValue(value, clz);
			if(obj instanceof Map){
				return (T) MapUtil.removeEmptyStr((Map<String, Object>) obj);
			}
			return obj;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} 
	}
	
	public static <T> T parseWithOnlyYMDDate(String value,Class<T> clz){
		if (StringUtils.isEmpty(value)) {
			return null;
		}
		try {
			return mapperWithYMDDate.readValue(value, clz);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} 
	}
	
	public static <T> T parse(byte[] bytes,Class<T> clz){
		try {
			return mapper.readValue(bytes, clz);
		} 
		catch (Exception e) {
			throw new IllegalStateException(e);
		} 
	}
	
	public static <T> T parse(InputStream ins,Class<T> clz){
		try {
			return mapper.readValue(ins, clz);
		} 
		catch (Exception e) {
			throw new IllegalStateException(e);
		} 
	}
	
	public static <T> T  parse(Reader reader,Class<T> clz){
		try {
			return mapper.readValue(reader, clz);
		} 
		catch (Exception e) {
			throw new IllegalStateException(e);
		} 
	}
	
	public static JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T parse(String value, JavaType javaType) {
		if (StringUtils.isEmpty(value)) {
			return null;
		}

		try {
			return (T) mapper.readValue(value, javaType);
		}
		catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T update(String value, T object) {
		try {
			return (T) mapper.readerForUpdating(object).readValue(value);
		} 
		catch (Exception e) {
			throw new IllegalStateException(e);
		} 
	}
	
	public static String writeValueAsString(Object o){
		try {
			return mapper.writeValueAsString(o);
		} 
		catch (Exception e) {
			throw new IllegalStateException(e);
		} 
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> domainToMap(Object o){
		try {
			String str = mapper.writeValueAsString(o);
			return parse(str, Map.class) ;
		} 
		catch (Exception e) {
			throw new IllegalStateException(e);
		} 
	}
	

    public static void writeJson(Object obj,HttpServletResponse resp){
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf8");
            PrintWriter pw=resp.getWriter();
			String str;
			if (!(obj instanceof String)) {
				str = toString(obj);
			} else {
				str = obj.toString();
			}
            pw.print(str);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

	public static void write(OutputStream outs,Object o){
		try {
			mapper.writeValue(outs,o);
		} 
		catch (Exception e) {
			throw new IllegalStateException(e);
		} 
	}
	
	public static void write(Writer writer,Object o){
		try {
			mapper.writeValue(writer,o);
		} 
		catch (Exception e) {
			throw new IllegalStateException(e);
		} 
	}
	
	public static void writeWithOnlyYMDDate(Writer writer,Object o){
		try {
			mapperWithYMDDate.writeValue(writer,o);
		} 
		catch (Exception e) {
			throw new IllegalStateException(e);
		} 
	}
	
	public static String toString(Object o){
		try {
			return mapper.writeValueAsString(o);
		} 
		catch (Exception e) {
			throw new IllegalStateException(e);
		} 
	}
	
	public static byte[] toBytes(Object o){
		try {
			return mapper.writeValueAsBytes(o);
		} 
		catch (Exception e) {
			throw new IllegalStateException(e);
		} 
	}
	
	/**
	 * 获取json字符串的值
	 * @param json
	 * @param key
	 * @return
	 */
	public static int getInt(String jsonStr,String key){
		return NumberUtil.getInt(StringUtil.isNull(getValue(jsonStr, key)));
	}
	
	/**
	 * 获取json字符串中的值
	 * @param jsonStr
	 * @param key
	 * @return
	 */
	public static double getDouble(String jsonStr,String key){
		return NumberUtil.getDouble(StringUtil.isNull(getValue(jsonStr, key)));
	}
	
	private static  Object getValue(String jsonStr,String key){
		JSONObject json = null;
		try {
			json = JSON.parseObject(jsonStr);
			return json.get(key);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "";
	}
	
	 private JSONObject jsonObject;
	 
    public static JsonUtil newJson() {
        JsonUtil jsonUtil = new JsonUtil();
        jsonUtil.jsonObject = new JSONObject();
        jsonUtil.jsonObject.put("message", Constant.OPERATION_SUCCESS);
        jsonUtil.jsonObject.put("code", Constant.SUCCEED_CODE_VALUE);
        return jsonUtil;
    }
    
    public static JsonUtil newFailJson() {
        JsonUtil jsonUtil = new JsonUtil();
        jsonUtil.jsonObject = new JSONObject();
        jsonUtil.jsonObject.put("message", Constant.OPERATION_FAIL);
        jsonUtil.jsonObject.put("code", Constant.FAIL_CODE_VALUE);
        return jsonUtil;
    }

    public JsonUtil addData(String key, Object value) {
        this.jsonObject.put(key, value);
        return this;
    }

    public JsonUtil addMessage(Object value) {
        this.jsonObject.put("message", value);
        return this;
    }

    public JsonUtil addCode(Object value) {
        this.jsonObject.put("code", value);
        return this;
    }

    public JsonUtil addPageInfo(PageInfo<?> pageInfo){
        JSONObject page = new JSONObject();
        page.put("currentPage", Integer.valueOf(pageInfo.getPageNum()));
        page.put("pages", Integer.valueOf(pageInfo.getPages()));
        page.put("total", Long.valueOf(pageInfo.getTotal()));
        this.jsonObject.put("page",page);
        return this;
    }

    public String toJsonString() {
        return this.jsonObject.toJSONString();
    }


    public JSON toJson() {
        return this.jsonObject;
    }
}
