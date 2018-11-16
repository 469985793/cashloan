package com.xindaibao.cashloan.cl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 邀请记录实体
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-18 15:54:41


 * 

 */
 public class UserInvite implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 邀请时间
    */
    private Date inviteTime;

    /**
    * 被邀请人id
    */
    private Long inviteId;

    /**
    * 被邀请人名称
    */
    private String inviteName;

    /**
    * 邀请人id
    */
    private Long userId;

    /**
    * 邀请人名称
    */
    private String userName;


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
    * 获取邀请时间
    *
    * @return 邀请时间
    */
    public Date getInviteTime(){
    return inviteTime;
    }

    /**
    * 设置邀请时间
    * 
    * @param inviteTime 要设置的邀请时间
    */
    public void setInviteTime(Date inviteTime){
    this.inviteTime = inviteTime;
    }

    /**
    * 获取被邀请人id
    *
    * @return 被邀请人id
    */
    public Long getInviteId(){
    return inviteId;
    }

    /**
    * 设置被邀请人id
    * 
    * @param inviteId 要设置的被邀请人id
    */
    public void setInviteId(Long inviteId){
    this.inviteId = inviteId;
    }

    /**
    * 获取被邀请人名称
    *
    * @return 被邀请人名称
    */
    public String getInviteName(){
    return inviteName;
    }

    /**
    * 设置被邀请人名称
    * 
    * @param inviteName 要设置的被邀请人名称
    */
    public void setInviteName(String inviteName){
    this.inviteName = inviteName;
    }

    /**
    * 获取邀请人id
    *
    * @return 邀请人id
    */
    public Long getUserId(){
    return userId;
    }

    /**
    * 设置邀请人id
    * 
    * @param userId 要设置的邀请人id
    */
    public void setUserId(Long userId){
    this.userId = userId;
    }

    /**
    * 获取邀请人名称
    *
    * @return 邀请人名称
    */
    public String getUserName(){
    return userName;
    }

    /**
    * 设置邀请人名称
    * 
    * @param userName 要设置的邀请人名称
    */
    public void setUserName(String userName){
    this.userName = userName;
    }

}