package com.xindaibao.cashloan.cl.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.jayway.jsonpath.JsonPath;
import com.xindaibao.cashloan.cl.domain.BankCard;
import com.xindaibao.cashloan.cl.domain.ClShuMeiBlacklist;
import com.xindaibao.cashloan.cl.domain.UserEquipmentInfo;
import com.xindaibao.cashloan.cl.mapper.ClShuMeiBlacklistMapper;
import com.xindaibao.cashloan.cl.service.BankCardService;
import com.xindaibao.cashloan.cl.service.ClShuMeiBlacklistService;
import com.xindaibao.cashloan.cl.service.UserEquipmentInfoService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.common.util.DateUtil;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.service.UserBaseInfoService;
import com.xindaibao.cashloan.rc.domain.TppBusiness;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 数美逾期黑名单ServiceImpl
 *
 * @author xx
 * @version 1.0.0
 * @date 2017-11-22 16:10:04
 */

@Service("clShuMeiBlacklistService")
public class ClShuMeiBlacklistServiceImpl extends BaseServiceImpl<ClShuMeiBlacklist, Long> implements ClShuMeiBlacklistService {

    public static final String IPHONE = "Iphone";
    public static final String HIT = "命中";
    public static final Integer SUCCESS = 1100;

    private static final Logger logger = LoggerFactory.getLogger(ClShuMeiBlacklistServiceImpl.class);

    @Resource
    private ClShuMeiBlacklistMapper clShuMeiBlacklistMapper;
    @Autowired
    private UserBaseInfoService userBaseInfoService;
    @Autowired
    private UserEquipmentInfoService userEquipmentInfoService;
    @Autowired
    private BankCardService bankCardService;

    @Override
    public BaseMapper<ClShuMeiBlacklist, Long> getMapper() {
        return clShuMeiBlacklistMapper;
    }

    public int queryShuMeiBlackList(Borrow borrow, TppBusiness business) {
        try {
            if (null == borrow || null == business) {
                logger.error("ShuMei blacklist borrow or business is null, borrow:" + borrow + "business" + business);
                return 0;
            }
            Map<String, Object> businessMap = JSONObject.parseObject(business.getExtend(), Map.class);

            final String APIHOST = business.getUrl();//接口地址 -- https://finance-api.fengkongcloud.com/v4/finance/overdue
            final String APIKEY = businessMap.get("apikey") + "";
            Long userId = borrow.getUserId();

            UserEquipmentInfo userEquipmentInfo = userEquipmentInfoService.findSelective(userId);
            //获取手机号
            UserBaseInfo baseInfo = userBaseInfoService.findByUserId(userId);
            //银行卡号
            BankCard bankCard = bankCardService.getBankCardByUserId(userId);

            Map<String, Object> map = build(APIKEY, userEquipmentInfo, baseInfo, bankCard);
            logger.info("数美请求参数:" + map);

            String result = sendRequest(APIHOST, map);

            return parse(result, userId);
        } catch (Exception e) {
            logger.error("shumei blacklist error:{}", e);
            return 0;
        }
    }


