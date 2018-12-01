package com.xindaibao.cashloan.core.mapper;

import com.xindaibao.cashloan.core.model.KanyaUserLive;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

import java.util.List;

@RDBatisDao
public interface KanyaUserLiveMapper extends BaseMapper<KanyaUserLive, Long> {
    int saveUsersLive(List<KanyaUserLive> list);
}