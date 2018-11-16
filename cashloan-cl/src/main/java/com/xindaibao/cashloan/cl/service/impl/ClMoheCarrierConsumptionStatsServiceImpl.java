package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheCarrierConsumptionStats;
import com.xindaibao.cashloan.cl.mapper.ClMoheCarrierConsumptionStatsMapper;
import com.xindaibao.cashloan.cl.service.ClMoheCarrierConsumptionStatsService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheCarrierConsumptionStatsStatsService")
public class ClMoheCarrierConsumptionStatsServiceImpl
        extends BaseServiceImpl<ClMoheCarrierConsumptionStats, Long> implements ClMoheCarrierConsumptionStatsService {

    @Autowired private ClMoheCarrierConsumptionStatsMapper clMoheCarrierConsumptionStatsMapper;

    @Override
    public BaseMapper<ClMoheCarrierConsumptionStats, Long> getMapper() {
        return clMoheCarrierConsumptionStatsMapper;
    }
}
