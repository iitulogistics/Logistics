DROP TABLE warehouse;
CREATE TABLE warehouse(
    warehouse_id BigSerial primary key,
    wh_name_kk varchar(50),
    wh_name_ru varchar(50),
    wh_name_en varchar(50),
    seller_company_id BigSerial,
    address_id BigSerial
);