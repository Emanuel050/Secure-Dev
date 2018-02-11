CREATE TABLE `postmansdb`.`forum` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fk_customer_id` INT NOT NULL,
  `date` DATE NOT NULL,
  `msg` VARCHAR(500) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `fk_parent_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_customer_id_idx` (`fk_customer_id` ASC),
  CONSTRAINT `fk_customer_id`
    FOREIGN KEY (`fk_customer_id`)
    REFERENCES `postmansdb`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);
