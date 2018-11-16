CREATE TABLE `cl_rc_r360_blacklist_log` (
   `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
   `user_id` BIGINT(20) NOT NULL COMMENT '用户标识',
   `order_no` VARCHAR(50) DEFAULT '' COMMENT '订单号',
   `is_black` VARCHAR(64) DEFAULT '' COMMENT '同步响应返回是否黑名单内容',
   `resp_code` VARCHAR(10) DEFAULT '' COMMENT '响应码',
   `resp_params` LONGTEXT COMMENT '同步响应结果',
   `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
   `channel` VARCHAR(50) DEFAULT NULL COMMENT '渠道',
   PRIMARY KEY (`id`)
 ) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='融360黑名单记录表'


后台配置
             code                       value                 name
  rong360_anti_fraud_method   tianji.api.agenty.AntiFraud    融360评分方法
  rong360_black_list_method   tianji.api.agentr.blacklist    融360黑名单方法



  baiRong_userName                 zlAPI(可能是测试账号)                       百融用户名
  baiRong_pwd                      zlAPI(可能是测试账号)                       百融密码
  baiRong_loginName                SandboxLoginApi(测试) /LoginApi (正式)      百融LoginApi
  baiRong_apiCode                  3000901(可能是测试账号)                     百融apiCode
  baiRong_apiName                  SandboxApi(测试)/BankServer4Api (正式)     百融apiName
  baiRong_modelName                brcreditpoint                              百融查询模块


 //百融tokenId记录表
