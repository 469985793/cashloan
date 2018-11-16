package com.xindaibao.cashloan.cl.sdk.face;

import com.alibaba.fastjson.JSON;
import com.xindaibao.cashloan.cl.sdk.face.model.R360AntiFraudBizData;
import com.xindaibao.cashloan.cl.sdk.utils.HttpTools;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.util.code.RSAUtils;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author
 *
 */
@Slf4j
public class R360AntiFraud {

    private static String version = "1.0.0";
    private static String charset = "utf-8";
    private static String format = "json";
    private static String signType 	= "RSA";
    private static int connectTimeout 	= 3000;
    private static int readTimeout    	= 40000;

    public static String request(UserBaseInfo userBaseInfo) throws Exception {

        Map<String, Object> configMap = Global.configMap;
        String rong360_url = (String)configMap.get("rong360_url");
        String rong360_appid = (String)configMap.get("rong360_appid");
        String rong360_privatekey = (String)configMap.get("rong360_privatekey");
        String rong360_anti_fraud_method = (String)configMap.get("rong360_anti_fraud_method");

      /*          String rong360_url = "https://openapi.rong360.com/gateway";
        String rong360_appid = "2010455";
        String rong360_privatekey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAM2D3e2HD6+oliRthe4MuWluug6x+vaNuvDQMv/LXqd029KtpLfYt+a1Qxa+A+OKj4SUKS5v/yueLvWsbVBvDLNNPNMfIhNLflivMIg8QcSwiOJ7cCdi9ubam2ZQqKtt9AU1+btIpYx/L2JElxWd/inzCqCcNcrlBK7r0MeEoKltAgMBAAECgYBsne4UFJbQwuwfcpcfOjBgLbo9/AjkeAG+NntNv+FV5agsj8OHuw1u7Iv5oBGUF+5EJr4hIeYd2mt9QlU/9oEh7AimVzC8ewH3umdH/bPV/0sdmecXc9nLGSSxjfJpJq62F25t8zTRuadoS+ChEnlzZLtTDQ/TMzljfvLMwfbLQQJBAOmMpOQDHM9XJNBG8eMVbiaOwbwgIq3X9IAI53A+NQKN52fJ6XPeU3XIh5pEmjOR1WevUSg/zGgjiPjqoWeeGD0CQQDhRVWaiZRfRBpn/f2jCDpmlL1iJQexoWtZ8hANWHyxmVwHWC+niju3VPBbCkXh4u8Df53Dt6v3tIYYVt7K4LjxAkEA51nDHXtX1qxkc01T37ci3l8b2teagssiEJLBcJauvFEFofKJp5Xc5xg1+8NpXRxhYcxxgIVyXtaUoL8MFDCvoQJBAIV0+BQHsdkGlcZKGPt/ImoncFM2sOoxQVIWMCBX7fehbX4EZqcRI/Om5IZq/Z+nPAaMkkCjDcRANLkyrebvX2ECQEQNji6M7kKX/ImU4EANMjMhvUehuNQIkcFbW6axHlStL+iH60FxbttDBVtcg9UWfLlxP8qwxfpdDDPRHm1Wa2s=";
        String rong360_anti_fraud_method = "tianji.api.agenty.AntiFraud";*/



        HashMap<String, String> params = new HashMap<>();
        params.put("method", rong360_anti_fraud_method);
        params.put("app_id", rong360_appid);
        params.put("format", format);
        params.put("version", version);
        params.put("sign_type", signType);
        params.put("timestamp", String.valueOf(new Date().getTime()));

        R360AntiFraudBizData r360AntiFraudBizData = new R360AntiFraudBizData();
        r360AntiFraudBizData.setIdNumber(userBaseInfo.getIdNo());
        r360AntiFraudBizData.setName(userBaseInfo.getRealName());
        r360AntiFraudBizData.setPhone(userBaseInfo.getPhone());
        params.put("biz_data", JSON.toJSONString(r360AntiFraudBizData));

        //sign处理 RSA加密
        String paramsStr = CreditLoanFace.getSortParams(params);
        log.info("待签名数据：" + paramsStr);
        //byte[] bytes = RSAUtils.generateSHA1withRSASigature(paramsStr, privateKey, charset);
        byte[] bytes = RSAUtils.generateSHA1withRSASigature(paramsStr,
                rong360_privatekey,
                charset);

        String sign =new Base64().encodeToString(bytes);
        log.info("签名后数据：" + sign);
        params.put("sign", sign);
        String result = HttpTools.post(rong360_url, params, connectTimeout, readTimeout);
        log.info("请求返回数据：" + result);
        return result;
    }
}
