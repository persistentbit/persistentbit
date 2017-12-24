-->>all
CREATE SCHEMA MYSCHEMA;

CREATE TABLE MYSCHEMA.persons (
  id              BIGINT       NOT NULL PRIMARY KEY,
  first_name      VARCHAR(256) NOT NULL,
  middle_name     VARCHAR(256) NULL,
  last_name       VARCHAR(256) NOT NULL,
  home_street     VARCHAR(256) NOT NULL,
  home_PostalCode VARCHAR(256) NOT NULL,
  home_City       VARCHAR(256) NOT NULL,
  created         TIMESTAMP DEFAULT current_timestamp
);

CREATE TABLE MYSCHEMA.tags (
  id      BIGSERIAL   NOT NULL PRIMARY KEY,
  name    VARCHAR(80) NOT NULL,
  created TIMESTAMP   NOT NULL DEFAULT current_timestamp
);

INSERT INTO MYSCHEMA.persons (id, first_name, last_name, home_street, home_PostalCode, home_City)
VALUES (1, 'Peter', 'Muys', 'Snoekstraat 10', '9000', 'Gent');
INSERT INTO MYSCHEMA.persons (id, first_name, last_name, home_street, home_PostalCode, home_City)
VALUES (2, 'Els', 'Van Oost', 'Snoekstraat 10', '9000', 'Gent');
INSERT INTO MYSCHEMA.persons (id, first_name, last_name, home_street, home_PostalCode, home_City)
VALUES (3, 'Katrien', 'Muys', 'BredeneStraat 1', '8150', 'Bredene');

-->>