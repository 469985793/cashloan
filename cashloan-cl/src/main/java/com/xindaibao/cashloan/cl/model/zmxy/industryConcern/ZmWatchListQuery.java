package com.xindaibao.cashloan.cl.model.zmxy.industryConcern;

import com.xindaibao.cashloan.cl.model.zmxy.base.ZmConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.antgroup.zmxy.openplatform.api.DefaultZhimaClient;
import com.antgroup.zmxy.openplatform.api.ZhimaApiException;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCreditWatchlistiiGetRequest;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditWatchlistiiGetResponse;
import com.xindaibao.cashloan.cl.model.zmxy.base.BaseQuery;


/**
 * Created by syq on 2016/9/12.
 */
public class ZmWatchListQuery extends BaseQuery {

	public static final Logger logger = LoggerFactory.getLogger(ZmWatchListQuery.class);
    public ZmWatchListQuery(String privateKey, String zhimaPublicKey, String appId) {
        super(privateKey, zhimaPublicKey, appId);
    }


    /**
     * 查询芝麻用户是否在行业关注名单内
     *
     * @param openId        用户授权后获取的openId
     * @param transactionId 代表一笔请求的唯一标志，该标识作为对账的关键信息
     *                      推荐生成方式是：30位，（其中17位时间值（精确到毫秒）：yyyyMMddHHmmssSSS）加上（13位自增数字：1234567890123）
     * @return
     */
    public ZmWatchListResp queryWatchList(String openId, String transactionId) {
        ZhimaCreditWatchlistiiGetRequest creditWatchlistGetRequest = new ZhimaCreditWatchlistiiGetRequest();
        creditWatchlistGetRequest.setPlatform(ZmConstant.ZmPlatform);
        creditWatchlistGetRequest.setChannel(ZmConstant.ZmChannel);
        creditWatchlistGetRequest.setTransactionId(transactionId);
        creditWatchlistGetRequest.setProductCode(ZmConstant.ZmWatchListProductCode);
        creditWatchlistGetRequest.setOpenId(openId);
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, getAppId(), ZmConstant.CHARSET, getPrivateKey(), getZhimaPublicKey());
        ZmWatchListResp zmWatchListResp = null;
        try {
            ZhimaCreditWatchlistiiGetResponse response = client.execute(creditWatchlistGetRequest);
            logger.info(JSON.toJSON(response)+"");
            zmWatchListResp = new ZmWatchListResp(response);
        } catch (ZhimaApiException e) {
        	logger.error(e.getMessage(),e);
        }
        return zmWatchListResp;
    }


}
