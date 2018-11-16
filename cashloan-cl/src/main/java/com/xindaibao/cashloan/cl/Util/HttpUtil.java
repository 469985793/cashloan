package com.xindaibao.cashloan.cl.Util;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class HttpUtil extends HttpClientUtil {
	
//	public static void main(String[] args) {
//		String reqUrl = "https://172.16.40.3:18071/payBopInst/bop/pay/payFirst";
////		String param = "{}";
//		String param = "{\"timestamp\":1517811612330,\"recordId\":\"465\",\"times\":0,\"extData\":{\"bopList\":[{\"accountNo\":\"6217710200443166\",\"inChannel\":\"12000000\",\"mac\":\"CEBB2CD0AC24B28BEA3D2E2851A3CA55\",\"origdate\":\"20180201\",\"origlogno\":\"1517557393243\",\"outAbbr\":\"CUPZJ\",\"recvId\":465,\"srcSys\":\"03\",\"totalAmount\":8}]}}";
//		
//		String res = HttpUtil.postPlain(reqUrl, param);
//		System.out.println(res);
//	}
	
	/**
	 * HTTP POST请求
	 * @param httpUrl 接口地址，可以是服务器相对地址
	 * @return
	 */
	public static String post(String httpUrl) {
		return sendPost(httpUrl, null, null, null);
	}
	
	/**
	 * HTTP POST请求
	 * @param httpUrl 接口地址，可以是服务器相对地址
	 * @param paramStr 请求参数
	 * @return
	 */
	public static String post(String httpUrl, String paramStr) {
		return sendPost(httpUrl, paramStr, null, null);
	}

	/**
	 * HTTP POST请求
	 * @param httpUrl 接口地址，可以是服务器相对地址
	 * @param paraMap 请求参数
	 * @return
	 */
	public static String post(String httpUrl, Map<String, Object> paraMap) {
		String paramStr = convertParamter(paraMap);
		return sendPost(httpUrl, paramStr, null, null);
	}
	
	/**
	 * HTTP POST请求
	 * @param httpUrl 接口地址，可以是服务器相对地址
	 * @param paramStr 请求参数
	 * @return
	 */
	public static String postXml(String httpUrl, String paramJsonStr) {
		return sendPost(httpUrl, paramJsonStr, CONTENT_TYPE_TEXT_HTML, null);
	}

	/**
	 * HTTP POST请求
	 * @param httpUrl 接口地址，可以是服务器相对地址
	 * @param paramStr 请求参数
	 * @return
	 */
	public static String postXml(String httpUrl, JSONObject paramJson) {
		return sendPost(httpUrl, paramJson.toJSONString(), CONTENT_TYPE_TEXT_HTML, null);
	}

	/**
	 * HTTP POST请求
	 * @param httpUrl 接口地址，可以是服务器相对地址
	 * @param paraMap 请求参数
	 * @return
	 */
	public static String postXml(String httpUrl, Map<String, Object> paraMap) {
		return sendPost(httpUrl, JSONObject.toJSONString(paraMap), CONTENT_TYPE_TEXT_HTML, null);
	}

	/**
	 * HTTP POST请求
	 * @param httpUrl 接口地址，可以是服务器相对地址
	 * @param paramStr 请求参数
	 * @return
	 */
	public static String postPlain(String httpUrl, String paramJsonStr) {
		return sendPost(httpUrl, paramJsonStr, CONTENT_TYPE_TEXT_PLAIN, null);
	}

	/**
	 * HTTP POST请求
	 * @param httpUrl 接口地址，可以是服务器相对地址
	 * @param paramStr 请求参数
	 * @return
	 */
	public static String postPlain(String httpUrl, JSONObject paramJson) {
		return sendPost(httpUrl, paramJson.toJSONString(), CONTENT_TYPE_TEXT_PLAIN, null);
	}

	/**
	 * HTTP POST请求
	 * @param httpUrl 接口地址，可以是服务器相对地址
	 * @param paraMap 请求参数
	 * @return
	 */
	public static String postPlain(String httpUrl, Map<String, Object> paraMap) {
		return sendPost(httpUrl, JSONObject.toJSONString(paraMap), CONTENT_TYPE_TEXT_PLAIN, null);
	}
	
	/**
	 * HTTP POST请求
	 * @param httpUrl 接口地址，可以是服务器相对地址
	 * @param paramStr 请求参数
	 * @return
	 */
	public static String postJson(String httpUrl, String paramJsonStr) {
		return sendPost(httpUrl, paramJsonStr, CONTENT_TYPE_JSON_URL, null);
	}

	/**
	 * HTTP POST请求
	 * @param httpUrl 接口地址，可以是服务器相对地址
	 * @param paramStr 请求参数
	 * @return
	 */
	public static String postJson(String httpUrl, JSONObject paramJson) {
		return sendPost(httpUrl, paramJson.toJSONString(), CONTENT_TYPE_JSON_URL, null);
	}

	/**
	 * HTTP POST请求
	 * @param httpUrl 接口地址，可以是服务器相对地址
	 * @param paraMap 请求参数
	 * @return
	 */
	public static String postJson(String httpUrl, Map<String, Object> paraMap) {
		return sendPost(httpUrl, JSONObject.toJSONString(paraMap), CONTENT_TYPE_JSON_URL, null);
	}
	
	
	/**
	 * HTTP GET请求
	 * @param httpUrl 接口地址，可以是服务器相对地址
	 * @return
	 */
	public static String get(String httpUrl) {
		return sendGet(httpUrl, null, null);
	}
	
	/**
	 * HTTP GET请求
	 * @param httpUrl 接口地址，可以是服务器相对地址
	 * @param paramStr 请求参数
	 * @return
	 */
	public static String get(String httpUrl, String paramStr) {
		return sendGet(httpUrl, paramStr, null);
	}

	/**
	 * HTTP GET请求
	 * @param httpUrl 接口地址，可以是服务器相对地址
	 * @param paraMap 请求参数
	 * @return
	 */
	public static String get(String httpUrl, Map<String, Object> paraMap) {
		String paramStr = convertParamter(paraMap);
		return sendGet(httpUrl, paramStr, null);
	}
}
