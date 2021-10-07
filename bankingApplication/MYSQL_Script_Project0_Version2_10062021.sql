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
-- Table `bankingapplication`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bankingapplication`.`account` (
  `AccountNumber` INT NOT NULL AUTO_INCREMENT,
  `RoutingNum` INT NOT NULL,
  `UserName` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`AccountNumber`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bankingapplication`.`checkingaccount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bankingapplication`.`checkingaccount` (
  `ChkAcctNum` INT NOT NULL AUTO_INCREMENT,
  `AccountNumber` INT NOT NULL,
  `Interest` DECIMAL(4,2) NOT NULL,
  PRIMARY KEY (`ChkAcctNum`),
  UNIQUE INDEX `AccountNumber_UNIQUE` (`AccountNumber` ASC) VISIBLE,
  CONSTRAINT `AccountNumber_Chk_FK`
    FOREIGN KEY (`AccountNumber`)
    REFERENCES `bankingapplication`.`account` (`AccountNumber`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bankingapplication`.`checkingtransactions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bankingapplication`.`checkingtransactions` (
  `CheckingTransID` INT NOT NULL AUTO_INCREMENT,
  `ChkAcctNum` INT NOT NULL,
  `Balance` DECIMAL(20,2) NOT NULL,
  `Withdrawn` DECIMAL(20,2) NULL DEFAULT NULL,
  `Deposited` DECIMAL(20,2) NULL DEFAULT NULL,
  `Dividend` DECIMAL(20,2) NULL DEFAULT NULL,
  `TransChkTimeStamp` DATE NOT NULL,
  PRIMARY KEY (`CheckingTransID`),
  INDEX `ChkAcctNum_idx` (`ChkAcctNum` ASC) VISIBLE,
  CONSTRAINT `ChkAcctNum`
    FOREIGN KEY (`ChkAcctNum`)
    REFERENCES `bankingapplication`.`checkingaccount` (`ChkAcctNum`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bankingapplication`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bankingapplication`.`client` (
  `SSN` INT NOT NULL AUTO_INCREMENT,
  `AccountNumber` INT NOT NULL,
  `FirstName` VARCHAR(45) NOT NULL,
  `LastName` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Age` INT NOT NULL,
  `Gender` VARCHAR(25) NULL DEFAULT NULL,
  `Race` VARCHAR(45) NULL DEFAULT NULL,
  `Street` VARCHAR(45) NOT NULL,
  `City` VARCHAR(45) NOT NULL,
  `State` CHAR(2) NOT NULL,
  `PostalCode` INT NOT NULL,
  `DateJoined` DATE NOT NULL,
  PRIMARY KEY (`SSN`),
  INDEX `AccountNumber_idx` (`AccountNumber` ASC) VISIBLE,
  CONSTRAINT `AccountNumber`
    FOREIGN KEY (`AccountNumber`)
    REFERENCES `bankingapplication`.`account` (`AccountNumber`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bankingapplication`.`savingsaccount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bankingapplication`.`savingsaccount` (
  `SvgsAcctNum` INT NOT NULL,
  `AccountNumber` INT NOT NULL,
  `interest` DECIMAL(4,2) NOT NULL,
  PRIMARY KEY (`SvgsAcctNum`),
  UNIQUE INDEX `AccountNumber_UNIQUE` (`AccountNumber` ASC) VISIBLE,
  CONSTRAINT `AccountNumber_Svgs_FK`
    FOREIGN KEY (`AccountNumber`)
    REFERENCES `bankingapplication`.`account` (`AccountNumber`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bankingapplication`.`savingstransactions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bankingapplication`.`savingstransactions` (
  `SavingsID` INT NOT NULL AUTO_INCREMENT,
  `SvgsAcctNum` INT NOT NULL,
  `CurrentBalance` DECIMAL(20,2) NOT NULL,
  `Withdrawn` DECIMAL(20,2) NULL DEFAULT NULL,
  `Deposited` DECIMAL(20,2) NULL DEFAULT NULL,
  `Dividend` DECIMAL(20,2) NULL DEFAULT NULL,
  `TransSvgsTimeStamp` DATE NOT NULL,
  PRIMARY KEY (`SavingsID`),
  INDEX `SvgsAcctNum_idx` (`SvgsAcctNum` ASC) VISIBLE,
  CONSTRAINT `SvgsAcctNum`
    FOREIGN KEY (`SvgsAcctNum`)
    REFERENCES `bankingapplication`.`savingsaccount` (`SvgsAcctNum`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
