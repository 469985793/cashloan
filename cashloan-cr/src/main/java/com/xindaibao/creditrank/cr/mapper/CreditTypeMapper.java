package com.xindaibao.creditrank.cr.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.creditrank.cr.domain.CreditType;
import com.xindaibao.creditrank.cr.model.CreditRatingModel;
import com.xindaibao.creditrank.cr.model.CreditTypeModel;
import org.apache.ibatis.annotations.Param;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 额度类型管理Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-18 16:43:13


 * 

 */
@RDBatisDao
public interface CreditTypeMapper extends BaseMapper<CreditType,Long> {

    /**
     * 查询详情信息，必须包含ID
     * @param creditType
     * @return
     */
	CreditTypeModel findCreditTypeInfo(CreditType creditType);
	
	/**
	 * 查询未被额度类型关联的借款类型
	 * @return
	 */
	List<Map<Long, String>> findUnUsedBorrowType();
	
	/**
	 * 查询额度类型编辑页面可以显示的借款类型
	 * @param typeId
	 * @return
	 */
	List<CreditRatingModel> findEditBorrowType(@Param("typeId")Long typeId);
	
	/**
	 * 根据借款类型查询额度类型记录
	 * @param borrowTypeId
	 * @return
	 */
	CreditTypeModel findByBorrowTypeId(@Param("borrowTypeId")Long borrowTypeId);
}
