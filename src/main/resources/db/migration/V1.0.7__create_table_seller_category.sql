CREATE TABLE Seller_category(
	seller_category_id bigserial not null
		constraint seller_category_pkey
			primary key,
	category_name_kk varchar(256),
	category_name_ru varchar(256),
	category_name_en varchar(256),
  add_info varchar(500)
);