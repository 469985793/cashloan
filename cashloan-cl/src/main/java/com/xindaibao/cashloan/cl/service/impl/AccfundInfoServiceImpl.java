package com.xindaibao.cashloan.cl.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.mapper.AccfundInfoMapper;
import com.xindaibao.cashloan.cl.service.AccfundInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tool.util.StringUtil;

import com.alibaba.fastjson.JSONObject;
import com.xindaibao.cashloan.cl.domain.AccfundInfo;
import com.xindaibao.cashloan.cl.domain.AccfundLog;
import com.xindaibao.cashloan.cl.mapper.AccfundLogMapper;
import com.xindaibao.cashloan.cl.mapper.UserAuthMapper;
import com.xindaibao.cashloan.core.common.exception.BussinessException;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.mapper.UserBaseInfoMapper;


/**
 * 公积金基本信息表ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-05-16 10:56:12




 */
 
@Service("accfundInfoService")
public class AccfundInfoServiceImpl extends BaseServiceImpl<AccfundInfo, Long> implements AccfundInfoService {
	
    private static final Logger logger = LoggerFactory.getLogger(AccfundInfoServiceImpl.class);
   
    @Resource
    private AccfundInfoMapper accfundInfoMapper;
    @Resource
    private UserBaseInfoMapper userBaseInfoMapper;
    @Resource
    private AccfundLogMapper accfundLogMapper;
    @Resource
    private UserAuthMapper userAuthMapper;

	@Override
	public BaseMapper<AccfundInfo, Long> getMapper() {
		return accfundInfoMapper;
	}

	@Override
	public void saveAccfundInfos(String res, Long userId, Date createTime) {
		if(StringUtil.isNotBlank(res)){
			Map<String, Object> resJson = JSONObject.parseObject(res);
			
//			UserBaseInfo basicInfo = null;
//			if(userId!=null && userId>0){
//				Map<String,Object> params = new HashMap<String, Object>();
//				params.put("userId", userId);
//				basicInfo = userBaseInfoMapper.findSelective(params);
//			}
			AccfundInfo accfundInfo=null;
			String basic_info=String.valueOf(resJson.get("basic_info_house_accumulation_fund_type_id"));
			if(StringUtil.isNotBlank(basic_info)){
				List<AccfundInfo> infoList=JSONObject.parseArray(basic_info,AccfundInfo.class);
				if(infoList!=null&&!infoList.isEmpty()){
					accfundInfo=infoList.get(0);
				}
			}
			
//			if(basicInfo!=null&&StringUtil.isNotBlank(basicInfo.getIdNo())&&accfundInfo!=null&&StringUtil.isNotBlank(accfundInfo.getIdCard())&&basicInfo.getIdNo().equals(accfundInfo.getIdCard())){
			if(accfundInfo!=null){
				accfundInfo.setUserId(userId);
				accfundInfo.setCreateTime(createTime);
				insert(accfundInfo);
				String detailInfos=String.valueOf(resJson.get("detail_info_house_accumulation_fund_type_id"));
				if(StringUtil.isNotBlank(detailInfos)){
					List<AccfundLog> accfundLogs=JSONObject.parseArray(detailInfos,AccfundLog.class);
					if(accfundLogs!=null && accfundLogs.size()>0){
						for(AccfundLog accfundLog:accfundLogs){
							accfundLog.setUserId(userId);
							accfundLog.setCreateTime(createTime);
							accfundLogMapper.save(accfundLog);
						}
					}
				}
				Map<String,Object> userAuth = new HashMap<String, Object>();
				userAuth.put("userId", userId);
				userAuth.put("accFundState", "30");
				userAuthMapper.updateByUserId(userAuth);
				logger.info("公积金异步回调，userId:"+userId+"，成功将userAuth.accFundState的状态改为20");
			}else{
				throw new BussinessException("回调数据与用户数据不匹配,回调数据"+res);
			}
		}else{
			logger.equals("用户【"+userId+"】公积金认证异步回调信息没有返回res，处理失败");
		}
	}
	
}