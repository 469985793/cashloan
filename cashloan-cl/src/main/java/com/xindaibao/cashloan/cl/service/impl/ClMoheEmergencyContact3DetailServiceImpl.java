package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheEmergencyContact3Detail;
import com.xindaibao.cashloan.cl.mapper.ClMoheEmergencyContact3DetailMapper;
import com.xindaibao.cashloan.cl.service.ClMoheEmergencyContact3DetailService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheEmergencyContact3DetailStatsService")
public class ClMoheEmergencyContact3DetailServiceImpl
        extends BaseServiceImpl<ClMoheEmergencyContact3Detail, Long> implements ClMoheEmergencyContact3DetailService {

    @Autowired private ClMoheEmergencyContact3DetailMapper clMoheEmergencyContact3DetailMapper;

    @Override
    public BaseMapper<ClMoheEmergencyContact3Detail, Long> getMapper() {
        return clMoheEmergencyContact3DetailMapper;
    }
}
