--liquibase formatted sql
--changeset Jayshree:insert-into-block-user
--preconditions onFail:HALT onError:HALT
INSERT INTO block_user 
(blocker_user_id,blocked_user_id,created_at)
VALUES ('2','3','2023-07-17');