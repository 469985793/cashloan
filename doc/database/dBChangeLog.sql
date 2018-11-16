-- 添加用户ID
ALTER TABLE cl_rc_scene_business_log ADD COLUMN `user_id` BIGINT(20) NULL COMMENT '用户ID' AFTER `borrow_id`;

-- 同盾贷前审核--
-- DROP TABLE IF EXISTS `cl_tongdun_req_log`;
CREATE TABLE `cl_tongdun_req_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) DEFAULT NULL COMMENT '申请订单号',
  `borrow_id` bigint(20) DEFAULT NULL COMMENT '借款标识',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `state` varchar(4) DEFAULT '10' COMMENT '审核状态  10 已提交申请   20 审核通过  30 审核不通过',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `submit_code` varchar(10) DEFAULT NULL COMMENT '获取审核报告返回码',
  `submit_params` mediumtext COMMENT '获取审核报告响应结果',
  `report_id` varchar(64) DEFAULT '' COMMENT '风险报告编码',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '查询报告时间',
  `query_params` mediumtext COMMENT '查询报告响应结果',
  `query_code` varchar(16) DEFAULT '' COMMENT '查询报告返回码',
  `rs_state` varchar(16) DEFAULT '' COMMENT '风控结果    Accept:建议通过,Review:建议审核,Reject:建议拒绝',
  `rs_score` int(20) DEFAULT NULL COMMENT '风控分数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COMMENT='同盾第三方请求记录';


-- 删除不需要的支付配置息--
delete  from  arc_sys_config where code='alipay_app_id';
delete  from  arc_sys_config where code='alipay_public_key';
delete  from  arc_sys_config where code='alipay_private_key';
delete  from  arc_sys_config where code='alipay_app_apihost';

delete  from  arc_sys_config where code='bank_card_interfaceName';
delete  from  arc_sys_config where code='bank_card_apihost';
delete  from  arc_sys_config where code='bank_card_channelNo';

-- 删除系统配置中浅橙的请求地址
DELETE FROM arc_sys_config WHERE code = 'ds_qianchen_rc_url';
ALTER TABLE cl_qiancheng_req_log COMMENT='风控数据-浅橙请求结果';

-- 修改字段长度
alter table arc_rule_engine_config modify column add_ip VARCHAR(32);
-- 修改芝麻分数据类型
alter table cl_zhima modify column score decimal(12,2);

-- 修改联系人关系
INSERT INTO `arc_sys_dict`(type_code,type_name,sort,remark) VALUES ('KINSFOLK_RELATION', '直系联系人', '46', '直系联系人');

INSERT INTO `arc_sys_config` (`id`, `type`, `name`, `code`, `value`, `status`, `remark`, `creator`) VALUES (null, '80', '连连对账SFTP配置', 'lianlian_sftp', '{\"host\":\"hz-sftp1.lianlianpay.com\",\"port\":\"\",\"user\":\"\",\"passwd\":\"\",\"path\":\"\"}', '1', '连连对账SFTP配置', '1');
INSERT INTO `arc_sys_config` (`id`, `type`, `name`, `code`, `value`, `status`, `remark`, `creator`) VALUES (null, '80', '支付是否开启', 'lianlian_switch', '1', '1', '1开2关', '1');
INSERT INTO `arc_sys_config` (`id`, `type`, `name`, `code`, `value`, `status`, `remark`, `creator`) VALUES (null, '20', '借款额度', 'borrow_credit', '100,200,300,400,500,600,700,800,900,1000', '1', '借款额度', '1');
INSERT INTO `arc_sys_config` (`id`, `type`, `name`, `code`, `value`, `status`, `remark`, `creator`) VALUES (null, '20', '当日最大注册用户数', 'day_register_max', '0', '0', '当日最大注册用户数', '1');
INSERT INTO `arc_sys_config` (`id`, `type`, `name`, `code`, `value`, `status`, `remark`, `creator`) VALUES (null, '20', '版本控制', 'check_version', '1.0.1', '1', 'version版本号', '1');

-- face++地址
INSERT INTO `arc_sys_config` (`type`, `name`, `code`, `value`, `status`, `remark`, `creator`) VALUES ('70', 'orc请求地址', 'ocr_host', 'http://ucdevapi.ucredit.erongyun.net/faceID/ocrHost', '1', '', '1');
INSERT INTO `arc_sys_config` (`type`, `name`, `code`, `value`, `status`, `remark`, `creator`) VALUES ('70', 'face++人证对比地址', 'verify_host', 'http://ucdevapi.ucredit.erongyun.net/faceID/verify', '1', '', '1');

--系统配置逾期利率修改
UPDATE arc_sys_config SET value = '7-0.02,10-0.03,14-0.04' WHERE code = 'penalty_fee';

--删掉菜单中的收入统计、支付统计
UPDATE arc_sys_menu SET is_delete = 1 where name = '收入统计';
UPDATE arc_sys_menu SET is_delete = 1 where name = '支出统计';

INSERT INTO `arc_sys_menu` VALUES (null, '0', '渠道信息统计', '11244', '', null, '00000000020', null, '', null, '', '渠道信息统计', '0', '1', 'ChannelInformationStatistics', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES (null, '0', '同盾贷前审核记录', '11195', '', null, '00000000028', null, '', null, '', '同盾贷前审核记录', '0', '1', 'ShildCreditAuditRecords', null, null, null, null);
INSERT INTO `arc_sys_role_menu`(role_id,menu_id) VALUES ('1', '11288');
INSERT INTO `arc_sys_role_menu`(role_id,menu_id) VALUES ('1', '11289');
 
-- ----------------------------
-- Table structure for cl_channel_app
-- ----------------------------
-- DROP TABLE IF EXISTS `cl_channel_app`;
CREATE TABLE `cl_channel_app` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `channel_id` bigint(20) NOT NULL COMMENT '渠道id',
  `app_type` varchar(16) DEFAULT '' COMMENT '应用类型',
  `latest_version` varchar(16) DEFAULT '' COMMENT '最新版本',
  `min_version` varchar(16) DEFAULT '' COMMENT '最低支持版本',
  `download_url` varchar(64) DEFAULT '' COMMENT '下载地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 2017-05-10
delete  from  arc_sys_config where code='sms_apikey';
delete  from  arc_sys_config where code='sms_secretkey';

-- 2017-0-5-11 borrow表添加审核说明字段
Alter table `cl_borrow`   
  add column `remark` varchar(64) DEFAULT ''  NULL  COMMENT '备注、审核说明' after `coordinate`;

Alter table `cl_borrow`   
  add column `ip` varchar(64) DEFAULT ''  NULL  COMMENT 'ip地址' after `remark`;
  
delete  from  arc_sys_config where code='min_credit';
delete  from  arc_sys_config where code='max_credit';
delete  from  arc_sys_config where code='min_days';
delete  from  arc_sys_config where code='max_days';

-- 增加人证识别类别选择
INSERT INTO `arc_sys_config` VALUES (null, '70', '人证识别接口类别', 'verify_type', '10','1','10-face++,20-小视,30-商汤','1');

-- cl_channel_app表新增state字段
alter table `cl_channel_app` add column `state` varchar(4) DEFAULT '' COMMENT '10 启用 20 禁用' after `download_url`;

-- 补充机审通过订单
INSERT INTO `arc_sys_menu` VALUES (null, '0', '机审通过订单', '11195', '', null, '00000000015', null, '', null, '', '机审通过订单', '0', '1', 'MachinePassList', null, null, null, null);

-- 将运营商认证请求记录里的notify_params存入分表   2017-5-17  xx
-- DROP TABLE IF EXISTS `cl_operator_resp_detail`;
CREATE TABLE `cl_operator_resp_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `req_log_id` bigint(20) NOT NULL COMMENT '请求记录标识',
  `order_no` varchar(50) DEFAULT '' COMMENT '订单号',
  `notify_params` longtext COMMENT '异步通知结果',
  `notify_time` datetime DEFAULT NULL COMMENT '异步通知时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商认证响应通知详情表';

