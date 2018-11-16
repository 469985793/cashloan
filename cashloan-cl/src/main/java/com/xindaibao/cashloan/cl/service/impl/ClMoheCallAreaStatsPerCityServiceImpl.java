package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheCallAreaStatsPerCity;
import com.xindaibao.cashloan.cl.mapper.ClMoheCallAreaStatsPerCityMapper;
import com.xindaibao.cashloan.cl.service.ClMoheCallAreaStatsPerCityService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheCallAreaStatsPerCityStatsService")
public class ClMoheCallAreaStatsPerCityServiceImpl
        extends BaseServiceImpl<ClMoheCallAreaStatsPerCity, Long> implements ClMoheCallAreaStatsPerCityService {

    @Autowired private ClMoheCallAreaStatsPerCityMapper clMoheCallAreaStatsPerCityMapper;

    @Override
    public BaseMapper<ClMoheCallAreaStatsPerCity, Long> getMapper() {
        return clMoheCallAreaStatsPerCityMapper;
    }
}
