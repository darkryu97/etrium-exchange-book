


CREATE TABLE IF NOT EXISTS users (
                        user_id bigserial NOT NULL,
                        email varchar(255) NOT NULL,
                        name varchar(180) NOT NULL,
                        password varchar(255) NOT NULL,
                        account_non_expired boolean DEFAULT true,
                        account_non_locked boolean DEFAULT true,
                        credentials_non_expired boolean DEFAULT true,
                        enabled boolean DEFAULT true,
                        PRIMARY KEY (user_id),
                        UNIQUE (email)
);
