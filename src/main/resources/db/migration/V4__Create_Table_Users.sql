


CREATE TABLE IF NOT EXISTS `users` (
                        `user_id` bigint NOT NULL AUTO_INCREMENT,
                        `email` varchar(255) NOT NULL,
                        `name` varchar(180) NOT NULL,
                        `password` varchar(255) NOT NULL,
                        `account_non_expired` bit(1) DEFAULT NULL,
                        `account_non_locked` bit(1) DEFAULT NULL,
                        `credentials_non_expired` bit(1) DEFAULT NULL,
                        `enabled` bit(1) DEFAULT NULL,
                        PRIMARY KEY (`user_id`),
                        UNIQUE KEY `uk_email` (`email`)
)ENGINE=InnoDB;
