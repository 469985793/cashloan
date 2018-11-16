ALTER TABLE  cl_fraud_td_hit_list
ADD COLUMN `frequency_detail_mob_idcard_cnt_90d` INTEGER (9) DEFAULT  NULL COMMENT '3个月身份证关联手机号数';

ALTER TABLE  cl_fraud_td_hit_list
ADD COLUMN `frequency_detail_email_idcard_cnt_90d` INTEGER (9) DEFAULT  NULL COMMENT '3个月身份证关联邮箱数';

ALTER TABLE  cl_fraud_td_hit_list
ADD COLUMN `frequency_detail_homeaddr_idcard_cnt_90d` INTEGER (9) DEFAULT  NULL COMMENT '3个月身份证关联家庭地址数';

ALTER TABLE  cl_fraud_td_hit_list
ADD COLUMN `frequency_detail_device_idcard_cnt_1d` INTEGER (9) DEFAULT  NULL COMMENT '1天内身份证关联设备数';

ALTER TABLE  cl_fraud_td_hit_list
ADD COLUMN `frequency_detail_device_idcard_cnt_30d` INTEGER (9) DEFAULT  NULL COMMENT '1个月内身份证关联设备数';

ALTER TABLE  cl_fraud_td_hit_list
ADD COLUMN `frequency_detail_device_idcard_cnt_7d` INTEGER (9) DEFAULT  NULL COMMENT '7天内身份证关联设备数';

ALTER TABLE  cl_fraud_td_hit_list
ADD COLUMN `frequency_detail_workaddr_idcard_cnt_90d` INTEGER (9) DEFAULT  NULL COMMENT '3个月内借款人身份证关联工作单位地址个数';

ALTER TABLE  cl_fraud_td_hit_list
ADD COLUMN `mob_cnt_hit_black_list` INTEGER (9) DEFAULT  NULL COMMENT '手机号命中信贷逾期黑名单次数';

ALTER TABLE  cl_fraud_td_hit_list
ADD COLUMN `idcard_cnt_hit_black_list` INTEGER (9) DEFAULT  NULL COMMENT '身份证命中信贷逾期黑名单次数';


ALTER TABLE  cl_fraud_td_multi_platform
ADD COLUMN `multi_platform_idcard_cnt_7d` INTEGER (9) DEFAULT  NULL COMMENT '7天内同一申请人身份证申请借款次数';


ALTER TABLE  cl_fraud_td_multi_platform
ADD COLUMN `multi_platform_mob_cnt_7d` INTEGER (9) DEFAULT  NULL COMMENT '7天内同一申请人手机号申请借款次数';


ALTER TABLE  cl_fraud_td_multi_platform
ADD COLUMN `multi_platform_device_cnt_7d` INTEGER (9) DEFAULT  NULL COMMENT '7天内同一申请人设备ID申请借款次数';




