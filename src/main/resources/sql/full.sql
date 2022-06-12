BEGIN;
DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO customers (name) VALUES
('Alexander'),
('Max'),
('Tony'),
('Harry Potter'),
('Leonid'),
('Mikhail'),
('Pavel'),
('Daniel');

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title varchar(255), price int);
INSERT INTO products (title, price) VALUES
('iPhone', 105000),
('Book', 150),
('Pencil', 15),
('an apple', 5),
('table', 2000),
('Spoon', 21.5),
('thing', 1),
('Coin', 100),
('Boots', 810),
('Lamp', 175),
('watches', 1102),
('Book 2', 121);

DROP TABLE IF EXISTS customers_products CASCADE;
CREATE TABLE customers_products (product_id bigint, customer_id bigint, FOREIGN KEY (product_id) REFERENCES products (id), FOREIGN KEY (customer_id) REFERENCES customers (id));
INSERT INTO customers_products (product_id, customer_id) VALUES
(1, 4),
(1, 1),
(3, 5),
(4, 2),
(4, 6),
(6, 7),
(7, 2),
(8, 3),
(9, 7),
(10, 8),
(12, 5),
(8, 7);

COMMIT;