-- MariaDB dump 10.17  Distrib 10.5.3-MariaDB, for Win64 (AMD64)
--
-- Host: runerne.dk    Database: cdioFinal_2020
-- ------------------------------------------------------
-- Server version	10.3.18-MariaDB-1:10.3.18+maria~bionic

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
-- Table structure for table `ProduktBatches`
--

DROP TABLE IF EXISTS `ProduktBatches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProduktBatches` (
  `PBID` decimal(8,0) NOT NULL,
  `RID` decimal(8,0) DEFAULT NULL,
  `Standing` enum('Ikke påbegyndt','Under Produktion','Afsluttet') DEFAULT 'Ikke påbegyndt',
  `UserID` decimal(2,0) DEFAULT NULL,
  `RBID` decimal(8,0) NOT NULL DEFAULT 0,
  `Tara` decimal(6,4) DEFAULT NULL,
  `Netto` decimal(6,4) DEFAULT NULL,
  `Dato` date DEFAULT curdate(),
  PRIMARY KEY (`PBID`,`RBID`),
  KEY `RBID` (`RBID`),
  KEY `ProduktBatches_userdto_userID_fk` (`UserID`),
  KEY `ProduktBatches_Recepter__fk` (`RID`),
  CONSTRAINT `ProduktBatches_Recepter__fk` FOREIGN KEY (`RID`) REFERENCES `Recepter` (`RID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProduktBatches`
--

LOCK TABLES `ProduktBatches` WRITE;
/*!40000 ALTER TABLE `ProduktBatches` DISABLE KEYS */;
INSERT INTO `ProduktBatches` VALUES (1,11,'Afsluttet',16,5,0.0200,5.0000,'2020-06-18'),(1,11,'Afsluttet',16,8,2.0000,7.0000,'2020-06-18'),(1,11,'Afsluttet',16,19,0.0400,8.0000,'2020-06-19'),(2,12,'Afsluttet',16,10,0.2000,1.0000,'2020-06-18'),(2,12,'Afsluttet',16,12,0.2000,1.0000,'2020-06-18'),(3,13,'Ikke påbegyndt',NULL,0,NULL,NULL,'2020-06-18'),(5,15,'Ikke påbegyndt',NULL,0,NULL,NULL,'2020-06-18'),(6,11,'Afsluttet',16,5,1.0000,0.0000,'2020-06-18'),(6,11,'Afsluttet',16,8,1.0000,0.0000,'2020-06-18'),(6,11,'Afsluttet',16,19,0.2000,0.0000,'2020-06-18'),(30,11,'Under Produktion',NULL,0,NULL,NULL,'2020-06-23'),(30,11,'Under Produktion',16,5,0.2000,0.7000,'2020-06-21'),(31,12,'Ikke påbegyndt',NULL,0,NULL,NULL,'2020-06-21'),(32,16,'Ikke påbegyndt',NULL,0,NULL,NULL,'2020-06-21'),(33,11,'Ikke påbegyndt',NULL,0,NULL,NULL,'2020-06-21'),(34,11,'Ikke påbegyndt',NULL,0,NULL,NULL,'2020-06-21'),(44,10,'Ikke påbegyndt',NULL,0,NULL,NULL,'2020-06-23');
/*!40000 ALTER TABLE `ProduktBatches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RaavareBatches`
--

DROP TABLE IF EXISTS `RaavareBatches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RaavareBatches` (
  `rBID` decimal(8,0) NOT NULL,
  `raavareID` decimal(8,0) NOT NULL,
  `maengde` decimal(8,4) NOT NULL,
  `aktuelMaengde` decimal(8,4) NOT NULL,
  PRIMARY KEY (`rBID`),
  KEY `raavareID` (`raavareID`),
  CONSTRAINT `RaavareBatches_ibfk_1` FOREIGN KEY (`raavareID`) REFERENCES `Raavarer` (`raavareID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RaavareBatches`
--

LOCK TABLES `RaavareBatches` WRITE;
/*!40000 ALTER TABLE `RaavareBatches` DISABLE KEYS */;
INSERT INTO `RaavareBatches` VALUES (1,1,20.0000,20.0000),(2,2,10.0000,0.0000),(3,3,18.0000,7.0000),(4,2,5.0000,5.0000),(5,4,13.0000,9.4000),(6,5,16.0000,16.0000),(7,6,8.0000,6.8000),(8,6,5.0000,1.1300),(9,7,25.0000,23.8000),(10,8,18.3330,2.2980),(11,9,14.0000,3.0000),(12,10,19.5000,13.5000),(13,11,15.0000,15.0000),(14,12,2.0000,2.0000),(15,13,8.0000,8.0000),(16,14,8.0000,7.4000),(17,15,6.0000,4.5500),(18,15,9.0000,9.0000),(19,16,12.0000,11.6289),(20,9,5.0000,5.0000),(22,1,2.0000,2.0000),(29,11,4.0000,4.0000),(36,8,12.0000,11.0000),(66,2,12.0000,12.0000),(67,2,12.0000,12.0000),(109,2,4.0000,4.0000),(110,3,4.0000,4.0000);
/*!40000 ALTER TABLE `RaavareBatches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Raavarer`
--

DROP TABLE IF EXISTS `Raavarer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Raavarer` (
  `raavareID` decimal(8,0) NOT NULL,
  `raavareNavn` varchar(20) NOT NULL,
  `leverandoer` varchar(20) NOT NULL,
  PRIMARY KEY (`raavareID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Raavarer`
--

LOCK TABLES `Raavarer` WRITE;
/*!40000 ALTER TABLE `Raavarer` DISABLE KEYS */;
INSERT INTO `Raavarer` VALUES (1,'Vodka','Fakta'),(2,'Rom','Fakta'),(3,'Tequila','Føtex'),(4,'Gin','Føtex'),(5,'Whiskey','Netto'),(6,'Tonic','Netto'),(7,'Cola','Føtex'),(8,'Fanta','Fakta'),(9,'Sweps','Fakta'),(10,'Øl','Netto'),(11,'Isterninger','Netto'),(12,'Mynte','Brugsen'),(13,'Mælk','Brugsen'),(14,'Likør 43','Netto'),(15,'Æble Juice','Netto'),(16,'Lime','Netto');
/*!40000 ALTER TABLE `Raavarer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Recepter`
--

DROP TABLE IF EXISTS `Recepter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Recepter` (
  `RID` decimal(8,0) NOT NULL,
  `RName` varchar(20) DEFAULT NULL,
  `raavareID` decimal(8,0) NOT NULL,
  `nonNetto` decimal(5,3) DEFAULT NULL,
  `Tolerance` decimal(3,1) DEFAULT NULL,
  PRIMARY KEY (`RID`,`raavareID`),
  KEY `raavareID` (`raavareID`),
  CONSTRAINT `Recepter_ibfk_1` FOREIGN KEY (`raavareID`) REFERENCES `Raavarer` (`raavareID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Recepter`
--

LOCK TABLES `Recepter` WRITE;
/*!40000 ALTER TABLE `Recepter` DISABLE KEYS */;
INSERT INTO `Recepter` VALUES (10,'Rom/Cola',2,0.800,8.0),(10,'Rom/Cola',7,1.200,8.0),(10,'Rom/Cola',11,0.160,4.0),(11,'Gin/Tonic',4,0.700,5.5),(11,'Gin/Tonic',6,1.300,9.0),(11,'Gin/Tonic',16,0.090,2.0),(12,'Radler',8,1.000,3.5),(12,'Radler',10,1.000,3.5),(13,'Trylledrik',1,0.300,1.4),(13,'Trylledrik',3,0.250,5.6),(13,'Trylledrik',5,0.350,7.2),(13,'Trylledrik',8,0.200,4.4),(13,'Trylledrik',9,0.300,5.0),(13,'Trylledrik',14,0.100,9.0),(13,'Trylledrik',15,0.500,6.4),(14,'Æbleshot',14,0.300,4.0),(14,'Æbleshot',15,0.500,6.0),(15,'Farlig drik',3,0.800,8.0),(15,'Farlig drik',8,0.800,9.0),(15,'Farlig drik',9,0.300,5.0),(15,'Farlig drik',11,0.060,8.0),(16,'Saft vand',7,0.500,3.9),(16,'Saft vand',8,0.900,5.9),(16,'Saft vand',9,0.700,0.1),(16,'Saft vand',12,0.800,4.9),(16,'Saft vand',15,0.600,2.0);
/*!40000 ALTER TABLE `Recepter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `printA`
--

DROP TABLE IF EXISTS `printA`;
/*!50001 DROP VIEW IF EXISTS `printA`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `printA` (
  `PBID` tinyint NOT NULL,
  `RID` tinyint NOT NULL,
  `raavareID` tinyint NOT NULL,
  `RName` tinyint NOT NULL,
  `raavareNavn` tinyint NOT NULL,
  `leverandoer` tinyint NOT NULL,
  `Standing` tinyint NOT NULL,
  `nonNetto` tinyint NOT NULL,
  `Tolerance` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `printB`
--

DROP TABLE IF EXISTS `printB`;
/*!50001 DROP VIEW IF EXISTS `printB`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `printB` (
  `RBID` tinyint NOT NULL,
  `PBID` tinyint NOT NULL,
  `UserID` tinyint NOT NULL,
  `RID` tinyint NOT NULL,
  `Tara` tinyint NOT NULL,
  `Netto` tinyint NOT NULL,
  `Dato` tinyint NOT NULL,
  `raavareID` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `printBatch`
--

DROP TABLE IF EXISTS `printBatch`;
/*!50001 DROP VIEW IF EXISTS `printBatch`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `printBatch` (
  `PBID` tinyint NOT NULL,
  `RID` tinyint NOT NULL,
  `raavareID` tinyint NOT NULL,
  `RName` tinyint NOT NULL,
  `raavareNavn` tinyint NOT NULL,
  `leverandoer` tinyint NOT NULL,
  `Standing` tinyint NOT NULL,
  `nonNetto` tinyint NOT NULL,
  `Tolerance` tinyint NOT NULL,
  `RBID` tinyint NOT NULL,
  `UserID` tinyint NOT NULL,
  `Tara` tinyint NOT NULL,
  `Netto` tinyint NOT NULL,
  `Dato` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `raavareLager`
--

DROP TABLE IF EXISTS `raavareLager`;
/*!50001 DROP VIEW IF EXISTS `raavareLager`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `raavareLager` (
  `raavareID` tinyint NOT NULL,
  `raavareNavn` tinyint NOT NULL,
  `leverandoer` tinyint NOT NULL,
  `lagerbeholdning` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `raavarebatchview`
--

DROP TABLE IF EXISTS `raavarebatchview`;
/*!50001 DROP VIEW IF EXISTS `raavarebatchview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `raavarebatchview` (
  `raavareID` tinyint NOT NULL,
  `rBID` tinyint NOT NULL,
  `maengde` tinyint NOT NULL,
  `aktuelMaengde` tinyint NOT NULL,
  `raavareNavn` tinyint NOT NULL,
  `leverandoer` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `receptRaavare`
--

DROP TABLE IF EXISTS `receptRaavare`;
/*!50001 DROP VIEW IF EXISTS `receptRaavare`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `receptRaavare` (
  `RID` tinyint NOT NULL,
  `RName` tinyint NOT NULL,
  `raavareID` tinyint NOT NULL,
  `raavareNavn` tinyint NOT NULL,
  `nonNetto` tinyint NOT NULL,
  `Tolerance` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `userdto`
--

DROP TABLE IF EXISTS `userdto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userdto` (
  `userID` decimal(2,0) NOT NULL,
  `userName` varchar(20) NOT NULL,
  `ini` varchar(4) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `job` enum('Administrator','Farmaceut','Produktionsleder','Laborant') DEFAULT NULL,
  `aktiv` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userdto`
--

LOCK TABLES `userdto` WRITE;
/*!40000 ALTER TABLE `userdto` DISABLE KEYS */;
INSERT INTO `userdto` VALUES (11,'Mark','MA','password222','Administrator',1),(12,'Frederik','FD','updatepassz','Farmaceut',0),(13,'Talha the man','TH','dontchange','Farmaceut',1),(14,'Mikkel','MK','pass123!','Produktionsleder',1),(15,'Lasse','LS','pass123!','Produktionsleder',1),(16,'Volkan','VI','12345','Laborant',1),(17,'Stig','SY','asdfwe','Laborant',0);
/*!40000 ALTER TABLE `userdto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `visRecepter`
--

DROP TABLE IF EXISTS `visRecepter`;
/*!50001 DROP VIEW IF EXISTS `visRecepter`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `visRecepter` (
  `RID` tinyint NOT NULL,
  `RName` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `printA`
--

/*!50001 DROP TABLE IF EXISTS `printA`*/;
/*!50001 DROP VIEW IF EXISTS `printA`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_unicode_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`kamel`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `printA` AS select distinct `ProduktBatches`.`PBID` AS `PBID`,`ProduktBatches`.`RID` AS `RID`,`Recepter`.`raavareID` AS `raavareID`,`Recepter`.`RName` AS `RName`,`Raavarer`.`raavareNavn` AS `raavareNavn`,`Raavarer`.`leverandoer` AS `leverandoer`,`ProduktBatches`.`Standing` AS `Standing`,`Recepter`.`nonNetto` AS `nonNetto`,`Recepter`.`Tolerance` AS `Tolerance` from (`ProduktBatches` join (`Recepter` join `Raavarer` on(`Recepter`.`raavareID` = `Raavarer`.`raavareID`)) on(`ProduktBatches`.`RID` = `Recepter`.`RID`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `printB`
--

/*!50001 DROP TABLE IF EXISTS `printB`*/;
/*!50001 DROP VIEW IF EXISTS `printB`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_unicode_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`kamel`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `printB` AS select `ProduktBatches`.`RBID` AS `RBID`,`ProduktBatches`.`PBID` AS `PBID`,`ProduktBatches`.`UserID` AS `UserID`,`ProduktBatches`.`RID` AS `RID`,`ProduktBatches`.`Tara` AS `Tara`,`ProduktBatches`.`Netto` AS `Netto`,`ProduktBatches`.`Dato` AS `Dato`,`RaavareBatches`.`raavareID` AS `raavareID` from (`ProduktBatches` left join `RaavareBatches` on(`ProduktBatches`.`RBID` = `RaavareBatches`.`rBID`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `printBatch`
--

/*!50001 DROP TABLE IF EXISTS `printBatch`*/;
/*!50001 DROP VIEW IF EXISTS `printBatch`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_unicode_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`kamel`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `printBatch` AS select `printA`.`PBID` AS `PBID`,`printA`.`RID` AS `RID`,`printA`.`raavareID` AS `raavareID`,`printA`.`RName` AS `RName`,`printA`.`raavareNavn` AS `raavareNavn`,`printA`.`leverandoer` AS `leverandoer`,`printA`.`Standing` AS `Standing`,`printA`.`nonNetto` AS `nonNetto`,`printA`.`Tolerance` AS `Tolerance`,`printB`.`RBID` AS `RBID`,`printB`.`UserID` AS `UserID`,`printB`.`Tara` AS `Tara`,`printB`.`Netto` AS `Netto`,`printB`.`Dato` AS `Dato` from (`printA` left join `printB` on(`printA`.`PBID` = `printB`.`PBID` and `printA`.`RID` = `printB`.`RID` and `printA`.`raavareID` = `printB`.`raavareID`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `raavareLager`
--

/*!50001 DROP TABLE IF EXISTS `raavareLager`*/;
/*!50001 DROP VIEW IF EXISTS `raavareLager`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`kamel`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `raavareLager` AS select `Raavarer`.`raavareID` AS `raavareID`,`Raavarer`.`raavareNavn` AS `raavareNavn`,`Raavarer`.`leverandoer` AS `leverandoer`,sum(`RaavareBatches`.`aktuelMaengde`) AS `lagerbeholdning` from (`Raavarer` left join `RaavareBatches` on(`Raavarer`.`raavareID` = `RaavareBatches`.`raavareID`)) group by `Raavarer`.`raavareID` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `raavarebatchview`
--

/*!50001 DROP TABLE IF EXISTS `raavarebatchview`*/;
/*!50001 DROP VIEW IF EXISTS `raavarebatchview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_unicode_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`kamel`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `raavarebatchview` AS select `RaavareBatches`.`raavareID` AS `raavareID`,`RaavareBatches`.`rBID` AS `rBID`,`RaavareBatches`.`maengde` AS `maengde`,`RaavareBatches`.`aktuelMaengde` AS `aktuelMaengde`,`Raavarer`.`raavareNavn` AS `raavareNavn`,`Raavarer`.`leverandoer` AS `leverandoer` from (`RaavareBatches` join `Raavarer` on(`RaavareBatches`.`raavareID` = `Raavarer`.`raavareID`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `receptRaavare`
--

/*!50001 DROP TABLE IF EXISTS `receptRaavare`*/;
/*!50001 DROP VIEW IF EXISTS `receptRaavare`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_unicode_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`kamel`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `receptRaavare` AS select `Recepter`.`RID` AS `RID`,`Recepter`.`RName` AS `RName`,`Recepter`.`raavareID` AS `raavareID`,`Raavarer`.`raavareNavn` AS `raavareNavn`,`Recepter`.`nonNetto` AS `nonNetto`,`Recepter`.`Tolerance` AS `Tolerance` from (`Recepter` join `Raavarer` on(`Recepter`.`raavareID` = `Raavarer`.`raavareID`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `visRecepter`
--

/*!50001 DROP TABLE IF EXISTS `visRecepter`*/;
/*!50001 DROP VIEW IF EXISTS `visRecepter`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_unicode_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`kamel`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `visRecepter` AS select distinct `Recepter`.`RID` AS `RID`,`Recepter`.`RName` AS `RName` from `Recepter` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-23 14:21:34
