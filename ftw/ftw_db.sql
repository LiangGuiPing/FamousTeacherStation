/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : ftw_db

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2019-08-01 18:22:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for adm_modules
-- ----------------------------
DROP TABLE IF EXISTS `adm_modules`;
CREATE TABLE `adm_modules` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `platformId` int(11) DEFAULT NULL COMMENT '平台ID',
  `moduleName` varchar(30) NOT NULL DEFAULT '' COMMENT '模块名称',
  `parentId` int(10) DEFAULT NULL COMMENT '父类ID',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(100) DEFAULT '' COMMENT '备注',
  `visitUrl` varchar(200) DEFAULT '' COMMENT '访问URL相对路径',
  `displaySort` int(6) DEFAULT '0' COMMENT '模块显示顺序',
  `isDisplay` int(1) DEFAULT '1' COMMENT '模块是否显示1是0否',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_6` (`platformId`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`platformId`) REFERENCES `adm_platform` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of adm_modules
-- ----------------------------
INSERT INTO `adm_modules` VALUES ('1', null, '管理员模块', null, '2019-07-29 00:00:00', null, null, '1', '1');
INSERT INTO `adm_modules` VALUES ('2', null, '模块管理', '1', '2019-07-29 00:00:00', null, '/module/list', '2', '1');
INSERT INTO `adm_modules` VALUES ('3', null, '用户管理', '1', '2019-07-29 00:00:00', null, '/user/list', '3', '1');
INSERT INTO `adm_modules` VALUES ('4', null, '角色管理', '1', '2019-07-29 07:54:09', '', '/role/list', '4', '1');
INSERT INTO `adm_modules` VALUES ('5', null, '权限管理', '1', '2019-07-29 08:00:05', '', '/permission/list', '5', '1');
INSERT INTO `adm_modules` VALUES ('6', null, '直播课程管理', null, '2019-07-29 09:25:17', '', '', '1', '1');
INSERT INTO `adm_modules` VALUES ('7', null, '直播课程列表', '6', '2019-07-29 09:25:42', '', '/', '2', '1');
INSERT INTO `adm_modules` VALUES ('8', null, '交易管理', null, '2019-07-29 09:34:41', '', '', '1', '1');
INSERT INTO `adm_modules` VALUES ('9', null, '交易管理列表', '8', '2019-07-29 09:34:56', '', '/', '2', '1');
INSERT INTO `adm_modules` VALUES ('10', null, '学员管理', null, '2019-07-29 09:35:29', '', '', '1', '1');
INSERT INTO `adm_modules` VALUES ('11', null, '学员管理列表', '10', '2019-07-29 09:35:45', '', '/', '2', '1');
INSERT INTO `adm_modules` VALUES ('12', null, '专家管理', null, '2019-07-29 09:36:09', '', '', '1', '1');
INSERT INTO `adm_modules` VALUES ('13', null, '专家管理列表', '12', '2019-07-29 09:36:29', '', '/', '2', '1');
INSERT INTO `adm_modules` VALUES ('14', null, '评价报告管理', null, '2019-07-29 09:36:48', '', '', '1', '1');
INSERT INTO `adm_modules` VALUES ('15', null, '评价报告列表', '14', '2019-07-29 09:37:02', '', '/', '2', '1');

-- ----------------------------
-- Table structure for adm_permission
-- ----------------------------
DROP TABLE IF EXISTS `adm_permission`;
CREATE TABLE `adm_permission` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) DEFAULT NULL COMMENT '角色ID',
  `permissionDesc` varchar(800) DEFAULT '' COMMENT '权限描述',
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '权限名称',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_7` (`roleId`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`roleId`) REFERENCES `adm_roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of adm_permission
-- ----------------------------
INSERT INTO `adm_permission` VALUES ('1', '1', '新增', 'add');
INSERT INTO `adm_permission` VALUES ('2', '1', '删除', 'delete');
INSERT INTO `adm_permission` VALUES ('3', '1', '更新', 'update');
INSERT INTO `adm_permission` VALUES ('4', '1', '查询', 'search');

-- ----------------------------
-- Table structure for adm_platform
-- ----------------------------
DROP TABLE IF EXISTS `adm_platform`;
CREATE TABLE `adm_platform` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `platformName` varchar(50) DEFAULT '' COMMENT '平台名称',
  `platformDesc` varchar(200) DEFAULT '' COMMENT '平台描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of adm_platform
-- ----------------------------

-- ----------------------------
-- Table structure for adm_roles
-- ----------------------------
DROP TABLE IF EXISTS `adm_roles`;
CREATE TABLE `adm_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(50) DEFAULT '' COMMENT '角色名称',
  `roleDesc` varchar(50) DEFAULT '' COMMENT '角色描述',
  `creator` int(10) DEFAULT NULL COMMENT '创建者',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of adm_roles
-- ----------------------------
INSERT INTO `adm_roles` VALUES ('1', '管理员', '拥有至高无上的权限', '1', '2019-07-29 08:21:49');
INSERT INTO `adm_roles` VALUES ('2', '运营', '具备运营一般操作的权限', '1', '2019-07-29 08:30:14');

