ALTER TABLE `arc_borrow_rule_result`
ADD INDEX `borrow_id` (`borrow_id`) USING BTREE ;

ALTER TABLE `arc_cr_factor_param`
ADD INDEX `factor_id` (`factor_id`) USING BTREE ;

ALTER TABLE `arc_cr_result`
ADD INDEX `consumer_no` (`consumer_no`) USING BTREE ;

ALTER TABLE `arc_cr_result_detail`
ADD INDEX `result_id` (`result_id`) USING BTREE ;

ALTER TABLE `arc_credit`
ADD UNIQUE INDEX `consumer_no` (`consumer_no`) USING BTREE ;

ALTER TABLE `arc_credit_log`
ADD INDEX `consumer_no` (`consumer_no`) USING BTREE ;

ALTER TABLE `cl_borrow`
ADD INDEX `user_id` (`user_id`) USING BTREE ,
ADD UNIQUE INDEX `order_no` (`order_no`) USING BTREE ;

ALTER TABLE `cl_borrow_repay`
ADD INDEX `borrow_id` (`borrow_id`) USING BTREE ;

ALTER TABLE `cl_borrow_repay_log`
ADD INDEX `repay_id` (`repay_id`) USING BTREE ,
ADD INDEX `borrow_id` (`borrow_id`) USING BTREE ;

ALTER TABLE `cl_operator_voices` 
ADD INDEX `user_id` (`user_id`) USING BTREE,
ADD INDEX `phone_num` (`phone_num`) USING BTREE ;

ALTER TABLE `cl_pay_log`
ADD INDEX `order_no` (`order_no`) USING BTREE ;

ALTER TABLE `cl_profit_amount`
ADD UNIQUE INDEX `user_id` (`user_id`) USING BTREE ;

ALTER TABLE `cl_profit_cash_log`
ADD INDEX `user_id` (`user_id`) USING BTREE ;

ALTER TABLE `cl_profit_log`
ADD INDEX `user_id` (`user_id`) USING BTREE ,
ADD INDEX `borrow_id` (`borrow_id`) USING BTREE ;

ALTER TABLE `cl_qiancheng_req_log`
ADD INDEX `borrow_id` (`borrow_id`) USING BTREE ,
ADD INDEX `user_id` (`user_id`) USING BTREE ,
ADD INDEX `order_no` (`order_no`) USING BTREE ;

-- ALTER TABLE `cl_sms`
-- ADD INDEX `phone` (`phone`) USING BTREE;

ALTER TABLE `cl_tongdun_req_log`
ADD INDEX `user_id` (`user_id`) USING BTREE ,
ADD UNIQUE INDEX `order_no` (`order_no`) USING BTREE ;

-- ALTER TABLE `cl_user`
-- ADD UNIQUE INDEX `login_name` (`login_name`) USING BTREE,
-- ADD UNIQUE INDEX `invitation_code` (`invitation_code`) USING BTREE;

ALTER TABLE `cl_user_base_info`
-- ADD UNIQUE INDEX `user_id` (`user_id`) USING BTREE,
ADD UNIQUE INDEX `phone` (`phone`) USING BTREE,
ADD UNIQUE INDEX `id_no` (`id_no`) USING BTREE;

ALTER TABLE `cl_urge_repay_log`
ADD INDEX `borrow_id` (`borrow_id`) USING BTREE ,
ADD INDEX `user_id` (`user_id`) USING BTREE ;

ALTER TABLE `cl_urge_repay_order`
ADD INDEX `user_id` (`user_id`) USING BTREE ,
ADD INDEX `borrow_id` (`borrow_id`) USING BTREE ;

ALTER TABLE `cl_user_card_credit_log`
ADD INDEX `user_id` (`user_id`) USING BTREE ;

ALTER TABLE `cl_user_contacts`
ADD INDEX `user_id` (`user_id`) USING BTREE ;

ALTER TABLE `cl_user_equipment_info`
ADD UNIQUE INDEX `user_id` (`user_id`) USING BTREE ;

ALTER TABLE `cl_user_messages`
ADD INDEX `user_id` (`user_id`) USING BTREE ;

ALTER TABLE `cl_user_other_info`
ADD UNIQUE INDEX `user_id` (`user_id`) USING BTREE ;

ALTER TABLE `cl_user_invite`
ADD INDEX `user_id` (`user_id`) USING BTREE ;

