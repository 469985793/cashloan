package com.xindaibao.cashloan.manage.controller;

import com.xindaibao.cashloan.cl.domain.*;
import com.xindaibao.cashloan.cl.mapper.BorrowRepayMapper;
import com.xindaibao.cashloan.cl.model.BorrowRepayLogModel;
import com.xindaibao.cashloan.cl.model.BorrowRepayModel;
import com.xindaibao.cashloan.cl.model.PayLogModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.QueryRepaymentModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.RepaymentModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.constant.LianLianConstant;
import com.xindaibao.cashloan.cl.model.pay.lianlian.util.LianLianHelper;
import com.xindaibao.cashloan.cl.service.*;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.util.DateUtil;
import com.xindaibao.cashloan.core.common.util.PropertiesUtil;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.core.domain.User;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.model.KanyaUser;
import com.xindaibao.cashloan.core.service.CloanUserService;
import com.xindaibao.cashloan.core.service.UserBaseInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tool.util.BigDecimalUtil;

import javax.annotation.Resource;
import java.util.*;

@Scope("prototype")
@Controller
public class ArsenalController {
    public static final Logger logger = LoggerFactory.getLogger(ArsenalController.class);
    @Resource
    private BorrowRepayMapper borrowRepayMapper;

    @Resource
    private CloanUserService cloanUserService;
    @Resource
    private UserBaseInfoService userBaseInfoService;
    @Resource
    private BankCardService bankCardService;
    @Resource
    private ClBorrowService clBorrowService;
    @Resource
    private PayLogService payLogService;
    @Resource
    private BorrowRepayService borrowRepayService;
    @Resource
    private ClSmsService clSmsService;

    // FIXME: 2017/11/24 borrowIdIn 传入不为空时，走此借款订单代扣， 否则走文件List
    @RequestMapping(value = "/arsenal/query-order.htm")
    public ResponseEntity queryOrder(String orderNo, String payReqTime) {
        logger.info("进入-arsenal-查询还款订单...");
        QueryRepaymentModel queryRepayment = null;
        try {
            Map<String, Object> queryRepaymentMap = new HashMap<>();
            queryRepaymentMap.put("queryOrderNo", orderNo);
            queryRepaymentMap.put("queryOrderTime", payReqTime);
            queryRepayment = (QueryRepaymentModel) LianLianHelper.queryRepayment(queryRepaymentMap);

            logger.info("结束-arsenal-查询还款订单...");
        }catch (Exception e){
            logger.error("arsenal-查询还款订单; e:",e);
        }
        return new ResponseEntity("result:" + queryRepayment.ret_code+"--msg"+queryRepayment.ret_msg, HttpStatus.OK);
    }

    // FIXME: 2017/11/24 borrowIdIn 传入不为空时，走此借款订单代扣， 否则走文件List
    // FIXME: 2017/11/24 amountIn 传0时， 代扣金额以系统为准， 否则以传入金额为准；
    @RequestMapping(value = "/arsenal/repayment.htm")
    public void dealRepayment(Long borrowIdIn,  double amountIn) {
        logger.info("进入-arsenal-代扣还款任务...");

        String types = PropertiesUtil.getValue("borrowIdList");
        List<String> list_47 = Arrays.asList(types.split(","));

        if(null != borrowIdIn){
            dealRepayByBorrowId(borrowIdIn, amountIn);
        } else {
            logger.info("代扣还款任务，待处理的还款计划总数为：" + list_47.size());
            for (String borrowId : list_47) {
                dealRepayByBorrowId(Long.parseLong(borrowId), amountIn);
            }
        }
        logger.info("结束-arsenal-代扣还款任务...");
    }

