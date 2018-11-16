package com.xindaibao.cashloan.core.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import org.apache.ibatis.annotations.Param;

import com.xindaibao.cashloan.core.model.ManagerUserModel;
import com.xindaibao.cashloan.core.model.UserWorkInfoModel;

/**
 * 用户详细信息Dao
 *
 * @version 1.0.0
 * @date 2017-02-21 13:44:30
 */
@RDBatisDao
public interface UserBaseInfoMapper extends BaseMapper<UserBaseInfo,Long> {

	List<Map<String, Object>> getDictsCache(String type);

	ManagerUserModel getBaseModelByUserId(Long userId);



	UserBaseInfo findUserImags(@Param("userId") Long userId);

	/**
	 * 查询用户工作信息
	 * 
	 * @param userId
	 * @return
	 */
	UserWorkInfoModel findWorkInfo(@Param("userId") Long userId);

	/**
	 * 据用户id查询用户详细信息
	 * 
	 * @param userId
	 * @return
	 */
	UserBaseInfo findByUserId(@Param("userId") Long userId);
	
}
