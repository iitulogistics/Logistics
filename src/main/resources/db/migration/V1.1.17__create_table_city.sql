CREATE TABLE City(
	city_id bigserial not null
		constraint city_pkey primary key,
      city_name_kk varchar(255),
	  city_name_ru varchar(255),
	  city_name_en varchar(255),
	  region_id bigserial
);
