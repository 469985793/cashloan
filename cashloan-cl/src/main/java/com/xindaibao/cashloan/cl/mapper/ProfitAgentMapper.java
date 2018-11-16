package com.xindaibao.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.domain.ProfitAgent;
import com.xindaibao.cashloan.cl.model.ManageAgentListModel;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 代理用户信息Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-18 16:24:45


 * 

 */
@RDBatisDao
public interface ProfitAgentMapper extends BaseMapper<ProfitAgent,Long> {

	/**
	 * 查询代理商
	 * @param map
	 * @return
	 */
	List<ManageAgentListModel> findAgentList(Map<String, Object> map);

}
