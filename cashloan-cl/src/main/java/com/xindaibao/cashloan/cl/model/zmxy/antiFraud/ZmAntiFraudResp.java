package com.xindaibao.cashloan.cl.model.zmxy.antiFraud;

import java.util.List;

import com.antgroup.zmxy.openplatform.api.domain.IvsDetail;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditIvsDetailGetResponse;
import com.xindaibao.cashloan.cl.model.zmxy.base.BaseResp;

/**
 * Created by syq on 2016/9/13.
 */
@SuppressWarnings("serial")
public class ZmAntiFraudResp extends BaseResp {

    /**
     * ivs评分。取值区间为[0,100]。分数越高，表示可信程度越高。
     */
    private Long ivsScore;

    /**
     * 风险因素code与风险描述说明
     */
    private List<IvsDetail> ivsDetails;

    /**
     * 芝麻信用对于每一次请求返回的业务号。后续可以通过此业务号进行对账
     */
    private String bizNo;

    public Long getIvsScore() {
        return ivsScore;
    }

    public List<IvsDetail> getIvsDetails() {
        return ivsDetails;
    }


    public String getBizNo() {
        return bizNo;
    }


    public ZmAntiFraudResp(ZhimaCreditIvsDetailGetResponse response) {
        super(response);
        this.bizNo = response.getBizNo();
        this.ivsScore = response.getIvsScore();
        this.ivsDetails = response.getIvsDetail();
    }






}