CREATE TABLE Warehouse_address(
	wh_address_id bigserial not null
		constraint wh_address_pkey
			primary key,
	region_name varchar(256),
	city_name varchar(256),
	street_name varchar(256),
  zip_code varchar(255)
);