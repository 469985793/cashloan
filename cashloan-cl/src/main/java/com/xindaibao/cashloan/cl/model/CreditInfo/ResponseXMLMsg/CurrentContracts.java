package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/6
 */
public class CurrentContracts {

    private CurrentBanking currentBanking;

    private CurrentNonBanking currentNonBanking;

    private Total total;

    public CurrentBanking getCurrentBanking() {
        return currentBanking;
    }

    public void setCurrentBanking(CurrentBanking currentBanking) {
        this.currentBanking = currentBanking;
    }

    public CurrentNonBanking getCurrentNonBanking() {
        return currentNonBanking;
    }

    public void setCurrentNonBanking(CurrentNonBanking currentNonBanking) {
        this.currentNonBanking = currentNonBanking;
    }

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }
}