INSERT INTO cl_operator_resp_detail(req_log_id, order_no, notify_params, notify_time) SELECT id, order_no, notify_params, update_time FROM cl_operator_req_log;
ALTER TABLE cl_operator_req_log DROP COLUMN `notify_params`;
ALTER TABLE cl_operator_req_log DROP COLUMN `update_time`;

--  同盾审核报告信息表 2017-5-17
CREATE TABLE `cl_tongdun_resp_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `req_id` bigint(20) DEFAULT NULL COMMENT '同盾请求id',
  `order_no` varchar(64) DEFAULT NULL COMMENT '同盾申请记录订单号',
  `report_id` varchar(64) DEFAULT NULL COMMENT '同盾审核报告id',
  `query_params` mediumtext COMMENT '审核报告具体信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='同盾审核报告详细信息';



INSERT into cl_tongdun_resp_detail(req_id,order_no,report_id,query_params)  select id as req_id,order_no,report_id,query_params from cl_tongdun_req_log ;

alter table `cl_tongdun_req_log`  drop column query_params; 

-- 菜单整理 2017-5-17 xx
update `arc_sys_menu` set is_delete = 1 where name = '用户认证数据统计';
update `arc_sys_menu` set is_delete = 1 where name = '审批中心';
update `arc_sys_menu` set is_delete = 1 where name = '待审批单据列表';
update `arc_sys_menu` set is_delete = 1 where name = '收入统计';
update `arc_sys_menu` set is_delete = 1 where name = '支出统计';
update `arc_sys_menu` set is_delete = 1 where name = '系统数据统计接口';
update `arc_sys_menu` set is_delete = 1 where name = '导流分析';

delete from `arc_sys_role_menu` where role_id = 17;
INSERT INTO `arc_sys_role_menu` VALUES (null, '17', '11140');
INSERT INTO `arc_sys_role_menu` VALUES (null, '17', '11161');
INSERT INTO `arc_sys_role_menu` VALUES (null, '17', '11168');
INSERT INTO `arc_sys_role_menu` VALUES (null, '17', '11280');

-- 删除sys_config中的芝麻反欺诈和行业关注黑名单的开关配置
DELETE FROM `arc_sys_config` where code = 'zhima_antiFraud_switch';
DELETE FROM `arc_sys_config` where code = 'zhima_industryConcern_switch';

-- 公积金
ALTER TABLE `cl_operator_req_log` ADD COLUMN `business_type`  varchar(50) NULL COMMENT '业务类型，10运营商   20公积金   ' AFTER `resp_order_no`;
ALTER TABLE `cl_user_auth` ADD COLUMN `acc_fund_state`  varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公积金认证状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善' AFTER `other_info_state`;
update cl_operator_req_log set business_type='10';
CREATE TABLE `cl_accfund_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_no` varchar(50) DEFAULT NULL COMMENT '用户账号',
  `amount` decimal(20,2) DEFAULT NULL COMMENT '账户金额(分)',
  `biz_no` varchar(50) DEFAULT NULL COMMENT '业务号',
  `company` varchar(200) DEFAULT NULL COMMENT '公司信息',
  `deposit_status` varchar(50) DEFAULT NULL COMMENT '缴纳状态',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `house_accumulation_fund_id` varchar(200) DEFAULT NULL COMMENT '公积金 ID',
  `id_card` varchar(50) DEFAULT NULL COMMENT '用户身份证号',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `region` varchar(200) DEFAULT NULL COMMENT '公积金所在地',
  `user_id` bigint(20) DEFAULT NULL COMMENT '客户表 外键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公积金基本信息表';

-- DROP TABLE IF EXISTS `cl_accfund_log`;
CREATE TABLE `cl_accfund_log` (
  `id` bigint(20) NOT NULL,
  `account_no` varchar(50) DEFAULT NULL COMMENT '用户账号',
  `amount` decimal(20,2) DEFAULT NULL COMMENT '账户金额(分)',
  `amount_balance` decimal(20,2) DEFAULT NULL,
  `biz_no` varchar(50) DEFAULT NULL COMMENT '业务号',
  `biz_time` datetime DEFAULT NULL COMMENT '业务发生时间',
  `digest` varchar(50) DEFAULT NULL COMMENT '摘要',
  `fund_flow` varchar(50) DEFAULT NULL COMMENT '资金流向（流入、流出）INCOME/EXPENSE',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `house_accumulation_fund_id` varchar(200) DEFAULT NULL COMMENT '公积金 ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '客户表 外键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公积金详细信息表(流水)';

INSERT INTO `arc_sys_config` (`type`, `name`, `code`, `value`, `status`, `remark`, `creator`) VALUES ('70', '活体识别使用记录同步', 'living_record', 'http://ucdevapi.ucredit.erongyun.net/minivision/livingRecord', '1', '', '1');
UPDATE arc_sys_config SET value = '7-0.02,10-0.03,14-0.04' WHERE code = 'penalty_fee';

-- 催收添加成功日期
ALTER TABLE `cl_urge_repay_order`ADD COLUMN `success_time`  datetime NULL COMMENT '催收成功日期' AFTER `create_time`;


-- 同盾运营商认证相关sql
INSERT INTO `arc_sys_config` VALUES (null, '70', '运营商认证类别', 'operator_type', '10','1','10 上树运营商,20 上树运营商2,30 同盾','1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '同盾运营商认证授权地址', 'operator_identify_url', '','1','同盾运营商认证授权地址','1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '同盾商户秘钥','tongdun_operator_partner_key','','1','同盾商户秘钥','1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '同盾商户标识','tongdun_operator_partner_code','hzsy','1','同盾商户标识','1');
INSERT INTO `arc_sys_config` VALUES (null, '80', '同盾运营商信息查询接口','tongdun_operator_query_url','https://api.shujumohe.com/octopus/task.unify.query/v3','1','同盾运营商信息查询接口','1');


CREATE TABLE `cl_operator_td_basic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `req_log_id` bigint(20) DEFAULT NULL COMMENT '请求记录id',
  `order_no` varchar(50) DEFAULT NULL COMMENT '请求订单号',
  `channel_src` varchar(32) DEFAULT NULL COMMENT '渠道数据源  中国移动中国联通中国电信',
  `user_mobile` varchar(32) DEFAULT NULL,
  `account_balance` varchar(32) DEFAULT NULL COMMENT '账户余额',
  `current_fee` varchar(32) DEFAULT NULL COMMENT '当前话费',
  `credit_level` varchar(32) DEFAULT NULL COMMENT '账户星级',
  `mobile_status` varchar(32) DEFAULT NULL COMMENT '账户状态',
  `net_time` varchar(32) DEFAULT NULL COMMENT '入网时间',
  `net_age` int(32) DEFAULT '0' COMMENT '网龄(天)',
  `real_info` varchar(32) DEFAULT NULL COMMENT '实名制信息',
  `credit_point` varchar(32) DEFAULT NULL COMMENT '积分',
  `credit_effective_time` varchar(100) DEFAULT NULL COMMENT '星级有效期 ',
  `credit_score` varchar(32) DEFAULT NULL COMMENT '星级得分',
  `land_level` varchar(32) DEFAULT NULL COMMENT '通话级别',
  `roam_state` varchar(32) DEFAULT NULL COMMENT '漫游级别',
  `balance_available` varchar(32) DEFAULT NULL COMMENT '账户可用余额',
  `balance_unavailable` varchar(32) DEFAULT NULL COMMENT '账户冻结余额',
  `prepay_available` varchar(32) DEFAULT NULL COMMENT '可用预存款',
  `prom_available` varchar(32) DEFAULT NULL COMMENT '可用赠款',
  `prepay_unavailable` varchar(32) DEFAULT NULL COMMENT '冻结预存款',
  `prom_unavailable` varchar(32) DEFAULT NULL COMMENT '冻结赠款',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='同盾运营商认证基本信息表';




