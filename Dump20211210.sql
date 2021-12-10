-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: webbanhang
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `anh_slider`
--

DROP TABLE IF EXISTS `anh_slider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anh_slider` (
  `id` int NOT NULL AUTO_INCREMENT,
  `file` varchar(300) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `slider_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Anh_slider_slider1_idx` (`slider_id`),
  CONSTRAINT `fk_Anh_slider_slider1` FOREIGN KEY (`slider_id`) REFERENCES `slider` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anh_slider`
--

LOCK TABLES `anh_slider` WRITE;
/*!40000 ALTER TABLE `anh_slider` DISABLE KEYS */;
/*!40000 ALTER TABLE `anh_slider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'Tên loại sản phẩm',
  `code` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `total_product` int NOT NULL DEFAULT '0',
  `avartar` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT 'no-image.jpg',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Quần áo nam','FDS54',3,'https://res.cloudinary.com/loc120299/image/upload/v1636631619/cookies-shop/jw8ixzkc1izsntpt1gpl.png'),(2,'HKT','FDS54',1,'https://res.cloudinary.com/loc120299/image/upload/v1637668367/cookies-shop/pfno0x1qq9uoqcsh02iq.png');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Tên người bình luận',
  `content` text CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `Users_id` int NOT NULL,
  `Products_id` int NOT NULL,
  `avatar` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Comment_Users1_idx` (`Users_id`),
  KEY `fk_Comment_Products1_idx` (`Products_id`),
  CONSTRAINT `fk_Comment_Products1` FOREIGN KEY (`Products_id`) REFERENCES `products` (`id`),
  CONSTRAINT `fk_Comment_Users1` FOREIGN KEY (`Users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'HaCuong','San pham không đẹp',2,1,'https://res.cloudinary.com/devatchannel/image/upload/v1602752402/avatar/avatar_cugq40.png?fbclid=IwAR1WVZLl0dmt01nM1K4-Lvy30w1-p5XOS7qATZNA7udT-Heak0NA9MvQnys'),(2,'Nguyễn Thanh Danh','Sản phẩm tốt',1,2,'https://res.cloudinary.com/loc120299/image/upload/v1637338114/cookies-shop/r23uyornivcrdbk9cduu.png');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `key_word`
--

DROP TABLE IF EXISTS `key_word`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `key_word` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `code` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `key_word`
--

