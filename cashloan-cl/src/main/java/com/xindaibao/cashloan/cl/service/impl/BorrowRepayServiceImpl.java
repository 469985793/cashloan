package com.xindaibao.cashloan.cl.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.domain.BankCard;
import com.xindaibao.cashloan.cl.domain.BorrowRepay;
import com.xindaibao.cashloan.cl.domain.UrgeRepayOrder;
import com.xindaibao.cashloan.cl.domain.UserInvite;
import com.xindaibao.cashloan.cl.mapper.*;
import com.xindaibao.cashloan.cl.model.kenya.LoanRecord;
import com.xindaibao.cashloan.cl.model.pay.alipay.helper.AlipayHelper;
import com.xindaibao.cashloan.cl.model.pay.lianlian.constant.LianLianConstant;
import com.xindaibao.cashloan.cl.model.pay.weixin.helper.WeixinPayHelper;
import com.xindaibao.cashloan.cl.service.UrgeRepayOrderLogService;
import com.xindaibao.cashloan.cl.service.UrgeRepayOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.cl.domain.BorrowProgress;
import com.xindaibao.cashloan.cl.domain.BorrowRepayLog;
import com.xindaibao.cashloan.cl.domain.PayLog;
import com.xindaibao.cashloan.cl.domain.UrgeRepayOrderLog;
import com.xindaibao.cashloan.cl.model.AlipayModel;
import com.xindaibao.cashloan.cl.model.BorrowRepayLogModel;
import com.xindaibao.cashloan.cl.model.BorrowRepayModel;
import com.xindaibao.cashloan.cl.model.ManageBRepayModel;
import com.xindaibao.cashloan.cl.model.ManageBorrowModel;
import com.xindaibao.cashloan.cl.model.PayLogModel;
import com.xindaibao.cashloan.cl.model.RepayExcelModel;
import com.xindaibao.cashloan.cl.model.UrgeRepayOrderModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.AuthApplyModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.util.LianLianHelper;
import com.xindaibao.cashloan.cl.service.BorrowRepayService;
import com.xindaibao.cashloan.cl.service.ProfitLogService;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.exception.BussinessException;
import com.xindaibao.cashloan.core.common.exception.SimpleMessageException;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.common.util.DateUtil;
import com.xindaibao.cashloan.core.common.util.OrderNoUtil;
import com.xindaibao.cashloan.core.common.util.excel.ReadExcelUtils;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.core.domain.User;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.mapper.UserBaseInfoMapper;
import com.xindaibao.cashloan.core.mapper.UserMapper;
import com.xindaibao.cashloan.core.model.BorrowModel;
import com.xindaibao.creditrank.cr.domain.Credit;
import com.xindaibao.creditrank.cr.mapper.CreditMapper;

import tool.util.BigDecimalUtil;
import tool.util.NumberUtil;
import tool.util.StringUtil;

/**
 * 还款计划ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-14 13:42:32 


 * 

 */

@Service("borrowRepayService")
public class BorrowRepayServiceImpl extends BaseServiceImpl<BorrowRepay, Long> implements BorrowRepayService {

	private static final Logger logger = LoggerFactory.getLogger(BorrowRepayServiceImpl.class);

	@Resource
	private BorrowRepayMapper borrowRepayMapper;
	@Resource
	private BorrowRepayLogMapper borrowRepayLogMapper;
	@Resource
	private ClBorrowMapper clBorrowMapper;
	@Resource
	private LoanRecordMapper loanRecordMapper;
	@Resource
	private BorrowProgressMapper borrowProgressMapper;
	@Resource
	private CreditMapper creditMapper;
	@Resource
	private UrgeRepayOrderService urgeRepayOrderService;
	@Resource
	private UrgeRepayOrderLogService urgeRepayOrderLogService;
	@Resource
	private ProfitLogService profitLogService;
	@Resource
	private UserInviteMapper userInviteMapper;
	@Resource
	private ProfitLogMapper profitLogMapper;
    @Resource
    private BankCardMapper bankCardMapper;
    @Resource
    private UserMapper userMapper;

    @Resource
    private UserBaseInfoMapper userBaseInfoMapper;
    
    @Resource
    private PayLogMapper payLogMapper;
	
    
	@Override
	public BaseMapper<BorrowRepay, Long> getMapper() {
		return borrowRepayMapper;
	}

