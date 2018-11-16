package com.xindaibao.creditrank.cr.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.creditrank.cr.domain.Item;
import com.xindaibao.creditrank.cr.model.ItemModel;

/**
 * 评分项目Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-04 15:09:22


 * 

 */
@RDBatisDao
public interface ItemMapper extends BaseMapper<Item,Long> {

	/**
	 * 根据itemName返回数据
	 * @param itemName
	 * @return
	 */
    Item findByItmeName(String itemName);

    /**
     * 列表查询返回ItemModel
     * @param secreditrankhMap
     * @return
     */
	List<ItemModel> listSelect(Map<String, Object> secreditrankhMap);

	/**
	 * 根据评分卡id查询
	 * @param id
	 * @return
	 */
	Item findByCardId(long cardId);

	/**
	 * 删除项目
	 * @param id
	 * @return
	 */
	int deleteSelective(long id);

	/**
	 * 查询所属评分卡项目总分
	 * @param cardId
	 * @return
	 */
	int findSumScore(long cardId);

}
