CREATE DATABASE  IF NOT EXISTS `mygarage` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mygarage`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: mygarage
-- ------------------------------------------------------
-- Server version	5.6.20

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
-- Table structure for table `user_information`
--

DROP TABLE IF EXISTS `user_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_information` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `last_update` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `store` bigint(20) DEFAULT NULL,
  `syw_id` bigint(20) NOT NULL,
  `family_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9bql56vlb1l63h6xtkw6nfwal` (`store`),
  CONSTRAINT `FK_9bql56vlb1l63h6xtkw6nfwal` FOREIGN KEY (`store`) REFERENCES `store` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_information`
--

LOCK TABLES `user_information` WRITE;
/*!40000 ALTER TABLE `user_information` DISABLE KEYS */;
INSERT INTO `user_information` VALUES (1,NULL,500,NULL,123456,103604138),(2,NULL,501,NULL,888,NULL);
/*!40000 ALTER TABLE `user_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_vehicle`
--

DROP TABLE IF EXISTS `user_vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_vehicle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `vehicle_id` bigint(20) NOT NULL,
  `isconfirmed` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `FK_user_information_id_idx` (`user_id`),
  KEY `FK_vehicle_id_idx` (`vehicle_id`),
  CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_information` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_vehicle_id` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_vehicle`
--

LOCK TABLES `user_vehicle` WRITE;
/*!40000 ALTER TABLE `user_vehicle` DISABLE KEYS */;
INSERT INTO `user_vehicle` VALUES (18,2,5,''),(19,2,6,''),(24,1,13,'');
/*!40000 ALTER TABLE `user_vehicle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehicle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `familyId` bigint(20) DEFAULT NULL,
  `tangibleId` bigint(20) DEFAULT NULL,
  `make` varchar(45) NOT NULL,
  `model` varchar(45) NOT NULL,
  `year` int(11) NOT NULL,
  `color` varchar(45) DEFAULT NULL,
  `mileage` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES (1,100,5555555,'toyota','corolla',2014,'black',1000),(2,100,55556,'toyota','camry',2014,'white',1200),(3,100,NULL,'Honda','Accord',2012,'Black',1002),(4,NULL,NULL,'Honda','Civic',2014,'Black',44),(5,100,200,'Renault','12',1994,'grey',100),(6,100,200,'Renault','19',1998,'Red',100),(7,73311110,132623332,'FORD TRUCK','F150 PICKUP',2004,NULL,0),(8,73311110,132623330,'HONDA','ACCORD',2008,'',0),(9,73311110,132623330,'HONDA','ACCORD',2008,'',0),(10,73311110,132623330,'HONDA','ACCORD',2008,'',0),(11,73311110,132623330,'HONDA','ACCORD',2008,'',0),(12,73311110,132623330,'HONDA','ACCORD',2008,'',0),(13,73311110,132623330,'HONDA','ACCORD',2008,'',0);
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-12 23:16:28
