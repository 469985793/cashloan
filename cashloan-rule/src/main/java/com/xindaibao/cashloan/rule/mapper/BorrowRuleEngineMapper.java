package com.xindaibao.cashloan.rule.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.cashloan.rule.domain.BorrowRuleEngine;

/**
 * 借款规则管理Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2016-12-20 10:22:30


 * 

 */
@RDBatisDao
public interface BorrowRuleEngineMapper extends BaseMapper<BorrowRuleEngine,Long> {

	int deleteById(long id);
	
	List<BorrowRuleEngine> listByBorrowType(@Param("borrowType")String borrowType);

}
