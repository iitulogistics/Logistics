CREATE TABLE Products(
	product_id bigserial not null
		constraint product_pkey
			primary key,
	product_name_kk varchar(256),
	product_name_ru varchar(256),
	product_name_en varchar(256),
	product_subcategory_id integer,
	unique_id_number varchar(50),
	serial_number varchar(50),
	manufacturer varchar(256),
	size varchar(256),
	weight integer,
	price integer,
	product_description varchar(500)
);
