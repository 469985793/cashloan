package com.xindaibao.cashloan.cl.mapper;

import com.xindaibao.cashloan.cl.model.kenya.KanyaPayRecord;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

@RDBatisDao
public interface KanyaPayRecordMapper extends BaseMapper<KanyaPayRecord, Long> {
    int save(KanyaPayRecord kanyaPayRecord);

    KanyaPayRecord findByLoanRecordId(Long id);
}