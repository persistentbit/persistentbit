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
  adres_street       VARCHAR(256)          NOT NULL,
  adres_house_number INT                   NOT NULL,
  adres_bus_number   VARCHAR(10),
  adres_postalcode   VARCHAR(15)           NOT NULL,
  adres_city         VARCHAR(50)           NOT NULL,
  adres_country      VARCHAR(80)           NOT NULL,
  owner_person_id BIGINT
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
INSERT into person (id, user_name, password, street, house_number, bus_number, postalcode, city, country)
    VALUES
      (1,'PeterMuys','pw','Snoekstraat',77,'','9000','Gent','BE'),
      (2, 'ElsVanOost','pw','Snoekstraat',10,'','9000','Gent','BE')
;
-->>
