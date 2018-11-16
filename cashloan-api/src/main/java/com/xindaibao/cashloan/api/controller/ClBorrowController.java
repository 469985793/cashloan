package com.xindaibao.cashloan.api.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xindaibao.cashloan.cl.mapper.ClBorrowMapper;
import com.xindaibao.cashloan.cl.service.*;
import com.xindaibao.cashloan.rc.domain.TppBusiness;
import com.xindaibao.cashloan.rc.service.TppBusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tool.util.BigDecimalUtil;
import tool.util.DateUtil;
import tool.util.NumberUtil;
import tool.util.StringUtil;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.BankCard;
import com.xindaibao.cashloan.cl.domain.BorrowRepay;
import com.xindaibao.cashloan.cl.domain.PayLog;
import com.xindaibao.cashloan.cl.domain.QianchengReqlog;
import com.xindaibao.cashloan.cl.model.ClBorrowModel;
import com.xindaibao.cashloan.cl.model.IndexModel;
import com.xindaibao.cashloan.cl.model.PayLogModel;
import com.xindaibao.cashloan.cl.model.QianchengReqlogModel;
import com.xindaibao.cashloan.cl.model.RepayModel;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.exception.BussinessException;
import com.xindaibao.cashloan.core.common.exception.SimpleMessageException;
import com.xindaibao.cashloan.core.common.util.OrderNoUtil;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.model.BorrowModel;
import com.xindaibao.cashloan.core.service.CloanUserService;
import com.xindaibao.cashloan.core.service.UserBaseInfoService;
import com.xindaibao.cashloan.rc.model.TppBusinessModel;
import com.xindaibao.cashloan.rc.service.SceneBusinessLogService;

import credit.ApiSignUtil;

/**
 * 借款申请风控接口执行记录
 *
 * @version 1.0
 * @date 2017年4月11日下午5:40:14
 */
@Scope("prototype")
@Controller
public class ClBorrowController extends BaseController {
	public static final Logger logger = LoggerFactory.getLogger(ClBorrowController.class);
	@Resource
	private CloanUserService cloanUserService;
	@Resource
	private ClBorrowService clBorrowService;
	@Resource
	private QianchengReqlogService qianchengReqlogService;
	@Resource
	private OperatorReqLogService operatorReqLogService;
	@Resource
	private CloanUserService userService;
	@Resource
	private SceneBusinessLogService sceneBusinessLogService;
	
	@Resource
	private BorrowRepayService borrowRepayService;
	@Resource
	private BankCardService bankCardService;
	@Resource
	private PayLogService payLogService;
	
	@Resource
	private UserBaseInfoService userBaseInfoService;
	
	/**
	 * 查询借款列表
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/act/borrow/findAll.htm")
	public void findAll() throws Exception {
		String userId = request.getSession().getAttribute("userId").toString();
		Map<String,Object> searchMap = new HashMap<>();
		searchMap.put("userId", userId);
		List<RepayModel> list = clBorrowService.findRepay(searchMap);
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("list", list);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data); 
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 查询借款
	 * @param userId
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/act/mine/borrow/page.htm", method = RequestMethod.POST)
	public void page(
			@RequestParam(value="userId") long userId,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String,Object> searchMap = new HashMap<>();
		searchMap.put("userId", userId);
		Page<ClBorrowModel> page = clBorrowService.page(searchMap,current, pageSize);
		Map<String, Object> data = new HashMap<>();
		data.put("list", page.getResult());
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 查询订单
	 * @param search
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/act/borrow/findBorrow.htm", method = RequestMethod.POST)
	public void findBorrow(
			@RequestParam(value="borrowId",required=false) Long borrowId) throws Exception {
		Borrow borrow = clBorrowService.getById(borrowId);
		ClBorrowModel data = new ClBorrowModel();
		BeanUtils.copyProperties(borrow, data);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}





	/**
	 * 首页信息查询
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/borrow/findIndex.htm")
	public void findIndex() throws Exception {
		String userId = request.getParameter("userId");
		Map<String,Object> data = clBorrowService.findIndex(userId);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 首页轮播信息
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/borrow/listIndex.htm")
	public void listIndex() throws Exception {
		List<IndexModel> list = clBorrowService.listIndex();
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("list", list);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 选择借款金额和期限
	 * app里选择借款金额和期限，返回实际到账金额、服务费、服务费明细
	 * @param amount
	 * @param timeLimit
	 */
	@RequestMapping(value = "/api/borrow/choice.htm")
	public void choice(@RequestParam(value = "amount") double amount,
			@RequestParam(value = "timeLimit") String timeLimit) {
		Map<String,Object> result = new HashMap<String,Object>();
		if(!(amount > 0) || StringUtil.isBlank(timeLimit)){
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "查询失败，请核对参数");
		} else {
			result.put(Constant.RESPONSE_DATA, clBorrowService.choice(amount, timeLimit));
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		}
		
