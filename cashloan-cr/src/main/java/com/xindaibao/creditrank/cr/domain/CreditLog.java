package com.xindaibao.creditrank.cr.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 授信额度记录实体
 * 
 * @author
 * @version 1.0.0
 * @date 2016-12-16 10:31:23


 * 

 */
 public class CreditLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 授信额度个人唯一标识
    */
    private String consumerNo;

    /**
    * 变动前额度
    */
    private Double pre;

    /**
    * 变动后额度
    */
    private Double now;

    /**
    * 变动额度
    */
    private Double modifyTotal;

    /**
    * 变动时间
    */
    private Date modifyTime;

    /**
    * 变动操作人
    */
    private String modifyUser;

    /**
    * 变动类型 10-增加 20-减少 30-冻结 40-解冻
    */
    private String type;

    /**
    * 额度类型
    */
    private long creditType;

    /**
    * 备注
    */
    private String remark;

    /**
    * 扩展字段
    */
    private String reqExt;


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
    * 获取授信额度个人唯一标识
    *
    * @return 授信额度个人唯一标识
    */
    public String getConsumerNo(){
    return consumerNo;
    }

    /**
    * 设置授信额度个人唯一标识
    * 
    * @param consumerNo 要设置的授信额度个人唯一标识
    */
    public void setConsumerNo(String consumerNo){
    this.consumerNo = consumerNo;
    }

    /**
    * 获取变动前额度
    *
    * @return 变动前额度
    */
    public Double getPre(){
    return pre;
    }

    /**
    * 设置变动前额度
    * 
    * @param pre 要设置的变动前额度
    */
    public void setPre(Double pre){
    this.pre = pre;
    }

    /**
    * 获取变动后额度
    *
    * @return 变动后额度
    */
    public Double getNow(){
    return now;
    }

    /**
    * 设置变动后额度
    * 
    * @param now 要设置的变动后额度
    */
    public void setNow(Double now){
    this.now = now;
    }

    /**
    * 获取变动额度
    *
    * @return 变动额度
    */
    public Double getModifyTotal(){
    return modifyTotal;
    }

    /**
    * 设置变动额度
    * 
    * @param modifyTotal 要设置的变动额度
    */
    public void setModifyTotal(Double modifyTotal){
    this.modifyTotal = modifyTotal;
    }

    /**
    * 获取变动时间
    *
    * @return 变动时间
    */
    public Date getModifyTime(){
    return modifyTime;
    }

    /**
    * 设置变动时间
    * 
    * @param modifyTime 要设置的变动时间
    */
    public void setModifyTime(Date modifyTime){
    this.modifyTime = modifyTime;
    }

    /**
    * 获取变动操作人
    *
    * @return 变动操作人
    */
    public String getModifyUser(){
    return modifyUser;
    }

    /**
    * 设置变动操作人
    * 
    * @param modifyUser 要设置的变动操作人
    */
    public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    }

    /**
    * 获取变动类型 10-增加 20-减少 30-冻结 40-解冻
    *
    * @return 变动类型 10-增加 20-减少 30-冻结 40-解冻
    */
    public String getType(){
    return type;
    }

    /**
    * 设置变动类型 10-增加 20-减少 30-冻结 40-解冻
    * 
    * @param type 要设置的变动类型 10-增加 20-减少 30-冻结 40-解冻
    */
    public void setType(String type){
    this.type = type;
    }

    /**
    * 获取额度类型
    *
    * @return 额度类型
    */
    public long getCreditType(){
    return creditType;
    }

    /**
    * 设置额度类型
    * 
    * @param trend 要设置的额度类型
    */
    public void setCreditType(long creditType){
    this.creditType = creditType;
    }

    /**
    * 获取备注
    *
    * @return 备注
    */
    public String getRemark(){
    return remark;
    }

    /**
    * 设置备注
    * 
    * @param remark 要设置的备注
    */
    public void setRemark(String remark){
    this.remark = remark;
    }

    /**
    * 获取扩展字段
    *
    * @return 扩展字段
    */
    public String getReqExt(){
    return reqExt;
    }

    /**
    * 设置扩展字段
    * 
    * @param reqExt 要设置的扩展字段
    */
    public void setReqExt(String reqExt){
    this.reqExt = reqExt;
    }

}