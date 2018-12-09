package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._Disputes;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/6
 */
public class Summary {

    private String NumberOfActiveDisputesContracts;

    private String NumberOfActiveDisputesInCourt;

    private String NumberOfActiveDisputesSubjectData;

    private String NumberOfClosedDisputesContracts;

    private String NumberOfClosedDisputesSubjectData;

    private String NumberOfFalseDisputes;

    public String getNumberOfActiveDisputesContracts() {
        return NumberOfActiveDisputesContracts;
    }

    public void setNumberOfActiveDisputesContracts(String numberOfActiveDisputesContracts) {
        NumberOfActiveDisputesContracts = numberOfActiveDisputesContracts;
    }

    public String getNumberOfActiveDisputesInCourt() {
        return NumberOfActiveDisputesInCourt;
    }

    public void setNumberOfActiveDisputesInCourt(String numberOfActiveDisputesInCourt) {
        NumberOfActiveDisputesInCourt = numberOfActiveDisputesInCourt;
    }

    public String getNumberOfActiveDisputesSubjectData() {
        return NumberOfActiveDisputesSubjectData;
    }

    public void setNumberOfActiveDisputesSubjectData(String numberOfActiveDisputesSubjectData) {
        NumberOfActiveDisputesSubjectData = numberOfActiveDisputesSubjectData;
    }

    public String getNumberOfClosedDisputesContracts() {
        return NumberOfClosedDisputesContracts;
    }

    public void setNumberOfClosedDisputesContracts(String numberOfClosedDisputesContracts) {
        NumberOfClosedDisputesContracts = numberOfClosedDisputesContracts;
    }

    public String getNumberOfClosedDisputesSubjectData() {
        return NumberOfClosedDisputesSubjectData;
    }

    public void setNumberOfClosedDisputesSubjectData(String numberOfClosedDisputesSubjectData) {
        NumberOfClosedDisputesSubjectData = numberOfClosedDisputesSubjectData;
    }

    public String getNumberOfFalseDisputes() {
        return NumberOfFalseDisputes;
    }

    public void setNumberOfFalseDisputes(String numberOfFalseDisputes) {
        NumberOfFalseDisputes = numberOfFalseDisputes;
    }
}
