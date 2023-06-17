--liquibase formatted sql
--changeset junior:202306170057
--comment: states table create

CREATE TABLE STATES (
    id bigint not null auto_increment,
    name varchar(150) not null,
    primary key(id)
)engine=InnoDB default charset=utf8;

--rollback DROP TABLE STATES;
