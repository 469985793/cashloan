package com.xindaibao.cashloan.cl.model.tongdun;


import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.model.tongdun.http.HttpRestClient;
import com.xindaibao.cashloan.cl.model.tongdun.sdk.PreloanReportRequest;
import com.xindaibao.cashloan.cl.model.tongdun.sdk.PreloanReportResponse;
import com.xindaibao.cashloan.rc.domain.TppBusiness;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xindaibao.cashloan.cl.model.tongdun.sdk.PreloanRequest;
import com.xindaibao.cashloan.cl.model.tongdun.sdk.PreloanResponse;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.base64.Base64Encoder;

/**
 * Created by syq on 2016/8/5.
 */
public class PreloanApi {

	private static final Logger logger = LoggerFactory.getLogger(PreloanApi.class);
	  /**
	   * 同盾贷前审核接口
	   * @param map  请求参数
	   * @param business  第三方参数信息
	   * @param testState  是否使用测试环境  true 测试环境  false 正式环境
	   * @return
	   */
    public synchronized String preloan(Map<String, Object> map, TppBusiness business, boolean testState, String mobileType){
        logger.info("DEBUG -- business: " + business + ";\n testState: " + testState
                + ";\n mobileType: " + mobileType + ";\n map: " + map);
        try {
			PreloanRequest tongdunRequest = new PreloanRequest();
			logger.info(business.getExtend());
			JSONObject extend = JSONObject.parseObject(business.getExtend());
			tongdunRequest.setHttpMethod("POST");

			String partnerKey = extend.getString("partner_key");
			String partnerCode = extend.getString("partner_code");
			String appName = extend.getString("app_name");
			String event_type =extend.getString("event_type");
			String version = extend.getString("version");

			JSONObject names = JSONObject.parseObject(appName);
			if(mobileType.equals("1")){
				appName=names.getString("ios");
			}else if(mobileType.equals("2")){
				appName=names.getString("and");
			}else{
				appName=names.getString("web");
			}
            String urlParam = "partner_key=" + partnerKey + "&partner_code=" + partnerCode +
                    "&app_name=" + appName + "&event_type=" + event_type + "&version=" + version;

            logger.info("==请求参数=" + urlParam);

            tongdunRequest.setUrlParam(urlParam);
			tongdunRequest.setServerHost(testState?business.getTestUrl():business.getUrl());
			tongdunRequest.setParamMap(map);

			//logger.info("同盾贷前审核request："+JSONObject.toJSONString(tongdunRequest));
			PreloanResponse tongdunResponse = HttpRestClient.create().executeThenGetJsonResponse2(tongdunRequest);
//			logger.info("同盾贷前审核："+JSON.toJSONString(tongdunResponse));
			logger.info("同盾贷前审核：成功放回.");
			return tongdunResponse.postResponseToJsonStr();
		} catch (Exception e) {
			logger.error("同盾贷前审核", e);
		}
        return "";
    }


    /**
     * 查询贷前审核的结果报告
     * @param map  请求参数
	 * @param business  第三方参数信息
	 * @param testState  是否使用测试环境  true 测试环境  false 正式环境
	 * @return
     */
    @SuppressWarnings("rawtypes")
	public PreloanReportResponse preloanReport(Map<String, Object> map, TppBusiness business, boolean testState, String mobileType){
	        PreloanReportRequest request = new PreloanReportRequest();
	        
	        JSONObject extend = JSONObject.parseObject(business.getExtend());
	        request.setHttpMethod("GET");
	        request.setServerHost(testState?business.getTestUrl():business.getUrl());
	        String partnerKey = extend.getString("partner_key"); 
	        String partnerCode = extend.getString("partner_code");
	        String appName =  extend.getString("app_name");
	        JSONObject names = JSONObject.parseObject(appName);
			if(mobileType.equals("1")){
				appName=names.getString("ios");
			}else if(mobileType.equals("2")){
				appName=names.getString("and");
			}else{
				appName=names.getString("web");
			}
	        String urlParam = "partner_key=" + partnerKey + "&partner_code=" + partnerCode + "&app_name=" + appName;
	        //logger.info("==请求参数2="+urlParam);
	        Map bodyMap = JSON.parseObject((String)map.get("body"), Map.class);
	        String reportId = (String) bodyMap.get("report_id");
	        urlParam = urlParam + "&report_id=" + reportId;
	        request.setUrlParam(urlParam);
	        
	        request.setParamMap(map);
	        PreloanReportResponse response = HttpRestClient.create().executeThenGetJsonResponse(request);
	        return response;
    }

    /**
     * 查询同盾运营商数据
	 * @return
     */
    public  String operatorQuery(Map<String, Object> map){
    	    PreloanRequest request = new PreloanRequest();
	        String partnerKey = Global.getValue("tongdun_operator_partner_key"); 
	        String partnerCode = Global.getValue("tongdun_operator_partner_code");
	        String queryUrl =  Global.getValue("tongdun_operator_query_url");
	        String urlParam = "partner_key=" + partnerKey + "&partner_code=" + partnerCode;
	        logger.info("==请求参数="+queryUrl+"?"+urlParam);
	        request.setHttpMethod("POST");
	        request.setUrlParam(urlParam);
	        request.setServerHost(queryUrl);
	        Map<String, Object> body = new HashMap<String, Object>();
	        body.put("body",JsonUtil.toString(map));
	        request.setParamMap(body);
	        PreloanResponse response = HttpRestClient.create().executeThenGetJsonResponse2(request);
	        return response.postResponseToStr();
    }
    
    
	public static String sign(Map<String, Object> signMap) {
		// TODO Auto-generated method stub
	  String original = genSignData(JSONObject.toJSONString(signMap));
	 
		try {
		  MessageDigest md5 = MessageDigest.getInstance("MD5");
		  Base64Encoder base64 = new Base64Encoder();
	     String md5Str = base64.encode(md5.digest(original.toString().getBytes("utf-8")));
		 return md5Str;
	    } catch (Exception e) {
		  e.printStackTrace();
	    }
		  return "";
	}

	/**
	 * 生成待签名串
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String genSignData(String jsonStr) {
		JSONObject jsonObject =  JSON.parseObject(jsonStr);
		StringBuilder content = new StringBuilder();

		// 按照key做首字母升序排列
		List<String> keys = new ArrayList<String>(jsonObject.keySet());
		Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
		for (int i = 0; i < keys.size(); i++) {
			String key = (String) keys.get(i);
			// sign 和ip_client 不参与签名
			if ("sign".equals(key)) {
				continue;
			}
			String value = (String) jsonObject.getString(key);
			// 空串不参与签名
			if (StringUtils.isEmpty(value)) {
				continue;
			}
			content.append((i == 0 ? "" : "&") + key + "=" + value);

		}
		String signSrc = content.toString();
		if (signSrc.startsWith("&")) {
			signSrc = signSrc.replaceFirst("&", "");
		}
		return signSrc;
	}
 
}
