package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/6
 */
public class RepaymentInformation {

    private String TotalMonthlyPayment;

    private String ClosedContracts;

    public String getTotalMonthlyPayment() {
        return TotalMonthlyPayment;
    }

    public void setTotalMonthlyPayment(String totalMonthlyPayment) {
        TotalMonthlyPayment = totalMonthlyPayment;
    }

    public String getClosedContracts() {
        return ClosedContracts;
    }

    public void setClosedContracts(String closedContracts) {
        ClosedContracts = closedContracts;
    }
}
