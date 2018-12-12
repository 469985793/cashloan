package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/6
 */
public class PastDueInformation {

    private String TotalCurrentPastDue;

    private String TotalCurrentDaysPastDue;

    private String MonthsWithoutArrearsLast12Months;

    private String TotalMonthsWithHistoryLast12Months;

    public String getTotalCurrentPastDue() {
        return TotalCurrentPastDue;
    }

    public void setTotalCurrentPastDue(String totalCurrentPastDue) {
        TotalCurrentPastDue = totalCurrentPastDue;
    }

    public String getTotalCurrentDaysPastDue() {
        return TotalCurrentDaysPastDue;
    }

    public void setTotalCurrentDaysPastDue(String totalCurrentDaysPastDue) {
        TotalCurrentDaysPastDue = totalCurrentDaysPastDue;
    }

    public String getMonthsWithoutArrearsLast12Months() {
        return MonthsWithoutArrearsLast12Months;
    }

    public void setMonthsWithoutArrearsLast12Months(String monthsWithoutArrearsLast12Months) {
        MonthsWithoutArrearsLast12Months = monthsWithoutArrearsLast12Months;
    }

    public String getTotalMonthsWithHistoryLast12Months() {
        return TotalMonthsWithHistoryLast12Months;
    }

    public void setTotalMonthsWithHistoryLast12Months(String totalMonthsWithHistoryLast12Months) {
        TotalMonthsWithHistoryLast12Months = totalMonthsWithHistoryLast12Months;
    }
}
