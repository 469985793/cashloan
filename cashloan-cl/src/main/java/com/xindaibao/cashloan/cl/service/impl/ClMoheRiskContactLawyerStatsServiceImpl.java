package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheRiskContactLawyerStats;
import com.xindaibao.cashloan.cl.domain.ClMoheRiskContactStats;
import com.xindaibao.cashloan.cl.mapper.ClMoheRiskContactLawyerStatsMapper;
import com.xindaibao.cashloan.cl.service.ClMoheRiskContactLawyerStatsService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheRiskContactLawyerStatsService")
public class ClMoheRiskContactLawyerStatsServiceImpl
        extends BaseServiceImpl<ClMoheRiskContactLawyerStats, Long> implements ClMoheRiskContactLawyerStatsService {

    @Autowired private ClMoheRiskContactLawyerStatsMapper clMoheRiskContactLawyerStatsMapper;

    @Override
    public BaseMapper<ClMoheRiskContactLawyerStats, Long> getMapper() {
        return clMoheRiskContactLawyerStatsMapper;
    }
}
