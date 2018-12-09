package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg;

import javax.xml.bind.annotation.*;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/1
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Response", propOrder = {
        "status"
})
public class Response {

    @XmlElement(required = true)
    private String status;

    @XmlAttribute(name = "infomsg", required = true)
    private String infomsg;

    @XmlAttribute(name = "Currency", required = true)
    private String Currency;

    private GeneralInformation generalInformation;

    private PersonalInformation personalInformation;

    private ScoringAnalysis scoringAnalysis;

    private InquiriesAnalysis inquiriesAnalysis;

    private CurrentContracts currentContracts;

    private PastDueInformation pastDueInformation;

    private RepaymentInformation repaymentInformation;

    private PolicyRules policyRules;

    private KenCb5_data kenCb5_data;

    private Strategy strategy;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfomsg() {
        return infomsg;
    }

    public void setInfomsg(String infomsg) {
        this.infomsg = infomsg;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public GeneralInformation getGeneralInformation() {
        return generalInformation;
    }

    public void setGeneralInformation(GeneralInformation generalInformation) {
        this.generalInformation = generalInformation;
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
    }

    public ScoringAnalysis getScoringAnalysis() {
        return scoringAnalysis;
    }

    public void setScoringAnalysis(ScoringAnalysis scoringAnalysis) {
        this.scoringAnalysis = scoringAnalysis;
    }

    public InquiriesAnalysis getInquiriesAnalysis() {
        return inquiriesAnalysis;
    }

    public void setInquiriesAnalysis(InquiriesAnalysis inquiriesAnalysis) {
        this.inquiriesAnalysis = inquiriesAnalysis;
    }

    public CurrentContracts getCurrentContracts() {
        return currentContracts;
    }

    public void setCurrentContracts(CurrentContracts currentContracts) {
        this.currentContracts = currentContracts;
    }

    public PastDueInformation getPastDueInformation() {
        return pastDueInformation;
    }

    public void setPastDueInformation(PastDueInformation pastDueInformation) {
        this.pastDueInformation = pastDueInformation;
    }

    public RepaymentInformation getRepaymentInformation() {
        return repaymentInformation;
    }

    public void setRepaymentInformation(RepaymentInformation repaymentInformation) {
        this.repaymentInformation = repaymentInformation;
    }

    public PolicyRules getPolicyRules() {
        return policyRules;
    }

    public void setPolicyRules(PolicyRules policyRules) {
        this.policyRules = policyRules;
    }

    public KenCb5_data getKenCb5_data() {
        return kenCb5_data;
    }

    public void setKenCb5_data(KenCb5_data kenCb5_data) {
        this.kenCb5_data = kenCb5_data;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
