package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;

/**
 * 贷后邦用户基本信息表实体
 */
 public class DhbUserBasic implements Serializable {

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
    * 年龄
    */
    private Integer age;

    /**
    * 性别
    */
    private String gender;

    /**
    * 生日日期
    */
    private String birthday;

    /**
    * 身份证是否是有效身份证   1: 通过，0：未通过
    */
    private Integer idcardValidate;

    /**
    * 身份证户籍省份
    */
    private String idcardProvince;

    /**
    * 身份证户籍城市
    */
    private String idcardCity;

    /**
    * 身份证户籍地区
    */
    private String idcardRegion;

    /**
    * 手机运营商
    */
    private String phoneOperator;

    /**
    * 手机归属地省份
    */
    private String phoneProvince;

    /**
    * 手机归属地城市
    */
    private String phoneCity;

    /**
    * 身份证号记录天数
    */
    private Integer recordIdcardDays;

    /**
    * 手机号记录天数
    */
    private Integer recordPhoneDays;

    /**
    * 身份证最近出现时间
    */
    private String lastAppearIdcard;

    /**
    * 手机号最近出现时间
    */
    private String lastAppearPhone;

    /**
    * 关联身份证数量
    */
    private Integer usedIdcardsCnt;

    /**
    * 关联手机号数量
    */
    private Integer usedPhonesCnt;


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
    * 获取年龄
    *
    * @return 年龄
    */
    public Integer getAge(){
        return age;
    }

    /**
    * 设置年龄
    * 
    * @param age 要设置的年龄
    */
    public void setAge(Integer age){
        this.age = age;
    }

    /**
    * 获取性别
    *
    * @return 性别
    */
    public String getGender(){
        return gender;
    }

    /**
    * 设置性别
    * 
    * @param gender 要设置的性别
    */
    public void setGender(String gender){
        this.gender = gender;
    }

    /**
    * 获取生日日期
    *
    * @return 生日日期
    */
    public String getBirthday(){
        return birthday;
    }

    /**
    * 设置生日日期
    * 
    * @param birthday 要设置的生日日期
    */
    public void setBirthday(String birthday){
        this.birthday = birthday;
    }

    /**
    * 获取身份证是否是有效身份证   1: 通过，0：未通过
    *
    * @return 身份证是否是有效身份证   1: 通过，0：未通过
    */
    public Integer getIdcardValidate(){
        return idcardValidate;
    }

    /**
    * 设置身份证是否是有效身份证   1: 通过，0：未通过
    * 
    * @param idcardValidate 要设置的身份证是否是有效身份证   1: 通过，0：未通过
    */
    public void setIdcardValidate(Integer idcardValidate){
        this.idcardValidate = idcardValidate;
    }

    /**
    * 获取身份证户籍省份
    *
    * @return 身份证户籍省份
    */
    public String getIdcardProvince(){
        return idcardProvince;
    }

    /**
    * 设置身份证户籍省份
    * 
    * @param idcardProvince 要设置的身份证户籍省份
    */
    public void setIdcardProvince(String idcardProvince){
        this.idcardProvince = idcardProvince;
    }

    /**
    * 获取身份证户籍城市
    *
    * @return 身份证户籍城市
    */
    public String getIdcardCity(){
        return idcardCity;
    }

    /**
    * 设置身份证户籍城市
    * 
    * @param idcardCity 要设置的身份证户籍城市
    */
    public void setIdcardCity(String idcardCity){
        this.idcardCity = idcardCity;
    }

    /**
    * 获取身份证户籍地区
    *
    * @return 身份证户籍地区
    */
    public String getIdcardRegion(){
        return idcardRegion;
    }

    /**
    * 设置身份证户籍地区
    * 
    * @param idcardRegion 要设置的身份证户籍地区
    */
    public void setIdcardRegion(String idcardRegion){
        this.idcardRegion = idcardRegion;
    }

    /**
    * 获取手机运营商
    *
    * @return 手机运营商
    */
    public String getPhoneOperator(){
        return phoneOperator;
    }

    /**
    * 设置手机运营商
    * 
    * @param phoneOperator 要设置的手机运营商
    */
    public void setPhoneOperator(String phoneOperator){
        this.phoneOperator = phoneOperator;
    }

    /**
    * 获取手机归属地省份
    *
    * @return 手机归属地省份
    */
    public String getPhoneProvince(){
        return phoneProvince;
    }

    /**
    * 设置手机归属地省份
    * 
    * @param phoneProvince 要设置的手机归属地省份
    */
    public void setPhoneProvince(String phoneProvince){
        this.phoneProvince = phoneProvince;
    }

    /**
    * 获取手机归属地城市
    *
    * @return 手机归属地城市
    */
    public String getPhoneCity(){
        return phoneCity;
    }

    /**
    * 设置手机归属地城市
    * 
    * @param phoneCity 要设置的手机归属地城市
    */
    public void setPhoneCity(String phoneCity){
        this.phoneCity = phoneCity;
    }

    /**
    * 获取身份证号记录天数
    *
    * @return 身份证号记录天数
    */
    public Integer getRecordIdcardDays(){
        return recordIdcardDays;
    }

    /**
    * 设置身份证号记录天数
    * 
    * @param recordIdcardDays 要设置的身份证号记录天数
    */
    public void setRecordIdcardDays(Integer recordIdcardDays){
        this.recordIdcardDays = recordIdcardDays;
    }

    /**
    * 获取手机号记录天数
    *
    * @return 手机号记录天数
    */
    public Integer getRecordPhoneDays(){
        return recordPhoneDays;
    }

    /**
    * 设置手机号记录天数
    * 
    * @param recordPhoneDays 要设置的手机号记录天数
    */
    public void setRecordPhoneDays(Integer recordPhoneDays){
        this.recordPhoneDays = recordPhoneDays;
    }

    /**
    * 获取身份证最近出现时间
    *
    * @return 身份证最近出现时间
    */
    public String getLastAppearIdcard(){
        return lastAppearIdcard;
    }

    /**
    * 设置身份证最近出现时间
    * 
    * @param lastAppearIdcard 要设置的身份证最近出现时间
    */
    public void setLastAppearIdcard(String lastAppearIdcard){
        this.lastAppearIdcard = lastAppearIdcard;
    }

    /**
    * 获取手机号最近出现时间
    *
    * @return 手机号最近出现时间
    */
    public String getLastAppearPhone(){
        return lastAppearPhone;
    }

    /**
    * 设置手机号最近出现时间
    * 
    * @param lastAppearPhone 要设置的手机号最近出现时间
    */
    public void setLastAppearPhone(String lastAppearPhone){
        this.lastAppearPhone = lastAppearPhone;
    }

    /**
    * 获取关联身份证数量
    *
    * @return 关联身份证数量
    */
    public Integer getUsedIdcardsCnt(){
        return usedIdcardsCnt;
    }

    /**
    * 设置关联身份证数量
    * 
    * @param usedIdcardsCnt 要设置的关联身份证数量
    */
    public void setUsedIdcardsCnt(Integer usedIdcardsCnt){
        this.usedIdcardsCnt = usedIdcardsCnt;
    }

    /**
    * 获取关联手机号数量
    *
    * @return 关联手机号数量
    */
    public Integer getUsedPhonesCnt(){
        return usedPhonesCnt;
    }

    /**
    * 设置关联手机号数量
    * 
    * @param usedPhonesCnt 要设置的关联手机号数量
    */
    public void setUsedPhonesCnt(Integer usedPhonesCnt){
        this.usedPhonesCnt = usedPhonesCnt;
    }

}