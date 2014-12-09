ALTER TABLE `mygarage`.`family_vehicle` ADD COLUMN  `last_mileage_update` DATETIME NULL DEFAULT NULL;
ALTER TABLE `mygarage`.`record` CHANGE COLUMN `mileage` `mileage` INT(11) NULL ;