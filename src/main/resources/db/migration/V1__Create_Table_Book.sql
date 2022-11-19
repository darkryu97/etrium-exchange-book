CREATE TABLE IF NOT EXISTS book (
                        book_id bigserial NOT NULL,
                        author varchar(180) NOT NULL,
                        imgurl varchar(255) NOT NULL,
                        publisher varchar(180) NOT NULL,
                        resume varchar(1000) DEFAULT NULL,
                        title varchar(250) NOT NULL,
                        PRIMARY KEY (book_id)
);
