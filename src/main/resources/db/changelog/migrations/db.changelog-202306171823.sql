--liquibase formatted sql
--changeset junior:202306170057
--comment: rename column name to abbreviation in state

ALTER TABLE STATES RENAME COLUMN name TO abbreviation;

--rollback ALTER TABLE STATES RENAME COLUMN abbreviation TO name;
