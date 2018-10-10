/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : 127.0.0.1:3306
 Source Schema         : test1

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 10/10/2018 11:28:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for relationInfo
-- ----------------------------
DROP TABLE IF EXISTS `relationInfo`;
CREATE TABLE `relationInfo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `entityClass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mapperClass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relationInfo
-- ----------------------------
INSERT INTO `relationInfo` VALUES (1, '我-0', 'com.neo.entity.po.CustomerInfo', 'com.neo.mapper.test1.CustomerInfoMapper', NULL);

-- ----------------------------
-- Table structure for taskInfo
-- ----------------------------
DROP TABLE IF EXISTS `taskInfo`;
CREATE TABLE `taskInfo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fileName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sheetIndex` int(11) NULL DEFAULT NULL,
  `startRnum` int(11) NULL DEFAULT NULL,
  `endRnum` int(11) NULL DEFAULT NULL,
  `finished` int(11) NULL DEFAULT NULL,
  `startDate` datetime(0) NULL DEFAULT NULL,
  `endDate` datetime(0) NULL DEFAULT NULL,
  `errorInfo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pass_word` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (2, 'aa', 'a123456', 'MAN', NULL);
INSERT INTO `users` VALUES (3, 'bb', 'b123456', 'WOMAN', NULL);
INSERT INTO `users` VALUES (4, 'cc', 'b123456', 'WOMAN', NULL);

-- ----------------------------
-- Table structure for 客户信息
-- ----------------------------
DROP TABLE IF EXISTS `客户信息`;
CREATE TABLE `客户信息`  (
  `名字` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `体重` double(10, 2) NULL DEFAULT NULL,
  `年龄` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
