TRUNCATE TABLE arc_borrow_rule_result;
TRUNCATE TABLE cl_borrow;
TRUNCATE TABLE cl_borrow_progress;
TRUNCATE TABLE cl_borrow_repay;
TRUNCATE TABLE cl_borrow_repay_log;
TRUNCATE TABLE  cl_rc_scene_business_log;

TRUNCATE TABLE cl_pay_check;
TRUNCATE TABLE cl_pay_log;
TRUNCATE TABLE cl_pay_req_log;
TRUNCATE TABLE cl_pay_resp_log;

TRUNCATE TABLE cl_qiancheng_req_log;
TRUNCATE TABLE cl_tongdun_req_log;
TRUNCATE TABLE cl_tongdun_resp_detail;
TRUNCATE TABLE cl_urge_repay_log;
TRUNCATE TABLE cl_urge_repay_order;

TRUNCATE TABLE arc_credit_log;
TRUNCATE TABLE cl_profit_cash_log;

UPDATE arc_credit SET unuse = total, used = 0;
UPDATE cl_profit_amount SET total = 0, no_cashed = 0, cashed = 0;

-- 清理用户记录
TRUNCATE TABLE `cl_user`;
TRUNCATE TABLE `cl_user_auth`;
TRUNCATE TABLE `cl_user_base_info`;
TRUNCATE TABLE `cl_user_card_credit_log`;
TRUNCATE TABLE `cl_user_contacts`;
TRUNCATE TABLE `cl_user_contacts_1`;
TRUNCATE TABLE `cl_user_education_info`;
TRUNCATE TABLE `cl_user_emer_contacts`;
TRUNCATE TABLE `cl_user_equipment_info`;
TRUNCATE TABLE `cl_user_invite`;
TRUNCATE TABLE `cl_user_messages`;
TRUNCATE TABLE `cl_user_other_info`;
TRUNCATE TABLE `cl_user_sdk_log`;
TRUNCATE TABLE `cl_zhima`;
TRUNCATE TABLE `arc_credit`;


TRUNCATE TABLE cl_profit_agent;
TRUNCATE TABLE cl_profit_amount;
TRUNCATE TABLE cl_profit_cash_log;
TRUNCATE TABLE cl_profit_log;

-- 基本信息
TRUNCATE TABLE cl_sms;
TRUNCATE TABLE cl_bank_card;
TRUNCATE TABLE cl_app_session;
TRUNCATE TABLE cl_rc_dhb_user_basic;

-- 贷后邦
TRUNCATE TABLE cl_rc_dhb_user_basic;
TRUNCATE TABLE cl_rc_dhb_risk_social_network;
TRUNCATE TABLE cl_rc_dhb_risk_blacklist;
TRUNCATE TABLE cl_rc_dhb_req_log;
TRUNCATE TABLE cl_rc_dhb_history_search;
TRUNCATE TABLE cl_rc_dhb_history_org;
TRUNCATE TABLE cl_rc_dhb_binding;

-- 运营商认证信息
TRUNCATE TABLE cl_operator_resp_detail;
TRUNCATE TABLE cl_operator_req_log;
TRUNCATE TABLE cl_operator_bills;
TRUNCATE TABLE cl_operator_basic;
TRUNCATE TABLE cl_operator_voices_1;
TRUNCATE TABLE cl_operator_voices;


