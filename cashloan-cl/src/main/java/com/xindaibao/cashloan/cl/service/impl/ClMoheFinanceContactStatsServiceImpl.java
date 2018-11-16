package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheFinanceContactStats;
import com.xindaibao.cashloan.cl.mapper.ClMoheFinanceContactStatsMapper;
import com.xindaibao.cashloan.cl.service.ClMoheFinanceContactStatsService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheFinanceContactStatsStatsService")
public class ClMoheFinanceContactStatsServiceImpl
        extends BaseServiceImpl<ClMoheFinanceContactStats, Long> implements ClMoheFinanceContactStatsService {

    @Autowired private ClMoheFinanceContactStatsMapper clMoheFinanceContactStatsMapper;

    @Override
    public BaseMapper<ClMoheFinanceContactStats, Long> getMapper() {
        return clMoheFinanceContactStatsMapper;
    }
}
