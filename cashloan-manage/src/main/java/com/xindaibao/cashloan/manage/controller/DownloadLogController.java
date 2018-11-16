package com.xindaibao.cashloan.manage.controller;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.system.domain.SysDownloadLog;
import com.xindaibao.cashloan.system.service.SysDownloadLogService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
* 下载日志Controller
*
*
*
*
*/
@Scope("prototype")
@Controller
public class DownloadLogController extends ManageBaseController {

   @Resource
   private SysDownloadLogService sysDownloadLogService;


   /**
    * 查询导出log
    *
    * @throws Exception
    */
   @RequestMapping(value = "/modules/manage/sysDownloadLog/list.htm")
   public void listDownloadLog(
           @RequestParam(value = "search", required = false) String search,
           @RequestParam(value = "current") int current,
           @RequestParam(value = "pageSize") int pageSize) throws Exception {

       Map<String, Object> searchMap = new HashMap<>();
       if (!StringUtils.isEmpty(search)) {
           searchMap = JsonUtil.parse(search, Map.class);
       }

       Page<SysDownloadLog> page = sysDownloadLogService.page(current, pageSize,
               searchMap);

       Map<String, Object> result = new HashMap<String, Object>();
       result.put(Constant.RESPONSE_DATA, page);
       result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
       result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
       result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
       ServletUtils.writeToResponse(response, result);
   }



}
