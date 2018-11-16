package com.xindaibao.cashloan.rc.mapper;

import java.util.Map;

import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 紧急联系人借款信息统计
 * @author
 * @version 1.0
 * @date 2017年4月21日下午2:47:03




 */
@RDBatisDao
public interface RcUserContactsCountMapper  {

	/**
	 * 通讯录总条数
	 * @param id
	 * @return
	 */
	Integer count(Map<String,Object> params);

	/**
	 * 通讯录借款未逾期人数
	 * @param id
	 * @return
	 */
	Integer countSucceed(Map<String,Object> params);

	/**
	 * 通讯录借款逾期人数
	 * @param id
	 * @return
	 */
	Integer countFail(Map<String,Object> params);

	/**
	 * 通讯录借款逾期大于90天人数
	 * @param id
	 * @return
	 */
	Integer countNinety(Map<String,Object> params);

	/**
	 * 通讯录借款逾期大于30天小于90天人数
	 * @param id
	 * @return
	 */
	Integer countThirty(Map<String,Object> params);

	/**
	 * 通讯录借款逾期小于30天人数
	 * @param id
	 * @return
	 */
	Integer countWithinThirty(Map<String,Object> params);
}
