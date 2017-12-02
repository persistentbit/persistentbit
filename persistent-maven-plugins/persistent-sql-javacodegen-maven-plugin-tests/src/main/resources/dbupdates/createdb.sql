-->>DropAll
DROP TABLE IF EXISTS invoice;
DROP TABLE IF EXISTS invoice_line;
DROP TABLE IF EXISTS company;
DROP TABLE IF EXISTS person;

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

INSERT INTO company (id, company_name, adres_street, adres_house_number, adres_bus_number, adres_postalcode, adres_city, adres_country, owner_person_id)
VALUES
  (1, 'MuysSoftware', 'Snoekstraat', 77, NULL, '9000', 'Gent', 'BE', 1);

INSERT INTO company (id, company_name, adres_street, adres_house_number, adres_bus_number, adres_postalcode, adres_city, adres_country, owner_person_id)
VALUES
  (2, 'Eau De Mie', 'Snoekstraat', 10, NULL, '9000', 'Gent', 'BE', 2);

INSERT into invoice (invoice_nummer, from_company_id, to_company_id)
    values('2017-01',2,1);

-->>
