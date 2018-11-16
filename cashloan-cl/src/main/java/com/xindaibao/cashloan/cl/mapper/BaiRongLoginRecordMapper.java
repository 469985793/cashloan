package com.xindaibao.cashloan.cl.mapper;


import com.xindaibao.cashloan.cl.domain.BaiRongLoginRecord;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 银行卡Dao
 */
@RDBatisDao
public interface BaiRongLoginRecordMapper extends BaseMapper<BaiRongLoginRecord,Long> {
	
	String selectToken();

	void updateToken(String tokenId);
}
