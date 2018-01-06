-->>create
CREATE TABLE pbtest.gen_data (
  gen_data_id INTEGER          NOT NULL PRIMARY KEY,
  a_int       INTEGER          NOT NULL,
  a_short     SMALLINT         NOT NULL,
  a_long      BIGINT           NOT NULL,
  a_num       NUMERIC(6, 2)    NOT NULL,
  a_double    DOUBLE PRECISION NOT NULL,
  a_real      REAL             NOT NULL,
  a_bool      BOOLEAN          NOT NULL,
  a_date      DATE             NOT NULL,
  a_time      TIME             NOT NULL,
  a_timestamp TIMESTAMP        NOT NULL,
  a_string    VARCHAR(500)     NOT NULL
);

INSERT INTO pbtest.gen_data
(gen_data_id, a_int, a_short, a_long, a_num, a_double, a_real, a_bool, a_date, a_time, a_timestamp, a_string) VALUES
  (100, 1, 2, 3, 4, 5, 6, TRUE, current_date, current_time, current_timestamp, 'Hello');
-->>