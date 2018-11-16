DROP TABLE IF EXISTS `cl_mohe_risk_contact_110_stats`;
CREATE TABLE `cl_mohe_risk_contact_110_stats` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `task_id` varchar(512) DEFAULT NULL COMMENT '数据获取任务id (关联)',
  `risk_type` varchar(512) DEFAULT NULL COMMENT '风险类型',
  `contact_count_1month` int(9) DEFAULT NULL COMMENT '近1月通话号码数量',
  `contact_count_3month` int(9) DEFAULT NULL COMMENT '近3月通话号码数量',
  `contact_count_6month` int(9) DEFAULT NULL COMMENT '近6月通话号码数量',
  `call_count_1month` int(9) DEFAULT NULL COMMENT '近1月通话次数',
  `call_count_3month` int(9) DEFAULT NULL COMMENT '近3月通话次数',
  `call_count_active_3month` int(9) DEFAULT NULL COMMENT '近3月主叫通话次数',
  `call_count_passive_3month` int(9) DEFAULT NULL COMMENT '近3月被叫通话次数',
  `call_count_6month` int(9) DEFAULT NULL COMMENT '近6月通话次数',
  `call_count_active_6month` int(9) DEFAULT NULL COMMENT '近6月主叫通话次数',
  `call_count_passive_6month` int(9) DEFAULT NULL COMMENT '近6月被叫通话次数',
  `call_time_1month` int(9) DEFAULT NULL COMMENT '近1月通话时长',
  `call_time_3month` int(9) DEFAULT NULL COMMENT '近3月通话时长',
  `call_time_active_3month` int(9) DEFAULT NULL COMMENT '近3月主叫通话时长',
  `call_time_passive_3month` int(9) DEFAULT NULL COMMENT '近3月被叫通话时长',
  `call_time_6month` int(9) DEFAULT NULL COMMENT '近6月通话时长',
  `call_time_active_6month` int(9) DEFAULT NULL COMMENT '近6月主叫通话时长',
  `call_time_passive_6month` int(9) DEFAULT NULL COMMENT '近6月被叫通话时长',
  `msg_count_3month` int(9) DEFAULT NULL COMMENT '近3月短信次数',
  `msg_count_6month` int(9) DEFAULT NULL COMMENT '近6月短信次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='110电话';


DROP TABLE IF EXISTS `cl_mohe_risk_contact_120_stats`;
CREATE TABLE `cl_mohe_risk_contact_120_stats` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `task_id` varchar(512) DEFAULT NULL COMMENT '数据获取任务id (关联)',
  `risk_type` varchar(512) DEFAULT NULL COMMENT '风险类型',
  `contact_count_1month` int(9) DEFAULT NULL COMMENT '近1月通话号码数量',
  `contact_count_3month` int(9) DEFAULT NULL COMMENT '近3月通话号码数量',
  `contact_count_6month` int(9) DEFAULT NULL COMMENT '近6月通话号码数量',
  `call_count_1month` int(9) DEFAULT NULL COMMENT '近1月通话次数',
  `call_count_3month` int(9) DEFAULT NULL COMMENT '近3月通话次数',
  `call_count_active_3month` int(9) DEFAULT NULL COMMENT '近3月主叫通话次数',
  `call_count_passive_3month` int(9) DEFAULT NULL COMMENT '近3月被叫通话次数',
  `call_count_6month` int(9) DEFAULT NULL COMMENT '近6月通话次数',
  `call_count_active_6month` int(9) DEFAULT NULL COMMENT '近6月主叫通话次数',
  `call_count_passive_6month` int(9) DEFAULT NULL COMMENT '近6月被叫通话次数',
  `call_time_1month` int(9) DEFAULT NULL COMMENT '近1月通话时长',
  `call_time_3month` int(9) DEFAULT NULL COMMENT '近3月通话时长',
  `call_time_active_3month` int(9) DEFAULT NULL COMMENT '近3月主叫通话时长',
  `call_time_passive_3month` int(9) DEFAULT NULL COMMENT '近3月被叫通话时长',
  `call_time_6month` int(9) DEFAULT NULL COMMENT '近6月通话时长',
  `call_time_active_6month` int(9) DEFAULT NULL COMMENT '近6月主叫通话时长',
  `call_time_passive_6month` int(9) DEFAULT NULL COMMENT '近6月被叫通话时长',
  `msg_count_3month` int(9) DEFAULT NULL COMMENT '近3月短信次数',
  `msg_count_6month` int(9) DEFAULT NULL COMMENT '近6月短信次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='120电话';


