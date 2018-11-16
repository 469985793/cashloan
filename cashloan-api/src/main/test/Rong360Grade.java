import com.alibaba.fastjson.JSON;
import com.xindaibao.cashloan.cl.mapper.Rong360GradeMapper;
import com.xindaibao.cashloan.cl.sdk.face.R360AntiFraud;
import com.xindaibao.cashloan.cl.sdk.face.dto.R360GradeRespDTO;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.service.UserBaseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by sparta on 2017/10/31.
 */
@Slf4j
public class Rong360Grade extends BaseTest{

    @Autowired private UserBaseInfoService userBaseInfoService;
    @Autowired private Rong360GradeMapper rong360GradeMapper;
/*
    @Test
    public void queryR360Grade() {
       // UserBaseInfo baseInfo = userBaseInfoService.findByUserId(userId);

        UserBaseInfo userBaseInfo = new UserBaseInfo();
        userBaseInfo.setIdNo("140502198811102244");
        userBaseInfo.setPhone("13986671110");
        userBaseInfo.setRealName("王亮");


        com.xindaibao.cashloan.cl.domain.Rong360Grade rong360Grade = new com.xindaibao.cashloan.cl.domain.Rong360Grade();
        rong360Grade.setCreateTime(new Date());
        rong360Grade.setUserId(234L);
        try {
            String request = R360AntiFraud.request(userBaseInfo);
            R360GradeRespDTO r360GradeRespDTO = JSON.parseObject(request, R360GradeRespDTO.class);
            if (StringUtils.equals("200", r360GradeRespDTO.getError())) {
                rong360Grade.setScore(Integer.valueOf(r360GradeRespDTO.getTianji_api_agenty_AntiFraud_response().getScore()));
                rong360Grade.setTag(r360GradeRespDTO.getTianji_api_agenty_AntiFraud_response().getTag());
            } else if (StringUtils.equals("10002", r360GradeRespDTO.getError())) {
                rong360Grade.setScore(998);
            } else {
                rong360Grade.setScore(999);
            }
            rong360Grade.setRemark(r360GradeRespDTO.getMsg());

        } catch (Exception e) {
            log.error("request grade exception, data={}", e);
            rong360Grade.setScore(999);
            rong360Grade.setRemark("request grade exception, data=" + e.getMessage());
        }
        rong360GradeMapper.save(rong360Grade);
    }*/
}
