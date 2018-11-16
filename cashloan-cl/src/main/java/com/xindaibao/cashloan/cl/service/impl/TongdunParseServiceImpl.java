package com.xindaibao.cashloan.cl.service.impl;

import com.google.common.collect.Maps;
import com.jayway.jsonpath.JsonPath;
import com.xindaibao.cashloan.cl.domain.ClFraudTdHitList;
import com.xindaibao.cashloan.cl.domain.ClFraudTdMultiPlatform;
import com.xindaibao.cashloan.cl.mapper.ClFraudTdHitListMapper;
import com.xindaibao.cashloan.cl.mapper.ClFraudTdMultiPlatformMapper;
import com.xindaibao.cashloan.cl.service.TongdunParseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("tongdunParseService")
public class TongdunParseServiceImpl implements TongdunParseService {

    @Autowired
    private ClFraudTdHitListMapper clFraudTdHitListMapper;
    @Autowired
    private ClFraudTdMultiPlatformMapper clFraudTdMultiPlatformMapper;

    @Override
    public void parse(String rawData, String reportId, Long userId) {
        log.info("TONGDUN - PARSE BEGIN");
        Long reportTime = JsonPath.read(rawData, "$.report_time");
        Date reportDate = new Date(reportTime);

        List<String> listTypes = JsonPath.read(rawData, LIST_TYPE_PATH);
        List<String> fraudTypes = JsonPath.read(rawData, RISK_TYPE_PATH);

        parseClFraudTdHitList(rawData, listTypes, fraudTypes, reportId, userId, reportDate);
        parseClFraudTdMultiPlatform(rawData, reportId, userId, reportDate);

        log.info("TONGDUN - PARSE END");
    }