DROP TABLE IF EXISTS `cl_mohe_risk_contact_macao_stats`;
CREATE TABLE `cl_mohe_risk_contact_macao_stats` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `task_id` varchar(512) DEFAULT NULL COMMENT '数据获取任务id (关联)',
  `risk_type` varchar(512) DEFAULT NULL COMMENT '风险类型',
  `contact_count_1month` int(9) DEFAULT NULL COMMENT '近1月通话号码数量',
  `contact_count_3month` int(9) DEFAULT NULL COMMENT '近3月通话号码数量',
  `contact_count_6month` int(9) DEFAULT NULL COMMENT '近6月通话号码数量',
  `call_count_1month` int(9) DEFAULT NULL COMMENT '近1月通话次数',
  `call_count_3month` int(9) DEFAULT NULL COMMENT '近3月通话次数',
  `call_count_active_3month` int(9) DEFAULT NULL COMMENT '近3月主叫通话次数',
  `call_count_passive_3month` int(9) DEFAULT NULL COMMENT '近3月被叫通话次数',
  `call_count_6month` int(9) DEFAULT NULL COMMENT '近6月通话次数',
  `call_count_active_6month` int(9) DEFAULT NULL COMMENT '近6月主叫通话次数',
  `call_count_passive_6month` int(9) DEFAULT NULL COMMENT '近6月被叫通话次数',
  `call_time_1month` int(9) DEFAULT NULL COMMENT '近1月通话时长',
  `call_time_3month` int(9) DEFAULT NULL COMMENT '近3月通话时长',
  `call_time_active_3month` int(9) DEFAULT NULL COMMENT '近3月主叫通话时长',
  `call_time_passive_3month` int(9) DEFAULT NULL COMMENT '近3月被叫通话时长',
  `call_time_6month` int(9) DEFAULT NULL COMMENT '近6月通话时长',
  `call_time_active_6month` int(9) DEFAULT NULL COMMENT '近6月主叫通话时长',
  `call_time_passive_6month` int(9) DEFAULT NULL COMMENT '近6月被叫通话时长',
  `msg_count_3month` int(9) DEFAULT NULL COMMENT '近3月短信次数',
  `msg_count_6month` int(9) DEFAULT NULL COMMENT '近6月短信次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='澳门电话';


DROP TABLE IF EXISTS `cl_mohe_risk_contact_lawyer_stats`;
CREATE TABLE `cl_mohe_risk_contact_lawyer_stats` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `task_id` varchar(512) DEFAULT NULL COMMENT '数据获取任务id (关联)',
  `risk_type` varchar(512) DEFAULT NULL COMMENT '风险类型',
  `contact_count_1month` int(9) DEFAULT NULL COMMENT '近1月通话号码数量',
  `contact_count_3month` int(9) DEFAULT NULL COMMENT '近3月通话号码数量',
  `contact_count_6month` int(9) DEFAULT NULL COMMENT '近6月通话号码数量',
  `call_count_1month` int(9) DEFAULT NULL COMMENT '近1月通话次数',
  `call_count_3month` int(9) DEFAULT NULL COMMENT '近3月通话次数',
  `call_count_active_3month` int(9) DEFAULT NULL COMMENT '近3月主叫通话次数',
  `call_count_passive_3month` int(9) DEFAULT NULL COMMENT '近3月被叫通话次数',
  `call_count_6month` int(9) DEFAULT NULL COMMENT '近6月通话次数',
  `call_count_active_6month` int(9) DEFAULT NULL COMMENT '近6月主叫通话次数',
  `call_count_passive_6month` int(9) DEFAULT NULL COMMENT '近6月被叫通话次数',
  `call_time_1month` int(9) DEFAULT NULL COMMENT '近1月通话时长',
  `call_time_3month` int(9) DEFAULT NULL COMMENT '近3月通话时长',
  `call_time_active_3month` int(9) DEFAULT NULL COMMENT '近3月主叫通话时长',
  `call_time_passive_3month` int(9) DEFAULT NULL COMMENT '近3月被叫通话时长',
  `call_time_6month` int(9) DEFAULT NULL COMMENT '近6月通话时长',
  `call_time_active_6month` int(9) DEFAULT NULL COMMENT '近6月主叫通话时长',
  `call_time_passive_6month` int(9) DEFAULT NULL COMMENT '近6月被叫通话时长',
  `msg_count_3month` int(9) DEFAULT NULL COMMENT '近3月短信次数',
  `msg_count_6month` int(9) DEFAULT NULL COMMENT '近6月短信次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='律师号码';


