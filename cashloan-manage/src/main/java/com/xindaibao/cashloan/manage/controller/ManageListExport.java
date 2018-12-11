package com.xindaibao.cashloan.manage.controller;

import com.xindaibao.cashloan.cl.model.kenya.LoanProduct;
import com.xindaibao.cashloan.cl.service.*;
import com.xindaibao.cashloan.core.common.context.ExportConstant;
import com.xindaibao.cashloan.core.common.util.DateUtil;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.excel.JsGridReportBase;
import com.xindaibao.cashloan.core.model.BorrowModel;
import com.xindaibao.cashloan.core.service.CloanUserService;
import com.xindaibao.cashloan.system.domain.SysDownloadLog;
import com.xindaibao.cashloan.system.domain.SysUser;
import com.xindaibao.cashloan.system.service.SysDownloadLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tool.util.StringUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.ROUND_UP;

@Scope("prototype")
@Controller
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ManageListExport extends ManageBaseController{

	@Resource
	private ClBorrowService clBorrowService;
	@Resource
	private CloanUserService userService;
	@Resource
	private OperatorReqLogService operatorReqLogService;
	@Resource
	private BorrowRepayLogService borrowRepayLogService;
	@Resource
	private PayLogService payLogService;
	@Resource
	private PayCheckService payCheckService;
	@Resource
	private UrgeRepayOrderService urgeRepayOrderService;
	@Autowired
	private SysDownloadLogService sysDownloadLogService;


	/**
	 * 导出支付审核报表
	 *
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/remitCheckLog/export.htm")
	public void remitCheckExport(
			@RequestParam(value="searchParams",required = false) String searchParams) throws Exception {
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		List list = clBorrowService.remitCheckLog(params);
		for(int i=0;i<list.size();i++){
			LoanProduct p=(LoanProduct)list.get(i);
			p.setStatusStr("Review and approval, pending payment");
			p.setScenesStr("Under line");
			list.set(i,p);
		}
		SysUser user = (SysUser) request.getSession().getAttribute("SysUser");
		response.setContentType("application/msexcel;charset=UTF-8");
		// 记录取得
		String title = "支付审核Excel表";
		String[] hearders =  ExportConstant.EXPORT_REMITCHECKLOG_LIST_HEARDERS;
		String[] fields = ExportConstant.EXPORT_REMITCHECKLOG_LIST_FIELDS;
		JsGridReportBase report = new JsGridReportBase(request, response);
		report.exportExcel(list,title,hearders,fields,user.getName());
		saveLog(list, user.getUserName(), SysDownloadLog.REPAY_LOG);
	}


	/**
	 * 导出还款记录报表
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/borrowRepayLog/export.htm")
	public void repayLogExport(
			@RequestParam(value="searchParams",required = false) String searchParams) throws Exception {
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		List list = borrowRepayLogService.listExport(params);
		SysUser user = (SysUser) request.getSession().getAttribute("SysUser");
		response.setContentType("application/msexcel;charset=UTF-8");
		// 记录取得
		String title = "还款记录Excel表";
		String[] hearders =  ExportConstant.EXPORT_REPAYLOG_LIST_HEARDERS;
		String[] fields = ExportConstant.EXPORT_REPAYLOG_LIST_FIELDS;
		JsGridReportBase report = new JsGridReportBase(request, response);
		report.exportExcel(list,title,hearders,fields,user.getName());
		saveLog(list, user.getUserName(), SysDownloadLog.REPAY_LOG);
	}

	/**
	 * 导出还款计划报表
	 *
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/repaymentPlanList/export.htm")
	public void repaymentPlanListExport(
			@RequestParam(value="searchParams",required = false) String searchParams) throws Exception {
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		String status = null;
		List stateList = new ArrayList();
		if(params==null){
			params=new HashMap<>();
			status = BorrowModel.STATE_REPAYING;
			stateList.add(status);
			status=BorrowModel.STATE_FINISH;
			stateList.add(status);
			status=BorrowModel.STATE_DELAY;
			stateList.add(status);
			status=BorrowModel.STATE_DELAY_FINISH;
			stateList.add(status);
			status=BorrowModel.STATE_BAD;
			stateList.add(status);
			params.put("stateList",stateList);
		}
		if(StringUtil.isNotBlank(params)) {
			if (StringUtil.isBlank(params.get("state"))) {
				status = BorrowModel.STATE_REPAYING;
				stateList.add(status);
				status=BorrowModel.STATE_FINISH;
				stateList.add(status);
				status=BorrowModel.STATE_DELAY;
				stateList.add(status);
				status=BorrowModel.STATE_DELAY_FINISH;
				stateList.add(status);
				status=BorrowModel.STATE_BAD;
				stateList.add(status);
				params.put("stateList",stateList);
			}
		}
		List<Object> list = clBorrowService.repayLogPlanExport(params);
		for(int i=0;i<list.size();i++){
			LoanProduct p=(LoanProduct)list.get(i);
			p.setBalanceBD(p.getRepayTotal()!=null?new BigDecimal(p.getBalance()).divide(new BigDecimal(100),2,ROUND_UP):new BigDecimal(0));
			p.setOverdueFeeBD(p.getRepayTotal()!=null?new BigDecimal(p.getOverdueFee()).divide(new BigDecimal(100),2,ROUND_UP):new BigDecimal(0));
			p.setActualBalanceBD(p.getRepayTotal()!=null?new BigDecimal(p.getActualBalance()).divide(new BigDecimal(100),2,ROUND_UP):new BigDecimal(0));
			p.setActualbackAmtBD(p.getRepayTotal()!=null?new BigDecimal(p.getActualbackAmt()).divide(new BigDecimal(100),2,ROUND_UP):new BigDecimal(0));
			p.setRepayTotalBD(p.getRepayTotal()!=null?new BigDecimal(p.getRepayTotal()).divide(new BigDecimal(100),2,ROUND_UP):new BigDecimal(0));
			switch (p.getStatus()){
				case 5:
					p.setStatusStr("Unpaid");//未还款
					break;
				case 6:
					p.setStatusStr("Repaid");//已还款
					break;
				case 21:
					p.setStatusStr("Overdue");//已逾期
					break;
				case 22:
					p.setStatusStr("Overdue payment");//逾期还款
					break;
				case 51:
					p.setStatusStr("Bad debts");//坏账
					break;
			}
			list.set(i,p);
		}
		SysUser user = (SysUser) request.getSession().getAttribute("SysUser");
		response.setContentType("application/msexcel;charset=UTF-8");
		// 记录取得
		String title = "还款计划Excel表";
		String[] hearders =  ExportConstant.EXPORT_REPAYLOGPLAN_LIST_HEARDERS;
		String[] fields = ExportConstant.EXPORT_REPAYLOGPLAN_LIST_FIELDS;
		JsGridReportBase report = new JsGridReportBase(request, response);
		report.exportExcel(list,title,hearders,fields,user.getName());
		saveLog(list, user.getUserName(), SysDownloadLog.REPAYPLAN_LOG);
	}
	
	/**
	 * 导出借款订单报表
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/borrow/export.htm")
	public void borrowExport(
			@RequestParam(value="searchParams",required = false) String searchParams) throws Exception {
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		List list = clBorrowService.listBorrow(params);
		SysUser user = (SysUser) request.getSession().getAttribute("SysUser");
		response.setContentType("application/msexcel;charset=UTF-8");   
		// 记录取得
		String title = "借款订单Excel表";
		String[] hearders =  ExportConstant.EXPORT_BORROW_LIST_HEARDERS;
		String[] fields = ExportConstant.EXPORT_BORROW_LIST_FIELDS;
		JsGridReportBase report = new JsGridReportBase(request, response);
		report.exportExcel(list,title,hearders,fields,user.getName());
		saveLog(list, user.getUserName(), SysDownloadLog.LOAN_LOG);
	}
	
	/**
	 * 导出支付记录报表
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/payLog/export.htm")
	public void payLogExport(
			@RequestParam(value="searchParams",required = false) String searchParams) throws Exception {
		List list = payLogService.listPayLog(searchParams);
		SysUser user = (SysUser) request.getSession().getAttribute("SysUser");
		response.setContentType("application/msexcel;charset=UTF-8");
		// 记录取得
		String title = "支付记录Excel表";
		String[] hearders =  ExportConstant.EXPORT_PAYLOG_LIST_HEARDERS;
		String[] fields = ExportConstant.EXPORT_PAYLOG_LIST_FIELDS;
		JsGridReportBase report = new JsGridReportBase(request, response);
		report.exportExcel(list,title,hearders,fields,user.getName());
		saveLog(list, user.getUserName(), SysDownloadLog.PAY_LOG);
	}
	
	/**
	 * 导出支付对账记录报表
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/payCheck/export.htm")
	public void payCheckExport(
			@RequestParam(value="searchParams",required = false) String searchParams) throws Exception {
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		List list = payCheckService.listPayCheck(params);
		SysUser user = (SysUser) request.getSession().getAttribute("SysUser");
		response.setContentType("application/msexcel;charset=UTF-8");
		// 记录取得
		String title = "支付对账记录Excel表";
		String[] hearders =  ExportConstant.EXPORT_PAYCHECK_LIST_HEARDERS;
		String[] fields = ExportConstant.EXPORT_PAYCHECK_LIST_FIELDS;
		JsGridReportBase report = new JsGridReportBase(request, response);
		report.exportExcel(list,title,hearders,fields,user.getName());
		saveLog(list, user.getUserName(), SysDownloadLog.PAY_CHECK_LOG);
	}
	
	/**
	 * 导出已逾期订单报表
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/overdue/export.htm")
	public void overdueExport(
			@RequestParam(value="searchParams",required = false) String searchParams) throws Exception {
		Map<String, Object> params;
		if (StringUtil.isBlank(searchParams)) {
			params = new HashMap<>();
		}else {
			params = JsonUtil.parse(searchParams, Map.class);
		}
		params.put("state", BorrowModel.STATE_DELAY);
		List list = clBorrowService.listBorrow(params);
		SysUser user = (SysUser) request.getSession().getAttribute("SysUser");
		response.setContentType("application/msexcel;charset=UTF-8");
		// 记录取得
		String title = "已逾期订单Excel表";
		String[] hearders =  ExportConstant.EXPORT_OVERDUE_LIST_HEARDERS;
		String[] fields = ExportConstant.EXPORT_OVERDUE_LIST_FIELDS;
		JsGridReportBase report = new JsGridReportBase(request, response);
		report.exportExcel(list,title,hearders,fields,user.getName());
		saveLog(list, user.getUserName(), SysDownloadLog.EXPORT_LOG);
	}
	
	/**
	 * 导出已坏账订单报表
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/badDebt/export.htm")
	public void badDebtExport(
			@RequestParam(value="searchParams",required = false) String searchParams) throws Exception {
		Map<String, Object> params;
		if (StringUtil.isBlank(searchParams)) {
			params = new HashMap<>();
		}else {
			params = JsonUtil.parse(searchParams, Map.class);
		}
		params.put("state", BorrowModel.STATE_BAD);
		List list = clBorrowService.listBorrow(params);
		SysUser user = (SysUser) request.getSession().getAttribute("SysUser");
		response.setContentType("application/msexcel;charset=UTF-8");
		// 记录取得
		String title = "已坏账订单Excel表";
		String[] hearders =  ExportConstant.EXPORT_BADDEBT_LIST_HEARDERS;
		String[] fields = ExportConstant.EXPORT_BADDEBT_LIST_FIELDS;
		JsGridReportBase report = new JsGridReportBase(request, response);
		report.exportExcel(list,title,hearders,fields,user.getName());
		saveLog(list, user.getUserName(), SysDownloadLog.BADDEBT_LOG);
	}
	
	/**
	 * 导出催收订单报表
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/urgeRepayOrder/export.htm")
	public void urgeRepayOrderExport(
			@RequestParam(value="searchParams",required = false) String searchParams) throws Exception {
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		List list = urgeRepayOrderService.listUrgeRepayOrder(params);
		SysUser user = (SysUser) request.getSession().getAttribute("SysUser");
		response.setContentType("application/msexcel;charset=UTF-8");
		// 记录取得
		String title = "催收订单Excel表";
		String[] hearders =  ExportConstant.EXPORT_REPAYORDER_LIST_HEARDERS;
		String[] fields = ExportConstant.EXPORT_REPAYORDER_LIST_FIELDS;
		JsGridReportBase report = new JsGridReportBase(request, response);
		report.exportExcel(list,title,hearders,fields,user.getName());
		saveLog(list, user.getUserName(), SysDownloadLog.URGEREPAY_LOG);
	}
	
	/**
	 * 导出催收反馈报表
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/urgeLog/export.htm")
	public void urgeLogExport(
			@RequestParam(value="searchParams",required = false) String searchParams) throws Exception {
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		List list = urgeRepayOrderService.listUrgeLog(params);
		SysUser user = (SysUser) request.getSession().getAttribute("SysUser");
		response.setContentType("application/msexcel;charset=UTF-8");
		// 记录取得
		String title = "催收反馈Excel表";
		String[] hearders =  ExportConstant.EXPORT_URGELOG_LIST_HEARDERS;
		String[] fields = ExportConstant.EXPORT_URGELOG_LIST_FIELDS;
		JsGridReportBase report = new JsGridReportBase(request, response);
		report.exportExcel(list,title,hearders,fields,user.getName());
		saveLog(list, user.getUserName(), SysDownloadLog.URGE_FEEDBACK_LOG);
	}

	private void saveLog(List list, String userName, String menuName) {
		SysDownloadLog sysDownloadLog = new SysDownloadLog();
		sysDownloadLog.setCreate_time(DateUtil.getNow());
		if (list != null && !CollectionUtils.isEmpty(list)) {
			sysDownloadLog.setDownload_count(list.size());
		}
		sysDownloadLog.setUser_name(userName);
		sysDownloadLog.setDownload_menu(menuName);
		sysDownloadLogService.insert(sysDownloadLog);
	}
}
