ALTER TABLE `mygarage`.`user_information` 
ADD COLUMN `syw_id` BIGINT(20) NOT NULL AFTER `store`,
ADD COLUMN `family_id` BIGINT(20) NULL AFTER `syw_id`;