package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/6
 */
public class InquiriesAnalysis {

    private String TotalLast7Days;

    private String TotalLast1Month;

    private String NonBankingLast1Month;

    private PolicyRules policyRules;

    public String getTotalLast7Days() {
        return TotalLast7Days;
    }

    public void setTotalLast7Days(String totalLast7Days) {
        TotalLast7Days = totalLast7Days;
    }

    public String getTotalLast1Month() {
        return TotalLast1Month;
    }

    public void setTotalLast1Month(String totalLast1Month) {
        TotalLast1Month = totalLast1Month;
    }

    public String getNonBankingLast1Month() {
        return NonBankingLast1Month;
    }

    public void setNonBankingLast1Month(String nonBankingLast1Month) {
        NonBankingLast1Month = nonBankingLast1Month;
    }

    public PolicyRules getPolicyRules() {
        return policyRules;
    }

    public void setPolicyRules(PolicyRules policyRules) {
        this.policyRules = policyRules;
    }
}
