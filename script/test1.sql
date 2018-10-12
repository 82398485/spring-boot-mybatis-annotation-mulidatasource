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

 Date: 12/10/2018 15:44:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for relationInfo
-- ----------------------------
DROP TABLE IF EXISTS `relationInfo`;
CREATE TABLE `relationInfo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `filePattern` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sheetIndex` int(11) NULL DEFAULT NULL,
  `minStartRnum` int(11) NULL DEFAULT NULL,
  `batchCount` int(255) NULL DEFAULT NULL,
  `entityClass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mapperClass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relationInfo
-- ----------------------------
INSERT INTO `relationInfo` VALUES (1, 'temp.*', 0, 3, 2000, 'com.neo.entity.po.CustomerInfo', 'com.neo.mapper.test1.CustomerInfoMapper', NULL);
INSERT INTO `relationInfo` VALUES (2, 't1emp.*', 0, 4, 100, 'com.neo.entity.po.CustomerInfo', 'com.neo.mapper.test1.CustomerInfoMapper', NULL);

-- ----------------------------
-- Table structure for sourceInfo
-- ----------------------------
DROP TABLE IF EXISTS `sourceInfo`;
CREATE TABLE `sourceInfo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `filePattern` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createDate` datetime(0) NULL DEFAULT NULL,
  `lastDate` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sourceInfo
-- ----------------------------
INSERT INTO `sourceInfo` VALUES (1, './src/main/resources/', 'temp.*.xls', '2018-10-11 11:29:26', NULL);
INSERT INTO `sourceInfo` VALUES (2, './src/main/resources/', 'temp1.*.xls', '2018-10-11 11:29:26', NULL);

-- ----------------------------
-- Table structure for subTaskInfo
-- ----------------------------
DROP TABLE IF EXISTS `subTaskInfo`;
CREATE TABLE `subTaskInfo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `filePath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sheetIndex` int(11) NULL DEFAULT NULL,
  `startRnum` int(11) NULL DEFAULT NULL,
  `endRnum` int(11) NULL DEFAULT NULL,
  `finished` int(11) NULL DEFAULT NULL,
  `startDate` datetime(0) NULL DEFAULT NULL,
  `endDate` datetime(0) NULL DEFAULT NULL,
  `errorInfo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2310 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `a` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `b` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `c` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('1', '2', '2018-10-11 11:04:50');

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
