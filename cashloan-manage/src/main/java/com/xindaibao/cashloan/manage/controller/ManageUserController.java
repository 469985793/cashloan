package com.xindaibao.cashloan.manage.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.text.SimpleDateFormat;
import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.model.kenya.KanyaUserCredit;
import com.xindaibao.cashloan.cl.model.kenya.KanyaUserObtainState;
import com.xindaibao.cashloan.cl.model.kenya.KanyaUserState;
import com.xindaibao.cashloan.core.model.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import com.xindaibao.cashloan.cl.service.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tool.util.StringUtil;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.BankCard;
import com.xindaibao.cashloan.cl.domain.Channel;
import com.xindaibao.cashloan.cl.domain.UserAuth;
import com.xindaibao.cashloan.cl.domain.UserEducationInfo;
import com.xindaibao.cashloan.cl.domain.UserEmerContacts;
import com.xindaibao.cashloan.cl.domain.Zhima;
import com.xindaibao.cashloan.cl.model.InviteBorrowModel;
import com.xindaibao.cashloan.cl.model.UserAuthModel;
import com.xindaibao.cashloan.cl.model.UserEducationInfoModel;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.domain.User;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.domain.UserOtherInfo;
import com.xindaibao.cashloan.core.service.CloanUserService;
import com.xindaibao.cashloan.core.service.UserBaseInfoService;
import com.xindaibao.cashloan.core.service.UserOtherInfoService;
import com.xindaibao.cashloan.system.permission.annotation.RequiresPermission;
import com.xindaibao.creditrank.cr.model.CreditModel;
import com.xindaibao.creditrank.cr.service.CreditService;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.DateUtil;
import java.text.DecimalFormat;
import credit.CreditRequest;
import credit.Header;

 /**
 * 用户记录Controller
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-21 13:39:06


 * 

 */
@Controller
@Scope("prototype")
public class ManageUserController extends ManageBaseController{
	private static final Logger logger = LoggerFactory.getLogger(ManageUserController.class);
	@Resource
	private CloanUserService cloanUserService;
	 @Resource
	 private ImportUserService importUserService;
	@Resource
	private UserAuthService authService;
	@Resource
	private UserBaseInfoService userBaseInfoService;
	@Resource
	private BankCardService bankCardService;
	@Resource
	private UserEmerContactsService userEmerContactsService;
	@Resource
	private UserInviteService userInviteService;
	@Resource
	private UserOtherInfoService userOtherInfoService;
	@Resource
	private UserEducationInfoService userEducationService;
	@Resource
	private CreditService creditService;
	 @Resource
	 private CloanUserService userService;
	@Resource
	private ZhimaService zhimaService;
	@Resource
	private ChannelService channelService;
	@Resource
	private KenyaUserInvitationMessageService  kenyaUserInvitationMessageService;



