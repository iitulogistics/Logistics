ALTER TABLE public.orders ALTER COLUMN order_number TYPE bigint USING order_number::bigint;