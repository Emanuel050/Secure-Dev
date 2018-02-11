ALTER TABLE `postmansdb`.`package` 
DROP FOREIGN KEY `fk_package_user`;
ALTER TABLE `postmansdb`.`package` 
ADD CONSTRAINT `fk_package_customer`
  FOREIGN KEY (`fk_customer_id`)
  REFERENCES `postmansdb`.`user` (`id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;