	@Override
	public int save(BorrowRepay borrowRepay) {
		return borrowRepayMapper.save(borrowRepay);
	}

	
	@Override
	public boolean genRepayPlan(Borrow borrow, Date calcStartTime){
		String beheadFee = Global.getValue("behead_fee");// 是否启用砍头息
		//放款成功,保存还款计划
		BorrowRepay br = new BorrowRepay();
		if ("10".equals(beheadFee)) {//启用
			br.setAmount(borrow.getAmount());
		}else {
			br.setAmount(borrow.getAmount() + borrow.getFee());
		}
		br.setBorrowId(borrow.getId());
		br.setUserId(borrow.getUserId());
		Date repayTime = DateUtil.rollDay(calcStartTime, (Integer.parseInt(borrow.getTimeLimit())) - 1);
		br.setRepayTime(DateUtil.getLastSecIntegralTime(repayTime));
		br.setState(BorrowRepayModel.STATE_REPAY_NO);
		br.setPenaltyAmout(0.0);
		br.setPenaltyDay("0");
		br.setCreateTime(DateUtil.getNow());
		int result = borrowRepayMapper.save(br);
	
		if (result > 0) {
			return true;
		}
		return false;
	}
	
	
    @Override
    public void authSignApply(Long borrowId) {
        Borrow borrow = clBorrowMapper.findByPrimary(borrowId);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("borrowId", borrowId);
        paramMap.put("state", BorrowRepayModel.STATE_REPAY_NO);
        List<BorrowRepay> borrowRepayList = borrowRepayMapper.listSelective(paramMap);

        // 有未还款还款计划，则重新进行授权
        if (borrowRepayList != null && !borrowRepayList.isEmpty()) {
            authApply(borrow.getUserId(), borrow.getOrderNo(), borrowRepayList);
        }
    }

	
    /**
	 * 调用连连支付 分期付 授权接口 为用户授权
	 * 
	 * @param borrow
	 * @param date
	 */
    private void authApply(final long userId, final String borrowOrderNo, final List<BorrowRepay> borrowRepayList) {
        // 查询用户信息及银行卡信息 用于授权
        new Thread() {
            @Override
            public void run() {
                BankCard bankCard = bankCardMapper.findByUserId(userId);
                User user = userMapper.findByPrimary(userId);

                Map<String, Object> authApplyMap = new HashMap<>();
                authApplyMap.put("user", user);
                authApplyMap.put("borrowOrderNo", borrowOrderNo);
                authApplyMap.put("agreeNo", bankCard.getAgreeNo());
                authApplyMap.put("borrowRepayList", borrowRepayList);
                AuthApplyModel authApply = (AuthApplyModel) LianLianHelper.authApply(authApplyMap);

                if (authApply.checkReturn()) {
                    logger.info("订单：" + borrowOrderNo + "分期付授权成功");
                } else {
                    logger.info("订单：" + borrowOrderNo + "分期付授权失败,原因：" + authApply.getRet_msg());
                }
            }
        }.start();
    }
	
	
	@Override
	public Page<ManageBRepayModel> listModel(Map<String, Object> params,
                                             int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<ManageBRepayModel> list = borrowRepayMapper.listModel(params);
		return (Page<ManageBRepayModel>) list;
	}

