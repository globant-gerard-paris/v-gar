CREATE TABLE `mygarage`.`vehicle` (
  `id`  BIGINT(20) NOT NULL AUTO_INCREMENT,
  `familyId` BIGINT(20) NULL,
  `tangibleId` BIGINT(20) NULL,
  `make` VARCHAR(45) NOT NULL,
  `model` VARCHAR(45) NOT NULL,
  `year` INT NOT NULL,
  `color` VARCHAR(45) NULL,
  `mileage` INT NULL DEFAULT 0,
  PRIMARY KEY (`id`));
