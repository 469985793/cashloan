package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._Dashboard;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/6
 */
public class PaymentsProfile {

    private String NumberOfDifferentSubscribers;

    private String WorstPastDueDaysCurrent;

    private String WorstPastDueDaysForLast12Months;

    public String getNumberOfDifferentSubscribers() {
        return NumberOfDifferentSubscribers;
    }

    public void setNumberOfDifferentSubscribers(String numberOfDifferentSubscribers) {
        NumberOfDifferentSubscribers = numberOfDifferentSubscribers;
    }

    public String getWorstPastDueDaysCurrent() {
        return WorstPastDueDaysCurrent;
    }

    public void setWorstPastDueDaysCurrent(String worstPastDueDaysCurrent) {
        WorstPastDueDaysCurrent = worstPastDueDaysCurrent;
    }

    public String getWorstPastDueDaysForLast12Months() {
        return WorstPastDueDaysForLast12Months;
    }

    public void setWorstPastDueDaysForLast12Months(String worstPastDueDaysForLast12Months) {
        WorstPastDueDaysForLast12Months = worstPastDueDaysForLast12Months;
    }
}
