CREATE DATABASE  IF NOT EXISTS `equipo_3` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `equipo_3`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: equipo_3
-- ------------------------------------------------------
-- Server version	5.5.61

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
-- Table structure for table `actuadorenum`
--

DROP TABLE IF EXISTS `actuadorenum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actuadorenum` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actuadorenum`
--

LOCK TABLES `actuadorenum` WRITE;
/*!40000 ALTER TABLE `actuadorenum` DISABLE KEYS */;
/*!40000 ALTER TABLE `actuadorenum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actuadorenum_observador`
--

DROP TABLE IF EXISTS `actuadorenum_observador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actuadorenum_observador` (
  `actuadorEnum_id` int(11) NOT NULL,
  `reglas_id` int(11) NOT NULL,
  KEY `FK_3qdm645p8cc0telx5yjsajh4n` (`reglas_id`),
  KEY `FK_knj1ci9c9c0rvpupc389sccw1` (`actuadorEnum_id`),
  CONSTRAINT `FK_knj1ci9c9c0rvpupc389sccw1` FOREIGN KEY (`actuadorEnum_id`) REFERENCES `actuadorenum` (`id`),
  CONSTRAINT `FK_3qdm645p8cc0telx5yjsajh4n` FOREIGN KEY (`reglas_id`) REFERENCES `observador` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actuadorenum_observador`
--

