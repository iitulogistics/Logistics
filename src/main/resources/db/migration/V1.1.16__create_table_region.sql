CREATE TABLE Region(
	region_id bigserial not null
		constraint region_pkey primary key,
      region_name_kk varchar(255),
	  region_name_ru varchar(255),
	  region_name_en varchar(255),
	  country_id bigserial
);