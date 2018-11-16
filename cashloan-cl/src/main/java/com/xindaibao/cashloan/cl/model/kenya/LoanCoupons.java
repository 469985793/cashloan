package com.xindaibao.cashloan.cl.model.kenya;

import java.util.Date;

public class LoanCoupons {
    private Long id;

    private Integer couponsNum;

    private Integer couponsTime;

    private Byte status;

    private Date createdTime;

    private Date updatedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCouponsNum() {
        return couponsNum;
    }

    public void setCouponsNum(Integer couponsNum) {
        this.couponsNum = couponsNum;
    }

    public Integer getCouponsTime() {
        return couponsTime;
    }

    public void setCouponsTime(Integer couponsTime) {
        this.couponsTime = couponsTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
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