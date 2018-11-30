package com.xindaibao.cashloan.manage.controller;
import com.xindaibao.cashloan.cl.Util.DataUtil;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.system.domain.SysAppVersion;
import com.xindaibao.cashloan.system.service.SysAppVersionService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* 访问码Controller
*
* @author
* @version 1.0.0
* @date 2017-03-24 17:37:49


*

*/
@Controller
@Scope("prototype")
public class SysAppVersionController extends BaseController {

   @Resource
   private SysAppVersionService sysAppVersionService;

   /**
    * APP版本信息展示
    */
   @SuppressWarnings("unchecked")
   @RequestMapping(value="/modules/manage/user/appVersion/list.htm",method={RequestMethod.GET,RequestMethod.POST})
   public void list(){
       SysAppVersion sysAppVersion  = sysAppVersionService.listSysAppVersion();
       Map<String,Object> result = new HashMap<String,Object>();
       result.put(Constant.RESPONSE_DATA, sysAppVersion);
       result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
       result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
       ServletUtils.writeToResponse(response,result);
   }

   /**
    * 修改APP版本
    */
   @RequestMapping(value="/modules/manage/user/appVersion/save.htm",method={RequestMethod.GET,RequestMethod.POST})
   public void add(SysAppVersion sysAppVersion){
       Map<String,Object> result = new HashMap<String,Object>();
       SysAppVersion appVersion = new SysAppVersion();
       if(DataUtil.isNull(sysAppVersion.getAppCode(),sysAppVersion.getAppName(),sysAppVersion.getAppType(),sysAppVersion.getDownUrl(),sysAppVersion.getForceFlag()
       ,sysAppVersion.getGoogleDownUrl(),sysAppVersion.getPublishUid(),sysAppVersion.getSpreadUrl(),sysAppVersion.getStatus()
       ,sysAppVersion.getVersionCode(),sysAppVersion.getVersionName())){
           result.put(Constant.RESPONSE_CODE_MSG, "获取参数不完整");
           ServletUtils.writeToResponse(response,result);
       }
       appVersion.setAppCode(sysAppVersion.getAppCode());
       appVersion.setAppName(sysAppVersion.getAppName());
       appVersion.setAppType(sysAppVersion.getAppType());
       appVersion.setVersionText(sysAppVersion.getVersionText());
       appVersion.setDownUrl(sysAppVersion.getDownUrl());
       appVersion.setForceFlag(sysAppVersion.getForceFlag());
       appVersion.setGoogleDownUrl(sysAppVersion.getGoogleDownUrl());
       appVersion.setPublishTime(sysAppVersion.getPublishTime());
       appVersion.setPublishUid(sysAppVersion.getPublishUid());
       appVersion.setSpreadUrl(sysAppVersion.getSpreadUrl());
       appVersion.setStatus(sysAppVersion.getStatus());
       appVersion.setVersionName(sysAppVersion.getVersionName());
       appVersion.setVersionCode(sysAppVersion.getVersionCode());
       appVersion.setPublishTime(new Date());
       appVersion.setCreatedTime(new Date());
       appVersion.setUpdatedTime(new Date());
       int msg1 = sysAppVersionService.delete(sysAppVersion);
       if (msg1>0) {
           result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
           result.put(Constant.RESPONSE_CODE_MSG, "删除成功");
       }
       else {
           result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
           result.put(Constant.RESPONSE_CODE_MSG, "删除失败");
       }
       int msg = sysAppVersionService.save(sysAppVersion);
       if (msg>0) {
           result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
           result.put(Constant.RESPONSE_CODE_MSG, "添加成功");
       }
       else {
           result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
           result.put(Constant.RESPONSE_CODE_MSG, "添加失败");
       }
       ServletUtils.writeToResponse(response,result);
   }

}
