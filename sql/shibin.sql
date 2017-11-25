/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.12-log : Database - weborders
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`weborders` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `weborders`;

/*Table structure for table `cart` */

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品ID',
  `number` int(10) unsigned DEFAULT NULL COMMENT '数量',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `goods_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `cart` */

insert  into `cart`(`cart_id`,`goods_id`,`number`,`user_id`,`goods_name`) values (10,32,2,2,'电脑');

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `goods_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(20) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `stock` int(10) unsigned DEFAULT NULL,
  `picture` varchar(20) DEFAULT NULL COMMENT '图片地址',
  `describes` varchar(20) DEFAULT NULL COMMENT '详情',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

insert  into `goods`(`goods_id`,`goods_name`,`price`,`stock`,`picture`,`describes`) values (93,'a',50,50,'fruits.jpg',''),(94,'iphone',5999,100,'iphone.jpg','苹果手机，手机中的战斗机'),(95,'水果',10,9000,'fruits.jpg','便宜又实惠'),(96,'越南美女',40000,5,'girl.jpg','上得厅堂，下得厨房 '),(97,'饮料',50,1000,'drinks.jpg','喝了好干活 '),(98,'电脑',3888,50,'pc.jpg','笔记本电脑 '),(99,'香烟',20,600,'yan.jpg','吸烟有害健康 ');

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `orders_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `status` int(2) DEFAULT '1' COMMENT '状态默认1为未付',
  `money` float DEFAULT NULL COMMENT '总价',
  `createtime` varchar(20) NOT NULL COMMENT '创建时间',
  `paymenttime` varchar(20) NOT NULL DEFAULT '未付款' COMMENT '付款时间',
  `user_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`orders_id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`orders_id`,`user_id`,`status`,`money`,`createtime`,`paymenttime`,`user_name`) values (88,1,2,10,'2017-08-24 19:54:40','2017-08-24 19:54:43','root'),(89,1,2,5999,'2017-08-25 08:54:46','2017-08-25 08:54:51','root'),(90,1,1,50,'2017-08-25 09:42:53','未付款','root'),(91,1,2,50,'2017-08-25 15:58:04','2017-08-25 16:05:39','root'),(92,1,2,50,'2017-08-25 16:09:12','2017-08-25 16:11:03','root'),(93,1,2,10,'2017-08-25 16:12:48','2017-08-25 16:13:48','root'),(94,1,1,6099,'2017-08-25 18:34:41','未付款','root'),(95,1,2,50,'2017-08-25 18:46:20','2017-08-25 18:46:30','root'),(96,2,2,10,'2017-08-25 18:47:12','2017-08-25 18:47:16','user'),(97,2,2,110,'2017-08-25 18:48:21','2017-08-25 18:48:26','user'),(99,2,2,49897,'2017-08-25 18:48:42','2017-08-28 20:21:25','user'),(100,2,2,50,'2017-08-26 10:44:40','2017-08-26 10:44:45','user'),(101,1,2,50,'2017-08-28 09:07:25','2017-08-28 09:07:35','root'),(102,1,2,6099,'2017-08-28 11:20:05','2017-08-28 11:20:11','root'),(103,1,2,80000,'2017-08-28 16:35:56','2017-08-30 15:39:47','root'),(106,1,1,10,'2017-08-28 16:42:28','未付款','root'),(107,2,2,23782,'2017-08-28 20:19:22','2017-08-28 20:21:41','user'),(108,2,1,0,'2017-08-28 20:19:30','未付款','user'),(109,2,1,0,'2017-08-28 20:19:40','未付款','user'),(110,2,2,3888,'2017-08-28 20:19:47','2017-08-28 20:21:53','user'),(111,2,2,3888,'2017-08-28 20:20:04','2017-08-28 20:20:16','user'),(112,1,1,50,'2017-08-30 15:41:36','未付款','root');

/*Table structure for table `orders_goods` */

DROP TABLE IF EXISTS `orders_goods`;

CREATE TABLE `orders_goods` (
  `og_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品ID',
  `number` int(10) unsigned DEFAULT NULL COMMENT '数量',
  `price` float DEFAULT NULL COMMENT '单价',
  `orders_id` int(11) DEFAULT NULL COMMENT '订单ID',
  `goods_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`og_id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8;

