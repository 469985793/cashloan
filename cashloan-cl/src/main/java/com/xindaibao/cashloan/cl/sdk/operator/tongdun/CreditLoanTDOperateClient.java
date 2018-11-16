package com.xindaibao.cashloan.cl.sdk.operator.tongdun;

import com.xindaibao.cashloan.cl.sdk.common.CreditLoanHttpAsyncClient;
import com.xindaibao.cashloan.cl.sdk.constant.Constant;
import com.xindaibao.cashloan.cl.sdk.model.Result;
import com.xindaibao.cashloan.cl.sdk.utils.GZIPUtil;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * 同盾运营商SDK client
 */
public class CreditLoanTDOperateClient {
    private static final Logger logger = LoggerFactory.getLogger(CreditLoanTDOperateClient.class);

    public String get(String url, String partnerCode, String partnerKey, String taskId){
        String content = null;
        try {
            Map<String, String> params = new HashMap<>();
            params.put("Content-Type", "application/x-www-form-urlencoded");

            Map<String, Object> bodyMap = new HashMap<>();
            bodyMap.put("task_id", taskId);

            HttpResponse response = CreditLoanHttpAsyncClient.getInstance()
                    .getByPost(String.format(Constant.TD_OPERATE_URL, partnerCode, partnerKey),
                            params, bodyMap, Constant.ENTITY_TYPE.URL_ENCODED_FORM_ENTITY);
            if (response.getStatusLine().getStatusCode() == 200){
                content = EntityUtils.toString(response.getEntity(), Charset.defaultCharset());
                content = (new Result(Constant.SUCCESS_CODE, Constant.SUCCESS_REQUEST_MESSAGE, GZIPUtil.gunzip(content))).toString();
            } else {
                logger.error("获取同盾运营商任务 - {} 结果失败", taskId);
                content = (new Result(Constant.ERROR_CODE, Constant.ERROR_REQUEST_MESSAGE, GZIPUtil.gunzip(content))).toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
