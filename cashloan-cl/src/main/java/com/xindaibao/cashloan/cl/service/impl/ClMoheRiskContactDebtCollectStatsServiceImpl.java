package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheRiskContactDebtCollectStats;
import com.xindaibao.cashloan.cl.mapper.ClMoheRiskContactDebtCollectStatsMapper;
import com.xindaibao.cashloan.cl.service.ClMoheRiskContactDebtCollectStatsService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheRiskContactDebtCollectStatsService")
public class ClMoheRiskContactDebtCollectStatsServiceImpl
        extends BaseServiceImpl<ClMoheRiskContactDebtCollectStats, Long> implements ClMoheRiskContactDebtCollectStatsService {

    @Autowired
    private ClMoheRiskContactDebtCollectStatsMapper clMoheRiskContactDebtCollectStatsMapper;

    @Override
    public BaseMapper<ClMoheRiskContactDebtCollectStats, Long> getMapper() {
        return clMoheRiskContactDebtCollectStatsMapper;
    }
}
