package com.xindaibao.cashloan.cl.model.tongdun.model;

import java.util.HashMap;
import java.util.Map;


/**
 * 同盾请求参数
 * @author
 * @version 1.0.0
 * @date 2017-4-26 下午4:22:58



 */
public class PreloanApplyModel {
	private static final Map<String, String> CODE_MESSAGE = new HashMap<>();
	static {
		CODE_MESSAGE.put("Accept", "建议通过");
		CODE_MESSAGE.put("Review", "建议审核");
		CODE_MESSAGE.put("Reject", "建议拒绝");
		CODE_MESSAGE.put("NoResult", "建议审核");
	}

	public static String getMessage(String key) {
		return CODE_MESSAGE.get(key);
	}

	/**
	 * 姓名  
	 * 必填
	 */
	private String account_name;
	/**
	 * 身份证号    
	 * 必填
	 */
	private String id_number;
	/**
	 * 手机号    
	 * 必填
	 */
	private String account_mobile;
	/**
	 * 是否通过实名认证
	 */
	private boolean is_id_checked;
	/**
	 * 银行卡号    
	 * 推荐
	 */
	private String card_number;
	/**
	 * IP 地址
	 */
	private String ip_address; 
	/**
	 * 同盾设备指纹网页端
	 */
	private String token_id; 
	/**
	 *同盾设备指纹移动端 
	 */
	private String black_box; 
	/**
	 * 回调推送接口地址
	 */
	private String notify_url; 
	/**
	 * 申请借款金额
	 */
	private double loan_amount; 
	/**
	 * 申请借款期限
	 */
	private Integer loan_term;
	/**
	 * 期限单位
	 * 默认是月，填写时可以为天或月:DAY,MONTH  
	 */
	private String loan_term_unit;
	/**
	 * 借款用途
	 */
	private String purpose;
	/**
	 * 进件省份
	 */
	private String apply_province;
	/**
	 * 进件城市
	 */
	private String apply_city;
	/**
	 * 进件渠道
	 */
	private String apply_channel;
	/**
	 * 申请借款日期
	 * 格式 默认当前日期
	 */
	private String loan_date;
	
	/**
	 * 公司座机 
	 * 格式(区号+号码) 0571-1111111
	 */
	private String work_phone;
	/**
	 * 家庭座机 
	 * 格式(区号+号码) 0571-1111111
	 */
	private String home_phone;
	/**
	 * QQ号码
	 * 推荐
	 */
	private String qq;
	/**
	 * 电子邮箱
	 * 推荐
	 */
	private String email;
	/**
	 * 学历
	 */
	private String diploma;
	/**
	 * 婚姻状况
	 */
	private String marriage;
	/**
	 * 房产情况
	 */
	private String house_property;
	/**
	 * 房产类型
	 */
	private String house_type;
	/**
	 * 户籍地址
	 */
	private String registered_address;
	/**
	 * 家庭地址
	 */
	private String home_address;
	/**
	 * 通讯地址
	 */
	private String contact_address;
	/**
	 * 职业
	 */
	private String career;
	/**
	 *申请人员类别  
	 *（在职；学生 ）其中之一
	 */
	private String applyer_type;
	/**
	 * 工作时间   
	 */
	private String work_time;
	/**
	 * 工作单位
	 */
	private String company_name;
	/**
	 * 单位地址
	 */
	private String company_address;
	/**
	 * 公司行业
	 */
	private String company_industry;
	/**
	 * 公司性质
	 */
	private String company_type;
	/**
	 * 职位
	 */
	private String occupation;
	/**
	 * 年收入
	 */
	private String annual_income;
	/**
	 * 三个月内是否跨平台申请借款
	 */
	private String is_cross_loan;
	/**
	 * 三个月内跨平台申请借款平台个数
	 */
	private String cross_loan_count;
	/**
	 * 三个月内是否跨平台借款负债
	 */
	private String is_liability_loan;
	/**
	 * 三个月内跨平台借款负债平台个数
	 */
	private String liability_loan_count;
	
