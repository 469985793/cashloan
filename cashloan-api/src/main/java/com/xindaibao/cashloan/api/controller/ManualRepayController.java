package com.xindaibao.cashloan.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.xindaibao.cashloan.api.user.service.ContractService;
import com.xindaibao.cashloan.cl.domain.*;
import com.xindaibao.cashloan.cl.model.BorrowRepayLogModel;
import com.xindaibao.cashloan.cl.model.BorrowRepayModel;
import com.xindaibao.cashloan.cl.model.PayLogModel;
import com.xindaibao.cashloan.cl.model.PayRespLogModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.QueryRepaymentModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.RepaymentModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.RiskItems;
import com.xindaibao.cashloan.cl.model.pay.lianlian.constant.LianLianConstant;
import com.xindaibao.cashloan.cl.model.pay.lianlian.util.LianLianHelper;
import com.xindaibao.cashloan.cl.model.pay.lianlian.util.SignUtil;
import com.xindaibao.cashloan.cl.service.*;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.core.domain.User;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.service.CloanUserService;
import com.xindaibao.cashloan.core.service.UserBaseInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tool.util.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 还款
 */
@Controller
@Scope("prototype")
@RequestMapping("/api/act/repay")
public class ManualRepayController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ManualRepayController.class);

    @Resource
    private PayReqLogService payReqLogService;
    @Resource
    private PayRespLogService payRespLogService;
    @Resource
    private PayLogService payLogService;
    @Resource
    private ClBorrowService clBorrowService;
    @Resource
    private BorrowProgressService borrowProgressService;
    @Resource
    private BorrowRepayService borrowRepayService;
    @Resource
    private BorrowRepayLogService borrowRepayLogService;
    @Resource
    private ProfitAmountService profitAmountService;
    @Resource
    private ContractService contractService;
    @Resource
    private BankCardService bankCardService;
    @Resource
    private CloanUserService cloanUserService;
    @Resource
    private UserBaseInfoService userBaseInfoService;
    @Resource
    private ClSmsService clSmsService;


    /**
     * 创建- 并还款
     * 订单
     */
    @RequestMapping("creatAndRepay")
    private void creatAndRepayOrder(@RequestParam(value = "userId", required = true) String userId) {
        try {
            Map<String, Object> result = new HashMap<>();
//        Long userId = getSessionUserId();
            Long user_id = Long.parseLong(userId);
            List<BorrowRepay> borrowRepayList = getBorrowRepays(user_id);

            if (borrowRepayList.isEmpty()) {
                resultReturn(result, Constant.OTHER_CODE_VALUE, "无需还款订单");
                return;
            }

            BorrowRepay borrowRepay = borrowRepayList.get(0);

            BankCard bankCard = bankCardService.getBankCardByUserId(user_id);
            User user = new User();//cloanUserService.getById(user_id);
            UserBaseInfo baseInfo = userBaseInfoService.findByUserId(user_id);
            Borrow borrow = clBorrowService.getById(borrowRepay.getBorrowId());

            // 扣款失败无异步通知 故先查询订单是否已经在支付处理中
            // FIXME: 2017/11/16 扣款分手动、自动，查询需重写
            PayLog repaymentLog = payLogService.findRepayLatestOne(user_id, borrowRepay.getBorrowId());

            // FIXME: 2017/11/15 记录存在，且不是支付失败 需要查询支付方得到准确结果
            if (null != repaymentLog && !PayLogModel.STATE_PAYMENT_FAILED.equals(repaymentLog.getState())) {
                if (PayLogModel.STATE_PAYMENT_SUCCESS.equals(repaymentLog.getState())) {
                    resultReturn(result, Constant.OTHER_CODE_VALUE, "付款状态 - 支付成功");
                    return;
                }
                //查询连连订单状态
                QueryRepaymentModel queryRepayment = queryRepaymentModelByOrderNo(repaymentLog);

                if (queryRepayment.checkReturn() && LianLianConstant.RESULT_SUCCESS.equals(queryRepayment.getResult_pay())) {
                    //更新平台借款信息
                    updateBorrow(borrowRepay, repaymentLog);

                    // 更新订单成功状态
                    updatePayLogSuccess(repaymentLog);

                    // FIXME: 2017/11/21 是否发送还款成功短信？
                    if(tool.util.DateUtil.daysBetween(tool.util.DateUtil.getNow(), borrowRepay.getRepayTime())>=0){
                        //未逾期还款成功
                        clSmsService.repayInform(borrowRepay.getUserId(), borrowRepay.getBorrowId(),"noRepayInform");
                    }else{
                        clSmsService.repayInform(borrowRepay.getUserId(), borrowRepay.getBorrowId(),"repayInformSucess");
                    }
                } else if (queryRepayment.checkReturn() && LianLianConstant.RESULT_PROCESSING.equals(queryRepayment.getResult_pay())) {
                    resultReturn(result, Constant.OTHER_CODE_VALUE, "还款处理中，请等待");
                    return;
                } else {
                    // 更新订单失败状态
                    updatePayLogFailed(repaymentLog, "query-" + queryRepayment.getRet_msg());
                }
            }


            // 计算实际还款金额
            //double principal = BigDecimalUtil.sub(borrowRepay.getAmount(), borrowRepay.getReliefAmount());
            //double penaltyAmout = BigDecimalUtil.sub(borrowRepay.getPenaltyAmout(), borrowRepay.getReliefFine());
            double principal = BigDecimalUtil.sub(borrowRepay.getAmount(), 0);
            double penaltyAmout = BigDecimalUtil.sub(borrowRepay.getPenaltyAmout(), 0);
            double amount = BigDecimalUtil.add(principal, penaltyAmout);

            RiskItems riskItems = new RiskItems(user.getUuid(), baseInfo.getPhone(), DateUtil.dateStr3(user.getRegistTime()), baseInfo.getRealName(),
                    baseInfo.getIdNo());
            Date repayTime = com.xindaibao.cashloan.core.common.util.DateUtil.getNow();
            String orderNo = com.xindaibao.cashloan.core.common.util.OrderNoUtil.getSerialNumber();

            RepaymentModel repayment = getRepaymentModelForApi(bankCard, user, baseInfo, borrow,
                    amount, riskItems, repayTime, orderNo);

            // FIXME: 2017/11/16 只返回app数据 ，还是需要生成初始订单
            resultReturn(result, Constant.SUCCEED_CODE_VALUE, "成功", repayment);

            LianLianHelper.saveReqLog(repayment);

            // FIXME: 2017/11/16 是否生成订单
            savepayLog(borrowRepay, bankCard, repayTime, repayment);

        } catch (Exception e) {
            logger.error("手动还款异常：e:", e);
        }
    }

    /**
     * 创建还款订单
     */
    @RequestMapping("creatRepay")
    private void creatRepayOrder(@RequestParam(value = "userId", required = true) String userId) {
        try {
            Map<String, Object> result = new HashMap<>();
//        Long userId = getSessionUserId();
            Long user_id = Long.parseLong(userId);

            List<BorrowRepay> borrowRepayList = getBorrowRepays(user_id);

            if (borrowRepayList.isEmpty()) {
                resultReturn(result, Constant.OTHER_CODE_VALUE, "无需还款订单");
                return;
            }

            BorrowRepay borrowRepay = borrowRepayList.get(0);

            BankCard bankCard = bankCardService.getBankCardByUserId(user_id);
            User user = new User();//cloanUserService.getById(user_id);
            UserBaseInfo baseInfo = userBaseInfoService.findByUserId(user_id);
            Borrow borrow = clBorrowService.getById(borrowRepay.getBorrowId());


            // 扣款失败无异步通知 故先查询订单是否已经在支付处理中
            // FIXME: 2017/11/16 扣款分手动、自动，查询需重写
            PayLog repaymentLog = payLogService.findRepayLatestOne(user_id, borrowRepay.getBorrowId());

            // FIXME: 2017/11/15 记录存在，且不是支付失败 需要查询支付方得到准确结果
            if (null != repaymentLog && !PayLogModel.STATE_PAYMENT_FAILED.equals(repaymentLog.getState())) {
                if (PayLogModel.STATE_PAYMENT_SUCCESS.equals(repaymentLog.getState())) {
                    resultReturn(result, Constant.OTHER_CODE_VALUE, "付款状态 - 支付成功");
                    return;
                }

                QueryRepaymentModel queryRepayment = queryRepaymentModelByOrderNo(repaymentLog);

                if (queryRepayment.checkReturn() && LianLianConstant.RESULT_SUCCESS.equals(queryRepayment.getResult_pay())) {
                    // 查找对应的还款计划
                    updateBorrow(borrowRepay, repaymentLog);

                    // 更新订单状态
                    updatePayLogSuccess(repaymentLog);

                    // FIXME: 2017/11/21 是否发送还款成功短信？
                    if(tool.util.DateUtil.daysBetween(tool.util.DateUtil.getNow(), borrowRepay.getRepayTime())>=0){
                        //未逾期还款成功
                        clSmsService.repayInform(borrowRepay.getUserId(), borrowRepay.getBorrowId(),"noRepayInform");
                    }else{
                        clSmsService.repayInform(borrowRepay.getUserId(), borrowRepay.getBorrowId(),"repayInformSucess");
                    }
                } else if (queryRepayment.checkReturn() && LianLianConstant.RESULT_PROCESSING.equals(queryRepayment.getResult_pay())) {
                    resultReturn(result, Constant.OTHER_CODE_VALUE, "还款处理中，请等待");
                    return;
                    // FIXME: 2017/11/29 订单时间内 的失败都为 waiting
                } else if (queryRepayment.checkReturn() && LianLianConstant.RESULT_WAITING.equals(queryRepayment.getResult_pay())) {
                    resultReturn(result, Constant.OTHER_CODE_VALUE, "还款处理中，请等待");
                    return;
                } else {
                    // 更新订单状态
                    updatePayLogFailed(repaymentLog, "");
                }
            }


            // 计算实际还款金额
            //double principal = BigDecimalUtil.sub(borrowRepay.getAmount(), borrowRepay.getReliefAmount());
            //double penaltyAmout = BigDecimalUtil.sub(borrowRepay.getPenaltyAmout(), borrowRepay.getReliefFine());
            double principal = BigDecimalUtil.sub(borrowRepay.getAmount(), 0);
            double penaltyAmout = BigDecimalUtil.sub(borrowRepay.getPenaltyAmout(), 0);
            double amount = BigDecimalUtil.add(principal, penaltyAmout);

            RiskItems riskItems = new RiskItems(user.getUuid(), baseInfo.getPhone(), DateUtil.dateStr3(user.getRegistTime()), baseInfo.getRealName(),
                    baseInfo.getIdNo());
            Date repayTime = com.xindaibao.cashloan.core.common.util.DateUtil.getNow();
            String orderNo = com.xindaibao.cashloan.core.common.util.OrderNoUtil.getSerialNumber();

            RepaymentModel repayment = getRepaymentModelForApi(bankCard, user, baseInfo, borrow,
                    amount, riskItems, repayTime, orderNo);

            // FIXME: 2017/11/16 只返回app数据 ，还是需要生成初始订单
            resultReturn(result, Constant.SUCCEED_CODE_VALUE, "成功", repayment);

            LianLianHelper.saveReqLog(repayment);

            // FIXME: 2017/11/16 是否生成订单
            savepayLog(borrowRepay, bankCard, repayTime, repayment);

        } catch (Exception e) {
            logger.error("手动还款异常：e:", e);
        }
    }

    /**
     * 创建还款订单
     * h5
     */
    @RequestMapping("creatH5Repay")
    private void creatH5RepayOrder(@RequestParam(value = "userId", required = true) String userId,
                                   @RequestParam(value = "app_request", required = true) String app_request) {
        try {
            Map<String, Object> result = new HashMap<>();
//        Long userId = getSessionUserId();
            Long user_id = Long.parseLong(userId);

            List<BorrowRepay> borrowRepayList = getBorrowRepays(user_id);

            if (borrowRepayList.isEmpty()) {
                resultReturn(result, Constant.OTHER_CODE_VALUE, "无需还款订单");
                return;
            }

            BorrowRepay borrowRepay = borrowRepayList.get(0);

            BankCard bankCard = bankCardService.getBankCardByUserId(user_id);
            User user = new User();//cloanUserService.getById(user_id);
            UserBaseInfo baseInfo = userBaseInfoService.findByUserId(user_id);
            Borrow borrow = clBorrowService.getById(borrowRepay.getBorrowId());


            // 扣款失败无异步通知 故先查询订单是否已经在支付处理中
            // FIXME: 2017/11/16 扣款分手动、自动，查询需重写
            PayLog repaymentLog = getPayLog(user_id, borrowRepay);

            // FIXME: 2017/11/15 记录存在，且不是支付失败 需要查询支付方得到准确结果
            if (null != repaymentLog && !PayLogModel.STATE_PAYMENT_FAILED.equals(repaymentLog.getState())) {
                if (PayLogModel.STATE_PAYMENT_SUCCESS.equals(repaymentLog.getState())) {
                    resultReturn(result, Constant.OTHER_CODE_VALUE, "付款状态 - 支付成功");
                    return;
                }

                QueryRepaymentModel queryRepayment = queryRepaymentModelByOrderNo(repaymentLog);

                if (queryRepayment.checkReturn() && LianLianConstant.RESULT_SUCCESS.equals(queryRepayment.getResult_pay())) {
                    // 查找对应的还款计划
                    updateBorrow(borrowRepay, repaymentLog);

                    // 更新订单状态
                    updatePayLogSuccess(repaymentLog);

                    // FIXME: 2017/11/21 是否发送还款成功短信？
                    if(tool.util.DateUtil.daysBetween(tool.util.DateUtil.getNow(), borrowRepay.getRepayTime())>=0){
                        //未逾期还款成功
                        clSmsService.repayInform(borrowRepay.getUserId(), borrowRepay.getBorrowId(),"noRepayInform");
                    }else{
                        clSmsService.repayInform(borrowRepay.getUserId(), borrowRepay.getBorrowId(),"repayInformSucess");
                    }
                } else if (queryRepayment.checkReturn() && LianLianConstant.RESULT_PROCESSING.equals(queryRepayment.getResult_pay())) {
                    resultReturn(result, Constant.OTHER_CODE_VALUE, "还款处理中，请等待");
                    return;
                    // FIXME: 2017/11/29 lianlian said waiting is failed if time in valid_order
                } else if (queryRepayment.checkReturn() && LianLianConstant.RESULT_WAITING.equals(queryRepayment.getResult_pay())) {
                    resultReturn(result, Constant.OTHER_CODE_VALUE, "还款处理中，请等待");
                    return;
                } else {
                    // 更新订单状态
                    updatePayLogFailed(repaymentLog, "");
                }
            }


            // 计算实际还款金额
            //double principal = BigDecimalUtil.sub(borrowRepay.getAmount(), borrowRepay.getReliefAmount());
            //double penaltyAmout = BigDecimalUtil.sub(borrowRepay.getPenaltyAmout(), borrowRepay.getReliefFine());
            double principal = BigDecimalUtil.sub(borrowRepay.getAmount(), 0);
            double penaltyAmout = BigDecimalUtil.sub(borrowRepay.getPenaltyAmout(), 0);
            double amount = BigDecimalUtil.add(principal, penaltyAmout);

            RiskItems riskItems = new RiskItems(user.getUuid(), baseInfo.getPhone(), DateUtil.dateStr3(user.getRegistTime()), baseInfo.getRealName(),
                    baseInfo.getIdNo());
            Date repayTime = com.xindaibao.cashloan.core.common.util.DateUtil.getNow();
            String orderNo = com.xindaibao.cashloan.core.common.util.OrderNoUtil.getSerialNumber();

            RepaymentModel repayment = getRepaymentModelToH5(bankCard, user, baseInfo, borrow,
                    amount, riskItems, repayTime, orderNo, app_request);

            // FIXME: 2017/11/16 只返回app数据 ，还是需要生成初始订单
            resultReturn(result, Constant.SUCCEED_CODE_VALUE, "成功", repayment);

            LianLianHelper.saveReqLog(repayment);

            // FIXME: 2017/11/16 是否生成订单
            savepayLog(borrowRepay, bankCard, repayTime, repayment);

        } catch (Exception e) {
            logger.error("手动还款-h5异常：e:", e);
        }
    }

    /**
     * 创建还款订单
     * h5
     */
    @RequestMapping("creatFastRepay")
    private void creatFastRepay(@RequestParam(value = "userId", required = true) String userId,
                                @RequestParam(value = "app_request", required = true) String app_request) {
        try {
            Map<String, Object> result = new HashMap<>();
            Long user_id = Long.parseLong(userId);

            List<BorrowRepay> borrowRepayList = getBorrowRepays(user_id);

            if (borrowRepayList.isEmpty()) {
                resultReturn(result, Constant.OTHER_CODE_VALUE, "无需还款订单");
                return;
            }

            BorrowRepay borrowRepay = borrowRepayList.get(0);

            BankCard bankCard = bankCardService.getBankCardByUserId(user_id);
            User user = new User();//cloanUserService.getById(user_id);
            UserBaseInfo baseInfo = userBaseInfoService.findByUserId(user_id);
            Borrow borrow = clBorrowService.getById(borrowRepay.getBorrowId());


            // 扣款失败无异步通知 故先查询订单是否已经在支付处理中
            // FIXME: 2017/11/16 扣款分手动、自动，查询需重写
            PayLog repaymentLog = payLogService.findRepayLatestOne(user_id, borrowRepay.getBorrowId());

            // FIXME: 2017/11/15 记录存在，且不是支付失败 需要查询支付方得到准确结果
            if (null != repaymentLog && !PayLogModel.STATE_PAYMENT_FAILED.equals(repaymentLog.getState())) {
                if (PayLogModel.STATE_PAYMENT_SUCCESS.equals(repaymentLog.getState())) {
                    resultReturn(result, Constant.OTHER_CODE_VALUE, "付款状态 - 支付成功");
                    return;
                }

                QueryRepaymentModel queryRepayment = queryRepaymentModelByOrderNo(repaymentLog);

                if (queryRepayment.checkReturn() && LianLianConstant.RESULT_SUCCESS.equals(queryRepayment.getResult_pay())) {
                    // 查找对应的还款计划
                    updateBorrow(borrowRepay, repaymentLog);

                    // 更新订单状态
                    updatePayLogSuccess(repaymentLog);

                    // FIXME: 2017/11/21 是否发送还款成功短信？
                    if(tool.util.DateUtil.daysBetween(tool.util.DateUtil.getNow(), borrowRepay.getRepayTime())>=0){
                        //未逾期还款成功
                        clSmsService.repayInform(borrowRepay.getUserId(), borrowRepay.getBorrowId(),"noRepayInform");
                    }else{
                        clSmsService.repayInform(borrowRepay.getUserId(), borrowRepay.getBorrowId(),"repayInformSucess");
                    }
                } else if (queryRepayment.checkReturn() && LianLianConstant.RESULT_PROCESSING.equals(queryRepayment.getResult_pay())) {
                    resultReturn(result, Constant.OTHER_CODE_VALUE, "还款处理中，请等待");
                    return;
                } else if (queryRepayment.checkReturn() && LianLianConstant.RESULT_WAITING.equals(queryRepayment.getResult_pay())) {
                    resultReturn(result, Constant.OTHER_CODE_VALUE, "还款处理中，请等待");
                    return;
                } else {
                    // 更新订单状态
                    updatePayLogFailed(repaymentLog, "");
                }
            }


            // 计算实际还款金额
            //double principal = BigDecimalUtil.sub(borrowRepay.getAmount(), borrowRepay.getReliefAmount());
            //double penaltyAmout = BigDecimalUtil.sub(borrowRepay.getPenaltyAmout(), borrowRepay.getReliefFine());
            double principal = BigDecimalUtil.sub(borrowRepay.getAmount(), 0);
            double penaltyAmout = BigDecimalUtil.sub(borrowRepay.getPenaltyAmout(), 0);
            double amount = BigDecimalUtil.add(principal, penaltyAmout);

            RiskItems riskItems = new RiskItems(user.getUuid(), baseInfo.getPhone(), DateUtil.dateStr3(user.getRegistTime()), baseInfo.getRealName(),
                    baseInfo.getIdNo());
            Date repayTime = com.xindaibao.cashloan.core.common.util.DateUtil.getNow();
            String orderNo = com.xindaibao.cashloan.core.common.util.OrderNoUtil.getSerialNumber();

            RepaymentModel repayment = getRepaymentModelForFast(bankCard, user, baseInfo, borrow,
                    amount, riskItems, repayTime, orderNo, app_request);

            // FIXME: 2017/11/16 只返回app数据 ，还是需要生成初始订单
            resultReturn(result, Constant.SUCCEED_CODE_VALUE, "成功", repayment);

            LianLianHelper.saveReqLog(repayment);

            // FIXME: 2017/11/16 是否生成订单
            saveFastPayLog(borrowRepay, bankCard, repayTime, repayment);

        } catch (Exception e) {
            logger.error("手动还款异常：e:", e);
        }
    }

    /**
     * 快捷
     * 还款同步返回
     * @param res_data
     * @throws IOException
     */
    @RequestMapping("syncFastRepay")
    private void syncFastRepayOrder(String res_data) throws IOException {
        logger.info("--手动还款-同步----" + res_data);
        try {
            RepaymentModel model = JSONObject.parseObject(res_data, RepaymentModel.class);
            boolean verifySignFlag = model.checkSign(model);
            if (!verifySignFlag) {
                logger.error("手动还款-同步-验签失败" + model.getNo_order());
                return;
            }
            logger.info("手动还款-同步-进入订单" + model.getNo_order() + "处理中.....");

            PayReqLog payReqLog = payReqLogService.findByOrderNo(model.getNo_order());
            if (payReqLog != null) {
                // 保存respLog
                PayRespLog payRespLog = new PayRespLog(model.getNo_order(), PayRespLogModel.RESP_LOG_TYPE_NOTIFY, res_data);
                payRespLogService.save(payRespLog);

                // 更新reqLog
                modifyPayReqLog(payReqLog, res_data);
            }

            PayLog payLog = payLogService.findByOrderNo(model.getNo_order());
            if (null == payLog) {
                logger.warn("手动还款-同步-未查询到对应的支付订单--", model.getNo_order());
                return;
            }

            if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())
                    || PayLogModel.STATE_PENDING_REVIEW.equals(payLog.getState())) {

                // FIXME: 2017/11/20 判断连连状态，
                if (LianLianConstant.RESULT_SUCCESS.equals(model.getResult_pay())) {

                    afterSuccess(payLog);

                } else if (LianLianConstant.RESULT_FAILURE.equals(model.getResult_pay())) {
                    updatePayLogFailed(payLog, "sync-" + model.getRet_msg());
                }

            } else {
                logger.info("订单" + payLog.getOrderNo() + "手动还款-同步-已处理");
            }

            Map<String, Object> modelMap = new HashMap<>();
            switch (payLog.getState()) {
                case PayLogModel.STATE_PAYMENT_SUCCESS:
                    modelMap.put("code", 1);
                    break;
                case PayLogModel.STATE_PAYMENT_FAILED:
                    modelMap.put("code", 2);
                    break;
                default:
                    modelMap.put("code", 0);
                    break;
            }

        } catch (Exception e) {
            logger.error("手动还款同步通知异常：e:", res_data, e);
        }
        ServletUtils.writeToResponse(response, Global.getValue("server_host") + "/h5/appCarry.jsp");
        return;
    }

    /**
     * 还款同步返回
     * @param res_data
     * @throws IOException
     */
    @RequestMapping("syncRepay")
    private void syncRepayOrder(String res_data) throws IOException {
        logger.info("--手动还款-同步----" + res_data);
        try {
            RepaymentModel model = JSONObject.parseObject(res_data, RepaymentModel.class);
            boolean verifySignFlag = model.checkSign(model);
            if (!verifySignFlag) {
                logger.error("手动还款-同步-验签失败" + model.getNo_order());
                return;
            }
            logger.info("手动还款-同步-进入订单" + model.getNo_order() + "处理中.....");

            PayReqLog payReqLog = payReqLogService.findByOrderNo(model.getNo_order());
            if (payReqLog != null) {
                // 保存respLog
                PayRespLog payRespLog = new PayRespLog(model.getNo_order(), PayRespLogModel.RESP_LOG_TYPE_NOTIFY, res_data);
                payRespLogService.save(payRespLog);

                // 更新reqLog
                modifyPayReqLog(payReqLog, res_data);
            }

            PayLog payLog = payLogService.findByOrderNo(model.getNo_order());
            if (null == payLog) {
                logger.warn("手动还款-同步-未查询到对应的支付订单--", model.getNo_order());
                return;
            }

            if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())
                    || PayLogModel.STATE_PENDING_REVIEW.equals(payLog.getState())) {

                // FIXME: 2017/11/20 判断连连状态，
                if (LianLianConstant.RESULT_SUCCESS.equals(model.getResult_pay())) {

                    afterSuccess(payLog);

                } else if (LianLianConstant.RESULT_FAILURE.equals(model.getResult_pay())) {
                    updatePayLogFailed(payLog, "sync-" + model.getRet_msg());
                }

            } else {
                logger.info("订单" + payLog.getOrderNo() + "手动还款-同步-已处理");
            }

            Map<String, Object> modelMap = new HashMap<>();
            switch (payLog.getState()) {
                case PayLogModel.STATE_PAYMENT_SUCCESS:
                    modelMap.put("code", 1);
                    break;
                case PayLogModel.STATE_PAYMENT_FAILED:
                    modelMap.put("code", 2);
                    break;
                default:
                    modelMap.put("code", 0);
                    break;
            }

        } catch (Exception e) {
            logger.error("手动还款同步通知异常：e:", res_data, e);
        }
        ServletUtils.writeToResponse(response, Global.getValue("server_host") + "/h5/appCarry.jsp");
        return;
    }


    private List<BorrowRepay> getBorrowRepays(Long user_id) {
        Map<String, Object> paramMap = Maps.newHashMap();

        paramMap.put("userId", user_id);
        paramMap.put("state", BorrowRepayModel.STATE_REPAY_NO);
        return borrowRepayService.findUnRepay(paramMap);
    }

    private QueryRepaymentModel queryRepaymentModelByOrderNo(PayLog repaymentLog) {
        Map<String, Object> queryRepaymentMap = new HashMap<>();
        queryRepaymentMap.put("queryOrderNo", repaymentLog.getOrderNo());
        queryRepaymentMap.put("queryOrderTime", repaymentLog.getPayReqTime());
        return (QueryRepaymentModel) LianLianHelper.queryRepayment(queryRepaymentMap);
    }

    private RepaymentModel getRepaymentModelForFast(BankCard bankCard, User user, UserBaseInfo baseInfo,
                                                    Borrow borrow, double amount, RiskItems riskItems,
                                                    Date repayTime, String orderNo, String app_request) {
        RepaymentModel repayment = new RepaymentModel(orderNo, "fast");
        repayment.setUser_id(user.getUuid());
        repayment.setApp_request(app_request);
        repayment.setId_type("0");
        repayment.setValid_order("10");
        repayment.setBusi_partner(LianLianConstant.GOODS_VIRTUAL);
        repayment.setDt_order(DateUtil.dateStr3(repayTime));
        repayment.setName_goods(borrow.getOrderNo() + "还款");
        repayment.setInfo_order(borrow.getOrderNo() + "还款");
        if ("dev".equals(Global.getValue("app_environment"))) {
            repayment.setMoney_order("0.01");
            repayment.setAmount(0.01);
        } else {
            repayment.setMoney_order(StringUtil.isNull(amount));
            repayment.setAmount(amount);
        }


        repayment.setUrl_return(Global.getValue("server_host") + "/api/act/repay/syncFastRepay.htm");
        repayment.setRisk_item(JSONObject.toJSONString(riskItems));
        repayment.setSchedule_repayment_date(DateUtil.dateStr2(repayTime));
        repayment.setRepayment_no(borrow.getOrderNo());
        repayment.setNo_agree(bankCard.getAgreeNo());
        repayment.setBank_code(bankCard.getCardNo());
        repayment.setId_no(baseInfo.getIdNo());
        repayment.setAcct_name(baseInfo.getRealName());
        repayment.setNotify_url(Global.getValue("server_host") + "/pay/lianlian/manualFastRepayNotify.htm");

        Map<String, Object> map = repayment.paramToMap(repayment.signParamNamesFast());
        repayment.setSign(SignUtil.genRSASign(JSON.toJSONString(map)));
        return repayment;
    }

    private RepaymentModel getRepaymentModelToH5(BankCard bankCard, User user, UserBaseInfo baseInfo,
                                                 Borrow borrow, double amount, RiskItems riskItems,
                                                 Date repayTime, String orderNo, String app_request) {
        logger.info("订单" + borrow.getOrderNo() + "手动还款-h5");
        RepaymentModel repayment = new RepaymentModel(orderNo, "manual");
        repayment.setUser_id(user.getUuid());
        repayment.setApp_request(app_request);
        repayment.setId_type("0");
        repayment.setValid_order("10");
        repayment.setBusi_partner(LianLianConstant.GOODS_VIRTUAL);
        repayment.setDt_order(DateUtil.dateStr3(repayTime));
        repayment.setName_goods(borrow.getOrderNo() + "还款");
        repayment.setInfo_order(borrow.getOrderNo() + "还款");
        if ("dev".equals(Global.getValue("app_environment"))) {
            repayment.setMoney_order("0.01");
            repayment.setAmount(0.01);
        } else {
            repayment.setMoney_order(StringUtil.isNull(amount));
            repayment.setAmount(amount);
        }


        repayment.setUrl_return(Global.getValue("server_host") + "/api/act/repay/syncRepay.htm");
        repayment.setRisk_item(JSONObject.toJSONString(riskItems));
        repayment.setSchedule_repayment_date(DateUtil.dateStr2(repayTime));
        repayment.setRepayment_no(borrow.getOrderNo());
        repayment.setNo_agree(bankCard.getAgreeNo());
        repayment.setBank_code(bankCard.getCardNo());
        repayment.setId_no(baseInfo.getIdNo());
        repayment.setAcct_name(baseInfo.getRealName());
        repayment.setNotify_url(Global.getValue("server_host") + "/pay/lianlian/manualRepayNotify.htm");

        Map<String, Object> map = repayment.paramToMap(repayment.signParamNamesToH5());
        repayment.setSign(SignUtil.genRSASign(JSON.toJSONString(map)));
        return repayment;
    }

    private RepaymentModel getRepaymentModelForApi(BankCard bankCard, User user, UserBaseInfo baseInfo, Borrow borrow, double amount, RiskItems riskItems, Date repayTime, String orderNo) {
        RepaymentModel repayment = new RepaymentModel(orderNo, "manual");
        repayment.setUser_id(user.getUuid());
        repayment.setBusi_partner(LianLianConstant.GOODS_VIRTUAL);
        repayment.setDt_order(DateUtil.dateStr3(repayTime));
        repayment.setName_goods(borrow.getOrderNo() + "还款");
        repayment.setInfo_order(borrow.getOrderNo() + "还款");
        if ("dev".equals(Global.getValue("app_environment"))) {
            repayment.setMoney_order("0.01");
            repayment.setAmount(0.01);
        } else {
            repayment.setMoney_order(StringUtil.isNull(amount));
            repayment.setAmount(amount);
        }


        repayment.setUrl_return(Global.getValue("server_host") + "/api/act/repay/syncRepay.htm");
        repayment.setRisk_item(JSONObject.toJSONString(riskItems));
        repayment.setSchedule_repayment_date(DateUtil.dateStr2(repayTime));
        repayment.setRepayment_no(borrow.getOrderNo());
        repayment.setNo_agree(bankCard.getAgreeNo());
        repayment.setBank_code(bankCard.getCardNo());
        repayment.setId_no(baseInfo.getIdNo());
        repayment.setAcct_name(baseInfo.getRealName());
        repayment.setNotify_url(Global.getValue("server_host") + "/pay/lianlian/manualRepayNotify.htm");

        Map<String, Object> map = repayment.paramToMap(repayment.signParamNames());
        repayment.setSign(SignUtil.genRSASign(JSON.toJSONString(map)));
        return repayment;
    }

    private void afterSuccess(PayLog payLog) {
        // 查找对应的还款计划
        Map<String, Object> repayMap = new HashMap<String, Object>();
        repayMap.put("userId", payLog.getUserId());
        repayMap.put("borrowId", payLog.getBorrowId());
        BorrowRepay borrowRepay = borrowRepayService.findSelective(repayMap);
        BankCard bankCard = bankCardService.getBankCardByUserId(payLog.getUserId());

        if (borrowRepay != null) {
            updateBorrow(payLog, borrowRepay, bankCard);
        }
        updatePayLogSuccess(payLog);


        //发送扣款成功短信
        if(tool.util.DateUtil.daysBetween(tool.util.DateUtil.getNow(), borrowRepay.getRepayTime())>=0){
            //未逾期还款成功
            clSmsService.repayInform(borrowRepay.getUserId(), borrowRepay.getBorrowId(),"noRepayInform");
        }else{
            clSmsService.repayInform(borrowRepay.getUserId(), borrowRepay.getBorrowId(),"repayInformSucess");
        }
    }

    private void savepayLog(BorrowRepay borrowRepay, BankCard bankCard, Date repayTime, RepaymentModel repayment) {
        PayLog payLog = new PayLog();
        payLog.setOrderNo(repayment.getOrderNo());
        payLog.setUserId(borrowRepay.getUserId());
        payLog.setBorrowId(borrowRepay.getBorrowId());
        payLog.setAmount(repayment.getAmount());
        payLog.setCardNo(bankCard.getCardNo());
        payLog.setBank(bankCard.getBank());
        payLog.setSource(PayLogModel.SOURCE_FUNDS_OWN);
        payLog.setType(PayLogModel.TYPE_ACTIVE_DEBIT_LIANLIAN);
        payLog.setScenes(PayLogModel.SCENES_ACTIVE_REPAYMENT);
        payLog.setState(PayLogModel.STATE_PAYMENT_WAIT);
        payLog.setRemark("创建订单");
        payLog.setPayReqTime(repayTime);
        payLog.setCreateTime(com.xindaibao.cashloan.core.common.util.DateUtil.getNow());
        payLogService.save(payLog);
    }

    private void saveFastPayLog(BorrowRepay borrowRepay, BankCard bankCard, Date repayTime, RepaymentModel repayment) {
        PayLog payLog = new PayLog();
        payLog.setOrderNo(repayment.getOrderNo());
        payLog.setUserId(borrowRepay.getUserId());
        payLog.setBorrowId(borrowRepay.getBorrowId());
        payLog.setAmount(repayment.getAmount());
        payLog.setCardNo(bankCard.getCardNo());
        payLog.setBank(bankCard.getBank());
        payLog.setSource(PayLogModel.SOURCE_FUNDS_OWN);
        payLog.setType(PayLogModel.TYPE_ACTIVE_DEBIT_LIANLIAN_FAST);
        payLog.setScenes(PayLogModel.SCENES_ACTIVE_REPAYMENT);
        payLog.setState(PayLogModel.STATE_PAYMENT_WAIT);
        payLog.setRemark("创建订单");
        payLog.setPayReqTime(repayTime);
        payLog.setCreateTime(com.xindaibao.cashloan.core.common.util.DateUtil.getNow());
        payLogService.save(payLog);
    }


    private void updateBorrow(PayLog payLog, BorrowRepay borrowRepay, BankCard bankCard) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", borrowRepay.getId());
        //param.put("repayTime", DateUtil.dateStr4(DateUtil.getNow()));
        param.put("repayTime", DateUtil.getNow());
        param.put("repayWay", BorrowRepayLogModel.REPAY_WAY_CERTIFIED);
        param.put("repayAccount", bankCard.getCardNo());
        param.put("amount", payLog.getAmount());
        param.put("serialNumber", payLog.getOrderNo());
        param.put("penaltyAmout", borrowRepay.getPenaltyAmout());
        param.put("state", "10");
        // FIXME: 2017/11/21 借款状态 未结束
        if (!borrowRepay.getState().equals(BorrowRepayModel.STATE_REPAY_YES)) {
            borrowRepayService.confirmRepay(param);
        }
    }

    private void updatePayLogSuccess(PayLog payLog) {
        // 更新订单状态
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
        paramMap.put("updateTime", DateUtil.getNow());
        paramMap.put("id", payLog.getId());
        payLogService.updateSelective(paramMap);
    }

    private void updatePayLogFailed(PayLog repaymentLog, String failedMsg) {
        Map<String, Object> payLogParamMap = new HashMap<String, Object>();
        payLogParamMap.put("state", PayLogModel.STATE_PAYMENT_FAILED);
        payLogParamMap.put("updateTime", com.xindaibao.cashloan.core.common.util.DateUtil.getNow());
        payLogParamMap.put("remark", failedMsg);
        payLogParamMap.put("id", repaymentLog.getId());
        payLogService.updateSelective(payLogParamMap);
    }

    private PayLog getPayLog(Long user_id, BorrowRepay borrowRepay) {
        // FIXME: 2017/11/16 扣款分手动、自动，查询需重写
        Map<String, Object> payLogMap = new HashMap<String, Object>();
        payLogMap.put("userId", user_id);
        payLogMap.put("borrowId", borrowRepay.getBorrowId());
        payLogMap.put("type", PayLogModel.TYPE_ACTIVE_DEBIT_LIANLIAN);
        payLogMap.put("scenes", PayLogModel.SCENES_ACTIVE_REPAYMENT);
        PayLog repaymentLog = payLogService.findLatestOne(payLogMap);

        // FIXME: 2017/11/22 不改变查询代码前提下，无自动扣款记录，再查询手动还款记录
        if(null == repaymentLog ){
            payLogMap.put("type", PayLogModel.TYPE_COLLECT);
            payLogMap.put("scenes", PayLogModel.SCENES_REPAYMENT);
            repaymentLog = payLogService.findLatestOne(payLogMap);
        }
        return repaymentLog;
    }

    private void updateBorrow(BorrowRepay borrowRepay, PayLog repaymentLog) {
        // 查找对应的还款计划
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", borrowRepay.getId());
        param.put("repayTime", com.xindaibao.cashloan.core.common.util.DateUtil.getNow());
        param.put("repayWay", BorrowRepayLogModel.REPAY_WAY_CERTIFIED);
//					param.put("repayAccount", bankCard.getCardNo());
        param.put("amount", borrowRepay.getAmount());
        param.put("serialNumber", repaymentLog.getOrderNo());
        param.put("penaltyAmout", borrowRepay.getPenaltyAmout());
        param.put("state", "10");
        if (!borrowRepay.getState().equals(BorrowRepayModel.STATE_REPAY_YES)) {
            borrowRepayService.confirmRepay(param);
        }
    }
    private void modifyPayReqLog(PayReqLog payReqLog, String params) {
        payReqLog.setNotifyParams(params);
        payReqLog.setNotifyTime(DateUtil.getNow());
        payReqLogService.updateById(payReqLog);
    }

    private void resultReturn(Map<String, Object> result, Object responseCode, Object responseMsg) {
        result.put(Constant.RESPONSE_CODE, responseCode);
        result.put(Constant.RESPONSE_CODE_MSG, responseMsg);
        ServletUtils.writeToResponse(response, result);
        return;
    }

    private void resultReturn(Map<String, Object> result, Object responseCode, Object responseMsg, Object date) {
        result.put(Constant.RESPONSE_CODE, responseCode);
        result.put(Constant.RESPONSE_CODE_MSG, responseMsg);
        result.put(Constant.RESPONSE_DATA, date);
        ServletUtils.writeToResponse(response, result);
        return;
    }


}