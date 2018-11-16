package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheEmergencyContact5Detail;
import com.xindaibao.cashloan.cl.mapper.ClMoheEmergencyContact5DetailMapper;
import com.xindaibao.cashloan.cl.service.ClMoheEmergencyContact5DetailService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheEmergencyContact5DetailStatsService")
public class ClMoheEmergencyContact5DetailServiceImpl
        extends BaseServiceImpl<ClMoheEmergencyContact5Detail, Long> implements ClMoheEmergencyContact5DetailService {

    @Autowired private ClMoheEmergencyContact5DetailMapper clMoheEmergencyContact5DetailMapper;

    @Override
    public BaseMapper<ClMoheEmergencyContact5Detail, Long> getMapper() {
        return clMoheEmergencyContact5DetailMapper;
    }
}
