ALTER TABLE `postmansdb`.`forum` 
CHANGE COLUMN `date` `date` TIMESTAMP NOT NULL ;

ALTER TABLE `postmansdb`.`forum` 
CHANGE COLUMN `date` `date` TIMESTAMP NULL DEFAULT NULL ;
