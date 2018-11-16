package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheHomeTelDetail;
import com.xindaibao.cashloan.cl.mapper.ClMoheHomeTelDetailMapper;
import com.xindaibao.cashloan.cl.service.ClMoheHomeTelDetailService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheHomeTelDetailStatsService")
public class ClMoheHomeTelDetailServiceImpl
        extends BaseServiceImpl<ClMoheHomeTelDetail, Long> implements ClMoheHomeTelDetailService {

    @Autowired private ClMoheHomeTelDetailMapper clMoheHomeTelDetailMapper;

    @Override
    public BaseMapper<ClMoheHomeTelDetail, Long> getMapper() {
        return clMoheHomeTelDetailMapper;
    }
}
