package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheBehaviorAnalysis;
import com.xindaibao.cashloan.cl.mapper.ClMoheBehaviorAnalysisMapper;
import com.xindaibao.cashloan.cl.service.ClMoheBehaviorAnalysisService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheBehaviorAnalysisStatsService")
public class ClMoheBehaviorAnalysisServiceImpl
        extends BaseServiceImpl<ClMoheBehaviorAnalysis, Long> implements ClMoheBehaviorAnalysisService {

    @Autowired private ClMoheBehaviorAnalysisMapper clMoheBehaviorAnalysisMapper;

    @Override
    public BaseMapper<ClMoheBehaviorAnalysis, Long> getMapper() {
        return clMoheBehaviorAnalysisMapper;
    }
}
