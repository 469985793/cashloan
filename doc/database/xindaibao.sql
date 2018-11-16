SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for arc_borrow_rule_config
-- ----------------------------
DROP TABLE IF EXISTS `arc_borrow_rule_config`;
CREATE TABLE `arc_borrow_rule_config` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `borrow_rule_id` bigint(11) DEFAULT NULL COMMENT '借款规则配置id',
  `rule_id` bigint(11) DEFAULT NULL COMMENT '规则id',
  `rule_sort` int(11) DEFAULT NULL COMMENT '规则执行顺序',
  `config_id` bigint(11) DEFAULT NULL COMMENT '规则配置id',
  `config_sort` int(11) DEFAULT NULL COMMENT '配置执行顺序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='借款规则详细配置表';

-- ----------------------------
-- Records of arc_borrow_rule_config
-- ----------------------------

-- ----------------------------
-- Table structure for arc_borrow_rule_engine
-- ----------------------------
DROP TABLE IF EXISTS `arc_borrow_rule_engine`;
CREATE TABLE `arc_borrow_rule_engine` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `borrow_type_name` varchar(32) NOT NULL DEFAULT '' COMMENT '借款类型',
  `borrow_type` varchar(11) NOT NULL DEFAULT '' COMMENT '借款类型标识 10 分期借款',
  `adapted_name` varchar(32) DEFAULT NULL COMMENT '规则适用场景名称 10 贷前，20 贷后',
  `adapted_id` varchar(11) DEFAULT NULL COMMENT '规则适用场景标识 10 贷前，20 贷后',
  `add_time` datetime NOT NULL COMMENT '添加规则时间',
  `req_ext` varchar(64) DEFAULT '' COMMENT '预留字段',
  `rule_count` int(11) DEFAULT '0' COMMENT '规则数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='借款规则管理';

-- ----------------------------
-- Records of arc_borrow_rule_engine
-- ----------------------------

-- ----------------------------
-- Table structure for arc_borrow_rule_result
-- ----------------------------
DROP TABLE IF EXISTS `arc_borrow_rule_result`;
CREATE TABLE `arc_borrow_rule_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `borrow_id` bigint(20) NOT NULL COMMENT '借款申请表ID',
  `rule_id` bigint(20) NOT NULL COMMENT '规则表ID',
  `tb_nid` varchar(50) NOT NULL COMMENT '表英文名称',
  `tb_name` varchar(50) NOT NULL COMMENT '表中文名称',
  `col_nid` varchar(50) NOT NULL COMMENT '列名英文名称',
  `col_name` varchar(50) NOT NULL COMMENT '列名中文名称',
  `matching` varchar(50) NOT NULL DEFAULT '' COMMENT '匹配当前值',
  `value` varchar(50) NOT NULL COMMENT '匹配值',
  `rule` varchar(50) NOT NULL COMMENT '匹配规则表达式',
  `result` varchar(1) NOT NULL COMMENT '规则匹配结果  Y 匹配成功  N匹配失败',
  `result_type` varchar(2) NOT NULL DEFAULT '' COMMENT '结果类型  10 不通过 20 需人工复审 30 通过',
  `req_ext` varchar(50) DEFAULT NULL COMMENT '扩展字段',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `borrow_id` (`borrow_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='借款规则匹配结果表';

-- ----------------------------
-- Records of arc_borrow_rule_result
-- ----------------------------

-- ----------------------------
-- Table structure for arc_borrow_type_card
-- ----------------------------
DROP TABLE IF EXISTS `arc_borrow_type_card`;
CREATE TABLE `arc_borrow_type_card` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `borrow_type_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '借款类型id',
  `borrow_type_name` varchar(32) NOT NULL DEFAULT '' COMMENT '借款类型名称',
  `card_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '评分卡id',
  `card_name` varchar(32) NOT NULL DEFAULT '' COMMENT '评分卡名称',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评分卡类型绑定表';

-- ----------------------------
-- Records of arc_borrow_type_card
-- ----------------------------

-- ----------------------------
-- Table structure for arc_cr_card
-- ----------------------------
DROP TABLE IF EXISTS `arc_cr_card`;
CREATE TABLE `arc_cr_card` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `card_name` varchar(32) NOT NULL DEFAULT '' COMMENT '评分卡名称',
  `score` int(11) NOT NULL DEFAULT '0' COMMENT '评分卡总分',
  `state` varchar(2) NOT NULL DEFAULT '10' COMMENT '状态 10启用,20禁用',
  `type` varchar(2) DEFAULT '' COMMENT '是否被使用 10-已使用 20-未使用',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `nid` varchar(16) NOT NULL DEFAULT '' COMMENT '唯一标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评分卡';

-- ----------------------------
-- Records of arc_cr_card
-- ----------------------------

-- ----------------------------
-- Table structure for arc_cr_credit_type
-- ----------------------------
DROP TABLE IF EXISTS `arc_cr_credit_type`;
CREATE TABLE `arc_cr_credit_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '额度类型',
  `credit_type_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '借款类型id',
  `card_id` varchar(64) NOT NULL DEFAULT '0' COMMENT '评分卡关联id',
  `rank_id` varchar(64) NOT NULL DEFAULT '0' COMMENT '评分等级关联id',
  `borrow_type_id` varchar(20) NOT NULL DEFAULT '0' COMMENT '借款类型关联id',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='额度类型管理表';

-- ----------------------------
-- Records of arc_cr_credit_type
-- ----------------------------

