CREATE TABLE Special_characteristic(
	special_characterisric_id bigserial not null
		constraint sp_characterisric_pkey
			primary key,
      characteristic_name_kk varchar(255),
      characteristic_name_ru varchar(255),
      characteristic_name_en varchar (255),
      add_info varchar (255)
     );