package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.service.KenyaUserInvitationMessageService;
import com.xindaibao.cashloan.core.mapper.KanyaUserMapper;
import com.xindaibao.cashloan.core.model.KanyaUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class KenyaUserInvitationMessageServiceImpl implements KenyaUserInvitationMessageService {
    @Resource
    private KanyaUserMapper kanyaUserMapper;


    @Override
    public List<KanyaUser> queryInvited(String inviteCode) {
        List<KanyaUser> kanyaUsers = kanyaUserMapper.queryInvited(inviteCode);
        return kanyaUsers;
    }

}
