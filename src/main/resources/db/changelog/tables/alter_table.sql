--liquibase formatted sql
--changeset Jayshree:alter-table-user
--preconditions onFail:HALT onError:HALT
create sequence user_id_seq
   owned by users.user_id;

alter table users
   alter column user_id set default nextval('user_id_seq');