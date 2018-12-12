package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/2
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "SubjectIDNumber"
})
public class GeneralInformation {

    //用户ID
    @XmlElement(required = true)
    private String SubjectIDNumber;

    //返回时间
    @XmlAttribute(name = "RequestDate", required = true)
    private String RequestDate;

    //ReferenceNumber
    @XmlAttribute(name = "ReferenceNumber", required = true)
    private String ReferenceNumber;

    //是否通过
    @XmlAttribute(name = "RecommendedDecision", required = true)
    private String RecommendedDecision;

    //BrokenRules
    @XmlAttribute(name = "BrokenRules", required = true)
    private String BrokenRules;

    //CreditInfo 建议额度
    @XmlAttribute(name = "CreditLimit", required = true)
    private BigDecimal CreditLimit;

    public String getSubjectIDNumber() {
        return SubjectIDNumber;
    }

    public void setSubjectIDNumber(String subjectIDNumber) {
        SubjectIDNumber = subjectIDNumber;
    }

    public String getRequestDate() {
        return RequestDate;
    }

    public void setRequestDate(String requestDate) {
        RequestDate = requestDate;
    }

    public String getReferenceNumber() {
        return ReferenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        ReferenceNumber = referenceNumber;
    }

    public String getRecommendedDecision() {
        return RecommendedDecision;
    }

    public void setRecommendedDecision(String recommendedDecision) {
        RecommendedDecision = recommendedDecision;
    }

    public String getBrokenRules() {
        return BrokenRules;
    }

    public void setBrokenRules(String brokenRules) {
        BrokenRules = brokenRules;
    }

    public BigDecimal getCreditLimit() {
        return CreditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        CreditLimit = creditLimit;
    }
}
