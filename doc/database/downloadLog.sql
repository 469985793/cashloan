CREATE TABLE `arc_sys_download_log` (
`id`  bigint(12) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
`user_name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`create_time`  datetime NULL DEFAULT NULL COMMENT '添加时间' ,
`download_menu`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下载菜单' ,
`download_count` INT (9) DEFAULT NULL COMMENT '下载条数',
  PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8  COMMENT='后台下载日志统计';
