package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheRiskContactDetail;
import com.xindaibao.cashloan.cl.mapper.ClMoheRiskContactDetailMapper;
import com.xindaibao.cashloan.cl.service.ClMoheRiskContactDetailService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheRiskContactDetailStatsService")
public class ClMoheRiskContactDetailServiceImpl
        extends BaseServiceImpl<ClMoheRiskContactDetail, Long> implements ClMoheRiskContactDetailService {

    @Autowired private ClMoheRiskContactDetailMapper clMoheRiskContactDetailMapper;

    @Override
    public BaseMapper<ClMoheRiskContactDetail, Long> getMapper() {
        return clMoheRiskContactDetailMapper;
    }
}
