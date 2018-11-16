package com.xindaibao.creditrank.cr.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.creditrank.cr.domain.Rank;
import com.xindaibao.creditrank.cr.model.RankModel;

/**
 * 评分等级Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-04 15:09:59


 * 

 */
@RDBatisDao
public interface RankMapper extends BaseMapper<Rank,Long> {

    /**
     * 查询等级
     * @param cardId
     * @param score
     * @return
     */
	List<Rank> findRankLeve(Long cardId,Integer score);

	/**
	 * 评分等级名称列表
	 * @return
	 */
	List<RankModel> countList();

	/**
	 * 删除一行
	 * @param id
	 * @return
	 */
	int deleteSelective(long id);

	List<RankModel> countList(Map<String, Object> search);
	
	/**
	 * 查询所有评分等级
	 * @return
	 */
	List<Rank> findAll();
}
