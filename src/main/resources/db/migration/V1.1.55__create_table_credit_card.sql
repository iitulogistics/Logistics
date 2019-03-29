CREATE TABLE Credit_card(
	cc_id bigserial not null
		constraint credit_card_pkey
			primary key,
      cc_number integer,
      holder_name varchar(255),
      expire_date date
      );