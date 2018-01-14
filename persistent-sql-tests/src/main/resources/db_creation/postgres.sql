-->>init
DROP SCHEMA IF EXISTS pbtest CASCADE;
CREATE SCHEMA pbtest;

-->>create
SET search_path TO pbtest, public;

-- Window functions test
CREATE TABLE product_groups (
  group_id   SERIAL PRIMARY KEY,
  group_name VARCHAR(255) NOT NULL
);

CREATE TABLE products (
  product_id   SERIAL PRIMARY KEY,
  product_name VARCHAR(255)     NOT NULL,
  price        DOUBLE PRECISION NOT NULL,
  group_id     INT              NOT NULL,
  FOREIGN KEY (group_id) REFERENCES product_groups (group_id)
);

INSERT INTO product_groups (group_name)
VALUES
  ('Smartphone'),
  ('Laptop'),
  ('Tablet');

INSERT INTO products (product_name, group_id, price)
VALUES
  ('Microsoft Lumia', 1, 200),
  ('HTC One', 1, 400),
  ('Nexus', 1, 500),
  ('iPhone', 1, 900),
  ('HP Elite', 2, 1200),
  ('Lenovo Thinkpad', 2, 700),
  ('Sony VAIO', 2, 700),
  ('Dell Vostro', 2, 800),
  ('iPad', 3, 700),
  ('Kindle Fire', 3, 150),
  ('Samsung Galaxy Tab', 3, 200);

-- Custom Function
DELIMITER !!
CREATE FUNCTION pbtest_inc(value INTEGER)
  RETURNS INTEGER AS $$
BEGIN
  RETURN value + 1;
END; $$
LANGUAGE plpgsql;
!!

DELIMITER ;
DELIMITER !!
CREATE FUNCTION pbtest_inct(value INTEGER)
  RETURNS INTEGER AS $$
BEGIN
  RETURN value + 2;
END; $$
LANGUAGE plpgsql;
!!

DELIMITER ;
-- People with history

--- ISO-639-1 2 char language code
CREATE DOMAIN pbtest.lang_iso_code AS VARCHAR(2)
  CHECK ( value SIMILAR TO '[a-z][a-z]');

CREATE DOMAIN pbtest.gender_code AS VARCHAR(10);
CREATE DOMAIN pbtest.salutation_code AS VARCHAR(10);

CREATE TABLE pbtest.genders (
  gender_code GENDER_CODE NOT NULL,
  PRIMARY KEY (gender_code),

  description VARCHAR     NOT NULL
);
CREATE TABLE pbtest.translations_gender (
  gender_code   GENDER_CODE   NOT NULL REFERENCES genders (gender_code)
  ON DELETE CASCADE ON UPDATE CASCADE,
  language_code LANG_ISO_CODE NOT NULL,
  PRIMARY KEY (gender_code, language_code),

  description   VARCHAR       NOT NULL
);
CREATE TABLE pbtest.salutations (
  salutation_code SALUTATION_CODE NOT NULL,
  PRIMARY KEY (salutation_code),

  description     VARCHAR         NOT NULL
);

CREATE TABLE pbtest.translations_salutation (
  salutation_code SALUTATION_CODE NOT NULL REFERENCES salutations (salutation_code)
  ON DELETE CASCADE ON UPDATE CASCADE,
  language_code   LANG_ISO_CODE   NOT NULL,
  PRIMARY KEY (salutation_code, language_code),

  description     VARCHAR         NOT NULL
);

-- country code: ISO 3166-1
CREATE DOMAIN pbtest.country_iso_code AS VARCHAR(2)
  CHECK (value SIMILAR TO '[A-Z][A-Z]');

CREATE TABLE pbtest.translations_country (
  country_code  COUNTRY_ISO_CODE NOT NULL,
  language_code LANG_ISO_CODE    NOT NULL,
  name          VARCHAR          NOT NULL,
  PRIMARY KEY (country_code, language_code)
);

CREATE DOMAIN pbtest.address_relation_code AS VARCHAR(10);

CREATE TABLE pbtest.address_relations (
  address_relation_code ADDRESS_RELATION_CODE NOT NULL,
  PRIMARY KEY (address_relation_code),
  description           VARCHAR               NOT NULL
);

