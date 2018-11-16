package com.xindaibao.cashloan.cl.mapper;

import java.util.List;

import com.xindaibao.cashloan.cl.domain.RcZhimaIndustry;
import org.apache.ibatis.annotations.Param;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 芝麻行业关注名单Dao
 */
@RDBatisDao
public interface RcZhimaIndustryMapper extends BaseMapper<RcZhimaIndustry,Long> {

	/**
	 * 根据用户Id查询芝麻信用信息
	 * @param userId
	 * @return
	 */
	List<RcZhimaIndustry> findByuserId(@Param("userId")Long userId);

}