    private int parse(String rawData, Long userId) {
        String in_black = null;
        Integer itfin_loan_overdues = 0;
        Integer itfin_loan_overdue_duration = 0;
        Integer itfin_loan_overdues_7d = 0;
        Integer itfin_loan_overdue_duration_7d = 0;
        Integer itfin_loan_overdues_30d = 0;
        Integer itfin_loan_overdue_duration_30d = 0;
        Integer itfin_loan_overdues_60d = 0;
        Integer itfin_loan_overdue_duration_60d = 0;
        Integer itfin_loan_overdues_90d = 0;
        Integer itfin_loan_overdue_duration_90d = 0;
        Integer itfin_loan_overdues_180d = 0;
        Integer itfin_loan_overdue_duration_180d = 0;
        String requestId = null;

        try {
            Integer code = JsonPath.read(rawData, "$.code");
            requestId = JsonPath.read(rawData, "$.requestId");

            if (code.equals(SUCCESS)) {
                logger.info("SHUMEI - PARSE BEGIN");

                List<String> hits = JsonPath.read(rawData, "$.hits[*]");
                if (null != hits && hits.size() > 0) {
                    in_black = HIT;
                }

                Map<String, Object> map = JsonPath.read(rawData, "$.detail");
                List<Integer> levels = Lists.newArrayList();

                for (String level : map.keySet()) {
                    if (level.contains("duration")) {
                        levels.add(Integer.valueOf(level));
                    }
                    if (level.contains("loan.overdue.iftin.default.plts")) {//该用户在多少不同网贷平台发生了逾期
                        itfin_loan_overdues = Integer.valueOf(map.get(level).toString());
                    }
                    if (level.contains("loan.overdue.itfin.7d.plts")) {
                        itfin_loan_overdues_7d = Integer.valueOf(map.get(level).toString());
                    }
                    if (level.contains("loan.overdue.itfin.7d.duration")) {
                        itfin_loan_overdues_7d = Integer.valueOf(map.get(level).toString());
                    }
                    if (level.contains("loan.overdue.itfin.30d.plts")) {
                        itfin_loan_overdues_30d = Integer.valueOf(map.get(level).toString());
                    }
                    if (level.contains("loan.overdue.itfin.30d.duration")) {
                        itfin_loan_overdue_duration_30d = Integer.valueOf(map.get(level).toString());
                    }
                    if (level.contains("loan.overdue.itfin.60d.plts")) {
                        itfin_loan_overdues_60d = Integer.valueOf(map.get(level).toString());
                    }
                    if (level.contains("loan.overdue.itfin.60d.duration")) {
                        itfin_loan_overdue_duration_60d = Integer.valueOf(map.get(level).toString());
                    }
                    if (level.contains("loan.overdue.itfin.90d.plts")) {
                        itfin_loan_overdues_90d = Integer.valueOf(map.get(level).toString());
                    }
                    if (level.contains("loan.overdue.itfin.90d.duration")) {
                        itfin_loan_overdue_duration_90d = Integer.valueOf(map.get(level).toString());
                    }
                    if (level.contains("loan.overdue.itfin.180d.plts")) {
                        itfin_loan_overdues_180d = Integer.valueOf(map.get(level).toString());
                    }
                    if (level.contains("loan.overdue.itfin.180d.duration")) {
                        itfin_loan_overdue_duration_180d = Integer.valueOf(map.get(level).toString());
                    }

                }

                if (levels.size() > 0) {
                    Collections.sort(levels);
                    itfin_loan_overdue_duration = levels.get(levels.get(levels.size() - 1));
                }
                logger.info("SHUMEI - PARSE END");
            } else {
                return 0;
            }

        } catch (Exception e) {
            logger.error("数美黑名单Json解析失败！", e);
            return 0;
        } finally {
            ClShuMeiBlacklist clShuMeiBlacklist = new ClShuMeiBlacklist();
            clShuMeiBlacklist.setIn_black(in_black);
            clShuMeiBlacklist.setCreate_time(DateUtil.getNow());
            clShuMeiBlacklist.setItfin_loan_overdue_duration(itfin_loan_overdue_duration);
            clShuMeiBlacklist.setItfin_loan_overdues(itfin_loan_overdues);
            clShuMeiBlacklist.setItfin_loan_overdue_duration_7d(itfin_loan_overdue_duration_7d);
            clShuMeiBlacklist.setItfin_loan_overdues_7d(itfin_loan_overdues_7d);
            clShuMeiBlacklist.setItfin_loan_overdue_duration_30d(itfin_loan_overdue_duration_30d);
            clShuMeiBlacklist.setItfin_loan_overdues_30d(itfin_loan_overdues_30d);

            clShuMeiBlacklist.setItfin_loan_overdue_duration_60d(itfin_loan_overdue_duration_60d);
            clShuMeiBlacklist.setItfin_loan_overdues_60d(itfin_loan_overdues_60d);
            clShuMeiBlacklist.setItfin_loan_overdue_duration_90d(itfin_loan_overdue_duration_90d);
            clShuMeiBlacklist.setItfin_loan_overdues_90d(itfin_loan_overdues_90d);
            clShuMeiBlacklist.setItfin_loan_overdue_duration_180d(itfin_loan_overdue_duration_180d);
            clShuMeiBlacklist.setItfin_loan_overdues_180d(itfin_loan_overdues_180d);
            clShuMeiBlacklist.setUser_id(userId);
            clShuMeiBlacklist.setRequestId(requestId);
            clShuMeiBlacklist.setSubmit_params(rawData);
            return clShuMeiBlacklistMapper.save(clShuMeiBlacklist);
        }
    }

    private String sendRequest(String APIHOST, Map<String, Object> map) {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(APIHOST);

        StringEntity entity = new StringEntity(net.sf.json.JSONObject.fromObject(map).toString(), "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        post.setEntity(entity);
        HttpResponse response = null;
        try {
            response = client.execute(post);
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            logger.error("shumei blacklist error:{}", e);
            return "";
        }
    }

    private Map<String, Object> build(String APIKEY, UserEquipmentInfo userEquipmentInfo, UserBaseInfo baseInfo, BankCard bankCard) {
        Map<String, Object> body = new HashMap<String, Object>();

        body.put("accessKey", APIKEY);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("phone", baseInfo.getPhone());  // 手机号
        data.put("prcid", baseInfo.getIdNo());  // 身份证号
        data.put("cardid", bankCard.getCardNo());//银行卡号
        if (userEquipmentInfo.equals(IPHONE)) {
            data.put("idfa", userEquipmentInfo.getPhoneMark());
        } else {
            data.put("imei", userEquipmentInfo.getPhoneMark());  // 设备号
        }
        body.put("data", data);

        return body;

    }

    //UT
//	public static void main(String args[]){
//		String accessKey = "enwXlpfqqmLKX4rOJKkK";
//		String url = "https://finance-api.fengkongcloud.com/v4/finance/overdue";
//		String phone = "13508155482";
//		String imei = "864906032474531";
//		String prcid = "511102198103075319";
//		String cardid = "6217003650007563409";
//
//
//		HashMap<String, Object> body = new HashMap<String, Object>();
//
//		body.put("accessKey", accessKey);  // 替换<access-key>为自己的
//
//		// 在data中填写入API手册要求的字段
//		HashMap<String, Object> data = new HashMap<String, Object>();
//		data.put("phone", phone);  // 手机号
//		data.put("prcid", prcid);  // 身份证号
//		data.put("cardid", cardid);
//		data.put("imei", imei);  // 设备号
//		body.put("data", data);
//
//		HttpClient client = new DefaultHttpClient();
//		HttpPost post = new HttpPost(url);
//		StringEntity entity = new StringEntity(net.sf.json.JSONObject.fromObject(body).toString(), "utf-8");
//		entity.setContentEncoding("UTF-8");
//		entity.setContentType("application/json");
//		post.setEntity(entity);
//		try {
//			HttpResponse response = client.execute(post);
//			System.out.println(EntityUtils.toString(response.getEntity()));
//		}catch (Exception e) {
//
//		}
//	}


}