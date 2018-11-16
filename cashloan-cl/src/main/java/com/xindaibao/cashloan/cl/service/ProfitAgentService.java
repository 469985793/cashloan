package com.xindaibao.cashloan.cl.service;

import java.util.Date;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.ProfitAgent;
import com.xindaibao.cashloan.cl.model.ManageAgentListModel;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.core.domain.User;

/**
 * 代理用户信息Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-18 16:24:45


 * 

 */
public interface ProfitAgentService extends BaseService<ProfitAgent, Long>{

	/**
	 * 代理商信息保存
	 * @param userId
	 * @param level
	 * @param rate 
	 * @param rate 
	 */
//	int save(long userId, int level, String leaderId, String rate);

	/**
	 * 代理商审核
	 * @param isUse
	 * @param  
	 */
	int pass(int isUse, long id);

	/**
	 * 二级代理升级
	 * @param id
	 * @param rate
	 * @param oldRate
	 * @return
	 */
	int rankUp(long id,long userId);

	/**
	 * 代理商查询
	 * @param userName
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<ManageAgentListModel> findAgentList(String userName, int current, int pageSize);

	/**
	 * 查询用户等级
	 * @param loginName
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<User> findUserLevel(String userName,int current,int pageSize);

	/**
	 * 取消一级代理
	 * @param id
	 * @return
	 */
	int freezeAgent(long userId,Date updateTime);

	/**
	 * 一级代理处理
	 * @param parseLong
	 * @return
	 */
	int saveOne(long parseLong,Date updateTime);

	/**
	 * 二级代理处理
	 * @param parseLong
	 * @param leaderId
	 * @param rate
	 * @return
	 */
	int saveTwo(long parseLong, String leaderId, String rate,Date updateTime);

}
