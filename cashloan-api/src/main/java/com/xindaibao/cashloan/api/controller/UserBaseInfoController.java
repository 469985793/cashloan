package com.xindaibao.cashloan.api.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xindaibao.cashloan.cl.sdk.face.CreditLoanFace;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.xindaibao.cashloan.cl.domain.OperatorReqLog;
import com.xindaibao.cashloan.cl.domain.UserAuth;
import com.xindaibao.cashloan.cl.domain.UserCardCreditLog;
import com.xindaibao.cashloan.cl.domain.UserMessages;
import com.xindaibao.cashloan.cl.model.FileTypeUtil;
import com.xindaibao.cashloan.cl.model.UploadFileRes;
import com.xindaibao.cashloan.cl.model.UserAuthModel;
import com.xindaibao.cashloan.cl.monitor.BusinessExceptionMonitor;
import com.xindaibao.cashloan.cl.service.AccfundInfoService;
import com.xindaibao.cashloan.cl.service.BankCardService;
import com.xindaibao.cashloan.cl.service.ClBorrowService;
import com.xindaibao.cashloan.cl.service.OperatorReqLogService;
import com.xindaibao.cashloan.cl.service.OperatorRespDetailService;
import com.xindaibao.cashloan.cl.service.OperatorService;
import com.xindaibao.cashloan.cl.service.UserAuthService;
import com.xindaibao.cashloan.cl.service.UserCardCreditLogService;
import com.xindaibao.cashloan.cl.service.UserContactsService;
import com.xindaibao.cashloan.cl.service.UserMessagesService;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.util.Base64;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.util.StringUtil;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.core.domain.User;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.model.UserWorkInfoModel;
import com.xindaibao.cashloan.core.service.CloanUserService;
import com.xindaibao.cashloan.core.service.UserBaseInfoService;
import com.xindaibao.creditrank.cr.domain.Credit;
import com.xindaibao.creditrank.cr.mapper.CreditMapper;

import credit.CreditRequest;
import credit.Header;
import credit.LinkfaceHsRequest;
import credit.MiniversionVerifyRequest;
import credit.SimpleFormRequest;
import tool.util.DateUtil;
import tool.util.NumberUtil;

/**
 * 用户详情表Controller
 */
@Controller
@Scope("prototype")
public class UserBaseInfoController extends BaseController {

    public static final Logger logger = LoggerFactory.getLogger(UserBaseInfoController.class);

    @Resource
    private UserBaseInfoService userBaseInfoService;

    @Resource
    private UserAuthService userAuthService;

    @Resource
    private BankCardService bankCardService;

    @Resource
    private OperatorReqLogService operatorReqLogService;

    @Resource
    private OperatorRespDetailService operatorRespDetailService;

    @Resource
    private UserContactsService userContactsService;

    @Resource
    private UserMessagesService userMessagesService;

    @Resource
    private ClBorrowService clBorrowService;

    @Resource
    private OperatorService operatorService;

    @Resource
    private CloanUserService cloanUserService;

    @Resource
    private UserCardCreditLogService userCardCreditLogService;

    @Resource
    private AccfundInfoService accfundInfoService;

    @Resource
    private CreditMapper creditMapper;

