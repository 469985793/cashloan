package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg;

import model.CreditInfo.ResponseXMLMsg._PaymentIncidentList.Summary;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/6
 */
public class PaymentIncidentList {

    private model.CreditInfo.ResponseXMLMsg._PaymentIncidentList.PaymentIncidentList paymentIncidentList;

    private model.CreditInfo.ResponseXMLMsg._PaymentIncidentList.Summary summary;

    public model.CreditInfo.ResponseXMLMsg._PaymentIncidentList.PaymentIncidentList getPaymentIncidentList() {
        return paymentIncidentList;
    }

    public void setPaymentIncidentList(model.CreditInfo.ResponseXMLMsg._PaymentIncidentList.PaymentIncidentList paymentIncidentList) {
        this.paymentIncidentList = paymentIncidentList;
    }

    public model.CreditInfo.ResponseXMLMsg._PaymentIncidentList.Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }
}
