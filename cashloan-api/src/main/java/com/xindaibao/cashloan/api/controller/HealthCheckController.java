package com.xindaibao.cashloan.api.controller;


import com.xindaibao.cashloan.cl.domain.ClMoheActiveSilenceStats;
import com.xindaibao.cashloan.cl.domain.ClMoheEmergencyContact1Detail;
import com.xindaibao.cashloan.cl.domain.ClMoheReportInfo;
import com.xindaibao.cashloan.cl.mapper.ClMoheActiveSilenceStatsMapper;
import com.xindaibao.cashloan.cl.mapper.ClMoheEmergencyContact1DetailMapper;
import com.xindaibao.cashloan.cl.mapper.ClMoheReportInfoMapper;
import com.xindaibao.cashloan.cl.model.mohe.MoheParamHolder;
import com.xindaibao.cashloan.cl.service.ClMoheReportInfoService;
import com.xindaibao.cashloan.core.common.web.controller.AbstractController;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Controller
public class HealthCheckController extends BaseController {

    @Autowired private ClMoheReportInfoService clMoheReportInfoService;
    @Autowired private ClMoheReportInfoMapper clMoheReportInfoMapper;
    @Autowired private ClMoheActiveSilenceStatsMapper clMoheActiveSilenceStatsMapper;
    @Autowired private ClMoheEmergencyContact1DetailMapper emergencyContact1DetailMapper;

    @RequestMapping(value = "hello.htm", method = RequestMethod.GET)
    public ResponseEntity sayHello() {

        return ResponseEntity.ok("hello");
    }


    /**
     *
     * 15000469476 查建强 362334198910245913 TASKYYS100000201710241401240331920056
     15000469476 查建强 362334198910245913 TASKYYS100000201710241110310720980404
     18267190212 王鹏 420625199405084011 TASKYYS100000201710221520270681101020
     */
    @RequestMapping(value = "/arsenal/mohe.htm", method = RequestMethod.GET)
    public ResponseEntity mohe() {

        System.out.println("arsenal-mohe ...");
        MoheParamHolder ss = new  MoheParamHolder();
        ss.setIdNo("420625199405084011");
        ss.setReal_name("王鹏");
        ss.setTask_id("TASKYYS100000201710221520270681101020");
        ss.setUserId(30L);
        clMoheReportInfoService.runMohe(ss);
//        Long userId = 33l;
//        String taskId = "TASKYYS100000201710241401240331920056";
//        clMoheReportInfoService.processResponseData(userId, taskId, resp);
        return ResponseEntity.ok("done.");
    }


    @RequestMapping(value = "/arsenal/mohe-save.htm", method = RequestMethod.GET)
    public ResponseEntity moheSave() {

        System.out.println("arsenal-mohe save ...");

        Long userId = 33l;
        String taskId = "TASKYYS100000201710241401240331920099";

//        ClMoheReportInfo reportInfo = new ClMoheReportInfo();
//        reportInfo.setReportId("reportId");
//        reportInfo.setUserId(33L);
//        clMoheReportInfoMapper.save(reportInfo);
//        ClMoheActiveSilenceStats obj = new ClMoheActiveSilenceStats();
//        obj.setUserId(991l);
//        obj.setTaskId("TASKYYS100000201710241401240331920056");
//        obj.setActiveDay1call3month("34gdfg");
//
//        clMoheActiveSilenceStatsMapper.save(obj);

        clMoheReportInfoService.processResponseData(userId, taskId, resp);


        //==== emergencyContact1DetailMapper test ===== //

//        ClMoheEmergencyContact1Detail emergencyContact1Detail = new ClMoheEmergencyContact1Detail();
//        emergencyContact1Detail.setUserId(991l);
//        emergencyContact1Detail.setTaskId("test Task Id");
//
//        emergencyContact1DetailMapper.save(emergencyContact1Detail);



        return ResponseEntity.ok("done.");
    }


