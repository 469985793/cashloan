package com.xindaibao.creditrank.cr.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.creditrank.cr.domain.Rank;
import com.xindaibao.creditrank.cr.model.RankModel;

/**
 * 评分等级Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-04 15:09:59


 * 

 */
public interface RankService extends BaseService<Rank, Long>{


	/**
	 * 分页查询
	 * @param searchMap
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<Rank> page(Map<String, Object> searchMap, int current, int pageSize);
	
	/**
	 * 查询所有评分等级
	 * @return
	 */
	List<Rank> findAll();

	/**
	 * 修改数据
	 * @param updateMap
	 * @return
	 */
	int updateSelective(Map<String, Object> updateMap);

	/**
	 * 查询所有
	 * @param search
	 * @return
	 */
	List<Rank> listSelective(Map<String, Object> search);

	/**
	 * 多条新增
	 * @param list
	 * @param rankName 
	 * @param id 
	 * @return
	 */
	Map<String,Object> save(List<Map<String, Object>> list, String rankName, long id);

	/**
	 * 查询单条
	 * @param search
	 * @return
	 */
	Rank findSelective(Map<String, Object> search);

	/**
	 * 删除一行
	 * @param id
	 * @return
	 */
	Map<String,Object> deleteSelective(long id);

	Page<RankModel> countList(Map<String, Object> searchMap, int current, int pageSize);

}
