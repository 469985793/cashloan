package com.xindaibao.creditrank.cr.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.exception.CreditException;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.creditrank.cr.domain.FactorParam;
import com.xindaibao.creditrank.cr.model.FactorParamModel;

/**
 * 评分因子参数Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-05 11:13:30


 * 

 */
public interface FactorParamService extends BaseService<FactorParam, Long>{


	/**
	 * 分页查看
	 * @param secreditrankhMap
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<FactorParam> page(Map<String, Object> secreditrankhMap, int current,
			int pageSize);

	/**
	 * 修改参数
	 * @param updateMap
	 * @return
	 */
	int updateSelective(Map<String, Object> updateMap);

	/**
	 * 查询所有参数
	 * @param param
	 * @return
	 */
	List<FactorParamModel> listSelect(Map<String, Object> param);

	/**
	 * 保存参数
	 * @param fp
	 * @return
	 */
	int save(FactorParam fp);

	/**
	 * 根据主键删除
	 * @param factorId 
	 * @param parseLong
	 * @return
	 * @throws CreditException 
	 */
	Map<String, Object> deleteSelective(long id) throws CreditException;

	/**
	 * 主键查询
	 * @param id
	 * @return
	 */
	FactorParam findByPrimary(long id);

	/**
	 * 引用删除
	 * @param id
	 * @return
	 */
	int deleteSelective(Long id);

	/**
	 * 计算最高因子参数分值
	 * @param factorId 
	 * @return
	 */
	int findMaxScore(long factorId);

}
