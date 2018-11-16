package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheWorkTelDetail;
import com.xindaibao.cashloan.cl.mapper.ClMoheWorkTelDetailMapper;
import com.xindaibao.cashloan.cl.service.ClMoheWorkTelDetailService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheWorkTelDetailStatsService")
public class ClMoheWorkTelDetailServiceImpl
        extends BaseServiceImpl<ClMoheWorkTelDetail, Long> implements ClMoheWorkTelDetailService {

    @Autowired private ClMoheWorkTelDetailMapper clMoheWorkTelDetailMapper;

    @Override
    public BaseMapper<ClMoheWorkTelDetail, Long> getMapper() {
        return clMoheWorkTelDetailMapper;
    }
}
