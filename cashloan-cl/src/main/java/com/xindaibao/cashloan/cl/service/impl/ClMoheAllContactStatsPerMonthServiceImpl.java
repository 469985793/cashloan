package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheAllContactStatsPerMonth;
import com.xindaibao.cashloan.cl.mapper.ClMoheAllContactStatsPerMonthMapper;
import com.xindaibao.cashloan.cl.service.ClMoheAllContactStatsPerMonthService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheAllContactStatsPerMonthStatsService")
public class ClMoheAllContactStatsPerMonthServiceImpl
        extends BaseServiceImpl<ClMoheAllContactStatsPerMonth, Long> implements ClMoheAllContactStatsPerMonthService {

    @Autowired private ClMoheAllContactStatsPerMonthMapper clMoheAllContactStatsPerMonthMapper;

    @Override
    public BaseMapper<ClMoheAllContactStatsPerMonth, Long> getMapper() {
        return clMoheAllContactStatsPerMonthMapper;
    }
}
