CREATE TABLE contacts
(
    id bigint NOT NULL AUTO_INCREMENT,
    first_name varchar(100) NOT NULL,
    last_name varchar(100) DEFAULT NULL,
    phone varchar(100) DEFAULT NULL,
    email varchar(100) DEFAULT NULL,
    PRIMARY KEY (id)
);