CREATE TABLE Inhabited_locality(
	inh_locality_id bigserial not null
		constraint inh_locality_pkey primary key,
    inh_locality_name_kk varchar(255),
	  inh_locality_name_ru varchar(255),
	  inh_locality_name_en varchar(255),
	  district_id bigserial
);