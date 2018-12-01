package com.xindaibao.cashloan.core.mapper;

import com.xindaibao.cashloan.core.model.KanyaUserContactInfo;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

import java.util.List;

@RDBatisDao
public interface KanyaUserContactInfoMapper extends BaseMapper<KanyaUserContactInfo, Long> {
    int saveUsersContactInfo(List<KanyaUserContactInfo> list);
}