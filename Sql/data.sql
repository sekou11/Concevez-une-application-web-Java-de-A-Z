LOCK TABLES `account` WRITE;

INSERT INTO `paymybuddy_db`.`account` ( `create_at`, `update_at`, `balance`, `user_id`) 
VALUES 
('2022-02-27 13:31:31.195000', '2022-02-27 20:43:47.782000' ,'159.90' ,'3'),
('2022-02-27 13:33:31.086000', '2022-02-27 13:33:31.086000' ,'100.90' ,'4'),
('2022-02-27 13:34:09.259000', '2022-02-27 20:39:51.100000' ,'89.90' ,'5'),
('2022-02-27 20:42:50.306000', '2022-02-27 13:43:47.781000' ,'49.75' ,'6');
UNLOCK TABLES;


LOCK TABLES `friends` WRITE;

INSERT INTO `paymybuddy_db`.`friend` ( `create_at`, `update_at`, `friend_id`, `user_id`) 
 VALUES 
('2022-02-27 12:09:31.084000','2022-02-27 13:36:21.760000',3,5),
('2022-02-27 12:10:46.054000','2022-02-27 13:36:46.582000',4,5),
('2022-02-27 12:09:31.084000','2022-02-27 13:40:45.351000',3,3),
('2022-02-27 12:10:46.054000','2022-02-27 13:41:20.622000',4,3),
('2022-02-27 12:10:46.054000','2022-02-27 20:43:27.748000',3,6);

UNLOCK TABLES;


LOCK TABLES `hibernate_sequence` WRITE;
INSERT INTO `hibernate_sequence` VALUES (1);
UNLOCK TABLES;

LOCK TABLES  `persistent_logins` WRITE;

INSERT INTO `paymybuddy_db`.`persistent_logins` ( `series`, `usernam`, `last_used`, `token`)  
VALUES 
('0xhRZqSAAbxoJxzLC/mcpg==','user4@email.com','2022-02-28 09:55:13.821000','l0YIW9EY+R1uS5bOscdYxw=='),
(' YOutWHnV7jCGxJ1uz+AgXg==','user1@email.com','2022-02-27 20:38:56.255000','qd/W87/zhKnIqJ/vgyPRQA==');

UNLOCK TABLES;


LOCK TABLES transaction` WRITE;


INSERT INTO `paymybuddy_db`.`transaction` ( `create_at`, `update_at`, `amount`, `charge`, `description`, `transacted`, `from_account_id`, `to_account_id`)
 VALUES 

('2022-02-27 13:37:16.824000' ,'2022-02-27 13:37:16.824000' ,'20.00' ,' 0.10' , 'virement a user1' 'y' ,'4' ,'2' ),

('2022-02-27 13:39:50.950000' ,'2022-02-27 13:39:50.950000' ,'10.00' ,' 0.05' , 'virement a user3' 'y' ,'2' ,'4' ),


('2022-02-27 13:41:03.055000' ,'2022-02-27 13:41:03.055000' ,'10.00' ,' 0.05' , 'virement for me user1' 'y' ,'2' ,'2' ),

('2022-02-27 20:43:47.751000' ,'2022-02-27 20:43:47.751000' ,'50.00' ,' 0.25' , 'virement a user1' 'y' ,'5' ,'2' );
UNLOCK TABLES;


LOCK TABLES user` WRITE;
INSERT INTO `paymybuddy_db`.`user` (`user_name`, `email`, `password`, `create_at`, `update_at`)
 VALUES 
('user1',user1@email.com , '$2a$10$U7YD9IehZ6GF/oWLo878TeXs4JZXNrnE6IkoT.RGl.3V/jMOkhv9q' ,'2022-02-27 13:27:39.441000' ,'2022-02-27 13:27:39.441000'),


('user2',user2@email.com , '$2a$10$zqE419qEqG.JyDg0e/ZygOE1H/EZOUXEYcskmF2P/NxUNrJE6rDBS' ,'2022-02-27 13:27:39.441000' ,'2022-02-27 13:28:03.678000'),



('user3',user3@email.com , '$2a$10$ElgaOmnW1J3Hu4yiP2YKHuV.dVWwCNgPhK5zD7Bg.Z/kH1qFJ3O.e' ,'2022-02-27 13:28:20.329000' ,'2022-02-27 13:28:20.329000'),


('user4',user4@email.com , '$2a$10$pND608wVxgSR2QdtAlEMeuaHSdV3ipH9L/k1n5CZLor.ScDvQLWPu' ,'2022-02-27 20:41:52.449000' ,'2022-02-27 20:41:52.449000');
