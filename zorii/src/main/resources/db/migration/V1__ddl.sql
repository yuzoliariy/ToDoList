CREATE SCHEMA IF NOT EXISTS test;

CREATE TABLE IF NOT EXISTS test.task
(
    id serial primary key,
    name varchar ( 255 ),
    description text,
    status varchar ( 12 )
);

