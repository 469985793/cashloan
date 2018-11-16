package com.xindaibao.cashloan.cl.mapper;


import com.xindaibao.cashloan.cl.domain.BankCard;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 银行卡Dao
 */
@RDBatisDao
public interface BankCardMapper extends BaseMapper<BankCard,Long> {
	
	BankCard findByUserId(Long userId);
	


}
