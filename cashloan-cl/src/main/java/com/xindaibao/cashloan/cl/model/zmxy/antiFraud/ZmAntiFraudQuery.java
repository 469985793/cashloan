package com.xindaibao.cashloan.cl.model.zmxy.antiFraud;




import com.xindaibao.cashloan.cl.model.zmxy.base.ZmConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.antgroup.zmxy.openplatform.api.DefaultZhimaClient;
import com.antgroup.zmxy.openplatform.api.ZhimaApiException;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCreditIvsDetailGetRequest;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditIvsDetailGetResponse;
import com.xindaibao.cashloan.cl.model.zmxy.base.BaseQuery;


/**
 * 反欺诈信息验证
 * 注意反欺诈验证不需要用户授权，即不需要openId
 * Created by syq on 2016/9/12.
 */
public class ZmAntiFraudQuery extends BaseQuery {
	public static final Logger logger = LoggerFactory.getLogger(ZmAntiFraudQuery.class);

    public ZmAntiFraudQuery(String privateKey, String zhimaPublicKey, String appId) {
        super(privateKey, zhimaPublicKey, appId);
    }


    public ZmAntiFraudResp QueryAntiFraud(AntiFraudParam antiFraudParam, String transactionId) {

        ZhimaCreditIvsDetailGetRequest req = new ZhimaCreditIvsDetailGetRequest();
        req.setChannel(ZmConstant.ZmChannel);
        req.setPlatform(ZmConstant.ZmPlatform);
        req.setProductCode(ZmConstant.ZmAnitFraudProductCode);// 必要参数
        req.setTransactionId(transactionId);

        req.setCertNo(antiFraudParam.getCertNo());
        req.setCertType("100");
        req.setName(antiFraudParam.getName());
        req.setMobile(antiFraudParam.getMobile());
        req.setEmail(antiFraudParam.getEmail());
        req.setBankCard(antiFraudParam.getBankCard());
        req.setAddress(antiFraudParam.getAddress());
        req.setIp(antiFraudParam.getIp());
        req.setMac(antiFraudParam.getMac());
        req.setWifimac(antiFraudParam.getWifimac());
        req.setImei(antiFraudParam.getImei());
        req.setImsi(antiFraudParam.getImsi());

        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, getAppId(), getPrivateKey(), getZhimaPublicKey());
        ZmAntiFraudResp zmAntiFraudResp = null;
        try {
            ZhimaCreditIvsDetailGetResponse response = client.execute(req);
            zmAntiFraudResp = new ZmAntiFraudResp(response);
        } catch (ZhimaApiException e) {
            logger.error(e.getMessage(),e);
        }
        return zmAntiFraudResp;
    }


}
