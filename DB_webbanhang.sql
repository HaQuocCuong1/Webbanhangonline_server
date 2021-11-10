-- MySQL Workbench Synchronization
-- Generated: 2021-11-07 11:53
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: ASUS

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `webbanhang` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;

CREATE TABLE IF NOT EXISTS `webbanhang`.`Categories` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL COMMENT 'Tên loại sản phẩm',
  `code` VARCHAR(45) NULL DEFAULT NULL,
  `total_product` INT(11) NOT NULL DEFAULT 0,
  `avartar` VARCHAR(500) NOT NULL DEFAULT 'no-image.jpg',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `webbanhang`.`Supplier` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL COMMENT 'Tên nhà cung cấp',
  `code` VARCHAR(45) NULL DEFAULT NULL,
  `logo` VARCHAR(500) NOT NULL DEFAULT 'no-image.jsp',
  `email` VARCHAR(60) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `webbanhang`.`Key_word` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `code` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `webbanhang`.`Products` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NOT NULL COMMENT 'Tên sản phẩm',
  `code` VARCHAR(45) NULL DEFAULT NULL,
  `sort_description` VARCHAR(600) NULL DEFAULT NULL COMMENT 'mô tả ngắn ngọn',
  `detail_description` TEXT NULL DEFAULT NULL COMMENT 'mô tả chi tiết',
  `ban_nhanh` TINYINT(4) NOT NULL DEFAULT 0 COMMENT 'bán chạy',
  `price` DOUBLE NOT NULL COMMENT 'giá bán',
  `featured` TINYINT(4) NOT NULL DEFAULT 0,
  `competitive_price` DOUBLE NULL DEFAULT 0,
  `avartar` VARCHAR(500) NOT NULL DEFAULT 'no-image.jpg',
  `date_sale` DATETIME NOT NULL,
  `quantity` INT(11) NOT NULL,
  `promotion` INT(11) NULL DEFAULT 0,
  `status` TINYINT(4) NOT NULL DEFAULT 0,
  `Supplier_id` INT(11) NOT NULL,
  `Categories_id` INT(11) NOT NULL,
  `Users_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Products_Supplier_idx` (`Supplier_id` ASC) VISIBLE,
  INDEX `fk_Products_Categories1_idx` (`Categories_id` ASC) VISIBLE,
  INDEX `fk_Products_Users1_idx` (`Users_id` ASC) VISIBLE,
  CONSTRAINT `fk_Products_Supplier`
    FOREIGN KEY (`Supplier_id`)
    REFERENCES `webbanhang`.`Supplier` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Products_Categories1`
    FOREIGN KEY (`Categories_id`)
    REFERENCES `webbanhang`.`Categories` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Products_Users1`
    FOREIGN KEY (`Users_id`)
    REFERENCES `webbanhang`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `webbanhang`.`Key_word_product` (
  `idKey_word_product` INT(11) NOT NULL AUTO_INCREMENT,
  `Key_word_id` INT(11) NOT NULL,
  `Products_id` INT(11) NOT NULL,
  PRIMARY KEY (`idKey_word_product`),
  INDEX `fk_Key_word_product_Key_word1_idx` (`Key_word_id` ASC) VISIBLE,
  INDEX `fk_Key_word_product_Products1_idx` (`Products_id` ASC) VISIBLE,
  CONSTRAINT `fk_Key_word_product_Key_word1`
    FOREIGN KEY (`Key_word_id`)
    REFERENCES `webbanhang`.`Key_word` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Key_word_product_Products1`
    FOREIGN KEY (`Products_id`)
    REFERENCES `webbanhang`.`Products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `webbanhang`.`Picture_product` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `file` VARCHAR(500) NOT NULL,
  `Products_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Picture_product_Products1_idx` (`Products_id` ASC) VISIBLE,
  CONSTRAINT `fk_Picture_product_Products1`
    FOREIGN KEY (`Products_id`)
    REFERENCES `webbanhang`.`Products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `webbanhang`.`slider` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `caption` VARCHAR(100) NULL DEFAULT NULL,
  `tom_tat` VARCHAR(300) NULL DEFAULT NULL,
  `link` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `webbanhang`.`Anh_slider` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `file` VARCHAR(300) NULL DEFAULT NULL,
  `slider_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Anh_slider_slider1_idx` (`slider_id` ASC) VISIBLE,
  CONSTRAINT `fk_Anh_slider_slider1`
    FOREIGN KEY (`slider_id`)
    REFERENCES `webbanhang`.`slider` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `webbanhang`.`Orders` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `payments` TINYINT(11) NOT NULL DEFAULT 0,
  `deliveryaddress` NVARCHAR(255) NOT NULL,
  `transportfee` DOUBLE NULL DEFAULT NULL,
  `status` TINYINT(10) NOT NULL DEFAULT 0,
  `dateorder` DATETIME NULL DEFAULT NULL,
  `namecustomer` NVARCHAR(70) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `note` TEXT NULL DEFAULT NULL,
  `Users_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Orders_Users1_idx` (`Users_id` ASC) VISIBLE,
  CONSTRAINT `fk_Orders_Users1`
    FOREIGN KEY (`Users_id`)
    REFERENCES `webbanhang`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `webbanhang`.`Order_detail` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `totalmoney` DOUBLE NOT NULL DEFAULT 0,
  `quantity` INT(11) NOT NULL DEFAULT 1,
  `discount` DOUBLE NULL DEFAULT NULL,
  `avartar` VARCHAR(500) NOT NULL DEFAULT 'no-image.jpg',
  `name` VARCHAR(150) NULL DEFAULT 'Tên sản phẩm',
  `status` TINYINT(1) NOT NULL DEFAULT 0,
  `Products_id` INT(11) NOT NULL,
  `Orders_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Order_Detail_Products1_idx` (`Products_id` ASC) VISIBLE,
  INDEX `fk_Order_Detail_Orders1_idx` (`Orders_id` ASC) VISIBLE,
  CONSTRAINT `fk_Order_Detail_Products1`
    FOREIGN KEY (`Products_id`)
    REFERENCES `webbanhang`.`Products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_Detail_Orders1`
    FOREIGN KEY (`Orders_id`)
    REFERENCES `webbanhang`.`Orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `webbanhang`.`Users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL COMMENT 'Tên khách hàng',
  `username` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(20) NOT NULL,
  `email` VARCHAR(60) NULL DEFAULT NULL,
  `password` VARCHAR(100) NOT NULL,
  `avartar` VARCHAR(500) NULL DEFAULT 'no-image.jpg',
  `address` VARCHAR(90) NOT NULL,
  `status` INT(11) NOT NULL DEFAULT 0,
  `verification_code` VARCHAR(64) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `webbanhang`.`Comment` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL COMMENT 'Tên người bình luận',
  `content` TEXT NULL DEFAULT NULL,
  `Users_id` INT(11) NOT NULL,
  `Products_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Comment_Users1_idx` (`Users_id` ASC) VISIBLE,
  INDEX `fk_Comment_Products1_idx` (`Products_id` ASC) VISIBLE,
  CONSTRAINT `fk_Comment_Users1`
    FOREIGN KEY (`Users_id`)
    REFERENCES `webbanhang`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comment_Products1`
    FOREIGN KEY (`Products_id`)
    REFERENCES `webbanhang`.`Products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `webbanhang`.`Role_user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NULL DEFAULT NULL,
  `Users_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Role_user_Users1_idx` (`Users_id` ASC) VISIBLE,
  CONSTRAINT `fk_Role_user_Users1`
    FOREIGN KEY (`Users_id`)
    REFERENCES `webbanhang`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `webbanhang`.`Store` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(45) NULL DEFAULT NULL,
  `name` VARCHAR(70) NULL DEFAULT NULL,
  `logo` VARCHAR(300) NULL DEFAULT NULL,
  `datestore` DATETIME NOT NULL,
  `Users_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_CuaHang_Users1_idx` (`Users_id` ASC) VISIBLE,
  CONSTRAINT `fk_CuaHang_Users1`
    FOREIGN KEY (`Users_id`)
    REFERENCES `webbanhang`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
