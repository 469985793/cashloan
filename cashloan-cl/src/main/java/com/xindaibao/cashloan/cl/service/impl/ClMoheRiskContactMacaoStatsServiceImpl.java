package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheRiskContactMacaoStats;
import com.xindaibao.cashloan.cl.domain.ClMoheRiskContactStats;
import com.xindaibao.cashloan.cl.mapper.ClMoheRiskContactMacaoStatsMapper;
import com.xindaibao.cashloan.cl.mapper.ClMoheRiskContactStatsMapper;
import com.xindaibao.cashloan.cl.service.ClMoheRiskContactMacaoStatsService;
import com.xindaibao.cashloan.cl.service.ClMoheRiskContactStatsService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheRiskContactMacaoStatsService")
public class ClMoheRiskContactMacaoStatsServiceImpl
        extends BaseServiceImpl<ClMoheRiskContactMacaoStats, Long> implements ClMoheRiskContactMacaoStatsService {

    @Autowired private ClMoheRiskContactMacaoStatsMapper clMoheRiskContactStatsMapper;

    @Override
    public BaseMapper<ClMoheRiskContactMacaoStats, Long> getMapper() {
        return clMoheRiskContactStatsMapper;
    }
}