CREATE TABLE `cl_rc_bairong_login_record` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `tokenId` varchar(200) DEFAULT NULL COMMENT '百融登录tokenid',
   `requestTime` datetime DEFAULT NULL COMMENT '请求时间',
   `expireTime` datetime DEFAULT NULL COMMENT '到期时间',
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8


 CREATE TABLE `cl_rong360_grade` (
   `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
   `user_id` BIGINT(20) DEFAULT NULL COMMENT '用户标识',
   `score` INT (10) DEFAULT '' COMMENT '分数',
   `remark` VARCHAR(200) DEFAULT NULL COMMENT '标记',
   `tag` VARCHAR(200) DEFAULT NULL COMMENT 'tag',
   `createTime` DATETIME DEFAULT NULL COMMENT '写入时间',
   PRIMARY KEY (`id`),
   KEY `user_id` (`user_id`) USING BTREE
 ) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='融360信用评分'


  CREATE TABLE `cl_bairong_grade` (
   `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
   `user_id` BIGINT(20) DEFAULT NULL COMMENT '用户标识',
   `brcreditpoint` INT (10) DEFAULT '' COMMENT '分数',
   `remark` VARCHAR(200) DEFAULT NULL COMMENT '标记',
   `createTime` DATETIME DEFAULT NULL COMMENT '写入时间',
       `code` VARCHAR(100) DEFAULT '',
	swift_number                 VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_ca_refuse   VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_com_fraud   VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_ca_bad      VARCHAR(100) DEFAULT '',
	sl_lm_cell_bank_overdue      VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_other_refuse VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_mc_fraud    VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_mc_lost     VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_other_bad   VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_mc_overdue  VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_com_refuse  VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_ca_overdue  VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_p2p_bad     VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_other_overdue  VARCHAR(100) DEFAULT '',
	sl_lm_cell_bank_fraud        VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_ca_fraud    VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_cf_lost     VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_mc_bad      VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_cf_fraud    VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_other_fraud VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_p2p_refuse  VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_com_lost    VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_p2p_lost    VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_other_lost  VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_com_overdue VARCHAR(100) DEFAULT '',
	sl_lm_cell_bank_refuse       VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_p2p_fraud   VARCHAR(100) DEFAULT '',
	sl_lm_cell_bank_lost         VARCHAR(100) DEFAULT '',
	sl_lm_cell_abnormal          VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_p2p_overdue VARCHAR(100) DEFAULT '',
	sl_lm_cell_phone_overdue     VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_cf_overdue  VARCHAR(100) DEFAULT '',
	sl_lm_cell_bank_bad          VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_mc_refuse   VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_ca_lost     VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_cf_bad      VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_com_bad     VARCHAR(100) DEFAULT '',
	sl_lm_cell_nbank_cf_refuse   VARCHAR(100) DEFAULT '',
	sl_id_nbank_ca_refuse        VARCHAR(100) DEFAULT '',
	sl_id_nbank_com_fraud        VARCHAR(100) DEFAULT '',
	sl_id_p2p_fraud              VARCHAR(100) DEFAULT '',
	sl_id_nbank_ca_bad           VARCHAR(100) DEFAULT '',
	sl_id_bank_overdue           VARCHAR(100) DEFAULT '',
	sl_id_nbank_other_refuse     VARCHAR(100) DEFAULT '',
	sl_id_nbank_mc_fraud         VARCHAR(100) DEFAULT '',
	sl_id_nbank_mc_lost          VARCHAR(100) DEFAULT '',
	sl_id_nbank_other_bad        VARCHAR(100) DEFAULT '',
	sl_id_court_executed         VARCHAR(100) DEFAULT '',
	sl_id_nbank_mc_overdue       VARCHAR(100) DEFAULT '',
	sl_id_nbank_com_refuse       VARCHAR(100) DEFAULT '',
	sl_id_nbank_ca_overdue       VARCHAR(100) DEFAULT '',
	sl_id_nbank_p2p_bad          VARCHAR(100) DEFAULT '',
	sl_id_p2p_lost               VARCHAR(100) DEFAULT '',
	sl_id_p2p_overdue            VARCHAR(100) DEFAULT '',
	sl_id_nbank_other_overdue    VARCHAR(100) DEFAULT '',
	sl_id_bank_fraud             VARCHAR(100) DEFAULT '',
	sl_id_p2p_bad                VARCHAR(100) DEFAULT '',
	sl_id_nbank_ca_fraud         VARCHAR(100) DEFAULT '',
	sl_id_nbank_cf_lost          VARCHAR(100) DEFAULT '',
	sl_id_nbank_mc_bad           VARCHAR(100) DEFAULT '',
	sl_id_nbank_cf_fraud         VARCHAR(100) DEFAULT '',
	sl_id_nbank_other_fraud      VARCHAR(100) DEFAULT '',
	sl_id_nbank_p2p_refuse       VARCHAR(100) DEFAULT '',
	sl_id_nbank_com_lost         VARCHAR(100) DEFAULT '',
	sl_id_nbank_p2p_lost         VARCHAR(100) DEFAULT '',
	sl_id_nbank_other_lost       VARCHAR(100) DEFAULT '',
	sl_id_nbank_com_overdue      VARCHAR(100) DEFAULT '',
	sl_id_bank_refuse            VARCHAR(100) DEFAULT '',
	sl_id_nbank_p2p_fraud        VARCHAR(100) DEFAULT '',
	sl_id_bank_lost              VARCHAR(100) DEFAULT '',
	sl_id_abnormal               VARCHAR(100) DEFAULT '',
	sl_id_nbank_p2p_overdue      VARCHAR(100) DEFAULT '',
	sl_id_phone_overdue          VARCHAR(100) DEFAULT '',
	sl_id_nbank_cf_overdue       VARCHAR(100) DEFAULT '',
	sl_id_bank_bad               VARCHAR(100) DEFAULT '',
	sl_id_nbank_mc_refuse        VARCHAR(100) DEFAULT '',
	sl_id_nbank_ca_lost          VARCHAR(100) DEFAULT '',
	sl_id_nbank_cf_bad           VARCHAR(100) DEFAULT '',
	sl_id_court_bad              VARCHAR(100) DEFAULT '',
	sl_id_nbank_com_bad          VARCHAR(100) DEFAULT '',
	sl_id_nbank_cf_refuse        VARCHAR(100) DEFAULT '',
	sl_id_p2p_refuse             VARCHAR(100) DEFAULT '',
	sl_cell_nbank_ca_refuse      VARCHAR(100) DEFAULT '',
	sl_cell_nbank_com_fraud      VARCHAR(100) DEFAULT '',
	sl_cell_p2p_fraud            VARCHAR(100) DEFAULT '',
	sl_cell_nbank_ca_bad         VARCHAR(100) DEFAULT '',
	sl_cell_bank_overdue         VARCHAR(100) DEFAULT '',
	sl_cell_nbank_other_refuse   VARCHAR(100) DEFAULT '',
	sl_cell_nbank_mc_fraud       VARCHAR(100) DEFAULT '',
	sl_cell_nbank_mc_lost        VARCHAR(100) DEFAULT '',
	sl_cell_nbank_other_bad      VARCHAR(100) DEFAULT '',
	sl_cell_nbank_mc_overdue     VARCHAR(100) DEFAULT '',
	sl_cell_nbank_com_refuse     VARCHAR(100) DEFAULT '',
	sl_cell_nbank_ca_overdue     VARCHAR(100) DEFAULT '',
	sl_cell_nbank_p2p_bad        VARCHAR(100) DEFAULT '',
	sl_cell_p2p_lost             VARCHAR(100) DEFAULT '',
	sl_cell_p2p_overdue          VARCHAR(100) DEFAULT '',
	sl_cell_nbank_other_overdue  VARCHAR(100) DEFAULT '',
	sl_cell_bank_fraud           VARCHAR(100) DEFAULT '',
	sl_cell_p2p_bad              VARCHAR(100) DEFAULT '',
	sl_cell_nbank_ca_fraud       VARCHAR(100) DEFAULT '',
	sl_cell_nbank_cf_lost        VARCHAR(100) DEFAULT '',
	sl_cell_nbank_mc_bad         VARCHAR(100) DEFAULT '',
	sl_cell_nbank_cf_fraud       VARCHAR(100) DEFAULT '',
	sl_cell_nbank_other_fraud    VARCHAR(100) DEFAULT '',
	sl_cell_nbank_p2p_refuse     VARCHAR(100) DEFAULT '',
	sl_cell_nbank_com_lost       VARCHAR(100) DEFAULT '',
	sl_cell_nbank_p2p_lost       VARCHAR(100) DEFAULT '',
	sl_cell_nbank_other_lost     VARCHAR(100) DEFAULT '',
	sl_cell_nbank_com_overdue    VARCHAR(100) DEFAULT '',
	sl_cell_bank_refuse          VARCHAR(100) DEFAULT '',
	sl_cell_nbank_p2p_fraud      VARCHAR(100) DEFAULT '',
	sl_cell_bank_lost            VARCHAR(100) DEFAULT '',
	sl_cell_abnormal             VARCHAR(100) DEFAULT '',
	sl_cell_nbank_p2p_overdue    VARCHAR(100) DEFAULT '',
	sl_cell_phone_overdue        VARCHAR(100) DEFAULT '',
	sl_cell_nbank_cf_overdue     VARCHAR(100) DEFAULT '',
	sl_cell_bank_bad             VARCHAR(100) DEFAULT '',
	sl_cell_nbank_mc_refuse      VARCHAR(100) DEFAULT '',
	sl_cell_nbank_ca_lost        VARCHAR(100) DEFAULT '',
	sl_cell_nbank_cf_bad         VARCHAR(100) DEFAULT '',
	sl_cell_nbank_com_bad        VARCHAR(100) DEFAULT '',
	sl_cell_nbank_cf_refuse     VARCHAR(100) DEFAULT '',
	sl_cell_p2p_refuse          VARCHAR(100) DEFAULT '',
	flag_score                  VARCHAR(100) DEFAULT '',
	flag_specialList_c          VARCHAR(100) DEFAULT '',

   PRIMARY KEY (`id`),
   KEY `user_id` (`user_id`) USING BTREE
 ) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='百融信用评分'






