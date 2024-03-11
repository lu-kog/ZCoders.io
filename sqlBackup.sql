-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
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
INSERT INTO `Clan` VALUES ('40378295','Zsttk','bangalore@gmail.com'),('66423152','coders','krishnagokul810@gmail.com'),('78320745','Zoho Schools','vasanth.albert@zohocorp.com'),('93820754','CringeSquad','tharan@gmail.com');
/*!40000 ALTER TABLE `Clan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ClanRelation`
--

DROP TABLE IF EXISTS `ClanRelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ClanRelation` (
  `clanID` varchar(8) NOT NULL,
  `mailID` varchar(50) NOT NULL,
  `role` enum('CO_ADMIN','MEMBER','ADMIN') DEFAULT 'MEMBER',
  PRIMARY KEY (`mailID`,`clanID`),
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
INSERT INTO `ClanRelation` VALUES ('66423152','amos@gmail.com','MEMBER'),('40378295','bangalore@gmail.com','ADMIN'),('66423152','charu07@gmail.com','MEMBER'),('66423152','indhu@gmail.com','MEMBER'),('66423152','indirajith@gmail.com','MEMBER'),('66423152','krishnagokul810@gmail.com','ADMIN'),('66423152','kumaravel@gmail.com','MEMBER'),('66423152','mahalakshmi@gmail.com','MEMBER'),('66423152','ragavi@gmail.com','MEMBER'),('66423152','sorimuthu@gmail.com','MEMBER'),('93820754','tharan@gmail.com','ADMIN'),('78320745','vasanth.albert@zohocorp.com','ADMIN'),('66423152','vijila@gmail.com','MEMBER');
/*!40000 ALTER TABLE `ClanRelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ClanRequest`
--

DROP TABLE IF EXISTS `ClanRequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ClanRequest` (
  `clanID` varchar(8) NOT NULL,
  `mailID` varchar(30) NOT NULL,
  PRIMARY KEY (`clanID`,`mailID`),
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
INSERT INTO `ClanRequest` VALUES ('66423152','padma@gmail.com'),('66423152','rabi@gmail.com'),('66423152','santhiya@gmail.com'),('66423152','sun.a@zohocorp.com');
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
INSERT INTO `LanguageRelation` VALUES (1,'56873465','public static double sum_array(double[] numbers)',''),(2,'56873465','def sum_array(a)',''),(1,'45986677','public static int positive_sum(int[] arr)',''),(2,'45986677','def positive_sum(arr)',''),(1,'42887152','public static int reverse(String word)',''),(2,'42887152','def reverse(word)',''),(2,'27624322','def findLargest(a):',''),(2,'54281620','def isEven(num):',''),(1,'44414555','public class kata{\n	public static String flames(String male, String female){\n		return \"Friendship\";\n	}\n}',''),(2,'44414555','def flames(male, female):\n	return \"Friendship\"',''),(1,'54281620','public class kata{\n	public static boolean isEven(int num){\n\n}\n}',''),(1,'65781017','public class Kata {\n   public static int countVowels(String str) {\n       // Your code here\n   }\n}',''),(2,'65781017','def countVowels(s):\n    # Your code here\n    pass',''),(1,'03819247','public class Kata {\n   public static int findMax(int[] nums) {\n       // Your code here\n   }\n}',''),(2,'03819247','def findMax(nums):\n    # Your code here\n    pass',''),(1,'03696605','public class Kata {\n   public static boolean isPalindrome(String str) {\n       // Your code here\n   }\n}',''),(2,'03696605','def isPalindrome(s):\n    # Your code here\n    pass',''),(1,'63393309','public class Kata {\n   public static int sumOfDigits(int n) {\n       // Your code here\n   }\n}',''),(2,'63393309','def sumOfDigits(n):\n    # Your code here\n    pass',''),(1,'19091509','public class Kata {\n   public static boolean isPowerOfTwo(int n) {\n       // Your code here\n   }\n}',''),(2,'19091509','def isPowerOfTwo(n):\n    # Your code here\n    pass',''),(1,'35338104','public class Kata {\n   public static boolean isPrime(int n) {\n       // Your code here\n   }\n}',''),(2,'35338104','def isPrime(n):\n    # Your code here\n    pass','');
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
INSERT INTO `Login` VALUES ('charu07@gmail.com','$2a$10$3U4KHiDOSaiqgHXf9RDbaeJzAcplsVpYdxas50NUda/HcIbpt8NlW','USER'),('krishnagokul810@gmail.com','$2a$10$gNt6SZzJKPmZtVXB1TMWQ.bonA233722UPPCIuuXCN4F2FE7NvLIK','USER'),('vasanth.albert@zohocorp.com','$2a$10$VJc29ymwppWLHK4KkjSVaOBcPot4JRj87jsPS2ZeBtVsfw3ykVTdq','USER'),('sun.a@zohocorp.com','$2a$10$xZzNQo8vuMc8W08QQnPrNuH134wiuz9uijNZaSYyTnqcgJFohGxt.','USER');
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
INSERT INTO `Questions` VALUES ('03696605','Palindrome Check','\nYou need to write a function that checks whether a given string is a palindrome or not. A palindrome is a word, phrase, number, or other sequence of characters that reads the same forward and backward, ignoring spaces, punctuation, and capitalization.\n','Example (Input --> Output)\n\n\"radar\" --> true\n\"Hello\" --> false\n','isPalindrome','{\"11\":{\"output\":true,\"params\":{\"0\":\"Step on no pets\"}},\"12\":{\"output\":true,\"params\":{\"0\":\"12345678987654321\"}},\"13\":{\"output\":false,\"params\":{\"0\":\"Palindrome\"}},\"14\":{\"output\":true,\"params\":{\"0\":\"Eva, can I see bees in a cave?\"}},\"15\":{\"output\":true,\"params\":{\"0\":\"A Santa lived as a devil at NASA\"}},\"16\":{\"output\":false,\"params\":{\"0\":\"This is not a palindrome\"}},\"17\":{\"output\":true,\"params\":{\"0\":\"Was it a car or a cat I saw?\"}},\"18\":{\"output\":true,\"params\":{\"0\":\"Civic\"}},\"19\":{\"output\":false,\"params\":{\"0\":\"Not a palindrome\"}},\"0\":{\"output\":true,\"params\":{\"0\":\"radar\"}},\"1\":{\"output\":false,\"params\":{\"0\":\"Hello\"}},\"2\":{\"output\":true,\"params\":{\"0\":\"racecar\"}},\"3\":{\"output\":true,\"params\":{\"0\":\"Able was I, ere I saw Elba\"}},\"4\":{\"output\":true,\"params\":{\"0\":\"A man, a plan, a canal, Panama!\"}},\"5\":{\"output\":true,\"params\":{\"0\":\"No lemon, no melon\"}},\"6\":{\"output\":true,\"params\":{\"0\":\"Was it a car or a cat I saw?\"}},\"7\":{\"output\":true,\"params\":{\"0\":\"12321\"}},\"8\":{\"output\":false,\"params\":{\"0\":\"12345\"}},\"9\":{\"output\":true,\"params\":{\"0\":\"A man a plan a canal panama\"}},\"10\":{\"output\":true,\"params\":{\"0\":\"Madam, in Eden I\'m Adam\"}}}',2,'charu07@gmail.com','APPROVED'),('03819247','Find Maximum Number','\nYou need to write a function that finds the maximum number in a given array of integers.\n','Example (Input --> Output)\n\n[1, 3, 5, 7, 9] --> 9\n[-10, 0, 20, 30, 40] --> 40\n','findMax','{\"11\":{\"output\":1,\"params\":{\"0\":[1,1,1,1,1,0,1,1,1,1]}},\"12\":{\"output\":10,\"params\":{\"0\":[1,2,3,4,5,6,7,8,9,10]}},\"13\":{\"output\":10,\"params\":{\"0\":[10,9,8,7,6,5,4,3,2,1]}},\"14\":{\"output\":9,\"params\":{\"0\":[2,5,8,4,7,1,3,6,9]}},\"15\":{\"output\":9,\"params\":{\"0\":[5,8,9,7,4,6,3,1,2]}},\"16\":{\"output\":9,\"params\":{\"0\":[9,5,2,7,3,8,1,4,6]}},\"17\":{\"output\":9,\"params\":{\"0\":[1,3,5,7,9,2,4,6,8]}},\"18\":{\"output\":9,\"params\":{\"0\":[8,6,4,2,9,7,5,3,1]}},\"19\":{\"output\":9,\"params\":{\"0\":[5,6,7,8,9,1,2,3,4]}},\"0\":{\"output\":9,\"params\":{\"0\":[1,3,5,7,9]}},\"1\":{\"output\":40,\"params\":{\"0\":[-10,0,20,30,40]}},\"2\":{\"output\":5,\"params\":{\"0\":[5,5,5,5,5]}},\"3\":{\"output\":5,\"params\":{\"0\":[-5,-3,-1,0,1,3,5]}},\"4\":{\"output\":100,\"params\":{\"0\":[100,90,80,70,60]}},\"5\":{\"output\":0,\"params\":{\"0\":[0,0,0,0,0,0,0]}},\"6\":{\"output\":5,\"params\":{\"0\":[1,2,3,4,5,4,3,2,1]}},\"7\":{\"output\":-1,\"params\":{\"0\":[-1,-2,-3,-4,-5,-4,-3,-2,-1]}},\"8\":{\"output\":1,\"params\":{\"0\":[1]}},\"9\":{\"output\":-1,\"params\":{\"0\":[-1]}},\"10\":{\"output\":0,\"params\":{\"0\":[0]}}}',2,'charu07@gmail.com','APPROVED'),('19091509','Power of Two','\nYou need to write a function that checks whether a given positive integer is a power of two.\n','Example (Input --> Output)\n\n4 --> true\n10 --> false\n','isPowerOfTwo','{\"11\":{\"output\":true,\"params\":{\"0\":4096}},\"12\":{\"output\":true,\"params\":{\"0\":8192}},\"13\":{\"output\":true,\"params\":{\"0\":16384}},\"14\":{\"output\":true,\"params\":{\"0\":32768}},\"15\":{\"output\":true,\"params\":{\"0\":65536}},\"16\":{\"output\":true,\"params\":{\"0\":131072}},\"17\":{\"output\":true,\"params\":{\"0\":262144}},\"18\":{\"output\":true,\"params\":{\"0\":524288}},\"19\":{\"output\":true,\"params\":{\"0\":1048576}},\"0\":{\"output\":true,\"params\":{\"0\":4}},\"1\":{\"output\":false,\"params\":{\"0\":10}},\"2\":{\"output\":true,\"params\":{\"0\":1}},\"3\":{\"output\":true,\"params\":{\"0\":16}},\"4\":{\"output\":true,\"params\":{\"0\":32}},\"5\":{\"output\":false,\"params\":{\"0\":100}},\"6\":{\"output\":true,\"params\":{\"0\":128}},\"7\":{\"output\":true,\"params\":{\"0\":256}},\"8\":{\"output\":true,\"params\":{\"0\":512}},\"9\":{\"output\":true,\"params\":{\"0\":1024}},\"10\":{\"output\":true,\"params\":{\"0\":2048}}}',2,'charu07@gmail.com','APPROVED'),('27624322','Find Largest Of An Array','You get an array of numbers, return largest of all.','Example [1,-4,7,12] => 12','findLargest','{\"0\": {\n        \"params\": {\"0\": [5, 7, 1, 8, 4]},\n        \"output\": 8\n    },\n    \"1\": {\n        \"params\": {\"0\": [-3, -6, -1, -4]},\n        \"output\": -1\n    },\n    \"2\": {\n        \"params\": {\"0\": [0, 0, 0]},\n        \"output\": 0\n    },\n    \"3\": {\n        \"params\": {\"0\": [1, 2, 3, 4, 5, 6, 7, 8, 9]},\n        \"output\": 9\n    },\n    \"4\": {\n        \"params\": {\"0\": [8, 2, 7, 5, 4]},\n        \"output\": 8\n    },\n    \"5\": {\n        \"params\": {\"0\": [1]},\n        \"output\": 1\n    },\n    \"6\": {\n        \"params\": {\"0\": [2, -7, 3, -4]},\n        \"output\": 3\n    },\n    \"7\": {\n        \"params\": {\"0\": [100, 50, 75, 60]},\n        \"output\": 100\n    },\n    \"8\": {\n        \"params\": {\"0\": [123, 456, 789]},\n        \"output\": 789\n    }}',2,'charu07@gmail.com','APPROVED'),('35338104','Check Prime','\nYou need to write a function that checks whether a given integer is a prime number.\n','Example (Input --> Output)\n\n7 --> true\n12 --> false\n','isPrime','{\"11\":{\"output\":true,\"params\":{\"0\":103}},\"12\":{\"output\":true,\"params\":{\"0\":127}},\"13\":{\"output\":true,\"params\":{\"0\":131}},\"14\":{\"output\":true,\"params\":{\"0\":137}},\"15\":{\"output\":true,\"params\":{\"0\":139}},\"16\":{\"output\":true,\"params\":{\"0\":149}},\"17\":{\"output\":true,\"params\":{\"0\":151}},\"18\":{\"output\":true,\"params\":{\"0\":157}},\"19\":{\"output\":true,\"params\":{\"0\":163}},\"0\":{\"output\":true,\"params\":{\"0\":7}},\"1\":{\"output\":false,\"params\":{\"0\":12}},\"2\":{\"output\":true,\"params\":{\"0\":2}},\"3\":{\"output\":true,\"params\":{\"0\":17}},\"4\":{\"output\":true,\"params\":{\"0\":23}},\"5\":{\"output\":true,\"params\":{\"0\":31}},\"6\":{\"output\":true,\"params\":{\"0\":47}},\"7\":{\"output\":true,\"params\":{\"0\":53}},\"8\":{\"output\":true,\"params\":{\"0\":89}},\"9\":{\"output\":true,\"params\":{\"0\":97}},\"10\":{\"output\":true,\"params\":{\"0\":101}}}',2,'charu07@gmail.com','APPROVED'),('42887152','Reverse The String','You get a string, return its reverse.','Example \"codewars\" => \"srawedoc\"','reverse','{\n    \"0\": {\n        \"params\": { \"0\": \"hello\" },\n        \"output\":  \"olleh\"\n    },\n    \"1\": {\n        \"params\": { \"0\": \"world\" },\n        \"output\":  \"dlrow\"\n    },\n    \"2\": {\n        \"params\": { \"0\": \"\" },\n        \"output\":  \"\"\n    },\n    \"3\": {\n        \"params\": { \"0\": \"a\" },\n        \"output\":  \"a\"\n    },\n    \"4\": {\n        \"params\": { \"0\": \"!@#$%^\" },\n        \"output\":  \"^%$#@!\"\n    },\n    \"5\": {\n        \"params\": { \"0\": \" hello \" },\n        \"output\":  \" olleh \"\n    },\n    \"6\": {\n        \"params\": { \"0\": \"HeLlO WoRlD\" },\n        \"output\":  \"DlRoW OlLeH\"\n    },\n    \"7\": {\n        \"params\": { \"0\": \"12345\" },\n        \"output\":  \"54321\"\n    },\n    \"8\": {\n        \"params\": { \"0\": \"hello001 \" },\n        \"output\":  \" 100olleh\"\n    },\n    \"9\": {\n        \"params\": { \"0\": \"@!23\" },\n        \"output\":  \"32!@\"\n    },\n    \"10\": {\n        \"params\": { \"0\": \"hello  world\" },\n        \"output\":  \"dlrow  olleh\"\n    }\n}',1,'charu07@gmail.com','APPROVED'),('44414555','FLAMES','\nFLAMES game is a method to find out the status of a love relationship. Entering two names will give you the outcome of a relationship between them.\n\nTo get the outcome,  \nFirst, cross out all letters the names have in common.  \nSecond, remove the cross out letter on both names.  \nThird, get the count of the characters that are left.  \nFourth, given the word FLAMES, with each letter, starting from the left, count up to the number you got in the previous step and return to the F on the left with each pass.  \nFinally, the letter you land on has a word that it stands for in the acronym \"flames\".\n','    F = Friendship\n    L = Love\n    A = Affection\n    M = Marriage\n    E = Enemies\n    S = Siblings','flames','{\"22\":{\"output\":\"Enemies\",\"params\":{\"0\":\"Nikhil\",\"1\":\"Alisha\"}},\"23\":{\"output\":\"Friendship\",\"params\":{\"0\":\"Omkar\",\"1\":\"Bhakti\"}},\"24\":{\"output\":\"Friendship\",\"params\":{\"0\":\"Pranav\",\"1\":\"Charita\"}},\"25\":{\"output\":\"Friendship\",\"params\":{\"0\":\"Qadir\",\"1\":\"Dipti\"}},\"26\":{\"output\":\"Enemies\",\"params\":{\"0\":\"Rahul\",\"1\":\"Esha\"}},\"27\":{\"output\":\"Friendship\",\"params\":{\"0\":\"Sachin\",\"1\":\"Falguni\"}},\"28\":{\"output\":\"Marriage\",\"params\":{\"0\":\"Tarun\",\"1\":\"Gunjan\"}},\"10\":{\"output\":\"Siblings\",\"params\":{\"0\":\"Bhuvan\",\"1\":\"Kavya\"}},\"11\":{\"output\":\"Love\",\"params\":{\"0\":\"Chetan\",\"1\":\"Anjali\"}},\"12\":{\"output\":\"Love\",\"params\":{\"0\":\"Dev\",\"1\":\"Priya\"}},\"13\":{\"output\":\"Friendship\",\"params\":{\"0\":\"Eshan\",\"1\":\"Riya\"}},\"14\":{\"output\":\"Enemies\",\"params\":{\"0\":\"Farhan\",\"1\":\"Sania\"}},\"15\":{\"output\":\"Love\",\"params\":{\"0\":\"Girish\",\"1\":\"Tanvi\"}},\"16\":{\"output\":\"Friendship\",\"params\":{\"0\":\"Harish\",\"1\":\"Uma\"}},\"17\":{\"output\":\"Love\",\"params\":{\"0\":\"Ishan\",\"1\":\"Vidya\"}},\"18\":{\"output\":\"Love\",\"params\":{\"0\":\"Jai\",\"1\":\"Wendy\"}},\"19\":{\"output\":\"Marriage\",\"params\":{\"0\":\"Karan\",\"1\":\"Xena\"}},\"0\":{\"output\":\"Affection\",\"params\":{\"0\":\"Krish\",\"1\":\"Hermione\"}},\"1\":{\"output\":\"Friendship\",\"params\":{\"0\":\"rajesh\",\"1\":\"indrajith\"}},\"2\":{\"output\":\"Siblings\",\"params\":{\"0\":\"WEPISCISZ\",\"1\":\"VFB\"}},\"3\":{\"output\":\"Friendship\",\"params\":{\"0\":\"Vasanth\",\"1\":\"ida\"}},\"4\":{\"output\":\"Enemies\",\"params\":{\"0\":\"Tharan\",\"1\":\"Zameema Barwin\"}},\"5\":{\"output\":\"Siblings\",\"params\":{\"0\":\"Sorimuthu\",\"1\":\"Keerthy suresh\"}},\"6\":{\"output\":\"Enemies\",\"params\":{\"0\":\"Indirajith\",\"1\":\"Komi\"}},\"7\":{\"output\":\"Affection\",\"params\":{\"0\":\"Charu priyan\",\"1\":\"Charu Priya\"}},\"8\":{\"output\":\"Love\",\"params\":{\"0\":\"Ragavan\",\"1\":\"Ragavi\"}},\"9\":{\"output\":\"Enemies\",\"params\":{\"0\":\"Aarav\",\"1\":\"Meera\"}},\"20\":{\"output\":\"Marriage\",\"params\":{\"0\":\"Lokesh\",\"1\":\"Yasmin\"}},\"21\":{\"output\":\"Enemies\",\"params\":{\"0\":\"Manav\",\"1\":\"Zara\"}}}',2,'krishnagokul810@gmail.com','APPROVED'),('45986677','Sum Of Positive','You get an array of numbers, return the sum of all of the positives ones.','Example [1,-4,7,12] => 1 + 7 + 12 = 20','positive_sum','{\n    \"0\": {\n        \"params\": {\"0\": []},\n        \"output\": 0\n    },\n    \"1\": {\n        \"params\": {\"0\": [5]},\n        \"output\": 5\n    },\n    \"2\": {\n        \"params\": {\"0\": [1, 2, 3, 4, 5]},\n        \"output\": 15\n    },\n    \"3\": {\n        \"params\": {\"0\": [-1, -2, -3, -4, -5]},\n        \"output\": 0\n    },\n    \"4\": {\n        \"params\": {\"0\": [1, -2, 3, -4, 5]},\n        \"output\": 9\n    },\n    \"5\": {\n        \"params\": {\"0\": [1000000, 2000000, 3000000]},\n        \"output\": 6000000\n    },\n    \"6\": {\n        \"params\": {\"0\": [5, 5, 5, 5, 5]},\n        \"output\": 25\n    },\n    \"7\": {\n        \"params\": {\"0\": [0, 0, 0, 0, 0]},\n        \"output\": 0\n    },\n    \"8\": {\n        \"params\": {\"0\": [-5, 10]},\n        \"output\": 10\n    },\n    \"9\": {\n        \"params\": {\"0\": [-1000000]},\n        \"output\": 0\n    }\n}',2,'krishnagokul810@gmail.com','APPROVED'),('54281620','Check If It Is Even','You get a number, return a boolean whether the number is even','Example 453 => false','isEven','\n    {\n    \"0\": {\n        \"params\": {\"0\": 89437},\n        \"output\": false\n    },\n    \"1\": {\n        \"params\": {\"0\": 9586},\n        \"output\": true\n    },\n    \"2\": {\n        \"params\": {\"0\": -1},\n        \"output\": false\n    },\n    \"3\": {\n        \"params\": {\"0\": 1},\n        \"output\": false\n    },\n    \"4\": {\n        \"params\": {\"0\": 9},\n        \"output\": false\n    },\n    \"5\": {\n        \"params\": {\"0\": -11},\n        \"output\": false\n    },\n    \"6\": {\n        \"params\": {\"0\": 32},\n        \"output\": true\n    },\n    \"7\": {\n        \"params\": {\"0\": -256},\n        \"output\": true\n    },\n    \"8\": {\n        \"params\": {\"0\": 999},\n        \"output\": false\n    },\n    \"9\": {\n        \"params\": {\"0\": -1000},\n        \"output\": true\n    }\n											\n}',2,'charu07@gmail.com','APPROVED'),('56873465','Sum Arrays','Write a function that takes an array of numbers and returns the sum of the numbers. The numbers can be negative or non-integer. If the array does not contain any numbers then you should return 0.','Examples Input: [1, 5.2, 4, 0, -1]\nOutput: 9.2\n\nInput: []\nOutput: 0\n\n Input: [-2.398]\nOutput: -2.398\n\n','sum_array','{\n    \"0\": {\n        \"params\": {\"0\": []},\n        \"output\": 0\n    },\n    \"1\": {\n        \"params\": {\"0\": [5]},\n        \"output\": 5\n    },\n    \"2\": {\n        \"params\": {\"0\": [1, 2, 3, 4, 5]},\n        \"output\": 15\n    },\n    \"3\": {\n        \"params\": {\"0\": [-1, -2, -3, -4, -5]},\n        \"output\": -15\n    },\n    \"4\": {\n        \"params\": {\"0\": [1, -2, 3, -4, 5]},\n        \"output\": 3\n    },\n    \"5\": {\n        \"params\": {\"0\": [1000000, 2000000, 3000000]},\n        \"output\": 6000000\n    },\n    \"6\": {\n        \"params\": {\"0\": [5, 5, 5, 5, 5]},\n        \"output\": 25\n    },\n    \"7\": {\n        \"params\": {\"0\": [0, 0, 0, 0, 0]},\n        \"output\": 0\n    },\n    \"8\": {\n        \"params\": {\"0\": [-5, 10]},\n        \"output\": 5\n    },\n    \"9\": {\n        \"params\": {\"0\": [-1000000]},\n        \"output\": -1000000\n    }\n}',1,'charu07@gmail.com','APPROVED'),('63393309','Sum of Digits','\nYou need to write a function that calculates the sum of the digits of a given integer.\n','Example (Input --> Output)\n\n123 --> 6\n4567 --> 22\n','sumOfDigits','{\"11\":{\"output\":3,\"params\":{\"0\":101010}},\"12\":{\"output\":45,\"params\":{\"0\":1234567890}},\"13\":{\"output\":32,\"params\":{\"0\":2468642}},\"14\":{\"output\":39,\"params\":{\"0\":987654}},\"15\":{\"output\":45,\"params\":{\"0\":9876543210}},\"16\":{\"output\":18,\"params\":{\"0\":369}},\"17\":{\"output\":25,\"params\":{\"0\":123454321}},\"18\":{\"output\":25,\"params\":{\"0\":13579}},\"19\":{\"output\":45,\"params\":{\"0\":99999}},\"0\":{\"output\":6,\"params\":{\"0\":123}},\"1\":{\"output\":22,\"params\":{\"0\":4567}},\"2\":{\"output\":24,\"params\":{\"0\":789}},\"3\":{\"output\":30,\"params\":{\"0\":9876}},\"4\":{\"output\":3,\"params\":{\"0\":111}},\"5\":{\"output\":27,\"params\":{\"0\":999}},\"6\":{\"output\":1,\"params\":{\"0\":1000}},\"7\":{\"output\":15,\"params\":{\"0\":54321}},\"8\":{\"output\":45,\"params\":{\"0\":987654321}},\"9\":{\"output\":25,\"params\":{\"0\":13579}},\"10\":{\"output\":6,\"params\":{\"0\":111111}}}',2,'charu07@gmail.com','APPROVED'),('65781017','Count Vowels','\nYou need to write a function that counts the number of vowels in a given string. Only consider the English alphabet vowels (a, e, i, o, u), and ignore case sensitivity.\n','Example (Input --> Output)\n\n\"hello\" --> 2\n\"World\" --> 1\n','countVowels','{\"11\":{\"output\":1,\"params\":{\"0\":\"Swift\"}},\"12\":{\"output\":3,\"params\":{\"0\":\"JavaScript\"}},\"13\":{\"output\":0,\"params\":{\"0\":\"HTML & CSS\"}},\"14\":{\"output\":0,\"params\":{\"0\":\"PHP\"}},\"15\":{\"output\":2,\"params\":{\"0\":\"GoLang\"}},\"16\":{\"output\":1,\"params\":{\"0\":\"Rust\"}},\"17\":{\"output\":2,\"params\":{\"0\":\"Kotlin\"}},\"18\":{\"output\":2,\"params\":{\"0\":\"Scala\"}},\"19\":{\"output\":1,\"params\":{\"0\":\"Perl\"}},\"0\":{\"output\":2,\"params\":{\"0\":\"hello\"}},\"1\":{\"output\":1,\"params\":{\"0\":\"World\"}},\"2\":{\"output\":3,\"params\":{\"0\":\"Programming\"}},\"3\":{\"output\":6,\"params\":{\"0\":\"Python is awesome\"}},\"4\":{\"output\":10,\"params\":{\"0\":\"AEIOUaeiou\"}},\"5\":{\"output\":5,\"params\":{\"0\":\"abcdefghijklmnopqrstuvwxyz\"}},\"6\":{\"output\":0,\"params\":{\"0\":\"\"}},\"7\":{\"output\":0,\"params\":{\"0\":\"1234567890\"}},\"8\":{\"output\":4,\"params\":{\"0\":\"Java is Fun!\"}},\"9\":{\"output\":4,\"params\":{\"0\":\"Ruby on Rails\"}},\"10\":{\"output\":3,\"params\":{\"0\":\"C++ Programming\"}}}',2,'charu07@gmail.com','APPROVED');
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
INSERT INTO `Session` VALUES ('0710f24d-93ee-45b8-a88d-fa1e7a3d211e','krishnagokul810@gmail.com','2024-03-10 08:08:44'),('0d21ad79-64bb-42c9-aa55-b56d1fad4c57','charu07@gmail.com','2024-03-10 05:57:28'),('1ad317d2-5a7b-4ce6-9acf-f978e0b65ded','krishnagokul810@gmail.com','2024-03-10 08:07:32'),('1e27595b-cfb7-4312-9671-7b2d2a2fa715','charu07@gmail.com','2024-03-10 03:14:52'),('272a6da3-23fd-463c-a1ff-f5877844abd3','charu07@gmail.com','2024-02-29 16:54:57'),('2e5208e8-7214-48c1-a49d-360c212a5a16','charu07@gmail.com','2024-03-09 17:44:27'),('409f10bb-c6ce-4baa-9a5a-c8960a3fab05','charu07@gmail.com','2024-03-09 16:03:01'),('53555758-c9de-4800-891e-7e28023f7d50','krishnagokul810@gmail.com','2024-02-29 16:55:04'),('56fbd67e-0945-4747-8c06-594f9971ecdc','vasanth.albert@zohocorp.com','2024-02-29 16:55:28'),('64dd9534-4f54-449a-97ca-52f0f7c580bb','charu07@gmail.com','2024-03-10 06:18:15'),('6895bf28-a9dc-4d97-b377-e47095e03f2b','charu07@gmail.com','2024-03-09 14:38:45'),('87bb4bb5-088a-40d6-b163-937e71aaf4bc','charu07@gmail.com','2024-03-09 16:34:39'),('8af7b6e1-b200-4cbf-815a-12561e63139c','charu07@gmail.com','2024-03-10 04:45:52'),('97a574f0-4d8b-4cfd-b8b0-ef50b86b2468','vasanth.albert@zohocorp.com','2024-03-09 14:47:36'),('a2582c01-73dd-4539-a860-b764ad9dede6','charu07@gmail.com','2024-03-09 16:00:07'),('b262c06e-6c14-4816-aff1-a07e8de4e6b6','krishnagokul810@gmail.com','2024-03-10 08:06:57'),('c7854f5b-bf8d-4049-97db-fd9459ce35bd','charu07@gmail.com','2024-03-09 16:04:49'),('ded92cb6-6fbb-4253-9438-26454077aff0','charu07@gmail.com','2024-03-10 07:32:47'),('f048e86c-57b5-4765-bdd2-ace9b5e671dd','krishnagokul810@gmail.com','2024-03-10 11:34:59'),('fb385401-45b3-4446-8def-b7a2510a9ad1','charu07@gmail.com','2024-03-09 15:59:34');
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
INSERT INTO `Solutions` VALUES ('06435513','krishnagokul810@gmail.com','56873465',NULL,2,'2024-03-10','ATTEMPTED','PRACTICE'),('08169060','vasanth.albert@zohocorp.com','54281620','def isEven(num):\n	return num%2==0',2,'2024-03-09','COMPLETED','PRACTICE'),('12345678','vasanth.albert@zohocorp.com','56873465','double total = 0;\n    if(numbers.length == 0 || numbers == null)return total;\n    for(double number : numbers){\n      total+=number;\n    }\n    return total;',1,'2023-02-07','COMPLETED','PRACTICE'),('23456789','vasanth.albert@zohocorp.com','56873465','testing',1,'2023-08-07','COMPLETED','PRACTICE'),('23742666','charu07@gmail.com','54281620','def isEven(num):\n	return num%2==0',2,'2024-03-10','COMPLETED','PRACTICE'),('31673344','charu07@gmail.com','54281620',NULL,1,'2024-03-10','ATTEMPTED','PRACTICE'),('33085966','vasanth.albert@zohocorp.com','42887152',NULL,1,'2024-03-10','ATTEMPTED','PRACTICE'),('34567890','vasanth.albert@zohocorp.com','42887152','testing',1,'2023-08-07','COMPLETED','PRACTICE'),('35337766','charu07@gmail.com','42887152',NULL,1,'2024-03-10','ATTEMPTED','PRACTICE'),('45038957','charu07@gmail.com','42887152','def reverse(word):\n	return word[::-1]',2,'2024-03-10','COMPLETED','PRACTICE'),('47319412','krishnagokul810@gmail.com','42887152',NULL,1,'2024-03-10','COMPLETED','PRACTICE'),('49515098','krishnagokul810@gmail.com','56873465',NULL,1,'2024-03-10','ATTEMPTED','PRACTICE'),('50830965','vasanth.albert@zohocorp.com','42887152','def reverse(word):\n	return word[::-1]',2,'2024-03-10','ATTEMPTED','PRACTICE'),('56345625','charu07@gmail.com','42887152',NULL,1,'2024-03-10','COMPLETED','PRACTICE'),('56919254','krishnagokul810@gmail.com','56873465','def sum_array(a):\n	if not a:\n		return 0\n	else:\n		return sum(a)',2,'2024-03-10','COMPLETED','PRACTICE'),('58911125','vasanth.albert@zohocorp.com','03696605','def isPalindrome(s):\n	s = \'\'.join(char.lower() for char in s if char.isalnum())\n	print(s)\n	return s == s[::-1]\n\n',2,'2024-03-10','COMPLETED','PRACTICE'),('60554341','vasanth.albert@zohocorp.com','03819247','def findMax(nums):\n	max=nums[0]\n	for i in nums:\n		if (max<i):\n			max=i\n	return max',2,'2024-03-10','COMPLETED','PRACTICE'),('63408500','vasanth.albert@zohocorp.com','65781017','def countVowels(s):\n    # Define a set of vowels\n    vowels = {\'a\', \'e\', \'i\', \'o\', \'u\', \'A\', \'E\', \'I\', \'O\', \'U\'}\n    \n    # Initialize count to 0\n    count = 0\n    \n    # Iterate through each character in the string\n    for char in s:\n        # Check if the character is a vowel\n        if char in vowels:\n            count += 1\n    \n    return count',2,'2024-03-10','COMPLETED','PRACTICE'),('72300305','charu07@gmail.com','42887152','def reverse(word):\n	return word[::-1]',2,'2024-03-09','COMPLETED','PRACTICE'),('76225827','charu07@gmail.com','42887152','def reverse(word):\n	return word[::-1]',2,'2024-03-10','COMPLETED','PRACTICE'),('76242502','vasanth.albert@zohocorp.com','63393309','def sumOfDigits(n):\n    # Initialize the sum to 0\n    digit_sum = 0\n    \n    # Iterate through each digit of the number\n    while n > 0:\n        # Add the last digit to the sum\n        digit_sum += n % 10\n        \n        # Remove the last digit from the number\n        n //= 10\n    \n    return digit_sum',2,'2024-03-10','COMPLETED','PRACTICE'),('84290486','charu07@gmail.com','54281620','def Even(num):\n	return num%2==0',2,'2024-03-10','ATTEMPTED','PRACTICE'),('87654321','vasanth.albert@zohocorp.com','45986677','testing',2,'2023-08-07','ATTEMPTED','PRACTICE'),('91425996','vasanth.albert@zohocorp.com','03696605','public class kata {\n   public static boolean isPalindrome(String str) {\n        // Convert the string to lowercase and remove spaces and punctuation\n        str = str.toLowerCase().replaceAll(\"[^a-z0-9]\", \"\");\n        \n        // Initialize pointers for the start and end of the string\n        int i = 0;\n        int j = str.length() - 1;\n        \n        // Iterate over the string from both ends, comparing characters\n        while (i < j) {\n            // If characters at the pointers don\'t match, it\'s not a palindrome\n            if (str.charAt(i) != str.charAt(j)) {\n                return false;\n            }\n            // Move the pointers towards the center\n            i++;\n            j--;\n        }\n        // If the loop completes without returning false, the string is a palindrome\n        return true;\n\n   }\n}',1,'2024-03-10','ATTEMPTED','PRACTICE'),('92684745','krishnagokul810@gmail.com','42887152','def reverse(word):\n	return word[::-1]',2,'2024-03-10','ATTEMPTED','PRACTICE'),('95271962','vasanth.albert@zohocorp.com','19091509','def isPowerOfTwo(n):\n	if n <= 0:\n		return False\n	return (n & (n - 1)) == 0',2,'2024-03-10','COMPLETED','PRACTICE'),('98012013','vasanth.albert@zohocorp.com','54281620','public class kata{\n	public static boolean isEven(int num){\n	return num%2==0\n}\n}',1,'2024-03-09','ATTEMPTED','PRACTICE'),('99482772','vasanth.albert@zohocorp.com','35338104','def isPrime(n):\n    # Check if the number is less than 2\n    if n < 2:\n        return False\n    \n    # Check if the number is divisible by any integer from 2 to the square root of n\n    for i in range(2, int(n**0.5) + 1):\n        if n % i == 0:\n            return False\n    \n    return True',2,'2024-03-10','COMPLETED','PRACTICE');
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
INSERT INTO `TagsRelation` VALUES ('56873465',1),('45986677',1),('42887152',5),('54281620',4),('27624322',4),('27624322',1),('03819247',1);
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
  `Join_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Submit_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Date` date DEFAULT NULL,
  `Code` text,
  `Score` double DEFAULT NULL,
  `Q_ID` varchar(8) DEFAULT NULL,
  `Execution_time` time DEFAULT NULL,
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
INSERT INTO `Users` VALUES ('abitha@gmail.com','Abitha',0,0,'2024-03-06',NULL,0,0,0),('amos@gmail.com','Amos',0,0,'2024-03-06',NULL,0,0,0),('arun@gmail.com','Arun',0,0,'2024-03-06',NULL,0,0,0),('bangalore@gmail.com','Bangalore',0,0,'2024-03-06',NULL,0,0,0),('charu07@gmail.com','charu07',98,1,'2024-02-29','2024-03-10',0,0,0),('elango@gmail.com','Elango',0,0,'2024-03-06',NULL,0,0,0),('gopika@gmail.com','Gopika',0,0,'2024-03-06',NULL,0,0,0),('indhu@gmail.com','Indhu',0,0,'2024-03-06',NULL,0,0,0),('indirajith@gmail.com','Indirajith',0,0,'2024-03-06',NULL,0,0,0),('jeyrajesh@gmail.com','Jey Rajesh',0,0,'2024-03-06',NULL,0,0,0),('krishnagokul810@gmail.com','krishaee',8,1,'2024-02-29','2024-03-10',0,0,0),('kumaravel@gmail.com','Kumaravel',0,0,'2024-03-06',NULL,0,0,0),('mahalakshmi@gmail.com','Mahalakshmi',0,0,'2024-03-06',NULL,0,0,0),('mathimaran@gmail.com','Mathimaran',0,0,'2024-03-06',NULL,0,0,0),('naveenbabu@gmail.com','Naveen Babu',0,0,'2024-03-06',NULL,0,0,0),('padma@gmail.com','Padma',0,0,'2024-03-06',NULL,0,0,0),('piravin@gmail.com','Piravin',0,0,'2024-03-06',NULL,0,0,0),('rabi@gmail.com','Rabi',0,0,'2024-03-06',NULL,0,0,0),('ragavi@gmail.com','Ragavi',0,0,'2024-03-06',NULL,0,0,0),('rahul@gmail.com','Rahul',0,0,'2024-03-06',NULL,0,0,0),('santhiya@gmail.com','Santhiya',0,0,'2024-03-06',NULL,0,0,0),('saranesh@gmail.com','Saranesh',0,0,'2024-03-06',NULL,0,0,0),('sorimuthu@gmail.com','Sorimuthu',0,0,'2024-03-06',NULL,0,0,0),('sun.a@zohocorp.com','iamsun',0,0,'2024-03-07','2024-03-07',0,0,0),('sun@gmail.com','Sun',0,0,'2024-03-06',NULL,0,0,0),('sundari@gmail.com','Sundari',0,0,'2024-03-06',NULL,0,0,0),('tharan@gmail.com','Tharan',0,0,'2024-03-06',NULL,0,0,0),('ulaga@gmail.com','Ulaga',0,0,'2024-03-06',NULL,0,0,0),('vasanth.albert@zohocorp.com','vsr',27,2,'2024-02-29','2024-03-09',0,0,0),('vijila@gmail.com','Vijila',0,0,'2024-03-06',NULL,0,0,0);
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

-- Dump completed on 2024-03-10 14:14:30
