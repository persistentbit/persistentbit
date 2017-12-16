-->>DropAll
DROP TABLE IF EXISTS invoice;
DROP TABLE IF EXISTS invoice_line;
DROP TABLE IF EXISTS company;
DROP TABLE IF EXISTS person;



DROP TABLE IF EXISTS auth_user_roles;
DROP TABLE IF EXISTS auth_user_remember_me;
DROP TABLE IF EXISTS auth_user;
DROP TABLE IF EXISTS auth_app;

DROP TABLE IF EXISTS all_generic;


-->>genericDataTypes

CREATE TABLE all_generic (
  id_part1              VARCHAR(256) NOT NULL,
  id_part2              BIGINT       NOT NULL,
  ser_small             SMALLSERIAL,
  ser                   SERIAL,
  ser_big               BIGSERIAL,
  an_integer            INTEGER,
  a_bigint              BIGINT,
  a_decimal_7_2         DECIMAL(7, 2),
  a_numeric_6           NUMERIC(6),
  a_numeric             NUMERIC,
  a_real                REAL,
  a_double              DOUBLE PRECISION,
  an_int2               INT2,
  an_int4               INT4,
  an_int8 int8,
  -- a_money money,
  -- an_enum ENUM_TEST,
  -- an_enum_in_public PUBLIC.ENUM_TEST_IN_PUBLIC_SCHEMA,
  -- a_struct FULL_NAME,
  -- a_domain us_postal_code,
  a_varchar             VARCHAR,
  a_varchar_10          VARCHAR(10),
  a_text                TEXT,
  a_char                CHAR,
  a_char_10             CHAR(10),
  a_bytea               BYTEA,
  a_timestamp_3         TIMESTAMP(3),
  a_timestamp           TIMESTAMP,
  a_timestamp_with_zone TIMESTAMP WITH TIME ZONE,
  a_date                DATE,
  a_time                TIME,
  a_time_with_zone      TIME WITH TIME ZONE,
  -- an_interval           INTERVAL,
  a_boolean             BOOLEAN,
  --a_cidr cidr,
  --an_inet inet,
  --a_macaddr macaddr,
  a_bit                 BIT,
  a_bit_40              BIT(40),
  a_bit_varying         BIT VARYING,
  --a_tsvector tsvector,
  --a_tsquery tsquery,
  --an_uuid uuid,
  --a_test_domain_interval test_domain_interval,
  --an_xml xml,
  --a_json json

  PRIMARY KEY (id_part1, id_part2)
)



-->>Authentication


CREATE TABLE auth_app (
  id                       BIGSERIAL PRIMARY KEY NOT NULL,
  name                     VARCHAR(256)          NOT NULL,
  password                 VARCHAR(256)          NOT NULL,
  is_root                  BOOL                  NOT NULL,
  is_active                BOOL                  NOT NULL DEFAULT TRUE,
  max_wrong_password_count INT                            DEFAULT 3
);
CREATE UNIQUE INDEX idx_app_name
  ON auth_app (name);
COMMENT ON TABLE auth_app IS 'Contains all the applications that can connect to this authentication service.';
COMMENT ON COLUMN auth_app.is_root IS 'The Root application can change data for other applications';


CREATE TABLE auth_user (
  id                         BIGSERIAL PRIMARY KEY NOT NULL,
  auth_app_id                BIGINT                NOT NULL REFERENCES auth_app,
  user_name                  VARCHAR(256)          NOT NULL,
  password                   VARCHAR(256)          NOT NULL,
  wrong_password_count       INT                   NOT NULL DEFAULT 0,
  created                    TIMESTAMP                      DEFAULT CURRENT_TIMESTAMP ,
  last_login                 TIMESTAMP                      DEFAULT CURRENT_TIMESTAMP ,
  verified                   TIMESTAMP,
  reset_password_code        VARCHAR(256),
  reset_password_valid_until TIMESTAMP,
  verify_code                VARCHAR(256),
  verify_code_valid_until    TIMESTAMP
);
CREATE UNIQUE INDEX idx_users_appId_user_name
  ON auth_user (auth_app_id, user_name);

CREATE TABLE auth_user_remember_me (
  id            BIGSERIAL PRIMARY KEY NOT NULL,
  auth_user_id  BIGINT                NOT NULL REFERENCES auth_user,
  code          VARCHAR(256)          NOT NULL,
  valid_until   TIMESTAMP             NOT NULL,
  password_code VARCHAR(256)          NOT NULL
);
CREATE UNIQUE INDEX idx_users_remember_me_code
  ON auth_user_remember_me (auth_user_id, code);



-->>createPerson
CREATE TABLE person (
  id           BIGSERIAL PRIMARY KEY NOT NULL,
  user_name    VARCHAR(256)          NOT NULL,
  password     VARCHAR(256)          NOT NULL,
  street       VARCHAR(256)          NOT NULL,
  house_number INT                   NOT NULL,
  bus_number   VARCHAR(10),
  postalcode   VARCHAR(15)           NOT NULL,
  city         VARCHAR(50)           NOT NULL,
  country      VARCHAR(80)           NOT NULL

);

-->>createCompany
CREATE TABLE company (
  id                 BIGSERIAL PRIMARY KEY NOT NULL,
  company_name       VARCHAR(256)          NOT NULL,
  adres_street       VARCHAR(256)          NOT NULL,
  adres_house_number INT                   NOT NULL,
  adres_bus_number   VARCHAR(10),
  adres_postalcode   VARCHAR(15)           NOT NULL,
  adres_city         VARCHAR(50)           NOT NULL,
  adres_country      VARCHAR(80)           NOT NULL,
  owner_person_id    BIGINT
);

-->>createInvoices
CREATE TABLE invoice (
  id              BIGSERIAL PRIMARY KEY NOT NULL,
  invoice_nummer  VARCHAR(20)           NOT NULL,
  from_company_id BIGINT                NOT NULL REFERENCES company,
  to_company_id   BIGINT                NOT NULL REFERENCES company
);
CREATE TABLE invoice_line (
  id         BIGSERIAL PRIMARY KEY NOT NULL,
  invoice_id BIGINT                NOT NULL,
  product    VARCHAR(256)
);

-->>insertTestData
INSERT INTO person (id, user_name, password, street, house_number, bus_number, postalcode, city, country)
VALUES
  (1, 'PeterMuys', 'pw', 'Snoekstraat', 77, '', '9000', 'Gent', 'BE'),
  (2, 'ElsVanOost', 'pw', 'Snoekstraat', 10, '', '9000', 'Gent', 'BE');

ALTER SEQUENCE person_id_seq RESTART WITH 100;

INSERT INTO company (id, company_name, adres_street, adres_house_number, adres_bus_number, adres_postalcode, adres_city, adres_country, owner_person_id)
VALUES
  (1, 'MuysSoftware', 'Snoekstraat', 77, NULL, '9000', 'Gent', 'BE', 1);

INSERT INTO company (id, company_name, adres_street, adres_house_number, adres_bus_number, adres_postalcode, adres_city, adres_country, owner_person_id)
VALUES
  (2, 'Eau De Mie', 'Snoekstraat', 10, NULL, '9000', 'Gent', 'BE', 2);
ALTER SEQUENCE company_id_seq RESTART WITH 100;

INSERT into invoice (invoice_nummer, from_company_id, to_company_id)
    values('2017-01',2,1);

ALTER SEQUENCE invoice_id_seq RESTART WITH 100;
-->>