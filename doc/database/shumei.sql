DROP TABLE IF EXISTS `cl_shumei_black_list`;
CREATE TABLE `cl_shumei_black_list` (
`id`  bigint(12) NOT  NULL AUTO_INCREMENT COMMENT ,
`user_id`  bigint(12) NULL DEFAULT NULL ,
`create_time`  datetime NULL DEFAULT NULL COMMENT '添加时间' ,
`in_black`  varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '命中黑名单' ,
`requestId`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求id' ,
`submit_params`  text DEFAULT  NULL COMMENT '返回data' ,
`itfin_loan_overdues`  int(9) NULL DEFAULT NULL COMMENT '该用户在多少不同网贷平台发生了逾期' ,
`itfin_loan_overdue_duration`  int(9) NULL DEFAULT NULL COMMENT '网贷最大逾期时长级别' ,
`itfin_loan_overdues_7d`  int(9) NULL DEFAULT NULL COMMENT '在最近 7 天中，该用户在多少 不同网贷平台发生了逾期' ,
`itfin_loan_overdue_duration_7d`  int(9) NULL DEFAULT NULL COMMENT '在最近 7 天中，网贷最大逾期 时长级别' ,
`itfin_loan_overdues_30d`  int(9) NULL DEFAULT NULL COMMENT '在最近 30 天中，该用户在多 少不同网贷平台发生了逾期' ,
`itfin_loan_overdue_duration_30d`  int(9) NULL DEFAULT NULL COMMENT '在最近 30 天中，网贷最大逾 期时长级别' ,
`itfin_loan_overdues_60d`  int(9) NULL DEFAULT NULL COMMENT '在最近 60 天中，该用户在多 少不同网贷平台发生了逾期' ,
`itfin_loan_overdue_duration_60d`  int(9) NULL DEFAULT NULL COMMENT '在最近 60 天中，网贷最大逾 期时长级别' ,
`itfin_loan_overdues_90d`  int(9) NULL DEFAULT NULL COMMENT '在最近 90 天中，该用户在多 少不同网贷平台发生了逾期' ,
`itfin_loan_overdue_duration_90d`  int(9) NULL DEFAULT NULL COMMENT '在最近 90 天中，网贷最大逾 期时长级别' ,
`itfin_loan_overdues_180d`  int(9) NULL DEFAULT NULL COMMENT '在最近 180 天中，该用户在多 少不同网贷平台发生了逾期' ,
`itfin_loan_overdue_duration_180d`  int(9) NULL DEFAULT NULL COMMENT '在最近 180 天中，网贷最大逾 期时长级别' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8  COMMENT='数美黑名单';

----字段注释修改
ALTER TABLE cl_shumei_black_list  MODIFY COLUMN itfin_loan_overdues_7d int(9) NULL DEFAULT NULL COMMENT '近7天发生逾期多少不同网贷平台';
ALTER TABLE cl_shumei_black_list  MODIFY COLUMN itfin_loan_overdue_duration_7d int(9) NULL DEFAULT NULL COMMENT '近7天网贷最大逾期时长级别';
ALTER TABLE cl_shumei_black_list  MODIFY COLUMN itfin_loan_overdues_30d int(9) NULL DEFAULT NULL COMMENT '近30天发生逾期多少不同网贷平台';
ALTER TABLE cl_shumei_black_list  MODIFY COLUMN itfin_loan_overdue_duration_30d int(9) NULL DEFAULT NULL COMMENT '近30天网贷最大逾期时长级别';
ALTER TABLE cl_shumei_black_list  MODIFY COLUMN itfin_loan_overdues_60d int(9) NULL DEFAULT NULL COMMENT '近60天发生逾期多少不同网贷平台';
ALTER TABLE cl_shumei_black_list  MODIFY COLUMN itfin_loan_overdue_duration_60d int(9) NULL DEFAULT NULL COMMENT '近60天网贷最大逾期时长级别';
ALTER TABLE cl_shumei_black_list  MODIFY COLUMN itfin_loan_overdues_90d int(9) NULL DEFAULT NULL COMMENT '近90天发生逾期多少不同网贷平台';
ALTER TABLE cl_shumei_black_list  MODIFY COLUMN itfin_loan_overdue_duration_90d int(9) NULL DEFAULT NULL COMMENT '近90天网贷最大逾期时长级别';
ALTER TABLE cl_shumei_black_list  MODIFY COLUMN itfin_loan_overdues_180d int(9) NULL DEFAULT NULL COMMENT '近180天发生逾期多少不同网贷平台';
ALTER TABLE cl_shumei_black_list  MODIFY COLUMN itfin_loan_overdue_duration_180d int(9) NULL DEFAULT NULL COMMENT '近180天网贷最大逾期时长级别';

