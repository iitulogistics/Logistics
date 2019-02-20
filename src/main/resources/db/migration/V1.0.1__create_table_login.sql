CREATE TABLE Login(
	login_id bigserial not null
		constraint login_pkey
			primary key,
	username varchar(256),
	password varchar(256),
	roles_id bigserial,
	customer_id bigserial,
	seller_company_id bigserial,
	shipper_id bigserial
);