	 /**
	  * 导入用户
	  */
	 @RequestMapping(value="/modules/manage/cl/cluser/saveUsers.htm",method={RequestMethod.GET,RequestMethod.POST})
	 @RequiresPermission(code = "modules:manage:cl:cluser:saveUsers",name = "导入用户")
	 public void saveUsers(@RequestParam(value = "Excle") MultipartFile Excle)throws Exception{
		 int rows=0;
		 String ExcleName=Excle.getOriginalFilename();
		 InputStream is = Excle.getInputStream();
		 String[] split=ExcleName.split("\\.");
		 Workbook wb;
		 //根据文件名判断后缀
		 if("xls".equals(split[1])){
			wb=new HSSFWorkbook(is);
		 }else if("xlsx".equals(split[1])){
			wb=new XSSFWorkbook(is);
		 }else {
			wb=null;
		 }

		 List<List<String>> result = new ArrayList<List<String>>();
		 Sheet sheet = wb.getSheetAt(0);     //读取sheet 0
		 int firstRowIndex = sheet.getFirstRowNum()+1;   //第一行是列名，所以不读
		 int lastRowIndex = sheet.getLastRowNum();
		 for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
		 Row row = sheet.getRow(rIndex);
		 if (row != null) {
		 	int firstCellIndex = row.getFirstCellNum();
		 	int lastCellIndex = row.getLastCellNum();
			 List<String> rowList = new ArrayList<String>();
		 	for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
		 		 Cell cell = row.getCell(cIndex);
				String val = "";
				if(cell!=null){
					switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							val = cell.getStringCellValue();
							break;
						case Cell.CELL_TYPE_NUMERIC:
							DecimalFormat df = new DecimalFormat("0");
							val = df.format(cell.getNumericCellValue());
							break;
						case Cell.CELL_TYPE_BLANK:
							break;
						default:
							throw new Exception("数据类型配置不正确");
					}
				}else {
					val=" ";
				}
				 rowList.add(val);
		 	}
			 result.add(rowList);
		 }
		 }
		 int excleRows=result.size();

		 List<KanyaUser> listUsers = new ArrayList<KanyaUser>();
		 List<KanyaUserInfo> listUsersInfo = new ArrayList<KanyaUserInfo>();
		 List<KanyaUserJob> listUsersJob = new ArrayList<KanyaUserJob>();
		 List<KanyaUserLive> listUsersLive = new ArrayList<KanyaUserLive>();
		 List<KanyaUserContactInfo> listUsersContactInfo = new ArrayList<KanyaUserContactInfo>();
		 List<KanyaUserState> listUsersState = new ArrayList<KanyaUserState>();
		 List<KanyaUserObtainState> listUsersObtainState = new ArrayList<KanyaUserObtainState>();
		 List<KanyaUserCredit> listUsersCredit = new ArrayList<KanyaUserCredit>();
		 for (int i=0;i<excleRows;i++){
		 		KanyaUser u=new KanyaUser();
		 		u.setUserName(result.get(i).get(2));
		 		u.setMobile(result.get(i).get(5));
		 		u.setPassword("96EF9496AD3AD87AC01293BFBB0FD46F625957C2F89704DF58056FA5AF30366C");
		 		u.setStatus((byte) 1);
		 		u.setChannelCode("HAKIKA");
		 		u.setCreatedTime(new Date());
		 		u.setUpdatedTime(new Date());
				//生成自己的邀请码
				String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
				Random random = new Random();
				StringBuffer sb = new StringBuffer();
				for (int k = 0; k < 6; k++) {
					int number = random.nextInt(base.length());
					sb.append(base.charAt(number));
				}
				u.setInviteCode(sb.toString());
			 	listUsers.add(u);
		 }

		 int importUsersRow=importUserService.saveUser(listUsers);
		 for(int i=0;i<listUsers.size();i++){
		 	if (listUsers.get(i).getId()!=null){
				KanyaUserInfo ui=new KanyaUserInfo();
				KanyaUserJob uj=new KanyaUserJob();
				KanyaUserLive ul=new KanyaUserLive();
				KanyaUserContactInfo uci=new KanyaUserContactInfo();
				KanyaUserState us=new KanyaUserState();
				KanyaUserObtainState uos=new KanyaUserObtainState();
				KanyaUserCredit uc=new KanyaUserCredit();
				ui.setUid(listUsers.get(i).getId());
				ui.setStatus((byte)1);
				ui.setCreatedTime(new Date());
				ui.setUpdatedTime(new Date());
				listUsersInfo.add(ui);
				uj.setUid(listUsers.get(i).getId());
				uj.setStatus((byte)1);
				uj.setCreatedTime(new Date());
				uj.setUpdatedTime(new Date());
				listUsersJob.add(uj);
				ul.setUid(listUsers.get(i).getId());
				ul.setStatus((byte)1);
				ul.setCreatedTime(new Date());
				ul.setUpdatedTime(new Date());
				listUsersLive.add(ul);
				uci.setUid(listUsers.get(i).getId());
				uci.setStatus((byte)1);
				uci.setCreatedTime(new Date());
				uci.setUpdatedTime(new Date());
				listUsersContactInfo.add(uci);
				us.setUid(listUsers.get(i).getId());
				us.setStatus((byte)1);
				us.setCreatedTime(new Date());
				us.setUpdatedTime(new Date());
				listUsersState.add(us);
				uos.setUid(listUsers.get(i).getId());
				uos.setStatus((byte)1);
				uos.setCreatedTime(new Date());
				uos.setUpdatedTime(new Date());
				listUsersObtainState.add(uos);
				uc.setUid(listUsers.get(i).getId());
				uc.setStatus((byte)1);
				uc.setLimits(200000);
				uc.setCreatedTime(new Date());
				uc.setUpdatedTime(new Date());
				listUsersCredit.add(uc);
			}
		 }
		 int importUsersInfoRow=importUserService.saveUsersInfo(listUsersInfo);
		 int importUsersJobRow=importUserService.saveUsersJob(listUsersJob);
		 int importUsersLiveRow=importUserService.saveUsersLive(listUsersLive);
		 int importUsersContactInfoRow=importUserService.saveUsersContactInfo(listUsersContactInfo);
		 int importUsersStateRow=importUserService.saveUsersState(listUsersState);
		 int importUsersObtainStateRow=importUserService.saveUsersObtainState(listUsersObtainState);
		 int importUsersCreditRow=importUserService.saveUsersCredit(listUsersCredit);
		 logger.info("导入用户共"+importUsersRow+"条。"+importUsersInfoRow+"///"+importUsersJobRow+"///"+importUsersLiveRow+"///"
				 +importUsersContactInfoRow+"///"+importUsersStateRow+"///"+importUsersObtainStateRow+"///"+importUsersCreditRow);
		 System.out.println("完成");
	 }

	/**
	 *用户信息列表
	 * @param searchParams
	 * @param currentPage
	 * @param pageSize
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/modules/manage/cl/cluser/list.htm",method={RequestMethod.GET,RequestMethod.POST})
	@RequiresPermission(code = "modules:manage:cl:cluser:list",name = "用户信息列表")
	public void list(@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int currentPage,
			@RequestParam(value = "pageSize") int pageSize){
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		Page<KanyaUser> page = cloanUserService.listUser(params,currentPage,pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}

	 /**
	  *用户信息列表
	  * @param searchParams
	  * @param currentPage
	  * @param pageSize
	  */
	 @SuppressWarnings("unchecked")
	 @RequestMapping(value="/modules/manage/cl/cluser/listOnlySearch.htm",method={RequestMethod.GET,RequestMethod.POST})
	 @RequiresPermission(code = "modules:manage:cl:cluser:listOnlySearch",name = "用户信息列表(查)")
	 public void listOnlySearch(@RequestParam(value="searchParams",required=false) String searchParams,
					  @RequestParam(value = "current") int currentPage,
					  @RequestParam(value = "pageSize") int pageSize){
		 Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		 Page<KanyaUser> page = cloanUserService.listUser(params,currentPage,pageSize);
		 Map<String,Object> result = new HashMap<String,Object>();
		 result.put(Constant.RESPONSE_DATA, page);
		 result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		 result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		 result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		 ServletUtils.writeToResponse(response,result);
	 }

	/**
	 * 用户详细信息
	 * @param userId
	 * @param currentPage
	 * @param pageSize
	 */
	@RequestMapping(value="/modules/manage/cl/cluser/detail.htm",method={RequestMethod.GET,RequestMethod.POST})   
	@RequiresPermission(code = "modules:manage:cl:cluser:detail",name = "用户详细信息")
	public void detail(@RequestParam(value = "userId") Long userId)throws Exception{
		String serverHost = Global.getValue("manage_host");
		HashMap<String, Object> map = new HashMap<String,Object>();
//		KanyaUser user = cloanUserService.getById(userId);
//		if (user != null && user.getId() != null) {
			//用户基本信息
			KanyaUserLocation model = userBaseInfoService.getBaseModelByUserId(userId);

			//计算工作年限

		if(model.getStartTime()!=null){
			try {
				int day = new Date().getDay()
						- model.getStartTime().getDay();
				int month = new Date().getMonth()
						- model.getStartTime().getMonth();
				int year = new Date().getYear()
						- model.getStartTime().getYear();

				if (day > 15) {
					month++;
				}

				if (month > 6) {
					year++;
				}
				model.setWorkingYears(year+"");
				int i =+1;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}




			//model.setLivingImg(model.getLivingImg()!=null?serverHost +"/readFile.htm?path="+ model.getLivingImg():"");
			//model.setFrontImg(model.getFrontImg()!=null?serverHost +"/readFile.htm?path="+ model.getFrontImg():"");
			//model.setBackImg(model.getBackImg()!=null?serverHost +"/readFile.htm?path="+ model.getBackImg():"");
			//model.setOcrImg(model.getOcrImg()!=null?serverHost +"/readFile.htm?path="+ model.getOcrImg():"");
			
//			if (StringUtil.isNotBlank(model.getWorkingImg())) {
//				String workImgStr = model.getWorkingImg();
//				List<String> workImgList = Arrays.asList(workImgStr.split(";"));
//				for (int i = 0; i < workImgList.size(); i++) {
//					String workImg = workImgList.get(i);
//					workImgList.set(i, serverHost +"/readFile.htm?path="+ workImg);
//				}
//				map.put("workImgArr", workImgList);
//			}
			
			//银行卡信息
//			BankCard bankCard=bankCardService.getBankCardByUserId(user.getId());
//			if (null != bankCard) {
//				model.setBank(bankCard.getBank());
//				model.setCardNo(bankCard.getCardNo());
//				model.setBankPhone(bankCard.getPhone());
//			}
			
//			Channel cl = channelService.getById(user.getChannelCode());
//			if (cl!=null) {
//				model.setChannelCode(cl.getName());
//			}
			
			//芝麻分
//			Zhima zm = zhimaService.findByUserId(userId);
//			if (zm!=null&&zm.getScore()>0) {
//				model.setScore(zm.getScore().toString());
//			}
			map.put("userbase", model);
			
			// 构造查询条件Map
			HashMap<String, Object> paramMap = new HashMap<String,Object>();
			paramMap.put("userId",userId);

			// 认证信息
//			UserAuth authModel = authService.getUserAuth(paramMap);
//			map.put("userAuth", authModel);
			
			// 联系人信息
			List<UserEmerContacts> infoModel = userEmerContactsService.getUserEmerContactsByUserId(paramMap);
			map.put("userContactInfo", infoModel);
			
			// 用户其他账号信息
			UserOtherInfo otherInfo = userOtherInfoService.getInfoByUserId(userId);
			map.put("userOtherInfo", otherInfo);
			
		//}
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, map);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 用户认证信息列表
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/modules/manage/cl/cluser/authlist.htm",method={RequestMethod.GET,RequestMethod.POST})
	@RequiresPermission(code = "modules:manage:cl:cluser:authlist",name = "用户认证信息列表")
	public void authlist(@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int currentPage,
			@RequestParam(value = "pageSize") int pageSize){
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		Page<UserAuthModel> page = authService.listUserAuth(params, currentPage, pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * @description  查询黑名单用户列表   不用
	 * @param response
	 * @param request
	 * @param currentPage
	 * @param pageSize
	 * @param search
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/manage/cl/cluser/credit/list.htm")
	@RequiresPermission(code = "modules:manage:cl:cluser:credit:list",name = "查询用户列表")
	public void page(
			@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int currentPage,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String,Object> searchMap = JsonUtil.parse(searchParams, Map.class);
		Page<CreditModel> page = creditService.page(searchMap,currentPage, pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 查询邀请用户借款记录
	 * @param userId
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/invite/listInviteBorrow.htm")
	public void listInviteBorrow(
			@RequestParam(value="userId") long userId,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		InviteBorrowModel ibm = userInviteService.listInviteBorrow(userId,current,pageSize);
		Map<String, Object> data = new HashMap<>();
		data.put("list", ibm);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 添加和取消黑名单
	 * @param id
	 * @param state
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/user/updateState.htm")
	public void updateState(
			@RequestParam(value="id") long id,
			@RequestParam(value="state") String state) throws Exception {
		int msg = userBaseInfoService.updateKenyaUserState(id,state);
		Map<String,Object> result = new HashMap<String,Object>();
		if (msg<0) {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "修改失败");
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "修改成功");
			
		}
		ServletUtils.writeToResponse(response,result);
	}
	
	 /**
     * 天行 学信查询
     * @throws Exception
     */
	@RequestMapping(value = "/modules/manage/user/educationRequest.htm")
    public void apiEducationRequest(
    		@RequestParam(value="name") String name,
    		@RequestParam(value="idCard") String idCard) throws Exception{
    	final String APIKEY = Global.getValue("apikey");
		final String SECRETKEY = Global.getValue("secretkey");
    	String url = Global.getValue("tx_apihost");
        final String channelNo = Global.getValue("tx_channelNo");
        final String interfaceName = Global.getValue("tx_interfaceName");
        
        long timestamp = new Date().getTime();
        Header header = new Header(APIKEY, channelNo, interfaceName, timestamp);

        CreditRequest creditRequest = new CreditRequest(url, header);

        Map<String, Object> payload = new HashMap<>();

        payload.put("name", name);
        payload.put("idCard", idCard);

        creditRequest.setPayload(payload);

        creditRequest.signByKey(SECRETKEY);

        String resultStr = creditRequest.request();

        JSONObject resultJson = JSONObject.parseObject(resultStr);
        
        
        Map<String,Object> map = new HashMap<>();
        map.put("loginName", name);
        User user = cloanUserService.findByPhone(name);
        
        UserBaseInfo ubi = new UserBaseInfo();
        if (user!=null) {
        	map = new HashMap<>();
        	map.put("userId", user.getId());
        	ubi = userBaseInfoService.findSelective(map);
		}
        
        int msg = 0;
        Map<String,Object> result = new HashMap<String,Object>();
        if (ubi!=null&&resultJson.getInteger("code")==200) {
        	JSONObject resJson = JSONObject.parseObject(StringUtil.isNull(resultJson.get("res")));
        	logger.info(StringUtil.isNull(resJson));
			UserEducationInfo ue = new UserEducationInfo();
			ue.setUserId(ubi.getUserId());
			ue.setEducationType(resJson.getString("educationType"));
			ue.setProfession(resJson.getString("profession"));
			ue.setMatriculationTime(resJson.getString("matriculationTime"));
			ue.setGraduateSchool(resJson.getString("graduateSchool"));
			ue.setGraduationTime(resJson.getString("graduationTime"));
			ue.setGraduationConclusion(resJson.getString("graduationConclusion"));
			ue.setEducationBackground(resJson.getString("educationBackground"));
			ue.setState("20");
			msg = userEducationService.save(ue);
		}
		logger.info(resultJson.getString("message"));
		if (msg>0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "操作成功");
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "操作失败");
			
		}
		ServletUtils.writeToResponse(response,result);
    }
	
	/**
	 * 修改学历信息
	 * @param uei
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/user/updateEducation.htm")
	public void updateEducation(UserEducationInfo uei) throws Exception {
		int msg = userEducationService.update(uei);
		Map<String,Object> result = new HashMap<String,Object>();
		if (msg<0) {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "修改失败");
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "修改成功");
			
		}
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 查询学历列表
	 * @param uei
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/manage/user/educationList.htm")
	public void educationList(
			@RequestParam(value="search",required=false) String search,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String,Object> searchMap = JsonUtil.parse(search, Map.class);
		Page<UserEducationInfoModel> page = userEducationService.list(searchMap,current,pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String, Object> data = new HashMap<>();
		data.put("list", page.getResult());
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
}
