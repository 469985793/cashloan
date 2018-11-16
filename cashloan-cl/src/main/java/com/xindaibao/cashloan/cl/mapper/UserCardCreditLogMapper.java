package com.xindaibao.cashloan.cl.mapper;

import java.util.Map;

import com.xindaibao.cashloan.cl.domain.UserCardCreditLog;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 人脸识别请求记录Dao
 */
@RDBatisDao
public interface UserCardCreditLogMapper extends BaseMapper<UserCardCreditLog,Long> {
	/**
	 * 获取用户当天请求次数
	 * @param paramMap
	 * @return
	 */
	int countByUserId(Map<String, Object> paramMap);

    

}
