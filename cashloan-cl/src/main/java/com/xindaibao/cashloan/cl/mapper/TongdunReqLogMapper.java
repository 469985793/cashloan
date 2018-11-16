package com.xindaibao.cashloan.cl.mapper;

import com.xindaibao.cashloan.cl.domain.TongdunReqLog;
import com.xindaibao.cashloan.cl.model.TongdunReqLogModel;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

import java.util.List;
import java.util.Map;

/**
 * 同盾第三方请求记录Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-04-26 15:26:56




 */
@RDBatisDao
public interface TongdunReqLogMapper extends BaseMapper<TongdunReqLog,Long> {

	List<TongdunReqLogModel> listModelByMap(Map<String, Object> params);

	TongdunReqLogModel findModelById(long id);


	TongdunReqLog findModelByReportId(String reportId);

	List<TongdunReqLog> findAllModel();





    

}
