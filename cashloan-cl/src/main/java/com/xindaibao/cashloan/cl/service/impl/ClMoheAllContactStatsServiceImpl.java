package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheAllContactStats;
import com.xindaibao.cashloan.cl.mapper.ClMoheAllContactStatsMapper;
import com.xindaibao.cashloan.cl.service.ClMoheAllContactStatsService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheAllContactStatsStatsService")
public class ClMoheAllContactStatsServiceImpl
        extends BaseServiceImpl<ClMoheAllContactStats, Long> implements ClMoheAllContactStatsService {

    @Autowired private ClMoheAllContactStatsMapper clMoheAllContactStatsMapper;

    @Override
    public BaseMapper<ClMoheAllContactStats, Long> getMapper() {
        return clMoheAllContactStatsMapper;
    }
}
