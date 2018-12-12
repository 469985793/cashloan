package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/6
 */
public class Record {

    private String Date;

    private String Grade;

    private String ProbabilityOfDefault;

    private ReasonsList reasonsList;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public String getProbabilityOfDefault() {
        return ProbabilityOfDefault;
    }

    public void setProbabilityOfDefault(String probabilityOfDefault) {
        ProbabilityOfDefault = probabilityOfDefault;
    }

    public ReasonsList getReasonsList() {
        return reasonsList;
    }

    public void setReasonsList(ReasonsList reasonsList) {
        this.reasonsList = reasonsList;
    }
}
