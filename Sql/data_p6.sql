CREATE DATABASE  IF NOT EXISTS ` paymybuddy_db` 
USE `pay_my_buddy`;

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int NOT NULL,
  `create_at` datetime(6) NOT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `balance` decimal(19,2) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_account` (`user_id`),
  CONSTRAINT `FK_account` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `friend`;

CREATE TABLE `friend` (
  `id` int NOT NULL,
  `create_at` datetime(6) NOT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `friend_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1` (`friend_id`),
  KEY `FK2` (`user_id`),
  CONSTRAINT `FK1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK2` FOREIGN KEY (`friend_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `persistent_logins`;

CREATE TABLE `persistent_logins` (
  `series` varchar(64) NOT NULL,
  `username` varchar(64) NOT NULL,
  `last_used` datetime(6) NOT NULL,
  `token` varchar(64) NOT NULL,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `transaction`;

CREATE TABLE `transaction` (
  `id` int NOT NULL,
  `create_at` datetime(6) NOT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `charge` decimal(19,2) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `transacted` char(1) DEFAULT NULL,
  `from_account_id` int DEFAULT NULL,
  `to_account_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1` (`from_account_id`),
  KEY `FK2` (`to_account_id`),
  CONSTRAINT `FK1` FOREIGN KEY (`to_account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FK2` FOREIGN KEY (`from_account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL,
  `create_at` datetime(6) NOT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;