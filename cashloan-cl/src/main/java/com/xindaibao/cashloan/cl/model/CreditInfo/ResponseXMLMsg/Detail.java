package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/6
 */
public class Detail {


    private String LostStolenRecordsFound;

    private String NumberOfCancelledClosedContracts;

    public String getLostStolenRecordsFound() {
        return LostStolenRecordsFound;
    }

    public void setLostStolenRecordsFound(String lostStolenRecordsFound) {
        LostStolenRecordsFound = lostStolenRecordsFound;
    }

    public String getNumberOfCancelledClosedContracts() {
        return NumberOfCancelledClosedContracts;
    }

    public void setNumberOfCancelledClosedContracts(String numberOfCancelledClosedContracts) {
        NumberOfCancelledClosedContracts = numberOfCancelledClosedContracts;
    }
}
