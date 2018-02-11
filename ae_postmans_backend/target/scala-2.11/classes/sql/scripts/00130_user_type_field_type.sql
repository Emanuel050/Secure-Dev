ALTER TABLE `postmansdb`.`user` 
ADD COLUMN `type` VARCHAR(45) NULL DEFAULT NULL AFTER `authToken`;
