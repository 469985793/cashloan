package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg;

import model.CreditInfo.ResponseXMLMsg._Parameters.Sections;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/6
 */
public class Parameters {

    private String Consent;

    private String IDNumber;

    private String IDNumberType;

    private String InquiryReason;

    private String ReportDate;

    private Sections sections;

    private String SubjectType;

    public String getConsent() {
        return Consent;
    }

    public void setConsent(String consent) {
        Consent = consent;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public String getIDNumberType() {
        return IDNumberType;
    }

    public void setIDNumberType(String IDNumberType) {
        this.IDNumberType = IDNumberType;
    }

    public String getInquiryReason() {
        return InquiryReason;
    }

    public void setInquiryReason(String inquiryReason) {
        InquiryReason = inquiryReason;
    }

    public String getReportDate() {
        return ReportDate;
    }

    public void setReportDate(String reportDate) {
        ReportDate = reportDate;
    }

    public Sections getSections() {
        return sections;
    }

    public void setSections(Sections sections) {
        this.sections = sections;
    }

    public String getSubjectType() {
        return SubjectType;
    }

    public void setSubjectType(String subjectType) {
        SubjectType = subjectType;
    }
}
