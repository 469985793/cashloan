package com.xindaibao.cashloan.cl.mapper;

import java.util.List;

import com.xindaibao.cashloan.cl.domain.RcZhimaAntiFraud;
import org.apache.ibatis.annotations.Param;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 芝麻反欺诈Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-06 18:47:03


 * 

 */
@RDBatisDao
public interface RcZhimaAntiFraudMapper extends BaseMapper<RcZhimaAntiFraud,Long> {

	List<RcZhimaAntiFraud> findByuserId(@Param("userId")Long userId);

}
