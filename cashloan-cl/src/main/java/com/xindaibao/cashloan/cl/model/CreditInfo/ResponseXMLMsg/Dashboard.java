package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg;

import com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._Dashboard.Collaterals;
import com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._Dashboard.PaymentsProfile;
import com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._Dashboard.Relations;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/6
 */
public class Dashboard {

    private BouncedCheques bouncedCheques;

    private CIP cip;

    private CIQ ciq;

    private Collaterals collaterals;

    private Disputes disputes;

    private Inquiries inquiries;

    private PaymentsProfile paymentsProfile;

    private Relations relations;


    public BouncedCheques getBouncedCheques() {
        return bouncedCheques;
    }

    public void setBouncedCheques(BouncedCheques bouncedCheques) {
        this.bouncedCheques = bouncedCheques;
    }

    public CIP getCip() {
        return cip;
    }

    public void setCip(CIP cip) {
        this.cip = cip;
    }

    public CIQ getCiq() {
        return ciq;
    }

    public void setCiq(CIQ ciq) {
        this.ciq = ciq;
    }

    public Collaterals getCollaterals() {
        return collaterals;
    }

    public void setCollaterals(Collaterals collaterals) {
        this.collaterals = collaterals;
    }

    public Disputes getDisputes() {
        return disputes;
    }

    public void setDisputes(Disputes disputes) {
        this.disputes = disputes;
    }

    public Inquiries getInquiries() {
        return inquiries;
    }

    public void setInquiries(Inquiries inquiries) {
        this.inquiries = inquiries;
    }

    public PaymentsProfile getPaymentsProfile() {
        return paymentsProfile;
    }

    public void setPaymentsProfile(PaymentsProfile paymentsProfile) {
        this.paymentsProfile = paymentsProfile;
    }

    public Relations getRelations() {
        return relations;
    }

    public void setRelations(Relations relations) {
        this.relations = relations;
    }
}
