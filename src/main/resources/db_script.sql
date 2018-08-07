create database coffee_house;
use coffee_house;

CREATE TABLE `coffee_dtls` (
  `coffee_id` int(20) NOT NULL AUTO_INCREMENT,
  `coffee_name` varchar(20) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `quantity` int(5) DEFAULT NULL,
  `price_per_cup` double(40,2) DEFAULT NULL,
  PRIMARY KEY (`coffee_id`),
  UNIQUE KEY `coffe_name` (`coffee_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

CREATE TABLE `customer_dtls` (
  `cust_id` int(20) NOT NULL AUTO_INCREMENT,
  `cust_name` varchar(20) DEFAULT NULL,
  `phone_number` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`cust_id`),
  UNIQUE KEY `phone_number` (`phone_number`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

CREATE TABLE `order_dtls` (
  `order_id` int(20) NOT NULL AUTO_INCREMENT,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `coffee_name` varchar(20) DEFAULT NULL,
  `cust_name` varchar(20) DEFAULT NULL,
  `phone_number` varchar(10) DEFAULT NULL,
  `quantity` int(5) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;


