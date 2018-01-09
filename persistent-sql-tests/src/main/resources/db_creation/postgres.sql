-->>init
DROP SCHEMA IF EXISTS pbtest CASCADE;
CREATE SCHEMA pbtest;

-->>create
SET search_path TO pbtest;

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


-- People with history

--- ISO-639-1 2 char language code
CREATE DOMAIN lang_iso_code AS VARCHAR(2)
  CHECK ( value SIMILAR TO '[a-z][a-z]');

CREATE DOMAIN gender_code AS VARCHAR(10);
CREATE DOMAIN salutation_code AS VARCHAR(10);

CREATE TABLE genders (
  gender_code GENDER_CODE NOT NULL,
  PRIMARY KEY (gender_code),

  description VARCHAR     NOT NULL
);
CREATE TABLE translations_gender (
  gender_code   GENDER_CODE   NOT NULL REFERENCES genders (gender_code)
  ON DELETE CASCADE ON UPDATE CASCADE,
  language_code LANG_ISO_CODE NOT NULL,
  PRIMARY KEY (gender_code, language_code),

  description   VARCHAR       NOT NULL
);
CREATE TABLE salutations (
  salutation_code SALUTATION_CODE NOT NULL,
  PRIMARY KEY (salutation_code),

  description     VARCHAR         NOT NULL
);

CREATE TABLE translations_salutation (
  salutation_code SALUTATION_CODE NOT NULL REFERENCES salutations (salutation_code)
  ON DELETE CASCADE ON UPDATE CASCADE,
  language_code   LANG_ISO_CODE   NOT NULL,
  PRIMARY KEY (salutation_code, language_code),

  description     VARCHAR         NOT NULL
);

-- country code: ISO 3166-1
CREATE DOMAIN country_iso_code AS VARCHAR(2)
  CHECK (value SIMILAR TO '[A-Z][A-Z]');

CREATE TABLE translations_country (
  country_code  COUNTRY_ISO_CODE NOT NULL,
  language_code LANG_ISO_CODE    NOT NULL,
  name          VARCHAR          NOT NULL,
  PRIMARY KEY (country_code, language_code)
);

CREATE DOMAIN address_relation_code AS VARCHAR(10);

CREATE TABLE address_relations (
  address_relation_code ADDRESS_RELATION_CODE NOT NULL,
  PRIMARY KEY (address_relation_code),
  description           VARCHAR               NOT NULL
);

CREATE TABLE addresses (
  address_id    BIGSERIAL        NOT NULL PRIMARY KEY,
  street_line_1 VARCHAR          NOT NULL,
  street_line_2 VARCHAR          NULL,
  postal_code   VARCHAR          NOT NULL,
  city_name     VARCHAR          NOT NULL,
  country_code  COUNTRY_ISO_CODE NOT NULL,
  district      VARCHAR          NULL
);

CREATE TABLE people (
  person_id       BIGSERIAL       NOT NULL PRIMARY KEY,
  salutation_code SALUTATION_CODE NOT NULL REFERENCES salutations (salutation_code) ON UPDATE CASCADE,
  name_first      VARCHAR         NOT NULL,
  name_middle     VARCHAR         NULL,
  name_last       VARCHAR         NOT NULL,
  gender_code     GENDER_CODE     NOT NULL REFERENCES genders (gender_code) ON UPDATE CASCADE
);


CREATE TABLE people_addresses_history (
  person_id             BIGSERIAL                 NOT NULL REFERENCES people (person_id) ON DELETE CASCADE,
  address_relation_code ADDRESS_RELATION_CODE     NOT NULL REFERENCES address_relations (address_relation_code) ON UPDATE CASCADE,
  start_date            DATE DEFAULT CURRENT_DATE NOT NULL,
  PRIMARY KEY (person_id, start_date),
  end_date              TIMESTAMP,
  address_id            BIGINT                    NOT NULL REFERENCES addresses,
  CONSTRAINT people_adr_started_before_ended CHECK ( start_date < end_date),
  CONSTRAINT people_adr_end_time_open_interval CHECK (end_date =
                                                      CAST(end_date AS DATE) +
                                                      INTERVAL '23 hours 59 minutes 59.9999999 seconds')
  -- ,
  --   CONSTRAINT people_adr_no_date_overlaps CHECK (NOT EXISTS(SELECT *
  --                                                            FROM people_addresses AS H1, Calendar AS C1
  --                                                            WHERE C1.cal_date BETWEEN H1.start_date AND H1.end_date
  --                                                            GROUP BY people_addresses
  --                                                            HAVING COUNT(*) > 1)),
  --   CONSTRAINT people_adr_only_one_current_status CHECK (NOT EXISTS(SELECT *
  --                                                                   FROM people_addresses AS H1
  --                                                                   WHERE H1.end_date IS NULL
  --                                                                   GROUP BY foo_key
  --                                                                   HAVING COUNT(*) > 1))
);

-->>