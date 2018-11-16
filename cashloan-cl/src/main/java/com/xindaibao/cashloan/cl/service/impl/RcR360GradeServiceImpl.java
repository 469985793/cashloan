package com.xindaibao.cashloan.cl.service.impl;

import com.alibaba.fastjson.JSON;
import com.xindaibao.cashloan.cl.domain.RcR360BlacklistLog;
import com.xindaibao.cashloan.cl.domain.Rong360Grade;
import com.xindaibao.cashloan.cl.mapper.RcR360BlacklistLogMapper;
import com.xindaibao.cashloan.cl.mapper.Rong360GradeMapper;
import com.xindaibao.cashloan.cl.sdk.face.R360AntiFraud;
import com.xindaibao.cashloan.cl.sdk.face.R360BlackList;
import com.xindaibao.cashloan.cl.sdk.face.dto.R360BlackListRespDTO;
import com.xindaibao.cashloan.cl.sdk.face.dto.R360GradeRespDTO;
import com.xindaibao.cashloan.cl.service.RcR360BlacklistLogService;
import com.xindaibao.cashloan.cl.service.RcR360GradeService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.service.UserBaseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 融360评分表
 *
 * @version 1.0.0
 * @date 2017-05-24 10:28:22
 * Copyright zuoli company  cashloan All Rights Reserved
 */

@Slf4j
@Service("RcR360GradeService")
public class RcR360GradeServiceImpl extends BaseServiceImpl<Rong360Grade, Long> implements RcR360GradeService {


    @Resource
    private Rong360GradeMapper rong360GradeMapper;
    @Resource
    private UserBaseInfoService userBaseInfoService;

    @Override
    public BaseMapper<Rong360Grade, Long> getMapper() {
        return rong360GradeMapper;
    }

    @SuppressWarnings("unchecked")
    @Override
    public int queryR360Grade(Long userId) {
        UserBaseInfo baseInfo = userBaseInfoService.findByUserId(userId);
        Rong360Grade rong360Grade = new Rong360Grade();
        rong360Grade.setCreateTime(new Date());
        rong360Grade.setUserId(userId);
        try {
            String request = R360AntiFraud.request(baseInfo);
            R360GradeRespDTO r360GradeRespDTO = JSON.parseObject(request, R360GradeRespDTO.class);
            if (StringUtils.equals("200", r360GradeRespDTO.getError())) {
                rong360Grade.setScore(Double.valueOf(r360GradeRespDTO.getTianji_api_agenty_AntiFraud_response().getScore()).intValue());
                rong360Grade.setTag(r360GradeRespDTO.getTianji_api_agenty_AntiFraud_response().getTag());
            } else if (StringUtils.equals("10002", r360GradeRespDTO.getError())) {
                rong360Grade.setScore(998);
            } else {
                rong360Grade.setScore(999);
            }
            rong360Grade.setRemark(r360GradeRespDTO.getMsg());

        } catch (Exception e) {
            log.error("request grade exception, data={}", e);
            rong360Grade.setScore(999);
            rong360Grade.setRemark("request grade exception, data=" + e.getMessage());
        }
       return rong360GradeMapper.save(rong360Grade);
    }

    @Override
    public Rong360Grade getById(Long aLong) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("userId", aLong);
        return rong360GradeMapper.findSelective(paramMap);
    }

}