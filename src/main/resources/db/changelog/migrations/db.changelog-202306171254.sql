--liquibase formatted sql
--changeset junior:202306170057
--comment: candidates table create

CREATE TABLE CANDIDATES (
    id bigint not null auto_increment,
    name varchar(150) not null,
    rg char(9) not null,
    cpf char(11) not null,
    birthdate date not null,
    sex varchar(6) not null,
    height decimal(3,2) not null,
    weight decimal(6,3),
    father varchar(150) not null,
    mother varchar(150) not null,
    blood_type_id bigint not null,
    constraint fk_candidates_blood_types foreign key (blood_type_id) references BLOOD_TYPES(id),
    primary key(id)
)engine=InnoDB default charset=utf8;

--rollback DROP TABLE CANDIDATES;
