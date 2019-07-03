CREATE TABLE basket(
	basket_id bigserial not null
		constraint basket_pkey primary key,
      login_id bigserial,
	  product_id bigserial
);