CREATE TABLE `cl_operator_td_bills` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `req_log_id` bigint(20) DEFAULT NULL COMMENT '请求记录id',
  `order_no` varchar(50) DEFAULT NULL COMMENT '请求订单号',
  `bill_cycle` varchar(32) DEFAULT NULL COMMENT '账单周期',
  `bill_fee` varchar(32) DEFAULT NULL COMMENT '账单费用',
  `bill_discount` varchar(32) DEFAULT NULL COMMENT '减免',
  `bill_total` varchar(32) DEFAULT NULL COMMENT '费用合计',
  `breach_amount` varchar(32) DEFAULT NULL COMMENT '违约金',
  `paid_amount` varchar(32) DEFAULT NULL COMMENT '已支付',
  `unpaid_amount` varchar(32) DEFAULT NULL COMMENT '未支付',
  `bill_record` longtext COMMENT '账单记录',
  `usage_detail` longtext COMMENT '使用量详情',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='同盾运营商账单信息';



CREATE TABLE `cl_operator_td_call_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '同盾运营商通话详单',
  `req_log_id` bigint(20) DEFAULT NULL COMMENT '请求记录id',
  `order_no` varchar(50) DEFAULT NULL COMMENT '请求订单号',
  `total_call_time` varchar(32) DEFAULT NULL COMMENT '总通话时长',
  `total_call_count` varchar(32) DEFAULT NULL COMMENT '总通话次数',
  `total_fee` varchar(32) DEFAULT NULL COMMENT '费用合计',
  `call_cycle` varchar(32) DEFAULT NULL COMMENT '通话周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='同盾运营商通话记录详单';

CREATE TABLE `cl_operator_td_call_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `info_id` bigint(20) DEFAULT NULL COMMENT '通话记录id',
  `call_start_time` varchar(20) DEFAULT NULL COMMENT '起始时间',
  `call_address` varchar(50) DEFAULT NULL COMMENT '通话地点',
  `call_type_name` varchar(32) DEFAULT NULL COMMENT '呼叫类型 主叫/被叫/呼转/未知',
  `call_other_number` varchar(32) DEFAULT NULL COMMENT '对方号码',
  `call_time` varchar(32) DEFAULT NULL COMMENT '通话时长',
  `call_land_type` varchar(32) DEFAULT NULL COMMENT '通话类型 本地/漫游',
  `call_roam_cost` varchar(32) DEFAULT NULL COMMENT '本地费或漫游费(分) ',
  `call_long_distance` varchar(32) DEFAULT NULL COMMENT '长途费(分) ',
  `call_discount` varchar(32) DEFAULT NULL COMMENT '减免(分) ',
  `call_cost` varchar(32) DEFAULT NULL COMMENT '费用小计(分) ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=380 DEFAULT CHARSET=utf8 COMMENT='同盾运营商通话记录具体详细';