LOCK TABLES `actuadorenum_observador` WRITE;
/*!40000 ALTER TABLE `actuadorenum_observador` DISABLE KEYS */;
/*!40000 ALTER TABLE `actuadorenum_observador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrador` (
  `fechaAltaSistema` datetime DEFAULT NULL,
  `identificadorSistema` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_lhjsinmexq43y65t1ffmjya1d` FOREIGN KEY (`id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `concepto` varchar(255) DEFAULT NULL,
  `consumoMaximo` float DEFAULT NULL,
  `consumoMinimo` float DEFAULT NULL,
  `costoNormal` float DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `unidad` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `accionadoAutomatico` bit(1) DEFAULT NULL,
  `fechaAltaServicio` datetime DEFAULT NULL,
  `numeroDocumento` varchar(255) DEFAULT NULL,
  `puntaje` int(11) DEFAULT NULL,
  `telefonoContacto` varchar(255) DEFAULT NULL,
  `tipoDocumento` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `categoria_id` int(11) DEFAULT NULL,
  `transformador_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_n11d8dadnvffw0aqbj84hdjfh` (`categoria_id`),
  KEY `FK_34o8c7q59jipgc3b3ujmfh9f9` (`transformador_id`),
  CONSTRAINT `FK_mc1x7ep68i663unmmahsgq3uc` FOREIGN KEY (`id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FK_34o8c7q59jipgc3b3ujmfh9f9` FOREIGN KEY (`transformador_id`) REFERENCES `transformador` (`id`),
  CONSTRAINT `FK_n11d8dadnvffw0aqbj84hdjfh` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `condicion`
--

DROP TABLE IF EXISTS `condicion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `condicion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comparador` varchar(255) DEFAULT NULL,
  `valorComparable` float DEFAULT NULL,
  `regla_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_o59x7e6nfir3fax0xn7qmvd8j` (`regla_id`),
  CONSTRAINT `FK_o59x7e6nfir3fax0xn7qmvd8j` FOREIGN KEY (`regla_id`) REFERENCES `observador` (`id`)
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consumo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `consumo` float DEFAULT NULL,
  `fechaFin` tinyblob,
  `fechaInicio` tinyblob,
  `modo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jtf2jsq864wjxuugnhtub9jf3` (`modo_id`),
  CONSTRAINT `FK_jtf2jsq864wjxuugnhtub9jf3` FOREIGN KEY (`modo_id`) REFERENCES `modo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumo`
--

LOCK TABLES `consumo` WRITE;
/*!40000 ALTER TABLE `consumo` DISABLE KEYS */;
/*!40000 ALTER TABLE `consumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coordenada`
--

DROP TABLE IF EXISTS `coordenada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coordenada` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `latitud` double DEFAULT NULL,
  `longitud` double DEFAULT NULL,
  `orden` int(11) DEFAULT NULL,
  `ubicable_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qdibx011bnc9lam0qi2ybfx6t` (`ubicable_id`),
  CONSTRAINT `FK_qdibx011bnc9lam0qi2ybfx6t` FOREIGN KEY (`ubicable_id`) REFERENCES `ubicable` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coordenada`
--

LOCK TABLES `coordenada` WRITE;
/*!40000 ALTER TABLE `coordenada` DISABLE KEYS */;
/*!40000 ALTER TABLE `coordenada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dispositivo`
--

DROP TABLE IF EXISTS `dispositivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dispositivo` (
  `tipo` varchar(31) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activado` bit(1) DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `dispositivoDetalle_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6m7rbebu37l1f66s4y5y8ccsq` (`cliente_id`),
  KEY `FK_1p5xfinukhit784vtlb7ji7rf` (`dispositivoDetalle_id`),
  CONSTRAINT `FK_1p5xfinukhit784vtlb7ji7rf` FOREIGN KEY (`dispositivoDetalle_id`) REFERENCES `dispositivodetalle` (`id`),
  CONSTRAINT `FK_6m7rbebu37l1f66s4y5y8ccsq` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dispositivo`
--

LOCK TABLES `dispositivo` WRITE;
/*!40000 ALTER TABLE `dispositivo` DISABLE KEYS */;
/*!40000 ALTER TABLE `dispositivo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dispositivodetalle`
--

DROP TABLE IF EXISTS `dispositivodetalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dispositivodetalle` (
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dispositivodetalle`
--

LOCK TABLES `dispositivodetalle` WRITE;
/*!40000 ALTER TABLE `dispositivodetalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `dispositivodetalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dispositivoestandar`
--

DROP TABLE IF EXISTS `dispositivoestandar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dispositivoestandar` (
  `horasPorDia` float DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_tno480su3qgkqmcrg23qhqe4` FOREIGN KEY (`id`) REFERENCES `dispositivo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dispositivoestandar`
--

LOCK TABLES `dispositivoestandar` WRITE;
/*!40000 ALTER TABLE `dispositivoestandar` DISABLE KEYS */;
/*!40000 ALTER TABLE `dispositivoestandar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dispositivointeligente`
--

DROP TABLE IF EXISTS `dispositivointeligente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dispositivointeligente` (
  `id` int(11) NOT NULL,
  `actuador_id` int(11) DEFAULT NULL,
  `dispositivo_id` int(11) DEFAULT NULL,
  `estandar_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4aeig44n3jdgyq4e8p19k0cm7` (`actuador_id`),
  KEY `FK_lju2qt6okfaasdgf5fgf94i3` (`dispositivo_id`),
  KEY `FK_3c8x6385o99spq6bv8fgs34yl` (`estandar_id`),
  CONSTRAINT `FK_4iewo4n0slfo3o2jqt2u9xp96` FOREIGN KEY (`id`) REFERENCES `dispositivo` (`id`),
  CONSTRAINT `FK_3c8x6385o99spq6bv8fgs34yl` FOREIGN KEY (`estandar_id`) REFERENCES `dispositivoestandar` (`id`),
  CONSTRAINT `FK_4aeig44n3jdgyq4e8p19k0cm7` FOREIGN KEY (`actuador_id`) REFERENCES `actuadorenum` (`id`),
  CONSTRAINT `FK_lju2qt6okfaasdgf5fgf94i3` FOREIGN KEY (`dispositivo_id`) REFERENCES `dispositivo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dispositivointeligente`
--

LOCK TABLES `dispositivointeligente` WRITE;
/*!40000 ALTER TABLE `dispositivointeligente` DISABLE KEYS */;
/*!40000 ALTER TABLE `dispositivointeligente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dispositivointeligente_observador`
--

DROP TABLE IF EXISTS `dispositivointeligente_observador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dispositivointeligente_observador` (
  `DispositivoInteligente_id` int(11) NOT NULL,
  `reglas_id` int(11) NOT NULL,
  KEY `FK_nc7p6jxyhg431e23vl8hxibde` (`reglas_id`),
  KEY `FK_kflq0h7pacybgj0839y6ptbbe` (`DispositivoInteligente_id`),
  CONSTRAINT `FK_kflq0h7pacybgj0839y6ptbbe` FOREIGN KEY (`DispositivoInteligente_id`) REFERENCES `dispositivointeligente` (`id`),
  CONSTRAINT `FK_nc7p6jxyhg431e23vl8hxibde` FOREIGN KEY (`reglas_id`) REFERENCES `observador` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dispositivointeligente_observador`
--

LOCK TABLES `dispositivointeligente_observador` WRITE;
/*!40000 ALTER TABLE `dispositivointeligente_observador` DISABLE KEYS */;
/*!40000 ALTER TABLE `dispositivointeligente_observador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dispositivosadaptadorlog`
--

DROP TABLE IF EXISTS `dispositivosadaptadorlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dispositivosadaptadorlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fechaFin` tinyblob,
  `fechaInicio` tinyblob,
  `adaptadorInteligente_id` int(11) DEFAULT NULL,
  `estandar_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_q40rscow41g81o3e3xud1kitd` (`adaptadorInteligente_id`),
  KEY `FK_862sjge21ylulknplk0ov66tn` (`estandar_id`),
  CONSTRAINT `FK_862sjge21ylulknplk0ov66tn` FOREIGN KEY (`estandar_id`) REFERENCES `dispositivoestandar` (`id`),
  CONSTRAINT `FK_q40rscow41g81o3e3xud1kitd` FOREIGN KEY (`adaptadorInteligente_id`) REFERENCES `dispositivointeligente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dispositivosadaptadorlog`
--

LOCK TABLES `dispositivosadaptadorlog` WRITE;
/*!40000 ALTER TABLE `dispositivosadaptadorlog` DISABLE KEYS */;
/*!40000 ALTER TABLE `dispositivosadaptadorlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicion`
--

DROP TABLE IF EXISTS `medicion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fechaFin` tinyblob,
  `fechaInicio` tinyblob,
  `magnitud` int(11) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_ms0d2ntmwplktygyugbildcve` FOREIGN KEY (`id`) REFERENCES `sensor` (`id`)
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modo` (
  `tipo` varchar(31) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fechaHoraFin` tinyblob,
  `fechaHoraInicio` tinyblob,
  `inteligente_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cqmg2axht5qgqasc7gco5h14e` (`inteligente_id`),
  CONSTRAINT `FK_cqmg2axht5qgqasc7gco5h14e` FOREIGN KEY (`inteligente_id`) REFERENCES `dispositivointeligente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modo`
--

LOCK TABLES `modo` WRITE;
/*!40000 ALTER TABLE `modo` DISABLE KEYS */;
/*!40000 ALTER TABLE `modo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `observador`
--

DROP TABLE IF EXISTS `observador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `observador` (
  `tipo` varchar(31) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `regla_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_h52wv59y1i410ijes24jiqp9o` (`regla_id`),
  CONSTRAINT `FK_egokj5340pk2lniyfhd19o7fu` FOREIGN KEY (`id`) REFERENCES `sensor` (`id`),
  CONSTRAINT `FK_h52wv59y1i410ijes24jiqp9o` FOREIGN KEY (`regla_id`) REFERENCES `observador` (`id`)
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
-- Table structure for table `observador_actuadorenum`
--

DROP TABLE IF EXISTS `observador_actuadorenum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `observador_actuadorenum` (
  `observador_id` int(11) NOT NULL,
  `actuadores_enums_id` int(11) NOT NULL,
  KEY `FK_sejicasbutijy6vr20ivfvv3t` (`actuadores_enums_id`),
  KEY `FK_8kmsjy56lvm9laqfq386mob31` (`observador_id`),
  CONSTRAINT `FK_8kmsjy56lvm9laqfq386mob31` FOREIGN KEY (`observador_id`) REFERENCES `observador` (`id`),
  CONSTRAINT `FK_sejicasbutijy6vr20ivfvv3t` FOREIGN KEY (`actuadores_enums_id`) REFERENCES `actuadorenum` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `observador_actuadorenum`
--

LOCK TABLES `observador_actuadorenum` WRITE;
/*!40000 ALTER TABLE `observador_actuadorenum` DISABLE KEYS */;
/*!40000 ALTER TABLE `observador_actuadorenum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `observador_dispositivointeligente`
--

DROP TABLE IF EXISTS `observador_dispositivointeligente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `observador_dispositivointeligente` (
  `observador_id` int(11) NOT NULL,
  `dispositivos_id` int(11) NOT NULL,
  KEY `FK_7okgafthp8458n2c98w1ap5ne` (`dispositivos_id`),
  KEY `FK_blr3l7547dmv42p4j2w0g6tvu` (`observador_id`),
  CONSTRAINT `FK_blr3l7547dmv42p4j2w0g6tvu` FOREIGN KEY (`observador_id`) REFERENCES `observador` (`id`),
  CONSTRAINT `FK_7okgafthp8458n2c98w1ap5ne` FOREIGN KEY (`dispositivos_id`) REFERENCES `dispositivointeligente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `observador_dispositivointeligente`
--

LOCK TABLES `observador_dispositivointeligente` WRITE;
/*!40000 ALTER TABLE `observador_dispositivointeligente` DISABLE KEYS */;
/*!40000 ALTER TABLE `observador_dispositivointeligente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sensor`
--

DROP TABLE IF EXISTS `sensor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
-- Table structure for table `transformador`
--

DROP TABLE IF EXISTS `transformador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transformador` (
  `id` int(11) NOT NULL,
  `ubicable_id` int(11) DEFAULT NULL,
  `zona_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g3comdux9m277mdn7o84qnk2n` (`ubicable_id`),
  KEY `FK_4eqbelrsm9wg0a9enalbqettt` (`zona_id`),
  CONSTRAINT `FK_fbvfnxwrucvsy63vsn62um66w` FOREIGN KEY (`id`) REFERENCES `ubicable` (`id`),
  CONSTRAINT `FK_4eqbelrsm9wg0a9enalbqettt` FOREIGN KEY (`zona_id`) REFERENCES `zona_geografica` (`id`),
  CONSTRAINT `FK_g3comdux9m277mdn7o84qnk2n` FOREIGN KEY (`ubicable_id`) REFERENCES `ubicable` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transformador`
--

LOCK TABLES `transformador` WRITE;
/*!40000 ALTER TABLE `transformador` DISABLE KEYS */;
/*!40000 ALTER TABLE `transformador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ubicable`
--

DROP TABLE IF EXISTS `ubicable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ubicable` (
  `tipo` varchar(31) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ubicable`
--

LOCK TABLES `ubicable` WRITE;
/*!40000 ALTER TABLE `ubicable` DISABLE KEYS */;
/*!40000 ALTER TABLE `ubicable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `apellido` varchar(255) DEFAULT NULL,
  `domicilio` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_ky4fsf2p8cggggirm1b0cajes` FOREIGN KEY (`id`) REFERENCES `ubicable` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zona_geografica`
--

DROP TABLE IF EXISTS `zona_geografica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zona_geografica` (
  `limitesZona` tinyblob,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_f9soxabdsrds7mh1oh2glxlqc` FOREIGN KEY (`id`) REFERENCES `ubicable` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zona_geografica`
--

LOCK TABLES `zona_geografica` WRITE;
/*!40000 ALTER TABLE `zona_geografica` DISABLE KEYS */;
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

