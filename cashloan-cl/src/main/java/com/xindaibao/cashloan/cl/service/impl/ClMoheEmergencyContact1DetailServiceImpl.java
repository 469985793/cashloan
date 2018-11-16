package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheEmergencyContact1Detail;
import com.xindaibao.cashloan.cl.mapper.ClMoheEmergencyContact1DetailMapper;
import com.xindaibao.cashloan.cl.service.ClMoheEmergencyContact1DetailService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheEmergencyContact1DetailStatsService")
public class ClMoheEmergencyContact1DetailServiceImpl
        extends BaseServiceImpl<ClMoheEmergencyContact1Detail, Long> implements ClMoheEmergencyContact1DetailService {

    @Autowired private ClMoheEmergencyContact1DetailMapper clMoheEmergencyContact1DetailMapper;

    @Override
    public BaseMapper<ClMoheEmergencyContact1Detail, Long> getMapper() {
        return clMoheEmergencyContact1DetailMapper;
    }
}
