package com.xindaibao.cashloan.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.CacheUtil;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.system.permission.annotation.RequiresPermission;

/**
 * API System Controller
 * 
 * @author
 * @version 1.0.0
 * @date 2017年4月29日 下午3:47:24



 */

@Controller
@Scope("prototype")
public class SysController extends BaseController {

    /**
     * 重加载系统配置数据
     * 
     * @throws Exception
     */
    @RequestMapping("/system/config/reload.htm")
    @RequiresPermission(code = "system:config:reload", name = "系统管理-系统参数设置-缓存数据重加载")
    public void reload() throws Exception {
        // 调用缓存辅助类 重加载系统配置数据
        CacheUtil.initSysConfig();
        
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, returnMap);
    }
    
}
