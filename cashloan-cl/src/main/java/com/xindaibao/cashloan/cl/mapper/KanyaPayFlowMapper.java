package com.xindaibao.cashloan.cl.mapper;

import com.xindaibao.cashloan.cl.model.kenya.KanyaPayFlow;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

@RDBatisDao
public interface KanyaPayFlowMapper extends BaseMapper<KanyaPayFlow, Long> {

    int save(KanyaPayFlow kanyaPayFlow);

    KanyaPayFlow findByLoanRecordId(Long id);
}