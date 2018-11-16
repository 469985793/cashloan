package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheActiveSilenceStats;
import com.xindaibao.cashloan.cl.mapper.ClMoheActiveSilenceStatsMapper;
import com.xindaibao.cashloan.cl.service.ClMoheActiveSilenceStatsService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheActiveSilenceStatsService")
public class ClMoheActiveSilenceStatsServiceImpl
        extends BaseServiceImpl<ClMoheActiveSilenceStats, Long> implements ClMoheActiveSilenceStatsService {

    @Autowired private ClMoheActiveSilenceStatsMapper clMoheActiveSilenceStatsMapper;

    @Override
    public BaseMapper<ClMoheActiveSilenceStats, Long> getMapper() {
        return clMoheActiveSilenceStatsMapper;
    }
}
