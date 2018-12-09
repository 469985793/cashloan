package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._Dashboard;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/6
 */
public class CIQ {

    private String FraudAlerts;

    private String FraudAlertsThirdParty;

    public String getFraudAlerts() {
        return FraudAlerts;
    }

    public void setFraudAlerts(String fraudAlerts) {
        FraudAlerts = fraudAlerts;
    }

    public String getFraudAlertsThirdParty() {
        return FraudAlertsThirdParty;
    }

    public void setFraudAlertsThirdParty(String fraudAlertsThirdParty) {
        FraudAlertsThirdParty = fraudAlertsThirdParty;
    }
}