	@Override
	public void confirmRepay(Map<String, Object> param) {
		Long id = (Long) param.get("id");
		//BorrowRepay br = borrowRepayMapper.findByPrimary(id);
		LoanRecord lr = loanRecordMapper.findByPrimary(id);
		logger.info("进入确认还款...借款id=" + id);
	
		//Borrow borrow = clBorrowMapper.findByPrimary(lr.getId());

//		// 判断还款是否逾期，若逾期 ,额度恢复到提额前
//		if (BorrowModel.STATE_DELAY.equals(borrow.getState())) {
//			Credit credit = creditMapper.findByConsumerNo(StringUtil.isNull(br.getUserId()));
//			if (credit != null && credit.getCount() != null && NumberUtil.getInt(credit.getCount()) > 0) {// 存在提额情况
//				double mentionAmount = BigDecimalUtil.mul(Credit.ONE_TIME_CREDIT, NumberUtil.getInt(credit.getCount()));
//				Map<String, Object> params = new HashMap<String, Object>();// 封装提额参数
//				params.put("consumerNo", br.getUserId());
//				params.put("total", BigDecimalUtil.sub(credit.getTotal(), mentionAmount));// 总额度
//				params.put("unuse", BigDecimalUtil.sub(credit.getUnuse(), mentionAmount));// 未使用额度
//				params.put("count", "0");// 提额次数归零
//				creditMapper.updateByUserId(params);
//			}
//		}
		long percent = getPercent(lr.getCycle(),lr.getBalance());
		boolean isReBorrow = true;
		String state = (String) param.get("state");
		
		if (BorrowRepayModel.NORMAL_REPAYMENT.equals(state)) {
			state = BorrowModel.STATE_FINISH;
			param.put("penaltyAmout", StringUtil.isNull(lr.getOverdueFee()));
            double repayAmount = NumberUtil.getDouble(StringUtil.isNull(param.get("actualbackAmt")));
            double penaltyAmount = NumberUtil.getDouble(StringUtil.isNull(param.get("penaltyAmout")));
            if (lr.getBalance() > repayAmount) {
                throw new BussinessException("还款金额不能小于应还金额");
            }
            if (lr.getOverdueFee() > penaltyAmount) {
                throw new BussinessException("逾期罚金不能小于原逾期罚金");
            }
		} else if (BorrowRepayModel.OVERDUE_RELIEF.equals(state)) {
			state = BorrowModel.STATE_DELAY_REMISSION_FINISH;
            param.put("penaltyAmout", StringUtil.isNull(lr.getOverdueFee()));
			double repayAmount = NumberUtil.getDouble(StringUtil.isNull(param.get("actualbackAmt")));
			double penaltyAmount = NumberUtil.getDouble(StringUtil.isNull(param.get("penaltyAmout")));
			if (lr.getBalance() > repayAmount) {
				throw new BussinessException("还款金额不能小于应还金额");
			}
			if (lr.getOverdueFee() > penaltyAmount) {
				throw new BussinessException("逾期罚金不能小于原逾期罚金");
			}
		}else if(BorrowRepayModel.OVERDUE_REPAYMENT.equals(state)){
			state = BorrowModel.STATE_DELAY_FINISH;
			param.put("penaltyAmout", StringUtil.isNull(lr.getOverdueFee()));
            double repayAmount = NumberUtil.getDouble(StringUtil.isNull(param.get("actualbackAmt")));
            double penaltyAmount = NumberUtil.getDouble(StringUtil.isNull(param.get("penaltyAmout")));
            if (lr.getBalance() > repayAmount) {
                throw new BussinessException("还款金额不能小于应还金额");
            }
            if (lr.getOverdueFee() > penaltyAmount) {
                throw new BussinessException("逾期罚金不能小于原逾期罚金");
            }
		}
//		else if (BorrowRepayModel.RENEW_APPLY_REPAYMENT.equals(state)) { // 续期申请-原订单还款
//			isReBorrow = false;
//			if (borrow.getRenewMark() != null && borrow.getRenewMark() > 0) {
//				if (borrow.getState().equals(BorrowModel.STATE_DELAY)
//						|| borrow.getState().equals(BorrowModel.STATE_BAD)) {
//					state = BorrowModel.STATE_DELAY_FINISH;
//				} else {
//					state = BorrowModel.STATE_FINISH;
//				}
//			} else {
//				state = BorrowModel.STATE_TO_RENEW;
//			}
//
//		}
		else {
			throw new BussinessException("信息提交有误。");
		}
		Date repayTime = (Date) param.get("repayTime");
		// 更新还款信息
		if(BorrowModel.STATE_DELAY.equals(lr.getStatus())){
			state = BorrowModel.STATE_DELAY_FINISH;
		}
		param.put("state",state);
		int msg = updateBorrowReplayForKaney(lr, repayTime, param);
		if (msg <= 0) {
			throw new BussinessException("更新还款信息出错" + lr.getId());
		}
		// 更新借款表和借款进度状态

		// 续借订单还款，修改原订单状态
//		if (borrow.getRenewMark() != null && borrow.getRenewMark() > 0) {
//			Map<String, Object> paramMap = new HashMap<>();
//			paramMap.put("id", borrow.getOriginalId());
//			paramMap.put("renewState", state);
//			clBorrowMapper.updateSelective(paramMap);
//		}
		
		// 信用额度修改
//		Credit credit = creditMapper.findByConsumerNo(StringUtil.isNull( br.getUserId()));
//		if (credit != null) {
//			if(isReBorrow){//续借不修改额度
//				credit.setUnuse(credit.getUnuse() + borrow.getAmount());
//				credit.setUsed(credit.getUsed() > borrow.getAmount() ? credit.getUsed() - borrow.getAmount() : 0);
//				creditMapper.update(credit);
//			}
//		} else {
//			throw new BussinessException("用户信用额度信息不存在" + br.getUserId());
//		}
		
		// 更新催收订单中的状态
		Map<String, Object> orderMap = new HashMap<>();
		orderMap.put("borrowId", lr.getId());
		UrgeRepayOrder order = urgeRepayOrderService.findOrderByMap(orderMap);
		if (order != null) {
			logger.debug("更新存在的催收订单中的状态");
			UrgeRepayOrderLog orderLog = new UrgeRepayOrderLog();
			orderLog.setRemark("用户还款成功");
			orderLog.setWay("50");
			orderLog.setCreateTime(DateUtil.getNow());
			orderLog.setState(UrgeRepayOrderModel.STATE_ORDER_SUCCESS);
			urgeRepayOrderLogService.saveOrderInfo(orderLog, order);
		}

//		// 判断是否有邀请人,若有邀请人则更新借款人的代理商资金奖励
//		Map<String, Object> inviteMap = new HashMap<>();
//		inviteMap.put("inviteId", br.getUserId());
//		UserInvite invite = userInviteMapper.findSelective(inviteMap);
//		if (StringUtil.isNotBlank(invite)) {
//			// 判断是否已分配奖金
//			Map<String, Object> profitMap = new HashMap<>();
//			profitMap.put("borrowId", br.getBorrowId());
//			int count = profitLogMapper.count(profitMap);
//			if (count == 0) {
//				profitLogService.save(br.getBorrowId(), DateUtil.getNow());
//			}
//		}
		// TODO：还款成功提额一次，一次50
//		if(isReBorrow){//续借不提额
//			Credit c = creditMapper.findByConsumerNo(StringUtil.isNull(br.getUserId()));
//			Map<String, Object> map = new HashMap<String, Object>();// 封装提额参数
//			map.put("consumerNo", br.getUserId());
//			map.put("total", c.getTotal() + Credit.ONE_TIME_CREDIT);// 总额度
//			map.put("unuse", c.getUnuse() + Credit.ONE_TIME_CREDIT);// 未使用额度
//			map.put("count", Integer.parseInt(StringUtil.isNull(c.getCount()))+1);// 提额次数+1
//
//			int x = 1;
//
//			if ((Integer.parseInt(StringUtil.isNull(c.getCount()))+1) * Credit.ONE_TIME_CREDIT <= 500) {// 提额上线为500
//				x = creditMapper.updateByUserId(map);
//			}
//			if (x < 1) {
//				throw new BussinessException("自动提额失败");
//			}
//		}
	}

