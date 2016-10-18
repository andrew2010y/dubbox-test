/*
SQLyog Enterprise Trial - MySQL GUI v8.05 
MySQL - 5.5.29 : Database - dubbo_test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`dubbo_test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `dubbo_test`;

/*Table structure for table `t_user` */

CREATE TABLE `t_user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` char(30) NOT NULL,
  `USER_PASSWORD` char(10) NOT NULL,
  `USER_EMAIL` char(30) NOT NULL,
  PRIMARY KEY (`USER_ID`),
  KEY `IDX_NAME` (`USER_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`USER_ID`,`USER_NAME`,`USER_PASSWORD`,`USER_EMAIL`) values (2,'evan','123','fff@126.com'),(3,'kaka','cadg','fwsfg@126.com'),(4,'simle','ddd','fsaf@126.com'),(7,'小小','cvff','fsaf@126.com'),(11,'qwerr','','qqq'),(12,'12345678','1','1'),(13,'www','w','w'),(14,'1111','111111','1111');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