-- ----------------------------
-- Table structure for adm_system_logs
-- ----------------------------
DROP TABLE IF EXISTS `adm_system_logs`;
CREATE TABLE `adm_system_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `logsTypeId` int(10) DEFAULT NULL COMMENT '系统日志类型',
  `operation` varchar(500) DEFAULT NULL COMMENT '操作记录',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `ip` varchar(30) DEFAULT NULL COMMENT 'IP',
  `creator` int(11) DEFAULT NULL COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of adm_system_logs
-- ----------------------------

-- ----------------------------
-- Table structure for adm_users
-- ----------------------------
DROP TABLE IF EXISTS `adm_users`;
CREATE TABLE `adm_users` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `platformId` int(11) DEFAULT NULL COMMENT '平台ID',
  `loginName` varchar(30) NOT NULL COMMENT '登录名',
  `realName` varchar(60) NOT NULL COMMENT '真实姓名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码SHA256加密',
  `expireTime` datetime DEFAULT NULL COMMENT '过期时间',
  `telephone` varchar(30) DEFAULT NULL COMMENT '手机',
  `status` int(1) DEFAULT '1' COMMENT '状态 1在用 0停用',
  `creator` int(5) DEFAULT NULL COMMENT '创建者',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastEditTime` datetime DEFAULT NULL COMMENT '上次登录的时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_5` (`platformId`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`platformId`) REFERENCES `adm_platform` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of adm_users
-- ----------------------------
INSERT INTO `adm_users` VALUES ('1', null, '15910953833', 'LGP', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', '2022-01-28 02:37:52', '', '1', '1', '2019-07-28 02:28:23', '2019-07-29 08:41:16');
INSERT INTO `adm_users` VALUES ('2', null, '洛天依', '洛天依', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', '2030-02-01 08:52:34', '', '1', '1', '2019-07-29 08:52:34', null);

-- ----------------------------
-- Table structure for adm_users_modules
-- ----------------------------
DROP TABLE IF EXISTS `adm_users_modules`;
CREATE TABLE `adm_users_modules` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userId` int(10) DEFAULT NULL COMMENT '用户ID',
  `moduleId` int(10) DEFAULT NULL COMMENT '模块ID',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_1` (`userId`),
  KEY `FK_Reference_2` (`moduleId`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`userId`) REFERENCES `adm_users` (`id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`moduleId`) REFERENCES `adm_modules` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of adm_users_modules
-- ----------------------------
INSERT INTO `adm_users_modules` VALUES ('39', '1', '1');
INSERT INTO `adm_users_modules` VALUES ('40', '1', '2');
INSERT INTO `adm_users_modules` VALUES ('41', '1', '3');
INSERT INTO `adm_users_modules` VALUES ('42', '1', '4');
INSERT INTO `adm_users_modules` VALUES ('43', '1', '5');
INSERT INTO `adm_users_modules` VALUES ('44', '1', '6');
INSERT INTO `adm_users_modules` VALUES ('45', '1', '7');
INSERT INTO `adm_users_modules` VALUES ('46', '1', '8');
INSERT INTO `adm_users_modules` VALUES ('47', '1', '9');
INSERT INTO `adm_users_modules` VALUES ('48', '1', '10');
INSERT INTO `adm_users_modules` VALUES ('49', '1', '11');
INSERT INTO `adm_users_modules` VALUES ('50', '1', '12');
INSERT INTO `adm_users_modules` VALUES ('51', '1', '13');
INSERT INTO `adm_users_modules` VALUES ('52', '1', '14');
INSERT INTO `adm_users_modules` VALUES ('53', '1', '15');
INSERT INTO `adm_users_modules` VALUES ('54', '2', '1');
INSERT INTO `adm_users_modules` VALUES ('55', '2', '5');
INSERT INTO `adm_users_modules` VALUES ('56', '2', '8');
INSERT INTO `adm_users_modules` VALUES ('57', '2', '9');
INSERT INTO `adm_users_modules` VALUES ('58', '2', '10');
INSERT INTO `adm_users_modules` VALUES ('59', '2', '11');

-- ----------------------------
-- Table structure for adm_user_role
-- ----------------------------
DROP TABLE IF EXISTS `adm_user_role`;
CREATE TABLE `adm_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) DEFAULT NULL COMMENT '角色ID',
  `userid` int(11) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of adm_user_role
-- ----------------------------
INSERT INTO `adm_user_role` VALUES ('3', '1', '1');
INSERT INTO `adm_user_role` VALUES ('4', '2', '2');

-- ----------------------------
-- Table structure for test_user
-- ----------------------------
DROP TABLE IF EXISTS `test_user`;
CREATE TABLE `test_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test_user
-- ----------------------------
INSERT INTO `test_user` VALUES ('1', 'Thomas', '123456');
INSERT INTO `test_user` VALUES ('2', '梁桂平', '456789');
INSERT INTO `test_user` VALUES ('3', '梁天使', '654321');
