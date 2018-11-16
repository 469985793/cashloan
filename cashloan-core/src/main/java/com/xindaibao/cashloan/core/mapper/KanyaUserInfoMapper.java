package com.xindaibao.cashloan.core.mapper;

import com.xindaibao.cashloan.core.model.KanyaUserInfo;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

@RDBatisDao
public interface KanyaUserInfoMapper extends BaseMapper<KanyaUserInfo, Long> {
    //添加用户基本信息
    int save(KanyaUserInfo kanyaUserInfo);
}