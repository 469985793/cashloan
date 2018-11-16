package com.xindaibao.cashloan.rc.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.cashloan.rc.domain.Tpp;
import com.xindaibao.cashloan.rc.model.ManageTppModel;
import com.xindaibao.cashloan.rc.model.TppModel;

/**
 * 第三方征信信息Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-14 13:41:05




 */
@RDBatisDao
public interface TppMapper extends BaseMapper<Tpp,Long> {

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<TppModel> listAll();

	/**
	 * 条件查询List
	 * 
	 * @param paramMap
	 * @return
	 */
	List<ManageTppModel> list(Map<String, Object> paramMap);

	/**
	 * 遍历所有第三方信息
	 * 
	 * @return
	 */
	List<TppModel> listAllWithBusiness();
}
