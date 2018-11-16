package com.xindaibao.cashloan.cl.model.tongdun.util;


/**
 * Created by syq on 2016/5/27.
 */
public class InterfaceConfig {

/*
    private static final Map<String, String> CODE_MESSAGE = new HashMap<>();


    public static final String partnerCode = Global.getValue("tongdun_partner_code");

    public static final String partnerKey = Global.getValue("tongdun_partner_key");

    public static final String appName = Global.getValue("tongdun_app_name");

    static {
        CODE_MESSAGE.put("Accept","建议通过");
        CODE_MESSAGE.put("Review","建议审核");
        CODE_MESSAGE.put("Reject","建议拒绝");

    }
    *//**
     * 主域名
     *//*
    //"https://api.tongdun.cn"; "https://apitest.tongdun.cn";
    private final static String HOST = Global.getValue("tongdun_partner_url");

    *//**
     * 前缀路径
     *//*
    private final static String LOAN_PREFIX = "/preloan/apply/v5";


    private final static String REPORT_PREFIX = "/preloan/report/v6";



    public static String preloanUrl(){
        return HOST + LOAN_PREFIX;
    }


    public static String getReportUrl(){
        return HOST + REPORT_PREFIX;
    }


    public static String getMessage(String key){
        return CODE_MESSAGE.get(key);
    }
*/
}
