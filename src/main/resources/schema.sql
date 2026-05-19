CREATE DATABASE IF NOT EXISTS student_db;

USE student_db;

CREATE TABLE IF NOT EXISTS students (
    id      BIGINT       NOT NULL AUTO_INCREMENT,
    name    VARCHAR(100) NOT NULL,
    email   VARCHAR(120) NOT NULL UNIQUE,
    department VARCHAR(80) NOT NULL,
    phone   VARCHAR(10)  NOT NULL,
    PRIMARY KEY (id)
);