    /**
     * @param userId
     * @return void
     * @description 根据userId获取用户信息
     * @since 1.0.0
     */
    @RequestMapping(value = "/api/act/mine/userInfo/getUserInfo.htm", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getUserInfo(
            @RequestParam(value = "userId", required = true) String userId) {
        String serverHost = Global.getValue("server_host");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        UserBaseInfo info = userBaseInfoService.findSelective(paramMap);
        if (null != info && null != info.getLivingImg()) {
            info.setLivingImg(serverHost + "/readFile.htm?path=" + info.getLivingImg());
            info.setFrontImg(serverHost + "/readFile.htm?path=" + info.getFrontImg());
            info.setBackImg(serverHost + "/readFile.htm?path=" + info.getBackImg());
            info.setOcrImg(serverHost + "/readFile.htm?path=" + info.getOcrImg());
        }
        return JsonUtil.newJson().addData(Constant.RESPONSE_DATA, info).toJson().toJSONString();
    }

    /**
     * 查询用户工作信息
     *
     * @param userId
     */
    @RequestMapping(value = "/api/act/mine/userInfo/getWorkInfo.htm", method = RequestMethod.GET)
    public void getWorkInfo(
            @RequestParam(value = "userId", required = true) Long userId) {
        UserWorkInfoModel info = userBaseInfoService.getWorkInfo(userId);
        if (StringUtil.isNotBlank(info.getWorkingImg())) {
            info.setWorkImgState(UserWorkInfoModel.WORK_IMG_ADDED);
        } else {
            info.setWorkImgState(UserWorkInfoModel.WORK_IMG_NO_ADD);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_DATA, info);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 查询用户工作信息
     *
     * @param userId
     */
    @RequestMapping(value = "/api/act/mine/userInfo/getWorkImg.htm", method = RequestMethod.GET)
    public void getWorkImg(@RequestParam(value = "userId", required = true) Long userId) {
        String serverHost = Global.getValue("server_host");

        UserWorkInfoModel info = userBaseInfoService.getWorkInfo(userId);
        String workImgPath = info.getWorkingImg();
        List<String> list = new ArrayList<String>();
        if (StringUtil.isNotBlank(workImgPath)) {
            String[] imgArray = workImgPath.split(";");

            for (int i = 0; i < imgArray.length; i++) {
                if (StringUtil.isNotBlank(imgArray[i])) {
                    list.add(serverHost + "/readFile.htm?path=" + imgArray[i]);
                }
            }
        }

        Map<String, Object> listMap = new HashMap<String, Object>();
        listMap.put("list", list);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_DATA, listMap);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * @param userId
     * @return void
     * @description 根据userId获取用户姓名
     * @author
     * @since 1.0.0
     */
    @RequestMapping(value = "/api/act/mine/userInfo/getUserName.htm", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getUserName(
            @RequestParam(value = "userId", required = true) String userId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        UserBaseInfo info = userBaseInfoService.findSelective(paramMap);
        paramMap.clear();
        Map<String, Object> temp = new HashMap<String, Object>();
        temp.put("name", info.getRealName());
        return JsonUtil.newJson().addData(Constant.RESPONSE_DATA, temp).toJson().toJSONString();
    }

    /**
     * @param type
     * @return void
     * @description 根据type获取相应的字典项
     * @author
     * @since 1.0.0
     */
    @RequestMapping(value = "/api/act/dict/list.htm", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getDicts(@RequestParam(value = "type") String type) {
        Map<String, Object> dicList = new HashMap<String, Object>();
        if (type != null && type != "") {
            String[] types = type.split(",");
            for (int i = 0; i < types.length; i++) {
                dicList.put(StringUtil.clearUnderline(types[i]) + "List", userBaseInfoService.getDictsCache(types[i]));
            }
        }
        if (!dicList.isEmpty()) {
            return JsonUtil.newJson().addData(Constant.RESPONSE_DATA, dicList).toJson().toJSONString();
        } else {
            return JsonUtil.newFailJson().toJson().toJSONString();
        }
    }

    /**
     * ocr请求地址获取
     *
     * @throws Exception
     */
    @RequestMapping(value = "/api/act/mine/userInfo/ocrUrl.htm")
    private void ocrHostRequest() throws Exception {
        String host = Global.getValue("face_ocr_url");
        String apikey = Global.getValue("face++_api_key");
        String secretkey = Global.getValue("face++_secret_key");

        long timestamp = new Date().getTime();
        Header header = new Header(apikey, timestamp);

        SimpleFormRequest formRequest = new SimpleFormRequest(host, header);
        formRequest.signByKey(secretkey);
        String ocrUrl = formRequest.request();

        logger.info(ocrUrl);
        Map<String, Object> ocrDate = new HashMap<>();

        if (StringUtil.isNotBlank(ocrUrl)) {
            JSONObject resultJson = JSONObject.parseObject(ocrUrl);
            JSONObject res = JSONObject.parseObject(StringUtil.isNull(resultJson.get("res")));
            if (Integer.parseInt(resultJson.get("code").toString()) == 200) {
                ocrDate.put("apiKey", res.get("apiKey"));
                ocrDate.put("secretKey", res.get("secretKey"));
                ocrDate.put("url", res.get("url"));
            } else {
                ocrDate.put("message", resultJson.get("message"));
            }
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_DATA, ocrDate);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * @param livingImg  自拍
     * @param frontImg   正面
     * @param backImg    背面
     * @param ocrImg     证上照片
     * @param name       名字
     * @param idCard     身份证号
     * @param education  学历
     * @param liveAddr   居住地址
     * @param detailAddr 居住详细地址
     * @throws Exception
     * @description 人证识别
     */
    @RequestMapping(value = "/api/act/mine/userInfo/authentication.htm", method = RequestMethod.POST)
    public void authentication(
            @RequestParam(value = "livingImg", required = true) MultipartFile livingImg,
            @RequestParam(value = "frontImg", required = true) MultipartFile frontImg,
            @RequestParam(value = "backImg", required = true) MultipartFile backImg,
            @RequestParam(value = "ocrImg", required = false) MultipartFile ocrImg,
            @RequestParam(value = "education", required = true) String education,
            @RequestParam(value = "liveAddr", required = true) String liveAddr,
            @RequestParam(value = "detailAddr", required = true) String detailAddr,
            @RequestParam(value = "liveCoordinate", required = true) String liveCoordinate,
            @RequestParam(value = "realName", required = true) String realName,
            @RequestParam(value = "idNo", required = true) String idNo,
            @RequestParam(value = "userId", required = true) String userId)
            throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        String type = Global.getValue("verify_type");
        if (StringUtil.equals(type, "10")) {//face++
            result = this.faceID(livingImg, frontImg, backImg, idNo, realName, education, liveAddr, detailAddr, liveCoordinate);
        }
        if (StringUtil.equals(type, "20")) {//小视
            result = this.miniversion(livingImg, frontImg, backImg, idNo, realName, education, liveAddr, detailAddr, liveCoordinate);
        }
        if (StringUtil.equals(type, "30")) {//商汤
            result = this.linkface(livingImg, frontImg, backImg, ocrImg, realName, idNo, education, liveAddr, detailAddr, userId, liveCoordinate);
        }
        if(StringUtil.equals(type, "40")){
        	result = this.rong360(livingImg,frontImg,backImg,ocrImg,education,liveAddr,detailAddr,liveCoordinate,realName,idNo,userId);
        }
        ServletUtils.writeToResponse(response, result);
    }
    
    /**
     * 融360
     * @param userId 
     * @param idNo 
     * @param realName 
     * @param liveCoordinate 
     * @param detailAddr 
     * @param liveAddr 
     * @param education 
     * @param ocrImg 
     * @param backImg 
     * @param frontImg 
     * @param livingImg 
     * @return
     * @throws Exception 
     * @throws IOException 
     */
    private Map<String, Object> rong360(MultipartFile livingImg, MultipartFile frontImg, MultipartFile backImg, MultipartFile ocrImg, String education, String liveAddr, String detailAddr, String liveCoordinate, String realName, String idNo, String userId) throws IOException, Exception {
    	//long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
        long parseUserId = Long.valueOf(userId);
    	Map<String, Object> ubiMap = new HashMap<String, Object>();
        ubiMap.put("idNo", idNo);
        UserBaseInfo ubi = userBaseInfoService.findSelective(ubiMap);
        User user = new User();//cloanUserService.getById(parseUserId);
        UserAuth ua = userAuthService.findSelective(parseUserId);
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (ubi != null && ubi.getUserId() != parseUserId) {
            resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            resultMap.put(Constant.RESPONSE_CODE_MSG, "身份证号码已存在");
        } else if (ubi != null && ua.getIdState().equals("30") && ubi.getUserId() == parseUserId) {
            ubi.setEducation(education);
            ubi.setLiveAddr(liveAddr);
            ubi.setLiveDetailAddr(detailAddr);
            ubi.setLiveCoordinate(liveCoordinate);
            int count = userBaseInfoService.updateById(ubi);
            if (count > 0) {
                resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
                resultMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
            } else {
                resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                resultMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
            }
        } else if (ubi == null && ua.getIdState().equals("10")) {
            ubiMap.clear();
            ubiMap.put("userId", parseUserId);
            ubi = userBaseInfoService.findSelective(ubiMap);
            if (user == null || ubi == null) {
                resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                resultMap.put(Constant.RESPONSE_CODE_MSG, "用户信息不存在!");
            } else if (!userCardCreditLogService.isCanCredit(parseUserId)) {
                resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                resultMap.put(Constant.RESPONSE_CODE_MSG, "今日请求认证次数已超过限制,请明日再尝试");
            } else {
                List<UploadFileRes> list = new LinkedList<>();
                saveMultipartFile(list, livingImg, ubi.getPhone());
                saveMultipartFile(list, frontImg, ubi.getPhone());
                saveMultipartFile(list, backImg, ubi.getPhone());
                
                String livingPath = list.get(0).getResPath().replaceAll("\\\\", "/");
                String frontPath =  list.get(1).getResPath().replaceAll("\\\\", "/");
                //人证对比
                String url = Global.getValue("rong360_url");
                String appid = Global.getValue("rong360_appid");
                String privatekey = Global.getValue("rong360_privatekey");
				String result = CreditLoanFace.compareRong360(url, appid, privatekey,
						livingPath,frontPath, realName, idNo);

                JSONObject resultJson = JSONObject.parseObject(result);
                int code = 500;
                if (resultJson.getString("tianji_api_faceplus_photocomparison_response") != null && "200".equals(resultJson.getString("error"))) {
                    code = 200;
                }

                Map<String, Object> reqMap = new HashMap<>();
                reqMap.put("idNo", idNo);
                reqMap.put("realName", realName);
                reqMap.put("appId", appid);
                reqMap.put("livingImgPath", livingPath);
                reqMap.put("frontImgPath", livingPath);

                UserCardCreditLog log = new UserCardCreditLog();
                log.setCreateTime(DateUtil.getNow());
                log.setUserId(ubi.getUserId());
                log.setReqParams(JSONObject.toJSONString(reqMap));
                log.setReturnParams(result);
                log.setResult(String.valueOf(code));

                if (StringUtil.isNotBlank(result) && code == 200) {
                	Double idCard_credit_pass_rate = Global.getDouble("idCard_credit_pass_rate") * 100;
                	String threshold= Global.getValue("threshold");
                	String resp = resultJson.getString("tianji_api_faceplus_photocomparison_response");
                    JSONObject respObj = JSONObject.parseObject(StringUtil.isNull(resp));
                    //活体照片验证结果 
                    JSONObject resultFaceidObj = JSONObject.parseObject(respObj.getString("result_faceid"));
                    //参考阈值
                    JSONObject resultFaceidObjThresholds = JSONObject.parseObject(resultFaceidObj.getString("thresholds"));
                    //比对结果的置信度
                    String resultFaceidconfidence = resultFaceidObj.getString("confidence");
                    //参照照片验证结果
                    JSONObject resultRef1 = JSONObject.parseObject(respObj.getString("result_ref1"));
                    //参考阈值
                    JSONObject resultRef1Thresholds = JSONObject.parseObject(resultRef1.getString("thresholds"));
                    //比对结果的置信度
                    String resultRef1confidence = resultRef1.getString("confidence");
                    if (Double.parseDouble(resultFaceidconfidence) >= idCard_credit_pass_rate
                    		&& Double.parseDouble(resultRef1confidence)>=idCard_credit_pass_rate
                    		&& Double.parseDouble(resultFaceidconfidence)> resultFaceidObjThresholds.getDoubleValue(threshold)
                    		&& Double.parseDouble(resultRef1confidence)>resultRef1Thresholds.getDoubleValue(threshold)
                    		) {
                        Map<String, Object> returnMap = new HashMap<>();
                         returnMap.put("idState", "30");
                        returnMap.put("userId", parseUserId);
                        userAuthService.updateByUserId(returnMap);

                        //保存对比日志
                        log.setConfidence(String.valueOf(Double.parseDouble(resultFaceidconfidence) / 100));
                        //保存详细信息
                        livingPath = list.get(0).getResPath();
                        frontPath = list.get(1).getResPath();
                        String backPath = list.get(2).getResPath();

                        ubi.setLivingImg(livingPath);
                        ubi.setFrontImg(frontPath);
                        ubi.setBackImg(backPath);
                        ubi.setRealName(realName);
                        ubi.setIdNo(idNo);
                        ubi.setEducation(education);
                        ubi.setLiveAddr(liveAddr);
                        ubi.setLiveDetailAddr(detailAddr);
                        ubi.setLiveCoordinate(liveCoordinate);
                        ubi.setAge(StringUtil.getAge(idNo));
                        ubi.setSex(StringUtil.getSex(idNo));
                        userBaseInfoService.updateById(ubi);

                        resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
                        resultMap.put(Constant.RESPONSE_CODE_MSG, "相似度达到指定标准,识别成功!");
                    } else {
                        resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                        resultMap.put(Constant.RESPONSE_CODE_MSG, "人证相似度不足,请重新识别!");
                    }
                } else {
                    //
                    BusinessExceptionMonitor.add(BusinessExceptionMonitor.TYPE_6, userId, resultJson.getString("message"));
                    resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                    resultMap.put(Constant.RESPONSE_CODE_MSG, "人证识别异常,请重新尝试!");
                }
                userCardCreditLogService.insert(log);
                logger.info("用户ID为" + userId + "的用户人证识别对比结果" + result);
            }
        } else {
            resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            resultMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
        }
        return resultMap;
		
	}

	/**
     * faceID
     */
    private Map<String, Object> faceID(MultipartFile livingImg, MultipartFile frontImg, MultipartFile backImg,
                                       String idNo, String realName, String education, String liveAddr, String detailAddr,
                                       String liveCoordinate) throws Exception {
//		String apikey = Global.getValue("apikey");
//		String secretkey = Global.getValue("secretkey");

        long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
        Map<String, Object> ubiMap = new HashMap<String, Object>();
        ubiMap.put("idNo", idNo);
        UserBaseInfo ubi = userBaseInfoService.findSelective(ubiMap);
        User user = new User();//cloanUserService.getById(userId);
        UserAuth ua = userAuthService.findSelective(userId);
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (ubi != null && ubi.getUserId() != userId) {
            resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            resultMap.put(Constant.RESPONSE_CODE_MSG, "身份证号码已存在");
        } else if (ubi != null && ua.getIdState().equals("30") && ubi.getUserId() == userId) {
            ubi.setEducation(education);
            ubi.setLiveAddr(liveAddr);
            ubi.setLiveDetailAddr(detailAddr);
            ubi.setLiveCoordinate(liveCoordinate);
            int count = userBaseInfoService.updateById(ubi);
            if (count > 0) {
                resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
                resultMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
            } else {
                resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                resultMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
            }
        } else if (ubi == null && ua.getIdState().equals("10")) {
            ubiMap.clear();
            ubiMap.put("userId", userId);
            ubi = userBaseInfoService.findSelective(ubiMap);
            if (user == null || ubi == null) {
                resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                resultMap.put(Constant.RESPONSE_CODE_MSG, "用户信息不存在!");
            } else if (!userCardCreditLogService.isCanCredit(userId)) {
                resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                resultMap.put(Constant.RESPONSE_CODE_MSG, "今日请求认证次数已超过限制,请明日再尝试");
            } else {
                List<UploadFileRes> list = new LinkedList<>();
                saveMultipartFile(list, livingImg, ubi.getPhone());
                saveMultipartFile(list, frontImg, ubi.getPhone());
                saveMultipartFile(list, backImg, ubi.getPhone());

                String livingPath = Global.getValue("server_host")
                        + "/readFile.htm?path=" + list.get(0).getResPath();
                String frontPath = Global.getValue("server_host")
                        + "/readFile.htm?path=" + list.get(1).getResPath();

                //人证对比
                String verifyHost = Global.getValue("face_pp_url");
                String faceApikey = Global.getValue("face++_api_key");
                String faceSecretkey = Global.getValue("face++_secret_key");
                String result = CreditLoanFace.compare(verifyHost, faceApikey, faceSecretkey, livingPath, frontPath);


                JSONObject resultJson = JSONObject.parseObject(result);
                int code = 500;
                if (resultJson.getInteger("confidence") != null) {
                    code = 200;
                }

                Map<String, Object> reqMap = new HashMap<>();
                reqMap.put("idNo", idNo);
                reqMap.put("realName", realName);
                reqMap.put("apikey", faceApikey);
                reqMap.put("secretkey", faceSecretkey);
                reqMap.put("livingImgPath", livingPath);
                reqMap.put("frontImgPath", livingPath);

                UserCardCreditLog log = new UserCardCreditLog();
                log.setCreateTime(DateUtil.getNow());
                log.setUserId(ubi.getUserId());
                log.setReqParams(JSONObject.toJSONString(reqMap));
                log.setReturnParams(result);
                log.setResult(String.valueOf(code));

                if (StringUtil.isNotBlank(resultJson) && code == 200) {
                    JSONObject data = JSONObject.parseObject(StringUtil.isNull(resultJson));
                    String confidence = data.getString("confidence");
                    if (Double.parseDouble(confidence) >= Global.getDouble("idCard_credit_pass_rate") * 100) {
                        Map<String, Object> returnMap = new HashMap<>();
                        returnMap.put("idState", "30");
                        returnMap.put("userId", userId);
                        userAuthService.updateByUserId(returnMap);

                        //保存对比日志
                        log.setConfidence(String.valueOf(Double.parseDouble(confidence) / 100));
                        //保存详细信息
                        livingPath = list.get(0).getResPath();
                        frontPath = list.get(1).getResPath();
                        String backPath = list.get(2).getResPath();

                        ubi.setLivingImg(livingPath);
                        ubi.setFrontImg(frontPath);
                        ubi.setBackImg(backPath);
                        ubi.setRealName(realName);
                        ubi.setIdNo(idNo);
                        ubi.setEducation(education);
                        ubi.setLiveAddr(liveAddr);
                        ubi.setLiveDetailAddr(detailAddr);
                        ubi.setLiveCoordinate(liveCoordinate);
                        ubi.setAge(StringUtil.getAge(idNo));
                        ubi.setSex(StringUtil.getSex(idNo));
                        userBaseInfoService.updateById(ubi);

                        resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
                        resultMap.put(Constant.RESPONSE_CODE_MSG, "相似度达到指定标准,识别成功!");
                    } else {
                        resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                        resultMap.put(Constant.RESPONSE_CODE_MSG, "人证相似度不足,请重新识别!");
                    }
                } else {
                    //
                    BusinessExceptionMonitor.add(BusinessExceptionMonitor.TYPE_6, userId, resultJson.getString("message"));
                    resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                    resultMap.put(Constant.RESPONSE_CODE_MSG, "人证识别异常,请重新尝试!");
                }
                userCardCreditLogService.insert(log);
                logger.info("用户ID为" + userId + "的用户人证识别对比结果" + result);
            }
        } else {
            resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            resultMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
        }
        return resultMap;
    }


    /**
     * 小视
     */
    private Map<String, Object> miniversion(MultipartFile livingImg, MultipartFile frontImg, MultipartFile backImg,
                                            String idNo, String realName, String education, String liveAddr, String detailAddr,
                                            String liveCoordinate) throws Exception {
        String apikey = Global.getValue("apikey");
        String secretkey = Global.getValue("secretkey");
        long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
        Map<String, Object> ubiMap = new HashMap<String, Object>();
        ubiMap.put("idNo", idNo);
        UserBaseInfo ubi = userBaseInfoService.findSelective(ubiMap);
        User user = new User();//cloanUserService.getById(userId);
        UserAuth ua = userAuthService.findSelective(userId);
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (ubi != null && ubi.getUserId() != userId) {
            resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            resultMap.put(Constant.RESPONSE_CODE_MSG, "身份证号码已存在");
        } else if (ubi != null && ua.getIdState().equals("30") && ubi.getUserId() == userId) {
            ubi.setEducation(education);
            ubi.setLiveAddr(liveAddr);
            ubi.setLiveDetailAddr(detailAddr);
            ubi.setLiveCoordinate(liveCoordinate);
            int count = userBaseInfoService.updateById(ubi);
            if (count > 0) {
                resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
                resultMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
            } else {
                resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                resultMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
            }
        } else if (ubi == null && ua.getIdState().equals("10")) {
            ubiMap.clear();
            ubiMap.put("userId", userId);
            ubi = userBaseInfoService.findSelective(ubiMap);
            if (user == null || ubi == null) {
                resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                resultMap.put(Constant.RESPONSE_CODE_MSG, "用户信息不存在!");
            } else if (!userCardCreditLogService.isCanCredit(userId)) {
                resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                resultMap.put(Constant.RESPONSE_CODE_MSG, "今日请求认证次数已超过限制,请明日再尝试");
            } else {
                List<UploadFileRes> list = new LinkedList<>();
                saveMultipartFile(list, livingImg, ubi.getPhone());
                saveMultipartFile(list, frontImg, ubi.getPhone());
                saveMultipartFile(list, backImg, ubi.getPhone());
                String livingPath = list.get(0).getResPath();// Global.getValue("server_host")+ "/readFile.htm?path=" + list.get(0).getResPath();
                //人证对比
                String verifyHost = Global.getValue("verify_host");

                long timestamp = new Date().getTime();
                Header header = new Header(apikey, timestamp);

                MiniversionVerifyRequest miniversionVerifyRequest = new MiniversionVerifyRequest(verifyHost, header);

                File livingImgfile = getRemoteFile(livingPath);

                miniversionVerifyRequest.setLivingImg(livingImgfile);
                miniversionVerifyRequest.setIdCard(idNo);
                miniversionVerifyRequest.setName(realName);

                miniversionVerifyRequest.signByKey(secretkey);

                String result = miniversionVerifyRequest.request();

                logger.info("小视认证结果:" + result);

                // 删除文件
                if (livingImgfile.exists()) {
                    livingImgfile.delete();
                }
                JSONObject resultJson = null;
                int code = 0;
                if (StringUtil.isNotBlank(result)) {
                    resultJson = JSONObject.parseObject(result);
                    code = resultJson.getInteger("code");
                }

                Map<String, Object> reqMap = new HashMap<>();
                reqMap.put("idNo", idNo);
                reqMap.put("realName", realName);
                reqMap.put("apikey", apikey);
                reqMap.put("secretkey", secretkey);
                reqMap.put("livingImgfile", livingImgfile);

                UserCardCreditLog log = new UserCardCreditLog();
                log.setCreateTime(DateUtil.getNow());
                log.setUserId(ubi.getUserId());
                log.setReqParams(JSONObject.toJSONString(reqMap));
                log.setReturnParams(result);
                log.setResult(String.valueOf(code));

                if (StringUtil.isNotBlank(resultJson) && code == 200) {
                    JSONObject data = JSONObject.parseObject(StringUtil.isNull(resultJson.get("data")));
                    String confidence = data.getString("score");
                    if (StringUtil.isNotBlank(confidence) && Double.parseDouble(confidence) >= Global.getDouble("idCard_credit_pass_rate") * 100) {

                        //保存对比日志
                        log.setConfidence(String.valueOf(Double.parseDouble(confidence) / 100));

                        //保存详细信息
                        livingPath = list.get(0).getResPath();
                        String frontPath = list.get(1).getResPath();
                        String backPath = list.get(2).getResPath();

                        ubi.setLivingImg(livingPath);
                        ubi.setFrontImg(frontPath);
                        ubi.setBackImg(backPath);
                        ubi.setRealName(realName);
                        ubi.setIdNo(idNo);
                        ubi.setEducation(education);
                        ubi.setLiveAddr(liveAddr);
                        ubi.setLiveDetailAddr(detailAddr);
                        ubi.setLiveCoordinate(liveCoordinate);
                        ubi.setAge(StringUtil.getAge(idNo));
                        ubi.setSex(StringUtil.getSex(idNo));
                        int count = userBaseInfoService.updateById(ubi);
                        if (count > 0) {
                            Map<String, Object> returnMap = new HashMap<>();
                            returnMap.put("idState", "30");
                            returnMap.put("userId", userId);
                            userAuthService.updateByUserId(returnMap);
                        }

                        resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
                        resultMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
                    } else {
                        resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                        resultMap.put(Constant.RESPONSE_CODE_MSG, "人证相似度不足,请重新识别!");
                    }
                } else {
                    BusinessExceptionMonitor.add(BusinessExceptionMonitor.TYPE_7, userId, resultJson.getString("message"));
                    resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                    resultMap.put(Constant.RESPONSE_CODE_MSG, "照片扫描失败,请重新尝试!");
                }
                userCardCreditLogService.insert(log);
                logger.info("用户ID为" + userId + "的用户人证识别对比结果" + result);
            }
        }
        return resultMap;
    }

    /**
     * @param livingImg  自拍
     * @param frontImg   正面
     * @param backImg    背面
     * @param ocrImg     证上照片
     * @param name       名字
     * @param idCard     身份证号
     * @param education  学历
     * @param liveAddr   居住地址
     * @param detailAddr 居住详细地址
     * @throws Exception
     * @description 人证识别
     */
    private Map<String, Object> linkface(MultipartFile livingImg, MultipartFile frontImg, MultipartFile backImg,
                                         MultipartFile ocrImg, String realName, String idNo, String education, String liveAddr,
                                         String detailAddr, String userId, String liveCoordinate)
            throws Exception {
        final String APIKEY = Global.getValue("apikey");
        final String SECRETKEY = Global.getValue("secretkey");
        final String LINKFACEHOST = Global.getValue("verify_host");

        Map<String, Object> returnMap = new HashMap<String, Object>();
        Map<String, Object> infoMap = new HashMap<String, Object>();
        infoMap.put("idNo", idNo);
        UserBaseInfo info = userBaseInfoService.findSelective(infoMap);
        if (info == null) {
            infoMap.clear();
            infoMap.put("userId", userId);
            info = userBaseInfoService.findSelective(infoMap);
        }

        if (info != null
                && info.getUserId().toString().equals(userId)
                && (StringUtil.isBlank(info.getIdNo()) || info.getIdNo()
                .equals(idNo))) {
            if (!userCardCreditLogService.isCanCredit(info.getUserId())) {
                returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                returnMap.put(Constant.RESPONSE_CODE_MSG, "今日请求认证次数已超过限制,请明日再尝试");
            } else {
                // 信息校验成功,继续业务处理
                // 根据userId获取user表里面的login_name并存入baseinfo表里面的phone字段
                User user = new User();//cloanUserService.getById(NumberUtil.getLong(userId));
                info.setPhone(user.getLoginName());
                info.setRealName(realName);
                info.setSex(StringUtil.getSex(idNo));
                info.setAge(StringUtil.getAge(idNo));
                info.setIdNo(idNo);
                info.setEducation(education);
                info.setLiveDetailAddr(detailAddr);
                info.setLiveAddr(liveAddr);
                info.setLiveCoordinate(liveCoordinate);

                Map<String, Object> userAuthMap = new HashMap<String, Object>();
                userAuthMap.put("userId", userId);
                UserAuth userAuth = userAuthService.getUserAuth(userAuthMap);

                double match = 0.0;
                if (livingImg != null && ocrImg != null && 30 != Integer.parseInt(userAuth.getIdState())) {
                    logger.info("用户" + user.getLoginName() + "完善个人信息，进入人证识别比对");
                    response.setContentType("text/html;charset=utf8");
                    List<UploadFileRes> list = new LinkedList<>();
                    saveMultipartFile(list, livingImg, info.getPhone());
                    saveMultipartFile(list, frontImg, info.getPhone());
                    saveMultipartFile(list, backImg, info.getPhone());
                    saveMultipartFile(list, ocrImg, info.getPhone());
                    info.setLivingImg(list.get(0).getResPath());
                    info.setFrontImg(list.get(1).getResPath());
                    info.setBackImg(list.get(2).getResPath());
                    info.setOcrImg(list.get(3).getResPath());

                    long timestamp = new Date().getTime();
                    Header header = new Header(APIKEY, timestamp);
                    LinkfaceHsRequest linkfaceRequest = new LinkfaceHsRequest(LINKFACEHOST, header);
                    // 读取远程文件
                    String livingPath = list.get(0).getResPath();
                    String ocrPath = list.get(3).getResPath();
                    File living = getRemoteFile(livingPath);
                    File ocr = getRemoteFile(ocrPath);
                    // 读取远程文件结束
                    linkfaceRequest.setLivingImg(living);
                    linkfaceRequest.setOcrImg(ocr);
                    linkfaceRequest.setName(realName);
                    linkfaceRequest.setIdCard(idNo);
                    linkfaceRequest.signByKey(SECRETKEY);
                    String result = linkfaceRequest.request();
                    logger.info("用户" + user.getLoginName() + "完善个人信息，进行人证识别比对，result为:" + result);

                    // 删除文件结束
                    UserCardCreditLog log = new UserCardCreditLog();
                    log.setCreateTime(DateUtil.getNow());
                    log.setUserId(info.getUserId());
                    log.setReqParams(JSONObject.toJSONString(linkfaceRequest));
                    log.setReturnParams(result);

                    JSONObject resultJson = JSONObject.parseObject(result);
                    if (null != resultJson
                            && StringUtil.isNotBlank(resultJson.getInteger("code"))
                            && 200 == resultJson.getInteger("code")) {
                        JSONObject data = JSONObject.parseObject(StringUtil.isNull(resultJson.get("data")));
                        String score = data.getString("confidence");
                        match = Double.valueOf(score);
                        log.setResult(String.valueOf(resultJson.getInteger("code")));
                        log.setConfidence(String.valueOf(match));
                    } else {
                        BusinessExceptionMonitor.add(BusinessExceptionMonitor.TYPE_8, user.getId(), StringUtil.isNull(resultJson.get("message")));
                    }
                    userCardCreditLogService.insert(log);
                    logger.info("用户" + user.getLoginName() + "完善个人信息，进行人证识别比对，比对值为:" + match);
                } else if (30 == Integer.parseInt(userAuth.getIdState())) {
                    match = Global.getDouble("idCard_credit_pass_rate");
                }

                logger.info("用户" + user.getLoginName() + "完善个人信息，人证识别比对最终值为：" + match);

                if (match >= Global.getDouble("idCard_credit_pass_rate")) {
                    int count = userBaseInfoService.updateById(info);
                    returnMap.put("idState", "30");
                    returnMap.put("userId", userId);
                    userAuthService.updateByUserId(returnMap);
                    returnMap.clear();
                    if (count > 0) {
                        returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
                        returnMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
                    } else {
                        returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                        returnMap.put(Constant.RESPONSE_CODE_MSG, "系统错误，保存失败");
                    }
                } else {
                    returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                    returnMap.put(Constant.RESPONSE_CODE_MSG, "认证失败，请重新认证");
                }
            }
        } else {
            // 信息校验失败,返回前台处理
            returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            returnMap.put(Constant.RESPONSE_CODE_MSG, "处理失败，身份信息已存在");
        }
        return returnMap;
    }


    /**
     * 商汤2.0身份证OCR识别，上传数据文件，该文件需要APP中的SDK 生成
     *
     * @param byteFront 正面身份证数据流
     * @param byteBack  反面身份证数据流
     * @throws Exception
     */
    @RequestMapping(value = "/api/act/mine/userInfo/apiLinkfaceIDOcrRequest.htm")
    public void apiLinkfaceIDOcrRequest(
            @RequestParam(value = "byteFront", required = false) MultipartFile byteFront,
            @RequestParam(value = "byteBack", required = false) MultipartFile byteBack
    ) throws Exception {
    	final String VERIFY_TYPE = Global.getValue("verify_type");
    	Map<String, Object> map = new HashMap<String, Object>();
    	Map<String, Object> resultMap = new HashMap<>();
    	if("30".equals(VERIFY_TYPE)){
    		final String APIKEY = Global.getValue("face++_api_key");
            final String SECRETKEY = Global.getValue("face++_secret_key");
            final String LINKFACEHOST = Global.getValue("face_ocr_url");
            
            String result = CreditLoanFace.ocridcard(LINKFACEHOST, APIKEY, SECRETKEY,Base64.encode(byteFront.getBytes()));
            logger.info("ocr返回结果-->" + result);

            JSONObject resultJson = JSONObject.parseObject(result);
            if (StringUtil.isNotBlank(resultJson)) {
                String errorMsg = resultJson.getString("error_message");
                if (StringUtils.isNotBlank(errorMsg)) {
                    String userId = request.getSession().getAttribute("userId").toString();
                    logger.error("用户 {} 请求Face++ 认证身份证失败，原因： {}", userId, errorMsg);
                    resultMap.put("msg", result);
                    map.put(Constant.RESPONSE_DATA, resultMap);
                    map.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                    map.put(Constant.RESPONSE_CODE_MSG, "保存失败");
                } else {
                    JSONArray cardes = JSON.parseArray(StringUtil.isNull(resultJson.get("cards")));
                    if (StringUtil.isNotBlank(cardes.get(0))) {
                        resultMap.put("name", cardes.getJSONObject(0).getString("name"));
                        resultMap.put("idNum", cardes.getJSONObject(0).getString("id_card_number"));
                        resultMap.put("address", cardes.getJSONObject(0).getString("address"));
                    }
                }
            }
    	}else if("40".equals(VERIFY_TYPE)){
    		final String rong360_url = Global.getValue("rong360_url");
    		final String rong360_appid = Global.getValue("rong360_appid");
    		final String rong360_privatekey=Global.getValue("rong360_privatekey");
    		String result = CreditLoanFace.rong360crIDcard(rong360_url,rong360_appid,rong360_privatekey,Base64.encode(byteFront.getBytes()));		
    		logger.info("ocr返回结果-->" + result);
			String userId = request.getSession().getAttribute("userId").toString();
            JSONObject resultJson = JSONObject.parseObject(result);
            if (StringUtil.isNotBlank(resultJson)) {
                String errorMsg = resultJson.getString("msg");
                if (StringUtils.isNotBlank(errorMsg)) {
                    
                    logger.error("用户 {} 请求融360 认证身份证失败，原因： {}", userId, errorMsg);
                    resultMap.put("msg", result);
                    map.put(Constant.RESPONSE_DATA, resultMap);
                    map.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                    map.put(Constant.RESPONSE_CODE_MSG, "保存失败");
                } else {
                	JSONObject cardes = JSONObject.parseObject(StringUtil.isNull(resultJson.get("tianji_api_faceplus_idcardocr_response")));
                    if (null!=cardes) {
						Map<String, Object> userMap = new HashMap<String, Object>();
						userMap.put("userId", userId);
						UserBaseInfo info = userBaseInfoService.findSelective(userMap);
						Map<String, Object> paramMap = new HashMap<String, Object>();

                        String addressOfId = cardes.getString("address");  //身份证地址
						try {
                            // FIXME: 03/11/2017 身份证 地址, 拆分
                            String province = StringUtils.EMPTY;
                            String city = StringUtils.EMPTY;
                            String county = StringUtils.EMPTY;

                            //省
                            if (addressOfId.contains(ZI_ZHI_QU)) {
                                province = StringUtils.substringBefore(addressOfId, ZI_ZHI_QU) + ZI_ZHI_QU;
                            } else if (addressOfId.contains(PROVINCE)) {
                                province = StringUtils.substringBefore(addressOfId, PROVINCE) + PROVINCE;
                            }

                            if (addressOfId.contains(PROVINCE) && addressOfId.contains(CITY)) {
                                city = StringUtils.substringBetween(addressOfId, PROVINCE, CITY) + CITY;
                            } else if (addressOfId.contains(ZI_ZHI_QU) && addressOfId.contains(CITY)) {
                                city = StringUtils.substringBetween(addressOfId, ZI_ZHI_QU, CITY) + CITY;
                            } else {
                                if (addressOfId.contains(CITY)) {
                                    city = StringUtils.substringBefore(addressOfId, CITY) + CITY;
                                }
                            }

                            //如果地址里不含有  市, 直接找县
                            if (!addressOfId.contains(CITY)) {
                                if (addressOfId.contains(PROVINCE)) {
                                    county = StringUtils.substringBetween(addressOfId, PROVINCE, COUNTY);
                                } else if (addressOfId.contains(ZI_ZHI_QU)){
                                    city = StringUtils.substringBetween(addressOfId, ZI_ZHI_QU, COUNTY);
                                }
                            } else {
                                String addrAfterCity = StringUtils.substringAfter(addressOfId, CITY);
                                if (addrAfterCity.contains(COUNTY)) {
                                    county = StringUtils.substringBefore(addrAfterCity, COUNTY);
                                } else if (addrAfterCity.contains(DISTINCT)) {
                                    county = StringUtils.substringBefore(addrAfterCity, DISTINCT);
                                } else if (addrAfterCity.contains(QI)) {
                                    county = StringUtils.substringBefore(addrAfterCity, QI);
                                }
                            }

                            paramMap.put("idProvince", province);
                            paramMap.put("idCity", city);
                            paramMap.put("idCounty", county);

                            logger.info(">>>>>>>>>>>>身份证地址解析结果: " + province + "; " + city + "; " + county);
                        } catch (Exception e) {
						    logger.error("身份证地址拆分异常: " + addressOfId, e);
                        }

                        paramMap.put("idAddr", addressOfId);
						paramMap.put("id", info.getId());
						userBaseInfoService.updateSelective(paramMap);
						
						resultMap.put("name", cardes.getString("name"));
                        resultMap.put("idNum", cardes.getString("id_card_number"));
                        resultMap.put("address", addressOfId);
                    }
                }
            }
    	}
        map.put(Constant.RESPONSE_DATA, resultMap);
        map.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        map.put(Constant.RESPONSE_CODE_MSG, "保存成功");
        ServletUtils.writeToResponse(response, map);
    }

    /**
     * @param byteLiving 自拍数据流
     * @param livingImg  自拍
     * @param frontImg   正面
     * @param backImg    背面
     * @param name       名字
     * @param idCard     身份证号
     * @param education  学历
     * @param liveAddr   居住地址
     * @param detailAddr 居住详细地址
     * @return void
     * @throws Exception
     * @description 人证对比，上传数据文件，该文件需要APP中的SDK 生成
     */
    @RequestMapping(value = "/api/act/mine/userInfo/apiLinkfaceliRequest.htm")
    public void apiLinkfaceliRequest(
            @RequestParam(value = "byteLiving", required = false) MultipartFile byteLiving,
            @RequestParam(value = "livingImg", required = false) MultipartFile livingImg,
            @RequestParam(value = "frontImg", required = false) MultipartFile frontImg,
            @RequestParam(value = "backImg", required = false) MultipartFile backImg,
            @RequestParam(value = "realName", required = true) String realName,
            @RequestParam(value = "idNo", required = true) String idNo,
            @RequestParam(value = "education", required = true) String education,
            @RequestParam(value = "liveAddr", required = true) String liveAddr,
            @RequestParam(value = "detailAddr", required = true) String detailAddr,
            @RequestParam(value = "userId", required = true) String userId,
            @RequestParam(value = "liveCoordinate", required = true) String liveCoordinate)
            throws Exception {
        final String APIKEY = Global.getValue("apikey");
        final String SECRETKEY = Global.getValue("secretkey");
        final String LINKFACEHOST = Global.getValue("linkface_liVerification");

        Map<String, Object> returnMap = new HashMap<String, Object>();
        Map<String, Object> infoMap = new HashMap<String, Object>();
        infoMap.put("idNo", idNo);
        UserBaseInfo info = userBaseInfoService.findSelective(infoMap);
        if (info == null) {
            infoMap.clear();
            infoMap.put("userId", userId);
            info = userBaseInfoService.findSelective(infoMap);
        }

        if (info != null
                && info.getUserId().toString().equals(userId)
                && (StringUtil.isBlank(info.getIdNo()) || info.getIdNo()
                .equals(idNo))) {
            if (!userCardCreditLogService.isCanCredit(info.getUserId())) {
                returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                returnMap.put(Constant.RESPONSE_CODE_MSG, "今日请求认证次数已超过限制,请明日再尝试");
            } else {
                // 信息校验成功,继续业务处理
                // 根据userId获取user表里面的login_name并存入baseinfo表里面的phone字段
                User user = new User();//cloanUserService.getById(NumberUtil.getLong(userId));
                info.setPhone(user.getLoginName());
                info.setRealName(realName);
                info.setSex(StringUtil.getSex(idNo));
                info.setAge(StringUtil.getAge(idNo));
                info.setIdNo(idNo);
                info.setEducation(education);
                info.setLiveDetailAddr(detailAddr);
                info.setLiveAddr(liveAddr);
                info.setLiveCoordinate(liveCoordinate);

                Map<String, Object> userAuthMap = new HashMap<String, Object>();
                userAuthMap.put("userId", userId);
                UserAuth userAuth = userAuthService.getUserAuth(userAuthMap);

                double match = 0.0;
                if (livingImg != null && 30 != Integer.parseInt(userAuth.getIdState())) {
                    logger.info("用户" + user.getLoginName() + "完善个人信息，进入人证识别比对");
                    response.setContentType("text/html;charset=utf8");
                    List<UploadFileRes> list = new LinkedList<>();
                    saveMultipartFile(list, livingImg, info.getPhone());
                    saveMultipartFile(list, frontImg, info.getPhone());
                    saveMultipartFile(list, backImg, info.getPhone());
                    saveFile(list, byteLiving);
                    info.setLivingImg(list.get(0).getResPath());
                    info.setFrontImg(list.get(1).getResPath());
                    info.setBackImg(list.get(2).getResPath());
                    info.setOcrImg(list.get(3).getResPath());

                    long timestamp = new Date().getTime();
                    Header header = new Header(APIKEY, timestamp);
                    LinkfaceHsRequest linkfaceRequest = new LinkfaceHsRequest(LINKFACEHOST, header);

                    linkfaceRequest.setLivingImg(new File(list.get(3).getResPath()));
                    linkfaceRequest.setName(realName);
                    linkfaceRequest.setIdCard(idNo);

                    linkfaceRequest.signByKey(SECRETKEY);

                    String result = linkfaceRequest.request();
                    logger.info("用户" + user.getLoginName() + "完善个人信息，进行人证识别比对，result为:" + result);

                    UserCardCreditLog log = new UserCardCreditLog();
                    log.setCreateTime(DateUtil.getNow());
                    log.setUserId(info.getUserId());
                    log.setReqParams(JSONObject.toJSONString(linkfaceRequest));
                    log.setReturnParams(result);

                    JSONObject resultJson = JSONObject.parseObject(result);
                    JSONObject data = JSONObject.parseObject(StringUtil.isNull(resultJson.get("data")));
                    if (null != resultJson
                            && StringUtil.isNotBlank(resultJson.get("code"))
                            && 200 == (Integer) resultJson.get("code")) {
                        match = data.getDoubleValue("verify_score");
                        log.setResult(String.valueOf(resultJson.get("code")));
                        log.setConfidence(String.valueOf(match));
                    }
                    userCardCreditLogService.insert(log);
                    logger.info("用户" + user.getLoginName() + "完善个人信息，进行人证识别比对，比对值为:" + match);
                } else if (30 == Integer.parseInt(userAuth.getIdState())) {
                    match = Global.getDouble("idCard_credit_pass_rate");
                }

                logger.info("用户" + user.getLoginName() + "完善个人信息，人证识别比对最终值为：" + match);

                if (match >= Global.getDouble("idCard_credit_pass_rate")) {
                    int count = userBaseInfoService.updateById(info);
                    returnMap.put("idState", "30");
                    returnMap.put("userId", userId);
                    userAuthService.updateByUserId(returnMap);
                    returnMap.clear();
                    if (count > 0) {
                        returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
                        returnMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
                    } else {
                        returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                        returnMap.put(Constant.RESPONSE_CODE_MSG, "系统错误，保存失败");
                    }
                } else {
                    returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                    returnMap.put(Constant.RESPONSE_CODE_MSG, "认证失败，请重新认证");
                }
            }
        } else {
            // 信息校验失败,返回前台处理
            returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            returnMap.put(Constant.RESPONSE_CODE_MSG, "处理失败，身份信息已存在");
        }
        ServletUtils.writeToResponse(response, returnMap);
    }

    /**
     * 工作信息保存
     *
     * @param userId
     * @param companyName
     * @param companyPhone
     * @param companyAddr
     * @param companyDetailAddr
     * @param companyCoordinate
     * @param workingYears
     */
    @RequestMapping(value = "/api/act/mine/workInfo/saveOrUpdate.htm", method = RequestMethod.POST)
    public void workInfoSave(
            @RequestParam(value = "userId", required = true) String userId,
            @RequestParam(value = "companyName", required = false) String companyName,
            @RequestParam(value = "companyPhone", required = false) String companyPhone,
            @RequestParam(value = "companyAddr", required = false) String companyAddr,
            @RequestParam(value = "companyDetailAddr", required = false) String companyDetailAddr,
            @RequestParam(value = "companyCoordinate", required = false) String companyCoordinate,
            @RequestParam(value = "workingYears", required = false) String workingYears) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        boolean isAuth = userAuthService.findAuthState(Long.parseLong(userId));
        if (!isAuth) {
            resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            resultMap.put(Constant.RESPONSE_CODE_MSG, "请先完成必要认证信息");
            ServletUtils.writeToResponse(response, resultMap);
            return;
        }

        String increaseCredit = Global.getValue("credit_increase_byAuth");
        if (StringUtils.isEmpty(increaseCredit)) {
            logger.error("系统配置项 credit_increase_byAuth 为空, 请联系后台管理员.");
            increaseCredit = "0.0";
        }

        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put("userId", userId);
        UserBaseInfo info = userBaseInfoService.findSelective(userMap);

        UserAuth userAuth = userAuthService.findSelective(Long.parseLong(userId));
        boolean isCredit = true;
        if ("30".equals(userAuth.getWorkInfoState())) {//工作信息认证完成
            isCredit = false;
        }


        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("companyName", companyName);
        paramMap.put("companyPhone", companyPhone);
        paramMap.put("companyAddr", companyAddr);
        paramMap.put("companyDetailAddr", companyDetailAddr);
        paramMap.put("companyCoordinate", companyCoordinate);
        paramMap.put("workingYears", workingYears);
        paramMap.put("id", info.getId());
        boolean flag = userBaseInfoService.updateSelective(paramMap);

        if (flag) {
            Map<String, Object> authMap = new HashMap<String, Object>();
            authMap.put("userId", userId);
            authMap.put("workInfoState", UserAuthModel.STATE_VERIFIED);
            userAuthService.updateByUserId(authMap);
            if (isCredit) {//提额
                Credit c = creditMapper.findByConsumerNo(userId.toString());
                Double total = c.getTotal();
                Double unuse = c.getUnuse();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("consumerNo", userId);
                map.put("total", total + Double.parseDouble(increaseCredit));
                map.put("unuse", unuse + Double.parseDouble(increaseCredit));
                creditMapper.updateByUserId(map);
            }
        }

        if (flag) {
            resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            resultMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
        } else {
            resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            resultMap.put(Constant.RESPONSE_CODE_MSG, "系统错误，保存失败");
        }
        ServletUtils.writeToResponse(response, resultMap);
    }

    @RequestMapping(value = "/api/act/mine/workInfo/workImgSave.htm", method = RequestMethod.POST)
    public void workImgSava(
            @RequestParam(value = "userId", required = true) String userId,
            @RequestParam(value = "workImgFir", required = false) MultipartFile workImgFir,
            @RequestParam(value = "workImgSec", required = false) MultipartFile workImgSec,
            @RequestParam(value = "workImgThr", required = false) MultipartFile workImgThr) {
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put("userId", userId);
        UserBaseInfo info = userBaseInfoService.findSelective(userMap);

        // 判断是否已经添加过 ，如果已经添加过 则将原图片路径添加过
        String workImg = "";
        if (null != info && StringUtil.isNotBlank(info.getWorkingImg())) {
            workImg = StringUtil.isNull(info.getWorkingImg());
        }

        StringBuilder buffer = new StringBuilder(workImg);

        if (workImgFir != null) {
            List<UploadFileRes> list = new LinkedList<>();
            response.setContentType("text/html;charset=utf8");
            saveMultipartFile(list, workImgFir, info.getPhone());
            buffer.append(list.get(0).getResPath()).append(";");
        }

        if (workImgSec != null) {
            List<UploadFileRes> list = new LinkedList<>();
            response.setContentType("text/html;charset=utf8");
            saveMultipartFile(list, workImgSec, info.getPhone());
            buffer.append(list.get(0).getResPath()).append(";");
        }

        if (workImgThr != null) {
            List<UploadFileRes> list = new LinkedList<>();
            response.setContentType("text/html;charset=utf8");
            saveMultipartFile(list, workImgThr, info.getPhone());
            buffer.append(list.get(0).getResPath()).append(";");
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("workingImg", StringUtil.isNull(buffer));
        if (info != null) {
            paramMap.put("id", info.getId());
        }
        boolean flag = userBaseInfoService.updateSelective(paramMap);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (flag) {
            resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            resultMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
        } else {
            resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            resultMap.put(Constant.RESPONSE_CODE_MSG, "系统错误，保存失败");
        }
        ServletUtils.writeToResponse(response, resultMap);
    }

    private void saveMultipartFile(List<UploadFileRes> list, MultipartFile file, String phone) {
        if (!file.isEmpty()) {
            UploadFileRes model = save(file, phone);
            list.add(model);
        }
    }

    private UploadFileRes save(MultipartFile file, String phone) {
        UploadFileRes model = new UploadFileRes();
        model.setCreateTime(DateUtil.getNow());

        // 文件名称
        String picName = phone + "_" + file.getOriginalFilename();

        CommonsMultipartFile cf = (CommonsMultipartFile) file;
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        File newFile = (File) fi.getStoreLocation();
        logger.debug("图片----------" + newFile);
        // 文件格式
        String fileType = FileTypeUtil.getFileType(newFile);
        if (StringUtil.isBlank(fileType) || !FileTypeUtil.isImage(newFile, fileType)) {
            model.setErrorMsg("图片格式错误或内容不规范");
            return model;
        }
        // 校验图片大小
        Long picSize = file.getSize();
        if (picSize.compareTo(20971520L) > 0) {
            model.setErrorMsg("文件超出20M大小限制");
            return model;
        }
        // 保存文件
        String basepath = this.getClass().getResource("/").getPath();
        String s = File.separator;
        String filePath = basepath + s + "data" + s + "image" + s + "faceID" + s +
                DateUtil.dateStr(DateUtil.getNow(), DateUtil.DATEFORMAT_STR_013) + s + picName;
        File files = new File(filePath);
        if (!files.exists()) {
            try {
                files.mkdirs();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                model.setErrorMsg("文件目录不存在");
                return model;
            }
        }
        try {
            file.transferTo(files);
        } catch (IllegalStateException | IOException e) {
            logger.error(e.getMessage(), e);
        }
        // 转存文件
        model.setResPath(filePath);
        model.setFileName(picName);
        model.setFileFormat(fileType);
        model.setFileSize(new BigDecimal(picSize));
        return model;
    }

    /**
     * @param info
     * @param userId
     * @return void
     * @description 保存用户通讯录信息
     * @author
     * @since 1.0.0
     */
    @SuppressWarnings({"unchecked"})
    @RequestMapping(value = "/api/act/mine/userInfo/contacts.htm", method = RequestMethod.POST)
    public void contacts(
            @RequestParam(value = "info", required = true) String encodedInfo,
            @RequestParam(value = "userId", required = true) String userId) {
        String info = new String(Base64.decode(encodedInfo));
        List<Map<String, Object>> infos = JsonUtil.parse(info, List.class);
        boolean flag = userContactsService.deleteAndSave(infos, userId);
        Map<String, Object> returnMap = new HashMap<String, Object>();
        if (flag) {
            returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            returnMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
        } else {
            returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            returnMap.put(Constant.RESPONSE_CODE_MSG, "保存失败");
        }
        ServletUtils.writeToResponse(response, returnMap);
    }

    /**
     * @param info
     * @param userId
     * @return void
     * @throws ParseException
     * @description 保存用户短信信息
     * @author
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/api/act/mine/userInfo/messages.htm", method = RequestMethod.POST)
    public void messages(
            @RequestParam(value = "info", required = true) String encodedInfo,
            @RequestParam(value = "userId", required = true) String userId)
            throws ParseException {
        String info = new String(Base64.decode(encodedInfo));
        List<Map<String, Object>> infos = JsonUtil.parse(info, List.class);
        for (Map<String, Object> map : infos) {
            UserMessages clUserMessages = new UserMessages();
            clUserMessages.setName(map.get("name") + "");
            clUserMessages.setPhone(map.get("phone") + "");
            clUserMessages.setTime(new Date(
                    Long.parseLong(map.get("time") + "")));
            clUserMessages.setType(map.get("type") + "");
            clUserMessages.setUserId(Long.parseLong(userId));
            userMessagesService.insert(clUserMessages);
        }
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        returnMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
        ServletUtils.writeToResponse(response, returnMap);
    }

    /**
     * @param userId
     * @return void
     * @description 获取验证状态
     * @author
     * @since 1.0.0
     */
    @RequestMapping(value = "/api/act/mine/userAuth/getUserAuth.htm", method = RequestMethod.GET)
    public void getUserAuth(
            @RequestParam(value = "userId", required = true) String userId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        UserAuth userAuth = userAuthService.getUserAuth(paramMap);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_DATA, userAuth);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response, result);
    }


    @RequestMapping(value = "/api/act/mine/userAuth/getAuthImgLogo.htm", method = RequestMethod.GET)
    public void getAuthImgLogo() {

        String serverHost = Global.getValue("server_host");
        String path = request.getSession().getServletContext().getRealPath("/");
        StringBuilder buffer = new StringBuilder(path)
                .append(File.separatorChar).append("static")
                .append(File.separatorChar).append("images")
                .append(File.separator).append("authImgLogo.png");

        Map<String, Object> data = new HashMap<>();
        String filePath = StringUtil.isNull(buffer);
        data.put("authImgLogo", serverHost + "/readFile.htm?path=" + filePath);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_DATA, data);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response, result);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/api/act/mine/userAuth/accFundRequest.htm", method = RequestMethod.GET)
    public void shuliOperatorRequest() throws Exception {
//        final String APIHOST = "http://ucdevapi.ucredit.erongyun.net/credit/api/v1.5/query";
        final String APIHOST = Global.getValue("acc_fund_apihost");
        final String APIKEY = Global.getValue("apikey");
        final String SECRETKEY = Global.getValue("secretkey");
        final String channelNo = "CH0673607634";
        final String interfaceName = "rongduAccfundUrl";
        long timestamp = new Date().getTime();
        Header header = new Header(APIKEY, channelNo, interfaceName, timestamp);
        CreditRequest creditRequest = new CreditRequest(APIHOST, header);
        Map<String, Object> payload = new HashMap<>();
        payload.put("prodCode", "ACCUFUND");
        creditRequest.setPayload(payload);
        creditRequest.signByKey(SECRETKEY);
        String result = creditRequest.request();
        Long userId = Long.parseLong(request.getSession().getAttribute("userId") + "");
        logger.info("用户：" + userId + "的公积金认证返回结果：" + result);
        /**
         * 处理返回信息
         */
        Map<String, Object> respMap = new HashMap<String, Object>();
        if (result != null) {

            Map<String, Object> resultMap = JsonUtil.parse(result, Map.class);
            OperatorReqLog operatorReqLog = new OperatorReqLog(userId, String.valueOf(resultMap.get("orderNo")), String.valueOf(resultMap.get("code")));
            operatorReqLog.setRespTime(DateUtil.getNow());
            operatorReqLog.setRespCode(String.valueOf(resultMap.get("code")));
            operatorReqLog.setRespParams(result);
            operatorReqLog.setBusinessType("20");
            operatorReqLogService.insert(operatorReqLog);

            Map<String, Object> res = (Map<String, Object>) resultMap.get("res");
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("url", res.get("url"));
            respMap.put(Constant.RESPONSE_DATA, data);
            respMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            respMap.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        } else {
            respMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            respMap.put(Constant.RESPONSE_CODE_MSG, "获取失败");
        }
//        ServletUtils.writeToResponse(response, result);
//        JsonUtil.writeJson(respMap, response);
        writeToResponseApp(respMap);
    }

    private void writeToResponseApp(Map<? extends Object, Object> res) {
        response.addHeader("content-type", "text/javascript");

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        OutputStreamWriter out = null;
        try {
            out = new OutputStreamWriter(
                    response.getOutputStream(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("系统出错：" + e.getMessage());
        } catch (IOException e) {
            logger.error("系统出错：" + e.getMessage());
        }
        JsonUtil.write(out, res);
    }


    private File getRemoteFile(String path) throws IOException {
        File file = new File(path);
        return file;
    }

    /**
     * 引流判断
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/api/act/needDivert.htm")
    public void needDivert(final HttpServletRequest request,
                           HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        String userId = (String) request.getSession().getAttribute("userId");
        String able = Global.getValue("divert_able");
        //判断用户是否登录
        if (userId != null) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("userId", userId);
            //获得用户借款数据
            Borrow borrow = clBorrowService.findLastBorrow(paramMap);
            //判断是否启用引流和审核通过
            if (borrow != null && "10".equals(able) && ("21".equals(borrow.getState()) || "27".equals(borrow.getState()))) {
                //不通过则引流
                dataMap.put("state", "10");
                dataMap.put("url", Global.getValue("divert_url"));
            } else {
                //通过则不需要引流
                dataMap.put("state", "20");
            }
            result.put(Constant.RESPONSE_DATA, dataMap);
            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
        } else {
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "查询失败");
        }
        ServletUtils.writeToResponse(response, result);
    }

    private void saveFile(List<UploadFileRes> list, MultipartFile file) {
        if (!file.isEmpty()) {
            UploadFileRes model = saveFile(file);
            list.add(model);
        }
    }

    private UploadFileRes saveFile(MultipartFile file) {
        UploadFileRes model = new UploadFileRes();
        model.setCreateTime(DateUtil.getNow());

        // 文件名称
        String picName = file.getOriginalFilename();

        CommonsMultipartFile cf = (CommonsMultipartFile) file;
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        File newFile = (File) fi.getStoreLocation();
        logger.info("图片----------" + newFile);
        // 校验图片大小
        Long picSize = file.getSize();
        if (picSize.compareTo(20971520L) > 0) {
            model.setErrorMsg("文件超出20M大小限制");
            return model;
        }
        // 保存文件
        String s = File.separator;
        String filePath = s + "data" + s + "image" + s + s + System.currentTimeMillis() + s + picName;
        File files = new File(filePath);
        if (!files.exists()) {
            try {
                files.mkdirs();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                model.setErrorMsg("文件目录不存在");
                return model;
            }
        }
        try {
            file.transferTo(files);
        } catch (IllegalStateException | IOException e) {
            logger.error(e.getMessage(), e);
        }
        // 转存文件
        model.setResPath(filePath);
        model.setFileName(picName);
        model.setFileSize(new BigDecimal(picSize));
        return model;
    }

    private static final String PROVINCE = "省";
    private static final String ZI_ZHI_QU = "自治区";
    private static final String CITY = "市";

    private static final String COUNTY = "县";
    private static final String DISTINCT = "区";
    private static final String QI = "旗";


    //======= UNIT TEST -======
    public static void main(String[] args) {
        String addressOfId = "西安市户县秦波镇新农村9号";
//        String addressOfId = "湖北省天门市张港镇增加村一组29号";
//        String addressOfId = "江苏省盐城市盐都区楼王镇文华路198号";
//        String addressOfId = "上海市虹口区场中路842弄32号602室";
//        String addressOfId = "哈尔滨市道外区南十七道街41号";
//        String addressOfId = "江西省上饶市婺源县浙源乡凤山村凤山38号";
//        String addressOfId = "安徽省六安市金安区双河镇雨淋岗村栗树组";
//        String addressOfId = "陕西省三原县陂西镇街道三桥村";

        String province = StringUtils.EMPTY;
        String city = StringUtils.EMPTY;
        String county = StringUtils.EMPTY;

        //省
//        if (addressOfId.contains(ZI_ZHI_QU)) {
//            province = StringUtils.substringBefore(addressOfId, ZI_ZHI_QU) + ZI_ZHI_QU;
//        } else if (addressOfId.contains(PROVINCE)) {
//            province = StringUtils.substringBefore(addressOfId, PROVINCE) + PROVINCE;
//        }
//
//        if (addressOfId.contains(PROVINCE) && addressOfId.contains(CITY)) {
//            city = StringUtils.substringBetween(addressOfId, PROVINCE, CITY) + CITY;
//        } else if (addressOfId.contains(ZI_ZHI_QU) && addressOfId.contains(CITY)) {
//            city = StringUtils.substringBetween(addressOfId, ZI_ZHI_QU, CITY) + CITY;
//        } else {
//            if (addressOfId.contains(CITY)) {
//                city = StringUtils.substringBefore(addressOfId, CITY) + CITY;
//            }
//        }
//
//        //如果地址里不含有  市, 直接找县
//        if (!addressOfId.contains(CITY)) {
//            if (addressOfId.contains(PROVINCE)) {
//                county = StringUtils.substringBetween(addressOfId, PROVINCE, COUNTY);
//            } else if (addressOfId.contains(ZI_ZHI_QU)){
//                city = StringUtils.substringBetween(addressOfId, ZI_ZHI_QU, COUNTY);
//            }
//        } else {
//            String addrAfterCity = StringUtils.substringAfter(addressOfId, CITY);
//            if (addrAfterCity.contains(COUNTY)) {
//                county = StringUtils.substringBefore(addrAfterCity, COUNTY);
//            } else if (addrAfterCity.contains(DISTINCT)) {
//                county = StringUtils.substringBefore(addrAfterCity, DISTINCT);
//            } else if (addrAfterCity.contains(QI)) {
//                county = StringUtils.substringBefore(addrAfterCity, QI);
//            }
//        }


        System.out.println("province : " + province);
        System.out.println("city : " + city);
        System.out.println("county : " + county);

    }


}
