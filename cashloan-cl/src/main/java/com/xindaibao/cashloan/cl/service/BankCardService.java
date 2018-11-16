package com.xindaibao.cashloan.cl.service;

import java.util.Map;

import com.xindaibao.cashloan.cl.domain.BankCard;
import com.xindaibao.cashloan.core.common.service.BaseService;

/**
 * 银行卡Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-15 14:37:14


 * 

 */
public interface BankCardService extends BaseService<BankCard, Long>{

	/**
	 * 据userId查询银行卡信息
	 * 
	 * @param userId
	 * @return
	 */
	BankCard findByUserId(Long userId);

	/**
	 * 保存记录
	 * @param bankCard
	 * @return
	 */
	boolean save(BankCard bankCard);
	
	/**
	 * 据userId查询银行卡信息
	 * 
	 * @param userId
	 * @return
	 */
	BankCard getBankCardByUserId(Long userId);

	/**
	 * 据条件查询银行卡信息
	 * 
	 * @param paramMap
	 * @return
	 */
	BankCard findSelective(Map<String, Object> paramMap);

	/**
	 * 解约并修改银行卡
	 * 
	 * @param card
	 * @return
	 */
	int cancelAuthSign(BankCard card);
	
	/**
	 * 修改银行卡信息
	 * 
	 * @param paramMap
	 * @return
	 */
	boolean updateSelective(Map<String, Object> paramMap);
	

	/**
	 * 保存或更新银行卡信息
	 * 
	 * @param userId
	 * @param bank
	 * @param cardNo
	 * @param agreeNo
	 * @return
	 */
	boolean saveOrUpdate(Long userId, String bank, String cardNo, String agreeNo);

}