    private static final String resp = "{\n" +
            "  \"code\": 0,\n" +
            "  \"msg\": \"查询成功\",\n" +
            "  \"data\": {\n" +
            "    \"report_info\": {\n" +
            "      \"report_id\": \"MH20170625122051223456\",\n" +
            "      \"report_update_time\": \"2017-06-25 12:20:51\",\n" +
            "      \"task_id\": \"TASKYYS1000001234567890\",\n" +
            "      \"report_version\": \"v1\",\n" +
            "      \"task_time\": \"2017-05-25 19:26:11\"\n" +
            "    },\n" +
            "    \"data_completeness\": {\n" +
            "      \"is_call_data_complete_1month\": \"是\",\n" +
            "      \"is_call_data_complete_3month\": \"是\",\n" +
            "      \"is_msg_data_complete_1month\": \"是\",\n" +
            "      \"is_msg_data_complete_6month\": \"是\",\n" +
            "      \"is_consume_data_complete_6month\": \"是\",\n" +
            "      \"is_consume_data_complete_3month\": \"是\",\n" +
            "      \"is_consume_data_complete_1month\": \"是\",\n" +
            "      \"is_call_data_complete_6month\": \"否\",\n" +
            "      \"is_msg_data_complete_3month\": \"是\",\n" +
            "      \"data_completeness_per_month\": [\n" +
            "        {\n" +
            "          \"is_msg_data_complete\": \"是\",\n" +
            "          \"is_consume_data_complete\": \"是\",\n" +
            "          \"month\": \"2016-12\",\n" +
            "          \"is_call_data_complete\": \"是\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    \"user_info\": {\n" +
            "      \"identity_code\": \"123456\",\n" +
            "      \"home_tel\": \"18726334909\",\n" +
            "      \"work_tel\": \"15755023181\",\n" +
            "      \"company_name\": \"同盾科技\",\n" +
            "      \"work_addr\": \"海创园18号楼123室\",\n" +
            "      \"home_addr\": \"杭州市文一西路100小区123室\",\n" +
            "      \"real_name\": \"张三\",\n" +
            "      \"email\": \"123@tongdun.cn\",\n" +
            "      \"age\": \"23\",\n" +
            "      \"gender\": \"男\",\n" +
            "      \"hometown\": \"浙江省.杭州市\"\n" +
            "    },\n" +
            "    \"mobile_info\": {\n" +
            "      \"identity_code\": \"3301021234567890\",\n" +
            "      \"contact_addr\": \"杭州市文一西路100小区123室\",\n" +
            "      \"account_balance\": \"7955\",\n" +
            "      \"user_mobile\": \"13625697921\",\n" +
            "      \"mobile_net_addr\": \"安徽省.芜湖市\",\n" +
            "      \"mobile_carrier\": \"中国移动\",\n" +
            "      \"real_name\": \"张三\",\n" +
            "      \"account_status\": \"正常\",\n" +
            "      \"mobile_net_time\": \"2013-09-13\",\n" +
            "      \"package_type\": \"全球通\",\n" +
            "      \"mobile_net_age\": \"44\",\n" +
            "      \"email\": \"未知\"\n" +
            "    },\n" +
            "    \"info_match\": {\n" +
            "      \"real_name_check_yys\": \"不匹配\",\n" +
            "      \"identity_code_check_yys\": \"模糊匹配\"\n" +
            "    },\n" +
            "    \"info_check\": {\n" +
            "      \"is_contact4_called_6month\": \"无数据\",\n" +
            "      \"is_net_addr_call_addr_3month\": \"否\",\n" +
            "      \"is_contact2_called_6month\": \"无数据\",\n" +
            "      \"is_work_tel_called_6month\": \"是\",\n" +
            "      \"is_contact3_called_6month\": \"无数据\",\n" +
            "      \"is_net_age_over_3month\": \"是\",\n" +
            "      \"is_mobile_status_active\": \"是\",\n" +
            "      \"is_contact1_called_6month\": \"是\",\n" +
            "      \"is_home_tel_called_6month\": \"是\",\n" +
            "      \"is_contact5_called_6month\": \"无数据\",\n" +
            "      \"is_net_addr_call_addr_1month\": \"否\",\n" +
            "      \"is_identity_code_valid\": \"否\",\n" +
            "      \"is_net_addr_call_addr_6month\": \"否\"\n" +
            "    },\n" +
            "    \"behavior_analysis\": {\n" +
            "      \"emergency_contact3_analysis_6month\": \"无数据\",\n" +
            "      \"late_night_analysis_6month\": \"很少活动\",\n" +
            "      \"call_120_analysis_6month\": \"从未通话\",\n" +
            "      \"emergency_contact4_analysis_6month\": \"无数据\",\n" +
            "      \"call_lawyer_analysis_6month\": \"从未通话\",\n" +
            "      \"emergency_contact2_analysis_6month\": \"无数据\",\n" +
            "      \"mutual_number_analysis_6month\": \"正常数量\",\n" +
            "      \"loan_contact_analysis_6month\": \"频繁通话\",\n" +
            "      \"emergency_contact5_analysis_6month\": \"无数据\",\n" +
            "      \"emergency_contact1_analysis_6month\": \"频繁通话\",\n" +
            "      \"collection_contact_analysis_6month\": \"从未通话\",\n" +
            "      \"call_110_analysis_6month\": \"从未通话\",\n" +
            "      \"mobile_net_age_analysis\": \"使用时间2年到5年\",\n" +
            "      \"mobile_silence_analysis_6month\": \"正常静默\",\n" +
            "      \"call_macau_analysis_6month\": \"从未通话\"\n" +
            "    },\n" +

            "    \"emergency_contact1_detail\": {\n" +
            "      \"call_count_holiday_3month\": \"5\",\n" +
            "      \"call_count_holiday_6month\": \"8\",\n" +
            "      \"contact_attribute\": \"手机号码\",\n" +
            "      \"call_count_active_6month\": \"0\",\n" +
            "      \"call_time_3month\": \"345\",\n" +
            "      \"msg_count_3month\": \"0\",\n" +
            "      \"call_time_1month\": \"0\",\n" +
            "      \"call_time_passive_6month\": \"967\",\n" +
            "      \"call_count_active_3month\": \"0\",\n" +
            "      \"call_time_6month\": \"967\",\n" +
            "      \"contact_type\": null,\n" +
            "      \"call_count_late_night_3month\": \"0\",\n" +
            "      \"call_count_late_night_6month\": \"0\",\n" +
            "      \"call_count_passive_6month\": \"10\",\n" +
            "      \"call_time_active_6month\": \"0\",\n" +
            "      \"msg_count_6month\": \"0\",\n" +
            "      \"call_count_work_time_6month\": \"9\",\n" +
            "      \"call_time_active_3month\": \"0\",\n" +
            "      \"call_count_passive_3month\": \"7\",\n" +
            "      \"call_count_3month\": \"7\",\n" +
            "      \"contact_name\": \"111\",\n" +
            "      \"contact_relation\": \"FATHER\",\n" +
            "      \"call_count_work_time_3month\": \"6\",\n" +
            "      \"call_count_workday_3month\": \"2\",\n" +
            "      \"call_count_6month\": \"10\",\n" +
            "      \"call_count_offwork_time_3month\": \"1\",\n" +
            "      \"call_count_offwork_time_6month\": \"1\",\n" +
            "      \"contact_number\": \"18375313977\",\n" +
            "      \"contact_area\": \"安徽省.芜湖市111\",\n" +
            "      \"call_count_1week\": \"0\",\n" +
            "      \"contact_seq_no\": \"3\",\n" +
            "      \"call_count_1month\": \"0\",\n" +
            "      \"call_time_passive_3month\": \"345\",\n" +
            "      \"call_count_workday_6month\": \"2\",\n" +
            "      \"msg_count_1month\": \"0\"\n" +
            "    },\n" +

