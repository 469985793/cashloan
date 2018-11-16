package com.xindaibao.cashloan.cl.sdk.sms;

import com.xindaibao.cashloan.cl.sdk.common.CreditLoanHttpAsyncClient;
import com.xindaibao.cashloan.cl.sdk.constant.Constant;
import com.xindaibao.cashloan.cl.sdk.model.Result;
import com.xindaibao.cashloan.cl.sdk.sms.utils.CheckSumBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * 云信短信业务
 * 短信验证码
 * Created by junlou.liu on 2017/9/11.
 */
public class CreditLoanSMSClient {
    private static final Logger logger = LoggerFactory.getLogger(CreditLoanSMSClient.class);

    /**
     * 发送短信验证码
     * @param apiKey        app_key
     * @param apiSecret     api_secret
     * @param targetPhone   目标手机号码
     * @param templateId    模版ID
     * @param nonce         随机数
     * @param codeLen       验证码长度
     * @return
     */
    public static String sendValidate(String apiKey, String apiSecret, String targetPhone, String templateId, String nonce, int codeLen){
        String content = null;
        try {
            String curTime = String.valueOf(System.currentTimeMillis() / 100L);
            String checkSum = CheckSumBuilder.getCheckSum(apiSecret, nonce, curTime);
            Map<String,String> params = new HashMap<>();
            // 设置请求的header
            params.put("AppKey", apiKey);
            params.put("Nonce", nonce);
            params.put("CurTime", curTime);
            params.put("CheckSum", checkSum);
            params.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            // 设置请求的的参数，requestBody参数
            Map<String, Object> bodyMap = new HashMap<>();
            bodyMap.put("templateid", templateId);
            bodyMap.put("mobile", targetPhone);
            bodyMap.put("codeLen", codeLen);

            HttpResponse response = CreditLoanHttpAsyncClient.getInstance().getByPost(Constant.NET_EASE_VALIDATE_URL, params, bodyMap, Constant.ENTITY_TYPE.URL_ENCODED_FORM_ENTITY);
            if (response.getStatusLine().getStatusCode() == 200){
                content = EntityUtils.toString(response.getEntity(), Charset.defaultCharset());
                content = (new Result(Constant.SUCCESS_CODE, Constant.SUCCESS_REQUEST_MESSAGE, content)).toString();
            } else {
                logger.error("云信平台--发送手机号：{} 短信验证码失败", targetPhone);
                content = (new Result(Constant.ERROR_CODE, Constant.ERROR_REQUEST_MESSAGE, content)).toString();
            }
        } catch (IOException e) {
            logger.error("请求发送手机号：{} 短信验证码失败", targetPhone);
            content = (new Result(Constant.ERROR_CODE, Constant.ERROR_REQUEST_MESSAGE, null)).toString();
        }
        return content;
    }


    /**
     * 短信通知
     */
//    public String sendNotify(){}
    /**
     * 运营短信
     */
//    public String sendOperate(){}

    public static void main(String[] args) {
//        CreditLoanSMSClient.sendValidate()
    }
}
