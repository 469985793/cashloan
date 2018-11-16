package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheRiskContact120Stats;
import com.xindaibao.cashloan.cl.mapper.ClMoheRiskContact120StatsMapper;
import com.xindaibao.cashloan.cl.service.ClMoheRiskContact120StatsService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheRiskContact120StatsService")
public class ClMoheRiskContact120StatsServiceImpl
        extends BaseServiceImpl<ClMoheRiskContact120Stats, Long> implements ClMoheRiskContact120StatsService {

    @Autowired private ClMoheRiskContact120StatsMapper clMoheRiskContact120StatsMapper;

    @Override
    public BaseMapper<ClMoheRiskContact120Stats, Long> getMapper() {
        return clMoheRiskContact120StatsMapper;
    }
}
