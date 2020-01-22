-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: ss3db
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appointment_date` date NOT NULL,
  `appointment_time` tinyint(4) NOT NULL,
  `service_provided` bit(1) NOT NULL DEFAULT b'0',
  `service_type` varchar(255) NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  `master_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4q5rt20vvnkv7eohwq22l3ayy` (`customer_id`),
  KEY `FKrqdfukjcn0rhqrac8j96cu9w4` (`master_id`),
  CONSTRAINT `FK4q5rt20vvnkv7eohwq22l3ayy` FOREIGN KEY (`customer_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKrqdfukjcn0rhqrac8j96cu9w4` FOREIGN KEY (`master_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
INSERT INTO `appointments` VALUES (1,'2020-01-22',19,_binary '','HAIRDRESSING',15,18),(59,'2020-01-22',9,_binary '\0','HAIRDRESSING',15,18),(60,'2020-01-22',11,_binary '\0','COSMETOLOGY',15,16),(61,'2020-01-22',13,_binary '\0','MAKEUP',15,26),(62,'2020-01-21',12,_binary '','HAIRDRESSING',23,18),(63,'2020-01-21',14,_binary '','COSMETOLOGY',23,16),(64,'2020-01-21',16,_binary '','MAKEUP',23,26),(65,'2020-01-21',18,_binary '','HAIRDRESSING',15,18),(66,'2020-01-21',16,_binary '','COSMETOLOGY',15,16),(67,'2020-01-21',10,_binary '','MAKEUP',15,26),(68,'2020-01-22',11,_binary '\0','HAIRDRESSING',23,18),(69,'2020-01-22',18,_binary '\0','COSMETOLOGY',23,16),(70,'2020-01-22',20,_binary '\0','MAKEUP',23,26),(71,'2020-01-20',19,_binary '','HAIRDRESSING',15,18),(72,'2020-01-20',10,_binary '','COSMETOLOGY',15,16),(73,'2020-01-20',17,_binary '','MAKEUP',15,26),(74,'2020-01-20',9,_binary '','HAIRDRESSING',23,18),(75,'2020-01-20',8,_binary '','COSMETOLOGY',23,16),(76,'2020-01-20',20,_binary '','MAKEUP',23,26),(77,'2020-01-19',10,_binary '','HAIRDRESSING',15,18),(78,'2020-01-19',15,_binary '','COSMETOLOGY',15,16),(79,'2020-01-19',18,_binary '','MAKEUP',15,26),(80,'2020-01-19',16,_binary '','HAIRDRESSING',23,18),(81,'2020-01-19',17,_binary '','COSMETOLOGY',23,16),(82,'2020-01-19',11,_binary '','MAKEUP',23,26),(83,'2020-01-18',12,_binary '','HAIRDRESSING',23,18),(84,'2020-01-18',14,_binary '','COSMETOLOGY',23,16),(85,'2020-01-18',16,_binary '','MAKEUP',23,26),(86,'2020-01-18',18,_binary '','HAIRDRESSING',15,18),(87,'2020-01-18',16,_binary '','COSMETOLOGY',15,16),(88,'2020-01-18',10,_binary '','MAKEUP',15,26),(89,'2020-01-17',11,_binary '','HAIRDRESSING',23,18),(90,'2020-01-17',18,_binary '','COSMETOLOGY',23,16),(91,'2020-01-17',20,_binary '','MAKEUP',23,26),(92,'2020-01-17',19,_binary '','HAIRDRESSING',15,18),(93,'2020-01-17',10,_binary '','COSMETOLOGY',15,16),(94,'2020-01-17',17,_binary '','MAKEUP',15,26),(95,'2020-01-16',9,_binary '','HAIRDRESSING',23,18),(96,'2020-01-16',8,_binary '','COSMETOLOGY',23,16),(97,'2020-01-16',20,_binary '','MAKEUP',23,26),(98,'2020-01-15',10,_binary '','HAIRDRESSING',15,18),(99,'2020-01-15',15,_binary '','COSMETOLOGY',15,16),(100,'2020-01-15',18,_binary '','MAKEUP',15,26),(101,'2020-01-15',16,_binary '','HAIRDRESSING',23,18),(102,'2020-01-15',17,_binary '','COSMETOLOGY',23,16),(103,'2020-01-15',11,_binary '','MAKEUP',23,26),(104,'2020-01-23',12,_binary '\0','HAIRDRESSING',23,18),(105,'2020-01-23',14,_binary '\0','COSMETOLOGY',23,16),(106,'2020-01-23',16,_binary '\0','MAKEUP',23,26),(107,'2020-01-23',18,_binary '\0','HAIRDRESSING',15,18),(108,'2020-01-23',16,_binary '\0','COSMETOLOGY',15,16),(109,'2020-01-23',10,_binary '\0','MAKEUP',15,26),(110,'2020-01-24',11,_binary '\0','HAIRDRESSING',23,18),(111,'2020-01-24',18,_binary '\0','COSMETOLOGY',23,16),(112,'2020-01-24',20,_binary '\0','MAKEUP',23,26),(113,'2020-01-25',19,_binary '\0','HAIRDRESSING',15,18),(114,'2020-01-25',10,_binary '\0','COSMETOLOGY',15,16),(115,'2020-01-25',17,_binary '\0','MAKEUP',15,26),(116,'2020-01-26',9,_binary '\0','HAIRDRESSING',23,18),(117,'2020-01-26',8,_binary '\0','COSMETOLOGY',23,16),(118,'2020-01-26',20,_binary '\0','MAKEUP',23,26),(119,'2020-01-22',17,_binary '\0','HAIRDRESSING',28,18),(120,'2020-01-22',16,_binary '\0','COSMETOLOGY',28,16);
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email_messages`
--

DROP TABLE IF EXISTS `email_messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `email_messages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_created` date NOT NULL,
  `date_link_opened` date DEFAULT NULL,
  `date_sent` date DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `quick_access_code` varchar(255) DEFAULT NULL,
  `subject` varchar(255) NOT NULL,
  `text_message` text,
  PRIMARY KEY (`id`),
  KEY `IDX_DATESENT` (`date_sent`),
  KEY `IDX_QUICKACCESSCODE` (`quick_access_code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_messages`
--

LOCK TABLES `email_messages` WRITE;
/*!40000 ALTER TABLE `email_messages` DISABLE KEYS */;
INSERT INTO `email_messages` VALUES (1,'2020-01-22',NULL,'2020-01-22','user@ukr.net','117968af-371c-4a56-b044-5045c2dd121f','leave feedback, please','please, leave your feedback here: http://localhost:8989/feedbacks/1/117968af-371c-4a56-b044-5045c2dd121f'),(2,'2020-01-22',NULL,'2020-01-22','user@ukr.net','98924ae1-0034-4d20-b8eb-386c903eab4b','leave feedback, please','please, leave your feedback here: http://localhost:8989/feedbacks/1/98924ae1-0034-4d20-b8eb-386c903eab4b'),(3,'2020-01-22',NULL,'2020-01-22','user@ukr.net','a12c5ebf-4d71-447c-9485-5db8eea33a13','leave feedback, please','please, leave your feedback here: http://localhost:8989/feedbacks/1/a12c5ebf-4d71-447c-9485-5db8eea33a13'),(4,'2020-01-22',NULL,'2020-01-22','user@ukr.net','ada2c7e7-4fbf-4564-bc81-b7a9a1b501f2','leave feedback, please','please, leave your feedback here: http://localhost:8989/feedbacks/98/ada2c7e7-4fbf-4564-bc81-b7a9a1b501f2');
/*!40000 ALTER TABLE `email_messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedbacks`
--

DROP TABLE IF EXISTS `feedbacks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedbacks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appointment_date` date NOT NULL,
  `appointment_time` tinyint(4) NOT NULL,
  `rating` tinyint(4) NOT NULL DEFAULT '0',
  `service_type` varchar(255) NOT NULL,
  `text_message` varchar(255) NOT NULL,
  `appointment_id` bigint(20) NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  `master_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK58uid2ow3xl8erqdy5qy7prkm` (`appointment_id`),
  KEY `FK8kw5agn6ypgg4lkjrbc54wk0c` (`customer_id`),
  KEY `FKrfdekc1kpu3cug5qjaxnkd4cv` (`master_id`),
  CONSTRAINT `FK58uid2ow3xl8erqdy5qy7prkm` FOREIGN KEY (`appointment_id`) REFERENCES `appointments` (`id`),
  CONSTRAINT `FK8kw5agn6ypgg4lkjrbc54wk0c` FOREIGN KEY (`customer_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKrfdekc1kpu3cug5qjaxnkd4cv` FOREIGN KEY (`master_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedbacks`
--

LOCK TABLES `feedbacks` WRITE;
/*!40000 ALTER TABLE `feedbacks` DISABLE KEYS */;
INSERT INTO `feedbacks` VALUES (1,'2020-01-22',19,10,'HAIRDRESSING','your first feedback!',1,15,18),(2,'2020-01-21',18,10,'HAIRDRESSING','дуже дякую за гарний сервіс!',65,15,18),(3,'2020-01-21',16,10,'COSMETOLOGY','дуже дякую за гарний сервіс!',66,15,16),(5,'2020-01-20',19,10,'HAIRDRESSING','thanks for your excellent work!',71,15,18),(6,'2020-01-20',10,10,'COSMETOLOGY','thanks for your excellent work!',72,15,16),(7,'2020-01-20',17,10,'MAKEUP','excellent!',73,15,26),(8,'2020-01-19',10,10,'HAIRDRESSING','дуже дякую за гарний сервіс!',77,15,18),(9,'2020-01-19',15,10,'COSMETOLOGY','дуже дякую за гарний сервіс!',78,15,16),(10,'2020-01-19',18,10,'MAKEUP','дуже дякую за гарний сервіс!',79,15,26),(20,'2020-01-21',10,10,'MAKEUP','thank you guys,\r\nwill recommend you to my friends!',67,15,26),(21,'2020-01-18',18,10,'HAIRDRESSING','thanks for your excellent work!',86,15,18),(22,'2020-01-18',16,10,'COSMETOLOGY','дуже дякую за гарний сервіс!',87,15,16),(23,'2020-01-18',10,10,'MAKEUP','дуже дякую за гарний сервіс!',88,15,26),(24,'2020-01-17',19,10,'HAIRDRESSING','thanks for your excellent work!',92,15,18),(25,'2020-01-17',10,10,'COSMETOLOGY','good job!!!',93,15,16),(26,'2020-01-17',17,10,'MAKEUP','thanks for your excellent work!',94,15,26),(27,'2020-01-15',10,10,'HAIRDRESSING','дуже дякую за гарний сервіс!',98,15,18),(28,'2020-01-15',15,10,'COSMETOLOGY','good job!!!',99,15,16),(29,'2020-01-15',18,10,'MAKEUP','thanks for your excellent work!',100,15,26),(35,'2020-01-21',12,10,'HAIRDRESSING','дуже дякую за гарний сервіс!',62,23,18),(36,'2020-01-21',14,10,'COSMETOLOGY','thanks for your excellent work!',63,23,16),(37,'2020-01-21',16,10,'MAKEUP','дуже дякую за гарний сервіс!',64,23,26),(38,'2020-01-20',9,10,'HAIRDRESSING','дуже дякую за гарний сервіс!',74,23,18),(39,'2020-01-20',8,10,'COSMETOLOGY','thanks for your excellent work!',75,23,16),(40,'2020-01-20',20,10,'MAKEUP','дуже дякую за гарний сервіс!',76,23,26),(41,'2020-01-19',16,10,'HAIRDRESSING','дуже дякую за гарний сервіс!',80,23,18),(42,'2020-01-19',17,10,'COSMETOLOGY','thanks for your excellent work!',81,23,16),(43,'2020-01-19',11,10,'MAKEUP','дуже дякую за гарний сервіс!',82,23,26),(44,'2020-01-18',12,10,'HAIRDRESSING','thanks for your excellent work!',83,23,18),(45,'2020-01-18',14,10,'COSMETOLOGY','дуже дякую за гарний сервіс!',84,23,16),(46,'2020-01-18',16,10,'MAKEUP','thanks for your excellent work!',85,23,26),(47,'2020-01-17',11,10,'HAIRDRESSING','good job!!!',89,23,18),(48,'2020-01-17',18,10,'COSMETOLOGY','thanks for your excellent work!',90,23,16),(49,'2020-01-17',20,10,'MAKEUP','good job!!!',91,23,26),(50,'2020-01-16',9,10,'HAIRDRESSING','дуже дякую за гарний сервіс!',95,23,18),(51,'2020-01-16',8,10,'COSMETOLOGY','thanks for your excellent work!',96,23,16),(52,'2020-01-16',20,10,'MAKEUP','дуже дякую за гарний сервіс!',97,23,26),(53,'2020-01-15',16,10,'HAIRDRESSING','thanks for your excellent work!',101,23,18),(54,'2020-01-15',17,10,'COSMETOLOGY','good job!!!',102,23,16),(55,'2020-01-15',11,10,'MAKEUP','thanks for your excellent work!',103,23,26);
/*!40000 ALTER TABLE `feedbacks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `service_type` varchar(255) DEFAULT NULL,
  `tel_number` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  KEY `IDX_ROLE` (`role`),
  KEY `IDX_SERVICETYPE` (`service_type`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (14,'admin@ukr.net','Admin','Admin','$2a$10$it5GyDjgwDCzSQdvZNzfIOwq1TAm4mf0phWLvZXMFtvTM6.4z5rm.','ROLE_ADMIN',NULL,'+380671234567'),(15,'user@ukr.net','User','User','$2a$10$dGkGLJal4NnWugv4IYcTkueQEOxAjMbu0U9loOBQuDjTsGueR8zUG','ROLE_USER',NULL,'+380671596897'),(16,'master@ukr.net','Master','Master','$2a$10$w1aZ.IvvQcRCIuFiVv4ooONdgI69KRDbNYUp56idGZUG7LoCcnW7G','ROLE_MASTER','COSMETOLOGY','+380675796428'),(18,'master2@ukr.net','Master2','Master2','$2a$10$w1aZ.IvvQcRCIuFiVv4ooONdgI69KRDbNYUp56idGZUG7LoCcnW7G','ROLE_MASTER','HAIRDRESSING','+380675796428'),(23,'user2@ukr.net','user2','user2','$2a$10$4betNKttzhCPxiAudLBNQ.IKpyFGTLp9P6cEwREWuVRa08Ukb300q','ROLE_USER',NULL,'+380671234567'),(26,'master3@ukr.net','Master3','Master3','$2a$10$QH7Njp5xedE4z/xwl9iXB.MusCuCEkMu6XhFLuJ8bucMsPw6YgwZm','ROLE_MASTER','MAKEUP','+380671234957'),(28,'123@ukr.net','123','123','$2a$10$V9SujUodCmu1L2/RFxVuHeMA7hoA8wNPC5Yu0H2YrtWQ.xIkUCZB6','ROLE_USER',NULL,'+380671234567');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-22 15:01:16