	/**
	 * 更新借款表和借款进度状态
	 * 
	 * @param borrowId
	 * @param userId
	 * @param repayTime
	 * @return
	 */
	public int updateBorrow(long borrowId, long userId,String state){
		 int i = 0;
		// 更新借款状态
		Map<String, Object> stateMap = new HashMap<String, Object>();
		stateMap.put("id", borrowId);
		stateMap.put("state", state);
		 i=clBorrowMapper.updateSelective(stateMap);
		if(i>0){
			// 添加借款进度
			BorrowProgress bp = new BorrowProgress();
			bp.setBorrowId(borrowId);
			bp.setUserId(userId);
			bp.setRemark(BorrowModel.convertBorrowRemark(state));
			bp.setState(state);
			bp.setCreateTime(DateUtil.getNow());
			return borrowProgressMapper.save(bp);
		}
		return i;
	}

	/**
	 * 更新还款计划和还款记录表
	 * 
	 * @param br
	 * @param repayTime
	 * @param param
	 * @return
	 */
	public int updateBorrowReplay(BorrowRepay br, Date repayTime,
			Map<String, Object> param) {
		// 更新还款计划状态
		int i = 0;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", br.getId());
		paramMap.put("state", BorrowRepayModel.STATE_REPAY_YES);
		
		Date repayPlanTime = DateUtil.valueOf(DateUtil.dateStr2(br.getRepayTime()));
		Date repay_time = DateUtil.valueOf(DateUtil.dateStr2(repayTime));
		
		if (StringUtil.isNotBlank(br.getPenaltyDay()) && br.getPenaltyAmout() > 0) {
			//实际还款时间在应还款时间之前或当天（不对比时分秒），重置逾期金额和天数
			if (!repay_time.after(repayPlanTime)) {
				br.setPenaltyDay(String.valueOf(0));
				br.setPenaltyAmout(Double.valueOf(0));
				paramMap.put("penaltyDay","0");
				paramMap.put("penaltyAmout", 0.00);
			}
		}
	    i=borrowRepayMapper.updateParam(paramMap);
		if(i>0){
			// 生成还款记录
			BorrowRepayLog log = new BorrowRepayLog();
			log.setBorrowId(br.getBorrowId());
			log.setRepayId(br.getId());
			log.setUserId(br.getUserId());
			log.setAmount(Double.valueOf((String) param.get("amount")));// 实际还款金额
			log.setRepayTime(repayTime);// 实际还款时间
			log.setPenaltyDay(br.getPenaltyDay());
           // 实际还款时间在应还款时间之前或当天（不对比时分秒），重置逾期金额和天数
		    if (!repay_time.after(repayPlanTime)) {
				log.setPenaltyAmout(0.00);
				log.setPenaltyDay("0");
			} else {
				// 金额减免时 罚金可页面填写
				String penaltyAmout = StringUtil.isNull(param.get("penaltyAmout"));
				if (StringUtil.isNotBlank(penaltyAmout)) {
					log.setPenaltyAmout(NumberUtil.getDouble(penaltyAmout));
				} else {
					log.setPenaltyAmout(br.getPenaltyAmout());
				}
			}

			log.setSerialNumber((String) param.get("serialNumber"));
			log.setRepayAccount((String) param.get("repayAccount"));
			log.setRepayWay((String) param.get("repayWay"));
			log.setCreateTime(DateUtil.getNow());
			return borrowRepayLogMapper.save(log);
		}
		return i;
	}

	/**
	 * 更新还款计划和还款记录表
	 *
	 * @param lr
	 * @param repayTime
	 * @param param
	 * @return
	 */
	public int updateBorrowReplayForKaney(LoanRecord lr, Date repayTime,
			Map<String, Object> param) {
		// 更新还款计划状态
		int i = 0;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", lr.getId());
		paramMap.put("state", BorrowRepayModel.STATE_REPAY_YES);

		Date repayPlanTime = DateUtil.valueOf(DateUtil.dateStr2(lr.getShouldbackTime()));
		Date repay_time = DateUtil.valueOf(DateUtil.dateStr2(repayTime));
		long day = tool.util.DateUtil.daysBetween(new Date(), lr.getShouldbackTime());

		i=loanRecordMapper.updateParam(param);
		if(i>0){
			// 生成还款记录
			BorrowRepayLog log = new BorrowRepayLog();
			log.setBorrowId(lr.getId());
			log.setRepayId(lr.getId());
			log.setUserId(lr.getUid());
			log.setAmount(Double.valueOf((String) param.get("actualbackAmt")));// 实际还款金额
			log.setRepayTime(repayTime);// 实际还款时间
			if(day < 0) {
				log.setPenaltyDay(String.valueOf(Math.abs(day)));
			}else {
				log.setPenaltyDay("0");
			}
           // 实际还款时间在应还款时间之前或当天（不对比时分秒），重置逾期金额和天数
		    if (!repay_time.after(repayPlanTime)) {
				log.setPenaltyAmout(0.00);
				log.setPenaltyDay("0");
			} else {
				// 金额减免时 罚金可页面填写
				String penaltyAmout = StringUtil.isNull(param.get("penaltyAmout"));
				if (StringUtil.isNotBlank(penaltyAmout)) {
					log.setPenaltyAmout(NumberUtil.getDouble(penaltyAmout));
				} else {
					log.setPenaltyAmout(lr.getOverdueFee().doubleValue());
				}
			}

			log.setSerialNumber((String) param.get("serialNumber"));
			log.setRepayAccount((String) param.get("repayAccount"));
			log.setRepayWay((String) param.get("repayWay"));
			log.setCreateTime(DateUtil.getNow());
			return borrowRepayLogMapper.save(log);
		}
		return i;
	}

