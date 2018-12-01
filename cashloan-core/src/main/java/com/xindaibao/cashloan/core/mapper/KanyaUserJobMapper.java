package com.xindaibao.cashloan.core.mapper;

import com.xindaibao.cashloan.core.model.KanyaUserJob;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

import java.util.List;

@RDBatisDao
public interface KanyaUserJobMapper extends BaseMapper<KanyaUserJob, Long> {
    int saveUsersJob(List<KanyaUserJob> list);
}