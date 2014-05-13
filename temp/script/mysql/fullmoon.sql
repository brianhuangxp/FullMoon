/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50529
Source Host           : localhost:3306
Source Database       : fullmoon

Target Server Type    : MYSQL
Target Server Version : 50529
File Encoding         : 65001

Date: 2014-01-26 15:10:55
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `member`
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(8) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `phone_number` varchar(200) DEFAULT NULL,
  `record_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('0', 'John Smith', 'john.smith@mailinator.com', '2125551212', '2014-01-12 15:03:28');
INSERT INTO `member` VALUES ('1', 'John Smith', 'john.smith1@mailinator.com', '2125551212', '2014-01-13 15:03:28');
INSERT INTO `member` VALUES ('2', 'John Smith', 'john.smith2@mailinator.com', '2125551212', '2014-01-14 15:03:28');
INSERT INTO `member` VALUES ('3', 'John Smith', 'john.smith3@mailinator.com', '2125551212', '2014-01-15 15:03:28');
INSERT INTO `member` VALUES ('4', 'John Smith', 'john.smith4@mailinator.com', '2125551212', '2014-01-16 15:03:28');
INSERT INTO `member` VALUES ('5', 'John Smith', 'john.smith5@mailinator.com', '2125551212', '2014-01-17 15:03:28');
INSERT INTO `member` VALUES ('6', 'John Smith', 'john.smith6@mailinator.com', '2125551212', '2014-01-18 15:03:28');
INSERT INTO `member` VALUES ('7', 'John Smith', 'john.smith7@mailinator.com', '2125551212', '2014-01-19 15:03:28');
INSERT INTO `member` VALUES ('8', 'John Smith', 'john.smith8@mailinator.com', '2125551212', '2014-01-20 15:03:28');
INSERT INTO `member` VALUES ('9', 'John Smith', 'john.smith9@mailinator.com', '2125551212', '2014-01-21 15:03:28');
INSERT INTO `member` VALUES ('10', 'John Smith', 'john.smith10@mailinator.com', '2125551212', '2014-01-22 15:03:28');

-- ----------------------------
-- Table structure for `sys_code`
-- ----------------------------
DROP TABLE IF EXISTS `sys_code`;
CREATE TABLE `sys_code` (
  `code_id` int(8) NOT NULL,
  `code_type` varchar(32) DEFAULT NULL,
  `parent_code_id` int(8) DEFAULT NULL,
  `parent_type` varchar(32) DEFAULT NULL,
  `code_value` varchar(32) DEFAULT NULL,
  `code_label` varchar(32) DEFAULT NULL,
  `disable` char(1) DEFAULT '0',
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`code_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_code
-- ----------------------------
INSERT INTO `sys_code` VALUES ('1', 'BASIC_ROLE', '19', 'ROLE_TYPE', '1', 'search', '0', null);

