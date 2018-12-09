package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/6
 */
public class ContractSummary {

    private AffordabilitySummary affordabilitySummary;

    private Debtor debtor;

    private Guarantor guarantor;

    private Overall overall;

    public AffordabilitySummary getAffordabilitySummary() {
        return affordabilitySummary;
    }

    public void setAffordabilitySummary(AffordabilitySummary affordabilitySummary) {
        this.affordabilitySummary = affordabilitySummary;
    }

    public Debtor getDebtor() {
        return debtor;
    }

    public void setDebtor(Debtor debtor) {
        this.debtor = debtor;
    }

    public Guarantor getGuarantor() {
        return guarantor;
    }

    public void setGuarantor(Guarantor guarantor) {
        this.guarantor = guarantor;
    }

    public Overall getOverall() {
        return overall;
    }

    public void setOverall(Overall overall) {
        this.overall = overall;
    }
}
