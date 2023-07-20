--liquibase formatted sql
--changeset Jayshree:create-table-blockUser
--precondition-sql-check expectedResult:0 SELECT count(*)FROM information_schema.tables where table_name = 'block_user'; 
--preconditions onFail:HALT onError:HALT
CREATE TABLE block_user
(
	block_user_id INT NOT NULL,
	blocker_user_id INT,
	blocked_user_id INT,
	created_at DATE,
    updated_at DATE,
    deleted_at DATE,
    PRIMARY KEY (block_user_id),
    CONSTRAINT "FK_blocker_user_id" FOREIGN KEY (blocker_user_id)
    	REFERENCES users (user_id),
    CONSTRAINT "FK_blocked_user_id" FOREIGN KEY (blocked_user_id)
    	REFERENCES users(user_id)
);