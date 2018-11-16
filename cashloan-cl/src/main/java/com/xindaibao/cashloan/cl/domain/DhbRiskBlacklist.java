package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;

/**
 * 贷后邦黑名单实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-06-02 18:21:33



 */
 public class DhbRiskBlacklist implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 订单号
    */
    private String orderNo;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 身份证是否命中黑名单
    */
    private String idcardInBlacklist;

    /**
    * 手机号是否命中黑名单
    */
    private String phoneInBlacklist;

    /**
    * 是否命中法院黑名单
    */
    private String inCourtBlacklist;

    /**
    * 是否命中网贷黑名单
    */
    private String inP2pBlacklist;

    /**
    * 是否命中银行黑名单
    */
    private String inBankBlacklist;

    /**
    * 最近该身份证出现在黑名单中时间
    */
    private String lastAppearIdcardInBlacklist;

    /**
    * 最近该手机号出现在黑名单中时间
    */
    private String lastAppearPhoneInBlacklist;


    /**
    * 获取主键Id
    *
    * @return id
    */
    public Long getId(){
        return id;
    }

    /**
    * 设置主键Id
    * 
    * @param 要设置的主键Id
    */
    public void setId(Long id){
        this.id = id;
    }

    /**
    * 获取订单号
    *
    * @return 订单号
    */
    public String getOrderNo(){
        return orderNo;
    }

    /**
    * 设置订单号
    * 
    * @param orderNo 要设置的订单号
    */
    public void setOrderNo(String orderNo){
        this.orderNo = orderNo;
    }

    /**
    * 获取用户id
    *
    * @return 用户id
    */
    public Long getUserId(){
        return userId;
    }

    /**
    * 设置用户id
    * 
    * @param userId 要设置的用户id
    */
    public void setUserId(Long userId){
        this.userId = userId;
    }

    /**
    * 获取身份证是否命中黑名单
    *
    * @return 身份证是否命中黑名单
    */
    public String getIdcardInBlacklist(){
        return idcardInBlacklist;
    }

    /**
    * 设置身份证是否命中黑名单
    * 
    * @param idcardInBlacklist 要设置的身份证是否命中黑名单
    */
    public void setIdcardInBlacklist(String idcardInBlacklist){
        this.idcardInBlacklist = idcardInBlacklist;
    }

    /**
    * 获取手机号是否命中黑名单
    *
    * @return 手机号是否命中黑名单
    */
    public String getPhoneInBlacklist(){
        return phoneInBlacklist;
    }

    /**
    * 设置手机号是否命中黑名单
    * 
    * @param phoneInBlacklist 要设置的手机号是否命中黑名单
    */
    public void setPhoneInBlacklist(String phoneInBlacklist){
        this.phoneInBlacklist = phoneInBlacklist;
    }

    /**
    * 获取是否命中法院黑名单
    *
    * @return 是否命中法院黑名单
    */
    public String getInCourtBlacklist(){
        return inCourtBlacklist;
    }

    /**
    * 设置是否命中法院黑名单
    * 
    * @param inCourtBlacklist 要设置的是否命中法院黑名单
    */
    public void setInCourtBlacklist(String inCourtBlacklist){
        this.inCourtBlacklist = inCourtBlacklist;
    }

    /**
    * 获取是否命中网贷黑名单
    *
    * @return 是否命中网贷黑名单
    */
    public String getInP2pBlacklist(){
        return inP2pBlacklist;
    }

    /**
    * 设置是否命中网贷黑名单
    * 
    * @param inP2pBlacklist 要设置的是否命中网贷黑名单
    */
    public void setInP2pBlacklist(String inP2pBlacklist){
        this.inP2pBlacklist = inP2pBlacklist;
    }

    /**
    * 获取是否命中银行黑名单
    *
    * @return 是否命中银行黑名单
    */
    public String getInBankBlacklist(){
        return inBankBlacklist;
    }

    /**
    * 设置是否命中银行黑名单
    * 
    * @param inBankBlacklist 要设置的是否命中银行黑名单
    */
    public void setInBankBlacklist(String inBankBlacklist){
        this.inBankBlacklist = inBankBlacklist;
    }

    /**
    * 获取最近该身份证出现在黑名单中时间
    *
    * @return 最近该身份证出现在黑名单中时间
    */
    public String getLastAppearIdcardInBlacklist(){
        return lastAppearIdcardInBlacklist;
    }

    /**
    * 设置最近该身份证出现在黑名单中时间
    * 
    * @param lastAppearIdcardInBlacklist 要设置的最近该身份证出现在黑名单中时间
    */
    public void setLastAppearIdcardInBlacklist(String lastAppearIdcardInBlacklist){
        this.lastAppearIdcardInBlacklist = lastAppearIdcardInBlacklist;
    }

    /**
    * 获取最近该手机号出现在黑名单中时间
    *
    * @return 最近该手机号出现在黑名单中时间
    */
    public String getLastAppearPhoneInBlacklist(){
        return lastAppearPhoneInBlacklist;
    }

    /**
    * 设置最近该手机号出现在黑名单中时间
    * 
    * @param lastAppearPhoneInBlacklist 要设置的最近该手机号出现在黑名单中时间
    */
    public void setLastAppearPhoneInBlacklist(String lastAppearPhoneInBlacklist){
        this.lastAppearPhoneInBlacklist = lastAppearPhoneInBlacklist;
    }

}