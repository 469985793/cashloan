package com.xindaibao.cashloan.cl.service;

import java.util.Map;

import com.xindaibao.cashloan.cl.domain.Zhima;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.core.domain.User;

/**
 * 芝麻信用Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-14 11:35:27


 * 

 */
public interface ZhimaService extends BaseService<Zhima, Long>{

	Zhima getZhima(Map<String, Object> paramMap);

	Map<String, Object> authCallBack(String params,User user) throws Exception;

	/**
	 * 获取用户芝麻积分
	 * @param userId
	 */
	int updateZhimaScore(Long userId);

	/**
	 * 根据用户查询芝麻信息
	 * @param userId
	 * @return
	 */
	Zhima findByUserId(Long userId);
}
