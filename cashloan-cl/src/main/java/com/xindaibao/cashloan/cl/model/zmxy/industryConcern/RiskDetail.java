package com.xindaibao.cashloan.cl.model.zmxy.industryConcern;

import java.util.List;

import com.antgroup.zmxy.openplatform.api.domain.ZmWatchListExtendInfo;

/**
 * Created by syq on 2016/9/12.
 */
public class RiskDetail {

    /**
     * 风险信息行业编号
     */
    private String bizCode;

    /**
     * 风险标签编号
     */
    private String code;

    /**
     * 结清状态
     */
    private boolean settlement;

    /**
     * 用户本人的异议状态信息
     */
    private String status;

    /**
     * 行业名单风险类型
     */
    private String type;

    /**
     * 风险信息行业编号对应的名称
     */
    private String bizMsg;

    /**
     * 风险标签编号对应的名称
     */
    private String codeMsg;

    /**
     * 行业名单风险类型对应的名称
     */
    private String typeMsg;

    /**
     * 具体详细信息（一般可不用）
     */
    private List<ZmWatchListExtendInfo> extendInfos;

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean getSettlement() {
        return settlement;
    }

    public void setSettlement(boolean settlement) {
        this.settlement = settlement;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBizMsg() {
        return bizMsg;
    }

    public void setBizMsg(String bizMsg) {
        this.bizMsg = bizMsg;
    }

    public String getCodeMsg() {
        return codeMsg;
    }

    public void setCodeMsg(String codeMsg) {
        this.codeMsg = codeMsg;
    }

    public String getTypeMsg() {
        return typeMsg;
    }

    public void setTypeMsg(String typeMsg) {
        this.typeMsg = typeMsg;
    }

    public List<ZmWatchListExtendInfo> getExtendInfos() {
        return extendInfos;
    }

    public void setExtendInfos(List<ZmWatchListExtendInfo> extendInfos) {
        this.extendInfos = extendInfos;
    }
}
