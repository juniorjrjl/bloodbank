--liquibase formatted sql
--changeset junior:202306170057
--comment: blood_types table create

CREATE TABLE BLOOD_TYPES (
    id bigint not null auto_increment,
    name varchar(3) not null,
    primary key(id)
)engine=InnoDB default charset=utf8;

--rollback DROP TABLE BLOOD_TYPES;
