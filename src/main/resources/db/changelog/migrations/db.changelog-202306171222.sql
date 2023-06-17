--liquibase formatted sql
--changeset junior:202306170057
--comment: cities table create

CREATE TABLE CITIES (
    id bigint not null auto_increment,
    name varchar(150) not null,
    state_id bigint not null,
    primary key(id),
    constraint fk_cities_states foreign key (state_id) references STATES(id)
)engine=InnoDB default charset=utf8;

--rollback DROP TABLE CITIES;
