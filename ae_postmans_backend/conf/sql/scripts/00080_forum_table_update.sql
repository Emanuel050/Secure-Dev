ALTER TABLE `postmansdb`.`forum` 
ADD INDEX `fk_parent_id_idx` (`fk_parent_id` ASC);
ALTER TABLE `postmansdb`.`forum` 
ADD CONSTRAINT `fk_parent_id`
  FOREIGN KEY (`fk_parent_id`)
  REFERENCES `postmansdb`.`forum` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
