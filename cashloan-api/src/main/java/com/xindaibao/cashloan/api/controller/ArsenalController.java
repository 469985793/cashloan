package com.xindaibao.cashloan.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.xindaibao.cashloan.cl.domain.ClFraudTdBasic;
import com.xindaibao.cashloan.cl.domain.TongdunReqLog;
import com.xindaibao.cashloan.cl.mapper.ClBorrowMapper;
import com.xindaibao.cashloan.cl.mapper.ClFraudTdBasicMapper;
import com.xindaibao.cashloan.cl.mapper.TongdunReqLogMapper;
import com.xindaibao.cashloan.cl.service.ClBorrowService;
import com.xindaibao.cashloan.cl.service.TongdunParseService;
import com.xindaibao.cashloan.cl.service.TongdunReqLogService;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.rc.domain.TppBusiness;
import com.xindaibao.cashloan.rc.service.TppBusinessService;
import com.xindaibao.creditrank.cr.service.CreditRatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Scope("prototype")
@Controller
public class ArsenalController {
    public static final Logger logger = LoggerFactory.getLogger(ArsenalController.class);
    @Resource
    private TongdunReqLogService tongdunReqLogService;
    @Resource
    private TppBusinessService tppBusinessService;
    @Resource
    private ClBorrowMapper clBorrowMapper;
    @Autowired
    private CreditRatingService creditRatingService;
    @Autowired
    private ClBorrowService clBorrowService;
    @Autowired
    private TongdunReqLogMapper tongdunReqLogMapper;
    @Autowired
    private TongdunParseService tongdunParseService;
    @Autowired
    private ClFraudTdBasicMapper clFraudTdBasicMapper;

    /**
     * 第三方数据
     * @throws Exception
     */
    @RequestMapping(value = "/arsenal/third-test.htm")
    public void findIndexT(Long id, @RequestParam(value = "nId") String nId,
                           @RequestParam(value = "mobileType") String mobileType, @RequestParam(value = "tppId") Long tppId) throws Exception {
        try {

            if(nId.equalsIgnoreCase("TongdunApply")){
                if(null != id){
                    logger.info("同盾贷前审核数据查询 单条记录-开始,borrowId : ", id);
                    tongdunBusiness(id, nId, mobileType, tppId);
                    logger.info("同盾贷前审核数据查询 单条记录-结束,borrowId : ", id);
                } else {
                    logger.info("同盾贷前审核数据查询 所有无同盾数据的记录-开始---");
                    List<Borrow> borrows = clBorrowMapper.findNotInTongdunBorrow();
                    if(borrows.isEmpty()){
                        logger.info("同盾贷前审核数据查询-所有无同盾数据的记录,无记录 -结束");
                    } else {
                        logger.info("同盾贷前审核数据查询-所有无同盾数据的记录,共： ", borrows.size());
                        for(Borrow borrow : borrows){
                            tongdunBusiness(borrow.getId(), nId, mobileType, tppId);
                        }
                    }
                }
            }


        }catch (Exception e){
            logger.error("third-test 第三方数据查询异常:",e);
        }
    }



    /**
     * 同盾数据修复
     * @throws Exception
     */
    @RequestMapping(value = "/arsenal/fixTongDun-test.htm")
    public void fixTongDun(String reportId) throws Exception {
        try {
            if(null != reportId){
                tongDunFixById(reportId);
            } else {
                logger.info("同盾贷前审核数据所有查询--开始---");
                List<TongdunReqLog> tongdunReqLogList = tongdunReqLogMapper.findAllModel();
                if(tongdunReqLogList.isEmpty()){
                    logger.info("同盾贷前审核数据查询,无记录 -结束");
                } else {
                    logger.info("同盾贷前审核数据查询-所有无同盾数据的记录,共： ", tongdunReqLogList.size());
                    for(TongdunReqLog tongdunReqLog : tongdunReqLogList){
                        tongDunFixById(tongdunReqLog.getReportId());
                    }
                }
            }
        }catch (Exception e){
            logger.error("同盾贷前审核数据fix异常:",e);
        }
    }

    
    private void tongdunBusiness(Long id, String nId, String mobileType, Long tppId) {
        logger.info("同盾贷前审核数据查询-开始,borrowId : ", id);
        Borrow borrow = clBorrowMapper.findByPrimary(id);
        TppBusiness bussiness = tppBusinessService.findByNid(nId,tppId);
        int count = tongdunReqLogService.preloanApplyRequest(borrow.getUserId(),borrow,bussiness,mobileType);

//        clBorrowService.syncSceneBusinessLog(borrow.getId(), nId, count);
        logger.info("同盾贷前审核数据查询-结束,borrowId : ", id);
    }


