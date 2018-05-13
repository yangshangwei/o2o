--
-- Table structure for table `tb_area`
--
DROP TABLE IF EXISTS `tb_area`;

CREATE TABLE `tb_area` (
	`area_id` INT (5) NOT NULL AUTO_INCREMENT,
	`area_name` VARCHAR (200) NOT NULL,
	`area_desc` VARCHAR (1000) DEFAULT NULL,
	`priority` INT (2) NOT NULL DEFAULT '0',
	`create_time` datetime DEFAULT NULL,
	`last_edit_time` datetime DEFAULT NULL,
	PRIMARY KEY (`area_id`),
	UNIQUE KEY `UK_AREA` (`area_name`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;


--
-- Table structure for table `tb_person_info`
--

DROP TABLE IF EXISTS `tb_person_info`;

CREATE TABLE `tb_person_info` (
	`user_id` INT (10) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR (32) DEFAULT NULL,
	`profile_img` VARCHAR (1024) DEFAULT NULL,
	`email` VARCHAR (128) DEFAULT NULL,
	`gender` VARCHAR (2) DEFAULT NULL,
	`enable_status` INT (2) NOT NULL DEFAULT '0' COMMENT '0:禁止使用, 1:允许使用',
	`user_type` INT (2) NOT NULL DEFAULT '1' COMMENT '1:顾客,2:店家,3:管理员',
	`create_time` datetime DEFAULT NULL,
	`last_edit_time` datetime DEFAULT NULL,
	PRIMARY KEY (`user_id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;