CREATE TABLE `cl_operator_td_sms_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL,
  `req_log_id` bigint(20) DEFAULT NULL COMMENT '请求记录id',
  `order_no` varchar(50) DEFAULT NULL COMMENT '请求订单号',
  `total_msg_cost` varchar(32) DEFAULT NULL COMMENT '费用合计',
  `total_msg_count` varchar(32) DEFAULT NULL COMMENT '总短信次数',
  `msg_cycle` varchar(32) DEFAULT NULL COMMENT '短信周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='同盾运营商短信详单';


CREATE TABLE `cl_operator_td_sms_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `info_id` bigint(20) DEFAULT NULL,
  `msg_start_time` varchar(32) DEFAULT NULL COMMENT '起始时间',
  `msg_type` varchar(32) DEFAULT NULL COMMENT '发送方式',
  `msg_other_num` varchar(32) DEFAULT NULL COMMENT '对方号码',
  `msg_channel` varchar(32) DEFAULT NULL COMMENT '信息类型',
  `msg_biz_name` varchar(32) DEFAULT NULL COMMENT '业务类型',
  `msg_address` varchar(32) DEFAULT NULL COMMENT '短信地区',
  `msg_fee` varchar(32) DEFAULT NULL COMMENT '通信费(分)',
  `msg_discount` varchar(32) DEFAULT NULL COMMENT '减免(分)',
  `msg_cost` varchar(32) DEFAULT NULL COMMENT '费用小计(分)',
  `msg_remark` varchar(32) DEFAULT NULL COMMENT '备注(分)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=711 DEFAULT CHARSET=utf8 COMMENT='同盾运营商短信记录具体记录';

