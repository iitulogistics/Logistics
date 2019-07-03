CREATE TABLE pr_params(
	pr_params_id bigserial not null
		constraint pr_params_pkey primary key,
		product_id bigserial,
		params_id bigserial,
		value_p varchar (255)
);