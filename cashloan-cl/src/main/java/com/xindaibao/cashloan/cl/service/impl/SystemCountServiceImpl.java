package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.mapper.LoanRecordMapper;
import com.xindaibao.cashloan.cl.mapper.SystemCountMapper;
import com.xindaibao.cashloan.cl.service.SystemCountService;
import com.xindaibao.cashloan.core.common.util.DateUtil;
import com.xindaibao.cashloan.core.mapper.KanyaUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import tool.util.BigDecimalUtil;
import tool.util.StringUtil;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 首页系统数据统计
 * @author
 * @version 1.0
 * @date 2017年3月16日上午10:16:51


 *

 */
@SuppressWarnings("unchecked")
@Service("systemCountService")
public class SystemCountServiceImpl implements SystemCountService {

	@Resource
	private SystemCountMapper systemCountMapper;
	@Resource
	private KanyaUserMapper kenyaUserMapper;
	@Resource
	private LoanRecordMapper loanRecordMapper;
	@Override
	public Map<String, Object> systemCount()throws Exception {
		Map<String,Object> rtMap = new HashMap<String, Object>();

		Map<String,Object> param = new HashMap<String, Object>();
		rtMap.put("todayTime", DateUtil.getNow());

		//当前用户注册数
		Integer register = kenyaUserMapper.selectRegisteredCount();
		rtMap.put("register", register);

		//当天借款申请数
		Integer borrow = loanRecordMapper.loanApplicationCount();
		rtMap.put("borrow", borrow);

		//通过次数
		Integer borrowPass = loanRecordMapper.throughCount();
		rtMap.put("borrowPass", borrowPass);

		// 放款通过率

		if(borrow>0 && borrowPass>0){
            Double passApr = loanRecordMapper.loanPassThroughRate();
            BigDecimal passAprs = new BigDecimal(passApr);
            rtMap.put("passApr", passAprs.multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP));
		}else{
			rtMap.put("passApr", 0);
		}

		//今日放款量
		Integer borrowLoan = loanRecordMapper.loanAmount();
		rtMap.put("borrowLoan", borrowLoan);


		//今日还款量
		Integer borrowRepay = loanRecordMapper.reimbursementAmount();
		rtMap.put("borrowRepay", borrowRepay);

		//历史放款总量（笔）
		Integer borrowLoanHistory = loanRecordMapper.totalHistoricalLoan();
		rtMap.put("borrowLoanHistory", borrowLoanHistory);

		//历史还款总量（笔）
		Integer borrowRepayHistory = loanRecordMapper.totalReimbursementCount();
		rtMap.put("borrowRepayHistory", borrowRepayHistory);

		//待还款总金额
		Integer needRepay  = loanRecordMapper.totalAmountRepaid();
		BigDecimal needsRepay = new BigDecimal(needRepay);
		rtMap.put("needRepay", needsRepay.divide(new BigDecimal(100)));

		//逾期未还款本金
		Integer overdueRepay = loanRecordMapper.overdueAmountPrincipal();
		rtMap.put("overdueRepay", overdueRepay/100);

		Map<String,Object> result = null;
		List<Map<String,Object>> rtValue = null;

		this.monthCountDispose(result,rtValue,rtMap);

		rtValue = systemCountMapper.countRepaySource();
		result = reBuildMap(rtValue);
		String[] source = {"自动代扣","银行卡转账","支付宝转账","其它"};
		List<Map<String,Object>> sourceList = new ArrayList<Map<String,Object>>();
		Map<String,Object> sm;
		for(int i=0;i<source.length;i++){
			if(!result.containsKey(source[i])){
				result.put(source[i], 0);
			}
			sm = new HashMap<String, Object>();
			sm.put(source[i], result.get(source[i]));
			sourceList.add(sm);
		}
		rtMap.put("repaySource", sourceList);

		List<String> days = new ArrayList<String>();
		Date nowDate = DateUtil.getNow();
		days.add(DateUtil.dateStr(nowDate, DateUtil.DATEFORMAT_STR_002));
		Calendar date = Calendar.getInstance();
		for(int i=0;i<15;i++){
			date.setTime(nowDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			nowDate = date.getTime();
			String day = DateUtil.dateStr(nowDate, DateUtil.DATEFORMAT_STR_002);
			days.add(day);
		}

		systemCountMapper.countFifteenDaysNeedRepay();
		List<Map<String,Object>> rtValue1 = systemCountMapper.countFifteenDaysNeedRepay();
		List<Map<String,Object>> rtValue2 = systemCountMapper.countFifteenDaysRealRepay();
		List<Map<String,Object>> rtValue4 = systemCountMapper.countFifteenDaysLoan();
		Map<String,Object> result1 = reBuildMap(rtValue1);
		Map<String,Object> result2 = reBuildMap(rtValue2);
		Map<String,Object> result4 = reBuildMap(rtValue4);
		Map<String,Object> result3 = new HashMap<String, Object>();
		for(int i=0;i<days.size();i++){
			String day = days.get(i);
			if(!result1.containsKey(day)){
				result1.put(day, 0.00);
			}
			if(!result2.containsKey(day)){
				result2.put(day, 0.00);
			}

			String needStr = String.valueOf(result1.get(day));
			needStr = (StringUtil.isNotBlank(needStr) && !"null".equals(needStr))?needStr:"0.00";
			String realStr = String.valueOf(result2.get(day));
			realStr = (StringUtil.isNotBlank(realStr) && !"null".equals(realStr))?realStr:"0.00";
			Double need = Double.valueOf(needStr);
			Double real = Double.valueOf(realStr);
			if(real>=need){
				result3.put(day, 0.00);
			}else if(real<need){
				Double diff = need - real;
				result3.put(day, diff/need);
			}else{
				result3.put(day, 1.0);
			}

			if(!result4.containsKey(day)){
				result4.put(day, 0);
			}
		}
		rtMap.put("fifteenDaysNeedRepay", result1);
		rtMap.put("fifteenDaysRealRepay", result2);
		rtMap.put("fifteenDaysOverdueApr", result3);
		rtMap.put("fifteenDaysLoan", result4);

		return rtMap;
	}

