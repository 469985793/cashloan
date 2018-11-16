package com.xindaibao.cashloan.rc.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.cashloan.rc.domain.SceneBusiness;
import com.xindaibao.cashloan.rc.model.ManageSceneBusinessModel;
import com.xindaibao.cashloan.rc.model.TppServiceInfoModel;

/**
 * 场景与第三方征信接口关联关系Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-14 13:42:36




 */
@RDBatisDao
public interface SceneBusinessMapper extends BaseMapper<SceneBusiness,Long> {

	/**
	 * 条件查询List
	 * 
	 * @param paramMap
	 * @return
	 */
	List<ManageSceneBusinessModel> list(Map<String, Object> paramMap);

	/**
	 * 详情查询
	 * 
	 * @param id
	 * @return
	 */
	ManageSceneBusinessModel findDetail(Long id);
	
	/**
	 * 查询第三方接口信息
	 * @return
	 */
	List<TppServiceInfoModel> findTppServiceInfo();

}
