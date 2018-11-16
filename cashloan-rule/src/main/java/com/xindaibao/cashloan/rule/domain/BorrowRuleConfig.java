package com.xindaibao.cashloan.rule.domain;

import java.io.Serializable;

/**
 * 借款规则详细配置表实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-04-21 15:23:19




 */
 public class BorrowRuleConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 借款规则配置id
    */
    private Long borrowRuleId;

    /**
    * 规则id
    */
    private Long ruleId;

    /**
    * 规则执行顺序
    */
    private Integer ruleSort;

    /**
    * 规则配置id
    */
    private Long configId;

    /**
    * 配置执行顺序
    */
    private Integer configSort;


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
    * 获取借款规则配置id
    *
    * @return 借款规则配置id
    */
    public Long getBorrowRuleId(){
    return borrowRuleId;
    }

    /**
    * 设置借款规则配置id
    * 
    * @param borrowRuleId 要设置的借款规则配置id
    */
    public void setBorrowRuleId(Long borrowRuleId){
    this.borrowRuleId = borrowRuleId;
    }

    /**
    * 获取规则id
    *
    * @return 规则id
    */
    public Long getRuleId(){
    return ruleId;
    }

    /**
    * 设置规则id
    * 
    * @param ruleId 要设置的规则id
    */
    public void setRuleId(Long ruleId){
    this.ruleId = ruleId;
    }

    /**
    * 获取规则执行顺序
    *
    * @return 规则执行顺序
    */
    public Integer getRuleSort(){
    return ruleSort;
    }

    /**
    * 设置规则执行顺序
    * 
    * @param ruleSort 要设置的规则执行顺序
    */
    public void setRuleSort(Integer ruleSort){
    this.ruleSort = ruleSort;
    }

    /**
    * 获取规则配置id
    *
    * @return 规则配置id
    */
    public Long getConfigId(){
    return configId;
    }

    /**
    * 设置规则配置id
    * 
    * @param configId 要设置的规则配置id
    */
    public void setConfigId(Long configId){
    this.configId = configId;
    }

    /**
    * 获取配置执行顺序
    *
    * @return 配置执行顺序
    */
    public Integer getConfigSort(){
    return configSort;
    }

    /**
    * 设置配置执行顺序
    * 
    * @param configSort 要设置的配置执行顺序
    */
    public void setConfigSort(Integer configSort){
    this.configSort = configSort;
    }

}