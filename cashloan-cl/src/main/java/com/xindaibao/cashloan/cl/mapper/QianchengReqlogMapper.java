package com.xindaibao.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.model.QianchengReqlogModel;
import org.apache.ibatis.annotations.Param;

import com.xindaibao.cashloan.cl.domain.QianchengReqlog;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 浅橙借款申请审核Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-15 11:46:54


 * 

 */
@RDBatisDao
public interface QianchengReqlogMapper extends BaseMapper<QianchengReqlog,Long> {
	
	/**
	 * 测试用（演示环境新增）
	 * @param qianchengReqlog
	 * @return
	 */
	int demoSave(QianchengReqlog qianchengReqlog);

    /**
     * 根据订单号查找日志
     * @param orderNo
     * @return
     */
	QianchengReqlog findByOrderNo(@Param("orderNo")String orderNo);
	
	
	/**
	 * 机审请求记录查询
	 * @param params
	 * return
	 */
	List<QianchengReqlogModel> listQianchengReqlog(Map<String, Object> params);
	
	/**
	 * 根据借款申请查找
	 * @param borrowId
	 * @return
	 */
	QianchengReqlog findByBorrowId(@Param("borrowId")Long borrowId);
	
}
