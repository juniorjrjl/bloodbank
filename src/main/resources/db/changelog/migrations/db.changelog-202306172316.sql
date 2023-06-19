--liquibase formatted sql
--changeset junior:202306170057
--comment: addresses table create

INSERT INTO BLOOD_TYPES
(id, name)
 values
(1, 'A+'),
(2, 'A-'),
(3, 'B+'),
(4, 'B-'),
(5, 'AB+'),
(6, 'AB-'),
(7, 'O+'),
(8, 'O-');
--rollback DELETE FROM BLOOD_TYPES;
