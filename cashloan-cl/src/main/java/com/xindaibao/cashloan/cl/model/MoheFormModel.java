package com.xindaibao.cashloan.cl.model;

import com.xindaibao.cashloan.cl.model.mohe.MoheParamHolder;
import com.xindaibao.cashloan.core.common.util.BeanUtils;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 魔盒输入参数接口;
 *
 */
@ToString
@Slf4j
@NoArgsConstructor
@Getter
@Setter
public class MoheFormModel implements Serializable {
	
	private static final long serialVersionUID = 4303792086507003724L;
	private static final Logger log = LoggerFactory.getLogger(MoheFormModel.class);
    private String task_id;   //必填, 其余可选

    private String real_name;  //真实姓名
    private String identity_code;  //身份证号码

    private String email;  //邮箱
    private String home_addr;  //家庭地址
    private String home_tel;  //家庭电话
    private String work_addr;  //工作地址
    private String work_tel;  //工作电话
    private String company_name;  //工作单位
    private String contact1_relation;  //联系人1社会关系
    private String contact1_name;  //联系人1姓名
    private String contact1_mobile;  //联系人1手机号
    private String contact2_relation;  //联系人2社会关系
    private String contact2_name;  //联系人2姓名
    private String contact2_mobile;  //联系人2手机号
    private String contact3_relation;  //联系人3社会关系
    private String contact3_name;  //联系人3姓名
    private String contact3_mobile;  //联系人3手机号
    private String contact4_relation;  //联系人4社会关系
    private String contact4_name;  //联系人4姓名
    private String contact4_mobile;  //联系人4手机号
    private String contact5_relation;  //联系人5社会关系
    private String contact5_name;  //联系人5姓名
    private String contact5_mobile;  //联系人5手机号


    public static MoheFormModel of(MoheParamHolder paramHolder) {
        if (paramHolder == null) {
            return null;
        }

        MoheFormModel formModel = new MoheFormModel();
        formModel.setTask_id(paramHolder.getTask_id()); // task_id

        formModel.setReal_name(paramHolder.getReal_name());
        formModel.setIdentity_code(paramHolder.getIdNo());

        // ===============
        formModel.setEmail(paramHolder.getEmail());
        formModel.setHome_addr(paramHolder.getHome_addr());
        formModel.setHome_tel(paramHolder.getHome_tel());
        formModel.setWork_addr(paramHolder.getWork_addr());
        formModel.setWork_tel(paramHolder.getWork_tel());
        formModel.setCompany_name(paramHolder.getCompany_name());

        formModel.setContact1_relation(paramHolder.getContact1_relation());
        formModel.setContact1_mobile(paramHolder.getContact1_mobile());
        formModel.setContact1_name(paramHolder.getContact1_name());

        formModel.setContact2_relation(paramHolder.getContact2_relation());
        formModel.setContact2_mobile(paramHolder.getContact2_mobile());
        formModel.setContact2_name(paramHolder.getContact2_name());

        formModel.setContact3_relation(paramHolder.getContact3_relation());
        formModel.setContact3_mobile(paramHolder.getContact3_mobile());
        formModel.setContact3_name(paramHolder.getContact3_name());

        formModel.setContact4_relation(paramHolder.getContact4_relation());
        formModel.setContact4_mobile(paramHolder.getContact4_mobile());
        formModel.setContact4_name(paramHolder.getContact4_name());

        formModel.setContact5_relation(paramHolder.getContact5_relation());
        formModel.setContact5_mobile(paramHolder.getContact5_mobile());
        formModel.setContact5_name(paramHolder.getContact5_name());

        return formModel;
    }

    public Map<String, String> toMap() {
        try {
            return BeanUtils.beanToMap(this);
        } catch (Exception e) {
            log.error("To map error", e);

            return null;
        }
    }






    //----------- mock ---------

    /**
     * order_no real_name id_no
     x 王鹏 420625199405084011


     * @return
     */
    public static Map<String, String> mock() {
        MoheFormModel moheFormModel = new MoheFormModel();
        moheFormModel.setTask_id("xxx");
        moheFormModel.setReal_name("王鹏");
        moheFormModel.setIdentity_code("420625199405084011");

        return moheFormModel.toMap();
    }

    public static void main(String[] args) {
        Map<String, String> map = mock();
        System.out.println(map.size());
    }


    
    
}
