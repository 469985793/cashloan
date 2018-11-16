package com.xindaibao.cashloan.cl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xindaibao.cashloan.cl.domain.ClMoheData;
import com.xindaibao.cashloan.cl.domain.ClMoheReportInfo;
import com.xindaibao.cashloan.cl.domain.UserEmerContacts;
import com.xindaibao.cashloan.cl.mapper.*;
import com.xindaibao.cashloan.cl.model.MoheFormModel;
import com.xindaibao.cashloan.cl.model.mohe.MoheParamHolder;
import com.xindaibao.cashloan.cl.sdk.utils.GZIPUtil;
import com.xindaibao.cashloan.cl.service.ClMoheReportInfoService;
import com.xindaibao.cashloan.cl.service.ClMoheRiskContactStatsService;
import com.xindaibao.cashloan.cl.service.UserEmerContactsService;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.common.util.BeanUtils;
import com.xindaibao.cashloan.core.common.util.HttpUtil;
import com.xindaibao.cashloan.core.domain.UserOtherInfo;
import com.xindaibao.cashloan.core.service.UserOtherInfoService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Service("clMoheReportInfoStatsService")
public class ClMoheReportInfoServiceImpl extends BaseServiceImpl<ClMoheReportInfo, Long>
		implements ClMoheReportInfoService {
	private static final Logger log = LoggerFactory.getLogger(ClMoheReportInfoServiceImpl.class);
//	private static final String PARTNER_CODE = "mohedemo";
//	private static final String PARTNER_KEY = "2c024e1c51bc4a2cba9c3c27d03acd09";
//	private static final String MOHE_URL = "https://api.shujumohe.com/octopus/report.task.query/v2?partner_code="
//			+ PARTNER_CODE + "&partner_key=" + PARTNER_KEY;

	@Autowired
	private ClMoheReportInfoMapper clMoheReportInfoMapper;

	@Autowired
	private ClMoheUserInfoMapper clMoheUserInfoMapper;
	@Autowired
	private ClMoheWorkTelDetailMapper clMoheWorkTelDetailMapper;
	@Autowired
	private ClMoheActiveSilenceStatsMapper clMoheActiveSilenceStatsMapper;
	@Autowired
	private ClMoheAllContactStatsMapper clMoheAllContactStatsMapper;
	@Autowired
	private ClMoheBehaviorAnalysisMapper clMoheBehaviorAnalysisMapper;

	@Autowired
	private ClMoheCallDurationStats2hourMapper clMoheCallDurationStats2hourMapper;
	@Autowired
	private ClMoheCarrierConsumptionStatsMapper clMoheCarrierConsumptionStatsMapper;

	@Autowired
	private ClMoheDataCompletenessMapper clMoheDataCompletenessMapper;
	@Autowired
	private ClMoheEmergencyContact1DetailMapper clMoheEmergencyContact1DetailMapper;
	@Autowired
	private ClMoheEmergencyContact2DetailMapper clMoheEmergencyContact2DetailMapper;
	@Autowired
	private ClMoheEmergencyContact3DetailMapper clMoheEmergencyContact3DetailMapper;
	@Autowired
	private ClMoheEmergencyContact4DetailMapper clMoheEmergencyContact4DetailMapper;
	@Autowired
	private ClMoheEmergencyContact5DetailMapper clMoheEmergencyContact5DetailMapper;

	@Autowired
	private ClMoheHomeTelDetailMapper clMoheHomeTelDetailMapper;
	@Autowired
	private ClMoheInfoCheckMapper clMoheInfoCheckMapper;
	@Autowired
	private ClMoheInfoMatchMapper clMoheInfoMatchMapper;
	@Autowired
	private ClMoheMobileInfoMapper clMoheMobileInfoMapper;

	@Autowired
	private ClMoheAllContactDetailMapper clMoheAllContactDetailMapper;
	@Autowired
	private ClMoheAllContactStatsPerMonthMapper clMoheAllContactStatsPerMonthMapper;
	@Autowired
	private ClMoheRiskContactDetailMapper clMoheRiskContactDetailMapper;
	@Autowired
	private ClMoheRiskContactStatsMapper clMoheRiskContactStatsMapper;
	@Autowired
	private ClMoheTravelTrackAnalysisPerCityMapper clMoheTravelTrackAnalysisPerCityMapper;
	@Autowired
	private ClMoheFinanceContactStatsMapper clMoheFinanceContactStatsMapper;
	@Autowired
	private ClMoheFinanceContactDetailMapper clMoheFinanceContactDetailMapper;
	@Autowired
	private ClMoheCarrierConsumptionStatsPerMonthMapper clMoheCarrierConsumptionStatsPerMonthMapper;
	@Autowired
	private ClMoheContactAreaStatsPerCityMapper clMoheContactAreaStatsPerCityMapper;
	@Autowired
	private ClMoheCallAreaStatsPerCityMapper clMoheCallAreaStatsPerCityMapper;

	private Map<String, BaseMapper> mapperMap = Maps.newHashMap();
	//private Map<String, BaseMapper> mapperMap = new HashMap<>();

	@Autowired
    private ClMoheRiskContactStatsService clMoheRiskContactStatsService;
	@Autowired
    private UserEmerContactsService userEmerContactsService;
	@Autowired
    private UserOtherInfoService userOtherInfoService;

    @Override
	public BaseMapper<ClMoheReportInfo, Long> getMapper() {
		return clMoheReportInfoMapper;
	}

	/**
	 * 1. call api, 2. parse JSON; 3. set taskId and userId into Obj 4. put data
	 * into DB
	 */
	@Override
	public void runMohe(MoheParamHolder moheParamHolder) {
		Long userId = moheParamHolder.getUserId();
		if (userId == null) {
			log.info("同盾魔盒, userId is null, return");
			return;
		}
		String taskId = moheParamHolder.getTask_id();
		if (moheParamHolder == null || StringUtils.isEmpty(taskId)) {
			log.info("同盾魔盒, task_id is null, return. userId: {}", userId);
			return;
		}

		try {
			Thread.sleep(10000); // 10s
		} catch (InterruptedException e) {
			log.error("Thread sleep error", e);
		}

		String resp = callMoheAPI(moheParamHolder);

		processResponseData(userId, taskId, resp);

		log.info("魔盒 thread - end");
	}

	private String callMoheAPI(MoheParamHolder moheParamHolder) {
        // FIXME: 13/11/2017 append user emer info
        try {
            Long userId = moheParamHolder.getUserId();
            List<UserEmerContacts> userEmerContactsList = userEmerContactsService.getUserEmerContactsByUserId(userId);
            UserOtherInfo userOtherInfo = userOtherInfoService.getInfoByUserId(userId);
            moheParamHolder.append(userEmerContactsList);
            moheParamHolder.append(userOtherInfo);
        } catch (Exception e) {
            log.error("魔盒 append user emer info Exception! userId: {}", moheParamHolder.getUserId(), e);
        }

        Map<String, String> param = MoheFormModel.of(moheParamHolder).toMap();
		if (param == null || param.size() == 0) {
			log.warn("同盾魔盒, 参数为空, 不调用接口, 返回.");
		}
		 final String PARTNER_CODE = Global.getValue("tongdun_operator_partner_code");
		 final String PARTNER_KEY = Global.getValue("tongdun_operator_partner_key");
		 final String MOHE_URL = "https://api.shujumohe.com/octopus/report.task.query/v2?partner_code="
				+ PARTNER_CODE + "&partner_key=" + PARTNER_KEY;
		// 1. call API
		//return HttpsUtil.postClient(MOHE_URL, param);
        log.info("********同盾魔盒 call api ****** " + moheParamHolder);
		return HttpUtil.postClientContentType(MOHE_URL, param).toString();
	}

	@Override
	public void processResponseData(Long userId, String taskId, String resp) {
		if (StringUtils.isEmpty(resp)) {
			log.error("无返回JSON数据, 返回");
			return;
		}

        // FIXME: 08/11/2017 put the raw JSON into DB like MongoDB
        //log.info("魔盒 API RETURN JSON: {}", resp);
        log.info("魔盒 CALL API RETURN Success. UserId: " + userId + ", taskId:" + taskId);

		// 2. parse data ...
		JSONObject respJson = JSONObject.parseObject(resp);
		
		String code = respJson.getString("code");
		if (!"0".equals(code)) {
			log.error("同盾魔盒, 接口返回不成功. 内容: {}", resp);
			return;
		}
		log.info("同盾魔盒, 接口返回: ", resp);

		String data = respJson.getString("data");
		String temp = GZIPUtil.gunzip(data);

        log.info("魔盒 API RETURN JSON - data: {}", temp);
		ClMoheData clMoheData = JSONObject.parseObject(temp, ClMoheData.class);
		if (clMoheData == null) {
			log.error("Parse JSON to ClMoheData error...");
			return;
		}
		clMoheData.setCode(respJson.getString("code"));
		clMoheData.setMsg(respJson.getString("msg"));

		// 3. set taskId and userId
		setTaskIdAndUserId(clMoheData, taskId, userId);

		// 4. populate into DB
		populate(clMoheData);

		// 5. 订制存储 风险联系人统计risk_contact_stats 根据类型分表存储
        clMoheRiskContactStatsService.saveStatsByRiskType(clMoheData);
	}

	private static void setTaskIdAndUserId(ClMoheData clMoheData, String taskId, Long userId) {
		if (clMoheData == null || StringUtils.isEmpty(taskId)) {
			log.error("error...");
			return;
		}

		List<Method> getterList = getGetterList(ClMoheData.class);
		for (Method getter : getterList) {
			Class returnClass = getter.getReturnType();
			if (returnClass == String.class) {
				continue;
			}

			// get the object
			Object getedObj = ReflectionUtils.invokeMethod(getter, clMoheData);
			if (getedObj == null) {
				continue;
			}

			if (returnClass == List.class) { // list
				try {
					Method iteratorMethod = ReflectionUtils.findMethod(List.class, "iterator");

					Iterator it = (Iterator) ReflectionUtils.invokeMethod(iteratorMethod, getedObj);
					while (it.hasNext()) {
						Object oneObj = it.next();

						BeanUtils.setProperty(oneObj, "taskId", taskId);
						BeanUtils.setProperty(oneObj, "userId", userId);
					}
				} catch (Exception e) {
					log.error("Set taskId of List error... {} ", getedObj, e);
					continue;
				}

			} else { // common object
				try {
					BeanUtils.setProperty(getedObj, "taskId", taskId);
					BeanUtils.setProperty(getedObj, "userId", userId);
				} catch (Exception e) {
					log.error("Set taskId error... {} ", getedObj, e);
					continue;
				}
			}
		}

	}

	private static List<Method> getGetterList(Class clazz) {
		Method[] methods = ReflectionUtils.getAllDeclaredMethods(clazz);
		List<Method> getterList = Lists.newArrayList();
		for (Method method : methods) {
			if (StringUtils.startsWith(method.getName(), "get")) {
				getterList.add(method);
			}
		}
		return getterList;
	}

	private void populate(ClMoheData clMoheData) {
		if (clMoheData == null) {
			log.error("clMoheData is null ...");
			return;
		}
		// fixme: put raw JSON

		// pub one by one
		// FIXME: 24/10/2017
		List<Method> getterList = getGetterList(ClMoheData.class); // 得到所有的getter方法
		for (Method getter : getterList) {
			Class returnClass = getter.getReturnType(); // ClMoheReportInfo
			if (returnClass == String.class || returnClass == Class.class) {
				continue;
			}

			// get the object
			Object getedObj = ReflectionUtils.invokeMethod(getter, clMoheData);
			if (getedObj == null) {
				continue;
			}

			if (returnClass == List.class) { // list
				try {
					Method iteratorMethod = ReflectionUtils.findMethod(List.class, "iterator");

					Iterator it = (Iterator) ReflectionUtils.invokeMethod(iteratorMethod, getedObj);
					while (it.hasNext()) {
						Object oneObj = it.next();

						String beanObjectName = oneObj.getClass().getSimpleName();
						BaseMapper mapper = mapperMap.get(beanObjectName);
						if (mapper != null) {
							mapper.save(oneObj);
						}
					}
				} catch (Exception e) {
					log.error("save obj save error... {} ", getedObj, e);
					continue;
				}

			} else { // common object
				try {
					String beanObjectName = returnClass.getSimpleName();
					BaseMapper mapper = mapperMap.get(beanObjectName);
					if (mapper != null) {
						mapper.save(getedObj);
					}
				} catch (Exception e) {
					log.error("\n\t Save object error... {} ", getedObj, e);
					continue;
				}
			}
		}

	}

	@PostConstruct
	private void initMappers() {
		log.info("init mappers......");

		mapperMap.put(extractBeanNameFromMapper(clMoheReportInfoMapper.getClass()), clMoheReportInfoMapper);
		mapperMap.put(extractBeanNameFromMapper(clMoheUserInfoMapper.getClass()), clMoheUserInfoMapper);
		mapperMap.put(extractBeanNameFromMapper(clMoheWorkTelDetailMapper.getClass()), clMoheWorkTelDetailMapper);
		mapperMap.put(extractBeanNameFromMapper(clMoheActiveSilenceStatsMapper.getClass()),
				clMoheActiveSilenceStatsMapper);
		mapperMap.put(extractBeanNameFromMapper(clMoheAllContactStatsMapper.getClass()), clMoheAllContactStatsMapper);
		mapperMap.put(extractBeanNameFromMapper(clMoheBehaviorAnalysisMapper.getClass()), clMoheBehaviorAnalysisMapper);

		mapperMap.put(extractBeanNameFromMapper(clMoheCallDurationStats2hourMapper.getClass()),
				clMoheCallDurationStats2hourMapper);
		mapperMap.put(extractBeanNameFromMapper(clMoheCarrierConsumptionStatsMapper.getClass()),
				clMoheCarrierConsumptionStatsMapper);

		mapperMap.put(extractBeanNameFromMapper(clMoheDataCompletenessMapper.getClass()), clMoheDataCompletenessMapper);
		mapperMap.put(extractBeanNameFromMapper(clMoheEmergencyContact1DetailMapper.getClass()),
				clMoheEmergencyContact1DetailMapper);
		mapperMap.put(extractBeanNameFromMapper(clMoheEmergencyContact2DetailMapper.getClass()),
				clMoheEmergencyContact2DetailMapper);
		mapperMap.put(extractBeanNameFromMapper(clMoheEmergencyContact3DetailMapper.getClass()),
				clMoheEmergencyContact3DetailMapper);
		mapperMap.put(extractBeanNameFromMapper(clMoheEmergencyContact4DetailMapper.getClass()),
				clMoheEmergencyContact4DetailMapper);
		mapperMap.put(extractBeanNameFromMapper(clMoheEmergencyContact5DetailMapper.getClass()),
				clMoheEmergencyContact5DetailMapper);

		mapperMap.put(extractBeanNameFromMapper(clMoheHomeTelDetailMapper.getClass()), clMoheHomeTelDetailMapper);
		mapperMap.put(extractBeanNameFromMapper(clMoheInfoCheckMapper.getClass()), clMoheInfoCheckMapper);
		mapperMap.put(extractBeanNameFromMapper(clMoheInfoMatchMapper.getClass()), clMoheInfoMatchMapper);
		mapperMap.put(extractBeanNameFromMapper(clMoheMobileInfoMapper.getClass()), clMoheMobileInfoMapper);
		mapperMap.put(extractBeanNameFromMapper(clMoheAllContactDetailMapper.getClass()), clMoheAllContactDetailMapper);
		mapperMap.put(extractBeanNameFromMapper(clMoheAllContactStatsPerMonthMapper.getClass()),
				clMoheAllContactStatsPerMonthMapper);
		mapperMap.put(extractBeanNameFromMapper(clMoheRiskContactDetailMapper.getClass()),
				clMoheRiskContactDetailMapper);
		mapperMap.put(extractBeanNameFromMapper(clMoheRiskContactStatsMapper.getClass()), clMoheRiskContactStatsMapper);
		mapperMap.put(extractBeanNameFromMapper(clMoheTravelTrackAnalysisPerCityMapper.getClass()),
				clMoheTravelTrackAnalysisPerCityMapper);
		mapperMap.put(extractBeanNameFromMapper(clMoheFinanceContactStatsMapper.getClass()),
				clMoheFinanceContactStatsMapper);
		mapperMap.put(extractBeanNameFromMapper(clMoheFinanceContactDetailMapper.getClass()),
				clMoheFinanceContactDetailMapper);
		mapperMap.put(extractBeanNameFromMapper(clMoheCarrierConsumptionStatsPerMonthMapper.getClass()),
				clMoheCarrierConsumptionStatsPerMonthMapper);
		mapperMap.put(extractBeanNameFromMapper(clMoheContactAreaStatsPerCityMapper.getClass()),
				clMoheContactAreaStatsPerCityMapper);
		mapperMap.put(extractBeanNameFromMapper(clMoheCallAreaStatsPerCityMapper.getClass()),
				clMoheCallAreaStatsPerCityMapper);
	}

	/**
	 * 得到 mapper 类对应的 bean 类
	 * 
	 * @param mapperClass
	 *            ClMoheActiveSilenceStatsMapper.class
	 * @return com.xindaibao.cashloan.cl.domain.ClMoheActiveSilenceStats
	 */
	private static String extractBeanNameFromMapper(Class mapperClass) {
		Type[] types = mapperClass.getGenericInterfaces();
		String typeName = types[0].toString(); // interface
												// com.xindaibao.cashloan.cl.mapper.ClMoheReportInfoMapper

		String simpleClassName = "";
		if (StringUtils.contains(typeName, "<")) {
			simpleClassName = StringUtils.substringBetween(typeName, "<", ",");
		} else {
			simpleClassName = StringUtils.substringBetween(typeName, "com.xindaibao.cashloan.cl.mapper.", "Mapper");
		}

		if (StringUtils.isEmpty(simpleClassName)) {
			log.error("Get the class name error....");
		}

		return simpleClassName;
	}


	// fixme: ============== UNIT TEST ===============
	public static void main(String[] args) throws IOException {

//		Class mapperClass = ClMoheActiveSilenceStatsMapper.class;
//		System.out.println(extractBeanNameFromMapper(mapperClass));

//		 File file = new File("/Users/qiaor/Desktop/learn/mohe_sample.json");
//		 file = new File("/Users/qiaor/Desktop/script/zuoli/temp_001.txt");
//		 try {
//             String content = FileUtils.readFileToString(file);
//             //System.out.println("baby::: " + content);
//
////             JSONObject respJson = JSONObject.parseObject(content);
////             String data = respJson.getString("data");
//             // FIXME: 23/10/2017 parse data ...
//             ClMoheData clMoheData = JSONObject.parseObject(content, ClMoheData.class);
//
//
//             ClMoheReportInfo clMoheReportInfo = clMoheData.getReport_info();
//             // System.out.println(clMoheReportInfo);
//
//             final String taskId = clMoheReportInfo.getTaskId();
//             final Long userId = 112l;
//
//
//             setTaskIdAndUserId(clMoheData, taskId, userId);
//
//             System.out.println("baby::: ");
//
//
//         } catch (IOException e) {
//             e.printStackTrace();
//         }

	}

}
