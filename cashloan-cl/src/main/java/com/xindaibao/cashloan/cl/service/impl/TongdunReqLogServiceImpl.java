package com.xindaibao.cashloan.cl.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.cl.domain.*;
import com.xindaibao.cashloan.cl.mapper.*;
import com.xindaibao.cashloan.cl.model.TongdunReqLogModel;
import com.xindaibao.cashloan.cl.model.tongdun.PreloanApi;
import com.xindaibao.cashloan.cl.model.tongdun.model.PreloanApplyModel;
import com.xindaibao.cashloan.cl.model.tongdun.sdk.PreloanReportResponse;
import com.xindaibao.cashloan.cl.monitor.BusinessExceptionMonitor;
import com.xindaibao.cashloan.cl.service.TongdunParseService;
import com.xindaibao.cashloan.cl.service.TongdunReqLogService;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.exception.BaseRuntimeException;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.common.util.DateUtil;
import com.xindaibao.cashloan.core.common.util.OrderNoUtil;
import com.xindaibao.cashloan.core.common.util.StringUtil;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.domain.UserOtherInfo;
import com.xindaibao.cashloan.core.mapper.UserBaseInfoMapper;
import com.xindaibao.cashloan.core.mapper.UserOtherInfoMapper;
import com.xindaibao.cashloan.rc.domain.TppBusiness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Service("tongdunReqLogService")
@SuppressWarnings("unchecked")
public class TongdunReqLogServiceImpl extends
		BaseServiceImpl<TongdunReqLog, Long> implements TongdunReqLogService {

	private static final Logger logger = LoggerFactory.getLogger(TongdunReqLogServiceImpl.class);

	@Resource
	private TongdunReqLogMapper tongdunReqLogMapper;
	@Resource
	private UserEmerContactsMapper userEmerContactsMapper;
	@Resource
	private UserBaseInfoMapper userBaseInfoMapper;
	@Resource
	private BankCardMapper bankCardMapper;
	@Resource
	private UserAuthMapper userAuthMapper;
	@Resource
	private UserOtherInfoMapper userOtherInfoMapper;
	@Resource
	private UserEquipmentInfoMapper userEquipmentInfoMapper;
	@Resource
	private TongdunRespDetailMapper tongdunRespDetailMapper;
	@Resource
	private ClFraudTdBasicMapper clFraudTdBasicMapper;
	@Autowired
    private TongdunParseService tongdunParseService;


	@Override
	public BaseMapper<TongdunReqLog, Long> getMapper() {
		return tongdunReqLogMapper;
	}

	@Override
	public int preloanApplyRequest(Long userId, Borrow borrow,TppBusiness bussiness,String mobileType){
		int m=0;
		// 组装请求参数
		PreloanApplyModel model = initModel(userId,borrow);
		TongdunReqLog log = new TongdunReqLog();
		log.setOrderNo(OrderNoUtil.getSerialNumber());
		log.setBorrowId(borrow.getId());
		log.setUserId(userId);
		log.setCreateTime(new Date());
		
		Map<String, Object> payload = new HashMap<String, Object>();
		payload.put("body", JSON.toJSONString(model));


		boolean testState=false;//默认正式环境地址
		if(Global.getValue("tongdun_url_state")!=null){
			 testState="0".equals(Global.getValue("tongdun_url_state"));
		}

        PreloanApi api = new PreloanApi();
		String result = api.preloan(payload, bussiness, testState, mobileType);

		logger.info("借款borrowId："+borrow.getId()+"，同盾贷前审核，同步响应结果："+result);
		if (!StringUtil.isBlank(result)) {
			Map<String, Object> resultMap = JSONObject.parseObject(result,Map.class);
			log.setSubmitCode(String.valueOf(resultMap.get("code")));
			log.setSubmitParams(result);
			if ("200".equals(String.valueOf(resultMap.get("code")))) {
				log.setState("20");
				JSONObject data = (JSONObject) resultMap.get("data");
				
				JSONObject data_data = JSONObject.parseObject(data.getString("data"));
				
				String reportId = String.valueOf(data.get("report_id"));
				log.setReportId(reportId);
				log.setRsScore(data_data.getInteger("final_score"));
				log.setRsState(PreloanApplyModel.getMessage(data_data.getString("final_decision")));
				//FIXME:同盾解析
				try {
					String temp =  JSONObject.toJSONString(data_data);
                    save(temp, reportId, userId);
				}catch (Exception e){
					logger.error("同盾解析失败.",e);
				}


				tongdunReqLogMapper.save(log);
				//查询审核报告结果
//				final TppBusiness b = tppBusinessMapper.findByNid("TongdunPreloan", bussiness.getTppId());
//				final String report_id = log.getReportId();
				m= 1;//reportTask(report_id,b,mobileType);
			}else{
				Object msg = resultMap.get("message");
				String message = "";
				if (StringUtil.isBlank(msg)) {
					message = "查询接口异常";
				} else {
					message = StringUtil.isNull(msg);
				} 
				// 加入异常信息
				BusinessExceptionMonitor.add(BusinessExceptionMonitor.TYPE_10, log.getOrderNo(), message);
				
				log.setState("10");
				log.setRsScore(0);
			    log.setRsState(message);
				tongdunReqLogMapper.save(log);
			}
		}else{
			 log.setState("10");
			 log.setRsScore(0);
			 log.setRsState("查询接口异常"); 
			 tongdunReqLogMapper.save(log);	
		}
		return m;
	}

	private void save(String temp, String reportId, Long userId) {
		ClFraudTdBasic clFraudTdBasic = JSONObject.parseObject(temp, ClFraudTdBasic.class);
		clFraudTdBasic.setReport_id(reportId);
		clFraudTdBasic.setUser_id(userId);
		clFraudTdBasicMapper.save(clFraudTdBasic);

        tongdunParseService.parse(temp, reportId, userId);
	}

	@Override
	public String preloanReportRequest(String report_id,TppBusiness bussiness,String mobileType) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("report_id", report_id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("body", JSON.toJSONString(model));
	 
			PreloanApi api = new PreloanApi();
			boolean testState = false;//默认正式环境地址
			if(Global.getValue("tongdun_url_state")!=null){
				 testState="0".equals(Global.getValue("tongdun_url_state"));
			}
			PreloanReportResponse response= api.preloanReport(map,bussiness,testState,mobileType);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("reportId", report_id);
			TongdunReqLog log = tongdunReqLogMapper.findSelective(paramMap);
			
			if (response.getSuccess()) {
				String result = response.postResponseToJsonStr();
				int m = saveReport(result, report_id);
				logger.info(result);
				return m == 1 ? "保存成功" : "保存失败";
			} else {
				JSONObject result = response.getData();
				logger.info(result.getString("reason_desc"));
				log.setUpdateTime(new Date());
				log.setQueryCode(String.valueOf(result.get("reason_code")));
				log.setState("30");
				int m=tongdunReqLogMapper.update(log);
				if(m>0){
					TongdunRespDetail detail=new TongdunRespDetail();
					detail.setReqId(log.getId());
					detail.setOrderNo(log.getOrderNo());
					detail.setReportId(report_id);
					detail.setQueryParams(result.toJSONString());
					tongdunRespDetailMapper.save(detail);
				}
				return result.getString("reason_desc");
			}
		 
	}

	/**
	 * 轮询查询审核报告的结果
	 * @return
	 * @throws Exception
	 */
	public int reportTask(String report_id,TppBusiness bussiness,String mobileType){
		 int m = 0;
		 String result = task(report_id,bussiness,mobileType);
		 if (!StringUtil.isBlank(result)) {
			m = saveReport(result, report_id);
		 }else{
			 logger.debug("没有查询出同盾审核报告结果"); 
		 }
		return m;
	}

	public String task(final String reportId,final TppBusiness bussiness,final String mobileType) throws BaseRuntimeException  {
		String report = "";
		/* 轮询调用preloanReport接口，直到获取到结果信息 */
		final Map<String, Object> model = new HashMap<String, Object>();
		model.put("report_id", reportId);
		FutureTask<String> task = new FutureTask<>(new Callable<String>() {
			@Override
			public String call() throws Exception {
				while (true) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("body", JSON.toJSONString(model));
					PreloanApi api = new PreloanApi();
					boolean testState="0".equals(String.valueOf(Global.getValue("tongdun_url_state")));
					PreloanReportResponse response= api.preloanReport(map,bussiness,testState,mobileType);
					if (response.getSuccess()) {
						String result = response.postResponseToJsonStr();
						return result;
					} else {
						String reasonCode = response.getData().getString("reason_code");
						if (!"204".equals(reasonCode)) {/*204代表订单还未生成，需要继续轮询，其他状态说明查询已经失败 */
							logger.error("code:"+reasonCode+",desc:"+response.getData().getString("reason_desc"));
							throw new BaseRuntimeException("{'code':'"+reasonCode+"','message':'",response.getData().getString("reason_desc")+"'");
						}
					}
				}
			}
		});
		new Thread(task).start();

		while (!task.isDone()) {
			try {
				logger.info("查询同盾审核报告开始...");
				Thread.sleep(30000); // 单位毫秒  暂停30秒再执行
			} catch (InterruptedException e) {
				//logger.error("同盾审核报告查询异常或超时..."+reportId,e);
			}
		}
		try {
			report = task.get(300, TimeUnit.SECONDS);//300秒内如果没有结果返回继续轮询
			logger.info("查询同盾审核报告结束...");
		} catch (BaseRuntimeException e) {
			logger.error("同盾返回..."+e.getMessage());
			report=e.getMessage();
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			logger.error("同盾审核报告查询异常或超时..."+reportId,e.getMessage());
			report="{'code':'404','message':'查询异常或超时'}";
		} finally {
			task.cancel(true);
		}

		return report;
	}
	/**
	 * 更新同盾第三方请求记录
	 * 更新征信管理信息以及审核结果
	 * @param result
	 * @param report_id
	 * @return
	 */
	public int saveReport(String result,String report_id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("reportId", report_id);
		TongdunReqLog log = tongdunReqLogMapper.findSelective(paramMap);
		Map<String, Object> resultMap = JSONObject.parseObject(result,Map.class);
		log.setUpdateTime(new Date());
		log.setQueryCode(String.valueOf(resultMap.get("code")));
		if(log.getQueryCode().equals("200")){
			log.setState("20");
			JSONObject data = (JSONObject) resultMap.get("data");
			log.setRsScore(Integer.valueOf(String.valueOf(data.get("final_score"))));
			log.setRsState(PreloanApplyModel.getMessage(String.valueOf(data.get("final_decision"))));
		}else{
			log.setState("30");
			log.setRsScore(0);
			log.setRsState("建议审核");
		}
		//更新同盾第三方请求记录
		int m = tongdunReqLogMapper.update(log);
		if(m>0){
			TongdunRespDetail detail=new TongdunRespDetail();
			detail.setReqId(log.getId());
			detail.setOrderNo(log.getOrderNo());
			detail.setReportId(report_id);
			detail.setQueryParams(result);
			tongdunRespDetailMapper.save(detail);
		}
	    return m;
	}
 
	/**
	 * 拼装同盾审核请求信息
	 * @param userId
	 * @param borrow
	 * @return
	 */
	public  PreloanApplyModel   initModel(Long userId, Borrow borrow){
		PreloanApplyModel model = new PreloanApplyModel();
		//用户基本信息
		UserBaseInfo info = userBaseInfoMapper.findByUserId(userId);
		model.setAccount_name(info.getRealName());
		model.setAccount_mobile(info.getPhone());
		model.setId_number(info.getIdNo());
		model.setWork_phone(info.getCompanyPhone());
		model.setWork_time(info.getWorkingYears());
		model.setCompany_name(info.getCompanyName());
		model.setCompany_address(info.getCompanyAddr());
		model.setAnnual_income(info.getSalary());
		model.setDiploma(info.getEducation());
		model.setMarriage(info.getMarryState());
		model.setRegistered_address(info.getIdAddr());
		model.setHome_address(info.getLiveAddr());
		
		//银行卡信息
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		BankCard card = bankCardMapper.findSelective(paramMap);
		if (card != null) {
			model.setCard_number(card.getCardNo());
		}
	 
		//其他信息
		UserOtherInfo otherInfo = userOtherInfoMapper.findSelective(paramMap);
		if(otherInfo!=null){
			model.setQq(otherInfo.getQq());
			model.setEmail(otherInfo.getEmail());
		}
		//借款信息
		model.setLoan_amount(borrow.getAmount());
		model.setLoan_term(Integer.valueOf(borrow.getTimeLimit()));
		model.setLoan_term_unit("DAY");
		model.setLoan_date(DateUtil.dateStr(borrow.getCreateTime(),DateUtil.DATEFORMAT_STR_002));
		model.setIp_address(borrow.getIp());
		//是否实名认证
		UserAuth auth = userAuthMapper.findSelective(paramMap);
		if(auth!=null){
		  model.setIs_id_checked("30".equals(auth.getIdState()));
		}
		//联系人信息
		List<UserEmerContacts> contacts = userEmerContactsMapper.listSelective(paramMap);
		if (contacts != null) {
			for(int i=0;i<contacts.size();i++){
				UserEmerContacts c=contacts.get(i);
				if(i==0){
					 model.setContact1_name(c.getName());
					 model.setContact1_mobile(c.getPhone());
					 model.setContact1_relation(c.getRelation());
				}else if(i==1){
					 model.setContact2_name(c.getName());
					 model.setContact2_mobile(c.getPhone());
					 model.setContact2_relation(c.getRelation());
				}else if(i==2){
					 model.setContact3_name(c.getName());
					 model.setContact3_mobile(c.getPhone());
					 model.setContact3_relation(c.getRelation());
				}else if(i==3){
					 model.setContact4_name(c.getName());
					 model.setContact4_mobile(c.getPhone());
					 model.setContact4_relation(c.getRelation());
				}else if(i==4){
					 model.setContact5_name(c.getName());
					 model.setContact5_mobile(c.getPhone());
					 model.setContact5_relation(c.getRelation());
			    }
			}
		}
		//设备指纹
		UserEquipmentInfo equipmentInfo =userEquipmentInfoMapper.findSelective(paramMap);
		if(equipmentInfo!=null){
			model.setBlack_box(equipmentInfo.getBlackBox());
			
		}
		
		return model;
	}

	@Override
	public Page<TongdunReqLogModel> pageListModel(Map<String, Object> params,
                                                  int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<TongdunReqLogModel> list = tongdunReqLogMapper.listModelByMap(params);
		return (Page<TongdunReqLogModel>) list;
	}

	@Override
	public TongdunReqLogModel getModelById(long id) {
		TongdunReqLogModel model=tongdunReqLogMapper.findModelById(id);
		return model;
	}

	@Override
	public List<TongdunReqLogModel> listByMap(Map<String, Object> params) {
		List<TongdunReqLogModel> list = tongdunReqLogMapper.listModelByMap(params);
		return  list;
	}


}