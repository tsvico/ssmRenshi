/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2019-10-25 13:50:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门名称',
  `telephone` varchar(15) DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `dCreateDate` date DEFAULT NULL COMMENT '成立时间',
  PRIMARY KEY (`dept_id`),
  UNIQUE KEY `dept_id` (`dept_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '总经办', '88888888', 'hq@wq.com', '五楼', '2019-09-07');
INSERT INTO `department` VALUES ('2', '研发部', '1372556', 'hq@163.com', '四楼', '2019-09-05');
INSERT INTO `department` VALUES ('5', '后勤部', '182365596', 'emm@12.com', '大厅', '2019-09-23');
INSERT INTO `department` VALUES ('6', '总经办', '88888888', 'hq@wq.com', '三楼', '2019-09-07');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_show` int(11) DEFAULT '1' COMMENT '是否显示',
  `menu_grade` int(11) DEFAULT NULL COMMENT '菜单等级',
  `menu_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
  `menu_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '链接',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级',
  `role_id` int(11) DEFAULT NULL COMMENT '分类',
  PRIMARY KEY (`menu_id`),
  UNIQUE KEY `menu_id` (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '1', null, 'layui-icon-username', '用户管理', '#', '0', '2');
INSERT INTO `menu` VALUES ('2', '1', null, 'layui-icon-log', '考勤管理', '#', '0', '8');
INSERT INTO `menu` VALUES ('3', '1', null, 'layui-icon-radio', '部门管理', 'admin/page/depart', '1', '2');
INSERT INTO `menu` VALUES ('4', '1', null, 'layui-icon-radio', '职位管理', 'admin/page/position', '1', '2');
INSERT INTO `menu` VALUES ('5', '1', null, 'layui-icon-radio', '用户管理', 'admin/page/user', '1', '2');
INSERT INTO `menu` VALUES ('7', '1', null, 'layui-icon-radio', '员工考勤管理', 'admin/page/punchClock', '2', '2');
INSERT INTO `menu` VALUES ('9', '1', null, 'layui-icon-radio', '考勤月报表', 'admin/page/attendReport', '2', '8');
INSERT INTO `menu` VALUES ('10', '1', null, 'layui-icon-chart-screen', '薪资管理', '/admin/page/wages', '0', '8');
INSERT INTO `menu` VALUES ('11', '1', null, 'layui-icon-radio', '离职用户', '/admin/page/leaveUser', '1', '2');

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `position_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `level` int(11) DEFAULT NULL COMMENT '权限等级',
  `name` varchar(255) DEFAULT NULL,
  `describtion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`position_id`),
  UNIQUE KEY `position_id` (`position_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES ('1', '1', '超级管理员', '管理系统最高权限');
INSERT INTO `position` VALUES ('2', '2', 'CEO', 'CEO类！~');
INSERT INTO `position` VALUES ('3', '3', '总经理', '总经理类！~');
INSERT INTO `position` VALUES ('4', '4', '研发部经理', null);
INSERT INTO `position` VALUES ('5', '5', '财务部经理', null);
INSERT INTO `position` VALUES ('6', '6', '市场部经理', null);
INSERT INTO `position` VALUES ('7', '7', '人事部经理', null);
INSERT INTO `position` VALUES ('8', '8', '人事专员', null);
INSERT INTO `position` VALUES ('9', '9', '劳资专员', null);
INSERT INTO `position` VALUES ('10', '10', '程序员', null);
INSERT INTO `position` VALUES ('11', '11', '初级工程师', null);
INSERT INTO `position` VALUES ('12', '12', '中级工程师', null);
INSERT INTO `position` VALUES ('13', '13', '高级工程师', null);
INSERT INTO `position` VALUES ('14', '14', '系统架构师', null);
INSERT INTO `position` VALUES ('15', '15', '需求分析师', null);
INSERT INTO `position` VALUES ('16', '16', '调查专员', null);
INSERT INTO `position` VALUES ('17', '17', '企业推广员', null);
INSERT INTO `position` VALUES ('18', '18', '平面设计师', null);
INSERT INTO `position` VALUES ('19', '19', '培训专员', null);
INSERT INTO `position` VALUES ('20', '20', '市场督导员', null);
INSERT INTO `position` VALUES ('21', '21', '核算会计', null);
INSERT INTO `position` VALUES ('22', '22', '税务会计', null);
INSERT INTO `position` VALUES ('23', '23', '出纳员', null);
INSERT INTO `position` VALUES ('25', '2222', '测试岗位！~', 'emmm！~~');

-- ----------------------------
-- Table structure for punchclock
-- ----------------------------
DROP TABLE IF EXISTS `punchclock`;
CREATE TABLE `punchclock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  `attendanceTime` date DEFAULT NULL COMMENT '考勤时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '迟到原因备注',
  `normal_attendance` int(2) DEFAULT '22' COMMENT '正常出勤',
  `late` int(2) DEFAULT '0' COMMENT '迟到',
  `zt` int(2) DEFAULT '0' COMMENT '早退',
  `bj` int(2) DEFAULT '0' COMMENT '病假',
  `sj` int(2) DEFAULT '0' COMMENT '事假',
  `kg` int(2) DEFAULT '0' COMMENT '旷工',
  PRIMARY KEY (`id`),
  KEY `uid1` (`uid`),
  CONSTRAINT `uid1` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of punchclock
-- ----------------------------
INSERT INTO `punchclock` VALUES ('1', '10002', '2019-10-15', '1', '22', '1', '2', '3', '4', '5');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '职位类别',
  `role_name` varchar(255) DEFAULT NULL,
  `role_value` int(11) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', null);
INSERT INTO `role` VALUES ('2', 'CEO', null);
INSERT INTO `role` VALUES ('3', '总经理', null);
INSERT INTO `role` VALUES ('4', '部门经理', null);
INSERT INTO `role` VALUES ('5', '普通员工', null);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) NOT NULL,
  `upassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `unickname` varchar(20) DEFAULT NULL COMMENT '昵称',
  `uphone` int(15) DEFAULT NULL COMMENT '手机号',
  `uage` int(11) NOT NULL,
  `avater` varchar(255) NOT NULL COMMENT '头像图片地址',
  `ubirthday` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `status` int(3) DEFAULT '1' COMMENT '1 在职 0离职',
  `dept_id` bigint(20) DEFAULT NULL,
  `position_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `www` (`uid`) USING BTREE,
  KEY `FFFFposition_id` (`position_id`) USING BTREE,
  KEY `FFFFrole` (`role_id`),
  KEY `FFFFdept_id` (`dept_id`),
  KEY `unickname` (`unickname`),
  CONSTRAINT `FFFFdept_id` FOREIGN KEY (`dept_id`) REFERENCES `department` (`dept_id`),
  CONSTRAINT `FFFFposition_id` FOREIGN KEY (`position_id`) REFERENCES `position` (`position_id`),
  CONSTRAINT `FFFFrole` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10005 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('10001', 'admin', '3c2cad99c8ac041e41d55da41942fddf', '管理', '10086', '20', 'https://api.adorable.io/avatars/100/admin', '2019-10-01', 'tsxygwj@163.com', '1', '1', '1', '1');
