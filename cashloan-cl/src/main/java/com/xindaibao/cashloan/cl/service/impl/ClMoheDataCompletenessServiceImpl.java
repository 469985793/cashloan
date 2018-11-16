package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheDataCompleteness;
import com.xindaibao.cashloan.cl.mapper.ClMoheDataCompletenessMapper;
import com.xindaibao.cashloan.cl.service.ClMoheDataCompletenessService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheDataCompletenessStatsService")
public class ClMoheDataCompletenessServiceImpl
        extends BaseServiceImpl<ClMoheDataCompleteness, Long> implements ClMoheDataCompletenessService {

    @Autowired private ClMoheDataCompletenessMapper clMoheDataCompletenessMapper;

    @Override
    public BaseMapper<ClMoheDataCompleteness, Long> getMapper() {
        return clMoheDataCompletenessMapper;
    }
}
