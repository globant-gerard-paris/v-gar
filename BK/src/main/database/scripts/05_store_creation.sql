CREATE TABLE `mygarage`.`store` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `car_rental` bit(1) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `district_name` varchar(255) DEFAULT NULL,
  `district_number` varchar(255) DEFAULT NULL,
  `friday_close` varchar(255) DEFAULT NULL,
  `friday_open` varchar(255) DEFAULT NULL,
  `latitude` varchar(255) DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL,
  `monday_close` varchar(255) DEFAULT NULL,
  `monday_open` varchar(255) DEFAULT NULL,
  `one_stop` bit(1) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `region_name` varchar(255) DEFAULT NULL,
  `region_number` varchar(255) DEFAULT NULL,
  `sac_store` varchar(255) DEFAULT NULL,
  `saturday_close` varchar(255) DEFAULT NULL,
  `saturday_open` varchar(255) DEFAULT NULL,
  `standing` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `store_manager` varchar(255) DEFAULT NULL,
  `store_type` varchar(255) DEFAULT NULL,
  `sunday_close` varchar(255) DEFAULT NULL,
  `sunday_open` varchar(255) DEFAULT NULL,
  `thursday_close` varchar(255) DEFAULT NULL,
  `thursday_open` varchar(255) DEFAULT NULL,
  `tuesday_close` varchar(255) DEFAULT NULL,
  `tuesday_open` varchar(255) DEFAULT NULL,
  `wednesday_close` varchar(255) DEFAULT NULL,
  `wednesday_open` varchar(255) DEFAULT NULL,
  `wifi` bit(1) NOT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=678 DEFAULT CHARSET=utf8;