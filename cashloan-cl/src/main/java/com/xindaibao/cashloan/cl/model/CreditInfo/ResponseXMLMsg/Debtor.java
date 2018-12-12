package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/6
 */
public class Debtor {

    private String ClosedContracts;

    private String OpenContracts;

    public String getClosedContracts() {
        return ClosedContracts;
    }

    public void setClosedContracts(String closedContracts) {
        ClosedContracts = closedContracts;
    }

    public String getOpenContracts() {
        return OpenContracts;
    }

    public void setOpenContracts(String openContracts) {
        OpenContracts = openContracts;
    }
}
