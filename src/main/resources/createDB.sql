-- ---
-- Globals
-- ---

-- SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
-- SET FOREIGN_KEY_CHECKS=0;

-- ---
-- Table 'User'
--
-- ---
-- create database MapperDB;
USE MapperDB;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `User`;

CREATE TABLE `User` (
  `id` INTEGER NOT NULL AUTO_INCREMENT DEFAULT NULL,
  `firstName` VARCHAR(20) NOT NULL,
  `lastName` VARCHAR(20) NOT NULL,
  `login` VARCHAR(20) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `role` ENUM("USER", "ADMIN") NOT NULL ,
  `dateOfBirth` DATE DEFAULT NULL,
  `sex` ENUM("MAN", "WOMAN") DEFAULT NULL,
  `about` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'Marker'
--
-- ---

DROP TABLE IF EXISTS `Marker`;

CREATE TABLE `Marker` (
  `id` INTEGER NOT NULL AUTO_INCREMENT DEFAULT NULL,
  `userId` INTEGER NOT NULL,
  `adress` VARCHAR(100) NOT NULL,
  `lat` DECIMAL(10,6) NOT NULL,
  `lng` DECIMAL(10,6) NOT NULL,
  `datetime` TIMESTAMP NOT NULL,
  `access` bit(5) NOT NULL,
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'Message'
--
-- ---

DROP TABLE IF EXISTS `Message`;

CREATE TABLE `Message` (
  `id` INTEGER NOT NULL AUTO_INCREMENT DEFAULT NULL,
  `headerId` INTEGER NOT NULL,
  `isFromSender` bit(5) NOT NULL,
  `content` VARCHAR(255) NOT NULL,
  `isRead` bit(5) NOT NULL DEFAULT 0,
  `datetime` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'Header'
--
-- ---

DROP TABLE IF EXISTS `Header`;

CREATE TABLE `Header` (
  `id` INTEGER NOT NULL AUTO_INCREMENT DEFAULT NULL,
  `fromId` INTEGER NOT NULL,
  `toId` INTEGER NOT NULL,
  `subject` VARCHAR(50) NULL DEFAULT NULL,
  `status` ENUM("removeSender", "removeReceiver", "removeBoth","existBoth") NOT NULL,
  `datetime` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'CommentMarker'
--
-- ---

DROP TABLE IF EXISTS `CommentMarker`;

CREATE TABLE `CommentMarker` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `noteId` INTEGER NOT NULL,
  `userId` INTEGER NOT NULL,
  `content` VARCHAR(255) NOT NULL,
  `datetime` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'Photo'
--
-- ---

DROP TABLE IF EXISTS `Photo`;

CREATE TABLE `Photo` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `noteId` INTEGER NOT NULL,
  `userId` INTEGER NOT NULL,
  `pathImageBig` VARCHAR(255) NOT NULL,
  `pathImageSmall` VARCHAR(255) NOT NULL,
  `datetime` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'CommentPhoto'
--
-- ---

DROP TABLE IF EXISTS `CommentPhoto`;

CREATE TABLE `CommentPhoto` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `photoId` INTEGER NOT NULL,
  `userId` INTEGER NOT NULL,
  `content` VARCHAR(255) NOT NULL,
  `datetime` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'Company'
--
-- ---

DROP TABLE IF EXISTS `Company`;

CREATE TABLE `Company` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `userId` INTEGER NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `dateFrom` DATE NULL,
  `dateTo` DATE NULL,
  `position` VARCHAR(50) NULL,
  `isActive` bit NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'Education'
--
-- ---

DROP TABLE IF EXISTS `Education`;

CREATE TABLE `Education` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `userId` INTEGER NOT NULL,
  `establishment` VARCHAR(100) NOT NULL,
  `dateFrom` DATE NULL,
  `dateTo` DATE NULL,
  `diploma` VARCHAR(50) NULL,
  `isActive` bit NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'Adress'
--
-- ---

DROP TABLE IF EXISTS `Adress`;

CREATE TABLE `Adress` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `userId` INTEGER NOT NULL,
  `country` VARCHAR(50) NULL DEFAULT NULL,
  `city` VARCHAR(100) NULL DEFAULT NULL,
  `adress` VARCHAR(100) NULL DEFAULT NULL,
  `dateFrom` DATE NULL,
  `dateTo` DATE NULL,
  `isActive` bit NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
);

-- ---
-- Foreign Keys
-- ---

ALTER TABLE `Marker` ADD FOREIGN KEY (userId) REFERENCES `User` (`id`);
ALTER TABLE `Message` ADD FOREIGN KEY (headerId) REFERENCES `Header` (`id`);
ALTER TABLE `Header` ADD FOREIGN KEY (fromId) REFERENCES `User` (`id`);
ALTER TABLE `Header` ADD FOREIGN KEY (toId) REFERENCES `User` (`id`);
ALTER TABLE `CommentMarker` ADD FOREIGN KEY (noteId) REFERENCES `Marker` (`id`);
ALTER TABLE `CommentMarker` ADD FOREIGN KEY (userId) REFERENCES `User` (`id`);
ALTER TABLE `Photo` ADD FOREIGN KEY (noteId) REFERENCES `Marker` (`id`);
ALTER TABLE `Photo` ADD FOREIGN KEY (userId) REFERENCES `User` (`id`);
ALTER TABLE `CommentPhoto` ADD FOREIGN KEY (photoId) REFERENCES `Photo` (`id`);
ALTER TABLE `CommentPhoto` ADD FOREIGN KEY (userId) REFERENCES `User` (`id`);
ALTER TABLE `Company` ADD FOREIGN KEY (userId) REFERENCES `User` (`id`);
ALTER TABLE `Education` ADD FOREIGN KEY (userId) REFERENCES `User` (`id`);
ALTER TABLE `Adress` ADD FOREIGN KEY (userId) REFERENCES `User` (`id`);

-- ---
-- Table Properties
-- ---

ALTER TABLE `User` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
ALTER TABLE `Marker` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
ALTER TABLE `Message` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
ALTER TABLE `Header` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
ALTER TABLE `CommentMarker` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
ALTER TABLE `Photo` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
ALTER TABLE `CommentPhoto` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
ALTER TABLE `Company` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
ALTER TABLE `Education` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
ALTER TABLE `Adress` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ---
-- Test Data
-- ---

-- INSERT INTO `User` (`id`,`firstName`,`lastName`,`login`,`password`,`email`,`role`,`dateOfBirth`,`sex`,`about`) VALUES
-- ('','','','','','','','','','');
-- INSERT INTO `Marker` (`id`,`userId`,`adress`,`lat`,`lng`,`datetime`,`access`) VALUES
-- ('','','','','','','');
-- INSERT INTO `Message` (`id`,`headerId`,`isFromSender`,`content`,`isRead`,`datetime`) VALUES
-- ('','','','','','');
-- INSERT INTO `Header` (`id`,`fromId`,`toId`,`subject`,`status`,`datetime`) VALUES
-- ('','','','','','');
-- INSERT INTO `CommentMarker` (`id`,`noteId`,`userId`,`content`,`datetime`) VALUES
-- ('','','','','');
-- INSERT INTO `Photo` (`id`,`noteId`,`userId`,`pathImageBig`,`pathImageSmall`,`datetime`) VALUES
-- ('','','','','','');
-- INSERT INTO `CommentPhoto` (`id`,`photoId`,`userId`,`content`,`datetime`) VALUES
-- ('','','','','');
-- INSERT INTO `Company` (`id`,`userId`,`name`,`dateFrom`,`dateTo`,`position`,`isActive`) VALUES
-- ('','','','','','','');
-- INSERT INTO `Education` (`id`,`userId`,`establishment`,`dateFrom`,`dateTo`,`diploma`,`isActive`) VALUES
-- ('','','','','','','');
-- INSERT INTO `Adress` (`id`,`userId`,`country`,`city`,`adress`,`dateFrom`,`dateTo`,`isActive`) VALUES
-- ('','','','','','','','');

SET FOREIGN_KEY_CHECKS = 1;