package com.xindaibao.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.domain.Opinion;
import com.xindaibao.cashloan.cl.model.OpinionModel;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;


/**
 * 意见反馈表Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-14 11:30:57


 * 

 */
@RDBatisDao
public interface OpinionMapper extends BaseMapper<Opinion,Long> {
	
	List<OpinionModel> listModel(Map<String, Object> searchMap);

}
