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

-->>