-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.11.0-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for coursera
CREATE DATABASE IF NOT EXISTS `coursera` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `coursera`;

-- Dumping structure for table coursera.courses
CREATE TABLE IF NOT EXISTS `courses` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL DEFAULT '',
  `instructor_id` int(11) NOT NULL DEFAULT 0,
  `total_time` tinyint(4) NOT NULL DEFAULT 0,
  `credit` tinyint(4) NOT NULL DEFAULT 0,
  `time_created` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `courses_instructors_id_fk` (`instructor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table coursera.courses: ~4 rows (approximately)
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` (`id`, `name`, `instructor_id`, `total_time`, `credit`, `time_created`) VALUES
	(1, 'Analysis', 1, 20, 10, '2020-03-16 13:26:44'),
	(2, 'Linear Algebra', 1, 30, 15, '2020-03-16 13:27:26'),
	(3, 'Statistics', 2, 30, 15, '2020-03-16 13:27:38'),
	(4, 'Geometry', 3, 35, 20, '2020-03-16 13:27:54');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;

-- Dumping structure for table coursera.instructors
CREATE TABLE IF NOT EXISTS `instructors` (
  `id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `time_created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table coursera.instructors: ~3 rows (approximately)
/*!40000 ALTER TABLE `instructors` DISABLE KEYS */;
INSERT INTO `instructors` (`id`, `first_name`, `last_name`, `time_created`) VALUES
	(1, 'Neno', 'Dimitrov', '2020-03-16 13:25:34'),
	(2, 'Petko', 'Valchev', '2020-03-16 13:26:00'),
	(3, 'Petar', 'Penchev', '2020-03-16 13:26:12');
/*!40000 ALTER TABLE `instructors` ENABLE KEYS */;

-- Dumping structure for table coursera.students
CREATE TABLE IF NOT EXISTS `students` (
  `pin` char(50) NOT NULL,
  `first_name` varchar(50) DEFAULT '',
  `last_name` varchar(50) DEFAULT '',
  `time_created` datetime DEFAULT NULL,
  PRIMARY KEY (`pin`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table coursera.students: ~3 rows (approximately)
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` (`pin`, `first_name`, `last_name`, `time_created`) VALUES
	('9412011005', 'Krasimir', 'Petrov', '2020-03-16 13:23:58'),
	('9501011014', 'Elena', 'Foteva', '2020-03-16 13:24:29'),
	('9507141009', 'Ivan', 'Ivanov', '2020-03-16 13:23:39');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;

-- Dumping structure for table coursera.students courses xref
CREATE TABLE IF NOT EXISTS `students courses xref` (
  `stundents_pin` char(50) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `completion_data` date NOT NULL,
  KEY `students courses xref_courses_null_fk` (`course_id`),
  KEY `students courses xref_students_null_fk` (`stundents_pin`),
  CONSTRAINT `students courses xref_courses_null_fk` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`),
  CONSTRAINT `students courses xref_students_null_fk` FOREIGN KEY (`stundents_pin`) REFERENCES `students` (`pin`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table coursera.students courses xref: ~7 rows (approximately)
/*!40000 ALTER TABLE `students courses xref` DISABLE KEYS */;
INSERT INTO `students courses xref` (`stundents_pin`, `course_id`, `completion_data`) VALUES
	('9412011005', 1, '2019-07-16'),
	('9412011005', 2, '2019-08-20'),
	('9501011014', 1, '2019-07-16'),
	('9501011014', 2, '2019-08-01'),
	('9501011014', 3, '2019-10-01'),
	('9501011014', 4, '2019-12-05'),
	('9507141009', 4, '2023-02-13');
/*!40000 ALTER TABLE `students courses xref` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
