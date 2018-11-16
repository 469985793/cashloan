package com.xindaibao.cashloan.cl.mapper;

import java.util.List;

import com.xindaibao.cashloan.cl.domain.Adver;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 广告管理Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-06-21 14:33:20



 */
@RDBatisDao
public interface AdverMapper extends BaseMapper<Adver, Long> {
	
	
	List<Adver> getBanner();

}
