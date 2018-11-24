package com.xindaibao.cashloan.cl.mapper;

import com.xindaibao.cashloan.cl.model.kenya.KanyaUserState;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

@RDBatisDao
public interface KanyaUserStateMapper extends BaseMapper<KanyaUserState, Long> {
    void updateCurrentState(KanyaUserState kanyaUserState);
}