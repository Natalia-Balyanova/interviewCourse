DROP TABLE students IF EXISTS;
CREATE TABLE IF NOT EXISTS students (id bigserial, name VARCHAR(255), mark integer, PRIMARY KEY (id));