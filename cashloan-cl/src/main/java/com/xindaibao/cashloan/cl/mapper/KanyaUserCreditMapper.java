package com.xindaibao.cashloan.cl.mapper;

import com.xindaibao.cashloan.cl.model.kenya.KanyaUserCredit;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

import java.util.List;

@RDBatisDao
public interface KanyaUserCreditMapper extends BaseMapper<KanyaUserCredit, Long> {
    int saveUsersCredit(List<KanyaUserCredit> list);
}