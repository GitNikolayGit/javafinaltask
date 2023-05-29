-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: travelagency
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `route` (
  `id` int NOT NULL AUTO_INCREMENT,
  `country_id` int NOT NULL,
  `motive_id` int NOT NULL,
  `days` int NOT NULL,
  `client_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `country_id` (`country_id`),
  KEY `motive_id` (`motive_id`),
  CONSTRAINT `route_ibfk_1` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`),
  CONSTRAINT `route_ibfk_2` FOREIGN KEY (`motive_id`) REFERENCES `motive` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES (1,1,1,10,1),(2,1,2,5,2),(3,1,3,30,3),(4,1,4,30,4),(5,1,5,120,5),(7,2,1,120,7),(8,2,2,10,8),(9,2,3,15,9),(10,2,4,10,10),(11,2,5,15,11),(12,2,6,120,11),(13,3,1,10,12),(14,3,2,10,13),(15,3,3,8,14),(17,3,5,120,15),(18,3,6,11,16),(19,4,1,120,17),(20,4,2,10,18),(21,4,3,10,1),(22,4,4,10,3),(23,4,5,50,11),(24,4,6,15,15),(25,5,1,120,18),(26,5,2,15,4),(27,5,3,120,5),(29,5,5,15,9),(31,6,1,5,8),(32,6,2,120,9),(33,6,3,35,12),(34,6,4,10,13),(35,6,5,8,2),(36,6,6,9,18),(37,7,1,12,15),(38,7,2,120,12),(39,7,3,120,1),(40,7,4,2,2),(41,7,5,120,3),(42,7,6,60,4),(43,8,1,10,5),(44,8,2,10,6),(45,8,3,90,7),(46,8,4,80,9),(47,8,5,10,8),(48,8,6,20,18),(49,9,1,120,17),(50,9,2,45,1),(51,9,3,10,5),(52,9,4,15,9),(53,9,5,15,8),(54,9,6,10,7),(55,10,1,33,3),(56,10,2,10,11),(57,10,3,15,12),(58,10,4,120,5),(59,10,5,20,6),(60,10,6,15,7),(61,11,1,15,3),(62,11,2,10,2),(63,11,3,10,9),(64,11,4,10,7),(65,11,5,15,5);
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-17 21:41:50
