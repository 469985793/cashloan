import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xindaibao.cashloan.cl.domain.RcR360BlacklistLog;
import com.xindaibao.cashloan.cl.mapper.RcR360BlacklistLogMapper;
import com.xindaibao.cashloan.cl.sdk.face.R360BlackList;
import com.xindaibao.cashloan.cl.sdk.face.dto.R360BlackListRespDTO;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.service.UserBaseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by sparta on 2017/10/27.
 */
@Slf4j
public class Rong360Test extends BaseTest {

    @Autowired private UserBaseInfoService userBaseInfoService;
    @Autowired private RcR360BlacklistLogMapper rcR360BlacklistLogMapper;

    @Test
    public void queryR360BlackList() {

        //获取手机号
        UserBaseInfo userBaseInfo = new UserBaseInfo();
        userBaseInfo.setIdNo("140502198811102244");
        userBaseInfo.setPhone("13986671110");
        userBaseInfo.setRealName("王亮");

        RcR360BlacklistLog rcR360BlacklistLog = new RcR360BlacklistLog();
        rcR360BlacklistLog.setCreateTime(new Date());
        rcR360BlacklistLog.setUserId(123123L);

        try {
            String request = R360BlackList.request(userBaseInfo);
            JSONObject jsonObject = JSONObject.parseObject(request);
            R360BlackListRespDTO r360BlackListRespDTO = JSON.parseObject(request, R360BlackListRespDTO.class);
            if (r360BlackListRespDTO.isSuccess()) {
                String response = jsonObject.get("tianji_api_agentr_blacklist_response").toString();
                rcR360BlacklistLog.setIsBlack("{}".equalsIgnoreCase(response) ? "false" : "true");
            } else {
                // FIXME: 2017/10/21 请求不为200.设置为 false，走下一个第三方黑名单验证（知道是否是true or false）
                rcR360BlacklistLog.setIsBlack("false");
            }
            rcR360BlacklistLog.setRespCode(r360BlackListRespDTO.getError());
            rcR360BlacklistLog.setRespParams(r360BlackListRespDTO.toString());
        } catch (Exception e) {
            log.error("request rong360 blacklist exception, error={}", e);
            rcR360BlacklistLog.setIsBlack("false");
            rcR360BlacklistLog.setRespParams(e.getMessage());
            rcR360BlacklistLog.setRespCode("9999");
        }
         rcR360BlacklistLogMapper.save(rcR360BlacklistLog);
    }



}
