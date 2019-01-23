CREATE TABLE Warehouse(
	warehouse_id bigserial not null
		constraint warehouse_pkey
			primary key,
	wh_name_kk varchar(256),
	wh_name_ru varchar(256),
	wh_name_en varchar(256),
  wh_address_id integer,
  seller_company_id integer,
  add_info varchar(500)
);
