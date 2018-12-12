package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/6
 */
public class Summary {

    private String NumberOfFraudAlertsPrimarySubject;

    private String NumberOfFraudAlertsThirdParty;

    public String getNumberOfFraudAlertsPrimarySubject() {
        return NumberOfFraudAlertsPrimarySubject;
    }

    public void setNumberOfFraudAlertsPrimarySubject(String numberOfFraudAlertsPrimarySubject) {
        NumberOfFraudAlertsPrimarySubject = numberOfFraudAlertsPrimarySubject;
    }

    public String getNumberOfFraudAlertsThirdParty() {
        return NumberOfFraudAlertsThirdParty;
    }

    public void setNumberOfFraudAlertsThirdParty(String numberOfFraudAlertsThirdParty) {
        NumberOfFraudAlertsThirdParty = numberOfFraudAlertsThirdParty;
    }
}
