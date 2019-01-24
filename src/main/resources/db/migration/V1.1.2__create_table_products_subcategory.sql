CREATE TABLE Products_subcategory(
	product_subcategory_id bigserial not null
		constraint product_subcategory_pkey
			primary key,
	subcategory_name_kk varchar(256),
	subcategory_name_ru varchar(256),
	subcategory_name_en varchar(256),
	product_category_id integer,
  subcategory_add_info varchar(500)
);