	/**
	 * 第一联系人社会关系
	 */
	private String contact1_relation;
	/**
	 * 第一联系人姓名
	 */
	private String contact1_name;
	/**
	 * 第一联系人手机号
	 */
	private String contact1_mobile;
	/**
	 * 第一联系人身份证
	 */
	private String contact1_id_number;
	/**
	 *第一联系人家庭地址
	 */
	private String contact1_addr;
	/**
	 * 第一联系人工作单位
	 */
	private String contact1_com_name;
	/**
	 * 第一联系人工作地址
	 */
	private String contact1_com_addr;
	/**
	 * 第二联系人社会关系
	 */
	private String contact2_relation;
	/**
	 * 第二联系人姓名
	 */
	private String contact2_name;
	/**
	 * 第二联系人手机号
	 */
	private String contact2_mobile;
	/**
	 * 第二联系人身份证
	 */
	private String contact2_id_number;
	/**
	 *第二联系人家庭地址
	 */
	private String contact2_addr;
	/**
	 * 第二联系人工作单位
	 */
	private String contact2_com_name;
	/**
	 * 第二联系人工作地址
	 */
	private String contact2_com_addr;
	/**
	 * 第三联系人社会关系
	 */
	private String contact3_relation;
	/**
	 * 第三联系人姓名
	 */
	private String contact3_name;
	/**
	 * 第三联系人手机号
	 */
	private String contact3_mobile;
	/**
	 * 第三联系人身份证
	 */
	private String contact3_id_number;
	/**
	 *第三联系人家庭地址
	 */
	private String contact3_addr;
	/**
	 * 第三联系人工作单位
	 */
	private String contact3_com_name;
	/**
	 * 第三联系人工作地址
	 */
	private String contact3_com_addr;
	/**
	 * 第四联系人社会关系
	 */
	private String contact4_relation;
	/**
	 * 第四联系人姓名
	 */
	private String contact4_name;
	/**
	 * 第四联系人手机号
	 */
	private String contact4_mobile;
	/**
	 * 第四联系人身份证
	 */
	private String contact4_id_number;
	/**
	 *第四联系人家庭地址
	 */
	private String contact4_addr;
	/**
	 * 第四联系人工作单位
	 */
	private String contact4_com_name;
	/**
	 * 第四联系人工作地址
	 */
	private String contact4_com_addr;
	/**
	 * 第五联系人社会关系
	 */
	private String contact5_relation;
	/**
	 * 第五联系人姓名
	 */
	private String contact5_name;
	/**
	 * 第五联系人手机号
	 */
	private String contact5_mobile;
	/**
	 * 第五联系人身份证
	 */
	private String contact5_id_number;
	/**
	 *第五联系人家庭地址
	 */
	private String contact5_addr;
	/**
	 * 第五联系人工作单位
	 */
	private String contact5_com_name;
	/**
	 * 第五联系人工作地址
	 */
	private String contact5_com_addr; 
	
	public String changeRelation(String relation) {
		String relationStr = "";
		if(relation!=null){
			if (relation.contains("父亲")) {
				relationStr = "father";
			} else if (relation.contains("母亲")) {
				relationStr = "mother";
			} else if (relation.contains("配偶")) {
				relationStr = "spouse";
			} else if (relation.contains("子女")) {
				relationStr = "child";
			} else if (relation.contains("其他亲属")) {
				relationStr = "other_relative";
			} else if (relation.contains("朋友")) {
				relationStr = "friend";
			} else if (relation.contains("同事")) {
				relationStr = "coworker";
			} else if (relation.contains("其他")) {
				relationStr = "others";
			} else {
				relationStr = "others";
			}
		}else {
			relationStr = "others";
		}
		return relationStr;
	}

