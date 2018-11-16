import com.alibaba.fastjson.JSON;
import com.bfd.facade.MerchantServer;
import com.xindaibao.cashloan.cl.domain.BaiRongGrade;
import com.xindaibao.cashloan.cl.mapper.BaiRongGradeMapper;
import com.xindaibao.cashloan.cl.mapper.BaiRongLoginRecordMapper;
import com.xindaibao.cashloan.cl.sdk.bairong.model.BaiRongRradeRespDTO;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by sparta on 2017/10/27.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration({"classpath:config/web/web-main.xml", "classpath:config/spring/*.xml"}) //加载配置文件
public class Test {
/*

    @Autowired
    private BaiRongLoginRecordMapper baiRongLoginRecordMapper;
    @Autowired private BaiRongGradeMapper baiRongGradeMapper;

    @org.junit.Test
    public void test(){

        //百融分

        UserBaseInfo userBaseInfo = new UserBaseInfo();
        userBaseInfo.setIdNo("140502198811102244");
        userBaseInfo.setPhone("13986671110");
        userBaseInfo.setRealName("王亮");
        userBaseInfo.setId(123123L);

        MerchantServer ms = new MerchantServer();
        String token = findToken();
        if (StringUtils.isEmpty(token)) {
            token = requestBaiRongTokenId(ms);
            baiRongLoginRecordMapper.updateToken(token);
        }

        BaiRongGrade baiRongGrade = BaiRongGrade.of(userBaseInfo.getId());

        try {
            BaiRongRradeRespDTO respDTO = excuteQuery(token, ms, userBaseInfo);
            log.info("bairong grade response = {}", respDTO.toString());
            if (StringUtils.equals("00", String.valueOf(respDTO.getCode()))){
                BeanUtils.copyProperties(respDTO, baiRongGrade);
            } else if(StringUtils.equals("100002", String.valueOf(respDTO.getCode()))){
                baiRongGrade.setBrcreditpoint(998);
                baiRongGrade.setRemark("查询key值未命中数据库");
            }else {
                baiRongGrade.setBrcreditpoint(999);
                baiRongGrade.setRemark("request code:" + respDTO.getCode());
            }
        } catch (Exception e) {
            log.error("request bairong grade fail, error={}", e);
            baiRongGrade.setBrcreditpoint(999);
            baiRongGrade.setRemark("请求异常，写入默认999分，error=" + e.getMessage());
        }

        baiRongGradeMapper.save(baiRongGrade);
    }

    public String findToken() {
        return baiRongLoginRecordMapper.selectToken();
    }


    private BaiRongRradeRespDTO excuteQuery(String token, MerchantServer ms, UserBaseInfo userBaseInfo) throws Exception {

        //用户报告：
        JSONObject jso = new JSONObject();
        JSONObject reqData = new JSONObject();
        jso.put("apiName", "SandboxApi");
        jso.put("tokenid", token);//tokenid一小时未查询报告，则过期，code码返回100007。需要重新登录获取新的tokenid。
        reqData.put("meal", "SpecialList_c," + "brcreditpoint");//此参数为设置客户需要查询的模块：可设置单模块查询也可以设置多模块查询。不设置此参数为所有模块都查询。
        reqData.put("id",userBaseInfo.getIdNo());
        reqData.put("cell", userBaseInfo.getPhone());
        reqData.put("name", userBaseInfo.getRealName());
        jso.put("reqData", reqData);
        String portrait_result = ms.getApiData(jso.toString(),"3000901");
        BaiRongRradeRespDTO respDTO = JSON.parseObject(portrait_result, BaiRongRradeRespDTO.class);
        return respDTO;
    }

    private String requestBaiRongTokenId(MerchantServer ms) {
        String token;
        //String userName = Global.getValue("baiRong_userName");
        //String pwd = Global.getValue("baiRong_pwd");
        //String loginName = Global.getValue("baiRong_loginName");
        //String apiCode = Global.getValue("baiRong_apiCode");

        */
/**
         *   baiRong_userName                 zlAPI(可能是测试账号)                       百融用户名
         baiRong_pwd                      zlAPI(可能是测试账号)                       百融密码
         baiRong_loginName                SandboxLoginApi(测试) /LoginApi (正式)      百融LoginApi
         baiRong_apiCode                  3000901(可能是测试账号)                     百融apiCode
         baiRong_apiName                  SandboxApi(测试)/BankServer4Api (正式)     百融apiName
         baiRong_modelName                brcreditpoint                              百融查询模块
         *
         *//*




        String userName = "zlAPI";
        String pwd = "zlAPI";
        String loginName = "LoginApi";
        String apiCode = "3000901";
        String login_result= null;
        try {
            login_result = ms.login(userName,pwd,loginName,apiCode);
        } catch (Exception e) {
            log.error("login bairong error = {}", e);
        }
        log.info("bairong login info = {}", login_result);
        JSONObject json=JSONObject.fromObject(login_result);
        token = json.getString("tokenid");
        return token;
    }
*/


}
