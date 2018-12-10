package com.xindaibao.cashloan.cl.mapper;


import com.xindaibao.cashloan.cl.model.CreditInfo.CreditInfoLog;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

import java.util.List;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/7
 */
@RDBatisDao
public interface CreditInfoMapper extends BaseMapper<CreditInfoLog, Long> {

    List<CreditInfoLog> findByUserNationalId(String nationalId);

}
