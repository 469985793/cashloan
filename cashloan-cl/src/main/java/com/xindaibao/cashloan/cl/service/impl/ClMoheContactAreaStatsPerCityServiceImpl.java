package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheContactAreaStatsPerCity;
import com.xindaibao.cashloan.cl.mapper.ClMoheContactAreaStatsPerCityMapper;
import com.xindaibao.cashloan.cl.service.ClMoheContactAreaStatsPerCityService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheContactAreaStatsPerCityStatsService")
public class ClMoheContactAreaStatsPerCityServiceImpl
        extends BaseServiceImpl<ClMoheContactAreaStatsPerCity, Long> implements ClMoheContactAreaStatsPerCityService {

    @Autowired private ClMoheContactAreaStatsPerCityMapper clMoheContactAreaStatsPerCityMapper;

    @Override
    public BaseMapper<ClMoheContactAreaStatsPerCity, Long> getMapper() {
        return clMoheContactAreaStatsPerCityMapper;
    }
}
