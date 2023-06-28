USE example;
DROP TABLE IF EXISTS TESTNEW;
CREATE TABLE TESTNEW(
                        id INTEGER PRIMARY KEY AUTO_INCREMENT,
                        username varchar(24) NOT NULL,
                        age INTEGER NOT NULL,
                        update_time timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP);
DROP TRIGGER IF EXISTS updateTrigger;
CREATE TRIGGER updateTrigger
    BEFORE
        UPDATE ON testnew FOR EACH ROW
BEGIN
    SET NEW.update_time=NOW();
END;
INSERT INTO TESTNEW (username,age) VALUES ('Tom', 18);
INSERT INTO TESTNEW (username,age) VALUES ('Jessica', 36);
INSERT INTO TESTNEW (username,age) VALUES ('Jack', 20);
INSERT INTO TESTNEW (username,age) VALUES ('Catherine', 10);
UPDATE testnew SET username = 'Austin' where id = 1
