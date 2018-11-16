package com.xindaibao.cashloan.rc.mapper;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.cashloan.rc.domain.SimpleBorrowCount;

/**
 * 风控数据统计-（简）借款统计Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-07-06 18:12:18



 */
@RDBatisDao
public interface SimpleBorrowCountMapper extends BaseMapper<SimpleBorrowCount, Long> {

    /**
     * 借款人有逾期30天以上已还借款数
     * @param userId
     * @return
     */
    int countOne(long userId);

}
