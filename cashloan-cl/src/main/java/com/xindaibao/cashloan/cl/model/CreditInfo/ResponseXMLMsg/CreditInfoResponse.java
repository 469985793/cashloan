package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg;

import java.math.BigDecimal;

/**
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/2
 */
public class CreditInfoResponse {

    /**
     * 是否通过
     */
    private String resultCode;

    /**
     * CreditInfo 返回该ID真实姓名
     */
    private String fullName;

    /**
     * 额度
     */
    private BigDecimal creditLimit;

    /**
     * 用户ID
     */
    private String  SubjectIDNumber;

    /**
     * 用户CIP等级
     */
    private String  CIPRiskGrade;

    /**
     * 用户等级
     */
    private String  MobileScoreRiskGrade;

    public String getCIPRiskGrade() {
        return CIPRiskGrade;
    }

    public void setCIPRiskGrade(String CIPRiskGrade) {
        this.CIPRiskGrade = CIPRiskGrade;
    }

    public String getMobileScoreRiskGrade() {
        return MobileScoreRiskGrade;
    }

    public void setMobileScoreRiskGrade(String mobileScoreRiskGrade) {
        MobileScoreRiskGrade = mobileScoreRiskGrade;
    }

    public String getSubjectIDNumber() {
        return SubjectIDNumber;
    }

    public void setSubjectIDNumber(String subjectIDNumber) {
        SubjectIDNumber = subjectIDNumber;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }
}
