package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheRiskContactSmallLoanStats;
import com.xindaibao.cashloan.cl.mapper.ClMoheRiskContactSmallLoanStatsMapper;
import com.xindaibao.cashloan.cl.service.ClMoheRiskContactSmallLoanStatsService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheRiskContactSmallLoanStatsService")
public class ClMoheRiskContactSmallLoanStatsServiceImpl
        extends BaseServiceImpl<ClMoheRiskContactSmallLoanStats, Long> implements ClMoheRiskContactSmallLoanStatsService {

    @Autowired
    private ClMoheRiskContactSmallLoanStatsMapper clMoheRiskContactSmallLoanStatsMapper;

    @Override
    public BaseMapper<ClMoheRiskContactSmallLoanStats, Long> getMapper() {
        return clMoheRiskContactSmallLoanStatsMapper;
    }
}
