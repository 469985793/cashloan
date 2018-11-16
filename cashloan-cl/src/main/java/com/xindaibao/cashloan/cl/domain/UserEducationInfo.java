package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;

/**
 * 学信查询记录表实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-04-18 16:30:45




 */
 public class UserEducationInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 教育类别
    */
    private String educationType;

    /**
    * 专业
    */
    private String profession;

    /**
    * 毕业学校
    */
    private String graduateSchool;

    /**
    * 入学时间
    */
    private String matriculationTime;

    /**
    * 毕业时间
    */
    private String graduationTime;

    /**
    * 教育情况
    */
    private String graduationConclusion;

    /**
    * 学位
    */
    private String educationBackground;

    /**
    * 是否匹配 10 - 匹配 20 - 不匹配
    */
    private String state;


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
    * 获取教育类别
    *
    * @return 教育类别
    */
    public String getEducationType(){
    return educationType;
    }

    /**
    * 设置教育类别
    * 
    * @param educationType 要设置的教育类别
    */
    public void setEducationType(String educationType){
    this.educationType = educationType;
    }

    /**
    * 获取专业
    *
    * @return 专业
    */
    public String getProfession(){
    return profession;
    }

    /**
    * 设置专业
    * 
    * @param profession 要设置的专业
    */
    public void setProfession(String profession){
    this.profession = profession;
    }

    /**
    * 获取毕业学校
    *
    * @return 毕业学校
    */
    public String getGraduateSchool(){
    return graduateSchool;
    }

    /**
    * 设置毕业学校
    * 
    * @param graduateSchool 要设置的毕业学校
    */
    public void setGraduateSchool(String graduateSchool){
    this.graduateSchool = graduateSchool;
    }

    /**
    * 获取入学时间
    *
    * @return 入学时间
    */
    public String getMatriculationTime(){
    return matriculationTime;
    }

    /**
    * 设置入学时间
    * 
    * @param matriculationTime 要设置的入学时间
    */
    public void setMatriculationTime(String matriculationTime){
    this.matriculationTime = matriculationTime;
    }

    /**
    * 获取毕业时间
    *
    * @return 毕业时间
    */
    public String getGraduationTime(){
    return graduationTime;
    }

    /**
    * 设置毕业时间
    * 
    * @param graduationTime 要设置的毕业时间
    */
    public void setGraduationTime(String graduationTime){
    this.graduationTime = graduationTime;
    }

    /**
    * 获取教育情况
    *
    * @return 教育情况
    */
    public String getGraduationConclusion(){
    return graduationConclusion;
    }

    /**
    * 设置教育情况
    * 
    * @param graduationConclusion 要设置的教育情况
    */
    public void setGraduationConclusion(String graduationConclusion){
    this.graduationConclusion = graduationConclusion;
    }

    /**
    * 获取学位
    *
    * @return 学位
    */
    public String getEducationBackground(){
    return educationBackground;
    }

    /**
    * 设置学位
    * 
    * @param educationBackground 要设置的学位
    */
    public void setEducationBackground(String educationBackground){
    this.educationBackground = educationBackground;
    }

    /**
    * 获取是否匹配 10 - 匹配 20 - 不匹配
    *
    * @return 是否匹配 10 - 匹配 20 - 不匹配
    */
    public String getState(){
    return state;
    }

    /**
    * 设置是否匹配 10 - 匹配 20 - 不匹配
    * 
    * @param state 要设置的是否匹配 10 - 匹配 20 - 不匹配
    */
    public void setState(String state){
    this.state = state;
    }

}