-- Dump completed on 2018-09-25  3:59:49



/* dispositivos detalle. */
INSERT INTO  equipo_3.dispositivodetalle ( nombre , descripcion , hsMensualMinimo , hsMensualMaximo ,  consumoKwHora , bajoConsumo , esencial , inteligente) 
VALUES ("Aire Acondicionado" , "De 3500 frigorias"  , 90.0  , 360.0 , 1.613 , false, false , true) ;


INSERT INTO  equipo_3.dispositivodetalle ( nombre , descripcion , hsMensualMinimo , hsMensualMaximo ,  consumoKwHora , bajoConsumo , esencial , inteligente)  
VALUES ("Aire Acondicionado" , "De 2200 frigorias"  , 90.0  , 360.0 , 1.013 , true, false , true) ;


INSERT INTO  equipo_3.dispositivodetalle ( nombre , descripcion , hsMensualMinimo , hsMensualMaximo ,  consumoKwHora , bajoConsumo , esencial , inteligente)  
VALUES ("Televisor" , "Color de tubo fluorescente de 21"  , 90.0  , 360.0 , 0.075 , false ,false , false) ;

INSERT INTO  equipo_3.dispositivodetalle ( nombre , descripcion , hsMensualMinimo , hsMensualMaximo ,  consumoKwHora , bajoConsumo , esencial , inteligente)  
VALUES ("Televisor" , "Color de tubo fluorescente de 29 a 34"  , 90.0  , 360.0 , 0.175 , false ,false , false) ;

