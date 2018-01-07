-->>init
DROP SCHEMA IF EXISTS pbtest;
CREATE SCHEMA pbtest;

-->>create
USE pbtest;

CREATE TABLE pbtest.mysql_all_types (
  a_bit              BIT     NOT NULL,
  a_tinyint          TINYINT NOT NULL,
  a_tinyint_unsinged TINYINT unsigned NOT NULL,
  a_bool             BOOL    NOT NULL
);

-- Custom function
DELIMITER !!
CREATE FUNCTION pbtest.inc(value INTEGER)
  RETURNS INTEGER
  DETERMINISTIC
BEGIN
RETURN VALUE + 1;
END
!!
DELIMITER;

-->>
