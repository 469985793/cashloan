package com.xindaibao.cashloan.rc.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.cashloan.rc.domain.TppReqLog;
import com.xindaibao.cashloan.rc.model.TppReqLogModel;

/**
 * 第三方征信请求记录Dao
 */
@RDBatisDao
public interface TppReqLogMapper extends BaseMapper<TppReqLog,Long> {

	/**
	 * 根据订单号修改记录
	 * @param log
	 * @return
	 */
	int modifyTppReqLog(TppReqLog log);

	/**
	 * 分页查询
	 * @param searchMap
	 * @return
	 */
	List<TppReqLogModel> page(Map<String, Object> searchMap);

}
