package com.xindaibao.cashloan.cl.model.zmxy.creditScore;

import com.xindaibao.cashloan.cl.model.zmxy.base.BaseQuery;
import com.xindaibao.cashloan.cl.model.zmxy.base.ZmConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.antgroup.zmxy.openplatform.api.DefaultZhimaClient;
import com.antgroup.zmxy.openplatform.api.ZhimaApiException;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCreditScoreGetRequest;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditScoreGetResponse;


/**
 * 芝麻分的查询
 * Created by syq on 2016/9/12.
 */
public class ZmScoreQuery extends BaseQuery {

	public static final Logger logger = LoggerFactory.getLogger(ZmScoreQuery.class);
    public ZmScoreQuery(String privateKey, String zhimaPublicKey, String appId) {
        super(privateKey, zhimaPublicKey, appId);
    }


    /**
     *
     *
     * @param openId 用户授权后获取的openId
     * @return 请求是否成功
     */


    /**
     * 查询芝麻信用分值
     * @param openId 用户授权后获取的openId
     * @param transactionId 代表一笔请求的唯一标志，该标识作为对账的关键信息
     * @return
     */
    public ZmScoreResp queryScore(String openId, String transactionId) {
        ZhimaCreditScoreGetRequest req = new ZhimaCreditScoreGetRequest();
        req.setProductCode(ZmConstant.ZmScoreProductCode);
        req.setOpenId(openId);
        req.setTransactionId(transactionId);
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, getAppId(), getPrivateKey(), getZhimaPublicKey());
        ZmScoreResp zmScoreResp = null;
        try {
            ZhimaCreditScoreGetResponse response = client.execute(req);
            logger.debug(JSON.toJSON(response)+"");
            zmScoreResp = new ZmScoreResp(response);
        } catch (ZhimaApiException e) {
        	logger.error(e.getMessage(),e);
        }
        return zmScoreResp;
    }
}
