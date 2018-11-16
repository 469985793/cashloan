import com.xindaibao.cashloan.cl.domain.BankCard;
import com.xindaibao.cashloan.cl.domain.BorrowProgress;
import com.xindaibao.cashloan.cl.domain.PayLog;
import com.xindaibao.cashloan.cl.mapper.BankCardMapper;
import com.xindaibao.cashloan.cl.mapper.BorrowProgressMapper;
import com.xindaibao.cashloan.cl.mapper.ClBorrowMapper;
import com.xindaibao.cashloan.cl.mapper.PayLogMapper;
import com.xindaibao.cashloan.cl.model.PayLogModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.PaymentModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.util.LianLianHelper;
import com.xindaibao.cashloan.cl.monitor.BusinessExceptionMonitor;
import com.xindaibao.cashloan.cl.service.BorrowRepayService;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.util.DateUtil;
import com.xindaibao.cashloan.core.common.util.OrderNoUtil;
import com.xindaibao.cashloan.core.common.util.StringUtil;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.mapper.UserBaseInfoMapper;
import com.xindaibao.cashloan.core.model.BorrowModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sparta on 2017/10/27.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration({"classpath:config/web/web-main.xml", "classpath:config/spring/*.xml"}) //加载配置文件
public class LianLianLoanTest {

    @Autowired private BankCardMapper bankCardMapper;
    @Autowired private UserBaseInfoMapper userBaseInfoMapper;
    @Autowired private ClBorrowMapper clBorrowMapper;
    @Autowired private PayLogMapper payLogMapper;
    @Autowired private BorrowProgressMapper borrowProgressMapper;
    @Autowired private BorrowRepayService borrowRepayService;


    @Test
    public void test(){
        Borrow borrow1 = new Borrow();
        borrow1.setUserId(12l);
        borrow1.setRealAmount(1.0);
        borrow1.setOrderNo("serialNumer74985734940");

        Date date = new Date();


        Map<String, Object> bankCardMap = new HashMap<String, Object>();
        bankCardMap.put("userId", borrow1.getUserId());
        BankCard bankCard = bankCardMapper.findSelective(bankCardMap);

        //UserBaseInfo baseInfo = userBaseInfoMapper.findByUserId(borrow1.getUserId());

        UserBaseInfo userBaseInfo = new UserBaseInfo();
        userBaseInfo.setRealName("查建强");

        String orderNo = OrderNoUtil.getSerialNumber();
//				PaymentModel payment = new PaymentModel(orderNo);
//				payment.setDt_order(DateUtil.dateStr3(date));
//				if ("dev".equals(Global.getValue("app_environment"))) {
//					payment.setMoney_order("0.01");
//				} else {
//					payment.setMoney_order(StringUtil.isNull(borrow.getRealAmount()));
//				}
//				payment.setAmount(borrow.getRealAmount());
//				payment.setCard_no(bankCard.getCardNo());
//				payment.setAcct_name(baseInfo.getRealName());
//				payment.setInfo_order(borrow.getOrderNo() + "付款");
//				payment.setMemo(borrow.getOrderNo() + "付款");
//				payment.setNotify_url(Global.getValue("server_host") + "/pay/lianlian/paymentNotify.htm");
//				LianLianHelper helper = new LianLianHelper();
//				payment = (PaymentModel) helper.payment(payment);

        Map<String, Object> paymentMap = new HashMap<>();
        paymentMap.put("payTime", date);
        paymentMap.put("amount", borrow1.getRealAmount());
        paymentMap.put("cardNo", bankCard.getCardNo());
        paymentMap.put("realName", userBaseInfo.getRealName());
        paymentMap.put("orderMemoInfo", borrow1.getOrderNo() + "-放款付款");
        paymentMap.put("notifyUrl", "http://api.zuoli360.com" + "/pay/lianlian/paymentNotify.htm");

        PaymentModel payment = (PaymentModel) LianLianHelper.payment(paymentMap);
        System.out.println("over");

        /*PayLog payLog = new PayLog();
        payLog.setOrderNo(payment.getNo_order());
        payLog.setUserId(borrow1.getUserId());
        payLog.setBorrowId(borrow1.getId());
        payLog.setAmount(payment.getAmount());
        payLog.setCardNo(bankCard.getCardNo());
        payLog.setBank(bankCard.getBank());
        payLog.setSource(PayLogModel.SOURCE_FUNDS_OWN);
        payLog.setType(PayLogModel.TYPE_PAYMENT);
        payLog.setScenes(PayLogModel.SCENES_LOANS);

        if (payment.checkReturn()) { // 已生成连连支付单，付款处理中（交易成功，不是指付款成功，是指流程正常）
            payLog.setState(PayLogModel.STATE_PAYMENT_WAIT);
        } else if ("4002".equals(payment.getRet_code())
                || "4003".equals(payment.getRet_code())
                || "4004".equals(payment.getRet_code())) { // 疑似重复订单，待人工审核
            payLog.setState(PayLogModel.STATE_PENDING_REVIEW);
            payLog.setConfirmCode(payment.getConfirm_code());
            payLog.setUpdateTime(DateUtil.getNow());
        } else if ("4006".equals(payment.getRet_code()) // 敏感信息加密异常
                || "4007".equals(payment.getRet_code()) // 敏感信息解密异常
                || "4009".equals(payment.getRet_code())) { // 验证码异常
            payLog.setState(PayLogModel.STATE_PAYMENT_WAIT);
        } else {
            BusinessExceptionMonitor.add(BusinessExceptionMonitor.TYPE_9, payLog.getOrderNo(), payment.getRet_msg());
            payLog.setState(PayLogModel.STATE_PAYMENT_FAILED);
            payLog.setUpdateTime(DateUtil.getNow());

           // clBorrowMapper.updateState(BorrowModel.STATE_REPAY_FAIL ,borrow1.getId());
        }*/
/*
        payLog.setRemark(payment.getRet_msg());
        payLog.setPayReqTime(date);
        payLog.setCreateTime(DateUtil.getNow());
        payLogMapper.save(payLog);
        //clSmsService.loanInform(borrow.getUserId(), borrow.getId());
        //demo环境下连连支付不开启状态，直接模拟连连回调成功
        String lianlianSwitch = Global.getValue("lianlian_switch");
        if ("dev".equals(Global.getValue("app_environment"))&& StringUtil.isNotBlank(lianlianSwitch) && "2".equals(lianlianSwitch)) {
            if(PayLogModel.SCENES_LOANS.equals(payLog.getScenes())){
                log.info("模拟连连放款回调成功，生成借款计划。。");
                // 修改借款状态
                Map<String, Object> map = new HashMap<>();
                map.put("id", payLog.getBorrowId());
                map.put("state", BorrowModel.STATE_REPAY);
                clBorrowMapper.updatePayState(map);

                // 放款进度添加
                BorrowProgress bp = new BorrowProgress();
                bp.setUserId(payLog.getUserId());
                bp.setBorrowId(payLog.getBorrowId());
                bp.setState(BorrowModel.STATE_REPAY);
                bp.setRemark(BorrowModel.convertBorrowRemark(bp.getState()));
                bp.setCreateTime(DateUtil.getNow());
                borrowProgressMapper.save(bp);

                Borrow borrow = clBorrowMapper.findByPrimary(payLog.getBorrowId());

                // 生成还款计划并授权
                borrowRepayService.genRepayPlan(borrow, DateUtil.getNow());
                // 更新订单状态
                Map<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
                paramMap.put("updateTime",DateUtil.getNow());
                paramMap.put("id",payLog.getId());
                payLogMapper.updateSelective(paramMap);

            }
        }*/
    }
}
