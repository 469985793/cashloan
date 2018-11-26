package com.xindaibao.cashloan.cl.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.common.collect.Maps;
import com.xindaibao.cashloan.cl.Util.FlowNumber;
import com.xindaibao.cashloan.cl.Util.HttpClientUtil;
import com.xindaibao.cashloan.cl.mapper.*;
import com.xindaibao.cashloan.cl.model.kenya.*;
import com.xindaibao.cashloan.cl.model.pay.lianlian.PaymentModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.constant.LianLianConstant;
import com.xindaibao.cashloan.cl.model.pay.lianlian.util.LianLianHelper;
import com.xindaibao.cashloan.cl.monitor.BusinessExceptionMonitor;
import com.xindaibao.cashloan.cl.service.*;
import com.xindaibao.cashloan.core.model.KanyaUser;
import org.apache.commons.collections.CollectionUtils;
import com.xindaibao.cashloan.system.domain.SysConfig;
import org.jfree.base.config.SystemPropertyConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tool.util.BigDecimalUtil;
import tool.util.NumberUtil;
import tool.util.RandomUtil;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.cl.domain.BankCard;
import com.xindaibao.cashloan.cl.domain.BorrowProgress;
import com.xindaibao.cashloan.cl.domain.BorrowRepay;
import com.xindaibao.cashloan.cl.domain.BorrowRepayLog;
import com.xindaibao.cashloan.cl.domain.PayLog;
import com.xindaibao.cashloan.cl.domain.QianchengReqlog;
import com.xindaibao.cashloan.cl.domain.UrgeRepayOrder;
import com.xindaibao.cashloan.cl.model.BorrowProgressModel;
import com.xindaibao.cashloan.cl.model.BorrowRepayModel;
import com.xindaibao.cashloan.cl.model.ClBorrowModel;
import com.xindaibao.cashloan.cl.model.IndexModel;
import com.xindaibao.cashloan.cl.model.ManageBorrowExportModel;
import com.xindaibao.cashloan.cl.model.ManageBorrowModel;
import com.xindaibao.cashloan.cl.model.ManageBorrowTestModel;
import com.xindaibao.cashloan.cl.model.PayLogModel;
import com.xindaibao.cashloan.cl.model.RepayModel;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.exception.BussinessException;
import com.xindaibao.cashloan.core.common.exception.SimpleMessageException;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.common.util.DateUtil;
import com.xindaibao.cashloan.core.common.util.NidGenerator;
import com.xindaibao.cashloan.core.common.util.OrderNoUtil;
import com.xindaibao.cashloan.core.common.util.ShardTableUtil;
import com.xindaibao.cashloan.core.common.util.StringUtil;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.core.domain.User;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.mapper.UserBaseInfoMapper;
import com.xindaibao.cashloan.core.mapper.UserMapper;
import com.xindaibao.cashloan.core.model.BorrowModel;
import com.xindaibao.cashloan.core.model.UserBaseInfoModel;
import com.xindaibao.cashloan.core.service.UserBaseInfoService;
import com.xindaibao.cashloan.rc.domain.SceneBusinessLog;
import com.xindaibao.cashloan.rc.domain.TppBusiness;
import com.xindaibao.cashloan.rc.mapper.SceneBusinessLogMapper;
import com.xindaibao.cashloan.rc.mapper.SceneBusinessMapper;
import com.xindaibao.cashloan.rc.model.TppBusinessModel;
import com.xindaibao.cashloan.rc.model.TppServiceInfoModel;
import com.xindaibao.cashloan.rc.service.BorrowCountService;
import com.xindaibao.cashloan.rc.service.ContactCountService;
import com.xindaibao.cashloan.rc.service.OperatorCountService;
import com.xindaibao.cashloan.rc.service.SceneBusinessLogService;
import com.xindaibao.cashloan.rc.service.SimpleBorrowCountService;
import com.xindaibao.cashloan.rc.service.SimpleContactCountService;
import com.xindaibao.cashloan.rc.service.SimpleVoicesCountService;
import com.xindaibao.cashloan.rc.service.TppBusinessService;
import com.xindaibao.cashloan.rule.domain.BorrowRuleResult;
import com.xindaibao.cashloan.rule.domain.RuleEngineConfig;
import com.xindaibao.cashloan.rule.mapper.BorrowRuleEngineMapper;
import com.xindaibao.cashloan.rule.mapper.BorrowRuleResultMapper;
import com.xindaibao.cashloan.rule.mapper.RuleEngineConfigMapper;
import com.xindaibao.cashloan.rule.mapper.RuleEngineMapper;
import com.xindaibao.cashloan.rule.model.ManageReviewModel;
import com.xindaibao.cashloan.rule.model.ManageRuleResultModel;
import com.xindaibao.cashloan.rule.model.srule.client.RulesExecutorUtil;
import com.xindaibao.cashloan.rule.model.srule.model.SimpleRule;
import com.xindaibao.cashloan.rule.model.srule.utils.GenerateRule;
import com.xindaibao.cashloan.system.service.SysConfigService;
import com.xindaibao.creditrank.cr.domain.Credit;
import com.xindaibao.creditrank.cr.mapper.CreditMapper;
import com.xindaibao.cashloan.core.mapper.KanyaUserMapper;

import static com.xindaibao.cashloan.cl.Util.HttpClientUtil.CONTENT_TYPE_JSON_URL;

/**
 * 借款信息表ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-14 10:36:53 



 */
@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
@Service("clBorrowService")
@Transactional(rollbackFor = Exception.class)
public class ClBorrowServiceImpl extends BaseServiceImpl<Borrow, Long> implements ClBorrowService {

	private static final Logger logger = LoggerFactory.getLogger(ClBorrowServiceImpl.class);

	@Resource
	private KanyaUserMapper kanyaUserMapper;
	@Resource
	private ClBorrowMapper clBorrowMapper;
	@Resource
	private BorrowProgressMapper borrowProgressMapper;
	@Resource
	private BorrowRepayMapper borrowRepayMapper;
	@Resource
	private CreditMapper creditMapper;
	@Resource
	private BorrowRepayLogMapper borrowRepayLogMapper;
	@Resource
	private BankCardMapper bankCardMapper;
	@Resource
	private UserInviteMapper userInviteMapper;
	@Resource
	private ProfitAgentMapper profitAgentMapper;
	@Resource
	private KanyaPayFlowMapper kanyaPayFlowMapper;
	@Resource
	private KanyaPayRecordMapper kanyaPayRecordMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private KanyaUserStateMapper kanyaUserStateMapper;
	@Resource
	private UserAuthService userAuthService;
	@Resource
	private BorrowRuleEngineMapper borrowRuleEngineMapper;
	@Resource
	private RuleEngineConfigMapper ruleEngineConfigMapper;
	@Resource
	private BorrowRuleResultMapper borrowRuleResultMapper;
	@Resource
	private RcQianchenService rcQianchenService;
	@Resource
	private QianchengReqlogMapper qianchengReqlogMapper;
	@Resource
	private UserBaseInfoMapper userBaseInfoMapper;
	@Resource
	private PayLogMapper payLogMapper;
	@Resource
	private OperatorReqLogMapper operatorReqLogMapper;
	@Resource
	private UrgeRepayOrderMapper urgeRepayOrderMapper;
	@Resource
	private RuleEngineMapper ruleEngineMapper;
	@Resource
	private ClSmsService clSmsService;
	@Resource
	private TongdunReqLogService tongdunReqLogService;
	@Resource
	private SceneBusinessLogMapper sceneBusinessLogMapper;
	@Resource
	private SceneBusinessLogService sceneBusinessLogService;
	@Resource
	private SceneBusinessMapper sceneBusinessMapper;
	@Resource
	private ZhimaService zhimaService;
	@Resource
	private OperatorCountService operatorCountService;
	@Resource
	private ContactCountService contactCountService;
	@Resource
	private BorrowCountService borrowCountService;
	@Resource
	private SimpleBorrowCountService simpleBorrowCountService;
	@Resource
	private SimpleVoicesCountService simpleVoicesCountService;
	@Resource
	private SimpleContactCountService simpleContactCountService;
	@Resource
	private UserBaseInfoService userBaseInfoService;
	@Resource
	private TppBusinessService tppBusinessService;
	@Resource
	private RcHuadaoBlacklistLogService rcHuadaoBlacklistLogService;
	@Resource
	private DhbReqLogService dhbReqLogService;
	@Resource
	private BorrowRepayService borrowRepayService;
	@Resource
	private SysConfigService sysConfigService;
	@Resource
	private RcR360BlacklistLogService rcR360BlacklistLogService;
	@Resource
	private RcJiGuangBlacklistLogService rcJiGuangBlacklistLogService;
	@Resource
	private RcJiGuangLbsCheckLogService rcJiGuangLbsCheckLogService;
	@Resource
	private RcJiGuangLbsFuzzyCheckLogService rcJiGuangLbsFuzzyCheckLogService;
	@Resource
	private ClMoheReportInfoService clMoheReportInfoService;
	@Resource
	private RcR360GradeService rcR360GradeService;
	@Resource
	private BaiRongGradeService baiRongGradeService;
	@Resource
	private ClShuMeiBlacklistService clShuMeiBlacklistService;
	@Resource
	private LoanRecordMapper loanRecordMapper;

	public BaseMapper<Borrow, Long> getMapper() {
		return clBorrowMapper;
	}
    
	@Override
	public boolean isCanBorrow(Borrow borrow,String tradePwd) {
    	
    	Long userId = borrow.getUserId();
    	User user = userMapper.findByPrimary(userId);
    	//1、校验用户是否符合借款条件
    	//1.1 校验是否有对应的用户信息
    	if(user==null || user.getId()<1){
    		throw new SimpleMessageException("找不到对应的用户信息");
    	}
    	//1.2 校验交易密码是否正确
    	if(StringUtil.isBlank(user.getTradePwd())){
	    	throw new SimpleMessageException("请先设置交易密码!");
	    }
    	if(!user.getTradePwd().equals(tradePwd)){
	    	throw new SimpleMessageException(String.valueOf(Constant.FAIL_CODE_PWD),"交易密码不正确!");
	    }
    	//1.3 校验中户是否在黑名单
    	UserBaseInfo userBaseInfo = userBaseInfoMapper.findByUserId(userId);
	    if(userBaseInfo != null && UserBaseInfoModel.USER_STATE_BLACK.equals(userBaseInfo.getState())){
	    	throw new SimpleMessageException("该账号无法借款,请联系客服人员。");
	    }
	    
		//1.4 校验用户是否通过各项认证
		Map<String, Object> authMap = new HashMap<String,Object>();
		authMap.put("userId", userId);
		Map<String,Object> auth = userAuthService.getAuthState(authMap);
	    if (StringUtil.isBlank(auth)||Integer.parseInt(auth.get("qualified").toString())==0) {
	    	throw new SimpleMessageException("资料未完善，无法借款");
		}
	    
	    //1.5 用户是否有未结束的借款
	    List<Borrow> list = findUserUnFinishedBorrow(userId);
		if (list.size() > 0) {
	    	throw new SimpleMessageException("有未完成借款，无法借款");
		}
	    
	    //1.7 借款天数限制
	    int day = 0;
		String againBorrow = Global.getValue("again_borrow");
		Map<String,Object> searchMap = new HashMap<>();
		searchMap.put("userId", userId);
		Borrow borrowTemp = findLast(searchMap);
		if (StringUtil.isNotBlank(borrowTemp)) {
			day = DateUtil.daysBetween(borrowTemp.getCreateTime(),new Date());
			day = Integer.parseInt(againBorrow)-day;
			if (day>0) {
				throw new SimpleMessageException("申请失败,您在"+day+"天后才能再次借款");
			}
		}
		
		//1.8 运营商数据校验
		String orderNo = operatorReqLogMapper.findOrderByUserId(userId);
		if(StringUtil.isBlank(orderNo)){
			throw new SimpleMessageException("运营商认证数据不正确");
		}
	    
	    //1.9 借款金额格式是否正确
		String borrowDay = Global.getValue("borrow_day");// 借款天数
		String[] days = borrowDay.split(",");
		int maxDays = Integer.parseInt(days[days.length-1]);// 最大借款期限
		int minDays = Integer.parseInt(days[0]);			// 最大借款期限
		String borrowCredit = Global.getValue("borrow_credit");// 借款额度
		String[] credits = borrowCredit.split(",");
		double maxCredit = Double.parseDouble(credits[credits.length-1]);// 最大借款额度
		double minCredit = Double.parseDouble(credits[0]);		
	    Double amount = borrow.getAmount();
	    if (Math.abs(borrow.getAmount())%10!=0 || amount<minCredit||amount>maxCredit) {
			throw new SimpleMessageException("借款金额格式不正确");
		}
	    
	    //1.10 信用额度是否足够
	    Map<String,Object> creditMap = new HashMap<>();
		creditMap.put("consumerNo", borrow.getUserId());
	    Credit credit = creditMapper.findSelective(creditMap);
	    if(!"10".equals(credit.getState())){
	    	throw new SimpleMessageException("当前额度不可用，请拨打客服电话联系我们！");
	    }
	    if (StringUtil.isBlank(credit) || credit.getUnuse() < borrow.getAmount()) {
	    	throw new SimpleMessageException("额度不足，无法借款");
		}
	    
	    // 1.11 判断放款上限 
	    double borrowTotal = clBorrowMapper.borrowAmountSum();
//	    double repayTotal = borrowRepayMapper.findRepayTotal();
        // FIXME: 12/11/2017 get the loanCeiling from DB.
	    double loanCeiling = Global.getDouble("loan_ceiling");
        SysConfig loanCeilingSysConfig = sysConfigService.findByCode("loan_ceiling");
        if (loanCeilingSysConfig == null || loanCeilingSysConfig.getValue() == null) {
            logger.error(" 今日借款上限  get loan_ceiling from table error ");
        } else {
            loanCeiling = Double.parseDouble(loanCeilingSysConfig.getValue());
            logger.info(" 今日借款上限 值: " + loanCeiling);
        }

        if( borrowTotal> 0  && loanCeiling> 0 && borrowTotal >= loanCeiling ){
	    	throw new SimpleMessageException("今日借款已达上限，请明天再来！");
	    }
		return true;
	}
    
