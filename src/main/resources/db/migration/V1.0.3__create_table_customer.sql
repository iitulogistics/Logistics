CREATE TABLE Customer(
	customer_id bigserial not null
		constraint customer_pkey
			primary key,
	customer_name_kk varchar(256),
	customer_name_ru varchar(256),
	customer_name_en varchar(256),
	iin_or_bin varchar(12),
	phone_number varchar(15),
	mobile_phone varchar(256),
	email varchar(256),
	address varchar(256),
	add_info varchar(500)
);