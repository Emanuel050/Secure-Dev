ALTER TABLE `postmansdb`.`package_bids` 
ADD UNIQUE INDEX `package_postman` (`fk_package_id` ASC, `fk_postman_id` ASC);