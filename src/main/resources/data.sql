-- Insert customer data
INSERT INTO customer_tbl (id, name, created_at, updated_at) VALUES(1, 'Fissara Company', CURRENT_DATE(), NULL);
-- First order with 1 taco and 2 ingredients
INSERT INTO order_tbl (id, customer_id, created_at, updated_at) VALUES(1, 1, CURRENT_DATE(), NULL);
INSERT INTO taco_tbl (id, name, order_id, created_at, updated_at) VALUES(1, 'Carne Asada Tacos', 1, CURRENT_DATE(), NULL);
INSERT INTO ingredient_tbl (id, name, taco_id, created_at, updated_at) VALUES(1, 'Strips of beef are marinated in lime and pepper, then quickly sautéed', 1, CURRENT_DATE(), NULL);
INSERT INTO ingredient_tbl (id, name, taco_id, created_at, updated_at) VALUES(2, 'Soft corn tortillas with tomatillo sauce', 1, CURRENT_DATE(), NULL);

-- Second order with 2 taco's and and total of 3 ingredients
INSERT INTO order_tbl (id, customer_id, created_at, updated_at) VALUES(2, 1, CURRENT_DATE(), NULL);
INSERT INTO taco_tbl (id, name, order_id, created_at, updated_at) VALUES(2, 'Dee''s Roast Pork for Tacos', 2, CURRENT_DATE(), NULL);
INSERT INTO ingredient_tbl (id, name, taco_id, created_at, updated_at) VALUES(3, 'Pork shoulder roasts slowly with diced green chilies', 2, CURRENT_DATE(), NULL);
INSERT INTO ingredient_tbl (id, name, taco_id, created_at, updated_at) VALUES(4, 'Seasonings for the meat just falls apart', 2, CURRENT_DATE(), NULL);
INSERT INTO taco_tbl (id, name, order_id, created_at, updated_at) VALUES(3, 'Tacos in Pasta Shells', 2, CURRENT_DATE(), NULL);
INSERT INTO ingredient_tbl (id, name, taco_id, created_at, updated_at) VALUES(5, 'Jumbo pasta shells are stuffed with seasoned ground beef and cream cheese and baked with taco sauce', 3, CURRENT_DATE(), NULL);


