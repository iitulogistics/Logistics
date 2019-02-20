CREATE TABLE address(
    address_id BigSerial primary key,
    district varchar(20),
    street varchar(20),
    house varchar(20),
    flat integer
);