	@Override
	public List<BorrowRepay> listSelective(Map<String, Object> paramMap) {
		return borrowRepayMapper.listSelective(paramMap);
	}


	@Override
	public int updateLate(LoanRecord data) {
		return loanRecordMapper.updateLate(data);
	}
	@Override
	public int updateSelective(Map<String , Object> paramMap) {
		return borrowRepayMapper.updateSelective(paramMap);
	}
	

	@Override
	public Page<ManageBorrowModel> listRepayModel(Map<String, Object> params,
                                                  int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<ManageBorrowModel> list = borrowRepayMapper.listRepayModel(params);
		return (Page<ManageBorrowModel>) list;
	}

	@Override
	public Page<ManageBorrowModel> listModelNotUrge(Map<String, Object> params,
			int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<ManageBorrowModel> list = borrowRepayMapper
				.listModelNotUrge(params);
		return (Page<ManageBorrowModel>) list;
	}

	@Override
	public List<BorrowRepay> findUnRepay(Map<String, Object> paramMap) {
		return borrowRepayMapper.findUnRepay(paramMap);
	}

	@Override
	public List<BorrowRepay> findUnRepayIntraday(Map<String, Object> paramMap) {
		return borrowRepayMapper.findUnRepayIntraday(paramMap);
	}

	@Override
	public BorrowRepay findSelective(Map<String, Object> paramMap) {
		return borrowRepayMapper.findSelective(paramMap);
	}

	@Override
	public List<ManageBRepayModel> listAllModel(Map<String, Object> params) {
		List<ManageBRepayModel> list = borrowRepayMapper.listModel(params);
		return list;
	}