	public Map<String,Object> reBuildMap(List<Map<String,Object>> maps){
		if(maps!=null){
			Map<String,Object> result = new HashMap<String, Object>();
			for(int i=0;i<maps.size();i++){
				String key = String.valueOf(maps.get(i).get("key"));
				if(StringUtil.isNotBlank(key)){
					key = key==null?"":key;

				}else{
					key = "未知地区";
				}
				Object value = maps.get(i).get("value");
				result.put(key, value);
			}
			result.remove("null");
			return result;
		}else{
			return new HashMap<String, Object>();
		}
	}

	/**
	 * 查询时间过长的内容保存context
	 * @param result
	 * @param rtValue
	 * @param rtMap
	 * @throws ParseException
	 */
	private void monthCountDispose(Map<String, Object> result, List<Map<String, Object>> rtValue, Map<String, Object> rtMap) throws ParseException{
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContext context = webApplicationContext.getServletContext();

		if (StringUtil.isNotBlank(context)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Object t  = context.getAttribute("monthCountSelectTime");
			int now = DateUtil.getDay(DateUtil.getNow());
			if (t == null || DateUtil.getDay(sdf.parse(t.toString())) != now) {

				rtValue = MonthCount(1);
				result = reBuildMap(rtValue);
				rtMap.put("monthBorrowAmt", result);
				context.setAttribute("monthBorrowAmt", rtValue);

				rtValue = MonthCount(2);
				result = reBuildMap(rtValue);
				rtMap.put("monthBorrowRepay", result);
				context.setAttribute("monthBorrowRepay", rtValue);

				rtValue = MonthCount(3);
				result = reBuildMap(rtValue);
				rtMap.put("monthRegister", result);
				context.setAttribute("monthRegister", rtValue);

				context.setAttribute("monthCountSelectTime", sdf.format(now));//保存时间

			}else {

				rtValue = (List<Map<String, Object>>) context.getAttribute("monthBorrowAmt");
				result = reBuildMap(rtValue);
				rtMap.put("monthBorrowAmt", result);

				rtValue = (List<Map<String, Object>>) context.getAttribute("monthBorrowRepay");
				result = reBuildMap(rtValue);
				rtMap.put("monthBorrowRepay", result);

				rtValue = (List<Map<String, Object>>) context.getAttribute("monthRegister");
				result = reBuildMap(rtValue);
				rtMap.put("monthRegister", result);
			}
		}
	}

	//所有地区数组
	private static String[] address = {"北京市","上海市","天津市","重庆市","内蒙古自治区","宁夏回族自治区","新疆维吾尔自治区","西藏自治区","广西壮族自治区"
		,"香港特别行政区","澳门特别行政区","黑龙江省","辽宁省","吉林省","河北省","河南省","湖北省","湖南省","山东省","山西省","陕西省","安徽省","浙江省","江苏省","福建省",
		"广东省","海南省","四川省","云南省","贵州省","青海省","甘肃省","江西省","台湾省"};
	//地区显示处理
	private String changeAdd(String address){
		address = address.replace("省", "").replace("市", "").replace("自治区", "").replace("回族", "").replace("维吾尔", "")
				.replace("壮族", "").replace("特别行政区", "");
		return address;
	}
	//累计数据统计
	private List<Map<String, Object>> MonthCount(int type){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		switch (type) {
		case 1:
			for (int i = 0; i < address.length; i++) {
				Map<String, Object> map = new HashMap<>();
				map.put("key",changeAdd(address[i]));
				map.put("value", systemCountMapper.sumBorrowAmtByProvince(address[i]+"%"));
				list.add(map);
			}
			break;
		case 2:
			for (int i = 0; i < address.length; i++) {
				Map<String, Object> map = new HashMap<>();
				map.put("key",changeAdd(address[i]));
				map.put("value", systemCountMapper.sumBorrowRepayByProvince(address[i]+"%"));
				list.add(map);
			}
			break;
		default:
			for (int i = 0; i < address.length; i++) {
				Map<String, Object> map = new HashMap<>();
				String userCount = systemCountMapper.countRegisterByProvince(address[i]+"%");
				if (Integer.valueOf(userCount)>0) {
					map.put("key",changeAdd(address[i]));
					map.put("value", userCount);
				}
				list.add(map);
			}
			break;
		}
		return list;
	}
}
