CREATE TABLE Seller_company(
	seller_company_id bigserial
		constraint seller_company_pkey
			primary key,
	company_name_kk varchar(256),
	company_name_ru varchar(256),
	company_name_en varchar(256),
	phone varchar(256),
	mobile_phone varchar(256),
	bin varchar(256),
	email varchar(256),
	seller_category_id bigserial
);