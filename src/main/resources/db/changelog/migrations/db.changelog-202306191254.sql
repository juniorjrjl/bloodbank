--liquibase formatted sql
--changeset junior:202306170057
--comment: rename BLOOD_COMPATIBILITY fk fields

ALTER TABLE BLOOD_COMPATIBILITY
    RENAME COLUMN giver TO giver_id,
    RENAME COLUMN receiver TO receiver_id;

--rollback ALTER TABLE BLOOD_COMPATIBILITY RENAME COLUMN giver_id TO giver, RENAME COLUMN receiver_id TO receiver;
