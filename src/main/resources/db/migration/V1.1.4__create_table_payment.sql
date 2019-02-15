CREATE TABLE Payment(
	payment_id bigserial not null
		constraint payment_pkey
			primary key,
      payment_amount integer,
      payment_state integer,
      timestamp timestamp,
      order_id bigserial,
      cc_id bigserial,
      payment_status integer  -- 0 не оплачен 1 оплаченный
);