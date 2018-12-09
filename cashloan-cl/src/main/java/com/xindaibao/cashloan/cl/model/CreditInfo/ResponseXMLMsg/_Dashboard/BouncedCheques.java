package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._Dashboard;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/6
 */
public class BouncedCheques {

    private String NumberOfCheques;

    private String WeekSinceLastCheque;

    public String getNumberOfCheques() {
        return NumberOfCheques;
    }

    public void setNumberOfCheques(String numberOfCheques) {
        NumberOfCheques = numberOfCheques;
    }

    public String getWeekSinceLastCheque() {
        return WeekSinceLastCheque;
    }

    public void setWeekSinceLastCheque(String weekSinceLastCheque) {
        WeekSinceLastCheque = weekSinceLastCheque;
    }
}
