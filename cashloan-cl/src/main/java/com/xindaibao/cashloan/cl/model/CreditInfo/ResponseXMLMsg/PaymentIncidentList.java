package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg;

import com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._PaymentIncidentList.Summary;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/6
 */
public class PaymentIncidentList {

    private com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._PaymentIncidentList.PaymentIncidentList paymentIncidentList;

    private com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._PaymentIncidentList.Summary summary;

    public com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._PaymentIncidentList.PaymentIncidentList getPaymentIncidentList() {
        return paymentIncidentList;
    }

    public void setPaymentIncidentList(com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._PaymentIncidentList.PaymentIncidentList paymentIncidentList) {
        this.paymentIncidentList = paymentIncidentList;
    }

    public com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._PaymentIncidentList.Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }
}