CREATE TABLE pbtest.addresses (
  address_id    BIGSERIAL        NOT NULL PRIMARY KEY,
  street_line_1 VARCHAR          NOT NULL,
  street_line_2 VARCHAR          NULL,
  postal_code   VARCHAR          NOT NULL,
  city_name     VARCHAR          NOT NULL,
  country_code  COUNTRY_ISO_CODE NOT NULL,
  district      VARCHAR          NULL
);

CREATE TABLE people (
  person_id       BIGSERIAL       NOT NULL,
  PRIMARY KEY (person_id),
  salutation_code SALUTATION_CODE NOT NULL REFERENCES salutations (salutation_code) ON UPDATE CASCADE,
  name_first      VARCHAR         NOT NULL,
  name_middle     VARCHAR         NULL,
  name_last       VARCHAR         NOT NULL,
  gender_code     GENDER_CODE     NOT NULL REFERENCES genders (gender_code) ON UPDATE CASCADE,
  birth_day       DATE            NULL
);

CREATE TABLE people_history (
  person_id       BIGINT                              NOT NULL REFERENCES PEOPLE (person_id) ON DELETE CASCADE,
  salutation_code SALUTATION_CODE                     NOT NULL REFERENCES salutations (salutation_code) ON UPDATE CASCADE,
  name_first      VARCHAR                             NOT NULL,
  name_middle     VARCHAR                             NULL,
  name_last       VARCHAR                             NOT NULL,
  gender_code     GENDER_CODE                         NOT NULL REFERENCES genders (gender_code) ON UPDATE CASCADE,
  birth_day       DATE                                NULL,

  start_time      TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  end_time        TIMESTAMP,
  CONSTRAINT people_base_start_before_end CHECK (start_time < end_time OR end_time IS NULL),
  PRIMARY KEY (person_id, start_time)
);

DELIMITER !!
CREATE OR REPLACE FUNCTION trigger_on_people()
  RETURNS TRIGGER LANGUAGE plpgsql
AS $function$
DECLARE
  pointInTime TIMESTAMP;
BEGIN
  pointInTime = current_timestamp;
  UPDATE people_history
  SET end_time = pointInTime
  WHERE end_time IS NULL AND person_id = new.person_id;
  IF tg_op = 'DELETE'
  THEN
    INSERT INTO people_history
      SELECT
        old.*,
        pointInTime,
        NULL;
    RETURN old;
  ELSE
    INSERT INTO people_history
      SELECT
        new.*,
        pointInTime,
        NULL;
    RETURN new;
  END IF;
END; $function$!!
DELIMITER;

CREATE TRIGGER history_trigger_on_people
  AFTER INSERT OR UPDATE OR DELETE
  ON people
  FOR EACH ROW
EXECUTE PROCEDURE trigger_on_people();


CREATE TABLE people_addresses (
  person_id             BIGSERIAL             NOT NULL REFERENCES people (person_id) ON DELETE CASCADE,
  address_relation_code ADDRESS_RELATION_CODE NOT NULL REFERENCES address_relations (address_relation_code) ON UPDATE CASCADE,
  address_id            BIGINT                NOT NULL REFERENCES addresses,
  PRIMARY KEY (person_id, address_relation_code)
);


CREATE TABLE people_addresses_history (
  person_id             BIGINT                              NOT NULL REFERENCES people (person_id) ON DELETE CASCADE,
  address_relation_code ADDRESS_RELATION_CODE               NOT NULL REFERENCES address_relations (address_relation_code) ON UPDATE CASCADE,
  address_id            BIGINT                              NOT NULL REFERENCES addresses,
  start_time            TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  end_time              TIMESTAMP,
  PRIMARY KEY (person_id, address_relation_code, start_time),
  CONSTRAINT people_adr_started_before_ended CHECK ( (start_time < end_time) OR end_time IS NULL)
);

DELIMITER !!
CREATE OR REPLACE FUNCTION trigger_on_people_addresses()
  RETURNS TRIGGER LANGUAGE plpgsql
AS $function$
DECLARE
  pointInTime TIMESTAMP;
BEGIN
  pointInTime = current_timestamp;
  UPDATE people_addresses_history
  SET end_time = pointInTime
  WHERE end_time IS NULL AND person_id = new.person_id AND address_relation_code = new.address_relation_code;
  IF tg_op = 'DELETE'
  THEN
    INSERT INTO people_addresses_history
      SELECT
        old.*,
        pointInTime,
        NULL;
    RETURN old;
  ELSE
    INSERT INTO people_addresses_history
      SELECT
        new.*,
        pointInTime,
        NULL;
    RETURN new;
  END IF;
