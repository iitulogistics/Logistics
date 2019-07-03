CREATE TABLE params(
	params_id bigserial not null
		constraint params_pkey primary key,
      params_name varchar (255)
);