CREATE TABLE Shipper(
	shipper_id bigserial not null
		constraint shipper_pkey
			primary key,
	shipper_name_kk varchar(256),
	shipper_name_ru varchar(256),
	shipper_name_en varchar(256),
	phone_number varchar(256),
	mobile_phone varchar(256),
	bin varchar(256),
	email varchar(256),
  address varchar(256)
);