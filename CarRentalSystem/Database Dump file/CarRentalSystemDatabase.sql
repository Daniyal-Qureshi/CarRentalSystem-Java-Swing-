/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.5.25 : Database - carrentalsystem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`carrentalsystem` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `carrentalsystem`;

/*Table structure for table `booking` */

DROP TABLE IF EXISTS `booking`;

CREATE TABLE `booking` (
  `CarID` int(128) NOT NULL,
  `username` varchar(128) DEFAULT NULL,
  KEY `CarID` (`CarID`),
  KEY `username` (`username`),
  CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`CarID`) REFERENCES `car` (`CarID`),
  CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`username`) REFERENCES `customer` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `booking` */

LOCK TABLES `booking` WRITE;

insert  into `booking`(`CarID`,`username`) values (933230269,'1'),(7444,'1'),(1494010084,'talha');

UNLOCK TABLES;

/*Table structure for table `car` */

DROP TABLE IF EXISTS `car`;

CREATE TABLE `car` (
  `CarID` int(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `rentprice` int(50) DEFAULT NULL,
  `status` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`CarID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `car` */

LOCK TABLES `car` WRITE;

insert  into `car`(`CarID`,`name`,`rentprice`,`status`) values (7444,'RangeOver',140000,'Reserved'),(933230269,'Porche',14000,'Reserved'),(1494010084,'Lamborghini',1000,'Reserved');

UNLOCK TABLES;

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `username` varchar(128) NOT NULL,
  `FullName` varchar(128) DEFAULT NULL,
  `Userpassword` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `customer` */

LOCK TABLES `customer` WRITE;

insert  into `customer`(`username`,`FullName`,`Userpassword`) values ('1','Daniyal','1'),('Dani','Daniyal','sdsds'),('talha','Talha ','1234');

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
