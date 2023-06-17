--liquibase formatted sql
--changeset junior:202306170057
--comment: contacts table create

CREATE TABLE CONTACTS (
    id bigint not null auto_increment,
    type varchar(150) not null,
    value varchar(150) not null,
    candidate_id bigint not null,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    constraint fk_contacts_candidates foreign key (candidate_id) references CANDIDATES(id),
    primary key(id)
)engine=InnoDB default charset=utf8;

--rollback DROP TABLE CONTACTS;
