package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/6
 */
public class CurrentNonBanking {

    private String Positive;

    private String Negative;

    private String Balance;

    private String BalanceAtRisk;

    public String getPositive() {
        return Positive;
    }

    public void setPositive(String positive) {
        Positive = positive;
    }

    public String getNegative() {
        return Negative;
    }

    public void setNegative(String negative) {
        Negative = negative;
    }

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String balance) {
        Balance = balance;
    }

    public String getBalanceAtRisk() {
        return BalanceAtRisk;
    }

    public void setBalanceAtRisk(String balanceAtRisk) {
        BalanceAtRisk = balanceAtRisk;
    }
}
