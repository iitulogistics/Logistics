CREATE TABLE Orders(
	order_id bigserial not null
		constraint orders_pkey
			primary key,
      order_number integer,
      product_id integer,
      seller_company_id integer,
      order_date date,
      product_count integer,
      unit_price integer,
      total_price integer,
      customer_id integer,
      delivering_status varchar(100)
);