-- 华道黑名单
CREATE TABLE `cl_rc_huadao_blacklist_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户标识',
  `order_no` varchar(50) DEFAULT '' COMMENT '订单号',
  `is_black` varchar(64) DEFAULT '' COMMENT '同步响应返回是否黑名单内容',
  `resp_code` varchar(10) DEFAULT '' COMMENT '响应码',
  `resp_params` longtext COMMENT '同步响应结果',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='华道黑名单记录表';


--  催收订单金额字段保留2位小数
alter table cl_urge_repay_order  modify column penalty_amout decimal(10,2) DEFAULT '0';
alter table cl_urge_repay_order  modify column amount decimal(10,2) DEFAULT '0';


-- 将operator_basic中的网龄改为int类型
alter table cl_operator_basic  modify column `extend_phone_age` int(11) DEFAULT 0 COMMENT '网龄';
-- 添加user_id
ALTER TABLE `cl_operator_basic`ADD COLUMN `user_id`  bigint(20) NOT NULL DEFAULT 0 COMMENT '用户id' AFTER `id`;
UPDATE `cl_operator_basic` SET user_id = (SELECT id FROM cl_user WHERE login_name = basic_phone_num)

-- 2017-5-31 11:57:34 sys_cofing表添加access_code_able字段（默认关闭）
INSERT INTO `arc_sys_config` VALUES (null, '20', '是否启用访问码','access_code_able','20','1','10开20关','1');

-- 2017-5-31 17:00:00 sys_user 表 删除字段 is_delete
ALTER TABLE arc_sys_user DROP COLUMN is_delete   



-- 2017-06-01 20:22　修改数据库结构
ALTER TABLE `cl_operator_basic` 
CHANGE `gmt_modified` `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
CHANGE `gmt_create` `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
CHANGE `extend_join_dt` `extend_join_dt` datetime DEFAULT NULL COMMENT '入网时间';

ALTER TABLE `cl_operator_bills`
CHANGE `gmt_modified` `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
CHANGE `gmt_create` `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
CHANGE `month` `month` datetime NOT NULL COMMENT '语音账单月份',
CHANGE `bill_month_date_end` `bill_month_date_end` datetime NOT NULL COMMENT '计费周期-结束日期';

ALTER TABLE `cl_operator_voices`
CHANGE `gmt_modified` `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
CHANGE `gmt_create` `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
CHANGE `month` `month` datetime DEFAULT NULL COMMENT '语音账单月份',
CHANGE `voice_date` `voice_date` datetime DEFAULT NULL COMMENT '时间';

ALTER TABLE `cl_opinion`
CHANGE `confirm_time` `confirm_time` datetime DEFAULT NULL COMMENT '确认时间';


ALTER TABLE `cl_qiancheng_req_log`
CHANGE `create_time` `create_time` datetime NOT NULL COMMENT '添加时间',
CHANGE `update_time` `update_time` datetime DEFAULT NULL COMMENT '回调更新时间';

ALTER TABLE `cl_rc_scene_business_log`
CHANGE `update_time` `update_time` datetime DEFAULT NULL COMMENT '更新时间';

ALTER TABLE `cl_tongdun_req_log`
CHANGE `update_time` `update_time` datetime DEFAULT NULL COMMENT '查询报告时间';

-- 运营商认证通话记录 cl_operator_voices 补充user_id 2017-6-5
ALTER TABLE `cl_operator_voices` ADD COLUMN `user_id` bigint(20) DEFAULT NULL COMMENT '用户id' AFTER `id`;
UPDATE cl_operator_voices v INNER JOIN cl_user u ON v.phone_num = u.login_name SET v.user_id = u.id;
-- 运营商认证通话记录 数据迁移 2017-6-6
INSERT INTO cl_operator_voices_1(user_id,gmt_modified,phone_num,voice_place,gmt_create,voice_duration,`month`,voice_type,voice_to_number,voice_date,voice_status,biz_no,create_time) 
SELECT user_id,gmt_modified,phone_num,voice_place,gmt_create,voice_duration,month,voice_type,voice_to_number,voice_date,voice_status,biz_no,create_time FROM cl_operator_voices WHERE user_id < 30000;

INSERT INTO cl_operator_voices_2(user_id,gmt_modified,phone_num,voice_place,gmt_create,voice_duration,`month`,voice_type,voice_to_number,voice_date,voice_status,biz_no,create_time) 
SELECT user_id,gmt_modified,phone_num,voice_place,gmt_create,voice_duration,month,voice_type,voice_to_number,voice_date,voice_status,biz_no,create_time FROM cl_operator_voices WHERE user_id >= 30000 and user_id < 60000;

INSERT INTO cl_operator_voices_3(user_id,gmt_modified,phone_num,voice_place,gmt_create,voice_duration,`month`,voice_type,voice_to_number,voice_date,voice_status,biz_no,create_time) 
SELECT user_id,gmt_modified,phone_num,voice_place,gmt_create,voice_duration,month,voice_type,voice_to_number,voice_date,voice_status,biz_no,create_time FROM cl_operator_voices WHERE user_id >= 60000 and user_id < 90000;

-- 通讯录 数据迁移  2017-6-6
INSERT INTO cl_user_contacts_1(name,phone,user_id) SELECT name,phone,user_id FROM cl_user_contacts WHERE user_id < 10000;
INSERT INTO cl_user_contacts_2(name,phone,user_id) SELECT name,phone,user_id FROM cl_user_contacts WHERE user_id >= 30000 and user_id < 60000;
INSERT INTO cl_user_contacts_3(name,phone,user_id) SELECT name,phone,user_id FROM cl_user_contacts WHERE user_id >= 60000 and user_id < 90000;

-- 短信通道
INSERT INTO `arc_sys_config` (`type`, `name`, `code`, `value`, `status`, `remark`, `creator`) VALUES ('60', '短信通道', 'sms_passageway', '20', '1', '10大圣  20创蓝', '1');
INSERT INTO `arc_sys_config` (`type`, `name`, `code`, `status`, `remark`, `creator`) VALUES ('60', '创蓝短信需要的变量', 'cl_sms_value', '{\"url\":\"http://sms.253.com/msg/send\",\"un\":\"N2116381\",\"pw\":\"BvpRkn869\",\"rd\":\"1\",\"ex\":\"\"}', '1', '创蓝短信需要的变量', '1');

-- 贷后邦反欺诈信息
CREATE TABLE `cl_rc_dhb_binding` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) DEFAULT NULL COMMENT '订单号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `binding_idcards_size` int(32) DEFAULT NULL COMMENT '可疑绑定身份证个数',
  `binding_idcards_detail` mediumtext COMMENT '可疑绑定身份证具体信息',
  `binding_phones_size` int(32) DEFAULT NULL COMMENT '可疑绑定号码个数',
  `binding_phones_detail` mediumtext COMMENT '可疑绑定号码具体信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='贷后邦可疑绑定信息';

CREATE TABLE `cl_rc_dhb_history_org` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) DEFAULT NULL COMMENT '订单号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `online_installment_cnt` int(32) DEFAULT NULL COMMENT '线上消费分期出现次数',
  `offline_installment_cnt` int(32) DEFAULT NULL COMMENT '线下消费分期出现次数',
  `credit_card_repayment_cnt` int(32) DEFAULT NULL COMMENT '信用卡代还出现次数',
  `payday_loan_cnt` int(32) DEFAULT NULL COMMENT '小额快速贷出现次数',
  `online_cash_loan_cnt` int(32) DEFAULT NULL COMMENT '线上现金贷出现次数',
  `offline_cash_loan_cnt` int(32) DEFAULT NULL COMMENT '线下现金贷出现次数',
  `others_cnt` int(32) DEFAULT NULL COMMENT '其他',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='贷后邦历史机构类型';
CREATE TABLE `cl_rc_dhb_history_search` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) DEFAULT NULL COMMENT '订单号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `search_cnt` int(32) DEFAULT NULL COMMENT '历史查询总次数',
  `search_cnt_recent_7_days` int(32) DEFAULT NULL COMMENT '最近7天查询次数',
  `search_cnt_recent_14_days` int(32) DEFAULT NULL COMMENT '最近14天查询次数',
  `search_cnt_recent_30_days` int(32) DEFAULT NULL COMMENT '最近30天查询次数',
  `search_cnt_recent_60_days` int(32) DEFAULT NULL COMMENT '最近60天查询次数',
  `search_cnt_recent_90_days` int(32) DEFAULT NULL COMMENT '最近90天查询次数',
  `search_cnt_recent_180_days` int(32) DEFAULT NULL COMMENT '最近180天查询次数',
  `org_cnt` int(32) DEFAULT NULL COMMENT '历史查询总机构数',
  `org_cnt_recent_7_days` int(32) DEFAULT NULL COMMENT '最近7天查询机构数',
  `org_cnt_recent_14_days` int(32) DEFAULT NULL COMMENT '最近14天查询机构数',
  `org_cnt_recent_30_days` int(32) DEFAULT NULL COMMENT '最近30天查询机构数',
  `org_cnt_recent_60_days` int(32) DEFAULT NULL COMMENT '最近60天查询机构数',
  `org_cnt_recent_90_days` int(32) DEFAULT NULL COMMENT '最近90天查询机构数',
  `org_cnt_recent_180_days` int(32) DEFAULT NULL COMMENT '最近180天查询机构数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='贷后邦历史查询';

CREATE TABLE `cl_rc_dhb_req_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) DEFAULT NULL COMMENT '申请订单号',
  `borrow_id` bigint(20) DEFAULT NULL COMMENT '借款标识',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `resp_code` varchar(10) DEFAULT NULL COMMENT '回调返回码',
  `resp_params` mediumtext COMMENT '同步响应结果',
  `resp_time` datetime DEFAULT NULL COMMENT '同步响应时间',
  `resp_order_no` varchar(64) DEFAULT '' COMMENT '同步响应订单号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='贷后邦贷后邦反欺诈请求记录表';