            "    \"emergency_contact2_detail\": {\n" +
            "      \"call_count_holiday_3month\": \"5\",\n" +
            "      \"call_count_holiday_6month\": \"8\",\n" +
            "      \"contact_attribute\": \"手机号码\",\n" +
            "      \"call_count_active_6month\": \"0\",\n" +
            "      \"call_time_3month\": \"345\",\n" +
            "      \"msg_count_3month\": \"0\",\n" +
            "      \"call_time_1month\": \"0\",\n" +
            "      \"call_time_passive_6month\": \"967\",\n" +
            "      \"call_count_active_3month\": \"0\",\n" +
            "      \"call_time_6month\": \"967\",\n" +
            "      \"contact_type\": null,\n" +
            "      \"call_count_late_night_3month\": \"0\",\n" +
            "      \"call_count_late_night_6month\": \"0\",\n" +
            "      \"call_count_passive_6month\": \"10\",\n" +
            "      \"call_time_active_6month\": \"0\",\n" +
            "      \"msg_count_6month\": \"0\",\n" +
            "      \"call_count_work_time_6month\": \"9\",\n" +
            "      \"call_time_active_3month\": \"0\",\n" +
            "      \"call_count_passive_3month\": \"7\",\n" +
            "      \"call_count_3month\": \"7\",\n" +
            "      \"contact_name\": \"111\",\n" +
            "      \"contact_relation\": \"FATHER\",\n" +
            "      \"call_count_work_time_3month\": \"6\",\n" +
            "      \"call_count_workday_3month\": \"2\",\n" +
            "      \"call_count_6month\": \"10\",\n" +
            "      \"call_count_offwork_time_3month\": \"1\",\n" +
            "      \"call_count_offwork_time_6month\": \"1\",\n" +
            "      \"contact_number\": \"18375313977\",\n" +
            "      \"contact_area\": \"安徽省.芜湖市222\",\n" +
            "      \"call_count_1week\": \"0\",\n" +
            "      \"contact_seq_no\": \"3\",\n" +
            "      \"call_count_1month\": \"0\",\n" +
            "      \"call_time_passive_3month\": \"345\",\n" +
            "      \"call_count_workday_6month\": \"2\",\n" +
            "      \"msg_count_1month\": \"0\"\n" +
            "    },\n" +

            "    \"emergency_contact3_detail\": {\n" +
            "      \"call_count_holiday_3month\": \"5\",\n" +
            "      \"call_count_holiday_6month\": \"8\",\n" +
            "      \"contact_attribute\": \"手机号码\",\n" +
            "      \"call_count_active_6month\": \"0\",\n" +
            "      \"call_time_3month\": \"345\",\n" +
            "      \"msg_count_3month\": \"0\",\n" +
            "      \"call_time_1month\": \"0\",\n" +
            "      \"call_time_passive_6month\": \"967\",\n" +
            "      \"call_count_active_3month\": \"0\",\n" +
            "      \"call_time_6month\": \"967\",\n" +
            "      \"contact_type\": null,\n" +
            "      \"call_count_late_night_3month\": \"0\",\n" +
            "      \"call_count_late_night_6month\": \"0\",\n" +
            "      \"call_count_passive_6month\": \"10\",\n" +
            "      \"call_time_active_6month\": \"0\",\n" +
            "      \"msg_count_6month\": \"0\",\n" +
            "      \"call_count_work_time_6month\": \"9\",\n" +
            "      \"call_time_active_3month\": \"0\",\n" +
            "      \"call_count_passive_3month\": \"7\",\n" +
            "      \"call_count_3month\": \"7\",\n" +
            "      \"contact_name\": \"111\",\n" +
            "      \"contact_relation\": \"FATHER\",\n" +
            "      \"call_count_work_time_3month\": \"6\",\n" +
            "      \"call_count_workday_3month\": \"2\",\n" +
            "      \"call_count_6month\": \"10\",\n" +
            "      \"call_count_offwork_time_3month\": \"1\",\n" +
            "      \"call_count_offwork_time_6month\": \"1\",\n" +
            "      \"contact_number\": \"18375313977\",\n" +
            "      \"contact_area\": \"安徽省.芜湖市333\",\n" +
            "      \"call_count_1week\": \"0\",\n" +
            "      \"contact_seq_no\": \"3\",\n" +
            "      \"call_count_1month\": \"0\",\n" +
            "      \"call_time_passive_3month\": \"345\",\n" +
            "      \"call_count_workday_6month\": \"2\",\n" +
            "      \"msg_count_1month\": \"0\"\n" +
            "    },\n" +

