package com.xindaibao.creditrank.cr.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.creditrank.cr.domain.Credit;
import com.xindaibao.creditrank.cr.model.CreditModel;

/**
 * 授信额度管理Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2016-12-15 15:47:24


 * 

 */
@RDBatisDao
public interface CreditMapper extends BaseMapper<Credit,Long> {
	
	/**
	 * 更新额度
	 * @param map
	 * @return
	 */
	int updateAmount(Map<String, Object> map);

	/**
	 * 列表查询返回CreditModel
	 * @param searchMap
	 * @return
	 */
	List<CreditModel> page(Map<String,Object> searchMap);

	/**
	 * 根据consumerNo查询
	 * @param consumerNo
	 * @return
	 */
	Credit findByConsumerNo(String consumerNo);
	
	/**
	 * 查询用户所有额度类型
	 * @param searchMap
	 * @return
	 */
	List<CreditModel> listAll(Map<String, Object> searchMap);
	
	
	/**
	 * 用户信用额度查询
	 * @param searchMap
	 * @return
	 */
	List<CreditModel> creditList(Map<String, Object> searchMap);
	
	/**
	 * 用户认证完成后，根据评分结果，更新额度 
	 * @param map
	 * @return
	 */
	int updateByAuth(Map<String, Object> map);
	
	/**
	 * 根据userId修改额度，提额/逾期恢复到原额度
	 * @param map
	 * @return
	 */
	int updateByUserId(Map<String, Object> map);
}
