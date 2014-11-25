USE `mygarage`;

DROP TABLE IF EXISTS `service_translation`;
CREATE TABLE IF NOT EXISTS `service_translation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `product_flag` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_q5ql612ykcasodp9dmsw27wru` (`product_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*!40000 ALTER TABLE `service_translation` DISABLE KEYS */;
INSERT INTO `service_translation` (`id`, `category`, `description`, `product_flag`) VALUES
	(1, 'Tires', 'Tire Service', 41),
	(2, 'Battery', 'Battery Service', 42),
	(3, 'Alignment', 'Alignment', 43),
	(4, 'Brakes', 'Brake Service', 44),
	(5, 'Suspension', 'Shock/Strut Service', 45),
	(6, 'Suspension', 'Front End/ Chassis Service', 46),
	(7, 'Tires', 'Tire Rotation Service', 47),
	(10, 'Maintenance', 'Maintenance Service', 48),
	(11, 'Oil', 'Oil Change Service', 49),
	(12, 'Engine/Drivetrain', 'Engine Repair Service', 50),
	(13, 'Exhaust Service', 'Exhaust Service', 51),
	(14, 'Maintenance', 'Engine Cooling Service', 52),
	(15, 'Maintenance', 'A/C Service', 53),
	(16, 'Battery', 'Start/Charging Service', 54),
	(17, 'Suspension', 'Undercar Eval', 55),
	(18, 'Coolant Exchange', 'Coolant Exchange', 56),
	(19, 'Trans Fluid Exchange', 'Transmission Fluid Service', 57),
	(20, 'Engine/Drivetrain', 'Drivetrain Service', 58),
	(21, 'A/C Recharge', 'A/C Recharge Service', 59),
	(22, 'Brakes', 'Brake Eval', 60),
	(23, 'Maintenance', 'Fuel/ Air Maintenance Service', 61),
	(24, 'Purchase Tire', 'Purchase Tire', 1);
