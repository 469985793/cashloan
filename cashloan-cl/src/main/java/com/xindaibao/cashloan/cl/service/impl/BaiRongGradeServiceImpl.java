package com.xindaibao.cashloan.cl.service.impl;

import com.alibaba.fastjson.JSON;
import com.bfd.facade.MerchantServer;
import com.xindaibao.cashloan.cl.domain.BaiRongGrade;
import com.xindaibao.cashloan.cl.mapper.BaiRongGradeMapper;
import com.xindaibao.cashloan.cl.mapper.BaiRongLoginRecordMapper;
import com.xindaibao.cashloan.cl.sdk.bairong.model.BaiRongRradeRespDTO;
import com.xindaibao.cashloan.cl.service.BaiRongGradeService;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.service.UserBaseInfoService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiezhao on 2017/10/21.
 */
@Slf4j
@Service("BaiRongGradeService")
public class BaiRongGradeServiceImpl implements BaiRongGradeService {

    @Autowired private BaiRongLoginRecordMapper baiRongLoginRecordMapper;
    @Autowired private BaiRongGradeMapper baiRongGradeMapper;
    @Autowired private UserBaseInfoService userBaseInfoService;

    @Override
    public String findToken() {
        return baiRongLoginRecordMapper.selectToken();
    }

    @Override
    public int requestGrade(Long userId) {
        UserBaseInfo baseInfo = userBaseInfoService.findByUserId(userId);
        MerchantServer ms = new MerchantServer();
        String token = findToken();
        if (StringUtils.isEmpty(token)) {
            token = requestBaiRongTokenId(ms);
            baiRongLoginRecordMapper.updateToken(token);
        }

        BaiRongGrade baiRongGrade = BaiRongGrade.of(userId);

        try {
            BaiRongRradeRespDTO respDTO = excuteQuery(token, ms, baseInfo);
            log.info("bairong grade response = {}", respDTO.toString());
            if (StringUtils.equals("00", String.valueOf(respDTO.getCode()))){
                BeanUtils.copyProperties(respDTO, baiRongGrade);
            } else if(StringUtils.equals("100002", String.valueOf(respDTO.getCode()))){
                baiRongGrade.setBrcreditpoint(998);
                baiRongGrade.setRemark("查询key值未命中数据库");
            }else {
                baiRongGrade.setBrcreditpoint(999);
                baiRongGrade.setRemark("request code:" + respDTO.getCode());
            }
        } catch (Exception e) {
            log.error("request bairong grade fail, error={}", e);
            baiRongGrade.setBrcreditpoint(999);
            baiRongGrade.setRemark("请求异常，写入默认999分，error=" + e.getMessage());
        }

        return baiRongGradeMapper.save(baiRongGrade);
    }


    private BaiRongRradeRespDTO excuteQuery(String token, MerchantServer ms, UserBaseInfo userBaseInfo) throws Exception {
        String modelName = Global.getValue("baiRong_modelName");
        String apiName = Global.getValue("baiRong_apiName");
        String apiCode = Global.getValue("baiRong_apiCode");

        //用户报告：
        JSONObject jso = new JSONObject();
        JSONObject reqData = new JSONObject();
        jso.put("apiName", apiName);
        jso.put("tokenid", token);//tokenid一小时未查询报告，则过期，code码返回100007。需要重新登录获取新的tokenid。
        reqData.put("meal", "SpecialList_c," + modelName);//此参数为设置客户需要查询的模块：可设置单模块查询也可以设置多模块查询。不设置此参数为所有模块都查询。
        reqData.put("id",userBaseInfo.getIdNo());
        reqData.put("cell", userBaseInfo.getPhone());
        reqData.put("name", userBaseInfo.getRealName());
        jso.put("reqData", reqData);
        String portrait_result = ms.getApiData(jso.toString(),apiCode);
        BaiRongRradeRespDTO respDTO = JSON.parseObject(portrait_result, BaiRongRradeRespDTO.class);
        return respDTO;
    }

    private String requestBaiRongTokenId(MerchantServer ms) {
        String token;
        String userName = Global.getValue("baiRong_userName");
        String pwd = Global.getValue("baiRong_pwd");
        String loginName = Global.getValue("baiRong_loginName");
        String apiCode = Global.getValue("baiRong_apiCode");
        String login_result= null;
        try {
            login_result = ms.login(userName,pwd,loginName,apiCode);
        } catch (Exception e) {
            log.error("login bairong error = {}", e);
        }
        log.info("bairong login info = {}", login_result);
        JSONObject json=JSONObject.fromObject(login_result);
        token = json.getString("tokenid");
        return token;
    }

    @Override
    public void updateToken(String token) {
        baiRongLoginRecordMapper.updateToken(token);
    }

    @Override
    public int insert(BaiRongGrade record) {
        return baiRongGradeMapper.save(record);
    }

    @Override
    public int updateById(BaiRongGrade record) {
        return 0;
    }

    @Override
    public BaiRongGrade getById(Long aLong) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("userId", aLong);
        return baiRongGradeMapper.findSelective(paramMap);
    }
}
