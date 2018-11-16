package com.xindaibao.cashloan.manage.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.cashloan.manage.domain.QuartzInfo;
import com.xindaibao.cashloan.manage.model.QuartzInfoModel;

/**
 * 定时任务详情Dao
 */
@RDBatisDao
public interface QuartzInfoMapper extends BaseMapper<QuartzInfo, Long> {

	/**
	 * 定时任务查询
	 * 
	 * @param map
	 * @return
	 */
	List<QuartzInfoModel> page(Map<String, Object> map);
}
