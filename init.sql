/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.7.20-log : Database - ctb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ctb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;

USE `ctb`;

/*Table structure for table `r_crud_column` */

DROP TABLE IF EXISTS `r_crud_column`;

CREATE TABLE `r_crud_column` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `keyword` varchar(50) DEFAULT NULL COMMENT '关键字',
  `column_name` varchar(50) DEFAULT NULL COMMENT '列名',
  `column_type` int(2) DEFAULT NULL COMMENT '控件类型',
  `column_id` varchar(100) DEFAULT NULL COMMENT '列ID',
  `dict_info` varchar(500) DEFAULT NULL COMMENT '字典值',
  `required` int(1) DEFAULT NULL COMMENT '是否必输 1-必输 0-非必输',
  `data_format` varchar(50) DEFAULT NULL COMMENT '类似：number,email,phone，mobile等',
  `regular_express` varchar(200) DEFAULT NULL COMMENT '正则表达式',
  `maxlength` int(5) DEFAULT NULL COMMENT '最大长度',
  `style_express` varchar(200) DEFAULT NULL COMMENT '样式表达式',
  `sort` int(3) DEFAULT NULL COMMENT '排序',
  `add_flag` int(1) DEFAULT NULL COMMENT '新增列 1-是 0-不是',
  `edit_flag` int(1) DEFAULT NULL COMMENT '修改列 1-是 0-不是',
  `list_flag` int(1) DEFAULT NULL COMMENT '列表列 1-是 0-不是',
  `query_flag` int(1) DEFAULT NULL COMMENT '检索条件列 1-是 0-不是',
  `query_format` varchar(100) DEFAULT NULL COMMENT '列表展示数据格式化（针对日期型等）',
  `href_column` varchar(100) DEFAULT NULL COMMENT '列表列超链接',
  `detail_flag` int(1) DEFAULT NULL COMMENT '详情列 1-是 0-不是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `r_dict_info` */

DROP TABLE IF EXISTS `r_dict_info`;

CREATE TABLE `r_dict_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `dict_type` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '字典类型',
  `dict_code` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '字典代码',
  `dict_value` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '字典值',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_r_dict_type_code` (`dict_type`,`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='字典表';

/*Table structure for table `r_report` */

DROP TABLE IF EXISTS `r_report`;

CREATE TABLE `r_report` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `keyword` varchar(50) DEFAULT NULL COMMENT '关键字',
  `visit_key` varchar(50) DEFAULT NULL COMMENT 'url访问关键字',
  `name` varchar(40) DEFAULT NULL COMMENT '报表名称',
  `state` int(1) DEFAULT '1' COMMENT '状态 1有效 0 无效',
  `from` int(1) DEFAULT '1' COMMENT '1:mysql 2:hive',
  `export_flag` int(1) DEFAULT '0' COMMENT '导出功能：1开通 0关闭',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Table structure for table `r_report_chart` */

DROP TABLE IF EXISTS `r_report_chart`;

CREATE TABLE `r_report_chart` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `keyword` varchar(50) DEFAULT NULL COMMENT '关键字',
  `name` varchar(50) DEFAULT NULL COMMENT '图表名',
  `chart_x` varchar(100) DEFAULT NULL COMMENT 'X轴字段',
  `chart_y` varchar(100) DEFAULT NULL COMMENT 'Y轴字段',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `r_report_sql` */

DROP TABLE IF EXISTS `r_report_sql`;

CREATE TABLE `r_report_sql` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sql_code` varchar(50) NOT NULL COMMENT '任务code',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `sql` varchar(4000) NOT NULL COMMENT 'sql',
  `data_source` varchar(20) DEFAULT NULL COMMENT '数据源',
  `show_column` varchar(1000) DEFAULT NULL COMMENT '展示列名',
  `param` varchar(1000) DEFAULT NULL COMMENT '查询条件',
  `page_size` int(10) DEFAULT '10' COMMENT '字典值',
  `result_type` int(1) DEFAULT '1' COMMENT '1：查询结果list 2：查询结果number',
  `state` int(1) DEFAULT NULL COMMENT '1：正常 2：删除',
  `notice_email` varchar(400) DEFAULT NULL COMMENT '通知邮箱',
  `schedule_plan` varchar(40) DEFAULT NULL COMMENT '调度计划',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Table structure for table `r_user` */

DROP TABLE IF EXISTS `r_user`;

CREATE TABLE `r_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(11) NOT NULL COMMENT '登录账号',
  `nick` varchar(64) DEFAULT NULL COMMENT '昵称',
  `login_pwd` varchar(64) DEFAULT NULL COMMENT '密码',
  `head_image` varchar(256) DEFAULT NULL COMMENT '头像地址',
  `sex` int(2) DEFAULT NULL COMMENT '性别：1男 0女',
  `qq` varchar(12) DEFAULT NULL COMMENT 'QQ',
  `email` varchar(100) DEFAULT NULL COMMENT 'email',
  `type` int(1) DEFAULT NULL COMMENT '用户类型 1：普通用户 2：vip',
  `register_ip` varchar(20) DEFAULT NULL COMMENT '注册ip',
  `is_verify` int(1) DEFAULT '0' COMMENT '是否已邮箱验证 1：是 0 否',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8mb4;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
