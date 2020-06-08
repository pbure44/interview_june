CREATE DATABASE contacts;
\c contacts
DROP TABLE IF EXISTS contacts;
DROP SEQUENCE IF EXISTS contact_seq;

CREATE SEQUENCE contact_seq START 100;

CREATE TABLE contacts
(
    id           INTEGER PRIMARY KEY DEFAULT nextval('contact_seq'),
    name         VARCHAR NOT NULL,
    surname      VARCHAR NOT NULL,
    birthday     date    NOT NULL,
    login        VARCHAR NOT NULL,
    password     VARCHAR NOT NULL,
    aboutContact VARCHAR NOT NULL,
    address      VARCHAR NOT NULL
);

INSERT INTO contacts (id, name, surname, birthday, login, password, aboutcontact, address) VALUES (100, 'Pavel', 'Burenok', '1984-12-19', 'burenok', 'asd12345', 'Our new junior developer', 'Kyiv');