CREATE TABLE company(
	id bigserial not null
		constraint company_pkey
			primary key,
	name_kk varchar(256),
	name_ru varchar(256),
	name_en varchar(256),
	company_phone varchar(256)
);