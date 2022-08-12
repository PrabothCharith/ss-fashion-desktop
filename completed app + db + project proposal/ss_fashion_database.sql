/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : ss_fashion

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 10/08/2022 20:55:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `pimg_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_admins_profile_img1_idx` (`pimg_id`),
  CONSTRAINT `fk_admins_profile_img1` FOREIGN KEY (`pimg_id`) REFERENCES `profile_img` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of admins
-- ----------------------------
BEGIN;
INSERT INTO `admins` VALUES (1, 'admin', 'admin', NULL);
INSERT INTO `admins` VALUES (3, 'admin', 'admin2', NULL);
COMMIT;

-- ----------------------------
-- Table structure for age
-- ----------------------------
DROP TABLE IF EXISTS `age`;
CREATE TABLE `age` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of age
-- ----------------------------
BEGIN;
INSERT INTO `age` VALUES (12, 'kids');
INSERT INTO `age` VALUES (13, 'women');
COMMIT;

-- ----------------------------
-- Table structure for availability
-- ----------------------------
DROP TABLE IF EXISTS `availability`;
CREATE TABLE `availability` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of availability
-- ----------------------------
BEGIN;
INSERT INTO `availability` VALUES (1, 'Enabled');
INSERT INTO `availability` VALUES (2, 'Disabled');
COMMIT;

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of brand
-- ----------------------------
BEGIN;
INSERT INTO `brand` VALUES (4, 'test brand');
INSERT INTO `brand` VALUES (5, 'tbsa');
COMMIT;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
INSERT INTO `category` VALUES (1, 'test category');
INSERT INTO `category` VALUES (2, 't catagory');
INSERT INTO `category` VALUES (3, 'testC');
COMMIT;

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of city
-- ----------------------------
BEGIN;
INSERT INTO `city` VALUES (7, 'kalaniya');
INSERT INTO `city` VALUES (8, 'waliweriya');
INSERT INTO `city` VALUES (9, 'gampaha');
INSERT INTO `city` VALUES (10, 'puttalama');
INSERT INTO `city` VALUES (11, 'ampara');
INSERT INTO `city` VALUES (12, 'kaluthatra');
INSERT INTO `city` VALUES (13, 'biyagama');
INSERT INTO `city` VALUES (14, 'rathnapura');
COMMIT;

-- ----------------------------
-- Table structure for colour
-- ----------------------------
DROP TABLE IF EXISTS `colour`;
CREATE TABLE `colour` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of colour
-- ----------------------------
BEGIN;
INSERT INTO `colour` VALUES (5, 'red');
INSERT INTO `colour` VALUES (6, 'green');
COMMIT;