CREATE TABLE `cl_rc_jiguang_blacklist_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户标识',
  `order_no` varchar(50) DEFAULT '' COMMENT '订单号',
  `resp_code` varchar(10) DEFAULT '' COMMENT '响应码',
  `score` int(10) DEFAULT NULL COMMENT '分数',
  `hits` longtext COMMENT '同步响应结果集',
  `description` longtext COMMENT '同步响应结果',
  `resp_params` longtext COMMENT '同步响应结果',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='极光黑名单记录表';

CREATE TABLE `cl_rc_jiguang_lbscheck_log` (
   `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
   `user_id` BIGINT(20) NOT NULL COMMENT '用户标识',
   `order_no` VARCHAR(50) DEFAULT '' COMMENT '订单号',
   `is_black` VARCHAR(64) DEFAULT '' COMMENT '同步响应返回是否黑名单内容',
   `resp_code` VARCHAR(10) DEFAULT '' COMMENT '响应码',
   `resp_params` LONGTEXT COMMENT '同步响应结果',
   `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
   `channel` VARCHAR(50) DEFAULT NULL COMMENT '渠道',
   PRIMARY KEY (`id`)
 ) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='极光Lbs分级验真记录表'


CREATE TABLE `cl_rc_jiguang_lbsfuzzycheck_log` (
   `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
   `user_id` BIGINT(20) NOT NULL COMMENT '用户标识',
   `order_no` VARCHAR(50) DEFAULT '' COMMENT '订单号',
   `is_black` VARCHAR(64) DEFAULT '' COMMENT '同步响应返回是否黑名单内容',
   `resp_code` VARCHAR(10) DEFAULT '' COMMENT '响应码',
   `resp_params` LONGTEXT COMMENT '同步响应结果',
   `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
   `channel` VARCHAR(50) DEFAULT NULL COMMENT '渠道',
   PRIMARY KEY (`id`)
 ) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='极光Lbs模糊匹配记录表'