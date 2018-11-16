package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheCarrierConsumptionStatsPerMonth;
import com.xindaibao.cashloan.cl.mapper.ClMoheCarrierConsumptionStatsPerMonthMapper;
import com.xindaibao.cashloan.cl.service.ClMoheCarrierConsumptionStatsPerMonthService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheCarrierConsumptionStatsPerMonthStatsService")
public class ClMoheCarrierConsumptionStatsPerMonthServiceImpl
        extends BaseServiceImpl<ClMoheCarrierConsumptionStatsPerMonth, Long> implements ClMoheCarrierConsumptionStatsPerMonthService {

    @Autowired private ClMoheCarrierConsumptionStatsPerMonthMapper clMoheCarrierConsumptionStatsPerMonthMapper;

    @Override
    public BaseMapper<ClMoheCarrierConsumptionStatsPerMonth, Long> getMapper() {
        return clMoheCarrierConsumptionStatsPerMonthMapper;
    }
}
