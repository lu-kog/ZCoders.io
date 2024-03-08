-- MySQL dump 10.13  Distrib 8.0.35, for Linux (x86_64)
--
-- Host: localhost    Database: Coders
-- ------------------------------------------------------
-- Server version	8.0.36-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Clan`
--

DROP TABLE IF EXISTS `Clan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Clan` (
  `clanID` varchar(8) NOT NULL,
  `clanName` varchar(100) DEFAULT NULL,
  `Admin` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`clanID`),
  KEY `Admin` (`Admin`),
  CONSTRAINT `Clan_ibfk_1` FOREIGN KEY (`Admin`) REFERENCES `Users` (`mailID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Clan`
--

LOCK TABLES `Clan` WRITE;
/*!40000 ALTER TABLE `Clan` DISABLE KEYS */;
INSERT INTO `Clan` VALUES ('58119654','CringeSquad','bangalore@gmail.com');
/*!40000 ALTER TABLE `Clan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ClanRelation`
--

DROP TABLE IF EXISTS `ClanRelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ClanRelation` (
  `clanID` varchar(8) DEFAULT NULL,
  `mailID` varchar(50) NOT NULL,
  `role` enum('CO_ADMIN','MEMBER','ADMIN') DEFAULT 'MEMBER',
  UNIQUE KEY `mailID` (`mailID`),
  KEY `clanID` (`clanID`),
  CONSTRAINT `ClanRelation_ibfk_1` FOREIGN KEY (`clanID`) REFERENCES `Clan` (`clanID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ClanRelation_ibfk_2` FOREIGN KEY (`mailID`) REFERENCES `Users` (`mailID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ClanRelation`
--

LOCK TABLES `ClanRelation` WRITE;
/*!40000 ALTER TABLE `ClanRelation` DISABLE KEYS */;
INSERT INTO `ClanRelation` VALUES ('58119654','amos@gmail.com','MEMBER'),('58119654','bangalore@gmail.com','ADMIN'),('58119654','charu07@gmail.com','MEMBER'),('58119654','indirajith@gmail.com','MEMBER'),('58119654','jeyrajesh@gmail.com','MEMBER');
/*!40000 ALTER TABLE `ClanRelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ClanRequest`
--

DROP TABLE IF EXISTS `ClanRequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ClanRequest` (
  `clanID` varchar(8) DEFAULT NULL,
  `mailID` varchar(30) DEFAULT NULL,
  KEY `clanID` (`clanID`),
  KEY `mailID` (`mailID`),
  CONSTRAINT `ClanRequest_ibfk_1` FOREIGN KEY (`clanID`) REFERENCES `Clan` (`clanID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ClanRequest_ibfk_2` FOREIGN KEY (`mailID`) REFERENCES `Users` (`mailID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ClanRequest`
--

LOCK TABLES `ClanRequest` WRITE;
/*!40000 ALTER TABLE `ClanRequest` DISABLE KEYS */;
INSERT INTO `ClanRequest` VALUES ('58119654','bangalore@gmail.com'),('58119654','charu07@gmail.com'),('58119654','amos@gmail.com'),('58119654','amos@gmail.com'),('58119654','amos@gmail.com'),('58119654','amos@gmail.com'),('58119654','amos@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','kumaravel@gmail.com'),('58119654','kumaravel@gmail.com'),('58119654','kumaravel@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com'),('58119654','mahalakshmi@gmail.com');
/*!40000 ALTER TABLE `ClanRequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LanguageRelation`
--

DROP TABLE IF EXISTS `LanguageRelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `LanguageRelation` (
  `l_ID` int DEFAULT NULL,
  `Q_ID` varchar(8) DEFAULT NULL,
  `funcName` text,
  `testCases` varchar(255) DEFAULT NULL,
  KEY `l_ID` (`l_ID`),
  KEY `Q_ID` (`Q_ID`),
  CONSTRAINT `LanguageRelation_ibfk_1` FOREIGN KEY (`l_ID`) REFERENCES `Languages` (`l_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `LanguageRelation_ibfk_2` FOREIGN KEY (`Q_ID`) REFERENCES `Questions` (`Q_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LanguageRelation`
--

LOCK TABLES `LanguageRelation` WRITE;
/*!40000 ALTER TABLE `LanguageRelation` DISABLE KEYS */;
INSERT INTO `LanguageRelation` VALUES (1,'56873465','public static double sum_array(double[] numbers)',''),(2,'56873465','def sum_array(a)',''),(1,'45986677','public static int positive_sum(int[] arr)',''),(2,'45986677','def positive_sum(arr)',''),(1,'42887152','public static int reverse(String word)',''),(2,'42887152','def reverse(word)',''),(2,'27624322','def findLargest(a):',''),(2,'54281620','def isEven(num):',''),(1,'44414555','public class kata{\npublic static String flames(String male, String female){\nreturn \"Friendship\";\n}\n}',''),(2,'44414555','def flames(male, female):\nreturn \"Friendship\"','');
/*!40000 ALTER TABLE `LanguageRelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Languages`
--

DROP TABLE IF EXISTS `Languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Languages` (
  `l_ID` int NOT NULL AUTO_INCREMENT,
  `lang_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`l_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Languages`
--

LOCK TABLES `Languages` WRITE;
/*!40000 ALTER TABLE `Languages` DISABLE KEYS */;
INSERT INTO `Languages` VALUES (1,'Java'),(2,'Python'),(3,'Mysql');
/*!40000 ALTER TABLE `Languages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Levels`
--

DROP TABLE IF EXISTS `Levels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Levels` (
  `levelID` tinyint NOT NULL AUTO_INCREMENT,
  `level_name` varchar(10) DEFAULT NULL,
  `score` tinyint DEFAULT '0',
  PRIMARY KEY (`levelID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Levels`
--

LOCK TABLES `Levels` WRITE;
/*!40000 ALTER TABLE `Levels` DISABLE KEYS */;
INSERT INTO `Levels` VALUES (1,'8Kyu',2),(2,'7Kyu',3),(3,'6Kyu',4);
/*!40000 ALTER TABLE `Levels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Login`
--

DROP TABLE IF EXISTS `Login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Login` (
  `mailID` varchar(30) DEFAULT NULL,
  `passwd` varchar(255) NOT NULL,
  `Role` enum('USER','ADMIN') DEFAULT 'USER',
  UNIQUE KEY `mailID` (`mailID`),
  CONSTRAINT `Login_ibfk_1` FOREIGN KEY (`mailID`) REFERENCES `Users` (`mailID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Login`
--

LOCK TABLES `Login` WRITE;
/*!40000 ALTER TABLE `Login` DISABLE KEYS */;
INSERT INTO `Login` VALUES ('charu07@gmail.com','$2a$10$3U4KHiDOSaiqgHXf9RDbaeJzAcplsVpYdxas50NUda/HcIbpt8NlW','USER'),('krishnagokul810@gmail.com','$2a$10$gNt6SZzJKPmZtVXB1TMWQ.bonA233722UPPCIuuXCN4F2FE7NvLIK','USER'),('vasanth.albert@zohocorp.com','$2a$10$VJc29ymwppWLHK4KkjSVaOBcPot4JRj87jsPS2ZeBtVsfw3ykVTdq','USER'),('sun.a@zohocorp.com','$2a$10$xZzNQo8vuMc8W08QQnPrNuH134wiuz9uijNZaSYyTnqcgJFohGxt.','USER'),('bangalore@gmail.com','$2a$10$ArqdJCQK11GTtD90Ei0mq.RUBPwE663IOVtrgdtWskKwH0a27iHPq','USER');
/*!40000 ALTER TABLE `Login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Questions`
--

DROP TABLE IF EXISTS `Questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Questions` (
  `Q_ID` varchar(8) NOT NULL,
  `Q_name` varchar(100) DEFAULT NULL,
  `description` text,
  `example` text,
  `functionString` varchar(200) DEFAULT NULL,
  `testcaseJSON` text,
  `levelID` tinyint DEFAULT NULL,
  `Author` varchar(30) DEFAULT NULL,
  `status` enum('APPROVED','NOTAPPROVED') DEFAULT 'NOTAPPROVED',
  PRIMARY KEY (`Q_ID`),
  KEY `Author` (`Author`),
  KEY `levelID` (`levelID`),
  CONSTRAINT `Questions_ibfk_1` FOREIGN KEY (`Author`) REFERENCES `Users` (`mailID`),
  CONSTRAINT `Questions_ibfk_2` FOREIGN KEY (`levelID`) REFERENCES `Levels` (`levelID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Questions`
--

LOCK TABLES `Questions` WRITE;
/*!40000 ALTER TABLE `Questions` DISABLE KEYS */;
INSERT INTO `Questions` VALUES ('27624322','findLargestOfArray','You get an array of numbers, return largest of all.','Example [1,-4,7,12] => 12','findLargest','{\"0\": {\n        \"params\": {\"0\": [5, 7, 1, 8, 4]},\n        \"output\": 8\n    },\n    \"1\": {\n        \"params\": {\"0\": [-3, -6, -1, -4]},\n        \"output\": -1\n    },\n    \"2\": {\n        \"params\": {\"0\": [0, 0, 0]},\n        \"output\": 0\n    },\n    \"3\": {\n        \"params\": {\"0\": [1, 2, 3, 4, 5, 6, 7, 8, 9]},\n        \"output\": 9\n    },\n    \"4\": {\n        \"params\": {\"0\": [8, 2, 7, 5, 4]},\n        \"output\": 8\n    },\n    \"5\": {\n        \"params\": {\"0\": [1]},\n        \"output\": 1\n    },\n    \"6\": {\n        \"params\": {\"0\": [2, -7, 3, -4]},\n        \"output\": 3\n    },\n    \"7\": {\n        \"params\": {\"0\": [100, 50, 75, 60]},\n        \"output\": 100\n    },\n    \"8\": {\n        \"params\": {\"0\": [123, 456, 789]},\n        \"output\": 789\n    }}',2,'charu07@gmail.com','APPROVED'),('42887152','reverse the string','You get a string, return its reverse.','Example \"codewars\" => \"srawedoc\"','reverse','{\n    \"0\": {\n        \"params\": { \"0\": \"hello\" },\n        \"output\":  \"olleh\"\n    },\n    \"1\": {\n        \"params\": { \"0\": \"world\" },\n        \"output\":  \"dlrow\"\n    },\n    \"2\": {\n        \"params\": { \"0\": \"\" },\n        \"output\":  \"\"\n    },\n    \"3\": {\n        \"params\": { \"0\": \"a\" },\n        \"output\":  \"a\"\n    },\n    \"4\": {\n        \"params\": { \"0\": \"!@#$%^\" },\n        \"output\":  \"^%$#@!\"\n    },\n    \"5\": {\n        \"params\": { \"0\": \" hello \" },\n        \"output\":  \" olleh \"\n    },\n    \"6\": {\n        \"params\": { \"0\": \"HeLlO WoRlD\" },\n        \"output\":  \"DlRoW OlLeH\"\n    },\n    \"7\": {\n        \"params\": { \"0\": \"12345\" },\n        \"output\":  \"54321\"\n    },\n    \"8\": {\n        \"params\": { \"0\": \"hello001 \" },\n        \"output\":  \" 100olleh\"\n    },\n    \"9\": {\n        \"params\": { \"0\": \"@!23\" },\n        \"output\":  \"32!@\"\n    },\n    \"10\": {\n        \"params\": { \"0\": \"hello  world\" },\n        \"output\":  \"dlrow  olleh\"\n    }\n}',1,'charu07@gmail.com','APPROVED'),('44414555','FLAMES','\nFLAMES game is a method to find out the status of a love relationship. Entering two names will give you the outcome of a relationship between them.\n\nTo get the outcome,  \nFirst, cross out all letters the names have in common.  \nSecond, remove the cross out letter on both names.  \nThird, get the count of the characters that are left.  \nFourth, given the word FLAMES, with each letter, starting from the left, count up to the number you got in the previous step and return to the F on the left with each pass.  \nFinally, the letter you land on has a word that it stands for in the acronym \"flames\".\n','    F = Friendship\n    L = Love\n    A = Affection\n    M = Marriage\n    E = Enemies\n    S = Siblings','flames','{\"22\":{\"output\":\"Enemies\",\"params\":{\"0\":\"Nikhil\",\"1\":\"Alisha\"}},\"23\":{\"output\":\"Friendship\",\"params\":{\"0\":\"Omkar\",\"1\":\"Bhakti\"}},\"24\":{\"output\":\"Friendship\",\"params\":{\"0\":\"Pranav\",\"1\":\"Charita\"}},\"25\":{\"output\":\"Friendship\",\"params\":{\"0\":\"Qadir\",\"1\":\"Dipti\"}},\"26\":{\"output\":\"Enemies\",\"params\":{\"0\":\"Rahul\",\"1\":\"Esha\"}},\"27\":{\"output\":\"Friendship\",\"params\":{\"0\":\"Sachin\",\"1\":\"Falguni\"}},\"28\":{\"output\":\"Marriage\",\"params\":{\"0\":\"Tarun\",\"1\":\"Gunjan\"}},\"10\":{\"output\":\"Siblings\",\"params\":{\"0\":\"Bhuvan\",\"1\":\"Kavya\"}},\"11\":{\"output\":\"Love\",\"params\":{\"0\":\"Chetan\",\"1\":\"Anjali\"}},\"12\":{\"output\":\"Love\",\"params\":{\"0\":\"Dev\",\"1\":\"Priya\"}},\"13\":{\"output\":\"Friendship\",\"params\":{\"0\":\"Eshan\",\"1\":\"Riya\"}},\"14\":{\"output\":\"Enemies\",\"params\":{\"0\":\"Farhan\",\"1\":\"Sania\"}},\"15\":{\"output\":\"Love\",\"params\":{\"0\":\"Girish\",\"1\":\"Tanvi\"}},\"16\":{\"output\":\"Friendship\",\"params\":{\"0\":\"Harish\",\"1\":\"Uma\"}},\"17\":{\"output\":\"Love\",\"params\":{\"0\":\"Ishan\",\"1\":\"Vidya\"}},\"18\":{\"output\":\"Love\",\"params\":{\"0\":\"Jai\",\"1\":\"Wendy\"}},\"19\":{\"output\":\"Marriage\",\"params\":{\"0\":\"Karan\",\"1\":\"Xena\"}},\"0\":{\"output\":\"Affection\",\"params\":{\"0\":\"Krish\",\"1\":\"Hermione\"}},\"1\":{\"output\":\"Friendship\",\"params\":{\"0\":\"rajesh\",\"1\":\"indrajith\"}},\"2\":{\"output\":\"Siblings\",\"params\":{\"0\":\"WEPISCISZ\",\"1\":\"VFB\"}},\"3\":{\"output\":\"Friendship\",\"params\":{\"0\":\"Vasanth\",\"1\":\"ida\"}},\"4\":{\"output\":\"Enemies\",\"params\":{\"0\":\"Tharan\",\"1\":\"Zameema Barwin\"}},\"5\":{\"output\":\"Siblings\",\"params\":{\"0\":\"Sorimuthu\",\"1\":\"Keerthy suresh\"}},\"6\":{\"output\":\"Enemies\",\"params\":{\"0\":\"Indirajith\",\"1\":\"Komi\"}},\"7\":{\"output\":\"Affection\",\"params\":{\"0\":\"Charu priyan\",\"1\":\"Charu Priya\"}},\"8\":{\"output\":\"Love\",\"params\":{\"0\":\"Ragavan\",\"1\":\"Ragavi\"}},\"9\":{\"output\":\"Enemies\",\"params\":{\"0\":\"Aarav\",\"1\":\"Meera\"}},\"20\":{\"output\":\"Marriage\",\"params\":{\"0\":\"Lokesh\",\"1\":\"Yasmin\"}},\"21\":{\"output\":\"Enemies\",\"params\":{\"0\":\"Manav\",\"1\":\"Zara\"}}}',2,'krishnagokul810@gmail.com','APPROVED'),('45986677','Sum of positive','You get an array of numbers, return the sum of all of the positives ones.','Example [1,-4,7,12] => 1 + 7 + 12 = 20','positive_sum','{\n    \"0\": {\n        \"params\": {\"0\": []},\n        \"output\": 0\n    },\n    \"1\": {\n        \"params\": {\"0\": [5]},\n        \"output\": 5\n    },\n    \"2\": {\n        \"params\": {\"0\": [1, 2, 3, 4, 5]},\n        \"output\": 15\n    },\n    \"3\": {\n        \"params\": {\"0\": [-1, -2, -3, -4, -5]},\n        \"output\": 0\n    },\n    \"4\": {\n        \"params\": {\"0\": [1, -2, 3, -4, 5]},\n        \"output\": 9\n    },\n    \"5\": {\n        \"params\": {\"0\": [1000000, 2000000, 3000000]},\n        \"output\": 6000000\n    },\n    \"6\": {\n        \"params\": {\"0\": [5, 5, 5, 5, 5]},\n        \"output\": 25\n    },\n    \"7\": {\n        \"params\": {\"0\": [0, 0, 0, 0, 0]},\n        \"output\": 0\n    },\n    \"8\": {\n        \"params\": {\"0\": [-5, 10]},\n        \"output\": 10\n    },\n    \"9\": {\n        \"params\": {\"0\": [-1000000]},\n        \"output\": 0\n    }\n}',2,'krishnagokul810@gmail.com','APPROVED'),('54281620','check if it is even','You get a number, return a boolean whether the number is even','Example 453 => false','isEven','{\"0\": {\"params\": {\"0\": 82376}, \"output\": true}, \"1\": {\"params\": {\"0\": 2739}, \"output\": false}, \"2\": {\"params\": {\"0\": -1}, \"output\": false}, \"3\": {\"params\": {\"0\": 1}, \"output\": false}, \"4\": {\"params\": {\"0\": 9}, \"output\": false}, \"5\": {\"params\": {\"0\": -11}, \"output\": false}, \"6\": {\"params\": {\"0\": 32}, \"output\": true}, \"7\": {\"params\": {\"0\": -256}, \"output\": true}, \"8\": {\"params\": {\"0\": 999}, \"output\": false}, \"9\": {\"params\": {\"0\": -1000}, \"output\": true}}',2,'charu07@gmail.com','APPROVED'),('56873465','Sum Arrays','Write a function that takes an array of numbers and returns the sum of the numbers. The numbers can be negative or non-integer. If the array does not contain any numbers then you should return 0.','Examples Input: [1, 5.2, 4, 0, -1]\nOutput: 9.2\n\nInput: []\nOutput: 0\n\n Input: [-2.398]\nOutput: -2.398\n\n','sum_array','{\n    \"0\": {\n        \"params\": {\"0\": []},\n        \"output\": 0\n    },\n    \"1\": {\n        \"params\": {\"0\": [5]},\n        \"output\": 5\n    },\n    \"2\": {\n        \"params\": {\"0\": [1, 2, 3, 4, 5]},\n        \"output\": 15\n    },\n    \"3\": {\n        \"params\": {\"0\": [-1, -2, -3, -4, -5]},\n        \"output\": -15\n    },\n    \"4\": {\n        \"params\": {\"0\": [1, -2, 3, -4, 5]},\n        \"output\": 3\n    },\n    \"5\": {\n        \"params\": {\"0\": [1000000, 2000000, 3000000]},\n        \"output\": 6000000\n    },\n    \"6\": {\n        \"params\": {\"0\": [5, 5, 5, 5, 5]},\n        \"output\": 25\n    },\n    \"7\": {\n        \"params\": {\"0\": [0, 0, 0, 0, 0]},\n        \"output\": 0\n    },\n    \"8\": {\n        \"params\": {\"0\": [-5, 10]},\n        \"output\": 5\n    },\n    \"9\": {\n        \"params\": {\"0\": [-1000000]},\n        \"output\": -1000000\n    }\n}',1,'charu07@gmail.com','APPROVED');
/*!40000 ALTER TABLE `Questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Session`
--

DROP TABLE IF EXISTS `Session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Session` (
  `sessionID` varchar(40) NOT NULL,
  `mailID` varchar(30) DEFAULT NULL,
  `loggedTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`sessionID`),
  KEY `mailID` (`mailID`),
  CONSTRAINT `Session_ibfk_1` FOREIGN KEY (`mailID`) REFERENCES `Users` (`mailID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Session`
--

LOCK TABLES `Session` WRITE;
/*!40000 ALTER TABLE `Session` DISABLE KEYS */;
INSERT INTO `Session` VALUES ('036954f2-65da-40fe-af46-bb5f3a84347b','vasanth.albert@zohocorp.com','2024-03-08 04:09:39'),('03773829-f955-4884-9cd4-7c53cec5df2e','charu07@gmail.com','2024-03-08 03:58:39'),('06d36b16-6f1b-4845-8af6-e8859518ba5d','bangalore@gmail.com','2024-03-07 23:24:35'),('09736bc9-33a1-41c9-ab87-87b7709769de','bangalore@gmail.com','2024-03-07 23:24:10'),('10ba660b-d119-4db6-aeb8-77bee0e34c2a','charu07@gmail.com','2024-03-07 23:03:52'),('10d68562-0073-4cc1-b2af-185a8daecf3d','bangalore@gmail.com','2024-03-07 23:18:19'),('1534dd65-7833-4fa0-bed6-d80dfe92a5b9','bangalore@gmail.com','2024-03-07 22:51:04'),('16ccc0fa-8be5-495d-8070-cc4d559d9719','bangalore@gmail.com','2024-03-07 23:19:26'),('1f738d48-7a35-4683-8219-467d33458938','bangalore@gmail.com','2024-03-07 23:21:36'),('2044cd4f-cbbc-495d-a436-528323de969b','charu07@gmail.com','2024-03-07 23:00:53'),('22084e23-7115-416f-8a4b-87fd0dcc2435','charu07@gmail.com','2024-03-07 22:57:11'),('23a79892-bd8a-445d-a2af-65868c603f61','bangalore@gmail.com','2024-03-07 23:40:41'),('23e63fd0-ea82-433c-ad91-05fa89a92de2','bangalore@gmail.com','2024-03-07 23:15:04'),('272a6da3-23fd-463c-a1ff-f5877844abd3','charu07@gmail.com','2024-02-29 16:54:57'),('27c4767f-0802-4ec0-90f8-29b96a3c7ee9','charu07@gmail.com','2024-03-08 04:25:36'),('2d0133b5-972e-4685-8b3b-f64791c5b6f7','bangalore@gmail.com','2024-03-07 23:38:10'),('306143ad-5d57-454f-9d41-500d63a91a3e','bangalore@gmail.com','2024-03-07 23:36:42'),('3355191f-accc-4b88-a931-4348bafdcddd','bangalore@gmail.com','2024-03-07 23:22:14'),('33cb4be8-6d2c-472c-bcb7-b8e4a4c35041','krishnagokul810@gmail.com','2024-03-08 03:16:50'),('36735792-f1a4-4396-a565-4d5befe49363','charu07@gmail.com','2024-03-07 22:58:33'),('4004610d-80bb-4bb0-95dc-1f65739e57e2','vasanth.albert@zohocorp.com','2024-03-08 04:19:30'),('42987052-6362-4183-87e2-4aa596d47873','charu07@gmail.com','2024-03-07 23:02:52'),('42c74e37-b629-4644-83df-8afe57564de7','charu07@gmail.com','2024-03-08 04:18:27'),('47f0279d-6d5e-49eb-b415-b87f273b14bd','bangalore@gmail.com','2024-03-07 23:21:55'),('48b3bd8f-e718-4727-afbb-2ae61ee5436e','charu07@gmail.com','2024-03-07 22:58:32'),('48d8e7b2-1267-4a2a-b589-e56aa75f010b','bangalore@gmail.com','2024-03-07 23:18:43'),('49f6b1cb-c414-4259-be29-fc35791a85c9','bangalore@gmail.com','2024-03-07 22:51:52'),('4a2482ba-7009-42a4-a7e1-5af154d7ab3b','charu07@gmail.com','2024-03-07 22:58:37'),('4dfa9fbe-ca9b-447e-821a-6cb3b5adae47','bangalore@gmail.com','2024-03-07 23:07:29'),('514634ac-97aa-41ab-9914-0d0ee69c453a','bangalore@gmail.com','2024-03-07 23:35:14'),('53555758-c9de-4800-891e-7e28023f7d50','krishnagokul810@gmail.com','2024-02-29 16:55:04'),('554dd04d-da83-4a51-87ac-9ba27e15fb68','charu07@gmail.com','2024-03-08 04:08:11'),('56b5a70e-4927-4e0e-adcf-de48e622360e','charu07@gmail.com','2024-03-07 22:56:52'),('56fbd67e-0945-4747-8c06-594f9971ecdc','vasanth.albert@zohocorp.com','2024-02-29 16:55:28'),('59654173-de45-4e3f-8025-bca3bb327f4e','charu07@gmail.com','2024-03-07 23:03:27'),('5c7f962b-c2f5-472f-9896-3bf1730d9167','bangalore@gmail.com','2024-03-07 23:19:26'),('67aa3e9f-b121-4043-8714-c4d037eaf142','charu07@gmail.com','2024-03-08 03:16:21'),('69a1edba-8c0b-4f6e-99bc-dc6ec04a198e','vasanth.albert@zohocorp.com','2024-03-08 04:24:10'),('6dde841b-ccbb-4178-9e23-a4da18bbc6e4','bangalore@gmail.com','2024-03-07 23:21:38'),('724c346a-3a22-4dc2-95d9-3dc15f4a7168','bangalore@gmail.com','2024-03-07 23:23:46'),('763fbf8a-a73c-4e3b-9622-f7b0b742481f','charu07@gmail.com','2024-03-08 03:40:29'),('76783f6e-fe8e-4d67-839f-b3446aeac85d','charu07@gmail.com','2024-03-07 23:00:51'),('77e91912-83c6-481e-bbb4-933e4a99c06c','charu07@gmail.com','2024-03-08 03:14:19'),('78034978-04a8-4c35-a089-1a68abc9ec7d','vasanth.albert@zohocorp.com','2024-03-08 04:14:29'),('7ab1d7b1-b3c1-4913-989e-938f64c33186','bangalore@gmail.com','2024-03-07 23:36:41'),('7b9e2794-8b3c-47c0-b046-66fabd8b7e9b','charu07@gmail.com','2024-03-08 03:35:36'),('7c32cdda-9422-433e-a3bc-5bc998b79189','charu07@gmail.com','2024-03-07 23:02:53'),('7d15b333-9380-47fa-a223-ad15d0493bbf','krishnagokul810@gmail.com','2024-03-08 04:08:59'),('8d2079a5-2c8a-4b4f-b3cf-f73df455d039','bangalore@gmail.com','2024-03-07 23:23:24'),('8d4569fd-2aaa-4c9f-8faa-4728c6aff873','bangalore@gmail.com','2024-03-07 23:36:20'),('8da8ba1b-6df4-41b1-908d-8e7cec684c13','charu07@gmail.com','2024-03-07 22:58:35'),('8ff4c444-cd8c-45fa-83cf-ea6d16c85471','bangalore@gmail.com','2024-03-07 23:36:38'),('9081aeb7-ab8c-40f4-90e6-c7e0a9793f2d','charu07@gmail.com','2024-03-07 23:01:38'),('9253e940-9d91-42a9-bf35-32bc9141013a','bangalore@gmail.com','2024-03-07 23:18:42'),('944c77e9-78c0-494e-8e84-c5c4f5a01956','bangalore@gmail.com','2024-03-07 23:19:25'),('9f0cdbc2-8628-4026-b2ee-a414018b2e7f','bangalore@gmail.com','2024-03-07 23:08:13'),('9fd9c8d1-3008-4536-97fe-bc91b756b89d','charu07@gmail.com','2024-03-08 03:35:12'),('a3ec9d43-c1d2-41d4-abc3-b85c8da19567','charu07@gmail.com','2024-03-08 04:28:40'),('b2b1312a-f3f1-485f-a100-755e9839be82','charu07@gmail.com','2024-03-08 03:20:43'),('b3e4a0da-e4c6-4387-8dc9-b85cfb20d7e0','charu07@gmail.com','2024-03-08 03:15:33'),('b6dd2ce6-987e-410e-8e1c-e44ff0cf91f8','bangalore@gmail.com','2024-03-07 23:35:34'),('b78c8191-a7f6-4f14-b6ef-784908c589fb','charu07@gmail.com','2024-03-08 04:03:43'),('bab30960-532e-4f82-989c-c08dc394b889','bangalore@gmail.com','2024-03-07 22:15:01'),('be3bbf56-902d-4a9d-9d93-4d0d565ff683','charu07@gmail.com','2024-03-07 23:04:51'),('c20ae388-b4df-4060-a5f5-cf0c64edec5a','charu07@gmail.com','2024-03-08 04:07:45'),('c454bb90-3b94-4865-a43e-833f6bcab0d0','bangalore@gmail.com','2024-03-07 23:22:38'),('c9681f06-f632-431c-909f-f47c7f9d9f6a','bangalore@gmail.com','2024-03-07 22:16:11'),('cee1f05f-da36-4472-a447-55e77f79dfdf','vasanth.albert@zohocorp.com','2024-03-08 04:12:29'),('d4981524-7d9c-4dde-8486-0bacc73d2de3','bangalore@gmail.com','2024-03-07 22:29:12'),('d66b7d65-995b-4bcd-8615-509dfc7d57a2','charu07@gmail.com','2024-03-07 22:58:38'),('da3f5d6d-9b38-4abe-8749-8238124947a8','bangalore@gmail.com','2024-03-07 23:18:17'),('dfd0bf42-66c8-4ccd-af72-031d3e384909','charu07@gmail.com','2024-03-07 23:07:08'),('e08c5e8d-6140-42d0-ab5d-3a930f4820ec','charu07@gmail.com','2024-03-07 23:03:26'),('e462a418-c7ac-4e0e-a413-528dd8835466','krishnagokul810@gmail.com','2024-03-08 03:17:43'),('e4d27f10-b7a4-40de-a036-de8d771c6689','charu07@gmail.com','2024-03-08 04:08:06'),('e6c5b6a8-9340-4736-bf43-4dd9061ca379','bangalore@gmail.com','2024-03-07 23:18:20'),('f202e6dd-b296-4229-a5c1-1066fcf7a925','bangalore@gmail.com','2024-03-07 22:10:26'),('f559b1bc-f52c-479b-bd17-649c02753d43','bangalore@gmail.com','2024-03-07 22:04:03'),('f5f84558-dc61-4b73-994e-3e2c62c68d60','charu07@gmail.com','2024-03-07 23:07:09'),('fee9ece8-190b-4a6f-96ab-35dc03ee3eff','bangalore@gmail.com','2024-03-07 23:11:11');
/*!40000 ALTER TABLE `Session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Solutions`
--

DROP TABLE IF EXISTS `Solutions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Solutions` (
  `Sol_ID` varchar(8) NOT NULL,
  `mailID` varchar(30) DEFAULT NULL,
  `Q_ID` varchar(8) DEFAULT NULL,
  `Sol` text,
  `lang_ID` int DEFAULT NULL,
  `solDate` date DEFAULT NULL,
  `status` enum('ATTEMPTED','COMPLETED') DEFAULT NULL,
  `solvedType` enum('PRACTICE','TOURNAMENT') DEFAULT NULL,
  PRIMARY KEY (`Sol_ID`),
  KEY `lang_ID` (`lang_ID`),
  KEY `mailID` (`mailID`),
  KEY `Q_ID` (`Q_ID`),
  CONSTRAINT `Solutions_ibfk_1` FOREIGN KEY (`lang_ID`) REFERENCES `Languages` (`l_ID`),
  CONSTRAINT `Solutions_ibfk_2` FOREIGN KEY (`mailID`) REFERENCES `Users` (`mailID`),
  CONSTRAINT `Solutions_ibfk_3` FOREIGN KEY (`Q_ID`) REFERENCES `Questions` (`Q_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Solutions`
--

LOCK TABLES `Solutions` WRITE;
/*!40000 ALTER TABLE `Solutions` DISABLE KEYS */;
INSERT INTO `Solutions` VALUES ('19829274','vasanth.albert@zohocorp.com','42887152',NULL,2,'2024-03-08','ATTEMPTED','PRACTICE'),('36309021','charu07@gmail.com','42887152',NULL,1,'2024-03-08','ATTEMPTED','PRACTICE'),('42994518','vasanth.albert@zohocorp.com','54281620',NULL,1,'2024-03-08','ATTEMPTED','PRACTICE'),('59892864','vasanth.albert@zohocorp.com','42887152','public class kata{\npublic static int reverse(String word) {\n	return word.reverse()\n}\n}',1,'2024-03-08','ATTEMPTED','PRACTICE'),('65266224','vasanth.albert@zohocorp.com','42887152','def reverse(word):\n	return word[::-1]',2,'2024-03-08','COMPLETED','PRACTICE'),('86532835','charu07@gmail.com','27624322',NULL,1,'2024-03-08','ATTEMPTED','PRACTICE'),('89290202','vasanth.albert@zohocorp.com','56873465','public class kata{\npublic static double sum_array(double[] numbers) {\ndouble total = 0;\n  for(double num : numbers){\n    total += num;\n  }\n  return total;\n}\n}',1,'2024-03-08','ATTEMPTED','PRACTICE');
/*!40000 ALTER TABLE `Solutions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tags`
--

DROP TABLE IF EXISTS `Tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Tags` (
  `Tag_ID` int NOT NULL AUTO_INCREMENT,
  `Tag_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Tag_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tags`
--

LOCK TABLES `Tags` WRITE;
/*!40000 ALTER TABLE `Tags` DISABLE KEYS */;
INSERT INTO `Tags` VALUES (1,'Arrays'),(2,'Algorithm'),(3,'Debugging'),(4,'Fundamentals'),(5,'String');
/*!40000 ALTER TABLE `Tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TagsRelation`
--

DROP TABLE IF EXISTS `TagsRelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TagsRelation` (
  `Q_ID` varchar(8) DEFAULT NULL,
  `Tag_ID` int DEFAULT NULL,
  KEY `Q_ID` (`Q_ID`),
  KEY `Tag_ID` (`Tag_ID`),
  CONSTRAINT `TagsRelation_ibfk_1` FOREIGN KEY (`Q_ID`) REFERENCES `Questions` (`Q_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `TagsRelation_ibfk_2` FOREIGN KEY (`Tag_ID`) REFERENCES `Tags` (`Tag_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TagsRelation`
--

LOCK TABLES `TagsRelation` WRITE;
/*!40000 ALTER TABLE `TagsRelation` DISABLE KEYS */;
INSERT INTO `TagsRelation` VALUES ('56873465',1),('45986677',1),('42887152',5),('54281620',4),('27624322',4),('27624322',1);
/*!40000 ALTER TABLE `TagsRelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tournament`
--

DROP TABLE IF EXISTS `Tournament`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Tournament` (
  `mailID` varchar(30) DEFAULT NULL,
  `Start_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `End_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Q_ID` varchar(8) DEFAULT NULL,
  UNIQUE KEY `mailID` (`mailID`),
  KEY `Q_ID` (`Q_ID`),
  CONSTRAINT `Tournament_ibfk_1` FOREIGN KEY (`Q_ID`) REFERENCES `Questions` (`Q_ID`),
  CONSTRAINT `Tournament_ibfk_2` FOREIGN KEY (`mailID`) REFERENCES `Users` (`mailID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tournament`
--

LOCK TABLES `Tournament` WRITE;
/*!40000 ALTER TABLE `Tournament` DISABLE KEYS */;
/*!40000 ALTER TABLE `Tournament` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Users` (
  `mailID` varchar(30) NOT NULL,
  `userName` varchar(50) DEFAULT NULL,
  `score` int DEFAULT '0',
  `streak` int DEFAULT '0',
  `Datejoined` date DEFAULT NULL,
  `Streakdate` date DEFAULT NULL,
  `Ace_badge` int DEFAULT '0',
  `Conquer_badge` int DEFAULT '0',
  `Crown_badge` int DEFAULT '0',
  PRIMARY KEY (`mailID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES ('amos@gmail.com','Amos',0,0,'2024-03-06',NULL,0,0,0),('bangalore@gmail.com','Bangalore',0,0,'2024-03-07','2024-03-08',0,0,0),('charu07@gmail.com','charu07',0,0,'2024-02-29','2024-03-08',0,0,0),('indhu@gmail.com','Indhu',0,0,'2024-03-06',NULL,0,0,0),('indirajith@gmail.com','Indirajith',0,0,'2024-03-06',NULL,0,0,0),('jeyrajesh@gmail.com','Jey Rajesh',0,0,'2024-03-06',NULL,0,0,0),('krishnagokul810@gmail.com','krishaee',0,0,'2024-02-29','2024-03-08',0,0,0),('kumaravel@gmail.com','Kumaravel',0,0,'2024-03-06',NULL,0,0,0),('mahalakshmi@gmail.com','Mahalakshmi',0,0,'2024-03-06',NULL,0,0,0),('sun.a@zohocorp.com','iamsun',0,0,'2024-03-07','2024-03-07',0,0,0),('vasanth.albert@zohocorp.com','vsr',16,0,'2024-02-29','2024-03-08',0,0,0);
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-08 10:09:52
