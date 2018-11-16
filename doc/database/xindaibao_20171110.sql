
DROP TABLE IF EXISTS `cl_fraud_td_basic`;
CREATE TABLE `cl_fraud_td_basic` (
  `Id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户编码',
  `report_id` varchar(64) DEFAULT NULL COMMENT '贷前申请风险报告编号',
  `reason_code` varchar(20) DEFAULT NULL COMMENT '调用失败时的错误码',
  `reason_desc` varchar(64) DEFAULT NULL COMMENT '错误详情描述',
  `final_score` int(11) DEFAULT NULL COMMENT '风险分数',
  `final_decision` varchar(20) DEFAULT NULL COMMENT '风险结果',
  `device_type` varchar(20) DEFAULT NULL COMMENT '设备类型',
  `proxy_info` varchar(64) DEFAULT NULL COMMENT '代理信息',
  `apply_time` bigint(20) DEFAULT NULL COMMENT '扫描时间',
  `report_time` bigint(20) DEFAULT NULL COMMENT '报告时间',
  `device_info` text COMMENT '设备环境信息',
  `geo_ip` varchar(500) DEFAULT NULL COMMENT '地理位置信息',
  `geo_trueip` varchar(500) DEFAULT NULL COMMENT '真实地理位置信息',
  `application_id` varchar(64) DEFAULT NULL COMMENT '申请编号',
  `credit_score` int(11) DEFAULT NULL COMMENT '信用分',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='同盾贷前审核基本报告';




DROP TABLE IF EXISTS `cl_fraud_td_hit_list`;
CREATE TABLE `cl_fraud_td_hit_list` (
   `Id` bigint(11) NOT NULL AUTO_INCREMENT,
   `user_id`  bigint(20) NULL DEFAULT NULL COMMENT '用户编码' ,
   `report_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '贷前申请风险报告编号' ,
   `idcard_hit_execution_list` int(8) DEFAULT NULL COMMENT '申请人姓名身份证号命中法院执行信息',
   `idcard_hit_court_list` int(8) DEFAULT NULL COMMENT '申请人姓名身份证号命中法院失信被执行人',
   `device_hit_black_list` int(8) DEFAULT NULL COMMENT '设备号命中信贷逾期黑名单',
   `mob_hit_black_list` int(8) DEFAULT NULL COMMENT '手机号命中欺诈名单',
   `idcard_hit_black_list` int(8) DEFAULT NULL COMMENT '身份证命中欺诈名单',
   `bank_hit_black_list` int(8) DEFAULT NULL COMMENT '银行卡号命中信贷逾期黑名单',
   `bank_mob_hit_black_list` int(8) DEFAULT NULL COMMENT '银行卡预留手机号命中信贷逾期黑名单',

   `mob_overdue_day_max` int(8) DEFAULT NULL COMMENT '手机号最高逾期天数',
   `idcard_overdue_day_max` int(8) DEFAULT NULL COMMENT '身份证最高逾期天数',
   `mob_hit_fuzzy_list` int(8) DEFAULT NULL COMMENT '手机号命中模糊名单',
   `idcard_hit_fuzzy_list` int(8) DEFAULT NULL COMMENT '身份证命中模糊名单',
   `mob_hit_grey_list` int(8) DEFAULT NULL COMMENT '手机号命中灰名单',
   `idcard_hit_grey_list` int(8) DEFAULT NULL COMMENT '身份证命中灰名单',
   `contact1_overdue_day_max` int(8) DEFAULT NULL COMMENT '第一联系人(近亲)手机号最高逾期天数',
   `contact2_overdue_day_max` int(8) DEFAULT NULL COMMENT '第一联系人(一般)手机号最高逾期天数',
   `report_date` datetime DEFAULT NULL COMMENT '报告日期',

  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='同盾贷前审核个人基本信息核查';


DROP TABLE IF EXISTS `cl_fraud_td_multi_platform`;
CREATE TABLE `cl_fraud_td_multi_platform` (
   `Id` bigint(11) NOT NULL AUTO_INCREMENT,
   `user_id`  bigint(20) NULL DEFAULT NULL COMMENT '用户编码' ,
   `report_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '贷前申请风险报告编号' ,
   `multi_platform_small_loan_cnt_7d` int(8) DEFAULT NULL COMMENT '7天内申请人在小额贷款公司申请借款次数',
   `multi_platform_p2p_cnt_7d` int(8) DEFAULT NULL COMMENT '7天内申请人在P2P平台申请借款次数',
   `multi_platform_consumer_cnt_7d` int(8) DEFAULT NULL COMMENT '7天内申请人在大型消费金融公司申请次数',
   `multi_platform_installment_cnt_7d` int(8) DEFAULT NULL COMMENT '7天内申请人在一般消费分期平台申请次数',
   `multi_platform_portals_cnt_7d` int(8) DEFAULT NULL COMMENT '7天内申请人在联网金融门户申请次数',
   `multi_platform_cnt_7d` int(8) DEFAULT NULL COMMENT '7天内不同平台的申请次数',
   `multi_platform_cnt_30d` int(8) DEFAULT NULL COMMENT '30天内不同平台的申请次数',

   `multi_platform_cnt_90d` int(8) DEFAULT NULL COMMENT '90天内不同平台的申请次数',
   `multi_platform_approved_cnt_90d` int(8) DEFAULT NULL COMMENT '3个月内申请人在多个平台被放款_不包含本合作方',
   `report_date` datetime DEFAULT NULL COMMENT '报告日期',

  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='同盾贷前审核多平台借贷申请检测';