		ServletUtils.writeToResponse(response, result);
	}
	
	/**
	 * 查询所有借款费用信息
	 */
	@RequestMapping(value = "/api/borrow/choices.htm")
	public void choices() {
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("list", clBorrowService.choices());
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response, result);
	}
	
	/**
	 * 借款申请  新
	 * @param amount
	 * @param timeLimit
	 * @param tradePwd
	 * @param cardId
	 * @param client
	 * @param address
	 * @param coordinate
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/act/borrow/save.htm")
	public void save(
			@RequestParam(value="amount") double amount,
			@RequestParam(value="timeLimit") String timeLimit,
			@RequestParam(value="tradePwd") String tradePwd,
			@RequestParam(value="cardId") long cardId,
			@RequestParam(value="client") String client,
			@RequestParam(value="address") String address,
			@RequestParam(value="coordinate") String coordinate,
			@RequestParam(value="ip",required=false) String ip
			) throws Exception {
		String mobileType=request.getParameter("mobileType"); 
		long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		Map<String,Object> result = new HashMap<String,Object>();
		Borrow borrow = new Borrow(userId, amount, timeLimit, cardId, client, address, coordinate,ip);
		borrow = clBorrowService.rcBorrowApply(borrow, tradePwd, mobileType);
		clBorrowService.verifyBorrowData(borrow.getId(),mobileType);
		
		if (borrow != null && borrow.getId() > 0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "申请成功");
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "申请失败");
		}
		
		ServletUtils.writeToResponse(response,result);
	}
	

	/**
	 * 浅橙借款申请异步回调接口
	 * @param request
	 */
	@RequestMapping(value = "/api/qianchenNotify.htm",method=RequestMethod.POST)
	public void qianchenNotify(HttpServletRequest request,HttpServletResponse response) throws Exception{
		final Map<String,Object> params = getRequestFormMap(request);
		String sign = String.valueOf(params.get("sign"));
		String res = String.valueOf(params.get("res"));
		String orderNo = String.valueOf(params.get("orderNo"));
		
		Map<String, Object> signMap = new HashMap<>();/*验签map*/
        signMap.put("res", res);
        signMap.put("orderNo", orderNo);
        signMap.put("timestamp", params.get("timestamp"));
		String tempSign = ApiSignUtil.sign(Global.getValue("apikey"), Global.getValue("secretkey"), signMap);
		
		String result = "FAIL";
		if(!tempSign.equals(sign)){
			logger.error("orderNo：" + orderNo + "浅橙回调验签失败，回调参数："+JSONObject.toJSONString(params));
		} else {
			QianchengReqlog reqLog = null;
			if(StringUtil.isNotBlank(orderNo)){
				reqLog = qianchengReqlogService.findByOrderNo(String.valueOf(params.get("orderNo")));
			}
			if (reqLog != null) {
				result = "SUCCESS";
				logger.info("浅橙回调0:"+result);
				if(QianchengReqlogModel.STATE_SUBMIT.equals(reqLog.getState())){
					reqLog.setNotifyParams(JSONObject.toJSONString(params));
					reqLog.setUpdateTime(DateUtil.getNow());
					reqLog.setState("30");
					final JSONObject resObj = JSONObject.parseObject(res);
					if(resObj != null){
						reqLog.setRsState(resObj.getString("state"));
						reqLog.setRsDesc(resObj.getString("msg"));
						// 20 审核通过 30 审核不通过
						if (reqLog.getRsState().equals("0")) {
							reqLog.setState("20");
						} else {
							reqLog.setState("30");
						}
					}
					qianchengReqlogService.update(reqLog);
					
					final long borrowId = reqLog.getBorrowId();
					logger.info("浅橙回调1:"+borrowId);
					Thread handleBorrow = new Thread(new Runnable() {
						@Override
						public void run() {
							logger.info("浅橙回调2:"+borrowId);
							clBorrowService.syncSceneBusinessLog("1", "成功", borrowId, TppBusinessModel.BUS_NID_QCRISK);
						}
					});
					handleBorrow.start();
				}
			} else {
				logger.error("orderNo：" + orderNo + "浅橙回调，未找到对应的浅橙请求订单，回调参数："+JSONObject.toJSONString(params));
			}
		}
		
		PrintWriter writer=  response.getWriter();
		writer.print(result);
		writer.flush();
		writer.close();
	}
	
	
	/**
	 * 申请借款   旧
	 * @param amount
	 * @param fee
	 * @param realAmount
	 * @param timeLimit
	 * @param orderNo
	 * @param tradePwd
	 * @param userId
	 * @param cardId
	 * @param client
	 * @throws Exception
	 */
	/*@RequestMapping(value = "/api/act/borrow/save.htm")
	public void save(
			@RequestParam(value="amount") double amount,
			@RequestParam(value="timeLimit") String timeLimit,
			@RequestParam(value="tradePwd") String tradePwd,
			@RequestParam(value="cardId") long cardId,
			@RequestParam(value="client") String client,
			@RequestParam(value="address") String address,
			@RequestParam(value="coordinate") String coordinate
			) throws Exception {
		long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		final String orderNo = operatorReqLogService.findOrderByUserId(userId);
		Map<String,Object> result = new HashMap<String,Object>();
		int day = isBorrow(userId);
		
		User user = cloanUserService.getById(userId);
		
		if ("0000".equals(tradePwd)||(StringUtil.isNotBlank(user)&&user.getTradePwd().equals(tradePwd))) {
			if (day<1) {
				if(StringUtil.isNotBlank(orderNo)){
					Borrow borrow = clBorrowService.save(amount,timeLimit,tradePwd, user.getId(), cardId, client, address, coordinate);
					if (borrow!=null && borrow.getId()!=null && borrow.getId()>0) {
						final Long brId = borrow.getId();
						final Long usrId = userId;
						
						if("20".equals(Global.getValue("qc_ser_state"))||"20".equals(Global.getValue("borrow_rule_state"))){//开启
							clBorrowService.autoVerifyBorrow(brId,usrId,orderNo);
						} else {
							clBorrowService.borrowLoan(borrow,DateUtil.getNow());
						}
						
						result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
						result.put(Constant.RESPONSE_CODE_MSG, "申请成功");
					} else {
						result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
						result.put(Constant.RESPONSE_CODE_MSG, "申请失败");
					}
				}else{
					result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
					result.put(Constant.RESPONSE_CODE_MSG, "申请失败，请先进行运营商认证");
				}
			}else {
				result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "申请失败,您在"+day+"天后才能再次借款");
			}
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_PWD);
			result.put(Constant.RESPONSE_CODE_MSG, "交易密码不正确");
		}
		
		ServletUtils.writeToResponse(response,result);
	}*/
	
	/**
	 * 浅橙借款申请异步回调接口
	 * @param request
	 */
