-- MySQL dump 10.13  Distrib 8.0.12, for osx10.13 (x86_64)
--
-- Host: equipo3.c8laxzmm3pst.us-east-2.rds.amazonaws.com    Database: equipo_3
-- ------------------------------------------------------
-- Server version	5.6.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `equipo_3`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `equipo_3` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `equipo_3`;

--
-- Table structure for table `DispositivoEstandar`
--

DROP TABLE IF EXISTS `DispositivoEstandar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `DispositivoEstandar` (
  `horasPorDia` float DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_tno480su3qgkqmcrg23qhqe4` FOREIGN KEY (`id`) REFERENCES `dispositivo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DispositivoEstandar`
--

LOCK TABLES `DispositivoEstandar` WRITE;
/*!40000 ALTER TABLE `DispositivoEstandar` DISABLE KEYS */;
INSERT INTO `DispositivoEstandar` VALUES (5,3),(5,8),(5,9),(10,12);
/*!40000 ALTER TABLE `DispositivoEstandar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DispositivoInteligente`
--

DROP TABLE IF EXISTS `DispositivoInteligente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `DispositivoInteligente` (
  `id` int(11) NOT NULL,
  `actuador_id` int(11) DEFAULT NULL,
  `dispositivo_id` int(11) DEFAULT NULL,
  `estandar_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4aeig44n3jdgyq4e8p19k0cm7` (`actuador_id`),
  KEY `FK_lju2qt6okfaasdgf5fgf94i3` (`dispositivo_id`),
  KEY `FK_3c8x6385o99spq6bv8fgs34yl` (`estandar_id`),
  CONSTRAINT `FK_3c8x6385o99spq6bv8fgs34yl` FOREIGN KEY (`estandar_id`) REFERENCES `DispositivoEstandar` (`id`),
  CONSTRAINT `FK_4aeig44n3jdgyq4e8p19k0cm7` FOREIGN KEY (`actuador_id`) REFERENCES `actuadorEnum` (`id`),
  CONSTRAINT `FK_4iewo4n0slfo3o2jqt2u9xp96` FOREIGN KEY (`id`) REFERENCES `dispositivo` (`id`),
  CONSTRAINT `FK_lju2qt6okfaasdgf5fgf94i3` FOREIGN KEY (`dispositivo_id`) REFERENCES `dispositivo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DispositivoInteligente`
--

LOCK TABLES `DispositivoInteligente` WRITE;
/*!40000 ALTER TABLE `DispositivoInteligente` DISABLE KEYS */;
INSERT INTO `DispositivoInteligente` VALUES (1,NULL,NULL,NULL),(2,NULL,NULL,NULL),(4,NULL,NULL,NULL),(5,NULL,NULL,NULL),(6,NULL,NULL,NULL),(7,NULL,NULL,NULL),(10,NULL,NULL,NULL),(11,NULL,NULL,NULL);
/*!40000 ALTER TABLE `DispositivoInteligente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actuadorEnum`
--

DROP TABLE IF EXISTS `actuadorEnum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `actuadorEnum` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `valorEnum` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actuadorEnum`
--

LOCK TABLES `actuadorEnum` WRITE;
/*!40000 ALTER TABLE `actuadorEnum` DISABLE KEYS */;
/*!40000 ALTER TABLE `actuadorEnum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actuadorString`
--

DROP TABLE IF EXISTS `actuadorString`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `actuadorString` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actuadorString`
--

LOCK TABLES `actuadorString` WRITE;
/*!40000 ALTER TABLE `actuadorString` DISABLE KEYS */;
/*!40000 ALTER TABLE `actuadorString` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `categoria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `concepto` varchar(255) DEFAULT NULL,
  `consumoMaximo` float DEFAULT NULL,
  `consumoMinimo` float DEFAULT NULL,
  `costoNormal` float DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `unidad` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'cargo fijo',150,0,18.76,'R1','$ / mes'),(4,NULL,0,0,0,'Baja',NULL);
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `condicion`
--

DROP TABLE IF EXISTS `condicion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `condicion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comparador` varchar(255) DEFAULT NULL,
  `valorComparable` float DEFAULT NULL,
  `regla_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_o59x7e6nfir3fax0xn7qmvd8j` (`regla_id`),
  CONSTRAINT `FK_o59x7e6nfir3fax0xn7qmvd8j` FOREIGN KEY (`regla_id`) REFERENCES `regla` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `condicion`
--

LOCK TABLES `condicion` WRITE;
/*!40000 ALTER TABLE `condicion` DISABLE KEYS */;
/*!40000 ALTER TABLE `condicion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consumo`
--

DROP TABLE IF EXISTS `consumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `consumo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `consumo` float DEFAULT NULL,
  `fechaFin` varchar(255) DEFAULT NULL,
  `fechaInicio` varchar(255) DEFAULT NULL,
  `modo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jtf2jsq864wjxuugnhtub9jf3` (`modo_id`),
  CONSTRAINT `FK_jtf2jsq864wjxuugnhtub9jf3` FOREIGN KEY (`modo_id`) REFERENCES `modo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumo`
--

LOCK TABLES `consumo` WRITE;
/*!40000 ALTER TABLE `consumo` DISABLE KEYS */;
INSERT INTO `consumo` VALUES (17,10,'2018-12-01 04:39','2018-11-21 04:39',2),(18,12,'2018-12-01 04:39','2018-11-21 04:39',4),(19,14,'2018-12-01 04:39','2018-11-21 04:39',6),(20,16,'2018-12-01 04:39','2018-11-21 04:39',8),(21,18,'2018-12-01 04:39','2018-11-21 04:39',10),(22,20,'2018-12-01 04:39','2018-11-21 04:39',12),(23,22,'2018-12-01 04:39','2018-11-21 04:39',14),(24,24,'2018-12-01 04:39','2018-11-21 04:39',16);
/*!40000 ALTER TABLE `consumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coordenada`
--

DROP TABLE IF EXISTS `coordenada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `coordenada` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `latitud` double DEFAULT NULL,
  `longitud` double DEFAULT NULL,
  `orden` int(11) DEFAULT NULL,
  `ubicable_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qdibx011bnc9lam0qi2ybfx6t` (`ubicable_id`),
  CONSTRAINT `FK_qdibx011bnc9lam0qi2ybfx6t` FOREIGN KEY (`ubicable_id`) REFERENCES `ubicable` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1242 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coordenada`
--

LOCK TABLES `coordenada` WRITE;
/*!40000 ALTER TABLE `coordenada` DISABLE KEYS */;
INSERT INTO `coordenada` VALUES (851,-58.3928,-34.592516,NULL,1),(852,-58.377142,-34.612528,NULL,2),(853,-58.41844,-34.609544,NULL,3),(854,-58.4003392,-34.5915908,NULL,4),(855,-58.3950094,-34.5905518,NULL,5),(856,-58.3785185,-34.6117183,NULL,6),(857,-58.4204898,-34.6080176,NULL,7),(865,-34.59769534,-58.4559204833296,NULL,56),(873,-34.5965761676,-58.4651613793847,14,57),(881,-34.5983289,-58.4277732175759,21,58),(889,-34.61214969,-58.4975330554217,28,59),(897,-34.6195831102,-58.412635307155,35,60),(905,-34.61017193,-58.4303706394215,42,61),(913,-34.6218774,-58.4861843614612,49,62),(921,-34.62759438,-58.5072937377628,56,63),(929,-34.6155085589,-58.5231198470571,63,64),(937,-34.6426558,-58.4424394616308,70,65),(945,-34.6242299,-58.4817872755475,77,66),(953,-34.63236208,-58.3796357190522,84,67),(961,-34.62428587,-58.3914031361925,91,68),(969,-34.62477815,-58.4121266481758,98,69),(977,-34.633942423,-58.4823802976343,105,70),(985,-34.6325566,-58.4989855796206,112,71),(993,-34.6456676984,-58.4128049759678,119,72),(1001,-34.66733993,-58.5119878187484,126,73),(1009,-34.66004938,-58.4644302838092,133,74),(1017,-34.6223951698,-58.3659664964511,140,75),(1025,-34.56219563,-58.4900433596499,147,76),(1033,-34.5667379,-58.4746626186112,154,77),(1041,-34.56377035,-58.4795414780967,161,78),(1049,-34.570563848,-58.4436397049603,168,79),(1057,-34.5980675,-58.4080567119894,175,80),(1065,-34.61408,-58.4756607446043,182,81),(1073,-34.58849234078,-58.4721174082916,189,82),(1081,-34.5988312,-58.4807802385542,196,83),(1089,-34.581853258,-58.460531948912,203,84),(1097,-34.63577807,-58.3699176031895,210,85),(1105,-34.6424171,-58.4679898002072,217,86),(1113,-34.64521601,-58.4326181247605,224,87),(1121,-34.64743450073,-58.4050002156404,231,88),(1129,-34.55256439,-58.4244180806301,238,89),(1137,-34.70109398,-58.457977934298,245,90),(1145,-34.70109398,-58.457977934298,245,91),(1153,-34.59108179,-58.5060719423589,259,92),(1161,-34.61443877,-58.5092058744191,266,93),(1169,-34.6330005,-58.5185225442665,273,94),(1177,-34.634646039,-58.508763610993,280,95),(1185,-34.5952357,-58.3568728026777,287,96),(1193,-34.61099849,-58.3672591297974,294,97),(1201,-34.6065239,-58.3693658551563,301,98),(1209,-34.531860012,-58.4512807613204,308,99),(1217,-34.57223517,-58.3927996372665,315,100),(1225,-34.5782103,-58.3680091541883,322,101),(1233,-34.5276483772,-58.45627168191,329,102),(1241,-34.619537121,-58.3530849547986,336,103);
/*!40000 ALTER TABLE `coordenada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dispositivo`
--

DROP TABLE IF EXISTS `dispositivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `dispositivo` (
  `tipo` varchar(31) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activado` bit(1) DEFAULT NULL,
  `fecha_alta` varchar(255) DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `dispositivoDetalle_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6m7rbebu37l1f66s4y5y8ccsq` (`cliente_id`),
  KEY `FK_1p5xfinukhit784vtlb7ji7rf` (`dispositivoDetalle_id`),
  CONSTRAINT `FK_1p5xfinukhit784vtlb7ji7rf` FOREIGN KEY (`dispositivoDetalle_id`) REFERENCES `dispositivoDetalle` (`id`),
  CONSTRAINT `FK_6m7rbebu37l1f66s4y5y8ccsq` FOREIGN KEY (`cliente_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dispositivo`
--

LOCK TABLES `dispositivo` WRITE;
/*!40000 ALTER TABLE `dispositivo` DISABLE KEYS */;
INSERT INTO `dispositivo` VALUES ('inteligente',1,_binary '','2018-12-01 02:37:42',4,1),('inteligente',2,_binary '','2018-12-01 02:37:43',4,9),('estandar',3,_binary '','2018-12-01 02:37:43',5,23),('inteligente',4,_binary '','2018-12-01 02:37:43',5,2),('inteligente',5,_binary '','2018-12-01 02:37:44',5,19),('inteligente',6,_binary '','2018-12-01 02:37:44',5,8),('inteligente',7,_binary '','2018-12-01 02:38:30',6,10),('estandar',8,_binary '','2018-12-01 02:38:30',6,23),('estandar',9,_binary '','2018-12-01 02:38:30',6,5),('inteligente',10,_binary '','2018-12-01 02:38:30',6,18),('inteligente',11,_binary '','2018-12-01 02:38:31',6,12),('estandar',12,_binary '','2018-12-01 02:37:42',4,5);
/*!40000 ALTER TABLE `dispositivo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dispositivoDetalle`
--

DROP TABLE IF EXISTS `dispositivoDetalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `dispositivoDetalle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `consumoKwHora` float DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `bajoConsumo` bit(1) DEFAULT NULL,
  `esencial` bit(1) DEFAULT NULL,
  `inteligente` bit(1) DEFAULT NULL,
  `hsMensualMaximo` int(11) DEFAULT NULL,
  `hsMensualMinimo` int(11) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dispositivoDetalle`
--

LOCK TABLES `dispositivoDetalle` WRITE;
/*!40000 ALTER TABLE `dispositivoDetalle` DISABLE KEYS */;
INSERT INTO `dispositivoDetalle` VALUES (1,1.613,'De 3500 frigorias',_binary '\0',_binary '\0',_binary '',360,90,'Aire Acondicionado'),(2,1.013,'De 2200 frigorias',_binary '',_binary '\0',_binary '',360,90,'Aire Acondicionado'),(3,0.075,'Color de tubo fluorescente de 21',_binary '\0',_binary '\0',_binary '\0',360,90,'Televisor'),(4,0.175,'Color de tubo fluorescente de 29 a 34',_binary '\0',_binary '\0',_binary '\0',360,90,'Televisor'),(5,0.18,'LCD de 40',_binary '\0',_binary '\0',_binary '\0',360,90,'Televisor'),(6,0.04,'LED 24',_binary '',_binary '\0',_binary '',360,90,'Televisor'),(7,0.055,'LED 32',_binary '',_binary '\0',_binary '',360,90,'Televisor'),(8,0.08,'LED 40',_binary '',_binary '',_binary '',360,90,'Televisor'),(9,0.09,'Con freezer',_binary '',_binary '',_binary '',0,0,'Heladera'),(10,0.075,'Sin freezer',_binary '',_binary '',_binary '',0,0,'Heladera'),(11,0.875,'Automatico de 5 kg con calentamiento de agua',_binary '\0',_binary '\0',_binary '',30,6,'Lavarropas'),(12,0.175,'Automatico de 5 kg',_binary '',_binary '\0',_binary '',30,6,'Lavarropas'),(13,0.1275,'Semi-automatico de 5 kg',_binary '',_binary '\0',_binary '\0',30,6,'Lavarropas'),(14,0.09,'De pie',_binary '',_binary '\0',_binary '\0',360,120,'Ventilador'),(15,0.06,'De techo',_binary '',_binary '\0',_binary '',360,120,'Ventilador'),(16,0.04,'Halogenas de 40 W',_binary '\0',_binary '\0',_binary '',360,90,'Lampara'),(17,0.06,'Halogenas de 60 W',_binary '\0',_binary '\0',_binary '',360,90,'Lampara'),(18,0.015,'Halogenas de 100 W',_binary '\0',_binary '\0',_binary '',360,90,'Lampara'),(19,0.011,'De 11 W',_binary '',_binary '\0',_binary '',360,90,'Lampara'),(20,0.015,'De 15 W',_binary '',_binary '\0',_binary '',360,90,'Lampara'),(21,0.02,'De 20 W',_binary '',_binary '\0',_binary '',360,90,'Lampara'),(22,0.4,'De escritorio',_binary '',_binary '\0',_binary '',360,90,'PC'),(23,0.64,'Convencional',_binary '',_binary '\0',_binary '\0',15,3,'Microondas'),(24,0.75,'A vapor',_binary '',_binary '\0',_binary '\0',30,0,'Plancha');
/*!40000 ALTER TABLE `dispositivoDetalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dispositivosAdaptadorLog`
--

DROP TABLE IF EXISTS `dispositivosAdaptadorLog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `dispositivosAdaptadorLog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fechaFin` tinyblob,
  `fechaInicio` tinyblob,
  `adaptadorInteligente_id` int(11) DEFAULT NULL,
  `estandar_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_q40rscow41g81o3e3xud1kitd` (`adaptadorInteligente_id`),
  KEY `FK_862sjge21ylulknplk0ov66tn` (`estandar_id`),
  CONSTRAINT `FK_862sjge21ylulknplk0ov66tn` FOREIGN KEY (`estandar_id`) REFERENCES `DispositivoEstandar` (`id`),
  CONSTRAINT `FK_q40rscow41g81o3e3xud1kitd` FOREIGN KEY (`adaptadorInteligente_id`) REFERENCES `DispositivoInteligente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dispositivosAdaptadorLog`
--

LOCK TABLES `dispositivosAdaptadorLog` WRITE;
/*!40000 ALTER TABLE `dispositivosAdaptadorLog` DISABLE KEYS */;
/*!40000 ALTER TABLE `dispositivosAdaptadorLog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicion`
--

DROP TABLE IF EXISTS `medicion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `medicion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fechaFin` tinyblob,
  `fechaInicio` tinyblob,
  `magnitud` int(11) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicion`
--

LOCK TABLES `medicion` WRITE;
/*!40000 ALTER TABLE `medicion` DISABLE KEYS */;
/*!40000 ALTER TABLE `medicion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modo`
--

DROP TABLE IF EXISTS `modo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `modo` (
  `tipo` varchar(31) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fechaHoraFin` varchar(255) DEFAULT NULL,
  `fechaHoraInicio` varchar(255) DEFAULT NULL,
  `dispositivo_inteligente_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1xv17lnytsneg9c3l2mx9cfmm` (`dispositivo_inteligente_id`),
  CONSTRAINT `FK_1xv17lnytsneg9c3l2mx9cfmm` FOREIGN KEY (`dispositivo_inteligente_id`) REFERENCES `DispositivoInteligente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modo`
--

LOCK TABLES `modo` WRITE;
/*!40000 ALTER TABLE `modo` DISABLE KEYS */;
INSERT INTO `modo` VALUES ('apagado',1,'2018-12-01T05:03:45','2018-11-21T05:12:23',1),('encendido',2,'2018-12-01T05:03:45','2018-11-21T05:12:23',1),('apagado',3,'2018-12-01T05:03:45','2018-11-21T05:12:23',2),('encendido',4,'2018-12-01T05:03:45','2018-11-21T05:12:23',2),('apagado',5,'2018-12-01T05:03:45','2018-11-21T05:12:23',4),('encendido',6,'2018-12-01T05:03:45','2018-11-21T05:12:23',4),('apagado',7,'2018-12-01T05:03:45','2018-11-21T05:12:23',5),('encendido',8,'2018-12-01T05:03:45','2018-11-21T05:12:23',5),('apagado',9,'2018-12-01T05:03:45','2018-11-21T05:12:23',6),('encendido',10,'2018-12-01T05:03:45','2018-11-21T05:12:23',6),('apagado',11,'2018-12-01T05:03:45','2018-11-21T05:12:23',7),('encendido',12,'2018-12-01T05:03:45','2018-11-21T05:12:23',7),('apagado',13,'2018-12-01T05:03:45','2018-11-21T05:12:23',10),('encendido',14,'2018-12-01T05:03:45','2018-11-21T05:12:23',10),('apagado',15,'2018-12-01T05:03:45','2018-11-21T05:12:23',11),('encendido',16,'2018-12-01T05:03:45','2018-11-21T05:12:23',11);
/*!40000 ALTER TABLE `modo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `observador`
--

DROP TABLE IF EXISTS `observador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `observador` (
  `tipo` varchar(31) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `observador`
--

LOCK TABLES `observador` WRITE;
/*!40000 ALTER TABLE `observador` DISABLE KEYS */;
/*!40000 ALTER TABLE `observador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regla`
--

DROP TABLE IF EXISTS `regla`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `regla` (
  `tipo` varchar(31) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activado` bit(1) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `reglapadre_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_51l7hm2nubanggjun5vp25ugk` (`reglapadre_id`),
  CONSTRAINT `FK_51l7hm2nubanggjun5vp25ugk` FOREIGN KEY (`reglapadre_id`) REFERENCES `regla` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regla`
--

LOCK TABLES `regla` WRITE;
/*!40000 ALTER TABLE `regla` DISABLE KEYS */;
/*!40000 ALTER TABLE `regla` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regla_DispositivoInteligente`
--

DROP TABLE IF EXISTS `regla_DispositivoInteligente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `regla_DispositivoInteligente` (
  `regla_id` int(11) NOT NULL,
  `dispositivos_id` int(11) NOT NULL,
  KEY `FK_bxcl4udu4vs9a2o5906e01nky` (`dispositivos_id`),
  KEY `FK_6gs6a0b4kxblbaoffs3yx4dkj` (`regla_id`),
  CONSTRAINT `FK_6gs6a0b4kxblbaoffs3yx4dkj` FOREIGN KEY (`regla_id`) REFERENCES `regla` (`id`),
  CONSTRAINT `FK_bxcl4udu4vs9a2o5906e01nky` FOREIGN KEY (`dispositivos_id`) REFERENCES `DispositivoInteligente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regla_DispositivoInteligente`
--

LOCK TABLES `regla_DispositivoInteligente` WRITE;
/*!40000 ALTER TABLE `regla_DispositivoInteligente` DISABLE KEYS */;
/*!40000 ALTER TABLE `regla_DispositivoInteligente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regla_actuadorEnum`
--

DROP TABLE IF EXISTS `regla_actuadorEnum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `regla_actuadorEnum` (
  `regla_id` int(11) NOT NULL,
  `actuadores_enums_id` int(11) NOT NULL,
  KEY `FK_9stlh4d9c2xqel36swdtx0ht8` (`actuadores_enums_id`),
  KEY `FK_4n0gnk3jg404vjgsrietirxjq` (`regla_id`),
  CONSTRAINT `FK_4n0gnk3jg404vjgsrietirxjq` FOREIGN KEY (`regla_id`) REFERENCES `regla` (`id`),
  CONSTRAINT `FK_9stlh4d9c2xqel36swdtx0ht8` FOREIGN KEY (`actuadores_enums_id`) REFERENCES `actuadorEnum` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regla_actuadorEnum`
--

LOCK TABLES `regla_actuadorEnum` WRITE;
/*!40000 ALTER TABLE `regla_actuadorEnum` DISABLE KEYS */;
/*!40000 ALTER TABLE `regla_actuadorEnum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regla_actuadorstring`
--

DROP TABLE IF EXISTS `regla_actuadorstring`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `regla_actuadorstring` (
  `regla_id` int(11) NOT NULL,
  `actuador_id` int(11) NOT NULL,
  KEY `FK_blix0eqkfd1iiqapd8h21bbwd` (`actuador_id`),
  KEY `FK_3f98hfma9qq57uaiacn05wlhj` (`regla_id`),
  CONSTRAINT `FK_3f98hfma9qq57uaiacn05wlhj` FOREIGN KEY (`regla_id`) REFERENCES `regla` (`id`),
  CONSTRAINT `FK_blix0eqkfd1iiqapd8h21bbwd` FOREIGN KEY (`actuador_id`) REFERENCES `actuadorString` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regla_actuadorstring`
--

LOCK TABLES `regla_actuadorstring` WRITE;
/*!40000 ALTER TABLE `regla_actuadorstring` DISABLE KEYS */;
/*!40000 ALTER TABLE `regla_actuadorstring` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regla_dispositivointeligente`
--

DROP TABLE IF EXISTS `regla_dispositivointeligente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `regla_dispositivointeligente` (
  `regla_id` int(11) NOT NULL,
  `dispositivo_id` int(11) NOT NULL,
  KEY `FK_qs7h3ckssy2sebohy7scd211p` (`dispositivo_id`),
  KEY `FK_niymieyrol44sywe7k263oiir` (`regla_id`),
  CONSTRAINT `FK_niymieyrol44sywe7k263oiir` FOREIGN KEY (`regla_id`) REFERENCES `regla` (`id`),
  CONSTRAINT `FK_qs7h3ckssy2sebohy7scd211p` FOREIGN KEY (`dispositivo_id`) REFERENCES `DispositivoInteligente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regla_dispositivointeligente`
--

LOCK TABLES `regla_dispositivointeligente` WRITE;
/*!40000 ALTER TABLE `regla_dispositivointeligente` DISABLE KEYS */;
/*!40000 ALTER TABLE `regla_dispositivointeligente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sensor`
--

DROP TABLE IF EXISTS `sensor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sensor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sensor`
--

LOCK TABLES `sensor` WRITE;
/*!40000 ALTER TABLE `sensor` DISABLE KEYS */;
/*!40000 ALTER TABLE `sensor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sensor_medicion`
--

DROP TABLE IF EXISTS `sensor_medicion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sensor_medicion` (
  `sensor_id` int(11) NOT NULL,
  `mediciones_id` int(11) NOT NULL,
  UNIQUE KEY `UK_hc2o8oowhrfm82apobtyoxumm` (`mediciones_id`),
  KEY `FK_nyb9mu6dia427bvds8krnpkye` (`sensor_id`),
  CONSTRAINT `FK_hc2o8oowhrfm82apobtyoxumm` FOREIGN KEY (`mediciones_id`) REFERENCES `medicion` (`id`),
  CONSTRAINT `FK_nyb9mu6dia427bvds8krnpkye` FOREIGN KEY (`sensor_id`) REFERENCES `sensor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sensor_medicion`
--

LOCK TABLES `sensor_medicion` WRITE;
/*!40000 ALTER TABLE `sensor_medicion` DISABLE KEYS */;
/*!40000 ALTER TABLE `sensor_medicion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sensor_regla`
--

DROP TABLE IF EXISTS `sensor_regla`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sensor_regla` (
  `sensor_id` int(11) NOT NULL,
  `observadores_id` int(11) NOT NULL,
  UNIQUE KEY `UK_qk96v570lctybfvp7ncfuiyca` (`observadores_id`),
  KEY `FK_t8kkbnd9q81y57hclqpp1l885` (`sensor_id`),
  CONSTRAINT `FK_qk96v570lctybfvp7ncfuiyca` FOREIGN KEY (`observadores_id`) REFERENCES `regla` (`id`),
  CONSTRAINT `FK_t8kkbnd9q81y57hclqpp1l885` FOREIGN KEY (`sensor_id`) REFERENCES `sensor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sensor_regla`
--

LOCK TABLES `sensor_regla` WRITE;
/*!40000 ALTER TABLE `sensor_regla` DISABLE KEYS */;
/*!40000 ALTER TABLE `sensor_regla` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transformador`
--

DROP TABLE IF EXISTS `transformador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `transformador` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ubicable_id` int(11) DEFAULT NULL,
  `zonaAsignada_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g3comdux9m277mdn7o84qnk2n` (`ubicable_id`),
  KEY `FK_sn8tnv2yu1qopyx917vasqfh0` (`zonaAsignada_id`),
  CONSTRAINT `FK_g3comdux9m277mdn7o84qnk2n` FOREIGN KEY (`ubicable_id`) REFERENCES `ubicable` (`id`),
  CONSTRAINT `FK_sn8tnv2yu1qopyx917vasqfh0` FOREIGN KEY (`zonaAsignada_id`) REFERENCES `zona_geografica` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transformador`
--

LOCK TABLES `transformador` WRITE;
/*!40000 ALTER TABLE `transformador` DISABLE KEYS */;
INSERT INTO `transformador` VALUES (1,56,1),(2,57,2),(3,58,3),(4,59,4),(5,60,5),(6,61,6),(7,62,7),(8,63,8),(9,64,9),(10,65,10),(11,66,11),(12,67,12),(13,68,13),(14,69,14),(15,70,15),(16,71,16),(17,72,17),(18,73,18),(19,74,19),(20,75,20),(21,76,21),(22,77,22),(23,78,23),(24,79,24),(25,80,25),(26,81,26),(27,82,27),(28,83,28),(29,84,29),(30,85,30),(31,86,31),(32,87,32),(33,88,33),(34,89,34),(35,90,35),(36,91,36),(37,92,37),(38,93,38),(39,94,39),(40,95,40),(41,96,41),(42,97,42),(43,98,43),(44,99,44),(45,100,45),(46,101,46),(47,102,47),(48,103,48),(68,4,45),(69,5,45),(70,6,42),(71,7,5);
/*!40000 ALTER TABLE `transformador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ubicable`
--

DROP TABLE IF EXISTS `ubicable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ubicable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ubicable`
--

LOCK TABLES `ubicable` WRITE;
/*!40000 ALTER TABLE `ubicable` DISABLE KEYS */;
INSERT INTO `ubicable` VALUES (1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11),(12),(13),(14),(15),(16),(17),(18),(19),(20),(21),(22),(23),(24),(25),(26),(27),(28),(29),(30),(31),(32),(33),(34),(35),(36),(37),(38),(39),(40),(41),(42),(43),(44),(45),(46),(47),(48),(49),(50),(51),(52),(53),(54),(55),(56),(57),(58),(59),(60),(61),(62),(63),(64),(65),(66),(67),(68),(69),(70),(71),(72),(73),(74),(75),(76),(77),(78),(79),(80),(81),(82),(83),(84),(85),(86),(87),(88),(89),(90),(91),(92),(93),(94),(95),(96),(97),(98),(99),(100),(101),(102),(103),(104),(105),(107),(108),(109),(111),(112),(113);
/*!40000 ALTER TABLE `ubicable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usuario` (
  `tipo_usuario` varchar(31) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) DEFAULT NULL,
  `domicilio` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `accionadoAutomatico` bit(1) DEFAULT NULL,
  `fechaAltaServicio` varchar(255) DEFAULT NULL,
  `numeroDocumento` varchar(255) DEFAULT NULL,
  `puntaje` int(11) DEFAULT NULL,
  `telefonoContacto` varchar(255) DEFAULT NULL,
  `tipoDocumento` varchar(255) DEFAULT NULL,
  `fechaAltaSistema` datetime DEFAULT NULL,
  `ubicable_id` int(11) DEFAULT NULL,
  `categoria_id` int(11) DEFAULT NULL,
  `transformador_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_863n1y3x0jalatoir4325ehal` (`username`),
  KEY `FK_koecksyr71737jsu98xmbr77l` (`ubicable_id`),
  KEY `FK_b5ggytil0mtin0limbvee2que` (`categoria_id`),
  KEY `FK_j6j8fkwccgyi15qf5t3m4eslj` (`transformador_id`),
  CONSTRAINT `FK_b5ggytil0mtin0limbvee2que` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`),
  CONSTRAINT `FK_j6j8fkwccgyi15qf5t3m4eslj` FOREIGN KEY (`transformador_id`) REFERENCES `transformador` (`id`),
  CONSTRAINT `FK_koecksyr71737jsu98xmbr77l` FOREIGN KEY (`ubicable_id`) REFERENCES `ubicable` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('administrador',2,'Escobar','Triunvirato 3001, Villa OrtÃºzar, Buenos Aires','Ezequiel','123456','eescobar',_binary '\0','2018-11-30 22:00',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('administrador',3,'Rojas','Bauness 303, Paternal, Buenos Aires','Miguel','123456','mrojas',_binary '\0','2018-11-30 22:00',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('cliente',4,'Medina','Av. Callao 1401, Recoleta, Buenos Aires','Pablo','123456','pmedina',_binary '\0','2018-11-30 22:00',NULL,0,NULL,NULL,NULL,1,1,1),('cliente',5,'Volpe','Av. Belgrano 750, Monserrat, Buenos Aires','Romina','123456','rvolpe',_binary '\0','2018-11-30 22:00',NULL,0,NULL,NULL,NULL,2,1,3),('cliente',6,'Monte','Bulnes 95, Almagro, Buenos Aires','Diana','123456','dmonte',_binary '\0','2018-11-30 22:00',NULL,0,NULL,NULL,NULL,3,1,4),('administrador',26,'Zanetti','Huergo 201, Buenos Aires','Paula','123456','pzanetti',_binary '\0','2018-11-30 22:00',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('administrador',27,'Garcia',NULL,'Roberto','admin','admin',_binary '\0','2018-11-30 22:00',NULL,0,NULL,NULL,'2018-11-30 22:44:25',104,NULL,NULL),('cliente',28,'Perez',NULL,'Mario','test','test',_binary '\0','2018-11-30 22:00',NULL,0,'44442222','DNI',NULL,105,4,NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zona_geografica`
--

DROP TABLE IF EXISTS `zona_geografica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `zona_geografica` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ubicable_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_co0iugobnunb6svrjvi1gtl61` (`ubicable_id`),
  CONSTRAINT `FK_co0iugobnunb6svrjvi1gtl61` FOREIGN KEY (`ubicable_id`) REFERENCES `ubicable` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zona_geografica`
--

LOCK TABLES `zona_geografica` WRITE;
/*!40000 ALTER TABLE `zona_geografica` DISABLE KEYS */;
INSERT INTO `zona_geografica` VALUES (1,8),(2,9),(3,10),(4,11),(5,12),(6,13),(7,14),(8,15),(9,16),(10,17),(11,18),(12,19),(13,20),(14,21),(15,22),(16,23),(17,24),(18,25),(19,26),(20,27),(21,28),(22,29),(23,30),(24,31),(25,32),(26,33),(27,34),(28,35),(29,36),(30,37),(31,38),(32,39),(33,40),(34,41),(35,42),(36,43),(37,44),(38,45),(39,46),(40,47),(41,48),(42,49),(43,50),(44,51),(45,52),(46,53),(47,54),(48,55);
/*!40000 ALTER TABLE `zona_geografica` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-01 14:41:10
