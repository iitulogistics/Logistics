CREATE TABLE Country(
	country_id bigserial not null
		constraint country_pkey primary key,
    country_name_kk varchar(255),
	  country_name_ru varchar(255),
	  country_name_en varchar(255)
);