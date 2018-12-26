-- MySQL dump 10.13  Distrib 5.7.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: onlinechat
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `content`
--

DROP TABLE IF EXISTS `content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sendId` varchar(16) NOT NULL COMMENT '对应user表id：内容发送者',
  `receiveId` varchar(16) NOT NULL COMMENT '对应user表id：内容接受者',
  `content` varchar(600) NOT NULL COMMENT '聊天内容',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '聊天内容发送时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `content_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='聊天内容（所用用户的都在这里）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `content`
--

LOCK TABLES `content` WRITE;
/*!40000 ALTER TABLE `content` DISABLE KEYS */;
INSERT INTO `content` VALUES (1,'113618','121411','123213','2018-12-21 08:55:55'),(2,'113618','121411','123213','2018-12-21 08:56:03'),(3,'113618','121411','123','2018-12-21 09:14:38'),(4,'113618','121411','qwe','2018-12-21 09:21:38'),(5,'121411','113618','123','2018-12-21 09:34:52'),(6,'121411','113618','123','2018-12-21 09:35:25'),(7,'113618','100391','123','2018-12-22 07:47:21'),(8,'113618','100391','123','2018-12-22 07:47:30'),(9,'113618','100391','123','2018-12-22 07:47:33'),(10,'113618','100391','123','2018-12-22 07:47:36'),(11,'113618','105084','12312','2018-12-22 07:47:40'),(12,'121411','113618','123','2018-12-23 06:57:48'),(13,'113618','121411','123','2018-12-23 06:58:04'),(14,'121411','113618','123','2018-12-23 07:07:17'),(15,'121411','113618','123','2018-12-23 07:16:02'),(17,'121411','113618','123','2018-12-23 10:45:15'),(18,'113618','121411','123','2018-12-24 08:20:58'),(19,'121411','113618','123','2018-12-24 08:21:03'),(20,'121411','113618','123','2018-12-24 08:59:20'),(21,'121411','113618','123','2018-12-24 09:59:47'),(22,'121411','113618','111111111111111111','2018-12-24 10:08:38'),(23,'121411','113618','12312','2018-12-24 11:25:33'),(24,'121411','113618','12312','2018-12-24 11:28:34'),(25,'113618','121411','123123','2018-12-24 11:28:43'),(26,'121411','113618','12312','2018-12-24 11:30:28'),(27,'121411','165579','12312','2018-12-24 11:31:19'),(28,'121411','165579','12312','2018-12-24 11:31:19'),(29,'121411','113618','<img class=\"chatPicture\" src=\"/img/picture/picture_2.jpg\" style=\"width: 115px; height: 115px; margin: 0px;\">','2018-12-24 11:49:48'),(30,'121411','113618','<img class=\"chatPicture\" src=\"/img/picture/picture_2.jpg\" style=\"width: 115px; height: 115px; margin: 0px;\">','2018-12-24 11:49:48'),(31,'113618','121411','<img class=\"soundImg\" onclick=\"sound(this)\" src=\"img/soundMsg.png\" alt=\"/audio/113618/74302999.mp3\"><span style=\"margin-left: 10px\">4</span>','2018-12-24 11:50:05'),(32,'176701','121411','12312','2018-12-24 11:50:41'),(33,'113618','121411','12312312','2018-12-24 12:19:39'),(34,'113618','121411','1231231231231213123123123123','2018-12-24 12:20:03');
/*!40000 ALTER TABLE `content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluate`
--

DROP TABLE IF EXISTS `evaluate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `evaluate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userid` varchar(16) NOT NULL COMMENT '对用user表id：被评价用户id',
  `evaluateinfoid` int(11) NOT NULL COMMENT '对应evaluateinfo表的id',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `evaluate_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='别人对用户评价内容表（评价内容有限）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluate`
--

LOCK TABLES `evaluate` WRITE;
/*!40000 ALTER TABLE `evaluate` DISABLE KEYS */;
INSERT INTO `evaluate` VALUES (1,'113618',6,'2018-12-22 14:52:00'),(60,'113618',3,'2018-12-22 15:17:36'),(61,'165579',7,'2018-12-22 15:19:23'),(62,'121411',3,'2018-12-22 15:20:03'),(63,'121411',8,'2018-12-22 15:20:34'),(64,'121411',9,'2018-12-22 15:20:44'),(65,'121411',10,'2018-12-22 15:20:50'),(66,'121411',11,'2018-12-22 15:20:57'),(67,'121411',12,'2018-12-22 15:21:09'),(68,'121411',13,'2018-12-22 15:21:20'),(69,'121411',14,'2018-12-22 15:21:25'),(70,'121411',15,'2018-12-22 15:21:31'),(71,'121411',16,'2018-12-22 15:21:40'),(72,'121411',17,'2018-12-22 15:21:49'),(73,'121411',18,'2018-12-22 15:21:56'),(74,'121411',19,'2018-12-22 15:22:04'),(75,'121411',20,'2018-12-22 15:22:10'),(76,'121411',21,'2018-12-22 15:22:14'),(77,'121411',22,'2018-12-22 15:22:26'),(78,'121411',23,'2018-12-22 15:22:32'),(79,'121411',24,'2018-12-22 15:22:47'),(80,'121411',25,'2018-12-22 15:22:49'),(81,'121411',7,'2018-12-22 15:22:54'),(82,'121411',26,'2018-12-22 15:25:55'),(83,'113618',24,'2018-12-23 11:16:52');
/*!40000 ALTER TABLE `evaluate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluateinfo`
--

DROP TABLE IF EXISTS `evaluateinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `evaluateinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(320) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `evaluate_id_uindex` (`id`),
  UNIQUE KEY `evaluate_content_uindex` (`content`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评价内容表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluateinfo`
--

LOCK TABLES `evaluateinfo` WRITE;
/*!40000 ALTER TABLE `evaluateinfo` DISABLE KEYS */;
INSERT INTO `evaluateinfo` VALUES (24,'111'),(25,'1111'),(7,'123'),(9,'nice'),(10,'不多说'),(17,'不强'),(2,'不错'),(15,'会打篮球'),(16,'但是'),(12,'你怎么知'),(20,'北京大佬'),(14,'厉害'),(26,'哈哈'),(3,'好看'),(11,'就是帅'),(22,'就知道打'),(8,'帅气'),(21,'死肥宅'),(6,'测试'),(23,'王者'),(18,'蓝瘦'),(13,'被看出来'),(1,'阳光'),(19,'香港游');
/*!40000 ALTER TABLE `evaluateinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friends` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userid` varchar(16) NOT NULL COMMENT '对用user表的id',
  `friendid` varchar(16) NOT NULL COMMENT '对应user表的id',
  `groupid` smallint(6) DEFAULT '1' COMMENT '好友分组',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(10) DEFAULT 'SR' COMMENT '好友状态：SR表示已是好友\n S：表示是好友请求发送方\n R：表示是好友请求接收方',
  PRIMARY KEY (`id`),
  UNIQUE KEY `friends_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=176 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='好友关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
INSERT INTO `friends` VALUES (2,'113618','121411',2,'2018-12-19 12:10:50','SR'),(3,'121411','113618',1,'2018-12-21 09:34:31','SR'),(9,'113618','165579',1,'2018-12-21 23:27:26','SR'),(13,'113618','196979',1,'2018-12-21 23:27:26','SR'),(14,'100391','113618',1,'2018-12-22 07:49:56','SR'),(15,'100391','103535',1,'2018-12-23 00:55:19','SR'),(19,'121411','165579',1,'2018-12-23 02:41:16','SR'),(20,'165579','121411',1,'2018-12-23 02:41:16','SR'),(174,'176701','121411',1,'2018-12-24 12:21:13','SR'),(175,'121411','176701',1,'2018-12-24 12:21:13','SR');
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `group_name_uindex` (`name`),
  UNIQUE KEY `group_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='分组信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
INSERT INTO `group` VALUES (3,'同事'),(2,'朋友'),(4,'闺蜜'),(1,'默认');
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` varchar(16) NOT NULL COMMENT '用户id 位数5位',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '用户密码',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('100391','刘泽迪','123','2018-12-21 09:31:11'),('103535','大山','123','2018-12-21 23:22:50'),('105084','黄昏','123','2018-12-21 09:26:37'),('113618','环顾','123','2018-12-19 12:10:12'),('121411','秋水','123','2018-12-19 09:59:59'),('129263','刘泽迪','123','2018-12-20 00:49:44'),('129960','黄昏','123','2018-12-21 23:22:31'),('144587','Liu1605103328','123','2018-12-22 12:11:28'),('165579','恐怖','123','2018-12-21 23:23:35'),('174834','秋水','123','2018-12-20 00:46:36'),('176701','黑客','123','2018-12-21 23:23:25'),('187404','但该','123','2018-12-22 12:47:55'),('196979','小白','123','2018-12-21 23:23:16');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userinfo`
--

DROP TABLE IF EXISTS `userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userid` varchar(16) NOT NULL COMMENT '对应user表id',
  `image` varchar(250) DEFAULT '/img/dog.png' COMMENT '用户头像',
  `sex` varchar(10) DEFAULT '不详' COMMENT '用户性别：默认man',
  `birthday` timestamp NULL DEFAULT NULL COMMENT '用户生日',
  `phone` varchar(32) DEFAULT NULL COMMENT '用户手机',
  `email` varchar(64) DEFAULT NULL COMMENT '用户邮箱',
  `country` varchar(32) DEFAULT '中国' COMMENT '用户国家',
  `city` varchar(32) DEFAULT NULL COMMENT '用户城市',
  PRIMARY KEY (`id`),
  UNIQUE KEY `userInfo_id_uindex` (`id`),
  UNIQUE KEY `userInfo_user_id_uindex` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinfo`
--

LOCK TABLES `userinfo` WRITE;
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` VALUES (5,'121411','/img/dog.png','不详','2018-12-23 21:58:14',NULL,NULL,NULL,NULL),(6,'113618','/img/dog.png','不详',NULL,NULL,NULL,NULL,NULL),(7,'174834','/img/dog.png','不详','2018-12-23 21:58:19',NULL,NULL,'中国','南昌'),(8,'129263','/img/dog.png','不详',NULL,NULL,NULL,NULL,'南昌'),(9,'105084','/img/dog.png','不详',NULL,NULL,NULL,'中国',NULL),(10,'100391','/img/dog.png','不详',NULL,NULL,NULL,NULL,NULL),(11,'129960','/img/dog.png','不详',NULL,NULL,NULL,NULL,NULL),(12,'103535','/img/dog.png','不详',NULL,NULL,NULL,NULL,NULL),(13,'196979','/img/dog.png','不详',NULL,NULL,NULL,NULL,NULL),(14,'176701','/img/dog.png','不详',NULL,NULL,NULL,NULL,NULL),(15,'165579','/img/dog.png','不详',NULL,NULL,NULL,NULL,NULL),(17,'144587','/img/dog.png','不详',NULL,NULL,NULL,NULL,NULL),(18,'187404','/img/dog.png','不详',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-24 20:30:11
