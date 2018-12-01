package com.xindaibao.cashloan.cl.mapper;

import com.xindaibao.cashloan.cl.model.kenya.KanyaUserObtainState;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

import java.util.List;

@RDBatisDao
public interface KanyaUserObtainStateMapper extends BaseMapper<KanyaUserObtainState, Long> {
    int saveUsersObtainState(List<KanyaUserObtainState> list);
}