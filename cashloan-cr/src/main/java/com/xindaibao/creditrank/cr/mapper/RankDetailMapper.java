package com.xindaibao.creditrank.cr.mapper;

import com.xindaibao.creditrank.cr.domain.RankDetail;
import org.apache.ibatis.annotations.Param;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 评分卡等级详情表Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-06 11:27:25


 * 

 */
@RDBatisDao
public interface RankDetailMapper extends BaseMapper<RankDetail,Long> {

	/**
	 * 根据等级父ID和评分找到对应的等级详情信息
	 * @param id
	 * @param score
	 * @return
	 */
    RankDetail findByParentIdAndScore(@Param("id") Long id, @Param("score") Integer score);
    
    /**
	 * 删除
	 * @param id
	 * @return 
	 */
	int deleteSelective(long id);

}