            "    \"emergency_contact4_detail\": {\n" +
            "      \"call_count_holiday_3month\": \"5\",\n" +
            "      \"call_count_holiday_6month\": \"8\",\n" +
            "      \"contact_attribute\": \"手机号码\",\n" +
            "      \"call_count_active_6month\": \"0\",\n" +
            "      \"call_time_3month\": \"345\",\n" +
            "      \"msg_count_3month\": \"0\",\n" +
            "      \"call_time_1month\": \"0\",\n" +
            "      \"call_time_passive_6month\": \"967\",\n" +
            "      \"call_count_active_3month\": \"0\",\n" +
            "      \"call_time_6month\": \"967\",\n" +
            "      \"contact_type\": null,\n" +
            "      \"call_count_late_night_3month\": \"0\",\n" +
            "      \"call_count_late_night_6month\": \"0\",\n" +
            "      \"call_count_passive_6month\": \"10\",\n" +
            "      \"call_time_active_6month\": \"0\",\n" +
            "      \"msg_count_6month\": \"0\",\n" +
            "      \"call_count_work_time_6month\": \"9\",\n" +
            "      \"call_time_active_3month\": \"0\",\n" +
            "      \"call_count_passive_3month\": \"7\",\n" +
            "      \"call_count_3month\": \"7\",\n" +
            "      \"contact_name\": \"111\",\n" +
            "      \"contact_relation\": \"FATHER\",\n" +
            "      \"call_count_work_time_3month\": \"6\",\n" +
            "      \"call_count_workday_3month\": \"2\",\n" +
            "      \"call_count_6month\": \"10\",\n" +
            "      \"call_count_offwork_time_3month\": \"1\",\n" +
            "      \"call_count_offwork_time_6month\": \"1\",\n" +
            "      \"contact_number\": \"18375313977\",\n" +
            "      \"contact_area\": \"安徽省.芜湖市444\",\n" +
            "      \"call_count_1week\": \"0\",\n" +
            "      \"contact_seq_no\": \"3\",\n" +
            "      \"call_count_1month\": \"0\",\n" +
            "      \"call_time_passive_3month\": \"345\",\n" +
            "      \"call_count_workday_6month\": \"2\",\n" +
            "      \"msg_count_1month\": \"0\"\n" +
            "    },\n" +

            "    \"emergency_contact5_detail\": {\n" +
            "      \"call_count_holiday_3month\": \"5\",\n" +
            "      \"call_count_holiday_6month\": \"8\",\n" +
            "      \"contact_attribute\": \"手机号码\",\n" +
            "      \"call_count_active_6month\": \"0\",\n" +
            "      \"call_time_3month\": \"345\",\n" +
            "      \"msg_count_3month\": \"0\",\n" +
            "      \"call_time_1month\": \"0\",\n" +
            "      \"call_time_passive_6month\": \"967\",\n" +
            "      \"call_count_active_3month\": \"0\",\n" +
            "      \"call_time_6month\": \"967\",\n" +
            "      \"contact_type\": null,\n" +
            "      \"call_count_late_night_3month\": \"0\",\n" +
            "      \"call_count_late_night_6month\": \"0\",\n" +
            "      \"call_count_passive_6month\": \"10\",\n" +
            "      \"call_time_active_6month\": \"0\",\n" +
            "      \"msg_count_6month\": \"0\",\n" +
            "      \"call_count_work_time_6month\": \"9\",\n" +
            "      \"call_time_active_3month\": \"0\",\n" +
            "      \"call_count_passive_3month\": \"7\",\n" +
            "      \"call_count_3month\": \"7\",\n" +
            "      \"contact_name\": \"111\",\n" +
            "      \"contact_relation\": \"FATHER\",\n" +
            "      \"call_count_work_time_3month\": \"6\",\n" +
            "      \"call_count_workday_3month\": \"2\",\n" +
            "      \"call_count_6month\": \"10\",\n" +
            "      \"call_count_offwork_time_3month\": \"1\",\n" +
            "      \"call_count_offwork_time_6month\": \"1\",\n" +
            "      \"contact_number\": \"18375313977\",\n" +
            "      \"contact_area\": \"安徽省.芜湖市555\",\n" +
            "      \"call_count_1week\": \"0\",\n" +
            "      \"contact_seq_no\": \"3\",\n" +
            "      \"call_count_1month\": \"0\",\n" +
            "      \"call_time_passive_3month\": \"345\",\n" +
            "      \"call_count_workday_6month\": \"2\",\n" +
            "      \"msg_count_1month\": \"0\"\n" +
            "    },\n" +

//            "    \"emergency_contact2_detail\": null,\n" +
//            "    \"emergency_contact4_detail\": null,\n" +
//            "    \"emergency_contact3_detail\": null,\n" +
//            "    \"emergency_contact5_detail\": null,\n" +


