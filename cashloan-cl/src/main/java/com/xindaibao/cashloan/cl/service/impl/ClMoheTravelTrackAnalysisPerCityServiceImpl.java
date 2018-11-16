package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheTravelTrackAnalysisPerCity;
import com.xindaibao.cashloan.cl.mapper.ClMoheTravelTrackAnalysisPerCityMapper;
import com.xindaibao.cashloan.cl.service.ClMoheTravelTrackAnalysisPerCityService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheTravelTrackAnalysisPerCityStatsService")
public class ClMoheTravelTrackAnalysisPerCityServiceImpl
        extends BaseServiceImpl<ClMoheTravelTrackAnalysisPerCity, Long> implements ClMoheTravelTrackAnalysisPerCityService {

    @Autowired private ClMoheTravelTrackAnalysisPerCityMapper clMoheTravelTrackAnalysisPerCityMapper;

    @Override
    public BaseMapper<ClMoheTravelTrackAnalysisPerCity, Long> getMapper() {
        return clMoheTravelTrackAnalysisPerCityMapper;
    }
}
