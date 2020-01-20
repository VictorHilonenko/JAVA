-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: mycardb
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
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop` (
  `idshop` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `telephone` varchar(45) NOT NULL,
  `idmanufacturer` int(11) NOT NULL,
  PRIMARY KEY (`idshop`),
  UNIQUE KEY `idshop_UNIQUE` (`idshop`),
  KEY `fk_shop_manufacturer_idx` (`idmanufacturer`),
  CONSTRAINT `fk_shop_manufacturer` FOREIGN KEY (`idmanufacturer`) REFERENCES `manufacturer` (`idmanufacturer`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES (1,'shop6','+380671100742',1),(2,'shop7','+380671284199',2),(3,'shop8','+380671467656',3),(4,'shop9','+380671651113',1),(5,'shop10','+380671834570',2),(6,'shop11','+380672018027',3),(7,'shop12','+380672201484',1),(8,'shop13','+380672384941',2),(9,'shop14','+380672568398',3),(10,'shop15','+380672751855',1),(11,'shop16','+380672935312',2),(12,'shop17','+380673118769',3),(13,'shop18','+380673302226',1),(14,'shop19','+380673485683',2),(15,'shop20','+380673669140',3),(16,'shop21','+380673852597',1),(17,'shop22','+380674036054',2),(18,'shop23','+380674219511',3),(19,'shop24','+380674402968',1),(20,'shop25','+380674586425',2),(21,'shop26','+380674769882',3),(22,'shop27','+380674953339',1),(23,'shop28','+380675136796',2),(24,'shop29','+380675320253',3),(25,'shop30','+380675503710',1),(26,'shop31','+380675687167',2),(27,'shop32','+380675870624',3),(28,'shop33','+380676054081',1),(29,'shop34','+380676237538',2),(30,'shop35','+380676420995',3),(31,'shop36','+380676604452',1),(32,'shop37','+380676787909',2),(33,'shop38','+380676971366',3),(34,'shop39','+380677154823',1),(35,'shop40','+380677338280',2),(36,'shop41','+380677521737',3),(37,'shop42','+380677705194',1),(38,'shop43','+380677888651',2),(39,'shop44','+380678072108',3),(40,'shop45','+380678255565',1),(41,'shop46','+380678439022',2),(42,'shop47','+380678622479',3),(43,'shop48','+380678805936',1),(44,'shop49','+380678989393',2),(45,'shop50','+380679172850',3);
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-20 16:15:50
