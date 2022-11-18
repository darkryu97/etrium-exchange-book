CREATE TABLE IF NOT EXISTS `posts` (
    `book_interests` varchar(255) DEFAULT NULL,
    `date` varchar(255) NOT NULL,
    `post_count` int NOT NULL,
    `id_user` bigint NOT NULL,
    `id_book` bigint NOT NULL,
    PRIMARY KEY (`id_book`,`id_user`),
    KEY `fk_user_id` (`id_user`),
    KEY `fk_book_id` (`id_book`),
    CONSTRAINT `fk_user_id` FOREIGN KEY (`id_user`) REFERENCES `users` (`user_id`),
    CONSTRAINT `fk_book_id` FOREIGN KEY (`id_book`) REFERENCES `book` (`book_id`)
    );
