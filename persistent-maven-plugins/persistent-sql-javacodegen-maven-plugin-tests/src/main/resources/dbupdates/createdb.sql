-->>DropAll
DROP TABLE if EXISTS person;
DROP TABLE if EXISTS invoice;
DROP TABLE if EXISTS invoice_line;
DROP TABLE if EXISTS company;



-->>createCompany
CREATE TABLE company (
  id                 BIGSERIAL PRIMARY KEY not null ,
  adres_street       VARCHAR(256)    NOT NULL,
  adres_house_number INT             NOT NULL,
  adres_bus_number   VARCHAR(10),
  adres_postalcode   VARCHAR(15)     NOT NULL,
  adres_city         VARCHAR(50)     NOT NULL,
  adres_country      VARCHAR(80)     NOT NULL
);


-->>createPerson
CREATE TABLE person (
  id           BIGSERIAL PRIMARY KEY not null ,
  user_name    VARCHAR(256)    NOT NULL,
  password     VARCHAR(256)    NOT NULL,
  street       VARCHAR(256)    NOT NULL,
  house_number INT             NOT NULL,
  bus_number   VARCHAR(10),
  postalcode   VARCHAR(15)     NOT NULL,
  city         VARCHAR(50)     NOT NULL,
  country      VARCHAR(80)     NOT NULL

);
-->>createInvoices
CREATE TABLE invoice (
  id              BIGSERIAL PRIMARY KEY not null ,
  invoice_nummer  VARCHAR(20)     NOT NULL,
  from_company_id INT             NOT NULL REFERENCES company,
  to_company_id   INT             NOT NULL REFERENCES company
);
CREATE TABLE invoice_line (
  id         BIGSERIAL PRIMARY KEY not null ,
  invoice_id INT             NOT NULL,
  product    VARCHAR(256)
);

-->>
