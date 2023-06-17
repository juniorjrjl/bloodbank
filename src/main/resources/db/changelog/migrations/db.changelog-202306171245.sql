--liquibase formatted sql
--changeset junior:202306170057
--comment: blood_compatibility table create

CREATE TABLE BLOOD_COMPATIBILITY (
    giver bigint not null,
    receiver bigint not null,
    constraint fk_giver_blood_type foreign key (giver) references BLOOD_TYPES(id),
    constraint fk_receiver_blood_type foreign key (receiver) references BLOOD_TYPES(id),
    primary key(giver, receiver)
)engine=InnoDB default charset=utf8;

--rollback DROP TABLE BLOOD_COMPATIBILITY;
