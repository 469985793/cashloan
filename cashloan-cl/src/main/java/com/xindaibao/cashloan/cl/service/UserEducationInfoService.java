package com.xindaibao.cashloan.cl.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.UserEducationInfo;
import com.xindaibao.cashloan.cl.model.UserEducationInfoModel;
import com.xindaibao.cashloan.core.common.service.BaseService;


/**
 * 学信查询记录表Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-04-18 16:30:45




 */
public interface UserEducationInfoService extends BaseService<UserEducationInfo, Long>{

	/**
	 * 信息入库
	 * @param ue
	 * @return
	 */
	int save(UserEducationInfo ue);

	/**
	 * 信息修改
	 * @param uei
	 * @return
	 */
	int update(UserEducationInfo uei);

	/**
	 * 列表查询
	 * @param searchMap
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<UserEducationInfoModel> list(Map<String, Object> searchMap,
			int current, int pageSize);

}
