/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : basic_info

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2017-09-14 15:10:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '姓名',
  `sex` int(11) DEFAULT '0' COMMENT '0未知,1男,2女',
  `age` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL COMMENT '用户账号',
  `user_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '杨杨', '1', '24', '123456789', '123456', 'yangyang', '123456');
INSERT INTO `user` VALUES ('2', 'yy', '0', '20', 'yy', '123', 'yy', 'yy');
INSERT INTO `user` VALUES ('3', 'yy', '0', '20', 'yy', '123', 'yy', 'yy');
INSERT INTO `user` VALUES ('4', 'yy', '0', '20', 'yy', '123', 'yy', 'yy');