-- ----------------------------
-- Table structure for arc_cr_factor
-- ----------------------------
DROP TABLE IF EXISTS `arc_cr_factor`;
CREATE TABLE `arc_cr_factor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `item_id` bigint(20) NOT NULL COMMENT '评分项目id',
  `factor_name` varchar(32) NOT NULL DEFAULT '' COMMENT '评分因子名称',
  `factor_score` int(11) NOT NULL DEFAULT '0' COMMENT '因子最高分值',
  `state` varchar(2) NOT NULL DEFAULT '10' COMMENT '状态 10启用 20禁用',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `type` varchar(8) DEFAULT '10' COMMENT '维护类型 10-系统 20-手动 30-关联评分卡',
  `nnid` varchar(8) DEFAULT '10' COMMENT '信息类型 10 定性 20定量',
  `ctable` varchar(32) DEFAULT '' COMMENT '关联表',
  `ctable_name` varchar(64) DEFAULT '' COMMENT '关联表名称',
  `ccolumn` varchar(32) DEFAULT '' COMMENT '关联字段',
  `ccolumn_name` varchar(64) DEFAULT '' COMMENT '关联字段名称',
  `ctype` varchar(8) DEFAULT '' COMMENT '关联字段类型',
  `card_id` bigint(20) DEFAULT '0' COMMENT '关联评分卡id',
  `nid` varchar(16) DEFAULT '' COMMENT '唯一标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评分因子表';

-- ----------------------------
-- Records of arc_cr_factor
-- ----------------------------

-- ----------------------------
-- Table structure for arc_cr_factor_param
-- ----------------------------
DROP TABLE IF EXISTS `arc_cr_factor_param`;
CREATE TABLE `arc_cr_factor_param` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `factor_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '评分因子id',
  `param_score` int(11) NOT NULL DEFAULT '0' COMMENT '分值',
  `formula` varchar(32) NOT NULL DEFAULT '' COMMENT '公式符号 <=>',
  `cvalue` varchar(32) NOT NULL DEFAULT '' COMMENT '值',
  `state` varchar(2) NOT NULL DEFAULT '10' COMMENT '状态 10启用 20禁用',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `nid` varchar(16) DEFAULT '' COMMENT '唯一标识',
  `req_ext` varchar(128) DEFAULT '' COMMENT '扩展字段',
  PRIMARY KEY (`id`),
  KEY `factor_id` (`factor_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评分因子参数表';

-- ----------------------------
-- Records of arc_cr_factor_param
-- ----------------------------

-- ----------------------------
-- Table structure for arc_cr_info
-- ----------------------------
DROP TABLE IF EXISTS `arc_cr_info`;
CREATE TABLE `arc_cr_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tb_nid` varchar(32) NOT NULL DEFAULT '' COMMENT '表名',
  `tb_name` varchar(32) NOT NULL DEFAULT '' COMMENT '表名注释',
  `detail` text NOT NULL COMMENT '规则信息',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `state` varchar(8) DEFAULT '10' COMMENT '状态10 启用 20 禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评分卡规则信息表';

-- ----------------------------
-- Records of arc_cr_info
-- ----------------------------

-- ----------------------------
-- Table structure for arc_cr_item
-- ----------------------------
DROP TABLE IF EXISTS `arc_cr_item`;
CREATE TABLE `arc_cr_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `card_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '评分卡id',
  `item_name` varchar(32) NOT NULL DEFAULT '' COMMENT '评分项目名称',
  `score` int(11) NOT NULL DEFAULT '0' COMMENT '项目分值',
  `state` varchar(2) NOT NULL DEFAULT '10' COMMENT '状态 10启用 20禁用',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `req_ext` varchar(128) DEFAULT '' COMMENT '扩展字段',
  `nid` varchar(16) DEFAULT '' COMMENT '唯一标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评分卡项目表';

-- ----------------------------
-- Records of arc_cr_item
-- ----------------------------

-- ----------------------------
-- Table structure for arc_cr_rank
-- ----------------------------
DROP TABLE IF EXISTS `arc_cr_rank`;
CREATE TABLE `arc_cr_rank` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rank_name` varchar(32) NOT NULL DEFAULT '' COMMENT '评分等级名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评分卡等级表';

-- ----------------------------
-- Records of arc_cr_rank
-- ----------------------------

-- ----------------------------
-- Table structure for arc_cr_rank_detail
-- ----------------------------
DROP TABLE IF EXISTS `arc_cr_rank_detail`;
CREATE TABLE `arc_cr_rank_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rank_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '评分等级id',
  `rank` varchar(32) NOT NULL DEFAULT '' COMMENT '评分等级',
  `amount_min` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '起始信用额度',
  `amount_max` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '最高信用额度',
  `score_min` int(11) NOT NULL DEFAULT '0' COMMENT '最低分值',
  `score_max` int(11) NOT NULL DEFAULT '0' COMMENT '最高分值',
  `state` varchar(2) NOT NULL DEFAULT '10' COMMENT '是否可用 10是,20否',
  `rtype` varchar(2) NOT NULL DEFAULT '' COMMENT '额度类别10 区间 20 固定值',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评分卡等级详情表';

-- ----------------------------
-- Records of arc_cr_rank_detail
-- ----------------------------

-- ----------------------------
-- Table structure for arc_cr_result
-- ----------------------------
DROP TABLE IF EXISTS `arc_cr_result`;
CREATE TABLE `arc_cr_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `consumer_no` varchar(30) NOT NULL,
  `credit_type_id` bigint(20) NOT NULL,
  `total_score` int(11) DEFAULT '0' COMMENT '总得分',
  `total_amount` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '总额度',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `consumer_no` (`consumer_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评分结果表';

-- ----------------------------
-- Records of arc_cr_result
-- ----------------------------

-- ----------------------------
-- Table structure for arc_cr_result_detail
-- ----------------------------
DROP TABLE IF EXISTS `arc_cr_result_detail`;
CREATE TABLE `arc_cr_result_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `result_id` bigint(20) NOT NULL COMMENT '评分结果表ID',
  `card_id` bigint(20) NOT NULL COMMENT '评分卡ID',
  `item_id` bigint(20) DEFAULT NULL COMMENT '评分项目ID',
  `factor_id` int(11) DEFAULT NULL,
  `param_id` bigint(20) DEFAULT NULL COMMENT '评分因子参数ID',
  `param_name` varchar(32) DEFAULT NULL COMMENT '参数名称',
  `param_score` int(255) DEFAULT NULL COMMENT '最高分值',
  `formula` varchar(255) DEFAULT NULL COMMENT '表达式',
  `cvalue` varchar(255) DEFAULT NULL COMMENT '参数取值范围',
  `value` varchar(255) DEFAULT NULL COMMENT '参与评分的因子实际值',
  `level` varchar(2) NOT NULL COMMENT '评分分数级别  10 评分卡 20 项目 30 因子',
  `score` int(11) NOT NULL COMMENT '得分',
  `amount` decimal(12,2) DEFAULT '0.00' COMMENT '评分卡额度',
  `card_level` varchar(2) DEFAULT NULL COMMENT '评分卡等级  来源于rank表',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `result_id` (`result_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评分结果明细表';

-- ----------------------------
-- Records of arc_cr_result_detail
-- ----------------------------

-- ----------------------------
-- Table structure for arc_credit
-- ----------------------------
DROP TABLE IF EXISTS `arc_credit`;
CREATE TABLE `arc_credit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `consumer_no` varchar(32) NOT NULL DEFAULT '0' COMMENT '用户标识',
  `total` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '总额度',
  `credit_type` bigint(20) NOT NULL DEFAULT '0' COMMENT '额度类型',
  `grade` varchar(16) NOT NULL DEFAULT '0' COMMENT '评分',
  `used` decimal(10,2) DEFAULT '0.00' COMMENT '已使用额度',
  `unuse` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '可使用额度',
  `state` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态 10 -正常 20-冻结 30-待审批 40-审批不通过',
  `req_ext` varchar(64) DEFAULT '' COMMENT '扩展字段',
  `count` varchar(2) DEFAULT '0' COMMENT '提额次数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `consumer_no` (`consumer_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='授信额度表';

-- ----------------------------
-- Records of arc_credit
-- ----------------------------

-- ----------------------------
-- Table structure for arc_credit_log
-- ----------------------------
DROP TABLE IF EXISTS `arc_credit_log`;
CREATE TABLE `arc_credit_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `consumer_no` varchar(32) NOT NULL DEFAULT '0' COMMENT '授信额度个人唯一标识',
  `pre` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '变动前额度',
  `now` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '变动后额度',
  `modify_total` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '变动额度',
  `modify_time` datetime NOT NULL COMMENT '变动时间',
  `modify_user` varchar(16) NOT NULL DEFAULT '' COMMENT '变动操作人',
  `type` varchar(2) NOT NULL DEFAULT '0' COMMENT '变动类型 10-增加 20-减少 30-冻结 40-解冻 50-审批通过 60-审批不通过',
  `credit_type` bigint(20) DEFAULT '0' COMMENT '额度类型',
  `remark` varchar(128) DEFAULT '' COMMENT '变更内容',
  `req_ext` varchar(64) DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`id`),
  KEY `consumer_no` (`consumer_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='授信额度调整记录';

-- ----------------------------
-- Records of arc_credit_log
-- ----------------------------

-- ----------------------------
-- Table structure for arc_rule_engine
-- ----------------------------
DROP TABLE IF EXISTS `arc_rule_engine`;
CREATE TABLE `arc_rule_engine` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) DEFAULT '' COMMENT '规则名称',
  `state` varchar(2) DEFAULT '10' COMMENT '状态 ：10启用，20禁用',
  `config_count` int(11) DEFAULT '0' COMMENT '规则引擎下的配置数量',
  `req_ext` varchar(32) DEFAULT '' COMMENT '扩展字段',
  `add_ip` varchar(64) DEFAULT '' COMMENT '添加IP',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `integral` int(11) DEFAULT '0' COMMENT '规则积分 备用',
  `type` varchar(2) DEFAULT '10' COMMENT '规则模式  10 评分模式  20 结果模式',
  `type_result_status` varchar(2) DEFAULT NULL COMMENT '是否启用结果评级   10 不启用 20启用 ',
  `sort` int(11) DEFAULT '0' COMMENT '权重',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则引擎';

-- ----------------------------
-- Records of arc_rule_engine
-- ----------------------------

-- ----------------------------
-- Table structure for arc_rule_engine_config
-- ----------------------------
DROP TABLE IF EXISTS `arc_rule_engine_config`;
CREATE TABLE `arc_rule_engine_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rule_engin_id` bigint(20) DEFAULT NULL COMMENT '所属规则引擎id',
  `ctable` varchar(32) DEFAULT '' COMMENT '设置关联表名称',
  `table_comment` varchar(64) DEFAULT NULL COMMENT '表名名称',
  `ccolumn` varchar(32) DEFAULT '' COMMENT '设置关联表列',
  `column_comment` varchar(64) DEFAULT NULL COMMENT '字段名称',
  `formula` varchar(16) DEFAULT '' COMMENT '公式',
  `cvalue` varchar(64) DEFAULT '' COMMENT '值',
  `state` varchar(2) DEFAULT '10' COMMENT '状态：10启用，20禁用  ',
  `req_ext` varchar(32) DEFAULT '' COMMENT '扩展字段',
  `add_ip` varchar(64) DEFAULT '' COMMENT '添加IP',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `type` varchar(16) DEFAULT NULL COMMENT '规则类型 int string',
  `integral` int(11) DEFAULT '0' COMMENT '分值   评分模式下有值',
  `result_type` varchar(2) DEFAULT NULL COMMENT '备用',
  `result` varchar(16) DEFAULT NULL COMMENT '结果    结果模式下有值 10 不通过 20 需人工审核  30通过',
  `sort` int(11) DEFAULT '0' COMMENT '权重',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则引擎配置信息';

-- ----------------------------
-- Records of arc_rule_engine_config
-- ----------------------------

-- ----------------------------
-- Table structure for arc_rule_engine_info
-- ----------------------------
DROP TABLE IF EXISTS `arc_rule_engine_info`;
CREATE TABLE `arc_rule_engine_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rule_engin_id` bigint(20) DEFAULT NULL COMMENT '所属规则引擎id',
  `min_integral` int(11) DEFAULT '0' COMMENT '分值范围最小值',
  `max_integral` int(11) DEFAULT '0' COMMENT '分值范围最大值',
  `result` varchar(16) DEFAULT NULL COMMENT '结果描述   评分结果模式下  10 不通过 20 需人工审核  30通过',
  `req_ext` varchar(2) DEFAULT NULL COMMENT '备份字段',
  `formula` varchar(16) DEFAULT NULL COMMENT '表达式 ',
  `integral` int(11) DEFAULT NULL COMMENT '分值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则评分设置';

-- ----------------------------
-- Records of arc_rule_engine_info
-- ----------------------------

-- ----------------------------
-- Table structure for arc_rule_info
-- ----------------------------
DROP TABLE IF EXISTS `arc_rule_info`;
CREATE TABLE `arc_rule_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tb_nid` varchar(50) NOT NULL COMMENT '表名称',
  `tb_name` varchar(50) NOT NULL COMMENT '列名',
  `detail` text NOT NULL COMMENT '对应的规则信息',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `req_ext` varchar(2) DEFAULT NULL COMMENT '备份字段',
  `state` varchar(2) DEFAULT '10' COMMENT '状态：10启用，20禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则信息表';

-- ----------------------------
-- Records of arc_rule_info
-- ----------------------------

-- ----------------------------
-- Table structure for arc_sys_access_code
-- ----------------------------
DROP TABLE IF EXISTS `arc_sys_access_code`;
CREATE TABLE `arc_sys_access_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sys_user_id` bigint(20) NOT NULL COMMENT 'arc_sys_user表主键',
  `code` varchar(10) NOT NULL DEFAULT '' COMMENT '访问码',
  `state` varchar(2) NOT NULL DEFAULT '' COMMENT '状态：10启用 20禁用',
  `create_time` datetime NOT NULL COMMENT '访问码创建时间',
  `exceed_time` datetime NOT NULL COMMENT '访问码过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of arc_sys_access_code
-- ----------------------------
INSERT INTO `arc_sys_access_code` VALUES ('1', '1', '123456', '10', '2017-03-31 11:10:36', '2017-09-27 11:10:36');
INSERT INTO `arc_sys_access_code` VALUES ('9', '13', '123456', '20', '2017-05-05 13:47:51', '2017-05-05 13:47:51');
INSERT INTO `arc_sys_access_code` VALUES ('10', '4', '123456', '20', '2017-05-06 13:35:14', '2017-12-31 13:35:17');
INSERT INTO `arc_sys_access_code` VALUES ('11', '5', '123456', '10', '2017-05-09 13:50:51', '2017-05-09 15:50:51');
INSERT INTO `arc_sys_access_code` VALUES ('12', '6', '123456', '10', '2017-05-09 13:52:49', '2017-05-10 01:52:49');
INSERT INTO `arc_sys_access_code` VALUES ('13', '2', '654321', '10', '2017-05-10 12:09:20', '2017-05-10 14:09:20');
INSERT INTO `arc_sys_access_code` VALUES ('14', '14', '123456', '10', '2017-05-31 10:20:19', '2027-05-31 10:20:19');
INSERT INTO `arc_sys_access_code` VALUES ('15', '15', '123456', '20', '2017-05-31 10:20:48', '2027-05-31 10:20:48');
INSERT INTO `arc_sys_access_code` VALUES ('16', '16', '123456', '10', '2017-05-31 10:29:29', '2027-05-31 10:29:29');

-- ----------------------------
-- Table structure for arc_sys_config
-- ----------------------------
DROP TABLE IF EXISTS `arc_sys_config`;
CREATE TABLE `arc_sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` tinyint(3) DEFAULT '10' COMMENT '类型',
  `name` varchar(30) DEFAULT '' COMMENT '参数名称',
  `code` varchar(50) DEFAULT '' COMMENT '编号',
  `value` varchar(2048) DEFAULT '' COMMENT '参数对应的值',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态  0不启用  1启用',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注说明',
  `creator` int(11) DEFAULT NULL COMMENT '创建者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8 COMMENT='系统配置表';

-- ----------------------------
-- Records of arc_sys_config
-- ----------------------------
INSERT INTO `arc_sys_config` VALUES ('1', '20', '综合费用', 'fee', '0.098,0.12,0.15', '1', '综合费用集合', '1');
INSERT INTO `arc_sys_config` VALUES ('2', '20', '服务费', 'service_fee', '0.75', '1', '综合费用的75%', '1');
INSERT INTO `arc_sys_config` VALUES ('3', '20', '信息认证费', 'info_auth_fee', '0.20', '1', '综合费用的25%', '1');
INSERT INTO `arc_sys_config` VALUES ('4', '20', '借款利息', 'interest', '0.05', '1', '综合费用的5%', '1');
INSERT INTO `arc_sys_config` VALUES ('5', '20', '最小借款额度', 'min_credit', '500', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('6', '20', '最大借款额度', 'max_credit', '10000', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('7', '20', '最小借款期限', 'min_days', '7', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('8', '20', '最大借款期限', 'max_days', '7', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('9', '20', '平台收款账户信息-收款人', 'repay_collection_info_name', '张三', '1', '用于还款登记', '1');
INSERT INTO `arc_sys_config` VALUES ('10', '20', '平台收款账户信息-收款行', 'repay_collection_info_bank', '工商银行', '1', '用于还款登记', '1');
INSERT INTO `arc_sys_config` VALUES ('11', '20', '平台收款账户信息-银行卡', 'repay_collection_info_bank_card', '6240005658863498743', '1', '用于还款登记', '1');
INSERT INTO `arc_sys_config` VALUES ('12', '20', '平台收款账户信息-支付宝账号', 'repay_collection_info_alipay_account', 'zhifubao123', '1', '用于还款登记', '1');
INSERT INTO `arc_sys_config` VALUES ('13', '20', '平台名称', 'title', '小额宝', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('14', '30', '注册协议', 'protocol_register', '/h5/protocol_register.jsp', '1', '注册协议', '1');
INSERT INTO `arc_sys_config` VALUES ('15', '30', '管理协议', 'protocol_manage', '/', '0', '管理协议', '1');
INSERT INTO `arc_sys_config` VALUES ('16', '30', '借款协议', 'protocol_borrow', '/h5/protocol_borrow.jsp', '1', '现金贷借款协议', '1');
INSERT INTO `arc_sys_config` VALUES ('17', '30', '协议', 'protocol_base', '/', '1', '协议', '1');
INSERT INTO `arc_sys_config` VALUES ('18', '20', '逾期利率', 'penalty_fee', '0.02,0.03,0.04', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('19', '20', '借款天数', 'borrow_day', '7,10,14', '1', '借款天数', '1');
INSERT INTO `arc_sys_config` VALUES ('20', '20', '平台电话', 'phone', '13333333333', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('21', '40', '关于我们', 'h5_about_us', '/h5/aboutUs.jsp', '1', '关于我们的', '1');
INSERT INTO `arc_sys_config` VALUES ('22', '40', '帮助中心', 'h5_help', '/h5/help.jsp', '1', '帮助中心', '1');
INSERT INTO `arc_sys_config` VALUES ('23', '40', '还款帮助', 'h5_repay_help', '/h5/repay_help.jsp', '1', '还款帮助', '1');
INSERT INTO `arc_sys_config` VALUES ('24', '40', '还款方式', 'h5_repay_type', '/h5/repay_type.jsp', '1', '还款方式', '1');
INSERT INTO `arc_sys_config` VALUES ('25', '40', '还款帮助', 'h5_repay_help2', '/h5/repay_help2.jsp', '1', '还款帮助', '1');
INSERT INTO `arc_sys_config` VALUES ('26', '40', '还款方式', 'h5_repay_type2', '/h5/repay_type2.jsp', '1', '还款方式', '1');
INSERT INTO `arc_sys_config` VALUES ('27', '70', '大圣数据APIKEY', 'apikey', '3ee1b56457f23e367fc0e237fa8878fe', '1', '调用大圣数据接口用', '1');
INSERT INTO `arc_sys_config` VALUES ('28', '70', '大圣数据SECRETKEY', 'secretkey', 'aab77b93708662066f2f2902b95afa0e938275f20792990887200deec87f6946', '1', '调用大圣数据接口用', '1');
INSERT INTO `arc_sys_config` VALUES ('29', '70', '运营商认证_商户号', 'operator_channelNo', 'CH0673607634', '1', '上数运营商验证用的商户号', '1');
INSERT INTO `arc_sys_config` VALUES ('31', '70', '运营商认证_接口', 'operator_interfaceName', 'buildAuthCollItemListUrl', '1', '上数运营商验证用的接口名', '1');
INSERT INTO `arc_sys_config` VALUES ('33', '70', '运营商认证_接口地址', 'operator_apihost', 'http://ucdevapi.ucredit.erongyun.net/credit/api/v1.5/query', '1', '上数运营商验证用的域名', '1');
INSERT INTO `arc_sys_config` VALUES ('35', '10', '本机服务器域名', 'server_host', 'http://119.23.19.64:8080', '1', '第三方接口回调用的域名', '1');
INSERT INTO `arc_sys_config` VALUES ('36', '70', '人证识别域名', 'linkfacehost', 'http://ucdevapi.ucredit.erongyun.net/linkface/hsVerification', '1', 'linkface人证识别用的域名', '1');
INSERT INTO `arc_sys_config` VALUES ('37', '40', '邀请页面', 'h5_invite', '/h5/index.jsp', '1', '邀请页面', '1');
INSERT INTO `arc_sys_config` VALUES ('38', '50', '绑卡备注', 'remark_bank_card', '备注\r\n1.借款通过申请后，我们将会将你的所借款项发放到该张银行卡；\r\n2.若申请重新绑卡，则新卡将激活为收款银行卡；\r\n3.未完成借款期间不允许更换银行卡。\r\n', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('39', '50', '邀请-我的奖金备注', 'remark_profit_amount', '系统会在每月10日将上月9日至本月10日的奖金汇款至您绑定的银行卡，请注意查收。', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('40', '20', '一级代理分润率', 'levelOne', '20.00', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('41', '20', '二级代理分润率', 'levelTwo', '10.00', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('42', '20', '普通用户分润率', 'levelThree', '5.00', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('43', '20', '芝麻验证间隔(天)', 'validate_interval', '30', '1', '申请借款之前调用芝麻信用的两个验证的前后两次的时间间隔，单位为天', '1');
INSERT INTO `arc_sys_config` VALUES ('44', '80', '连连支付商户号', 'lianlian_business_no', '201703021001534601', '1', '调用第三方支付接口用', '1');
INSERT INTO `arc_sys_config` VALUES ('47', '80', '连连支付公钥', 'lianlian_public_key', 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCSS/DiwdCf/aZsxxcacDnooGph3d2JOj5GXWi+q3gznZauZjkNP8SKl3J2liP0O6rU/Y/29+IUe+GTMhMOFJuZm1htAtKiu5ekW0GlBMWxf4FPkYlQkPE0FtaoMP3gYfh+OwI+fIRrpW3ySn3mScnc6Z700nU/VYrRkfcSCbSnRwIDAQAB', '1', '调用第三方支付接口用', '1');
INSERT INTO `arc_sys_config` VALUES ('48', '80', '连连支付私钥', 'lianlian_private_key', 'MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAOkwElQujjHasinnqwbLmk48A3T0oGFBCWDE7voEfWn+v6MbuLLorsLgwtCHzk9pQon5+q+vPB+LQ1DLWoKFUIBKV2bUrH9xUSyCHkmXlCwhcStAO6pvqw9cljGlnRSDqwJR5p5ZjsHWSHY5zsH9kgswabxwepTXWSDMQ2ZfsED9AgMBAAECgYA5IQXcietcW2/6xD8qUwdi02Elbik8qmpZwuWpayAur7LTix8c9dSa+eXAoQTyf59FnnDESfI46C19xdRO1cp6q/34sgAMvPUyZrXZF42gxZQTbDpuQuOpZYsYtUpqgIsGRZ20fDzL6MRmzyX6a8CoimvpwDZRuM0xVs6gxFGbIQJBAPk/QFCTGJXab92tOiwQKpLLNcnf6NG02J0dH+VZmb6hTcUsIhuWPsQkEpz18Ze/x6uqyJx7a18kWPCHMP1TSNMCQQDvgW9ZfAVTlqKvyjkr4wc++kF6ur54jtO/RhNnLm/AvZMe12gOwWgRf9oNp873kONLRppY6AtA3X/7XnalCSzvAkEAuU0H8J9bUNVkA2ds7yNZs2a2Z4461eVggXYMp4M0pR/g5KzJ8r6BjWrQF7dVVJ80v/CpUoUDL2mjYbbwefV4FwJAGng4oTAmuo08X6c+2E3YACrST8kRwesvFcWd4VpteWS6PL9bvb28TarOo5G3Ov8lT61243qJu9p/CX3wKN8xuwJAC4jE+AmpjtDIVmfU5S6MM+HTtkOP7GqUEvTALY3uuW4p0qJDe72ScD+JWz1KC3CoA6wAaNCfwRf1fusgP1Vwkw==', '1', '调用第三方支付接口用', '1');
INSERT INTO `arc_sys_config` VALUES ('51', '80', '芝麻应用标识', 'zhima_app_id', '1004064', '1', '反欺诈和行业关注名单用', '1');
INSERT INTO `arc_sys_config` VALUES ('52', '80', '芝麻公钥', 'zhima_public_key', 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCo2law2ieNYmoIEmYsU9fNdPOqpLL2sLn7kRnPUJUKSI5ekkNtiUxUez8BRQub2lzCxbDHGZci62CehSiUNbKUKHvKIDe+mvpUIikMIZ3LoTNdtXctbEaugVg/m/4woNn1PZgWBcp7NOg05WCRFEpCa6vuNSvWo62/LkMDxEkTQQIDAQAB', '1', '反欺诈和行业关注名单用', '1');
INSERT INTO `arc_sys_config` VALUES ('53', '80', '商家私钥', 'zhima_private_key', 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCo2law2ieNYmoIEmYsU9fNdPOqpLL2sLn7kRnPUJUKSI5ekkNtiUxUez8BRQub2lzCxbDHGZci62CehSiUNbKUKHvKIDe+mvpUIikMIZ3LoTNdtXctbEaugVg/m/4woNn1PZgWBcp7NOg05WCRFEpCa6vuNSvWo62/LkMDxEkTQQIDAQAB', '1', '反欺诈和行业关注名单用，其实是商家私钥，code值主要是为了不冲突。。', '1');
INSERT INTO `arc_sys_config` VALUES ('54', '10', '应用环境（非技术人员勿动）', 'app_environment', 'dev', '1', 'dev/prod', '1');
INSERT INTO `arc_sys_config` VALUES ('55', '20', '单用户每次借款最大次数', 'borrow_most_times', '1', '1', '单用户每次借款最大次数，未完成的借款', '1');
INSERT INTO `arc_sys_config` VALUES ('56', '60', '发送短信APIKEY', 'sms_apikey', 'bfde3396-3956-4fab-bc73-9b015026', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('57', '60', '送送短信SECRETKEY', 'sms_secretkey', 'cab3f18c37712ec6cef1dce6e3ca79bca7b605e3ca42f889ba12ca0ab94a6c1c', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('58', '60', '发送短信APIHOST', 'sms_apihost', 'https://api.dsdatas.com/sms/api/getSmsParameSend', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('59', '60', '发送短信渠道编号', 'sms_channelNo', 'CH1945487800', '0', null, '1');
INSERT INTO `arc_sys_config` VALUES ('60', '60', '发送短信接口名称', 'sms_interfaceName', 'movekSimpleInfo', '0', null, '1');
INSERT INTO `arc_sys_config` VALUES ('61', '60', '短信验证码过期时间', 'sms_time_limit', '5', '1', '单位：分钟', '1');
INSERT INTO `arc_sys_config` VALUES ('62', '20', '注册时给予额度', 'init_credit', '1000', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('64', '60', '短信获取倒计时', 'sms_countdown', '60', '1', '倒计时60秒', '1');
INSERT INTO `arc_sys_config` VALUES ('65', '10', '移动端密钥', 'app_key', 'oQIhAP24Kb3Bsf7IE14wpl751bQc9VAPsFZ+lql4riBgg2TDAiEAsSomOO1v8mK2VWhEQh6mttgN', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('66', '60', '单日每类短信验证码允许获取的最大次数', 'sms_day_most_times', '{\"verifyTime\": 10,\"register\": 5,\"findReg\":5,\"findPay\": 5,\"bindCard\": 5}', '1', '单日每类短信验证码允许获取的最大次数', '1');
INSERT INTO `arc_sys_config` VALUES ('67', '60', '短信批量接口APIHOST', 'sms_apihost_batch', 'https://api.dsdatas.com/sms/api/getSmsParameSend', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('68', '60', '短信批量接口名称', 'sms_interfaceName_batch', 'movekSmsSend', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('69', '20', '手机号码号段', 'phone_number_segment', '134,135,136,137,138,139,147,150,151,152,157,158,159,178,182,183,184,187,188,130,131,132,145,155,156,171,175,176,185,186,133,149,153,173,177,180,181,189,170', '1', '用于校验手机号码格式', '1');
INSERT INTO `arc_sys_config` VALUES ('70', '20', '客户热线', 'customer_hotline', '400-000-0000', '1', '客户服务200热线', '1');
INSERT INTO `arc_sys_config` VALUES ('77', '20', '人证识别比对合格率', 'idCard_credit_pass_rate', '0.8', '1', '用于个人信息中的人证识别', '1');
INSERT INTO `arc_sys_config` VALUES ('78', '20', '借款失败后再次借款间隔', 'again_borrow', '0', '1', '', '1');
INSERT INTO `arc_sys_config` VALUES ('86', '50', '邀请用户备注', 'remark_invite', '1分钟认证，20分钟到账，无抵押，纯信用贷。时下最流行的移动贷款APP。国内首批利用大数据、人工智能实现风控审批的信贷服务平台。\r\n', '1', '邀请用户内容', '1');
INSERT INTO `arc_sys_config` VALUES ('87', '20', '单日每人证识别最大次数', 'idCardCredit_day_most_times', '10', '1', '单日每人提交人脸识别最大次数', '1');
INSERT INTO `arc_sys_config` VALUES ('88', '20', '单日每人运营商认证最大次数', 'operatorCredit_day_most_times', '10', '1', '单日每人进行运营商认证的最大次数', '1');
INSERT INTO `arc_sys_config` VALUES ('89', '70', 'OCR使用记录同步测试地址', 'linkfacehost_test', 'http://ucdevapi.ucredit.erongyun.net/linkface/ocr', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('90', '70', 'OCR使用记录同步正式地址', 'linkfacehost_real', 'http://ucdevapi.ucredit.erongyun.net/linkface/ocr', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('91', '20', '是否启用砍头息', 'behead_fee', '20', '1', '10 - 启用 20 - 关闭', '1');
INSERT INTO `arc_sys_config` VALUES ('92', '20', 'sdk识别每日可调用次数', 'sdk_time', '{\"faceTime\":\"3\",\"ocrTime\":\"6\"}', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('93', '70', '天行学信查询地址', 'tx_apihost', 'http://ucdevapi.ucredit.erongyun.net/credit/api/mock/query', '1', '三方收费地址:http://ucdevapi.ucredit.erongyun.net/credit/api/v1/query', '1');
INSERT INTO `arc_sys_config` VALUES ('94', '70', '天行学信查询渠道号', 'tx_channelNo', 'CH0844282371', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('95', '70', '天行学信查询接口名', 'tx_interfaceName', 'txEducationQuery', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('100', '80', '支付是否开启', 'lianlian_switch', '1', '1', '1开2关', '1');
INSERT INTO `arc_sys_config` VALUES ('114', '20', '借款额度', 'borrow_credit', '100,200,300,400,500,600,700,800,900,1000', '1', '借款额度', '1');
INSERT INTO `arc_sys_config` VALUES ('115', '80', '同盾是否请求测试环境', 'tongdun_url_state', '0', '1', '同盾是否请求测试环境   1正式环境  0测试环境', '1');
INSERT INTO `arc_sys_config` VALUES ('116', '20', '借款必填项总数', 'auth_total', '7', '1', ' ', '1');
INSERT INTO `arc_sys_config` VALUES ('117', '20', '芝麻信用配置', 'zhima_auth', '30', '1', '10-去除 20-选填 30-必填', '1');
INSERT INTO `arc_sys_config` VALUES ('118', '40', '邀请规则页面', 'h5_invite_rule', '/h5/invite_rule.jsp', '1', '邀请规则页面', '1');
INSERT INTO `arc_sys_config` VALUES ('119', '20', '当日最大注册用户数', 'day_register_max', '0', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('120', '70', 'orc请求地址获取', 'ocr_host', 'http://ucdevapi.ucredit.erongyun.net/faceID/ocrHost', '1', '测试地址', '1');
INSERT INTO `arc_sys_config` VALUES ('121', '70', 'face++人证对比地址', 'verify_host', 'http://ucdevapi.ucredit.erongyun.net/linkface/hsVerification', '1', '测试地址', '1');
INSERT INTO `arc_sys_config` VALUES ('122', '10', '版本控制', 'check_version', '1.0.1', '0', 'state是否启用10启用20禁用  version版本号', '1');
INSERT INTO `arc_sys_config` VALUES ('123', '30', 'app_register_demo', 'app_register_demo', '0', '1', '0 不添加  1 添加   添加用户部分认证状态和银行卡信息', null);
INSERT INTO `arc_sys_config` VALUES ('124', '20', '是否启用访问码', 'access_code_able', '20', '1', '10开20关', '1');
INSERT INTO `arc_sys_config` VALUES ('133', '70', '人证识别接口类别', 'verify_type', '30', '1', '10-face++,20-小视,30-商汤', '1');
INSERT INTO `arc_sys_config` VALUES ('134', '60', '短信通道', 'sms_passageway', '10', '1', '10大圣  20创蓝', '1');
INSERT INTO `arc_sys_config` VALUES ('135', '60', '创蓝短信需要的变量', 'cl_sms_value', '{\"url\":\"http://sms.253.com/msg/send\",\"un\":\"N2116381\",\"pw\":\"BvpRkn869\",\"rd\":\"1\",\"ex\":\"\"}', '1', '创蓝短信需要的变量', '1');
INSERT INTO `arc_sys_config` VALUES ('136', '70', '运营商认证类别', 'operator_type', '10', '1', '10 上树运营商,20 上树运营商2,30 同盾', '1');
INSERT INTO `arc_sys_config` VALUES ('137', '80', '同盾运营商认证授权地址', 'operator_identify_url', '', '1', '同盾运营商认证授权地址', '1');
INSERT INTO `arc_sys_config` VALUES ('138', '80', '同盾商户秘钥', 'tongdun_operator_partner_key', '', '1', '同盾商户秘钥', '1');
INSERT INTO `arc_sys_config` VALUES ('139', '80', '同盾商户标识', 'tongdun_operator_partner_code', 'hzsy', '1', '同盾商户标识', '1');
INSERT INTO `arc_sys_config` VALUES ('140', '80', '同盾运营商信息查询接口', 'tongdun_operator_query_url', 'https://api.shujumohe.com/octopus/task.unify.query/v3', '1', '同盾运营商信息查询接口', '1');
INSERT INTO `arc_sys_config` VALUES ('142', '20', '当日放款上限', 'loan_ceiling', '10000', '1', '当日放款上限', '1');
INSERT INTO `arc_sys_config` VALUES ('143', '20', 'APP引流地址', 'divert_url', 'http://h5.qa.51xjzx.com/popWin', '1', null, null);
INSERT INTO `arc_sys_config` VALUES ('144', '20', '规则引擎配置表', 'rule_tables', 'cl_user_base_info,cl_zhima,cl_user_equipment_info,cl_rc_phone_call_count,cl_rc_ds_cheat_info,cl_rc_ds_bad_info,cl_rc_contact_count,cl_rc_borrow_count,cl_qiancheng_req_log,cl_operator_req_log,cl_tongdun_req_log,cl_rc_huadao_blacklist_log,cl_operator_td_basic', '1', null, null);
INSERT INTO `arc_sys_config` VALUES ('146', '20', '逾期罚息上限', 'penalty_amout_max', '0.5', '1', '超过不再计算罚息', '1');
INSERT INTO `arc_sys_config` VALUES ('147', '80', '代扣最大次数', 'do_repayment_max', '9', '1', '单笔还款计划代扣最大次数', '1');
INSERT INTO `arc_sys_config` VALUES ('148', '20', '是否代扣今天的待还', 'do_repayment_today', '10', '1', '是否代扣今天的待还', '1');
INSERT INTO `arc_sys_config` VALUES ('149', '20', '商汤2.0OCR地址识别地址', 'linkface_idOcr', 'http://ucdevapi.ucredit.erongyun.net/linkface/idOcr', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('150', '20', '商汤2.0人证对比地址', 'linkface_liVerification', 'http://ucdevapi.ucredit.erongyun.net/linkface/liVerification', '1', null, '1');
INSERT INTO `arc_sys_config` VALUES ('151', '70', '四要素认证接口', 'four_elements_interfaceName', 'txbankCardFourCheckFourQuery', '1', '四要素认证接口', '1');
INSERT INTO `arc_sys_config` VALUES ('152', '60', '获取短信报告结果', 'sms_apihost_report', 'http://api.dsdatas.com/smsSend/getReportByOrderNo', '1', '获取短信报告结果', '1');
-- ----------------------------
-- Table structure for arc_sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `arc_sys_dict`;
CREATE TABLE `arc_sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_code` varchar(64) DEFAULT NULL COMMENT '类型编码',
  `type_name` varchar(32) DEFAULT NULL COMMENT '类型名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(30) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `type_code` (`type_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='数据字典表';

-- ----------------------------
-- Records of arc_sys_dict
-- ----------------------------
INSERT INTO `arc_sys_dict` VALUES ('2', 'PRODUCT_TYPE', '借款类型', '20', '借款类型');
INSERT INTO `arc_sys_dict` VALUES ('6', 'RANK_TYPE', '授信等级', '10', '授信等级');
INSERT INTO `arc_sys_dict` VALUES ('7', 'CREDIT_TYPE', '额度类型', '30', '额度类型');
INSERT INTO `arc_sys_dict` VALUES ('8', 'CONTACT_RELATION', '联系人关系类型', '40', '联系人关系类型');
INSERT INTO `arc_sys_dict` VALUES ('9', 'EDUCATIONAL_STATE', '教育程度', '36', '教育程度');
INSERT INTO `arc_sys_dict` VALUES ('10', 'MARITAL_STATE', '婚姻状况', '28', '婚姻状况');
INSERT INTO `arc_sys_dict` VALUES ('11', 'LIVE_TIME', '居住时长', '27', '居住时长');
INSERT INTO `arc_sys_dict` VALUES ('12', 'WORK_TIME', '工作时长', '26', '工作时长');
INSERT INTO `arc_sys_dict` VALUES ('13', 'SALARY_RANGE', '月薪范围', '25', '月薪范围');
INSERT INTO `arc_sys_dict` VALUES ('14', 'BANK_TYPE', '银行代码', '24', '银行代码');
INSERT INTO `arc_sys_dict` VALUES ('15', 'SYSTEM_TYPE', '系统参数类别', '41', '系统参数类别');
INSERT INTO `arc_sys_dict` VALUES ('16', 'URGE_WAY', '催收方式', '42', '催收方式');
INSERT INTO `arc_sys_dict` VALUES ('18', 'URGE_STATE', '催收状态', '43', '催收状态');
INSERT INTO `arc_sys_dict` VALUES ('19', 'ACCESS_CODE', '访问码时限', '44', '访问码时限');
INSERT INTO `arc_sys_dict` VALUES ('20', 'THIRD_DATA_SCENE', '借款场景', '45', '借款场景');
INSERT INTO `arc_sys_dict` VALUES ('21', 'KINSFOLK_RELATION', '直系联系人', '46', '直系联系人');

-- ----------------------------
-- Table structure for arc_sys_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `arc_sys_dict_detail`;
CREATE TABLE `arc_sys_dict_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `item_code` varchar(64) DEFAULT NULL COMMENT '参数编码',
  `item_value` varchar(32) DEFAULT NULL COMMENT '参数值',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级ID',
  `state` varchar(2) NOT NULL DEFAULT '10' COMMENT '状态  10 有效 20 无效',
  PRIMARY KEY (`id`),
  KEY `item_code` (`item_code`) USING BTREE,
  KEY `parent_id_index` (`parent_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8 COMMENT='字典表数据明细';

-- ----------------------------
-- Records of arc_sys_dict_detail
-- ----------------------------
INSERT INTO `arc_sys_dict_detail` VALUES ('1', '01', '现金贷', '2', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('2', '01', 'A', '6', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('3', '02', 'B', '6', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('4', '03', 'C', '6', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('5', '01', '信贷额度', '7', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('10', '01', '配偶', '21', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('11', '02', '子女', '21', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('12', '03', '父亲', '21', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('13', '04', '母亲', '21', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('14', '05', '同事', '8', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('15', '01', '初中及小学以下', '9', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('16', '02', '中专/职高/高中', '9', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('17', '03', '高职/大专', '9', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('18', '04', '本科', '9', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('19', '05', '硕士', '9', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('20', '06', '博士', '9', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('21', '07', '博士后', '9', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('22', '01', '未婚', '10', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('23', '02', '已婚', '10', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('24', '03', '离异', '10', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('25', '04', '丧偶', '10', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('26', '01', '半年以内', '11', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('27', '02', '半年到一年', '11', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('28', '03', '一年以上', '11', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('29', '01', '一年以下', '12', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('30', '02', '一年至三年', '12', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('31', '03', '三年至五年', '12', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('32', '04', '五年以上', '12', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('33', '01', '5千以下', '13', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('34', '02', '5千至1万', '13', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('35', '03', '1万至2万', '13', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('36', '04', '2万以上', '13', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('37', '01', '中国工商银行', '14', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('38', '02', '中国农业银行', '14', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('39', '03', '中国银行', '14', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('40', '04', '中国建设银行', '14', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('41', '05', '交通银行', '14', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('42', '06', '中信银行', '14', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('43', '07', '光大银行', '14', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('44', '08', '华夏银行', '14', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('45', '09', '民生银行', '14', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('46', '10', '广发银行', '14', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('47', '11', '平安银行', '14', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('48', '12', '招商银行', '14', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('49', '30', '协议参数', '15', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('50', '20', '业务参数', '15', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('51', '10', '系统关键参数', '15', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('52', '06', '朋友', '8', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('53', '40', 'H5参数', '15', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('54', '70', '大圣', '15', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('55', '80', '第三方', '15', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('56', '50', '备注参数', '15', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('57', '60', '短信参数', '15', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('58', '10', '电话', '16', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('59', '20', '邮件', '16', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('60', '30', '短信', '16', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('61', '40', '现场沟通', '16', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('62', '50', '其他', '16', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('66', '20', '催收中', '18', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('67', '30', '承诺还款', '18', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('68', '40', '催收成功', '18', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('69', '50', '坏账', '18', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('70', '01', '2小时', '19', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('71', '02', '12小时', '19', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('72', '03', '一天', '19', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('73', '04', '二天', '19', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('74', '05', '七天', '19', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('75', '06', '一个月', '19', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('76', '07', '三个月', '19', '10');
INSERT INTO `arc_sys_dict_detail` VALUES ('77', '08', '六个月', '19', '10');

-- ----------------------------
-- Table structure for arc_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `arc_sys_menu`;
CREATE TABLE `arc_sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sys_type` tinyint(3) DEFAULT '0' COMMENT '类型  0公用',
  `name` varchar(128) DEFAULT '' COMMENT '菜单名称',
  `parent_id` int(11) unsigned DEFAULT NULL COMMENT '父级ID',
  `href` varchar(512) DEFAULT '' COMMENT '链接地址',
  `icon_cls` varchar(512) DEFAULT '' COMMENT '图标',
  `sort` int(11) unsigned zerofill DEFAULT '00000000000' COMMENT '排序',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user` varchar(128) DEFAULT '' COMMENT '添加者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(128) DEFAULT '' COMMENT '修改者',
  `remark` varchar(256) DEFAULT '' COMMENT '备注',
  `is_delete` tinyint(2) DEFAULT NULL COMMENT '是否删除 ：0不删除，1删除',
  `is_menu` tinyint(2) DEFAULT '1' COMMENT '是否菜单 0否，1是',
  `scriptid` varchar(255) DEFAULT NULL COMMENT '脚本名称',
  `controller_name` varchar(255) DEFAULT NULL COMMENT '控制器名字',
  `leaf` tinyint(1) DEFAULT NULL COMMENT '是否为子节点  1 true 0 false',
  `level` tinyint(2) unsigned zerofill DEFAULT NULL COMMENT '树状层数据',
  `scriptid_old` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11294 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of arc_sys_menu
-- ----------------------------
INSERT INTO `arc_sys_menu` VALUES ('1', '0', '系统管理', '0', null, 'icon-xitongguanli', '00000000099', '2016-05-31 00:00:00', null, '2016-12-15 17:11:18', null, '1', '0', '1', 'sysManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('2', '0', '菜单管理', '1', null, 'icon-caidanguanli', '00000000030', null, null, '2016-08-02 09:36:12', null, '1', '0', '1', 'sysMenuManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('3', '0', '用户管理', '1', null, 'icon-yonghuguanli', '00000000010', null, null, '2016-08-02 09:36:27', null, '1', '0', '1', 'sysUserManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('4', '0', '角色管理', '1', null, 'icon-jiaoseguanli', '00000000020', null, null, '2016-08-02 09:36:53', null, '1', '0', '1', 'sysRoleManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('7', '0', '字典管理', '1', null, 'icon-zidianguanli', '00000000040', null, null, '2016-08-02 09:44:48', null, '1', '0', '1', 'sysDicManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11050', '0', '系统参数设置', '1', '', null, '00000000100', null, '', null, '', null, '0', '1', 'SystemParameterSettings', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11140', '0', '用户信息', '11161', '', null, '00000000010', null, '', '2017-03-07 19:59:12', '', '用户基本信息', '0', '1', 'UserBasicInformation', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11141', '0', '规则配置', '11153', '', 'icon', '00000000020', null, '', '2016-12-26 14:45:24', '', 'ds', '0', '1', 'ruleEngine', null, '1', '02', null);
INSERT INTO `arc_sys_menu` VALUES ('11145', '0', '授信额度审批', '11158', '', null, '00000000040', null, '', '2017-07-17 09:05:19', '', '授信额度调整', '0', '1', 'AdjustCreditLine', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11146', '0', '规则类型绑定', '11153', '', null, '00000000030', null, '', '2017-04-25 15:43:06', '', '规则类型绑定', '0', '1', 'BorrowingRulesManagement', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11149', '0', '贷后管理', '0', '', null, '00000000052', null, '', '2017-03-04 16:12:53', '', null, '1', '1', 'LoanBackManege', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11150', '0', '评分卡', '11158', '', null, '00000000020', null, '', '2017-03-07 20:30:08', '', '', '0', '1', 'AssessScoreCard', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11151', '0', '表字段维护', '11153', '', null, '00000000010', null, '', '2017-01-11 14:48:42', '', null, '0', '1', 'FormFieldsToAdd', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11153', '0', '规则引擎', '0', '', 'icon-guizeshuoming', '00000000020', null, '', '2017-03-08 13:40:39', '', null, '0', '1', null, null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11154', '0', '规则匹配记录', '11153', '', null, '00000000040', null, '', '2017-04-25 17:35:13', '', null, '1', '1', 'RulesMatchResults', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11156', '0', '评分等级', '11158', '', null, '00000000030', null, '', '2017-03-07 20:30:14', '', null, '0', '1', 'ScoreRank', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11157', '0', '发布借款', '11159', '', null, '00000000002', null, '', '2017-03-21 09:30:59', '', null, '1', '1', 'TargetRelease', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11158', '0', '信用评级', '0', '', 'icon-tongji', '00000000013', null, '', '2017-08-01 16:18:11', '', '信用评级', '0', '0', 'creditRating', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11159', '0', '借款管理', '0', '', 'icon-jiekuanjilu', '00000000040', null, '', '2017-03-08 13:43:08', '', '借款管理', '0', '1', '', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11160', '0', '表字段维护', '11158', '', null, '00000000005', null, '', '2017-03-07 20:31:48', '', null, '0', '1', 'TableFieldMaintenance', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11161', '0', '用户管理', '0', '', 'icon-yonghuxinxi', '00000000010', null, '', '2017-03-08 13:40:46', '', '用户信息', '0', '1', 'UserInformation', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11162', '0', '黑名单', '11161', '', null, '00000000020', null, '', '2017-03-23 14:33:00', '', '黑名单客户', '1', '1', 'BlackCustomerList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11163', '0', '额度类型', '11158', '', null, '00000000010', null, '', '2017-03-07 20:29:54', '', null, '0', '1', 'LineTypeManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11164', '0', '还款管理', '11149', '', '', '00000000010', null, '', '2017-03-04 14:24:10', '', null, '1', '1', 'PaymentManagt', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11165', '0', '催收管理', '11149', '', 'icon-cuishouguanli', '00000000020', null, '', '2017-03-04 11:37:01', '', null, '1', '1', 'CollectionManagt', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11168', '0', '用户认证信息', '11161', '', null, '00000000020', null, '', '2017-03-07 19:59:01', '', '用户认证信息', '0', '1', 'UserAuthenticationList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11171', '0', '还款计划', '11149', '', null, '00000000010', null, '', '2017-03-04 16:12:48', '', '还款计划', '1', '1', 'RepaymentPlanList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11172', '0', '还款记录', '11149', '', null, '00000000020', null, '', '2017-03-04 16:12:43', '', '还款记录', '1', '1', 'PaymentHistory', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11173', '0', '代理商管理', '1', '', null, '00000000060', null, '', '2017-03-03 13:59:24', '', '代理商管理', '1', '1', 'AgentManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11174', '0', '代理商管理', '0', '', 'icon-dailishang', '00000000011', null, '', '2017-03-08 13:44:18', '', '代理商管理-管理员', '0', '1', 'AgentManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11175', '0', '代理商管理', '11174', '', null, '00000000010', null, '', null, '', '代理商管理', '1', '1', 'AgentModuleAdmin', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11176', '0', '奖励获得记录', '11174', '', null, '00000000040', null, '', '2017-03-24 11:38:52', '', '奖励获得记录', '0', '1', 'ShareDetail', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11177', '0', '奖励资金账户', '11174', '', null, '00000000030', null, '', '2017-03-24 11:37:13', '', '奖励资金账户', '0', '1', 'CashCheck', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11178', '0', '奖励打款记录', '11174', '', null, '00000000050', null, '', '2017-03-24 11:38:43', '', '奖励打款记录', '0', '1', 'CashRecord', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11179', '0', '代理商清单', '11174', '', null, '00000000070', null, '', '2017-03-03 14:22:24', '', '代理商管理-代理商', '1', '1', 'AgentBranchManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11180', '0', '一级代理商管理', '0', '', 'icon-dailishang', '00000000012', null, '', '2017-03-08 13:44:32', '', '代理商管理-代理商', '0', '1', 'AgentBranchManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11181', '0', '代理商管理', '11180', '', null, '00000000010', null, '', null, '', '代理商管理', '0', '1', 'AgentModuleAdminBranch', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11182', '0', '奖励获得记录', '11180', '', null, '00000000030', null, '', '2017-04-05 19:18:19', '', '奖励获得记录', '0', '1', 'ShareDetailBranch', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11183', '0', '奖励资金账户', '11180', '', null, '00000000020', null, '', '2017-04-05 19:18:13', '', '奖励资金账户', '0', '1', 'CashCheckBranch', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11184', '0', '奖励打款记录', '11180', '', null, '00000000040', null, '', '2017-04-05 19:08:37', '', '奖励打款记录', '0', '1', 'CashRecordBranch', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11185', '0', '催收管理', '0', '', null, '00000000060', null, '', '2017-03-04 16:38:20', '', '催收管理', '1', '1', 'CollectionManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11186', '0', '催收人员管理', '11185', '', null, '00000000010', null, '', '2017-03-04 16:38:55', '', '催收人员管理', '1', '1', 'CollectionPersonnelManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11187', '0', '催收反馈意见', '11185', '', null, '00000000020', null, '', '2017-03-04 16:39:00', '', '催收反馈意见', '1', '1', 'CollectionFeedback', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11188', '0', '催收总订单列表', '11185', '', null, '00000000030', null, '', '2017-03-04 16:39:05', '', '催收总订单列表', '1', '1', 'CollectionTotalOrderList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11189', '0', '待催收记录列表', '11185', '', null, '00000000040', null, '', '2017-03-04 16:39:10', '', '待催收记录列表', '1', '1', 'CollectionRecordList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11190', '0', '我的订单', '11185', '', null, '00000000050', null, '', '2017-03-04 16:39:14', '', '我的订单', '1', '1', 'MyOrder', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11191', '0', '待催收列表', '11185', '', null, '00000000060', null, '', '2017-03-04 16:39:19', '', '待催收列表', '1', '1', 'WaitCollectionList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11192', '0', '催收中列表', '11185', '', null, '00000000070', null, '', '2017-03-04 16:39:24', '', '催收中列表', '1', '1', 'CollectionInList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11193', '0', '承诺还款列表', '11185', '', null, '00000000080', null, '', '2017-03-04 16:39:28', '', '承诺还款列表', '1', '1', 'CommitmentRepaymentList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11194', '0', '催收成功列表', '11185', '', null, '00000000090', null, '', '2017-03-04 16:39:33', '', '催收成功列表', '1', '1', 'CollectionSuccessList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11195', '0', '风控管理', '0', '', 'icon-zhuanyefengkong', '00000000021', null, '', '2017-03-08 13:42:47', '', '风控管理', '0', '1', 'RiskControlManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11196', '0', '待机审订单', '11195', '', null, '00000000010', null, '', '2017-03-04 15:49:22', '', '待机审订单列表', '0', '1', 'StandbyReviewList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11197', '0', '机审拒绝订单', '11195', '', null, '00000000020', null, '', null, '', '机审拒绝订单列表', '0', '1', 'RejectedOrderList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11198', '0', '人工复审', '11195', '', null, '00000000030', null, '', null, '', '人工复审', '0', '1', 'LoanApplicationManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11199', '0', '还款管理', '0', '', 'icon-qian', '00000000014', null, '', '2017-08-01 16:18:52', '', '还款管理', '0', '1', 'RepaymentManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11200', '0', '支付宝还款记录', '11199', '', null, '00000000050', null, '', '2017-03-22 11:56:40', '', '支付宝还款记录列表', '0', '1', 'AlipayPaymentList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11201', '0', '银行卡还款记录', '11199', '', null, '00000000060', null, '', '2017-03-22 11:56:49', '', '银行卡还款记录列表', '0', '1', 'BankCardPaymentList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11202', '0', '还款计划', '11199', '', null, '00000000010', null, '', '2017-03-21 11:54:46', '', '还款计划列表', '0', '1', 'RepaymentPlanList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11203', '0', '还款记录', '11199', '', null, '00000000020', null, '', '2017-03-22 16:05:46', '', '还款记录', '0', '1', 'PaymentHistory', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11204', '0', '催收人员管理', '0', '', 'icon-renyuan', '00000000060', null, '', '2017-03-08 13:47:30', '', '催收人员管理', '0', '1', 'CollectionPersonnelManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11205', '0', '催收员', '11204', '', null, '00000000010', null, '', '2017-03-21 15:23:42', '', '催收员列表', '0', '1', 'CollectionPersonnelList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11206', '0', '催收反馈', '11207', '', null, '00000000030', null, '', '2017-03-22 18:02:19', '', '催收反馈', '0', '1', 'CollectionFeedback', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11207', '0', '催收订单管理', '0', '', 'icon-dingdan', '00000000061', null, '', '2017-03-13 19:19:52', '', '催收订单管理', '0', '1', 'CollectionOrderListManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11208', '0', '催收总订单', '11207', '', null, '00000000010', null, '', null, '', '催收总订单列表', '0', '1', 'CollectionTotalOrderList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11209', '0', '待催收订单', '11207', '', null, '00000000020', null, '', null, '', '待催收记录列表', '0', '1', 'CollectionRecordList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11210', '0', '我的催收订单', '0', '', 'icon-wodedingdan', '00000000062', null, '', '2017-03-08 13:47:09', '', '我的催收订单管理', '0', '1', 'MyCollectionOrderManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11211', '0', '我的订单', '11210', '', null, '00000000010', null, '', null, '', '我的订单', '0', '1', 'MyOrder', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11212', '0', '待催收订单', '11210', '', null, '00000000020', null, '', null, '', '待催收列表', '0', '1', 'WaitCollectionList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11213', '0', '催收中订单', '11210', '', null, '00000000030', null, '', null, '', '催收中列表', '0', '1', 'CollectionInList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11214', '0', '承诺还款订单', '11210', '', null, '00000000040', null, '', null, '', '承诺还款列表', '0', '1', 'CommitmentRepaymentList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11215', '0', '催收成功订单', '11210', '', null, '00000000050', null, '', null, '', '催收成功列表', '0', '1', 'CollectionSuccessList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11216', '0', '放款订单', '11159', '', null, '00000000060', null, '', null, '', '放款列表', '0', '1', 'LoanList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11217', '0', '已逾期订单', '11232', '', null, '00000000072', null, '', '2017-03-21 11:57:26', '', '已逾期列表', '0', '1', 'OverdueList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11218', '0', '催收预警', '0', '', 'icon-yujingxiaoxi', '00000000063', null, '', '2017-03-08 13:47:52', '', '催收预警', '0', '1', null, null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11219', '0', '已逾期未入催', '11218', '', null, '00000000010', null, '', null, '', '已逾期未入催', '0', '1', 'OverdueNoAdmission', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11220', '0', '代理商列表', '11174', '', null, '00000000005', null, '', '2017-04-25 17:21:00', '', '代理商列表', '0', '1', 'AgentList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11221', '0', '用户代理等级', '11174', '', null, '00000000004', null, '', '2017-04-25 17:21:18', '', '用户代理等级', '0', '1', 'OrdinaryUserList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11222', '0', '未还款已出催', '11218', '', null, '00000000020', null, '', null, '', '未还款已出催', '0', '1', 'NoRepaymentPutForward', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11223', '0', '未分配催收员', '11218', '', null, '00000000030', null, '', null, '', '未分配催收员', '0', '1', 'UnallocatedCollection', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11224', '0', '催收统计', '0', '', 'icon-tongji', '00000000064', null, '', '2017-03-08 13:47:41', '', '催收统计管理', '0', '1', null, null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11225', '0', '催回率统计', '11224', '', null, '00000000010', null, '', null, '', '催回率统计', '0', '1', 'RecoveryRateStatistics', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11226', '0', '催收订单统计', '11224', '', null, '00000000020', null, '', null, '', '催收订单统计', '0', '1', 'CollectionOrderStatistics', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11227', '0', '催收员每日统计', '11224', '', null, '00000000030', null, '', null, '', '催收员每日统计', '0', '1', 'CollectionDailyStatistics', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11228', '0', '借款进度', '11159', '', null, '00000000055', null, '', '2017-03-07 15:02:29', '', '借款进度', '0', '1', 'LoanSchedule', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11232', '0', '贷后管理', '0', '', 'icon-qian', '00000000052', null, '', '2017-03-08 13:48:29', '', '贷后管理', '0', '1', null, null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11233', '0', '已还款订单', '11232', '', null, '00000000071', null, '', '2017-03-21 11:57:09', '', '已还款列表', '0', '1', 'RepaymentList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11235', '0', '财务管理', '0', '', null, '00000000046', null, '', '2017-03-07 15:24:17', '', '财务管理', '1', '1', null, null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11236', '0', '支付管理', '0', '', 'icon-qian', '00000000050', null, '', '2017-04-19 11:47:37', '', '打款管理', '0', '1', null, null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11237', '0', '还款管理', '11235', '', null, '00000000034', null, '', '2017-03-07 15:24:38', '', '还款管理', '1', '1', null, null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11238', '0', '代扣支付记录', '11199', '', null, '00000000036', null, '', '2017-03-22 16:02:37', '', '代扣支付记录', '0', '1', 'DeductionsList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11239', '0', '待扣款订单', '11199', '', null, '00000000037', null, '', '2017-03-22 16:02:48', '', '待扣款列表', '1', '1', 'StayDeductionsList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11240', '0', '支付记录', '11236', '', null, '00000000043', null, '', '2017-04-19 11:47:52', '', '支付记录', '0', '1', 'remitList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11241', '0', '支付审核', '11236', '', null, '00000000044', null, '', '2017-04-19 11:47:59', '', '放款支付审核', '0', '1', 'remitCheck', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11242', '0', '系统放款订单', '11236', '', null, '00000000045', null, '', '2017-03-22 16:04:20', '', '系统打款列表', '1', '1', 'systemCallList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11243', '0', '导流分析', '0', '', 'icon-tongji', '00000000084', null, '', '2017-03-20 16:34:11', '', '导流分析', '1', '1', null, null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11244', '0', '渠道管理', '0', '', 'icon-tongji', '00000000122', null, '', '2017-04-24 14:02:59', '', '渠道管理', '0', '1', 'PipelineManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11245', '0', '用户通讯录', '11161', '', null, '00000000026', null, '', null, '', null, null, '1', 'UserAddressBook', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11246', '0', '用户通讯录', '11161', '', null, '00000000026', null, '', '2017-03-17 15:35:48', '', '用户通讯录', '1', '1', 'UserBook', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11247', '0', '借款订单', '11159', '', null, '00000000005', null, '', '2017-03-27 17:31:52', '', '借款列表', '0', '1', 'LoanInformation', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11248', '0', '审核员列表', '11195', '', null, '00000000033', null, '', '2017-03-20 15:32:57', '', '审核员列表', '1', '1', 'AuditorList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11249', '0', '审核订单数量', '11195', '', null, '00000000035', null, '', '2017-03-20 15:33:06', '', '审核订单数量', '1', '1', 'AuditOrderQuantity', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11250', '0', '风控数据', '0', '', 'icon-zhuanyefengkong', '00000000080', null, '', '2017-03-08 13:42:38', '', '风控数据', '0', '0', 'WindControlData', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11251', '0', '每日通过率', '11250', '', null, '00000000061', null, '', null, '', '每日通过率', '0', '1', 'DailyPassRate', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11252', '0', '平台数据日报', '11250', '', null, '00000000062', null, '', null, '', '平台数据日报', '0', '1', 'PlatformDataDaily', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11253', '0', '用户认证数据统计', '11250', '', null, '00000000063', null, '', null, '', '用户认证数据统计', '1', '1', 'UserAuthenticationData', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11254', '0', '运营数据', '0', '', 'icon-tongji', '00000000083', null, '', '2017-03-08 13:46:35', '', ' 运营数据', '0', '0', 'OperationalData', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11255', '0', '整体运营数据', '11254', '', null, '00000000071', null, '', '2017-03-20 11:08:53', '', ' 整体运营数据', '1', '1', 'OverallOperationalData', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11256', '0', '渠道数据汇总列表', '11254', '', null, '00000000072', null, '', '2017-03-20 11:08:45', '', '渠道数据汇总列表', '1', '1', 'ChannelDataList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11257', '0', '每日还款分析', '11254', '', null, '00000000073', null, '', null, '', '每日还款分析', '0', '1', 'DailyRepaymentAnalysis', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11258', '0', '每月还款分析', '11254', '', null, '00000000074', null, '', null, '', ' 每月还款分析', '0', '1', 'MonthlyRepaymentAnalysis', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11259', '0', '每月逾期分析', '11254', '', null, '00000000076', null, '', '2017-03-20 14:59:16', '', ' 每月逾期分析', '1', '1', ' Monthly1', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11260', '0', '财务数据', '0', '', 'icon-tongji', '00000000081', null, '', '2017-03-20 11:09:35', '', '  财务数据', '1', '0', 'FinancialData', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11261', '0', '每日放款数据', '11260', '', null, '00000000079', null, '', '2017-03-20 11:09:44', '', '每日放款数据', '1', '1', 'DailyLoanData', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11262', '0', '审批中心', '0', '', 'icon-xinyong', '00000000100', null, '', '2017-03-08 13:45:37', '', '  审批中心', '1', '0', 'ApprovalCenter', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11263', '0', '待审批单据列表', '11262', '', null, '00000000101', null, '', null, '', '待审批单据列表', '1', '1', 'PengdingApproveBill', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11264', '0', '统计管理', '0', '', 'icon-tongji', '00000000085', null, '', '2017-03-08 13:43:42', '', ' 统计管理', '0', '0', 'StatisticalManagement', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11265', '0', '收入统计', '11264', '', null, '00000000106', null, '', '2017-03-07 20:49:39', '', '收入统计', '1', '1', 'IncomeStatistics', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11266', '0', '支出统计', '11264', '', null, '00000000109', null, '', null, '', '支出统计', '1', '1', 'ExpenditureStatistics', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11267', '0', '对账列表', '11264', '', null, '00000000110', null, '', '2017-03-20 11:10:03', '', '对账列表', '1', '1', 'CheckList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11268', '0', '每日未还本金', '11264', '', null, '00000000111', null, '', null, '', '每日未还本金', '0', '1', 'DailyPrincipal', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11269', '0', '每日放款收支数据', '11264', '', null, '00000000112', null, '', null, '', ' 每日放款收支数据', '0', '1', 'DailylLoanData', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11270', '0', '定时任务', '0', '', 'icon-tongji', '00000000108', null, '', '2017-03-20 15:26:40', '', '定时任务', '0', '1', 'TimedTask', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11271', '0', '任务列表', '11270', '', null, '00000000110', null, '', '2017-03-21 18:14:57', '', '定时任务列表', '0', '1', 'TimedTaskList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11272', '0', '每月逾期分析', '11254', '', null, '00000000077', null, '', '2017-03-20 14:59:45', '', '每月逾期分析', '0', '1', 'Monthly', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11273', '0', '已坏账订单', '11232', '', null, '00000000073', null, '', null, '', '已坏账订单', '0', '1', 'BadDebtsList', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11274', '0', '执行记录', '11270', '', null, '00000000123', null, '', '2017-03-21 18:16:14', '', '定时任务执行记录', '0', '1', 'TimedTaskLog', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11275', '0', '催收反馈', '11210', '', null, '00000000060', null, '', null, '', '催收反馈', '0', '1', 'CollectionFeedbackManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11276', '0', '机审请求记录', '11195', '', null, '00000000025', null, '', null, '', '机审请求记录', '0', '1', 'MachineRequestRecord', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11277', '0', 'Druid监控', '1', '', null, '00000000111', null, '', '2017-03-27 09:38:32', '', 'Druid监控', '0', '1', 'Druid', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11278', '0', '访问码管理', '1', '', null, '00000000001', null, '', null, '', '访问码管理', '0', '1', 'AccessCode', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11279', '0', '工作台', '0', '', 'icon-yonghuxinxi', '00000000001', null, '', '2017-04-06 10:52:13', '', '工作台', '1', '1', 'Workbench', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11280', '0', '用户反馈列表', '11161', '', null, '00000000033', null, '', null, '', '用户反馈列表', '0', '1', 'UserFeedback', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11281', '0', '征信数据接口', '0', '', 'icon-tongji', '00000000070', null, '', '2017-04-10 10:14:41', '', '第三方征信', '0', '1', 'ThirdPartyInquiry', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11282', '0', '第三方征信接口', '11281', '', null, '00000000010', null, '', '2017-04-20 11:38:57', '', '第三方征信', '0', '1', 'ThirdPartyInquiry', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11283', '0', '场景与接口关系维护', '11281', '', null, '00000000030', null, '', '2017-04-20 11:38:38', '', '场景与接口关系维护', '0', '1', 'ScenePortManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11285', '0', '支付对账记录', '11236', '', null, '00000000045', null, '', '2017-04-20 11:23:12', '', '支付对账记录', '0', '1', 'CheckManagement', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11286', '0', '系统数据统计接口', '11281', '', '', '00000000020', null, '', '2017-04-20 13:31:01', '', '系统数据统计接口', '0', '1', 'RiskControlDataStatistics', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11287', '0', '渠道管理', '11244', '', null, '00000000010', null, '', '2017-03-20 11:08:45', '', '用户渠道管理', '0', '1', 'ChannelManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11288', '0', '渠道信息统计', '11244', '', null, '00000000020', null, '', null, '', '渠道信息统计', '0', '1', 'ChannelInformationStatistics', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11291', '0', '渠道app管理', '11244', '', null, '00000000030', null, '', '2017-05-11 14:35:45', '', '渠道app管理', '0', '1', 'ChannelAppManage', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11292', '0', '系统配置', '1', '', null, '00000000099', null, '', '2017-05-27 18:39:17', '', '系统配置', '0', '1', 'SystemConfig', null, null, null, null);
INSERT INTO `arc_sys_menu` VALUES ('11293', '0', 'test', '11161', '', null, '00000000034', null, '', '2017-07-25 17:24:49', '', 'test', '0', '1', 'test', null, null, null, null);

-- ----------------------------
-- Table structure for arc_sys_perm
-- ----------------------------
DROP TABLE IF EXISTS `arc_sys_perm`;
CREATE TABLE `arc_sys_perm` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(128) NOT NULL COMMENT '编码',
  `name` varchar(512) DEFAULT '' COMMENT '权限名称',
  `perm_level` int(2) NOT NULL DEFAULT '1' COMMENT '权限级别 1-系统级别 2-普通级别',
  `remark` varchar(128) DEFAULT '' COMMENT '权限备注',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `add_user` varchar(11) NOT NULL DEFAULT '' COMMENT '添加人',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of arc_sys_perm
-- ----------------------------
INSERT INTO `arc_sys_perm` VALUES ('65', 'modules:manage:system:user:save', '新增用户', '1', '用户管理', '2016-12-05 14:25:30', 'system', '3');
INSERT INTO `arc_sys_perm` VALUES ('66', 'modules:manage:system:user:update', '用户修改', '1', '用户管理', '2016-12-05 14:49:03', 'system', '3');
INSERT INTO `arc_sys_perm` VALUES ('67', 'modules:manage:system:roleMenu:fetch', '分配修改权限', '1', '分配修改权限', '2016-12-05 14:59:51', 'system', '4');
INSERT INTO `arc_sys_perm` VALUES ('68', 'modules:manage:system:role:save', '新增修改角色', '1', '角色管理', '2016-12-05 15:02:26', 'system', '4');
INSERT INTO `arc_sys_perm` VALUES ('69', 'modules:manage:system:role:delete', '删除角色', '1', '角色管理', '2016-12-05 15:03:21', 'system', '4');
INSERT INTO `arc_sys_perm` VALUES ('76', 'modules:manage:system:menu:update', '新增修改菜单', '1', '菜单管理', '2016-12-05 15:41:18', 'system', '2');
INSERT INTO `arc_sys_perm` VALUES ('77', 'modules:manage:system:user:lis', '用户管理', '1', '用户管理', '2016-12-05 15:47:46', 'system', '3');
INSERT INTO `arc_sys_perm` VALUES ('78', 'modules:manage:system:dict:page', '字典查询', '1', '字典管理', '2016-12-05 15:49:17', 'system', '7');
INSERT INTO `arc_sys_perm` VALUES ('79', 'modules:manage:system:dict:detail:find', '字典详情', '1', '字典管理', '2016-12-05 15:50:25', 'system', '7');
INSERT INTO `arc_sys_perm` VALUES ('80', 'modules:manage:system:dict:save', '新增修改字典', '1', '字典管理', '2016-12-05 15:51:31', 'system', '7');
INSERT INTO `arc_sys_perm` VALUES ('81', 'modules:manage:system:dict:detail:save', '新增修改字典详情', '1', '字典管理', '2016-12-05 15:52:20', 'system', '7');
INSERT INTO `arc_sys_perm` VALUES ('82', 'modules:manage:system:dict:delete', '删除字典', '1', '字典管理', '2016-12-05 15:52:57', 'system', '7');
INSERT INTO `arc_sys_perm` VALUES ('83', 'modules:manage:system:dict:detail:delete', '删除字典详情', '1', '字典管理', '2016-12-05 15:53:34', 'system', '7');
INSERT INTO `arc_sys_perm` VALUES ('90', 'modules:manage:system:config:save', '新增修改系统参数', '1', '系统参数设置', '2016-12-05 17:10:25', 'system', '11050');
INSERT INTO `arc_sys_perm` VALUES ('91', 'modules:manage:system:config:list', '查询系统参数', '1', '系统参数设置', '2016-12-05 17:11:04', 'system', '11050');
INSERT INTO `arc_sys_perm` VALUES ('92', 'modules:manage:system:config:delete', '删除系统参数', '1', '系统参数设置', '2016-12-05 17:12:00', 'system', '11050');
INSERT INTO `arc_sys_perm` VALUES ('97', 'modules:manage:system:menu:find', '加载出原有菜单数据', '1', '加载出原有菜单数据', '2016-12-05 21:39:37', 'system', '4');
INSERT INTO `arc_sys_perm` VALUES ('98', 'modules:manage:system:menu:save', '修改用户权限', '1', '修改用户权限', '2016-12-05 21:41:36', 'system', '4');
INSERT INTO `arc_sys_perm` VALUES ('99', 'modules:manage:system:perm:find', '查询所有菜单', '1', '查询所有菜单', '2016-12-05 21:41:36', 'system', '4');
INSERT INTO `arc_sys_perm` VALUES ('100', 'modules:system:sconfig:westone', '获取用户某个菜单面板项集合', '1', '获取用户某个菜单面板项集合', '2016-12-05 21:41:36', 'system', '4');
INSERT INTO `arc_sys_perm` VALUES ('101', 'modules:system:sconfig:tree', '获取菜单树状图', '1', '获取菜单树状图', '2016-12-05 21:47:36', 'system', '4');
INSERT INTO `arc_sys_perm` VALUES ('102', 'modules:manage:system:menu:findMenuTrees', '配置菜单', '1', '配置菜单', '2016-12-05 21:50:16', 'system', '4');
INSERT INTO `arc_sys_perm` VALUES ('103', 'menu:delete', '删除菜单', '1', '删除菜单', '2016-12-05 21:50:16', 'system', '4');
INSERT INTO `arc_sys_perm` VALUES ('104', 'modules:system:sconfig:rolemenu', '分配菜单', '1', '分配菜单', '2016-12-05 21:50:16', 'system', '4');
INSERT INTO `arc_sys_perm` VALUES ('105', 'modules:manage:system:menu:combo:find', '菜单下拉框', '1', '菜单下拉框', '2016-12-05 21:50:16', 'system', '4');
INSERT INTO `arc_sys_perm` VALUES ('106', 'modules:system:modifyPassword', '修改密码', '1', '修改密码', '2016-12-05 14:25:30', 'system', '3');
INSERT INTO `arc_sys_perm` VALUES ('107', 'modules:system:getcustomerservicestafflist', '获取客服专员', '1', '获取客服专员', '2016-12-05 15:47:46', 'system', '3');
INSERT INTO `arc_sys_perm` VALUES ('108', 'modules:manage:system:user:info:find', '查询用户', '1', '查询用户', '2016-12-05 15:47:46', 'system', '3');
INSERT INTO `arc_sys_perm` VALUES ('109', 'modules:manage:system:dict:cache:list', '缓存字典使用    ', '1', '缓存字典使用    ', '2016-12-05 15:53:34', 'system', '7');

-- ----------------------------
-- Table structure for arc_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `arc_sys_role`;
CREATE TABLE `arc_sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(128) DEFAULT '' COMMENT '角色名',
  `nid` varchar(64) DEFAULT '' COMMENT '角色唯一标示',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user` varchar(128) DEFAULT '' COMMENT '添加者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(128) DEFAULT '' COMMENT '修改者',
  `remark` varchar(256) DEFAULT '' COMMENT '备注',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '是否删除：0不删除，1删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nid_index` (`nid`) USING BTREE,
  KEY `primary_key` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of arc_sys_role
-- ----------------------------
INSERT INTO `arc_sys_role` VALUES ('1', '系统管理员', 'system', '2016-05-06 00:59:00', 'system', '2016-05-06 12:00:00', 'system', '超级管理员', '0');
INSERT INTO `arc_sys_role` VALUES ('2', '财务人员', 'financialStaff', '2016-08-04 10:08:35', 'system', '2017-03-05 16:02:02', 'system', '财务人员', '0');
INSERT INTO `arc_sys_role` VALUES ('3', '代理商', 'agent', '2017-03-03 15:19:17', 'system', '2017-03-03 16:01:57', 'system', '代理商', '0');
INSERT INTO `arc_sys_role` VALUES ('4', '运营人员', 'operator', '2017-03-08 12:07:55', 'system', '2017-03-08 12:07:55', 'system', '运营人员', '0');
INSERT INTO `arc_sys_role` VALUES ('5', '催收管理员', 'collector', '2017-03-08 12:08:40', 'system', '2017-03-08 12:08:40', 'system', '催收管理员', '0');
INSERT INTO `arc_sys_role` VALUES ('6', '催收专员', 'collectionSpecialist', '2017-03-08 12:09:10', 'system', '2017-03-08 12:09:10', 'system', '催收专员', '0');
INSERT INTO `arc_sys_role` VALUES ('17', '审核客服', 'customerServiceOfficer', '2017-03-08 12:11:17', 'system', '2017-03-08 12:11:17', 'system', '客服人员', '0');
INSERT INTO `arc_sys_role` VALUES ('18', '风控人员', 'riskControlPersonnel', '2017-03-08 12:11:46', 'system', '2017-03-08 12:11:46', 'system', '风控人员', '0');

-- ----------------------------
-- Table structure for arc_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `arc_sys_role_menu`;
CREATE TABLE `arc_sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` int(11) NOT NULL COMMENT '角色主键',
  `menu_id` int(11) NOT NULL COMMENT '菜单主键',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id` (`role_id`,`menu_id`) USING BTREE,
  KEY `role_id_index` (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5609 DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

-- ----------------------------
-- Records of arc_sys_role_menu
-- ----------------------------
INSERT INTO `arc_sys_role_menu` VALUES ('5398', '1', '1');
INSERT INTO `arc_sys_role_menu` VALUES ('5390', '1', '2');
INSERT INTO `arc_sys_role_menu` VALUES ('5391', '1', '3');
INSERT INTO `arc_sys_role_menu` VALUES ('5392', '1', '4');
INSERT INTO `arc_sys_role_menu` VALUES ('5393', '1', '7');
INSERT INTO `arc_sys_role_menu` VALUES ('5394', '1', '11050');
INSERT INTO `arc_sys_role_menu` VALUES ('5413', '1', '11140');
INSERT INTO `arc_sys_role_menu` VALUES ('5399', '1', '11141');
INSERT INTO `arc_sys_role_menu` VALUES ('5403', '1', '11145');
INSERT INTO `arc_sys_role_menu` VALUES ('5400', '1', '11146');
INSERT INTO `arc_sys_role_menu` VALUES ('5404', '1', '11150');
INSERT INTO `arc_sys_role_menu` VALUES ('5401', '1', '11151');
INSERT INTO `arc_sys_role_menu` VALUES ('5402', '1', '11153');
INSERT INTO `arc_sys_role_menu` VALUES ('5405', '1', '11156');
INSERT INTO `arc_sys_role_menu` VALUES ('5408', '1', '11158');
INSERT INTO `arc_sys_role_menu` VALUES ('5412', '1', '11159');
INSERT INTO `arc_sys_role_menu` VALUES ('5406', '1', '11160');
INSERT INTO `arc_sys_role_menu` VALUES ('5407', '1', '11163');
INSERT INTO `arc_sys_role_menu` VALUES ('5414', '1', '11168');
INSERT INTO `arc_sys_role_menu` VALUES ('5421', '1', '11174');
INSERT INTO `arc_sys_role_menu` VALUES ('5416', '1', '11176');
INSERT INTO `arc_sys_role_menu` VALUES ('5417', '1', '11177');
INSERT INTO `arc_sys_role_menu` VALUES ('5418', '1', '11178');
INSERT INTO `arc_sys_role_menu` VALUES ('5426', '1', '11180');
INSERT INTO `arc_sys_role_menu` VALUES ('5422', '1', '11181');
INSERT INTO `arc_sys_role_menu` VALUES ('5423', '1', '11182');
INSERT INTO `arc_sys_role_menu` VALUES ('5424', '1', '11183');
INSERT INTO `arc_sys_role_menu` VALUES ('5425', '1', '11184');
INSERT INTO `arc_sys_role_menu` VALUES ('5433', '1', '11195');
INSERT INTO `arc_sys_role_menu` VALUES ('5427', '1', '11196');
INSERT INTO `arc_sys_role_menu` VALUES ('5428', '1', '11197');
INSERT INTO `arc_sys_role_menu` VALUES ('5429', '1', '11198');
INSERT INTO `arc_sys_role_menu` VALUES ('5439', '1', '11199');
INSERT INTO `arc_sys_role_menu` VALUES ('5434', '1', '11200');
INSERT INTO `arc_sys_role_menu` VALUES ('5435', '1', '11201');
INSERT INTO `arc_sys_role_menu` VALUES ('5436', '1', '11202');
INSERT INTO `arc_sys_role_menu` VALUES ('5437', '1', '11203');
INSERT INTO `arc_sys_role_menu` VALUES ('5441', '1', '11204');
INSERT INTO `arc_sys_role_menu` VALUES ('5440', '1', '11205');
INSERT INTO `arc_sys_role_menu` VALUES ('5442', '1', '11206');
INSERT INTO `arc_sys_role_menu` VALUES ('5445', '1', '11207');
INSERT INTO `arc_sys_role_menu` VALUES ('5443', '1', '11208');
INSERT INTO `arc_sys_role_menu` VALUES ('5444', '1', '11209');
INSERT INTO `arc_sys_role_menu` VALUES ('5452', '1', '11210');
INSERT INTO `arc_sys_role_menu` VALUES ('5446', '1', '11211');
INSERT INTO `arc_sys_role_menu` VALUES ('5447', '1', '11212');
INSERT INTO `arc_sys_role_menu` VALUES ('5448', '1', '11213');
INSERT INTO `arc_sys_role_menu` VALUES ('5449', '1', '11214');
INSERT INTO `arc_sys_role_menu` VALUES ('5450', '1', '11215');
INSERT INTO `arc_sys_role_menu` VALUES ('5409', '1', '11216');
INSERT INTO `arc_sys_role_menu` VALUES ('5461', '1', '11217');
INSERT INTO `arc_sys_role_menu` VALUES ('5456', '1', '11218');
INSERT INTO `arc_sys_role_menu` VALUES ('5453', '1', '11219');
INSERT INTO `arc_sys_role_menu` VALUES ('5419', '1', '11220');
INSERT INTO `arc_sys_role_menu` VALUES ('5420', '1', '11221');
INSERT INTO `arc_sys_role_menu` VALUES ('5454', '1', '11222');
INSERT INTO `arc_sys_role_menu` VALUES ('5455', '1', '11223');
INSERT INTO `arc_sys_role_menu` VALUES ('5460', '1', '11224');
INSERT INTO `arc_sys_role_menu` VALUES ('5457', '1', '11225');
INSERT INTO `arc_sys_role_menu` VALUES ('5458', '1', '11226');
INSERT INTO `arc_sys_role_menu` VALUES ('5459', '1', '11227');
INSERT INTO `arc_sys_role_menu` VALUES ('5410', '1', '11228');
INSERT INTO `arc_sys_role_menu` VALUES ('5464', '1', '11232');
INSERT INTO `arc_sys_role_menu` VALUES ('5462', '1', '11233');
INSERT INTO `arc_sys_role_menu` VALUES ('5468', '1', '11236');
INSERT INTO `arc_sys_role_menu` VALUES ('5438', '1', '11238');
INSERT INTO `arc_sys_role_menu` VALUES ('5465', '1', '11240');
INSERT INTO `arc_sys_role_menu` VALUES ('5466', '1', '11241');
INSERT INTO `arc_sys_role_menu` VALUES ('5472', '1', '11244');
INSERT INTO `arc_sys_role_menu` VALUES ('5411', '1', '11247');
INSERT INTO `arc_sys_role_menu` VALUES ('5475', '1', '11250');
INSERT INTO `arc_sys_role_menu` VALUES ('5473', '1', '11251');
INSERT INTO `arc_sys_role_menu` VALUES ('5474', '1', '11252');
INSERT INTO `arc_sys_role_menu` VALUES ('5479', '1', '11254');
INSERT INTO `arc_sys_role_menu` VALUES ('5476', '1', '11257');
INSERT INTO `arc_sys_role_menu` VALUES ('5477', '1', '11258');
INSERT INTO `arc_sys_role_menu` VALUES ('5482', '1', '11264');
INSERT INTO `arc_sys_role_menu` VALUES ('5480', '1', '11268');
INSERT INTO `arc_sys_role_menu` VALUES ('5481', '1', '11269');
INSERT INTO `arc_sys_role_menu` VALUES ('5485', '1', '11270');
INSERT INTO `arc_sys_role_menu` VALUES ('5483', '1', '11271');
INSERT INTO `arc_sys_role_menu` VALUES ('5478', '1', '11272');
INSERT INTO `arc_sys_role_menu` VALUES ('5463', '1', '11273');
INSERT INTO `arc_sys_role_menu` VALUES ('5484', '1', '11274');
INSERT INTO `arc_sys_role_menu` VALUES ('5451', '1', '11275');
INSERT INTO `arc_sys_role_menu` VALUES ('5430', '1', '11276');
INSERT INTO `arc_sys_role_menu` VALUES ('5395', '1', '11277');
INSERT INTO `arc_sys_role_menu` VALUES ('5396', '1', '11278');
INSERT INTO `arc_sys_role_menu` VALUES ('5415', '1', '11280');
INSERT INTO `arc_sys_role_menu` VALUES ('5489', '1', '11281');
INSERT INTO `arc_sys_role_menu` VALUES ('5486', '1', '11282');
INSERT INTO `arc_sys_role_menu` VALUES ('5487', '1', '11283');
INSERT INTO `arc_sys_role_menu` VALUES ('5467', '1', '11285');
INSERT INTO `arc_sys_role_menu` VALUES ('5488', '1', '11286');
INSERT INTO `arc_sys_role_menu` VALUES ('5469', '1', '11287');
INSERT INTO `arc_sys_role_menu` VALUES ('5470', '1', '11288');
INSERT INTO `arc_sys_role_menu` VALUES ('5431', '1', '11289');
INSERT INTO `arc_sys_role_menu` VALUES ('5432', '1', '11290');
INSERT INTO `arc_sys_role_menu` VALUES ('5471', '1', '11291');
INSERT INTO `arc_sys_role_menu` VALUES ('5397', '1', '11292');
INSERT INTO `arc_sys_role_menu` VALUES ('1701', '2', '1');
INSERT INTO `arc_sys_role_menu` VALUES ('1696', '2', '2');
INSERT INTO `arc_sys_role_menu` VALUES ('1697', '2', '3');
INSERT INTO `arc_sys_role_menu` VALUES ('1698', '2', '4');
INSERT INTO `arc_sys_role_menu` VALUES ('1699', '2', '7');
INSERT INTO `arc_sys_role_menu` VALUES ('1700', '2', '11050');
INSERT INTO `arc_sys_role_menu` VALUES ('1702', '2', '11138');
INSERT INTO `arc_sys_role_menu` VALUES ('1705', '2', '11159');
INSERT INTO `arc_sys_role_menu` VALUES ('1712', '2', '11199');
INSERT INTO `arc_sys_role_menu` VALUES ('1706', '2', '11200');
INSERT INTO `arc_sys_role_menu` VALUES ('1707', '2', '11201');
INSERT INTO `arc_sys_role_menu` VALUES ('1708', '2', '11202');
INSERT INTO `arc_sys_role_menu` VALUES ('1709', '2', '11203');
INSERT INTO `arc_sys_role_menu` VALUES ('1703', '2', '11216');
INSERT INTO `arc_sys_role_menu` VALUES ('1713', '2', '11217');
INSERT INTO `arc_sys_role_menu` VALUES ('1704', '2', '11228');
INSERT INTO `arc_sys_role_menu` VALUES ('1715', '2', '11232');
INSERT INTO `arc_sys_role_menu` VALUES ('1714', '2', '11233');
INSERT INTO `arc_sys_role_menu` VALUES ('1719', '2', '11236');
INSERT INTO `arc_sys_role_menu` VALUES ('1710', '2', '11238');
INSERT INTO `arc_sys_role_menu` VALUES ('1711', '2', '11239');
INSERT INTO `arc_sys_role_menu` VALUES ('1716', '2', '11240');
INSERT INTO `arc_sys_role_menu` VALUES ('1717', '2', '11241');
INSERT INTO `arc_sys_role_menu` VALUES ('1718', '2', '11242');
INSERT INTO `arc_sys_role_menu` VALUES ('1721', '2', '11260');
INSERT INTO `arc_sys_role_menu` VALUES ('1720', '2', '11261');
INSERT INTO `arc_sys_role_menu` VALUES ('1727', '2', '11264');
INSERT INTO `arc_sys_role_menu` VALUES ('1722', '2', '11265');
INSERT INTO `arc_sys_role_menu` VALUES ('1723', '2', '11266');
INSERT INTO `arc_sys_role_menu` VALUES ('1724', '2', '11267');
INSERT INTO `arc_sys_role_menu` VALUES ('1725', '2', '11268');
INSERT INTO `arc_sys_role_menu` VALUES ('1726', '2', '11269');
INSERT INTO `arc_sys_role_menu` VALUES ('3392', '3', '11180');
INSERT INTO `arc_sys_role_menu` VALUES ('3388', '3', '11181');
INSERT INTO `arc_sys_role_menu` VALUES ('3389', '3', '11182');
INSERT INTO `arc_sys_role_menu` VALUES ('3390', '3', '11183');
INSERT INTO `arc_sys_role_menu` VALUES ('3391', '3', '11184');
INSERT INTO `arc_sys_role_menu` VALUES ('1728', '4', '11140');
INSERT INTO `arc_sys_role_menu` VALUES ('1732', '4', '11161');
INSERT INTO `arc_sys_role_menu` VALUES ('1729', '4', '11162');
INSERT INTO `arc_sys_role_menu` VALUES ('1730', '4', '11168');
INSERT INTO `arc_sys_role_menu` VALUES ('1736', '4', '11250');
INSERT INTO `arc_sys_role_menu` VALUES ('1733', '4', '11251');
INSERT INTO `arc_sys_role_menu` VALUES ('1734', '4', '11252');
INSERT INTO `arc_sys_role_menu` VALUES ('1735', '4', '11253');
INSERT INTO `arc_sys_role_menu` VALUES ('1742', '4', '11254');
INSERT INTO `arc_sys_role_menu` VALUES ('1737', '4', '11255');
INSERT INTO `arc_sys_role_menu` VALUES ('1738', '4', '11256');
INSERT INTO `arc_sys_role_menu` VALUES ('1739', '4', '11257');
INSERT INTO `arc_sys_role_menu` VALUES ('1740', '4', '11258');
INSERT INTO `arc_sys_role_menu` VALUES ('1741', '4', '11259');
INSERT INTO `arc_sys_role_menu` VALUES ('1744', '4', '11260');
INSERT INTO `arc_sys_role_menu` VALUES ('1743', '4', '11261');
INSERT INTO `arc_sys_role_menu` VALUES ('2149', '5', '11204');
INSERT INTO `arc_sys_role_menu` VALUES ('2147', '5', '11205');
INSERT INTO `arc_sys_role_menu` VALUES ('2148', '5', '11206');
INSERT INTO `arc_sys_role_menu` VALUES ('2152', '5', '11207');
INSERT INTO `arc_sys_role_menu` VALUES ('2150', '5', '11208');
INSERT INTO `arc_sys_role_menu` VALUES ('2151', '5', '11209');
INSERT INTO `arc_sys_role_menu` VALUES ('2156', '5', '11218');
INSERT INTO `arc_sys_role_menu` VALUES ('2153', '5', '11219');
INSERT INTO `arc_sys_role_menu` VALUES ('2154', '5', '11222');
INSERT INTO `arc_sys_role_menu` VALUES ('2155', '5', '11223');
INSERT INTO `arc_sys_role_menu` VALUES ('2160', '5', '11224');
INSERT INTO `arc_sys_role_menu` VALUES ('2157', '5', '11225');
INSERT INTO `arc_sys_role_menu` VALUES ('2158', '5', '11226');
INSERT INTO `arc_sys_role_menu` VALUES ('2159', '5', '11227');
INSERT INTO `arc_sys_role_menu` VALUES ('2140', '6', '11207');
INSERT INTO `arc_sys_role_menu` VALUES ('2138', '6', '11208');
INSERT INTO `arc_sys_role_menu` VALUES ('2139', '6', '11209');
INSERT INTO `arc_sys_role_menu` VALUES ('2146', '6', '11210');
INSERT INTO `arc_sys_role_menu` VALUES ('2141', '6', '11211');
INSERT INTO `arc_sys_role_menu` VALUES ('2142', '6', '11212');
INSERT INTO `arc_sys_role_menu` VALUES ('2143', '6', '11213');
INSERT INTO `arc_sys_role_menu` VALUES ('2144', '6', '11214');
INSERT INTO `arc_sys_role_menu` VALUES ('2145', '6', '11215');
INSERT INTO `arc_sys_role_menu` VALUES ('5599', '17', '11140');
INSERT INTO `arc_sys_role_menu` VALUES ('5598', '17', '11159');
INSERT INTO `arc_sys_role_menu` VALUES ('5600', '17', '11168');
INSERT INTO `arc_sys_role_menu` VALUES ('5608', '17', '11195');
INSERT INTO `arc_sys_role_menu` VALUES ('5602', '17', '11196');
INSERT INTO `arc_sys_role_menu` VALUES ('5603', '17', '11197');
INSERT INTO `arc_sys_role_menu` VALUES ('5604', '17', '11198');
INSERT INTO `arc_sys_role_menu` VALUES ('5595', '17', '11216');
INSERT INTO `arc_sys_role_menu` VALUES ('5596', '17', '11228');
INSERT INTO `arc_sys_role_menu` VALUES ('5597', '17', '11247');
INSERT INTO `arc_sys_role_menu` VALUES ('5605', '17', '11276');
INSERT INTO `arc_sys_role_menu` VALUES ('5601', '17', '11280');
INSERT INTO `arc_sys_role_menu` VALUES ('5606', '17', '11289');
INSERT INTO `arc_sys_role_menu` VALUES ('5607', '17', '11290');
INSERT INTO `arc_sys_role_menu` VALUES ('1770', '18', '11141');
INSERT INTO `arc_sys_role_menu` VALUES ('1771', '18', '11146');
INSERT INTO `arc_sys_role_menu` VALUES ('1772', '18', '11151');
INSERT INTO `arc_sys_role_menu` VALUES ('1774', '18', '11153');
INSERT INTO `arc_sys_role_menu` VALUES ('1773', '18', '11154');
INSERT INTO `arc_sys_role_menu` VALUES ('1781', '18', '11195');
INSERT INTO `arc_sys_role_menu` VALUES ('1775', '18', '11196');
INSERT INTO `arc_sys_role_menu` VALUES ('1776', '18', '11197');
INSERT INTO `arc_sys_role_menu` VALUES ('1777', '18', '11198');
INSERT INTO `arc_sys_role_menu` VALUES ('1778', '18', '11247');
INSERT INTO `arc_sys_role_menu` VALUES ('1779', '18', '11248');
INSERT INTO `arc_sys_role_menu` VALUES ('1780', '18', '11249');
INSERT INTO `arc_sys_role_menu` VALUES ('1785', '18', '11250');
INSERT INTO `arc_sys_role_menu` VALUES ('1782', '18', '11251');
INSERT INTO `arc_sys_role_menu` VALUES ('1783', '18', '11252');
INSERT INTO `arc_sys_role_menu` VALUES ('1784', '18', '11253');
INSERT INTO `arc_sys_role_menu` VALUES ('1787', '18', '11262');
INSERT INTO `arc_sys_role_menu` VALUES ('1786', '18', '11263');
INSERT INTO `arc_sys_role_menu` VALUES ('5490', '19', '1');
INSERT INTO `arc_sys_role_menu` VALUES ('5491', '19', '2');
INSERT INTO `arc_sys_role_menu` VALUES ('5492', '19', '3');
INSERT INTO `arc_sys_role_menu` VALUES ('5493', '19', '4');
INSERT INTO `arc_sys_role_menu` VALUES ('5494', '19', '7');
INSERT INTO `arc_sys_role_menu` VALUES ('5495', '19', '11050');
INSERT INTO `arc_sys_role_menu` VALUES ('5514', '19', '11140');
INSERT INTO `arc_sys_role_menu` VALUES ('5500', '19', '11141');
INSERT INTO `arc_sys_role_menu` VALUES ('5504', '19', '11145');
INSERT INTO `arc_sys_role_menu` VALUES ('5501', '19', '11146');
INSERT INTO `arc_sys_role_menu` VALUES ('5505', '19', '11150');
INSERT INTO `arc_sys_role_menu` VALUES ('5502', '19', '11151');
INSERT INTO `arc_sys_role_menu` VALUES ('5499', '19', '11153');
INSERT INTO `arc_sys_role_menu` VALUES ('5506', '19', '11156');
INSERT INTO `arc_sys_role_menu` VALUES ('5503', '19', '11158');
INSERT INTO `arc_sys_role_menu` VALUES ('5509', '19', '11159');
INSERT INTO `arc_sys_role_menu` VALUES ('5507', '19', '11160');
INSERT INTO `arc_sys_role_menu` VALUES ('5513', '19', '11161');
INSERT INTO `arc_sys_role_menu` VALUES ('5508', '19', '11163');
INSERT INTO `arc_sys_role_menu` VALUES ('5515', '19', '11168');
INSERT INTO `arc_sys_role_menu` VALUES ('5518', '19', '11174');
INSERT INTO `arc_sys_role_menu` VALUES ('5519', '19', '11176');
INSERT INTO `arc_sys_role_menu` VALUES ('5520', '19', '11177');
INSERT INTO `arc_sys_role_menu` VALUES ('5521', '19', '11178');
INSERT INTO `arc_sys_role_menu` VALUES ('5524', '19', '11180');
INSERT INTO `arc_sys_role_menu` VALUES ('5525', '19', '11181');
INSERT INTO `arc_sys_role_menu` VALUES ('5526', '19', '11182');
INSERT INTO `arc_sys_role_menu` VALUES ('5527', '19', '11183');
INSERT INTO `arc_sys_role_menu` VALUES ('5528', '19', '11184');
INSERT INTO `arc_sys_role_menu` VALUES ('5529', '19', '11195');
INSERT INTO `arc_sys_role_menu` VALUES ('5530', '19', '11196');
INSERT INTO `arc_sys_role_menu` VALUES ('5531', '19', '11197');
INSERT INTO `arc_sys_role_menu` VALUES ('5532', '19', '11198');
INSERT INTO `arc_sys_role_menu` VALUES ('5536', '19', '11199');
INSERT INTO `arc_sys_role_menu` VALUES ('5537', '19', '11200');
INSERT INTO `arc_sys_role_menu` VALUES ('5538', '19', '11201');
INSERT INTO `arc_sys_role_menu` VALUES ('5539', '19', '11202');
INSERT INTO `arc_sys_role_menu` VALUES ('5540', '19', '11203');
INSERT INTO `arc_sys_role_menu` VALUES ('5542', '19', '11204');
INSERT INTO `arc_sys_role_menu` VALUES ('5543', '19', '11205');
INSERT INTO `arc_sys_role_menu` VALUES ('5545', '19', '11206');
INSERT INTO `arc_sys_role_menu` VALUES ('5544', '19', '11207');
INSERT INTO `arc_sys_role_menu` VALUES ('5546', '19', '11208');
INSERT INTO `arc_sys_role_menu` VALUES ('5547', '19', '11209');
INSERT INTO `arc_sys_role_menu` VALUES ('5548', '19', '11210');
INSERT INTO `arc_sys_role_menu` VALUES ('5549', '19', '11211');
INSERT INTO `arc_sys_role_menu` VALUES ('5550', '19', '11212');
INSERT INTO `arc_sys_role_menu` VALUES ('5551', '19', '11213');
INSERT INTO `arc_sys_role_menu` VALUES ('5552', '19', '11214');
INSERT INTO `arc_sys_role_menu` VALUES ('5553', '19', '11215');
INSERT INTO `arc_sys_role_menu` VALUES ('5510', '19', '11216');
INSERT INTO `arc_sys_role_menu` VALUES ('5564', '19', '11217');
INSERT INTO `arc_sys_role_menu` VALUES ('5555', '19', '11218');
INSERT INTO `arc_sys_role_menu` VALUES ('5556', '19', '11219');
INSERT INTO `arc_sys_role_menu` VALUES ('5522', '19', '11220');
INSERT INTO `arc_sys_role_menu` VALUES ('5523', '19', '11221');
INSERT INTO `arc_sys_role_menu` VALUES ('5557', '19', '11222');
INSERT INTO `arc_sys_role_menu` VALUES ('5558', '19', '11223');
INSERT INTO `arc_sys_role_menu` VALUES ('5559', '19', '11224');
INSERT INTO `arc_sys_role_menu` VALUES ('5560', '19', '11225');
INSERT INTO `arc_sys_role_menu` VALUES ('5561', '19', '11226');
INSERT INTO `arc_sys_role_menu` VALUES ('5562', '19', '11227');
INSERT INTO `arc_sys_role_menu` VALUES ('5511', '19', '11228');
INSERT INTO `arc_sys_role_menu` VALUES ('5563', '19', '11232');
INSERT INTO `arc_sys_role_menu` VALUES ('5565', '19', '11233');
INSERT INTO `arc_sys_role_menu` VALUES ('5567', '19', '11236');
INSERT INTO `arc_sys_role_menu` VALUES ('5541', '19', '11238');
INSERT INTO `arc_sys_role_menu` VALUES ('5568', '19', '11240');
INSERT INTO `arc_sys_role_menu` VALUES ('5569', '19', '11241');
INSERT INTO `arc_sys_role_menu` VALUES ('5571', '19', '11244');
INSERT INTO `arc_sys_role_menu` VALUES ('5512', '19', '11247');
INSERT INTO `arc_sys_role_menu` VALUES ('5575', '19', '11250');
INSERT INTO `arc_sys_role_menu` VALUES ('5576', '19', '11251');
INSERT INTO `arc_sys_role_menu` VALUES ('5577', '19', '11252');
INSERT INTO `arc_sys_role_menu` VALUES ('5578', '19', '11254');
INSERT INTO `arc_sys_role_menu` VALUES ('5579', '19', '11257');
INSERT INTO `arc_sys_role_menu` VALUES ('5580', '19', '11258');
INSERT INTO `arc_sys_role_menu` VALUES ('5582', '19', '11264');
INSERT INTO `arc_sys_role_menu` VALUES ('5583', '19', '11268');
INSERT INTO `arc_sys_role_menu` VALUES ('5584', '19', '11269');
INSERT INTO `arc_sys_role_menu` VALUES ('5585', '19', '11270');
INSERT INTO `arc_sys_role_menu` VALUES ('5586', '19', '11271');
INSERT INTO `arc_sys_role_menu` VALUES ('5581', '19', '11272');
INSERT INTO `arc_sys_role_menu` VALUES ('5566', '19', '11273');
INSERT INTO `arc_sys_role_menu` VALUES ('5587', '19', '11274');
INSERT INTO `arc_sys_role_menu` VALUES ('5554', '19', '11275');
INSERT INTO `arc_sys_role_menu` VALUES ('5533', '19', '11276');
INSERT INTO `arc_sys_role_menu` VALUES ('5496', '19', '11277');
INSERT INTO `arc_sys_role_menu` VALUES ('5497', '19', '11278');
INSERT INTO `arc_sys_role_menu` VALUES ('5516', '19', '11280');
INSERT INTO `arc_sys_role_menu` VALUES ('5588', '19', '11281');
INSERT INTO `arc_sys_role_menu` VALUES ('5589', '19', '11282');
INSERT INTO `arc_sys_role_menu` VALUES ('5590', '19', '11283');
INSERT INTO `arc_sys_role_menu` VALUES ('5570', '19', '11285');
INSERT INTO `arc_sys_role_menu` VALUES ('5591', '19', '11286');
INSERT INTO `arc_sys_role_menu` VALUES ('5572', '19', '11287');
INSERT INTO `arc_sys_role_menu` VALUES ('5573', '19', '11288');
INSERT INTO `arc_sys_role_menu` VALUES ('5534', '19', '11289');
INSERT INTO `arc_sys_role_menu` VALUES ('5535', '19', '11290');
INSERT INTO `arc_sys_role_menu` VALUES ('5574', '19', '11291');
INSERT INTO `arc_sys_role_menu` VALUES ('5498', '19', '11292');
INSERT INTO `arc_sys_role_menu` VALUES ('5517', '19', '11293');

-- ----------------------------
-- Table structure for arc_sys_role_perm
-- ----------------------------
DROP TABLE IF EXISTS `arc_sys_role_perm`;
CREATE TABLE `arc_sys_role_perm` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `perm_id` int(11) NOT NULL COMMENT '权限ID',
  `add_user` varchar(11) NOT NULL DEFAULT '' COMMENT '添加人',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5910 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of arc_sys_role_perm
-- ----------------------------
INSERT INTO `arc_sys_role_perm` VALUES ('2845', '2', '76', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2846', '2', '65', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2847', '2', '66', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2848', '2', '77', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2849', '2', '106', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2850', '2', '107', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2851', '2', '108', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2852', '2', '67', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2853', '2', '68', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2854', '2', '69', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2855', '2', '97', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2856', '2', '98', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2857', '2', '99', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2858', '2', '100', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2859', '2', '101', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2860', '2', '102', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2861', '2', '103', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2862', '2', '104', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2863', '2', '105', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2864', '2', '78', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2865', '2', '79', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2866', '2', '80', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2867', '2', '81', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2868', '2', '82', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2869', '2', '83', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2870', '2', '109', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2871', '2', '90', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2872', '2', '91', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('2873', '2', '92', 'system', '2017-03-03 16:00:02');
INSERT INTO `arc_sys_role_perm` VALUES ('5852', '1', '76', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5853', '1', '65', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5854', '1', '66', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5855', '1', '77', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5856', '1', '106', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5857', '1', '107', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5858', '1', '108', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5859', '1', '67', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5860', '1', '68', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5861', '1', '69', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5862', '1', '97', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5863', '1', '98', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5864', '1', '99', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5865', '1', '100', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5866', '1', '101', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5867', '1', '102', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5868', '1', '103', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5869', '1', '104', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5870', '1', '105', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5871', '1', '78', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5872', '1', '79', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5873', '1', '80', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5874', '1', '81', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5875', '1', '82', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5876', '1', '83', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5877', '1', '109', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5878', '1', '90', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5879', '1', '91', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5880', '1', '92', 'system', '2017-07-13 14:52:35');
INSERT INTO `arc_sys_role_perm` VALUES ('5881', '19', '76', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5882', '19', '65', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5883', '19', '66', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5884', '19', '77', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5885', '19', '106', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5886', '19', '107', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5887', '19', '108', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5888', '19', '67', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5889', '19', '68', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5890', '19', '69', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5891', '19', '97', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5892', '19', '98', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5893', '19', '99', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5894', '19', '100', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5895', '19', '101', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5896', '19', '102', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5897', '19', '103', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5898', '19', '104', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5899', '19', '105', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5900', '19', '78', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5901', '19', '79', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5902', '19', '80', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5903', '19', '81', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5904', '19', '82', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5905', '19', '83', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5906', '19', '109', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5907', '19', '90', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5908', '19', '91', 'system', '2017-07-29 09:56:10');
INSERT INTO `arc_sys_role_perm` VALUES ('5909', '19', '92', 'system', '2017-07-29 09:56:10');

-- ----------------------------
-- Table structure for arc_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `arc_sys_user`;
CREATE TABLE `arc_sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `name` varchar(128) DEFAULT '' COMMENT '姓名',
  `user_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '登陆名',
  `password` varchar(128) DEFAULT '' COMMENT '密码',
  `job_num` varchar(128) DEFAULT '' COMMENT '工号',
  `company_id` char(64) DEFAULT NULL COMMENT '公司',
  `office_id` char(64) DEFAULT NULL COMMENT '部门',
  `office_over` varchar(1024) DEFAULT NULL COMMENT '管辖机构',
  `position` int(3) unsigned zerofill DEFAULT '001' COMMENT '职位 0普通职员 1主管  2部门经理',
  `email` varchar(256) DEFAULT '' COMMENT '邮箱',
  `phone` varchar(128) DEFAULT '' COMMENT '电话',
  `mobile` varchar(128) DEFAULT '' COMMENT '手机',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态：0正常 1禁用',
  `login_ip` varchar(128) DEFAULT '' COMMENT '最后登陆IP',
  `login_time` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user` varchar(128) DEFAULT '' COMMENT '添加者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(128) DEFAULT '' COMMENT '修改者',
  `remark` varchar(256) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `userNameIndex` (`user_name`) USING BTREE,
  KEY `officeIdIndex` (`office_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
-- Records of arc_sys_user
-- ----------------------------
INSERT INTO `arc_sys_user` VALUES ('1', '管理员', 'system', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '1', '11', '11', '1101,1102,11030101,11030102,110301,11030201,11030202,110302,11030501,11030502,110305,11030701,11030702,110307,1103,11040101,11040102,110401,1104,11050101,11050102,110501,11050201,11050202,110502,1105,11', '001', null, null, null, '0', null, null, null, null, '2017-05-31 13:58:02', 'system', null);
INSERT INTO `arc_sys_user` VALUES ('2', '财务人员', 'cwry', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', 'ly123456', '11', '11030502', null, '000', null, null, '13436444855', '0', null, null, '2016-08-19 14:05:04', '吴彦祖', '2017-05-31 13:49:43', 'system', '演示');
INSERT INTO `arc_sys_user` VALUES ('4', '运营人员', 'yyry', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '20170001', null, null, null, '001', '', '', '', '0', '', null, '2017-03-03 15:15:07', '', '2017-05-31 13:55:04', '', '');
INSERT INTO `arc_sys_user` VALUES ('5', '催收管理员', 'csry', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '201700012', null, null, null, '001', '', '', '', '0', '', null, '2017-03-03 15:15:07', '', '2017-05-31 11:46:22', '', '');
INSERT INTO `arc_sys_user` VALUES ('6', '催收专员', 'cszy', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '20170003', null, null, null, '001', '', '18258224675', '', '0', '', null, '2017-03-03 15:15:07', '', '2017-05-31 13:57:26', '', '');
INSERT INTO `arc_sys_user` VALUES ('7', '客服人员', 'kfry', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '20170004', null, null, null, '001', '', '', '', '0', '', null, '2017-03-03 15:15:07', '', null, '', '');
INSERT INTO `arc_sys_user` VALUES ('8', '风控人员', 'fkry', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '20170005', null, null, null, '001', '', '', '', '1', '', null, '2017-03-03 15:15:07', '', '2017-05-31 15:36:02', '', '');
INSERT INTO `arc_sys_user` VALUES ('13', '江丹丹', '18258224674', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', null, null, null, null, null, null, '18258224674', null, '1', null, null, '2017-05-05 13:47:51', null, null, null, null);
INSERT INTO `arc_sys_user` VALUES ('14', '陶昊', '13575754343', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', null, null, null, null, null, null, '13575754343', null, '1', null, null, '2017-05-31 10:20:19', null, '2017-05-31 16:44:41', null, null);
INSERT INTO `arc_sys_user` VALUES ('15', '陈明', '17630060060', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', null, null, null, null, null, null, '17630060060', null, '1', null, null, '2017-05-31 10:20:47', null, '2017-07-01 15:17:20', null, null);
INSERT INTO `arc_sys_user` VALUES ('16', '无', '15068733003', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '222', null, null, null, null, null, '15068733003', null, '1', null, null, '2017-05-31 10:29:29', null, null, 'system', null);
INSERT INTO `arc_sys_user` VALUES ('17', '徐星', '15968892760', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', null, null, null, null, null, null, '15968892760', null, '1', null, null, '2017-05-31 11:02:48', null, null, null, null);
INSERT INTO `arc_sys_user` VALUES ('18', '测试110', '测试110', 'upagryh6ndgqe5znim3ih4iycqr5mtzqifs25ha', '110', null, 'null', null, null, '嘎嘎', '0571-2898968', '13598569632', '0', null, null, '2017-05-31 14:29:36', '管理员', '2017-06-01 09:38:22', 'system', '0');
INSERT INTO `arc_sys_user` VALUES ('19', '徐星', '1234', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', 'null', null, 'null', null, null, null, null, null, '0', null, null, '2017-06-28 15:59:30', '管理员', '2017-06-28 15:59:30', '管理员', null);
INSERT INTO `arc_sys_user` VALUES ('20', '无', '333', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '333', null, null, null, null, '无', '无', '15968892760', '0', null, null, '2017-06-28 16:10:49', '管理员', '2017-06-28 16:10:49', 'system', '无');
INSERT INTO `arc_sys_user` VALUES ('21', '徐星', '123', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '123', null, null, null, null, null, null, null, '1', null, null, '2017-07-01 15:17:40', '管理员', '2017-07-01 15:17:54', '管理员', null);

-- ----------------------------
-- Table structure for arc_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `arc_sys_user_role`;
CREATE TABLE `arc_sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NOT NULL COMMENT '角色主键',
  `user_id` int(11) NOT NULL COMMENT '用户主键',
  `level` tinyint(1) unsigned zerofill DEFAULT NULL COMMENT '级别',
  PRIMARY KEY (`id`),
  KEY `role_id_index` (`role_id`) USING BTREE,
  KEY `user_id_index` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of arc_sys_user_role
-- ----------------------------
INSERT INTO `arc_sys_user_role` VALUES ('3', '3', '3', null);
INSERT INTO `arc_sys_user_role` VALUES ('4', '4', '4', null);
INSERT INTO `arc_sys_user_role` VALUES ('5', '5', '5', null);
INSERT INTO `arc_sys_user_role` VALUES ('6', '6', '6', null);
INSERT INTO `arc_sys_user_role` VALUES ('7', '17', '7', null);
INSERT INTO `arc_sys_user_role` VALUES ('8', '18', '8', null);
INSERT INTO `arc_sys_user_role` VALUES ('15', '6', '9', null);
INSERT INTO `arc_sys_user_role` VALUES ('16', '3', '10', null);
INSERT INTO `arc_sys_user_role` VALUES ('17', '3', '11', null);
INSERT INTO `arc_sys_user_role` VALUES ('18', '1', '12', null);
INSERT INTO `arc_sys_user_role` VALUES ('19', '3', '13', null);
INSERT INTO `arc_sys_user_role` VALUES ('20', '3', '14', null);
INSERT INTO `arc_sys_user_role` VALUES ('21', '3', '15', null);
INSERT INTO `arc_sys_user_role` VALUES ('23', '3', '17', null);
INSERT INTO `arc_sys_user_role` VALUES ('33', '2', '2', null);
INSERT INTO `arc_sys_user_role` VALUES ('34', '4', '2', null);
INSERT INTO `arc_sys_user_role` VALUES ('35', '6', '18', null);
INSERT INTO `arc_sys_user_role` VALUES ('36', '2', '18', null);
INSERT INTO `arc_sys_user_role` VALUES ('37', '4', '18', null);
INSERT INTO `arc_sys_user_role` VALUES ('38', '1', '19', null);
INSERT INTO `arc_sys_user_role` VALUES ('44', '1', '20', null);
INSERT INTO `arc_sys_user_role` VALUES ('45', '3', '16', null);
INSERT INTO `arc_sys_user_role` VALUES ('46', '1', '21', null);
INSERT INTO `arc_sys_user_role` VALUES ('48', '1', '1', null);
INSERT INTO `arc_sys_user_role` VALUES ('49', '4', '1', null);
INSERT INTO `arc_sys_user_role` VALUES ('50', '18', '1', null);
INSERT INTO `arc_sys_user_role` VALUES ('51', '17', '1', null);
INSERT INTO `arc_sys_user_role` VALUES ('52', '2', '1', null);
INSERT INTO `arc_sys_user_role` VALUES ('53', '6', '1', null);
INSERT INTO `arc_sys_user_role` VALUES ('54', '5', '1', null);
INSERT INTO `arc_sys_user_role` VALUES ('55', '3', '1', null);

-- ----------------------------
-- Table structure for cl_app_session
-- ----------------------------
DROP TABLE IF EXISTS `cl_app_session`;
CREATE TABLE `cl_app_session` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` char(36) DEFAULT NULL,
  `refresh_token` char(36) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  `last_access_time` datetime DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `session` varchar(2000) DEFAULT NULL,
  `err_data` varchar(255) DEFAULT NULL,
  `login_type` tinyint(2) DEFAULT NULL,
  `login_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `token` (`token`) USING BTREE,
  KEY `fresh_token` (`refresh_token`) USING BTREE,
  KEY `customer_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cl_app_session
-- ----------------------------

-- ----------------------------
-- Table structure for cl_bank_card
-- ----------------------------
DROP TABLE IF EXISTS `cl_bank_card`;
CREATE TABLE `cl_bank_card` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户标识',
  `bank` varchar(30) DEFAULT '' COMMENT '开户行',
  `card_no` varchar(30) DEFAULT '' COMMENT '银行卡号',
  `phone` varchar(30) DEFAULT '' COMMENT '预留手机号',
  `agree_no` varchar(32) DEFAULT '' COMMENT '签约协议编号',
  `bind_time` datetime DEFAULT NULL COMMENT '绑卡时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银行卡信息';

-- ----------------------------
-- Records of cl_bank_card
-- ----------------------------

-- ----------------------------
-- Table structure for cl_borrow
-- ----------------------------
DROP TABLE IF EXISTS `cl_borrow`;
CREATE TABLE `cl_borrow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `order_no` varchar(30) NOT NULL DEFAULT '0' COMMENT '订单号',
  `amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '借款金额',
  `real_amount` decimal(10,2) DEFAULT '0.00' COMMENT '实际到账金额',
  `fee` decimal(10,2) DEFAULT '0.00' COMMENT '综合费用(借款利息+服务费+信息认证费)',
  `create_time` datetime NOT NULL COMMENT '订单生成时间',
  `time_limit` varchar(30) NOT NULL COMMENT '借款期限(天)',
  `state` varchar(3) NOT NULL DEFAULT '10' COMMENT '订单状态 10-审核中 20-自动审核成功  21自动审核不通过  22自动审核未决待人工复审 26人工复审通过 27人工复审不通过 30-待还款 40-已还款 41减免还款 50已逾期  36贷审核放款 37审核放款通过 38审核放款不通过',
  `card_id` bigint(30) NOT NULL DEFAULT '0' COMMENT '收款银行卡关联id',
  `service_fee` decimal(10,2) DEFAULT NULL COMMENT '服务费',
  `info_auth_fee` decimal(10,2) DEFAULT NULL COMMENT '信息认证费',
  `interest` decimal(10,2) DEFAULT NULL COMMENT '借款利息',
  `client` varchar(30) DEFAULT '10' COMMENT '客户端 默认10-移动app',
  `address` varchar(512) DEFAULT '' COMMENT '发起借款地址',
  `coordinate` varchar(64) DEFAULT '' COMMENT '借款经纬度坐标',
  `remark` varchar(64) DEFAULT '' COMMENT '备注、审核说明',
  `ip` varchar(64) DEFAULT '' COMMENT 'ip地址',
  `sign_service_id` varchar(512) DEFAULT '' COMMENT 'e签宝签署记录id',
  `original_id` bigint(20) DEFAULT NULL COMMENT '原订单id',
  `original_order_no` varchar(30) DEFAULT NULL COMMENT '原订单订单号',
  `renew_state` varchar(3) DEFAULT NULL COMMENT '续借状态',
  `renew_mark` int(2) DEFAULT '0' COMMENT '续借标记（0 表示原订单)',
  `renew_amount` decimal(10,0) DEFAULT '0' COMMENT '续借支付金额',
  `icloud_id` bigint(20) DEFAULT '0' COMMENT 'icloud账号id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_no` (`order_no`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='借款信息表';

-- ----------------------------
-- Records of cl_borrow
-- ----------------------------

-- ----------------------------
-- Table structure for cl_borrow_progress
-- ----------------------------
DROP TABLE IF EXISTS `cl_borrow_progress`;
CREATE TABLE `cl_borrow_progress` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT '0' COMMENT '关联用户id',
  `borrow_id` bigint(20) DEFAULT '0' COMMENT '借款信息id',
  `state` varchar(30) DEFAULT NULL COMMENT '借款进度状态 10申请成功待审核  20自动审核通过 21自动审核不通过  22自动审核未决待人工复审 26人工复审通过 27人工复审不通过    30放款成功  31放款失败   40还款成功    50逾期  90坏账 36待放款审核 37放款审核通过 38放款审核拒绝',
  `remark` varchar(256) DEFAULT NULL COMMENT '进度描述',
  `audit_remark` varchar(256) DEFAULT NULL COMMENT '审核备注',
  `audit_user` bigint(20) DEFAULT NULL COMMENT '审核人',
  `create_time` datetime DEFAULT NULL COMMENT '进度生成时间',
  `loan_time` datetime DEFAULT NULL COMMENT '放款日期',
  `repay_time` datetime DEFAULT NULL COMMENT '还款日期',
  PRIMARY KEY (`id`),
  KEY `borrow_id` (`borrow_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='借款进度';

-- ----------------------------
-- Records of cl_borrow_progress
-- ----------------------------

-- ----------------------------
-- Table structure for cl_borrow_repay
-- ----------------------------
DROP TABLE IF EXISTS `cl_borrow_repay`;
CREATE TABLE `cl_borrow_repay` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `borrow_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '借款订单id',
  `amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '还款金额',
  `repay_time` datetime NOT NULL COMMENT '应还款时间',
  `state` varchar(10) NOT NULL COMMENT '还款状态 10-已还款 20-未还款',
  `penalty_amout` decimal(20,2) DEFAULT '0.00' COMMENT '逾期罚金',
  `penalty_day` int(10) DEFAULT '0' COMMENT '逾期天数',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `borrow_id` (`borrow_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='还款计划';

-- ----------------------------
-- Records of cl_borrow_repay
-- ----------------------------

-- ----------------------------
-- Table structure for cl_borrow_repay_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_borrow_repay_log`;
CREATE TABLE `cl_borrow_repay_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `repay_id` bigint(20) DEFAULT '0' COMMENT '还款计划id',
  `borrow_id` bigint(20) DEFAULT '0' COMMENT '借款订单id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `amount` decimal(10,2) DEFAULT '0.00' COMMENT '实际还款金额',
  `penalty_day` int(10) DEFAULT '0' COMMENT '逾期天数',
  `penalty_amout` decimal(10,2) DEFAULT '0.00' COMMENT '逾期罚金',
  `repay_way` varchar(2) DEFAULT '' COMMENT '还款方式   10代扣，20银行卡转账，30支付宝转账',
  `repay_account` varchar(60) DEFAULT '' COMMENT '还款账号',
  `serial_number` varchar(60) DEFAULT '' COMMENT '还款流水号',
  `refund_deduction` decimal(10,2) DEFAULT '0.00' COMMENT '退还或补扣金额',
  `pay_time` datetime DEFAULT NULL COMMENT '退还或补扣支付时间',
  `repay_time` datetime DEFAULT NULL COMMENT '实际还款时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `repay_id` (`repay_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='还款记录';

-- ----------------------------
-- Records of cl_borrow_repay_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_channel
-- ----------------------------
DROP TABLE IF EXISTS `cl_channel`;
CREATE TABLE `cl_channel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `code` varchar(16) NOT NULL DEFAULT '' COMMENT '渠道代码',
  `name` varchar(16) NOT NULL DEFAULT '' COMMENT '渠道名称',
  `linker` varchar(16) DEFAULT '' COMMENT '联系人',
  `phone` varchar(16) DEFAULT '' COMMENT '联系电话',
  `type` varchar(2) DEFAULT '' COMMENT '渠道类型  (备用)',
  `state` varchar(2) NOT NULL DEFAULT '' COMMENT '状态 10：启用20：禁用',
  `create_time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='渠道信息表';

-- ----------------------------
-- Records of cl_channel
-- ----------------------------

-- ----------------------------
-- Table structure for cl_channel_app
-- ----------------------------
DROP TABLE IF EXISTS `cl_channel_app`;
CREATE TABLE `cl_channel_app` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `channel_id` bigint(20) NOT NULL COMMENT '渠道id',
  `app_type` varchar(16) DEFAULT '' COMMENT '应用类型 10 android 20 ios',
  `latest_version` varchar(16) DEFAULT '' COMMENT '最新版本',
  `min_version` varchar(16) DEFAULT '' COMMENT '最低支持版本',
  `download_url` varchar(64) DEFAULT '' COMMENT '下载地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cl_channel_app
-- ----------------------------

-- ----------------------------
-- Table structure for cl_operator_basic
-- ----------------------------
DROP TABLE IF EXISTS `cl_operator_basic`;
CREATE TABLE `cl_operator_basic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `basic_expenditure` decimal(15,0) DEFAULT NULL COMMENT '当月消费(单位为分)',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `extend_join_dt` datetime DEFAULT NULL COMMENT '入网时间',
  `basic_all_bonus` int(11) DEFAULT NULL COMMENT '累计积分（可以为0）',
  `extend_certifed_status` varchar(8) DEFAULT NULL COMMENT '实名认证状态',
  `basic_balance` decimal(15,0) DEFAULT NULL COMMENT '余额（单位为分）',
  `basic_phone_num` varchar(11) NOT NULL COMMENT '号码',
  `extend_belongto` varchar(16) DEFAULT NULL COMMENT '归属地',
  `extend_contact_addr` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `extend_phone_age` int(11) DEFAULT '0' COMMENT '网龄',
  `biz_no` varchar(128) DEFAULT NULL COMMENT '业务编号',
  `basic_user_name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商信息--基础信息';

-- ----------------------------
-- Records of cl_operator_basic
-- ----------------------------

-- ----------------------------
-- Table structure for cl_operator_bills
-- ----------------------------
DROP TABLE IF EXISTS `cl_operator_bills`;
CREATE TABLE `cl_operator_bills` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `phone_num` varchar(11) NOT NULL DEFAULT '' COMMENT '号码',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `month` datetime NOT NULL COMMENT '语音账单月份',
  `bill_month_date_start` datetime NOT NULL COMMENT '计费周期-起始日期',
  `bill_month_date_end` datetime NOT NULL COMMENT '计费周期-结束日期',
  `bill_month_amt` decimal(15,0) DEFAULT NULL COMMENT '本月费用总额（单位为分）',
  `biz_no` varchar(128) DEFAULT '' COMMENT '业务编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商信息--月账单';

-- ----------------------------
-- Records of cl_operator_bills
-- ----------------------------

-- ----------------------------
-- Table structure for cl_operator_req_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_operator_req_log`;
CREATE TABLE `cl_operator_req_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户标识',
  `order_no` varchar(50) DEFAULT NULL COMMENT '订单号',
  `resp_code` varchar(10) DEFAULT '' COMMENT '响应码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `resp_params` longtext COMMENT '同步响应结果',
  `resp_time` datetime DEFAULT NULL COMMENT '同步响应时间',
  `resp_order_no` varchar(64) DEFAULT '' COMMENT '同步响应订单号',
  `business_type` varchar(50) DEFAULT NULL COMMENT '业务类型，10运营商   20公积金   ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商认证中间表';

-- ----------------------------
-- Records of cl_operator_req_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_operator_resp_detail
-- ----------------------------
DROP TABLE IF EXISTS `cl_operator_resp_detail`;
CREATE TABLE `cl_operator_resp_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `req_log_id` bigint(20) NOT NULL COMMENT '请求记录标识',
  `order_no` varchar(50) DEFAULT '' COMMENT '订单号',
  `notify_params` longtext COMMENT '异步通知结果',
  `notify_time` datetime DEFAULT NULL COMMENT '异步通知时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商认证响应通知详情表';

-- ----------------------------
-- Records of cl_operator_resp_detail
-- ----------------------------

-- ----------------------------
-- Table structure for cl_operator_td_basic
-- ----------------------------
DROP TABLE IF EXISTS `cl_operator_td_basic`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='同盾运营商认证基本信息表';

-- ----------------------------
-- Records of cl_operator_td_basic
-- ----------------------------

-- ----------------------------
-- Table structure for cl_operator_td_bills
-- ----------------------------
DROP TABLE IF EXISTS `cl_operator_td_bills`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='同盾运营商账单信息';

-- ----------------------------
-- Records of cl_operator_td_bills
-- ----------------------------

-- ----------------------------
-- Table structure for cl_operator_td_call_info
-- ----------------------------
DROP TABLE IF EXISTS `cl_operator_td_call_info`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='同盾运营商通话记录详单';

-- ----------------------------
-- Records of cl_operator_td_call_info
-- ----------------------------

-- ----------------------------
-- Table structure for cl_operator_td_call_record
-- ----------------------------
DROP TABLE IF EXISTS `cl_operator_td_call_record`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='同盾运营商通话记录具体详细';

-- ----------------------------
-- Records of cl_operator_td_call_record
-- ----------------------------

-- ----------------------------
-- Table structure for cl_operator_td_sms_info
-- ----------------------------
DROP TABLE IF EXISTS `cl_operator_td_sms_info`;
CREATE TABLE `cl_operator_td_sms_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL,
  `req_log_id` bigint(20) DEFAULT NULL COMMENT '请求记录id',
  `order_no` varchar(50) DEFAULT NULL COMMENT '请求订单号',
  `total_msg_cost` varchar(32) DEFAULT NULL COMMENT '费用合计',
  `total_msg_count` varchar(32) DEFAULT NULL COMMENT '总短信次数',
  `msg_cycle` varchar(32) DEFAULT NULL COMMENT '短信周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='同盾运营商短信详单';

-- ----------------------------
-- Records of cl_operator_td_sms_info
-- ----------------------------

-- ----------------------------
-- Table structure for cl_operator_td_sms_record
-- ----------------------------
DROP TABLE IF EXISTS `cl_operator_td_sms_record`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='同盾运营商短信记录具体记录';

-- ----------------------------
-- Records of cl_operator_td_sms_record
-- ----------------------------

-- ----------------------------
-- Table structure for cl_operator_voices
-- ----------------------------
DROP TABLE IF EXISTS `cl_operator_voices`;
CREATE TABLE `cl_operator_voices` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `phone_num` varchar(11) DEFAULT '' COMMENT '号码',
  `voice_place` varchar(32) DEFAULT '' COMMENT '通话地',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `voice_duration` bigint(20) DEFAULT NULL COMMENT '通话时长（单位为秒）',
  `month` datetime DEFAULT NULL COMMENT '语音账单月份',
  `voice_type` varchar(32) DEFAULT '' COMMENT '通话类型',
  `voice_to_number` varchar(20) DEFAULT '' COMMENT '对方号码',
  `voice_date` datetime DEFAULT NULL COMMENT '时间',
  `voice_status` varchar(32) DEFAULT '' COMMENT '通话状态',
  `biz_no` varchar(128) DEFAULT NULL COMMENT '业务编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商信息--通话记录';

-- ----------------------------
-- Records of cl_operator_voices
-- ----------------------------

-- ----------------------------
-- Table structure for cl_opinion
-- ----------------------------
DROP TABLE IF EXISTS `cl_opinion`;
CREATE TABLE `cl_opinion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `opinion` varchar(160) DEFAULT '' COMMENT '意见',
  `sys_user_id` bigint(20) DEFAULT NULL COMMENT '管理员标识',
  `feedback` varchar(500) DEFAULT '' COMMENT '反馈',
  `state` varchar(2) DEFAULT '' COMMENT '状态 10待确认，20已确认',
  `create_time` datetime DEFAULT NULL COMMENT 'create_time',
  `confirm_time` datetime DEFAULT NULL COMMENT '确认时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='意见反馈';

-- ----------------------------
-- Records of cl_opinion
-- ----------------------------

-- ----------------------------
-- Table structure for cl_pay_check
-- ----------------------------
DROP TABLE IF EXISTS `cl_pay_check`;
CREATE TABLE `cl_pay_check` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `order_no` varchar(64) NOT NULL DEFAULT '' COMMENT '订单号',
  `order_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '订单金额',
  `real_pay_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '实际支付金额(第三方交易金额)',
  `type` varchar(2) NOT NULL DEFAULT '' COMMENT '错误类型 10:金额不匹配   20:我方单边账 30:支付方单边',
  `state` varchar(2) NOT NULL DEFAULT '' COMMENT '交易状态 10:成功 20:退款',
  `process_result` varchar(2) DEFAULT '' COMMENT '处理结果 10:待处理 20:已处理',
  `process_way` varchar(2) DEFAULT '' COMMENT '处理方式 10不处理 20补录 30补扣 40 退还',
  `process_time` datetime DEFAULT NULL COMMENT '处理时间',
  `remark` varchar(255) DEFAULT '' COMMENT '备注说明',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_no_unique` (`order_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cl_pay_check
-- ----------------------------

-- ----------------------------
-- Table structure for cl_pay_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_pay_log`;
CREATE TABLE `cl_pay_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) NOT NULL COMMENT '请求订单标识',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户标识',
  `borrow_id` bigint(20) DEFAULT '0' COMMENT '借款标识',
  `amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '支付金额',
  `card_no` varchar(20) NOT NULL DEFAULT '' COMMENT '用户银行卡卡号',
  `bank` varchar(30) NOT NULL DEFAULT '' COMMENT '用户银行卡开户行',
  `confirm_code` varchar(10) DEFAULT '' COMMENT '确认码，实时付款确认交易使用',
  `source` varchar(2) NOT NULL DEFAULT '' COMMENT '资金来源 10:自有资金 20:其他资金',
  `type` varchar(2) NOT NULL DEFAULT '' COMMENT '支付方式 10:代付 20:代扣 30:线下代付  40:线下代扣',
  `scenes` varchar(2) NOT NULL DEFAULT '' COMMENT '业务场景  10、放款  11、分润 12、退还 20、还款 21、补扣',
  `state` varchar(2) NOT NULL DEFAULT '' COMMENT '支付状态   10:待支付 、15:待审核 、20:审核通过、 30:审核不通过 、40:支付成功、50:支付失败',
  `remark` varchar(128) DEFAULT '' COMMENT '备注',
  `pay_req_time` datetime DEFAULT NULL COMMENT '支付请求时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `order_no` (`order_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付记录表';

-- ----------------------------
-- Records of cl_pay_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_pay_req_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_pay_req_log`;
CREATE TABLE `cl_pay_req_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `service` varchar(30) DEFAULT '' COMMENT '第三方接口名称',
  `order_no` varchar(64) DEFAULT '' COMMENT '商户订单编号',
  `params` mediumtext COMMENT '请求参数',
  `req_detail_params` mediumtext COMMENT '请求tpp参数拼接',
  `return_params` mediumtext COMMENT '页面返回/同步回调参数',
  `return_time` datetime DEFAULT NULL COMMENT '页面返回/同步回调时间',
  `notify_params` mediumtext COMMENT '后台通知/异步回调参数',
  `notify_time` datetime DEFAULT NULL COMMENT '后台通知/异步回调时间',
  `result` int(1) DEFAULT '0' COMMENT '响应结果：1成功，-1失败',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `ip` varchar(15) DEFAULT '' COMMENT '请求IP',
  PRIMARY KEY (`id`),
  UNIQUE KEY `reqLog_orderNo` (`order_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='请求记录表';

-- ----------------------------
-- Records of cl_pay_req_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_pay_resp_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_pay_resp_log`;
CREATE TABLE `cl_pay_resp_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) DEFAULT NULL COMMENT '商户订单编号',
  `type` tinyint(1) DEFAULT '0' COMMENT '通知类型   1、TPP同步返回  2、Tpp异步响应',
  `params` text COMMENT 'Tpp通知信息',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `respLog_orderNo` (`order_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='响应消息记录表';

-- ----------------------------
-- Records of cl_pay_resp_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_profit_agent
-- ----------------------------
DROP TABLE IF EXISTS `cl_profit_agent`;
CREATE TABLE `cl_profit_agent` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `level` int(11) NOT NULL DEFAULT '0' COMMENT '分润等级',
  `user_id` bigint(20) NOT NULL COMMENT '代理商id（用户id）',
  `rate` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '分润率',
  `create_time` datetime NOT NULL COMMENT '成为代理商时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `leader_id` bigint(20) DEFAULT '0' COMMENT '上级代理id',
  `apply_time` datetime DEFAULT NULL COMMENT '二级代理商升级为一级代理商时间',
  `old_rate` decimal(20,2) DEFAULT NULL COMMENT '二级代理商时的利润率',
  `is_use` int(11) DEFAULT '20' COMMENT '10-启用 20-禁用',
  PRIMARY KEY (`id`),
  KEY `agent_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理商用户信息表';

-- ----------------------------
-- Records of cl_profit_agent
-- ----------------------------

-- ----------------------------
-- Table structure for cl_profit_amount
-- ----------------------------
DROP TABLE IF EXISTS `cl_profit_amount`;
CREATE TABLE `cl_profit_amount` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `total` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '总金额',
  `no_cashed` decimal(20,2) DEFAULT '0.00' COMMENT '未提现',
  `cashed` decimal(20,0) DEFAULT '0' COMMENT '已提现',
  `state` varchar(2) NOT NULL DEFAULT '' COMMENT '账户状态 10-启用 20-冻结',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分润资金表';

-- ----------------------------
-- Records of cl_profit_amount
-- ----------------------------

-- ----------------------------
-- Table structure for cl_profit_cash_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_profit_cash_log`;
CREATE TABLE `cl_profit_cash_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '提现金额',
  `add_time` datetime DEFAULT NULL COMMENT '提现时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分润提现记录表';

-- ----------------------------
-- Records of cl_profit_cash_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_profit_level
-- ----------------------------
DROP TABLE IF EXISTS `cl_profit_level`;
CREATE TABLE `cl_profit_level` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `level` int(11) DEFAULT '0' COMMENT '分润等级',
  `rate` decimal(20,2) DEFAULT '0.00' COMMENT '分润率',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `remark` varchar(256) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分润等级表';

-- ----------------------------
-- Records of cl_profit_level
-- ----------------------------

-- ----------------------------
-- Table structure for cl_profit_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_profit_log`;
CREATE TABLE `cl_profit_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `borrow_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '借款id',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `agent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '分润人id',
  `amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '分润金额',
  `rate` decimal(20,2) DEFAULT '0.00' COMMENT '分润率',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `borrow_id` (`borrow_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分润记录表';

-- ----------------------------
-- Records of cl_profit_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_qiancheng_req_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_qiancheng_req_log`;
CREATE TABLE `cl_qiancheng_req_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) NOT NULL COMMENT '申请订单号',
  `borrow_id` bigint(20) NOT NULL COMMENT '借款标识',
  `user_id` bigint(20) NOT NULL COMMENT '用户标识',
  `state` varchar(4) NOT NULL DEFAULT '10' COMMENT '审核状态  10 已提交申请   20 审核通过  30 审核不通过',
  `create_time` datetime NOT NULL COMMENT '添加时间',
  `resp_code` varchar(10) DEFAULT NULL COMMENT '回调返回码',
  `update_time` datetime DEFAULT NULL COMMENT '回调更新时间',
  `resp_params` varchar(1024) DEFAULT '' COMMENT '同步响应结果',
  `notify_params` varchar(2048) DEFAULT '' COMMENT '异步通知结果',
  `resp_time` datetime DEFAULT NULL COMMENT '同步响应时间',
  `resp_order_no` varchar(64) DEFAULT '' COMMENT '同步响应订单号',
  `rs_state` varchar(32) DEFAULT '' COMMENT '审核结果',
  `rs_desc` varchar(512) DEFAULT '' COMMENT '审核结果描述',
  PRIMARY KEY (`id`),
  KEY `borrow_id` (`borrow_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `order_no` (`order_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='风控数据--浅橙请求结果';

-- ----------------------------
-- Records of cl_qiancheng_req_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_quartz_info
-- ----------------------------
DROP TABLE IF EXISTS `cl_quartz_info`;
CREATE TABLE `cl_quartz_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '定时任务名称',
  `code` varchar(64) NOT NULL DEFAULT '' COMMENT '定时任务code标识',
  `cycle` varchar(32) NOT NULL DEFAULT '' COMMENT '定时任务执行周期',
  `class_name` varchar(64) NOT NULL DEFAULT '' COMMENT '定时任务执行类',
  `succeed` int(11) NOT NULL DEFAULT '0' COMMENT '成功执行次数',
  `fail` int(11) NOT NULL DEFAULT '0' COMMENT '失败执行次数',
  `state` varchar(2) NOT NULL DEFAULT '' COMMENT '是否启用 10-启用 20-禁用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务信息';

-- ----------------------------
-- Records of cl_quartz_info
-- ----------------------------

-- ----------------------------
-- Table structure for cl_quartz_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_quartz_log`;
CREATE TABLE `cl_quartz_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `quartz_id` bigint(20) DEFAULT '0' COMMENT '定时任务id',
  `start_time` datetime DEFAULT NULL COMMENT '启动时间',
  `time` int(11) DEFAULT '0' COMMENT '任务用时',
  `result` varchar(2) DEFAULT '20' COMMENT '执行是否成功 10-成功 20-失败',
  `remark` varchar(128) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`id`),
  KEY `quartz_id` (`quartz_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务执行日志';

-- ----------------------------
-- Records of cl_quartz_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_rc_borrow_count
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_borrow_count`;
CREATE TABLE `cl_rc_borrow_count` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户标识',
  `count` int(11) DEFAULT '0' COMMENT '借款总次数',
  `fail_count` int(11) DEFAULT '0' COMMENT '借款不通过总次数',
  `day_fail_count` int(11) DEFAULT '0' COMMENT '当日借款不通过总次数',
  `count_one` int(11) DEFAULT '0' COMMENT '借款人有逾期未还借款数',
  `count_two` int(11) DEFAULT '0' COMMENT '借款人有逾期90天(M3)以上已还借款数',
  `count_three` int(11) DEFAULT '0' COMMENT '借款人有逾期30天(M1)以上,90天以内已还借款数',
  `count_four` int(11) DEFAULT '0' COMMENT '借款人有逾期30天(M1)以内已还借款数',
  `count_five` int(11) DEFAULT '0' COMMENT '紧急联系人有逾期未还借款数',
  `count_six` int(11) DEFAULT '0' COMMENT '紧急联系人有逾期90天(M3)以上已还借款数',
  `count_seven` int(11) DEFAULT '0' COMMENT '紧急联系人有逾期30天(M1)以上90天以内已还借款',
  `count_eight` int(11) DEFAULT '0' COMMENT '紧急联系人有逾期30天(M1)以内已还借款',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='风控数据-借款信息统计';

-- ----------------------------
-- Records of cl_rc_borrow_count
-- ----------------------------

-- ----------------------------
-- Table structure for cl_rc_contact_count
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_contact_count`;
CREATE TABLE `cl_rc_contact_count` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户标识',
  `count` int(11) DEFAULT '0' COMMENT '通讯录总条数',
  `count_one` int(11) DEFAULT '0' COMMENT '借款未逾期人数',
  `count_two` int(11) DEFAULT '0' COMMENT '逾期未还借款人数',
  `count_three` int(11) DEFAULT '0' COMMENT '逾期90天(M3)已还借款人数',
  `count_four` int(11) DEFAULT '0' COMMENT '逾期30天(M1)以上已还借款人数',
  `count_five` int(11) DEFAULT '0' COMMENT '逾期30天(M1)以内已还借款人数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='风控数据-通讯录信息统计';

-- ----------------------------
-- Records of cl_rc_contact_count
-- ----------------------------

-- ----------------------------
-- Table structure for cl_rc_ds_bad_info
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_ds_bad_info`;
CREATE TABLE `cl_rc_ds_bad_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户标识',
  `type` varchar(2) DEFAULT '' COMMENT '不良信息类型 ，10吸毒 20前科 30犯罪',
  `time` datetime DEFAULT NULL COMMENT '不良信息发生时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='风控数据-大圣不良信息统计';

-- ----------------------------
-- Records of cl_rc_ds_bad_info
-- ----------------------------

-- ----------------------------
-- Table structure for cl_rc_ds_cheat_info
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_ds_cheat_info`;
CREATE TABLE `cl_rc_ds_cheat_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户标识',
  `huadao_black` varchar(2) DEFAULT '' COMMENT '借款人华道黑名单命中情况 ，10未命中 20命中',
  `huadao_contact_black` varchar(2) DEFAULT '' COMMENT '紧急联系人华道黑名单命中情况 ，10未命中 20命中',
  `baiqishi_cheat` varchar(2) DEFAULT '' COMMENT '白骑士反欺诈审核情况 ，10通过 20拒绝 30人工复审',
  `baiqishi_high_risk` varchar(2) DEFAULT '' COMMENT '白骑士高风险命中情况 ，10未命中 20命中',
  `weishidun_overdue` varchar(2) DEFAULT '' COMMENT '维氏盾个人逾期命中情况 ，10未命中 20命中',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='风控数据-大圣反欺诈信息统计';

-- ----------------------------
-- Records of cl_rc_ds_cheat_info
-- ----------------------------

-- ----------------------------
-- Table structure for cl_rc_huadao_blacklist_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_huadao_blacklist_log`;
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

-- ----------------------------
-- Records of cl_rc_huadao_blacklist_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_rc_phone_call_count
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_phone_call_count`;
CREATE TABLE `cl_rc_phone_call_count` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户标识',
  `count` int(11) DEFAULT '0' COMMENT '通话总次数',
  `name_matching` varchar(2) DEFAULT '' COMMENT '姓名匹配 ，10一致 20不一致',
  `address_matching` varchar(2) DEFAULT '' COMMENT '最近90天通话地中无现居地址城市 ，10未命中 20命中',
  `month_source` int(11) DEFAULT '0' COMMENT '运营商月消费(分)',
  `count_one` int(11) DEFAULT '0' COMMENT '在网时长(月数)',
  `count_two` int(11) DEFAULT '0' COMMENT '紧急联系人在过去6个月通话记录中的最小次数',
  `count_three` int(11) DEFAULT '0' COMMENT '最近30天通话次数',
  `count_four` int(11) DEFAULT '0' COMMENT '最近30天主叫次数',
  `count_five` int(11) DEFAULT '0' COMMENT '最近30天被叫次数',
  `count_six` int(11) DEFAULT '0' COMMENT '最近30天通话时长(秒)',
  `count_seven` int(11) DEFAULT '0' COMMENT '最近30天主叫时长(秒)',
  `count_eight` int(11) DEFAULT '0' COMMENT '最近30天被叫时长(秒)',
  `count_nine` int(11) DEFAULT '0' COMMENT '最近90天通话次数',
  `count_ten` int(11) DEFAULT '0' COMMENT '最近90天主叫次数',
  `count_eleven` int(11) DEFAULT '0' COMMENT '最近90天被叫次数',
  `count_twelve` int(11) DEFAULT '0' COMMENT '最近90天通话时长(秒)',
  `count_thirteen` int(11) DEFAULT '0' COMMENT '最近90天主叫时长(秒)',
  `count_fourteen` int(11) DEFAULT '0' COMMENT '最近90天被叫时长(秒)',
  `count_fifteen` int(11) DEFAULT '0' COMMENT '最近90天联系号码个数',
  `count_sixteen` int(11) DEFAULT '0' COMMENT '最近90天主叫号码个数',
  `count_seventeen` int(11) DEFAULT '0' COMMENT '最近90天被叫号码个数',
  `count_eighteen` int(11) DEFAULT '0' COMMENT '最近90天联系次数超过5次的号码个数',
  `count_nineteen` int(11) DEFAULT '0' COMMENT '最近90天联系次数差超过3次，通话时长超过60秒的号码个数',
  `count_twenty` int(11) DEFAULT '0' COMMENT '最近90天联系最多的20个号码中借款未逾期人数',
  `count_twenty_one` int(11) DEFAULT '0' COMMENT '最近90天联系最多的20个号码中有逾期未还借款人数',
  `count_twenty_two` int(11) DEFAULT '0' COMMENT '最近90天联系最多的20个号码中有逾期90天(M3)已还借款人数',
  `count_twenty_three` int(11) DEFAULT '0' COMMENT '最近90天联系最多的20个号码中有逾期30天(M1)以上已还借款人数',
  `count_twenty_four` int(11) DEFAULT '0' COMMENT '最近90天联系最多的20个号码中有逾期30天(M1)以内已还借款人数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='风控数据-通话记录信息统计';

-- ----------------------------
-- Records of cl_rc_phone_call_count
-- ----------------------------

-- ----------------------------
-- Table structure for cl_rc_scene_business
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_scene_business`;
CREATE TABLE `cl_rc_scene_business` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `scene` varchar(32) DEFAULT '' COMMENT '场景',
  `business_id` int(11) DEFAULT '0' COMMENT '第三方征信接口主键',
  `get_way` varchar(2) DEFAULT '' COMMENT '获取方式 00，获取一次；10，每次获取；20，固定周期获取（单位为天）',
  `period` int(11) DEFAULT '0' COMMENT '周期，单位为天 当get_way为20时有效',
  `state` varchar(2) DEFAULT '' COMMENT '状态 10，启用；20，禁用',
  `type` varchar(255) NOT NULL COMMENT '类型  10 第三方接口  20 数据统计接口',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='场景与接口关系';

-- ----------------------------
-- Records of cl_rc_scene_business
-- ----------------------------

-- ----------------------------
-- Table structure for cl_rc_scene_business_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_scene_business_log`;
CREATE TABLE `cl_rc_scene_business_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `scene_id` bigint(20) NOT NULL COMMENT '场景关联ID',
  `borrow_id` bigint(20) NOT NULL COMMENT '借款申请Id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `tpp_id` int(11) NOT NULL DEFAULT '0' COMMENT '第三方主键ID',
  `business_id` bigint(20) NOT NULL COMMENT '接口Id',
  `nid` varchar(32) NOT NULL DEFAULT '' COMMENT '接口简称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `type` varchar(4) NOT NULL COMMENT '类型  10 第三方接口 20 数据统计接口',
  `rs_state` varchar(255) DEFAULT NULL COMMENT '返回状态',
  `rs_desc` varchar(255) DEFAULT NULL COMMENT '返回结果描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方征信接口信息';

-- ----------------------------
-- Records of cl_rc_scene_business_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_rc_simple_borrow_count
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_simple_borrow_count`;
CREATE TABLE `cl_rc_simple_borrow_count` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户标识',
  `count_one` int(11) DEFAULT '0' COMMENT '借款人逾期30天以上已还借款数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='风控数据统计-（简）借款统计';

-- ----------------------------
-- Records of cl_rc_simple_borrow_count
-- ----------------------------

-- ----------------------------
-- Table structure for cl_rc_simple_contact_count
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_simple_contact_count`;
CREATE TABLE `cl_rc_simple_contact_count` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户标识',
  `count_one` int(11) DEFAULT '0' COMMENT '通讯录总条数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='风控数据统计-（简）通讯录统计';

-- ----------------------------
-- Records of cl_rc_simple_contact_count
-- ----------------------------

-- ----------------------------
-- Table structure for cl_rc_simple_voices_count
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_simple_voices_count`;
CREATE TABLE `cl_rc_simple_voices_count` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户标识',
  `count_one` int(11) DEFAULT '0' COMMENT '通话记录总次数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='风控数据统计-（简）通话记录统计';

-- ----------------------------
-- Records of cl_rc_simple_voices_count
-- ----------------------------

-- ----------------------------
-- Table structure for cl_rc_statistics
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_statistics`;
CREATE TABLE `cl_rc_statistics` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT '' COMMENT '第三方名称',
  `nid` varchar(32) DEFAULT '' COMMENT '第三方简称',
  `extend` varchar(1024) DEFAULT '' COMMENT '第三方参数扩展字段',
  `state` varchar(10) DEFAULT '10' COMMENT '状态 10，启用；20，禁用',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据统计分类';

-- ----------------------------
-- Records of cl_rc_statistics
-- ----------------------------

-- ----------------------------
-- Table structure for cl_rc_statistics_business
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_statistics_business`;
CREATE TABLE `cl_rc_statistics_business` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `statistics_id` int(11) DEFAULT '0' COMMENT '第三方主键',
  `name` varchar(32) DEFAULT '' COMMENT '接口名称',
  `nid` varchar(32) DEFAULT '' COMMENT '接口简称',
  `state` varchar(10) DEFAULT '10' COMMENT '状态 10，启用；20，禁用',
  `extend` varchar(1024) DEFAULT '',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据统计分类接口';

-- ----------------------------
-- Records of cl_rc_statistics_business
-- ----------------------------

-- ----------------------------
-- Table structure for cl_rc_tpp
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_tpp`;
CREATE TABLE `cl_rc_tpp` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT '' COMMENT '第三方名称',
  `nid` varchar(32) DEFAULT '' COMMENT '第三方简称',
  `mer_no` varchar(128) DEFAULT '' COMMENT '商户号',
  `sign_type` varchar(8) DEFAULT '' COMMENT '加密方式',
  `key` varchar(2048) DEFAULT '' COMMENT '加密所需要的key',
  `extend` varchar(1024) DEFAULT '' COMMENT '第三方参数扩展字段',
  `state` varchar(2) DEFAULT '' COMMENT '状态 10，启用；20，禁用',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方征信信息';

-- ----------------------------
-- Records of cl_rc_tpp
-- ----------------------------

-- ----------------------------
-- Table structure for cl_rc_tpp_business
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_tpp_business`;
CREATE TABLE `cl_rc_tpp_business` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tpp_id` int(11) DEFAULT '0' COMMENT '第三方主键',
  `name` varchar(32) DEFAULT '' COMMENT '接口名称',
  `nid` varchar(32) DEFAULT '' COMMENT '接口简称',
  `state` varchar(2) DEFAULT '' COMMENT '状态 10，启用；20，禁用',
  `extend` varchar(1024) DEFAULT '',
  `url` varchar(256) DEFAULT '' COMMENT '接口请求地址',
  `test_url` varchar(256) DEFAULT '' COMMENT '测试地址',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方征信接口信息';

-- ----------------------------
-- Records of cl_rc_tpp_business
-- ----------------------------

-- ----------------------------
-- Table structure for cl_rc_zhima_anti_fraud
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_zhima_anti_fraud`;
CREATE TABLE `cl_rc_zhima_anti_fraud` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `ivs_score` varchar(30) DEFAULT NULL COMMENT 'ivs评分。取值区间为[0,100]，越高越好',
  `ivs_detail` text COMMENT '风险因素code与风险描述说明',
  `biz_no` varchar(100) DEFAULT NULL COMMENT '芝麻返回的业务号',
  `transaction_id` varchar(100) DEFAULT NULL COMMENT '请求标识',
  `create_time` datetime DEFAULT NULL COMMENT '请求时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='反欺诈';

-- ----------------------------
-- Records of cl_rc_zhima_anti_fraud
-- ----------------------------

-- ----------------------------
-- Table structure for cl_rc_zhima_industry
-- ----------------------------
DROP TABLE IF EXISTS `cl_rc_zhima_industry`;
CREATE TABLE `cl_rc_zhima_industry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `is_matched` varchar(30) DEFAULT NULL COMMENT '是否命中，10命中20未命中',
  `biz_no` varchar(100) DEFAULT NULL COMMENT '业务号',
  `details` text COMMENT '详情',
  `transaction_id` varchar(100) DEFAULT NULL COMMENT '请求标识',
  `create_time` datetime DEFAULT NULL COMMENT '请求时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='行业关注名单';

-- ----------------------------
-- Records of cl_rc_zhima_industry
-- ----------------------------

-- ----------------------------
-- Table structure for cl_sms
-- ----------------------------
DROP TABLE IF EXISTS `cl_sms`;
CREATE TABLE `cl_sms` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `content` varchar(200) DEFAULT NULL COMMENT '发送信息',
  `resp_time` datetime DEFAULT NULL COMMENT '响应时间',
  `resp` varchar(255) DEFAULT NULL COMMENT '响应信息',
  `sms_type` varchar(30) DEFAULT NULL COMMENT '短信类型',
  `code` varchar(6) DEFAULT '' COMMENT '验证码',
  `order_no` varchar(32) DEFAULT '' COMMENT '订单号',
  `state` varchar(2) DEFAULT '10' COMMENT '短信是否被使用 10-已使用 20-未使用',
  `verify_time` int(11) DEFAULT '0' COMMENT '短信验证次数',
  PRIMARY KEY (`id`),
  KEY `phone` (`phone`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信信息';

-- ----------------------------
-- Records of cl_sms
-- ----------------------------

-- ----------------------------
-- Table structure for cl_sms_tpl
-- ----------------------------
DROP TABLE IF EXISTS `cl_sms_tpl`;
CREATE TABLE `cl_sms_tpl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(30) DEFAULT NULL COMMENT '短信类型',
  `type_name` varchar(50) DEFAULT NULL COMMENT '类型名称',
  `tpl` varchar(255) DEFAULT NULL COMMENT '短信模板',
  `number` varchar(64) DEFAULT '' COMMENT '模板编号',
  `state` varchar(2) DEFAULT '20' COMMENT '短信模板状态 10 -启用 20 - 禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cl_sms_tpl
-- ----------------------------

-- ----------------------------
-- Table structure for cl_tongdun_req_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_tongdun_req_log`;
CREATE TABLE `cl_tongdun_req_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) DEFAULT NULL COMMENT '申请订单号',
  `borrow_id` bigint(20) DEFAULT NULL COMMENT '借款标识',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `state` varchar(4) DEFAULT '10' COMMENT '审核状态  10 已提交申请   20 审核通过  30 审核不通过',
  `create_time` datetime DEFAULT NULL COMMENT '提交审核报告时间',
  `submit_code` varchar(10) DEFAULT NULL COMMENT '获取审核报告返回码',
  `submit_params` mediumtext COMMENT '获取审核报告响应结果',
  `report_id` varchar(64) DEFAULT '' COMMENT '风险报告编码',
  `update_time` datetime DEFAULT NULL COMMENT '查询报告时间',
  `query_params` mediumtext COMMENT '查询报告响应结果',
  `query_code` varchar(16) DEFAULT '' COMMENT '查询报告返回码',
  `rs_state` varchar(16) DEFAULT '' COMMENT '风险结果    Accept:建议通过,Review:建议审核,Reject:建议拒绝',
  `rs_score` int(20) DEFAULT NULL COMMENT '风险分数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_no` (`order_no`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='同盾第三方请求记录';

-- ----------------------------
-- Records of cl_tongdun_req_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_urge_repay_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_urge_repay_log`;
CREATE TABLE `cl_urge_repay_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `due_id` bigint(20) DEFAULT NULL COMMENT '催收订单id',
  `borrow_id` bigint(20) DEFAULT NULL COMMENT '借款id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '催款人id',
  `state` varchar(2) DEFAULT NULL COMMENT '状态   20催收中;30承诺还款;40催收成功;50坏账',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注说明',
  `promise_time` datetime DEFAULT NULL COMMENT '承诺还款时间',
  `create_time` datetime DEFAULT NULL COMMENT '催收时间',
  `way` varchar(2) DEFAULT '50' COMMENT '催款方式   10 电话；20 邮件 ；30 短信；40现场沟通；50 其他',
  PRIMARY KEY (`id`),
  KEY `borrow_id` (`borrow_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='催款记录表';

-- ----------------------------
-- Records of cl_urge_repay_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_urge_repay_order
-- ----------------------------
DROP TABLE IF EXISTS `cl_urge_repay_order`;
CREATE TABLE `cl_urge_repay_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) DEFAULT '0' COMMENT '催收人员id',
  `user_name` varchar(20) DEFAULT NULL COMMENT '催款人姓名',
  `borrow_name` varchar(20) DEFAULT NULL COMMENT '借款人姓名',
  `phone` varchar(20) DEFAULT NULL COMMENT '借款人手机号',
  `borrow_id` bigint(20) DEFAULT '0' COMMENT '借款id',
  `order_no` varchar(255) DEFAULT NULL COMMENT '借款订单号',
  `amount` decimal(10,2) DEFAULT '0.00',
  `time_limit` varchar(30) DEFAULT NULL COMMENT '借款期限',
  `borrow_time` datetime DEFAULT NULL COMMENT '借款时间',
  `repay_time` datetime DEFAULT NULL COMMENT '应还款时间',
  `penalty_day` bigint(20) DEFAULT NULL COMMENT '逾期天数',
  `penalty_amout` decimal(10,2) DEFAULT '0.00',
  `state` varchar(2) DEFAULT '10' COMMENT '订单状态   10未分配;11待催收;20催收中;30承诺还款;40催收成功;50坏账',
  `count` bigint(20) DEFAULT '0' COMMENT '催款总次数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `success_time` datetime DEFAULT NULL COMMENT '催收成功日期',
  `level` varchar(2) DEFAULT 'M1' COMMENT '逾期等级  M1 (1-30天)  M2 (31-60天)  M3 (61以上)',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `borrow_id` (`borrow_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='催款计划表';

-- ----------------------------
-- Records of cl_urge_repay_order
-- ----------------------------

-- ----------------------------
-- Table structure for cl_user
-- ----------------------------
DROP TABLE IF EXISTS `cl_user`;
CREATE TABLE `cl_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(40) DEFAULT NULL,
  `login_name` varchar(30) DEFAULT NULL COMMENT '登录名',
  `login_pwd` varchar(50) DEFAULT NULL COMMENT '登录密码',
  `loginpwd_modify_time` datetime DEFAULT NULL COMMENT '上次登录密码修改时间',
  `regist_time` datetime DEFAULT NULL COMMENT '注册时间',
  `register_client` varchar(10) DEFAULT NULL COMMENT '注册客户端',
  `trade_pwd` varchar(50) DEFAULT NULL COMMENT '交易密码',
  `tradepwd_modify_time` datetime DEFAULT NULL COMMENT '上次交易密码修改时间',
  `invitation_code` varchar(10) DEFAULT NULL COMMENT '邀请码',
  `channel_id` bigint(20) DEFAULT NULL COMMENT '渠道',
  `level` varchar(2) DEFAULT '' COMMENT '代理等级 ，1一级，2二级，3普通用户',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`) USING BTREE,
  UNIQUE KEY `Invitation code` (`invitation_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户账户信息';

-- ----------------------------
-- Records of cl_user
-- ----------------------------

-- ----------------------------
-- Table structure for cl_user_auth
-- ----------------------------
DROP TABLE IF EXISTS `cl_user_auth`;
CREATE TABLE `cl_user_auth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `id_state` varchar(2) DEFAULT '' COMMENT '身份认证状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善',
  `contact_state` varchar(2) DEFAULT '' COMMENT '紧急联系人状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善',
  `bank_card_state` varchar(2) DEFAULT '' COMMENT '银行卡状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善',
  `phone_state` varchar(2) DEFAULT '' COMMENT '手机运营商认证状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善',
  `zhima_state` varchar(2) DEFAULT '' COMMENT '芝麻授信状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善',
  `acc_fund_state` varchar(2) DEFAULT '' COMMENT '公积金认证状态 ,10未认证/未完善，20认证中/完善中，30已认证/已完善',
  `work_info_state` varchar(2) DEFAULT '' COMMENT '工作信息状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善',
  `other_info_state` varchar(2) DEFAULT '' COMMENT '更多信息状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户认证状态';

-- ----------------------------
-- Records of cl_user_auth
-- ----------------------------

-- ----------------------------
-- Table structure for cl_user_base_info
-- ----------------------------
DROP TABLE IF EXISTS `cl_user_base_info`;
CREATE TABLE `cl_user_base_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '客户表 外键',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `real_name` varchar(30) DEFAULT NULL COMMENT '真实姓名',
  `age` int(11) DEFAULT '0' COMMENT '年龄 ',
  `sex` varchar(30) DEFAULT NULL COMMENT '性别',
  `national` varchar(30) DEFAULT NULL COMMENT '民族',
  `id_no` varchar(18) DEFAULT NULL COMMENT '证件号码',
  `id_addr` varchar(255) DEFAULT NULL COMMENT '身份证地址',
  `living_img` varchar(255) DEFAULT NULL COMMENT '自拍(人脸识别照片)',
  `ocr_img` varchar(255) DEFAULT NULL COMMENT '身份证头像',
  `front_img` varchar(255) DEFAULT NULL COMMENT '身份证正面',
  `back_img` varchar(255) DEFAULT NULL COMMENT '身份证反面',
  `education` varchar(30) DEFAULT NULL COMMENT '学历',
  `marry_state` varchar(30) DEFAULT NULL COMMENT '婚姻状况',
  `company_name` varchar(50) DEFAULT NULL COMMENT '公司名称',
  `company_phone` varchar(32) DEFAULT '' COMMENT '公司电话',
  `company_addr` varchar(255) DEFAULT NULL COMMENT '公司地址',
  `company_detail_addr` varchar(64) DEFAULT NULL COMMENT '公司详细地址',
  `company_coordinate` varchar(100) DEFAULT '' COMMENT '公司坐标(经度,纬度)',
  `salary` varchar(30) DEFAULT NULL COMMENT '月薪范围',
  `working_years` varchar(30) DEFAULT NULL COMMENT '工作年限',
  `working_img` varchar(512) DEFAULT NULL COMMENT '工作照片',
  `live_time` varchar(30) DEFAULT NULL COMMENT '居住时长',
  `live_addr` varchar(255) DEFAULT NULL COMMENT '居住地址',
  `live_detail_addr` varchar(255) DEFAULT NULL COMMENT '居住详细地址',
  `live_coordinate` varchar(100) DEFAULT NULL COMMENT '居住地坐标，(经度,纬度)',
  `phone_server_pwd` varchar(30) DEFAULT NULL COMMENT '运营商服务密码',
  `register_addr` varchar(255) DEFAULT NULL COMMENT '注册地址',
  `register_coordinate` varchar(100) DEFAULT NULL COMMENT '注册地坐标，(经度,纬度)',
  `state` varchar(30) DEFAULT '20' COMMENT '是否黑名单 ，10是20不是',
  `black_reason` varchar(255) DEFAULT '' COMMENT '拉黑原因',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`) USING BTREE,
  UNIQUE KEY `id_no` (`id_no`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户详情表';

-- ----------------------------
-- Records of cl_user_base_info
-- ----------------------------

-- ----------------------------
-- Table structure for cl_user_card_credit_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_user_card_credit_log`;
CREATE TABLE `cl_user_card_credit_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `req_params` mediumtext COMMENT '请求参数',
  `return_params` mediumtext COMMENT '响应参数',
  `confidence` varchar(30) DEFAULT NULL COMMENT '人脸匹配值',
  `result` varchar(10) DEFAULT NULL COMMENT '结果',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人脸识别请求记录';

-- ----------------------------
-- Records of cl_user_card_credit_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_user_contacts
-- ----------------------------
DROP TABLE IF EXISTS `cl_user_contacts`;
CREATE TABLE `cl_user_contacts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `name` varchar(20) DEFAULT '' COMMENT '姓名',
  `phone` varchar(20) DEFAULT '' COMMENT '手机号码',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户资料--通讯录';

-- ----------------------------
-- Records of cl_user_contacts
-- ----------------------------

-- ----------------------------
-- Table structure for cl_user_education_info
-- ----------------------------
DROP TABLE IF EXISTS `cl_user_education_info`;
CREATE TABLE `cl_user_education_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `education_type` varchar(32) DEFAULT '' COMMENT '教育类别',
  `profession` varchar(32) DEFAULT '' COMMENT '专业',
  `graduate_school` varchar(32) DEFAULT '' COMMENT '毕业学校',
  `matriculation_time` varchar(32) DEFAULT '' COMMENT '入学时间',
  `graduation_time` varchar(32) DEFAULT '' COMMENT '毕业时间',
  `graduation_conclusion` varchar(32) DEFAULT '' COMMENT '教育情况',
  `education_background` varchar(32) DEFAULT '' COMMENT '学位',
  `state` varchar(2) DEFAULT '20' COMMENT '是否匹配 10 - 匹配 20 - 不匹配',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学信查询记录表';

-- ----------------------------
-- Records of cl_user_education_info
-- ----------------------------

-- ----------------------------
-- Table structure for cl_user_emer_contacts
-- ----------------------------
DROP TABLE IF EXISTS `cl_user_emer_contacts`;
CREATE TABLE `cl_user_emer_contacts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系号码',
  `user_id` bigint(20) DEFAULT NULL COMMENT '客户表 外键',
  `relation` varchar(30) DEFAULT NULL COMMENT '与本人关系',
  `type` varchar(20) DEFAULT NULL COMMENT '是否直系',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='紧急联系人信息';

-- ----------------------------
-- Records of cl_user_emer_contacts
-- ----------------------------

-- ----------------------------
-- Table structure for cl_user_equipment_info
-- ----------------------------
DROP TABLE IF EXISTS `cl_user_equipment_info`;
CREATE TABLE `cl_user_equipment_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT '0' COMMENT '用户id',
  `black_box` varchar(2048) DEFAULT '' COMMENT '设备指纹',
  `operating_system` varchar(20) DEFAULT '' COMMENT '操作系统',
  `system_versions` varchar(20) DEFAULT '' COMMENT '系统版本',
  `phone_type` varchar(20) DEFAULT '' COMMENT '手机型号',
  `phone_brand` varchar(20) DEFAULT '' COMMENT '手机品牌',
  `phone_mark` varchar(100) DEFAULT NULL,
  `mac` varchar(20) DEFAULT '' COMMENT 'mac',
  `version_name` varchar(20) DEFAULT '' COMMENT '应用版本号',
  `version_code` varchar(20) DEFAULT '' COMMENT '应用build号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户设备信息表';

-- ----------------------------
-- Records of cl_user_equipment_info
-- ----------------------------

-- ----------------------------
-- Table structure for cl_user_invite
-- ----------------------------
DROP TABLE IF EXISTS `cl_user_invite`;
CREATE TABLE `cl_user_invite` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `invite_time` datetime DEFAULT NULL COMMENT '邀请时间',
  `invite_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '被邀请人id',
  `invite_name` varchar(32) NOT NULL DEFAULT '' COMMENT '被邀请人名称',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '邀请人id',
  `user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '邀请人名称',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邀请记录表';

-- ----------------------------
-- Records of cl_user_invite
-- ----------------------------

-- ----------------------------
-- Table structure for cl_user_messages
-- ----------------------------
DROP TABLE IF EXISTS `cl_user_messages`;
CREATE TABLE `cl_user_messages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `name` varchar(20) DEFAULT '' COMMENT '短信收发人',
  `phone` varchar(20) DEFAULT '' COMMENT '手机号码',
  `time` datetime DEFAULT NULL COMMENT '收发时间',
  `type` varchar(2) DEFAULT '' COMMENT '收发标识，10发20收',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户资料--短信';

-- ----------------------------
-- Records of cl_user_messages
-- ----------------------------

-- ----------------------------
-- Table structure for cl_user_other_info
-- ----------------------------
DROP TABLE IF EXISTS `cl_user_other_info`;
CREATE TABLE `cl_user_other_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `user_id` bigint(20) DEFAULT '0' COMMENT '用户标识(关联客户主键)',
  `taobao` varchar(32) DEFAULT '' COMMENT '淘宝账号',
  `email` varchar(32) DEFAULT '' COMMENT '常用邮箱',
  `qq` varchar(16) DEFAULT '' COMMENT 'qq账号',
  `wechat` varchar(32) DEFAULT '' COMMENT '微信账号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cl_user_other_info
-- ----------------------------

-- ----------------------------
-- Table structure for cl_user_sdk_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_user_sdk_log`;
CREATE TABLE `cl_user_sdk_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT '0' COMMENT '用户id',
  `time_type` varchar(20) DEFAULT '' COMMENT '识别类型',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sdk识别记录表';

-- ----------------------------
-- Records of cl_user_sdk_log
-- ----------------------------

-- ----------------------------
-- Table structure for cl_user_taojinyun_info
-- ----------------------------
DROP TABLE IF EXISTS `cl_user_taojinyun_info`;
CREATE TABLE `cl_user_taojinyun_info` (
  `id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `return_params` mediumtext,
  `create_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='抓取用户信息表';

-- ----------------------------
-- Records of cl_user_taojinyun_info
-- ----------------------------

-- ----------------------------
-- Table structure for cl_zhima
-- ----------------------------
DROP TABLE IF EXISTS `cl_zhima`;
CREATE TABLE `cl_zhima` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `open_id` varchar(100) DEFAULT '' COMMENT '用户在芝麻信用里面的唯一标识',
  `score` varchar(10) DEFAULT '' COMMENT '分数',
  `bind` varchar(2) DEFAULT '' COMMENT '是否已绑定 10 否 20 是',
  `bind_time` datetime DEFAULT NULL COMMENT '绑定时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='芝麻信用';

-- ----------------------------
-- Records of cl_zhima
-- ----------------------------
