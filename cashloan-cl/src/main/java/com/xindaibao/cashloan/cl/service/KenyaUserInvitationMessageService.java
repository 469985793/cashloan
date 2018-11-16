package com.xindaibao.cashloan.cl.service;
;

import com.xindaibao.cashloan.core.model.KanyaUser;

import java.util.List;

public interface KenyaUserInvitationMessageService {
    List<KanyaUser> queryInvited(String inviteCode);//邀请人信息查询
}
