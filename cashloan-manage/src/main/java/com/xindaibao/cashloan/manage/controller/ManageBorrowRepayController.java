package com.xindaibao.cashloan.manage.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xindaibao.cashloan.cl.mapper.KanyaUserStateMapper;
import com.xindaibao.cashloan.cl.model.kenya.KanyaUserState;
import com.xindaibao.cashloan.cl.model.kenya.LoanProduct;
import com.xindaibao.cashloan.cl.model.kenya.LoanRecord;
import com.xindaibao.cashloan.cl.service.ClBorrowService;
import com.xindaibao.cashloan.cl.service.KanyaUserStateService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import tool.util.DateUtil;
import tool.util.StringUtil;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.BorrowRepay;
import com.xindaibao.cashloan.cl.model.BorrowRepayModel;
import com.xindaibao.cashloan.cl.model.ManageBRepayModel;
import com.xindaibao.cashloan.cl.model.ManageBorrowModel;
import com.xindaibao.cashloan.cl.model.RepayExcelModel;
import com.xindaibao.cashloan.cl.service.BorrowRepayLogService;
import com.xindaibao.cashloan.cl.service.BorrowRepayService;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.exception.BussinessException;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.model.BorrowModel;
import com.xindaibao.cashloan.system.permission.annotation.RequiresPermission;

/**
 * 还款计划表和还款记录Controller
 */
