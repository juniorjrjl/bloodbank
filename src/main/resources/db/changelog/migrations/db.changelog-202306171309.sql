--liquibase formatted sql
--changeset junior:202306170057
--comment: addresses table create

CREATE TABLE ADDRESSES (
    id bigint not null auto_increment,
    street varchar(255) not null,
    number varchar(50) not null,
    district varchar(255) not null,
    cep char(8) not null,
    city_id bigint not null,
    candidate_id bigint not null,
    primary key(id),
    constraint fk_addresses_cities foreign key (city_id) references CITIES(id),
    constraint fk_addresses_candidates foreign key (candidate_id) references CANDIDATES(id)
)engine=InnoDB default charset=utf8;

--rollback DROP TABLE ADDRESSES;
