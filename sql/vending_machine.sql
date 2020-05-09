DROP DATABASE IF EXISTS vending_machine;

CREATE DATABASE vending_machine;

USE vending_machine;

CREATE TABLE IF NOT EXISTS `items` (
 `item_id` int NOT NULL AUTO_INCREMENT,
 `name` varchar(50) NOT NULL,
 `price` decimal(4,2) NOT NULL,
 `quantity` int NOT NULL,
 PRIMARY KEY (`item_id`)
);