package com.xindaibao.cashloan.cl.mapper;

import java.util.List;

import com.xindaibao.cashloan.cl.domain.ProfitLevel;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 分润等级Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-18 16:58:10


 * 

 */
@RDBatisDao
public interface ProfitLevelMapper extends BaseMapper<ProfitLevel,Long> {

	/**
	 * 查询所有
	 * @return
	 */
	List<ProfitLevel> listAll();

    

}
