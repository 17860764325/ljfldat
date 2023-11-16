/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : ljflxcx

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 16/11/2023 18:45:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account_pwd
-- ----------------------------
DROP TABLE IF EXISTS `account_pwd`;
CREATE TABLE `account_pwd` (
  `id` int NOT NULL AUTO_INCREMENT,
  `phone_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `age` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of account_pwd
-- ----------------------------
BEGIN;
INSERT INTO `account_pwd` (`id`, `phone_number`, `age`) VALUES (1, '17860764325', 1);
COMMIT;

-- ----------------------------
-- Table structure for dd
-- ----------------------------
DROP TABLE IF EXISTS `dd`;
CREATE TABLE `dd` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dd_name` varchar(255) DEFAULT NULL,
  `dd_phonenumber` int DEFAULT NULL,
  `dd_shop_id` int DEFAULT NULL,
  `dd_age` varchar(255) DEFAULT NULL,
  `dd_buynumber` int DEFAULT NULL,
  `dd_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of dd
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for phb_jiedao
-- ----------------------------
DROP TABLE IF EXISTS `phb_jiedao`;
CREATE TABLE `phb_jiedao` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Jiedao_Name` varchar(255) DEFAULT NULL,
  `number` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of phb_jiedao
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for phb_nianji
-- ----------------------------
DROP TABLE IF EXISTS `phb_nianji`;
CREATE TABLE `phb_nianji` (
  `Nianji_Name` varchar(255) DEFAULT NULL,
  `number` int DEFAULT NULL,
  `id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of phb_nianji
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for phb_qiye
-- ----------------------------
DROP TABLE IF EXISTS `phb_qiye`;
CREATE TABLE `phb_qiye` (
  `id` int NOT NULL,
  `Qiye_Name` varchar(255) DEFAULT NULL,
  `number` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of phb_qiye
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for phb_school
-- ----------------------------
DROP TABLE IF EXISTS `phb_school`;
CREATE TABLE `phb_school` (
  `number` int DEFAULT NULL,
  `School_Name` varchar(255) DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of phb_school
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `questions` varchar(255) DEFAULT NULL,
  `option_A` varchar(255) DEFAULT NULL,
  `option_B` varchar(255) DEFAULT NULL,
  `option_C` varchar(255) DEFAULT NULL,
  `option_D` varchar(255) DEFAULT NULL,
  `question_true` varchar(255) DEFAULT NULL,
  `question_type` varchar(255) DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of question
-- ----------------------------
BEGIN;
INSERT INTO `question` (`questions`, `option_A`, `option_B`, `option_C`, `option_D`, `question_true`, `question_type`, `id`) VALUES ('1、生活垃圾分为______类。', '一', '二', '三', '四', 'D', '2', 1);
INSERT INTO `question` (`questions`, `option_A`, `option_B`, `option_C`, `option_D`, `question_true`, `question_type`, `id`) VALUES ('1、生活垃圾分为______类。', '一', '二', '三', '四', 'D', '2', 2);
INSERT INTO `question` (`questions`, `option_A`, `option_B`, `option_C`, `option_D`, `question_true`, `question_type`, `id`) VALUES ('1、生活垃圾分为______类。', '一', '二', '三', '四', 'D', '2', 3);
INSERT INTO `question` (`questions`, `option_A`, `option_B`, `option_C`, `option_D`, `question_true`, `question_type`, `id`) VALUES ('1、生活垃圾分为______类。', '一', '二', '三', '四', 'D', '2', 4);
INSERT INTO `question` (`questions`, `option_A`, `option_B`, `option_C`, `option_D`, `question_true`, `question_type`, `id`) VALUES ('1、生活垃圾分为______类。', '一', '二', '三', '四', 'D', '2', 5);
INSERT INTO `question` (`questions`, `option_A`, `option_B`, `option_C`, `option_D`, `question_true`, `question_type`, `id`) VALUES ('1、生活垃圾分为______类。', '一', '二', '三', '四', 'D', '2', 6);
INSERT INTO `question` (`questions`, `option_A`, `option_B`, `option_C`, `option_D`, `question_true`, `question_type`, `id`) VALUES ('1、生活垃圾分为______类。', '一', '二', '三', '四', 'D', '2', 7);
INSERT INTO `question` (`questions`, `option_A`, `option_B`, `option_C`, `option_D`, `question_true`, `question_type`, `id`) VALUES ('1、生活垃圾分为______类。', '一', '二', '三', '四', 'D', '2', 8);
INSERT INTO `question` (`questions`, `option_A`, `option_B`, `option_C`, `option_D`, `question_true`, `question_type`, `id`) VALUES ('1、生活垃圾分为______类。', '一', '二', '三', '四', 'D', '2', 9);
INSERT INTO `question` (`questions`, `option_A`, `option_B`, `option_C`, `option_D`, `question_true`, `question_type`, `id`) VALUES ('1、生活垃圾分为______类。', '一', '二', '三', '四', 'D', '2', 10);
COMMIT;

-- ----------------------------
-- Table structure for shopping
-- ----------------------------
DROP TABLE IF EXISTS `shopping`;
CREATE TABLE `shopping` (
  `shop_id` int NOT NULL AUTO_INCREMENT,
  `shop_name` varchar(255) DEFAULT NULL,
  `shop_img` varchar(255) DEFAULT NULL,
  `shop_price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of shopping
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_adult
-- ----------------------------
DROP TABLE IF EXISTS `user_adult`;
CREATE TABLE `user_adult` (
  `user_qiye` varchar(255) DEFAULT NULL,
  `user_jfnumber_all` int DEFAULT NULL,
  `user_jiedao` varchar(255) DEFAULT NULL,
  `user_jfnumber_last` int DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `phone_number` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `user_age` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `user_answer_number` int DEFAULT '50',
  `userdan` varchar(255),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_adult
-- ----------------------------
BEGIN;
INSERT INTO `user_adult` (`user_qiye`, `user_jfnumber_all`, `user_jiedao`, `user_jfnumber_last`, `id`, `phone_number`, `username`, `user_age`, `time`, `user_answer_number`, `userdan`) VALUES ('崂山区住房和城乡建设局', 10, '金家岭街道', 10, 4, '17860764325', '测试 1', '1', '2023-11-16 15:37:30', 49, '1');
COMMIT;

-- ----------------------------
-- Table structure for user_student
-- ----------------------------
DROP TABLE IF EXISTS `user_student`;
CREATE TABLE `user_student` (
  `user_answer_number` varchar(255) DEFAULT '50',
  `id` int NOT NULL AUTO_INCREMENT,
  `time` datetime DEFAULT NULL,
  `userdan` varchar(255),
  `phone_number` varchar(255) DEFAULT NULL,
  `user_school` varchar(255) DEFAULT NULL,
  `user_class` varchar(255) DEFAULT NULL,
  `user_jfnumber_last` varchar(255) DEFAULT NULL,
  `user_jfnumber_all` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_student
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