INSERT INTO  equipo_3.dispositivodetalle ( nombre , descripcion , hsMensualMinimo , hsMensualMaximo ,  consumoKwHora , bajoConsumo , esencial , inteligente)  
VALUES ("Televisor" , "LCD de 40"  , 90.0  , 360.0 , 0.18 , false ,false , false) ;

INSERT INTO  equipo_3.dispositivodetalle ( nombre , descripcion , hsMensualMinimo , hsMensualMaximo ,  consumoKwHora , bajoConsumo , esencial , inteligente)  
VALUES ("Televisor" , "LED 24"  , 90.0  , 360.0 , 0.04 , true , false , true) ;

INSERT INTO  equipo_3.dispositivodetalle ( nombre , descripcion , hsMensualMinimo , hsMensualMaximo ,  consumoKwHora , bajoConsumo , esencial , inteligente)  
VALUES ("Televisor" , "LED 32"  , 90.0  , 360.0 , 0.055 ,  true ,false , true) ; 

INSERT INTO  equipo_3.dispositivodetalle ( nombre , descripcion , hsMensualMinimo , hsMensualMaximo ,  consumoKwHora , bajoConsumo , esencial , inteligente)  
VALUES ("Televisor" , "LED 40"  , 90.0  , 360.0 , 0.08 ,  true ,true , true) ;

