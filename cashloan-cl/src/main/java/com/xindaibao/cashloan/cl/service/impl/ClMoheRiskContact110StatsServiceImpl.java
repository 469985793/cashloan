package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheRiskContact110Stats;
import com.xindaibao.cashloan.cl.mapper.ClMoheRiskContact110StatsMapper;
import com.xindaibao.cashloan.cl.service.ClMoheRiskContact110StatsService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheRiskContact110StatsService")
public class ClMoheRiskContact110StatsServiceImpl
        extends BaseServiceImpl<ClMoheRiskContact110Stats, Long> implements ClMoheRiskContact110StatsService {

    @Autowired private ClMoheRiskContact110StatsMapper clMoheRiskContact110StatsMapper;

    @Override
    public BaseMapper<ClMoheRiskContact110Stats, Long> getMapper() {
        return clMoheRiskContact110StatsMapper;
    }
}
