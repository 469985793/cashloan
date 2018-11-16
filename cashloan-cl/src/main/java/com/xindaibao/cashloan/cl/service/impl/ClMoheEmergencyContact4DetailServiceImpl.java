package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheEmergencyContact4Detail;
import com.xindaibao.cashloan.cl.mapper.ClMoheEmergencyContact4DetailMapper;
import com.xindaibao.cashloan.cl.service.ClMoheEmergencyContact4DetailService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheEmergencyContact4DetailStatsService")
public class ClMoheEmergencyContact4DetailServiceImpl
        extends BaseServiceImpl<ClMoheEmergencyContact4Detail, Long> implements ClMoheEmergencyContact4DetailService {

    @Autowired private ClMoheEmergencyContact4DetailMapper clMoheEmergencyContact4DetailMapper;

    @Override
    public BaseMapper<ClMoheEmergencyContact4Detail, Long> getMapper() {
        return clMoheEmergencyContact4DetailMapper;
    }
}
