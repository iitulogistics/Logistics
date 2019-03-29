CREATE TABLE Addresses(
	address_id bigserial not null
		constraint address_pkey primary key,
		district_id bigserial,
		inh_locality_id bigserial,
	  street_name_kk varchar(255),
	  street_name_ru varchar(255),
 	  street_name_en varchar(255),
    building_number varchar(25),
    flat_number varchar(25),
    zip_code varchar(25),
    address_assign integer
);