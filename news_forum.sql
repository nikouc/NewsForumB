/*
SQLyog  v12.2.6 (64 bit)
MySQL - 8.0.29 : Database - news_forum
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`news_forum` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `news_forum`;

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(30) DEFAULT NULL,
  `content` varchar(10000) DEFAULT NULL,
  `cover_img` varchar(128) DEFAULT NULL,
  `state` varchar(3) DEFAULT '草稿',
  `category_id` int DEFAULT NULL,
  `create_userid` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  KEY `create_userid` (`create_userid`),
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `create_userid` FOREIGN KEY (`create_userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `article` */

insert  into `article`(`id`,`title`,`content`,`cover_img`,`state`,`category_id`,`create_userid`,`create_time`,`update_time`) values 
(1,'bbbbb','ghwogbegoeiwgew1few65gf168ewf14we6g165erh56werg','https://fjiweghgdfoiwe.com','草稿',2,6,'2024-04-01 22:39:48','2024-04-01 22:39:48'),
(3,'dfefgj','<p>6666661111111</p>','https://fiwhghgewg.com','草稿',12,10,'2024-04-01 22:48:08','2024-04-12 05:18:18'),
(6,'fgwaew21f2','fgwegewgewgwe154156165erh56werg','https://fjoiwgwe.cn','已发布',2,6,'2024-04-02 01:12:33','2024-04-02 01:12:33'),
(7,'xzvcsdvsd','<p>dsfsdgsdgfsdf</p>','https://newsforum.oss-cn-guangzhou.aliyuncs.com/4cd6cc20-563e-4f88-a1a5-a082439190ce.jpg','已发布',12,10,'2024-04-11 21:17:02','2024-04-11 21:17:02'),
(8,'xzvcsdvsd','<p>dsfsdgsdgfsdf</p>','https://newsforum.oss-cn-guangzhou.aliyuncs.com/4cd6cc20-563e-4f88-a1a5-a082439190ce.jpg','已发布',12,10,'2024-04-11 21:18:09','2024-04-12 05:21:46'),
(10,'xzvcsdvsd','<p>dsfsdgsdgfsdf</p>','https://newsforum.oss-cn-guangzhou.aliyuncs.com/4cd6cc20-563e-4f88-a1a5-a082439190ce.jpg','已发布',12,10,'2024-04-11 21:18:13','2024-04-11 21:18:13'),
(11,'xzvcsdvsd','<p>dsfsdgsdgfsdf</p>','https://newsforum.oss-cn-guangzhou.aliyuncs.com/4cd6cc20-563e-4f88-a1a5-a082439190ce.jpg','草稿',12,10,'2024-04-11 21:18:14','2024-04-12 05:24:10'),
(12,'gfwaegeg','<p>gwegewgeg</p>','https://newsforum.oss-cn-guangzhou.aliyuncs.com/2d6ad749-a2a0-4c31-853d-adf6eee09105.jpg','已发布',12,10,'2024-04-11 21:50:28','2024-04-12 05:24:07'),
(13,'dsgfawgaew','<p>dgfawgh4werhgtttt</p>','https://newsforum.oss-cn-guangzhou.aliyuncs.com/d73a9a0b-48cb-4756-ad5f-556f06d24c33.jpg','已发布',11,10,'2024-04-11 21:53:03','2024-04-12 05:24:19'),
(14,'dfgdg','<p>dsfgweg</p>','https://newsforum.oss-cn-guangzhou.aliyuncs.com/4e4ee827-9e16-4e57-a83b-b266effc8cca.jpg','草稿',12,10,'2024-04-11 21:55:59','2024-04-12 05:24:01'),
(15,'1234567','<p>qwertyussss</p>','https://newsforum.oss-cn-guangzhou.aliyuncs.com/4cd6cc20-563e-4f88-a1a5-a082439190ce.jpg','已发布',12,10,'2024-04-12 04:51:46','2024-04-12 05:24:34'),
(16,'风格的放入豆腐','<p>地方都是三生三世十里桃花</p>','https://newsforum.oss-cn-guangzhou.aliyuncs.com/cfbefb20-a891-4fe2-ae5f-a24d87ec7e63.png','已发布',12,10,'2024-04-12 05:40:46','2024-04-12 05:41:16');

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `category_alias` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_user` int DEFAULT NULL,
  `creat_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `create_user` (`create_user`),
  CONSTRAINT `create_user` FOREIGN KEY (`create_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `category` */

insert  into `category`(`id`,`category_name`,`category_alias`,`create_user`,`creat_time`,`update_time`) values 
(1,'科技','kj',6,'2024-04-01 02:54:41','2024-04-01 02:54:41'),
(2,'趣闻','qw',6,'2024-04-01 02:57:44','2024-04-01 21:29:21'),
(4,'人文','rw',6,'2024-04-01 03:01:55','2024-04-01 03:01:55'),
(5,'政治','zz',6,'2024-04-01 21:38:24','2024-04-01 21:38:24'),
(6,'政治','zz',6,'2024-04-01 21:44:51','2024-04-01 21:44:51'),
(10,'政治','zz',6,'2024-04-09 08:38:15','2024-04-09 08:38:15'),
(11,'jfaso','dddddddd',10,'2024-04-09 09:18:25','2024-04-09 09:18:31'),
(12,'damn!','m3!',10,'2024-04-10 17:43:13','2024-04-10 17:43:13');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `nickname` varchar(10) DEFAULT 'null',
  `email` varchar(128) DEFAULT 'null',
  `user_pic` varchar(128) DEFAULT 'null',
  `creat_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`nickname`,`email`,`user_pic`,`creat_time`,`update_time`) values 
