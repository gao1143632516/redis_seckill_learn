/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50645
 Source Host           : localhost:3306
 Source Schema         : miaosha

 Target Server Type    : MySQL
 Target Server Version : 50645
 File Encoding         : 65001

 Date: 14/09/2020 15:14:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `goods_price` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 6 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '牛奶', 63.50);
INSERT INTO `goods` VALUES (2, '面包', 23.56);
INSERT INTO `goods` VALUES (3, '火腿肠', 2.00);
INSERT INTO `goods` VALUES (4, '火腿肠', 2.00);
INSERT INTO `goods` VALUES (5, '火腿肠', 2.00);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_num` varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT '0',
  `user_id` int(11) NULL DEFAULT 0,
  `goods_id` int(11) NULL DEFAULT 0,
  `seckill_id` int(11) NULL DEFAULT 0,
  `receive_phone` varchar(110) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT '0',
  `create_ts` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (1, '1', 2, 3, 4, '5', NULL);
INSERT INTO `order` VALUES (2, '1111', 0, 2, 2, '0', '2020-09-14 15:08:06');
INSERT INTO `order` VALUES (3, 'bbf4f5e4-8211-45fb-9613-aeab4334c1a5', 1234111, 1, 1, '15136798934', '2020-09-14 14:51:29');

-- ----------------------------
-- Table structure for seckill
-- ----------------------------
DROP TABLE IF EXISTS `seckill`;
CREATE TABLE `seckill`  (
  `seckill_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) NOT NULL,
  `goods_num` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT 0,
  `start_date` datetime(0) NULL DEFAULT NULL,
  `end_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`seckill_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of seckill
-- ----------------------------
INSERT INTO `seckill` VALUES (1, 1, 10, 1, '2020-09-11 11:03:54', '2020-09-27 11:04:01');
INSERT INTO `seckill` VALUES (2, 2, 20, 1, '2020-09-14 15:07:37', '2020-09-15 15:07:41');

SET FOREIGN_KEY_CHECKS = 1;
