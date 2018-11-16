package com.xindaibao.cashloan.cl.service;

import com.xindaibao.cashloan.cl.domain.UserEquipmentInfo;
import com.xindaibao.cashloan.core.common.service.BaseService;


/**
 * 用户设备信息表Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-04-17 17:32:05




 */
public interface UserEquipmentInfoService extends BaseService<UserEquipmentInfo, Long>{

	/**
	 * 信息入库
	 * @param uei
	 * @return
	 */
	int saveOrUpdate(UserEquipmentInfo uei);

	/**
	 * 查询
	 * @param parseLong
	 * @return
	 */
	UserEquipmentInfo findSelective(long userId);

	/**
	 * 保存设备指纹
	 * @param loginName
	 * @param blackBox
	 */
	void save(String loginName, String blackBox);

}
