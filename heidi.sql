-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.18-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for ltweb2
DROP DATABASE IF EXISTS `ltweb2`;
CREATE DATABASE IF NOT EXISTS `ltweb2` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `ltweb2`;

-- Dumping structure for table ltweb2.department
DROP TABLE IF EXISTS `department`;
CREATE TABLE IF NOT EXISTS `department` (
  `DEPT_ID` int(11) NOT NULL,
  `DEPT_NAME` varchar(255) NOT NULL,
  `DEPT_NO` varchar(20) NOT NULL,
  `LOCATION` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DEPT_ID`),
  UNIQUE KEY `DEPT_NO` (`DEPT_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ltweb2.department: ~4 rows (approximately)
DELETE FROM `department`;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` (`DEPT_ID`, `DEPT_NAME`, `DEPT_NO`, `LOCATION`) VALUES
	(10, 'ACCOUNTING', 'D10', 'NEW YORK'),
	(20, 'RESEARCH', 'D20', 'DALLAS'),
	(30, 'SALES', 'D30', 'CHICAGO'),
	(40, 'OPERATIONS', 'D40', 'BOSTON');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;

-- Dumping structure for table ltweb2.employee
DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `EMP_ID` bigint(20) NOT NULL,
  `EMP_NAME` varchar(50) NOT NULL,
  `EMP_NO` varchar(20) NOT NULL,
  `HIRE_DATE` date NOT NULL,
  `IMAGE` longblob DEFAULT NULL,
  `JOB` varchar(30) NOT NULL,
  `SALARY` float NOT NULL,
  `DEPT_ID` int(11) NOT NULL,
  `MNG_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`EMP_ID`),
  UNIQUE KEY `EMP_NO` (`EMP_NO`),
  KEY `FK_employee_department` (`DEPT_ID`),
  CONSTRAINT `FK_EMP_DEPT` FOREIGN KEY (`DEPT_ID`) REFERENCES `department` (`DEPT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ltweb2.employee: ~7 rows (approximately)
DELETE FROM `employee`;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`EMP_ID`, `EMP_NAME`, `EMP_NO`, `HIRE_DATE`, `IMAGE`, `JOB`, `SALARY`, `DEPT_ID`, `MNG_ID`) VALUES
	(123, 'kekw', '3456', '2019-08-23', NULL, 'Cleaner', 1000, 10, 12),
	(321, '321', '4321', '2018-01-28', NULL, 'Senior IT', 3000, 20, 4321),
	(9887, 'Khanh', '12345', '1998-01-12', NULL, 'Dev', 1000, 30, 123),
	(463636, 'khanh', '345768', '2019-01-11', NULL, 'Lecturer', 2245, 30, 4321),
	(654321, 'ABcd', '654321', '0019-07-11', NULL, 'Senior IT', 2000, 40, 4321);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;

-- Dumping structure for table ltweb2.salary_grade
DROP TABLE IF EXISTS `salary_grade`;
CREATE TABLE IF NOT EXISTS `salary_grade` (
  `GRADE` int(11) NOT NULL,
  `HIGH_SALARY` float NOT NULL,
  `LOW_SALARY` float NOT NULL,
  PRIMARY KEY (`GRADE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ltweb2.salary_grade: ~3 rows (approximately)
DELETE FROM `salary_grade`;
/*!40000 ALTER TABLE `salary_grade` DISABLE KEYS */;
INSERT INTO `salary_grade` (`GRADE`, `HIGH_SALARY`, `LOW_SALARY`) VALUES
	(3, 40000, 20000),
	(4, 40000, 20000),
	(9, 40000, 20000);
/*!40000 ALTER TABLE `salary_grade` ENABLE KEYS */;

-- Dumping structure for table ltweb2.timekeeper
DROP TABLE IF EXISTS `timekeeper`;
CREATE TABLE IF NOT EXISTS `timekeeper` (
  `Timekeeper_Id` varchar(36) NOT NULL,
  `Date_Time` datetime NOT NULL,
  `In_Out` char(1) NOT NULL,
  `EMP_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`Timekeeper_Id`),
  KEY `FK744D9BFF6106A42` (`EMP_ID`),
  CONSTRAINT `FK744D9BFF6106A42` FOREIGN KEY (`EMP_ID`) REFERENCES `employee` (`EMP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ltweb2.timekeeper: ~0 rows (approximately)
DELETE FROM `timekeeper`;
/*!40000 ALTER TABLE `timekeeper` DISABLE KEYS */;
INSERT INTO `timekeeper` (`Timekeeper_Id`, `Date_Time`, `In_Out`, `EMP_ID`) VALUES
	('1', '2022-09-14 00:00:00', 'i', 123);
/*!40000 ALTER TABLE `timekeeper` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
