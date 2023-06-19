--liquibase formatted sql
--changeset junior:202306170057
--comment: addresses table create

ALTER TABLE STATES RENAME COLUMN name TO abbreviation;

--rollback ALTER TABLE STATES RENAME COLUMN abbreviation TO name;
