/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : testboot

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 11/11/2019 10:38:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trigger_id` int(11) DEFAULT NULL,
  `job_id` int(11) DEFAULT NULL,
  `job_type` int(2) DEFAULT NULL COMMENT '1.java 2.脚本 3. sql脚本 4.其他',
  `job_status` int(2) DEFAULT NULL COMMENT '1.运行 2.作废',
  `run_status` int(2) DEFAULT NULL COMMENT '1.运行成功2.失败',
  `job_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `job_desc` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `group` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `cron` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `clazz` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `sqlText` longtext COLLATE utf8_bin,
  `scriptText` longtext COLLATE utf8_bin,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;