            "    \"work_tel_detail\": {\n" +
            "      \"call_count_holiday_3month\": \"1\",\n" +
            "      \"call_count_holiday_6month\": \"1\",\n" +
            "      \"contact_attribute\": \"手机号码\",\n" +
            "      \"call_count_active_6month\": \"0\",\n" +
            "      \"call_time_3month\": \"1123\",\n" +
            "      \"msg_count_3month\": \"0\",\n" +
            "      \"call_time_1month\": \"0\",\n" +
            "      \"call_time_passive_6month\": \"1781\",\n" +
            "      \"call_count_active_3month\": \"0\",\n" +
            "      \"call_time_6month\": \"1781\",\n" +
            "      \"contact_type\": null,\n" +
            "      \"call_count_late_night_3month\": \"0\",\n" +
            "      \"call_count_late_night_6month\": \"0\",\n" +
            "      \"call_count_passive_6month\": \"2\",\n" +
            "      \"call_time_active_6month\": \"0\",\n" +
            "      \"msg_count_6month\": \"0\",\n" +
            "      \"call_count_work_time_6month\": \"1\",\n" +
            "      \"call_time_active_3month\": \"0\",\n" +
            "      \"call_count_passive_3month\": \"1\",\n" +
            "      \"call_count_3month\": \"1\",\n" +
            "      \"contact_name\": null,\n" +
            "      \"contact_relation\": null,\n" +
            "      \"call_count_work_time_3month\": \"0\",\n" +
            "      \"call_count_workday_3month\": \"0\",\n" +
            "      \"call_count_6month\": \"2\",\n" +
            "      \"call_count_offwork_time_3month\": \"1\",\n" +
            "      \"call_count_offwork_time_6month\": \"1\",\n" +
            "      \"contact_number\": \"15755023181\",\n" +
            "      \"contact_area\": \"安徽省.滁州市\",\n" +
            "      \"call_count_1week\": \"0\",\n" +
            "      \"contact_seq_no\": \"21\",\n" +
            "      \"call_count_1month\": \"0\",\n" +
            "      \"call_time_passive_3month\": \"1123\",\n" +
            "      \"call_count_workday_6month\": \"1\",\n" +
            "      \"msg_count_1month\": \"0\"\n" +
            "    },\n" +
            "    \"home_tel_detail\": null,\n" +
            "    \"all_contact_stats\": {\n" +
            "      \"call_count_holiday_3month\": \"47\",\n" +
            "      \"call_count_holiday_6month\": \"88\",\n" +
            "      \"contact_count_active_3month\": \"15\",\n" +
            "      \"contact_count_active_6month\": \"20\",\n" +
            "      \"call_count_active_6month\": \"39\",\n" +
            "      \"call_time_3month\": \"13324\",\n" +
            "      \"msg_count_3month\": \"4\",\n" +
            "      \"call_time_passive_6month\": \"20891\",\n" +
            "      \"call_count_active_3month\": \"26\",\n" +
            "      \"call_time_6month\": \"25919\",\n" +
            "      \"call_count_late_night_3month\": \"0\",\n" +
            "      \"call_count_late_night_6month\": \"1\",\n" +
            "      \"call_count_passive_6month\": \"167\",\n" +
            "      \"call_time_active_6month\": \"5028\",\n" +
            "      \"msg_count_6month\": \"10\",\n" +
            "      \"contact_count_passive_6month\": \"58\",\n" +
            "      \"call_count_work_time_6month\": \"144\",\n" +
            "      \"call_time_active_3month\": \"2995\",\n" +
            "      \"contact_count_passive_3month\": \"35\",\n" +
            "      \"call_count_passive_3month\": \"91\",\n" +
            "      \"call_count_3month\": \"117\",\n" +
            "      \"call_count_work_time_3month\": \"81\",\n" +
            "      \"call_count_workday_3month\": \"70\",\n" +
            "      \"call_count_6month\": \"206\",\n" +
            "      \"call_count_offwork_time_3month\": \"36\",\n" +
            "      \"call_count_offwork_time_6month\": \"61\",\n" +
            "      \"contact_count_mutual_3month\": \"9\",\n" +
            "      \"call_time_passive_3month\": \"10329\",\n" +
            "      \"contact_count_3month\": \"41\",\n" +
            "      \"call_count_workday_6month\": \"118\",\n" +
            "      \"contact_count_6month\": \"66\",\n" +
            "      \"contact_count_mutual_6month\": \"12\"\n" +
            "    },\n" +
            "    \"all_contact_detail\": [\n" +
            "      {\n" +
            "        \"call_count_holiday_3month\": \"7\",\n" +
            "        \"call_count_holiday_6month\": \"14\",\n" +
            "        \"contact_attribute\": \"手机号码\",\n" +
            "        \"call_count_active_6month\": \"9\",\n" +
            "        \"call_time_3month\": \"2551\",\n" +
            "        \"msg_count_3month\": \"1\",\n" +
            "        \"call_time_1month\": \"0\",\n" +
            "        \"call_time_passive_6month\": \"1255\",\n" +
            "        \"call_count_active_3month\": \"7\",\n" +
            "        \"call_time_6month\": \"3902\",\n" +
            "        \"contact_type\": null,\n" +
            "        \"call_count_late_night_3month\": \"0\",\n" +
            "        \"call_count_late_night_6month\": \"1\",\n" +
            "        \"call_count_passive_6month\": \"25\",\n" +
            "        \"call_time_active_6month\": \"2647\",\n" +
            "        \"msg_count_6month\": \"1\",\n" +
            "        \"call_count_work_time_6month\": \"18\",\n" +
            "        \"call_time_active_3month\": \"1943\",\n" +
            "        \"call_count_passive_3month\": \"13\",\n" +
            "        \"call_count_3month\": \"20\",\n" +
            "        \"contact_name\": null,\n" +
            "        \"contact_relation\": null,\n" +
            "        \"call_count_work_time_3month\": \"12\",\n" +
            "        \"call_count_workday_3month\": \"13\",\n" +
            "        \"call_count_6month\": \"34\",\n" +
            "        \"call_count_offwork_time_3month\": \"8\",\n" +
            "        \"call_count_offwork_time_6month\": \"15\",\n" +
            "        \"contact_number\": \"18726334909\",\n" +
            "        \"contact_area\": \"安徽省.宿州市\",\n" +
            "        \"call_count_1week\": \"0\",\n" +
            "        \"contact_seq_no\": \"1\",\n" +
            "        \"call_count_1month\": \"0\",\n" +
            "        \"call_time_passive_3month\": \"608\",\n" +
            "        \"call_count_workday_6month\": \"20\",\n" +
            "        \"msg_count_1month\": \"0\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"all_contact_stats_per_month\": [\n" +
            "      {\n" +
            "        \"call_count_active\": \"14\",\n" +
            "        \"call_count_passive\": \"40\",\n" +
            "        \"contact_count\": \"22\",\n" +
            "        \"call_time\": \"5167\",\n" +
            "        \"contact_count_active\": \"10\",\n" +
            "        \"call_count_offwork_time\": \"16\",\n" +
            "        \"call_count_work_time\": \"38\",\n" +
            "        \"contact_count_mutual\": \"7\",\n" +
            "        \"month\": \"2017-05\",\n" +
            "        \"call_time_passive\": \"4170\",\n" +
            "        \"call_count_late_night\": \"0\",\n" +
            "        \"call_count\": \"54\",\n" +
            "        \"call_time_active\": \"997\",\n" +
            "        \"contact_count_passive\": \"19\",\n" +
            "        \"msg_count\": \"3\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"risk_contact_stats\": [\n" +
            "      {\n" +
            "        \"call_count_3month\": \"0\",\n" +
            "        \"risk_type\": \"小贷公司\",\n" +
            "        \"call_count_6month\": \"3\",\n" +
            "        \"call_count_active_6month\": \"2\",\n" +
            "        \"call_time_3month\": \"0\",\n" +
            "        \"msg_count_3month\": \"0\",\n" +
            "        \"call_time_passive_6month\": \"77\",\n" +
            "        \"call_count_active_3month\": \"0\",\n" +
            "        \"call_time_6month\": \"794\",\n" +
            "        \"call_count_passive_6month\": \"1\",\n" +
            "        \"call_time_active_6month\": \"717\",\n" +
            "        \"call_time_passive_3month\": \"0\",\n" +
            "        \"msg_count_6month\": \"0\",\n" +
            "        \"contact_count_3month\": \"0\",\n" +
            "        \"contact_count_6month\": \"3\",\n" +
            "        \"call_time_active_3month\": \"0\",\n" +
            "        \"call_count_passive_3month\": \"0\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"call_count_3month\": \"0\",\n" +
            "        \"risk_type\": \"催收\",\n" +
            "        \"call_count_6month\": \"3\",\n" +
            "        \"call_count_active_6month\": \"2\",\n" +
            "        \"call_time_3month\": \"0\",\n" +
            "        \"msg_count_3month\": \"0\",\n" +
            "        \"call_time_passive_6month\": \"77\",\n" +
            "        \"call_count_active_3month\": \"0\",\n" +
            "        \"call_time_6month\": \"794\",\n" +
            "        \"call_count_passive_6month\": \"1\",\n" +
            "        \"call_time_active_6month\": \"717\",\n" +
            "        \"call_time_passive_3month\": \"0\",\n" +
            "        \"msg_count_6month\": \"0\",\n" +
            "        \"contact_count_3month\": \"0\",\n" +
            "        \"contact_count_6month\": \"3\",\n" +
            "        \"call_time_active_3month\": \"0\",\n" +
            "        \"call_count_passive_3month\": \"0\"\n" +
            "      },\n" +

