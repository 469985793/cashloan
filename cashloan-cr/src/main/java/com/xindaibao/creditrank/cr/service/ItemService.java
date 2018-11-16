package com.xindaibao.creditrank.cr.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.exception.CreditException;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.creditrank.cr.domain.Item;
import com.xindaibao.creditrank.cr.model.ItemModel;

/**
 * 评分项目Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-04 15:09:22


 * 

 */
public interface ItemService extends BaseService<Item, Long>{

	/**
	 * 保存项目数据
	 * @param itemName
	 * @param cardId
	 * @param cardName
	 * @return
	 * @throws CreditException 
	 */
	Map<String, Object> save(String itemName,Long cardId) throws CreditException;
	
	/**
	 * 分页查询
	 * @param secreditrankhMap
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<ItemModel> page(Map<String, Object> secreditrankhMap, int current, int pageSize);

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	Item findByPrimary(long id);

	/**
	 * 查询所有列表
	 * @param result
	 * @return
	 */
	List<ItemModel> listSelect(Map<String, Object> result);

	/**
	 * 修改
	 * @param map
	 * @return
	 */
	int updateSelective(Map<String, Object> map);

	/**
	 * 删除项目
	 * @param id
	 * @return
	 * @throws CreditException 
	 */
	Map<String, Object> deleteSelective(long id) throws CreditException;

	/**
	 * 查询评分卡下的项目
	 * @param cardId
	 * @return
	 */
	Map<String, Object> list(long cardId);

	/**
	 * 查询所属评分卡项目最高分
	 * @param cardId
	 * @return
	 */
	int findSumScore(long cardId);

}