INSERT INTO  equipo_3.dispositivodetalle ( nombre , descripcion , hsMensualMinimo , hsMensualMaximo ,  consumoKwHora , bajoConsumo , esencial , inteligente)  
VALUES ("Heladera" , "Con freezer"  , NULL  , NULL , 0.09 ,  true ,true , true) ;

INSERT INTO  equipo_3.dispositivodetalle ( nombre , descripcion , hsMensualMinimo , hsMensualMaximo ,  consumoKwHora , bajoConsumo , esencial , inteligente)  
VALUES ("Heladera" , "Sin freezer"  ,NULL  ,NULL , 0.075 ,  true ,true , true) ;

INSERT INTO  equipo_3.dispositivodetalle ( nombre , descripcion , hsMensualMinimo , hsMensualMaximo ,  consumoKwHora , bajoConsumo , esencial , inteligente)  
VALUES ("Lavarropas" , "Automatico de 5 kg con calentamiento de agua"  , 6.0  , 30.0 , 0.875 ,  false ,false , true) ;

INSERT INTO  equipo_3.dispositivodetalle ( nombre , descripcion , hsMensualMinimo , hsMensualMaximo ,  consumoKwHora , bajoConsumo , esencial , inteligente)  
VALUES ("Lavarropas" , "Automatico de 5 kg"  , 6.0  , 30.0 , 0.175 ,  true ,false , true) ;

