package com.xindaibao.cashloan.cl.mapper;

import com.xindaibao.cashloan.cl.model.kenya.DicArea;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

import java.util.List;
import java.util.Map;

/**
 * 省市区查询
 */
@RDBatisDao
public interface DicAreaMapper extends BaseMapper<DicArea, Long> {

    /**
     * 列表查询
     * @param paramMap
     * @return
     */
    List<DicArea> page(Map<String, Object> paramMap);
}