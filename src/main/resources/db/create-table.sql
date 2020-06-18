-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema petstore_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `petstore_db` ;

-- -----------------------------------------------------
-- Schema petstore_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `petstore_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `petstore_db` ;

-- -----------------------------------------------------
-- Table `petstore_db`.`store`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `petstore_db`.`store` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `location` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `petstore_db`.`pet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `petstore_db`.`pet` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `color` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `type` VARCHAR(255) NULL DEFAULT NULL,
  `store_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKo88yjg5w4dsj308icokdld8iq` (`store_id` ASC) VISIBLE,
  CONSTRAINT `FKo88yjg5w4dsj308icokdld8iq`
    FOREIGN KEY (`store_id`)
    REFERENCES `petstore_db`.`store` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `petstore_db`.`store_pets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `petstore_db`.`store_pets` (
  `store_id` INT(11) NOT NULL,
  `pets_id` INT(11) NOT NULL,
  UNIQUE INDEX `UK_s8my7qc7bpa7g3g5qnh6f8pb5` (`pets_id` ASC) VISIBLE,
  INDEX `FK4afc40mkf7igbxhy0xaswcf3h` (`store_id` ASC) VISIBLE,
  CONSTRAINT `FK4afc40mkf7igbxhy0xaswcf3h`
    FOREIGN KEY (`store_id`)
    REFERENCES `petstore_db`.`store` (`id`),
  CONSTRAINT `FKj7w10ixoptyu9v8knos1y25wc`
    FOREIGN KEY (`pets_id`)
    REFERENCES `petstore_db`.`pet` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
