package com.xindaibao.cashloan.cl.model.tongdun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;

/**
 * 来自同盾官方文档 - 目前未使用
 */
public class RiskServicePreloan {
    private static final Log log = LogFactory.getLog(RiskServicePreloan.class);
    private static final String submitUrl = "https://api.tongdun.cn/credit/event/v3";


    private static final String PARTNER_CODE = "changna";// 合作方标识
    private static final String PARTNER_KEY = "a966c2afdc2b44ecbd3a934ccfb1a011";//合作方密钥
    private static final String PARTNER_APP = "llqb_and";//应用名 fixme: ????

    private static final String EVENT_TYPE = "loan";//  事件类型
    private static final String VERSION = "v1";//  表单版本号


    private HttpsURLConnection conn;
    private SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();

    /**
     * submit接口示例
     *
     * @param params
     * @return
     */
    public PreloanSubmitResponse apply(Map<String, Object> params) {

        PreloanSubmitResponse submitResponse = new PreloanSubmitResponse();
        try {
            String urlString = new StringBuilder().append(submitUrl)
                    .append("?partner_code=")
                    .append(PARTNER_CODE).append("&partner_key=")
                    .append(PARTNER_KEY).append("&app_name=")
                    .append(PARTNER_APP).append("&event_type=")
                    .append(EVENT_TYPE).append("&version=")
                    .append(VERSION).toString();

            URL url = new URL(urlString);
            // 组织请求参数
            StringBuilder postBody = new StringBuilder();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (entry.getValue() == null)
                    continue;

                postBody.append(entry.getKey()).append("=")
                        .append(URLEncoder.encode(entry.getValue().toString(),
                        "utf-8")).append("&");
            }

            if (!params.isEmpty()) {
                postBody.deleteCharAt(postBody.length() - 1);
            }

            conn = (HttpsURLConnection) url.openConnection();
            //设置https
            conn.setSSLSocketFactory(ssf);
            // 设置长链接
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置连接超时
            conn.setConnectTimeout(1000);
            // 设置读取超时，建议设置为3000ms。若同时调用了信息核验服务，请与客户经理协商确认具体时间”
            conn.setReadTimeout(3000);
            // 提交参数
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.getOutputStream().write(postBody.toString().getBytes());
            conn.getOutputStream().flush();
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), "utf-8"));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line).append("\n");
                }
                return JSON.parseObject(result.toString().trim(), PreloanSubmitResponse.class);
            }
        } catch (Exception e) {
            log.error("[RiskServicePreloan] apply throw exception, details: " + e);
        }
        return submitResponse;
    }

}
