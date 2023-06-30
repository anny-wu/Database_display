DROP DATABASE IF EXISTS DB;
CREATE DATABASE DB;
USE DB;
DROP TABLE IF EXISTS USER;
CREATE TABLE USER(
                        id INTEGER PRIMARY KEY AUTO_INCREMENT,
                        username varchar(24) NOT NULL,
                        age INTEGER NOT NULL,
                        update_time timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP);
DROP TRIGGER IF EXISTS updateTrigger;
CREATE TRIGGER updateTrigger
    BEFORE
        UPDATE ON USER FOR EACH ROW
BEGIN
    SET NEW.update_time=NOW();
END;
