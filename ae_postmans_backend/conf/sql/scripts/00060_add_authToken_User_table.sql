ALTER TABLE `postmansdb`.`user` 
ADD COLUMN `authToken` VARCHAR(60) NULL AFTER `gender`,
ADD UNIQUE INDEX `authToken_uq` (`authToken` ASC);
