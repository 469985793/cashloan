package com.xindaibao.cashloan.rc.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 借款统计实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-10 14:59:59


 * 

 */
 public class BorrowCount implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户标识
    */
    private Long userId;

    /**
    * 借款总次数
    */
    private Integer count;

    /**
    * 借款不通过总次数
    */
    private Integer failCount;

    /**
    * 当日借款不通过总次数
    */
    private Integer dayFailCount;

    /**
    * 借款人有逾期未还借款数
    */
    private Integer countOne;

    /**
    * 借款人有逾期90天(M3)以上已还借款数
    */
    private Integer countTwo;

    /**
    * 借款人有逾期30天(M1)以上,90天以内已还借款数
    */
    private Integer countThree;

    /**
    * 借款人有逾期30天(M1)以内已还借款数
    */
    private Integer countFour;

    /**
    * 紧急联系人有逾期未还借款数
    */
    private Integer countFive;

    /**
    * 紧急联系人有逾期90天(M3)以上已还借款数
    */
    private Integer countSix;

    /**
    * 紧急联系人有逾期30天(M1)以上90天以内已还借款
    */
    private Integer countSeven;

    /**
    * 紧急联系人有逾期30天(M1)以内已还借款
    */
    private Integer countEight;

    /**
    * 创建时间
    */
    private Date createTime;


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
    * 获取用户标识
    *
    * @return 用户标识
    */
    public Long getUserId(){
    return userId;
    }

    /**
    * 设置用户标识
    * 
    * @param userId 要设置的用户标识
    */
    public void setUserId(Long userId){
    this.userId = userId;
    }

    /**
    * 获取借款总次数
    *
    * @return 借款总次数
    */
    public Integer getCount(){
    return count;
    }

    /**
    * 设置借款总次数
    * 
    * @param count 要设置的借款总次数
    */
    public void setCount(Integer count){
    this.count = count;
    }

    /**
    * 获取借款不通过总次数
    *
    * @return 借款不通过总次数
    */
    public Integer getFailCount(){
    return failCount;
    }

    /**
    * 设置借款不通过总次数
    * 
    * @param failCount 要设置的借款不通过总次数
    */
    public void setFailCount(Integer failCount){
    this.failCount = failCount;
    }

    /**
    * 获取当日借款不通过总次数
    *
    * @return 当日借款不通过总次数
    */
    public Integer getDayFailCount(){
    return dayFailCount;
    }

    /**
    * 设置当日借款不通过总次数
    * 
    * @param dayFailCount 要设置的当日借款不通过总次数
    */
    public void setDayFailCount(Integer dayFailCount){
    this.dayFailCount = dayFailCount;
    }

    /**
    * 获取借款人有逾期未还借款数
    *
    * @return 借款人有逾期未还借款数
    */
    public Integer getCountOne(){
    return countOne;
    }

    /**
    * 设置借款人有逾期未还借款数
    * 
    * @param countOne 要设置的借款人有逾期未还借款数
    */
    public void setCountOne(Integer countOne){
    this.countOne = countOne;
    }

    /**
    * 获取借款人有逾期90天(M3)以上已还借款数
    *
    * @return 借款人有逾期90天(M3)以上已还借款数
    */
    public Integer getCountTwo(){
    return countTwo;
    }

    /**
    * 设置借款人有逾期90天(M3)以上已还借款数
    * 
    * @param countTwo 要设置的借款人有逾期90天(M3)以上已还借款数
    */
    public void setCountTwo(Integer countTwo){
    this.countTwo = countTwo;
    }

    /**
    * 获取借款人有逾期30天(M1)以上,90天以内已还借款数
    *
    * @return 借款人有逾期30天(M1)以上,90天以内已还借款数
    */
    public Integer getCountThree(){
    return countThree;
    }

    /**
    * 设置借款人有逾期30天(M1)以上,90天以内已还借款数
    * 
    * @param countThree 要设置的借款人有逾期30天(M1)以上,90天以内已还借款数
    */
    public void setCountThree(Integer countThree){
    this.countThree = countThree;
    }

    /**
    * 获取借款人有逾期30天(M1)以内已还借款数
    *
    * @return 借款人有逾期30天(M1)以内已还借款数
    */
    public Integer getCountFour(){
    return countFour;
    }

    /**
    * 设置借款人有逾期30天(M1)以内已还借款数
    * 
    * @param countFour 要设置的借款人有逾期30天(M1)以内已还借款数
    */
    public void setCountFour(Integer countFour){
    this.countFour = countFour;
    }

    /**
    * 获取紧急联系人有逾期未还借款数
    *
    * @return 紧急联系人有逾期未还借款数
    */
    public Integer getCountFive(){
    return countFive;
    }

    /**
    * 设置紧急联系人有逾期未还借款数
    * 
    * @param countFive 要设置的紧急联系人有逾期未还借款数
    */
    public void setCountFive(Integer countFive){
    this.countFive = countFive;
    }

    /**
    * 获取紧急联系人有逾期90天(M3)以上已还借款数
    *
    * @return 紧急联系人有逾期90天(M3)以上已还借款数
    */
    public Integer getCountSix(){
    return countSix;
    }

    /**
    * 设置紧急联系人有逾期90天(M3)以上已还借款数
    * 
    * @param countSix 要设置的紧急联系人有逾期90天(M3)以上已还借款数
    */
    public void setCountSix(Integer countSix){
    this.countSix = countSix;
    }

    /**
    * 获取紧急联系人有逾期30天(M1)以上90天以内已还借款
    *
    * @return 紧急联系人有逾期30天(M1)以上90天以内已还借款
    */
    public Integer getCountSeven(){
    return countSeven;
    }

    /**
    * 设置紧急联系人有逾期30天(M1)以上90天以内已还借款
    * 
    * @param countSeven 要设置的紧急联系人有逾期30天(M1)以上90天以内已还借款
    */
    public void setCountSeven(Integer countSeven){
    this.countSeven = countSeven;
    }

    /**
    * 获取紧急联系人有逾期30天(M1)以内已还借款
    *
    * @return 紧急联系人有逾期30天(M1)以内已还借款
    */
    public Integer getCountEight(){
    return countEight;
    }

    /**
    * 设置紧急联系人有逾期30天(M1)以内已还借款
    * 
    * @param countEight 要设置的紧急联系人有逾期30天(M1)以内已还借款
    */
    public void setCountEight(Integer countEight){
    this.countEight = countEight;
    }

    /**
    * 获取创建时间
    *
    * @return 创建时间
    */
    public Date getCreateTime(){
    return createTime;
    }

    /**
    * 设置创建时间
    * 
    * @param createTime 要设置的创建时间
    */
    public void setCreateTime(Date createTime){
    this.createTime = createTime;
    }

}