(1,'aaaaa','123456','null','null','null','2024-03-28 22:45:58','2024-03-28 22:45:58'),
(2,'bbbbbb','12315sfdf','null','null','null','2024-03-28 22:46:02','2024-03-28 22:46:03'),
(3,'cccccc','39e8deebdbdd223c28709570d99079b8','null','null','null','2024-03-29 01:27:33','2024-03-29 01:27:33'),
(4,'ddddddd','43d4e381d1a9be8c4869c1f3912c069c','null','null','null','2024-03-29 01:41:17','2024-03-29 01:41:17'),
(5,'eeeee','43d4e381d1a9be8c4869c1f3912c069c','null','null','null','2024-03-29 02:52:57','2024-03-29 02:52:57'),
(6,'abcddd','adcaec3805aa912c0d0b14a81bedb6ff','udfd','','http://dfjewwej.com','2024-03-29 03:48:35','2024-04-04 01:45:24'),
(7,'11111224','b0baee9d279d34fa1dfd71aadb908c3f','null','null','null','2024-04-07 16:08:39','2024-04-07 16:08:39'),
(8,'543245354','b0baee9d279d34fa1dfd71aadb908c3f','null','null','null','2024-04-07 16:10:03','2024-04-07 16:10:03'),
(9,'454545','79b7cdcd14db14e9cb498f1793817d69','null','null','null','2024-04-07 16:30:39','2024-04-07 16:30:39'),
(10,'55555','3d2172418ce305c7d16d4b05597c6a59','fewff','idshgo@kdgj.com','https://newsforum.oss-cn-guangzhou.aliyuncs.com/199fe498-4028-4ae0-ac0f-2db4a1e682b8.png','2024-04-07 16:38:52','2024-04-15 18:01:59'),
(11,'45245245','b0baee9d279d34fa1dfd71aadb908c3f','null','null','null','2024-04-07 16:40:15','2024-04-07 16:40:15'),
(12,'52525225','b0baee9d279d34fa1dfd71aadb908c3f','null','null','null','2024-04-07 17:35:35','2024-04-07 17:35:35'),
(13,'452542','79b7cdcd14db14e9cb498f1793817d69','null','null','null','2024-04-08 08:43:17','2024-04-08 08:43:17'),
(14,'6783354','c5fe25896e49ddfe996db7508cf00534','null','null','null','2024-04-08 08:55:09','2024-04-08 08:55:09'),
(15,'532453','4e16fa9c7f06220cc4c24a08470eee6b','null','null','null','2024-04-08 09:05:02','2024-04-08 09:05:02'),
(16,'66666','421293cb7d0d496496b86be406bd708c','null','null','null','2024-04-08 09:19:15','2024-04-08 09:19:15');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
