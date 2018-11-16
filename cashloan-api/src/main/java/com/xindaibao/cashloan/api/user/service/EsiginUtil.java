package com.xindaibao.cashloan.api.user.service;

import com.timevale.esign.sdk.tech.bean.PersonBean;
import com.timevale.esign.sdk.tech.bean.PosBean;
import com.timevale.esign.sdk.tech.bean.result.AddAccountResult;
import com.timevale.esign.sdk.tech.bean.result.AddSealResult;
import com.timevale.esign.sdk.tech.bean.result.LoginResult;
import com.timevale.esign.sdk.tech.bean.result.Result;
import com.timevale.esign.sdk.tech.bean.seal.PersonTemplateType;
import com.timevale.esign.sdk.tech.bean.seal.SealColor;
import com.timevale.esign.sdk.tech.impl.constants.SignType;
import com.timevale.esign.sdk.tech.service.AccountService;
import com.timevale.esign.sdk.tech.service.EsignsdkService;
import com.timevale.esign.sdk.tech.service.SealService;
import com.timevale.esign.sdk.tech.service.SignService;
import com.timevale.esign.sdk.tech.service.factory.AccountServiceFactory;
import com.timevale.esign.sdk.tech.service.factory.EsignsdkServiceFactory;
import com.timevale.esign.sdk.tech.service.factory.SealServiceFactory;
import com.timevale.esign.sdk.tech.service.factory.SignServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Created by lsk on 2016/4/26.
 */
public class EsiginUtil {
    /**
     * 項目ID
     */
    public static final String PROJECTID = "1111563517";
    /**
     * 项目密钥
     */
    public static final String PROJECT_SECRET = "95439b0863c241c63a861b87d1e647b7";
    private Logger logger = LoggerFactory.getLogger(EsiginUtil.class);
    private EsignsdkService SDK = EsignsdkServiceFactory.instance();
    private AccountService SERVICE = AccountServiceFactory.instance();

    private SealService SEAL = SealServiceFactory.instance();

    // 摘要服务
    private SignService signService;
    public String devId = null;

    public EsiginUtil() {
        signService = SignServiceFactory.instance();
        Result result = SDK.init(PROJECTID, PROJECT_SECRET);

        if (0 != result.getErrCode()) {

            logger.debug("初始化失败，错误码=" + result.getErrCode() + ",错误信息=" + result.getMsg());
            throw new RuntimeException(result.getMsg());
        }
        LoginResult loginResult = SDK.login();

        if (0 != loginResult.getErrCode()) {
            logger.debug("验证处理失败！");

        }

        // 设置项目账户标识，后续接口调用时可以从session中获取
        logger.info("返回会的account {}", loginResult.getAccountId());
        devId = loginResult.getAccountId();
        logger.info("验证处理成功! accountId:" + loginResult.getAccountId());
    }


    /**
     * @return
     */
    public String addPerson(String name, String sfz, String mobile) {
        PersonBean psn = new PersonBean();
        psn.setName(name).setIdNo(sfz).setMobile(mobile);

        AddAccountResult r = SERVICE.addAccount(devId, psn);
        if (0 != r.getErrCode() || null == r.getAccountId() || r.getAccountId().isEmpty()) {
            logger.info("错误：{}", r.getErrCode());
            throw new RuntimeException(r.getMsg());
        }

        // 保存用户账户 要持久化
        logger.info("个人账户:{}", r.getAccountId());
        return r.getAccountId();
    }

    public String getUserSealData(String userId) {
        PersonTemplateType seal;
//        String userId = "67F14350859D4DAE946EB816FFB538F1";
        int type = 1;
        switch (type) {

            case 1:
                seal = PersonTemplateType.FZKC;
                break;

            case 2:
                seal = PersonTemplateType.HYLSF;
                break;

            case 3:
                seal = PersonTemplateType.RECTANGLE;
                break;

            case 4:
                seal = PersonTemplateType.SQUARE;
                break;

            case 5:
            default:
                seal = PersonTemplateType.YYGXSF;
                break;
        }
        AddSealResult r = SEAL.addTemplateSeal(devId, userId, seal,
            SealColor.BLACK);
        if (0 != r.getErrCode()) {
            logger.info("错误:{}", r.getErrCode());
        }

        logger.info("个人的签章数据:" + r.getSealData());
        logger.info("个人的签章数据:{}", r.getSealData());
        return r.getSealData();
    }

    public void buildSign(String username, String sfz, String mobile, String srcPdf) {
        String dstPdf = srcPdf;
        buildSign(username, sfz, mobile, srcPdf, dstPdf);
    }

    public void buildSign(String username, String sfz, String mobile, String srcPdf, String dstPdf) {
        String userId = addPerson(username, sfz, mobile);
        String sealData = getUserSealData(userId);
        addEnterpriseSeal(srcPdf, dstPdf, sealData);
        addUserSign(dstPdf, dstPdf, userId, sealData);
    }

    /**
     * 平台用户摘要签署 用指定账户的证书对文档进行签署，此签署过程不将文档上传至e签宝平台，只传递文档摘要信息。
     */
    public void addEnterpriseSeal(String srcPdffile, String dstPdfFile, String sealData) {
        PosBean pos = new PosBean();
        pos.setPosX(400);
        pos.setPosY(100);
        pos.setPosPage("1");
        int sealId = 2;
        SignType s = SignType.Single;

        String eFileName = "";
        Result r = signService.localSignPDF(devId, srcPdffile, dstPdfFile, pos, sealId, s, eFileName);

        if (0 != r.getErrCode()) {

            return;
        }
    }

    public void addUserSign(String srcPdf, String dstPdf, String userId, String sealData) {
        PosBean pos = new PosBean();
        pos.setPosX(400);
        pos.setPosY(100);
        pos.setPosPage("1");


        StringBuilder relative = new StringBuilder();
        relative.append("doc/signed_");
        relative.append(UUID.randomUUID().toString());
        relative.append(".pdf");

        // 使用用户印章签名
        Result r = signService.localSignPDF(devId, userId, sealData, srcPdf, dstPdf, pos, SignType.Single, "文广测试文档.pdf");

        if (0 != r.getErrCode()) {



            String errorMsg = "签署文档失败:" + r.getMsg();

            // Tools.forward(req, resp, "/error.jsp");
            throw new RuntimeException(errorMsg);
        }

    }



}
