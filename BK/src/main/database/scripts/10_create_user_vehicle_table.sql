CREATE TABLE `mygarage`.`user_vehicle` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NOT NULL,
  `vehicle_id` BIGINT(20) NOT NULL,
  `isconfirmed` BIT NOT NULL DEFAULT false,
  PRIMARY KEY (`id`),
  INDEX `FK_user_information_id_idx` (`user_id` ASC),
  INDEX `FK_vehicle_id_idx` (`vehicle_id` ASC),
  CONSTRAINT `FK_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `mygarage`.`user_information` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_vehicle_id`
    FOREIGN KEY (`vehicle_id`)
    REFERENCES `mygarage`.`vehicle` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);