//	@RequestMapping(value = "/api/qianchenNotify.htm",method=RequestMethod.POST)
//	public void borrowNotify(HttpServletRequest request,HttpServletResponse response) throws Exception{
//		final Map<String,Object> params = getRequestFormMap(request);
//		String sign = String.valueOf(params.get("sign"));
//		String res = String.valueOf(params.get("res"));
//		String orderNo = String.valueOf(params.get("orderNo"));
//		
//		Map<String, Object> signMap = new HashMap<>();/*验签map*/
//        signMap.put("res", res);
//        signMap.put("orderNo", orderNo);
//        signMap.put("timestamp", params.get("timestamp"));
//		String tempSign = ApiSignUtil.sign(Global.getValue("apikey"), Global.getValue("secretkey"), signMap);
//		
//		String result = "FAIL";
//		if(!tempSign.equals(sign)){
//			logger.error("【借款申请回调】验签失败,回调参数："+JSONObject.toJSONString(params));
//		}else{
//			QianchengReqlog reqLog = null;
//			if(StringUtil.isNotBlank(orderNo)){
//				reqLog = qianchengReqlogService.findByOrderNo(String.valueOf(params.get("orderNo")));
//			}
//			if(reqLog!=null){
//				reqLog.setNotifyParams(JSONObject.toJSONString(params));
//				reqLog.setUpdateTime(DateUtil.getNow());
//				JSONObject resObj = JSONObject.parseObject(res);
//				if(resObj != null){
//					reqLog.setRsState(resObj.getString("state"));
//					reqLog.setRsDesc(resObj.getString("msg"));
//				}
//				qianchengReqlogService.update(reqLog);
//				final long borrowId = reqLog.getBorrowId();
//				Thread handleBorrow = new Thread(new Runnable() {
//					@Override
//					public void run() {
//						clBorrowService.updateBorrowInfo(params,borrowId);
//					}
//				});
//				handleBorrow.start();
//				result = "SUCCESS";
//			}else{
//				logger.error("【借款申请回调】找不到对应的订单日志,回调参数："+JSONObject.toJSONString(params));
//			}
//		}
//		
//		PrintWriter writer=  response.getWriter();
//		writer.print(result);
//		writer.flush();
//		writer.close();
//	}
	
