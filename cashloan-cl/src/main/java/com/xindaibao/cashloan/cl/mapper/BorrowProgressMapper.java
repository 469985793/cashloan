package com.xindaibao.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.domain.BorrowProgress;
import com.xindaibao.cashloan.cl.model.BorrowProgressModel;
import com.xindaibao.cashloan.cl.model.ManageBorrowProgressModel;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 借款进度表Dao
 */
@RDBatisDao
public interface BorrowProgressMapper extends BaseMapper<BorrowProgress,Long> {

	/**
	 * 首页查询进度
	 * @param bpMap
	 * @return
	 */
	List<BorrowProgressModel> listIndex(Map<String, Object> bpMap);
	
	/**
	 * 后台借款进度列表
	 * @param bpMap
	 * @return
	 */
	List<ManageBorrowProgressModel> listModel(Map<String, Object> params);

	/**
	 * 借款进度查询
	 * @param bpMap
	 * @return
	 */
	List<BorrowProgressModel> listProgress(Map<String, Object> bpMap);

}
