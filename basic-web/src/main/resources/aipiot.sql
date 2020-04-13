#
# Structure for table "aip_saas_user"
#

CREATE TABLE `aip_saas_user`
(
  `id`                       int(11)     NOT NULL AUTO_INCREMENT,
  `aip_saas_code`            varchar(50) NOT NULL DEFAULT '' COMMENT '唯一code，全局唯一',
  `name`                     varchar(50)          DEFAULT NULL COMMENT '登录帐号全局唯一',
  `password`                 varchar(255)         DEFAULT NULL COMMENT '登录密码',
  `phone`                    varchar(50)          DEFAULT NULL,
  `state`                    varchar(50)          DEFAULT NULL COMMENT '预留',
  `is_enable`                int(11)              DEFAULT '0' COMMENT '是否启用0 启用；1禁用',
  `account_type`             int(11)              DEFAULT '0' COMMENT '帐号类型：0是主账号，1是子账号。默认是主账号',
  `parent_account_saas_code` varchar(50)          DEFAULT NULL COMMENT '子账号才有的字段，所属父帐号id',
  `remark`                   varchar(255)         DEFAULT NULL COMMENT '备注信息',
  `create_time`              datetime             DEFAULT NULL,
  `create_user`              varchar(40)          DEFAULT NULL,
  `last_modify_time`         datetime             DEFAULT NULL,
  `last_modify_user`         varchar(40)          DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='帐号表';

INSERT INTO `aip_saas_user` (`id`, `aip_saas_code`, `name`, `password`, `phone`, `state`, `is_enable`, `account_type`,
                             `parent_account_saas_code`, `remark`, `create_time`, `create_user`, `last_modify_time`,
                             `last_modify_user`)
VALUES (1, '2088970626725445', 'caojing', '8e5134a239fbc69e1ba6450b3c777c18', NULL, 'COMMON', 0, 0, NULL, NULL,
        '2019-08-13 10:40:40', 'iot', '2019-08-13 10:40:40', 'iot');