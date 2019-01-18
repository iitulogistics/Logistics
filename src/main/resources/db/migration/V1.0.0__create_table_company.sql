CREATE TABLE Seller_company(
	id bigserial not null
		constraint company_pkey
			primary key,
	name_kk varchar(256),
	name_ru varchar(256),
	name_en varchar(256),
	phone varchar(256),
	mobile_phone varchar(256),
	bin varchar(256),
	email varchar(256)
);