            "      {\n" +
            "        \"call_count_3month\": \"0\",\n" +
            "        \"risk_type\": \"120\",\n" +
            "        \"call_count_6month\": \"3\",\n" +
            "        \"call_count_active_6month\": \"2\",\n" +
            "        \"call_time_3month\": \"0\",\n" +
            "        \"msg_count_3month\": \"0\",\n" +
            "        \"call_time_passive_6month\": \"77\",\n" +
            "        \"call_count_active_3month\": \"0\",\n" +
            "        \"call_time_6month\": \"794\",\n" +
            "        \"call_count_passive_6month\": \"1\",\n" +
            "        \"call_time_active_6month\": \"717\",\n" +
            "        \"call_time_passive_3month\": \"0\",\n" +
            "        \"msg_count_6month\": \"0\",\n" +
            "        \"contact_count_3month\": \"0\",\n" +
            "        \"contact_count_6month\": \"3\",\n" +
            "        \"call_time_active_3month\": \"0\",\n" +
            "        \"call_count_passive_3month\": \"0\"\n" +
            "      }\n" +



            "    ],\n" +
            "    \"risk_contact_detail\": [\n" +
            "      {\n" +
            "        \"call_count_holiday_3month\": \"0\",\n" +
            "        \"call_count_holiday_6month\": \"0\",\n" +
            "        \"contact_attribute\": \"其他号码\",\n" +
            "        \"call_count_active_6month\": \"2\",\n" +
            "        \"call_time_3month\": \"0\",\n" +
            "        \"msg_count_3month\": \"0\",\n" +
            "        \"call_time_1month\": \"0\",\n" +
            "        \"call_time_passive_6month\": \"77\",\n" +
            "        \"call_count_active_3month\": \"0\",\n" +
            "        \"call_time_6month\": \"794\",\n" +
            "        \"contact_type\": \"小贷公司\",\n" +
            "        \"call_count_late_night_3month\": \"0\",\n" +
            "        \"call_count_late_night_6month\": \"0\",\n" +
            "        \"call_count_passive_6month\": \"1\",\n" +
            "        \"call_time_active_6month\": \"717\",\n" +
            "        \"msg_count_6month\": \"0\",\n" +
            "        \"call_count_work_time_6month\": \"3\",\n" +
            "        \"call_time_active_3month\": \"0\",\n" +
            "        \"call_count_passive_3month\": \"0\",\n" +
            "        \"call_count_3month\": \"0\",\n" +
            "        \"contact_name\": \"某某小贷公司\",\n" +
            "        \"contact_relation\": null,\n" +
            "        \"call_count_work_time_3month\": \"0\",\n" +
            "        \"call_count_workday_3month\": \"0\",\n" +
            "        \"call_count_6month\": \"3\",\n" +
            "        \"call_count_offwork_time_3month\": \"0\",\n" +
            "        \"call_count_offwork_time_6month\": \"0\",\n" +
            "        \"contact_number\": \"95188\",\n" +
            "        \"contact_area\": \"未知\",\n" +
            "        \"call_count_1week\": \"0\",\n" +
            "        \"contact_seq_no\": \"16\",\n" +
            "        \"call_count_1month\": \"0\",\n" +
            "        \"call_time_passive_3month\": \"0\",\n" +
            "        \"call_count_workday_6month\": \"3\",\n" +
            "        \"msg_count_1month\": \"0\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"finance_contact_stats\": [\n" +
            "      {\n" +
            "        \"call_count_3month\": \"6\",\n" +
            "        \"call_count_6month\": \"6\",\n" +
            "        \"call_count_active_6month\": \"3\",\n" +
            "        \"call_time_3month\": \"328\",\n" +
            "        \"msg_count_3month\": \"0\",\n" +
            "        \"call_time_passive_6month\": \"203\",\n" +
            "        \"call_count_active_3month\": \"3\",\n" +
            "        \"call_time_6month\": \"328\",\n" +
            "        \"contact_type\": \"银行\",\n" +
            "        \"call_count_passive_6month\": \"3\",\n" +
            "        \"call_time_active_6month\": \"125\",\n" +
            "        \"call_time_passive_3month\": \"3\",\n" +
            "        \"msg_count_6month\": \"0\",\n" +
            "        \"contact_count_3month\": \"6\",\n" +
            "        \"contact_count_6month\": \"6\",\n" +
            "        \"call_time_active_3month\": \"125\",\n" +
            "        \"call_count_passive_3month\": \"3\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"finance_contact_detail\": [\n" +
            "      {\n" +
            "        \"call_count_holiday_3month\": \"2\",\n" +
            "        \"call_count_holiday_6month\": \"2\",\n" +
            "        \"contact_attribute\": \"其他号码\",\n" +
            "        \"call_count_active_6month\": \"3\",\n" +
            "        \"call_time_3month\": \"328\",\n" +
            "        \"msg_count_3month\": \"0\",\n" +
            "        \"call_time_1month\": \"0\",\n" +
            "        \"call_time_passive_6month\": \"203\",\n" +
            "        \"call_count_active_3month\": \"3\",\n" +
            "        \"call_time_6month\": \"328\",\n" +
            "        \"contact_type\": \"银行\",\n" +
            "        \"call_count_late_night_3month\": \"0\",\n" +
            "        \"call_count_late_night_6month\": \"0\",\n" +
            "        \"call_count_passive_6month\": \"3\",\n" +
            "        \"call_time_active_6month\": \"125\",\n" +
            "        \"msg_count_6month\": \"0\",\n" +
            "        \"call_count_work_time_6month\": \"5\",\n" +
            "        \"call_time_active_3month\": \"125\",\n" +
            "        \"call_count_passive_3month\": \"3\",\n" +
            "        \"call_count_3month\": \"6\",\n" +
            "        \"contact_name\": \"招商银行信用卡中心\",\n" +
            "        \"contact_relation\": null,\n" +
            "        \"call_count_work_time_3month\": \"5\",\n" +
            "        \"call_count_workday_3month\": \"4\",\n" +
            "        \"call_count_6month\": \"6\",\n" +
            "        \"call_count_offwork_time_3month\": \"1\",\n" +
            "        \"call_count_offwork_time_6month\": \"1\",\n" +
            "        \"contact_number\": \"4006595555\",\n" +
            "        \"contact_area\": \"上海市.上海市\",\n" +
            "        \"call_count_1week\": \"0\",\n" +
            "        \"contact_seq_no\": \"7\",\n" +
            "        \"call_count_1month\": \"0\",\n" +
            "        \"call_time_passive_3month\": \"203\",\n" +
            "        \"call_count_workday_6month\": \"4\",\n" +
            "        \"msg_count_1month\": \"0\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"contact_area_stats_per_city\": [\n" +
            "      {\n" +
            "        \"call_count_holiday_3month\": \"3\",\n" +
            "        \"call_count_holiday_6month\": \"11\",\n" +
            "        \"contact_count_active_3month\": \"4\",\n" +
            "        \"contact_count_active_6month\": \"6\",\n" +
            "        \"call_count_active_6month\": \"8\",\n" +
            "        \"call_time_3month\": \"2761\",\n" +
            "        \"msg_count_3month\": \"3\",\n" +
            "        \"call_time_1month\": \"0\",\n" +
            "        \"call_time_passive_6month\": \"6361\",\n" +
            "        \"call_count_active_3month\": \"4\",\n" +
            "        \"call_time_6month\": \"6724\",\n" +
            "        \"call_count_late_night_3month\": \"0\",\n" +
            "        \"contact_area_city\": \"上海市.上海市\",\n" +
            "        \"call_count_late_night_6month\": \"0\",\n" +
            "        \"call_count_passive_6month\": \"49\",\n" +
            "        \"call_time_active_6month\": \"363\",\n" +
            "        \"msg_count_6month\": \"3\",\n" +
            "        \"contact_count_passive_6month\": \"12\",\n" +
            "        \"call_count_work_time_6month\": \"42\",\n" +
            "        \"call_time_active_3month\": \"120\",\n" +
            "        \"contact_count_passive_3month\": \"8\",\n" +
            "        \"call_count_passive_3month\": \"20\",\n" +
            "        \"call_count_3month\": \"24\",\n" +
            "        \"contact_count_1month\": \"0\",\n" +
            "        \"call_count_work_time_3month\": \"19\",\n" +
            "        \"call_count_workday_3month\": \"21\",\n" +
            "        \"call_count_6month\": \"57\",\n" +
            "        \"call_count_offwork_time_3month\": \"5\",\n" +
            "        \"call_count_offwork_time_6month\": \"15\",\n" +
            "        \"contact_count_mutual_3month\": \"3\",\n" +
            "        \"call_count_1month\": \"0\",\n" +
            "        \"call_time_passive_3month\": \"2641\",\n" +
            "        \"contact_count_3month\": \"9\",\n" +
            "        \"contact_area_seq_no\": \"1\",\n" +
            "        \"call_count_workday_6month\": \"46\",\n" +
            "        \"contact_count_6month\": \"15\",\n" +
            "        \"contact_count_mutual_6month\": \"3\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"carrier_consumption_stats\": {\n" +
            "      \"recharge_amount_3month\": \"10000\",\n" +
            "      \"recharge_amount_6month\": \"26000\",\n" +
            "      \"consume_amount_6month\": \"25913\",\n" +
            "      \"recharge_count_3month\": \"2\",\n" +
            "      \"consume_amount_3month\": \"6837\",\n" +
            "      \"recharge_count_6month\": \"7\"\n" +
            "    },\n" +
            "    \"carrier_consumption_stats_per_month\": [\n" +
            "      {\n" +
            "        \"consume_amount\": \"7333\",\n" +
            "        \"recharge_amount\": \"8000\",\n" +
            "        \"month\": \"2016-12\",\n" +
            "        \"recharge_count\": \"3\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"call_area_stats_per_city\": [\n" +
            "      {\n" +
            "        \"call_count_3month\": \"88\",\n" +
            "        \"call_count_holiday_3month\": \"35\",\n" +
            "        \"call_count_holiday_6month\": \"55\",\n" +
            "        \"call_count_workday_3month\": \"53\",\n" +
            "        \"call_count_6month\": \"155\",\n" +
            "        \"active_day_1call_6month\": \"76\",\n" +
            "        \"active_day_1call_3month\": \"38\",\n" +
            "        \"call_count_active_6month\": \"29\",\n" +
            "        \"call_time_3month\": \"11039\",\n" +
            "        \"call_time_1month\": \"0\",\n" +
            "        \"call_time_passive_6month\": \"17386\",\n" +
            "        \"call_area_city\": \"上海市.上海市\",\n" +
            "        \"call_count_active_3month\": \"18\",\n" +
            "        \"call_time_6month\": \"21699\",\n" +
            "        \"active_day_1call_1month\": \"0\",\n" +
            "        \"call_count_1month\": \"0\",\n" +
            "        \"call_count_passive_6month\": \"126\",\n" +
            "        \"call_time_active_6month\": \"4313\",\n" +
            "        \"call_time_passive_3month\": \"8679\",\n" +
            "        \"call_count_workday_6month\": \"100\",\n" +
            "        \"call_area_seq_no\": \"1\",\n" +
            "        \"call_time_active_3month\": \"2360\",\n" +
            "        \"call_count_passive_3month\": \"70\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"active_silence_stats\": {\n" +
            "      \"max_continue_silence_day_0call_6month\": \"6\",\n" +
            "      \"continue_silence_day_over15_0call_3month_detail\": [],\n" +
            "      \"max_continue_silence_day_0call_3month\": \"6\",\n" +
            "      \"active_day_1call_6month\": \"91\",\n" +
            "      \"continue_silence_day_over3_0call_3month\": \"6\",\n" +
            "      \"active_day_1call_3month\": \"47\",\n" +
            "      \"continue_silence_day_over3_0call_6month\": \"12\",\n" +
            "      \"continue_silence_day_over3_0call_6month_detail\": [\n" +
            "        {\n" +
            "          \"end_date\": \"2016-12-08\",\n" +
            "          \"start_date\": \"2016-12-04\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"continue_silence_day_over15_0call_3month\": \"0\",\n" +
            "      \"continue_silence_day_over3_0call_3month_detail\": [\n" +
            "        {\n" +
            "          \"end_date\": \"2017-03-06\",\n" +
            "          \"start_date\": \"2017-03-01\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"continue_silence_day_over15_0call_6month\": \"0\",\n" +
            "      \"continue_silence_day_over15_0call_6month_detail\": [],\n" +
            "      \"silence_day_0call_3month\": \"64\",\n" +
            "      \"silence_day_0call_6month\": \"84\"\n" +
            "    },\n" +
            "    \"travel_track_analysis_per_city\": [\n" +
            "      {\n" +
            "        \"arrive_city\": \"江苏省.徐州市\",\n" +
            "        \"arrive_day_type\": \"节假日\",\n" +
            "        \"leave_city\": \"上海市.上海市\",\n" +
            "        \"leave_day_type\": \"工作日\",\n" +
            "        \"arrive_day\": \"2017-01-27\",\n" +
            "        \"leave_day\": \"2017-01-26\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}\n" +
            "\n" +
            "\n" +
            "\n";


}
