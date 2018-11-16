package com.xindaibao.creditrank.cr.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.system.domain.SysUser;
import com.xindaibao.creditrank.cr.domain.Credit;
import com.xindaibao.creditrank.cr.model.CreditModel;

/**
 * 授信额度管理Service
 * 
 * @author
 * @version 1.0.0
 * @date 2016-12-15 15:47:24


 * 

 */
public interface CreditService extends BaseService<Credit, Long>{

	/**
	 * 分页查询
	 * @param searchMap
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<CreditModel> page(Map<String,Object> searchMap,int current,int pageSize);
	
	/**
	 * 修改数据
	 * @param sysUser 
	 * @param remark 
	 * @param Param
	 * @return
	 */
	int updateSelective(long id, double unuse, SysUser sysUser, String remark);
	
	/**
	 * 主键查询
	 * @param id
	 * @return
	 */
	Credit findByPrimary(long id);

	/**
	 * 修改
	 * @param param
	 * @return
	 */
	int updateSelective(Map<String, Object> param);

	/**
	 * 查询所有
	 * @param searchMap
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<CreditModel> listAll(Map<String, Object> searchMap, int current,
			int pageSize);

	/**
	 * 查询用户
	 * @param consumerNo
	 * @return
	 */
	List<Credit> findByConsumerNo(String consumerNo);

	/**
	 * 冻结解冻账户
	 * @param id
	 * @param state
	 * @param sysUser 
	 * @return
	 */
	int updateState(long id, String state, SysUser sysUser);
	
	/**
	 * 查询用户所有额度类型
	 * @param paramMap
	 * @return
	 */
	Credit findSelective(Map<String, Object> paramMap);
	
	/**
	 * 用户认证完成后，根据评分结果，更新额度 
	 * @param param
	 * @return
	 */
	int updateByAuth(Map<String, Object> param);
	
	/**
	 * 额度审批修改数据
	 * @param id
	 * @param state
	 * @param sysUser 
	 * @return
	 */
	int updateByApproval(long id,double total, String state, SysUser sysUser);
	
}
