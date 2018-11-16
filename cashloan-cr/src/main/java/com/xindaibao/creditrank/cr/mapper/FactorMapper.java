package com.xindaibao.creditrank.cr.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.creditrank.cr.domain.Factor;
import com.xindaibao.creditrank.cr.model.FactorModel;

/**
 * 评分因子Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-04 15:11:15


 * 

 */
@RDBatisDao
public interface FactorMapper extends BaseMapper<Factor,Long> {

	/**
	 * 根据factorName查询
	 * @param factorName
	 * @return
	 */
    Factor findByFactorName(String factorName);

    /**
     * 列表查询数据
     * @param param
     * @return
     */
	List<FactorModel> listSelect(Map<String, Object> param);

	/**
	 * 根据ItemId查询列表
	 * @param id
	 * @return
	 */
	List<Factor> findByItemId(long id);

	/**
	 * 根据cardId查询
	 * @param cardId
	 * @return
	 */
	Factor findByCardId(long cardId);

	/**
	 * 删除因子
	 * @param id
	 * @return 
	 */
	int deleteSelective(long id);

	/**
	 * 查询所属项目因子总分
	 * @param itemId
	 * @return
	 */
	int findSumScore(long itemId);
	
	/**
	 * 查询所有
	 * @param factor
	 * @return
	 */
	List<FactorModel> listFactorModel(Map<String, Object> factor);

}
