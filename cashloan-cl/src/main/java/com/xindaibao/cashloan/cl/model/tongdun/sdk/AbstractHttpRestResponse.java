package com.xindaibao.cashloan.cl.model.tongdun.sdk;


import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xindaibao.cashloan.cl.model.tongdun.http.AutoPickField;
import com.xindaibao.cashloan.cl.model.tongdun.http.HttpRestRequest;
import com.xindaibao.cashloan.cl.model.tongdun.http.HttpRestResponse;

/**
 * Created by syq on 2016/6/7.
 */
public class AbstractHttpRestResponse extends HttpRestResponse {


    @AutoPickField("success")
    private Boolean success;


    @AutoPickField("reason_desc")
    private String retMsg;

    @AutoPickField("reason_code")
    private String retCode;

    @AutoPickField
    private JSONObject data;
    
    @AutoPickField("code")
    private  String code;
    
    
    @AutoPickField("message")
    private  String message;
    
    
    public Boolean getSuccess() {
        return success;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public JSONObject getData() {
        return data;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
 

	@Override
    public String postResponseToJsonStr() {
        JSONObject retJson = new JSONObject(true);
        if (this.success) {
            retJson.put("code", 200);
            retJson.put("data", this.getData());
            retJson.put("message", "调用成功！");
        } else {
            retJson.put("code", this.retCode);
            retJson.put("message", this.getRetMsg());
        }
        return JSON.toJSONString(retJson);
    }
    public String postResponseToStr() {
        JSONObject retJson = new JSONObject(true);
        if (this.code.equals("0")) {
            retJson.put("code", 200);
            retJson.put("data", this.getData());
            retJson.put("message", "调用成功！");
        } else {
            retJson.put("code", this.code);
            retJson.put("message", this.getMessage());
        }
        return JSON.toJSONString(retJson);
    }


    @SuppressWarnings("rawtypes")
	public Map handleReqJson() {
        HttpRestRequest restRequest = this.getHttpRestRequest();
        Map req = JSONObject.parseObject(restRequest.getRequestBody(), Map.class);
        return req;
    }


    /**
     * 集成子类根据自己的需要对源信息做自定义处理
     *
     * @return
     */
    public JSONObject handleResJson() throws Exception{
        return this.getData();
    }


}
