package com.xindaibao.cashloan.cl.model.zmxy.authorize;

import java.net.URLDecoder;

import com.xindaibao.cashloan.cl.model.zmxy.base.ZmConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.antgroup.zmxy.openplatform.api.DefaultZhimaClient;
import com.antgroup.zmxy.openplatform.api.ZhimaApiException;
import com.antgroup.zmxy.openplatform.api.request.ZhimaAuthInfoAuthorizeRequest;
import com.antgroup.zmxy.openplatform.api.request.ZhimaAuthInfoAuthqueryRequest;
import com.antgroup.zmxy.openplatform.api.response.ZhimaAuthInfoAuthqueryResponse;
import com.xindaibao.cashloan.cl.model.zmxy.base.BaseQuery;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.core.common.util.StringUtil;


/**
 * 包装芝麻信用的授权查询
 * Created by syq on 2016/9/11.
 */
public class AuthInfoQuery extends BaseQuery {

	public static final Logger logger = LoggerFactory.getLogger(AuthInfoQuery.class);
	
    public AuthInfoQuery(String privateKey, String zhimaPublicKey, String appId) {
        super(privateKey, zhimaPublicKey, appId);
    }


    /**
     * 查询授权状态接口
     * 在查询这个接口之前，推荐先去数据库中查询目标的openId是否已经存在，避免重复查询
     *
     * @param certNo 身份证
     * @param name   姓名
     * @return 返回是授权状态
     * @throws ServiceException 
     */
    public AuthOpenIdResp queryOpenId(String certNo, String name) throws ServiceException {
        final String certType = "IDENTITY_CARD";
        JSONObject paramJson = new JSONObject(true);
        paramJson.put("certNo", certNo);
        paramJson.put("name", name);
        paramJson.put("certType", certType);
        ZhimaAuthInfoAuthqueryRequest req = new ZhimaAuthInfoAuthqueryRequest();
        req.setIdentityType("2");// 必要参数
        req.setIdentityParam(paramJson.toJSONString());
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, getAppId(), getPrivateKey(), getZhimaPublicKey());
        AuthOpenIdResp resp = null;
        try {
            ZhimaAuthInfoAuthqueryResponse response = client.execute(req);
            if(!StringUtil.isBlank(response.getErrorMessage())){
            	throw new ServiceException(response.getErrorMessage());
            }
            logger.info(JSON.toJSON(response)+"");
            resp = new AuthOpenIdResp(response);
        } catch (Exception e) {
			throw new ServiceException(e.getMessage(),e);
        }
        return resp;
    }


    /**
     * 获取PC授权页面跳转的地址
     *
     * @param certNo 身份证
     * @param name   姓名
     * @param key    回调业务唯一标识，例如订单号这种
     * @return 返回授权跳转地址，前端收到地址后可直接跳转
     */
    public String getAuthorizeUrl(String certNo, String name, String key) {
        final String certType = "IDENTITY_CARD";
        final String AuthCode = "M_APPPC_CERT";
        ZhimaAuthInfoAuthorizeRequest req = new ZhimaAuthInfoAuthorizeRequest();
        req.setIdentityType("2");
        req.setChannel("apppc");
        JSONObject bizParamsJson = new JSONObject(true);
        bizParamsJson.put("auth_code", AuthCode);
        bizParamsJson.put("state", key);
        req.setBizParams(bizParamsJson.toJSONString());
        JSONObject paramJson = new JSONObject(true);
        paramJson.put("certNo", certNo);
        paramJson.put("name", name);
        paramJson.put("certType", certType);
        req.setIdentityParam(paramJson.toJSONString());
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, getAppId(), getPrivateKey(), getZhimaPublicKey());
        String url = null;
        try {
            url = client.generatePageRedirectInvokeUrl(req);
            logger.info(url);
        } catch (ZhimaApiException e) {
        	logger.error(e.getMessage(),e);
        }
        return url;
    }
    /**
     * 获取H5授权页面跳转的地址 手机上使用
     *
     * @param certNo 身份证
     * @param name   姓名
     * @param key    回调业务唯一标识，例如订单号这种
     * @return 返回授权跳转地址，前端收到地址后可直接跳转
     */
    public String getPhoneAuthorizeUrl(String certNo, String name, String key) {
       final String certType = "IDENTITY_CARD";
        final String AuthCode = "M_H5";
        ZhimaAuthInfoAuthorizeRequest req = new ZhimaAuthInfoAuthorizeRequest();
        req.setIdentityType("2");
        req.setChannel("app");
        req.setPlatform("zmop");
        JSONObject bizParamsJson = new JSONObject(true);
        bizParamsJson.put("auth_code", AuthCode);
        bizParamsJson.put("channelType", "app");
        bizParamsJson.put("state", key);
        req.setBizParams(bizParamsJson.toJSONString());
        JSONObject paramJson = new JSONObject(true);
        paramJson.put("certNo", certNo);
        paramJson.put("name", name);
        paramJson.put("certType", certType);
        req.setIdentityParam(paramJson.toJSONString());
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, getAppId(),getPrivateKey(), getZhimaPublicKey());
//        logger.debug("certNo+name+key:"+certNo+",    "+name+",    "+key);
//        logger.debug("appId+私钥+公钥："+getAppId()+",     "+getPrivateKey()+",         "+getZhimaPublicKey());
//        logger.debug("req："+req.toString());
//        System.out.println("certNo+name+key:"+certNo+",    "+name+",    "+key);
//        System.out.println("appId+私钥+公钥："+getAppId()+",     "+getPrivateKey()+",         "+getZhimaPublicKey());
//        System.out.println("req："+req.toString());
        
        String url = null;
        try {
            url = client.generatePageRedirectInvokeUrl(req);
            logger.debug(url);
        } catch (ZhimaApiException e) {
        	logger.error(e.getMessage(),e);
        }
        return url;
    }
    public String sign(String params,String sign) throws Exception{
		String params_ = "";
		String sign_ = "";
		if (params.indexOf("%") != -1) {
			params_ = URLDecoder.decode(params, ZmConstant.CHARSET);
		}

		if (sign.indexOf("%") != -1) {
			sign_ = URLDecoder.decode(sign, ZmConstant.CHARSET);
		}

		DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, getAppId(), getPrivateKey(),
				getZhimaPublicKey());
		String result = null;
		try {
			result = client.decryptAndVerifySign(params_, sign_);
			logger.info(result);

		} catch (ZhimaApiException e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}
}
