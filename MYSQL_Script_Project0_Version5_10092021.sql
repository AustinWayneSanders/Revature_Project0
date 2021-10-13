SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bankingapplication
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bankingapplication
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bankingapplication` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `bankingapplication` ;

-- -----------------------------------------------------
-- Table `bankingapplication`.`accounttype`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bankingapplication`.`accounttype` (
  `AccountTypeId` INT NOT NULL AUTO_INCREMENT,
  `AccountName` VARCHAR(45) NOT NULL,
  `InterstRate` DECIMAL(4,2) NOT NULL,
  PRIMARY KEY (`AccountTypeId`),
  UNIQUE INDEX `accountName_UNIQUE` (`AccountName` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bankingapplication`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bankingapplication`.`client` (
  `ClientID` INT NOT NULL AUTO_INCREMENT,
  `FirstName` VARCHAR(45) NOT NULL,
  `LastName` VARCHAR(45) NOT NULL,
  `UserName` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Age` INT NOT NULL,
  `Gender` VARCHAR(25) NULL DEFAULT NULL,
  `Race` VARCHAR(45) NULL DEFAULT NULL,
  `Street` VARCHAR(45) NOT NULL,
  `City` VARCHAR(45) NOT NULL,
  `State` CHAR(2) NOT NULL,
  `PostalCode` INT NOT NULL,
  `DateJoined` DATE NOT NULL,
  PRIMARY KEY (`ClientID`),
  UNIQUE INDEX `UserName_UNIQUE` (`UserName` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bankingapplication`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bankingapplication`.`account` (
  `AccountNumber` INT NOT NULL AUTO_INCREMENT,
  `Balance` DECIMAL(20,2) NOT NULL,
  `ClientID` INT NULL DEFAULT NULL,
  `AccountTypeId` INT NOT NULL,
  PRIMARY KEY (`AccountNumber`),
  INDEX `AccountTypeFk_idx` (`AccountTypeId` ASC) VISIBLE,
  INDEX `ClientFk_idx` (`ClientID` ASC) VISIBLE,
  CONSTRAINT `AccountTypeFk`
    FOREIGN KEY (`AccountTypeId`)
    REFERENCES `bankingapplication`.`accounttype` (`AccountTypeId`),
  CONSTRAINT `ClientFk`
    FOREIGN KEY (`ClientID`)
    REFERENCES `bankingapplication`.`client` (`ClientID`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bankingapplication`.`jointclient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bankingapplication`.`jointclient` (
  `JointClientID` INT NOT NULL AUTO_INCREMENT,
  `FirstName` VARCHAR(45) NOT NULL,
  `LastName` VARCHAR(45) NOT NULL,
  `UserName` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Age` INT NOT NULL,
  `Gender` VARCHAR(25) NULL DEFAULT NULL,
  `Race` VARCHAR(45) NULL DEFAULT NULL,
  `Street` VARCHAR(45) NOT NULL,
  `City` VARCHAR(45) NOT NULL,
  `State` CHAR(2) NOT NULL,
  `PostalCode` INT NOT NULL,
  `DateJoined` DATE NOT NULL,
  `PrimaryClientID` INT NOT NULL,
  PRIMARY KEY (`JointClientID`),
  UNIQUE INDEX `PrimaryClientID_UNIQUE` (`PrimaryClientID` ASC) VISIBLE,
  CONSTRAINT `ClientIDfk`
    FOREIGN KEY (`PrimaryClientID`)
    REFERENCES `bankingapplication`.`client` (`ClientID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bankingapplication`.`transactions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bankingapplication`.`transactions` (
  `TransactionID` INT NOT NULL AUTO_INCREMENT,
  `AccountNumber` INT NOT NULL,
  `TransType` VARCHAR(45) NOT NULL,
  `Amount` DECIMAL(20,2) NOT NULL,
  `TransTimeStamp` TIMESTAMP NOT NULL,
  PRIMARY KEY (`TransactionID`),
  INDEX `AcctNumFk_idx` (`AccountNumber` ASC) VISIBLE,
  CONSTRAINT `AcctNumFk`
    FOREIGN KEY (`AccountNumber`)
    REFERENCES `bankingapplication`.`account` (`AccountNumber`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