	@Override
	public List<List<String>> fileBatchRepay(MultipartFile repayFile, String type) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("state", 20);
		List<ManageBRepayModel> list = borrowRepayMapper.listModel(params);
		List<List<String>>  result=new ArrayList<List<String>>();
		String ext = repayFile.getOriginalFilename().substring(repayFile.getOriginalFilename().lastIndexOf("."));
		if(".xlsx".equals(ext)||".xls".equals(ext)){
			String title = "批量还款匹配结果";
			if(type.equals("alpay")){
				result=parserByFile(repayFile,list);
			}else if("bank".equals(type)){
				result=toPaseBank(repayFile,list);
				RepayExcelModel.createWorkBook(result,title);
			}else{
				throw new BussinessException("请上传格式正确的文档。");
			}
		}else{
			throw new BussinessException("支持.xls和.xlsx格式文档，请上传格式正确的文档。");
		}
		return result;
	}
	/**
	 * 解析支付宝财务明显账单,与备注信息有匹配信息确认还款
	 * @param repayFile
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public  List<List<String>>  parserByFile(MultipartFile repayFile,List<ManageBRepayModel> list) throws Exception  {
		 //csv文档格式解析
         //CsvParser parser=new CsvParser("");
         //arr=parser.getParsedArrayList(repayFile);
		 //xls文档格式解析
		 ReadExcelUtils excelReader = new ReadExcelUtils(repayFile);
		 List<List<String>> arr = excelReader.readExcelContent();
		 //logger.info("获得支付宝账单表格的内容:"+JSONObject.toJSONString(arr));
        int j=0;
        List<AlipayModel> alipayList=new ArrayList<AlipayModel>();
        AlipayModel model = null;
		for (int i = 0; i < arr.size(); i++) {
			model = new AlipayModel();
			List<String> ls = arr.get(i);
			for (int k = 0; k < ls.size(); k++) {
				String item = ls.get(k);
				if ("".equals(item)) {
					continue;
				}
				if (item.contains("账务流水号")) {
					j = i;
					arr.get(i).add(ls.size(), "是否有备注信息与还款计划匹配");
				}
			}
			if (j != 0 && j + 1 < ls.size() && j + 1 <= i) {
				model.setSerialNumber(ls.get(1));
				model.setAccount(ls.get(5));
				model.setAmount(ls.get(6));
				model.setRemark(ls.get(11));
				model.setRepayTime(ls.get(4) != null ? DateUtil.valueOf(ls.get(4),"yyyy/MM/dd HH:mm") : null);
				if (model != null && model.getAccount() != null) {
					alipayList.add(model);
				}
				boolean flag = false;
				flag = remarkPay(flag, model, list);
				arr.get(i).add(ls.size(), flag ? "有" : "无");
			}
		}
		if(alipayList.size()<=0){
			throw new BussinessException("没有解析到匹配的数据，请上传正确的文档");	
		}
       // logger.info("=获得支付宝账单表格的内容==="+JSONObject.toJSONString(arr));
       return arr;
    }  
 
	public  List<List<String>> toPaseBank(MultipartFile file,List<ManageBRepayModel> list) throws Exception{
		ReadExcelUtils excelReader = new ReadExcelUtils(file);
		List<List<String>> arr = excelReader.readExcelContent();
		//logger.info("获得银行卡帐单内容:" + JSONObject.toJSONString(arr));
		List<AlipayModel> alipayList = new ArrayList<AlipayModel>();
		AlipayModel model = null;
		int j = 0;
		for (int i = 0; i < arr.size(); i++) {
			model = new AlipayModel();
			List<String> ls = arr.get(i);
			for (int k = 0; k < ls.size(); k++) {
				String item = ls.get(k);
				if ("".equals(item)) {
					continue;
				}
				if (item.contains("交易日")) {
					j = i;
					arr.get(i).add(ls.size(), "是否有备注信息与还款计划匹配");
				}
			}
			if (j != 0 && j + 1 < ls.size() && j + 1 <= i) {
				String repayTime = ls.get(0) + ls.get(1);
				model.setRepayTime(repayTime != "" ? DateUtil.valueOf(repayTime, DateUtil.DATEFORMAT_STR_011) : null);
				model.setSerialNumber(ls.get(8));
				model.setAccount(ls.get(17));
				model.setAmount(ls.get(6));
				model.setRemark(ls.get(7));
				if (model != null && model.getAccount() != null) {
					alipayList.add(model);
				}
				boolean flag = false;
				flag = remarkPay(flag, model, list);
				arr.get(i).add(ls.size(), flag ? "有" : "无");
			}
		}
		if(alipayList.size()<=0){
			throw new BussinessException("没有解析到匹配的数据，请上传正确的文档");	
		}
		// logger.info("==对比之后的值==:"+JSONObject.toJSONString(arr));
		return arr;
	   }
	/**
	 * 对账文档解析结果与还款计划对比
	 * @param flag
	 * @param model
	 * @param list
	 * @return
	 */
	public boolean remarkPay(boolean flag,AlipayModel model,List<ManageBRepayModel> list){
		for (ManageBRepayModel repay : list) {
			// 账单中存在未还款的用户信息   只匹配手机号码
			if (model.getRemark().contains(repay.getPhone())) {
				flag=true;
                logger.info("批量还款匹配到的还款数据==" + repay.getId() + "="+ model.getRemark() + "=" + repay.getPhone()+ "=" + repay.getRealName());
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("id", repay.getId());
				param.put("repayTime",model.getRepayTime());
				param.put("repayWay",BorrowRepayLogModel.REPAY_WAY_ALIPAY_TRANSFER);
				param.put("repayAccount", model.getAccount());
				param.put("penaltyAmout", 0);
				if (Double.valueOf(model.getAmount()) < repay.getRepayAmount()) {
					param.put("state", "20");
				} else {
					param.put("state", "10");
				}
				param.put("serialNumber", model.getSerialNumber());
				param.put("amount", model.getAmount());
				confirmRepay(param);
				break;
			}
		}
		return flag;
	}
	/**
	 * 参数封装
	 * 
	 * @param type
	 * @param amount
	 * @param ip
	 * @param body
	 * @param orderNo
	 * @return
	 */
	@Override
	public Map<String, String> paySdkParams(Long userId, String type, double amount, String ip, String body,
			String orderNo) {

		if (PayLogModel.TYPE_ACTIVE_DEBIT_WXPAY.equals(type)) { // 微信支付
			try {
				Map<String, String> respData = WeixinPayHelper.appPay(amount, ip, body, orderNo);
				respData.put("payType", type);
				respData.put("payOrderNo", orderNo);
				logger.info("微信APP支付参数：" + respData);
				return respData;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				return null;
			}
		}

		if (PayLogModel.TYPE_ACTIVE_DEBIT_ALIPAY.equals(type)) { // 支付宝支付

			String resp = AlipayHelper.appPay(amount, body, orderNo);
			if (StringUtil.isBlank(resp)) {
				return null;
			}
			Map<String, String> params = new HashMap<String, String>();
			params.put("payType", type);
			params.put("sdkParams", resp);
			params.put("payOrderNo", orderNo);
			return params;
		}

		if (PayLogModel.TYPE_ACTIVE_DEBIT_LIANLIAN.equals(type)) { // 连连认证支付
			// 查询所需参数
			User user = userMapper.findByPrimary(userId);
			UserBaseInfo userBaseInfo = userBaseInfoMapper.findByUserId(userId);
			BankCard bankCard = bankCardMapper.findByUserId(userId);

			if (null == bankCard || StringUtil.isBlank(bankCard.getAgreeNo())) {
				throw new SimpleMessageException(StringUtil.isNull(Constant.FAIL_CODE_VALUE), "银行卡未绑定,请先绑定银行卡");
			}

			// 连连认证支付所需参数加入Map集合
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("user", user);
			paramMap.put("userBaseInfo", userBaseInfo);
			paramMap.put("orderNo", orderNo);
			paramMap.put("agreeNo", bankCard.getAgreeNo());
			paramMap.put("amount", amount);
			paramMap.put("notifyUrl", Global.getValue("server_host") + "/pay/lianlian/certifiedNotify.htm");
			String sdkParams = LianLianHelper.certifiedPay(paramMap);

			Map<String, String> params = new HashMap<String, String>();
			params.put("payType", type);
			params.put("payOrderNo", orderNo);
			params.put("sdkParams", sdkParams);
			return params;
		}

		return null;
	}

	@Override
	public void repaymentNotify(PayLog payLog, String logState, String amount, String repayWay, String repayAccount) {

		Map<String, Object> repayMap = new HashMap<String, Object>();
		repayMap.put("userId", payLog.getUserId());
		repayMap.put("borrowId", payLog.getBorrowId());
		BorrowRepay borrowRepay = borrowRepayMapper.findSelective(repayMap);
		// 若已完成还款，那么直接返回success
		if (borrowRepay == null || BorrowRepayModel.STATE_REPAY_YES.equals(borrowRepay.getState())) {
			logger.warn("还款计划有误");
			return;
		}

		Long borrowId = borrowRepay.getBorrowId();

		
		// 判断交易状态，若为成功 调用确认还款； 若为失败， 修改还款状态
		if (PayLogModel.STATE_PAYMENT_SUCCESS.equals(logState)) {
			// 实际罚金
			double penaltyAmout = borrowRepay.getPenaltyAmout();
			
			logger.info("还款支付成功，订单还款及生成续借记录...");
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("id", borrowRepay.getId());
			param.put("repayTime", DateUtil.getNow());
			param.put("repayWay", repayWay);
			param.put("repayAccount", "");
			param.put("amount", amount);
			param.put("serialNumber", payLog.getOrderNo());
			param.put("penaltyAmout",penaltyAmout);
			param.put("state", BorrowRepayModel.NORMAL_REPAYMENT);

			confirmRepay(param);
		} else if (PayLogModel.STATE_PAYMENT_FAILED.equals(logState)) {
			logger.info("还款支付失败，还原借款状态");
			String state = "";
			// 如果还款时间在当前时间前面，说明已经逾期
			if (borrowRepay.getRepayTime().before(DateUtil.getNow())) {
				state = BorrowModel.STATE_DELAY;
			} else {
				state = BorrowModel.STATE_REPAY;
			}
			// 修改标的状态为 借款中/逾期
			clBorrowMapper.updateState(state, borrowId);
			
			// 修改续借状态
 			Borrow originalBorrow  = clBorrowMapper.findByPrimary(borrowId);
			if(originalBorrow != null && originalBorrow.getRenewMark() != null && originalBorrow.getRenewMark() > 0){
				Map<String, Object> renewStateMap = new HashMap<>();
				renewStateMap.put("id", originalBorrow.getOriginalId());
				renewStateMap.put("renewState", state);
				clBorrowMapper.updateSelective(renewStateMap);
			}
		}
	}

	@Override
	public Map<String, String> repayment(String type, Long borrowId,Long userId, String ip) {
		// 借款状态判断，如果不是贷款中或者逾期，抛出异常
		Borrow borrow = clBorrowMapper.findByPrimary(borrowId);
		if (borrow == null) {
			throw new SimpleMessageException("借款信息有误");
		}
		
		String state = borrow.getState();
		if (!BorrowModel.STATE_REPAY.equals(state) && !BorrowModel.STATE_RENEW.equals(state)
				 && !BorrowModel.STATE_DELAY.equals(state) && !BorrowModel.STATE_BAD.equals(state)) {
			throw new SimpleMessageException("借款状态有误");
		}
		
		
		// 根据借款标识(还款标识) 查询应还款金额
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("borrowId", borrowId);
		paramMap.put("userId", userId);
		BorrowRepay borrowRepay = findSelective(paramMap);
		
		// 还款金额
		double principal = borrowRepay.getAmount();
		double penaltyAmout = borrowRepay.getPenaltyAmout();
		double amount = BigDecimalUtil.add(principal ,penaltyAmout); 

		// 根据类型 走不同支付渠道，获取SDK所需参数
		String orderNo = OrderNoUtil.getSerialNumber();
		// APP应用 + 小额贷款
		String body = "小额宝";
		Map<String, String> sdkParam = paySdkParams(userId, type, amount, ip, body, orderNo);

		// 若参数封装失败，直接返回失败，业务不再执行
		if (sdkParam == null || sdkParam.size() < 1) {
			throw new SimpleMessageException("支付失败");
		}

		// 若参数封装成功， 标的状态修改为赎回处理中（还款处理中）
		// updateBorrow(borrowId, userId, BorrowModel.STATE_REPAY_PROCESSING);

		// 插入一条待支付的支付记录
		String cardNo = "";
		String bank = "";
		if (PayLogModel.TYPE_ACTIVE_DEBIT_LIANLIAN.equals(type)) {
			BankCard bankCard = bankCardMapper.findByUserId(userId);
			cardNo = bankCard.getCardNo();
			bank = bankCard.getBank();
		}

		String scenes = PayLogModel.SCENES_ACTIVE_REPAYMENT;
		if (borrow.getRenewMark() != null && borrow.getRenewMark() > 0) {
			scenes = PayLogModel.SCENES_RENEW_ACTIVE_REPAYMENT;
		}

		savePayLog(orderNo, userId, borrowId, amount, cardNo, bank, type, scenes);

		return sdkParam;
	}

	@Override
	public void repaymentReturn(String payType, String payResult, String payOrderNo) {
		// 根据订单号查询支付记录
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderNo", payOrderNo);
		PayLog payLog = payLogMapper.findSelective(paramMap);

		
		if (!PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())) {
			logger.info("已处理");
			return ;
		}
		// 失败时更改支付记录状态， 成功更新借款状态， 支付状态等待异步再修改
		String state = getPayLogState(payType, payResult);
		if (PayLogModel.STATE_PAYMENT_FAILED.equals(state)) {
			payLog.setState(state);
			payLogMapper.update(payLog);
		} else {
			// 根据支付状态 决定 是否修改借款状态为赎回处理中
			modifyBorrowState(payLog.getBorrowId(), payLog.getUserId(), BorrowModel.STATE_REPAY_PROCESSING);
			// 修改续借状态
 			Borrow borrow  = clBorrowMapper.findByPrimary(payLog.getBorrowId());
			if(borrow != null && borrow.getRenewMark() != null && borrow.getRenewMark() > 0){
				// 修改原订单状态 				
				Map<String, Object> renewStateMap = new HashMap<>();
				renewStateMap.put("id", borrow.getOriginalId());
				renewStateMap.put("renewState", state);
				clBorrowMapper.updateSelective(renewStateMap);
			}
		}
	}

	/**
	 * 保存支付记录
	 * 
	 * @param orderNo
	 * @param userId
	 * @param borrowId
	 * @param amount
	 * @param cardNo
	 * @param bank
	 * @param type
	 * @param scenes
	 * @return
	 */
	private int savePayLog(String orderNo, Long userId, Long borrowId, double amount, String cardNo, String bank,
			String type, String scenes) {
		PayLog payLog = new PayLog();
		payLog.setOrderNo(orderNo);
		payLog.setUserId(userId);
		payLog.setBorrowId(borrowId);
		payLog.setAmount(amount);
		payLog.setCardNo(cardNo);
		payLog.setBank(bank);
		payLog.setSource(PayLogModel.SOURCE_FUNDS_OWN);
		payLog.setType(type);
		payLog.setScenes(scenes);
		payLog.setState(PayLogModel.STATE_PAYMENT_WAIT);// 待支付
		payLog.setPayReqTime(DateUtil.getNow());
		payLog.setCreateTime(DateUtil.getNow());
		int result = payLogMapper.save(payLog);
		return result;
	}
	
	/**
	 * 获取支付记录状态
	 * 
	 * @param payType
	 * @param payResult
	 * @return
	 */
	private String getPayLogState(String payType, String payResult) {
		String state = PayLogModel.STATE_PAYMENT_WAIT;
		if (PayLogModel.TYPE_ACTIVE_DEBIT_WXPAY.equals(payType)) {
			if ("0".equals(payResult)) {
				state = PayLogModel.STATE_PAYMENT_SUCCESS;
			} else {
				state = PayLogModel.STATE_PAYMENT_FAILED;
			}
			return state;
		}

		if (PayLogModel.TYPE_ACTIVE_DEBIT_ALIPAY.equals(payType)) {
			// 9000 支付成功 ； 8000 处理中；6004 未知
			if ("9000".equals(payResult) || "8000".equals(payResult) || "6004".equals(payResult)) {
				state = PayLogModel.STATE_PAYMENT_SUCCESS;
			} else {
				state = PayLogModel.STATE_PAYMENT_FAILED;
			}
			return state;
		}

		if (PayLogModel.TYPE_ACTIVE_DEBIT_LIANLIAN.equals(payType)) {

			if (LianLianConstant.RESULT_SUCCESS.equals(payResult)
					|| LianLianConstant.RESULT_PROCESSING.equals(payResult)) {
				state = PayLogModel.STATE_PAYMENT_SUCCESS;
			} else {
				state = PayLogModel.STATE_PAYMENT_FAILED;
			}
			return state;
		}

		return state;
	}
	/**
	 * 还款同步回调使用，更新标的之前会判断原标的状态是否为还清，如果已经还清，则不再更改状态
	 * @param borrowId
	 * @param userId
	 * @param state
	 * @return
	 */
	private int modifyBorrowState(long borrowId, long userId, String state) {
		
		// 允许还款状态集合
		List<String> stateList = Arrays.asList(BorrowModel.STATE_REPAY,BorrowModel.STATE_RENEW,BorrowModel.STATE_DELAY,BorrowModel.STATE_BAD);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", borrowId);
		params.put("state", state);
		params.put("stateList", stateList);
		int count = clBorrowMapper.syncUpdateState(params);
		
		if (count > 0) { // 借款状态更新成功、添加借款进度
			BorrowProgress bp = new BorrowProgress(userId, borrowId, state, BorrowModel.convertBorrowRemark(state));
			bp.setCreateTime(DateUtil.getNow());
			count = borrowProgressMapper.save(bp);
		}
		return count;
	}

	/**
	 *
	 */
	private long getPercent(long cycle,Long balance){
		BigDecimal amount = new BigDecimal(balance);
		BigDecimal percent = new BigDecimal(0);
		if(cycle == 7){
			percent = new BigDecimal(0.105);
		}else if (cycle == 14){
			percent = new BigDecimal(0.21);
		}
		amount = amount.multiply(percent);
		return amount.longValue();
	}
	
 
}