package com.xindaibao.cashloan.cl.model.pay.weixin.sdk.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.xindaibao.cashloan.cl.model.pay.weixin.sdk.IWXPayDomain;
import com.xindaibao.cashloan.cl.model.pay.weixin.sdk.WXPayConfig;
import com.xindaibao.cashloan.core.common.context.Global;

public class WXPayConfigImpl extends WXPayConfig{

    private byte[] certData;
    private static WXPayConfigImpl INSTANCE;

    private WXPayConfigImpl() throws Exception{
        /*String certPath = "D://CERT/common/apiclient_cert.p12";
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();*/
    }

    public static WXPayConfigImpl getInstance() throws Exception{
        if (INSTANCE == null) {
            synchronized (WXPayConfigImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WXPayConfigImpl();
                }
            }
        }
        return INSTANCE;
    }

    public String getAppID() {
    	return Global.getValue("wxpay_app_id");
        //return "wxc82eed57874871ab";
    }

    public String getMchID() {
        return Global.getValue("wxpay_mch_id");
        //return "1484029102";
    }

    public String getKey() {
    	return Global.getValue("wxpay_key");
    	//return "QNVIPInfor18Technology2017062403";
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis;
        certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }


    public int getHttpConnectTimeoutMs() {
        return 2000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    public IWXPayDomain getWXPayDomain() {
        return WXPayDomainSimpleImpl.instance();
    }

    /*public String getPrimaryDomain() {
        return "api.mch.weixin.qq.com";
    }

    public String getAlternateDomain() {
        return "api2.mch.weixin.qq.com";
    }
*/
    @Override
    public int getReportWorkerNum() {
        return 1;
    }

    @Override
    public int getReportBatchSize() {
        return 2;
    }
}
