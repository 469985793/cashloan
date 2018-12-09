package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/6
 */
public class Overall {

    private String MaxDueInstallmentsClosedContracts;

    private String MaxDueInstallmentsOpenContracts;

    private String WorstDaysInArrears;

    private WorstOverdueBalance worstOverdueBalance;

    public String getMaxDueInstallmentsClosedContracts() {
        return MaxDueInstallmentsClosedContracts;
    }

    public void setMaxDueInstallmentsClosedContracts(String maxDueInstallmentsClosedContracts) {
        MaxDueInstallmentsClosedContracts = maxDueInstallmentsClosedContracts;
    }

    public String getMaxDueInstallmentsOpenContracts() {
        return MaxDueInstallmentsOpenContracts;
    }

    public void setMaxDueInstallmentsOpenContracts(String maxDueInstallmentsOpenContracts) {
        MaxDueInstallmentsOpenContracts = maxDueInstallmentsOpenContracts;
    }

    public String getWorstDaysInArrears() {
        return WorstDaysInArrears;
    }

    public void setWorstDaysInArrears(String worstDaysInArrears) {
        WorstDaysInArrears = worstDaysInArrears;
    }

    public WorstOverdueBalance getWorstOverdueBalance() {
        return worstOverdueBalance;
    }

    public void setWorstOverdueBalance(WorstOverdueBalance worstOverdueBalance) {
        this.worstOverdueBalance = worstOverdueBalance;
    }
}
