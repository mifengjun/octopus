/*
Navicat MySQL OctopusData Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : octopus

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2020-05-19 23:24:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '评论id',
  `movie_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '影片id',
  `comment_people` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '评论人',
  `comment` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `comment_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '评论日期',
  `comment_rating` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '评论星级',
  `source` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '评论来源',
  `data_time` datetime DEFAULT NULL COMMENT '数据时间',
  `valuable` int(11) DEFAULT NULL COMMENT '点击有用数',
  `worthless` int(11) DEFAULT NULL COMMENT '点击没用数',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='评论表';

-- ----------------------------
-- Table structure for custom_code
-- ----------------------------
DROP TABLE IF EXISTS `custom_code`;
CREATE TABLE `custom_code` (
  `code` varchar(5) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '码表code',
  `name` varchar(5) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '码表name',
  `purpose` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '码值用途'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='星级码表';

-- ----------------------------
-- Table structure for movie
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `movie_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '影片id',
  `movie_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '影片名称',
  `initial_release_date` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '影片年份',
  `movie_time` int(11) DEFAULT NULL COMMENT '影片时长',
  `producer_country` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '制片国家/地区',
  `source` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '影片来源',
  `data_time` datetime DEFAULT NULL COMMENT '数据时间',
  UNIQUE KEY `primarykey` (`movie_id`,`source`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='电影信息表';