CREATE TABLE `cl_rc_dhb_risk_blacklist` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) DEFAULT NULL COMMENT '订单号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `idcard_in_blacklist` varchar(10) DEFAULT NULL COMMENT '身份证是否命中黑名单',
  `phone_in_blacklist` varchar(10) DEFAULT NULL COMMENT '手机号是否命中黑名单',
  `in_court_blacklist` varchar(10) DEFAULT NULL COMMENT '是否命中法院黑名单',
  `in_p2p_blacklist` varchar(10) DEFAULT NULL COMMENT '是否命中网贷黑名单',
  `in_bank_blacklist` varchar(10) DEFAULT NULL COMMENT '是否命中银行黑名单',
  `last_appear_idcard_in_blacklist` varchar(32) DEFAULT NULL COMMENT '最近该身份证出现在黑名单中时间',
  `last_appear_phone_in_blacklist` varchar(32) DEFAULT NULL COMMENT '最近该手机号出现在黑名单中时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='贷后邦黑名单';

CREATE TABLE `cl_rc_dhb_risk_social_network` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) DEFAULT NULL COMMENT '订单号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `sn_score` int(32) DEFAULT NULL COMMENT '葫芦分',
  `sn_order1_contacts_cnt` int(32) DEFAULT NULL COMMENT '直接联系人',
  `sn_order1_blacklist_contacts_cnt` int(32) DEFAULT NULL COMMENT '直接联系人在黑名单中数量(直接黑人)',
  `sn_order2_blacklist_contacts_cnt` int(32) DEFAULT NULL COMMENT '间接联系人在黑名单中数量(间接黑人)',
  `sn_order2_blacklist_routers_cnt` int(32) DEFAULT NULL COMMENT '认识间接黑人的直接联系人个数',
  `sn_order2_blacklist_routers_pct` double(32,2) DEFAULT NULL COMMENT '认识间接黑人的直接联系人比例',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='贷后邦社交风险点';

