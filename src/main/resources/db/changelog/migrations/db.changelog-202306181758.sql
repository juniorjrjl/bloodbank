--liquibase formatted sql
--changeset junior:202306170057
--comment: store bloods compatibilities

INSERT INTO BLOOD_COMPATIBILITY
(receiver, giver)
 values
--A+
(1, 1), --A+
(1, 2), --A-
(1, 7), --O+
(1, 8), --O-
--A-
(2, 2), --A-
(2, 8), --O-
--B+
(3, 3), --B+
(3, 4), --B-
(3, 7), --O+
(3, 8), --O-
--B-
(4, 4), --B-
(4, 8), --O-
--AB+
(5, 1), --A-
(5, 2), --A-
(5, 3), --B+
(5, 4), --B-
(5, 5), --AB+
(5, 6), --AB-
(5, 7), --O+
(5, 8), --O-
--AB -
(6, 2), --A-
(6, 4), --B-
(6, 6), --AB-
(6, 8), --O-
--O+
(7, 7), --O+
(7, 8), --O-
--O-
(8, 8) --O-
--rollback DELETE FROM BLOOD_COMPATIBILITY;
