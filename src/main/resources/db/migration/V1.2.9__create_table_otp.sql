CREATE TABLE otp
(
    id serial PRIMARY KEY NOT NULL,
    mobile_phone varchar(20),
    otp varchar(10)
);