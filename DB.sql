CREATE SCHEMA IF NOT EXISTS `crafts` ;
USE `crafts` ;

-- -----------------------------------------------------
-- Table `crafts`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crafts`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `phone` VARCHAR(45) NOT NULL UNIQUE,
  `password` VARCHAR(255) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `state_delete` BOOLEAN NOT NULL,
  `date_create` DATETIME NOT NULL,
  `date_update` DATETIME NOT NULL
);

-- -----------------------------------------------------
-- Table `crafts`.`regions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crafts`.`regions` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `code` VARCHAR(3) NULL,
  `name` VARCHAR(100) NOT NULL
);

-- -----------------------------------------------------
-- Table `crafts`.`cities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crafts`.`cities` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `regions_id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  FOREIGN KEY (`regions_id`) REFERENCES `crafts`.`regions` (`id`) 
);

-- -----------------------------------------------------
-- Table `crafts`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crafts`.`categories` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  `state_delete` BOOLEAN NOT NULL,
  `date_create` DATETIME NOT NULL,
  `date_update` DATETIME NOT NULL
);

-- -----------------------------------------------------
-- Table `crafts`.`posts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crafts`.`posts` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `title` VARCHAR(100) NOT NULL,
  `summary` TEXT NULL,
  `description` TEXT NOT NULL,
  `image` VARCHAR(100) NULL,
  `categories_id` INT NULL,
  `cities_id` INT NULL,
  `users_id` INT NOT NULL,
  `state_delete` BOOLEAN NOT NULL,
  `date_create` DATETIME NOT NULL,
  `date_update` DATETIME NOT NULL,
  FOREIGN KEY (`categories_id`) REFERENCES `crafts`.`categories` (`id`),
  FOREIGN KEY (`cities_id`) REFERENCES `crafts`.`cities` (`id`),
  FOREIGN KEY (`users_id`) REFERENCES `crafts`.`users` (`id`)
);
  
-- -----------------------------------------------------
-- Table `crafts`.`comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crafts`.`comments` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `text` TEXT NOT NULL,
  `posts_id` INT NOT NULL,
  `users_id` INT NOT NULL,
  `state_delete` BOOLEAN NOT NULL,
  `date_create` DATETIME NOT NULL,
  `date_update` DATETIME NOT NULL,
  FOREIGN KEY (`posts_id`) REFERENCES `crafts`.`posts` (`id`),
  FOREIGN KEY (`users_id`) REFERENCES `crafts`.`users` (`id`)
); 