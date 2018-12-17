-- MySQL dump 10.13  Distrib 5.7.21, for Win64 (x86_64)
--
-- Host: localhost    Database: onlinechat
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
  `from` varchar(16) NOT NULL COMMENT '对应user表id：内容发送者',
  `to` varchar(16) NOT NULL COMMENT '对应user表id：内容接受者',
  `content` varchar(600) NOT NULL COMMENT '聊天内容',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '聊天内容发送时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `content_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='聊天内容（所用用户的都在这里）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `content`
--

LOCK TABLES `content` WRITE;
/*!40000 ALTER TABLE `content` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='别人对用户评价内容表（评价内容有限）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluate`
--

LOCK TABLES `evaluate` WRITE;
/*!40000 ALTER TABLE `evaluate` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评价内容表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluateinfo`
--

LOCK TABLES `evaluateinfo` WRITE;
/*!40000 ALTER TABLE `evaluateinfo` DISABLE KEYS */;
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
  `groupid` smallint(6) DEFAULT NULL COMMENT '好友分组',
  PRIMARY KEY (`id`),
  UNIQUE KEY `friends_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='好友关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='分组信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
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
  `image` varchar(250) DEFAULT NULL COMMENT '用户头像',
  `sex` varchar(10) DEFAULT NULL COMMENT '用户性别：默认man',
  `birthday` timestamp NOT NULL COMMENT '用户生日',
  `phone` varchar(32) DEFAULT NULL COMMENT '用户手机',
  `email` varchar(64) DEFAULT NULL COMMENT '用户邮箱',
  `country` varchar(32) DEFAULT NULL COMMENT '用户国家',
  `city` varchar(32) DEFAULT NULL COMMENT '用户城市',
  PRIMARY KEY (`id`),
  UNIQUE KEY `userInfo_id_uindex` (`id`),
  UNIQUE KEY `userInfo_user_id_uindex` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinfo`
--

LOCK TABLES `userinfo` WRITE;
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
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

-- Dump completed on 2018-12-17 15:36:36
