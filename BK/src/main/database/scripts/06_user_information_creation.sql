CREATE TABLE `mygarage`.`user_information` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `last_update` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `store` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9bql56vlb1l63h6xtkw6nfwal` (`store`),
  CONSTRAINT `FK_9bql56vlb1l63h6xtkw6nfwal` FOREIGN KEY (`store`) REFERENCES `store` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
