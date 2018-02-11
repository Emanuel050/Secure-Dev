DROP DATABASE IF EXISTS `postmansdb`;
CREATE DATABASE  IF NOT EXISTS `postmansdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `postmansdb`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: postmansdb
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `package`
--

DROP TABLE IF EXISTS `package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `package` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_customer_id` int(11) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `weight` double NOT NULL,
  `from_city` varchar(100) NOT NULL,
  `from_address` varchar(100) NOT NULL,
  `from_pincode` int(11) NOT NULL,
  `to_address` varchar(100) NOT NULL,
  `to_pincode` int(11) NOT NULL,
  `to_city` varchar(100) NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Pack_Id_UNIQUE` (`id`),
  KEY `fk_package_user_idx` (`fk_customer_id`),
  CONSTRAINT `fk_package_user` FOREIGN KEY (`fk_customer_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package`
--

LOCK TABLES `package` WRITE;
/*!40000 ALTER TABLE `package` DISABLE KEYS */;
/*!40000 ALTER TABLE `package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package_bids`
--

DROP TABLE IF EXISTS `package_bids`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `package_bids` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_package_id` int(11) NOT NULL,
  `fk_postman_id` int(11) DEFAULT NULL,
  `chosen` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_package_bids_package_idx` (`fk_package_id`),
  KEY `fk_package_bids_user_idx` (`fk_postman_id`),
  CONSTRAINT `fk_package_bids_package` FOREIGN KEY (`fk_package_id`) REFERENCES `package` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_package_bids_user` FOREIGN KEY (`fk_postman_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package_bids`
--

LOCK TABLES `package_bids` WRITE;
/*!40000 ALTER TABLE `package_bids` DISABLE KEYS */;
/*!40000 ALTER TABLE `package_bids` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ranks`
--

DROP TABLE IF EXISTS `ranks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ranks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_customer_id` int(11) DEFAULT NULL,
  `fk_postman_id` int(11) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `rank` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Seq_UNIQUE` (`id`),
  KEY `fk_ranks_user_1_idx` (`fk_customer_id`),
  KEY `fk_ranks_user_2_idx` (`fk_postman_id`),
  CONSTRAINT `fk_ranks_user_1` FOREIGN KEY (`fk_customer_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `fk_ranks_user_2` FOREIGN KEY (`fk_postman_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ranks`
--

LOCK TABLES `ranks` WRITE;
/*!40000 ALTER TABLE `ranks` DISABLE KEYS */;
/*!40000 ALTER TABLE `ranks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `script_tracking`
--

DROP TABLE IF EXISTS `script_tracking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `script_tracking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `script_num` varchar(45) NOT NULL,
  `sp_ver` varchar(45) NOT NULL,
  `script_name` varchar(255) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `script_tracking`
--

LOCK TABLES `script_tracking` WRITE;
/*!40000 ALTER TABLE `script_tracking` DISABLE KEYS */;
/*!40000 ALTER TABLE `script_tracking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `birthDate` timestamp NULL DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `mail` varchar(45) NOT NULL,
  `phoneNumber` varchar(45) NOT NULL,
  `passportPicture` blob,
  `password` varchar(45) NOT NULL,
  `gender` enum('MALE','FEMALE') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `mail_uq` (`mail`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Amit','Vaknin',NULL,NULL,'amitv555@gmail.com','434543543',NULL,'453543',NULL),(2,'Ortal','Nigri',NULL,NULL,'ortalni@gmail.com','434543543',NULL,'453543',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-02 23:38:01
