package com.xindaibao.cashloan.cl.service;


import com.xindaibao.cashloan.cl.domain.ClFraudTdMultiPlatform;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.BaseService;

/**
 * 风控数据统计-（简）通话记录统计Service
 */
public interface ClFraudTdMultiPlatformService extends BaseService<ClFraudTdMultiPlatform, Long> {
    BaseMapper<ClFraudTdMultiPlatform, Long> getClFraudTdMultiPlatformMapperMapper();

}
