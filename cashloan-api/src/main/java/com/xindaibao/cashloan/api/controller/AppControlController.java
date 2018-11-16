package com.xindaibao.cashloan.api.controller;

import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.system.domain.SysConfig;
import com.xindaibao.cashloan.system.service.SysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于后台控制 APP 显示模块
 *
 */

@Slf4j
@Scope("prototype")
@Controller
@RequestMapping(value = "/app-conf/")
public class AppControlController extends BaseController {

    private static final String CONFIG_CODE = "is_ios_show_bank_card"; //os 端根据字段控制“绑定银行卡”模块的显示和隐藏. (0隐藏, 1显示)

    @Autowired private SysConfigService sysConfigService;

    @RequestMapping(value = "/ios-is-show-bk.htm")
    public void iosIsShowBankCardModule() {
        try {
            //0隐藏, 1显示
            SysConfig sysConfig = sysConfigService.findByCode(CONFIG_CODE);

            String value = sysConfig == null ? "0" : sysConfig.getValue();

            Map<String, Object> temp = new HashMap<String, Object>();
            temp.put("is_ios_show_bank_card", value);

            Map<String, Object> result = new HashMap<String, Object>();
            result.put(Constant.RESPONSE_DATA, temp);
            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "获取成功");

            ServletUtils.writeToResponse(response, result);
        } catch (Exception e) {
            log.error("Exception ...", e);

            Map<String, Object> temp = new HashMap<String, Object>();
            temp.put("is_ios_show_bank_card", "0");

            Map<String, Object> result = new HashMap<String, Object>();
            result.put(Constant.RESPONSE_DATA, temp);
            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
            ServletUtils.writeToResponse(response, result);
        }
    }
}
