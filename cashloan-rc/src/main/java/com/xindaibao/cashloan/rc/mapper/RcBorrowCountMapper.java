package com.xindaibao.cashloan.rc.mapper;

import java.util.Map;

import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;


/**
 * 借款信息统计
 * @author
 * @version 1.0
 * @date 2017年4月21日下午2:55:46




 */
@RDBatisDao
public interface RcBorrowCountMapper {
	
	/**
	 * 统计所有借款次数
	 * @param id
	 * @return
	 */
	Integer borrowCount(Long id);
	
	/**
     * 查询用户未完成借款数
     * @param map
     * @return
     */
    int countUnFinished(Map<String, Object> map);
    
	/**
	 * 统计借款失败次数
	 * @param id
	 * @return
	 */
	Integer borrowFailCount(Long id);

	/**
	 * 统计当日借款失败次数
	 * @param id
	 * @return
	 */
	Integer dayFailCount(Long id);

	/**
	 * 统计逾期借款
	 * @param id
	 * @return
	 */
	Integer failCount(Long id);

	/**
	 * 统计紧急联系人有逾期未还借款数
	 * @param id
	 * @return
	 */
	Integer failCountRelative(Long id);
}