INSERT INTO  equipo_3.dispositivodetalle ( nombre , descripcion , hsMensualMinimo , hsMensualMaximo ,  consumoKwHora , bajoConsumo , esencial , inteligente)  
VALUES ("Lavarropas" , "Semi-automatico de 5 kg"  , 6.0  , 30.0 , 0.1275 ,  true ,false , false) ;

INSERT INTO  equipo_3.dispositivodetalle ( nombre , descripcion , hsMensualMinimo , hsMensualMaximo ,  consumoKwHora , bajoConsumo , esencial , inteligente)  
VALUES ("Ventilador" , "De pie"  , 120.0  , 360.0 , 0.09 , true , false , false) ;

INSERT INTO  equipo_3.dispositivodetalle ( nombre , descripcion , hsMensualMinimo , hsMensualMaximo ,  consumoKwHora , bajoConsumo , esencial , inteligente)  
VALUES ("Ventilador" , "De techo"  , 120.0  , 360.0 , 0.06 , true , false , true) ;

INSERT INTO  equipo_3.dispositivodetalle ( nombre , descripcion , hsMensualMinimo , hsMensualMaximo ,  consumoKwHora , bajoConsumo , esencial , inteligente)  
VALUES ("Lampara" , "Halogenas de 40 W"  , 90.0  , 360.0 , 0.04 , false , false , true) ;

INSERT INTO  equipo_3.dispositivodetalle ( nombre , descripcion , hsMensualMinimo , hsMensualMaximo ,  consumoKwHora , bajoConsumo , esencial , inteligente)  
VALUES ("Lampara" , "Halogenas de 60 W"  , 90.0  , 360.0 , 0.06 , false , false , true) ;

INSERT INTO  equipo_3.dispositivodetalle ( nombre , descripcion , hsMensualMinimo , hsMensualMaximo ,  consumoKwHora , bajoConsumo , esencial , inteligente)  
VALUES ("Lampara" , "Halogenas de 100 W"  , 90.0  , 360.0 , 0.015 , false , false , true) ;

INSERT INTO  equipo_3.dispositivodetalle ( nombre , descripcion , hsMensualMinimo , hsMensualMaximo ,  consumoKwHora , bajoConsumo , esencial , inteligente)  
VALUES ("Lampara" , "De 11 W"  , 90.0  , 360.0 , 0.011 , true , false , true) ;

INSERT INTO  equipo_3.dispositivodetalle ( nombre , descripcion , hsMensualMinimo , hsMensualMaximo ,  consumoKwHora , bajoConsumo , esencial , inteligente)  
VALUES ("Lampara" , "De 15 W"  , 90.0  , 360.0 , 0.015 , true , false , true) ;

INSERT INTO  equipo_3.dispositivodetalle ( nombre , descripcion , hsMensualMinimo , hsMensualMaximo ,  consumoKwHora , bajoConsumo , esencial , inteligente)  
VALUES ("Lampara" , "De 20 W"  , 90.0  , 360.0 , 0.02 , true , false , true) ;

INSERT INTO  equipo_3.dispositivodetalle ( nombre , descripcion , hsMensualMinimo , hsMensualMaximo ,  consumoKwHora , bajoConsumo , esencial , inteligente)  
VALUES ("PC" , "De escritorio"  , 90.0  , 360.0 , 0.4 , true , false , true) ;


