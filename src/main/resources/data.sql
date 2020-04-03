--INSERT INTO customer_tbl (id, name, created_at, updated_at) VALUES(1, 'test customer 1', CURRENT_DATE(), NULL);
--INSERT INTO order_tbl (id, customer_id, created_at, updated_at) VALUES(1, 1, CURRENT_DATE(), NULL);
--INSERT INTO taco_tbl (id, name, order_id, created_at, updated_at) VALUES(1, 'test taco 1', 1, CURRENT_DATE(), NULL);
--INSERT INTO ingredient_tbl (id, name, taco_id, created_at, updated_at) VALUES(1, 'test ingredient 1', 1, CURRENT_DATE(), NULL);
SELECT CURRENT_DATE

