package com.xindaibao.creditrank.cr.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.creditrank.cr.domain.Card;

/**
 * 评分卡Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-04 15:06:51


 * 

 */
@RDBatisDao
public interface CardMapper extends BaseMapper<Card,Long> {

	/**
	 * 根据cardName返回数据
	 * @param cardName
	 * @return
	 */
	Card findByCardName(String cardName);

	/**
	 * 禁用评分卡
	 * @param map
	 * @return
	 */
	int updateState(Map<String, Object> map);
	
	/**
	 * 标记评分卡是否已经被使用
	 * @param id
	 * @return
	 */
	int updateType(Long id);
	
	/**
	 * 查询所有评分卡
	 * @return
	 */
	List<Card> findAll();
}
