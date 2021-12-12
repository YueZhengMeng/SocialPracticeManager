CREATE DATABASE  IF NOT EXISTS `socialpracticemanager` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `socialpracticemanager`;
-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: socialpracticemanager
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity` (
  `activityID` int NOT NULL,
  `activityName` varchar(255) NOT NULL,
  `practiceID` int NOT NULL,
  `startTime` datetime NOT NULL,
  `endTime` datetime DEFAULT NULL,
  `state` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`activityID`),
  KEY `practiceID_idx` (`practiceID`),
  CONSTRAINT `practiceID_a` FOREIGN KEY (`practiceID`) REFERENCES `practice` (`practiceID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activityparticipation`
--

DROP TABLE IF EXISTS `activityparticipation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activityparticipation` (
  `activityParticipationID` int NOT NULL AUTO_INCREMENT,
  `activityID` int NOT NULL,
  `userID` int NOT NULL,
  `finishTime` datetime DEFAULT NULL,
  `state` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`activityParticipationID`),
  KEY `activityID_idx` (`activityID`),
  KEY `userID_idx` (`userID`),
  CONSTRAINT `activityID` FOREIGN KEY (`activityID`) REFERENCES `activity` (`activityID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userID_a` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activityparticipation`
--

LOCK TABLES `activityparticipation` WRITE;
/*!40000 ALTER TABLE `activityparticipation` DISABLE KEYS */;
/*!40000 ALTER TABLE `activityparticipation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group` (
  `groupID` int NOT NULL AUTO_INCREMENT,
  `groupName` varchar(255) NOT NULL,
  `practiceID` int NOT NULL,
  PRIMARY KEY (`groupID`),
  KEY `practiceID_idx` (`practiceID`),
  CONSTRAINT `practiceID` FOREIGN KEY (`practiceID`) REFERENCES `practice` (`practiceID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groupparticipation`
--

DROP TABLE IF EXISTS `groupparticipation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `groupparticipation` (
  `groupParticipationID` int NOT NULL AUTO_INCREMENT,
  `groupID` int NOT NULL,
  `userID` int NOT NULL,
  PRIMARY KEY (`groupParticipationID`),
  KEY `groupID_idx` (`groupID`),
  KEY `userID_idx` (`userID`),
  CONSTRAINT `groupID` FOREIGN KEY (`groupID`) REFERENCES `group` (`groupID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userID` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groupparticipation`
--

LOCK TABLES `groupparticipation` WRITE;
/*!40000 ALTER TABLE `groupparticipation` DISABLE KEYS */;
/*!40000 ALTER TABLE `groupparticipation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `practice`
--

DROP TABLE IF EXISTS `practice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `practice` (
  `practiceID` int NOT NULL AUTO_INCREMENT,
  `practiceName` varchar(255) NOT NULL,
  `startTime` datetime NOT NULL,
  `endTime` datetime DEFAULT NULL,
  `state` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`practiceID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `practice`
--

LOCK TABLES `practice` WRITE;
/*!40000 ALTER TABLE `practice` DISABLE KEYS */;
/*!40000 ALTER TABLE `practice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL DEFAULT 'student',
  PRIMARY KEY (`userID`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin','admin'),(2,'pwb','pwb','student'),(3,'zhj','zhj','teacher');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'socialpracticemanager'
--

--
-- Dumping routines for database 'socialpracticemanager'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-12 18:57:36
