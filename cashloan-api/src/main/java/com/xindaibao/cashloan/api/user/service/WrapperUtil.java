package com.xindaibao.cashloan.api.user.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tool.util.BeanUtil;
import tool.util.StringUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.core.common.util.HttpUtil;
import com.xindaibao.cashloan.system.domain.SysConfig;
import com.xindaibao.cashloan.system.service.SysConfigService;


public class WrapperUtil {
	private static Logger logger = LoggerFactory.getLogger(WrapperUtil.class);
	
    /**
     * 初始化
     */
    public WrapperUtil(String url){
    	Map<String,Object> data = new HashMap<>();
    	logger.info("初始化开始--");
    	String projectId = Global.getValue("project_id");//项目id
    	String projectSecret = Global.getValue("project_secret");//项目秘钥
    	String itsmApiUrl = Global.getValue("itsm_api_url");//开放平台地址
    	
    	Map<String,Object> projectConfig = new HashMap<>();
    	projectConfig.put("projectId", projectId);
    	projectConfig.put("projectSecret", projectSecret);
    	projectConfig.put("itsmApiUrl", itsmApiUrl);
    	
    	Map<String,Object> httpConfig = new HashMap<>();
    	httpConfig.put("proxyIp", "");
    	httpConfig.put("proxyPort", "");
    	httpConfig.put("retry", "");
    	httpConfig.put("httpType", "HTTP");
    	
    	Map<String,Object> signConfig = new HashMap<>();
    	signConfig.put("algorithm", "HMACSHA256");
    	signConfig.put("privateKey", "");
    	signConfig.put("esignPublicKey", "");
    	
    	data.put("projectConfig", projectConfig);
    	data.put("httpConfig", httpConfig);
    	data.put("signConfig", signConfig);
    	
    	JSONObject result = HttpUtil.postClient(url+"/tech-sdkwrapper/timevale/init",JSON.toJSONString(data));
    	
    	logger.info("初始化结果--"+result);
    	
    	if (StringUtil.isBlank(result)) {
    		throw new RuntimeException("初始化失败,返回值为空");
		}else if (0 != result.getIntValue("errCode")) {
            logger.info("初始化失败，错误码=" + result.getString("errCode") + ",错误信息=" + result.getString("msg"));
            throw new RuntimeException(result.getString("msg"));
        }
    	logger.info("初始化结束--");
    }
    
    /**
     * 创建企业账户
     * @param name
     * @param organCode
     * @param regType
     * @return
     */
    public String addOrganize(String url){
    	logger.info("创建企业账户开始--");
    	String accountId = Global.getValue("account_id");
    	String organName = Global.getValue("organ_name");
    	String organCode = Global.getValue("organ_code");
    	String regType = Global.getValue("reg_type");
    	
    	SysConfigService sysConfigService = (SysConfigService) BeanUtil.getBean("sysConfigService");

    	if (StringUtil.isBlank(accountId)&&organName!=null&&organCode!=null&&regType!=null) {
    		Map<String,Object> data = new HashMap<>();
    		data.put("email", "");
    		data.put("mobile", "");
    		data.put("name", organName);
    		data.put("organType", "0");
    		data.put("userType", "0");
    		data.put("organCode", organCode);
    		data.put("legalName", "");
    		data.put("legalIdNo", "");
    		data.put("legalArea", "0");
    		data.put("agentIdNo", "");
    		data.put("agentName", "");
    		data.put("address", "");
    		data.put("scope", "");
    		data.put("regType", regType);
    		
    		JSONObject result = HttpUtil.postClient(url+"/tech-sdkwrapper/timevale/account/addOrganize",JSON.toJSONString(data));
    		
    		logger.info("创建企业账户结果--"+result);

    		if (StringUtil.isBlank(result)) {
        		throw new RuntimeException("创建企业账户失败,返回值为空");
    		}else if (0 != result.getIntValue("errCode")) {
    			logger.info("创建账户失败，错误码=" + result.getString("errCode") + ",错误信息=" + result.getString("msg"));
    			throw new RuntimeException(result.getString("msg"));
    		}
    		accountId = result.getString("accountId");
    		
    		SysConfig sc = sysConfigService.findByCode("account_id");
    		sc.setValue(accountId);
    		try {
				long msg = sysConfigService.updateSysConfig(sc);
				if (msg<1L) {
					throw new RuntimeException("修改信息错误");
				}
			} catch (ServiceException e) {
				logger.error(e.getMessage(),e);
			}
    		
		}
    	
    	logger.info("创建企业账户结束--");
    	return accountId;
    }
    
    /**
     * 创建印章
     * @param userName
     * @return
     */
    public String getUserSealData(String url,String accountId) {
    	logger.info("创建企业印章开始--");
    	Map<String,Object> data = new HashMap<>();
    	data.put("accountId", accountId);
    	data.put("color", "RED");
    	data.put("templateType", "STAR");
    	data.put("hText", "");
    	data.put("qText", "3305020055639");
    	JSONObject result = HttpUtil.postClient(url+"/tech-sdkwrapper/timevale/seal/addOrganizeSeal",JSON.toJSONString(data));
    	
    	logger.info("创建企业印章结果--"+result);

    	if (StringUtil.isBlank(result)) {
    		throw new RuntimeException("创建企业印章失败,返回值为空");
		}else if (0 != result.getIntValue("errCode")) {
			logger.info("创建印章失败，错误码=" + result.getIntValue("errCode") + ",错误信息=" + result.getString("msg"));
			throw new RuntimeException(result.getString("msg"));
		}
		logger.info("创建企业印章开始--");
    	return result.getString("sealData");
    }
    
    /**
     * 签章
     * @param name
     * @param path
     * @param url
     */
    public String addUserSign(String path,String url){
    	String accountId = addOrganize(url);
    	Map<String,Object> data = new HashMap<>();
    	logger.info("签章开始--");
    	Map<String,Object> signPos = new HashMap<>();
    	signPos.put("posType", 0);
    	signPos.put("posPage", 1);
    	signPos.put("posX", "400");
    	signPos.put("posY", "100");
    	signPos.put("key", "");
    	signPos.put("width", "");
    	
    	Map<String,Object> file = new HashMap<>();
    	file.put("srcPdfFile", path);
    	file.put("dstPdfFile", path);
    	file.put("fileName", "");
    	file.put("ownerPassword", "");
    	
    	data.put("signPos", signPos);
    	data.put("file", file);
    	data.put("signType", "Single");
    	data.put("accountId", accountId);
    	data.put("sealData", getUserSealData(url,accountId));
    	
    	JSONObject result = HttpUtil.postClient(url+"/tech-sdkwrapper/timevale/sign/userFileSign",JSON.toJSONString(data));
    	
    	logger.info("签章结果--"+result);
    	
    	if (StringUtil.isBlank(result)) {
    		throw new RuntimeException("签章失败,返回值为空");
		}else if (0 != result.getIntValue("errCode")) {
			logger.info("签章失败，错误码=" + result.getIntValue("errCode") + ",错误信息=" + result.getString("msg"));
			throw new RuntimeException(result.getString("msg"));
		}
		logger.info("签章结束--");
		return result.getString("signServiceId");
    }

}