DROP TABLE IF EXISTS `cl_mohe_risk_contact_debt_collect_stats`;
CREATE TABLE `cl_mohe_risk_contact_debt_collect_stats` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `task_id` varchar(512) DEFAULT NULL COMMENT '数据获取任务id (关联)',
  `risk_type` varchar(512) DEFAULT NULL COMMENT '风险类型',
  `contact_count_1month` int(9) DEFAULT NULL COMMENT '近1月通话号码数量',
  `contact_count_3month` int(9) DEFAULT NULL COMMENT '近3月通话号码数量',
  `contact_count_6month` int(9) DEFAULT NULL COMMENT '近6月通话号码数量',
  `call_count_1month` int(9) DEFAULT NULL COMMENT '近1月通话次数',
  `call_count_3month` int(9) DEFAULT NULL COMMENT '近3月通话次数',
  `call_count_active_3month` int(9) DEFAULT NULL COMMENT '近3月主叫通话次数',
  `call_count_passive_3month` int(9) DEFAULT NULL COMMENT '近3月被叫通话次数',
  `call_count_6month` int(9) DEFAULT NULL COMMENT '近6月通话次数',
  `call_count_active_6month` int(9) DEFAULT NULL COMMENT '近6月主叫通话次数',
  `call_count_passive_6month` int(9) DEFAULT NULL COMMENT '近6月被叫通话次数',
  `call_time_1month` int(9) DEFAULT NULL COMMENT '近1月通话时长',
  `call_time_3month` int(9) DEFAULT NULL COMMENT '近3月通话时长',
  `call_time_active_3month` int(9) DEFAULT NULL COMMENT '近3月主叫通话时长',
  `call_time_passive_3month` int(9) DEFAULT NULL COMMENT '近3月被叫通话时长',
  `call_time_6month` int(9) DEFAULT NULL COMMENT '近6月通话时长',
  `call_time_active_6month` int(9) DEFAULT NULL COMMENT '近6月主叫通话时长',
  `call_time_passive_6month` int(9) DEFAULT NULL COMMENT '近6月被叫通话时长',
  `msg_count_3month` int(9) DEFAULT NULL COMMENT '近3月短信次数',
  `msg_count_6month` int(9) DEFAULT NULL COMMENT '近6月短信次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='催收电话';


DROP TABLE IF EXISTS `cl_mohe_risk_contact_small_loan_stats`;
CREATE TABLE `cl_mohe_risk_contact_small_loan_stats` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `task_id` varchar(512) DEFAULT NULL COMMENT '数据获取任务id (关联)',
  `risk_type` varchar(512) DEFAULT NULL COMMENT '风险类型',
  `contact_count_1month` int(9) DEFAULT NULL COMMENT '近1月通话号码数量',
  `contact_count_3month` int(9) DEFAULT NULL COMMENT '近3月通话号码数量',
  `contact_count_6month` int(9) DEFAULT NULL COMMENT '近6月通话号码数量',
  `call_count_1month` int(9) DEFAULT NULL COMMENT '近1月通话次数',
  `call_count_3month` int(9) DEFAULT NULL COMMENT '近3月通话次数',
  `call_count_active_3month` int(9) DEFAULT NULL COMMENT '近3月主叫通话次数',
  `call_count_passive_3month` int(9) DEFAULT NULL COMMENT '近3月被叫通话次数',
  `call_count_6month` int(9) DEFAULT NULL COMMENT '近6月通话次数',
  `call_count_active_6month` int(9) DEFAULT NULL COMMENT '近6月主叫通话次数',
  `call_count_passive_6month` int(9) DEFAULT NULL COMMENT '近6月被叫通话次数',
  `call_time_1month` int(9) DEFAULT NULL COMMENT '近1月通话时长',
  `call_time_3month` int(9) DEFAULT NULL COMMENT '近3月通话时长',
  `call_time_active_3month` int(9) DEFAULT NULL COMMENT '近3月主叫通话时长',
  `call_time_passive_3month` int(9) DEFAULT NULL COMMENT '近3月被叫通话时长',
  `call_time_6month` int(9) DEFAULT NULL COMMENT '近6月通话时长',
  `call_time_active_6month` int(9) DEFAULT NULL COMMENT '近6月主叫通话时长',
  `call_time_passive_6month` int(9) DEFAULT NULL COMMENT '近6月被叫通话时长',
  `msg_count_3month` int(9) DEFAULT NULL COMMENT '近3月短信次数',
  `msg_count_6month` int(9) DEFAULT NULL COMMENT '近6月短信次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='小贷公司';



