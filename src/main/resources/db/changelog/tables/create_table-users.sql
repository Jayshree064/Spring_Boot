--liquibase formatted sql
--changeset Jayshree:create-table-users
--precondition-sql-check expectedResult:0 SELECT count(*)FROM information_schema.tables where table_name = 'users';
--preconditions onFail:HALT onError:HALT
CREATE TABLE users
(
    user_id INT NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(255),
    password VARCHAR(255),
    address VARCHAR(255),
    phone_number BIGINT,
    birth_date date,
    created_at date,
    updated_at date,
    deleted_at date,
    PRIMARY KEY (user_id)
);
