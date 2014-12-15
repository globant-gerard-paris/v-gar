/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

USE `mygarage`;

-- Volcando estructura para tabla mygarage.suggested_service
DROP TABLE IF EXISTS `suggested_service`;
CREATE TABLE IF NOT EXISTS `suggested_service` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `system_description` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `sku` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8scb3lupi2th17fc9a0f8b73w` (`sku`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla mygarage.suggested_service: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `suggested_service` DISABLE KEYS */;
INSERT INTO `suggested_service` (`id`, `system_description`, `description`, `link`, `sku`) VALUES
	(1, 'APR STATE,INSPECTION', 'State Inspection Due in April', NULL, '2841804000'),
	(2, 'AUG STATE,INSPECTION', 'State Inspection Due in August', NULL, '2841808000'),
	(3, 'DEC STATE,INSPECTION', 'State Inspection Due in December', NULL, '2841812000'),
	(4, 'FEB STATE,INSPECTION', 'State Inspection Due in February', NULL, '2841802000'),
	(5, 'JAN STATE,INSPECTION', 'State Inspection Due in January', NULL, '2841801000'),
	(6, 'JULY STATE,INSPECTION', 'State Inspection Due in July', NULL, '2841807000'),
	(7, 'JUN STATE,INSPECTION', 'State Inspection Due in June', NULL, '2841806000'),
	(8, 'MAR STATE,INSPECTION', 'State Inspection Due in March', NULL, '2841803000'),
	(9, 'MAY STATE,INSPECTION', 'State Inspection Due in May', NULL, '2841805000'),
	(10, 'NOV STATE,INSPECTION', 'State Inspection Due in November', NULL, '2841811000'),
	(11, 'OCT STATE,INSPECTION', 'State Inspection Due in October', NULL, '2841810000'),
	(12, 'SEPT STATE,INSPECTION', 'State Inspection Due in September', NULL, '2841809000'),
	(13, 'SUGG COOL,ADJ FLUID LVL', 'Adjust Low Coolant Level', 'http://www.searsauto.com/auto-services/coolant-exchange', '2866717000'),
	(14, 'SUGG OBD,RELATED REPAIR', 'Diagnostic Repair', 'http://www.searsauto.com/auto-services/check-engine-light', '2866103000'),
	(15, 'SUGG OIL,ADJ FLUID LVL', 'Adjust Low Oil Level', 'http://www.searsauto.com/auto-services/oil-change', '2806672000'),
	(16, 'SUGG PS,ADJ FLUID LVL', 'Adjust Low Power Steering Fluid Level', 'http://www.searsauto.com/auto-services/power-steering-fluid', '2866719000'),
	(17, 'SUGG TRAN,ADJ FLUID LVL', 'Adjust Low Transmission Fluid Level', 'http://www.searsauto.com/auto-services/transmission-fluid', '2866718000'),
	(18, 'SUGG, HEADLGT RESTOR', 'Headlight Restoration', NULL, '2810892000'),
	(19, 'SUGGEST, AIR FILTER', 'Suggested Air Filter Replacement', NULL, '2801310000'),
	(20, 'SUGGEST, ALIGNMENT', 'Alignment Service', 'http://www.searsauto.com/auto-services/wheel-alignment', '2801305000'),
	(21, 'SUGGEST, BATTERY', 'Battery Replacement', 'http://www.searsauto.com/batteries', '2801314000'),
	(22, 'SUGGEST, BELT', 'Belt(s) Replacement', 'http://www.searsauto.com/auto-services/belt-replacement', '2801312000'),
	(23, 'SUGGEST, BRAK SERV / EVAL', 'Complete Brake Evaluation', 'http://www.searsauto.com/auto-services/brake-repair', '2801300000'),
	(24, 'SUGGEST, CABIN FILTE', 'Suggested Cabin Air Filter Replacement', NULL, '2801311000'),
	(25, 'SUGGEST, COOLANT EXC', 'Coolant Exchange', 'http://www.searsauto.com/auto-services/coolant-exchange', '2801307000'),
	(26, 'SUGGEST, CV BOOT/SHA', 'CV Boot and/or Shaft Replacement', NULL, '2801303000'),
	(27, 'SUGGEST, FUEL SYST CLEANI', 'Fuel System Cleaning', 'http://www.searsauto.com/auto-services/fuel-system-cleaning', '2801394000'),
	(28, 'SUGGEST, HOSE', 'Hose(s) Replacement', NULL, '2801313000'),
	(29, 'SUGGEST, OBD II SCAN', 'Advanced Diagnostic Test', 'http://www.searsauto.com/auto-services/check-engine-light', '2866720000'),
	(30, 'SUGGEST, PS FLUID EX', 'Power Steering Fluid Exchange', 'http://www.searsauto.com/auto-services/power-steering-fluid', '2801309000'),
	(31, 'SUGGEST, SHOCK/STRUT', 'Shocks and/or Struts Replacement', 'http://www.searsauto.com/auto-services/shocks-and-struts', '2801302000'),
	(32, 'SUGGEST, STEERING SE', 'Steering Service Repair', 'http://www.searsauto.com/auto-services/steering-and-suspension', '2801306000'),
	(33, 'SUGGEST, SUSPENSION', 'Suspension Repair', 'http://www.searsauto.com/auto-services/steering-and-suspension', '2801301000'),
	(34, 'SUGGEST, TIRE(S)', 'Suggested New Tires', 'http://www.searsauto.com/tires', '2801315000'),
	(35, 'SUGGEST, TRANS FLUID', 'Adjust Low Transmission Fluid Level', 'http://www.searsauto.com/auto-services/transmission-fluid', '2801308000'),
	(36, 'SUGGEST, WHEEL BEARI', 'Wheel Bearing Replacement', NULL, '2801304000'),
	(37, 'SUGGEST,BATT HARDWARE', 'Battery Hardware Replacement', 'http://www.searsauto.com/batteries', '2866721000'),
	(38, 'SUGGEST,BULB/LIGHTNG', 'Light Bulb(s) Replacement', NULL, '2803518000'),
	(39, 'SUGGEST,EXHAUST REPAIR', 'Exhaust Repair', NULL, '2803517000'),
	(40, 'SUGGEST,SPRKPLG/TNUP', 'Spark Plugs and/or Tune Up', 'http://www.searsauto.com/auto-services/car-tune-up', '2806081000'),
	(41, 'SUGGEST,TPMS SERVICE', 'Tire Pressure Monitoring System Evaluation', NULL, '2806422000'),
	(42, 'SUGGEST,WIPER BLADES', 'Wiper Blade(s) Replacement', NULL, '2866716000');
/*!40000 ALTER TABLE `suggested_service` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
