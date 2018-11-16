package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheEmergencyContact2Detail;
import com.xindaibao.cashloan.cl.mapper.ClMoheEmergencyContact2DetailMapper;
import com.xindaibao.cashloan.cl.service.ClMoheEmergencyContact2DetailService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheEmergencyContact2DetailStatsService")
public class ClMoheEmergencyContact2DetailServiceImpl
        extends BaseServiceImpl<ClMoheEmergencyContact2Detail, Long> implements ClMoheEmergencyContact2DetailService {

    @Autowired private ClMoheEmergencyContact2DetailMapper clMoheEmergencyContact2DetailMapper;

    @Override
    public BaseMapper<ClMoheEmergencyContact2Detail, Long> getMapper() {
        return clMoheEmergencyContact2DetailMapper;
    }
}