    private void tongDunFixById(String reportId) {
        logger.info("同盾贷前审核数据修复-开始,reportId : " + reportId);
        TongdunReqLog tongdunReqLog = tongdunReqLogMapper.findModelByReportId(reportId);
        if (tongdunReqLog.getSubmitParams() != null) {
            Map<String, Object> resultMap = JSONObject.parseObject(tongdunReqLog.getSubmitParams(), Map.class);

            JSONObject data = (JSONObject) resultMap.get("data");

            JSONObject data_data = JSONObject.parseObject(data.getString("data"));
            String temp = JSONObject.toJSONString(data_data);

            saveClFraudTdBasic(reportId, tongdunReqLog, temp);

            try {
                tongdunParseService.parse(temp, tongdunReqLog.getReportId(), tongdunReqLog.getUserId());
            }catch (Exception e){
                logger.error("data解析失败！reportId = " +reportId ,e);
            }

            }
            logger.info("同盾贷前审核数据修复-结束,reportId : " + reportId);
    }

    private void saveClFraudTdBasic(String reportId, TongdunReqLog tongdunReqLog, String temp) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("report_id", reportId);

        List<ClFraudTdBasic> clFraudTdBasicList = clFraudTdBasicMapper.listSelective(param);
        if (clFraudTdBasicList == null || clFraudTdBasicList.isEmpty()) {
            ClFraudTdBasic clFraudTdBasic = JSONObject.parseObject(temp, ClFraudTdBasic.class);
            clFraudTdBasic.setReport_id(reportId);
            clFraudTdBasic.setUser_id(tongdunReqLog.getUserId());
            clFraudTdBasicMapper.save(clFraudTdBasic);
        }
    }


    @RequestMapping(value = "/arsenal/credit.htm")
    public void credit(@RequestParam(value = "nId") String consumerNo){
        creditRatingService.saveCreditRating(consumerNo, 1l);
    }


//    /**
//     * 数美测试
//     * @throws Exception
//     */
//    @Autowired
//    ClShuMeiBlacklistService clShuMeiBlacklistService;
//    @RequestMapping(value = "/arsenal/shumei-test.htm")
//    public void test() throws Exception {
//        try {
//            clShuMeiBlacklistService.queryShuMeiBlackList();
//        }catch (Exception e){
//            logger.error("同盾贷前审核数据fix异常:",e);
//        }
//    }



    /**
     * 2017-11-01 21:47:21,364 INFO http-nio-8080-exec-9 [com.xindaibao.cashloan.api.interceptor.ApiInterceptor] - >>>>>>>>>>>>>>>http://api.zuoli360.com/api/user/probeSms.htm耗时：3
     2017-11-01 21:47:56,312 INFO http-nio-8080-exec-2 [com.xindaibao.cashloan.api.interceptor.ApiInterceptor] - 请求URL : http://api.zuoli360.com/api/act/mine/bankCard/authSign.htm
     2017-11-01 21:47:56,312 INFO http-nio-8080-exec-2 [com.xindaibao.cashloan.api.interceptor.ApiInterceptor] - HTTP_METHOD : POST
     2017-11-01 21:47:56,312 INFO http-nio-8080-exec-2 [com.xindaibao.cashloan.api.interceptor.ApiInterceptor] - IP : 116.236.242.198
     2017-11-01 21:47:56,312 INFO http-nio-8080-exec-2 [com.xindaibao.cashloan.api.interceptor.ApiInterceptor] -

        ARGS : appType : 0;bank : 中国建设银行;cardNo : 6217001180000639669;mobileType : 2;name : 乔荣荣;token : 0b0411841dd54f06ab4594846308986c;userId : 456789387;versionNumber : 1.0;

     2017-11-01 21:47:56,325 WARN http-nio-8080-exec-2 [com.xindaibao.cashloan.api.controller.BankCardController] -
        bind card request args ==========>
        {"oid_partner":"201710170001030816","time_stamp":"20171101214756","risk_item":"{\"user_info_full_name\":\"乔荣荣\",\"user_info_id_no\":\"142601198108281333\",\"user_info_dt_registe\":\"20171101214544\",\"user_info_identify_type\":\"1\",\"user_info_identify_state\":\"1\",\"user_info_mercht_userno\":\"5316e06d61e3449ab649b24d747aef3b\",\"frms_ware_category\":\"2010\"}","sign":"Ep+RGVSGlecJxGlr/fJQKY28jcTZUQBG2nAr6v3S2l4VD3yOEMl22ixQfjU3bjHrmVgre6bI8nDW8ACUPbysK2GyJBChfLl1/cVCit6cQrfa42e2+qirD+uJxjf2IAAk/39gdxM4mwn9IDqaVWfJfaeu32iV4KW0qZtPkG/lahs=","dt_order":"20171101214756","api_version":"1.0","notify_url":"","flag_pay_product":"8","no_order":"1711010325575867","id_no":"142601198108281333","card_no":"6217001180000639669","user_id":"5316e06d61e3449ab649b24d747aef3b","flag_chnl":0,"id_type":"0","bind_mob":"13817741873","acct_name":"乔荣荣","sign_type":"RSA"}

     2017-11-01 21:47:56,366 ERROR http-nio-8080-exec-2 [com.xindaibao.cashloan.core.common.util.HttpUtil] - sun.security.validator.ValidatorException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target
     2017-11-01 21:47:56,367 ERROR http-nio-8080-exec-2 [com.xindaibao.cashloan.core.common.web.controller.BaseController] - RuntimeException
     java.lang.RuntimeException: 请求返回异常
     */




}