    private void dealRepayByBorrowId(Long id, double amountIn) {
        try {
            logger.info("borrowId-start :"+ id);
            BorrowRepay borrowRepay = borrowRepayMapper.findByBorrowId(id);
            logger.info("代扣还款任务，还款计划borrowReapyId：" + borrowRepay.getId() + "开始处理");

            // 查询用户、用户详情、借款及用户银行卡信息
            KanyaUser user = cloanUserService.getById(borrowRepay.getUserId());
            UserBaseInfo baseInfo = userBaseInfoService.findByUserId(borrowRepay.getUserId());
            Borrow borrow = clBorrowService.getById(borrowRepay.getBorrowId());
            BankCard bankCard = bankCardService.getBankCardByUserId(borrowRepay.getUserId());


            // 扣款失败无异步通知 故先查询订单是否已经在支付处理中
            Map<String, Object> payLogMap = new HashMap<String, Object>();
            payLogMap.put("userId", borrowRepay.getUserId());
            payLogMap.put("borrowId", borrowRepay.getBorrowId());
            payLogMap.put("type", PayLogModel.TYPE_COLLECT);
            payLogMap.put("scenes", PayLogModel.SCENES_REPAYMENT);
            PayLog repaymentLog = payLogService.findLatestOne(payLogMap);

            // 支付记录存在且不是支付失败，需要查询支付方得到准确结果
            if (null != repaymentLog && !PayLogModel.STATE_PAYMENT_FAILED.equals(repaymentLog.getState())) {
                if (PayLogModel.STATE_PAYMENT_SUCCESS.equals(repaymentLog.getState())) {
                    logger.info("borrowReapyId：" + borrowRepay.getId() + "-finish" + PayLogModel.STATE_PAYMENT_SUCCESS );
                    return;
                }

                Map<String, Object> queryRepaymentMap = new HashMap<>();
                queryRepaymentMap.put("queryOrderNo", repaymentLog.getOrderNo());
                queryRepaymentMap.put("queryOrderTime", repaymentLog.getPayReqTime());
                QueryRepaymentModel queryRepayment = (QueryRepaymentModel) LianLianHelper.queryRepayment(queryRepaymentMap);

                if (queryRepayment.checkReturn() && LianLianConstant.RESULT_SUCCESS.equals(queryRepayment.getResult_pay())) {
                    logger.info("borrowReapyId：" + borrowRepay.getId() + "-lianlian said success, " );
                    // 查找对应的还款计划
                    Map<String, Object> param = new HashMap<String, Object>();
                    param.put("id", borrowRepay.getId());
                    param.put("repayTime", DateUtil.getNow());
                    param.put("repayWay", BorrowRepayLogModel.REPAY_WAY_CHARGE);
                    param.put("repayAccount", bankCard.getCardNo());
                    param.put("amount", borrowRepay.getAmount());
                    param.put("serialNumber", repaymentLog.getOrderNo());
                    param.put("penaltyAmout", borrowRepay.getPenaltyAmout());
                    param.put("state", "10");
                    if (!borrowRepay.getState().equals(BorrowRepayModel.STATE_REPAY_YES)) {
                        borrowRepayService.confirmRepay(param);
                    }

                    // 更新订单状态
                    Map<String, Object> payLogParamMap = new HashMap<String, Object>();
                    payLogParamMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
                    payLogParamMap.put("updateTime", DateUtil.getNow());
                    payLogParamMap.put("id", repaymentLog.getId());
                    payLogService.updateSelective(payLogParamMap);
                    // FIXME: 2017/11/23 是否发短信
                    clSmsService.repayInform(borrowRepay.getUserId(), borrowRepay.getBorrowId(),"noRepayInform");
                    return;
                } else if (queryRepayment.checkReturn() && LianLianConstant.RESULT_PROCESSING.equals(queryRepayment.getResult_pay())) {
                    logger.info("borrowReapyId：" + borrowRepay.getId() + "-lianlian said processing, " );
                    return;
                } else {
                    // 更新订单状态
                    logger.info("borrowReapyId：" + borrowRepay.getId() + "-lianlian said failed, " );
                    Map<String, Object> payLogParamMap = new HashMap<String, Object>();
                    payLogParamMap.put("state", PayLogModel.STATE_PAYMENT_FAILED);
                    payLogParamMap.put("updateTime", DateUtil.getNow());
                    payLogParamMap.put("id", repaymentLog.getId());
                    payLogService.updateSelective(payLogParamMap);
                }
            }

            // 计算实际还款金额
            //double principal = BigDecimalUtil.sub(borrowRepay.getAmount(), borrowRepay.getReliefAmount());
            //double penaltyAmout = BigDecimalUtil.sub(borrowRepay.getPenaltyAmout(), borrowRepay.getReliefFine());
            double principal = BigDecimalUtil.sub(borrowRepay.getAmount(), 0);
            double penaltyAmout = BigDecimalUtil.sub(borrowRepay.getPenaltyAmout(), 0);
            double amount = BigDecimalUtil.add(principal, penaltyAmout);

            Date payReqTime = DateUtil.getNow();
            Map<String, Object> repaymentMap = new HashMap<>();
            repaymentMap.put("user", user);
            repaymentMap.put("userBaseInfo", baseInfo);
            repaymentMap.put("agreeNo", bankCard.getAgreeNo());
            repaymentMap.put("payTime", payReqTime);

            if(0 == amountIn){
                repaymentMap.put("amount", amount);
            } else {
                repaymentMap.put("amount", amountIn);
            }

            logger.info("true amount :"+repaymentMap.get("amount")+"-borrowId :"+borrowRepay.getBorrowId());

            repaymentMap.put("repayTime", borrowRepay.getRepayTime());
            repaymentMap.put("borrowOrderNo", borrow.getOrderNo());
            repaymentMap.put("orderMemoInfo", borrow.getOrderNo() + "还款");
            repaymentMap.put("notifyUrl", Global.getValue("server_host") + "/pay/lianlian/repaymentNotify.htm");
            RepaymentModel repayment = (RepaymentModel) LianLianHelper.repayment(repaymentMap);

            logger.info("borrowReapyId：" + borrowRepay.getId() + "-lianlian repayment response: "+ repayment );
            logger.info("getOrderNo:" + repayment.getOrderNo() + "-getAmount: "+ repayment.getAmount() + "-getRet_msg(): "+ repayment.getRet_msg());

            PayLog payLog = new PayLog();
            payLog.setOrderNo(repayment.getOrderNo());
            payLog.setUserId(borrowRepay.getUserId());
            payLog.setBorrowId(borrowRepay.getBorrowId());
            payLog.setAmount(repayment.getAmount());
            payLog.setCardNo(bankCard.getCardNo());
            payLog.setBank(bankCard.getBank());
            payLog.setSource(PayLogModel.SOURCE_FUNDS_OWN);
            payLog.setType(PayLogModel.TYPE_COLLECT);
            payLog.setScenes(PayLogModel.SCENES_REPAYMENT);
            payLog.setState(PayLogModel.STATE_PAYMENT_WAIT);
            payLog.setRemark(repayment.getRet_msg());
            payLog.setPayReqTime(payReqTime);
            payLog.setCreateTime(DateUtil.getNow());
            payLogService.save(payLog);

            logger.info("borrowId-end :"+ id);
        } catch (Exception e) {
            logger.error("arsenal-代扣还款任务-error:", e);
        }
    }

    @RequestMapping(value = "/arsenal/authSignApply.htm")
    public void authSignApplyController(Long borrowIdIn){
        logger.info("进入-arsenal-重新授权任务...");
        if(borrowIdIn != null) {
            borrowRepayService.authSignApply(borrowIdIn);
        }else{
            logger.error("arsenal-重新授权任务-error");
        }
        logger.info("结束-arsenal-重新授权任务...");
    }

}
