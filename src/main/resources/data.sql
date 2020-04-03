INSERT INTO order_tbl (id, created_at, updated_at) VALUES(101, CURRENT_DATE(), NULL);
INSERT INTO taco_tbl (id, name, order_id, created_at, updated_at) VALUES(10, 'test taco 1', 101, CURRENT_DATE(), NULL);
INSERT INTO ingredient_tbl (id, name, taco_id, created_at, updated_at) VALUES(1, 'test ingredient 1', 10, CURRENT_DATE(), NULL);