LOCK TABLES `key_word` WRITE;
/*!40000 ALTER TABLE `key_word` DISABLE KEYS */;
/*!40000 ALTER TABLE `key_word` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `key_word_product`
--

DROP TABLE IF EXISTS `key_word_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `key_word_product` (
  `idKey_word_product` int NOT NULL AUTO_INCREMENT,
  `Key_word_id` int NOT NULL,
  `Products_id` int NOT NULL,
  PRIMARY KEY (`idKey_word_product`),
  KEY `fk_Key_word_product_Key_word1_idx` (`Key_word_id`),
  KEY `fk_Key_word_product_Products1_idx` (`Products_id`),
  CONSTRAINT `fk_Key_word_product_Key_word1` FOREIGN KEY (`Key_word_id`) REFERENCES `key_word` (`id`),
  CONSTRAINT `fk_Key_word_product_Products1` FOREIGN KEY (`Products_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `key_word_product`
--

LOCK TABLES `key_word_product` WRITE;
/*!40000 ALTER TABLE `key_word_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `key_word_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `totalmoney` double NOT NULL DEFAULT '0',
  `quantity` int NOT NULL DEFAULT '1',
  `discount` double DEFAULT NULL,
  `avartar` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT 'no-image.jpg',
  `name` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT 'Tên sản phẩm',
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `Products_id` int NOT NULL,
  `Orders_id` int NOT NULL,
  `dateorder` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Order_Detail_Products1_idx` (`Products_id`),
  KEY `fk_Order_Detail_Orders1_idx` (`Orders_id`),
  CONSTRAINT `fk_Order_Detail_Orders1` FOREIGN KEY (`Orders_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `fk_Order_Detail_Products1` FOREIGN KEY (`Products_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (5,400,1,0,'https://res.cloudinary.com/loc120299/image/upload/v1636633314/cookies-shop/gp2s62rlsr3gkewgfsea.png','Quần áo đẹp',4,2,3,'2021-11-22 00:00:00'),(6,200,0,0,'https://res.cloudinary.com/loc120299/image/upload/v1636631662/cookies-shop/lvygkl3l9zdcmbv4xlkr.png','Quần áo phong cách việt nam xx',0,2,4,'2021-11-22 00:00:00'),(7,200,0,140,'https://res.cloudinary.com/loc120299/image/upload/v1636631662/cookies-shop/lvygkl3l9zdcmbv4xlkr.png','Quần áo phong cách việt nam',0,1,5,'2021-10-22 00:00:00'),(8,200,0,160,'https://res.cloudinary.com/loc120299/image/upload/v1636631662/cookies-shop/lvygkl3l9zdcmbv4xlkr.png','Quần áo phong cách việt nam xx',0,2,5,'2021-11-22 00:00:00'),(9,200,1,140,'https://res.cloudinary.com/loc120299/image/upload/v1636631662/cookies-shop/lvygkl3l9zdcmbv4xlkr.png','Quần áo phong cách việt nam',0,1,6,'2021-11-22 00:00:00'),(10,200,1,160,'https://res.cloudinary.com/loc120299/image/upload/v1636631662/cookies-shop/lvygkl3l9zdcmbv4xlkr.png','Quần áo phong cách việt nam xx',0,2,6,'2021-10-22 00:00:00'),(11,200,0,120,'https://res.cloudinary.com/loc120299/image/upload/v1636631662/cookies-shop/lvygkl3l9zdcmbv4xlkr.png','Quần áo phong cách việt nam',0,1,7,'2021-10-22 00:00:00'),(12,200,0,160,'https://res.cloudinary.com/loc120299/image/upload/v1636631662/cookies-shop/lvygkl3l9zdcmbv4xlkr.png','Quần áo phong cách việt nam xx',0,2,7,'2021-11-22 00:00:00'),(13,200,1,0,'https://res.cloudinary.com/loc120299/image/upload/v1636631662/cookies-shop/lvygkl3l9zdcmbv4xlkr.png','Quần áo phong cách việt nam',0,1,8,'2021-11-22 00:00:00'),(14,400,2,0,'https://res.cloudinary.com/loc120299/image/upload/v1637337610/cookies-shop/ektwinycboetcuihfd08.png','Quần áo truyền thống',2,3,9,'2021-11-22 00:00:00'),(15,100,1,0,'https://res.cloudinary.com/loc120299/image/upload/v1636631662/cookies-shop/lvygkl3l9zdcmbv4xlkr.png','Quần áo xx ',0,1,10,'2021-09-22 00:00:00'),(16,100,1,60,'https://res.cloudinary.com/loc120299/image/upload/v1636631662/cookies-shop/lvygkl3l9zdcmbv4xlkr.png','Quần áo xx ',0,1,11,'2021-09-22 00:00:00'),(25,100,1,0,'https://res.cloudinary.com/loc120299/image/upload/v1636631662/cookies-shop/lvygkl3l9zdcmbv4xlkr.png','Quần áo xx ',0,1,15,'2021-11-22 00:00:00'),(35,100,1,0,'https://res.cloudinary.com/loc120299/image/upload/v1636631662/cookies-shop/lvygkl3l9zdcmbv4xlkr.png','Quần áo xx ',0,1,25,'2021-11-22 00:00:00'),(45,100,1,0,'https://res.cloudinary.com/loc120299/image/upload/v1636631662/cookies-shop/lvygkl3l9zdcmbv4xlkr.png','Quần áo xx ',0,1,35,'2021-11-22 00:00:00'),(55,100,1,0,'https://res.cloudinary.com/loc120299/image/upload/v1636631662/cookies-shop/lvygkl3l9zdcmbv4xlkr.png','Quần áo xx ',0,1,45,'2021-11-22 00:00:00'),(65,400,2,0,'https://res.cloudinary.com/loc120299/image/upload/v1638110008/cookies-shop/aejwaosgtgqgeexcsfx4.png','Quần áo trẻ trung',2,4,55,'2021-11-28 00:00:00'),(66,30,2,10,'https://res.cloudinary.com/loc120299/image/upload/v1636127581/cookies-shop/r7yuagr673hj5y7luh8h.png','Quan ao han quoc',0,1,56,'2021-12-07 00:00:00'),(67,15,1,20,'https://res.cloudinary.com/loc120299/image/upload/v1636127581/cookies-shop/r7yuagr673hj5y7luh8h.png','Quan ao han quoc',0,2,56,'2021-12-07 00:00:00'),(68,100,1,0,'https://res.cloudinary.com/loc120299/image/upload/v1636631662/cookies-shop/lvygkl3l9zdcmbv4xlkr.png','Quần áo phong hàn quốc ',0,2,57,'2021-12-07 00:00:00');
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `payments` tinyint NOT NULL DEFAULT '0',
  `deliveryaddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `transportfee` double DEFAULT NULL,
  `status` tinyint NOT NULL DEFAULT '0',
  `dateorder` datetime DEFAULT NULL,
  `namecustomer` varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `note` text CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `Users_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Orders_Users1_idx` (`Users_id`),
  CONSTRAINT `fk_Orders_Users1` FOREIGN KEY (`Users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,'long an',30000,1,'2021-11-11 00:00:00','Nguyễn Thanh Danh','cuonghq7.longan@gmail.com','0373846574','không',1),(2,1,'long an',30000,0,'2021-11-11 00:00:00','Nguyễn Thanh Danh','cuonghq7.longan@gmail.com','0373846574','không',1),(3,1,'Vinh long',30000,0,'2021-11-14 00:00:00','Nguyễn Thanh Danh','cuonghq7.longan@gmail.com','0373846574','không',1),(4,1,'long an',30000,0,'2021-11-17 00:00:00','Nguyễn Thanh Danh','cuonghq7.longan@gmail.com','0373846574','không',1),(5,1,'long an',30000,0,'2021-11-17 00:00:00','Nguyễn Thanh Danh','cuonghq7.longan@gmail.com','0373846574','không',1),(6,1,'long an',30000,0,'2021-11-17 00:00:00','Nguyễn Thanh Danh','cuonghq7.longan@gmail.com','0373846574','không',1),(7,1,'long an',30000,0,'2021-11-17 00:00:00','Nguyễn Thanh Danh','cuonghq7.longan@gmail.com','0373846574','không',1),(8,1,'long an',30000,0,'2021-11-17 00:00:00','Nguyễn Thanh Danh','cuonghq7.longan@gmail.com','0373846574','không',1),(9,1,'long an',30000,0,'2021-11-19 00:00:00','Nguyễn Thanh Danh','cuonghq7.longan@gmail.com','0373846574','không',1),(10,1,'long an',30000,0,'2021-11-20 00:00:00','Nguyễn Thanh Danh','cuonghq7.longan@gmail.com','0373846574','không',1),(11,1,'long an',30000,0,'2021-11-20 00:00:00','Nguyễn Thanh Danh','cuonghq7.longan@gmail.com','0373846574','không',1),(15,1,'bb',30000,0,'2021-11-22 00:00:00','vv','cuonghq8.longan@gmail.com','0378028840','ggfgfg',3),(25,1,'bb',30000,0,'2021-11-22 00:00:00','vv','cuonghq8.longan@gmail.com','0378028840','ggfgfg',3),(35,1,'bb',30000,0,'2021-11-22 00:00:00','vv','cuonghq8.longan@gmail.com','0378028840','ggfgfg',3),(45,1,'bb',30000,0,'2021-11-22 00:00:00','vv','cuonghq8.longan@gmail.com','0378028840','ggfgfg',3),(55,1,'tân trà, cần thơ',30000,0,'2021-11-28 00:00:00','Nguyên văn A','cuonghq7.longan@gmail.com','0374859746','không',1),(56,1,'Thanh hoa',30,0,'2021-12-07 00:00:00','Nguyen thanh Van','Cuong3@gmail.com','0384758358','Khong',1),(57,0,'long an',30000,0,'2021-12-07 00:00:00','Võ Minh Nhựt','cuonghq8.longan@gmail.com','0373948583','',3);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `picture_product`
--

DROP TABLE IF EXISTS `picture_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `picture_product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `file` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Products_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Picture_product_Products1_idx` (`Products_id`),
  CONSTRAINT `fk_Picture_product_Products1` FOREIGN KEY (`Products_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `picture_product`
--

LOCK TABLES `picture_product` WRITE;
/*!40000 ALTER TABLE `picture_product` DISABLE KEYS */;
INSERT INTO `picture_product` VALUES (1,'https://res.cloudinary.com/loc120299/image/upload/v1636633314/cookies-shop/f1ltgvgpxu8nlxwm673e.png',2),(2,'https://res.cloudinary.com/loc120299/image/upload/v1637337610/cookies-shop/igp9sjjon4mekl5rycex.png',3),(3,'https://res.cloudinary.com/loc120299/image/upload/v1637337610/cookies-shop/rdet5fdxmgzqxmouyf42.png',3),(4,'https://res.cloudinary.com/loc120299/image/upload/v1638110009/cookies-shop/ofymv11rvwfeoarwuvmu.png',4);
/*!40000 ALTER TABLE `picture_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'Tên sản phẩm',
  `code` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `sort_description` varchar(600) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'mô tả ngắn ngọn',
  `detail_description` text CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT 'mô tả chi tiết',
  `ban_nhanh` tinyint NOT NULL DEFAULT '0' COMMENT 'bán chạy',
  `price` double NOT NULL COMMENT 'giá bán',
  `featured` tinyint NOT NULL DEFAULT '0',
  `competitive_price` double DEFAULT '0',
  `avartar` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT 'no-image.jpg',
  `date_sale` datetime NOT NULL,
  `quantity` int NOT NULL,
  `promotion` int DEFAULT '0',
  `status` tinyint NOT NULL DEFAULT '0',
  `Supplier_id` int NOT NULL,
  `Categories_id` int NOT NULL,
  `Users_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Products_Supplier_idx` (`Supplier_id`),
  KEY `fk_Products_Categories1_idx` (`Categories_id`),
  KEY `fk_Products_Users1_idx` (`Users_id`),
  CONSTRAINT `fk_Products_Categories1` FOREIGN KEY (`Categories_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `fk_Products_Supplier` FOREIGN KEY (`Supplier_id`) REFERENCES `supplier` (`id`),
  CONSTRAINT `fk_Products_Users1` FOREIGN KEY (`Users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Quần áo xx ','FDSF34','Đồ hàng hiệu chất lượng ','Đồ được ưa chuộn trên thị trường việt',10,100,0,60,'https://res.cloudinary.com/loc120299/image/upload/v1636631662/cookies-shop/lvygkl3l9zdcmbv4xlkr.png','2021-11-11 00:00:00',3,40,1,1,1,2),(2,'Quần áo phong hàn quốc ','FDSF34','Đồ hàng hiệu chất lượng ','Đồ được ưa chuộn trên thị trường việt',4,100,0,80,'https://res.cloudinary.com/loc120299/image/upload/v1636631662/cookies-shop/lvygkl3l9zdcmbv4xlkr.png','2021-11-11 00:00:00',8,20,1,1,1,2),(3,'Quần áo truyền thống','DF6K3D','Quần áo mang đâm chất truyền thống','Quần áo mang đâm chất truyền thống đựa ưa chuộng trên thị trường việt nam',2,200,0,180,'https://res.cloudinary.com/loc120299/image/upload/v1638110101/cookies-shop/klyhw4l9zc75yiriwb2v.png','2021-11-19 00:00:00',7,10,1,1,1,3),(4,'Quần áo trẻ trung','FDS54','Quần áo phong phong cách giới trẻ','Quần áo phong phong cách giới trẻ được ưa chuộng nhất hiện nay',2,200,1,200,'https://res.cloudinary.com/loc120299/image/upload/v1638110008/cookies-shop/aejwaosgtgqgeexcsfx4.png','2021-11-28 00:00:00',6,0,1,1,2,3);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_user`
--

DROP TABLE IF EXISTS `role_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `Users_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Role_user_Users1_idx` (`Users_id`),
  CONSTRAINT `fk_Role_user_Users1` FOREIGN KEY (`Users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_user`
--

LOCK TABLES `role_user` WRITE;
/*!40000 ALTER TABLE `role_user` DISABLE KEYS */;
INSERT INTO `role_user` VALUES (1,'Admin',2),(2,'user',2),(3,'Buyer',3),(4,'user',1),(5,'Buyer',4);
/*!40000 ALTER TABLE `role_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slider`
--

DROP TABLE IF EXISTS `slider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `slider` (
  `id` int NOT NULL AUTO_INCREMENT,
  `caption` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `tom_tat` varchar(300) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `link` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slider`
--

LOCK TABLES `slider` WRITE;
/*!40000 ALTER TABLE `slider` DISABLE KEYS */;
/*!40000 ALTER TABLE `slider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(70) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `logo` varchar(300) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `datestore` datetime NOT NULL,
  `Users_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_CuaHang_Users1_idx` (`Users_id`),
  CONSTRAINT `fk_CuaHang_Users1` FOREIGN KEY (`Users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES (1,'txSns','HA Cuong','https://res.cloudinary.com/devatchannel/image/upload/v1602752402/avatar/avatar_cugq40.png?fbclid=IwAR1WVZLl0dmt01nM1K4-Lvy30w1-p5XOS7qATZNA7udT-Heak0NA9MvQnys','2021-11-11 00:00:00',3);
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'Tên nhà cung cấp',
  `code` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `logo` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT 'no-image.jsp',
  `email` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'HQC','FDS54','https://res.cloudinary.com/loc120299/image/upload/v1636631596/cookies-shop/cs2grfgcmn3au8d58d3l.png','cuonghq258.longan@gmail.com','0373948583','DFSFD');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'Tên khách hàng',
  `username` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `avartar` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT 'no-image.jpg',
  `address` varchar(90) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `status` int NOT NULL DEFAULT '0',
  `verification_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Nguyễn Thanh Danh','thanhdanh','0373846574','cuonghq7.longan@gmail.com','$2a$10$8ypaVMbkc4TOKyXguOAASerqTRO2Xg6jsSa2SunntFKBiN8ghXcvi','https://res.cloudinary.com/loc120299/image/upload/v1637338114/cookies-shop/r23uyornivcrdbk9cduu.png','long an',0,'SZ1D89xtlwxCl8XCgnfIf8cmnlU5RojsH5pyFWCwcS4OLyRsUdVokLtZo0HnSSdP'),(2,'Hà Quốc Cường','hacuong','0374856743','cuonghq3.longan@gmail.com','$2a$10$Yj8d78MCSBGMOC2nQqXMyOb24Icmo7GhQ8P46hyLA3tyvIMCI4kHS','https://res.cloudinary.com/devatchannel/image/upload/v1602752402/avatar/avatar_cugq40.png?fbclid=IwAR1WVZLl0dmt01nM1K4-Lvy30w1-p5XOS7qATZNA7udT-Heak0NA9MvQnys','long an',1,'ZCWr36RB2oNz0cIfqwdBt7a69k53ifilMrlvEHRL0faILkE9C3FhkLEybS9itjDz'),(3,'Võ Minh Nhựt','minhnhut','0373948583','cuonghq8.longan@gmail.com','$2a$10$MOfViO67M3YLiWzqEYvZO.ACtRkUJ2jVKbFrR6eSZWlZADPscHaTm','https://res.cloudinary.com/devatchannel/image/upload/v1602752402/avatar/avatar_cugq40.png?fbclid=IwAR1WVZLl0dmt01nM1K4-Lvy30w1-p5XOS7qATZNA7udT-Heak0NA9MvQnys','long an',1,'u0l1nM0O0UBFyBQreOhrIj1nAAcg4dqDUPYZDVzhFmtGqP7lAy5WDItgYQTN9WDI'),(4,'Nguyễn Văn Khang','vankhang','0373847594','cuonghq2.longan@gmail.com','$2a$10$F4WZKHQAtlWa/Qk98xgs.uJk9eHs/AOjF3cn.bbCnhrNClRUJJ99u','https://res.cloudinary.com/devatchannel/image/upload/v1602752402/avatar/avatar_cugq40.png?fbclid=IwAR1WVZLl0dmt01nM1K4-Lvy30w1-p5XOS7qATZNA7udT-Heak0NA9MvQnys','long an',0,'dRSc0rWr6ZmEGAr1BEQGDwF6EYuc3UKDGiSzK8UagNbTlwgomcVzoimybZ4NaSPE');
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

-- Dump completed on 2021-12-10 12:08:25