END; $function$!!
DELIMITER;

CREATE TRIGGER history_trigger_on_people_addresses
  AFTER INSERT OR UPDATE OR DELETE
  ON people_addresses
  FOR EACH ROW
EXECUTE PROCEDURE trigger_on_people_addresses();


INSERT INTO salutations (salutation_code, description) VALUES
  ('MR', 'Mister'),
  ('MS', 'Miss');


INSERT INTO genders (gender_code, description) VALUES
  ('MALE', 'Male'), ('FEMALE', 'Female'), ('UNKNOWN', 'Unknown');

INSERT INTO addresses (address_id, street_line_1, street_line_2, postal_code, city_name, country_code, district) VALUES
  (1, 'Koophandelsplein 31', NULL, '9000', 'Gent', 'BE', NULL),
  (2, 'August Van OostStreaat 1', NULL, '9050', 'Gent', 'BE', NULL),
  (3, 'Invalidenstraat 102', NULL, '9040', 'Oostakker', 'BE', NULL),
  (4, 'Duivelsteeg 1', NULL, '9000', 'Gent', 'BE', NULL),
  (5, 'Baudeloo kaai ?', NULL, '9000', 'Gent', 'BE', NULL),
  (6, 'Zandloperstraat 102', NULL, '9030', 'Mariakerke', 'BE', NULL),
  (7, 'Herman Lovelingstraat 8', NULL, '9000', 'Nevele', 'BE', NULL),
  (8, 'Jonkvrouw Matte straat 2', NULL, '9000', 'Gent', 'BE', NULL),
  (9, 'Snoekstraat 77', NULL, '9000', 'Gent', 'BE', NULL),
  (10, 'Snoekstraat 10', NULL, '9000', 'Gent', 'BE', NULL);


INSERT INTO people (person_id, salutation_code, name_first, name_middle, name_last, gender_code, birth_day)
VALUES
  (1, 'MR', 'Peter', NULL, 'Muys', 'UNKNOWN', DATE '1972-05-21'),
  (2, 'MS', 'Els', NULL, 'Van Oost', 'FEMALE', DATE '1976-06-16');

UPDATE people
SET gender_code = 'MALE'
WHERE person_id = 1;

INSERT INTO address_relations (address_relation_code, description) VALUES
  ('HOME', 'Home address'),
  ('WORK', 'Work address'),
  ('DELIVERY', 'Delivery address');

INSERT INTO people_addresses (person_id, address_relation_code, address_id)
VALUES (1, 'HOME', 1);

UPDATE people_addresses
SET address_id = 2
WHERE person_id = 1 AND address_relation_code = 'HOME';
UPDATE people_addresses
SET address_id = 3
WHERE person_id = 1 AND address_relation_code = 'HOME';
UPDATE people_addresses
SET address_id = 4
WHERE person_id = 1 AND address_relation_code = 'HOME';
UPDATE people_addresses
SET address_id = 5
WHERE person_id = 1 AND address_relation_code = 'HOME';
UPDATE people_addresses
SET address_id = 6
WHERE person_id = 1 AND address_relation_code = 'HOME';
UPDATE people_addresses
SET address_id = 7
WHERE person_id = 1 AND address_relation_code = 'HOME';
UPDATE people_addresses
SET address_id = 8
WHERE person_id = 1 AND address_relation_code = 'HOME';
UPDATE people_addresses
SET address_id = 9
WHERE person_id = 1 AND address_relation_code = 'HOME';
UPDATE people_addresses
SET address_id = 10
WHERE person_id = 1 AND address_relation_code = 'HOME';

INSERT INTO people_addresses (person_id, address_relation_code, address_id)
VALUES (2, 'HOME', 10);

UPDATE people_addresses
SET address_id = 9
WHERE person_id = 2 AND address_relation_code = 'HOME';
UPDATE people_addresses
SET address_id = 10
WHERE person_id = 2 AND address_relation_code = 'HOME';

INSERT INTO translations_gender (gender_code, language_code, description)
VALUES
  ('MALE', 'nl', 'Man'),
  ('FEMALE', 'nl', 'Vrouw'),
  ('UNKNOWN', 'nl', 'Onbekend'),
  ('MALE', 'en', 'Male'),
  ('FEMALE', 'en', 'Female'),
  ('UNKNOWN', 'en', 'Unknown');

