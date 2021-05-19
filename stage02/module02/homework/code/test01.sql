/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 8.0.23 : Database - test01
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test01` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `test01`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `card` varchar(100) DEFAULT NULL,
  `balance` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `account` */

insert  into `account`(`id`,`username`,`card`,`balance`) values 
(1,'tom','1122334455',20000),
(2,'lucy','55443332211',10000);

/*Table structure for table `phone` */

DROP TABLE IF EXISTS `phone`;

CREATE TABLE `phone` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pname` varchar(20) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `prodate` date DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `phone` */

insert  into `phone`(`id`,`pname`,`price`,`prodate`,`color`) values 
(1,'IPhone11',7800,'2019-07-20','土豪金'),
(2,'荣耀6X',5689,'2018-02-12','白色'),
(3,'诺基亚3',5699,'2011-12-05','银白色'),
(4,'红米6',599,'2017-01-18','香槟金');

/*Table structure for table `transaction` */

DROP TABLE IF EXISTS `transaction`;

CREATE TABLE `transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cardid` varchar(100) DEFAULT NULL,
  `tratype` varchar(100) DEFAULT NULL,
  `tramoney` double DEFAULT NULL,
  `tradate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `transaction` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