INSERT INTO `users` VALUES ('10002', 'zhang', '3c2cad99c8ac041e41d55da41942fddf', '张三', '1234565', '44', 'https://api.adorable.io/avatars/100/zhang', '2019-10-08', 'www@163.com', '1', '2', '4', '4');
INSERT INTO `users` VALUES ('10003', 'wang', '3c2cad99c8ac041e41d55da41942fddf', '大花', '1234', '19', 'https://api.adorable.io/avatars/100/aac', '2019-10-23', 'tsxygwj@qqqq.com', '0', '5', '23', '5');
INSERT INTO `users` VALUES ('10004', 'dali', '3c2cad99c8ac041e41d55da41942fddf', '小丁', '3123', '44', 'https://api.adorable.io/avatars/100/dali', '2019-10-04', 'wq@qq.com', '1', '5', '7', '4');

-- ----------------------------
-- Table structure for wages
-- ----------------------------
DROP TABLE IF EXISTS `wages`;
CREATE TABLE `wages` (
  `wages_id` int(10) NOT NULL,
  `uid` int(10) NOT NULL,
  `wages_time` date DEFAULT NULL COMMENT '工资年月',
  `basic_wage` float DEFAULT NULL COMMENT '基本工资',
  `bonus` float DEFAULT NULL COMMENT '奖金',
  `allowance` float DEFAULT NULL COMMENT '津贴',
  `attendance` float DEFAULT NULL COMMENT '考勤薪资',
  PRIMARY KEY (`wages_id`),
  UNIQUE KEY `id` (`wages_id`) USING BTREE,
  KEY `uid` (`uid`),
  CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wages
-- ----------------------------
INSERT INTO `wages` VALUES ('1', '10002', '2019-10-09', '1000', '100', '15', '30');
INSERT INTO `wages` VALUES ('2', '10003', '2019-10-09', '1000', '100', '22', '10');
INSERT INTO `wages` VALUES ('3', '10004', '2013-03-24', '1000', '100', '15', '0');