-- ----------------------------
-- Table structure for `sys_code_type`
-- ----------------------------
DROP TABLE IF EXISTS `sys_code_type`;
CREATE TABLE `sys_code_type` (
  `code_type_id` int(8) NOT NULL,
  `code_type` varchar(32) DEFAULT NULL,
  `code_label` varchar(32) DEFAULT NULL,
  `disable` char(1) DEFAULT '0',
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`code_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_code_type
-- ----------------------------
INSERT INTO `sys_code_type` VALUES ('1', 'BASIC_ROLE', 'Basic Role', '0', null);
INSERT INTO `sys_code_type` VALUES ('2', 'ADVANCED_ROLE', 'Advanced Role', '0', null);
INSERT INTO `sys_code_type` VALUES ('3', 'SERVER_ROLE', 'Server Role', '0', null);

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` varchar(8) NOT NULL,
  `route_id` int(8) DEFAULT NULL,
  `parent_id` varchar(8) DEFAULT NULL,
  `menu_name` varchar(200) DEFAULT NULL,
  `menu_url` varchar(255) DEFAULT NULL,
  `disable` char(1) DEFAULT '0',
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('0', '0', '-1', 'root', null, '0', null);
INSERT INTO `sys_menu` VALUES ('1', null, '0', 'User', null, '0', null);
INSERT INTO `sys_menu` VALUES ('11', '1', '1', 'user1', 'user/subuser1', '0', null);
INSERT INTO `sys_menu` VALUES ('12', null, '1', 'user2', null, '0', null);
INSERT INTO `sys_menu` VALUES ('121', '2', '12', 'subuser2', 'user/subuser2', '0', null);
INSERT INTO `sys_menu` VALUES ('2', null, '0', 'Member', null, '0', null);
INSERT INTO `sys_menu` VALUES ('21', '3', '2', 'Member1', 'user/member', '0', null);
INSERT INTO `sys_menu` VALUES ('22', '4', '2', 'Member2', 'user/member2', '0', null);

-- ----------------------------
-- Table structure for `sys_menu_re_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_re_role`;
CREATE TABLE `sys_menu_re_role` (
  `menu_role_id` int(8) NOT NULL,
  `menu_id` varchar(8) NOT NULL,
  `role_id` int(8) NOT NULL,
  PRIMARY KEY (`menu_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu_re_role
-- ----------------------------
INSERT INTO `sys_menu_re_role` VALUES ('2', '0', '1');
INSERT INTO `sys_menu_re_role` VALUES ('3', '1', '1');
INSERT INTO `sys_menu_re_role` VALUES ('4', '11', '1');
INSERT INTO `sys_menu_re_role` VALUES ('5', '12', '1');
INSERT INTO `sys_menu_re_role` VALUES ('6', '2', '1');
INSERT INTO `sys_menu_re_role` VALUES ('7', '21', '1');
INSERT INTO `sys_menu_re_role` VALUES ('8', '22', '1');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(8) NOT NULL,
  `role_name` varchar(32) DEFAULT NULL,
  `role_type` char(1) DEFAULT NULL,
  `disable` char(1) DEFAULT '0',
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'role1', '2', '0', null);
INSERT INTO `sys_role` VALUES ('2', 'role2', '2', '0', null);
INSERT INTO `sys_role` VALUES ('3', 'role3', '2', '0', null);

-- ----------------------------
-- Table structure for `sys_route`
-- ----------------------------
DROP TABLE IF EXISTS `sys_route`;
CREATE TABLE `sys_route` (
  `route_id` int(8) NOT NULL,
  `route_path` varchar(255) DEFAULT NULL,
  `module_path` varchar(255) DEFAULT NULL,
  `module_name` varchar(200) DEFAULT NULL,
  `load_css` char(1) DEFAULT '0',
  `disable` char(1) DEFAULT '0',
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`route_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_route
-- ----------------------------
INSERT INTO `sys_route` VALUES ('0', '/', 'overview/', 'Overview', '1', '0', null);
INSERT INTO `sys_route` VALUES ('1', '/user/subuser1', 'user/subuser1/', 'Subuser1', '0', '0', null);
INSERT INTO `sys_route` VALUES ('2', '/user/subuser2', 'user/subuser2/subuser22/', 'Subuser22', '0', '0', null);
INSERT INTO `sys_route` VALUES ('3', '/user/member', 'user/member/', 'Member', '0', '0', null);
INSERT INTO `sys_route` VALUES ('4', '/user/member2', 'user/member/', 'Member2', '0', '0', null);

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(8) NOT NULL,
  `user_name` varchar(32) NOT NULL,
  `nick_name` varchar(200) NOT NULL,
  `user_pwd` varchar(32) NOT NULL,
  `disable` char(1) DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'root', 'root', 'root', '0');

-- ----------------------------
-- Table structure for `sys_user_re_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_re_role`;
CREATE TABLE `sys_user_re_role` (
  `user_role_id` int(8) NOT NULL,
  `user_id` int(8) NOT NULL,
  `role_id` int(8) NOT NULL,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_re_role
-- ----------------------------
INSERT INTO `sys_user_re_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_re_role` VALUES ('2', '1', '2');
INSERT INTO `sys_user_re_role` VALUES ('3', '1', '3');
