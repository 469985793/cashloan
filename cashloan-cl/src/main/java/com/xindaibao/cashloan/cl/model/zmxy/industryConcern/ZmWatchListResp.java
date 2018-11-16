package com.xindaibao.cashloan.cl.model.zmxy.industryConcern;

import java.util.ArrayList;
import java.util.List;

import com.antgroup.zmxy.openplatform.api.domain.ZmWatchListDetail;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditWatchlistiiGetResponse;
import com.xindaibao.cashloan.cl.model.zmxy.base.BaseResp;
import com.xindaibao.cashloan.cl.model.zmxy.base.ZmConstant;

/**
 * Created by syq on 2016/9/13.
 */
@SuppressWarnings("serial")
public class ZmWatchListResp extends BaseResp {

    /**
     * 是否存在于行业关注名单中
     */
    private boolean isMatched;

    /**
     * 芝麻信用对于每一次请求返回的业务号。后续可以通过此业务号进行对账
     */
    private String bizNo;

    /**
     * 具体风险详情
     */
    private List<RiskDetail> riskDetails;

    public boolean isMatched() {
        return isMatched;
    }

    public String getBizNo() {
        return bizNo;
    }

    public List<RiskDetail> getRiskDetails() {
        return riskDetails;
    }


    public ZmWatchListResp(ZhimaCreditWatchlistiiGetResponse response) {
        super(response);
        this.isMatched = response.getIsMatched()==null?false:response.getIsMatched();
        this.bizNo = response.getBizNo();
        this.handelDetailList(response.getDetails());
    }


    private void handelDetailList(List<ZmWatchListDetail> details) {
        if (details != null && details.size() > 0) {
            riskDetails = new ArrayList<>(details.size());
            for (ZmWatchListDetail detail : details) {
                RiskDetail riskDetail = new RiskDetail();
                riskDetail.setBizCode(detail.getBizCode());
                riskDetail.setCode(detail.getCode());
                riskDetail.setSettlement(detail.getSettlement());
                riskDetail.setStatus(detail.getStatus());
                riskDetail.setType(detail.getType());
                riskDetail.setBizMsg(ZmConstant.getRiskMessage(detail.getBizCode()));
                riskDetail.setCodeMsg(ZmConstant.getRiskMessage(detail.getCode()));
                riskDetail.setTypeMsg(ZmConstant.getRiskMessage(detail.getType()));
                riskDetail.setExtendInfos(detail.getExtendInfo());
                riskDetails.add(riskDetail);
            }
        }
    }


}