-- ----------------------------
-- Table structure for com_payments
-- ----------------------------
DROP TABLE IF EXISTS `com_payments`;
CREATE TABLE `com_payments` (
  `com_id` int NOT NULL,
  `amount` double DEFAULT '0',
  PRIMARY KEY (`com_id`),
  CONSTRAINT `com_payments_ibfk_1` FOREIGN KEY (`com_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of com_payments
-- ----------------------------
BEGIN;
INSERT INTO `com_payments` VALUES (9, 0);
INSERT INTO `com_payments` VALUES (10, -87900);
INSERT INTO `com_payments` VALUES (11, -1000);
INSERT INTO `com_payments` VALUES (12, 100);
COMMIT;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `mobile` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of company
-- ----------------------------
BEGIN;
INSERT INTO `company` VALUES (9, 'test company', '0754003799', 'test@gamil.com', '19287 amwimwon wekcj, own');
INSERT INTO `company` VALUES (10, 'new', '0777777777', 'khb;bl@gmail.com', 'iyyufitfiyfkig');
INSERT INTO `company` VALUES (11, 'com1', '0777238744', 'qihbebw@gmail.com', 'ksjd;;sahwe');
INSERT INTO `company` VALUES (12, 'new company 3', '0756477389', 'test@gmail.com', 'test address');
COMMIT;

-- ----------------------------
-- Table structure for company_has_city
-- ----------------------------
DROP TABLE IF EXISTS `company_has_city`;
CREATE TABLE `company_has_city` (
  `id` int NOT NULL AUTO_INCREMENT,
  `city_id` int NOT NULL,
  `company_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_city_has_company_company1_idx` (`company_id`),
  KEY `fk_city_has_company_city1_idx` (`city_id`),
  CONSTRAINT `company_has_city_ibfk_1` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`),
  CONSTRAINT `fk_city_has_company_company1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of company_has_city
-- ----------------------------
BEGIN;
INSERT INTO `company_has_city` VALUES (28, 7, 9);
INSERT INTO `company_has_city` VALUES (29, 8, 9);
INSERT INTO `company_has_city` VALUES (30, 9, 10);
INSERT INTO `company_has_city` VALUES (31, 7, 11);
INSERT INTO `company_has_city` VALUES (32, 7, 12);
INSERT INTO `company_has_city` VALUES (33, 11, 10);
COMMIT;

-- ----------------------------
-- Table structure for customers
-- ----------------------------
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers` (
  `email` varchar(255) NOT NULL,
  `password` varchar(32) NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `fname` varchar(45) DEFAULT NULL,
  `lname` varchar(45) DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `mobile` varchar(12) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `city_id` int NOT NULL,
  `pimg_id` int DEFAULT NULL,
  `gender_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_customers_city_idx` (`city_id`),
  KEY `fk_customers_profile_img1_idx` (`pimg_id`),
  KEY `fk_customers_gender1_idx` (`gender_id`),
  CONSTRAINT `customers_ibfk_1` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`),
  CONSTRAINT `fk_customers_gender1` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`id`),
  CONSTRAINT `fk_customers_profile_img1` FOREIGN KEY (`pimg_id`) REFERENCES `profile_img` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of customers
-- ----------------------------
BEGIN;
INSERT INTO `customers` VALUES ('unknown', 'unknown', '2022-07-26 00:39:06', 'unknown', 'unknown', 1, 'unknown', 'unknown', 7, NULL, 5);
INSERT INTO `customers` VALUES ('praboth@gmail.com', 'ppppp', '2022-07-25 20:43:06', 'praboth', 'charith', 2, '0777465839', 'test address for customer', 9, NULL, 5);
INSERT INTO `customers` VALUES ('test@gmail.com', '123456', '2022-07-28 22:29:26', 'chatura', 'rathnayake', 3, '0754003799', 'ksjlwncwecw', 13, NULL, 5);
INSERT INTO `customers` VALUES ('obx@gmail.com', 'testp', '2022-07-28 22:39:52', 'test', 'test', 4, '0764003799', 'oaisbcuoboc', 13, NULL, 5);
INSERT INTO `customers` VALUES ('amanda@gmail.com', 'qqqqqq1W', '2022-08-09 03:34:33', 'amanda', 'wikramasinhe', 5, '0777463829', 'hjdvlhwkwe', 10, NULL, 6);
COMMIT;

-- ----------------------------
-- Table structure for employees
-- ----------------------------
DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fname` varchar(45) DEFAULT NULL,
  `lname` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `mobile` varchar(12) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `city_id` int NOT NULL,
  `NIC` varchar(13) DEFAULT NULL,
  `gender_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_employees_city1_idx` (`city_id`),
  KEY `fk_employees_profile_img1_idx` (`NIC`),
  KEY `fk_employees_gender1_idx` (`gender_id`),
  CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`),
  CONSTRAINT `fk_employees_gender1` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of employees
-- ----------------------------
BEGIN;
INSERT INTO `employees` VALUES (1, 'test emp', 'test lname emp', 'test', 'test', '0777093748', 'sdclkscm[m pm[wmecw', 'qwkjndw@gmail.com', 9, NULL, 5);
INSERT INTO `employees` VALUES (10, 'praboth', 'charith', 'praboth', 'praboth', '0765557488', '189/1 ambaraluwa, north, waliweriya', 'praboth@gmail.com', 8, '200126302997', 5);
INSERT INTO `employees` VALUES (11, 'new employee', 'employee last name', 'employee', 'employee', '0777947382', 'address', 'teest@gmail.com', 14, '200126322997', 5);
COMMIT;

-- ----------------------------
-- Table structure for gender
-- ----------------------------
DROP TABLE IF EXISTS `gender`;
CREATE TABLE `gender` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of gender
-- ----------------------------
BEGIN;
INSERT INTO `gender` VALUES (5, 'male');
INSERT INTO `gender` VALUES (6, 'female');
COMMIT;

-- ----------------------------
-- Table structure for grn
-- ----------------------------
DROP TABLE IF EXISTS `grn`;
CREATE TABLE `grn` (
  `id` int NOT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  `suppliers_id` int NOT NULL,
  `admin_id` int NOT NULL,
  `total_amount` double DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_grn_suppliers1_idx` (`suppliers_id`),
  KEY `fk_grn_employees1_idx` (`admin_id`),
  KEY `deleted` (`deleted`),
  CONSTRAINT `fk_grn_suppliers1` FOREIGN KEY (`suppliers_id`) REFERENCES `suppliers` (`id`),
  CONSTRAINT `grn_ibfk_1` FOREIGN KEY (`deleted`) REFERENCES `availability` (`id`),
  CONSTRAINT `grn_ibfk_2` FOREIGN KEY (`admin_id`) REFERENCES `admins` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of grn
-- ----------------------------
BEGIN;
INSERT INTO `grn` VALUES (1, '2022-07-24 00:23:45', 5, 1, NULL, 1);
INSERT INTO `grn` VALUES (2, '2022-07-24 00:34:23', 5, 1, NULL, 1);
INSERT INTO `grn` VALUES (3, '2022-07-24 00:37:33', 5, 1, NULL, 2);
INSERT INTO `grn` VALUES (4, '2022-07-24 00:42:48', 5, 1, 1900, 1);
INSERT INTO `grn` VALUES (5, '2022-07-24 01:04:50', 5, 1, 50, 1);
INSERT INTO `grn` VALUES (6, '2022-07-24 01:08:33', 6, 1, NULL, 2);
INSERT INTO `grn` VALUES (7, '2022-07-24 01:11:37', 6, 1, NULL, 2);
INSERT INTO `grn` VALUES (8, '2022-07-24 01:16:17', 6, 1, 400, 1);
INSERT INTO `grn` VALUES (9, '2022-07-24 12:19:22', 5, 1, 10000, 1);
INSERT INTO `grn` VALUES (10, '2022-07-29 23:00:44', 5, 1, 3000, 1);
INSERT INTO `grn` VALUES (11, '2022-07-30 00:17:46', 6, 1, 2000, 1);
INSERT INTO `grn` VALUES (12, '2022-07-30 00:20:42', 5, 1, 2000, 1);
INSERT INTO `grn` VALUES (13, '2022-07-30 00:23:15', 5, 1, 2000, 1);
INSERT INTO `grn` VALUES (14, '2022-07-30 00:32:39', 5, 1, 2000, 1);
INSERT INTO `grn` VALUES (15, '2022-07-30 03:50:42', 7, 1, 20000, 1);
INSERT INTO `grn` VALUES (16, '2022-07-30 04:26:14', 7, 1, 2000, 1);
INSERT INTO `grn` VALUES (17, '2022-07-30 13:08:40', 6, 1, 11000, 1);
INSERT INTO `grn` VALUES (18, '2022-07-30 14:49:58', 8, 1, 6100, 1);
INSERT INTO `grn` VALUES (19, '2022-07-30 14:59:27', 6, 1, 6600, 1);
INSERT INTO `grn` VALUES (20, '2022-07-30 22:20:13', 5, 1, 2200, 1);
INSERT INTO `grn` VALUES (21, '2022-07-30 22:35:30', 5, 1, NULL, 1);
INSERT INTO `grn` VALUES (22, '2022-07-30 23:17:56', 5, 1, 1100, 1);
INSERT INTO `grn` VALUES (23, '2022-07-30 23:20:18', 5, 1, 2090, 1);
INSERT INTO `grn` VALUES (24, '2022-07-30 23:23:21', 5, 1, 1100, 1);
INSERT INTO `grn` VALUES (25, '2022-08-02 13:44:40', 5, 1, 66000, 1);
INSERT INTO `grn` VALUES (26, '2022-08-06 00:50:32', 5, 1, 2200, 1);
INSERT INTO `grn` VALUES (27, '2022-08-06 00:55:04', 6, 1, 10000, 1);
INSERT INTO `grn` VALUES (28, '2022-08-06 00:58:49', 6, 1, 1100, 1);
INSERT INTO `grn` VALUES (29, '2022-08-06 01:03:06', 5, 1, 1100, 1);
INSERT INTO `grn` VALUES (30, '2022-08-06 01:11:12', 6, 1, 1100, 1);
INSERT INTO `grn` VALUES (31, '2022-08-06 01:15:23', 5, 1, 1100, 1);
INSERT INTO `grn` VALUES (32, '2022-08-06 12:42:56', 6, 1, 11000, 1);
INSERT INTO `grn` VALUES (33, '2022-08-07 12:56:23', 5, 1, NULL, 1);
INSERT INTO `grn` VALUES (34, '2022-08-09 00:11:50', 5, 1, 1500, 2);
INSERT INTO `grn` VALUES (35, '2022-08-09 01:23:31', 5, 1, 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for grn_item
-- ----------------------------
DROP TABLE IF EXISTS `grn_item`;
CREATE TABLE `grn_item` (
  `grn_id` int DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `bprice` double NOT NULL,
  `stock_id` int NOT NULL,
  `qty` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_grn_item_grn1_idx` (`grn_id`),
  KEY `fk_grn_item_stock1_idx` (`stock_id`),
  CONSTRAINT `fk_grn_item_grn1` FOREIGN KEY (`grn_id`) REFERENCES `grn` (`id`),
  CONSTRAINT `fk_grn_item_stock1` FOREIGN KEY (`stock_id`) REFERENCES `stock` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of grn_item
-- ----------------------------
BEGIN;
INSERT INTO `grn_item` VALUES (8, 7, 10, 9, 30);
INSERT INTO `grn_item` VALUES (9, 8, 15, 6, 10);
INSERT INTO `grn_item` VALUES (8, 9, 15, 10, 10);
INSERT INTO `grn_item` VALUES (8, 11, 15, 10, 10);
INSERT INTO `grn_item` VALUES (8, 12, 15, 10, 10);
INSERT INTO `grn_item` VALUES (8, 14, 15, 10, 10);
INSERT INTO `grn_item` VALUES (8, 15, 15, 10, 10);
INSERT INTO `grn_item` VALUES (9, 16, 500, 11, 10);
INSERT INTO `grn_item` VALUES (9, 17, 500, 11, 10);
INSERT INTO `grn_item` VALUES (9, 18, 500, 11, 10);
INSERT INTO `grn_item` VALUES (9, 19, 500, 11, 10);
INSERT INTO `grn_item` VALUES (10, 20, 100, 12, 10);
INSERT INTO `grn_item` VALUES (10, 21, 100, 12, 10);
INSERT INTO `grn_item` VALUES (10, 22, 100, 12, 10);
INSERT INTO `grn_item` VALUES (11, 23, 100, 12, 10);
INSERT INTO `grn_item` VALUES (11, 24, 100, 12, 10);
INSERT INTO `grn_item` VALUES (12, 25, 100, 12, 10);
INSERT INTO `grn_item` VALUES (12, 26, 100, 12, 10);
INSERT INTO `grn_item` VALUES (12, 27, 100, 12, 10);
INSERT INTO `grn_item` VALUES (12, 28, 100, 12, 10);
INSERT INTO `grn_item` VALUES (13, 29, 100, 12, 10);
INSERT INTO `grn_item` VALUES (13, 30, 100, 12, 10);
INSERT INTO `grn_item` VALUES (13, 31, 100, 12, 10);
INSERT INTO `grn_item` VALUES (13, 32, 100, 12, 10);
INSERT INTO `grn_item` VALUES (14, 33, 100, 12, 10);
INSERT INTO `grn_item` VALUES (14, 34, 100, 12, 10);
INSERT INTO `grn_item` VALUES (15, 35, 100, 13, 100);
INSERT INTO `grn_item` VALUES (15, 36, 100, 13, 100);
INSERT INTO `grn_item` VALUES (16, 37, 100, 13, 10);
INSERT INTO `grn_item` VALUES (16, 38, 100, 13, 10);
INSERT INTO `grn_item` VALUES (17, 39, 110, 13, 100);
INSERT INTO `grn_item` VALUES (18, 40, 110, 13, 10);
INSERT INTO `grn_item` VALUES (18, 41, 110, 13, 10);
INSERT INTO `grn_item` VALUES (18, 42, 100, 12, 10);
INSERT INTO `grn_item` VALUES (18, 43, 100, 12, 10);
INSERT INTO `grn_item` VALUES (18, 44, 100, 12, 10);
INSERT INTO `grn_item` VALUES (18, 45, 100, 12, 10);
INSERT INTO `grn_item` VALUES (18, 46, 100, 12, 10);
INSERT INTO `grn_item` VALUES (18, 47, 110, 13, 10);
INSERT INTO `grn_item` VALUES (18, 48, 100, 12, 10);
INSERT INTO `grn_item` VALUES (18, 49, 100, 12, 10);
INSERT INTO `grn_item` VALUES (18, 50, 100, 12, 10);
INSERT INTO `grn_item` VALUES (18, 51, 100, 12, 10);
INSERT INTO `grn_item` VALUES (18, 52, 100, 12, 10);
INSERT INTO `grn_item` VALUES (18, 53, 110, 13, 10);
INSERT INTO `grn_item` VALUES (18, 54, 100, 12, 10);
INSERT INTO `grn_item` VALUES (18, 55, 100, 12, 10);
INSERT INTO `grn_item` VALUES (18, 56, 100, 12, 10);
INSERT INTO `grn_item` VALUES (18, 57, 100, 12, 10);
INSERT INTO `grn_item` VALUES (18, 58, 100, 12, 10);
INSERT INTO `grn_item` VALUES (19, 59, 110, 13, 10);
INSERT INTO `grn_item` VALUES (19, 60, 110, 13, 10);
INSERT INTO `grn_item` VALUES (19, 61, 110, 13, 10);
INSERT INTO `grn_item` VALUES (19, 62, 110, 13, 10);
INSERT INTO `grn_item` VALUES (19, 63, 110, 13, 10);
INSERT INTO `grn_item` VALUES (19, 64, 110, 13, 10);
INSERT INTO `grn_item` VALUES (20, 65, 11, 14, 100);
INSERT INTO `grn_item` VALUES (20, 66, 11, 14, 100);
INSERT INTO `grn_item` VALUES (20, 67, 11, 14, 100);
INSERT INTO `grn_item` VALUES (20, 68, 11, 14, 100);
INSERT INTO `grn_item` VALUES (21, 69, 11, 14, 100);
INSERT INTO `grn_item` VALUES (21, 70, 11, 14, 100);
INSERT INTO `grn_item` VALUES (21, 71, 11, 14, 100);
INSERT INTO `grn_item` VALUES (21, 72, 11, 14, 100);
INSERT INTO `grn_item` VALUES (21, 73, 11, 14, 100);
INSERT INTO `grn_item` VALUES (21, 74, 11, 14, 100);
INSERT INTO `grn_item` VALUES (21, 75, 11, 14, 100);
INSERT INTO `grn_item` VALUES (21, 76, 11, 14, 100);
INSERT INTO `grn_item` VALUES (22, 82, 11, 14, 100);
INSERT INTO `grn_item` VALUES (23, 83, 11, 14, 190);
INSERT INTO `grn_item` VALUES (24, 84, 11, 14, 100);
INSERT INTO `grn_item` VALUES (25, 85, 110, 15, 100);
INSERT INTO `grn_item` VALUES (25, 86, 110, 15, 100);
INSERT INTO `grn_item` VALUES (25, 87, 110, 15, 100);
INSERT INTO `grn_item` VALUES (25, 88, 110, 15, 100);
INSERT INTO `grn_item` VALUES (25, 89, 110, 15, 100);
INSERT INTO `grn_item` VALUES (25, 90, 110, 15, 100);
INSERT INTO `grn_item` VALUES (26, 91, 110, 15, 10);
INSERT INTO `grn_item` VALUES (26, 92, 110, 15, 10);
INSERT INTO `grn_item` VALUES (27, 93, 100, 12, 100);
INSERT INTO `grn_item` VALUES (28, 94, 110, 15, 10);
INSERT INTO `grn_item` VALUES (29, 95, 110, 15, 10);
INSERT INTO `grn_item` VALUES (30, 96, 110, 15, 10);
INSERT INTO `grn_item` VALUES (31, 97, 110, 15, 10);
INSERT INTO `grn_item` VALUES (32, 98, 110, 15, 100);
INSERT INTO `grn_item` VALUES (34, 99, 50, 16, 30);
COMMIT;

-- ----------------------------
-- Table structure for grn_payment
-- ----------------------------
DROP TABLE IF EXISTS `grn_payment`;
CREATE TABLE `grn_payment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `grn_id` int NOT NULL,
  `payment_type_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_grn_payment_grn1_idx` (`grn_id`),
  KEY `fk_grn_payment_payment_type1_idx` (`payment_type_id`),
  CONSTRAINT `fk_grn_payment_grn1` FOREIGN KEY (`grn_id`) REFERENCES `grn` (`id`),
  CONSTRAINT `fk_grn_payment_payment_type1` FOREIGN KEY (`payment_type_id`) REFERENCES `payment_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of grn_payment
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for invoice
-- ----------------------------
DROP TABLE IF EXISTS `invoice`;
CREATE TABLE `invoice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `payment_type_id` int NOT NULL,
  `customer_id` int NOT NULL,
  `employees_id` int NOT NULL,
  `price` double DEFAULT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_invoice_payment_type1_idx` (`payment_type_id`),
  KEY `fk_invoice_customers1_idx` (`customer_id`),
  KEY `fk_invoice_employees1_idx` (`employees_id`),
  CONSTRAINT `fk_invoice_customers1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  CONSTRAINT `fk_invoice_employees1` FOREIGN KEY (`employees_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `fk_invoice_payment_type1` FOREIGN KEY (`payment_type_id`) REFERENCES `payment_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of invoice
-- ----------------------------
BEGIN;
INSERT INTO `invoice` VALUES (1, 5, 1, 1, 31, '2022-07-26 13:13:34');
INSERT INTO `invoice` VALUES (2, 5, 1, 1, 130, '2022-07-24 13:14:54');
INSERT INTO `invoice` VALUES (3, 7, 2, 1, 1545, '2022-07-26 13:26:39');
INSERT INTO `invoice` VALUES (4, 7, 2, 1, 5750, '2022-07-27 06:17:45');
INSERT INTO `invoice` VALUES (5, 7, 2, 10, 755, '2022-07-30 03:08:16');
INSERT INTO `invoice` VALUES (6, 5, 2, 10, 1130, '2022-07-30 03:10:41');
INSERT INTO `invoice` VALUES (7, 7, 2, 10, 2815, '2022-07-30 03:13:35');
INSERT INTO `invoice` VALUES (8, 7, 2, 10, 40, '2022-07-30 03:16:49');
INSERT INTO `invoice` VALUES (9, 5, 1, 10, 40, '2022-07-30 03:19:32');
INSERT INTO `invoice` VALUES (10, 7, 2, 10, 40, '2022-07-30 03:19:49');
INSERT INTO `invoice` VALUES (11, 7, 2, 10, 40, '2022-07-30 03:22:12');
INSERT INTO `invoice` VALUES (12, 7, 2, 10, 40, '2022-07-30 03:25:45');
INSERT INTO `invoice` VALUES (13, 5, 2, 10, 40, '2022-07-30 03:32:52');
INSERT INTO `invoice` VALUES (14, 5, 2, 10, 40, '2022-07-30 03:33:04');
INSERT INTO `invoice` VALUES (15, 5, 2, 10, 20, '2022-07-30 03:34:29');
INSERT INTO `invoice` VALUES (16, 7, 2, 10, 220, '2022-07-30 03:52:09');
INSERT INTO `invoice` VALUES (17, 7, 2, 10, 220, '2022-07-30 04:03:42');
INSERT INTO `invoice` VALUES (18, 7, 2, 10, 220, '2022-07-30 04:09:50');
INSERT INTO `invoice` VALUES (19, 5, 1, 10, 220, '2022-07-30 04:20:02');
INSERT INTO `invoice` VALUES (20, 7, 2, 10, 220, '2022-07-30 04:21:59');
INSERT INTO `invoice` VALUES (21, 7, 2, 10, 222, '2022-07-30 04:29:07');
INSERT INTO `invoice` VALUES (22, 7, 2, 10, 2210, '2022-07-30 13:27:14');
INSERT INTO `invoice` VALUES (23, 5, 2, 11, 120, '2022-07-30 23:24:08');
INSERT INTO `invoice` VALUES (24, 5, 2, 11, 120, '2022-07-30 23:25:40');
INSERT INTO `invoice` VALUES (25, 5, 1, 11, 12, '2022-08-05 22:39:38');
INSERT INTO `invoice` VALUES (26, 5, 1, 11, 12, '2022-08-05 22:56:39');
INSERT INTO `invoice` VALUES (27, 5, 1, 11, 12, '2022-08-05 22:56:45');
INSERT INTO `invoice` VALUES (28, 5, 1, 11, 12, '2022-08-05 22:56:59');
INSERT INTO `invoice` VALUES (29, 5, 2, 11, 12, '2022-08-05 22:58:11');
INSERT INTO `invoice` VALUES (30, 5, 2, 11, 12, '2022-08-05 23:17:46');
INSERT INTO `invoice` VALUES (31, 5, 2, 11, 12, '2022-08-06 00:25:28');
INSERT INTO `invoice` VALUES (32, 5, 1, 11, 12, '2022-08-06 00:29:53');
INSERT INTO `invoice` VALUES (33, 5, 1, 11, 12, '2022-08-06 00:30:28');
INSERT INTO `invoice` VALUES (34, 5, 3, 11, 12, '2022-08-06 00:37:28');
INSERT INTO `invoice` VALUES (35, 5, 4, 11, 12, '2022-08-06 00:40:26');
INSERT INTO `invoice` VALUES (36, 5, 3, 11, 12, '2022-08-06 00:45:33');
INSERT INTO `invoice` VALUES (37, 7, 2, 11, 120, '2022-08-06 01:16:58');
INSERT INTO `invoice` VALUES (38, 7, 2, 11, 1200, '2022-08-06 12:44:55');
COMMIT;

-- ----------------------------
-- Table structure for invoice_item
-- ----------------------------
DROP TABLE IF EXISTS `invoice_item`;
CREATE TABLE `invoice_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `invoice_id` int NOT NULL,
  `grn_item_id` int DEFAULT NULL,
  `qty` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_invoice_item_product1_idx` (`product_id`),
  KEY `fk_invoice_item_invoice1_idx` (`invoice_id`),
  KEY `grn_item_id` (`grn_item_id`),
  CONSTRAINT `fk_invoice_item_invoice1` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`),
  CONSTRAINT `fk_invoice_item_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `invoice_item_ibfk_1` FOREIGN KEY (`grn_item_id`) REFERENCES `grn_item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of invoice_item
-- ----------------------------
BEGIN;
INSERT INTO `invoice_item` VALUES (10, 5, 1, 7, 0);
INSERT INTO `invoice_item` VALUES (11, 6, 1, 9, 1);
INSERT INTO `invoice_item` VALUES (12, 6, 2, 9, 0);
INSERT INTO `invoice_item` VALUES (13, 5, 2, 8, 0);
INSERT INTO `invoice_item` VALUES (14, 5, 3, 8, 0);
INSERT INTO `invoice_item` VALUES (15, 7, 3, 16, 0);
INSERT INTO `invoice_item` VALUES (16, 6, 4, 9, 10);
INSERT INTO `invoice_item` VALUES (17, 7, 4, 16, 10);
INSERT INTO `invoice_item` VALUES (19, 6, 5, 9, 10);
INSERT INTO `invoice_item` VALUES (20, 7, 5, 16, 1);
INSERT INTO `invoice_item` VALUES (21, 6, 6, 9, 1);
INSERT INTO `invoice_item` VALUES (22, 7, 6, 16, 2);
INSERT INTO `invoice_item` VALUES (23, 7, 7, 16, 5);
INSERT INTO `invoice_item` VALUES (24, 6, 7, 9, 2);
INSERT INTO `invoice_item` VALUES (25, 6, 8, 9, 2);
INSERT INTO `invoice_item` VALUES (26, 6, 9, 9, 2);
INSERT INTO `invoice_item` VALUES (27, 6, 10, 9, 2);
INSERT INTO `invoice_item` VALUES (28, 6, 11, 9, 2);
INSERT INTO `invoice_item` VALUES (29, 6, 12, 9, 2);
INSERT INTO `invoice_item` VALUES (30, 6, 13, 9, 2);
INSERT INTO `invoice_item` VALUES (31, 6, 14, 9, 2);
INSERT INTO `invoice_item` VALUES (32, 6, 15, 9, 1);
INSERT INTO `invoice_item` VALUES (33, 9, 16, 20, 2);
INSERT INTO `invoice_item` VALUES (34, 9, 17, 20, 2);
INSERT INTO `invoice_item` VALUES (35, 9, 18, 20, 2);
INSERT INTO `invoice_item` VALUES (36, 9, 19, 20, 2);
INSERT INTO `invoice_item` VALUES (37, 9, 20, 20, 2);
INSERT INTO `invoice_item` VALUES (38, 10, 21, 35, 2);
INSERT INTO `invoice_item` VALUES (39, 10, 22, 35, 10);
INSERT INTO `invoice_item` VALUES (40, 9, 22, 20, 10);
INSERT INTO `invoice_item` VALUES (41, 10, 23, 65, 10);
INSERT INTO `invoice_item` VALUES (42, 10, 24, 65, 10);
INSERT INTO `invoice_item` VALUES (43, 10, 25, 65, 1);
INSERT INTO `invoice_item` VALUES (44, 10, 26, 65, 1);
INSERT INTO `invoice_item` VALUES (45, 10, 27, 65, 1);
INSERT INTO `invoice_item` VALUES (46, 10, 28, 65, 1);
INSERT INTO `invoice_item` VALUES (47, 10, 29, 65, 1);
INSERT INTO `invoice_item` VALUES (48, 10, 30, 65, 1);
INSERT INTO `invoice_item` VALUES (49, 10, 31, 65, 1);
INSERT INTO `invoice_item` VALUES (50, 10, 32, 65, 1);
INSERT INTO `invoice_item` VALUES (51, 10, 33, 65, 1);
INSERT INTO `invoice_item` VALUES (52, 10, 34, 65, 1);
INSERT INTO `invoice_item` VALUES (53, 10, 35, 65, 1);
INSERT INTO `invoice_item` VALUES (54, 10, 36, 65, 1);
INSERT INTO `invoice_item` VALUES (55, 10, 37, 65, 10);
INSERT INTO `invoice_item` VALUES (56, 10, 38, 65, 100);
COMMIT;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  `who` varchar(50) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for material
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of material
-- ----------------------------
BEGIN;
INSERT INTO `material` VALUES (6, 'cotten');
INSERT INTO `material` VALUES (7, 'nilon');
COMMIT;

-- ----------------------------
-- Table structure for payment_type
-- ----------------------------
DROP TABLE IF EXISTS `payment_type`;
CREATE TABLE `payment_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of payment_type
-- ----------------------------
BEGIN;
INSERT INTO `payment_type` VALUES (5, 'card');
INSERT INTO `payment_type` VALUES (7, 'cash');
COMMIT;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` text,
  `category_id` int NOT NULL,
  `brand_id` int NOT NULL,
  `gender_id` int NOT NULL,
  `age_id` int NOT NULL,
  `availability` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_product_category1_idx` (`category_id`),
  KEY `fk_product_brand1_idx` (`brand_id`),
  KEY `fk_product_gender1_idx` (`gender_id`),
  KEY `fk_product_age1_idx` (`age_id`),
  KEY `availability` (`availability`),
  CONSTRAINT `fk_product_age1` FOREIGN KEY (`age_id`) REFERENCES `age` (`id`),
  CONSTRAINT `fk_product_brand1` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`),
  CONSTRAINT `fk_product_gender1` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`availability`) REFERENCES `availability` (`id`),
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of product
-- ----------------------------
BEGIN;
INSERT INTO `product` VALUES (5, 'uest produ', 'test descripti', 2, 5, 5, 13, 1);
INSERT INTO `product` VALUES (6, 'test', 'test d', 1, 4, 6, 12, 2);
INSERT INTO `product` VALUES (7, 'testpp', 'des', 1, 5, 6, 12, 1);
INSERT INTO `product` VALUES (8, 'tet', 'ihbxobwelxh;ewb;coenc;oiejf] epokv ]epovk\neplv\n[p]peowkroekrp]wcm][]wpc,w', 1, 5, 5, 12, 1);
INSERT INTO `product` VALUES (9, 'testp', 'ihwbwbchwiuhbcopwhicnowiec', 1, 4, 5, 12, 2);
INSERT INTO `product` VALUES (10, 'test2', 'sdalk', 1, 4, 5, 12, 1);
COMMIT;

-- ----------------------------
-- Table structure for product_colour
-- ----------------------------
DROP TABLE IF EXISTS `product_colour`;
CREATE TABLE `product_colour` (
  `product_id` int NOT NULL,
  `colour_id` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_product_has_colour_colour1_idx` (`colour_id`),
  KEY `fk_product_has_colour_product1_idx` (`product_id`),
  CONSTRAINT `fk_product_has_colour_colour1` FOREIGN KEY (`colour_id`) REFERENCES `colour` (`id`),
  CONSTRAINT `fk_product_has_colour_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of product_colour
-- ----------------------------
BEGIN;
INSERT INTO `product_colour` VALUES (5, 5, 5);
INSERT INTO `product_colour` VALUES (6, 5, 6);
INSERT INTO `product_colour` VALUES (7, 5, 7);
INSERT INTO `product_colour` VALUES (8, 5, 8);
INSERT INTO `product_colour` VALUES (9, 5, 9);
INSERT INTO `product_colour` VALUES (10, 5, 10);
COMMIT;

-- ----------------------------
-- Table structure for product_img
-- ----------------------------
DROP TABLE IF EXISTS `product_img`;
CREATE TABLE `product_img` (
  `product_id` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(100) DEFAULT NULL,
  `type` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_product_img_product1_idx` (`product_id`),
  CONSTRAINT `fk_product_img_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of product_img
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product_material
-- ----------------------------
DROP TABLE IF EXISTS `product_material`;
CREATE TABLE `product_material` (
  `product_id` int NOT NULL,
  `material_id` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_product_has_material_material1_idx` (`material_id`),
  KEY `fk_product_has_material_product1_idx` (`product_id`),
  CONSTRAINT `fk_product_has_material_material1` FOREIGN KEY (`material_id`) REFERENCES `material` (`id`),
  CONSTRAINT `fk_product_has_material_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of product_material
-- ----------------------------
BEGIN;
INSERT INTO `product_material` VALUES (5, 6, 4);
INSERT INTO `product_material` VALUES (6, 6, 5);
INSERT INTO `product_material` VALUES (7, 7, 6);
INSERT INTO `product_material` VALUES (8, 6, 7);
INSERT INTO `product_material` VALUES (9, 7, 8);
INSERT INTO `product_material` VALUES (10, 6, 9);
COMMIT;

-- ----------------------------
-- Table structure for product_size
-- ----------------------------
DROP TABLE IF EXISTS `product_size`;
CREATE TABLE `product_size` (
  `product_id` int NOT NULL,
  `size_id` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_product_has_size_size1_idx` (`size_id`),
  KEY `fk_product_has_size_product1_idx` (`product_id`),
  CONSTRAINT `fk_product_has_size_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_product_has_size_size1` FOREIGN KEY (`size_id`) REFERENCES `size` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of product_size
-- ----------------------------
BEGIN;
INSERT INTO `product_size` VALUES (5, 4, 5);
INSERT INTO `product_size` VALUES (6, 4, 6);
INSERT INTO `product_size` VALUES (7, 4, 7);
INSERT INTO `product_size` VALUES (8, 5, 8);
INSERT INTO `product_size` VALUES (9, 4, 9);
INSERT INTO `product_size` VALUES (10, 4, 10);
COMMIT;

-- ----------------------------
-- Table structure for profile_img
-- ----------------------------
DROP TABLE IF EXISTS `profile_img`;
CREATE TABLE `profile_img` (
  `id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of profile_img
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for reports
-- ----------------------------
DROP TABLE IF EXISTS `reports`;
CREATE TABLE `reports` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int DEFAULT '1',
  `employees_id` int DEFAULT NULL,
  `message` text CHARACTER SET utf8mb3 COLLATE utf8_general_ci,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  KEY `employee_id` (`employees_id`),
  CONSTRAINT `reports_ibfk_1` FOREIGN KEY (`status`) REFERENCES `availability` (`id`),
  CONSTRAINT `reports_ibfk_2` FOREIGN KEY (`employees_id`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of reports
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for size
-- ----------------------------
DROP TABLE IF EXISTS `size`;
CREATE TABLE `size` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of size
-- ----------------------------
BEGIN;
INSERT INTO `size` VALUES (4, 'xl');
INSERT INTO `size` VALUES (5, 's');
COMMIT;

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `product_id` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `sprice` double NOT NULL,
  `qty` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_stock_product1_idx` (`product_id`),
  CONSTRAINT `fk_stock_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of stock
-- ----------------------------
BEGIN;
INSERT INTO `stock` VALUES (5, 6, 110, 0);
INSERT INTO `stock` VALUES (6, 7, 110, 10);
INSERT INTO `stock` VALUES (5, 8, 0.6, 1);
INSERT INTO `stock` VALUES (5, 9, 11, 0);
INSERT INTO `stock` VALUES (6, 10, 20, 0);
INSERT INTO `stock` VALUES (7, 11, 555, 0);
INSERT INTO `stock` VALUES (9, 12, 110, 380);
INSERT INTO `stock` VALUES (10, 13, 111, 408);
INSERT INTO `stock` VALUES (10, 14, 12, 1498);
INSERT INTO `stock` VALUES (10, 15, 120, 760);
INSERT INTO `stock` VALUES (10, 16, 100, 30);
COMMIT;

-- ----------------------------
-- Table structure for suppliers
-- ----------------------------
DROP TABLE IF EXISTS `suppliers`;
CREATE TABLE `suppliers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fname` varchar(45) DEFAULT NULL,
  `lname` varchar(45) DEFAULT NULL,
  `mobile` varchar(12) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `branch_id` int NOT NULL,
  `company_id` int NOT NULL,
  `gender_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_suppliers_city1_idx` (`branch_id`),
  KEY `fk_suppliers_company1_idx` (`company_id`),
  KEY `fk_suppliers_gender1_idx` (`gender_id`),
  CONSTRAINT `fk_suppliers_company1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `fk_suppliers_gender1` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`id`),
  CONSTRAINT `suppliers_ibfk_1` FOREIGN KEY (`branch_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of suppliers
-- ----------------------------
BEGIN;
INSERT INTO `suppliers` VALUES (5, 'praboth', 'charith', '0754002344', 'ojedn@gmail.com', 7, 9, 5);
INSERT INTO `suppliers` VALUES (6, 'test', 'test', '0777877767', 'hvligvliyfylfv', 9, 10, 6);
INSERT INTO `suppliers` VALUES (7, 'te2', 'ts2', '0777382638', 'wjbfe@gmail.com', 7, 11, 5);
INSERT INTO `suppliers` VALUES (8, 'new supplier', 'new supplier2', '0753983774', 'iqwhiw@gmail.com', 7, 12, 5);
INSERT INTO `suppliers` VALUES (9, 'chatura', 'rathnayake', '0754002299', 'klwden @gmail.com', 11, 10, 5);
COMMIT;

-- ----------------------------
-- Table structure for system_log
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(20) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of system_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(16) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(32) NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