	@Override
	public List<RepayModel> findRepay(Map<String, Object> searchMap) {
		List<RepayModel> modelList = clBorrowMapper.findRepay(searchMap);
		for (RepayModel repayModel : modelList) {
			if (StringUtil.isNotBlank(repayModel)) {
				int day = DateUtil.daysBetween(new Date(),
						repayModel.getRepayTime());
				if (day > 0) {
					repayModel.setIsPunish("false");
					repayModel.setMsg(day + "天后还款");
				} else if (day == 0) {
					repayModel.setIsPunish("false");
					repayModel.setMsg("今日还款");
				} else {
					repayModel.setIsPunish("true");
					repayModel.setMsg("已逾期" + Math.abs(day) + "天");
				}
				repayModel.setRepayTimeStr(DateUtil.dateStr8(repayModel
						.getRepayTime()));
			}
		}
		return modelList;
	}

	@Override
	public Page<ClBorrowModel> page(Map<String, Object> searchMap, int current,
                                    int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<ClBorrowModel> list = clBorrowMapper.listAll(searchMap);
		for (ClBorrowModel clBorrowModel : list) {
			clBorrowModel.setCreditTimeStr(DateUtil.dateStr(
					clBorrowModel.getCreateTime(), "yyyy-MM-dd HH:mm"));
			clBorrowModel.setStateStr(clBorrowModel.getState());
			if ("审核通过".equals(clBorrowModel.getStateStr())||"放款失败".equals(clBorrowModel.getStateStr())) {
				clBorrowModel.setState("打款中");
				clBorrowModel.setStateStr("打款中");
			}
			if ("还款中".equals(clBorrowModel.getStateStr())) {
				clBorrowModel.setState("待还款");
				clBorrowModel.setStateStr("待还款");
			}
		}
		return (Page<ClBorrowModel>) list;
	}

