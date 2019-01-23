CREATE TABLE Roles(
	role_id bigserial not null
		constraint roles_pkey
			primary key,
	role_name varchar(256),
	description varchar (500)
);