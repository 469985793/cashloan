package com.xindaibao.cashloan.cl.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xindaibao.cashloan.cl.domain.RcHuadaoBlacklistLog;
import com.xindaibao.cashloan.cl.domain.RcR360BlacklistLog;
import com.xindaibao.cashloan.cl.mapper.RcHuadaoBlacklistLogMapper;
import com.xindaibao.cashloan.cl.mapper.RcR360BlacklistLogMapper;
import com.xindaibao.cashloan.cl.sdk.face.R360BlackList;
import com.xindaibao.cashloan.cl.sdk.face.dto.R360BlackListRespDTO;
import com.xindaibao.cashloan.cl.service.RcHuadaoBlacklistLogService;
import com.xindaibao.cashloan.cl.service.RcR360BlacklistLogService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.service.UserBaseInfoService;
import com.xindaibao.cashloan.rc.domain.TppBusiness;
import credit.CreditRequest;
import credit.Header;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 华道黑名单记录表ServiceImpl
 *
 * @version 1.0.0
 * @date 2017-05-24 10:28:22
 * Copyright zuoli company  cashloan All Rights Reserved
 */

@Slf4j
@Service("rcR360BlacklistLogService")
public class RcR360BlacklistLogServiceImpl extends BaseServiceImpl<RcR360BlacklistLog, Long> implements RcR360BlacklistLogService {


    @Resource
    private RcR360BlacklistLogMapper rcR360BlacklistLogMapper;
    @Resource
    private UserBaseInfoService userBaseInfoService;

    @Override
    public BaseMapper<RcR360BlacklistLog, Long> getMapper() {
        return rcR360BlacklistLogMapper;
    }

    @SuppressWarnings("unchecked")
    @Override
    public int queryR360BlackList(Long userId) {

        //获取手机号
        UserBaseInfo baseInfo = userBaseInfoService.findByUserId(userId);

        RcR360BlacklistLog rcR360BlacklistLog = new RcR360BlacklistLog();
        rcR360BlacklistLog.setCreateTime(new Date());
        rcR360BlacklistLog.setUserId(userId);

        try {
            String request = R360BlackList.request(baseInfo);
            JSONObject jsonObject = JSONObject.parseObject(request);
            R360BlackListRespDTO r360BlackListRespDTO = JSON.parseObject(request, R360BlackListRespDTO.class);
            if (r360BlackListRespDTO.isSuccess()) {
                String response = jsonObject.get("tianji_api_agentr_blacklist_response").toString();
                rcR360BlacklistLog.setIsBlack("{}".equalsIgnoreCase(response) ? "false" : "true");
            } else {
                // FIXME: 2017/10/21 请求不为200.设置为 false，走下一个第三方黑名单验证（知道是否是true or false）
                rcR360BlacklistLog.setIsBlack("false");
            }
            rcR360BlacklistLog.setRespCode(r360BlackListRespDTO.getError());
            rcR360BlacklistLog.setRespParams(r360BlackListRespDTO.toString());
        } catch (Exception e) {
            log.error("request rong360 blacklist exception, error={}", e);
            rcR360BlacklistLog.setIsBlack("false");
            rcR360BlacklistLog.setRespParams(e.getMessage());
            rcR360BlacklistLog.setRespCode("9999");
        }
        return rcR360BlacklistLogMapper.save(rcR360BlacklistLog);
    }

    @Override
    public RcR360BlacklistLog getById(Long aLong) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("userId", aLong);
        return rcR360BlacklistLogMapper.findSelective(paramMap);
    }

}