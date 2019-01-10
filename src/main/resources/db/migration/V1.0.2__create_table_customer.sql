CREATE TABLE customer(
	id bigserial not null
		constraint cutomer_pkey
			primary key,
	mobile_phone varchar(256),
	email varchar(256),
	name varchar(256),
	address varchar(256)
);