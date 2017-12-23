-->>all
CREATE SCHEMA MYSCHEMA;

CREATE TABLE MYSCHEMA.persons (
  id              BIGINT       NOT NULL PRIMARY KEY,
  first_name      VARCHAR(256) NOT NULL,
  middle_name     VARCHAR(256) NULL,
  last_name       VARCHAR(256) NOT NULL,
  home_street     VARCHAR(256) NOT NULL,
  home_PostalCode VARCHAR(256) NOT NULL,
  home_City       VARCHAR(256) NOT NULL
);

INSERT INTO MYSCHEMA.persons (id, first_name, last_name, home_street, home_PostalCode, home_City)
VALUES (1, 'Peter', 'Muys', 'Snoekstraat 10', '9000', 'Gent');
-->>