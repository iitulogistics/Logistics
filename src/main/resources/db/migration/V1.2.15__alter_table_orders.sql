ALTER TABLE public.orders ALTER COLUMN order_date TYPE timestamp USING order_date::timestamp;
