CREATE TABLE Login(
	id bigserial not null
		constraint login_pkey
			primary key,
	username varchar(256),
	password varchar(256),
	company_id bigserial,
	client_id bigserial,
	role_id bigserial
);