@Controller
@Scope("prototype")
@SuppressWarnings({ "unchecked" })
public class ManageBorrowRepayController extends ManageBaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(ManageBorrowRepayController.class);
	@Resource
	private BorrowRepayService borrowRepayService;
	@Resource
	private BorrowRepayLogService borrowRepayLogService;
	@Resource
	private KanyaUserStateService kanyaUserStateService;
	@Resource
	private ClBorrowService clBorrowService;
	/**
	 * 还款计划列表
	 * 
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 */
	@RequestMapping(value = "/modules/manage/borrow/repay/list.htm")
	@RequiresPermission(code = "modules:manage:borrow:repay:list", name = "还款信息列表")
	public void list(
			@RequestParam(value = "searchParams", required = false) String searchParams,
			@RequestParam(value = "current") int currentPage,
			@RequestParam(value = "pageSize") int pageSize) {
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		//Page<ManageBRepayLogModel> page = borrowRepayLogService.listModel(params, currentPage, pageSize);
		String status = null;
		List stateList = new ArrayList();
		if(StringUtil.isNotBlank(params)) {
			if (StringUtil.isBlank(params.get("state"))) {
				status = BorrowModel.STATE_REPAYING;
				stateList.add(status);
			}
		}else {
			params = new HashMap<>();
			status = BorrowModel.STATE_REPAYING;
			stateList.add(status);
		}
		stateList.add(21);
		params.put("stateList",stateList);
		Page<LoanProduct> page = clBorrowService.listBorrowModel(params,currentPage,pageSize);
		//Page<ManageBRepayModel> page = borrowRepayService.listModel(params,currentPage, pageSize);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 还款计划列表(查)
	 *
	 * @param searchParams
	 * @param currentPage
	 * @param pageSize
	 */
	@RequestMapping(value = "/modules/manage/borrow/repay/listOnlySearch.htm")
	@RequiresPermission(code = "modules:manage:borrow:repay:listOnlySearch", name = "还款信息列表(查)")
	public void listOnlySearch(
			@RequestParam(value = "searchParams", required = false) String searchParams,
			@RequestParam(value = "current") int currentPage,
			@RequestParam(value = "pageSize") int pageSize) {
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		//Page<ManageBRepayLogModel> page = borrowRepayLogService.listModel(params, currentPage, pageSize);
		String status = null;
		List stateList = new ArrayList();
		if(StringUtil.isNotBlank(params)) {
			if (StringUtil.isBlank(params.get("state"))) {
				status = BorrowModel.STATE_REPAYING;
				stateList.add(status);
				params.put("stateList",stateList);
			}
		}else {
			params = new HashMap<>();
			status = BorrowModel.STATE_REPAYING;
			stateList.add(status);
			stateList.add(21);
			params.put("stateList",stateList);
		}
		Page<LoanProduct> page = clBorrowService.listBorrowModel(params,currentPage,pageSize);
		//Page<ManageBRepayModel> page = borrowRepayService.listModel(params,currentPage, pageSize);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 确认还款
	 * @param id  还款计划id
	 * @param amount  还款金额
	 * @param penaltyAmout   逾期罚息
	 * @param repayTime  还款时间
	 * @param repayWay   还款方式
	 * @param serialNumber 流水号
	 * @param repayAccount 还款账号
	 * @param state 正常还款  10  ，金额减免 20
	 * @throws  
	 */
	@RequestMapping(value = "/modules/manage/borrow/repay/confirmRepay.htm", method = {RequestMethod.POST })
	@RequiresPermission(code = "modules:manage:borrow:repay:confirmRepay", name = "确认还款")
	public void confirmRepay(
			@RequestParam(value = "id") Long id,
			@RequestParam(value = "amount", required = false) String amount,
			@RequestParam(value = "penaltyAmout", required = false) String penaltyAmout,
			@RequestParam(value = "repayTime") String repayTime,
			@RequestParam(value = "repayWay") String repayWay,
			@RequestParam(value = "serialNumber") String serialNumber,
			@RequestParam(value = "repayAccount") String repayAccount,
			@RequestParam(value = "state") String state) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		KanyaUserState kanyaUserState=new KanyaUserState();
		try{
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("id", id);
			param.put("repayTime",DateUtil.valueOf(repayTime, DateUtil.DATEFORMAT_STR_001));
			param.put("repayWay", repayWay);
			param.put("repayAccount", repayAccount);
			param.put("amount", amount);
			param.put("serialNumber", serialNumber);
			param.put("penaltyAmout", penaltyAmout);
			param.put("state", state);
			LoanRecord lr = clBorrowService.findByPrimaryforKenya(id);
			if (lr != null) {
				if (!lr.getStatus().equals(BorrowRepayModel.STATE_REPAY_YES)) {
					borrowRepayService.confirmRepay(param);
					kanyaUserState.setUid(lr.getUid());
					kanyaUserState.setCurrentState((byte)5);
					kanyaUserStateService.updateCurrentState(kanyaUserState);
					resultMap.put("Code", Constant.SUCCEED_CODE_VALUE);
					resultMap.put("Msg", "还款成功");
				} else {
					resultMap.put("Code", Constant.FAIL_CODE_VALUE);
					resultMap.put("Msg", "该还款计划已还款");
				}
			} else {
				resultMap.put("Code", Constant.FAIL_CODE_VALUE);
				resultMap.put("Msg", "还款计划不存在");
			}
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
			resultMap.put("Code", Constant.FAIL_CODE_VALUE);
			resultMap.put("Msg", "还款失败");
		}
		result.put(Constant.RESPONSE_CODE, resultMap.get("Code"));
		result.put(Constant.RESPONSE_CODE_MSG, resultMap.get("Msg"));
		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 后台催收管理列表
	 * 
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 */
	@RequestMapping(value = "/modules/manage/borrow/repayList.htm", method = {RequestMethod.POST, RequestMethod.GET })
	@RequiresPermission(code = "modules:manage:borrow:repayList.htm", name = "催收管理列表")
	public void repayList(
			@RequestParam(value = "searchParams", required = false) String searchParams,
			@RequestParam(value = "current") int currentPage,
			@RequestParam(value = "pageSize") int pageSize) {
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		if (params == null) {
			params = new HashMap<String, Object>();
			List<String> stateList = Arrays.asList(BorrowModel.STATE_DELAY,
					BorrowModel.STATE_BAD, BorrowModel.STATE_REPAY); // 未收款的借款
			params.put("stateList", stateList);
			params.put("state", BorrowModel.STATE_REPAY);
		} else {
			String state = StringUtil.isNull(params.get("state"));
			if (null == state || StringUtil.isBlank(state)) {
				List<String> stateList = Arrays.asList(BorrowModel.STATE_DELAY,
						BorrowModel.STATE_BAD, BorrowModel.STATE_REPAY); // 未收款的借款
				params.put("stateList", stateList);
				params.put("state", BorrowModel.STATE_REPAY);
			} else {
				List<String> stateList = Arrays.asList(state); // 未收款的借款
				params.put("stateList", stateList);
				params.put("state", state);
			}
		}
		Page<ManageBorrowModel> page = borrowRepayService.listRepayModel(
				params, currentPage, pageSize);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response, result);
	}
	/**
	 * 文件上传批量还款
	 * @param repayFile
	 * @param type
	 */
	@RequestMapping(value = "/modules/manage/borrow/repay/fileBatchRepay.htm", method = {RequestMethod.POST })
	@RequiresPermission(code = "modules:manage:borrow:repay:fileBatchRepay", name = "文件上传批量还款")
	public void fileBatchRepay(
			@RequestParam(value = "repayFile") MultipartFile repayFile,
			@RequestParam(value = "type") String type) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<List<String>> list=new ArrayList<List<String>>();
	    try {
	    	list=borrowRepayService.fileBatchRepay(repayFile,type);
	    	String title = "批量还款匹配结果";
	    	RepayExcelModel report = new RepayExcelModel();
	    	String fileName=report.saveExcelByList(list, title, repayFile.getOriginalFilename(),request);
	    	result.put(Constant.RESPONSE_DATA, "/modules/manage/borrow/repay/downRepayByFile.htm?path="+fileName);
	    	result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		} catch (BussinessException e) {
			logger.error(e.getMessage(),e);
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "批量还款失败");
		}
	    ServletUtils.writeToResponse(response, result);
	    
	}
	/**
	 * 下载批量确认还款模板
	 * @param repayFile
	 * @param type
	 */
	@RequestMapping(value = "/modules/manage/borrow/repay/downRepayByFile.htm", method = {RequestMethod.GET,RequestMethod.POST})
	@RequiresPermission(code = "modules:manage:borrow:repay:downRepayByFile", name = "下载批量确认还款模板")
	public void fileBatchRepay(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		response.setCharacterEncoding("UTF-8");
        String fileBatchRepay = "批量确认还款模板.rar";
        InputStream input = null;
		
		try {
			request.setCharacterEncoding("UTF-8");
			String url = session.getServletContext().getRealPath("/")+"/excel/批量确认还款模板.rar";
			File file = new File(url);
			input = FileUtils.openInputStream(file);
            byte[] data = IOUtils.toByteArray(input);
            response.reset();
            response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode(fileBatchRepay, "UTF-8"));
            response.addHeader("Content-Length", "" + data.length);
            
            response.setContentType("text/html;charset=UTF-8");
            IOUtils.write(data, response.getOutputStream());
		} catch(Exception e){
			logger.error(e.getMessage(),e);
		}
	}
	
}