    private void parseClFraudTdMultiPlatform(String rawData, String reportId, Long userId, Date reportDate) {
        Integer multi_platform_small_loan_cnt_7d = 0; //group: "多平台借贷申请检测"  -> "item_name": "7天内申请人在多个平台申请借款" // -> "platform_detail" - >"小额贷款公司:1", 数据缺省0  risk_items->item_detail->platform_detail
        Integer multi_platform_p2p_cnt_7d = 0; //group: "多平台借贷申请检测"  -> "item_name": "7天内申请人在多个平台申请借款" -> "platform_detail" - >"P2P网贷:1", 数据缺省0
        Integer multi_platform_consumer_cnt_7d = 0; //group: "多平台借贷申请检测"  -> "item_name": "7天内申请人在多个平台申请借款" -> "platform_detail" - >"大型消费金融公司:1", 数据缺省0
        Integer multi_platform_installment_cnt_7d = 0; //group: "多平台借贷申请检测"  -> "item_name": "7天内申请人在多个平台申请借款" -> "platform_detail" - >"一般消费分期平台:1", 数据缺省0
        Integer multi_platform_portals_cnt_7d = 0; //group: "多平台借贷申请检测"  -> "item_name": "7天内申请人在多个平台申请借款" -> "platform_detail" - >"互联网联网金融门户:1", 数据缺省0

        Integer multi_platform_idcard_cnt_7d = 0; //"group": "多平台借贷申请检测" -> "item_name": "7天内申请人在多个平台申请借款"-> "dimension": "借款人身份证详情" - >"count":  ， 数据缺省0
        Integer multi_platform_mob_cnt_7d = 0; //"group": "多平台借贷申请检测" -> "item_name": "7天内申请人在多个平台申请借款"-> "dimension": "借款人手机详情" - >"count":  ， 数据缺省0
        Integer multi_platform_device_cnt_7d = 0; //"group": "多平台借贷申请检测" -> "item_name": "7天内申请人在多个平台申请借款"-> "dimension": "设备ID详情" - >"count":  ， 数据缺省0

        Integer multi_platform_cnt_7d = 0; //"group": "多平台借贷申请检测" -> "item_name": "7天内申请人在多个平台申请借款"->"count":  ， 数据缺省0
        Integer multi_platform_cnt_30d = 0; //"group": "多平台借贷申请检测" -> "item_name": "一个月内申请人在多个平台申请借款"->"count":  ， 数据缺省0
        Integer multi_platform_cnt_90d = 0; //"group": "多平台借贷申请检测" -> "item_name": "三个月申请人在多个平台申请借款"->"count":  ， 数据缺省0
        Integer multi_platform_approved_cnt_90d = 0; //"group": "多平台借贷负债检测" -> "item_name": "3个月内申请人在多个平台被放款_不包含本合作方" -> "platform_count": 1，数据缺省0

        try {
            List<String> rawList = JsonPath.read(rawData,
                    "$.risk_items[?(@.group=='多平台借贷申请检测' && @.item_name=='7天内申请人在多个平台申请借款')].item_detail.platform_detail[*]");
            for (String detail : rawList) {
                String trimed = StringUtils.trim(detail);
                if (StringUtils.startsWithIgnoreCase(trimed, "小额贷款公司")) {
                    multi_platform_small_loan_cnt_7d = extractByDesc(detail, "小额贷款公司");
                }
                if (StringUtils.startsWithIgnoreCase(trimed, "P2P网贷")) {
                    multi_platform_p2p_cnt_7d = extractByDesc(detail, "P2P网贷");
                }
                if (StringUtils.startsWithIgnoreCase(trimed, "大型消费金融公司")) {
                    multi_platform_consumer_cnt_7d = extractByDesc(detail, "大型消费金融公司");
                }
                if (StringUtils.startsWithIgnoreCase(trimed, "一般消费分期平台")) {
                    multi_platform_installment_cnt_7d = extractByDesc(detail, "一般消费分期平台");
                }
                if (StringUtils.startsWithIgnoreCase(trimed, "互联网金融门户")) {
                    multi_platform_portals_cnt_7d = extractByDesc(detail, "互联网金融门户");
                }
            }

            List<net.minidev.json.JSONArray> rawJsonArrayList = JsonPath.read(rawData,
                    "$.risk_items[?(@.group=='多平台借贷申请检测' && @.item_name=='7天内申请人在多个平台申请借款')].item_detail.platform_detail_dimension");
            if (!CollectionUtils.isEmpty(rawJsonArrayList)) {
                net.minidev.json.JSONArray jsonArray = rawJsonArrayList.get(0);
                for (int i = 0; i < jsonArray.size(); i++) {
                    Object jsonObject = jsonArray.get(i);
                    Map<String, Object> map = (Map<String, Object>) jsonObject;

                    String dimensionVaule = (String) map.get("dimension");
                    if (StringUtils.equals(dimensionVaule, "借款人身份证详情")) {
                        Integer count = (Integer) map.get("count");

                        multi_platform_idcard_cnt_7d = count;
                    }
                    if (StringUtils.equals(dimensionVaule, "借款人手机详情")) {
                        Integer count = (Integer) map.get("count");

                        multi_platform_mob_cnt_7d = count;
                    }
                    if (StringUtils.equals(dimensionVaule, "设备ID详情")) {
                        Integer count = (Integer) map.get("count");

                        multi_platform_device_cnt_7d = count;
                    }
                }
            }


            // FIXME: 15/11/2017 生产数据找不到 count 在该节点下
            // FIXME: 16/11/2017 核对后修复
            List<Integer> cnt_7dList = JsonPath.read(rawData,
                    "$.risk_items[?(@.group=='多平台借贷申请检测' && @.item_name=='7天内申请人在多个平台申请借款')].item_detail.platform_count");
            multi_platform_cnt_7d = CollectionUtils.isEmpty(cnt_7dList) ? 0 : cnt_7dList.get(0);

            List<Integer> cnt_30dList = JsonPath.read(rawData,
                    "$.risk_items[?(@.group=='多平台借贷申请检测' && @.item_name=='1个月内申请人在多个平台申请借款')].item_detail.platform_count");
            multi_platform_cnt_30d = CollectionUtils.isEmpty(cnt_30dList) ? 0 : cnt_30dList.get(0);

            List<Integer> cnt_90dList = JsonPath.read(rawData,
                    "$.risk_items[?(@.group=='多平台借贷申请检测' && @.item_name=='3个月申请人在多个平台申请借款')].item_detail.platform_count");
            multi_platform_cnt_90d = CollectionUtils.isEmpty(cnt_90dList) ? 0 : cnt_90dList.get(0);


            List<Integer> approved_cnt_90dList = JsonPath.read(rawData,
                    "$.risk_items[?(@.group=='多平台借贷负债检测' && @.item_name=='3个月内申请人在多个平台被放款_不包含本合作方')].item_detail.platform_count");
            multi_platform_approved_cnt_90d = CollectionUtils.isEmpty(approved_cnt_90dList) ? 0 : approved_cnt_90dList.get(0);


        } catch (Exception e) {
            log.error("Parse ClFraudTdMultiPlatform error. input data is: {}", rawData, e);
        } finally {
            ClFraudTdMultiPlatform clFraudTdMultiPlatform = new ClFraudTdMultiPlatform();

            clFraudTdMultiPlatform.setMulti_platform_small_loan_cnt_7d(multi_platform_small_loan_cnt_7d);
            clFraudTdMultiPlatform.setMulti_platform_p2p_cnt_7d(multi_platform_p2p_cnt_7d);
            clFraudTdMultiPlatform.setMulti_platform_consumer_cnt_7d(multi_platform_consumer_cnt_7d);
            clFraudTdMultiPlatform.setMulti_platform_installment_cnt_7d(multi_platform_installment_cnt_7d);
            clFraudTdMultiPlatform.setMulti_platform_portals_cnt_7d(multi_platform_portals_cnt_7d);

            clFraudTdMultiPlatform.setMulti_platform_cnt_7d(multi_platform_cnt_7d);
            clFraudTdMultiPlatform.setMulti_platform_cnt_30d(multi_platform_cnt_30d);
            clFraudTdMultiPlatform.setMulti_platform_cnt_90d(multi_platform_cnt_90d);
            clFraudTdMultiPlatform.setMulti_platform_approved_cnt_90d(multi_platform_approved_cnt_90d);

            clFraudTdMultiPlatform.setMulti_platform_idcard_cnt_7d(multi_platform_idcard_cnt_7d);
            clFraudTdMultiPlatform.setMulti_platform_mob_cnt_7d(multi_platform_mob_cnt_7d);
            clFraudTdMultiPlatform.setMulti_platform_device_cnt_7d(multi_platform_device_cnt_7d);

            clFraudTdMultiPlatform.setUser_id(userId);
            clFraudTdMultiPlatform.setReport_id(reportId);
            clFraudTdMultiPlatform.setReport_date(reportDate);

            Map<String, Object> param = Maps.newHashMap();
            param.put("report_id", reportId);

            List<ClFraudTdMultiPlatform> clFraudTdMultiPlatformList = clFraudTdMultiPlatformMapper.listSelective(param);
            if (clFraudTdMultiPlatformList == null || clFraudTdMultiPlatformList.isEmpty()) {
                clFraudTdMultiPlatformMapper.save(clFraudTdMultiPlatform);
            } else {
                clFraudTdMultiPlatform.setId(clFraudTdMultiPlatformList.get(0).getId());
                clFraudTdMultiPlatformMapper.update(clFraudTdMultiPlatform);
            }
        }
    }

