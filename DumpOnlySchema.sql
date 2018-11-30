CREATE DATABASE  IF NOT EXISTS `equipo_3` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `equipo_3`;
-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: localhost    Database: equipo_3
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.17.10.1

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
-- Table structure for table `ConsumoHogar`
--

DROP TABLE IF EXISTS `ConsumoHogar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ConsumoHogar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) DEFAULT NULL,
  `consumo_estandar` double DEFAULT NULL,
  `consumo_inteligentes` double DEFAULT NULL,
  `consumo_total` float DEFAULT NULL,
  `domicilio` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `DispositivoEstandar`
--

DROP TABLE IF EXISTS `DispositivoEstandar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DispositivoEstandar` (
  `horasPorDia` float DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_tno480su3qgkqmcrg23qhqe4` FOREIGN KEY (`id`) REFERENCES `dispositivo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `DispositivoInteligente`
--

DROP TABLE IF EXISTS `DispositivoInteligente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
-- Table structure for table `actuadorEnum`
--

DROP TABLE IF EXISTS `actuadorEnum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actuadorEnum` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `valorEnum` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  CONSTRAINT `FK_o59x7e6nfir3fax0xn7qmvd8j` FOREIGN KEY (`regla_id`) REFERENCES `regla` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `consumo`
--

DROP TABLE IF EXISTS `consumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consumo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `consumo` float DEFAULT NULL,
  `fechaFin` varchar(255) DEFAULT NULL,
  `fechaInicio` varchar(255) DEFAULT NULL,
  `modo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jtf2jsq864wjxuugnhtub9jf3` (`modo_id`),
  CONSTRAINT `FK_jtf2jsq864wjxuugnhtub9jf3` FOREIGN KEY (`modo_id`) REFERENCES `modo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `fecha_alta` varchar(255) DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `dispositivoDetalle_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6m7rbebu37l1f66s4y5y8ccsq` (`cliente_id`),
  KEY `FK_1p5xfinukhit784vtlb7ji7rf` (`dispositivoDetalle_id`),
  CONSTRAINT `FK_1p5xfinukhit784vtlb7ji7rf` FOREIGN KEY (`dispositivoDetalle_id`) REFERENCES `dispositivoDetalle` (`id`),
  CONSTRAINT `FK_6m7rbebu37l1f66s4y5y8ccsq` FOREIGN KEY (`cliente_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dispositivoDetalle`
--

DROP TABLE IF EXISTS `dispositivoDetalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dispositivosAdaptadorLog`
--

DROP TABLE IF EXISTS `dispositivosAdaptadorLog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `modo`
--

DROP TABLE IF EXISTS `modo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modo` (
  `tipo` varchar(31) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fechaHoraFin` varchar(255) DEFAULT NULL,
  `fechaHoraInicio` varchar(255) DEFAULT NULL,
  `dispositivo_inteligente_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1xv17lnytsneg9c3l2mx9cfmm` (`dispositivo_inteligente_id`),
  CONSTRAINT `FK_1xv17lnytsneg9c3l2mx9cfmm` FOREIGN KEY (`dispositivo_inteligente_id`) REFERENCES `DispositivoInteligente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `observador`
--

DROP TABLE IF EXISTS `observador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `observador` (
  `tipo` varchar(31) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `regla`
--

DROP TABLE IF EXISTS `regla`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
-- Table structure for table `regla_DispositivoInteligente`
--

DROP TABLE IF EXISTS `regla_DispositivoInteligente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
-- Table structure for table `regla_actuadorEnum`
--

DROP TABLE IF EXISTS `regla_actuadorEnum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
-- Table structure for table `sensor_medicion`
--

DROP TABLE IF EXISTS `sensor_medicion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
-- Table structure for table `sensor_regla`
--

DROP TABLE IF EXISTS `sensor_regla`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
-- Table structure for table `transformador`
--

DROP TABLE IF EXISTS `transformador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transformador` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ubicable_id` int(11) DEFAULT NULL,
  `zonaAsignada_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g3comdux9m277mdn7o84qnk2n` (`ubicable_id`),
  KEY `FK_sn8tnv2yu1qopyx917vasqfh0` (`zonaAsignada_id`),
  CONSTRAINT `FK_g3comdux9m277mdn7o84qnk2n` FOREIGN KEY (`ubicable_id`) REFERENCES `ubicable` (`id`),
  CONSTRAINT `FK_sn8tnv2yu1qopyx917vasqfh0` FOREIGN KEY (`zonaAsignada_id`) REFERENCES `zona_geografica` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ubicable`
--

DROP TABLE IF EXISTS `ubicable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ubicable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  `identificadorSistema` int(11) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `zona_geografica`
--

DROP TABLE IF EXISTS `zona_geografica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zona_geografica` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ubicable_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_co0iugobnunb6svrjvi1gtl61` (`ubicable_id`),
  CONSTRAINT `FK_co0iugobnunb6svrjvi1gtl61` FOREIGN KEY (`ubicable_id`) REFERENCES `ubicable` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-30 17:21:39
