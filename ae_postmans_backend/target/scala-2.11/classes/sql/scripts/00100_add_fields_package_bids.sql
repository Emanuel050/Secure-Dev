ALTER TABLE `postmansdb`.`package_bids` 
DROP FOREIGN KEY `fk_package_bids_user`;
ALTER TABLE `postmansdb`.`package_bids` 
CHANGE COLUMN `fk_postman_id` `fk_postman_id` INT(11) NOT NULL ,
ADD COLUMN `price` DOUBLE NOT NULL AFTER `chosen`,
ADD COLUMN `shipping_time` VARCHAR(512) NOT NULL AFTER `price`;
ALTER TABLE `postmansdb`.`package_bids` 
ADD CONSTRAINT `fk_package_bids_user`
  FOREIGN KEY (`fk_postman_id`)
  REFERENCES `postmansdb`.`user` (`id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;
