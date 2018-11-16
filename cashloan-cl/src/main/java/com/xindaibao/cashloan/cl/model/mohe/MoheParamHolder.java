package com.xindaibao.cashloan.cl.model.mohe;

import com.xindaibao.cashloan.cl.domain.UserEmerContacts;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.domain.UserOtherInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MoheParamHolder {
	
	private Long userId;
	private String task_id;
	private String real_name; //真实姓名
	private String idNo;


	//----- 可选
    private String identity_code;  //身份证号码 userBaseInfo
    private String email;  //邮箱  userOtherInfo
    private String home_addr;  //家庭地址 ?
    private String home_tel;  //家庭电话 ?
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


    public MoheParamHolder(String task_id) {
        this.task_id = task_id;
    }


    public void append(UserBaseInfo userBaseInfo) {
        if (userBaseInfo != null) {
            this.idNo = userBaseInfo.getIdNo();
            this.real_name = userBaseInfo.getRealName();
            this.userId = userBaseInfo.getUserId();
            this.identity_code = userBaseInfo.getIdNo();

            this.company_name = userBaseInfo.getCompanyName();
            this.work_tel = userBaseInfo.getCompanyPhone();
            this.work_addr = userBaseInfo.getCompanyAddr();
        }
    }

    /**
     * id	name	phone	user_id	relation	type
     7	邢*	1500029xxxx	8	子女	10
     */
    public void append(List<UserEmerContacts> emerContactsList) {
        if (CollectionUtils.isEmpty(emerContactsList)) {
            log.warn("紧急李新喜人信息为空, userId:{}, task_id: {} ", userId, task_id);
        } else {
            if (emerContactsList.size() == 1) {
                appendFirstEmerContact(emerContactsList.get(0));
            } else if (emerContactsList.size() == 2) {
                appendFirstEmerContact(emerContactsList.get(0));
                appendSecondEmerContact(emerContactsList.get(1));
            } else {
                log.warn("紧急李新喜人信息超过2个, userId:{}, task_id: {} ", userId, task_id);
                appendFirstEmerContact(emerContactsList.get(0));
                appendSecondEmerContact(emerContactsList.get(1));
            }
        }
    }

    private void appendFirstEmerContact(UserEmerContacts userEmerContacts) {
        MoheRelationEnum relationEnum = MoheRelationEnum.matchByDesc(userEmerContacts.getRelation());
        this.contact1_relation = relationEnum.name();

        this.contact1_name = userEmerContacts.getName();
        this.contact1_mobile = userEmerContacts.getPhone();
    }

    private void appendSecondEmerContact(UserEmerContacts userEmerContacts) {
        MoheRelationEnum relationEnum = MoheRelationEnum.matchByDesc(userEmerContacts.getRelation());
        this.contact2_relation = relationEnum.name();

        this.contact2_name = userEmerContacts.getName();
        this.contact2_mobile = userEmerContacts.getPhone();
    }


    public void append(UserOtherInfo userOtherInfo) {
        if (userOtherInfo != null) {
            this.email = userOtherInfo.getEmail();
        }
    }


}
