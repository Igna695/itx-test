-- brands
INSERT INTO brands VALUES ('1', 'ZARA');

-- products
INSERT INTO products VALUES ('35455', 'Camiseta');

-- priceList
INSERT INTO price_list VALUES ('1', 'Tarifa 1');
INSERT INTO price_list VALUES ('2', 'Tarifa 2');
INSERT INTO price_list VALUES ('3', 'Tarifa 3');
INSERT INTO price_list VALUES ('4', 'Tarifa 4');

-- prices
INSERT INTO prices(brand_id, start_date, end_date, price_list_id, product_id, priority, price, curr) VALUES ('1', '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR');
INSERT INTO prices(brand_id, start_date, end_date, price_list_id, product_id, priority, price, curr) VALUES ('1', '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR');
INSERT INTO prices(brand_id, start_date, end_date, price_list_id, product_id, priority, price, curr) VALUES ('1', '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1, 30.50, 'EUR');
INSERT INTO prices(brand_id, start_date, end_date, price_list_id, product_id, priority, price, curr) VALUES ('1', '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 1, 38.95, 'EUR');