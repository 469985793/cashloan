package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/3
 */
public class ScoringAnalysis {

    private String CIPScore;

    private String CIPRiskGrade;

    private String MobileScore;

    private String MobileScoreRiskGrade;

    private String Conclusion;

    private PolicyRules policyRules;

    public String getCIPScore() {
        return CIPScore;
    }

    public void setCIPScore(String CIPScore) {
        this.CIPScore = CIPScore;
    }

    public String getCIPRiskGrade() {
        return CIPRiskGrade;
    }

    public void setCIPRiskGrade(String CIPRiskGrade) {
        this.CIPRiskGrade = CIPRiskGrade;
    }

    public String getMobileScore() {
        return MobileScore;
    }

    public void setMobileScore(String mobileScore) {
        MobileScore = mobileScore;
    }

    public String getMobileScoreRiskGrade() {
        return MobileScoreRiskGrade;
    }

    public void setMobileScoreRiskGrade(String mobileScoreRiskGrade) {
        MobileScoreRiskGrade = mobileScoreRiskGrade;
    }

    public String getConclusion() {
        return Conclusion;
    }

    public void setConclusion(String conclusion) {
        Conclusion = conclusion;
    }

    public PolicyRules getPolicyRules() {
        return policyRules;
    }

    public void setPolicyRules(PolicyRules policyRules) {
        this.policyRules = policyRules;
    }
}
