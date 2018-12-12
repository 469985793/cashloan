package com.xindaibao.cashloan.cl.model.CreditInfo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/7
 */
public class CreditInfoLog {

    /**
     * 主键
     */
    private Long id;

    //是否通过
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
    private String  subjectIDNumber;

    /**
     * 用户CIP等级
     */
    private String  CIPRiskGrade;

    /**
     * 用户等级
     */
    private String  mobileScoreGrade;

    /**
     * 用户分数
     */
    private String  mobileScore;

    /**
     * 用户CIP分数
     */
    private String  CIPScore;

    /**
     * messageID
     */
    private String  messageId;

    /**
     * remark
     */
    private String  remark;

    /**
     * 状态
     */
    private String  status;

    /**
     * 创建时间
     */
    private Date createdTime;


    /**
     * 修改时间
     */
    private Date  updatedTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSubjectIDNumber() {
        return subjectIDNumber;
    }

    public void setSubjectIDNumber(String subjectIDNumber) {
        this.subjectIDNumber = subjectIDNumber;
    }

    public String getCIPRiskGrade() {
        return CIPRiskGrade;
    }

    public void setCIPRiskGrade(String CIPRiskGrade) {
        this.CIPRiskGrade = CIPRiskGrade;
    }

    public String getMobileScoreGrade() {
        return mobileScoreGrade;
    }

    public void setMobileScoreGrade(String mobileScoreGrade) {
        this.mobileScoreGrade = mobileScoreGrade;
    }

    public String getMobileScore() {
        return mobileScore;
    }

    public void setMobileScore(String mobileScore) {
        this.mobileScore = mobileScore;
    }

    public String getCIPScore() {
        return CIPScore;
    }

    public void setCIPScore(String CIPScore) {
        this.CIPScore = CIPScore;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

}
