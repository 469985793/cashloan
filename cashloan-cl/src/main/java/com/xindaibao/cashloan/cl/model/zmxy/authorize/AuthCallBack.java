package com.xindaibao.cashloan.cl.model.zmxy.authorize;

import java.net.URLDecoder;

import com.xindaibao.cashloan.cl.model.zmxy.base.ZmConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.antgroup.zmxy.openplatform.api.internal.util.RSACoderUtil;
import com.xindaibao.cashloan.cl.model.zmxy.base.BaseQuery;

/**
 * 对授权返回的参数进行解析
 * Created by syq on 2016/9/11.
 */
public class AuthCallBack extends BaseQuery {

	public static final Logger logger = LoggerFactory.getLogger(AuthCallBack.class);
    private String openId;

    private String key;

    private String errorCode;

    private String errorMessage;

    public AuthCallBack(String privateKey, String zhimaPublicKey, String appId) {
        super(privateKey);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getOpenId() {
        return openId;
    }

    public String getKey() {
        return key;
    }

    /**
     * 解析芝麻信用回调后的参数，返回解析结果状态
     *
     * @param param
     * @return
     */
    public AuthCallBackResp decryptedParam(String param) {
        AuthCallBackResp authCallBackResp = new AuthCallBackResp();
        try {
            String decryptedParam = RSACoderUtil.decrypt(param, getPrivateKey(), ZmConstant.CHARSET);//通过浏览器返回时，不需要decode
            decryptedParam = URLDecoder.decode(decryptedParam, ZmConstant.CHARSET);
            logger.info(decryptedParam);
            String[] keyValues = decryptedParam.split("&");
            JSONObject paramJson = new JSONObject(true);
            for (String keyValue : keyValues) {
                String[] each = keyValue.split("=");
                paramJson.put(each[0], each[1]);
            }
            authCallBackResp.setSuccess(paramJson.getBooleanValue("success"));
            authCallBackResp.setOpenId(paramJson.getString("open_id"));
            authCallBackResp.setKey(paramJson.getString("state"));
            authCallBackResp.setErrorCode(paramJson.getString("error_code"));
            authCallBackResp.setErrorMessage( paramJson.getString("error_message"));
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return authCallBackResp;
    }

}
