package com.xindaibao.cashloan.api.user.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import tool.util.BeanUtil;
import tool.util.FreemarkerUtil;

import com.itextpdf.text.pdf.BaseFont;
import com.xindaibao.cashloan.cl.service.ClBorrowService;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.util.DateUtil;
import com.xindaibao.cashloan.core.common.util.MapUtil;
import com.xindaibao.cashloan.core.domain.Borrow;

import freemarker.template.Configuration;

/**
 * Created by lsk on 2017/5/24.
 */
@Service
public class ContractService {
	private static final Logger logger = Logger.getLogger(ContractService.class);
	
    @Autowired
    private DBService dbService;

    private File rootDir = new File("/data/contracts");

    static {
        FreemarkerUtil.CONFIG = new Configuration();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void buildPdf(String borrowId) {
        try {
            Map borrow = dbService.queryRec("select * from cl_borrow where id=?", borrowId);
            Map user = dbService.queryRec("select * from cl_user_base_info where user_id=?", borrow.get("user_id"));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

            String path = ContractService.class.getResource("/config/contractTpl/ht.html").getFile();
//            path = "D:\\ideaProject_rd2\\LskJarCenterMvn\\src\\main\\java\\cn\\lsk\\jar\\pdf\\lsk2.html";
            logger.info(path);


            String html = FileUtils.readFileToString(new File(path), "utf8");

            int timeLimit = Integer.parseInt(borrow.get("time_limit").toString());
            
            String penaltyFee = Global.getValue("penalty_fee");
            String[] penaltyFees = penaltyFee.split(",");
            
            String penalty = "2";
            for (String string : penaltyFees) {
            	String[] penaltyParams = string.split("-");
            	if (penaltyParams.length==2) {
            		if (Integer.parseInt(penaltyParams[0])==(timeLimit)) {
            			penalty = penaltyParams[1];
					}
				}
			}

            String orderNo = borrow.get("order_no").toString();
            String customerName = user.get("real_name").toString();
            String sfz = user.get("id_no").toString();
            String mobile = user.get("phone").toString();
            
            
            
            html = FreemarkerUtil.fillTemplate(html, MapUtil.array2Map(new Object[][]{
                {"htno", orderNo},
                {"fromDate", sdf.format(new Date())},
                {"endDate", sdf.format(DateUtil.dateAddDays(new Date(), timeLimit - 1))},
                {"dayCnt", timeLimit},
                {"sfz", sfz},
                {"mobile", mobile},
                {"penalty", Double.parseDouble(penalty)*100},
                {"customerName", customerName},
                {"amount",borrow.get("amount")},
                {"fee",borrow.get("fee")}
            }));

            sdf = new SimpleDateFormat("yyyy-MM");

            File pdffile = new File(rootDir, sdf.format(borrow.get("create_time")) + "/" + orderNo + "-contract.pdf");
            logger.info(pdffile);
            pdffile.getParentFile().mkdirs();
            renderPdf(html, new FileOutputStream(pdffile));


            logger.info("已生成pdf文件");

//            EsiginUtil o = new EsiginUtil();
            String url = Global.getValue("tech_host");
            WrapperUtil w = new WrapperUtil(url);
            
//            customerName= "岑文丽";
//            sfz= "511702197506159005"; //67F14350859D4DAE946EB816FFB538F1
//            mobile = "15623562589";

            String signServiceId = w.addUserSign(pdffile.getAbsolutePath(), url);
//            o.buildSign(customerName, sfz, mobile, pdffile.getAbsolutePath());
            
            //保存签署记录id
            ClBorrowService clBorrowService = (ClBorrowService) BeanUtil.getBean("clBorrowService");
            Borrow bw = clBorrowService.findByPrimary(Long.parseLong(borrowId));
            bw.setSignServiceId(signServiceId);
            clBorrowService.updateById(bw);
            
            logger.info("已签名");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public static void renderPdf(String htmlCode, OutputStream os) {
        ITextRenderer renderer = new ITextRenderer();
        ITextFontResolver fontResolver = renderer.getFontResolver();
        try {
            String path = ContractService.class.getResource("/config/font/simsun.ttc").getPath();
            fontResolver.addFont(path, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            renderer.setDocumentFromString(htmlCode);
            renderer.layout();
            renderer.createPDF(os);
            os.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
