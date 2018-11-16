package com.xindaibao.cashloan.core.mapper;

import com.xindaibao.cashloan.core.model.KanyaUser;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.cashloan.core.model.KanyaUserLocation;

import java.util.List;
import java.util.Map;

@RDBatisDao
public interface KanyaUserMappers extends BaseMapper<KanyaUser, Long> {

    KanyaUserLocation getBaseModelByUserId(Long userId);

    List<KanyaUser> findByPrimary(Map<String, Object> params);

    int updateUserState(Map<String, Object> params);

    int selectRegisteredCount();
}