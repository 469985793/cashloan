package com.xindaibao.cashloan.core.mapper;

import com.xindaibao.cashloan.core.model.KanyaUserInfo;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

import java.util.List;

@RDBatisDao
public interface KanyaUserInfoMapper extends BaseMapper<KanyaUserInfo, Long> {
    //添加用户基本信息
    int save(KanyaUserInfo kanyaUserInfo);
    //查询身份ID是否重复
    KanyaUserInfo selectByNationId(String selectByNationId);
    int saveUsersInfo(List<KanyaUserInfo> list);
    KanyaUserInfo findByUid(Long uid);
}