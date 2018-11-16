package com.xindaibao.cashloan.core.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.model.BorrowModel;
import org.apache.ibatis.annotations.Param;

import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.cashloan.core.domain.Borrow;

/**
 * 借款申请管理Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2016-12-08 15:27:00


 * 

 */
@RDBatisDao
public interface BorrowMapper extends BaseMapper<Borrow,Long> {

    /**
     * 查询借款申请
     * @param map
     * @return
     */
    List<BorrowModel> selectByConditions(Map<String,Object> map);
    
    /**
     * 根据用户标识和标的标识查询借款申请
     */
    List<BorrowModel> findByConsumerAndBorrow(String consumerNo,String borrowNo);
    
    /**
     * 自动审批查找需要比对的值
     * @param sql
     * @return
     */
    String findValidValue(@Param("statement")String statement);
    
   

  
}