	@Override
	public List<IndexModel> listIndex() {
		List<IndexModel> list = clBorrowMapper.listIndex();
		List indexList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			String cardNo = list.get(i).getCardNo();
			if (StringUtil.isBlank(cardNo)) {
				continue;
			}
			cardNo = StringUtil.substring(cardNo, cardNo.length() - 4);
			double money = list.get(i).getAmount();
			int time = 0;
			if (list.get(i) != null && list.get(i).getCreateTime() != null
					&& list.get(i).getLoanTime() != null) {
				time = DateUtil.minuteBetween(list.get(i).getCreateTime(), list
						.get(i).getLoanTime());
			}
			
			String value = "尾号" + cardNo + " " + "成功借款" + (int) (money)
					+ "元 用时" + time + "分钟";
			Map<String, Object> map = new HashMap<>();
			map.put("id", i);
			map.put("value", value);
			indexList.add(map);
		}
		return indexList;
	}

	@Override
	public Map<String, Object> findIndex(String userId) {
		String fee = Global.getValue("fee");// 综合费用
		double loanCeiling = Global.getDouble("loan_ceiling"); // 放款上限
		// double repayTotal = borrowRepayMapper.findRepayTotal();
		double repayTotal = clBorrowMapper.borrowAmountSum();// 当前放款量
		// 计算剩余放款数
		if (loanCeiling <= 0 || loanCeiling <= repayTotal) {
			loanCeiling = 0;
		} else {
			loanCeiling -= repayTotal;
		}
		String[] fees = fee.split(",");
		String borrowDay = Global.getValue("borrow_day");// 借款天数
		String[] days = borrowDay.split(",");
		int maxDays = Integer.parseInt(days[days.length - 1]);// 最大借款期限
		int minDays = Integer.parseInt(days[0]); // 最大借款期限
		String borrowCredit = Global.getValue("borrow_credit");// 借款额度
		String[] credits = borrowCredit.split(",");
		double maxCredit = Double.parseDouble(credits[credits.length - 1]);// 最大借款额度
		double minCredit = Double.parseDouble(credits[0]); // 最小借款额度
		String title = Global.getValue("title");// 标题
		int authTotal = Global.getInt("auth_total");

		Credit credit;
		Borrow borrow;
		Boolean isBorrow = false;
		long count;
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> auth = new HashMap<String, Object>();
		if (StringUtil.isNotBlank(userId) && !StringUtil.equals(userId, "0")) {
			result.put("total", Global.getValue("init_credit"));
		} else {
			String unregistered_credit = Global.getValue("unregistered_credit");
			if (unregistered_credit == null || unregistered_credit.equals("")) {
				unregistered_credit = Global.getValue("init_credit");
			}
			result.put("total", unregistered_credit);
		}
		auth.put("total", authTotal);
		auth.put("result", 0);
		auth.put("qualified", 0);
		result.put("cardNo", "");
		if (StringUtil.isNotBlank(userId) && !StringUtil.equals(userId, "0")) {
			User user = userMapper.findByPrimary(Long.parseLong(userId));
			boolean isPwd = false;
			if (StringUtil.isNotBlank(user) && StringUtil.isNotBlank(user.getTradePwd())) {
				isPwd = true;
			}
			result.put("isPwd", isPwd);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userId", Long.parseLong(userId));
			BankCard bc = bankCardMapper.findSelective(paramMap);
			if (StringUtil.isNotBlank(bc)) {
				result.put("cardId", bc.getId());
				result.put("cardNo", bc.getCardNo());
				result.put("cardName", bc.getBank());
			}
			count = clBorrowMapper.countBorrow(Long.parseLong(userId));
			Map<String, Object> borrowMap = new HashMap<String, Object>();
			borrowMap.put("userId", Long.parseLong(userId));
			borrow = clBorrowMapper.findRepayBorrow(borrowMap);
			List<BorrowProgressModel> list = new ArrayList<BorrowProgressModel>();
			if (borrow != null) {
				if (borrow.getState().equals(BorrowModel.STATE_REPAY)
						|| borrow.getState().equals(BorrowModel.STATE_DELAY)
						|| borrow.getState().equals(BorrowModel.STATE_BAD)) {
					result.put("isRepay", true);
				} else {
					result.put("isRepay", false);
				}
			}
			if (borrow != null) {
				list = borrowProgress(borrow.getId());
				result.put("list", list);
				result.put("borrowId", borrow.getId());
			}

			if (borrow != null && getBorrowDays(borrow.getUserId()) > 0) {
				isBorrow = true;
			}

			if (list != null && !list.isEmpty()) {
				isBorrow = true;
				Map<String, Object> unRepayMap = new HashMap<String, Object>();
				List<String> stateList = Arrays.asList(BorrowModel.STATE_PRE, BorrowModel.STATE_AUTO_PASS,
						BorrowModel.STATE_NEED_REVIEW, BorrowModel.STATE_PASS, BorrowModel.STATE_REPAY);
				unRepayMap.put("userId", userId);
				unRepayMap.put("stateList", stateList);
				Borrow unRepayBorrow = clBorrowMapper.findByUserIdAndState(unRepayMap);
				if (unRepayBorrow != null) {
					borrow = unRepayBorrow;
				}
			}

			result.put("borrow", borrow);

			Map<String, Object> authMap = new HashMap<String, Object>();
			authMap.put("userId", userId);
			auth = userAuthService.getAuthState(authMap);

			if (auth != null && auth.get("qualified") != null
					&& Integer.parseInt(auth.get("qualified").toString()) == 1) {

				Map<String, Object> creditMap = new HashMap<String, Object>();
				creditMap.put("consumerNo", userId);
				credit = creditMapper.findSelective(creditMap);
				result.put("total", credit.getUnuse());
				if (StringUtil.isNotBlank(credit) && "10".equals(credit.getState())) {
					result.put("total", credit.getUnuse());
					if (credit.getTotal() - credit.getUsed() < 100) {
						minCredit = credit.getTotal() - credit.getUsed();
					}
				}
				maxCredit = credit.getUnuse();
			}
			result.put("count", count);

		}
		result.put("auth", auth);
		result.put("maxCredit", maxCredit);
		result.put("minCredit", minCredit);
		List creditList = new ArrayList<>();
		List dayList = new ArrayList<>();
		List interests = new ArrayList<>();
		double total = Double.parseDouble(result.get("total").toString());// 总额度
		for (int i = 0; i < credits.length; i++) {
			double creditValue = Double.parseDouble(credits[i]);
			if (total <= creditValue) {// 比较总额度和数组中的值
				creditList.add(((int) total) + "");
				break;// 结束取值
			}
			creditList.add(credits[i]);
		}
		for (int i = 0; i < days.length; i++) {
			dayList.add(days[i]);
		}
		for (int i = 0; i < fees.length; i++) {
			interests.add(fees[i]);
		}
		result.put("creditList", creditList);
		result.put("dayList", dayList);
		result.put("interests", interests);
		result.put("maxDays", maxDays);
		result.put("minDays", minDays);
		result.put("isBorrow", isBorrow);
		result.put("title", title);
		result.put("loanCeiling", loanCeiling);
		return result;
	}
	
	//获取到可借款的天数
	public int getBorrowDays(Long userId){
		int day = 0;
		String againBorrow = Global.getValue("again_borrow");
		Map<String,Object> searchMap = new HashMap<>();
		searchMap.put("userId", userId);
		Borrow borrowTemp = findLast(searchMap);
		if (StringUtil.isNotBlank(borrowTemp)) {
			day = DateUtil.daysBetween(borrowTemp.getCreateTime(),new Date());
			day = Integer.parseInt(againBorrow)-day;
		}
		return day;
	}

	/**
	 * 借款进度显示
	 * 
	 * @param borrow
	 * @param pageFlag
	 * @return
	 */
	@Override
	public List<BorrowProgressModel> borrowProgress(long borrowId) {
		Borrow progressBorrow = clBorrowMapper.findByPrimary(borrowId);

		// 若当前借款是续借订单，则展示原订单进度
		if (progressBorrow != null && progressBorrow.getRenewMark() != null && progressBorrow.getRenewMark() > 0) {
			progressBorrow = clBorrowMapper.findByPrimary(progressBorrow.getOriginalId());
			progressBorrow.setState(progressBorrow.getRenewState());
		}

		// 已赎回订单不显示
		List<BorrowProgressModel> list = new ArrayList<BorrowProgressModel>();
		List<String> borrowNoStateList = Arrays.asList(BorrowModel.STATE_FINISH, BorrowModel.STATE_DELAY_FINISH,
				BorrowModel.STATE_DELAY_REMISSION_FINISH);
		if (progressBorrow == null || borrowNoStateList.contains(progressBorrow.getState())) {
			return list;
		}

		Double repayAmount = 0.0;
		double penaltyAmount = 0.0;
		Date repayTime = null;
		BorrowRepay borrowRepay = borrowRepayMapper.findByBorrowId(borrowId);
		if (null != borrowRepay) {
			repayAmount = BigDecimalUtil.add(borrowRepay.getAmount(), penaltyAmount);
			penaltyAmount = borrowRepay.getPenaltyAmout();
			repayTime = borrowRepay.getRepayTime();
		}

		Map<String, Object> bpMap = new HashMap<String, Object>();
		bpMap.put("borrowId", progressBorrow.getId());
		List<BorrowProgressModel> pgList = borrowProgressMapper.listProgress(bpMap);
		
		BorrowProgressModel progress1 = new BorrowProgressModel(); // 申请成功
		BorrowProgressModel progress2 = new BorrowProgressModel(); // 自动审核通过、自动审核不通过  、审核未决待人工复审、人工审核通过、人工审核不通过、待客服一审核(未分配ICloud账号)
		BorrowProgressModel progress3 = new BorrowProgressModel(); // 待客服一审核(已分配ICloud账号)、客服一审核通过、客服一审核不通过、待客服二审核、客服二审核通过、客服二审核不通过、放款中、放款失败
		BorrowProgressModel progress4 = new BorrowProgressModel(); // 放款成功、续借处理中、转续借、赎回处理中、逾期

		for (BorrowProgressModel model : pgList) {
			if (BorrowModel.STATE_PRE.equals(model.getState())) {
				progress1.setState(BorrowModel.apiConvertBorrowState(model.getState()));
				progress1.setRemark(BorrowModel.apiConvertBorrowRemark(progressBorrow, model.getState(), repayAmount,
						penaltyAmount, repayTime));
				progress1.setType(getAppType(model.getState(), progressBorrow.getState()));
				progress1.setCreateTime(model.getCreateTime());
				progress1.setCreateTimeStr(DateUtil.dateStr(model.getCreateTime(), "yyyy-MM-dd HH:mm"));
			}

			if (BorrowModel.STATE_AUTO_PASS.equals(model.getState())
					|| BorrowModel.STATE_AUTO_REFUSED.equals(model.getState())
					|| BorrowModel.STATE_NEED_REVIEW.equals(model.getState())
					|| BorrowModel.STATE_PASS.equals(model.getState())
					|| BorrowModel.STATE_REFUSED.equals(model.getState())) {
				// 已当前进度中最新一条记录为准
				if (progress2 != null && StringUtil.isNotBlank(progress2.getState())) {
					continue;
				}

				progress2.setState(BorrowModel.apiConvertBorrowState(model.getState()));
				progress2.setRemark(BorrowModel.apiConvertBorrowRemark(progressBorrow, model.getState(), repayAmount,
						penaltyAmount, repayTime));
				progress2.setType(getAppType(model.getState(), progressBorrow.getState()));
				progress2.setCreateTime(model.getCreateTime());
				progress2.setCreateTimeStr(DateUtil.dateStr(model.getCreateTime(), "yyyy-MM-dd HH:mm"));

			}

			if (BorrowModel.STATE_REPAYING.equals(model.getState())
					|| BorrowModel.STATE_REPAY_FAIL.equals(model.getState())) {
				// 已当前进度中最新一条记录为准
				if (progress3 != null && StringUtil.isNotBlank(progress3.getState())) {
					continue;
				}
				if (BorrowModel.STATE_REPAYING.equals(model.getState())
						|| BorrowModel.STATE_REPAY_FAIL.equals(model.getState())) { // 打款中
					progress3.setState(BorrowModel.apiConvertBorrowState(BorrowModel.STATE_REPAYING));
					progress3.setRemark(BorrowModel.apiConvertBorrowRemark(progressBorrow, model.getState(),
							repayAmount, penaltyAmount, repayTime));
					progress3.setType(getAppType(model.getState(), BorrowModel.STATE_REPAYING));
					progress3.setCreateTime(model.getCreateTime());
					progress3.setCreateTimeStr(DateUtil.dateStr(model.getCreateTime(), "yyyy-MM-dd HH:mm"));
				} else if (BorrowModel.STATE_REPAY.equals(progressBorrow.getState())
						|| BorrowModel.STATE_TO_RENEW.equals(progressBorrow.getState())
						|| BorrowModel.STATE_RENEW_PROCESSING.equals(progressBorrow.getState())
						|| BorrowModel.STATE_REPAY_PROCESSING.equals(progressBorrow.getState())
						|| BorrowModel.STATE_DELAY.equals(progressBorrow.getState())) { // 已打款
					progress3.setState(BorrowModel.apiConvertBorrowState(BorrowModel.STATE_REPAY));
					progress3.setRemark(BorrowModel.apiConvertBorrowRemark(progressBorrow, BorrowModel.STATE_REPAY,
							repayAmount, penaltyAmount, repayTime));
					progress3.setType(getAppType(model.getState(), progressBorrow.getState()));
					progress3.setCreateTime(model.getCreateTime());
					progress3.setCreateTimeStr(DateUtil.dateStr(model.getCreateTime(), "yyyy-MM-dd HH:mm"));
				}
			}

			if (BorrowModel.STATE_REPAY.equals(model.getState()) || BorrowModel.STATE_TO_RENEW.equals(model.getState())
					|| BorrowModel.STATE_RENEW.equals(model.getState())
					|| BorrowModel.STATE_RENEW_PROCESSING.equals(model.getState())
					|| BorrowModel.STATE_REPAY_PROCESSING.equals(model.getState())
					|| BorrowModel.STATE_DELAY.equals(model.getState())
					|| BorrowModel.STATE_BAD.equals(model.getState())) {
				  //已当前进度中最新一条记录为准
				if(progress4!=null&&StringUtil.isNotBlank(progress4.getState())){
					continue;
				 }
				// 当借款订单当前状态为 放款成功后自动追加 待还款的状态说明
				if (BorrowModel.STATE_REPAY.equals(progressBorrow.getState())
						|| BorrowModel.STATE_RENEW.equals(progressBorrow.getState())) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(model.getCreateTime());
					cal.add(Calendar.SECOND, +1);
					progress4.setState(BorrowModel.apiConvertBorrowState("repay"));
					progress4.setType("40");
					progress4.setRemark(BorrowModel.apiConvertBorrowRemark(progressBorrow, "repay", repayAmount,
							penaltyAmount, repayTime));
					progress4.setCreateTime(cal.getTime());
					progress4.setCreateTimeStr(DateUtil.dateStr(cal.getTime(), "yyyy-MM-dd HH:mm"));
				} else if (BorrowModel.STATE_RENEW_PROCESSING.equals(progressBorrow.getState())) {
					progress4.setType(getAppType(BorrowModel.STATE_RENEW_PROCESSING, progressBorrow.getState()));
					progress4.setState(BorrowModel.apiConvertBorrowState(BorrowModel.STATE_RENEW_PROCESSING));
					progress4.setRemark(BorrowModel.apiConvertBorrowRemark(progressBorrow, progressBorrow.getState(),
							repayAmount, penaltyAmount, repayTime));
					progress4.setCreateTime(model.getCreateTime());
					progress4.setCreateTimeStr(DateUtil.dateStr(model.getCreateTime(), "yyyy-MM-dd HH:mm"));
				} else if (BorrowModel.STATE_DELAY.equals(progressBorrow.getState())
						|| BorrowModel.STATE_REPAY_PROCESSING.equals(progressBorrow.getState())
						|| BorrowModel.STATE_BAD.equals(progressBorrow.getState())) {
					progress4.setType(getAppType(progressBorrow.getState(), progressBorrow.getState()));
					progress4.setState(BorrowModel.apiConvertBorrowState(progressBorrow.getState()));
					progress4.setRemark(BorrowModel.apiConvertBorrowRemark(progressBorrow, progressBorrow.getState(),
							repayAmount, penaltyAmount, repayTime));
					progress4.setCreateTime(model.getCreateTime());
					progress4.setCreateTimeStr(DateUtil.dateStr(model.getCreateTime(), "yyyy-MM-dd HH:mm"));
				}
			}
		}
		if (null != progress4 && StringUtil.isNotBlank(progress4.getState())) {
			list.add(progress4);

		}
		if (null != progress3 && StringUtil.isNotBlank(progress3.getState())) {
			if (null != progress4 && StringUtil.isNotBlank(progress4.getState())) {
				progress3.setState("已打款");
				progress3.setRemark("已打款，请查看您的收款银行卡。");
				progress3.setType("40");
				progress3.setCreateTime(progress3.getCreateTime());
				progress3.setCreateTimeStr(DateUtil.dateStr(progress3.getCreateTime(), "yyyy-MM-dd HH:mm"));
			}
			list.add(progress3);
		}

		if (null != progress2 && StringUtil.isNotBlank(progress2.getState())) {
			if ((progress3 != null && StringUtil.isNotBlank(progress3.getState()))
					&& (!BorrowModel.STATE_AUTO_PASS.equals(progressBorrow.getState())
							&& !BorrowModel.STATE_NEED_REVIEW.equals(progressBorrow.getState())
							&& !BorrowModel.STATE_PASS.equals(progressBorrow.getState()))) {
				progress2.setState("审核通过");
				progress2.setRemark("恭喜您通过风控审核");
				progress2.setType("40");
				progress2.setCreateTime(progress2.getCreateTime());
				progress2.setCreateTimeStr(DateUtil.dateStr(progress2.getCreateTime(), "yyyy-MM-dd HH:mm"));
			}
			list.add(progress2);
		}
		if (null != progress1 && StringUtil.isNotBlank(progress1.getState())) {
			list.add(progress1);
		}
		return list;
	}
	
	/**
	 * 根据借款状态判断app图标显示 10-√ 20-× 30-! 40-L
	 * 
	 * @param state
	 *            借款进度状态
	 * @param borrowState
	 *            当前借款订单状态
	 * @return
	 */
	public String getAppType(String state, String borrowState) {
		// 审核不通过
		List<String> refuseStateList = new ArrayList<String>();
		refuseStateList = Arrays.asList(BorrowModel.STATE_AUTO_REFUSED, BorrowModel.STATE_REFUSED);
		String type = "10";
		if (state.equals(borrowState)) {
			type = "40";
			if (refuseStateList.contains(state)) {
				type = "20";
			}
			if (state.equals(BorrowModel.STATE_DELAY)) {
				type = "30";
			}
			if (state.equals(BorrowModel.STATE_DELAY_FINISH) || state.equals(BorrowModel.STATE_FINISH)
					|| state.equals(BorrowModel.STATE_DELAY_REMISSION_FINISH)) {
				type = "10";
			}
		}
		return type;
	}
	

	@Override
	public Map<String, Object> choice(double amount, String timeLimit) {
		String fee_ = Global.getValue("fee");// 综合费用
		String[] fees = fee_.split(",");
		String borrowDay = Global.getValue("borrow_day");// 借款天数
		String[] days = borrowDay.split(",");
		double infoAuthFee = Double.valueOf(Global.getValue("info_auth_fee"));// 信息认证费
		double serviceFee = Double.valueOf(Global.getValue("service_fee"));// 居间服务费
		double interest = Double.valueOf(Global.getValue("interest"));// 利息
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("amount", amount);
		map.put("timeLimit", timeLimit);
		
		double fee = 0;
		for (int i = 0; i < days.length; i++) {
			if (timeLimit.equals(days[i])) {
				fee = BigDecimalUtil.round(BigDecimalUtil.mul(amount, Double.parseDouble(fees[i])));
				map.put("fee", BigDecimalUtil.decimal(fee, 2));
				
				String beheadFee = Global.getValue("behead_fee");// 是否启用砍头息
				if (beheadFee.equals("10")) {// 启用
					map.put("realAmount", amount - fee);
				} else {
					map.put("realAmount", amount);
				}
			}
		}
		
		Map<String, Object> feeDetail = new HashMap<String, Object>();
		infoAuthFee = BigDecimalUtil.round(BigDecimalUtil.mul(fee, infoAuthFee));
		serviceFee = BigDecimalUtil.round(BigDecimalUtil.mul(fee, serviceFee));
		interest = BigDecimalUtil.sub(fee, infoAuthFee, serviceFee);
		
		feeDetail.put("infoAuthFee", infoAuthFee);
		feeDetail.put("serviceFee", serviceFee);
		feeDetail.put("interest", interest);
		
		map.put("feeDetail", feeDetail);

		return map;
	}
	
	@Override
	public List<Map<String, Object>> choices() {
		String fee_ = Global.getValue("fee");// 综合费用
		String feeName = sysConfigService.findByCode("fee").getName();// 综合费用
		String[] fees = fee_.split(",");
		String borrowDay = Global.getValue("borrow_day");// 借款天数
		String[] days = borrowDay.split(",");
		String borrowCredit = Global.getValue("borrow_credit");//借款金额
		String[] borrowCredits = borrowCredit.split(",");
		double infoAuthFee = Double.valueOf(Global.getValue("info_auth_fee"));// 信息认证费
		double serviceFee = Double.valueOf(Global.getValue("service_fee"));// 居间服务费
		double interest = Double.valueOf(Global.getValue("interest"));// 利息
		String infoAuthFeeName = sysConfigService.findByCode("info_auth_fee").getName();// 信息认证费
		String serviceFeeName = sysConfigService.findByCode("service_fee").getName();// 居间服务费
		String interestName = sysConfigService.findByCode("interest").getName();// 利息
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < days.length; i++) {
			for (int j = 0; j < borrowCredits.length; j++) {
				Map<String,Object> map = new HashMap<>();
				double fee = Double.parseDouble(borrowCredits[j])*Double.parseDouble(fees[i]);
				map.put("fee", BigDecimalUtil.decimal(fee, 2));
				map.put("feeName", feeName);
				
				double amount = Double.parseDouble(borrowCredits[j]);
				String beheadFee = Global.getValue("behead_fee");// 是否启用砍头息
				if (beheadFee.equals("10")) {// 启用
					map.put("realAmount", amount - fee);
				} else {
					map.put("realAmount", amount);
				}
				Map<String, Object> feeDetail = new HashMap<String, Object>();
				
				feeDetail.put("infoAuthFeeName", infoAuthFeeName);
				feeDetail.put("infoAuthFee", BigDecimalUtil.decimal(fee*infoAuthFee, 2));
				feeDetail.put("serviceFeeName", serviceFeeName);
				feeDetail.put("serviceFee", BigDecimalUtil.decimal(fee*serviceFee, 2));
				feeDetail.put("interestName", interestName);
				feeDetail.put("interest", BigDecimalUtil.decimal(fee*interest, 2));
				
				map.put("feeDetail", feeDetail);
				
				map.put("timeLimit", days[i]);
				map.put("amount", amount);
				list.add(map);
			}
		}
		
		return list;
	}

	@Override
	public Borrow saveBorrow(Borrow borrow, String orderNo, Integer renewMark,double renewAmount) {
		String fee_ = Global.getValue("fee");// 综合费用
		String[] fees = fee_.split(",");
		String borrowDay = Global.getValue("borrow_day");// 借款天数
		String[] days = borrowDay.split(",");
		double serviceFee = Double.valueOf(Global.getValue("service_fee"));// 服务费
		double infoAuthFee = Double.valueOf(Global.getValue("info_auth_fee"));// 信息认证费
		double interest = Double.valueOf(Global.getValue("interest"));// 利息
		String beheadFee = Global.getValue("behead_fee");// 是否启用砍头息

		double fee = 0;
		for (int i = 0; i < days.length; i++) {
			if (borrow.getTimeLimit().equals(days[i])) {
				fee = BigDecimalUtil.round(BigDecimalUtil.mul(borrow.getAmount(), Double.parseDouble(fees[i])));
				borrow.setFee(fee);
				if ("10".equals(beheadFee)) {
					borrow.setRealAmount(borrow.getAmount() - fee);
				} else {
					borrow.setRealAmount(borrow.getAmount());
				}
			}
		}
		infoAuthFee = BigDecimalUtil.round(BigDecimalUtil.mul(fee, infoAuthFee));
		serviceFee = BigDecimalUtil.round(BigDecimalUtil.mul(fee, serviceFee));
		interest = BigDecimalUtil.sub(fee, infoAuthFee, serviceFee);

		borrow.setInfoAuthFee(infoAuthFee);
		borrow.setServiceFee(serviceFee);
		borrow.setInterest(interest);
		borrow.setOrderNo(orderNo);
		if (BorrowModel.RENEW_MARK_ORIGINAL == renewMark) {
			borrow.setState(BorrowModel.STATE_PRE);
		} else {
			borrow.setState(BorrowModel.STATE_RENEW);
		}
		borrow.setRenewMark(renewMark);
		borrow.setRenewAmount(renewAmount);
		borrow.setCreateTime(DateUtil.getNow());

		clBorrowMapper.save(borrow);

		return borrow;
	}


	@Override
	public List<Borrow> findBorrowByMap(Map<String, Object> searchMap) {
		List<Borrow> list = clBorrowMapper.listSelective(searchMap);
		return list;
	}

	private void addList(BorrowProgressModel bpModel) {
		if (bpModel.getState().equals(BorrowModel.STATE_PRE)
				|| bpModel.getState().equals(BorrowModel.STATE_NEED_REVIEW)) {
			bpModel.setMsg("系统审核中,请耐心等待");
			bpModel.setType("10");
		}
		if (bpModel.getState().equals(BorrowModel.STATE_AUTO_PASS)
				|| bpModel.getState().equals(BorrowModel.STATE_PASS)) {
			bpModel.setMsg("恭喜通过风控审核");
			bpModel.setType("10");
		}
		if (bpModel.getState().equals(BorrowModel.STATE_AUTO_REFUSED)
				|| bpModel.getState().equals(BorrowModel.STATE_REFUSED)) {
			bpModel.setMsg("很遗憾,您未通过审核");
			bpModel.setType("20");
		}
		if (bpModel.getState().equals(BorrowModel.STATE_REPAY)
				|| bpModel.getState().equals(BorrowModel.STATE_REPAY_FAIL)) {
			bpModel.setMsg("打款中,请注意查收短信");
			bpModel.setType("10");
		}
		if (bpModel.getState().equals(BorrowModel.STATE_FINISH)
				|| bpModel.getState()
						.equals(BorrowModel.STATE_DELAY_FINISH)) {
			bpModel.setMsg("已还款");
			bpModel.setType("30");
		}
		if (bpModel.getState().equals(BorrowModel.STATE_DELAY)) {
			bpModel.setMsg("已逾期,请尽快还款");
			bpModel.setType("20");
		}
		if (bpModel.getState().equals(BorrowModel.STATE_BAD)) {
			bpModel.setMsg("已坏账");
			bpModel.setType("20");
		}
	}

	@Override
	public Page<ManageBorrowModel> listModel(Map<String, Object> params,
                                             int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<ManageBorrowModel> list = clBorrowMapper.listModel(params);
		return (Page<ManageBorrowModel>) list;
	}

	@Override
	public int updateSelective(Map<String, Object> data) {
		return clBorrowMapper.updateSelective(data);
	}

	public void saveQcResult(String qcRsMsg,Borrow borrow){
		//处理浅橙接口返回结果
    	if(StringUtil.isNotBlank(qcRsMsg)){
			JSONObject resultJson = null;
			if (!qcRsMsg.toUpperCase().startsWith("<HTML>")) {
				resultJson = JSONObject.parseObject(qcRsMsg);
			}
			String code = resultJson == null ? "" : resultJson.getString("code");
			String message = resultJson == null ? (qcRsMsg) : (String.valueOf(resultJson.get("message")));

			QianchengReqlog reqLog = qianchengReqlogMapper.findByBorrowId(borrow.getId());
			reqLog.setRespCode(code);
			reqLog.setRespParams(qcRsMsg);
			reqLog.setRespTime(DateUtil.getNow());

			if ("200".equals(code) && resultJson != null) {
				reqLog.setRespOrderNo(resultJson.getString("orderNo"));
				qianchengReqlogMapper.update(reqLog);
			} else {
				BusinessExceptionMonitor.add(BusinessExceptionMonitor.TYPE_4, reqLog.getOrderNo(), message);
				reqLog.setRsState("1");
				reqLog.setRsDesc(message);
				qianchengReqlogMapper.update(reqLog);
				syncSceneBusinessLog("1", "成功", borrow.getId(), TppBusinessModel.BUS_NID_QCRISK);
			}
		}else{
			syncSceneBusinessLog("0", "浅橙返回为空字符串", borrow.getId(), TppBusinessModel.BUS_NID_QCRISK);
		}
	}


	/**
	 * 修改标的状态
	 * 
	 * @param borrow
	 * @param state
	 */
	@Override
	public int modifyState(long id, String state) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("state", state);
		paramMap.put("id", id);
		return clBorrowMapper.updateSelective(paramMap);
	}

	/**
	 * 添加借款进度
	 * 
	 * @param borrow
	 * @param state
	 */
	@Override
	public void savePressState(Borrow borrow, String state) {
		BorrowProgress borrowProgress = new BorrowProgress();
		borrowProgress.setBorrowId(borrow.getId());
		borrowProgress.setUserId(borrow.getUserId());
		if (state.equals(BorrowModel.STATE_PRE)) {
			borrowProgress.setRemark("借款" + StringUtil.isNull(borrow.getAmount()) + "元，期限" + borrow.getTimeLimit()
					+ "天，综合费用" + StringUtil.isNull(borrow.getFee()) + "元，" + BorrowModel.convertBorrowRemark(state));
		} else {
			borrowProgress.setRemark(BorrowModel.convertBorrowRemark(state));
		}
		borrowProgress.setState(state);
		borrowProgress.setCreateTime(DateUtil.getNow());
		borrowProgressMapper.save(borrowProgress);
	}

	/**
	 * 信用额度修改
	 * @param userId
	 * @param amount
	 * @param type
	 * @return
	 */
	@Override
	public int modifyCredit(Long userId, double amount, String type) {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> creditMap = new HashMap<String, Object>();
		creditMap.put("consumerNo", userId);
		Credit credit = creditMapper.findSelective(creditMap);
		if (credit != null) {
			params.put("id", credit.getId());
			if ("used".equals(type)) {
				params.put("used", amount);
				params.put("unuse", - amount);
			} else {
				params.put("used", - amount);
				params.put("unuse", amount);
			}
			int result = creditMapper.updateAmount(params);
			
			if(result != 1){
				logger.error("更新额度失败，不能出现负值，type：" + type + ",userId:" + userId);
				throw new BussinessException("更新额度失败");
			}
			return result;
		} else {
			logger.error("更新额度失败，不能出现负值，type：" + type + ",userId:" + userId);
			throw new BussinessException("更新额度失败");
		}
	}

	/**
	 * 规则引擎自动审批
	 * 
	 * @param borrow
	 * @return
	 */
	@Override
	public String verifyBorrow(Borrow borrow, String adaptedId) {
		// 根据借款申请类型查询该类型对应的所有规则  按照权重排序
		List<RuleEngineConfig> configCollection = ruleEngineConfigMapper.findRuleEnginConfigForBorrow(adaptedId);
		if (configCollection != null && !configCollection.isEmpty()) {
			BorrowRuleResult result;
			int pass = 0;
			int reveiw = 0;
			for (int i = 0; i < configCollection.size(); i++) {
				RuleEngineConfig config = configCollection.get(i);
				result = new BorrowRuleResult();
				result.setBorrowId(borrow.getId());
				result.setRuleId(config.getRuleEnginId());
				result.setTbNid(config.getCtable());
				result.setTbName(config.getTableComment());
				result.setColNid(config.getCcolumn());
				result.setColName(config.getColumnComment());
				result.setRule(config.getFormula());
				result.setAddTime(new Date());
				String statement = "select " + config.getCcolumn() + " from " + config.getCtable() + " where user_id = " + borrow.getUserId();
				if(config.getCtable().equals("cl_user")){
					statement = "select " + config.getCcolumn() + " from " + config.getCtable() + " where id = " + borrow.getUserId();
				}
				String value = ruleEngineMapper.findValidValue(statement);
				boolean flag = false;
				result.setValue(config.getCvalue());
				if (StringUtil.isNotBlank(value)) {
					String rs = SimpleRule.COMPAR_FAIL;
					if ("int".equals(config.getType())) {

						SimpleRule simpleRule = RulesExecutorUtil.singleRuleResult(config.getId(),config.getCcolumn(),config.getFormula(),config.getCvalue(), value,config.getType(), "");
						// 如果匹配通过，那么分数赋值为对应规则指定的分数
						rs = simpleRule.getComparResult();
						if (SimpleRule.COMPAR_PASS.equals(simpleRule.getComparResult())) {
							flag = true;
						} 
					} else if ("string".equals(config.getType())) {
						flag = GenerateRule.comparText(config.getFormula(),config.getCvalue(), value);
						if (flag) {
							rs = SimpleRule.COMPAR_PASS;
						} 
					}
					result.setResult(rs);
					result.setMatching(value != null ? value : "");
					result.setResultType(config.getResult());
				} else {
					// 数据库没有值，进入人工复审
					result.setMatching("未知 ");
					result.setResult(SimpleRule.COMPAR_FAIL);
					result.setResultType(BorrowRuleResult.RESULT_TYPE_REVIEW);
					borrowRuleResultMapper.save(result);
					reveiw ++;
				}
				borrowRuleResultMapper.save(result);
				if (flag && RuleEngineConfig.RESULT_PASS.equals(config.getResult())) {
					pass ++;
				}else if (flag && RuleEngineConfig.RESULT_REVIEW.equals(config.getResult())){
					reveiw ++;
				}else if (flag && RuleEngineConfig.RESULT_FAIL.equals(config.getResult())){
					return config.getResult();
				}
			}
			if(reveiw>0){
				return BorrowRuleResult.RESULT_TYPE_REVIEW;
			}else if(pass>0) {
				return BorrowRuleResult.RESULT_TYPE_PASS;
			}else{
				return BorrowRuleResult.RESULT_TYPE_REVIEW;
			}
		}else{
			return BorrowRuleResult.RESULT_TYPE_REFUSED;
		}
	}

	/**
	 * 借款标放款
	 * 
	 * @param borrow
	 * @param date
	 * @param amount
	 * @param timeLimit
	 * @return
	 */
	@Override
	public void borrowLoan(final Borrow borrow, final Date date) {

		// 调用连连支付实时付款进行放款
		new Thread() {
			@Override
			public void run() {
				Map<String, Object> bankCardMap = new HashMap<String, Object>();
				bankCardMap.put("userId", borrow.getUserId());
				BankCard bankCard = bankCardMapper.findSelective(bankCardMap);

				UserBaseInfo baseInfo = userBaseInfoMapper.findByUserId(borrow.getUserId());
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
				paymentMap.put("amount", borrow.getRealAmount());
				paymentMap.put("cardNo", bankCard.getCardNo());
				paymentMap.put("realName", baseInfo.getRealName());
				paymentMap.put("orderMemoInfo", borrow.getOrderNo() + "-放款付款");
				paymentMap.put("notifyUrl", Global.getValue("server_host") + "/pay/lianlian/paymentNotify.htm");

				PaymentModel payment = (PaymentModel) LianLianHelper.payment(paymentMap);
		
				PayLog payLog = new PayLog();
				payLog.setOrderNo(payment.getNo_order());
				payLog.setUserId(borrow.getUserId());
				payLog.setBorrowId(borrow.getId());
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
					
					clBorrowMapper.updateState(BorrowModel.STATE_REPAY_FAIL ,borrow.getId());
				}
		
				payLog.setRemark(payment.getRet_msg());
				payLog.setPayReqTime(date);
				payLog.setCreateTime(DateUtil.getNow());
				payLogMapper.save(payLog);
				//clSmsService.loanInform(borrow.getUserId(), borrow.getId());
				//demo环境下连连支付不开启状态，直接模拟连连回调成功
				String lianlianSwitch = Global.getValue("lianlian_switch");
				if ("dev".equals(Global.getValue("app_environment"))&&StringUtil.isNotBlank(lianlianSwitch) && "2".equals(lianlianSwitch)) {
					if(PayLogModel.SCENES_LOANS.equals(payLog.getScenes())){
						logger.info("模拟连连放款回调成功，生成借款计划。。");
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
				} 
				
				
				
				
			}
		}.start();
	}

	@Override
	public Map<String, Object> findResult(long borrowId) {
		Map<String, Object> data = new HashMap<>();
		List<ManageReviewModel> ruleList = borrowRuleResultMapper
				.findRuleResult(borrowId);
		data.put("ruleList", ruleList);
		List resultList = new ArrayList<>();
		List<ManageRuleResultModel> result = borrowRuleResultMapper
				.findResult(borrowId);
		for (ManageRuleResultModel model : result) {
			Map<String, Object> search = new HashMap<>();
			search.put("ruleId", model.getRuleId());
			search.put("borrowId", borrowId);
			List<BorrowRuleResult> infoList = borrowRuleResultMapper
					.findRule(search);
			for (BorrowRuleResult borrowRuleResult : infoList) {
				borrowRuleResult.setResultType(borrowRuleResult
						.alterType(borrowRuleResult.getResultType()));
			}
			model.setInfoList(infoList);
		}
		resultList.add(result);
		data.put("resultList", resultList);
		return data;
	}

	@Override
	public List<ManageBorrowTestModel> seleteUser() {
		List<ManageBorrowTestModel> list = clBorrowMapper.seleteUser();
		List<ManageBorrowTestModel> userList = new ArrayList<>();
		for (ManageBorrowTestModel user : list) {
			boolean type = true;
			Map<String, Object> searchMap = new HashMap<>();
			searchMap.put("userId", user.getUserId());
			List<Borrow> borrowList = clBorrowMapper.listSelective(searchMap);
			for (Borrow borrow : borrowList) {
				if (!borrow.getState().equals(BorrowModel.STATE_AUTO_REFUSED)
						& !borrow.getState().equals(BorrowModel.STATE_REFUSED)
						& !borrow.getState().equals(BorrowModel.STATE_FINISH)
						& !borrow.getState().equals(
								BorrowModel.STATE_DELAY_FINISH)) {
					type = false;
				}
			}
			if (type) {
				userList.add(user);
			}
			if (userList.size() >= 20){
				break;
			}
		}
		return userList;
	}

	/**
	 * 人工复审
	 */
	@Override
    //,String lineType
	public int manualVerifyBorrow(Long borrowId, String state, String remark) {
		int code = 0;
		//Borrow borrow = clBorrowMapper.findByPrimary(borrowId);
		PayLog payLog = new PayLog();
		KanyaPayFlow kanyaPayFlow=new KanyaPayFlow();
		KanyaPayRecord kanyaPayRecord=new KanyaPayRecord();
		LoanRecord loanRecord = loanRecordMapper.findByPrimary(borrowId);
		if (loanRecord != null) {
			if(loanRecord.getStatus().equals(BorrowModel.STATE_NEED_REVIEW)){
				logger.error("人工复审失败,当前状态不是待人工复审");
				throw new BussinessException("复审失败,当前状态不是待人工复审");
			}
			Map<String,Object> map = new HashMap<>();
			map.put("id", loanRecord.getIndentNo());
			map.put("state", state);
			if(state.equals(BorrowModel.STATE_REPAYING)) {
				map.put("arriveTime", new Date());
				map.put("actualBalance", (loanRecord.getBalance() - loanRecord.getFee()));
				map.put("shouldbackTime", DateUtil.getDateBefore(loanRecord.getCycle() - 1, new Date()));
			}
			code = clBorrowMapper.reviewStatus(map);
			if (code!=1) {
				throw new BussinessException("复审失败,当前状态不是待人工复审");
			}
			if ("34".equals(state)){
				kanyaUserMapper.addBlackList(loanRecord.getUid());
			}

			Long amount = loanRecord.getBalance()/100;

			if(state.equals(BorrowModel.STATE_REPAYING)){
				//if(lineType.equals("online")){
					//保存线上放款记录
//					kanyaPayRecord.setLoanRecordId(loanRecord.getId());
//					kanyaPayRecord.setLoanRecordNo(loanRecord.getIndentNo());
//					kanyaPayRecord.setIndentNo(FlowNumber.getNewFlowNumber("PR"));
//					kanyaPayRecord.setWayCode("M-PESA");
//					kanyaPayRecord.setPayTime(new Date());
//					kanyaPayRecord.setAmount(new BigDecimal((loanRecord.getBalance()-loanRecord.getFee())/100));
//					kanyaPayRecord.setStatus((byte)1);
//					kanyaPayRecord.setCreatedTime(new Date());
//					kanyaPayRecord.setUpdatedTime(new Date());
//					kanyaPayRecordMapper.save(kanyaPayRecord);
//					KanyaPayRecord kanyaPayRecord1=kanyaPayRecordMapper.findByLoanRecordId(loanRecord.getId());
//					//保存线上放款流水
//					kanyaPayFlow.setLoanRecordId(loanRecord.getId());
//					kanyaPayFlow.setLoanRecordNo(loanRecord.getIndentNo());
//					kanyaPayFlow.setPayRecordId(kanyaPayRecord1.getId());
//					kanyaPayFlow.setPayRecordNo(kanyaPayRecord1.getIndentNo());
//					kanyaPayFlow.setIndentNo(FlowNumber.getNewFlowNumber("PF"));
//					kanyaPayFlow.setWayCode("M-PESA");
//					kanyaPayFlow.setAmount(new BigDecimal((loanRecord.getBalance()-loanRecord.getFee())/100));
//					kanyaPayFlow.setStatus((byte)1);
//					kanyaPayFlow.setCreatedTime(new Date());
//					kanyaPayFlow.setUpdatedTime(new Date());
//					kanyaPayFlowMapper.save(kanyaPayFlow);
//					KanyaPayFlow kanyaPayFlow1=kanyaPayFlowMapper.findByLoanRecordId(loanRecord.getId());
//					BigDecimal amount1 = new BigDecimal((loanRecord.getBalance()-loanRecord.getFee())/100);
//					String mobile1 = loanRecord.getMobile();
//					String orderNo1 = kanyaPayFlow1.getIndentNo();
//					Map<String, Object> param=new HashMap<>();
//					param.put("amount",amount1);
//					param.put("mobile","254"+mobile1);//测试时去掉254
//					param.put("orderNo",orderNo1);
//					//调用接口(测试)
//					String mes=HttpClientUtil.sendPost("http://10.0.51.38:6082/mpesa/b2c/send",JSONObject.toJSONString(param),CONTENT_TYPE_JSON_URL,null);
//					//判断接口是否成功
//					if(1==1){
//						code=1;
//					}
//					return code;
//				}else{
					//保存线下支付流水号
					payLog.setAmount(amount.doubleValue());
					payLog.setOrderNo(remark);
					payLog.setUserId(loanRecord.getUid());
					payLog.setCardNo(loanRecord.getBankCardNo());
					payLog.setBank(loanRecord.getBankNo());
					payLog.setSource("10");
					payLog.setType("30");
					payLog.setScenes("10");
					payLog.setState("40");
					payLog.setRemark(loanRecord.getIndentNo());
					payLog.setUpdateTime(new Date());
					payLog.setCreateTime(new Date());
					payLogMapper.save(payLog);
				//}
				KanyaUserState kanyaUserState=new KanyaUserState();
				//如果放款成功，改变用户的当前状态
				if(1==1){
					kanyaUserState.setUid(loanRecord.getUid());
					kanyaUserState.setCurrentState((byte)4);
					kanyaUserStateMapper.updateCurrentState(kanyaUserState);
				}
			}
//			savePressState(borrow, state);
//			if (BorrowModel.STATE_REFUSED.equals(state)|| BorrowModel.STATE_AUTO_REFUSED.equals(state)) {
//				// 审核不通过返回信用额度
//				modifyCredit(borrow.getUserId(), borrow.getAmount(), "unuse");
//			}
			// 人工复审成功 放款
//			if (BorrowModel.STATE_PASS.equals(state)) {
//				borrowLoan(borrow, new Date());
//			}
		} else {
			logger.error("复审失败，当前标不存在");
			throw new BussinessException("复审失败，当前标不存在");
		}
		return code;
	}

	private String findBorrowDay(long userId) {
		String remark = null;
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("userId", userId);
		List<RepayModel> modelList = clBorrowMapper.findRepay(searchMap);
		for (RepayModel repayModel : modelList) {
			if (StringUtil.isNotBlank(repayModel)) {
				int day = DateUtil.daysBetween(new Date(),
						repayModel.getRepayTime());
				if (day > 0) {
					remark = "您需要" + day + "天后还款" + repayModel.getAmount()
							+ "元";
				} else if (day == 0) {
					remark = "您需要在今天还款" + repayModel.getAmount() + "元";
				}
			}
		}
		return remark;
	}

	@Override
	public LoanProduct getBorrowModelForIndentNo(Map<String, Object> params) {
		List<LoanProduct> list = clBorrowMapper.searchBorrowModelByKenya(params);
		LoanProduct loanProduct = list.get(0);
		return loanProduct;
	}

	/**
	 * 借款详细信息
	 */
	@SuppressWarnings("static-access")
	@Override
	public ManageBorrowModel getModelByBorrowId(long borrowId) {
		ManageBorrowModel model = new ManageBorrowModel();
		Borrow borrow = clBorrowMapper.findByPrimary(borrowId);
		if (borrow == null) {
			logger.error("查询的借款标不存在");
		} else {
			model = model.instance(borrow);

			model.setBorrowId(borrow.getId());
			UserBaseInfo userBaseInfo = userBaseInfoMapper.findByUserId(borrow
					.getUserId());
			if(userBaseInfo!=null){
				model.setPhone(userBaseInfo.getPhone());
				model.setRealName(userBaseInfo.getRealName());
			}

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("borrowId", borrowId);
			paramMap.put("state", BorrowProgressModel.STATE_REPAYING);
			BorrowProgress bp = borrowProgressMapper.findSelective(paramMap);
			if (bp != null) {
				model.setLoanTime(bp.getCreateTime());
			}
			paramMap = new HashMap<String, Object>();
			paramMap.put("borrowId", borrowId);
			BorrowRepay borrowRepay = borrowRepayMapper.findSelective(paramMap);
			if (borrowRepay != null) {
				model.setPenaltyAmout(borrowRepay.getPenaltyAmout());
				model.setPenaltyDay(borrowRepay.getPenaltyDay());
				if(StringUtil.isNotBlank(borrowRepay.getAmount())){
					model.setRepayTotal(BigDecimalUtil.add(borrowRepay.getAmount(),borrowRepay.getPenaltyAmout()));
				}else{
					model.setRepayTotal( 0.0);
				}
			}
			paramMap = new HashMap<String, Object>();
			paramMap.put("borrowId", borrowId);
			BorrowRepayLog borrowRepaylog = borrowRepayLogMapper
					.findSelective(paramMap);
			if (borrowRepaylog != null) {
				model.setRepayTime(DateUtil.dateStr(borrowRepaylog.getRepayTime(),DateUtil.DATEFORMAT_STR_001));
				model.setRepayAmount(borrowRepaylog.getAmount());
				if(StringUtil.isNotBlank(borrowRepay.getAmount())){
					model.setRepayYesTotal(BigDecimalUtil.add(borrowRepaylog.getAmount(),borrowRepaylog.getPenaltyAmout()));
				}else{
					model.setRepayYesTotal(0.0);
				}
				
			 }
			
			paramMap = new HashMap<String, Object>();
			paramMap.put("borrowId", borrowId);
			UrgeRepayOrder order=urgeRepayOrderMapper.findSelective(paramMap);
			if(order!=null){
				model.setLevel(order.getLevel());
			 }
			}
		
		return model;
	}

	@Override
	public Page<LoanProduct> listBorrowModel(Map<String, Object> params, int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<LoanProduct> list = clBorrowMapper.searchBorrowModelByKenya(params);
		return (Page<LoanProduct>) list;
	}

	@Override
	public Borrow findLast(Map<String, Object> searchMap) {
		return clBorrowMapper.findLast(searchMap);
	}

	@Override
	public void updatePayState(Map<String, Object> paramMap){
		int result  =  clBorrowMapper.updatePayState(paramMap);
		if(result < 1){
			throw new BussinessException("当前借款状态不允许修改");
		}
	}

	@Override
	public Borrow findByPrimary(Long borrowId) {
		return clBorrowMapper.findByPrimary(borrowId);
	}
	
	@Override
	public Borrow rcBorrowApply(Borrow borrow,String tradePwd,String mobileType) throws Exception {
		Borrow realBorrow = null;
		// 校验用户是否符合借款条件
		boolean isCanBorrow = isCanBorrow(borrow,tradePwd);
		if(isCanBorrow){
			realBorrow = saveBorrow(borrow, NidGenerator.getOrderNo(), BorrowModel.RENEW_MARK_ORIGINAL,0.0 );
		}
		
		if (realBorrow != null && realBorrow.getId() > 0) {
			long borrowId = realBorrow.getId();
			savePressState(realBorrow, BorrowModel.STATE_PRE);
			modifyCredit(realBorrow.getUserId(),realBorrow.getAmount(),"used");
			
			List<TppServiceInfoModel> infoList = sceneBusinessMapper.findTppServiceInfo();
			//不需要执行有可用历史记录的数量
			logger.debug("审核需要执行的接口信息"+JSONObject.toJSONString(infoList));
			//如果配置了风控数据,先调用风控数据接口
			if (infoList != null && infoList.size() > 0) {
				SceneBusinessLog sceneLog = null;
				for(TppServiceInfoModel info : infoList){
					boolean needExcute = sceneBusinessLogService.needExcute(realBorrow.getUserId(),info.getBusId(),info.getGetWay(),info.getPeriod());
					if(needExcute){
						sceneLog = new SceneBusinessLog(info.getSceneId(), realBorrow.getId(), realBorrow.getUserId(), info.getTppId(), info.getBusId(), info.getBusNid(), realBorrow.getCreateTime(),info.getType());
						sceneBusinessLogMapper.save(sceneLog);
					}
				}
			}
		} else {
			throw new BussinessException("借款失败");
		}
		return realBorrow;
	}

	/**
	 * 第三方接口机审
	 * @param borrow
	 * @param nid
	 * @param tppId
	 * @param mobileType
	 */
	public void getThirdServiceData(final Borrow borrow,final String nid,final Long tppId,final String mobileType){

		logger.warn("args : ------> nid=" + nid + "|" + tppId + "|" + mobileType);

		//芝麻分
		if("CreditScoreQuery".equals(nid)){
			Thread t = new Thread(new Runnable(){  
				public void run(){
					int count = zhimaService.updateZhimaScore(borrow.getUserId());
					syncSceneBusinessLog(borrow.getId(), nid, count);
				}
			});  
			t.start();
		//浅橙风控审核
		} else if("QcRisk".equals(nid)){
			//增加"复借是否请求浅橙"开关 1 否 2 是
			String reborrow_qiancheng_switch=Global.getValue("reborrow_qiancheng_switch");
			if(reborrow_qiancheng_switch==null||reborrow_qiancheng_switch.equals("")){//sys_config表里面没有字段的时候默认没有这个功能，值直接是2
				reborrow_qiancheng_switch="2";
			}
			int borrowCount=0;//默认为0，当开关值是1的时候，去数据库查询当前用户的还款成功次数，次数为0时，请求浅橙的接口
			if(reborrow_qiancheng_switch.equals("1")){
				borrowCount=clBorrowMapper.userBorrowCount(borrow.getUserId());
			}
			if(borrowCount==0){
				Thread t = new Thread(new Runnable(){  
					public void run(){
						TppBusiness bussiness = tppBusinessService.findByNid(nid,tppId);
						String reqOrderNo = RandomUtil.getRandomNumString(9);
						String operatorNo = operatorReqLogMapper.findOrderByUserId(borrow.getUserId());
						String qcRsMsg = rcQianchenService.qianchenRiskRequest(borrow.getUserId(), borrow, operatorNo, reqOrderNo,bussiness);
						logger.info("qianchen返回结果"+qcRsMsg);
						saveQcResult(qcRsMsg,borrow);
					}
				});  
				t.start(); 
			}
			//同盾贷前审核
		} else if("TongdunApply".equals(nid)){
			logger.info("进入同盾贷前审核数据查询");
			Thread t = new Thread(new Runnable(){
				public void run(){
                    logger.warn("========= 不起线程  ==========");
					TppBusiness bussiness = tppBusinessService.findByNid(nid,tppId);
					int count = tongdunReqLogService.preloanApplyRequest(borrow.getUserId(),borrow,bussiness,mobileType);
					syncSceneBusinessLog(borrow.getId(), nid, count);
				}
			});
			t.start();
		//华道黑名单
		} else if("HuadaoBlacklist".equals(nid)){
			logger.info("进入华道黑名单查询");
			Thread t = new Thread(new Runnable(){  
				public void run(){
					TppBusiness business = tppBusinessService.findByNid(nid,tppId);
					int count = rcHuadaoBlacklistLogService.queryHuadaoBlackList(borrow.getUserId(),business);
					syncSceneBusinessLog(borrow.getId(), nid, count);
				}
			});  
			t.start(); 
		//大圣 贷后邦
		} else if ("DhbSauron".equals(nid)) {
			logger.info("进入贷后邦_反欺诈信息查询");
			Thread t = new Thread(new Runnable() {
				public void run() {
					TppBusiness business = tppBusinessService.findByNid(nid,tppId);
					int count = dhbReqLogService.queryDhbSauron(borrow, business);
					syncSceneBusinessLog(borrow.getId(), nid, count);
				}
			});
			t.start();
		}else if("Rong360Blacklist".equals(nid)){
			logger.info("进入融360黑名单查询");
			Thread t = new Thread(new Runnable(){
				public void run(){
					TppBusiness business = tppBusinessService.findByNid(nid,tppId);
					int count = rcR360BlacklistLogService.queryR360BlackList(borrow.getUserId());
					syncSceneBusinessLog(borrow.getId(), nid, count);
				}
			});
			t.start();
		} else if("Rong360YScore".equals(nid)){
			logger.info("进入融360评分查询");
			Thread t = new Thread(new Runnable(){
				public void run(){
					TppBusiness business = tppBusinessService.findByNid(nid,tppId);
					int count = rcR360GradeService.queryR360Grade(borrow.getUserId());
					syncSceneBusinessLog(borrow.getId(), nid, count);
				}
			});
			t.start();
		} else if("BaiRongGrade".equals(nid)){
			logger.info("进入百融通用评分查询");
			Thread t = new Thread(new Runnable(){
				public void run(){
					TppBusiness business = tppBusinessService.findByNid(nid,tppId);
					int count = baiRongGradeService.requestGrade(borrow.getUserId());
					syncSceneBusinessLog(borrow.getId(), nid, count);
				}
			});
			t.start();
		} else if("JiGuangBlacklist".equals(nid)){
			logger.info("进入极光黑名单查询");
			Thread t = new Thread(new Runnable(){
				public void run(){
					TppBusiness business = tppBusinessService.findByNid(nid,tppId);
					int count = rcJiGuangBlacklistLogService.queryJiGuangBlackList(borrow,business);
					syncSceneBusinessLog(borrow.getId(), nid, count);
				}
			});
			t.start();
		} else if("ShuMeiBlacklist".equals(nid)){
			logger.info("进入数美逾期黑名单查询");
			Thread t = new Thread(new Runnable(){
				public void run(){
					TppBusiness business = tppBusinessService.findByNid(nid,tppId);
					int count = clShuMeiBlacklistService.queryShuMeiBlackList(borrow,business);
					syncSceneBusinessLog(borrow.getId(), nid, count);
				}
			});
			t.start();
		} else if("JiGuangLbsCheck".equals(nid)){
			logger.info("进入极光LBS验真接口-分级验真查询");
			Thread t = new Thread(new Runnable(){
				public void run(){
					TppBusiness business = tppBusinessService.findByNid(nid,tppId);
					int count = rcJiGuangLbsCheckLogService.queryJiGuangLbsCheck(borrow,business);
					syncSceneBusinessLog(borrow.getId(), nid, count);
				}
			});
			t.start();
		} else if("JiGuangLbsFuzzyCheck".equals(nid)){
			logger.info("进入极光LBS验真接口-模糊匹配查询");
			Thread t = new Thread(new Runnable(){
				public void run(){
					TppBusiness business = tppBusinessService.findByNid(nid,tppId);
					int count = rcJiGuangLbsFuzzyCheckLogService.queryJiGuangLbsFuzzyCheck(borrow,business);
					syncSceneBusinessLog(borrow.getId(), nid, count);
				}
			});
			t.start();
		} else{
			logger.error("没有找到"+nid+"对应的第三方接口信息");
		}
	}
	
	@Override
	public void rcBorrowRuleVerify(Long borrowId){
		Borrow borrow = getById(borrowId);
		String resultType = BorrowRuleResult.RESULT_TYPE_REFUSED;
		// 根据借款申请类型查询该类型对应的所有规则
		List<RuleEngineConfig> configCollection = ruleEngineConfigMapper.findRuleEnginConfigForBorrow("10");
//		logger.info("\t需要执行的规则"+JSONObject.toJSONString(configCollection));
		if (configCollection != null && !configCollection.isEmpty()) {
//			 logger.info("\t\t规则表达式开始执行");
			
			boolean review = false;
			
			BorrowRuleResult result;
			for (int i = 0; i < configCollection.size(); i++) {
//				logger.info("\t\t当前执行的规则:"+JSONObject.toJSONString(configCollection.get(i)));
				RuleEngineConfig config = configCollection.get(i);
				result = new BorrowRuleResult();
				result.setBorrowId(borrowId);
				result.setRuleId(config.getRuleEnginId());
				result.setTbNid(config.getCtable());
				result.setTbName(config.getTableComment());
				result.setColNid(config.getCcolumn());
				result.setColName(config.getColumnComment());
				result.setRule(config.getFormula());
				result.setAddTime(new Date());

				String tabelName = config.getCtable();
				if("".equals(config.getCtable()) || "".equals(config.getCtable())){
					tabelName = ShardTableUtil.generateTableNameById(config.getCtable(), borrow.getUserId(), 30000);
				}
				String statement = "select " + config.getCcolumn() + " from " + config.getCtable() + " where user_id = " + borrow.getUserId()+" order by id desc limit 1";
				if(config.getCtable().equals("cl_user")){
					statement = "select " + config.getCcolumn() + " from " + config.getCtable() + " where id = " + borrow.getUserId()+" order by id desc limit 1";
				}
//				logger.info("\t\t规则表达式执行sql="+statement);
				String value = ruleEngineMapper.findValidValue(statement);
				boolean flag = false;
				result.setValue(config.getCvalue());
//                logger.info("\t\t规则表达式执行value=" + value);
                if (StringUtil.isNotBlank(value)) {
//					logger.info("\t\t规则表达式执行类型="+config.getType());
					if ("int".equals(config.getType())) {

						SimpleRule simpleRule = RulesExecutorUtil.singleRuleResult(config.getId(),config.getCcolumn(),config.getFormula(),config.getCvalue(), value,config.getType(), "");
						// 如果匹配通过，那么分数赋值为对应规则指定的分数
						if (SimpleRule.COMPAR_PASS.equals(simpleRule.getComparResult())) {
							result.setResult(SimpleRule.COMPAR_PASS);
							flag = true;
						} else {
							result.setResult(SimpleRule.COMPAR_FAIL);
						}
					} else if ("string".equals(config.getType())) {
						flag = GenerateRule.comparText(config.getFormula(), config.getCvalue(),value);
						result.setResult(flag ? SimpleRule.COMPAR_PASS : SimpleRule.COMPAR_FAIL);
					}
//					logger.info("\t\t规则表达式执行结果flag="+flag+",resultType="+config.getResult());
					result.setMatching(value != null ? value : "");
					result.setResultType(config.getResult());
				} else {
//					logger.info("\t\t数据库没有值，进入人工复审");
					// 数据库没有值，进入人工复审
					result.setMatching("未知 ");
					result.setResult(SimpleRule.COMPAR_FAIL);
					result.setResultType(BorrowRuleResult.RESULT_TYPE_REVIEW);
					resultType = BorrowRuleResult.RESULT_TYPE_REVIEW;
				}
//				logger.info("\t\t保存执行结果");
				borrowRuleResultMapper.save(result);
				if (flag) {
					resultType = config.getResult();
					if(BorrowRuleResult.RESULT_TYPE_REVIEW.equals(config.getResult())) {
						review = true;
					}
					if (BorrowRuleResult.RESULT_TYPE_REFUSED.equals(config.getResult())) {
						break;
					}
				}
				// 没有命中结果，但是规则已经执行完毕，则进入人工复审   2017.5.6 改成审核通过
				if (i == (configCollection.size() - 1)) {
//					logger.info("规则已经执行完毕，则判断是否命中过人工复审，若未命中人工复审则审核通过");
					if(review){
						resultType = BorrowRuleResult.RESULT_TYPE_REVIEW;
					}else{
						resultType =BorrowRuleResult.RESULT_TYPE_PASS;
					}
					break ;
				}
//			logger.info("当前规则表达式执行结束resultType="+resultType);
			}
		}
		//logger.info("最终规则执行结束resultType="+resultType);
		if(StringUtil.isNotBlank(resultType)){
			// 规则匹配结果命中不通过，直接修改借款申请状态为审核失败并更新借款进度,释放额度
			if (BorrowRuleResult.RESULT_TYPE_REFUSED.equals(resultType)) {
				modifyState(borrow.getId(), BorrowModel.STATE_AUTO_REFUSED);
				savePressState(borrow, BorrowModel.STATE_AUTO_REFUSED);
				modifyCredit(borrow.getUserId(), borrow.getAmount(), "unuse");

			// 规则匹配结果命中需要人工复审，直接修改借款申请状态为需要人工复审并更新借款进度
			} else if (BorrowRuleResult.RESULT_TYPE_REVIEW.equals(resultType)) {
				modifyState(borrow.getId(), BorrowModel.STATE_NEED_REVIEW);
				savePressState(borrow, BorrowModel.STATE_NEED_REVIEW);

			// 规则匹配结果命中通过，或者结果为“”，则为审核通过
			} else {
				modifyState(borrow.getId(), BorrowModel.STATE_AUTO_PASS);
				savePressState(borrow, BorrowModel.STATE_AUTO_PASS);
				// 放款
				borrowLoan(borrow, new Date());
				
			}
		}else{
			//借款审核失败
			modifyState(borrow.getId(), BorrowModel.STATE_AUTO_REFUSED);
			savePressState(borrow, BorrowModel.STATE_AUTO_REFUSED);
			modifyCredit(borrow.getUserId(), borrow.getAmount(), "unuse");
		}
	}

	/**
	 * 系统内部审核引擎
	 * @param borrow
	 * @param nid
	 */
	public void getStatisticsServiceData(final Borrow borrow,final String nid){
		if("borrow".equals(nid)){ //借款人借款信息，逾期借款信息，联系人逾期借款信息
			Thread t = new Thread(new Runnable() { 
				public void run(){
					int count = borrowCountService.countBorrowRefusedTimes(borrow.getUserId());
					syncSceneBusinessLog(borrow.getId(), nid,count);
				}
			});  
			t.start();
		} else if("simple_borrow_count".equals(nid)){ //（简）借款统计
			Thread t = new Thread(new Runnable() {  
				public void run(){
					int count = simpleBorrowCountService.countOne(borrow.getUserId());
					syncSceneBusinessLog(borrow.getId(), nid, count);
				}
			});  
			t.start();
		} else if("simple_contact_count".equals(nid)){ //（简）通讯录统计
			Thread t = new Thread(new Runnable() {  
				public void run(){
					int count = simpleContactCountService.countOne(borrow.getUserId());
					syncSceneBusinessLog(borrow.getId(), nid, count);
				}
			});  
			t.start();
		} else if("simple_voices_count".equals(nid)){ //（简）通话记录统计
			Thread t = new Thread(new Runnable() {  
				public void run(){
					int count = simpleVoicesCountService.countOne(borrow.getUserId());
					syncSceneBusinessLog(borrow.getId(), nid, count);
				}
			});  
			t.start();
		} else if("concats".equals(nid)){ //通讯录借款信息统计
			Thread t = new Thread(new Runnable(){  
				public void run(){
					int count = contactCountService.countContacts(borrow.getUserId());
					syncSceneBusinessLog(borrow.getId(), nid,count);
				}
			});  
			t.start();
		}
		else{
			logger.error("没有找到"+nid+"对应的统计处理");
		}
	}
	
	@Override
	public void syncSceneBusinessLog(final Long borrowId, String nid, int count) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("borrowId", borrowId);
		params.put("nid", nid);
		SceneBusinessLog log = sceneBusinessLogMapper.findSelective(params);
		
		if(log != null){
			String state = "0";
			String desc = "失败";
			if (count > 0) {
				state = "1";
				desc = "成功";
			}
			log.setUpdateTime(new Date());
			log.setRsState(state);
			log.setRsDesc(desc);
			int result = sceneBusinessLogMapper.update(log);
			logger.info("syncSceneBusinessLog，borrowId："+borrowId+"，nid："+nid+"，syncSceneBusinessLog更新结果："+result);
			boolean haveNeed = sceneBusinessLogService.haveNeedExcuteService(borrowId);
			if(!haveNeed){
				rcBorrowRuleVerify(borrowId);
			}
		} else {
			logger.error("syncSceneBusinessLog，borrowId："+borrowId+"，nid："+nid+"，未找到对应的sceneBusinessLog");
		}
	}
	
	@Override
	public void syncSceneBusinessLog(String state, String desc, final Long borrowId, String nid) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("borrowId", borrowId);
		params.put("nid", nid);
		SceneBusinessLog log = sceneBusinessLogMapper.findSelective(params);
		
		if(log != null){
			log.setUpdateTime(new Date());
			log.setRsState(state);
			log.setRsDesc(desc);
			sceneBusinessLogMapper.update(log);
			boolean haveNeed = sceneBusinessLogService.haveNeedExcuteService(borrowId);
			if(!haveNeed){
				rcBorrowRuleVerify(borrowId);
			}
		}
	}

	/**
	 * 机审借款信息
	 * @param borrowId
	 */
	@Override
	public void verifyBorrowData(long borrowId,String mobileType) {
		Borrow borrow = clBorrowMapper.findByPrimary(borrowId);
		List<SceneBusinessLog> sceneLogList = sceneBusinessLogMapper.findSceneLogByBorrowId(borrowId);
		
		if(borrow != null){
			if (sceneLogList != null && !sceneLogList.isEmpty()){
				for(SceneBusinessLog log : sceneLogList){
					if(TppServiceInfoModel.SERVICE_TYPE_STATISTICS.equals(log.getType())){
						// 系统内部审核
						getStatisticsServiceData(borrow,log.getNid());
					} else if(TppServiceInfoModel.SERVICE_TYPE_THIRD.equals(log.getType())){
						// 第三方系统审核
						getThirdServiceData(borrow,log.getNid(),log.getTppId(),mobileType);
					}
				}
			} else {
				rcBorrowRuleVerify(borrowId);
			}
		}
	}

	/**
	 * 重审借款
	 * @param borrowId
	 */
	@Override
	public void reVerifyBorrowData(Long borrowId) {
		Borrow borrow = clBorrowMapper.findByPrimary(borrowId);
		List<SceneBusinessLog> sceneLogList = sceneBusinessLogMapper.findSceneLogByBorrowId(borrowId);
		
		if (borrow != null && sceneLogList != null && !sceneLogList.isEmpty()){
			logger.info("风控审核重触发，borrowId："+borrow.getId()+"进入征信数据重获取流程");
			
			for(SceneBusinessLog log : sceneLogList){
				//sceneBusinessLogMapper.save(log);
				if(TppServiceInfoModel.SERVICE_TYPE_STATISTICS.equals(log.getType())){
					getStatisticsServiceData(borrow,log.getNid());
					
				} else if(TppServiceInfoModel.SERVICE_TYPE_THIRD.equals(log.getType())){
					getThirdServiceData(borrow,log.getNid(),log.getTppId(),"0");
				}
			}
		} else if (sceneLogList == null || sceneLogList.isEmpty()) {
			Map<String, Object> ruleResultMap = new HashMap<String, Object>();
			ruleResultMap.put("borrowId", borrowId);
			List<BorrowRuleResult> resultList = borrowRuleResultMapper.listSelective(ruleResultMap);
			if (resultList == null || resultList.isEmpty()) {
				logger.info("风控审核重触发，borrowId："+borrow.getId()+"进入规则审核流程");
				rcBorrowRuleVerify(borrowId);
			} else if (BorrowModel.STATE_AUTO_PASS.equals(borrow.getState())
					|| BorrowModel.STATE_PASS.equals(borrow.getState())) {
				logger.info("风控审核重触发，borrowId："+borrow.getId()+"进入放款流程");
				borrowLoan(borrow, DateUtil.getNow());
			}
		} else {
			logger.info("风控审核重触发，borrowId："+borrow.getId()+"，不满足执行条件，执行失败");
		}
	}
	
	public List listBorrow(Map<String, Object> params) {
		List<ManageBorrowExportModel> list = clBorrowMapper.listExportModel(params);
		for (ManageBorrowExportModel model : list) {
			model.setState(BorrowModel.apiConvertBorrowState(model.getState()));
			UserBaseInfo ubi = userBaseInfoMapper.findByUserId(model.getUserId());
			if (ubi!=null) {
				model.setRealName(ubi.getRealName());
				model.setPhone(ubi.getPhone());
			}
			Map<String, Object> params2 = new HashMap<>();
			params2.put("borrowId", model.getId());
			params2.put("state", BorrowModel.STATE_REPAY);
			BorrowProgress bp = borrowProgressMapper.findSelective(params2);
			if (bp!=null) {
				model.setLoanTime(bp.getCreateTime());
			}
			Map<String, Object> params3 = new HashMap<>();
			params3.put("borrowId", model.getId());
			BorrowRepay br = borrowRepayMapper.findSelective(params3);
			if (br!=null) {
				model.setPenaltyDay(br.getPenaltyDay());
				model.setPenaltyAmout(br.getPenaltyAmout());
			}
			BorrowRepayLog brl = borrowRepayLogMapper.findSelective(params3);
			if (brl!=null) {
				model.setRepayAmount(brl.getAmount());
				model.setRepayTime(brl.getRepayTime());
			}
			UrgeRepayOrder uro = urgeRepayOrderMapper.findSelective(params3);
			if (uro!=null) {
				model.setLevel(uro.getLevel());
			}
			
		}
		return list;
	}

	@Override
	public Page<ManageBorrowModel> listReview(Map<String,Object> params,int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<ManageBorrowModel> list = clBorrowMapper.listReview(params);
		return (Page<ManageBorrowModel>) list;
	}

	@Override
	public List<Borrow> findUserUnFinishedBorrow(long userId) {
		List<String> stateList = Arrays.asList(BorrowModel.STATE_AUTO_REFUSED, BorrowModel.STATE_REFUSED,
				BorrowModel.STATE_DELAY_FINISH, BorrowModel.STATE_DELAY_REMISSION_FINISH, BorrowModel.STATE_FINISH,
				BorrowModel.STATE_TO_RENEW);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("stateList", stateList);
		return clBorrowMapper.findUserUnFinishedBorrow(param);
	}

	@Override
	public Borrow findLastBorrow(Map<String, Object> borrowMap) {
		return clBorrowMapper.findLastBorrow(borrowMap);
	}
	
	/**
	 * 审核放款
	 */
	@Override
	public int auditBorrowLoan(Long borrowId, String state, String remark,Long userId) {
		int code = 0;
		Borrow borrow = clBorrowMapper.findByPrimary(borrowId);
		if (borrow != null) {
			if(!borrow.getState().equals(BorrowModel.WAIT_AUDIT_LOAN)){
				logger.error("审核失败,当前状态不是待审核放款");
				throw new BussinessException("审核失败,当前状态不是待审核放款");
			}
			Map<String,Object> map = new HashMap<>();
			map.put("id", borrowId);
			map.put("state", state);
			map.put("remark", remark);
			code = clBorrowMapper.loanState(map);
			if (code!=1) {
				throw new BussinessException("放款审核失败,当前状态不是待审核放款");
			}
			
		//	savePressState(borrow, state);
			//添加借款进度
			BorrowProgress borrowProgress = new BorrowProgress();
			borrowProgress.setBorrowId(borrow.getId());
			borrowProgress.setUserId(borrow.getUserId());
			borrowProgress.setState(state);
			borrowProgress.setRemark("人工放款审核");
			
			borrowProgress.setAuditRemark(remark);
			borrowProgress.setAuditUser(userId);
			borrowProgress.setCreateTime(DateUtil.getNow());
			borrowProgressMapper.save(borrowProgress);
			
			if (BorrowModel.AUDIT_LOAN_FAIL.equals(state)) {
				// 审核不通过返回信用额度
				modifyCredit(borrow.getUserId(), borrow.getAmount(), "unuse");
			}
			// 审核放款通过 放款
			if (BorrowModel.AUDIT_LOAN_PASS.equals(state)) {
				borrowLoan(borrow, new Date());
			}
		} else {
			logger.error("审核放款失败，当前标不存在");
			throw new BussinessException("审核放款失败，当前标不存在");
		}
		return code;
	}

	@Override
	public boolean isCanRenew(Borrow borrow) {
		// 1.1 校验借款是否存在
		if (null == borrow) {
			throw new SimpleMessageException(StringUtil.isNull(Constant.FAIL_CODE_VALUE), "请稍候重试");
		}
		// 1.2 校验借款状态
		if (!BorrowModel.STATE_REPAY.equals(borrow.getState()) && !BorrowModel.STATE_RENEW.equals(borrow.getState())
				&& !BorrowModel.STATE_DELAY.equals(borrow.getState())) {
			throw new SimpleMessageException(StringUtil.isNull(Constant.FAIL_CODE_VALUE), "当前借款不允许续借");
		}

		// 1.3 校验是否有对应的用户信息
		User user = userMapper.findByPrimary(borrow.getUserId());
		if (user == null || user.getId() < 1) {
			throw new SimpleMessageException(StringUtil.isNull(Constant.FAIL_CODE_VALUE), "找不到对应的用户信息");
		}

		// 1.4校验中户是否在黑名单
		UserBaseInfo userBaseInfo = userBaseInfoMapper.findByUserId(borrow.getUserId());
		if (userBaseInfo != null && UserBaseInfoModel.USER_STATE_BLACK.equals(userBaseInfo.getState())) {
			throw new SimpleMessageException(Constant.FAIL_CODE_VALUE + "", "该账号无法借款,请联系客服人员。");
		}
		return true;
	}
	@Override
	public int updateBorrow(long borrowId, long userId, String state) {
		// 更新借款状态
		int count = clBorrowMapper.updateState(state, borrowId);

		if (count > 0) { // 借款状态更新成功、添加借款进度
			BorrowProgress bp = new BorrowProgress(userId, borrowId, state, BorrowModel.convertBorrowRemark(state));
			bp.setCreateTime(DateUtil.getNow());
			count = borrowProgressMapper.save(bp);
		}
		return count;
	}
	@Override
	public boolean renewReturn(String payType, String payResult, String payOrderNo) {
		logger.info("续借申请支付订单:" + payOrderNo + "结果:" + payResult);
		// 根据订单号查询支付记录
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderNo", payOrderNo);
		PayLog payLog = payLogMapper.findSelective(paramMap);
		if (null == payLog) {
			throw new SimpleMessageException(StringUtil.isNull(Constant.FAIL_CODE_VALUE), "联系客服");
		}
		
		if (!PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())) {
			logger.info("订单已经处理");
			return true;
		}
		
		// 失败时更改支付记录状态， 成功更新借款状态， 支付状态等待异步再修改
		String state = getPayLogState(payType, payResult);
		
		// 将订单状态改为处理前状态 (放款成功、续借中、逾期中)
		if (PayLogModel.STATE_PAYMENT_FAILED.equals(state)) {
			payLog.setState(state);
			payLogMapper.update(payLog);
			return false;
		} else {
			// 根据支付状态 决定 是否修改借款状态为赎回处理中
			modifyBorrowState(payLog.getBorrowId(), payLog.getUserId(), BorrowModel.STATE_RENEW_PROCESSING);

			// 修改续借状态
			Borrow borrow = clBorrowMapper.findByPrimary(payLog.getBorrowId());
			if (borrow != null && borrow.getRenewMark() != null && borrow.getRenewMark() > 0) {
				Map<String, Object> renewStateMap = new HashMap<>();
				renewStateMap.put("id", borrow.getOriginalId());
				renewStateMap.put("renewState", BorrowModel.STATE_RENEW_PROCESSING);
				clBorrowMapper.updateSelective(renewStateMap);
			}
			return true;
		}
	}

	@Override
	public List<Borrow> listSelective(Map<String, Object> paramMap) {
		return clBorrowMapper.listSelective(paramMap);
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
	 * 续借同步回调使用，更新标的之前会判断原标的状态是否为还清，如果已经还清，则不再更改状态
	 * @param borrowId
	 * @param userId
	 * @param state
	 * @return
	 */
	private int modifyBorrowState(long borrowId, long userId, String state) {
		// 允许续借状态集合
		List<String> stateList = Arrays.asList(BorrowModel.STATE_REPAY,BorrowModel.STATE_RENEW,BorrowModel.STATE_DELAY,BorrowModel.STATE_BAD);
		// 更新借款状态
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

	
	@Override
	public void renewNotify(Long borrowId, String payState, String repayOrderNo, String payType, String repayAccount,
			String repayAmount) {

		Borrow borrow = clBorrowMapper.findByPrimary(borrowId);
		// 判断续借源项目是否存在
		if (borrow == null) {
			return;
		}
		Map<String, Object> borrowRepaySearch = new HashMap<>();
		borrowRepaySearch.put("borrowId", borrowId);
		BorrowRepay borrowRepay = borrowRepayMapper.findSelective(borrowRepaySearch);

		// 判断交易状态，若为成功 调用确认还款； 若为失败， 修改还款状态
		if (PayLogModel.STATE_PAYMENT_SUCCESS.equals(payState)) {

			// 原订单进行还款 - 还款计划修改，添加还款记录
			Map<String, Object> repayParamMap = new HashMap<>();
			repayParamMap.put("id", borrowRepay.getId());
			repayParamMap.put("repayTime", DateUtil.getNow());
			repayParamMap.put("repayWay", payType);
			repayParamMap.put("repayAccount", repayAccount);
			repayParamMap.put("amount", repayAmount);
			repayParamMap.put("serialNumber", repayOrderNo);
			repayParamMap.put("penaltyAmout", borrowRepay.getPenaltyAmout());
			repayParamMap.put("state", BorrowRepayModel.RENEW_APPLY_REPAYMENT);
			borrowRepayService.confirmRepay(repayParamMap);
			
			// 生成新的续借项目
			Borrow realRenewBorrow = null;

			long originalId = 0;
			String originalOrderNo = "";
			if (borrow.getRenewMark() != null && borrow.getRenewMark() > 0) {
				originalId = borrow.getOriginalId();
				originalOrderNo = borrow.getOriginalOrderNo();
			} else {
				originalId = borrow.getId();
				originalOrderNo = borrow.getOrderNo();
			}

			Map<String, Object> params = new HashMap<>();
			params.put("originalId", originalId);
			params.put("originalOrderNo", originalOrderNo);
			List<Borrow> renewList = clBorrowMapper.listSelective(params);

			int renewNum = renewList.size() + 1;
			String orderPadding = StringUtil.leftPad(StringUtil.isNull(renewNum), 3, "0");
			String renewOrderNo = originalOrderNo.concat("_").concat(orderPadding);
			Borrow renewBorrow = new Borrow(borrow.getUserId(), borrow.getAmount(), borrow.getTimeLimit(),
					borrow.getCardId(), borrow.getClient(), borrow.getAddress(), borrow.getCoordinate(),
					borrow.getIp());
			renewBorrow.setOriginalId(originalId);
			renewBorrow.setOriginalOrderNo(originalOrderNo);
			realRenewBorrow = saveBorrow(renewBorrow, renewOrderNo, renewNum,NumberUtil.getDouble(repayAmount));

			// 原订单添加借款进度
			if (realRenewBorrow != null && realRenewBorrow.getId() > 0) {
				savePressState(realRenewBorrow, BorrowModel.STATE_RENEW);
			}

			// 生成续借项目还款计划并授权
			Date now = DateUtil.getNow();
			if (now.before(borrowRepay.getRepayTime())) { // 当前时间在还款之前 ，则认为未逾期
				borrowRepayService.genRepayPlan(realRenewBorrow, DateUtil.rollDay(borrowRepay.getRepayTime(), 1));
			} else {
				// 项目逾期续借
				borrowRepayService.genRepayPlan(realRenewBorrow, DateUtil.getNow());
			}

		    // 续借项目还款计划代扣授权
            borrowRepayService.authSignApply(realRenewBorrow.getId());
            
		} else if (PayLogModel.STATE_PAYMENT_FAILED.equals(payState)) {
			String state = "";
			// 如果还款时间在当前时间前面，说明已经逾期
			if (borrowRepay.getRepayTime().before(DateUtil.getNow())) {
				state = BorrowModel.STATE_DELAY;
			} else {
				state = BorrowModel.STATE_REPAY;
			}
			// 修改标的状态为 借款中/逾期
			clBorrowMapper.updateState(state, borrowId);

			// 原订单添加借款进度
			if (borrow != null && borrow.getId() > 0) {
				savePressState(borrow, state);
			}
		}
	}
	
	@Override
	public Page<Borrow> findRenewBorrowPage(Map<String, Object> paramMap, int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<Borrow> list = clBorrowMapper.listSelective(paramMap);
		return (Page<Borrow>) list;
	}

	@Override
	public LoanRecord findByPrimaryforKenya(long id){
		return loanRecordMapper.findByPrimary(id);
	}

	@Override
	public ClBorrowModel findBorrow(Long borrowId) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("id", borrowId);
		List<ClBorrowModel> clBorrowModelList = clBorrowMapper.findBorrow(params);
		ClBorrowModel clBorrowModel = null;
		if (CollectionUtils.isNotEmpty(clBorrowModelList)) {
			clBorrowModel = clBorrowModelList.get(0);
		}
		return clBorrowModel;
	}
}