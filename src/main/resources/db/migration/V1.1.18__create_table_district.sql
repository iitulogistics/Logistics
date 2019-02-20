CREATE TABLE District(
	district_id bigserial not null
		constraint district_pkey primary key,
      district_name_kk varchar(255),
	  district_name_ru varchar(255),
	  district_name_en varchar(255),
	  region_id bigserial,
	  city_id bigserial
);