/*Data for the table `orders_goods` */

insert  into `orders_goods`(`og_id`,`goods_id`,`number`,`price`,`orders_id`,`goods_name`) values (1,29,1,10,0,'水果'),(2,30,1,40000,11,'越南美女'),(3,29,1,10,11,'水果'),(4,30,1,40000,12,'越南美女'),(5,29,1,10,13,'水果'),(6,28,1,5999,13,'iphone'),(7,33,1,20,14,'烟'),(8,30,1,40000,15,'越南美女'),(9,30,1,40000,16,'越南美女'),(10,28,1,5999,19,'iphone'),(11,30,1,40000,27,'越南美女'),(12,30,1,40000,30,'越南美女'),(13,30,1,40000,32,'越南美女'),(14,30,1,40000,35,'越南美女'),(15,30,1,40000,38,'越南美女'),(16,30,1,40000,39,'越南美女'),(17,30,1,40000,41,'越南美女'),(18,30,1,40000,42,'越南美女'),(19,30,1,40000,43,'越南美女'),(20,29,1,10,44,'水果'),(21,30,1,40000,45,'越南美女'),(22,30,1,40000,46,'越南美女'),(23,30,1,40000,47,'越南美女'),(24,29,1,10,48,'水果'),(25,30,1,40000,48,'越南美女'),(26,33,1,20,48,'烟'),(27,28,1,5999,49,'iphone'),(28,29,1,10,51,'水果'),(29,28,1,5999,52,'iphone'),(30,29,1,10,53,'水果'),(31,29,1,10,54,'水果'),(32,29,1,10,55,'水果'),(33,29,1,10,56,'水果'),(34,28,1,5999,56,'iphone'),(35,31,1,50,57,'饮料'),(36,29,1,10,58,'水果'),(37,29,1,10,71,'水果'),(38,29,1,10,74,'水果'),(39,31,1,50,75,'饮料'),(40,36,1,40000,76,'越南美女'),(41,36,1,40000,77,'越南美女'),(42,36,1,40000,78,'越南美女'),(43,31,1,50,79,'饮料'),(44,31,1,50,80,'饮料'),(45,31,1,50,81,'饮料'),(46,29,1,10,82,'水果'),(47,34,1,5999,83,'iphone'),(48,31,1,50,84,'饮料'),(49,31,1,50,85,'饮料'),(50,29,1,10,86,'水果'),(51,31,1,50,87,'饮料'),(52,29,1,10,88,'水果'),(53,34,1,5999,89,'iphone'),(54,31,1,50,90,'饮料'),(55,31,1,50,91,'饮料'),(56,31,1,50,92,'饮料'),(57,29,1,10,93,'水果'),(58,31,1,50,94,'饮料'),(59,34,1,5999,94,'iphone'),(60,37,1,50,94,'饮料'),(61,31,1,50,95,'饮料'),(62,29,1,10,96,'水果'),(63,29,1,10,97,'水果'),(64,31,1,50,97,'饮料'),(65,37,1,50,97,'饮料'),(66,32,1,3888,98,'电脑'),(67,28,1,5999,99,'iphone'),(68,32,1,3888,99,'电脑'),(69,35,1,10,99,'水果'),(70,36,1,40000,99,'越南美女'),(71,31,1,50,100,'饮料'),(72,31,1,50,101,'饮料'),(73,34,1,5999,102,'iphone'),(74,31,2,50,102,'饮料'),(75,36,2,40000,103,'越南美女'),(76,29,1,10,104,'水果'),(77,29,1,10,106,'水果'),(78,32,3,3888,107,'电脑'),(79,31,2,50,107,'饮料'),(80,34,2,5999,107,'iphone'),(81,33,1,20,107,'烟'),(82,32,1,3888,110,'电脑'),(83,32,1,3888,111,'电脑'),(84,31,1,50,112,'饮料');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL,
  `age` int(10) unsigned DEFAULT NULL,
  `type` int(2) unsigned DEFAULT '1' COMMENT '权限',
  `password` varchar(64) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`user_name`,`age`,`type`,`password`,`phone`) values (11,'user',20,1,'202cb962ac59075b964b07152d234b70','888888'),(12,'root',25,2,'caf1a3dfb505ffed0d024130f58c5cfa','0777');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