    private void parseClFraudTdHitList(String rawData, List<String> listTypes, List<String> fraudTypes, String reportId, Long userId, Date reportDate) {
        Integer idcard_hit_execution_list = 0;   //"风险类型:法院执行 命中次数 命中 1 未命中 0 缺省值 -1
        List<String> executionList = JsonPath.read(rawData, "$.risk_items.[*].item_detail.namelist_hit_details[*].court_details[?(@.fraud_type=='法院执行')]");
        if (executionList != null && !CollectionUtils.isEmpty(executionList)) {
            idcard_hit_execution_list = executionList.size();
        }

        Integer idcard_hit_court_list = 0; //风险类型:法院失信 命中次数 命中 1 未命中 0 缺省值 -1
        List<String> courtList = JsonPath.read(rawData, "$.risk_items.[*].item_detail.namelist_hit_details[*].court_details[?(@.fraud_type=='法院失信')]");
        if (courtList != null && !CollectionUtils.isEmpty(courtList)) {
            idcard_hit_court_list = courtList.size();
        }

        //暂缓
        Integer device_hit_black_list = 0; //black_list 设备号命中信贷逾期黑名单。命中 1 未命中 0 缺省值 -1  risk_items->item_detail->namelist_hit_details->type
        //暂缓
        Integer mob_hit_black_list = 0; // "fraud_type": "电信欺诈" 手机号命中欺诈名单 命中 1 未命中 0 缺省值 -1 risk_items->item_detail->fraud_type

        Integer idcard_hit_black_list = 0; // "fraud_type": "电信欺诈" 身份证命中欺诈名单 命中 1 未命中 0 缺省值 -1
        List<Integer> idcardBlackList = JsonPath.read(rawData, "$.risk_items[?(@.item_name=='身份证命中高风险关注名单')].item_detail.namelist_hit_details[?(@.fraud_type=='电信欺诈')]");
        if (idcardBlackList != null && !CollectionUtils.isEmpty(idcardBlackList)) {
            idcard_hit_black_list = idcardBlackList.size();
        }

        Integer bank_hit_black_list = 0; //black_list 银行卡号命中信贷逾期黑名单 命中 1 未命中 0 缺省值 -1
        Integer mob_cnt_hit_black_list = 0; //"group": "风险信息扫描" - > "item_name": "手机号命中信贷逾期名单" -> discredit_times ；数据缺省0   risk_items->item_detail->discredit_times
        Integer idcard_cnt_hit_black_list = 0; //"group": "风险信息扫描" - > "item_name": "身份证命中信贷逾期名单" -> discredit_times ；数据缺省0
        Integer bank_mob_hit_black_list = 0; //black_list 银行卡预留手机号命中信贷逾期黑名单 命中 1 未命中 0 缺省值 -1
        // FIXME: 15/11/2017 需求暂缓
        Integer mob_overdue_day_max = 0; //"group": "风险信息扫描" - > "item_name": "手机号命中信贷逾期名单" -> overdue_details -> overdue_day_range ；若存在多个取值，则求最大值；数据缺省0
        // FIXME: 取overdueList最小值 "overdue_day_range": "(270, 360]"
        List<String> overdueList = JsonPath.read(rawData, "$.risk_items[?(@.group=='风险信息扫描' && @.item_name=='手机号命中信贷逾期名单')].item_detail.overdue_details[*].overdue_day_range");
        try {
            if (overdueList != null && !CollectionUtils.isEmpty(overdueList) && overdueList.get(0).contains("(")) {

                String value[] = StringUtils.split(overdueList.get(0), ",");
                if (value == null) {
                    log.warn("data error, splited is null. {}", overdueList.get(0));
                    mob_overdue_day_max = 0;
                }

                value = StringUtils.split(StringUtils.trim(value[0]), "(");
                if (value == null) {
                    log.warn("data error, splited is null. {}", overdueList.get(0));
                    mob_overdue_day_max = 0;
                }
                if (value.length == 1) {
                    mob_overdue_day_max = Integer.valueOf(value[0]);
                } else {
                    log.warn("data error. {}", overdueList.get(0));
                }
            } else if (overdueList != null && !CollectionUtils.isEmpty(overdueList) && overdueList.get(0).contains("+")) {
                mob_overdue_day_max = Integer.valueOf(overdueList.get(0).replace("+", ""));
           //FIXME:2017/11/17 ,有逾期记录,同盾没有返回,默认60
            }else if(overdueList != null && !CollectionUtils.isEmpty(overdueList) ){
                mob_overdue_day_max = 60;
            }
        } catch (Exception e) {
            mob_overdue_day_max = 0;
            log.error("data error",e);
        }

        Integer idcard_overdue_day_max = 0; //"group": "风险信息扫描" - > "item_name": "身份证命中信贷逾期名单" -> overdue_details -> overdue_day_range ；若存在多个取值，则求最大值；数据缺省0
        // FIXME: 取overdueList最小值 "overdue_day_range": "(270, 360]"
        List<String> idcardOverdueList = JsonPath.read(rawData, "$.risk_items[?(@.group=='风险信息扫描' && @.item_name=='身份证命中信贷逾期名单')].item_detail.overdue_details[*].overdue_day_range");
        try {
            if (idcardOverdueList != null && !CollectionUtils.isEmpty(idcardOverdueList) && idcardOverdueList.get(0).contains("(")) {
                String value[] = StringUtils.split(idcardOverdueList.get(0), ",");
                if (value == null) {
                    log.warn("data error, splited is null. {}", idcardOverdueList.get(0));
                    idcard_overdue_day_max = 0;
                }

                value = StringUtils.split(StringUtils.trim(value[0]), "(");
                if (value == null) {
                    log.warn("data error, splited is null. {}", idcardOverdueList.get(0));
                    idcard_overdue_day_max = 0;
                }
                if (value.length == 1) {
                    idcard_overdue_day_max = Integer.valueOf(value[0]);
                } else {
                    log.warn("data error. {}", idcardOverdueList.get(0));
                }
            } else if (idcardOverdueList != null && !CollectionUtils.isEmpty(idcardOverdueList) && idcardOverdueList.get(0).contains("+")) {
                idcard_overdue_day_max = Integer.valueOf(idcardOverdueList.get(0).replace("+", ""));
                //FIXME:2017/11/17 ,有逾期记录,同盾没有返回,默认60
            }else if(idcardOverdueList != null && !CollectionUtils.isEmpty(idcardOverdueList)){
                idcard_overdue_day_max = 60;
            }
        } catch (Exception e) {
            idcard_overdue_day_max = 0;
            log.error("data error",e);
        }
        Integer mob_hit_fuzzy_list = LACK; //fuzzy_list “手机号命中模糊名单"”命中 1 未命中 0 缺省值 -1
        Integer idcard_hit_fuzzy_list = LACK; //fuzzy_list  “身份证号命中模糊名单"”命中 1 未命中 0 缺省值 -1
        Integer mob_hit_grey_list = LACK; //grey_list “手机号命中低风险关注名单”” + “手机号命中中风险关注名单”” 求和 缺省值 0   risk_items->item_detail->namelist_hit_details->type
        Integer idcard_hit_grey_list = 0; //grey_list “身份证号命低低风险关注名单”” + “手机号命中低风险关注名单”” 求和 缺省值 0

        Integer frequency_detail_mob_idcard_cnt_90d = 0; //“group":"客户行为检测" -> “type":"frequency_detail" ->"detail": "3个月身份证关联手机号数：2",// ，数据缺省0   risk_items->item_detail->frequency_detail_list->detail
        Integer frequency_detail_email_idcard_cnt_90d = 0; //“group":"客户行为检测" -> "type":"frequency_detail" -> "detail": "3个月身份证关联邮箱数：1"，数据缺省0
        Integer frequency_detail_homeaddr_idcard_cnt_90d = 0; //“group":"客户行为检测"  “type":"frequency_detail" -> "detail": "3个月身份证关联家庭地址数：6"，数据缺省0
        Integer frequency_detail_device_idcard_cnt_1d = 0; //“group":"客户行为检测" ，"type":"frequency_detail" ->"detail": "1天内身份证关联设备ID数：6"，，数据缺省0
        Integer frequency_detail_device_idcard_cnt_30d = 0; //“group":"客户行为检测" ，"type":"frequency_detail" ->"detail": "1个月内身份证关联设备ID数：6"，，数据缺省0
        Integer frequency_detail_device_idcard_cnt_7d = 0; //“group":"客户行为检测" ，"type":"frequency_detail" ->"detail": "1天内身份证关联设备ID数：6"，，数据缺省0

        Integer frequency_detail_workaddr_idcard_cnt_90d = 0;

        try {

            //=====issue:
            if (CollectionUtils.isEmpty(fraudTypes)) {
                mob_hit_black_list = LACK;
            } else if (fraudTypes.contains("电信欺诈")) {
                mob_hit_black_list = HIT;
            }

            if (CollectionUtils.isEmpty(listTypes)) {
                bank_hit_black_list = LACK;
            } else if (fraudTypes.contains(BLACK_LIST)) {
                bank_hit_black_list = HIT;
            }

            List<Integer> mob_cnt_hit_black_listList = JsonPath.read(rawData, "$.risk_items[?(@.group=='风险信息扫描' && @.item_name=='手机号命中信贷逾期名单')].item_detail.discredit_times");
            if (CollectionUtils.isEmpty(mob_cnt_hit_black_listList)) {
                mob_cnt_hit_black_list = LACK;
            } else {
                mob_cnt_hit_black_list = mob_cnt_hit_black_listList.get(0);
            }

            List<Integer> idcard_cnt_hit_black_listList = JsonPath.read(rawData, "$.risk_items[?(@.group=='风险信息扫描' && @.item_name=='身份证命中信贷逾期名单')].item_detail.discredit_times");
            if (CollectionUtils.isEmpty(idcard_cnt_hit_black_listList)) {
                idcard_cnt_hit_black_list = LACK;
            } else {
                idcard_cnt_hit_black_list = idcard_cnt_hit_black_listList.get(0);
            }

            // FIXME: 15/11/2017 需求不确定  生产数据没有: 银行卡预留手机号命中信贷逾期黑名单  TODO
            if (CollectionUtils.isEmpty(listTypes)) {
                bank_mob_hit_black_list = LACK;
            } else {
                bank_mob_hit_black_list = HIT;
            }

            List<String> itemNameList = JsonPath.read(rawData, "$.risk_items.[*].item_name");
            if (itemNameList.contains("手机号命中模糊名单")) {
                List<String> subTypeList = JsonPath.read(rawData, "$.risk_items.[*].item_detail.namelist_hit_details.type");
                if (subTypeList.contains("fuzzy_list")) {
                    mob_hit_fuzzy_list = HIT;
                }
            } else {
                mob_hit_fuzzy_list = NOT_HIT;
            }

            List<String> idcard_hitItemNameList = JsonPath.read(rawData, "$.risk_items.[*].item_name");
            if (idcard_hitItemNameList.contains("身份证号命中模糊名单")) {
                List<String> subTypeList = JsonPath.read(rawData, "$.risk_items.[*].item_detail.namelist_hit_details.type");
                if (subTypeList.contains("fuzzy_list")) {
                    idcard_hit_fuzzy_list = HIT;
                }
            } else {
                idcard_hit_fuzzy_list = NOT_HIT;
            }


            List<String> subTypeList = JsonPath.read(rawData, "$.risk_items[?(@.item_name=='手机号命中低风险关注名单')].item_detail.namelist_hit_details[?(@.type=='grey_list')].type");
            int size = CollectionUtils.isEmpty(subTypeList) ? 0 : subTypeList.size();

            subTypeList.clear();
            subTypeList = JsonPath.read(rawData, "$.risk_items[?(@.item_name=='手机号命中中风险关注名单')].item_detail.namelist_hit_details[?(@.type=='grey_list')].type");
            int size2 = CollectionUtils.isEmpty(subTypeList) ? 0 : subTypeList.size();
            mob_hit_grey_list = size + size2;


            int idcard_hit_grey_listSize = 0;
            subTypeList = JsonPath.read(rawData, "$.risk_items[?(@.item_name=='身份证命中低风险关注名单')].item_detail.namelist_hit_details[?(@.type=='grey_list')].type");
            idcard_hit_grey_listSize = CollectionUtils.isEmpty(subTypeList) ? 0 : subTypeList.size();

            subTypeList = JsonPath.read(rawData, "$.risk_items[?(@.item_name=='身份证命中中风险关注名单')].item_detail.namelist_hit_details[?(@.type=='grey_list')].type");
            int idcard_hit_grey_listSize2 = CollectionUtils.isEmpty(subTypeList) ? 0 : subTypeList.size();
            idcard_hit_grey_list = idcard_hit_grey_listSize + idcard_hit_grey_listSize2;


            List<String> fbList = JsonPath.read(rawData, "$.risk_items[?(@.group=='客户行为检测')].item_detail[?(@.type=='frequency_detail')].frequency_detail_list[*].detail"); //
            for (String fbDetail : fbList) {
                String trimed = StringUtils.trim(fbDetail);
                if (StringUtils.startsWithIgnoreCase(trimed, "3个月身份证关联手机号数")) {
                    frequency_detail_mob_idcard_cnt_90d = extractByDesc(fbDetail, "3个月身份证关联手机号数");
                }
                if (StringUtils.startsWithIgnoreCase(trimed, "3个月身份证关联邮箱数")) {
                    frequency_detail_email_idcard_cnt_90d = extractByDesc(fbDetail, "3个月身份证关联邮箱数");
                }
                if (StringUtils.startsWithIgnoreCase(trimed, "3个月身份证关联家庭地址数")) {
                    frequency_detail_homeaddr_idcard_cnt_90d = extractByDesc(fbDetail, "3个月身份证关联家庭地址数");
                }
                if (StringUtils.startsWithIgnoreCase(trimed, "1天内身份证关联设备数")) {
                    frequency_detail_device_idcard_cnt_1d = extractByDesc(fbDetail, "1天内身份证关联设备数");
                }
                if (StringUtils.startsWithIgnoreCase(trimed, "1个月内身份证关联设备数")) {
                    frequency_detail_device_idcard_cnt_30d = extractByDesc(fbDetail, "1个月内身份证关联设备数");
                }
                if (StringUtils.startsWithIgnoreCase(trimed, "7天内身份证关联设备数")) {
                    frequency_detail_device_idcard_cnt_7d = extractByDesc(fbDetail, "7天内身份证关联设备数");
                }
            }


            List<String> fbWordAddrList = JsonPath.read(rawData,
                    "$.risk_items.[?(@.group=='客户行为检测' && @.item_name=='3个月内申请人身份证关联工作单位地址个数大于等于2')].item_detail[?(@.type=='frequency_detail')].frequency_detail_list[*].detail");
            for (String fbDetail : fbWordAddrList) {
                frequency_detail_workaddr_idcard_cnt_90d = extractByDesc(fbDetail, "3个月内借款人身份证关联工作单位地址个数");
            }

        } catch (Exception e) {
            log.error("Parse ClFraudTdHitList error. input data is: {}", rawData, e);
        } finally {
            ClFraudTdHitList clFraudTdHitList = new ClFraudTdHitList();
            clFraudTdHitList.setIdcard_hit_execution_list(idcard_hit_execution_list);
            clFraudTdHitList.setIdcard_hit_court_list(idcard_hit_court_list);
            clFraudTdHitList.setDevice_hit_black_list(device_hit_black_list);
            clFraudTdHitList.setMob_hit_black_list(mob_hit_black_list);
            clFraudTdHitList.setIdcard_hit_black_list(idcard_hit_black_list);

            clFraudTdHitList.setBank_hit_black_list(bank_hit_black_list);
            clFraudTdHitList.setBank_mob_hit_black_list(bank_mob_hit_black_list);
            clFraudTdHitList.setMob_overdue_day_max(mob_overdue_day_max);
            clFraudTdHitList.setIdcard_overdue_day_max(idcard_overdue_day_max);
            clFraudTdHitList.setMob_hit_fuzzy_list(mob_hit_fuzzy_list);

            clFraudTdHitList.setIdcard_hit_fuzzy_list(idcard_hit_fuzzy_list);
            clFraudTdHitList.setMob_hit_grey_list(mob_hit_grey_list);
            clFraudTdHitList.setIdcard_hit_grey_list(idcard_hit_grey_list);
//        clFraudTdHitList.setContact1_overdue_day_max(c);
//        clFraudTdHitList.setContact2_overdue_day_max();

            clFraudTdHitList.setFrequency_detail_mob_idcard_cnt_90d(frequency_detail_mob_idcard_cnt_90d);
            clFraudTdHitList.setFrequency_detail_email_idcard_cnt_90d(frequency_detail_email_idcard_cnt_90d);
            clFraudTdHitList.setFrequency_detail_homeaddr_idcard_cnt_90d(frequency_detail_homeaddr_idcard_cnt_90d);
            clFraudTdHitList.setFrequency_detail_device_idcard_cnt_1d(frequency_detail_device_idcard_cnt_1d);
            clFraudTdHitList.setFrequency_detail_device_idcard_cnt_30d(frequency_detail_device_idcard_cnt_30d);

            clFraudTdHitList.setFrequency_detail_device_idcard_cnt_7d(frequency_detail_device_idcard_cnt_7d);
            clFraudTdHitList.setFrequency_detail_workaddr_idcard_cnt_90d(frequency_detail_workaddr_idcard_cnt_90d);
            clFraudTdHitList.setMob_cnt_hit_black_list(mob_cnt_hit_black_list);
            clFraudTdHitList.setIdcard_cnt_hit_black_list(idcard_cnt_hit_black_list);

            clFraudTdHitList.setReport_id(reportId);
            clFraudTdHitList.setUser_id(userId);
            clFraudTdHitList.setReport_date(reportDate);

            Map<String, Object> param = Maps.newHashMap();
            param.put("report_id", reportId);

            List<ClFraudTdHitList> clFraudTdHitListList = clFraudTdHitListMapper.listSelective(param);
            if (clFraudTdHitListList == null || clFraudTdHitListList.isEmpty()) {
                clFraudTdHitListMapper.save(clFraudTdHitList);
            } else {
                clFraudTdHitList.setId(clFraudTdHitListList.get(0).getId());
                clFraudTdHitListMapper.update(clFraudTdHitList);
            }
        }
    }

