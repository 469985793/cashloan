package com.xindaibao.cashloan.cl.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.ProfitAmount;
import com.xindaibao.cashloan.cl.model.ManageProfitAmountModel;
import com.xindaibao.cashloan.core.common.service.BaseService;

/**
 * 分润资金Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-18 16:29:51


 * 

 */
public interface ProfitAmountService extends BaseService<ProfitAmount, Long>{

	/**
	 * 提现修改资金
	 * @param userId
	 * @param money
	 * @return
	 */
	int cash(long userId, double money);

	/**
	 * 查看我的奖金
	 * @param userId
	 * @return
	 */
	ProfitAmount find(long userId);

	/**
	 * 查询可提现奖金
	 * @param phone
	 * @param userName 
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<ManageProfitAmountModel> findAmount(String phone, String userName, int current, int pageSize);

	/**
	 * 管理员查询可提现奖金
	 * @param phone
	 * @param userName 
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<ManageProfitAmountModel> findSysAmount(String userName, int current,
			int pageSize);
	
	/**
	 * 查询未取现金额用户
	 * @param map
	 * @return
	 */
	List<ProfitAmount> listNoCash();

}
