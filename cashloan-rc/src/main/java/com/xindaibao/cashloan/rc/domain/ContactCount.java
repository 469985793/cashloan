package com.xindaibao.cashloan.rc.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 通讯录统计实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-10 15:04:13


 * 

 */
 public class ContactCount implements Serializable {

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
    * 通讯录总条数
    */
    private Integer count;

    /**
    * 借款未逾期人数
    */
    private Integer countOne;

    /**
    * 逾期未还借款人数
    */
    private Integer countTwo;

    /**
    * 逾期90天(M3)已还借款人数
    */
    private Integer countThree;

    /**
    * 逾期30天(M1)以上已还借款人数
    */
    private Integer countFour;

    /**
    * 逾期30天(M1)以内已还借款人数
    */
    private Integer countFive;

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
    * 获取通讯录总条数
    *
    * @return 通讯录总条数
    */
    public Integer getCount(){
    return count;
    }

    /**
    * 设置通讯录总条数
    * 
    * @param count 要设置的通讯录总条数
    */
    public void setCount(Integer count){
    this.count = count;
    }

    /**
    * 获取借款未逾期人数
    *
    * @return 借款未逾期人数
    */
    public Integer getCountOne(){
    return countOne;
    }

    /**
    * 设置借款未逾期人数
    * 
    * @param countOne 要设置的借款未逾期人数
    */
    public void setCountOne(Integer countOne){
    this.countOne = countOne;
    }

    /**
    * 获取逾期未还借款人数
    *
    * @return 逾期未还借款人数
    */
    public Integer getCountTwo(){
    return countTwo;
    }

    /**
    * 设置逾期未还借款人数
    * 
    * @param countTwo 要设置的逾期未还借款人数
    */
    public void setCountTwo(Integer countTwo){
    this.countTwo = countTwo;
    }

    /**
    * 获取逾期90天(M3)已还借款人数
    *
    * @return 逾期90天(M3)已还借款人数
    */
    public Integer getCountThree(){
    return countThree;
    }

    /**
    * 设置逾期90天(M3)已还借款人数
    * 
    * @param countThree 要设置的逾期90天(M3)已还借款人数
    */
    public void setCountThree(Integer countThree){
    this.countThree = countThree;
    }

    /**
    * 获取逾期30天(M1)以上已还借款人数
    *
    * @return 逾期30天(M1)以上已还借款人数
    */
    public Integer getCountFour(){
    return countFour;
    }

    /**
    * 设置逾期30天(M1)以上已还借款人数
    * 
    * @param countFour 要设置的逾期30天(M1)以上已还借款人数
    */
    public void setCountFour(Integer countFour){
    this.countFour = countFour;
    }

    /**
    * 获取逾期30天(M1)以内已还借款人数
    *
    * @return 逾期30天(M1)以内已还借款人数
    */
    public Integer getCountFive(){
    return countFive;
    }

    /**
    * 设置逾期30天(M1)以内已还借款人数
    * 
    * @param countFive 要设置的逾期30天(M1)以内已还借款人数
    */
    public void setCountFive(Integer countFive){
    this.countFive = countFive;
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