package com.xindaibao.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.domain.OperatorTdCallRecord;
import com.xindaibao.cashloan.cl.domain.OperatorVoices;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 通话记录具体详细Dao
 */
@RDBatisDao
public interface OperatorTdCallRecordMapper extends BaseMapper<OperatorTdCallRecord, Long> {

	List<OperatorVoices> listOperatorVoicesModel(Map<String, Object> params);

    

}
