ALTER TABLE `postmansdb`.`package` 
ADD COLUMN `from_lng` DOUBLE NOT NULL AFTER `recipient_name`,
ADD COLUMN `from_lat` DOUBLE NOT NULL AFTER `from_lng`,
ADD COLUMN `to_lng` DOUBLE NOT NULL AFTER `from_lat`,
ADD COLUMN `to_lat` DOUBLE NOT NULL AFTER `to_lng`;
