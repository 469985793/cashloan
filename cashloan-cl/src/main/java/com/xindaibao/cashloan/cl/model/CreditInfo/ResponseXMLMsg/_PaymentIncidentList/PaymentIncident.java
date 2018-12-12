package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._PaymentIncidentList;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/6
 */
public class PaymentIncident {

    private String AdditionalInformation;

    private String Code;

    private String DueDate;

    private OverdueBalance overdueBalance;

    private String PaymentStatus;

    private String PaymentType;

    private String Sector;

    public String getAdditionalInformation() {
        return AdditionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        AdditionalInformation = additionalInformation;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getDueDate() {
        return DueDate;
    }

    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }

    public OverdueBalance getOverdueBalance() {
        return overdueBalance;
    }

    public void setOverdueBalance(OverdueBalance overdueBalance) {
        this.overdueBalance = overdueBalance;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        PaymentStatus = paymentStatus;
    }

    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String paymentType) {
        PaymentType = paymentType;
    }

    public String getSector() {
        return Sector;
    }

    public void setSector(String sector) {
        Sector = sector;
    }
}