CREATE TABLE `cl_rc_dhb_user_basic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) DEFAULT NULL COMMENT '订单号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `age` int(20) DEFAULT '0' COMMENT '年龄',
  `gender` varchar(2) DEFAULT NULL COMMENT '性别',
  `birthday` varchar(32) DEFAULT NULL COMMENT '生日日期',
  `idcard_validate` int(1) DEFAULT NULL COMMENT '身份证是否是有效身份证   1: 通过，0：未通过',
  `idcard_province` varchar(32) DEFAULT NULL COMMENT '身份证户籍省份',
  `idcard_city` varchar(32) DEFAULT NULL COMMENT '身份证户籍城市',
  `idcard_region` varchar(32) DEFAULT NULL COMMENT '身份证户籍地区',
  `phone_operator` varchar(32) DEFAULT NULL COMMENT '手机运营商',
  `phone_province` varchar(32) DEFAULT NULL COMMENT '手机归属地省份',
  `phone_city` varchar(32) DEFAULT NULL COMMENT '手机归属地城市',
  `record_idcard_days` int(20) DEFAULT '0' COMMENT '身份证号记录天数',
  `record_phone_days` int(20) DEFAULT '0' COMMENT '手机号记录天数',
  `last_appear_idcard` varchar(32) DEFAULT NULL COMMENT '身份证最近出现时间',
  `last_appear_phone` varchar(32) DEFAULT NULL COMMENT '手机号最近出现时间',
  `used_idcards_cnt` int(20) DEFAULT '0' COMMENT '关联身份证数量',
  `used_phones_cnt` int(20) DEFAULT '0' COMMENT '关联手机号数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='贷后邦用户基本信息表';



-- 还款计划添加创建时间  初始化历史数据
ALTER TABLE `cl_borrow_repay` ADD COLUMN `create_time` DATETIME NOT NULL  COMMENT '创建时间' AFTER `penalty_day`;
UPDATE  cl_borrow_repay br,cl_borrow_progress bp SET br.`create_time`  = bp.`create_time` WHERE  br.`borrow_id` = bp.`borrow_id` AND bp.`state` = 30 
INSERT INTO `arc_sys_config` ( `type`, `name`, `code`, `value`, `status`, `remark`, `creator`) VALUES ( '20', '当日放款上限', 'loan_ceiling', '10000', '1', '当日放款上限', '1');

-- 去掉cl_operator_bills 字段的非空限制  2017-06-07 
ALTER TABLE cl_operator_bills  modify column `phone_num` varchar(11) DEFAULT '' COMMENT '号码';
ALTER TABLE cl_operator_bills  modify column `month` datetime DEFAULT NULL COMMENT '语音账单月份';
ALTER TABLE cl_operator_bills  modify column `bill_month_date_start` datetime DEFAULT NULL COMMENT '计费周期-起始日期';
ALTER TABLE cl_operator_bills  modify column `bill_month_date_end` datetime DEFAULT NULL COMMENT '计费周期-结束日期';

-- arc_sys_config 增加规则引擎配置表参数  2017-06-08
INSERT INTO `arc_sys_config` ( `type`, `name`, `code`, `value`, `status`, `remark`, `creator`) VALUES ( '20', '规则引擎配置表', 'rule_tables', 'cl_user_base_info,cl_user_auth,cl_zhima,cl_user_equipment_info,cl_rc_phone_call_count,cl_rc_ds_cheat_info,cl_rc_ds_bad_info,cl_rc_contact_count,cl_rc_borrow_count,cl_qiancheng_req_log,cl_operator_req_log', '1', '规则引擎配置表', '1');

--  2017/6/9 催收订单添加 借款订单号
ALTER TABLE `cl_urge_repay_order` ADD COLUMN  `order_no` varchar(255) DEFAULT NULL COMMENT '借款订单号' AFTER `borrow_id`;
UPDATE  cl_urge_repay_order o SET o.`order_no` =  (select order_no from cl_borrow b WHERE  o.`borrow_id` = b.`id`) ;

-- 2017-06-14 添加app首页引流功能
INSERT INTO `arc_sys_config` VALUES ('143', '20', 'APP引流地址', 'divert_url', 'http://h5.qa.51xjzx.com/popWin', '1', 'APP引流地址', '1');
INSERT INTO `arc_sys_config` VALUES ('144', '20', '引流开关', 'divert_able', '10', '1', '10 - 开 20 - 关', '1');

-- 删除多余参数
delete  from  arc_sys_config where code= 'levelTwo';
delete  from  arc_sys_config where code= 'customer_hotline';

-- 2017-06-20
INSERT INTO `arc_sys_config` VALUES (null, '20', '逾期罚金上限', 'penalty_amout_max', '0.5', '1', '超过本金一定比例后不再计算罚金', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '奖金发放下限', 'amount_grant_min', '100', '1', '达到一定额度才给予发放奖金', '1');

-- 2017-06-22修改系统配置表
UPDATE `arc_sys_config` SET `code` = 'level_one' WHERE `name` LIKE '一级代理分润率';
DELETE FROM `arc_sys_config` WHERE `name` LIKE '二级代理分润率';
UPDATE `arc_sys_config` SET `code` = 'level_three' WHERE `name` LIKE '普通用户分润率';
DELETE FROM `arc_sys_config` WHERE `name` LIKE '%客户热线%';
DELETE FROM `arc_sys_config` WHERE `name` LIKE '%天行学信%';

INSERT INTO `arc_sys_config` VALUES (null, '20', '商汤2.0OCR地址识别地址', 'linkface_idOcr', 'http://ucdevapi.ucredit.erongyun.net/linkface/idOcr', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '商汤2.0人证对比地址', 'linkface_liVerification', 'http://ucdevapi.ucredit.erongyun.net/linkface/liVerification', '1', null, '1');

-- 浅橙风控审核保存请求参数
ALTER TABLE cl_qiancheng_req_log ADD COLUMN req_params mediumtext  COMMENT '请求参数';

-- 2017年6月28日 17:37:32 增加上数公积金的请求地址(同上数运营商的请求地址)
INSERT INTO `arc_sys_config` (`type`,`name`,`code`,`value`,`status`,`remark`,`creator`)VALUES('70','公积金认证_接口地址','acc_fund_apihost','https://api.dsdatas.com/credit/api/v1.5/query','1','上数公积金验证用的域名','1');

-- 2017-07-04 e签宝信息
INSERT INTO `arc_sys_config` VALUES (null, '60', 'e签宝签署账号标识', 'account_id', '32EB904AA168438A9D0F480AC939511E', '1', '', '1');
INSERT INTO `arc_sys_config` VALUES (null, '60', 'e签宝企业机构名称', 'organ_name', '湖州英豪网络科技有限公司', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES (null, '60', 'e签宝组织机构代号', 'organ_code', '91330501MA28CE896U', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES (null, '60', 'e签宝组织机构类型', 'reg_type', 'MERGE', '1', '企业注册类型，NORMAL（组织机构代码）、MERGE（社会信用代码）、REGCODE（工商注册号）', '1');
INSERT INTO `arc_sys_config` VALUES (null, '60', 'e签宝部署服务器域名', 'tech_host', 'http://10.10.2.124:8080', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES (null, '60', 'e签宝签署id', 'project_id', '1111563517', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES (null, '60', 'e签宝签署秘钥', 'project_secret', '95439b0863c241c63a861b87d1e647b7', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES (null, '60', 'e签宝开放平台地址', 'itsm_api_url', 'http://121.43.159.210:8080/tgmonitor/rest/app!getAPIInfo2', '1', null, '1');
ALTER TABLE `cl_borrow` ADD COLUMN `sign_service_id`  varchar(32) NULL DEFAULT NULL COMMENT 'e签宝签署记录id' AFTER `ip`;

-- 2017年7月5日 16:58:46 在未注册的时候首页显示一个额度，在注册的时候给用户另一个额度
-- INSERT INTO `arc_sys_config` VALUES (null, '20', '未注册时显示额度', 'unregistered_credit', '3000', '1', '未注册时显示额度', '1');

-- 2017年7月6日 10:00:21 设备信息表里面的手机型号字段存在超过指定长度的值的情况
ALTER TABLE `cl_user_equipment_info` MODIFY COLUMN `phone_brand`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '手机品牌' AFTER `phone_type`;


-- 2017-07-06 风控数据-简版
CREATE TABLE `cl_rc_simple_borrow_count` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户标识',
  `count_one` int(11) DEFAULT '0' COMMENT '借款人逾期30天以上已还借款数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='风控数据-（简）借款统计';

CREATE TABLE `cl_rc_simple_contact_count` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户标识',
  `count_one` int(11) DEFAULT '0' COMMENT '通讯录总条数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='风控数据-（简）通讯录统计';

CREATE TABLE `cl_rc_simple_voices_count` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户标识',
  `count_one` int(11) DEFAULT '0' COMMENT '通话记录总次数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='风控数据-（简）通话记录统计';


-- 单笔还款计划最大代扣次数  2017-07-12
INSERT INTO `arc_sys_config` VALUES (null, '20', '代扣最大次数', 'do_repayment_max', '9', '1', '单笔还款计划代扣最大次数', '1');
INSERT INTO `arc_sys_config` VALUES (null, '20', '是否代扣今天的待还', 'do_repayment_today', '20', '1', '10代扣，20不代扣', '1');
INSERT INTO `arc_sys_config` VALUES (null, '10', 'manage服务域名', 'manage_host', 'http://10.10.2.156:8080', '1', '管理后台域名', '1');
DELETE FROM `arc_sys_config` WHERE code = 'borrow_most_times';
ALTER TABLE cl_profit_amount MODIFY cashed decimal(20,2) DEFAULT '0.00' COMMENT '已提现';

-- 新增复借是否请求浅橙开关，默认不开
INSERT INTO `arc_sys_config` VALUES (null,'20', '复借是否请求浅橙开关', 'reborrow_qiancheng_switch', '2', '1', '1 开 2 关', '1');

-- 2017-8-7 银行卡四要素校验接口名称
INSERT INTO `arc_sys_config` VALUES (null, '70', '四要素认证接口', 'four_elements_interfaceName', 'txbankCardFourCheckFourQuery', '1', '四要素认证接口', '1');
INSERT INTO `arc_sys_config` VALUES (null, '60', '获取短信报告结果', 'sms_apihost_report', 'http://api.dsdatas.com/smsSend/getReportByOrderNo', '1', '获取短信报告结果', '1');