    private Integer extractByDesc(String fbDetail, String needMatch) {
        Integer value = 0;
        String[] splited = null;
        if (StringUtils.startsWithIgnoreCase(StringUtils.trim(fbDetail), needMatch)) {
            if (fbDetail.contains(":")) {
                splited = StringUtils.split(fbDetail, ":");
            }

            if (fbDetail.contains("：")) {
                splited = StringUtils.split(fbDetail, "：");
            }

            if (splited == null) {
                log.warn("data error, splited is null. {}", fbDetail);
                return 0;
            }

            if (splited.length == 2) {
                value = Integer.valueOf(splited[1]);
            } else {
                log.warn("data error. {}", fbDetail);
            }
        }

        return value;
    }

    //JSON PATH
    private static final String LIST_TYPE_PATH = "$.risk_items.[*].item_detail.namelist_hit_details[*].type";//黑名单,灰名单,模糊名单path
    private static final String RISK_TYPE_PATH = "$.risk_items.[*].item_detail.fraud_type";//风险信息扫描path

    private static final String COURT_ENFORCEMENT = "法院执行";
    private static final String COURT_PROMISE_BREAKING = "法院失信";

    private static final String BLACK_LIST = "black_list";
    private static final String GREY_LIST = "grey_list";
    private static final String FUZZY_LIST = "fuzzy_list";

    // 命中 1 未命中 0 缺省值 -1
    private static final Integer NOT_HIT = 0;
    private static final Integer HIT = 1;
    private static final Integer LACK = -1;






    // ================= ut
    public static void main(String[] args) {
//        TongdunParseServiceImpl service = new TongdunParseServiceImpl();
//        service.parse(temp, "", 1l);

//        JSONObject data = JSONObject.parseObject(temp);
//        Long reportTime = (Long) data.get("report_time");
//        Date reportDate = new Date(reportTime);
//
//        System.out.println(reportDate);

    }


}