	public String changeMarriage(String marriage) {
		String marriageStr = "";
		if(marriage!=null){
			if (marriage.contains("未婚")) {
				marriageStr = "SPINSTERHOOD";
			} else if (marriage.contains("已婚")) {
				marriageStr = "MARRIED";
			} else if (marriage.contains("离异")) {
				marriageStr = "DIVORCED";
			} else if (marriage.contains("丧偶")) {
				marriageStr = "WIDOWED";
			} else if (marriage.contains("再婚")) {
				marriageStr = "REMARRY ";
			} else if (marriage.contains("复婚")) {
				marriageStr = "REMARRY_FORMER ";
			} else {
				marriageStr = "";
			}
		} else {
			marriageStr = "";
		}
		return marriageStr;
	}

	public String changeDiploma(String diploma) {
		String diplomaStr = "";
		if(diploma!=null){
			if (diploma.contains("高中以下")) {
				diplomaStr = "PRE_HIGH_SCHOOL";
			} else if (diploma.contains("高中/中专")) {
				diplomaStr = "HIGH_SCHOOL";
			} else if (diploma.contains("大专")) {
				diplomaStr = "JUNIOR_COLLEGE";
			} else if (diploma.contains("本科")) {
				diplomaStr = "UNDER_GRADUATE";
			} else if (diploma.contains("研究生")) {
				diplomaStr = "POST_GRADUATE ";
			} else {
				diplomaStr = "";
			}
		}else{
			diplomaStr = "";
		}
		return diplomaStr;
	}
	
	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getAccount_mobile() {
		return account_mobile;
	}

	public void setAccount_mobile(String account_mobile) {
		this.account_mobile = account_mobile;
	}

	public String getId_number() {
		return id_number;
	}
	public void setId_number(String id_number) {
		this.id_number = id_number;
	}
	
