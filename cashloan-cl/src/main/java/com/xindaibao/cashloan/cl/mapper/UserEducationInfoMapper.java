package com.xindaibao.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.domain.UserEducationInfo;
import com.xindaibao.cashloan.cl.model.UserEducationInfoModel;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 学信查询记录表Mapper
 * 
 * @author
 * @version 1.0.0
 * @date 2017-04-18 16:30:45




 */
@RDBatisDao
public interface UserEducationInfoMapper extends BaseMapper<UserEducationInfo,Long> {

	/**
	 * 列表查询
	 * @param searchMap
	 * @return
	 */
	List<UserEducationInfoModel> page(Map<String, Object> searchMap);

    

}
