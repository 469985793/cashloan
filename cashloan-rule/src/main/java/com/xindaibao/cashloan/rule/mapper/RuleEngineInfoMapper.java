package com.xindaibao.cashloan.rule.mapper;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.cashloan.rule.domain.RuleEngineInfo;

/**
 * 规则评分设置管理Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-03 17:28:16


 * 

 */
@RDBatisDao
public interface RuleEngineInfoMapper extends BaseMapper<RuleEngineInfo,Long> {

	int insert(RuleEngineInfo info);

	int deleteInfoByRuleId(long id);

    

}