//	private int isBorrow(long userId){
//		String againBorrow = Global.getValue("again_borrow");
//		Map<String,Object> searchMap = new HashMap<>();
//		searchMap.put("userId", userId);
//		Borrow borrow = clBorrowService.findLast(searchMap);
//		if (StringUtil.isNotBlank(borrow)) {
//			return Integer.parseInt(againBorrow)-DateUtil.daysBetween(borrow.getCreateTime(),new Date());
//		}
//		return 0;
//	}
	
	
	/**
	 * 续借 申请
	 * 
	 * @param borrowId
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/act/borrow/renewDetail.htm")
	public void renewDetail(@RequestParam(value = "borrowId") Long borrowId) throws Exception {

		Long userId = getSessionUserId();
		// 若Session中0 大于等于userId  则认为未登录
		if (0 >= userId) {
			logger.error("用户未登录,不能续借");
			throw new SimpleMessageException(StringUtil.isNull(Constant.FAIL_CODE_VALUE), "请先登录");
		}
		// 校验borrow项目
		Borrow borrow = clBorrowService.getById(borrowId);
		if (null == borrow) {
			logger.error("续借项目不存在");
			throw new SimpleMessageException(StringUtil.isNull(Constant.FAIL_CODE_VALUE), "续借项目不存在");
		}

		// 查询续借项目还款计划
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("borrowId", borrowId);
		BorrowRepay borrowRepay = borrowRepayService.findSelective(paramMap);

	
		// 生成续借还款期限
		Date now = DateUtil.getNow();
		Date repayTime  = null;	//
		if (now.before(borrowRepay.getRepayTime())) { // 当前时间在还款之前 ，则认为未逾期
			repayTime = DateUtil.rollDay(borrowRepay.getRepayTime(), NumberUtil.getInt(borrow.getTimeLimit()));
		} else { // 项目逾期续借
			repayTime = DateUtil.rollDay(now, NumberUtil.getInt(borrow.getTimeLimit())-1);
		}
		
		// 续借支付费用 = 服务费 + 滞纳金(逾期罚金)			
		double amount = BigDecimalUtil.add(borrow.getFee(), borrowRepay.getPenaltyAmout());
		Map<String, Object> map = new HashMap<>();
		map.put("amount", borrow.getAmount()); // 回收价
		map.put("renewalDay", Global.getValue("borrow_day")); // 续借期限
		map.put("serviceFee", borrow.getFee()); // 综合服务费
		map.put("repayDate", DateUtil.dateStr2(repayTime)); // 还款日
		map.put("lateFee", borrowRepay.getPenaltyAmout()); // 滞纳金
		map.put("payAmount", amount); // 支付金额

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, map);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 续借 申请
	 * 
	 * @param borrowId
	 * @param payType
	 * @param ip
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/act/borrow/renewApply.htm")
	public void renewApply(@RequestParam(value = "borrowId") Long borrowId,
			@RequestParam(value = "ip", required = false) String ip) throws Exception {
		// 校验是否能进行续借,计算服务费
		Borrow borrow = clBorrowService.getById(borrowId);
		double renewAmount = 0; // 续借上期服务费
		boolean isCanRenew = clBorrowService.isCanRenew(borrow);
		if (isCanRenew) {
			// 计算服务费
			String overdueRenew = Global.getValue("overdue_renew"); // 逾期是否允许续借

			// 若逾期不可以续借且当前借款已逾期,提示借款已逾期，不可续借
			if (StringUtil.isNotBlank(overdueRenew) && !"10".equals(overdueRenew)
					&& (BorrowModel.STATE_DELAY.equals(borrow.getState())
							|| BorrowModel.STATE_BAD.equals(borrow.getState()))) {
				logger.info("当前借款已逾期不可续借,订单号：" + borrow.getOrderNo());
				throw new SimpleMessageException(StringUtil.isNull(Constant.FAIL_CODE_VALUE), "借款已逾期,不可续借");
			} else {
				// TODO:逾期可续借时，检查额度，恢复到提额前

				Map<String, Object> repayMap = new HashMap<String, Object>();
				repayMap.put("borrowId", borrow.getId());
				BorrowRepay borrowRepay = borrowRepayService.findSelective(repayMap);
				
				renewAmount = BigDecimalUtil.add(borrow.getFee(), borrowRepay.getPenaltyAmout());
			}
		}

		// 服务费大于0, 则认为正常
		if (0 >= renewAmount) {
			logger.info("借款BorrowId" + borrow.getId() + "服务费计算出错!");
			throw new SimpleMessageException(StringUtil.isNull(Constant.FAIL_CODE_VALUE), "服务费计算出错,请联系客服 ");
		}

		// 添加支付订单记录 ,封装支付参数并返回给App进行支付
		String body = "小额宝";
		String orderNo = OrderNoUtil.getSerialNumber();
		Map<String, String> sdkParam = borrowRepayService.paySdkParams(borrow.getUserId(), PayLogModel.TYPE_ACTIVE_DEBIT_LIANLIAN, renewAmount, ip,
				body, orderNo);

		// 若参数封装失败，直接返回失败，业务不再执行
		if (sdkParam == null || sdkParam.size() < 1) {
			throw new SimpleMessageException(StringUtil.isNull(Constant.FAIL_CODE_VALUE), "支付失败");
		}

		// 插入一条待支付的支付记录
		BankCard bankCard = bankCardService.findByUserId(borrow.getUserId());
		savePayLog(orderNo, borrow.getUserId(), borrowId, renewAmount, bankCard.getCardNo(), bankCard.getBank(),
				PayLogModel.TYPE_ACTIVE_DEBIT_LIANLIAN);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, sdkParam);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 续借返回处理
	 * 
	 * @param payType
	 * @param payResult
	 * @param payOrderNo
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/act/borrow/renewReturn.htm")
	public void renewReturn(@RequestParam(value = "payResult") String payResult,
			@RequestParam(value = "payOrderNo") String payOrderNo ) throws Exception {
		// 连连认证支付
		clBorrowService.renewReturn(PayLogModel.TYPE_ACTIVE_DEBIT_LIANLIAN, payResult, payOrderNo);

		// 返回支付类型、参数封装结果及状态码
		Map<String, Object> result = new HashMap<String, Object>();

		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, result);
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
	 * @return
	 */
	private int savePayLog(String orderNo, Long userId, Long borrowId, double amount, String cardNo, String bank,
			String type) {
		PayLog payLog = new PayLog();
		payLog.setOrderNo(orderNo);
		payLog.setUserId(userId);
		payLog.setBorrowId(borrowId);
		payLog.setAmount(amount);
		payLog.setCardNo(cardNo);
		payLog.setBank(bank);
		payLog.setSource(PayLogModel.SOURCE_FUNDS_OWN);
		payLog.setType(type);
		payLog.setScenes(PayLogModel.SCENES_RENEW_APPLY);
		payLog.setState(PayLogModel.STATE_PAYMENT_WAIT);
		payLog.setPayReqTime(DateUtil.getNow());
		payLog.setCreateTime(DateUtil.getNow());
		int result = payLogService.insert(payLog);
		return result;
	}
	
	/**
	 * 借款协议预览
	 * 
	 * @param amount
	 * @param timeLimit
	 * @return
	 */
	@RequestMapping(value = "/api/act/borrow/protocolPreview.htm")
	public String protocolPreview(@RequestParam(value = "amount") double amount,
			@RequestParam(value = "timeLimit") String timeLimit) {
		String userId = request.getParameter("userId");
		
		if(StringUtil.isBlank(userId)  || NumberUtil.getLong(userId) == 0 ){
			throw new BussinessException(StringUtil.isNull(Constant.FAIL_CODE_VALUE),"请先登录");
		}
		
		String companyName = Global.getValue("company_name");// 乙方平台名称
		UserBaseInfo userBaseInfo = userBaseInfoService.findByUserId(NumberUtil.getLong(userId));
		// 根据借款金额和借款期限，返回其余费用明细
		Map<String, Object> map = clBorrowService.choice(amount, timeLimit);

		Date now = DateUtil.getNow(); // 当前时间
		Date repayTime = DateUtil.rollDay(now, Integer.parseInt(timeLimit) - 1); // 还款日期

		map.put("companyName", companyName);// 平台名称
		map.put("userName", userBaseInfo.getRealName());// 资金借入方
		map.put("idNo", userBaseInfo.getIdNo());// 身份证号码
		map.put("amount", amount);// 借款金额
		map.put("createTime", DateUtil.valueOf(DateUtil.dateStr(now), "yyyy年MM月dd日"));// 借款日期
		map.put("repayTime", DateUtil.valueOf(DateUtil.dateStr(repayTime), "yyyy年MM月dd日"));// 还款日期
		map.put("timeLimit", timeLimit);// 借款期限
		request.setAttribute("datas", map);
		return "protocolBorrow";
	}
	
	/**
	 * 续借列表
	 * 
	 * @param borrowId
	 */
	@RequestMapping(value = "/api/act/borrow/renewList.htm")
	public void renewList(@RequestParam(value = "borrowId") Long borrowId, @RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) {
		Long userId = getSessionUserId();
		if (0 >= userId) {
			logger.error("用户未登录");
			throw new SimpleMessageException(StringUtil.isNull(Constant.FAIL_CODE_VALUE), "请先登录");
		}
		// 校验borrow项目
				Borrow borrow = clBorrowService.getById(borrowId);
				long originalId = 0;
				String originalOrderNo = "";
				Map<String, Object> result = new HashMap<String, Object>();
				if (borrow != null) {
					if (borrow.getRenewMark() != null && borrow.getRenewMark() > 0) {
						originalId = borrow.getOriginalId();
						originalOrderNo = borrow.getOriginalOrderNo();
					} else {
						originalId = borrow.getId();
						originalOrderNo = borrow.getOriginalOrderNo();
					}
					
					Map<String, Object> params = new HashMap<>();
					params.put("originalId", originalId);
					params.put("originalOrderNo", originalOrderNo);
					
					Page<Borrow> page = clBorrowService.findRenewBorrowPage(params, current, pageSize);
					
					Map<String, Object> data = new HashMap<>();
					data.put("list", page.getResult());
					
					result.put(Constant.RESPONSE_DATA, data);
					result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
					result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
					result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
				} else {
					result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
					result.put(Constant.RESPONSE_CODE_MSG, "未查询到续借信息");
				}

				ServletUtils.writeToResponse(response, result);
	}
	
	
	
	
}
