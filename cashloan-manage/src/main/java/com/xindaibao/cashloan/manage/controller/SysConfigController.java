package com.xindaibao.cashloan.manage.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.core.common.util.CacheUtil;
import com.xindaibao.cashloan.core.common.util.DateUtil;
import com.xindaibao.cashloan.core.common.util.HttpUtil;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.PropertiesUtil;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.util.StringUtil;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.system.constant.SystemConstant;
import com.xindaibao.cashloan.system.domain.SysConfig;
import com.xindaibao.cashloan.system.domain.SysUser;
import com.xindaibao.cashloan.system.model.SysConfigModel;
import com.xindaibao.cashloan.system.permission.annotation.RequiresPermission;
import com.xindaibao.cashloan.system.service.SysConfigService;
import com.xindaibao.cashloan.system.service.SysDictService;

/**
 * User:    mcwang
 * DateTime:2016-08-04 03:26:22
 * details: 系统参数表,Action请求层
 * source:  代码生成器
 */
@Scope("prototype")
@Controller
public class SysConfigController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(SysConfigController.class);
	@Resource
	private SysConfigService sysConfigService;
	@Resource
	private SysDictService sysDictService;
	
    /**
     * 系统参数表表,插入数据
     * @param response      页面的response
     * @param json          页面参数
     * @throws ServiceException
     */
	@RequestMapping("/modules/manage/system/config/save.htm")
    @RequiresPermission(code = "modules:manage:system:config:save", name = "系统管理-系统参数设置-新增")
    public void saveOrUpdate(HttpServletRequest request,HttpServletResponse response,
    	@RequestParam(value = "json" ,required = false)String json,
    	@RequestParam(value = "status" ,required = false)String status  //执行的动作
    	)throws Exception {
	     Map<String,Object> returnMap=new HashMap<String,Object>();
	     long flag;
     
        SysConfig sysConfig = new SysConfig();
        //对json对象进行转换
        if (!StringUtils.isEmpty(json))
            sysConfig = JsonUtil.parse(json, SysConfig.class);
		if("create".equals(status)){
			  SysUser sysUser = this.getLoginUser(request);
			  sysConfig.setStatus(1);//新建时有效
			  sysConfig.setCreator(sysUser.getId());
		//动态插入数据
			flag=sysConfigService.insertSysConfig(sysConfig);
		}else{
		 //修改数据
			flag=sysConfigService.updateSysConfig(sysConfig);
		}
		
		if(flag>0){//判断操作是否成功
        	returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        	returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS+",刷新缓存后生效");
        }else{
        	returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
        	returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
        }
        //返回给页面
        ServletUtils.writeToResponse(response, returnMap);
    }

    

    /**
     * 系统参数表,查询数据
     * @param response      页面的response
     * @param currentPage   当前页数
     * @param pageSize      每页限制
     * @param searchParam   查询条件
     * @param whereSql      直接的sql
     * @param fields        排序字段
     * @param rule          排序方式
     * @throws ServiceException
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/modules/manage/system/config/list.htm")
    @RequiresPermission(code = "modules:manage:system:config:list", name = "系统管理-系统参数设置-查询")
    public void listConfigs ( HttpServletRequest request,HttpServletResponse response,
                        @RequestParam(value = "current") Integer current,
                        @RequestParam(value = "pageSize") Integer pageSize,
                        @RequestParam(value = "searchParams",required = false)String searchParams)
    throws Exception{
		Map<String, Object> paramap = new HashMap<String, Object>();
    	if (!StringUtils.isEmpty(searchParams)){
    		paramap = JsonUtil.parse(searchParams, Map.class);
    	}
    	List<Map<String,Object>> typeList = new ArrayList<Map<String,Object>>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
    	//获取系统参数类型数据字典
    	List<Map<String, Object>> dicList = sysDictService.getDictsCache("SYSTEM_TYPE");
		for (Map<String, Object> dic : dicList) {
    		Map<String, Object> types = new HashMap<String, Object>();
    		types.put("systemType", dic.get("value"));
			types.put("systemTypeName", dic.get("text"));
    		dataMap.put((String) dic.get("value"), dic.get("text"));
    		typeList.add(types);
    	}
    	//返回页面的json参数
		Page<SysConfig> page = sysConfigService.getSysConfigPageList(current,pageSize,paramap);
    	
		List<SysConfigModel> sysModel = new ArrayList<SysConfigModel>();
		if (page != null && !page.isEmpty()) {
			for (SysConfig sys : page) {
				SysConfigModel model = new SysConfigModel();
				model = model.getSysModel(sys, dataMap);
				sysModel.add(model);
			}
		}
		Map<String, Object> returnMap = new HashMap<String,Object>();
    	
    	//返回给页面
    	returnMap.put("dicData", typeList);
    	returnMap.put(Constant.RESPONSE_DATA, sysModel);
    	returnMap.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
    	returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
    	returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);

    	ServletUtils.writeToResponse(response, returnMap);
    }
    
    /**
     * 系统参数表表,逻辑删除 修改状态
     * @param response      页面的response
     * @param json          页面参数
     * @throws ServiceException
     */
    @RequestMapping("/modules/manage/system/config/delete.htm")
    @RequiresPermission(code = "modules:manage:system:config:delete", name = "系统管理-系统参数设置- 修改状态")
    public void deleteSysConfig(HttpServletRequest request,HttpServletResponse response,
    	@RequestParam(value = "id" )String id,
    	@RequestParam(value = "status" )int status
    	)throws Exception {
	     Map<String,Object> returnMap=new HashMap<String,Object>();
	     long flag;
        SysConfig sysConfig = new SysConfig();
		 //修改数据
        	sysConfig.setId(Long.valueOf(id));
        	sysConfig.setStatus(status);
			flag=sysConfigService.updateSysConfig(sysConfig);
		
		if(flag>0){//判断操作是否成功
        	returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        	returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS+",刷新缓存后生效");
        }else{
        	returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
        	returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
        }
        //返回给页面
        ServletUtils.writeToResponse(response, returnMap);
    }
    
    /**
     * 重加载系统配置数据
     * 
     * @throws Exception
     */
    @RequestMapping("/modules/manage/system/config/reload.htm")
    @RequiresPermission(code = "modules:manage:system:config:reload", name = "系统管理-系统参数设置-缓存数据重加载")
    public void reload() throws Exception {
        Map<String, Object> returnMap = new HashMap<String, Object>();

        // 调用缓存辅助类 重加载系统配置数据
        CacheUtil.initSysConfig();


        //前台缓存清理
        String webCleanUrl = Global.getValue("server_host") + "/system/config/reload.htm";
        String webResult = null;
        try {
        	webResult = HttpUtil.getHttpResponse(webCleanUrl);
        	logger.info("刷新api缓存结果:" + webResult);
        } catch (Exception e) {
        	logger.info("刷新api缓存出错");
        	logger.error(e.getMessage(),e);
        }
        
        if(StringUtil.isNotBlank(webResult)){
        	@SuppressWarnings("unchecked")
			Map<String, Object> result = JsonUtil.parse(webResult, Map.class);
			String resultCode = StringUtil.isNull(result.get(Constant.RESPONSE_CODE));
        	if (StringUtil.isNotBlank(resultCode)
        			&& StringUtil.isNull(Constant.SUCCEED_CODE_VALUE).equals(resultCode)) {
        		returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        		returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
        	}else{
        		returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
        		returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
        	}
        }else{
        	returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        	returnMap.put(Constant.RESPONSE_CODE_MSG, "后台缓存刷新完成,前台缓存刷新失败");
        }

        // 返回给页面
        ServletUtils.writeToResponse(response, returnMap);
    }
    
    /**
	 * 检测系统配置数据
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/modules/manage/system/config/testConfiguration.htm")
	// @RequiresPermission(code =
	// "/modules/manage/system/config/testConfiguration.htm", name
	// ="系统管理-系统参数设置-检测配置")
	public void testConfiguration(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "searchParams", required = false) String searchParams) {
		try {
			Map<String, Object> returnMap = new HashMap<String, Object>();
			Map<String, Object> paramap = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(searchParams)) {
				paramap = JsonUtil.parse(searchParams, Map.class);
			}
			List<SysConfig> list = sysConfigService.getList(paramap);

			FileWriter fw = null;
			BufferedWriter bw = null; // 缓冲区输出
			FileReader fr = null;
			BufferedReader br = null; // 缓冲区输入
			String basepath = this.getClass().getResource("/").getPath()+ "configuration";
//			String basepath= "D:\\configuration";
			File folder = new File(basepath);
			folder.mkdirs();
			String fileName = DateUtil.dateStr(new Date(), "yyyyMMddHHmm");
			String path_ = basepath +"/" +fileName;

			fw = new FileWriter(path_ + ".txt"); // 要写入内容 的文件
			bw = new BufferedWriter(fw); // 用缓冲区保存输入的内容
			String content = "";
			List<Map<String, Object>> dataList = new ArrayList<>();
			for (SysConfig sysConfig : list) {
				if (sysConfig.getValue().contains(
						SystemConstant.TEST_CONFIGURATION)) {
					Map<String, Object> mapDate = new HashMap<String, Object>();
					String status = "配置错误";
					content = appendContent(content, sysConfig);
					mapDate.put("code", sysConfig.getCode());
					mapDate.put("value", sysConfig.getValue());
					mapDate.put("status", status);
					dataList.add(mapDate);
				}
			}
			Map<String, Object> toMap = new HashMap<String, Object>();
			ObjectMapper mapper = new ObjectMapper();
			String types = PropertiesUtil.getValue("types");
			String[] codes = types.split(",");
			for (String code : codes) {
				SysConfig configs = sysConfigService.findByCode(code);
				String codevalue = "";
				if (null != configs) {
					codevalue = configs.getValue();
				}
				String sysConfigMap = PropertiesUtil.getValue(code + "_"+ codevalue);
				toMap = mapper.readValue(sysConfigMap,new TypeReference<HashMap<String, String>>() {});
				Map<String, Object> mapDate = new HashMap<String, Object>();
				int status = 0;
				String codeAppen = "";
				for (Entry<String, Object> entry : toMap.entrySet()) {
					SysConfig sysConfig = sysConfigService.findByCode(entry.getKey());
					if (null == sysConfig) {
						continue;
					}
					if (!entry.getValue().toString().contains(sysConfig.getValue())) {
						status++;
						content += DateUtil.dateStr(new Date()) + "【"
								+ code
								+ "】                code:" + entry.getKey()
								+ "       value:" + sysConfig.getValue()
								+ "\r\n";
					}
					codeAppen += "{code:" + sysConfig.getCode() + "     value:"+ sysConfig.getValue() + "},  ";
				}
				if (status > 0) {
					mapDate.put("status", "配置错误");
					mapDate.put("code", code);
					mapDate.put("value", codeAppen);
					dataList.add(mapDate);
				}
			}
			bw.write(content); // 写的内容
			bw.newLine(); // 换一行
			bw.flush(); // 刷新缓冲区
			fr = new FileReader(path_ + ".txt"); // 要注意和你写入的文件名一致
			br = new BufferedReader(fr); // 读缓冲区的内容
			String line = br.readLine();
			while (line != null) {
				System.out.print(line);
				line = br.readLine();
			}
			fr.close(); // 关闭数据流
			fw.close();
			br.close();
			returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			returnMap.put(Constant.RESPONSE_CODE_MSG,
					Constant.OPERATION_SUCCESS);
			returnMap.put(Constant.RESPONSE_DATA, dataList);
			ServletUtils.writeToResponse(response, returnMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String appendContent(String content, SysConfig sysConfig) {
		String status = sysConfig.getStatus() == 1 ? "启用" : "关闭";
		return content += DateUtil.dateStr(new Date()) + ": <"
				+ sysConfig.getName() + ">" + "配置详情：" + "    id:"
				+ sysConfig.getId() + "    type:" + sysConfig.getType()
				+ "    code:" + sysConfig.getCode() + "    value:"
				+ sysConfig.getValue() + "     状态:" + status + "\r\n";
	}
}

