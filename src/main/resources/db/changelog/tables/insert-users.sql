--liquibase formatted sql
--changeset Jayshree:insert-into-users
--preconditions onFail:HALT onError:HALT
INSERT INTO users 
(first_name,last_name,email,password,address,phone_number,birth_date,created_at)
VALUES ('Jhon','Doe','jhondoe@gmail.com','8d969eef6ecad3c29a3a629280e686cfc3f5d5a86aff3ca122c923adc6c92',
'B-206,Apexa Apartment','9825736220','2002-08-12','2023-07-17');

INSERT INTO users 
(first_name,last_name,email,password,address,phone_number,birth_date,created_at)
VALUES ('Jessica','Smith','jessica@gmail.com','8d969eef6ecad3c29a3a629280e686cfc3f5d5a86aff3ca122c923adc6c92',
'B-206,Apexa Apartment','6578491234','2002-10-22','2023-07-17');