	public boolean getIs_id_checked() {
		return is_id_checked;
	}
	public void setIs_id_checked(boolean is_id_checked) {
		this.is_id_checked = is_id_checked;
	}
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getToken_id() {
		return token_id;
	}
	public void setToken_id(String token_id) {
		this.token_id = token_id;
	}
	public String getBlack_box() {
		return black_box;
	}
	public void setBlack_box(String black_box) {
		this.black_box = black_box;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public double getLoan_amount() {
		return loan_amount;
	}
	public void setLoan_amount(double loan_amount) {
		this.loan_amount = loan_amount;
	}
	public Integer getLoan_term() {
		return loan_term;
	}
	public void setLoan_term(Integer loan_term) {
		this.loan_term = loan_term;
	}
	public String getLoan_term_unit() {
		return loan_term_unit;
	}
	public void setLoan_term_unit(String loan_term_unit) {
		this.loan_term_unit = loan_term_unit;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getApply_province() {
		return apply_province;
	}
	public void setApply_province(String apply_province) {
		this.apply_province = apply_province;
	}
	public String getApply_city() {
		return apply_city;
	}
	public void setApply_city(String apply_city) {
		this.apply_city = apply_city;
	}
	public String getApply_channel() {
		return apply_channel;
	}
	public void setApply_channel(String apply_channel) {
		this.apply_channel = apply_channel;
	}
	public String getLoan_date() {
		return loan_date;
	}
	public void setLoan_date(String loan_date) {
		this.loan_date = loan_date;
	}
	public String getWork_phone() {
		return work_phone;
	}
	public void setWork_phone(String work_phone) {
		this.work_phone = work_phone;
	}
	public String getHome_phone() {
		return home_phone;
	}
	public void setHome_phone(String home_phone) {
		this.home_phone = home_phone;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDiploma() {
		return diploma;
	}
	public void setDiploma(String diploma) {
		this.diploma = changeDiploma(diploma);
	}
	public String getMarriage() {
		return marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = changeMarriage(marriage);
	}
	public String getHouse_property() {
		return house_property;
	}
	public void setHouse_property(String house_property) {
		this.house_property = house_property;
	}
	public String getHouse_type() {
		return house_type;
	}
	public void setHouse_type(String house_type) {
		this.house_type = house_type;
	}
	public String getRegistered_address() {
		return registered_address;
	}
	public void setRegistered_address(String registered_address) {
		this.registered_address = registered_address;
	}
	public String getHome_address() {
		return home_address;
	}
	public void setHome_address(String home_address) {
		this.home_address = home_address;
	}
	public String getContact_address() {
		return contact_address;
	}
	public void setContact_address(String contact_address) {
		this.contact_address = contact_address;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getApplyer_type() {
		return applyer_type;
	}
	public void setApplyer_type(String applyer_type) {
		this.applyer_type = applyer_type;
	}
	public String getWork_time() {
		return work_time;
	}
	public void setWork_time(String work_time) {
		this.work_time = work_time;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_address() {
		return company_address;
	}
	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}
	public String getCompany_industry() {
		return company_industry;
	}
	public void setCompany_industry(String company_industry) {
		this.company_industry = company_industry;
	}
	public String getCompany_type() {
		return company_type;
	}
	public void setCompany_type(String company_type) {
		this.company_type = company_type;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getAnnual_income() {
		return annual_income;
	}
	public void setAnnual_income(String annual_income) {
		this.annual_income = annual_income;
	}
	public String getIs_cross_loan() {
		return is_cross_loan;
	}
	public void setIs_cross_loan(String is_cross_loan) {
		this.is_cross_loan = is_cross_loan;
	}
	public String getCross_loan_count() {
		return cross_loan_count;
	}
	public void setCross_loan_count(String cross_loan_count) {
		this.cross_loan_count = cross_loan_count;
	}
	public String getIs_liability_loan() {
		return is_liability_loan;
	}
	public void setIs_liability_loan(String is_liability_loan) {
		this.is_liability_loan = is_liability_loan;
	}
	public String getLiability_loan_count() {
		return liability_loan_count;
	}
	public void setLiability_loan_count(String liability_loan_count) {
		this.liability_loan_count = liability_loan_count;
	}
	public String getContact1_relation() {
		return contact1_relation;
	}
	public void setContact1_relation(String contact1_relation) {
		this.contact1_relation = changeRelation(contact1_relation);
	}
	public String getContact1_name() {
		return contact1_name;
	}
	public void setContact1_name(String contact1_name) {
		this.contact1_name = contact1_name;
	}
	public String getContact1_mobile() {
		return contact1_mobile;
	}
	public void setContact1_mobile(String contact1_mobile) {
		this.contact1_mobile = contact1_mobile;
	}
	public String getContact1_id_number() {
		return contact1_id_number;
	}
	public void setContact1_id_number(String contact1_id_number) {
		this.contact1_id_number = contact1_id_number;
	}
	public String getContact1_addr() {
		return contact1_addr;
	}
	public void setContact1_addr(String contact1_addr) {
		this.contact1_addr = contact1_addr;
	}
	public String getContact1_com_name() {
		return contact1_com_name;
	}
	public void setContact1_com_name(String contact1_com_name) {
		this.contact1_com_name = contact1_com_name;
	}
	public String getContact1_com_addr() {
		return contact1_com_addr;
	}
	public void setContact1_com_addr(String contact1_com_addr) {
		this.contact1_com_addr = contact1_com_addr;
	}
	public String getContact2_relation() {
		return contact2_relation;
	}
	public void setContact2_relation(String contact2_relation) {
		this.contact2_relation = changeRelation(contact2_relation);
	}
	public String getContact2_name() {
		return contact2_name;
	}
	public void setContact2_name(String contact2_name) {
		this.contact2_name = contact2_name;
	}
	public String getContact2_mobile() {
		return contact2_mobile;
	}
	public void setContact2_mobile(String contact2_mobile) {
		this.contact2_mobile = contact2_mobile;
	}
	public String getContact2_id_number() {
		return contact2_id_number;
	}
	public void setContact2_id_number(String contact2_id_number) {
		this.contact2_id_number = contact2_id_number;
	}
	public String getContact2_addr() {
		return contact2_addr;
	}
	public void setContact2_addr(String contact2_addr) {
		this.contact2_addr = contact2_addr;
	}
	public String getContact2_com_name() {
		return contact2_com_name;
	}
	public void setContact2_com_name(String contact2_com_name) {
		this.contact2_com_name = contact2_com_name;
	}
	public String getContact2_com_addr() {
		return contact2_com_addr;
	}
	public void setContact2_com_addr(String contact2_com_addr) {
		this.contact2_com_addr = contact2_com_addr;
	}
	public String getContact3_relation() {
		return contact3_relation;
	}
	public void setContact3_relation(String contact3_relation) {
		this.contact3_relation = changeRelation(contact3_relation);
	}
	public String getContact3_name() {
		return contact3_name;
	}
	public void setContact3_name(String contact3_name) {
		this.contact3_name = contact3_name;
	}
	public String getContact3_mobile() {
		return contact3_mobile;
	}
	public void setContact3_mobile(String contact3_mobile) {
		this.contact3_mobile = contact3_mobile;
	}
	public String getContact3_id_number() {
		return contact3_id_number;
	}
	public void setContact3_id_number(String contact3_id_number) {
		this.contact3_id_number = contact3_id_number;
	}
	public String getContact3_addr() {
		return contact3_addr;
	}
	public void setContact3_addr(String contact3_addr) {
		this.contact3_addr = contact3_addr;
	}
	public String getContact3_com_name() {
		return contact3_com_name;
	}
	public void setContact3_com_name(String contact3_com_name) {
		this.contact3_com_name = contact3_com_name;
	}
	public String getContact3_com_addr() {
		return contact3_com_addr;
	}
	public void setContact3_com_addr(String contact3_com_addr) {
		this.contact3_com_addr = contact3_com_addr;
	}
	public String getContact4_relation() {
		return contact4_relation;
	}
	public void setContact4_relation(String contact4_relation) {
		this.contact4_relation = changeRelation(contact4_relation);
	}
	public String getContact4_name() {
		return contact4_name;
	}
	public void setContact4_name(String contact4_name) {
		this.contact4_name = contact4_name;
	}
	public String getContact4_mobile() {
		return contact4_mobile;
	}
	public void setContact4_mobile(String contact4_mobile) {
		this.contact4_mobile = contact4_mobile;
	}
	public String getContact4_id_number() {
		return contact4_id_number;
	}
	public void setContact4_id_number(String contact4_id_number) {
		this.contact4_id_number = contact4_id_number;
	}
	public String getContact4_addr() {
		return contact4_addr;
	}
	public void setContact4_addr(String contact4_addr) {
		this.contact4_addr = contact4_addr;
	}
	public String getContact4_com_name() {
		return contact4_com_name;
	}
	public void setContact4_com_name(String contact4_com_name) {
		this.contact4_com_name = contact4_com_name;
	}
	public String getContact4_com_addr() {
		return contact4_com_addr;
	}
	public void setContact4_com_addr(String contact4_com_addr) {
		this.contact4_com_addr = contact4_com_addr;
	}
	public String getContact5_relation() {
		return contact5_relation;
	}
	public void setContact5_relation(String contact5_relation) {
		this.contact5_relation = changeRelation(contact5_relation);
	}
	public String getContact5_name() {
		return contact5_name;
	}
	public void setContact5_name(String contact5_name) {
		this.contact5_name = contact5_name;
	}
	public String getContact5_mobile() {
		return contact5_mobile;
	}
	public void setContact5_mobile(String contact5_mobile) {
		this.contact5_mobile = contact5_mobile;
	}
	public String getContact5_id_number() {
		return contact5_id_number;
	}
	public void setContact5_id_number(String contact5_id_number) {
		this.contact5_id_number = contact5_id_number;
	}
	public String getContact5_addr() {
		return contact5_addr;
	}
	public void setContact5_addr(String contact5_addr) {
		this.contact5_addr = contact5_addr;
	}
	public String getContact5_com_name() {
		return contact5_com_name;
	}
	public void setContact5_com_name(String contact5_com_name) {
		this.contact5_com_name = contact5_com_name;
	}
	public String getContact5_com_addr() {
		return contact5_com_addr;
	}
	public void setContact5_com_addr(String contact5_com_addr) {
		this.contact5_com_addr = contact5_com_addr;
	}
}
