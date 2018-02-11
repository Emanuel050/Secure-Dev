ALTER TABLE `postmansdb`.`package` 
CHANGE COLUMN `from_pincode` `from_postal_code` VARCHAR(10) NOT NULL ,
CHANGE COLUMN `to_pincode` `to_postal_code` VARCHAR(10) NOT NULL ,
CHANGE COLUMN `status` `status` ENUM('NEW', 'WAITING_FOR_PICKUP', 'ON_THE_WAY', 'DELIVERED') NOT NULL DEFAULT 'NEW' ,
ADD COLUMN `fk_postman_id` INT NULL AFTER `fk_customer_id`,
ADD COLUMN `to_country` VARCHAR(45) NOT NULL AFTER `status`,
ADD COLUMN `from_country` VARCHAR(45) NOT NULL AFTER `to_country`,
ADD COLUMN `from_street_number` INT NOT NULL AFTER `from_country`,
ADD COLUMN `to_street_number` INT NOT NULL AFTER `from_street_number`,
ADD COLUMN `created` TIMESTAMP NOT NULL  DEFAULT CURRENT_TIMESTAMP AFTER `to_street_number`,
ADD COLUMN `updated` TIMESTAMP NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP AFTER `created`,
ADD COLUMN `sender_name` VARCHAR(45) NULL AFTER `updated`,
ADD COLUMN `recipient_name` VARCHAR(45) NULL AFTER `sender_name`;

ADD INDEX `fk_package_postman_idx` (`fk_postman_id` ASC);
ALTER TABLE `postmansdb`.`package` 
ADD CONSTRAINT `fk_package_postman`
  FOREIGN KEY (`fk_postman_id`)
  REFERENCES `postmansdb`.`user` (`id`)
  ON DELETE SET NULL
  ON UPDATE NO ACTION;