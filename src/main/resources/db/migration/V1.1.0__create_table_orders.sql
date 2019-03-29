CREATE TABLE Orders(
	order_id bigserial not null
		constraint orders_pkey
			primary key,
      order_number integer,
      product_id bigserial,
      seller_company_id bigserial,
      order_date date,
      product_count integer,
      unit_price integer,
      total_price integer,
      customer_id bigserial,
      delivering_status varchar(100)
);