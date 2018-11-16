package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClMoheUserInfo;
import com.xindaibao.cashloan.cl.mapper.ClMoheUserInfoMapper;
import com.xindaibao.cashloan.cl.service.ClMoheUserInfoService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clMoheUserInfoStatsService")
public class ClMoheUserInfoServiceImpl
        extends BaseServiceImpl<ClMoheUserInfo, Long> implements ClMoheUserInfoService {

    @Autowired private ClMoheUserInfoMapper clMoheUserInfoMapper;

    @Override
    public BaseMapper<ClMoheUserInfo, Long> getMapper() {
        return clMoheUserInfoMapper;
    }
}
